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
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

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
            if (be.isRemoved() || !be.hasLevel() || be.getLevel().getCapability(Capabilities.EnergyStorage.BLOCK, be.getBlockPos(), be.getBlockState(), be, inputs.get(be)) == null)
                inputs.remove(be);

        for (BlockEntity be : new HashSet<>(outputs.keySet()))
            if (be.isRemoved() || !be.hasLevel() || be.getLevel().getCapability(Capabilities.EnergyStorage.BLOCK, be.getBlockPos(), be.getBlockState(), be, inputs.get(be)) == null)
                outputs.remove(be);

        for (BlockEntity be : new HashSet<>(batteries.keySet()))
            if (be.isRemoved() || !be.hasLevel() || be.getLevel().getCapability(Capabilities.EnergyStorage.BLOCK, be.getBlockPos(), be.getBlockState(), be, inputs.get(be)) == null)
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

        if (getEnergy() > 0)
        {
            int energyLeft = (int) Math.min(getEnergy(), transfer);
            HashMap<IEnergyStorage, Integer> outputMap = new HashMap<>();
            for (BlockEntity output : outputs.keySet())
            {
                if (energyLeft <= 0)
                    break;

                IEnergyStorage handler = level.getCapability(Capabilities.EnergyStorage.BLOCK, output.getBlockPos(), output.getBlockState(), output, outputs.get(output));
                if (handler == null || handler == EnergyStorage.EMPTY || !handler.canReceive() || handler.getMaxEnergyStored() <= 0)
                    continue;

                int energyAccepted = handler.receiveEnergy(energyLeft, true);
                if (energyAccepted <= 0)
                    continue;

                energyLeft -= energyAccepted;
                outputMap.put(handler, energyAccepted);
            }
            long energyToOutput = 0;
            for (int energy : outputMap.values())
                energyToOutput += energy;
            long inputEnergy = getInputEnergy();
            long energyToStore = inputEnergy - energyToOutput;
            HashMap<IEnergyStorage, Integer> batteryMap = new HashMap<>();
            for (BlockEntity battery : batteries.keySet())
            {
                if (energyToOutput >= inputEnergy || energyToStore <= 0)
                    break;

                IEnergyStorage handler = level.getCapability(Capabilities.EnergyStorage.BLOCK, battery.getBlockPos(), battery.getBlockState(), battery, batteries.get(battery));
                if (handler == null || handler == EnergyStorage.EMPTY || !handler.canReceive() || handler.getMaxEnergyStored() <= 0)
                    continue;
                int energyAccepted = handler.receiveEnergy((int) energyToStore, true);
                energyToStore -= energyAccepted;
                batteryMap.put(handler, energyAccepted);
            }

            if (!outputMap.isEmpty() || !batteryMap.isEmpty())
            {
                isWorking = true;
                int energyTransferred = 0;
                boolean useBatteries = batteryMap.isEmpty();
                for (IEnergyStorage handler : outputMap.keySet())
                    energyTransferred += handler.receiveEnergy(outputMap.get(handler), false);
                for (IEnergyStorage handler : batteryMap.keySet())
                    energyTransferred += handler.receiveEnergy(batteryMap.get(handler), false);

                for (BlockEntity input : inputs.keySet())
                {
                    if (energyTransferred <= 0)
                        break;

                    IEnergyStorage handler = level.getCapability(Capabilities.EnergyStorage.BLOCK, input.getBlockPos(), input.getBlockState(), input, inputs.get(input));
                    if (handler == null || handler == EnergyStorage.EMPTY || !handler.canExtract() || handler.getMaxEnergyStored() <= 0)
                        continue;

                    int energyExtracted = handler.extractEnergy(energyTransferred, false);
                    if (energyExtracted <= 0)
                        continue;

                    energyTransferred -= energyExtracted;
                }
                if (useBatteries && energyTransferred > 0)
                {
                    for (BlockEntity battery : batteries.keySet())
                    {
                        if (energyTransferred <= 0)
                            break;

                        IEnergyStorage handler = level.getCapability(Capabilities.EnergyStorage.BLOCK, battery.getBlockPos(), battery.getBlockState(), battery, batteries.get(battery));
                        if (handler == null || handler == EnergyStorage.EMPTY || !handler.canExtract() || handler.getMaxEnergyStored() <= 0)
                            continue;

                        int energyExtracted = handler.extractEnergy(energyTransferred, true);
                        if (energyExtracted <= 0)
                            continue;

                        energyTransferred -= energyExtracted;
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
        long energy = getInputEnergy();
        for (BlockEntity be : this.batteries.keySet())
            if (be != null && be.hasLevel() && be.getLevel().getCapability(Capabilities.EnergyStorage.BLOCK, be.getBlockPos(), be.getBlockState(), be, null) != null)
                energy += be.getLevel().getCapability(Capabilities.EnergyStorage.BLOCK, be.getBlockPos(), be.getBlockState(), be, null).getEnergyStored();
        return energy;
    }

    public long getInputEnergy()
    {
        long energy = 0;
        for (BlockEntity be : this.inputs.keySet())
            if (be != null && be.hasLevel() && be.getLevel().getCapability(Capabilities.EnergyStorage.BLOCK, be.getBlockPos(), be.getBlockState(), be, null) != null)
                energy += be.getLevel().getCapability(Capabilities.EnergyStorage.BLOCK, be.getBlockPos(), be.getBlockState(), be, null).getEnergyStored();
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
