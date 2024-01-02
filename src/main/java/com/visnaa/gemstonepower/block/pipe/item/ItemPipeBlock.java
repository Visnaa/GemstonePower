package com.visnaa.gemstonepower.block.pipe.item;

import com.visnaa.gemstonepower.block.pipe.PipeBlock;
import com.visnaa.gemstonepower.client.render.Tintable;
import com.visnaa.gemstonepower.client.render.Tints;

public abstract class ItemPipeBlock extends PipeBlock implements Tintable
{
    private Tints color;

    public ItemPipeBlock(Properties properties, Tints color)
    {
        super(properties);
        Tints.TINTED_BLOCKS.add(this);
        this.color = color;
    }

    @Override
    public int getColor()
    {
        return color.getColor();
    }
}
