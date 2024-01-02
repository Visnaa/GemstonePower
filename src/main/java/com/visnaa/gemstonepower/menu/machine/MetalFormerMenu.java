package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.menu.MenuData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Vector2i;

public class MetalFormerMenu extends MachineMenu
{
    public MetalFormerMenu(int id, Inventory inventory, FriendlyByteBuf data)
    {
        super(ModMenus.METAL_FORMER.get(), ModRecipes.METAL_FORMER_RECIPE, new MenuData(id, inventory, new SimpleContainer(2), 2, MenuData.createSlots(
                        new Vector2i(53, 38),
                        new Vector2i(109, 38))),
                data == null ? null : data.readBlockPos());
    }
}
