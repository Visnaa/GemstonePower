package com.visnaa.gemstonepower.item.armour;

import com.visnaa.gemstonepower.client.render.Tintable;
import com.visnaa.gemstonepower.client.render.Tints;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;

public class TintedArmourItem extends DyeableArmorItem implements Tintable, DyeableLeatherItem
{
    private final int color;

    public TintedArmourItem(ArmorMaterial material, Type type, Properties properties)
    {
        super(material, type, properties);
        this.color = material.getMaterial().getColor();
        Tints.TINTED_ITEMS.add(this);
    }

    public int getColor()
    {
        return color;
    }

    @Override
    public int getColor(ItemStack stack)
    {
        return this.getColor();
    }

}
