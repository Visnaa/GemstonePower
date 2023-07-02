package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.client.render.Tints;
import net.minecraft.world.item.ItemStack;

public class FoilItem extends TintedItem
{
    public FoilItem(Properties properties, Tints color)
    {
        super(properties, color);
    }

    @Override
    public boolean isFoil(ItemStack stack)
    {
        return true;
    }
}
