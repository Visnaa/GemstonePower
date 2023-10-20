package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.menu.MenuData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Vector2i;

public class GemstoneManipulatorMenu extends MachineMenu
{
    public GemstoneManipulatorMenu(int id, Inventory inventory, FriendlyByteBuf data)
    {
        super(ModMenus.GEMSTONE_MANIPULATOR.get(), ModRecipes.GEMSTONE_MANIPULATOR_RECIPE, new MenuData(id, inventory, new SimpleContainer(3), 3, MenuData.createSlots(
                        new Vector2i(28, 38),
                        new Vector2i(53, 38),
                        new Vector2i(109, 38))),
                data == null ? null : data.readBlockPos());
    }
}
