package com.visnaa.gemstonepower.menu.machine;

import com.visnaa.gemstonepower.init.ModMenus;
import com.visnaa.gemstonepower.menu.MenuData;
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
        super(ModMenus.ELECTRIC_FURNACE.get(), null, new MenuData(id, inventory, new SimpleContainer(2), 2, MenuData.createSlots(
                        new Vector2i(53, 38),
                        new Vector2i(109, 38))),
                data == null ? null : data.readBlockPos());
    }

    @Override
    protected boolean canProcess(ItemStack itemStack)
    {
        Container container = new SimpleContainer(2);
        container.setItem(0, itemStack);
        return this.level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, container, this.level).isPresent();
    }
}
