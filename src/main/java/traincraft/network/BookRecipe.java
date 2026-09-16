package traincraft.network;

import java.util.List;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

/** One page of the recipe book: the slot-addressed inputs and what they make. */
public record BookRecipe(List<ItemStack> inputs, ItemStack output, int tier, boolean craftingTable) {

    public static final StreamCodec<RegistryFriendlyByteBuf, BookRecipe> CODEC = StreamCodec.composite(
            ItemStack.OPTIONAL_STREAM_CODEC.apply(ByteBufCodecs.list()), BookRecipe::inputs,
            ItemStack.OPTIONAL_STREAM_CODEC, BookRecipe::output,
            ByteBufCodecs.VAR_INT, BookRecipe::tier,
            ByteBufCodecs.BOOL, BookRecipe::craftingTable,
            BookRecipe::new);
}
