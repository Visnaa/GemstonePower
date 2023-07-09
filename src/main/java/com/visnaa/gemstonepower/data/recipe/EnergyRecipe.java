package com.visnaa.gemstonepower.data.recipe;

import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

public interface EnergyRecipe extends Recipe<Container>
{
    int getProcessingTime();
    int getEnergyUsage();
}
