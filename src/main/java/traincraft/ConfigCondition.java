package traincraft;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

/** A datapack condition that holds while the named Traincraft config switch has the given value. */
public record ConfigCondition(String key, boolean value) implements ICondition {

    public static final MapCodec<ConfigCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("key").forGetter(ConfigCondition::key),
            Codec.BOOL.optionalFieldOf("value", true).forGetter(ConfigCondition::value)
    ).apply(instance, ConfigCondition::new));

    public static final DeferredRegister<MapCodec<? extends ICondition>> CODECS =
            DeferredRegister.create(NeoForgeRegistries.Keys.CONDITION_CODECS, Traincraft.MODID);

    public static final DeferredHolder<MapCodec<? extends ICondition>, MapCodec<ConfigCondition>> CONFIG =
            CODECS.register("config", () -> CODEC);

    public ConfigCondition {
        if (!TraincraftConfig.SWITCHES.containsKey(key)) {
            throw new IllegalArgumentException("Unknown Traincraft config switch " + key);
        }
    }

    @Override
    public boolean test(IContext context) {
        return TraincraftConfig.SWITCHES.get(key).get() == value;
    }

    @Override
    public MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
}
