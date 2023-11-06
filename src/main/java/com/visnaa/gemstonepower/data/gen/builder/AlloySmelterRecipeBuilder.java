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
        Advancement.Builder builder = output.advancement()
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);
        criteria.forEach(builder::addCriterion);
        output.accept(new AlloySmelterRecipeBuilder.Result(recipeId, this.inputs, this.amount1, this.amount2, this.output, this.count, this.processingTime, this.energyUsage, builder.build(new ResourceLocation(recipeId.getNamespace(), "recipes/" + recipeId.getPath()))));
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
        private final AdvancementHolder advancement;

        public Result(ResourceLocation id, List<Ingredient> inputs, int amount1, int amount2, Item output, int count, int processingTime, int energyUsage, AdvancementHolder advancement)
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
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            json.add("input1", inputs.get(0).toJson(false));
            json.add("input2", inputs.get(1).toJson(false));

            json.addProperty("amount1", this.amount1);
            json.addProperty("amount2", this.amount2);

            json.add("output", new JsonPrimitive(ForgeRegistries.ITEMS.getKey(output).toString()));

            json.addProperty("count", this.count);
            json.addProperty("processingTime", this.processingTime);
            json.addProperty("energyUsage", this.energyUsage);
        }

        @Override
        public ResourceLocation id()
        {
            return new ResourceLocation(GemstonePower.MOD_ID, this.id.getPath() + "_using_alloy_smelter");
        }

        @Override
        public RecipeSerializer<?> type()
        {
            return ModRecipes.ALLOY_SMELTER_RECIPE_SERIALIZER.get();
        }

        @Nullable
        @Override
        public AdvancementHolder advancement()
        {
            return advancement;
        }
    }
}