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
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.RecipeMatcher;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AlloySmelterRecipe implements EnergyRecipe
{
    public static final ResourceLocation TYPE_ID = new ResourceLocation(GemstonePower.MOD_ID, "alloy_smelter");
    private final ResourceLocation id;
    private final NonNullList<Ingredient> inputs;
    private final int amount1;
    private final int amount2;
    private final ItemStack output;
    private final int count;
    private final int processingTime;
    private final int energyUsage;
    private final boolean isSimple;

    public AlloySmelterRecipe(ResourceLocation id, NonNullList<Ingredient> inputs, int amount1, int amount2, ItemStack output, int count, int processingTime, int energyUsage)
    {
        this.id = id;
        this.inputs = inputs;
        this.amount1 = amount1;
        this.amount2 = amount2;
        this.output = output;
        this.count = count;
        this.processingTime = processingTime;
        this.energyUsage = energyUsage;
        this.isSimple = inputs.stream().allMatch(Ingredient::isSimple);
    }

    @Override
    public boolean matches(Container container, Level level)
    {
        StackedContents stackedContents = new StackedContents();
        List<ItemStack> inputs = new ArrayList<>();
        int i = 0;

        for(int j = 0; j < container.getContainerSize() - 1; j++) // Must be lowered by 1 to skip the output slot
        {
            ItemStack itemStack = container.getItem(j);
            if (!itemStack.isEmpty())
            {
                i++;
                if (isSimple)
                    stackedContents.accountStack(itemStack, 1);
                else inputs.add(itemStack);
            }
        }

        return i == this.inputs.size() && (isSimple ? stackedContents.canCraft(this, null) : RecipeMatcher.findMatches(inputs,  this.inputs) != null);
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
        return this.inputs.get(0).test(itemStack) ? amount1 : amount2;
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
        @Override
        public AlloySmelterRecipe fromJson(ResourceLocation recipeID, JsonObject json)
        {
            NonNullList<Ingredient> input = itemsFromJson(GsonHelper.getAsJsonArray(json, "inputs"));
            int amount1 = GsonHelper.getAsInt(json, "amount1");
            int amount2 = GsonHelper.getAsInt(json, "amount2");
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            int count = GsonHelper.getAsInt(json, "count");
            int processingTime = GsonHelper.getAsInt(json, "processingTime");
            int energyUsage = GsonHelper.getAsInt(json, "energyUsage");

            return new AlloySmelterRecipe(recipeID, input, amount1, amount2, output, count, processingTime, energyUsage);
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray p_44276_) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for(int i = 0; i < p_44276_.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(p_44276_.get(i));
                if (true || !ingredient.isEmpty()) { // FORGE: Skip checking if an ingredient is empty during shapeless recipe deserialization to prevent complex ingredients from caching tags too early. Can not be done using a config value due to sync issues.
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        @Nullable
        @Override
        public AlloySmelterRecipe fromNetwork(ResourceLocation recipeID, FriendlyByteBuf buffer)
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
            return new AlloySmelterRecipe(recipeID, inputs, amount1, amount2, output, count, processingTime, energyUsage);
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
