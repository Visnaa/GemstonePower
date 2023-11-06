package com.visnaa.gemstonepower.data.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModBlocks;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OreWasherRecipe implements EnergyRecipe, FluidRecipe
{
    public static final ResourceLocation TYPE_ID = new ResourceLocation(GemstonePower.MOD_ID, "ore_washer");
    private final Ingredient input;
    private final FluidStack fluid;
    private final List<ItemStack> outputs;
    private final List<Integer> counts;
    private final int processingTime;
    private final int energyUsage;

    public OreWasherRecipe(Ingredient input, FluidStack fluid, List<ItemStack> outputs, List<Integer> counts, int processingTime, int energyUsage)
    {
        this.input = input;
        this.fluid = fluid;
        this.outputs = outputs;
        this.counts = counts;
        this.processingTime = processingTime;
        this.energyUsage = energyUsage;
    }

    @Override
    public boolean matches(Container container, Level level)
    {
        if (level.isClientSide) return false;
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
        return new ItemStack(outputs.get(0).getItem(), getCounts().get(0));
    }

    @Override
    public ItemStack getResultItem(@Nullable RegistryAccess access)
    {
        return outputs.get(0).copy();
    }

    public NonNullList<ItemStack> getResultItems()
    {
        NonNullList<ItemStack> list = NonNullList.createWithCapacity(outputs.size());
        for (int i = 0; i < list.size(); i++)
            list.set(i, outputs.get(i));
        return list;
    }

    @Override
    public RecipeType<?> getType()
    {
        return ModRecipes.ORE_WASHER_RECIPE;
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

    public List<Integer> getCounts()
    {
        return counts;
    }

    @Override
    public int getCount()
    {
        return 0;
    }

    public int getProcessingTime()
    {
        return processingTime;
    }

    public int getEnergyUsage()
    {
        return energyUsage;
    }

    @Override
    public FluidStack getFluid()
    {
        return fluid;
    }

    public ItemStack getIcon()
    {
        return new ItemStack(ModBlocks.ORE_WASHER.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.ORE_WASHER_RECIPE_SERIALIZER.get();
    }

    public static class Type implements RecipeType<OreWasherRecipe>
    {
        @Override
        public String toString()
        {
            return OreWasherRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer implements RecipeSerializer<OreWasherRecipe>
    {
        private static final Codec<OreWasherRecipe> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("input").forGetter(recipe -> recipe.getIngredients().get(0)),
                FluidRecipe.FLUID_CODEC.fieldOf("fluid").forGetter(OreWasherRecipe::getFluid),
                Codec.list(ForgeRegistries.ITEMS.getCodec().xmap(ItemStack::new, ItemStack::getItem)).fieldOf("outputs").forGetter(OreWasherRecipe::getResultItems),
                Codec.list(Codec.INT).fieldOf("counts").forGetter(OreWasherRecipe::getCounts),
                Codec.INT.fieldOf("processingTime").forGetter(OreWasherRecipe::getProcessingTime),
                Codec.INT.fieldOf("energyUsage").forGetter(OreWasherRecipe::getEnergyUsage)
        ).apply(builder, OreWasherRecipe::new));

        @Override
        public Codec<OreWasherRecipe> codec()
        {
            return CODEC;
        }

        @Nullable
        @Override
        public OreWasherRecipe fromNetwork(FriendlyByteBuf buffer)
        {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++)
            {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            FluidStack fluid = buffer.readFluidStack();

            NonNullList<ItemStack> outputs = NonNullList.withSize(buffer.readInt(), ItemStack.EMPTY);
            List<Integer> counts = new ArrayList<>();
            for (int i = 0; i < outputs.size(); i++)
            {
                outputs.set(i, buffer.readItem());
                counts.set(i, buffer.readInt());
            }

            int processingTime = buffer.readInt();
            int energyUsage = buffer.readInt();
            return new OreWasherRecipe(inputs.get(0), fluid, outputs, counts, processingTime, energyUsage);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, OreWasherRecipe recipe)
        {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient input : recipe.getIngredients())
            {
                input.toNetwork(buffer);
            }

            buffer.writeFluidStack(recipe.getFluid());

            buffer.writeInt(recipe.getResultItems().size());
            for (int i = 0; i < recipe.getResultItems().size(); i++)
            {
                buffer.writeItemStack(recipe.getResultItems().get(i), false);
                buffer.writeInt(recipe.getCounts().get(i));
            }
            buffer.writeInt(recipe.getProcessingTime());
            buffer.writeInt(recipe.getEnergyUsage());
        }
    }
}
