package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.init.ModDamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class RadioactiveBlockItem extends TintedBlockItem
{
    public RadioactiveBlockItem(Block block, Properties properties, Tints color)
    {
        super(block, properties.food(new FoodProperties.Builder().alwaysEat().effect(() -> new MobEffectInstance(MobEffects.HARM, 1, 2), 1.0F).build()), color);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected)
    {
        super.inventoryTick(stack, level, entity, slot, selected);
        if (!level.isClientSide())
            entity.hurt(level.damageSources().source(ModDamageTypes.RADIATION, null, null), 0.03F * stack.getCount());
    }
}
