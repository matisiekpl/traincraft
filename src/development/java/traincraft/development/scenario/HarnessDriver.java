package traincraft.development.scenario;

import net.minecraft.client.CloudStatus;
import net.minecraft.client.GraphicsPreset;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ParticleStatus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.WorldDataConfiguration;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.levelgen.presets.WorldPresets;
import net.minecraft.world.phys.Vec3;

import traincraft.Traincraft;
import traincraft.bootstrap.BlockRegistry;
import traincraft.track.Placement;
import traincraft.track.TrackPlacementPlanner;
import traincraft.track.TrackPlacer;
import traincraft.track.TrackPlan;
import traincraft.track.TrackType;
import traincraft.track.block.TrackBlockEntity;
import traincraft.vehicle.entity.LocomotiveEntity;
import traincraft.vehicle.entity.RollingStockEntity;
import traincraft.vehicle.entity.SteamLocomotiveEntity;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class HarnessDriver {

    private enum State {
        WAIT_TITLE,
        CREATE_WORLD,
        WAIT_LEVEL,
        APPLY_OPTIONS,
        RUN_SETUP,
        BUILD,
        RUN_STEPS,
        REPORT,
        QUIT,
        DONE
    }

    private static final String LEVEL_ID = "tc_harness";

    /** A standing player's eye, which is where a scenario's camera Y refers to. */
    private static final double EYE_HEIGHT = 1.62;

    /** Ticks to let chunks generate and sync before writing any blocks. */
    private static final int CHUNK_SETTLE_TICKS = 60;

    private final Scenario scenario;
    private final Path outDir;
    private final HarnessReport report;

    /** One row per frame, for the questions a screenshot cannot answer. See {@link MotionTrace}. */
    private final MotionTrace motion = new MotionTrace();

    private State state = State.WAIT_TITLE;
    private int ticksInState;
    private int setupIndex;
    private int stepIndex;

    /**
     * Ticks left to settle before the current step is shot; negative means the step has not been
     * applied yet.
     *
     * <p>Must start negative. Starting at zero made the very first step skip {@code applyStep}
     * entirely and shoot immediately, so every scenario's first screenshot was taken from the world
     * spawn instead of the requested camera -- which then made its silhouette mask cover the whole
     * frame.
     */
    private int settleRemaining = -1;

    /** Entities spawned by this scenario, so an "empty" step can remove exactly them. */
    private final List<Entity> spawnedEntities = new ArrayList<>();

    /** The subset of those that can be driven, so a step's throttle has somewhere to go. */
    private final List<LocomotiveEntity> locomotives = new ArrayList<>();

    /** Positions written by placeTrack, so an "empty" step can clear exactly them. */
    private final List<BlockPos> placedTracks = new ArrayList<>();

    /**
     * Assertions are evaluated right after BUILD, not at REPORT: a scenario's "empty" step
     * deliberately tears the scene down, so asserting afterwards would check the wrong world.
     */
    private final List<Map<String, Object>> assertionResults = new ArrayList<>();

    /**
     * The camera the current step asked for, re-asserted while settling.
     *
     * <p>Issuing the teleport once is not enough: the camera can still be a few ticks behind, or
     * drift, and a shot that does not sit at exactly the same viewpoint as its empty baseline
     * produces a silhouette mask covering the whole frame instead of the subject. The reference rig
     * holds its camera the same way, so both sides behave identically.
     */
    private double @org.jspecify.annotations.Nullable [] currentCamera;

    /** Bounded so a per-tick re-teleport cannot flood the log. */
    private int teleportsLogged;

    /** Set by the tick thread, consumed by the frame handler. */
    private volatile String pendingShot;

    private volatile boolean shotDone;

    public HarnessDriver(Scenario scenario, Path outDir) {
        this.scenario = scenario;
        this.outDir = outDir;
        this.report = new HarnessReport(scenario, outDir);
    }

    public boolean isFinished() {
        return state == State.DONE;
    }

    // ------------------------------------------------------------------ tick

    public void tick(Minecraft mc) {
        ticksInState++;
        try {
            switch (state) {
                case WAIT_TITLE -> waitTitle(mc);
                case CREATE_WORLD -> createWorld(mc);
                case WAIT_LEVEL -> waitLevel(mc);
                case APPLY_OPTIONS -> applyOptions(mc);
                case RUN_SETUP -> runSetup(mc);
                case BUILD -> build(mc);
                case RUN_STEPS -> runSteps(mc);
                case REPORT -> writeReport(mc);
                case QUIT -> quit(mc);
                case DONE -> {}
            }
        } catch (RuntimeException e) {
            Traincraft.LOGGER.error("Harness failed in state {}", state, e);
            report.fail(state.name(), e.toString());
            state = State.REPORT;
        }
    }

    private void enter(State next) {
        Traincraft.LOGGER.info("Harness: {} -> {}", state, next);
        state = next;
        ticksInState = 0;
    }

    /**
     * Waits for the client to settle on a menu before touching anything.
     *
     * <p>Deliberately not {@code instanceof TitleScreen}: in a dev launch the first stable screen
     * can be an accessibility prompt, a Realms notification or a pack-mismatch dialog, and pinning
     * to one class hangs the run forever. What actually matters is that no level is loaded, no
     * overlay is still running, and some screen is up and has stayed up.
     */
    private void waitTitle(Minecraft mc) {
        if (ticksInState % 40 == 0) {
            Traincraft.LOGGER.info(
                    "Harness waiting for menu: screen={} overlay={} level={}",
                    mc.gui.screen(),
                    mc.gui.overlay(),
                    mc.level);
        }
        boolean settled = mc.level == null && mc.gui.overlay() == null && mc.gui.screen() != null;
        if (settled && ticksInState > 20) {
            enter(State.CREATE_WORLD);
        } else if (ticksInState > 20 * 180) {
            throw new IllegalStateException(
                    "client never reached a stable menu; last screen was "
                            + mc.gui.screen()
                            + " overlay "
                            + mc.gui.overlay());
        }
    }

    private void createWorld(Minecraft mc) {
        File saves = mc.getLevelSource().getBaseDir().toFile();
        File existing = new File(saves, LEVEL_ID);
        if (existing.exists()) {
            deleteRecursively(existing);
        }
        LevelSettings settings =
                new LevelSettings(
                        LEVEL_ID,
                        GameType.CREATIVE,
                        LevelSettings.DifficultySettings.DEFAULT,
                        true,
                        WorldDataConfiguration.DEFAULT);
        // Flat in every dimension, fixed seed, no structures, no bonus chest -- the flattest
        // deterministic backdrop the vanilla presets offer.
        WorldOptions options = new WorldOptions(scenario.seed, false, false);
        mc.createWorldOpenFlows()
                .createFreshLevel(
                        LEVEL_ID,
                        settings,
                        options,
                        WorldPresets::createTestWorldDimensions,
                        mc.gui.screen());
        enter(State.WAIT_LEVEL);
    }

    private void waitLevel(Minecraft mc) {
        if (mc.level != null
                && mc.player != null
                && mc.getSingleplayerServer() != null
                && mc.gui.screen() == null) {
            enter(State.APPLY_OPTIONS);
        } else if (ticksInState > 20 * 120) {
            throw new IllegalStateException("world did not load within 120s");
        }
    }

    /**
     * Pins every setting that could make two runs differ. The graphics backend ({@code
     * preferredGraphicsBackend}) is deliberately not touched: in 26.2 it only takes effect at
     * startup, so it belongs in options.txt, not here. The report records which backend was
     * actually in use.
     */
    private void applyOptions(Minecraft mc) {
        var o = mc.options;
        var c = scenario.client;
        o.guiScale().set(c.guiScale);
        o.fov().set((int) c.fov);
        o.renderDistance().set(c.renderDistance);
        o.graphicsPreset().set(parseEnum(GraphicsPreset.class, c.graphics, GraphicsPreset.FANCY));
        o.bobView().set(c.viewBobbing);
        o.entityShadows().set(c.entityShadows);
        o.cloudStatus().set(c.clouds ? CloudStatus.FANCY : CloudStatus.OFF);
        o.particles().set(parseEnum(ParticleStatus.class, c.particles, ParticleStatus.MINIMAL));
        // A windowed client pauses when it loses focus, and a paused client photographs its own
        // pause menu across the middle of the frame. Nothing about a scripted capture wants that.
        o.pauseOnLostFocus = false;
        // hideGui left Options in 26.2; the HUD is toggled on the Hud object itself.
        if (c.hideGui != mc.gui.hud.isHidden()) {
            mc.gui.hud.toggle();
        }
        o.save();

        // Report anything that did not stick rather than letting it quietly differ between runs.
        // Render distance in particular can be overridden once a level is loaded.
        warnIfDifferent("guiScale", c.guiScale, o.guiScale().get());
        warnIfDifferent("fov", (int) c.fov, o.fov().get());
        warnIfDifferent("renderDistance", c.renderDistance, o.renderDistance().get());
        warnIfDifferent(
                "particles",
                parseEnum(ParticleStatus.class, c.particles, ParticleStatus.MINIMAL),
                o.particles().get());
        warnIfDifferent("hudHidden", c.hideGui, mc.gui.hud.isHidden());
        Traincraft.LOGGER.info(
                "Options applied: guiScale={} fov={} renderDistance={} preset={} particles={}"
                        + " hudHidden={}",
                o.guiScale().get(),
                o.fov().get(),
                o.renderDistance().get(),
                o.graphicsPreset().get(),
                o.particles().get(),
                mc.gui.hud.isHidden());
        enter(State.RUN_SETUP);
    }

    private void warnIfDifferent(String name, Object requested, Object actual) {
        if (!requested.equals(actual)) {
            String message =
                    name + ": requested " + requested + " but the client reports " + actual;
            Traincraft.LOGGER.warn("Harness option did not stick -- {}", message);
            report.optionDrift(message);
        }
    }

    private static <E extends Enum<E>> E parseEnum(Class<E> type, String name, E fallback) {
        for (E value : type.getEnumConstants()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return fallback;
    }

    private void runSetup(Minecraft mc) {
        // Two ticks between commands: the server processes them asynchronously and a gamerule
        // that has not landed yet can change what the next command does.
        if (ticksInState % 2 != 0) {
            return;
        }
        if (setupIndex >= scenario.setup.size()) {
            enter(State.BUILD);
            return;
        }
        String command = translateGameRules(scenario.setup.get(setupIndex++));
        if (mc.player != null) {
            mc.player.connection.sendCommand(command);
        }
    }

    private static String translateGameRules(String command) {
        if (!command.startsWith("gamerule ")) {
            return command;
        }
        String[] parts = command.split(" ");
        if (parts.length < 2) {
            return command;
        }
        if ("doFireTick".equals(parts[1])) {
            // No longer a switch: 26.2 expresses it as a radius around the player, and -1 is off.
            return "false".equals(parts[parts.length - 1])
                    ? "gamerule fire_spread_radius_around_player -1"
                    : "gamerule fire_spread_radius_around_player 128";
        }
        String renamed = GAME_RULES.get(parts[1]);
        if (renamed == null) {
            return command;
        }
        parts[1] = renamed;
        return String.join(" ", parts);
    }

    private static final Map<String, String> GAME_RULES =
            Map.of(
                    "doDaylightCycle", "advance_time",
                    "doWeatherCycle", "advance_weather",
                    "doMobSpawning", "spawn_mobs",
                    "randomTickSpeed", "random_tick_speed",
                    "sendCommandFeedback", "send_command_feedback",
                    "keepInventory", "keep_inventory",
                    "doMobGriefing", "mob_griefing",
                    "commandBlockOutput", "command_block_output",
                    "logAdminCommands", "log_admin_commands");

    private void build(Minecraft mc) {
        // A level reports itself loaded before its chunks are generated and synced; writing
        // blocks into chunks that do not exist yet silently loses them.
        if (ticksInState < CHUNK_SETTLE_TICKS) {
            return;
        }
        MinecraftServer server = mc.getSingleplayerServer();
        if (server == null) {
            throw new IllegalStateException("no integrated server");
        }
        server.execute(
                () -> {
                    ServerLevel level = server.overworld();
                    for (Scenario.BuildOp op : scenario.build) {
                        switch (op.op) {
                            case "placeTrack" -> placeTrack(level, op);
                            case "fill", "fillBackdrop", "setBlock" ->
                                    Traincraft.LOGGER.info(
                                            "fill {} -> {} blocks", op.material, fill(level, op));
                            default -> Traincraft.LOGGER.warn("Unknown build op {}", op.op);
                        }
                    }
                    for (Scenario.SpawnOp op : scenario.spawn) {
                        spawn(level, op);
                    }
                    // Evaluated here, on the server thread, while the scene is still fully built.
                    for (Scenario.Assertion a : scenario.assertions) {
                        Map<String, Object> row = evaluate(level, a);
                        assertionResults.add(row);
                        if (!Boolean.TRUE.equals(row.get("pass"))) {
                            Traincraft.LOGGER.error(
                                    "Assertion failed: {} -- {}",
                                    row.get("kind"),
                                    row.get("detail"));
                        }
                    }
                });
        // The camera is held with a teleport every tick, and each one echoes into chat unless this
        // is off. Ten lines of "Teleported Dev to..." across the bottom-left of every frame is not
        // what any scenario is trying to photograph.
        server.execute(
                () ->
                        server.getCommands()
                                .performPrefixedCommand(
                                        server.createCommandSourceStack().withSuppressedOutput(),
                                        "gamerule send_command_feedback false"));
        enter(State.RUN_STEPS);
    }

    /**
     * Puts one entity in the world.
     *
     * <p>Spawned rather than summoned by command so the position and orientation are exact: the
     * summon command rounds, and half a degree of yaw is enough to make a silhouette comparison
     * disagree for a reason that has nothing to do with the model.
     */
    private void spawn(ServerLevel level, Scenario.SpawnOp op) {
        Identifier id = Identifier.parse(op.id);
        if (op.op.equals("item")) {
            var item = BuiltInRegistries.ITEM.getOptional(id).orElseThrow(() -> new IllegalArgumentException("unknown item " + op.id));
            var dropped = new net.minecraft.world.entity.item.ItemEntity(level, op.pos[0], op.pos[1], op.pos[2], new net.minecraft.world.item.ItemStack(item));
            dropped.setDeltaMovement(0.0, 0.0, 0.0);
            dropped.setNoGravity(true);
            dropped.setNeverPickUp();
            dropped.setUnlimitedLifetime();
            level.addFreshEntity(dropped);
            spawnedEntities.add(dropped);
            return;
        }
        EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.getOptional(id).orElse(null);
        if (type == null) {
            Traincraft.LOGGER.error("spawn: no such entity type {}", op.id);
            return;
        }
        Entity entity = type.create(level, EntitySpawnReason.COMMAND);
        if (entity == null) {
            Traincraft.LOGGER.error("spawn: {} could not be created", op.id);
            return;
        }
        double y =
                op.onRail ? Math.floor(op.pos[1]) + 0.2 + RollingStockEntity.Y_OFFSET : op.pos[1];
        entity.snapTo(op.pos[0], y, op.pos[2], (float) op.yaw, 0.0F);
        if (op.tag != null) {
            entity.addTag(op.tag);
        }
        level.addFreshEntity(entity);
        if (entity instanceof LocomotiveEntity loco) {
            locomotives.add(loco);
            if (op.colour != null) {
                if (!loco.spec().colours().contains(op.colour)) {
                    throw new IllegalArgumentException("Unsupported livery " + op.colour + " for " + op.id);
                }
                loco.setColour(op.colour);
            }
            // Seeded rather than shovelled in: a capture that has to wait a hundred ticks for the
            // firebox to catch is a capture that mostly photographs an idle locomotive.
            if (op.fuel > 0) {
                loco.restoreFuel(op.fuel);
            }
            // And the boiler with it. A fuelled locomotive still will not move while it is cold,
            // and the warm-up is longer than most scenarios run; a scenario that wanted to watch
            // one heat up would seed nothing and wait.
            loco.setTemperature(loco.getAverageOverheat());
        }
        if (entity instanceof SteamLocomotiveEntity steam && op.water > 0) {
            steam.fillWater(op.water);
        }
        spawnedEntities.add(entity);
        Traincraft.LOGGER.info(
                "spawned {} at {},{},{} yaw {}", op.id, op.pos[0], op.pos[1], op.pos[2], op.yaw);
    }

    /**
     * Places a track piece through the real placement path, so a capture exercises what the mod
     * actually does rather than a shortcut that only ever works for single-block pieces.
     */
    private void placeTrack(ServerLevel level, Scenario.BuildOp op) {
        if (op.pos == null || op.type == null) {
            return;
        }
        TrackType type;
        try {
            type = TrackType.valueOf(op.type);
        } catch (IllegalArgumentException e) {
            Traincraft.LOGGER.error("placeTrack: no such track type {}", op.type);
            return;
        }
        BlockPos origin = new BlockPos(op.pos[0], op.pos[1], op.pos[2]);
        traincraft.track.PlacementResult result = TrackPlacer.place(level, origin, type, op.facing);
        if (!result.placed()) {
            Traincraft.LOGGER.error(
                    "placeTrack: {} facing {} at {} not placed -- {}{}",
                    type,
                    op.facing,
                    origin,
                    result.reason(),
                    result instanceof traincraft.track.PlacementResult.Blocked blocked
                            ? " at " + blocked.position()
                            : "");
            return;
        }
        // Record every block the piece wrote, so an empty baseline undoes the whole assembly
        // rather than leaving the gags behind and masking half the subject.
        TrackPlan plan = TrackPlacementPlanner.plan(type, op.facing);
        for (Placement placement : plan.placements()) {
            placedTracks.add(origin.offset(placement.dx(), placement.dy(), placement.dz()));
        }
        Traincraft.LOGGER.info(
                "placed {} facing {} at {}: {} blocks", type, op.facing, origin, plan.size());
    }

    private int fill(ServerLevel level, Scenario.BuildOp op) {
        int[] from = op.from != null ? op.from : op.pos;
        int[] to = op.to != null ? op.to : from;
        if (from == null || to == null || op.material == null) {
            return 0;
        }
        int placed = 0;
        BlockState material = resolveMaterial(op.material);
        for (int x = Math.min(from[0], to[0]); x <= Math.max(from[0], to[0]); x++) {
            for (int y = Math.min(from[1], to[1]); y <= Math.max(from[1], to[1]); y++) {
                for (int z = Math.min(from[2], to[2]); z <= Math.max(from[2], to[2]); z++) {
                    if (level.setBlock(new BlockPos(x, y, z), material, Block.UPDATE_ALL)) {
                        placed++;
                    }
                }
            }
        }
        return placed;
    }

    private static BlockState resolveMaterial(String material) {
        return switch (material) {
            case "PLATFORM" -> Blocks.STONE.defaultBlockState();
            case "BACKDROP" -> Blocks.SMOOTH_QUARTZ.defaultBlockState();
            case "AIR" -> Blocks.AIR.defaultBlockState();
            default -> {
                if (material.contains("[")) {
                    try {
                        yield net.minecraft.commands.arguments.blocks.BlockStateParser.parseForBlock(
                                net.minecraft.core.registries.BuiltInRegistries.BLOCK, material, false).blockState();
                    } catch (com.mojang.brigadier.exceptions.CommandSyntaxException exception) {
                        throw new IllegalArgumentException("unknown block state " + material, exception);
                    }
                }
                yield net.minecraft.core.registries.BuiltInRegistries.BLOCK
                        .getOptional(net.minecraft.resources.Identifier.parse(material))
                        .orElseThrow(() -> new IllegalArgumentException("unknown block " + material))
                        .defaultBlockState();
            }
        };
    }

    private void runSteps(Minecraft mc) {
        if (pendingShot != null) {
            return; // waiting for the frame handler to capture
        }
        if (shotDone) {
            shotDone = false;
            stepIndex++;
            settleRemaining = -1;
            return;
        }
        if (stepIndex >= scenario.steps.size()) {
            enter(State.REPORT);
            return;
        }

        Scenario.Step step = scenario.steps.get(stepIndex);
        if (settleRemaining < 0) {
            // Chat is part of the frame in a player-faced scenario, and whatever the previous step
            // said is not what this one is photographing. Cleared at the start of the step so the
            // settle ticks also cover the fade.
            mc.gui.hud.getChat().clearMessages(false);
            applyStep(mc, step);
            settleRemaining = Math.max(step.settleTicks, 1);
            return;
        }
        holdCamera(mc);
        pressMenuKeyIfPending(mc);
        if (mc.gui.screen() instanceof net.minecraft.client.gui.screens.PauseScreen) {
            mc.gui.setScreen(null);
        }
        if (settleRemaining-- > 0) {
            return;
        }
        if (step.shoot) {
            pendingShot = scenario.id + "." + step.name + ".png";
        } else {
            stepIndex++;
            settleRemaining = -1;
        }
    }

    private void applyStep(Minecraft mc, Scenario.Step step) {
        // Chat keeps its history, so turning command feedback off only stops new lines. Cleared at
        // the start of the step rather than before the grab: the frame being captured was rendered
        // earlier, so clearing at grab time cleans the next shot instead of this one.
        mc.gui.hud.getChat().clearMessages(true);
        if (step.clearTracks) {
            MinecraftServer server = mc.getSingleplayerServer();
            if (server != null) {
                server.execute(
                        () -> {
                            ServerLevel level = server.overworld();
                            for (BlockPos pos : placedTracks) {
                                level.setBlock(
                                        pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                            }
                            // Spawned entities go with them: the empty baseline has to be the scene
                            // with
                            // the whole subject removed, not just its track.
                            for (Entity entity : spawnedEntities) {
                                entity.discard();
                            }
                            Traincraft.LOGGER.info(
                                    "Cleared {} track pieces and {} entities for the empty"
                                            + " baseline",
                                    placedTracks.size(),
                                    spawnedEntities.size());
                        });
            }
        }
        if (step.throttle != null) {
            MinecraftServer server = mc.getSingleplayerServer();
            if (server != null) {
                String throttle = step.throttle;
                server.execute(
                        () -> {
                            for (LocomotiveEntity loco : locomotives) {
                                loco.setKeyHeld(
                                        LocomotiveEntity.KEY_FORWARD, "forward".equals(throttle));
                                loco.setKeyHeld(
                                        LocomotiveEntity.KEY_BACKWARD, "backward".equals(throttle));
                                loco.setKeyHeld(
                                        LocomotiveEntity.KEY_BRAKE, "brake".equals(throttle));
                                ScenarioDriver.mount(loco, !"none".equals(throttle));
                            }
                            Traincraft.LOGGER.info(
                                    "throttle {} on {} locomotive(s)",
                                    throttle,
                                    locomotives.size());
                        });
            }
        }
        if (step.velocity != null) {
            MinecraftServer server = mc.getSingleplayerServer();
            if (server != null) {
                double[] v = step.velocity;
                server.execute(
                        () -> {
                            for (Entity entity : spawnedEntities) {
                                entity.setDeltaMovement(v[0], v[1], v[2]);
                            }
                            Traincraft.LOGGER.info(
                                    "velocity {},{},{} on {} entities",
                                    v[0],
                                    v[1],
                                    v[2],
                                    spawnedEntities.size());
                        });
            }
        }
        if (!step.actions.isEmpty()) {
            runActions(mc, step.actions);
        }
        if (step.ride) {
            MinecraftServer server = mc.getSingleplayerServer();
            if (server != null) {
                server.execute(
                        () -> {
                            ServerPlayer player =
                                    server.getPlayerList().getPlayers().isEmpty()
                                            ? null
                                            : server.getPlayerList().getPlayers().getFirst();
                            if (player == null) {
                                return;
                            }
                            for (Entity entity : spawnedEntities) {
                                if (entity instanceof LocomotiveEntity loco) {
                                    player.startRiding(loco, true, true);
                                    Traincraft.LOGGER.info(
                                            "player riding {}", loco.getDisplayName().getString());
                                    return;
                                }
                            }
                        });
            }
        }
        if (step.openGui != null) {
            MinecraftServer server = mc.getSingleplayerServer();
            if (server != null) {
                server.execute(() -> openGui(server, step.openGui));
            }
        }
        if (step.closeGui && mc.player != null) {
            mc.player.closeContainer();
        }
        if (step.camera != null) {
            currentCamera = step.camera;
            sendTeleport(mc);
        }
    }

    /**
     * Runs a step's player actions in order, on the client.
     *
     * <p>On the client because that is where a player is. Every action reaches the server the way a
     * click does, through the packet the client sends, rather than by calling the server's own code
     * -- which is what makes the shots evidence about the mod a player meets rather than about the
     * mod's internals.
     */
    private void runActions(Minecraft mc, java.util.List<Scenario.PlayerAction> actions) {
        for (Scenario.PlayerAction action : actions) {
            switch (action.op) {
                case "openCreative" -> {
                    ClientPlayerActions.logTabContents(action.item);
                    ClientPlayerActions.openCreativeTab(mc, action.item);
                }
                case "pick" -> ClientPlayerActions.pickIntoHotbar(mc, action.item, action.count);
                case "closeScreen" -> ClientPlayerActions.closeScreen(mc);
                case "clickSlot" ->
                        ClientPlayerActions.clickItem(mc, action.item, action.count != 0);
                case "key" -> ClientPlayerActions.key(mc, action.item, action.count != 0);
                case "ride" -> rideNearestLocomotive(mc);
                case "gauges" -> logGauges(mc);
                case "clickButton" -> ClientPlayerActions.clickButton(mc, action.item);
                case "stand" -> {
                    BlockPos at = blockPos(action);
                    ClientPlayerActions.standBy(mc, at, action.yaw);
                    // The camera is the player here, so standing has to move it too. Leaving the
                    // camera where it was makes the per-tick hold drag the player back before the
                    // server has processed the click, which is exactly what happened: the client
                    // predicted success from the right place and the server refused from the
                    // wrong one.
                    currentCamera =
                            new double[] {
                                at.getX() + 0.5,
                                at.getY() + 1.5 + EYE_HEIGHT,
                                at.getZ() + 1.5,
                                action.yaw,
                                20.0
                            };
                    sendTeleport(mc);
                }
                case "useItemOn" -> {
                    if (action.item != null) {
                        ClientPlayerActions.pickIntoHotbar(mc, action.item, 0);
                    }
                    ClientPlayerActions.useOnBlock(
                            mc, blockPos(action), face(action.face), action.yaw);
                }
                case "breakBlock" -> ClientPlayerActions.breakBlock(mc, blockPos(action));
                case "interact" ->
                        withTagged(
                                action.tag,
                                action.item,
                                entity ->
                                        ClientPlayerActions.useOnEntity(
                                                mc, entity, action.crouching));
                case "attack" ->
                        withTagged(
                                action.tag,
                                action.item,
                                entity -> ClientPlayerActions.attackEntity(mc, entity));
                case "reportBlocks" -> reportBlocks(mc, blockPos(action), action.count);
                case "setBlock" -> setBlock(mc, blockPos(action), action.item);
                default -> Traincraft.LOGGER.warn("Unknown player action {}", action.op);
            }
        }
        if (mc.player != null) {
            Traincraft.LOGGER.info("player now carrying: {}", carried(mc));
        }
    }

    /**
     * Logs every Traincraft block near a position, on both sides.
     *
     * <p>Both sides on purpose. A client that shows nothing and a server that holds a whole curve
     * is a synchronisation fault; both empty is a placement that never happened; and the two need
     * different fixes. Reading it off a screenshot cannot tell them apart, and the chat overlay is
     * quite capable of sitting on top of the evidence.
     */
    private void reportBlocks(Minecraft mc, BlockPos centre, int radius) {
        int r = Math.max(1, radius);
        StringBuilder client = new StringBuilder();
        if (mc.level != null) {
            for (BlockPos pos :
                    BlockPos.betweenClosed(centre.offset(-r, -1, -r), centre.offset(r, 2, r))) {
                var block = mc.level.getBlockState(pos).getBlock();
                if (block == BlockRegistry.TC_RAIL.get()
                        || block == BlockRegistry.TC_RAIL_GAG.get()) {
                    client.append(' ').append(pos.toShortString());
                }
            }
        }
        Traincraft.LOGGER.info(
                "client sees near {}:{}",
                centre.toShortString(),
                client.isEmpty() ? " nothing" : client);
        if (mc.player != null) {
            Traincraft.LOGGER.info(
                    "  client player at {},{},{}",
                    mc.player.getX(),
                    mc.player.getY(),
                    mc.player.getZ());
        }

        MinecraftServer server = mc.getSingleplayerServer();
        if (server != null) {
            server.execute(
                    () -> {
                        ServerLevel level = server.overworld();
                        StringBuilder found = new StringBuilder();
                        for (BlockPos pos :
                                BlockPos.betweenClosed(
                                        centre.offset(-r, -1, -r), centre.offset(r, 2, r))) {
                            var block = level.getBlockState(pos).getBlock();
                            if (block == BlockRegistry.TC_RAIL.get()
                                    || block == BlockRegistry.TC_RAIL_GAG.get()) {
                                found.append(' ').append(pos.toShortString());
                            }
                        }
                        Traincraft.LOGGER.info(
                                "server has near {}:{}",
                                centre.toShortString(),
                                found.isEmpty() ? " nothing" : found);
                        ServerPlayer sp = PlayerActions.player(server);
                        if (sp != null) {
                            Traincraft.LOGGER.info(
                                    "  server player at {},{},{} holding {}",
                                    sp.getX(),
                                    sp.getY(),
                                    sp.getZ(),
                                    sp.getMainHandItem().getItem());
                        }
                    });
        }
    }

    /** Puts a block down from the server, for the parts of a scene a player would not click. */
    private void setBlock(
            Minecraft mc, BlockPos pos, @org.jspecify.annotations.Nullable String id) {
        MinecraftServer server = mc.getSingleplayerServer();
        if (server == null || id == null) {
            return;
        }
        server.execute(
                () -> {
                    var block =
                            net.minecraft.core.registries.BuiltInRegistries.BLOCK
                                    .getOptional(net.minecraft.resources.Identifier.parse(id))
                                    .orElse(null);
                    if (block == null) {
                        Traincraft.LOGGER.error("setBlock: no such block {}", id);
                        return;
                    }
                    server.overworld().setBlock(pos, block.defaultBlockState(), Block.UPDATE_ALL);
                    Traincraft.LOGGER.info("set {} at {}", id, pos.toShortString());
                });
    }

    private static BlockPos blockPos(Scenario.PlayerAction action) {
        return new BlockPos(action.pos[0], action.pos[1], action.pos[2]);
    }

    private static net.minecraft.core.Direction face(String name) {
        net.minecraft.core.Direction direction = net.minecraft.core.Direction.byName(name);
        return direction == null ? net.minecraft.core.Direction.UP : direction;
    }

    /** What the client thinks the player is holding, which is what a screenshot would show. */
    private static String carried(Minecraft mc) {
        StringBuilder sb = new StringBuilder();
        for (int slot = 0; slot < 9; slot++) {
            var stack = mc.player.getInventory().getItem(slot);
            if (!stack.isEmpty()) {
                if (!sb.isEmpty()) {
                    sb.append(", ");
                }
                sb.append(stack.getCount())
                        .append("x ")
                        .append(
                                net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(
                                        stack.getItem()));
            }
        }
        return sb.isEmpty() ? "(nothing)" : sb.toString();
    }

    /** Applies something to the spawned entity carrying a tag, or logs that there is none. */
    private void withTagged(
            @org.jspecify.annotations.Nullable String tag,
            @org.jspecify.annotations.Nullable String typeId,
            java.util.function.Consumer<Entity> action) {
        Minecraft mc = Minecraft.getInstance();
        // The client's own copy, not the server's: a client action has to be given the entity the
        // client is looking at, and the two are different objects with different ids in memory.
        if (mc.level != null) {
            for (Entity entity : mc.level.entitiesForRendering()) {
                if (entity.isRemoved()) {
                    continue;
                }
                // A tag names one spawned entity; a type id names a kind of it, which is what a
                // scenario that placed its stock with an item has to go on.
                if (tag != null && !entity.entityTags().contains(tag)) {
                    continue;
                }
                if (typeId != null
                        && !typeId.equals(
                                net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE
                                        .getKey(entity.getType())
                                        .toString())) {
                    continue;
                }
                action.accept(entity);
                return;
            }
        }
        Traincraft.LOGGER.error("no live client entity tagged {} of type {}", tag, typeId);
    }

    /**
     * Opens a menu through the real interaction path.
     *
     * <p>Deliberately not by constructing the screen: a screen built by hand renders whatever it is
     * handed and proves nothing about whether the menu, its slots and its payloads are wired up.
     * Going through the entity's own interact is what makes the shot evidence.
     */
    private void openGui(MinecraftServer server, String which) {
        ServerPlayer player =
                server.getPlayerList().getPlayers().isEmpty()
                        ? null
                        : server.getPlayerList().getPlayers().getFirst();
        if (player == null) {
            Traincraft.LOGGER.error("openGui {}: no player on the server", which);
            return;
        }
        // Scenario-spawned stock first, then anything the player put there with the item: a
        // player-faced scenario places its own locomotive rather than being handed one.
        java.util.List<Entity> candidates = new java.util.ArrayList<>(spawnedEntities);
        server.overworld().getEntities().getAll().forEach(candidates::add);
        for (Entity entity : candidates) {
            if (entity instanceof LocomotiveEntity loco && "loco".equals(which)) {
                // A player opens this menu by getting in and pressing the Traincraft key, so that
                // is what happens here: right-click to mount through the entity's own interact,
                // then press the key on the client. Calling openMenu directly -- which this did --
                // photographs a menu that no key on the keyboard has been shown to reach.
                player.snapTo(loco.getX() + 1.5, loco.getY(), loco.getZ(), player.getYRot(), 0.0F);
                loco.interact(player, InteractionHand.MAIN_HAND, Vec3.ZERO);
                if (player.getVehicle() != loco) {
                    Traincraft.LOGGER.error(
                            "openGui {}: right-click did not seat the player", which);
                    return;
                }
                pendingMenuKey = true;
                Traincraft.LOGGER.info("riding {}, pressing the menu key", which);
                return;
            }
        }
        Traincraft.LOGGER.error("openGui {}: nothing to open it on", which);
    }

    /** Right-clicks the nearest locomotive on the server, which is how a player gets in. */
    private void rideNearestLocomotive(Minecraft mc) {
        // The client owns the player's look and sends it every tick, so turning only the server's
        // copy lasts one tick. Both are turned along the locomotive here: the throttle is applied
        // along the driver's facing quantised to a compass point, and a driver still looking where
        // the last camera pointed pushes the locomotive sideways -- which the rail then discards,
        // leaving eight per cent of the throttle to do the work.
        if (mc.player != null) {
            for (Entity entity :
                    mc.level == null
                            ? java.util.List.<Entity>of()
                            : mc.level.entitiesForRendering()) {
                if (entity instanceof LocomotiveEntity loco) {
                    mc.player.setYRot(loco.getYRot());
                    mc.player.setYHeadRot(loco.getYRot());
                    mc.player.setXRot(0.0F);
                    break;
                }
            }
        }
        MinecraftServer server = mc.getSingleplayerServer();
        if (server == null) {
            return;
        }
        server.execute(
                () -> {
                    ServerPlayer player = PlayerActions.player(server);
                    if (player == null) {
                        return;
                    }
                    for (Entity entity : server.overworld().getEntities().getAll()) {
                        if (entity instanceof LocomotiveEntity loco) {

                            player.snapTo(
                                    loco.getX() + 1.5,
                                    loco.getY(),
                                    loco.getZ(),
                                    loco.getYRot(),
                                    0.0F);
                            loco.interact(player, InteractionHand.MAIN_HAND, Vec3.ZERO);
                            Traincraft.LOGGER.info(
                                    "riding {} facing {} -> {}",
                                    loco.getDisplayName().getString(),
                                    loco.getYRot(),
                                    player.getVehicle() != null);
                            return;
                        }
                    }
                    Traincraft.LOGGER.error("ride: no locomotive in the world");
                });
    }

    /**
     * Writes the gauges to the log, in the same words the reference rig uses.
     *
     * <p>A picture shows where a locomotive is; it does not show whether the boiler has water in it
     * or which way the throttle is set. Both rigs print the same five numbers so the run can be
     * compared as a sequence of states rather than as a set of frames.
     */
    private void logGauges(Minecraft mc) {
        MinecraftServer server = mc.getSingleplayerServer();
        if (server == null) {
            return;
        }
        server.execute(
                () -> {
                    for (Entity entity : server.overworld().getEntities().getAll()) {
                        if (entity instanceof LocomotiveEntity loco) {
                            Traincraft.LOGGER.info(
                                    String.format(
                                            java.util.Locale.ROOT,
                                            "gauges: Fuel=%d Water=%d Speed=%d Engine=%b Brake=%b"
                                                    + " pos=%.2f,%.2f,%.2f yaw=%.1f",
                                            loco.getFuel(),
                                            loco instanceof SteamLocomotiveEntity steam
                                                    ? steam.getWater()
                                                    : 0,
                                            loco.getSpeedKmH(),
                                            loco.isEngineOn(),
                                            loco.isParkingBrakeOn(),
                                            loco.getX(),
                                            loco.getY(),
                                            loco.getZ(),
                                            loco.getYRot()));
                            StringBuilder slots = new StringBuilder();
                            for (int i = 0; i < loco.getContainerSize(); i++) {
                                slots.append(' ')
                                        .append(i)
                                        .append(':')
                                        .append(
                                                loco.getItem(i).isEmpty()
                                                        ? "-"
                                                        : loco.getItem(i).getCount()
                                                                + "x"
                                                                + loco.getItem(i).getItem());
                            }
                            Traincraft.LOGGER.info("  slots:{}", slots);
                            return;
                        }
                    }
                    Traincraft.LOGGER.error("gauges: no locomotive in the world");
                });
    }

    private void sendTeleport(Minecraft mc) {
        if (currentCamera == null || mc.player == null) {
            return;
        }
        double[] c = currentCamera;
        // A scenario's camera Y is where the eye goes, not where the feet go. Teleports position
        // the feet, and the two versions have different eye heights, so without this correction
        // the same scenario frames a different view on each rig -- which showed up as a constant
        // vertical offset between otherwise identical shots.
        double feetY = c[1] - mc.player.getEyeHeight();
        String command =
                String.format(
                        java.util.Locale.ROOT,
                        "tp @s %.4f %.4f %.4f %.4f %.4f",
                        c[0],
                        feetY,
                        c[2],
                        c[3],
                        c[4]);
        mc.player.connection.sendCommand(command);
        if (teleportsLogged++ < 4) {
            Traincraft.LOGGER.info(
                    "camera: /{}  eyeHeight={} playerNow={},{},{} yaw={} pitch={}",
                    command,
                    mc.player.getEyeHeight(),
                    mc.player.getX(),
                    mc.player.getY(),
                    mc.player.getZ(),
                    mc.player.getYRot(),
                    mc.player.getXRot());
        }
    }

    /**
     * Re-issues the teleport if the camera has drifted. Checked every tick but only acted on when
     * it actually moved, so the server is not spammed with a command per tick.
     */
    private void holdCamera(Minecraft mc) {
        if (currentCamera == null || mc.player == null) {
            return;
        }
        if (mc.player.getVehicle() != null) {
            // A teleport dismounts, so a scenario that puts the player in the cab cannot also hold
            // the camera somewhere else. Riding wins: the seat is the shot.
            return;
        }
        double[] c = currentCamera;
        double feetY = c[1] - mc.player.getEyeHeight();
        boolean drifted =
                Math.abs(mc.player.getX() - c[0]) > 1.0e-3
                        || Math.abs(mc.player.getY() - feetY) > 1.0e-3
                        || Math.abs(mc.player.getZ() - c[2]) > 1.0e-3
                        || Math.abs(mc.player.getYRot() - c[3]) > 1.0e-2
                        || Math.abs(mc.player.getXRot() - c[4]) > 1.0e-2;
        if (drifted) {
            sendTeleport(mc);
        }
    }

    // ------------------------------------------------------------------ frame

    /**
     * Called from {@code RenderFrameEvent.Post}, i.e. after the frame is fully composed. Grabbing
     * from the tick handler instead would capture whatever happened to be in the framebuffer
     * mid-frame.
     */
    /** Set on the server thread when the player is seated; acted on from the client tick. */
    private volatile boolean pendingMenuKey;

    /**
     * Presses the menu key, once, from the client.
     *
     * <p>{@code KeyMapping.click} is what the keyboard handler itself calls, so everything after it
     * -- the binding, the tick handler that reads it, the payload, the server's checks -- is the
     * same code a player's keystroke runs through.
     */
    private void pressMenuKeyIfPending(Minecraft mc) {
        if (!pendingMenuKey || mc.player == null || mc.player.getVehicle() == null) {
            return;
        }
        pendingMenuKey = false;
        KeyMapping.click(traincraft.client.KeyBindings.OPEN_MENU.getKey());
        Traincraft.LOGGER.info("pressed {}", traincraft.client.KeyBindings.OPEN_MENU.getName());
    }

    public void onFrameEnd(Minecraft mc) {
        // Every frame, not only the ones that are photographed: the whole point is the frames
        // between the shots.
        motion.record(mc);
        String name = pendingShot;
        if (name == null) {
            return;
        }
        pendingShot = null;
        File workDir = outDir.toFile();
        // Where the camera actually was, in the frame that was taken. Both rigs print this line,
        // so a difference in framing can be read as numbers rather than guessed at from pixels.
        // The camera itself is private in 26.2; the player's eye is the same point in first
        // person, which is what every scenario uses.
        if (mc.player != null) {
            Traincraft.LOGGER.info(
                    String.format(
                            java.util.Locale.ROOT,
                            "camera at %s: %.4f,%.4f,%.4f yaw %.2f pitch %.2f fov %d eye %.4f",
                            name,
                            mc.player.getX(),
                            mc.player.getEyeY(),
                            mc.player.getZ(),
                            mc.player.getYRot(),
                            mc.player.getXRot(),
                            mc.options.fov().get(),
                            mc.player.getEyeHeight()));
        }
        Screenshot.grab(workDir, name, mc.gameRenderer.mainRenderTarget(), 1, message -> {});
        report.recordShot(name);
        Traincraft.LOGGER.info("Harness captured {}", name);
        shotDone = true;
    }

    // ------------------------------------------------------------------ finish

    private void writeReport(Minecraft mc) {
        motion.write(outDir);
        report.finish(mc, assertionResults);
        enter(State.QUIT);
    }

    private Map<String, Object> evaluate(ServerLevel level, Scenario.Assertion a) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("kind", a.kind);
        boolean pass = false;
        String detail = "";
        switch (a.kind) {
            case "blockAt" -> {
                if (a.pos != null && a.block != null) {
                    BlockPos pos = new BlockPos(a.pos[0], a.pos[1], a.pos[2]);
                    var actual =
                            net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(
                                    level.getBlockState(pos).getBlock());
                    pass = a.block.equals(actual.toString());
                    detail = "expected " + a.block + ", found " + actual;
                }
            }
            case "trackType" -> {
                if (a.pos != null && a.value != null) {
                    BlockPos pos = new BlockPos(a.pos[0], a.pos[1], a.pos[2]);
                    String actual =
                            level.getBlockEntity(pos) instanceof TrackBlockEntity tile
                                    ? String.valueOf(tile.getTypeLabel())
                                    : "<no tile>";
                    String expected = TrackType.valueOf(a.value).label();
                    pass = expected.equals(actual);
                    detail = "expected " + expected + ", found " + actual;
                }
            }
            default -> detail = "unsupported assertion kind";
        }
        row.put("pass", pass);
        row.put("detail", detail);
        return row;
    }

    private void quit(Minecraft mc) {
        // Give the report write a couple of ticks to hit disk before tearing the game down.
        if (ticksInState < 5) {
            return;
        }
        Traincraft.LOGGER.info("Harness done, stopping client");
        mc.stop();
        enter(State.DONE);
    }

    private static void deleteRecursively(File file) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                deleteRecursively(child);
            }
        }
        if (!file.delete()) {
            Traincraft.LOGGER.warn("Could not delete {}", file);
        }
    }
}
