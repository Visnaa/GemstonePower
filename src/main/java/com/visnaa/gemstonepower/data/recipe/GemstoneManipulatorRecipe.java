package com.visnaa.gemstonepower.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModBlocks;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class GemstoneManipulatorRecipe implements EnergyRecipe
{
    public static final ResourceLocation TYPE_ID = new ResourceLocation(GemstonePower.MOD_ID, "alloy_smelter");
    private final ResourceLocation id;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;
    private final int count;
    private final int processingTime;
    private final int energyUsage;

    public GemstoneManipulatorRecipe(ResourceLocation id, NonNullList<Ingredient> inputs, ItemStack output, int count, int processingTime, int energyUsage)
    {
        this.id = id;
        this.inputs = inputs;
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
        return (inputs.get(0).test(container.getItem(0)) && inputs.get(1).test(container.getItem(1))) ||
                (inputs.get(0).test(container.getItem(1)) && inputs.get(1).test(container.getItem(0)));
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return inputs;
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
    public ResourceLocation getId()
    {
        return id;
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
        @Override
        public GemstoneManipulatorRecipe fromJson(ResourceLocation recipeID, JsonObject json)
        {
            NonNullList<Ingredient> input = itemsFromJson(GsonHelper.getAsJsonArray(json, "inputs"));
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            int count = GsonHelper.getAsInt(json, "count");
            int processingTime = GsonHelper.getAsInt(json, "processingTime");
            int energyUsage = GsonHelper.getAsInt(json, "energyUsage");

            return new GemstoneManipulatorRecipe(recipeID, input, output, count, processingTime, energyUsage);
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray array) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for(int i = 0; i < array.size(); ++i)
                nonnulllist.add(Ingredient.fromJson(array.get(i)));

            return nonnulllist;
        }

        @Nullable
        @Override
        public GemstoneManipulatorRecipe fromNetwork(ResourceLocation recipeID, FriendlyByteBuf buffer)
        {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
                inputs.set(i, Ingredient.fromNetwork(buffer));

            ItemStack output = buffer.readItem();
            int count = buffer.readInt();
            int processingTime = buffer.readInt();
            int energyUsage = buffer.readInt();
            return new GemstoneManipulatorRecipe(recipeID, inputs, output, count, processingTime, energyUsage);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, GemstoneManipulatorRecipe recipe)
        {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient input : recipe.getIngredients())
                input.toNetwork(buffer);
            buffer.writeItemStack(recipe.getResultItem(null), false);
            buffer.writeInt(recipe.getCount());
            buffer.writeInt(recipe.getProcessingTime());
            buffer.writeInt(recipe.getEnergyUsage());
        }
    }
}
