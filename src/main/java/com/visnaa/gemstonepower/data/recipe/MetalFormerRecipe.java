package com.visnaa.gemstonepower.data.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModBlocks;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.util.MachinePresets;
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

public class MetalFormerRecipe implements EnergyRecipe
{
    public static final ResourceLocation TYPE_ID = new ResourceLocation(GemstonePower.MOD_ID, "metal_former");
    private final ResourceLocation id;
    private final Ingredient input;
    private final ItemStack output;
    private final int count;
    private final String preset;
    private final int processingTime;
    private final int energyUsage;

    public MetalFormerRecipe(ResourceLocation id, Ingredient input, ItemStack output, int count, String preset, int processingTime, int energyUsage)
    {
        this.id = id;
        this.input = input;
        this.output = output;
        this.count = count;
        this.preset = preset;
        this.processingTime = processingTime;
        this.energyUsage = energyUsage;
    }

    @Override
    public boolean matches(Container container, Level level)
    {
        if (level.isClientSide) return false;
        if (input.test(container.getItem(0)) && MachinePresets.compare(container.getItem(1), preset))
            return true;
        return false;
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
    public ResourceLocation getId()
    {
        return id;
    }

    @Override
    public RecipeType<?> getType()
    {
        return ModRecipes.METAL_FORMER_RECIPE;
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
    public String getPreset()
    {
        return preset;
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
        return new ItemStack(ModBlocks.METAL_FORMER.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.METAL_FORMER_RECIPE_SERIALIZER.get();
    }

    public static class Type implements RecipeType<MetalFormerRecipe>
    {
        @Override
        public String toString()
        {
            return MetalFormerRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer implements RecipeSerializer<MetalFormerRecipe>
    {
        @Override
        public MetalFormerRecipe fromJson(ResourceLocation recipeID, JsonObject json)
        {
            JsonElement seedElement = GsonHelper.getAsJsonObject(json, "input");
            Ingredient input = Ingredient.fromJson(seedElement);
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            int count = GsonHelper.getAsInt(json, "count");
            String preset = GsonHelper.getAsString(json, "preset");
            int processingTime = GsonHelper.getAsInt(json, "processingTime");
            int energyUsage = GsonHelper.getAsInt(json, "energyUsage");

            return new MetalFormerRecipe(recipeID, input, output, count, preset, processingTime, energyUsage);
        }

        @Nullable
        @Override
        public MetalFormerRecipe fromNetwork(ResourceLocation recipeID, FriendlyByteBuf buffer)
        {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
            {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            int count = buffer.readInt();
            String preset = buffer.readUtf();
            int processingTime = buffer.readInt();
            int energyUsage = buffer.readInt();
            return new MetalFormerRecipe(recipeID, inputs.get(0), output, count, preset, processingTime, energyUsage);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MetalFormerRecipe recipe)
        {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient seed : recipe.getIngredients())
            {
                seed.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(null), false);
            buffer.writeInt(recipe.getCount());
            buffer.writeUtf(recipe.getPreset());
            buffer.writeInt(recipe.getProcessingTime());
            buffer.writeInt(recipe.getEnergyUsage());
        }
    }
}
