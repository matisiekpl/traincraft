package traincraft;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import traincraft.bootstrap.BlockEntityRegistry;
import traincraft.bootstrap.BlockRegistry;
import traincraft.bootstrap.CreativeTabs;
import traincraft.bootstrap.EntityRegistry;
import traincraft.bootstrap.ItemRegistry;
import traincraft.bootstrap.MenuRegistry;
import traincraft.bootstrap.SoundRegistry;
import traincraft.bootstrap.TrackItemRegistry;

@Mod(Traincraft.MODID)
public class Traincraft {

    /** Temporary movement tracing, switched on with -Dtc.trace=true. */
    public static final boolean TRACE = Boolean.getBoolean("tc.trace");

    public static final String MODID = "tc";
    public static final net.neoforged.neoforge.common.world.chunk.TicketController CHUNK_TICKETS =
            new net.neoforged.neoforge.common.world.chunk.TicketController(net.minecraft.resources.Identifier.fromNamespaceAndPath("tc", "locomotives"));
    public static final Logger LOGGER = LoggerFactory.getLogger("Traincraft");

    public Traincraft(IEventBus modBus, ModContainer container) {
        LOGGER.info("Traincraft loading");
        container.registerConfig(ModConfig.Type.COMMON, TraincraftConfig.SPEC);
        ConfigCondition.CODECS.register(modBus);
        traincraft.village.VillageRegistry.register(modBus);
        traincraft.production.ProductionRegistry.register(modBus);
        traincraft.structure.StructureRegistry.touch();
        traincraft.item.TraincraftItems.touch();
        traincraft.structure.FluidRegistry.register(modBus);
        modBus.addListener(traincraft.structure.StructureRegistry::registerCapabilities);
        modBus.addListener((net.neoforged.neoforge.common.world.chunk.RegisterTicketControllersEvent event) -> event.register(CHUNK_TICKETS));
        BlockRegistry.BLOCKS.register(modBus);
        BlockEntityRegistry.TYPES.register(modBus);
        EntityRegistry.TYPES.register(modBus);
        ItemRegistry.ITEMS.register(modBus);
        TrackItemRegistry.ITEMS.register(modBus);
        MenuRegistry.TYPES.register(modBus);
        CreativeTabs.TABS.register(modBus);
        SoundRegistry.SOUNDS.register(modBus);
    }
}
