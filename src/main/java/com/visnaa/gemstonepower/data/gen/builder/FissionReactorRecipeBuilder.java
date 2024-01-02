package com.visnaa.gemstonepower.data.gen.builder;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.data.recipe.FissionReactorRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class FissionReactorRecipeBuilder implements RecipeBuilder
{
    private final Ingredient input;
    private final Item output;
    private final int processingTime;
    private final int energyGeneration;
    private final int heatGeneration;
    private final HashMap<String, Criterion<?>> criteria = new HashMap<>();

    public FissionReactorRecipeBuilder(Ingredient input, Item output, int processingTime, int energyGeneration, int heatGeneration)
    {
        this.input = input;
        this.output = output;
        this.processingTime = processingTime;
        this.energyGeneration = energyGeneration;
        this.heatGeneration = heatGeneration;
    }

    public static FissionReactorRecipeBuilder create(Ingredient input, Item output, int processingTime, int energyGeneration, int heatGeneration)
    {
        return new FissionReactorRecipeBuilder(input, output, processingTime, energyGeneration, heatGeneration);
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
        return output;
    }

    @Override
    public void save(RecipeOutput output, ResourceLocation recipeId)
    {
        recipeId = new ResourceLocation(recipeId.getNamespace(), recipeId.getPath() + "_using_fission_reactor");
        Advancement.Builder builder = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);
        criteria.forEach(builder::addCriterion);
        FissionReactorRecipe recipe = new FissionReactorRecipe(input, new ItemStack(this.output), processingTime, energyGeneration, heatGeneration);
        output.accept(recipeId, recipe, builder.build(GemstonePower.getId("recipes/" + recipeId.getPath())));
    }
}