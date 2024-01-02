package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.capability.energy.EnergyStorage;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.energy.IEnergyStorage;

public interface EnergyStorageBE
{
    void setEnergy(int energy);

    void setCapacity(int capacity);

    int getEnergy();

    int getCapacity();

    EnergyStorage createEnergyStorage();

    IEnergyStorage getEnergyStorage(Direction dir);
}
