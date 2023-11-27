package com.visnaa.gemstonepower.data.gen.builder;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.util.MachineUtil.MachineModes;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class MetalFormerRecipeBuilder implements RecipeBuilder
{
    private final Ingredient input;
    private final Item output;
    private final int count;
    private final MachineModes mode;
    private final int processingTime;
    private final int energyUsage;
    private final HashMap<String, Criterion<?>> criteria = new HashMap<>();

    public MetalFormerRecipeBuilder(Ingredient input, Item output, int count, MachineModes mode, int processingTime, int energyUsage)
    {
        this.input = input;
        this.output = output;
        this.count = count;
        this.mode = mode;
        this.processingTime = processingTime;
        this.energyUsage = energyUsage;
    }

    public static MetalFormerRecipeBuilder create(Ingredient input, Item output, int count, MachineModes mode, int processingTime, int energyUsage)
    {
        return new MetalFormerRecipeBuilder(input, output, count, mode, processingTime, energyUsage);
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
        output.accept(new MetalFormerRecipeBuilder.Result(recipeId, this.input, this.output, this.count, this.mode, this.processingTime, this.energyUsage, builder.build(new ResourceLocation(recipeId.getNamespace(), "recipes/" + recipeId.getPath()))));
    }

    public static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final Ingredient input;
        private final Item output;
        private final int count;
        private final MachineModes mode;
        private final int processingTime;
        private final int energyUsage;
        private final AdvancementHolder advancement;

        public Result(ResourceLocation id, Ingredient input, Item output, int count, MachineModes mode, int processingTime, int energyUsage, AdvancementHolder advancement)
        {
            this.id = id;
            this.input = input;
            this.output = output;
            this.count = count;
            this.mode = mode;
            this.processingTime = processingTime;
            this.energyUsage = energyUsage;
            this.advancement = advancement;
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            json.add("input", this.input.toJson(false));

            json.add("output", new JsonPrimitive(BuiltInRegistries.ITEM.getKey(output).toString()));

            json.addProperty("count", this.count);
            json.addProperty("mode", this.mode.getName());
            json.addProperty("processingTime", this.processingTime);
            json.addProperty("energyUsage", this.energyUsage);
        }

        @Override
        public ResourceLocation id()
        {
            return new ResourceLocation(GemstonePower.MOD_ID, this.id.getPath() + "_using_metal_former");
        }

        @Override
        public RecipeSerializer<?> type()
        {
            return ModRecipes.METAL_FORMER_RECIPE_SERIALIZER.get();
        }

        @Nullable
        @Override
        public AdvancementHolder advancement()
        {
            return this.advancement;
        }
    }
}