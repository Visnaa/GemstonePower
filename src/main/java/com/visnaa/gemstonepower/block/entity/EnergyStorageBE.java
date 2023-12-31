package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.capability.energy.EnergyStorage;

public interface EnergyStorageBE
{
    void setEnergy(int energy);

    void setCapacity(int capacity);

    int getEnergy();

    int getCapacity();

    EnergyStorage createEnergyStorage();
}
