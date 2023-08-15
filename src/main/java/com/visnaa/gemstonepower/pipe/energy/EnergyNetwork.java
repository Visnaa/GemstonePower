package com.visnaa.gemstonepower.pipe.energy;

import com.visnaa.gemstonepower.block.entity.pipe.cable.CableBE;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class EnergyNetwork
{
    private List<CableBE> cables;
    public Set<BlockEntity> inputs;
    public Set<BlockEntity> batteries;

    public EnergyNetwork()
    {
        this.cables = new ArrayList<>();
        this.inputs = new HashSet<>();
        this.batteries = new HashSet<>();
    }

    public void registerToNetwork(CableBE cable)
    {
        if (!this.cables.contains(cable))
            this.cables.add(cable);
    }

    public void registerInput(BlockEntity input)
    {
        if (input != null) this.inputs.add(input);
    }

    public void registerBattery(BlockEntity battery)
    {
        if (battery != null) this.batteries.add(battery);
    }

    public void refresh()
    {
        this.inputs.removeIf(be -> be == null ||
                !be.getCapability(ForgeCapabilities.ENERGY).isPresent() ||
                !be.getCapability(ForgeCapabilities.ENERGY).map(IEnergyStorage::canExtract).orElse(false));

        this.batteries.removeIf(be -> be == null ||
                !be.getCapability(ForgeCapabilities.ENERGY).isPresent() ||
                !be.getCapability(ForgeCapabilities.ENERGY).map(IEnergyStorage::canExtract).orElse(false) ||
                !be.getCapability(ForgeCapabilities.ENERGY).map(IEnergyStorage::canReceive).orElse(false));

        this.cables.removeIf(cable -> {
            if (cable.isRemoved())
            {
                if (cable.network != null) cable.network.destroy();
                return true;
            }
            return false;
        });
    }

    public void merge(EnergyNetwork newHost)
    {
        if (newHost == this) return;

        for (CableBE cable : this.cables)
        {
            if (!newHost.cables.contains(cable))
            {
                newHost.registerToNetwork(cable);
                cable.network = newHost;
            }
        }

        for (BlockEntity input : this.inputs)
            if (!newHost.inputs.contains(input))
                newHost.registerInput(input);
        for (BlockEntity battery : this.batteries)
            if (!newHost.batteries.contains(battery))
                newHost.registerBattery(battery);
    }

    public boolean withdrawFromNetwork(int amount)
    {
        if (amount > 0 && amount <= getEnergy())
        {
            AtomicInteger left = new AtomicInteger(amount);
            for (BlockEntity be : this.inputs)
            {
                if (left.get() <= 0) break;
                if (be != null && be.getCapability(ForgeCapabilities.ENERGY).isPresent())
                {
                    be.getCapability(ForgeCapabilities.ENERGY).map(handler -> {
                        int extracted = handler.extractEnergy(left.get(), false);
                        left.addAndGet(-extracted);
                        return true;
                    });
                    be.setChanged();
                }
            }
            for (BlockEntity be : this.batteries)
            {
                if (left.get() <= 0) break;
                if (be != null && be.getCapability(ForgeCapabilities.ENERGY).isPresent())
                {
                    be.getCapability(ForgeCapabilities.ENERGY).map(handler -> {
                        int extracted = handler.extractEnergy(left.get(), false);
                        left.addAndGet(-extracted);
                        return true;
                    });
                    be.setChanged();
                }
            }
            return true;
        }
        return false;
    }

    public void explode()
    {
        this.cables.forEach(cable -> {
            cable.getLevel().explode(null, cable.getBlockPos().getX(), cable.getBlockPos().getY(), cable.getBlockPos().getZ(), 1.0F, Level.ExplosionInteraction.NONE);
            cable.getLevel().setBlock(cable.getBlockPos(), Blocks.AIR.defaultBlockState(), 3);
        });
    }

    public long getEnergy()
    {
        long energy = 0;
        for (BlockEntity be : this.inputs)
        {
            if (be != null && be.getCapability(ForgeCapabilities.ENERGY).isPresent())
            {
                energy += be.getCapability(ForgeCapabilities.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
            }
        }
        for (BlockEntity be : this.batteries)
        {
            if (be != null && be.getCapability(ForgeCapabilities.ENERGY).isPresent())
            {
                energy += be.getCapability(ForgeCapabilities.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
            }
        }
        return energy;
    }

    private void destroy()
    {
        for (CableBE cable : this.cables)
        {
            if (cable != null)
            {
                cable.network = null;
            }
        }
    }
}
