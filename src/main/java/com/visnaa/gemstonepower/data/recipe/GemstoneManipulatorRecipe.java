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

public class GemstoneManipulatorRecipe implements EnergyRecipe
{
    public static final ResourceLocation TYPE_ID = new ResourceLocation(GemstonePower.MOD_ID, "alloy_smelter");
    private final Ingredient input1;
    private final Ingredient input2;
    private final ItemStack output;
    private final int count;
    private final int processingTime;
    private final int energyUsage;

    public GemstoneManipulatorRecipe(Ingredient input1, Ingredient input2, ItemStack output, int count, int processingTime, int energyUsage)
    {
        this.input1 = input1;
        this.input2 = input2;
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
        return (input1.test(container.getItem(0)) && input2.test(container.getItem(1))) ||
                (input1.test(container.getItem(1)) && input2.test(container.getItem(0)));
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return NonNullList.of(Ingredient.EMPTY, input1, input2);
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
        return ModRecipes.GEMSTONE_MANIPULATOR_RECIPE;
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
        return new ItemStack(ModBlocks.GEMSTONE_MANIPULATOR.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.GEMSTONE_MANIPULATOR_RECIPE_SERIALIZER.get();
    }

    public static class Type implements RecipeType<GemstoneManipulatorRecipe>
    {
        @Override
        public String toString()
        {
            return GemstoneManipulatorRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer implements RecipeSerializer<GemstoneManipulatorRecipe>
    {
        private final Codec<GemstoneManipulatorRecipe> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("input1").forGetter(recipe -> recipe.getIngredients().get(0)),
                Ingredient.CODEC_NONEMPTY.fieldOf("input2").forGetter(recipe -> recipe.getIngredients().get(1)),
                BuiltInRegistries.ITEM.byNameCodec().xmap(ItemStack::new, ItemStack::getItem).fieldOf("output").forGetter(recipe -> recipe.getResultItem(null)),
                Codec.INT.fieldOf("count").forGetter(GemstoneManipulatorRecipe::getCount),
                Codec.INT.fieldOf("processingTime").forGetter(GemstoneManipulatorRecipe::getProcessingTime),
                Codec.INT.fieldOf("energyUsage").forGetter(GemstoneManipulatorRecipe::getEnergyUsage)
        ).apply(builder, GemstoneManipulatorRecipe::new));

        @Override
        public Codec<GemstoneManipulatorRecipe> codec()
        {
            return CODEC;
        }

        @Nullable
        @Override
        public GemstoneManipulatorRecipe fromNetwork(FriendlyByteBuf buffer)
        {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
                inputs.set(i, Ingredient.fromNetwork(buffer));

            ItemStack output = buffer.readItem();
            int count = buffer.readInt();
            int processingTime = buffer.readInt();
            int energyUsage = buffer.readInt();
            return new GemstoneManipulatorRecipe(inputs.get(0), inputs.get(1), output, count, processingTime, energyUsage);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, GemstoneManipulatorRecipe recipe)
        {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient input : recipe.getIngredients())
                input.toNetwork(buffer);
            buffer.writeItem(recipe.getResultItem(null));
            buffer.writeInt(recipe.getCount());
            buffer.writeInt(recipe.getProcessingTime());
            buffer.writeInt(recipe.getEnergyUsage());
        }
    }
}
