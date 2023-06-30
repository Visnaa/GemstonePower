package com.visnaa.gemstonepower.item.tool;

import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.registry.ModItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public enum ToolMaterial implements Tier
{
    GEMSTONE(Tints.EMPTY, 4, 1851, 13.0F, 3.5F, 18, () ->
            Ingredient.of(ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get()),
            Tags.Blocks.NEEDS_NETHERITE_TOOL),
    COPPER(Tints.COPPER, 2, 220, 5.5F, 1.8F, 18, () ->
            Ingredient.of(Items.COPPER_INGOT),
            BlockTags.NEEDS_IRON_TOOL),
    ALUMINUM(Tints.ALUMINUM, 2, 220, 5.5F, 1.8F, 18, () ->
            Ingredient.of(ModItems.ALUMINUM_INGOT.get()),
            BlockTags.NEEDS_IRON_TOOL),
    BRONZE(Tints.BRONZE, 2, 220, 5.5F, 1.8F, 18, () ->
            Ingredient.of(ModItems.BRONZE_INGOT.get()),
            BlockTags.NEEDS_IRON_TOOL),
    SILVER(Tints.SILVER, 2, 220, 5.5F, 1.8F, 18, () ->
            Ingredient.of(ModItems.SILVER_INGOT.get()),
            BlockTags.NEEDS_IRON_TOOL),
    INVAR(Tints.INVAR, 2, 220, 5.5F, 1.8F, 18, () ->
            Ingredient.of(ModItems.INVAR_INGOT.get()),
            BlockTags.NEEDS_IRON_TOOL),
    STEEL(Tints.STEEL, 2, 220, 5.5F, 1.8F, 18, () ->
            Ingredient.of(ModItems.STEEL_INGOT.get()),
            BlockTags.NEEDS_IRON_TOOL);

    private final Tints material;
    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final TagKey<Block> tag;

    ToolMaterial(Tints material, int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient, TagKey<Block> tag)
    {
        this.material = material;
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
        this.tag = tag;
    }

    public Tints getMaterial()
    {
        return material;
    }

    public int getUses()
    {
        return this.uses;
    }

    public float getSpeed()
    {
        return this.speed;
    }

    public float getAttackDamageBonus()
    {
        return this.damage;
    }

    public int getLevel()
    {
        return this.level;
    }

    public int getEnchantmentValue()
    {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient()
    {
        return this.repairIngredient.get();
    }

    @Nullable
    public TagKey<Block> getTag()
    {
        return tag;
    }
}
