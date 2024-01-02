package com.visnaa.gemstonepower.data.gen.builder;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.data.recipe.CrystalChargerRecipe;
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

public class CrystalChargerRecipeBuilder implements RecipeBuilder
{
    private final Ingredient input;
    private final Item output;
    private final int count;
    private final int processingTime;
    private final int energyUsage;
    private final HashMap<String, Criterion<?>> criteria = new HashMap<>();

    public CrystalChargerRecipeBuilder(Ingredient input, Item output, int count, int processingTime, int energyUsage)
    {
        this.input = input;
        this.output = output;
        this.count = count;
        this.processingTime = processingTime;
        this.energyUsage = energyUsage;
    }

    public static CrystalChargerRecipeBuilder create(Ingredient input, Item output, int count, int processingTime, int energyUsage)
    {
        return new CrystalChargerRecipeBuilder(input, output, count, processingTime, energyUsage);
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion criterion)
    {
        criteria.put(name, criterion);
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
        recipeId = new ResourceLocation(recipeId.getNamespace(), recipeId.getPath() + "_using_crystal_charger");
        Advancement.Builder builder = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);
        criteria.forEach(builder::addCriterion);
        CrystalChargerRecipe recipe = new CrystalChargerRecipe(input, new ItemStack(this.output, count), count, processingTime, energyUsage);
        output.accept(recipeId, recipe, builder.build(GemstonePower.getId("recipes/" + recipeId.getPath())));
    }
}