package com.visnaa.gemstonepower.data.gen.builder;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
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
        Advancement.Builder builder = output.advancement()
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);
        criteria.forEach(builder::addCriterion);
        output.accept(new FissionReactorRecipeBuilder.Result(recipeId, this.input, this.output, this.processingTime, this.energyGeneration, this.heatGeneration, builder.build(new ResourceLocation(recipeId.getNamespace(), "recipes/" + recipeId.getPath()))));
    }

    public static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final Ingredient input;
        private final Item output;
        private final int processingTime;
        private final int energyGeneration;
        private final int heatGeneration;
        private final AdvancementHolder advancement;

        public Result(ResourceLocation id, Ingredient input, Item output, int processingTime, int energyGeneration, int heatGeneration, AdvancementHolder advancement)
        {
            this.id = id;
            this.input = input;
            this.output = output;
            this.processingTime = processingTime;
            this.energyGeneration = energyGeneration;
            this.heatGeneration = heatGeneration;
            this.advancement = advancement;
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            json.add("input", this.input.toJson(false));

            json.add("output", new JsonPrimitive(ForgeRegistries.ITEMS.getKey(output).toString()));

            json.addProperty("processingTime", this.processingTime);
            json.addProperty("energyGeneration", this.energyGeneration);
            json.addProperty("heatGeneration", this.heatGeneration);
        }

        @Override
        public ResourceLocation id()
        {
            return new ResourceLocation(GemstonePower.MOD_ID, this.id.getPath() + "_using_fission_reactor");
        }

        @Override
        public RecipeSerializer<?> type()
        {
            return ModRecipes.FISSION_REACTOR_RECIPE_SERIALIZER.get();
        }

        @Nullable
        @Override
        public AdvancementHolder advancement()
        {
            return this.advancement;
        }
    }
}