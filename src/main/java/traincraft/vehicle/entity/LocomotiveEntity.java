package traincraft.vehicle.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

import org.jspecify.annotations.Nullable;

import traincraft.track.TrackMovement;
import traincraft.TraincraftConfig;
import traincraft.vehicle.control.LocomotiveDrive;
import traincraft.vehicle.definition.Exhaust;
import traincraft.vehicle.inventory.LocomotiveMenu;
import traincraft.vehicle.inventory.VehicleInventory;
import traincraft.vehicle.simulation.BoilerSimulation;
import traincraft.vehicle.simulation.ConsistLoad;
import traincraft.vehicle.simulation.FuelStore;
import traincraft.vehicle.simulation.HeatState;
import traincraft.vehicle.sound.LocomotiveSounds;

import java.util.List;

public abstract class LocomotiveEntity extends RollingStockEntity implements Container, MenuProvider, KeyControlled {

    public static final int MAX_FUEL = FuelStore.CAPACITY;

    private static final EntityDataAccessor<Integer> FUEL =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SPEED_KMH =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> ENGINE_ON =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> PARKING_BRAKE =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CAN_BE_PULLED =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> MAX_SPEED =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> OVERHEAT_LEVEL =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HEAT_STATE =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CARTS_PULLED =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> MASS_PULLED =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> SPEED_SLOW_DOWN =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> ACCEL_SLOW_DOWN =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> BRAKE_SLOW_DOWN =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> FILLED = SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FUEL_RATE =
            SynchedEntityData.defineId(LocomotiveEntity.class, EntityDataSerializers.INT);

    private static final HeatState[] HEAT_STATES = HeatState.values();

    private final VehicleInventory inventory;
    protected final FuelStore fuel = new FuelStore();

    private final LocomotiveDrive drive = new LocomotiveDrive();

    protected LocomotiveEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.inventory = new VehicleInventory(inventorySize());
    }

    protected boolean startsLit() {
        return false;
    }

    /** A stranger may sit in a locked locomotive; the controls simply will not answer them. */
    @Override
    protected boolean canBeRiddenWhileLocked() {
        return true;
    }

    public abstract int inventorySize();

    @Override
    public boolean isLocomotive() {
        return true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(FUEL, 0);
        builder.define(FILLED, 0);
        builder.define(SPEED_KMH, 0);
        builder.define(ENGINE_ON, startsLit());
        builder.define(PARKING_BRAKE, false);
        builder.define(CAN_BE_PULLED, false);
        builder.define(MAX_SPEED, (float) spec().maxSpeed());
        builder.define(OVERHEAT_LEVEL, 0);
        builder.define(HEAT_STATE, HeatState.COLD.ordinal());
        builder.define(CARTS_PULLED, 0);
        builder.define(MASS_PULLED, 0.0F);
        builder.define(SPEED_SLOW_DOWN, 0.0F);
        builder.define(ACCEL_SLOW_DOWN, 0.0F);
        builder.define(BRAKE_SLOW_DOWN, 0.0F);
        builder.define(FUEL_RATE, 0);
    }

    public int getCartsPulled() {
        return entityData.get(CARTS_PULLED);
    }

    public float getMassPulled() {
        return entityData.get(MASS_PULLED);
    }

    public float getSpeedSlowDown() {
        return entityData.get(SPEED_SLOW_DOWN);
    }

    public float getAccelSlowDown() {
        return entityData.get(ACCEL_SLOW_DOWN);
    }

    public float getBrakeSlowDown() {
        return entityData.get(BRAKE_SLOW_DOWN);
    }

    /** Upstream's {@code getFuelConsumption}: the rate the consist has left it running at. */
    public int getFuelConsumption() {
        return entityData.get(FUEL_RATE);
    }

    // --- Gauges -----------------------------------------------------------------------------

    public int getFuel() {
        return entityData.get(FUEL);
    }

    public boolean isFuelled() {
        return getFuel() > 0;
    }

    public int getFuelScaled(int height) {
        return getFuel() * height / MAX_FUEL;
    }

    public int getSpeedKmH() {
        return entityData.get(SPEED_KMH);
    }

    public boolean isEngineOn() {
        return entityData.get(ENGINE_ON);
    }

    public void setEngineOn(boolean on) {
        entityData.set(ENGINE_ON, on);
    }

    public boolean isParkingBrakeOn() {
        return entityData.get(PARKING_BRAKE);
    }

    public void setParkingBrake(boolean on) {
        entityData.set(PARKING_BRAKE, on);
    }

    /** The ceiling this locomotive is running at, in km/h, once the consist is accounted for. */
    public float getMaxSpeedGauge() {
        return entityData.get(MAX_SPEED);
    }

    // --- The consist ------------------------------------------------------------------------

    /** Upstream's pull mode: false means this locomotive pulls, true means it is pulled. */
    private boolean canBeAdjusted;

    @Override
    public boolean canBeAdjusted(RollingStockEntity other) {
        return canBeAdjusted;
    }

    public void setCanBeAdjusted(boolean adjusted) {
        canBeAdjusted = adjusted;
    }

    public boolean canBePulled() {
        return entityData.get(CAN_BE_PULLED);
    }

    public void setCanBePulled(boolean pulled) {
        entityData.set(CAN_BE_PULLED, pulled);
    }

    private double currentMassPulled;

    private double accelerationRate = -1.0;
    private double brakeRate = -1.0;
    private int fuelRate = -1;

    /**
     * The top speed this locomotive can hold, in km/h, with what it is dragging.
     *
     * <p>Upstream divides the specification's figure by the mass on the drawbar over the power
     * available, and only once that ratio passes one -- a light train costs nothing.
     */
    public float getMaxSpeed() {
        float multiplier = performanceMultiplier();
        if (currentMassPulled > 1.0) {
            float power = (float) (currentMassPulled / (spec().horsePower() * 0.37F));
            if (power > 1.0F) {
                return (float) (spec().maxSpeed() / power) * multiplier;
            }
        }
        return spec().maxSpeed() * multiplier;
    }

    /** Per-rider jokes in CE alter both displayed power and the physics which consume it. */
    public int currentHorsePower() {
        return Math.round(spec().horsePower() * performanceMultiplier());
    }

    protected float performanceMultiplier() {
        return 1.0F;
    }

    private double accelerationRate() {
        return accelerationRate < 0.0 ? spec().accelerationRate() : accelerationRate;
    }

    private double brakeRate() {
        return brakeRate < 0.0 ? spec().brakeRate() : brakeRate;
    }

    private int fuelRate() {
        return fuelRate < 0 ? spec().fuelConsumption() : fuelRate;
    }

    /** Re-reads the consist and re-rates the locomotive against it. */
    private void applyConsistLoad() {
        double totalMass = 0.0;
        int members = 0;
        double power = 0.0;
        if (consist != null && !consist.members().isEmpty()) {
            members = consist.members().size();
            for (RollingStockEntity member : consist.members()) {
                totalMass += member.weightKg() * 0.1;
            }
            if (members > 1) {
                power = currentHorsePower();
                for (RollingStockEntity member : consist.members()) {
                    if (member instanceof LocomotiveEntity other && other != this) {
                        power += other.currentHorsePower();
                    }
                }
            }
        }
        ConsistLoad load = ConsistLoad.of(spec(), totalMass, members, power, getMaxSpeed());
        currentMassPulled = load.massPulled();
        accelerationRate = load.accelerate();
        brakeRate = load.brake();
        fuelRate = load.fuelRate();
        entityData.set(CARTS_PULLED, load.cartsPulled());
        entityData.set(MASS_PULLED, (float) load.massPulled());
        entityData.set(SPEED_SLOW_DOWN, (float) load.speedSlowDown());
        entityData.set(ACCEL_SLOW_DOWN, (float) load.accelSlowDown());
        entityData.set(BRAKE_SLOW_DOWN, (float) load.brakeSlowDown());
        entityData.set(FUEL_RATE, fuelRate());
    }

    // --- The boiler -------------------------------------------------------------------------

    /** Ticks the boiler, once per tick, on the server only. */
    private final BoilerSimulation boiler = new BoilerSimulation();

    private int blowUpDelay;

    public boolean canOverheat() {
        return getOverheatTime() > 0;
    }

    /** The spec's {@code heatingTime}: 200 for the Alice, 135 for the BR 80. */
    public int getOverheatTime() {
        return spec().heatingTime();
    }

    public int getAverageOverheat() {
        return BoilerSimulation.operatingTemperature(getOverheatTime());
    }

    public int getOverheatLevel() {
        return entityData.get(OVERHEAT_LEVEL);
    }

    public void setOverheatLevel(int level) {
        entityData.set(OVERHEAT_LEVEL, level);
    }

    public HeatState getState() {
        return HEAT_STATES[Math.clamp(entityData.get(HEAT_STATE), 0, HEAT_STATES.length - 1)];
    }

    public void setState(HeatState state) {
        entityData.set(HEAT_STATE, state.ordinal());
    }

    public boolean isBraking() {
        return drive.braking();
    }

    public void setTemperature(int level) {
        setOverheatLevel(level);
        setState(BoilerSimulation.stateFor(getOverheatTime(), level));
    }

    private void applyHeatState() {
        switch (getState()) {
            case COLD -> {
                // Not damped: stopped. A cold locomotive does not creep, however hard it is driven.
                clearFire();
                setDeltaMovement(0.0, getDeltaMovement().y, 0.0);
            }
            case WARM -> {
                clearFire();
                setDeltaMovement(getDeltaMovement().multiply(0.94, 1.0, 0.94));
            }
            case HOT -> clearFire();
            case VERY_HOT -> {}
            case TOO_HOT -> {
                setDeltaMovement(getDeltaMovement().multiply(0.95, 1.0, 0.95));
                smoke(1);
            }
            case BROKEN -> {
                setRemainingFireTicks(8 * 20);
                setDeltaMovement(getDeltaMovement().multiply(0.97, 1.0, 0.97));
                smoke(2);
                if (++blowUpDelay > 80 && level() instanceof ServerLevel server) {

                    server.explode(
                            this, getX(), getY(), getZ(), 0.5F, Level.ExplosionInteraction.NONE);
                    discard();
                }
            }
        }
    }

    private void smoke(int count) {
        if (level() instanceof ServerLevel server) {
            server.sendParticles(
                    ParticleTypes.LARGE_SMOKE,
                    getX(),
                    getY() + 0.3,
                    getZ(),
                    count,
                    0.0,
                    0.0,
                    0.0,
                    0.0);
        }
    }

    // --- The driving position ---------------------------------------------------------------


    public @Nullable Exhaust exhaust() {
        return null;
    }

    // --- Controls ---------------------------------------------------------------------------

    public void setKeyHeld(int key, boolean held) {
        if (key < 0 || key >= LocomotiveDrive.Control.values().length) return;
        drive.setHeld(LocomotiveDrive.Control.values()[key], held);
    }

    /**
     * A driver who leaves -- dismounts, disconnects, is thrown off -- takes their hands off the
     * controls. The client cannot send that release itself: by the time it notices, it no longer
     * rides anything to address the packet to.
     */
    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        for (LocomotiveDrive.Control control : LocomotiveDrive.Control.values()) {
            drive.setHeld(control, false);
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        if (reason.shouldDestroy()) {
            forceChunks(false);
        }
        super.remove(reason);
    }

    public void restoreFuel(int burnTime) {
        fuel.restore(burnTime);
        entityData.set(FUEL, fuel.amount());
    }

    private boolean chunkLoading;
    private net.minecraft.world.level.ChunkPos loadedChunk;

    public boolean isChunkLoading() {
        return chunkLoading;
    }

    public void setChunkLoading(boolean loading) {
        chunkLoading = loading;
        if (!loading) {
            forceChunks(false);
            loadedChunk = null;
        }
    }

    private void forceChunks(boolean add) {
        if (loadedChunk == null || !(level() instanceof ServerLevel server)) {
            return;
        }
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                traincraft.Traincraft.CHUNK_TICKETS.forceChunk(server, this, loadedChunk.x() + x, loadedChunk.z() + z, add, false);
            }
        }
    }

    protected void tickChunkLoading() {
        if (!chunkLoading || level().isClientSide()) {
            return;
        }
        net.minecraft.world.level.ChunkPos current = chunkPosition();
        if (current.equals(loadedChunk)) {
            return;
        }
        forceChunks(false);
        loadedChunk = current;
        forceChunks(true);
    }

    public static final int KEY_FORWARD = 0;
    public static final int KEY_BACKWARD = 1;
    public static final int KEY_BRAKE = 2;

    /**
     * On Traincraft's own track upstream hands the speed handler the locomotive's own top speed
     * where the rail's figure would go, so the rail never limits it and the band tests never fire.
     */
    @Override
    protected double tcRailSpeedCap() {
        return traincraft.vehicle.simulation.SpeedHandler.handleSpeed(
                getMaxSpeed(), true, getMaxSpeed(), TraincraftConfig.REAL_TRAIN_SPEED.get());
    }

    @Override
    protected double vanillaRailSpeedCap(double railMaxSpeed) {
        return traincraft.vehicle.simulation.SpeedHandler.handleSpeed(
                railMaxSpeed, true, getMaxSpeed(), TraincraftConfig.REAL_TRAIN_SPEED.get());
    }

    @Override
    protected double bogieShift() {
        return spec().bogieLocoPosition();
    }

    /** A locomotive is not dragged by the track; only the brake slows it. See TrackMovement. */
    @Override
    protected boolean hasTrackDrag() {
        return false;
    }

    /**
     * Upstream's tick order, which is not the obvious one.
     *
     * <p>The throttle and the brake are applied first, the boiler afterwards, and only then does the
     * rolling stock's own tick put the result on the rails. A locked locomotive with a stranger at
     * the controls returns from here without ticking at all, which is upstream's behaviour and is
     * why such a locomotive freezes rather than coasting.
     */
    @Override
    public void tick() {
        if (level().isClientSide()) {
            super.tick();
            return;
        }

        if (!drive.throttling()) {
            if (drive.braking()) {
                setDeltaMovement(getDeltaMovement().multiply(brakeRate(), 1.0, brakeRate()));
            }
        } else if (getRandom().nextInt(4) == 0) {
            Player driver = getFirstPassenger() instanceof Player player ? player : null;
            if (getFuel() > 0 && isEngineOn() && driver != null) {
                if (isLocked() && !access().isOwner(driver.getGameProfile().name())) {
                    return;
                }
                applyThrottle(driver);
            }
        }

        if (updateTicks % 20 == 0) {
            applyConsistLoad();
        }

        tickFuel(fuelRate());
        refuelFromSlot();
        tickChunkLoading();

        if (!isEngineOn()) {
            setDeltaMovement(0.0, getDeltaMovement().y, 0.0);
        }

        if (whistleDelay > 0) {
            whistleDelay--;
        }

        if (isParkingBrakeOn() && getState() != HeatState.BROKEN) {
            setDeltaMovement(0.0, getDeltaMovement().y, 0.0);
        }

        updateWorkingSound();

        tickHeat();

        super.tick();

        entityData.set(FUEL, fuel.amount());
        entityData.set(SPEED_KMH, speedKmH());
        entityData.set(MAX_SPEED, getMaxSpeed());
    }

    protected void tickFuel(int consumption) {
        if (fuel.tick(isEngineOn(), consumption)) {
            setDeltaMovement(getDeltaMovement().multiply(0.8, 1.0, 0.8));
        }
    }

    protected void tickHeat() {
        var heat =
                boiler.tick(
                        new BoilerSimulation.Input(
                                getOverheatLevel(),
                                getOverheatTime(),
                                getState(),
                                getFuel(),
                                isEngineOn(),
                                isBraking(),
                                Math.abs(TrackMovement.planarSpeed(this)),
                                Math.abs(getDeltaMovement().x) + Math.abs(getDeltaMovement().z),
                                this instanceof SteamLocomotiveEntity steam ? steam.getWater() : 0,
                                this instanceof SteamLocomotiveEntity steam
                                        ? steam.getTankCapacity()
                                        : 0),
                        getRandom()::nextInt);
        setOverheatLevel(heat.temperature());
        setState(heat.state());
        applyHeatState();

    }

    private void applyThrottle(Player driver) {
        var motion =
                drive.accelerate(
                        getDeltaMovement().x,
                        getDeltaMovement().z,
                        driver.getYRot(),
                        accelerationRate());
        setDeltaMovement(motion.x(), getDeltaMovement().y, motion.z());
    }

    public LocomotiveSounds sounds() {
        return LocomotiveSounds.SILENT;
    }

    private int whistleDelay;

    private int soundPosition;

    public void soundHorn() {
        LocomotiveSounds sounds = sounds();
        if (sounds.horn() == null || whistleDelay != 0 || level().isClientSide()) {
            return;
        }
        level().playSound(
                        null, this, sounds.horn(), SoundSource.NEUTRAL, sounds.hornVolume(), 1.0F);
        whistleDelay = 65;
    }

    private void updateWorkingSound() {
        LocomotiveSounds sounds = sounds();
        if (sounds.run() == null || getFuel() <= 0 || !isEngineOn()) {
            return;
        }
        double speed = TrackMovement.planarSpeed(this);
        if (soundPosition == 0) {
            if (speed > -0.001 && speed < 0.01) {
                play(sounds.idle(), sounds.idleVolume(), 0.001F);
                soundPosition = sounds.idleLength();
            } else if (!sounds.changeWithSpeed()) {
                play(sounds.run(), sounds.runVolume(), 0.4F);
                soundPosition = sounds.runLength();
            } else if (speed < 0.06) {
                play(sounds.run(), sounds.runVolume(), 0.1F);
                soundPosition = sounds.runLength();
            } else if (speed < 0.2) {
                play(sounds.run(), sounds.runVolume(), 0.4F);
                soundPosition = sounds.runLength() / 2;
            } else {
                play(sounds.run(), sounds.runVolume(), 0.5F);
                soundPosition = sounds.runLength() / 3;
            }
        }
        if (soundPosition > 0) {
            soundPosition--;
        }
    }

    private void play(
            @org.jspecify.annotations.Nullable SoundEvent sound, float volume, float pitch) {
        if (sound != null) {
            level().playSound(null, this, sound, SoundSource.NEUTRAL, volume, pitch);
        }
    }

    protected void refuelFromSlot() {
        ItemStack stack = getItem(FUEL_SLOT);
        if (stack.isEmpty() || !(level() instanceof ServerLevel server)) {
            return;
        }
        int burn = server.fuelValues().burnDuration(stack);
        if (!fuel.refill(burn)) {
            return;
        }
        stack.shrink(1);
        setChanged();
    }

    public static final int FUEL_SLOT = 0;


    // --- Interaction ------------------------------------------------------------------------

    @Override
    public InteractionResult interact(
            Player player,
            net.minecraft.world.InteractionHand hand,
            net.minecraft.world.phys.Vec3 location) {
        if (yieldsToPaintbrush(player, player.getItemInHand(hand))) {
            return InteractionResult.PASS;
        }
        if (level().isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        if (toggleLock(player, player.getItemInHand(hand))) {
            return InteractionResult.SUCCESS;
        }
        if (paint(player, player.getItemInHand(hand))) {
            return InteractionResult.SUCCESS;
        }
        if (refusesLocked(player, player.getItemInHand(hand))) {
            return InteractionResult.SUCCESS;
        }
        if (TraincraftConfig.CHUNK_LOADING.get() && player.getItemInHand(hand).is(traincraft.item.TraincraftItems.item("chunk_loader_activator"))) {
            setChunkLoading(!chunkLoading);
            player.getItemInHand(hand).hurtAndBreak(1, player, hand);
            player.sendSystemMessage(Component.literal(chunkLoading ? "Chunk loading on" : "Chunk loading off"));
            return InteractionResult.SUCCESS;
        }

        if (getFirstPassenger() == null) {
            player.startRiding(this);
        }
        return InteractionResult.SUCCESS;
    }

    protected void openMenu(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(this, buffer -> buffer.writeVarInt(getId()));
        }
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new LocomotiveMenu(containerId, inventory, this);
    }

    /**
     * Opens the driving menu for a player who has asked for it through the key.
     *
     * <p>The guards live in the payload handler rather than here, so that the harness and the key
     * take exactly the same path into the menu and a scenario cannot pass through a door a player
     * cannot.
     */
    public void openMenuFor(Player player) {
        openMenu(player);
    }

    // --- Container --------------------------------------------------------------------------

    @Override
    public int getContainerSize() {
        return inventory.getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return inventory.getItem(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int count) {
        return inventory.removeItem(slot, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return inventory.removeItemNoUpdate(slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        inventory.setItem(slot, stack);
        stack.limitSize(getMaxStackSize(stack));
    }

    @Override
    public int filledSlots() {
        return entityData.get(FILLED);
    }

    private void countFilled() {
        entityData.set(FILLED, (int) inventory.contents().stream().filter(stack -> !stack.isEmpty()).count());
    }

    @Override
    public void setChanged() {
        countFilled();
    }

    @Override
    public boolean stillValid(Player player) {
        return !isRemoved() && player.distanceToSqr(this) <= 64.0;
    }

    protected List<ItemStack> contents() {
        return inventory.contents();
    }

    @Override
    public void clearContent() {
        inventory.clearContent();
    }

    // --- Persistence ------------------------------------------------------------------------

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        chunkLoading = input.getBooleanOr("chunkLoading", false);
        canBeAdjusted = input.getBooleanOr("canBeAdjusted", false);
        setCanBePulled(input.getBooleanOr("canBePulled", false));
        fuel.restore(input.getIntOr("fuelTrain", 0));
        entityData.set(FUEL, fuel.amount());
        setEngineOn(input.getBooleanOr("isLocoTurnedOn", false));
        setParkingBrake(input.getBooleanOr("parkingBrake", false));
        setTemperature(input.getIntOr("overheatLevel", 0));
        inventory.clearContent();
        inventory.load(input);
        countFilled();
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        output.putBoolean("chunkLoading", chunkLoading);
        super.addAdditionalSaveData(output);
        output.putBoolean("canBeAdjusted", canBeAdjusted);
        output.putBoolean("canBePulled", canBePulled());

        output.putInt("fuelTrain", fuel.amount());
        output.putBoolean("isLocoTurnedOn", isEngineOn());
        output.putBoolean("parkingBrake", isParkingBrakeOn());

        output.putInt("overheatLevel", getOverheatLevel());
        inventory.save(output);
    }

    /**
     * Upstream's {@code Locomotive.attackEntityFrom}: the common half runs first and counts the
     * blow, then this counts it a second time, which is why a locomotive takes five swings from a
     * bare hand and a cart takes three.
     */
    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        if (canBeDestroyedByPlayer(source)) {
            return true;
        }
        super.hurtServer(level, source, damage);
        setHurtDir(-getHurtDir());
        setHurtTime(10);
        markHurt();
        setDamage(getDamage() + damage * 10.0F);
        if (getDamage() > 40.0F) {
            ejectPassengers();
            discard();
            announceRemoval(source);
            boolean creative =
                    source.getEntity() instanceof Player player && player.getAbilities().instabuild;
            dropCart(level, creative);
        }
        return true;
    }

    /**
     * Upstream drops the contents whether or not the blow was creative: only the cart itself is
     * behind the creative test.
     */
    private void dropCart(ServerLevel level, boolean creative) {
        if (!creative) {
            spawnAtLocation(level, dropStack());
        }
        for (ItemStack stack : contents()) {
            if (!stack.isEmpty()) {
                spawnAtLocation(level, stack);
            }
        }
    }

    /** The item a destroyed locomotive leaves behind. */
    @Override
    protected abstract net.minecraft.world.item.Item getDropItem();

    @Override
    public boolean isPushable() {
        return false;
    }
}
