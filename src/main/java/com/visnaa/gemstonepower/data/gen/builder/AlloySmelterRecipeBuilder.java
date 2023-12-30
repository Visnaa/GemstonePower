package com.visnaa.gemstonepower.data.gen.builder;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.data.recipe.AlloySmelterRecipe;
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
import java.util.List;

public class AlloySmelterRecipeBuilder implements RecipeBuilder
{
    private final List<Ingredient> inputs;
    private final int amount1;
    private final int amount2;
    private final Item output;
    private final int count;
    private final int processingTime;
    private final int energyUsage;
    private final HashMap<String, Criterion<?>> criteria = new HashMap<>();

    public AlloySmelterRecipeBuilder(List<Ingredient> inputs, int amount1, int amount2, Item output, int count, int processingTime, int energyUsage)
    {
        this.inputs = inputs;
        this.amount1 = amount1;
        this.amount2 = amount2;
        this.output = output;
        this.count = count;
        this.processingTime = processingTime;
        this.energyUsage = energyUsage;
    }

    public static AlloySmelterRecipeBuilder create(List<Ingredient> inputs, int amount1, int amount2, Item output, int count, int processingTime, int energyUsage)
    {
        return new AlloySmelterRecipeBuilder(inputs, amount1, amount2, output, count, processingTime, energyUsage);
    }

    @Override
    public AlloySmelterRecipeBuilder unlockedBy(String name, Criterion critertion)
    {
        criteria.put(name, critertion);
        return this;
    }

    @Override
    public AlloySmelterRecipeBuilder group(@Nullable String name)
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
        recipeId = new ResourceLocation(recipeId.getNamespace(), recipeId.getPath() + "_using_alloy_smelter");
        Advancement.Builder builder = output.advancement()
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);
        criteria.forEach(builder::addCriterion);
        AlloySmelterRecipe recipe = new AlloySmelterRecipe(inputs.get(0), inputs.get(1), amount1, amount2, new ItemStack(this.output, count), count, processingTime, energyUsage);
        output.accept(recipeId, recipe, builder.build(GemstonePower.getId("recipes/" + recipeId.getPath())));
    }
}