package com.visnaa.gemstonepower.block;

import com.visnaa.gemstonepower.client.render.Tintable;
import com.visnaa.gemstonepower.client.render.Tints;
import net.minecraft.world.level.block.Block;

public class TintedBlock extends Block implements Tintable
{
    private final int colour;

    public TintedBlock(Properties properties, Tints colour)
    {
        super(properties);
        this.colour = colour.getColor();
        Tints.TINTED_BLOCKS.add(this);
    }

    public int getColor()
    {
        return colour;
    }
}
