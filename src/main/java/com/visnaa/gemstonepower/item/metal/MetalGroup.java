package com.visnaa.gemstonepower.item.metal;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class MetalGroup
{
    private final boolean isAlloy;
    private final List<Item> alloyIngots;
    private final List<Block> alloyBlocks;
    private final List<Item> alloyNuggets;
    private final List<Item> alloyDusts;
    private final List<Item> alloyTinyPiles;
    private final int amount1;
    private final int amount2;
    private final int amount;
    private final Item ingot;
    private final Block block;
    private final Block oreStone;
    private final Block oreDeepslate;
    private final Item raw;
    private final Item nugget;
    private final Item dust;
    private final Item tinyPile;
    private final Item oreDust;
    private final NonNullList<Item> oreDustWashing;
    private final Item plate;
    private final Item rod;
    private final Item gear;
    private final Block wire;
    private final Block cable;

    public MetalGroup(Item ingot, Block block, Block oreStone, Block oreDeepslate, Item raw, Item nugget, Item dust, Item tinyPile, Item oreDust, NonNullList<Item> oreDustWashing, Item plate, Item rod, Item gear, Block wire, Block cable)
    {
        this.isAlloy = false;
        this.alloyIngots = null;
        this.alloyBlocks = null;
        this.alloyNuggets = null;
        this.alloyDusts = null;
        this.alloyTinyPiles = null;
        this.amount1 = 0;
        this.amount2 = 0;
        this.amount = 0;
        this.ingot = ingot;
        this.block = block;
        this.oreStone = oreStone;
        this.oreDeepslate = oreDeepslate;
        this.raw = raw;
        this.nugget = nugget;
        this.dust = dust;
        this.tinyPile = tinyPile;
        this.oreDust = oreDust;
        this.oreDustWashing = oreDustWashing;
        this.plate = plate;
        this.rod = rod;
        this.gear = gear;
        this.wire = wire;
        this.cable = cable;
    }

    public MetalGroup(List<Item> alloyIngots, List<Block> alloyBlocks, List<Item> alloyNuggets, List<Item> alloyDusts, List<Item> alloyTinyPiles, int amount1, int amount2, int amount, Item ingot, Block block, Item nugget, Item dust, Item tinyPile, Item plate, Item rod, Item gear, Block wire, Block cable)
    {
        this.isAlloy = true;
        this.alloyIngots = alloyIngots;
        this.alloyBlocks = alloyBlocks;
        this.alloyNuggets = alloyNuggets;
        this.alloyDusts = alloyDusts;
        this.alloyTinyPiles = alloyTinyPiles;
        this.amount1 = amount1;
        this.amount2 = amount2;
        this.amount = amount;
        this.ingot = ingot;
        this.block = block;
        this.oreStone = null;
        this.oreDeepslate = null;
        this.raw = null;
        this.nugget = nugget;
        this.dust = dust;
        this.tinyPile = tinyPile;
        this.oreDust = null;
        this.oreDustWashing = null;
        this.plate = plate;
        this.rod = rod;
        this.gear = gear;
        this.wire = wire;
        this.cable = cable;
    }

    public boolean isAlloy()
    {
        return isAlloy;
    }

    public List<Item> getAlloyIngots()
    {
        return alloyIngots;
    }

    public List<Block> getAlloyBlocks()
    {
        return alloyBlocks;
    }

    public List<Item> getAlloyNuggets()
    {
        return alloyNuggets;
    }

    public List<Item> getAlloyDusts()
    {
        return alloyDusts;
    }

    public List<Item> getAlloyTinyPiles()
    {
        return alloyTinyPiles;
    }

    public int getAmount1()
    {
        return amount1;
    }

    public int getAmount2()
    {
        return amount2;
    }

    public int getAmount()
    {
        return amount;
    }

    public Item getIngot()
    {
        return ingot;
    }

    public Block getBlock()
    {
        return block;
    }

    public Block getOreStone()
    {
        return oreStone;
    }

    public Block getOreDeepslate()
    {
        return oreDeepslate;
    }

    public Item getRaw()
    {
        return raw;
    }

    public Item getNugget()
    {
        return nugget;
    }

    public Item getDust()
    {
        return dust;
    }

    public Item getTinyPile()
    {
        return tinyPile;
    }

    public Item getOreDust()
    {
        return oreDust;
    }

    public NonNullList<Item> getOreDustWashing()
    {
        return oreDustWashing;
    }

    public Item getPlate()
    {
        return plate;
    }

    public Item getRod()
    {
        return rod;
    }

    public Item getGear()
    {
        return gear;
    }

    public Block getWire()
    {
        return wire;
    }

    public Block getCable()
    {
        return cable;
    }
}
