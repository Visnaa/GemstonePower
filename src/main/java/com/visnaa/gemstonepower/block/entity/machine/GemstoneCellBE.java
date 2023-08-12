package com.visnaa.gemstonepower.block.entity.machine;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.menu.MenuData;
import com.visnaa.gemstonepower.menu.machine.GemstoneCellMenu;
import com.visnaa.gemstonepower.network.ModPackets;
import com.visnaa.gemstonepower.network.packet.EnergySyncS2C;
import com.visnaa.gemstonepower.pipe.energy.ForgeEnergyStorage;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GemstoneCellBE extends MachineBE<Recipe<Container>>
{
    public GemstoneCellBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.GEMSTONE_CELL.get(), null, pos, state, 0, 0);
    }

    protected AbstractContainerMenu createMenu(int id, Inventory inv)
    {
        return new GemstoneCellMenu(new MenuData(id, inv, this, 0, MenuData.createSlots(0)), getBlockPos());
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state)
    {
    }

    @Override
    protected Component getDefaultName()
    {
        return Component.translatable("menu." + GemstonePower.MOD_ID + ".gemstone_cell");
    }

    @Override
    protected ForgeEnergyStorage createEnergyStorage()
    {
        return new ForgeEnergyStorage(1000000000, ServerConfig.ENERGY_TRANSFER_RATE.get(), ServerConfig.ENERGY_TRANSFER_RATE.get())
        {
            @Override
            protected void onEnergyChanged() {
                setChanged();
                if (level != null && !level.isClientSide())
                    ModPackets.sendToClient(new EnergySyncS2C(this.energy, this.capacity, getBlockPos()));
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
}
