package traincraft.vehicle.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.state.BlockState;

import org.jspecify.annotations.Nullable;

import traincraft.TraincraftConfig;
import traincraft.track.block.TrackBlock;
import traincraft.track.block.TrackBlockEntity;
import traincraft.track.block.TrackOccupancyBlock;
import traincraft.track.block.TrackOccupancyBlockEntity;
import traincraft.vehicle.entity.LocomotiveEntity;
import traincraft.vehicle.entity.RollingStockEntity;

import java.util.function.Supplier;

public class RollingStockItem extends Item {

    private final Supplier<EntityType<? extends RollingStockEntity>> type;

    public RollingStockItem(
            Properties properties, Supplier<EntityType<? extends RollingStockEntity>> type) {
        super(properties);
        this.type = type;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof TrackBlock)
                && !(state.getBlock() instanceof TrackOccupancyBlock)
                && !(state.getBlock() instanceof BaseRailBlock)) {
            return InteractionResult.PASS;
        }
        // Upstream refuses a sloped vanilla rail without a word: metadata two to five.
        if (state.getBlock() instanceof BaseRailBlock rail
                && state.getValue(rail.getShapeProperty()).isSlope()) {
            return InteractionResult.PASS;
        }
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        Player player = context.getPlayer();
        int meta = railMeta(level, pos);
        int dir = player == null ? 0 : Mth.floor(player.getYRot() * 8.0F / 360.0F + 0.5) & 7;

        RollingStockEntity stock = type.get().create(level, EntitySpawnReason.SPAWN_ITEM_USE);
        if (stock == null || !TraincraftConfig.allows(stock)) {
            return InteractionResult.FAIL;
        }
        // Keep the placement restrictions and preferred direction from the original rules.
        // The actual pose is resolved against rail geometry before adding the entity below.
        boolean vanillaRail = state.getBlock() instanceof BaseRailBlock;
        Float rotation;
        if (stock instanceof LocomotiveEntity) {
            rotation = locomotiveRotation(level, pos, meta, dir);
        } else if (!vanillaRail && !acceptsMeta(dir, meta)) {
            rotation = null;
        } else {
            rotation = DEFAULT_ROTATION[dir][railAxis(meta, vanillaRail) == DEFAULT_PRIMARY_META[dir] ? 0 : 1];
        }
        if (rotation == null) {
            if (player != null) {
                player.sendSystemMessage(
                        Component.literal("Place me on a straight piece of track!"));
            }
            return InteractionResult.FAIL;
        }
        float yaw = Mth.wrapDegrees(rotation - 90.0F);
        // Rail top plus the yOffset every position in the movement code carries. Placing at the
        // block's own Y instead drops the model through the sleepers.
        stock.snapTo(
                pos.getX() + 0.5,
                pos.getY() + 0.2 + RollingStockEntity.Y_OFFSET,
                pos.getZ() + 0.5,
                yaw,
                0.0F);

        if (player != null) {
            // The placer owns it, which is what the lock button in the menu checks against.
            stock.setOwner(player.getGameProfile().name());
        }
        // A cart that was picked up while coupled carries its id on the stack, and taking it back
        // is what lets the neighbour's stored link find it again.
        CustomData data = context.getItemInHand().get(DataComponents.CUSTOM_DATA);
        if (data != null) {
            String colour = data.copyTag().getStringOr("trainColor", stock.defaultColour());
            if (stock.spec().colours().contains(colour)) stock.setColour(colour);
            if (stock.supportsEngineNumber()) {
                stock.setEngineNumber(data.copyTag().getStringOr("engineNumber", ""));
            }
            int carried = data.copyTag().getIntOr("uniqueID", -1);
            if (carried != -1) {
                stock.setUniqueID(carried);
            }
        }
        stock.alignToTrackOnPlacement();
        level.addFreshEntity(stock);
        traincraft.Traincraft.LOGGER.info(
                "placed {} at {} facing {} (rail meta {}, player dir {})",
                stock.getType().builtInRegistryHolder().key().identifier(),
                pos.toShortString(),
                stock.getYRot(),
                meta,
                dir);

        ItemStack stack = context.getItemInHand();
        if (player == null || !player.hasInfiniteMaterials()) {
            stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }

    /**
     * The angle a locomotive takes, or null when there is no straight to put it on.
     *
     * <p>Upstream tests three rail directions per player direction, in order, and each wants two
     * more rails ahead of the one clicked. Anything else gets "Place me on a straight piece of
     * track!". Vanilla rails count towards the two, which is what lets a locomotive stand on them.
     */
    private static @Nullable Float locomotiveRotation(
            Level level, BlockPos pos, int meta, int dir) {
        for (Rule rule : RULES[dir]) {
            if (!rule.accepts(meta)) {
                continue;
            }
            if (isRail(level, pos.offset(rule.dx1, 0, rule.dz1))
                    && isRail(level, pos.offset(rule.dx2, 0, rule.dz2))) {
                return rule.rotation;
            }
        }
        return null;
    }

    /**
     * Whether a piece of stock may stand on track running this way, facing this way.
     *
     * <p>The three rail directions each player direction accepts are the same three the locomotive
     * rules below test; a cart differs only in not wanting two more rails ahead of it.
     */
    private static boolean acceptsMeta(int dir, int meta) {
        for (Rule rule : RULES[dir]) {
            if (rule.accepts(meta)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The metadata each player direction treats as its own axis, and the two angles that follow.
     *
     * <p>Upstream's {@code placeCart} opens every direction with the same shape: one rail direction
     * gets one angle and everything else gets another. Only a locomotive ever looks further.
     */
    private static final int[] DEFAULT_PRIMARY_META = {0, 0, 1, 0, 0, 0, 1, 0};

    private static final float[][] DEFAULT_ROTATION = {
        {90.0F, 0.0F},
        {90.0F, 180.0F},
        {180.0F, 90.0F},
        {-90.0F, 180.0F},
        {-90.0F, 180.0F},
        {-90.0F, 0.0F},
        {0.0F, -90.0F},
        {90.0F, 0.0F}
    };

    /** One accepted rail direction for one player direction. */
    private record Rule(int metaA, int metaB, int dx1, int dz1, int dx2, int dz2, float rotation) {
        boolean accepts(int meta) {
            return meta == metaA || meta == metaB;
        }
    }

    private static final Rule[][] RULES = {
        // dir 0 -- looking south
        {
            new Rule(2, 0, 0, 1, 0, 2, 90.0F),
            new Rule(6, 4, -1, 1, -2, 2, 135.0F),
            new Rule(5, 7, 1, 1, 2, 2, 45.0F)
        },
        // dir 1
        {
            new Rule(2, 0, 0, 1, 0, 2, 90.0F),
            new Rule(1, 3, -1, 0, -2, 0, 180.0F),
            new Rule(6, 4, -1, 1, -2, 2, 135.0F)
        },
        // dir 2 -- looking west
        {
            new Rule(1, 3, -1, 0, -2, 0, 180.0F),
            new Rule(5, 7, -1, -1, -2, -2, -135.0F),
            new Rule(6, 4, -1, 1, -2, 2, 135.0F)
        },
        // dir 3
        {
            new Rule(5, 7, -1, -1, -2, -2, -135.0F),
            new Rule(2, 0, 0, 1, 0, 2, -90.0F),
            new Rule(1, 3, -1, 0, -2, 0, 180.0F)
        },
        // dir 4 -- looking north
        {
            new Rule(0, 2, 0, -1, 0, -2, -90.0F),
            new Rule(5, 7, -1, -1, -2, -2, -135.0F),
            new Rule(6, 4, -1, 1, -2, 2, -45.0F)
        },
        // dir 5
        {
            new Rule(6, 4, 1, -1, 2, -2, -45.0F),
            new Rule(1, 3, 1, 0, 2, 0, 0.0F),
            new Rule(0, 2, 0, -1, 0, -2, -90.0F)
        },
        // dir 6 -- looking east
        {
            new Rule(1, 3, 1, 0, 2, 0, 0.0F),
            new Rule(5, 7, -1, -1, -2, -2, 45.0F),
            new Rule(6, 4, 1, -1, 2, -2, -45.0F)
        },
        // dir 7
        {
            new Rule(5, 7, 1, 1, 2, 2, 45.0F),
            new Rule(1, 3, 1, 0, 2, 0, 0.0F),
            new Rule(0, 2, 0, 1, 0, 2, 90.0F)
        },
    };

    private static boolean isRail(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.getBlock() instanceof TrackBlock
                || state.getBlock() instanceof TrackOccupancyBlock
                || state.getBlock() instanceof BaseRailBlock;
    }

    /**
     * The clicked rail's facing metadata, following a filler block back to the piece that owns it.
     *
     * <p>A filler block has no geometry of its own, so asking it which way it runs means asking the
     * rail it belongs to.
     */
    /**
     * The axis a rail runs along, 0 for north-south and 1 for east-west, which is what CE's car
     * table keys on: it was written for vanilla's flat metas 0 and 1, and a Traincraft straight
     * facing north reports 2, which read as "the other axis" and turned every car to yaw 0.
     */
    private static int railAxis(int meta, boolean vanillaRail) {
        if (!vanillaRail) {
            return meta & 1;
        }
        return switch (meta) {
            case 0, 4, 5 -> 0;
            case 1, 2, 3 -> 1;
            default -> meta;
        };
    }

    private static int railMeta(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof BaseRailBlock rail) {
            // Upstream reads getBasicRailMetadata here, which is the shape's own ordinal.
            return state.getValue(rail.getShapeProperty()).ordinal();
        }
        if (state.getBlock() instanceof TrackBlock) {
            return TrackBlock.metaFromFacing(state.getValue(TrackBlock.FACING));
        }
        if (level.getBlockEntity(pos) instanceof TrackOccupancyBlockEntity gag
                && level.getBlockEntity(gag.origin()) instanceof TrackBlockEntity owner) {
            return owner.getFacing();
        }
        return 0;
    }
}
