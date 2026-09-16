package traincraft.client;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class TraincraftClientConfig {

    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue AUTO_THIRD_PERSON;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        AUTO_THIRD_PERSON = builder
                .comment("Switches the camera to third person when boarding a locomotive and back when leaving")
                .define("AUTO_THIRD_PERSON", true);
        SPEC = builder.build();
    }

    private TraincraftClientConfig() {}
}
