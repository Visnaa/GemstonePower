package com.visnaa.gemstonepower.util;

import com.visnaa.gemstonepower.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public enum MachinePresets
{
    PLATE(ModItems.PLATE_PRESET.get()),
    ROD(ModItems.ROD_PRESET.get()),
    WIRE(ModItems.WIRE_PRESET.get());

    private Item item;

    MachinePresets(Item item)
    {
        this.item = item;
    }

    public static MachinePresets getByName(String name)
    {
        switch (name)
        {
            case "plate": return PLATE;
            case "rod": return ROD;
            case "wire": return WIRE;
        }
        return PLATE;
    }

    public static MachinePresets getByItem(ItemStack stack)
    {
        if (stack.is(PLATE.item)) return PLATE;
        else if (stack.is(ROD.item)) return ROD;
        else if (stack.is(WIRE.item)) return WIRE;
        return null;
    }

    public static boolean compare(ItemStack stack, String name)
    {
        return getByItem(stack) == getByName(name);
    }

    public Item getItem()
    {
        return item;
    }
}
