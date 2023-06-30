package com.visnaa.gemstonepower.data.gen.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.registry.ModRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class AlloySmelterRecipeBuilder implements RecipeBuilder
{
    private final List<Ingredient> inputs;
    private final int amount1;
    private final int amount2;
    private final Item output;
    private final int count;
    private final int processingTime;
    private final int energyUsage;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

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
    public AlloySmelterRecipeBuilder unlockedBy(String name, CriterionTriggerInstance trigger)
    {
        this.advancement.addCriterion(name, trigger);
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
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation recipeId)
    {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(RequirementsStrategy.OR);

        consumer.accept(new AlloySmelterRecipeBuilder.Result(recipeId, this.inputs, this.amount1, this.amount2, this.output, this.count, this.processingTime, this.energyUsage, this.advancement, new ResourceLocation(recipeId.getNamespace(), "recipes/" + recipeId.getPath())));
    }

    public static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final List<Ingredient> inputs;
        private final int amount1;
        private final int amount2;
        private final Item output;
        private final int count;
        private final int processingTime;
        private final int energyUsage;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, List<Ingredient> inputs, int amount1, int amount2, Item output, int count, int processingTime, int energyUsage, Advancement.Builder advancement, ResourceLocation advancementId)
        {
            this.id = id;
            this.inputs = inputs;
            this.amount1 = amount1;
            this.amount2 = amount2;
            this.output = output;
            this.count = count;
            this.processingTime = processingTime;
            this.energyUsage = energyUsage;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {

            JsonArray jsonArray = new JsonArray();
            for(Ingredient input : this.inputs)
                jsonArray.add(input.toJson());
            json.add("inputs", jsonArray);

            json.addProperty("amount1", this.amount1);
            json.addProperty("amount2", this.amount2);

            JsonObject output = new JsonObject();
            output.addProperty("item", ForgeRegistries.ITEMS.getKey(this.output).toString());
            json.add("output", output);

            json.addProperty("count", this.count);
            json.addProperty("processingTime", this.processingTime);
            json.addProperty("energyUsage", this.energyUsage);
        }

        @Override
        public ResourceLocation getId()
        {
            return new ResourceLocation(GemstonePower.MOD_ID, this.id.getPath() + "_using_alloy_smelter");
        }

        @Override
        public RecipeSerializer<?> getType()
        {
            return ModRecipes.ALLOY_SMELTER_RECIPE_SERIALIZER.get();
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