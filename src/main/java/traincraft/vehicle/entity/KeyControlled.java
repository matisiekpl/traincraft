package traincraft.vehicle.entity;

import net.minecraft.world.entity.player.Player;

public interface KeyControlled {

    void setKeyHeld(int key, boolean held);

    boolean mayControl(Player player);
}
