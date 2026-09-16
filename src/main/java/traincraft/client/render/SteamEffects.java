package traincraft.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

import traincraft.vehicle.entity.LocomotiveEntity;

public final class SteamEffects {

    public static final double[][] ALICE_EMITTERS = {{2.2, 1.6, 0.0}};

    public static final int ALICE_ITERATIONS = 20;

    private static final float MAX_PITCH = 30.0F;

    private static final float SMOKE_YAW_OFFSET = 90.0F;

    private SteamEffects() {}

    public static void emit(
            LocomotiveEntity loco, float yaw, float pitch, double[][] emitters, int iterations) {
        emit(loco, yaw, pitch, emitters, iterations, ParticleTypes.SMOKE);
    }

    public static void emit(
            LocomotiveEntity loco,
            float yaw,
            float pitch,
            double[][] emitters,
            int iterations,
            ParticleOptions particle) {
        if (iterations <= 0 || emitters.length == 0) {
            return;
        }
        if (!loco.isEngineOn() || loco.getFuel() <= 0 || Math.abs(pitch) > MAX_PITCH) {
            return;
        }
        Level level = loco.level();
        double speed = Math.abs(loco.getSpeedKmH());
        if (Minecraft.getInstance().level == null) {
            return;
        }
        int draw = level.getRandom().nextInt(10 * iterations);
        if (draw >= iterations * 4 + speed * 5.0) {
            return;
        }

        for (int i = 0; i < iterations; i++) {
            for (double[] emitter : emitters) {
                double[] offset =
                        rotate(emitter[0], emitter[1], emitter[2], pitch, yaw + SMOKE_YAW_OFFSET);
                level.addParticle(
                        particle,
                        loco.getX() + offset[0],
                        loco.getY() + offset[1],
                        loco.getZ() + offset[2],
                        0.0,
                        0.0,
                        0.0);
            }
        }
    }

    public static void emitCylinderSteam(
            LocomotiveEntity loco,
            float yaw,
            float pitch,
            double[][] emitters,
            int iterations,
            ParticleOptions particle) {
        if (iterations <= 0 || emitters.length == 0) {
            return;
        }
        if (!loco.isEngineOn() || loco.getFuel() <= 0 || Math.abs(pitch) > MAX_PITCH) {
            return;
        }
        Level level = loco.level();
        if (Minecraft.getInstance().level == null) {
            return;
        }
        if (level.getRandom().nextInt(300) >= iterations * 10) {
            return;
        }
        double lift = Math.tan(Math.toDegrees(pitch)) * 4.0;
        for (int i = 0; i < iterations; i++) {
            for (double[] emitter : emitters) {
                double up = emitter[1] + lift * -emitter[1];
                for (double side : new double[] {emitter[2], -emitter[2]}) {
                    double[] offset = rotate(emitter[0], up, side, pitch, yaw + SMOKE_YAW_OFFSET);
                    level.addParticle(
                            particle,
                            loco.getX() + offset[0],
                            loco.getY() + offset[1],
                            loco.getZ() + offset[2],
                            0.0,
                            0.0,
                            0.0);
                }
            }
        }
    }

    private static double[] rotate(double x, double y, double z, float pitch, float yaw) {
        double[] xyz = {x, y, z};
        if (pitch != 0.0F) {
            float radians = pitch * Mth.DEG_TO_RAD;
            float cos = Mth.cos(radians);
            float sin = Mth.sin(radians);
            xyz[0] = y * sin + x * cos;
            xyz[1] = y * cos - x * sin;
        }
        if (yaw != 0.0F) {
            float radians = yaw * Mth.DEG_TO_RAD;
            float cos = Mth.cos(radians);
            float sin = Mth.sin(radians);
            xyz[0] = x * cos - z * sin;
            xyz[2] = x * sin + z * cos;
        }
        return xyz;
    }
}
