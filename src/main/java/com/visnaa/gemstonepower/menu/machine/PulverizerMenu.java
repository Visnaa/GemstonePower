package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.menu.MenuData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Vector2i;

public class PulverizerMenu extends MachineMenu
{
    public PulverizerMenu(int id, Inventory inventory, FriendlyByteBuf data)
    {
        super(ModMenus.PULVERIZER.get(), ModRecipes.PULVERIZER_RECIPE, new MenuData(id, inventory, new SimpleContainer(2), 2, MenuData.createSlots(
                        new Vector2i(53, 38),
                        new Vector2i(109, 38))),
                data == null ? null : data.readBlockPos());
    }
}
