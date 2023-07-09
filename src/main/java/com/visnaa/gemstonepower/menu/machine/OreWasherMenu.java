package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.menu.MenuData;
import com.visnaa.gemstonepower.registry.ModContainers;
import com.visnaa.gemstonepower.registry.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Vector2i;

public class OreWasherMenu extends MachineMenu
{
    public OreWasherMenu(int id, Inventory inventory, FriendlyByteBuf data)
    {
        this(new MenuData(id, inventory, new SimpleContainer(5), 5, MenuData.createSlots(
                        new Vector2i(53, 38),
                        new Vector2i(100, 28),
                        new Vector2i(119, 28),
                        new Vector2i(100, 47),
                        new Vector2i(119, 47))),
                data == null ? null : data.readBlockPos());
    }

    public OreWasherMenu(MenuData data, BlockPos pos)
    {
        super(ModContainers.ORE_WASHER.get(), ModRecipes.ORE_WASHER_RECIPE, data, pos);
    }
}
