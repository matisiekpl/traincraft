package traincraft.vehicle.definition;

import net.minecraft.core.particles.ParticleOptions;

public record Exhaust(
        ParticleOptions smoke,
        int smokeIterations,
        double[][] chimneys,
        ParticleOptions cylinderSteam,
        int cylinderIterations,
        double[][] cylinders) {}
