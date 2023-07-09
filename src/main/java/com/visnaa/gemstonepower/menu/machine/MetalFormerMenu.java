package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.menu.MenuData;
import com.visnaa.gemstonepower.registry.ModContainers;
import com.visnaa.gemstonepower.registry.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Vector2i;

public class MetalFormerMenu extends MachineMenu
{
    public MetalFormerMenu(int id, Inventory inventory, FriendlyByteBuf data)
    {
        this(new MenuData(id, inventory, new SimpleContainer(3), 3, MenuData.createSlots(
                        new Vector2i(53, 38),
                        new Vector2i(16, 38),
                        new Vector2i(109, 38))),
                data == null ? null : data.readBlockPos());
    }

    public MetalFormerMenu(MenuData data, BlockPos pos)
    {
        super(ModContainers.METAL_FORMER.get(), ModRecipes.METAL_FORMER_RECIPE, data, pos);
    }
}
