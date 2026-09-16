package traincraft.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import traincraft.bootstrap.EntityRegistry;
import traincraft.vehicle.entity.ZeppelinEntity;

public class ZeppelinItem extends Item {

    private static final double MAX_SHIFT = 8.0;
    private static final double CLEARANCE = 1.5;

    private final boolean twoBalloons;

    public ZeppelinItem(Properties properties, boolean twoBalloons) {
        super(properties);
        this.twoBalloons = twoBalloons;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockHitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hit.getType() != HitResult.Type.BLOCK) {
            return InteractionResult.PASS;
        }
        if (!level.isClientSide()) {
            var type = twoBalloons ? EntityRegistry.AIRSHIP.get() : EntityRegistry.ZEPPELIN.get();
            ZeppelinEntity zeppelin = type.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
            var pos = hit.getBlockPos();
            zeppelin.setYRot(player.getYRot());
            Vec3 away = Vec3.directionFromRotation(0.0F, player.getYRot());
            AABB clearance = player.getBoundingBox().inflate(CLEARANCE, 0.0, CLEARANCE);
            String obstruction = null;
            for (double shift = 0.0; shift <= MAX_SHIFT; shift += 0.5) {
                zeppelin.setPos(pos.getX() + 0.5 + away.x * shift, pos.getY() + 1.5, pos.getZ() + 0.5 + away.z * shift);
                obstruction = zeppelin.obstruction(clearance);
                if (obstruction == null) {
                    level.addFreshEntity(zeppelin);
                    stack.consume(1, player);
                    return InteractionResult.SUCCESS;
                }
            }
            player.sendSystemMessage(Component.literal("There is no room for a zeppelin here: " + obstruction));
            return InteractionResult.FAIL;
        }
        return InteractionResult.SUCCESS;
    }
}
