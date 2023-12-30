package com.visnaa.gemstonepower.data.gen.builder;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.data.recipe.GemstoneGeneratorRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class GemstoneGeneratorRecipeBuilder implements RecipeBuilder
{
    private final Ingredient fuel;
    private final int processingTime;
    private final int energy;
    private final HashMap<String, Criterion<?>> criteria = new HashMap<>();

    public GemstoneGeneratorRecipeBuilder(Ingredient fuel, int processingTime, int energy)
    {
        this.fuel = fuel;
        this.processingTime = processingTime;
        this.energy = energy;
    }

    public static GemstoneGeneratorRecipeBuilder create(Ingredient fuel, int burnTime, int energy)
    {
        return new GemstoneGeneratorRecipeBuilder(fuel, burnTime, energy);
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
        return Items.AIR;
    }

    @Override
    public void save(RecipeOutput output, ResourceLocation recipeId)
    {
        recipeId = new ResourceLocation(recipeId.getNamespace(), recipeId.getPath() + "_using_gemstone_generator");
        Advancement.Builder builder = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);
        criteria.forEach(builder::addCriterion);
        GemstoneGeneratorRecipe recipe = new GemstoneGeneratorRecipe(fuel, processingTime, energy);
        output.accept(recipeId, recipe, builder.build(GemstonePower.getId("recipes/" + recipeId.getPath())));
    }
}