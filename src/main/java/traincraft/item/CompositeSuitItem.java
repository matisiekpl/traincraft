package traincraft.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import org.jspecify.annotations.Nullable;

public class CompositeSuitItem extends Item {

    private static final int CURE_COST = 5;

    public CompositeSuitItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (!(owner instanceof Player player) || slot == null || !slot.isArmor()) {
            return;
        }
        switch (slot) {
            case HEAD -> helmet(stack, level, player);
            case CHEST -> {
                if (player.getHealth() < player.getMaxHealth() && player.tickCount % 100 == 0) {
                    player.heal(1.0F);
                    stack.hurtAndBreak(1, player, slot);
                }
            }
            case LEGS -> {
                if (player.isOnFire()) {
                    player.clearFire();
                    stack.hurtAndBreak(1, player, slot);
                }
            }
            default -> {}
        }
    }

    private void helmet(ItemStack stack, ServerLevel level, Player player) {
        for (var effect : new net.minecraft.core.Holder[] {MobEffects.POISON, MobEffects.WITHER, MobEffects.BLINDNESS, MobEffects.NAUSEA}) {
            if (player.hasEffect(effect)) {
                player.removeEffect(effect);
                stack.hurtAndBreak(CURE_COST, player, EquipmentSlot.HEAD);
            }
        }
        if (player.isInWater() && !player.hasEffect(MobEffects.WATER_BREATHING)) {
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 200, 0));
            stack.hurtAndBreak(1, player, EquipmentSlot.HEAD);
        }
        var eyes = player.blockPosition().above();
        if (level.getMaxLocalRawBrightness(eyes) <= 4 && level.getBlockState(eyes).isAir()) {
            MobEffectInstance nightVision = player.getEffect(MobEffects.NIGHT_VISION);
            if (nightVision == null || nightVision.getDuration() < 220) {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 820, 0, true, false));
                stack.hurtAndBreak(1, player, EquipmentSlot.HEAD);
            }
        }
    }
}
