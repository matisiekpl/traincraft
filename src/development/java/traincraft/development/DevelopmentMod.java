package traincraft.development;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import traincraft.development.gametest.GameTestRegistry;

/** Registers test infrastructure only in development runs. */
@Mod(DevelopmentMod.MODID)
public final class DevelopmentMod {
    public static final String MODID = "tc_development";

    public DevelopmentMod(IEventBus bus) {
        GameTestRegistry.TYPES.register(bus);
    }
}
