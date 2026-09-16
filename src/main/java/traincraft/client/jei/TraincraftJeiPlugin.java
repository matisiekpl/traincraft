package traincraft.client.jei;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

import traincraft.Traincraft;
import traincraft.client.ClientRecipes;
import traincraft.production.MachineKind;
import traincraft.production.MachineRecipe;
import traincraft.production.ProductionRegistry;

@JeiPlugin
public final class TraincraftJeiPlugin implements IModPlugin {

    private static final Identifier UID = Identifier.fromNamespaceAndPath(Traincraft.MODID, "jei");
    private static final List<MachineKind> KINDS = List.of(MachineKind.WORKBENCH, MachineKind.ASSEMBLY, MachineKind.HEARTH, MachineKind.DISTILLERY);

    private final Set<ResourceKey<Recipe<?>>> registered = new HashSet<>();
    private List<MachineCategory> categories = List.of();
    private IJeiRuntime runtime;

    public TraincraftJeiPlugin() {
        ClientRecipes.listen(this::addReceivedRecipes);
    }

    @Override
    public Identifier getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        categories = KINDS.stream().map(kind -> new MachineCategory(kind, registration.getJeiHelpers().getGuiHelper())).toList();
        registration.addRecipeCategories(categories.toArray(MachineCategory[]::new));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registered.clear();
        for (MachineCategory category : categories) {
            List<RecipeHolder<MachineRecipe>> recipes = ClientRecipes.machine(category.kind);
            recipes.forEach(holder -> registered.add(holder.id()));
            registration.addRecipes(category.getRecipeType(), recipes);
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        for (MachineCategory category : categories) {
            if (category.kind == MachineKind.ASSEMBLY) {
                registration.addCraftingStation(category.getRecipeType(), ProductionRegistry.item(MachineKind.ASSEMBLY_I.id),
                        ProductionRegistry.item(MachineKind.ASSEMBLY_II.id), ProductionRegistry.item(MachineKind.ASSEMBLY.id));
            } else {
                registration.addCraftingStation(category.getRecipeType(), ProductionRegistry.item(category.kind.id));
            }
        }
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        runtime = jeiRuntime;
        addReceivedRecipes();
    }

    @Override
    public void onRuntimeUnavailable() {
        runtime = null;
        registered.clear();
    }

    private void addReceivedRecipes() {
        if (runtime == null) return;
        for (MachineCategory category : categories) {
            List<RecipeHolder<MachineRecipe>> recipes = ClientRecipes.machine(category.kind).stream()
                    .filter(holder -> registered.add(holder.id())).toList();
            if (!recipes.isEmpty()) runtime.getRecipeManager().addRecipes(category.getRecipeType(), recipes);
        }
    }
}
