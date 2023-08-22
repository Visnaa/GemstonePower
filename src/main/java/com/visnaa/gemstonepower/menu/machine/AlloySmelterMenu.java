package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.menu.MenuData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Vector2i;

public class AlloySmelterMenu extends MachineMenu
{
    public AlloySmelterMenu(int id, Inventory inventory, FriendlyByteBuf data)
    {
        super(ModMenus.ALLOY_SMELTER.get(), ModRecipes.ALLOY_SMELTER_RECIPE, new MenuData(id, inventory, new SimpleContainer(3), 3, MenuData.createSlots(
                new Vector2i(53, 28),
                new Vector2i(53, 49),
                new Vector2i(109, 38))),
                data == null ? null : data.readBlockPos());
    }
}
