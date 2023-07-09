package com.visnaa.gemstonepower.data.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.registry.ModBlocks;
import com.visnaa.gemstonepower.registry.ModRecipes;
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

public class OreWasherRecipe implements EnergyRecipe
{
    public static final ResourceLocation TYPE_ID = new ResourceLocation(GemstonePower.MOD_ID, "ore_washer");
    private final ResourceLocation id;
    private final Ingredient input;
    private final NonNullList<ItemStack> outputs;
    private final int[] counts;
    private final int processingTime;
    private final int energyUsage;

    public OreWasherRecipe(ResourceLocation id, Ingredient input, NonNullList<ItemStack> outputs, int[] counts, int processingTime, int energyUsage)
    {
        this.id = id;
        this.input = input;
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
        return new ItemStack(outputs.get(0).getItem(), getCounts()[0]);
    }

    @Override
    public ItemStack getResultItem(@Nullable RegistryAccess access)
    {
        return outputs.get(0).copy();
    }

    public NonNullList<ItemStack> getResultItems()
    {
        return outputs;
    }

    @Override
    public ResourceLocation getId()
    {
        return id;
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

    public int[] getCounts()
    {
        return counts;
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
        @Override
        public OreWasherRecipe fromJson(ResourceLocation recipeID, JsonObject json)
        {
            JsonElement seedElement = GsonHelper.getAsJsonObject(json, "input");
            Ingredient input = Ingredient.fromJson(seedElement);

            JsonObject outputsJson = GsonHelper.getAsJsonObject(json, "outputs");
            NonNullList<ItemStack> outputs = NonNullList.withSize(outputsJson.size(), ItemStack.EMPTY);
            int[] counts = new int[4];
            for (int i = 0; i < outputsJson.size(); i++)
            {
                JsonObject output = GsonHelper.getAsJsonObject(outputsJson, "output" + i);
                outputs.set(i, ShapedRecipe.itemStackFromJson(output));
                counts[i] = GsonHelper.getAsInt(output, "count");
            }

            int processingTime = GsonHelper.getAsInt(json, "processingTime");
            int energyUsage = GsonHelper.getAsInt(json, "energyUsage");

            return new OreWasherRecipe(recipeID, input, outputs, counts, processingTime, energyUsage);
        }

        @Nullable
        @Override
        public OreWasherRecipe fromNetwork(ResourceLocation recipeID, FriendlyByteBuf buffer)
        {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++)
            {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            NonNullList<ItemStack> outputs = NonNullList.withSize(buffer.readInt(), ItemStack.EMPTY);
            int[] counts = new int[4];
            for (int i = 0; i < outputs.size(); i++)
            {
                outputs.set(i, buffer.readItem());
                counts[i] = buffer.readInt();
            }

            int processingTime = buffer.readInt();
            int energyUsage = buffer.readInt();
            return new OreWasherRecipe(recipeID, inputs.get(0), outputs, counts, processingTime, energyUsage);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, OreWasherRecipe recipe)
        {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient seed : recipe.getIngredients())
            {
                seed.toNetwork(buffer);
            }

            buffer.writeInt(recipe.getResultItems().size());
            for (int i = 0; i < recipe.getResultItems().size(); i++)
            {
                buffer.writeItemStack(recipe.getResultItems().get(i), false);
                buffer.writeInt(recipe.getCounts()[i]);
            }
            buffer.writeInt(recipe.getProcessingTime());
            buffer.writeInt(recipe.getEnergyUsage());
        }
    }
}
