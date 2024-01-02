package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.menu.MenuData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;

public class GemstoneCellMenu extends MachineMenu
{
    public GemstoneCellMenu(int id, Inventory inventory, FriendlyByteBuf data)
    {
        super(ModMenus.GEMSTONE_CELL.get(), null, new MenuData(id, inventory, new SimpleContainer(0), 0, MenuData.createSlots()), data == null ? null : data.readBlockPos());
    }
}
