package com.visnaa.gemstonepower.block.pipe.fluid;

import com.visnaa.gemstonepower.block.pipe.PipeBlock;
import com.visnaa.gemstonepower.client.render.Tintable;
import com.visnaa.gemstonepower.client.render.Tints;

public abstract class FluidPipeBlock extends PipeBlock implements Tintable
{
    private Tints color;

    public FluidPipeBlock(Properties properties, Tints color)
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
