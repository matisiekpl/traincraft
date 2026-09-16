package traincraft.vehicle.sound;

import net.minecraft.sounds.SoundEvent;

import org.jspecify.annotations.Nullable;

public record LocomotiveSounds(
        @Nullable SoundEvent horn,
        float hornVolume,
        @Nullable SoundEvent run,
        float runVolume,
        int runLength,
        @Nullable SoundEvent idle,
        float idleVolume,
        int idleLength,
        boolean changeWithSpeed) {

    /** Stock with nothing to say. */
    public static final LocomotiveSounds SILENT =
            new LocomotiveSounds(null, 0.0F, null, 0.0F, 0, null, 0.0F, 0, false);
}
