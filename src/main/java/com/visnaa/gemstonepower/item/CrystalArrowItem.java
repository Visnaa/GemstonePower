package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.client.render.Tintable;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.entity.projectile.CrystalArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import java.util.function.Consumer;

public class CrystalArrowItem extends ArrowItem implements Tintable
{
    private final Tints tint;
    private final Consumer<EntityHitResult> hit;
    private final boolean foil;

    public CrystalArrowItem(Properties properties, Tints tint, Consumer<EntityHitResult> hit, boolean foil)
    {
        super(properties);
        this.tint = tint;
        this.hit = hit;
        this.foil = foil;
        Tints.TINTED_ITEMS.add(this);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack stack, LivingEntity entity)
    {
        CrystalArrow arrow = new CrystalArrow(this, tint, level, entity);
        return arrow;
    }

    @Override
    public int getColor()
    {
        return this.tint.getColor();
    }

    public Consumer<EntityHitResult> getHit()
    {
        return hit;
    }

    @Override
    public boolean isFoil(ItemStack stack)
    {
        return this.foil;
    }
}
