package com.visnaa.gemstonepower.data.gen.builder;

import com.google.gson.JsonObject;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class GemstoneGeneratorRecipeBuilder implements RecipeBuilder
{
    private final Ingredient fuel;
    private final int burnTime;
    private final int energy;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public GemstoneGeneratorRecipeBuilder(Ingredient fuel, int burnTime, int energy)
    {
        this.fuel = fuel;
        this.burnTime = burnTime;
        this.energy = energy;
    }

    public static GemstoneGeneratorRecipeBuilder create(Ingredient fuel, int burnTime, int energy)
    {
        return new GemstoneGeneratorRecipeBuilder(fuel, burnTime, energy);
    }

    @Override
    public RecipeBuilder unlockedBy(String name, CriterionTriggerInstance trigger)
    {
        this.advancement.addCriterion(name, trigger);
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
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation recipeId)
    {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(RequirementsStrategy.OR);

        consumer.accept(new GemstoneGeneratorRecipeBuilder.Result(recipeId, this.fuel, this.burnTime, this.energy, this.advancement, new ResourceLocation(recipeId.getNamespace(), "recipes/" + recipeId.getPath())));
    }

    public static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final Ingredient fuel;
        private final int burnTime;
        private final int energy;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, Ingredient fuel, int burnTime, int energy, Advancement.Builder advancement, ResourceLocation advancementId)
        {
            this.id = id;
            this.fuel = fuel;
            this.burnTime = burnTime;
            this.energy = energy;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            json.add("fuel", this.fuel.toJson());
            json.addProperty("burnTime", this.burnTime);
            json.addProperty("energy", this.energy);
        }

        @Override
        public ResourceLocation getId()
        {
            return new ResourceLocation(GemstonePower.MOD_ID, this.id.getPath() + "_using_gemstone_generator");
        }

        @Override
        public RecipeSerializer<?> getType()
        {
            return ModRecipes.GEMSTONE_GENERATOR_RECIPE_SERIALIZER.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement()
        {
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId()
        {
            return this.advancementId;
        }
    }
}