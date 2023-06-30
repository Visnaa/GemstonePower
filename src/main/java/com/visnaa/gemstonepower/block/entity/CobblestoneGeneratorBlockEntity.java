package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.network.energy.ForgeEnergyStorage;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class CobblestoneGeneratorBlockEntity extends BlockEntity
{
    private int cooldown = 0;

    public CobblestoneGeneratorBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.COBBLESTONE_GENERATOR.get(), pos, state);
    }

    public void load(CompoundTag tag)
    {
        super.load(tag);
        this.energyStorage.deserializeNBT(tag.get("Energy"));
        this.cooldown =  tag.getInt("Cooldown");
    }

    protected void saveAdditional(CompoundTag tag)
    {
        super.saveAdditional(tag);
        tag.put("Energy", this.energyStorage.serializeNBT());
        tag.putInt("Cooldown", this.cooldown);
    }
    
    public static void serverTick(Level level, BlockPos pos, BlockState state, CobblestoneGeneratorBlockEntity blockEntity)
    {
        blockEntity.generate(level, pos, state);
    }

    public void generate(Level level, BlockPos pos, BlockState state)
    {
        if (energyStorage.getEnergyStored() > 40 && cooldown <= 0)
        {
            BlockEntity be = level.getBlockEntity(pos.below());
            if (be != null && be.getCapability(ForgeCapabilities.ITEM_HANDLER).isPresent())
            {
                be.getCapability(ForgeCapabilities.ITEM_HANDLER).map(handler -> {
                    for (int i = 0; i < handler.getSlots(); i++)
                    {
                        if ((handler.getStackInSlot(i).is(Items.COBBLESTONE) || handler.getStackInSlot(i).isEmpty()) && handler.getStackInSlot(i).getCount() <= handler.getStackInSlot(i).getMaxStackSize() - 1)
                        {
                            ItemStack stack = handler.insertItem(i, new ItemStack(Blocks.COBBLESTONE), false);
                            if (stack.isEmpty())
                            {
                                energyStorage.consumeEnergy(40);
                                cooldown = 100;
                            }
                            return true;
                        }
                    }
                    return false;
                });
            }
        }
        cooldown--;
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
        return new ForgeEnergyStorage(20000, Integer.MAX_VALUE, Integer.MAX_VALUE) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
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
