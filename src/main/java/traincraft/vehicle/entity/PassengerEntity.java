package traincraft.vehicle.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import traincraft.vehicle.coupling.Coupling;

public abstract class PassengerEntity extends RollingStockEntity {

    protected PassengerEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    protected boolean rideable() {
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
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
        if (Coupling.onClickWithStake(this, player.getItemInHand(hand), player, hand)) {
            return InteractionResult.SUCCESS;
        }
        if (rideable() && getFirstPassenger() == null) {
            player.startRiding(this);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal(spec().displayName());
    }

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
            if (source.getEntity() instanceof Player player && !player.getAbilities().instabuild) {
                spawnAtLocation(level, dropStack());
            }
        }
        return true;
    }
}
