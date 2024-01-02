package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.menu.MenuData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Vector2i;

public class OreWasherMenu extends MachineMenu
{
    public OreWasherMenu(int id, Inventory inventory, FriendlyByteBuf data)
    {
        super(ModMenus.ORE_WASHER.get(), ModRecipes.ORE_WASHER_RECIPE, new MenuData(id, inventory, new SimpleContainer(5), 5, MenuData.createSlots(
                        new Vector2i(53, 38),
                        new Vector2i(100, 28),
                        new Vector2i(119, 28),
                        new Vector2i(100, 47),
                        new Vector2i(119, 47))),
                data == null ? null : data.readBlockPos());
    }
}
