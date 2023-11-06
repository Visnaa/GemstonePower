package com.visnaa.gemstonepower.data.gen.builder;

import com.google.gson.JsonObject;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
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
        Advancement.Builder builder = output.advancement()
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);
        criteria.forEach(builder::addCriterion);
        output.accept(new GemstoneGeneratorRecipeBuilder.Result(recipeId, this.fuel, this.processingTime, this.energy, builder.build(new ResourceLocation(recipeId.getNamespace(), "recipes/" + recipeId.getPath()))));
    }

    public static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final Ingredient fuel;
        private final int processingTime;
        private final int energy;
        private final AdvancementHolder advancement;

        public Result(ResourceLocation id, Ingredient fuel, int processingTime, int energy, AdvancementHolder advancement)
        {
            this.id = id;
            this.fuel = fuel;
            this.processingTime = processingTime;
            this.energy = energy;
            this.advancement = advancement;
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            json.add("fuel", this.fuel.toJson(false));
            json.addProperty("processingTime", this.processingTime);
            json.addProperty("energy", this.energy);
        }

        @Override
        public ResourceLocation id()
        {
            return new ResourceLocation(GemstonePower.MOD_ID, this.id.getPath() + "_using_gemstone_generator");
        }

        @Override
        public RecipeSerializer<?> type()
        {
            return ModRecipes.GEMSTONE_GENERATOR_RECIPE_SERIALIZER.get();
        }

        @Nullable
        @Override
        public AdvancementHolder advancement()
        {
            return this.advancement;
        }
    }
}