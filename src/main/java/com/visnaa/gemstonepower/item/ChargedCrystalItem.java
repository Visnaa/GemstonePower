package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.client.render.Tints;
import net.minecraft.world.item.ItemStack;

public class ChargedCrystalItem extends CrystalItem
{
    public ChargedCrystalItem(Properties properties, Tints color)
    {
        super(properties, color);
    }

    @Override
    public boolean isFoil(ItemStack stack)
    {
        return true;
    }
}
