package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.capability.energy.EnergyStorage;
import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.network.packet.EnergySyncS2C;
import com.visnaa.gemstonepower.util.MachineUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.network.PacketDistributor;

public class SolarPanelBE extends BlockEntity implements TickingBlockEntity, EnergyStorageBE
{
    public SolarPanelBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.SOLAR_PANEL.get(), pos, state);
    }

    public void load(CompoundTag tag)
    {
        super.load(tag);
        this.energyStorage.deserializeNBT(tag.get("Energy"));
    }

    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        tag.put("Energy", energyStorage.serializeNBT());
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state)
    {
        generate(level, pos, state);
    }

    public void generate(Level level, BlockPos pos, BlockState state)
    {
        if (level.canSeeSky(pos.above()) && (level.getDayTime() % 24000) <= 12500L && level.getBlockState(pos.above()).is(Blocks.AIR))
        {
            energyStorage.addEnergy(MachineUtil.getGeneration(state, ServerConfig.IDLE_GENERATOR_GENERATION.get()));
        }
    }

    final EnergyStorage energyStorage = createEnergyStorage();

    @Override
    public void setEnergy(int energy)
    {
        energyStorage.setEnergy(energy);
    }

    @Override
    public void setCapacity(int capacity)
    {
        energyStorage.setCapacity(capacity);
    }

    @Override
    public int getEnergy()
    {
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getCapacity()
    {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public EnergyStorage createEnergyStorage()
    {
        return new EnergyStorage(MachineUtil.getCapacity(this.getBlockState(), ServerConfig.DEFAULT_GENERATOR_CAPACITY.get()), 0, Integer.MAX_VALUE) {
            @Override
            public void onEnergyChanged()
            {
                if (level != null && !level.isClientSide())
                    PacketDistributor.ALL.noArg().send(new EnergySyncS2C(energy, capacity, getBlockPos()));
            }

            @Override
            public boolean canReceive()
            {
                return false;
            }

            @Override
            public boolean canExtract()
            {
                return true;
            }
        };
    }

    @Override
    public IEnergyStorage getEnergyStorage(Direction dir)
    {
        return energyStorage;
    }
}
