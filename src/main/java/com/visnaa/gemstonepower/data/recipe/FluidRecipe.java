package com.visnaa.gemstonepower.data.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public interface FluidRecipe extends Recipe<Container>
{
    FluidStack getFluid();

    static FluidStack fromJson(JsonObject json)
    {
        if (json != null && !json.isJsonNull())
        {
            try
            {
                String[] resourceLocation = GsonHelper.getAsString(json, "name").split(":");
                Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(resourceLocation[0], resourceLocation[1]));
                int amount = GsonHelper.getAsInt(json, "amount");
                if (fluid != null)
                    return new FluidStack(fluid, amount);
            }
            catch (Exception e)
            {
                throw new JsonSyntaxException("Invalid json: " + json);
            }
        }
        return FluidStack.EMPTY;
    }

    static JsonElement toJson(FluidStack fluid)
    {
        JsonObject json = new JsonObject();
        json.addProperty("name", Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluid.getFluid())).toString());
        json.addProperty("amount", fluid.getAmount());
        return json;
    }
}
