package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.client.render.Tintable;
import com.visnaa.gemstonepower.client.render.Tints;
import net.minecraft.world.item.Item;

public class TintedItem extends Item implements Tintable
{
    private int color;

    public TintedItem(Properties properties, Tints color)
    {
        super(properties);
        this.color = color.getColor();
        Tints.TINTED_ITEMS.add(this);
    }

    public int getColor()
    {
        return color;
    }
}
