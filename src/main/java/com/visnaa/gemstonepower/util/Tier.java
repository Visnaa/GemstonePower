package com.visnaa.gemstonepower.util;

import com.visnaa.gemstonepower.block.TieredBlock;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.init.ModItems;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.ArrayList;
import java.util.List;

public enum Tier implements StringRepresentable
{
    STANDARD(1, "standard"),
    INTERMEDIATE(2, "intermediate"),
    ADVANCED(3, "advanced"),
    ULTRA(4, "ultra"),
    EXTREME(5, "extreme");

    public static EnumProperty<Tier> TIER = EnumProperty.create("tier", Tier.class, Tier.values());
    public static final List<TieredBlock<?>> BLOCKS = new ArrayList<>();

    private int id;
    private String name;

    Tier(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int id()
    {
        return this.id;
    }

    public static Tier getById(int id)
    {
        for (Tier tier : Tier.values())
        {
            if (id == tier.id()) return tier;
        }
        return STANDARD;
    }

    public static ItemStack getTierUpgrade(Tier tier)
    {
        switch (tier)
        {
            case INTERMEDIATE ->
            {
                return new ItemStack(ModItems.INTERMEDIATE_UPGRADE.get());
            }
            case ADVANCED ->
            {
                return new ItemStack(ModItems.ADVANCED_UPGRADE.get());
            }
            case ULTRA ->
            {
                return new ItemStack(ModItems.ULTRA_UPGRADE.get());
            }
            case EXTREME ->
            {
                return new ItemStack(ModItems.EXTREME_UPGRADE.get());
            }
        }
        return ItemStack.EMPTY;
    }

    public static Tints getTierTint(Tier tier)
    {
        switch (tier)
        {
            case STANDARD ->
            {
                return Tints.STANDARD;
            }
            case INTERMEDIATE ->
            {
                return Tints.INTERMEDIATE;
            }
            case ADVANCED ->
            {
                return Tints.ADVANCED;
            }
            case ULTRA ->
            {
                return Tints.ULTRA;
            }
            case EXTREME ->
            {
                return Tints.EXTREME;
            }
        }
        return Tints.EMPTY;
    }

    @Override
    public String getSerializedName()
    {
        return this.name;
    }
}
