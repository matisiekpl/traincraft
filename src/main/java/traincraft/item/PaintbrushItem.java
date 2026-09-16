package traincraft.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import traincraft.vehicle.entity.RollingStockEntity;

public class PaintbrushItem extends Item {

    private static final double REACH = 5.0;

    public PaintbrushItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide() || !player.isShiftKeyDown()) {
            return InteractionResult.PASS;
        }
        Vec3 eyes = player.getEyePosition();
        Vec3 end = eyes.add(player.getLookAngle().scale(REACH));
        EntityHitResult hit = net.minecraft.world.entity.projectile.ProjectileUtil.getEntityHitResult(
                level, player, eyes, end, player.getBoundingBox().expandTowards(player.getLookAngle().scale(REACH)).inflate(1.0),
                entity -> entity instanceof RollingStockEntity, 0.3F);
        if (hit == null || !(hit.getEntity() instanceof RollingStockEntity stock)
                || stock.spec().colours().isEmpty() && !stock.supportsEngineNumber()) {
            return InteractionResult.PASS;
        }
        if (stock.supportsEngineNumber()) {
            traincraft.client.ClientScreens.openEngineNumber(stock);
        } else {
            traincraft.client.ClientScreens.openLivery(stock);
        }
        return InteractionResult.SUCCESS;
    }
}
