package com.visnaa.gemstonepower.data.gen.builder;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.data.recipe.OreWasherRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OreWasherRecipeBuilder implements RecipeBuilder
{
    private final Ingredient input;
    private final FluidStack fluid;
    private final NonNullList<Item> outputs;
    private final int[] counts;
    private final int processingTime;
    private final int energyUsage;
    private final HashMap<String, Criterion<?>> criteria = new HashMap<>();

    public OreWasherRecipeBuilder(Ingredient input, FluidStack fluid, NonNullList<Item> outputs, int[] counts, int processingTime, int energyUsage)
    {
        this.input = input;
        this.fluid = fluid;
        this.outputs = outputs;
        this.counts = counts;
        this.processingTime = processingTime;
        this.energyUsage = energyUsage;
    }

    public static OreWasherRecipeBuilder create(Ingredient input, FluidStack fluid, NonNullList<Item> outputs, int[] counts, int processingTime, int energyUsage)
    {
        return new OreWasherRecipeBuilder(input, fluid, outputs, counts, processingTime, energyUsage);
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion criterion)
    {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String name)
    {
        return this;
    }

    @Override
    public Item getResult()
    {
        return outputs.get(0);
    }

    @Override
    public void save(RecipeOutput output, ResourceLocation recipeId)
    {
        recipeId = new ResourceLocation(recipeId.getNamespace(), recipeId.getPath() + "_using_ore_washer");
        Advancement.Builder builder = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);
        criteria.forEach(builder::addCriterion);
        List<ItemStack> outputs = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < Math.min(this.outputs.size(), this.counts.length); i++)
        {
            outputs.add(new ItemStack(this.outputs.get(i), this.counts[i]));
            counts.add(this.counts[i]);
        }
        OreWasherRecipe recipe = new OreWasherRecipe(input, fluid, outputs, counts, processingTime, energyUsage);
        output.accept(recipeId, recipe, builder.build(GemstonePower.getId("recipes/" + recipeId.getPath())));
    }
}