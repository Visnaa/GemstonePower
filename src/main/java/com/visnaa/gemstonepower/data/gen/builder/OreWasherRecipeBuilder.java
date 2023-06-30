package com.visnaa.gemstonepower.data.gen.builder;

import com.google.gson.JsonObject;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.registry.ModRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class OreWasherRecipeBuilder implements RecipeBuilder
{
    private final Ingredient input;
    private final NonNullList<Item> outputs;
    private final int[] counts;
    private final int processingTime;
    private final int energyUsage;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public OreWasherRecipeBuilder(Ingredient input, NonNullList<Item> outputs, int[] counts, int processingTime, int energyUsage)
    {
        this.input = input;
        this.outputs = outputs;
        this.counts = counts;
        this.processingTime = processingTime;
        this.energyUsage = energyUsage;
    }

    public static OreWasherRecipeBuilder create(Ingredient input, NonNullList<Item> outputs, int[] counts, int processingTime, int energyUsage)
    {
        return new OreWasherRecipeBuilder(input, outputs, counts, processingTime, energyUsage);
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
        return outputs.get(0);
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation recipeId)
    {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(RequirementsStrategy.OR);

        consumer.accept(new OreWasherRecipeBuilder.Result(recipeId, this.input, this.outputs, this.counts, this.processingTime, this.energyUsage, this.advancement, new ResourceLocation(recipeId.getNamespace(), "recipes/" + recipeId.getPath())));
    }

    public static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final Ingredient input;
        private final NonNullList<Item> outputs;
        private final int[] counts;
        private final int processingTime;
        private final int energyUsage;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, Ingredient input, NonNullList<Item> outputs, int[] counts, int processingTime, int energyUsage, Advancement.Builder advancement, ResourceLocation advancementId)
        {
            this.id = id;
            this.input = input;
            this.outputs = outputs;
            this.counts = counts;
            this.processingTime = processingTime;
            this.energyUsage = energyUsage;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            json.add("input", this.input.toJson());

            JsonObject outputs = new JsonObject();
            for (int i = 0; i < this.outputs.size(); i++)
            {
                JsonObject output = new JsonObject();
                output.addProperty("item", ForgeRegistries.ITEMS.getKey(this.outputs.get(i)).toString());
                output.addProperty("count", this.counts[i]);
                outputs.add("output" + i, output);
            }
            json.add("outputs", outputs);

            json.addProperty("processingTime", this.processingTime);
            json.addProperty("energyUsage", this.energyUsage);
        }

        @Override
        public ResourceLocation getId()
        {
            return new ResourceLocation(GemstonePower.MOD_ID, this.id.getPath() + "_using_ore_washer");
        }

        @Override
        public RecipeSerializer<?> getType()
        {
            return ModRecipes.ORE_WASHER_RECIPE_SERIALIZER.get();
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