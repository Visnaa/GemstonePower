package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.menu.MenuData;
import com.visnaa.gemstonepower.registry.ModContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.joml.Vector2i;

public class ElectricFurnaceMenu extends MachineMenu
{
    public ElectricFurnaceMenu(int id, Inventory inventory, FriendlyByteBuf data)
    {
        this(new MenuData(id, inventory, new SimpleContainer(2), 2, MenuData.createSlots(
                        new Vector2i(53, 38),
                        new Vector2i(109, 38))),
                data == null ? null : data.readBlockPos());
    }

    public ElectricFurnaceMenu(MenuData data, BlockPos pos)
    {
        super(ModContainers.ELECTRIC_FURNACE.get(), null, data, pos);
    }

    @Override
    protected boolean canProcess(ItemStack itemStack)
    {
        Container container = new SimpleContainer(2);
        container.setItem(0, itemStack);
        return this.level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, container, this.level).isPresent();
    }
}
