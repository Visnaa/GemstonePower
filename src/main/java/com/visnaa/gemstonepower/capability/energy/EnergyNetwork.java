package com.visnaa.gemstonepower.capability.energy;

import com.visnaa.gemstonepower.block.entity.pipe.PipeBE;
import com.visnaa.gemstonepower.block.entity.pipe.cable.CableBE;
import com.visnaa.gemstonepower.capability.PipeNetwork;
import com.visnaa.gemstonepower.config.ServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class EnergyNetwork implements PipeNetwork<CableBE>
{
    private Set<CableBE> cables;
    public HashMap<BlockEntity, Direction> inputs;
    public HashMap<BlockEntity, Direction> outputs;
    public HashMap<BlockEntity, Direction> batteries;
    private long lastDistribution;
    private boolean isWorking;

    public EnergyNetwork()
    {
        this.cables = new HashSet<>();
        this.inputs = new HashMap();
        this.outputs = new HashMap();
        this.batteries = new HashMap();
    }

    public void registerToNetwork(CableBE cable)
    {
        if (!this.cables.contains(cable))
            this.cables.add(cable);
    }

    public void registerInput(BlockEntity input, Direction direction)
    {
        if (input != null)
        {
            inputs.put(input, direction);
            outputs.remove(input);
            batteries.remove(input);
        }
    }

    public void registerOutput(BlockEntity output, Direction direction)
    {
        if (output != null)
        {
            outputs.put(output, direction);
            inputs.remove(output);
            batteries.remove(output);
        }
    }

    public void registerBattery(BlockEntity battery, Direction direction)
    {
        if (battery != null)
        {
            batteries.put(battery, direction);
            inputs.remove(battery);
            outputs.remove(battery);
        }
    }

    public void refresh()
    {
        for (BlockEntity be : new HashSet<>(inputs.keySet()))
            if (be.isRemoved() || !be.getCapability(ForgeCapabilities.ENERGY, inputs.get(be)).isPresent())
                inputs.remove(be);

        for (BlockEntity be : new HashSet<>(outputs.keySet()))
            if (be.isRemoved() || !be.getCapability(ForgeCapabilities.ENERGY, outputs.get(be)).isPresent())
                outputs.remove(be);

        for (BlockEntity be : new HashSet<>(batteries.keySet()))
            if (be.isRemoved() || !be.getCapability(ForgeCapabilities.ENERGY, batteries.get(be)).isPresent())
                batteries.remove(be);

        this.cables.removeIf(cable ->
        {
            if (cable.isRemoved())
            {
                if (cable.network != null) cable.network.destroy(cable);
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

        for (BlockEntity input : this.inputs.keySet())
            if (!newHost.inputs.containsKey(input))
                newHost.registerInput(input, this.inputs.get(input));

        for (BlockEntity output : this.outputs.keySet())
            if (!newHost.outputs.containsKey(output))
                newHost.registerInput(output, this.outputs.get(output));

        for (BlockEntity battery : this.batteries.keySet())
            if (!newHost.batteries.containsKey(battery))
                newHost.registerBattery(battery, this.batteries.get(battery));
    }

    @Override
    public void distribute(Level level, BlockState state, BlockPos pos, int transfer)
    {
        if (level.isClientSide() || level.getGameTime() <= lastDistribution)
            return;
        lastDistribution = level.getGameTime() + ServerConfig.ENERGY_CABLE_FREQUENCY.get() - 1;
        isWorking = false;

        for (int i = 0; i < transfer; i++)
        {
            if (getEnergy() > 1)
            {
                BlockEntity donor = null;
                IEnergyStorage donorHandler = null;

                for (BlockEntity input : this.inputs.keySet())
                {
                    IEnergyStorage iHandler = input.getCapability(ForgeCapabilities.ENERGY, inputs.get(input)).orElse(EnergyStorage.EMPTY);
                    if (iHandler == EnergyStorage.EMPTY || !iHandler.canExtract() || iHandler.getMaxEnergyStored() <= 0)
                        continue;

                    if (iHandler.extractEnergy(1, true) != 1)
                        continue;
                    donor = input;
                    donorHandler = iHandler;
                }
                if (donor == null)
                {
                    for (BlockEntity battery : this.batteries.keySet())
                    {
                        IEnergyStorage bHandler = battery.getCapability(ForgeCapabilities.ENERGY, batteries.get(battery)).orElse(EnergyStorage.EMPTY);
                        if (bHandler == EnergyStorage.EMPTY || !bHandler.canExtract() || bHandler.getMaxEnergyStored() <= 0)
                            continue;

                        if (bHandler.extractEnergy(1, true) != 1)
                            continue;
                        donor = battery;
                        donorHandler = bHandler;
                    }
                }

                boolean useBattery = true;
                if (donor != null)
                {
                    for (BlockEntity output : outputs.keySet())
                    {
                        IEnergyStorage oHandler = output.getCapability(ForgeCapabilities.ENERGY, outputs.get(output)).orElse(EnergyStorage.EMPTY);
                        if (oHandler == EnergyStorage.EMPTY || !oHandler.canReceive() || oHandler.getMaxEnergyStored() <= 0)
                            continue;

                        donorHandler.extractEnergy(1, false);
                        oHandler.receiveEnergy(1, false);
                        useBattery = false;
                        isWorking = true;
                    }

                    if (useBattery && !batteries.containsKey(donor))
                    {
                        for (BlockEntity battery : batteries.keySet())
                        {
                            IEnergyStorage bHandler = battery.getCapability(ForgeCapabilities.ENERGY, batteries.get(battery)).orElse(EnergyStorage.EMPTY);
                            if (bHandler == EnergyStorage.EMPTY || !bHandler.canReceive() || bHandler.getMaxEnergyStored() <= 0)
                                continue;

                            donorHandler.extractEnergy(1, false);
                            bHandler.receiveEnergy(1, false);
                            isWorking = true;
                        }
                    }
                }
            }
        }
    }

    public void explode()
    {
        this.cables.forEach(cable ->
        {
            if (cable.getLevel() != null)
            {
                cable.getLevel().explode(null, cable.getBlockPos().getX(), cable.getBlockPos().getY(), cable.getBlockPos().getZ(), 1.0F, Level.ExplosionInteraction.NONE);
                cable.getLevel().setBlock(cable.getBlockPos(), Blocks.AIR.defaultBlockState(), 3);
            }
        });
    }

    public long getEnergy()
    {
        long energy = 0;
        for (BlockEntity be : this.inputs.keySet())
            if (be != null && be.getCapability(ForgeCapabilities.ENERGY).isPresent())
                energy += be.getCapability(ForgeCapabilities.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);

        for (BlockEntity be : this.batteries.keySet())
            if (be != null && be.getCapability(ForgeCapabilities.ENERGY).isPresent())
                energy += be.getCapability(ForgeCapabilities.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
        return energy;
    }

    @Override
    public void destroy(PipeBE caller)
    {
        if (caller.getLevel() != null && !caller.getLevel().isClientSide())
            for (CableBE cables : this.cables)
                if (cables != null)
                    cables.network = new EnergyNetwork();
    }

    public boolean isWorking()
    {
        return isWorking;
    }
}
