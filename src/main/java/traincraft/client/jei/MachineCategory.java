package traincraft.client.jei;

import com.mojang.serialization.Codec;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.ICodecHelper;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeHolderType;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

import traincraft.Traincraft;
import traincraft.production.MachineKind;
import traincraft.production.MachineRecipe;
import traincraft.production.ProductionRegistry;

final class MachineCategory implements IRecipeCategory<RecipeHolder<MachineRecipe>> {

    final MachineKind kind;
    private final IRecipeHolderType<MachineRecipe> type;
    private final Component title;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated progress;
    private final int originX;
    private final int originY;

    MachineCategory(MachineKind kind, IGuiHelper helper) {
        this.kind = kind;
        type = IRecipeHolderType.create(Identifier.fromNamespaceAndPath(Traincraft.MODID, kind.id));
        title = kind.assembly() ? Component.translatable("jei.tc.assembly_table") : Component.translatable("block.tc." + kind.id);
        Identifier texture = Identifier.fromNamespaceAndPath(Traincraft.MODID, "textures/gui/" + kind.texture + ".png");
        int[] crop = switch (kind) {
            case WORKBENCH -> new int[] {25, 12, 130, 54};
            case HEARTH -> new int[] {30, 12, 110, 64};
            case DISTILLERY -> new int[] {50, 2, 100, 80};
            default -> new int[] {18, 14, 148, 134};
        };
        originX = crop[0];
        originY = crop[1];
        background = helper.createDrawable(texture, crop[0], crop[1], crop[2], crop[3]);
        icon = helper.createDrawableItemLike(ProductionRegistry.item(kind.id));
        progress = switch (kind) {
            case HEARTH -> helper.drawableBuilder(texture, 176, 14, 24, 16).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
            case DISTILLERY -> helper.drawableBuilder(texture, 184, 15, 22, 41).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
            default -> null;
        };
    }

    @Override
    public IRecipeType<RecipeHolder<MachineRecipe>> getRecipeType() {
        return type;
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public int getWidth() {
        return background.getWidth();
    }

    @Override
    public int getHeight() {
        return background.getHeight();
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<MachineRecipe> holder, IFocusGroup focuses) {
        MachineRecipe recipe = holder.value();
        int[][] coordinates = kind.coordinates();
        for (MachineRecipe.Input input : recipe.inputs()) {
            builder.addInputSlot(coordinates[input.slot()][0] - originX, coordinates[input.slot()][1] - originY)
                    .addItemStacks(input.ingredient().items().map(item -> new ItemStack(item, input.count())).toList());
        }
        builder.addOutputSlot(coordinates[kind.output][0] - originX, coordinates[kind.output][1] - originY)
                .add(recipe.result().create())
                .addRichTooltipCallback((view, tooltip) -> {
                    if (recipe.chance() > 1) tooltip.add(Component.translatable("jei.tc.chance", recipe.chance()));
                    if (recipe.diesel() > 0) tooltip.add(Component.translatable("jei.tc.diesel", recipe.diesel()));
                });
    }

    /** JEI draws this before the slots; a drawable added in {@code createRecipeExtras} lands on top of them. */
    @Override
    public void draw(RecipeHolder<MachineRecipe> holder, IRecipeSlotsView slots, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
        background.draw(graphics);
        if (progress != null) progress.draw(graphics, (kind == MachineKind.HEARTH ? 79 : 87) - originX, (kind == MachineKind.HEARTH ? 34 : 36) - originY);
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, RecipeHolder<MachineRecipe> holder, IFocusGroup focuses) {
        if (kind.assembly()) {
            builder.addText(Component.translatable("jei.tc.tier", "I".repeat(holder.value().tier())), 60, 10).setPosition(2, 2).setColor(0xFFFFFF);
        }
    }

    @Override
    public Codec<RecipeHolder<MachineRecipe>> getCodec(ICodecHelper helper, IRecipeManager manager) {
        return helper.getSlowRecipeCategoryCodec(this, manager);
    }
}
