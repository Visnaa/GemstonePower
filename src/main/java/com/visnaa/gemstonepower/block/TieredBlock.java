package com.visnaa.gemstonepower.block;

import com.visnaa.gemstonepower.util.Tier;
import net.minecraft.world.level.block.state.BlockState;

public interface TieredBlock<T extends TieredBlock<T>>
{
    default void registerColors(T block)
    {
        Tier.BLOCKS.add(block);
    }

    default Tier getTier(BlockState state)
    {
        return state.getValue(Tier.TIER);
    }
}
