package com.visnaa.gemstonepower.entity.projectile;

import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.init.ModEntities;
import com.visnaa.gemstonepower.item.CrystalArrowItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import java.util.function.Consumer;

public class CrystalArrow extends AbstractArrow
{
    private int colour;
    private final CrystalArrowItem arrowItem;
    private final Consumer<EntityHitResult> consumer;

    public CrystalArrow(EntityType<? extends CrystalArrow> arrow, CrystalArrowItem arrowItem, Tints tint, Level level)
    {
        super(arrow, level);
        this.arrowItem = arrowItem;
        this.colour = tint.getColor();
        this.consumer = arrowItem.getHit();
    }

    public CrystalArrow(CrystalArrowItem arrowItem, Tints tint, Level level, double x, double y, double z)
    {
        super(ModEntities.RUBY_ARROW.get(), x, y, z, level);
        this.arrowItem = arrowItem;
        this.colour = tint.getColor();
        this.consumer = arrowItem.getHit();
    }

    public CrystalArrow(CrystalArrowItem arrowItem, Tints tint, Level level, LivingEntity entity)
    {
        super(ModEntities.RUBY_ARROW.get(), entity, level);
        this.arrowItem = arrowItem;
        this.colour = tint.getColor();
        this.consumer = arrowItem.getHit();
    }

    public void tick()
    {
        super.tick();
        if (!this.level().isClientSide && this.inGround && this.inGroundTime != 0 && this.inGroundTime >= 600)
        {
            this.level().broadcastEntityEvent(this, (byte)0);
        }
    }

    public int getColor()
    {
        return this.colour;
    }

    public void addAdditionalSaveData(CompoundTag tag)
    {
        super.addAdditionalSaveData(tag);
        tag.putInt("Color", this.getColor());
    }

    public void readAdditionalSaveData(CompoundTag tag)
    {
        super.readAdditionalSaveData(tag);
        this.colour = tag.getInt("Color");

    }

    @Override
    protected void onHitEntity(EntityHitResult hit)
    {
        super.onHitEntity(hit);
        consumer.accept(hit);
    }

    protected ItemStack getPickupItem()
    {
        return new ItemStack(arrowItem);
    }
}
