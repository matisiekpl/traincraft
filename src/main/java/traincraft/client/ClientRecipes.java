package traincraft.client;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;

import traincraft.Traincraft;
import traincraft.production.MachineKind;
import traincraft.production.MachineRecipe;
import traincraft.production.ProductionRegistry;

@EventBusSubscriber(modid = Traincraft.MODID, value = Dist.CLIENT)
public final class ClientRecipes {

    private static List<RecipeHolder<MachineRecipe>> machine = List.of();
    private static final List<Runnable> listeners = new ArrayList<>();

    private ClientRecipes() {}

    public static List<RecipeHolder<MachineRecipe>> machine(MachineKind kind) {
        return machine.stream().filter(holder -> holder.value().machine().equals(kind.id)).toList();
    }

    public static void listen(Runnable listener) {
        listeners.add(listener);
    }

    @SubscribeEvent
    static void received(RecipesReceivedEvent event) {
        machine = List.copyOf(event.getRecipeMap().byType(ProductionRegistry.RECIPE_TYPE.get()));
        listeners.forEach(Runnable::run);
    }

    @SubscribeEvent
    static void loggedOut(ClientPlayerNetworkEvent.LoggingOut event) {
        machine = List.of();
    }
}
