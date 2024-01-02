package com.visnaa.gemstonepower.data.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModBlocks;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PolarizerRecipe implements EnergyRecipe
{
    public static final ResourceLocation TYPE_ID = new ResourceLocation(GemstonePower.MOD_ID, "polarizer");
    private final Ingredient input;
    private final ItemStack output;
    private final int count;
    private final int processingTime;
    private final int energyUsage;

    public PolarizerRecipe(Ingredient input, ItemStack output, int count, int processingTime, int energyUsage)
    {
        this.input = input;
        this.output = output;
        this.count = count;
        this.processingTime = processingTime;
        this.energyUsage = energyUsage;
    }

    @Override
    public boolean matches(Container container, Level level)
    {
        if (level.isClientSide)
            return false;
        return input.test(container.getItem(0));
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        NonNullList<Ingredient> ingredients = NonNullList.withSize(1, input);
        return ingredients;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess access)
    {
        return new ItemStack(output.getItem(), getCount());
    }

    @Override
    public ItemStack getResultItem(@Nullable RegistryAccess access)
    {
        return output.copy();
    }

    @Override
    public RecipeType<?> getType()
    {
        return ModRecipes.POLARIZER_RECIPE;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return true;
    }

    @Override
    public boolean isIncomplete()
    {
        return true;
    }

    public int getCount()
    {
        return count;
    }

    public int getProcessingTime()
    {
        return processingTime;
    }

    public int getEnergyUsage()
    {
        return energyUsage;
    }

    public ItemStack getIcon()
    {
        return new ItemStack(ModBlocks.POLARIZER.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.POLARIZER_RECIPE_SERIALIZER.get();
    }

    public static class Type implements RecipeType<PolarizerRecipe>
    {
        @Override
        public String toString()
        {
            return PolarizerRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer implements RecipeSerializer<PolarizerRecipe>
    {
        private final Codec<PolarizerRecipe> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("input").forGetter(recipe -> recipe.getIngredients().get(0)),
                BuiltInRegistries.ITEM.byNameCodec().xmap(ItemStack::new, ItemStack::getItem).fieldOf("output").forGetter(recipe -> recipe.getResultItem(null)),
                Codec.INT.fieldOf("count").forGetter(PolarizerRecipe::getCount),
                Codec.INT.fieldOf("processingTime").forGetter(PolarizerRecipe::getProcessingTime),
                Codec.INT.fieldOf("energyUsage").forGetter(PolarizerRecipe::getEnergyUsage)
        ).apply(builder, PolarizerRecipe::new));

        @Override
        public Codec<PolarizerRecipe> codec()
        {
            return CODEC;
        }

        @Nullable
        @Override
        public PolarizerRecipe fromNetwork(FriendlyByteBuf buffer)
        {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
            {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            int count = buffer.readInt();
            int processingTime = buffer.readInt();
            int energyUsage = buffer.readInt();
            return new PolarizerRecipe(inputs.get(0), output, count, processingTime, energyUsage);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, PolarizerRecipe recipe)
        {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient seed : recipe.getIngredients())
            {
                seed.toNetwork(buffer);
            }
            buffer.writeItem(recipe.getResultItem(null));
            buffer.writeInt(recipe.getCount());
            buffer.writeInt(recipe.getProcessingTime());
            buffer.writeInt(recipe.getEnergyUsage());
        }
    }
}
