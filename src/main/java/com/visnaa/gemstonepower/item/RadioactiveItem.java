package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.registry.ModDamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RadioactiveItem extends TintedItem
{
    public RadioactiveItem(Properties properties, Tints color)
    {
        super(properties, color);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected)
    {
        super.inventoryTick(stack, level, entity, slot, selected);
        if (!level.isClientSide())
            entity.hurt(level.damageSources().source(ModDamageTypes.RADIATION, null, null), 0.01F * stack.getCount());
    }
}
