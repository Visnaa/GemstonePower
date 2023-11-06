package com.visnaa.gemstonepower.data.gen.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.data.recipe.FluidRecipe;
import com.visnaa.gemstonepower.init.ModRecipes;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;

public class OreWasherRecipeBuilder implements RecipeBuilder
{
    private final Ingredient input;
    private final FluidStack fluid;
    private final NonNullList<Item> outputs;
    private final int[] counts;
    private final int processingTime;
    private final int energyUsage;
    private final HashMap<String, Criterion<?>> criteria = new HashMap<>();

    public OreWasherRecipeBuilder(Ingredient input, FluidStack fluid, NonNullList<Item> outputs, int[] counts, int processingTime, int energyUsage)
    {
        this.input = input;
        this.fluid = fluid;
        this.outputs = outputs;
        this.counts = counts;
        this.processingTime = processingTime;
        this.energyUsage = energyUsage;
    }

    public static OreWasherRecipeBuilder create(Ingredient input, FluidStack fluid, NonNullList<Item> outputs, int[] counts, int processingTime, int energyUsage)
    {
        return new OreWasherRecipeBuilder(input, fluid, outputs, counts, processingTime, energyUsage);
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
        return outputs.get(0);
    }

    @Override
    public void save(RecipeOutput output, ResourceLocation recipeId)
    {
        Advancement.Builder builder = output.advancement()
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);
        output.accept(new OreWasherRecipeBuilder.Result(recipeId, this.input, this.fluid, this.outputs, this.counts, this.processingTime, this.energyUsage, builder.build(new ResourceLocation(recipeId.getNamespace(), "recipes/" + recipeId.getPath()))));
    }

    public static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final Ingredient input;
        private final FluidStack fluid;
        private final NonNullList<Item> outputs;
        private final int[] counts;
        private final int processingTime;
        private final int energyUsage;
        private final AdvancementHolder advancement;

        public Result(ResourceLocation id, Ingredient input, FluidStack fluid, NonNullList<Item> outputs, int[] counts, int processingTime, int energyUsage, AdvancementHolder advancement)
        {
            this.id = id;
            this.input = input;
            this.fluid = fluid;
            this.outputs = outputs;
            this.counts = counts;
            this.processingTime = processingTime;
            this.energyUsage = energyUsage;
            this.advancement = advancement;
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            json.add("input", this.input.toJson(false));
            json.add("fluid", FluidRecipe.toJson(this.fluid));

            JsonArray outputs = new JsonArray();
            this.outputs.forEach(item -> outputs.add(new JsonPrimitive(ForgeRegistries.ITEMS.getKey(item).toString())));
            json.add("outputs", outputs);

            JsonArray counts = new JsonArray();
            Arrays.stream(this.counts).forEach(count -> counts.add(new JsonPrimitive(count)));
            json.add("counts", counts);

            json.addProperty("processingTime", this.processingTime);
            json.addProperty("energyUsage", this.energyUsage);
        }

        @Override
        public ResourceLocation id()
        {
            return new ResourceLocation(GemstonePower.MOD_ID, this.id.getPath() + "_using_ore_washer");
        }

        @Override
        public RecipeSerializer<?> type()
        {
            return ModRecipes.ORE_WASHER_RECIPE_SERIALIZER.get();
        }

        @Nullable
        @Override
        public AdvancementHolder advancement()
        {
            return this.advancement;
        }
    }
}