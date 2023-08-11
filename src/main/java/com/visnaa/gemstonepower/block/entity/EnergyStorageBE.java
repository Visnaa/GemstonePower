package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.network.ModPackets;
import com.visnaa.gemstonepower.network.packet.EnergySyncS2C;
import com.visnaa.gemstonepower.pipe.energy.ForgeEnergyStorage;
import com.visnaa.gemstonepower.util.EnergyUtilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public abstract class EnergyStorageBE extends BaseContainerBlockEntity
{
    protected ForgeEnergyStorage energyStorage = createEnergyStorage();
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    public EnergyStorageBE(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }

    public void setEnergy(int energy)
    {
        this.energyStorage.setEnergy(energy);
    }

    public void setCapacity(int capacity)
    {
        this.energyStorage.setCapacity(capacity);
    }

    public int getEnergy()
    {
        return this.energyStorage.getEnergyStored();
    }

    public int getCapacity()
    {
        return this.energyStorage.getMaxEnergyStored();
    }

    @Override
    protected Component getDefaultName()
    {
        return Component.translatable("menu." + GemstonePower.MOD_ID + "." + ForgeRegistries.BLOCKS.getKey(getBlockState().getBlock()).getPath());
    }

    @Override
    public void load(CompoundTag tag)
    {
        super.load(tag);
        this.energyStorage.deserializeNBT(tag.get("Energy"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        tag.put("Energy", energyStorage.serializeNBT());
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing)
    {
        if (!this.isRemoved() && capability == ForgeCapabilities.ENERGY)
        {
            return energy.cast();
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

    protected ForgeEnergyStorage createEnergyStorage()
    {
        return new ForgeEnergyStorage(EnergyUtilities.getCapacity(this.getBlockState(), ServerConfig.DEFAULT_MACHINE_CAPACITY.get()), Integer.MAX_VALUE, 0) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                if (!level.isClientSide())
                    ModPackets.sendToClient(new EnergySyncS2C(getEnergy(), getCapacity(), getBlockPos()));
            }

            @Override
            public boolean canReceive()
            {
                return true;
            }

            @Override
            public boolean canExtract()
            {
                return false;
            }
        };
    }
}
