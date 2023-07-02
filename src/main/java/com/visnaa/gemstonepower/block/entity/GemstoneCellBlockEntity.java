package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.screen.menu.GemstoneCellMenu;
import com.visnaa.gemstonepower.config.CommonConfig;
import com.visnaa.gemstonepower.network.energy.ForgeEnergyStorage;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GemstoneCellBlockEntity extends BaseContainerBlockEntity
{
    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int id)
        {
            return switch (id)
                    {
                        case 0 -> GemstoneCellBlockEntity.this.energyStorage.getEnergyStored();
                        case 1 -> GemstoneCellBlockEntity.this.energyStorage.getMaxEnergyStored();
                        default -> 0;
                    };
        }

        public void set(int id, int val) {
        }

        public int getCount() {
            return 2;
        }
    };

    public GemstoneCellBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.GEMSTONE_CELL.get(), pos, state);
    }

    protected AbstractContainerMenu createMenu(int id, Inventory inv)
    {
        return new GemstoneCellMenu(id, inv, this, this.dataAccess);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, GemstoneCellBlockEntity blockEntity)
    {
        blockEntity.sendPower(level, pos, state);
    }

    protected void sendPower(Level level, BlockPos pos, BlockState state)
    {
        AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        if (capacity.get() > 0) {
            List<BlockEntity> outputs = new ArrayList<>();
            for (Direction direction : Direction.values())
            {
                BlockEntity be = level.getBlockEntity(pos.relative(direction.getOpposite()));
                if (be != null && be.getCapability(ForgeCapabilities.ENERGY, direction).isPresent())
                {
                    outputs.add(be);
                }
            }

            if (outputs.size() > 0)
            {
                int energy = Math.min(capacity.get() / outputs.size(), CommonConfig.ENERGY_TRANSFER_RATE.get());
                for (BlockEntity be : outputs)
                {
                    be.getCapability(ForgeCapabilities.ENERGY).map(handler ->
                    {
                        int received = handler.receiveEnergy(energy, false);
                        capacity.addAndGet(-received);
                        energyStorage.consumeEnergy(received);
                        setChanged();
                        return true;
                    });
                }
                setChanged(level, pos, state);
            }
        }
    }

    @Override
    public void load(CompoundTag tag) {
        energyStorage.deserializeNBT(tag.get("Energy"));
        super.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag)
    {
        tag.put("Energy", energyStorage.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("menu." + GemstonePower.MOD_ID + ".gemstone_cell");
    }

    final ForgeEnergyStorage energyStorage = createEnergyStorage();
    LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private ForgeEnergyStorage createEnergyStorage()
    {
        return new ForgeEnergyStorage(1000000000, CommonConfig.ENERGY_TRANSFER_RATE.get(), CommonConfig.ENERGY_TRANSFER_RATE.get())
        {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }

            @Override
            public boolean canReceive() {
                return true;
            }

            @Override
            public boolean canExtract() {
                return true;
            }
        };
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability)
    {
        if (!this.remove && capability == ForgeCapabilities.ENERGY)
        {
            return energy.cast();
        }
        return super.getCapability(capability);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing)
    {
        if (!this.remove && capability == ForgeCapabilities.ENERGY)
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

    @Override
    public int getContainerSize()
    {
        return 0;
    }

    @Override
    public boolean isEmpty()
    {
        return true;
    }

    @Override
    public ItemStack getItem(int id)
    {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int id, int value)
    {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItemNoUpdate(int value)
    {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItem(int id, ItemStack stack) {

    }

    @Override
    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this)
        {
            return false;
        } else {
            return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clearContent() {

    }
}
