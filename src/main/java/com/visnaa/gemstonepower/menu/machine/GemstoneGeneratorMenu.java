package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.init.ModRecipes;
import com.visnaa.gemstonepower.menu.MenuData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Vector2i;

public class GemstoneGeneratorMenu extends MachineMenu
{
    public GemstoneGeneratorMenu(int id, Inventory inventory, FriendlyByteBuf data)
    {
        super(ModMenus.GEMSTONE_GENERATOR.get(), ModRecipes.GEMSTONE_GENERATOR_RECIPE, new MenuData(id, inventory, new SimpleContainer(1), 1, MenuData.createSlots(
                        new Vector2i(53, 38))),
                data == null ? null : data.readBlockPos());
    }
}
