package traincraft.development.gametest;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.GameTestInstance;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.Map;
import java.util.function.Consumer;

/**
 * A game test whose body is a method in this mod, named by a string.
 *
 * <p>26.2 rebuilt the framework around data: a test is an entry in a registry with a codec, a
 * structure template and an environment, and the {@code @GameTest}-annotated static method is gone.
 * Vanilla's own answer is {@link net.minecraft.gametest.framework.FunctionGameTestInstance}, which
 * looks its body up in the {@code TEST_FUNCTION} registry -- a registry a mod cannot add to from
 * its own bus. This is the same idea with the lookup kept local: the codec carries a name, and
 * {@link RailwayGameTests} holds the map from name to body.
 *
 * <p>The alternative, a separate instance class per test, would need a codec and a registry entry
 * each. One class and a map is the same thing with less ceremony, and it keeps every assertion in
 * one file where it can be read as a list of claims about the mod.
 */
public class ScenarioTestInstance extends GameTestInstance {

    public static final MapCodec<ScenarioTestInstance> CODEC =
            RecordCodecBuilder.mapCodec(
                    instance ->
                            instance.group(
                                            com.mojang.serialization.Codec.STRING
                                                    .fieldOf("name")
                                                    .forGetter(ScenarioTestInstance::name),
                                            TestData.CODEC.forGetter(
                                                    ScenarioTestInstance::testInfo))
                                    .apply(instance, ScenarioTestInstance::new));

    private final String name;

    public ScenarioTestInstance(String name, TestData<Holder<TestEnvironmentDefinition<?>>> info) {
        super(info);
        this.name = name;
    }

    public String name() {
        return name;
    }

    /** {@code info()} is protected in the base class, and a codec getter has to be reachable. */
    private TestData<Holder<TestEnvironmentDefinition<?>>> testInfo() {
        return info();
    }

    @Override
    public void run(GameTestHelper helper) {
        Map<String, Consumer<GameTestHelper>> bodies = RailwayGameTests.bodies();
        Consumer<GameTestHelper> body = bodies.get(name);
        if (body == null) {
            throw new IllegalStateException(
                    "no test body called " + name + "; this mod knows " + bodies.keySet());
        }
        body.accept(helper);
    }

    @Override
    public MapCodec<ScenarioTestInstance> codec() {
        return CODEC;
    }

    @Override
    protected MutableComponent typeDescription() {
        return Component.literal("Traincraft test");
    }

    @Override
    public Component describe() {
        return describeType().append(Component.literal(" " + name)).append(describeInfo());
    }
}
