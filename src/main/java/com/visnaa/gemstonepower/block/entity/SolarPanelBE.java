package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.pipe.energy.ForgeEnergyStorage;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import com.visnaa.gemstonepower.util.EnergyUtilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class SolarPanelBE extends BlockEntity
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
    
    public static void serverTick(Level level, BlockPos pos, BlockState state, SolarPanelBE blockEntity)
    {
        blockEntity.generate(level, pos, state);
    }

    public void generate(Level level, BlockPos pos, BlockState state)
    {
        if (level.canSeeSky(pos.above()) && (level.getDayTime() % 24000) <= 12500L && level.getBlockState(pos.above()).is(Blocks.AIR))
        {
            energyStorage.addEnergy(EnergyUtilities.getGeneration(state, ServerConfig.IDLE_GENERATOR_GENERATION.get()));
        }
    }

    final ForgeEnergyStorage energyStorage = createEnergyStorage();
    LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing)
    {
        if (!this.remove)
        {
            if (capability == ForgeCapabilities.ENERGY)
            {
                return energy.cast();
            }
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();
        energy.invalidate();
    }

    @Override
    public void reviveCaps()
    {
        super.reviveCaps();
        this.energy = LazyOptional.of(() -> energyStorage);
    }

    private ForgeEnergyStorage createEnergyStorage()
    {
        return new ForgeEnergyStorage(EnergyUtilities.getCapacity(this.getBlockState(), ServerConfig.DEFAULT_GENERATOR_CAPACITY.get()), 0, Integer.MAX_VALUE) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
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
}
