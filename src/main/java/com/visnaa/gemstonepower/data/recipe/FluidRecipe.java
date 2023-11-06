package com.visnaa.gemstonepower.data.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public interface FluidRecipe extends Recipe<Container>
{
    Codec<FluidStack> FLUID_CODEC = RecordCodecBuilder.create(builder -> builder.group(
            ResourceLocation.CODEC.fieldOf("name").forGetter(stack -> ForgeRegistries.FLUIDS.getKey(stack.getFluid())),
            Codec.INT.fieldOf("amount").forGetter(FluidStack::getAmount)
    ).apply(builder, (id, amount) -> new FluidStack(ForgeRegistries.FLUIDS.getValue(id), amount)));

    FluidStack getFluid();

    static JsonElement toJson(FluidStack fluid)
    {
        JsonObject json = new JsonObject();
        json.addProperty("name", Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluid.getFluid())).toString());
        json.addProperty("amount", fluid.getAmount());
        return json;
    }
}
