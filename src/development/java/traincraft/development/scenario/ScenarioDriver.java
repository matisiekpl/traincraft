package traincraft.development.scenario;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.level.ServerLevel;
import net.neoforged.neoforge.common.util.FakePlayerFactory;

import traincraft.vehicle.entity.LocomotiveEntity;

/** Drives scenarios through the same passenger and control rules as a player. */
final class ScenarioDriver {
    private ScenarioDriver() {}

    static void mount(LocomotiveEntity locomotive, boolean driving) {
        var player =
                FakePlayerFactory.get(
                        (ServerLevel) locomotive.level(),
                        new GameProfile(locomotive.getUUID(), "ScenarioDriver"));
        if (driving && locomotive.getFirstPassenger() == null) {
            player.setPos(locomotive.position());
            player.setYRot(locomotive.getYRot());
            player.startRiding(locomotive);
        } else if (!driving && player.getVehicle() == locomotive) {
            player.stopRiding();
        }
    }
}
