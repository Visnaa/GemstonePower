package com.visnaa.gemstonepower.pipe.energy;

public class EnergyStorage extends net.minecraftforge.energy.EnergyStorage
{
    public static final EnergyStorage EMPTY = new EnergyStorage(0, 0, 0);

    public EnergyStorage(int capacity, int maxReceive, int maxExtract)
    {
        super(capacity, maxReceive, maxExtract);
    }

    public void onEnergyChanged()
    {

    }

    public void setEnergy(int energy)
    {
        this.energy = energy;
        onEnergyChanged();
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
        onEnergyChanged();
    }

    public void addEnergy(int energy)
    {
        this.energy += energy;
        if (this.energy > getMaxEnergyStored())
        {
            this.energy = getMaxEnergyStored();
        }
        onEnergyChanged();
    }

    public void consumeEnergy(int energy)
    {
        this.energy -= energy;
        if (this.energy < 0)
        {
            this.energy = 0;
        }
        onEnergyChanged();
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        onEnergyChanged();
        return super.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        onEnergyChanged();
        return super.extractEnergy(maxExtract, simulate);
    }
}
