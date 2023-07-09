package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.menu.MenuData;
import com.visnaa.gemstonepower.registry.ModContainers;
import com.visnaa.gemstonepower.registry.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Vector2i;

public class AlloySmelterMenu extends MachineMenu
{
    public AlloySmelterMenu(int id, Inventory inventory, FriendlyByteBuf data)
    {
        this(new MenuData(id, inventory, new SimpleContainer(3), 3, MenuData.createSlots(
                new Vector2i(53, 28),
                new Vector2i(53, 49),
                new Vector2i(109, 38))),
                data == null ? null : data.readBlockPos());
    }

    public AlloySmelterMenu(MenuData data, BlockPos pos)
    {
        super(ModContainers.ALLOY_SMELTER.get(), ModRecipes.ALLOY_SMELTER_RECIPE, data, pos);
    }
}
