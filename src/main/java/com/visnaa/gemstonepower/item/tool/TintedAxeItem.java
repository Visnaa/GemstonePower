package com.visnaa.gemstonepower.item.tool;

import com.visnaa.gemstonepower.client.render.Tintable;
import com.visnaa.gemstonepower.client.render.Tints;
import net.minecraft.world.item.AxeItem;

public class TintedAxeItem extends AxeItem implements Tintable
{
    private final int color;

    public TintedAxeItem(ToolMaterial material, int damage, float multiplier, Properties properties)
    {
        super(material, damage, multiplier, properties);
        this.color = material.getMaterial().getColor();
        Tints.TINTED_ITEMS.add(this);
    }

    public int getColor()
    {
        return color;
    }
}
