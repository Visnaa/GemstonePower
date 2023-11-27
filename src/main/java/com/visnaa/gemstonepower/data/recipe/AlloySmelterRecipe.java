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

public class AlloySmelterRecipe implements EnergyRecipe
{
    public static final ResourceLocation TYPE_ID = new ResourceLocation(GemstonePower.MOD_ID, "alloy_smelter");
    private final Ingredient input1;
    private final Ingredient input2;
    private final int amount1;
    private final int amount2;
    private final ItemStack output;
    private final int count;
    private final int processingTime;
    private final int energyUsage;

    public AlloySmelterRecipe(Ingredient input1, Ingredient input2, int amount1, int amount2, ItemStack output, int count, int processingTime, int energyUsage)
    {
        this.input1 = input1;
        this.input2 = input2;
        this.amount1 = amount1;
        this.amount2 = amount2;
        this.output = output;
        this.count = count;
        this.processingTime = processingTime;
        this.energyUsage = energyUsage;
    }

    @Override
    public boolean matches(Container container, Level level)
    {
        if (level.isClientSide())
            return false;
        return (input1.test(container.getItem(0)) && input1.test(container.getItem(1))) || (input1.test(container.getItem(1)) && input1.test(container.getItem(0)));
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
        return ModRecipes.ALLOY_SMELTER_RECIPE;
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

    public int getAmount(ItemStack itemStack)
    {
        return this.input1.test(itemStack) ? amount1 : amount2;
    }

    private int getAmount1()
    {
        return amount1;
    }

    private int getAmount2()
    {
        return amount2;
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
        return new ItemStack(ModBlocks.ALLOY_SMELTER.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.ALLOY_SMELTER_RECIPE_SERIALIZER.get();
    }

    public static class Type implements RecipeType<AlloySmelterRecipe>
    {
        @Override
        public String toString()
        {
            return AlloySmelterRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer implements RecipeSerializer<AlloySmelterRecipe>
    {
        private final Codec<AlloySmelterRecipe> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("input1").forGetter(recipe -> recipe.getIngredients().get(0)),
                Ingredient.CODEC_NONEMPTY.fieldOf("input2").forGetter(recipe -> recipe.getIngredients().get(1)),
                Codec.INT.fieldOf("amount1").forGetter(AlloySmelterRecipe::getAmount1),
                Codec.INT.fieldOf("amount2").forGetter(AlloySmelterRecipe::getAmount2),
                BuiltInRegistries.ITEM.byNameCodec().xmap(ItemStack::new, ItemStack::getItem).fieldOf("output").forGetter(recipe -> recipe.getResultItem(null)),
                Codec.INT.fieldOf("count").forGetter(AlloySmelterRecipe::getCount),
                Codec.INT.fieldOf("processingTime").forGetter(AlloySmelterRecipe::getProcessingTime),
                Codec.INT.fieldOf("energyUsage").forGetter(AlloySmelterRecipe::getEnergyUsage)
        ).apply(builder, AlloySmelterRecipe::new));

        @Override
        public Codec<AlloySmelterRecipe> codec()
        {
            return CODEC;
        }

        @Nullable
        @Override
        public AlloySmelterRecipe fromNetwork(FriendlyByteBuf buffer)
        {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
            {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            int amount1 = buffer.readInt();
            int amount2 = buffer.readInt();
            ItemStack output = buffer.readItem();
            int count = buffer.readInt();
            int processingTime = buffer.readInt();
            int energyUsage = buffer.readInt();
            return new AlloySmelterRecipe(inputs.get(0), inputs.get(1), amount1, amount2, output, count, processingTime, energyUsage);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, AlloySmelterRecipe recipe)
        {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient input : recipe.getIngredients())
            {
                input.toNetwork(buffer);
            }
            buffer.writeInt(recipe.getAmount1());
            buffer.writeInt(recipe.getAmount2());
            buffer.writeItemStack(recipe.getResultItem(null), false);
            buffer.writeInt(recipe.getCount());
            buffer.writeInt(recipe.getProcessingTime());
            buffer.writeInt(recipe.getEnergyUsage());
        }
    }
}
