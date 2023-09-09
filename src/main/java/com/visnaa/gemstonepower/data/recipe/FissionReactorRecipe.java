package com.visnaa.gemstonepower.data.recipe;

import com.google.gson.JsonElement;
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

public class FissionReactorRecipe implements EnergyRecipe
{
    public static final ResourceLocation TYPE_ID = new ResourceLocation(GemstonePower.MOD_ID, "fission_reactor");
    private final ResourceLocation id;
    private final Ingredient input;
    private final ItemStack output;
    private final int processingTime;
    private final int energyGeneration;
    private final int heatGeneration;

    public FissionReactorRecipe(ResourceLocation id, Ingredient input, ItemStack output, int processingTime, int energyGeneration, int heatGeneration)
    {
        this.id = id;
        this.input = input;
        this.output = output;
        this.processingTime = processingTime;
        this.energyGeneration = energyGeneration;
        this.heatGeneration = heatGeneration;
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
        return new ItemStack(output.getItem(), 1);
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
        return ModRecipes.FISSION_REACTOR_RECIPE;
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

    public int getProcessingTime()
    {
        return processingTime;
    }

    @Override
    public int getEnergyUsage()
    {
        return -energyGeneration;
    }

    public int getEnergyGeneration()
    {
        return energyGeneration;
    }

    public int getHeatGeneration()
    {
        return heatGeneration;
    }

    public ItemStack getIcon()
    {
        return new ItemStack(ModBlocks.FISSION_REACTOR.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.FISSION_REACTOR_RECIPE_SERIALIZER.get();
    }

    public static class Type implements RecipeType<FissionReactorRecipe>
    {
        @Override
        public String toString()
        {
            return FissionReactorRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer implements RecipeSerializer<FissionReactorRecipe>
    {
        @Override
        public FissionReactorRecipe fromJson(ResourceLocation recipeID, JsonObject json)
        {
            JsonElement seedElement = GsonHelper.getAsJsonObject(json, "input");
            Ingredient input = Ingredient.fromJson(seedElement);
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            int processingTime = GsonHelper.getAsInt(json, "processingTime");
            int energyGeneration = GsonHelper.getAsInt(json, "energyGeneration");
            int heatGeneration = GsonHelper.getAsInt(json, "heatGeneration");

            return new FissionReactorRecipe(recipeID, input, output, processingTime, energyGeneration, heatGeneration);
        }

        @Nullable
        @Override
        public FissionReactorRecipe fromNetwork(ResourceLocation recipeID, FriendlyByteBuf buffer)
        {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
            {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            int processingTime = buffer.readInt();
            int energyGeneration = buffer.readInt();
            int heatGeneration = buffer.readInt();
            return new FissionReactorRecipe(recipeID, inputs.get(0), output, processingTime, energyGeneration, heatGeneration);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FissionReactorRecipe recipe)
        {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient seed : recipe.getIngredients())
            {
                seed.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(null), false);
            buffer.writeInt(recipe.getProcessingTime());
            buffer.writeInt(recipe.getEnergyGeneration());
            buffer.writeInt(recipe.getHeatGeneration());
        }
    }
}
