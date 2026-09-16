package traincraft;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.neoforged.neoforge.common.ModConfigSpec;

import traincraft.vehicle.entity.DieselLocomotiveEntity;
import traincraft.vehicle.entity.ElectricLocomotiveEntity;
import traincraft.vehicle.entity.RollingStockEntity;
import traincraft.vehicle.entity.SteamLocomotiveEntity;
import traincraft.vehicle.entity.TenderEntity;
import traincraft.vehicle.entity.TracksBuilderEntity;

public final class TraincraftConfig {

    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue FUEL_ORES;
    public static final ModConfigSpec.BooleanValue COPPER_ORE;
    public static final ModConfigSpec.BooleanValue STEAM_TRAINS;
    public static final ModConfigSpec.BooleanValue DIESEL_TRAINS;
    public static final ModConfigSpec.BooleanValue ELECTRIC_TRAINS;
    public static final ModConfigSpec.BooleanValue TRACKS_BUILDER;
    public static final ModConfigSpec.BooleanValue TENDERS;
    public static final ModConfigSpec.BooleanValue CHUNK_LOADING;
    public static final ModConfigSpec.BooleanValue POSSIBLE_COLOURS_IN_CHAT;
    public static final ModConfigSpec.BooleanValue REAL_TRAIN_SPEED;
    public static final ModConfigSpec.BooleanValue MODPACK_BALANCE;
    public static final ModConfigSpec.BooleanValue SPLIT_CREATIVE_TAB;
    public static final ModConfigSpec.IntValue WINDMILL_CHECK_RADIUS;
    public static final ModConfigSpec.BooleanValue DISABLE_TRAIN_WORKBENCH;
    public static final ModConfigSpec.BooleanValue WAGON_REMOVAL_NOTICES;
    public static final ModConfigSpec.BooleanValue TRANSPORT_LOGGING;
    public static final ModConfigSpec.ConfigValue<String> HOLIDAY_SKINS;
    public static final Map<String, ModConfigSpec.BooleanValue> SWITCHES;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        FUEL_ORES = builder.define("ENABLE_FUEL_ORES_SPAWN", true);
        COPPER_ORE = builder.define("ENABLE_COPPER_SPAWN", true);
        STEAM_TRAINS = builder.define("ENABLE_STEAM_TRAINS", true);
        DIESEL_TRAINS = builder.define("ENABLE_DIESEL_TRAINS", true);
        ELECTRIC_TRAINS = builder.define("ENABLE_ELECTRIC_TRAINS", true);
        TRACKS_BUILDER = builder.define("ENABLE_TRACKS_BUILDER", true);
        TENDERS = builder.define("ENABLE_TENDERS", true);
        CHUNK_LOADING = builder.define("ENABLE_CHUNK_LOADING", true);
        POSSIBLE_COLOURS_IN_CHAT = builder
                .comment("Disables the chat messages telling you the possible colors when coloring trains with dye")
                .define("SHOW_POSSIBLE_TRAINS_COLORS_IN_CHAT", true);
        REAL_TRAIN_SPEED = builder.define("REAL_TRAIN_SPEED", false);
        MODPACK_BALANCE = builder
                .comment("This will disable some of Traincrafts easier recipes to balance Modpacks")
                .define("MAKE_MODPACKS_GREAT_AGAIN", false);
        SPLIT_CREATIVE_TAB = builder
                .comment("setting this to true will split the creative tab in 2, one is used for trains, the other for materials and Tracks.")
                .define("SPLIT_CREATIVE_TAB", false);
        WINDMILL_CHECK_RADIUS = builder
                .comment("This sets the radius for the can-see-the-sky-check area around the windmill. 0=only location of windmill, 1=3x3, 2=5x5 etc. Use -1 to turn of this check completely.")
                .defineInRange("WINDMILL_CHECK_RADIUS", 1, -1, 10);
        DISABLE_TRAIN_WORKBENCH = builder
                .comment("disables the train workbench, for those of you who want to use a custom part builder")
                .define("DISABLE_TRAIN_WORKBENCH", false);
        WAGON_REMOVAL_NOTICES = builder
                .comment("When OP and creative mode, tells you the owner of the train or rollingstock you just removed")
                .define("ENABLE_WAGON_REMOVAL_NOTICES", true);
        TRANSPORT_LOGGING = builder
                .comment("Logs the data for trains and rollingstock, turning this off will improve performance but break the admin book")
                .define("ENABLE_TRANSPORT_LOGGING", true);
        HOLIDAY_SKINS = builder
                .comment("Enables seasonal holiday skins for certain trains and stock. Options: True, False, Force")
                .defineInList("ENABLE_HOLIDAY_SKINS", "True", Arrays.asList("True", "False", "Force"));
        SPEC = builder.build();
        SWITCHES = Map.of(
                "ENABLE_FUEL_ORES_SPAWN", FUEL_ORES,
                "ENABLE_COPPER_SPAWN", COPPER_ORE,
                "MAKE_MODPACKS_GREAT_AGAIN", MODPACK_BALANCE,
                "DISABLE_TRAIN_WORKBENCH", DISABLE_TRAIN_WORKBENCH);
    }

    public static boolean allows(RollingStockEntity stock) {
        return switch (stock) {
            case SteamLocomotiveEntity ignored -> STEAM_TRAINS.get();
            case DieselLocomotiveEntity ignored -> DIESEL_TRAINS.get();
            case ElectricLocomotiveEntity ignored -> ELECTRIC_TRAINS.get();
            case TracksBuilderEntity ignored -> TRACKS_BUILDER.get();
            case TenderEntity ignored -> TENDERS.get();
            default -> true;
        };
    }

    private TraincraftConfig() {}
}
