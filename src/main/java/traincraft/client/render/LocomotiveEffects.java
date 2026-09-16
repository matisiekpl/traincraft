package traincraft.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import traincraft.Traincraft;
import traincraft.vehicle.entity.AliceLocomotiveEntity;
import traincraft.vehicle.entity.Br80LocomotiveEntity;
import traincraft.vehicle.entity.LocomotiveEntity;

/** Particle emission is scheduled in game ticks, independently of render passes. */
@EventBusSubscriber(modid = Traincraft.MODID, value = Dist.CLIENT)
public final class LocomotiveEffects {
    private LocomotiveEffects() {}

    @SubscribeEvent
    static void tick(ClientTickEvent.Post event) {
        Minecraft client = Minecraft.getInstance();
        if (client.level == null || client.isPaused()) return;
        for (var entity : client.level.entitiesForRendering()) {
            if (!(entity instanceof LocomotiveEntity locomotive)) continue;
            float yaw = entity.getYRot();
            float pitch = entity.getXRot();
            var exhaust = locomotive.exhaust();
            if (exhaust != null) {
                SteamEffects.emit(locomotive, yaw, pitch, exhaust.chimneys(), exhaust.smokeIterations(), exhaust.smoke());
                SteamEffects.emitCylinderSteam(locomotive, yaw, pitch, exhaust.cylinders(), exhaust.cylinderIterations(), exhaust.cylinderSteam());
            } else if (entity instanceof traincraft.vehicle.entity.Es44LocomotiveEntity) {
                SteamEffects.emit(locomotive, yaw, pitch, ES44_EXHAUST, 5, ParticleTypes.LARGE_SMOKE);
            } else if (entity instanceof traincraft.vehicle.entity.Sd40LocomotiveEntity) {
                SteamEffects.emit(locomotive, yaw, pitch, SD40_EXHAUST, 5);
            } else if (entity instanceof traincraft.vehicle.entity.Sd70LocomotiveEntity) {
                SteamEffects.emit(locomotive, yaw, pitch, SD70_EXHAUST, 5);
            } else if (entity instanceof traincraft.vehicle.entity.V60LocomotiveEntity) {
                SteamEffects.emit(locomotive, yaw, pitch, V60_EXHAUST, 4);
            } else if (entity instanceof Br80LocomotiveEntity) {
                SteamEffects.emit(
                        locomotive, yaw, pitch, BR80_CHIMNEY, 3, ParticleTypes.LARGE_SMOKE);
                SteamEffects.emitCylinderSteam(
                        locomotive, yaw, pitch, BR80_CYLINDER, 4, ParticleTypes.POOF);
            } else if (entity instanceof AliceLocomotiveEntity) {
                SteamEffects.emit(
                        locomotive,
                        yaw,
                        pitch,
                        SteamEffects.ALICE_EMITTERS,
                        SteamEffects.ALICE_ITERATIONS);
                SteamEffects.emitCylinderSteam(
                        locomotive, yaw, pitch, ALICE_CYLINDER, 2, ParticleTypes.POOF);
            }
        }
    }

    private static final double[][] ES44_EXHAUST = {{1.0, 1.4, 0.0}};
    private static final double[][] SD40_EXHAUST = {{1.45, 1.45, 0.0}};
    private static final double[][] SD70_EXHAUST = {{0.4, 1.3, 0.0}};
    private static final double[][] V60_EXHAUST = {{0.75, 1.7, 0.0}};
    private static final double[][] BR80_CHIMNEY = {{1.8, 1.75, 0}};
    private static final double[][] BR80_CYLINDER = {{1.6, -0.4, 0.8}};
    private static final double[][] ALICE_CYLINDER = {{1.8, -0.4, 0.8}};
}
