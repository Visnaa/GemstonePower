package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.menu.MenuData;
import com.visnaa.gemstonepower.registry.ModContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;

public class GemstoneCellMenu extends MachineMenu
{
    public GemstoneCellMenu(int id, Inventory inventory, FriendlyByteBuf data)
    {
        this(new MenuData(id, inventory, new SimpleContainer(0), 0, MenuData.createSlots()), data == null ? null : data.readBlockPos());
    }

    public GemstoneCellMenu(MenuData data, BlockPos pos)
    {
        super(ModContainers.GEMSTONE_CELL.get(), null, data, pos);
    }
}
