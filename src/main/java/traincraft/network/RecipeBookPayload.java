package traincraft.network;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplayContext;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import traincraft.Traincraft;
import traincraft.production.MachineKind;
import traincraft.production.MachineRecipe;
import traincraft.production.ProductionRegistry;

/** The recipe book's pages: one train workbench and one assembly table recipe per output item, as CE's list cleaners left them. */
public record RecipeBookPayload(List<BookRecipe> workbench, List<BookRecipe> assembly) implements CustomPacketPayload {

    public static final Type<RecipeBookPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(Traincraft.MODID, "recipe_book"));

    public static final StreamCodec<RegistryFriendlyByteBuf, RecipeBookPayload> CODEC = StreamCodec.composite(
            BookRecipe.CODEC.apply(ByteBufCodecs.list()), RecipeBookPayload::workbench,
            BookRecipe.CODEC.apply(ByteBufCodecs.list()), RecipeBookPayload::assembly,
            RecipeBookPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void send(ServerPlayer player) {
        ServerLevel level = player.level();
        Set<Item> craftingTable = new HashSet<>();
        for (var holder : level.recipeAccess().recipeMap().byType(RecipeType.CRAFTING)) {
            for (RecipeDisplay display : holder.value().display()) {
                craftingTable.add(display.result().resolveForFirstStack(SlotDisplayContext.fromLevel(level)).getItem());
            }
        }
        Map<Item, BookRecipe> workbench = new LinkedHashMap<>();
        Map<Item, BookRecipe> assembly = new LinkedHashMap<>();
        for (var holder : level.recipeAccess().recipeMap().byType(ProductionRegistry.RECIPE_TYPE.get())) {
            MachineRecipe recipe = holder.value();
            boolean table = recipe.machine().startsWith("assembly");
            if (!table && !recipe.machine().equals(MachineKind.WORKBENCH.id)) {
                continue;
            }
            List<ItemStack> inputs = new ArrayList<>();
            for (int slot = 0; slot < (table ? MachineKind.ASSEMBLY.inputs : MachineKind.WORKBENCH.inputs); slot++) {
                inputs.add(ItemStack.EMPTY);
            }
            for (MachineRecipe.Input input : recipe.inputs()) {
                input.ingredient().items().findFirst().ifPresent(item -> inputs.set(input.slot(), new ItemStack(item.value(), input.count())));
            }
            ItemStack output = recipe.result().create();
            (table ? assembly : workbench).putIfAbsent(output.getItem(), new BookRecipe(inputs, output, recipe.tier(), craftingTable.contains(output.getItem())));
        }
        Comparator<BookRecipe> byName = Comparator.comparing(recipe -> recipe.output().getHoverName().getString());
        PacketDistributor.sendToPlayer(player, new RecipeBookPayload(
                workbench.values().stream().sorted(byName).toList(),
                assembly.values().stream().sorted(byName).toList()));
    }

    public static void handle(RecipeBookPayload payload, IPayloadContext context) {
        traincraft.client.ClientScreens.openRecipeBook(payload);
    }
}
