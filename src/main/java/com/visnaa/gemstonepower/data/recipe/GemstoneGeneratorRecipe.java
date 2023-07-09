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
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class GemstoneGeneratorRecipe implements EnergyRecipe
{
    public static final ResourceLocation TYPE_ID = new ResourceLocation(GemstonePower.MOD_ID, "gemstone_generator");
    private final ResourceLocation id;
    private final Ingredient fuel;
    private final int burnTime;
    private final int energy;

    public GemstoneGeneratorRecipe(ResourceLocation id, Ingredient fuel, int burnTime, int energy)
    {
        this.id = id;
        this.fuel = fuel;
        this.burnTime = burnTime;
        this.energy = energy;
    }

    @Override
    public boolean matches(Container container, Level level)
    {
        if (level.isClientSide) return false;
        if (fuel.test(container.getItem(0))) return true;
        return false;
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        NonNullList<Ingredient> ingredients = NonNullList.withSize(1, fuel);
        return ingredients;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess access)
    {
        return null;
    }

    @Override
    public ItemStack getResultItem(@Nullable RegistryAccess access)
    {
        return null;
    }

    @Override
    public ResourceLocation getId()
    {
        return id;
    }

    @Override
    public RecipeType<?> getType()
    {
        return ModRecipes.GEMSTONE_GENERATOR_RECIPE;
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

    @Override
    public int getProcessingTime()
    {
        return this.burnTime;
    }

    @Override
    public int getEnergyUsage()
    {
        return 0;
    }

    public int getEnergy()
    {
        return energy;
    }

    public ItemStack getIcon()
    {
        return new ItemStack(ModBlocks.GEMSTONE_GENERATOR.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.GEMSTONE_GENERATOR_RECIPE_SERIALIZER.get();
    }

    public static class Type implements RecipeType<GemstoneGeneratorRecipe>
    {
        @Override
        public String toString()
        {
            return GemstoneGeneratorRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer implements RecipeSerializer<GemstoneGeneratorRecipe>
    {

        @Override
        public GemstoneGeneratorRecipe fromJson(ResourceLocation recipeID, JsonObject json)
        {
            JsonElement fuelElement = GsonHelper.getAsJsonObject(json, "fuel");
            Ingredient fuel = Ingredient.fromJson(fuelElement);
            int burnTime = GsonHelper.getAsInt(json, "burnTime");
            int energy = GsonHelper.getAsInt(json, "energy");

            return new GemstoneGeneratorRecipe(recipeID, fuel, burnTime, energy);
        }

        @Nullable
        @Override
        public GemstoneGeneratorRecipe fromNetwork(ResourceLocation recipeID, FriendlyByteBuf buffer)
        {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
            {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            int burnTime = buffer.readInt();
            int energy = buffer.readInt();
            return new GemstoneGeneratorRecipe(recipeID, inputs.get(0), burnTime, energy);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, GemstoneGeneratorRecipe recipe)
        {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients())
            {
                ing.toNetwork(buffer);
            }
            buffer.writeInt(recipe.getProcessingTime());
            buffer.writeInt(recipe.getEnergy());
        }
    }
}
