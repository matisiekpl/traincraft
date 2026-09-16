package traincraft.development.gametest;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTestInstance;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import traincraft.Traincraft;

/**
 * The codec that lets this mod's game tests exist.
 *
 * <p>A {@link GameTestInstance} is dispatched by its codec through {@code TEST_INSTANCE_TYPE},
 * which is a registry rather than a hard-coded list -- so a mod can add its own kind of test
 * without touching vanilla's. This registers exactly one: {@link ScenarioTestInstance}, whose body
 * is looked up by name.
 */
public final class GameTestRegistry {

    public static final DeferredRegister<MapCodec<? extends GameTestInstance>> TYPES =
            DeferredRegister.create(Registries.TEST_INSTANCE_TYPE, Traincraft.MODID);

    public static final DeferredHolder<
                    MapCodec<? extends GameTestInstance>, MapCodec<ScenarioTestInstance>>
            TC_TEST = TYPES.register("tc_test", () -> ScenarioTestInstance.CODEC);

    private GameTestRegistry() {}
}
