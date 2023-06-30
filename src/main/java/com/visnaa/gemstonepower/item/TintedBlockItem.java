package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.client.render.Tintable;
import com.visnaa.gemstonepower.client.render.Tints;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class TintedBlockItem extends BlockItem implements Tintable
{
    // Must be a HEX
    private int color;

    public TintedBlockItem(Block block, Properties properties, Tints colour)
    {
        super(block, properties);
        this.color = colour.getColor();
        Tints.TINTED_ITEMS.add(this);
    }

    public int getColor()
    {
        return color;
    }
}
