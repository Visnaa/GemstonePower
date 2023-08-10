package com.visnaa.gemstonepower.block.entity.pipe.item;

import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class PlatinumItemPipeBE extends ItemPipeBE
{
    public PlatinumItemPipeBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.PLATINUM_ITEM_PIPE.get(), pos, state);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, PlatinumItemPipeBE blockEntity)
    {
        blockEntity.updateConnections(level, pos, state, PlatinumItemPipeBE.class);
        blockEntity.refreshNetwork(level, pos, state, PlatinumItemPipeBE.class);
        blockEntity.refreshOutputs(level, pos, state);
        blockEntity.distributeItems(level, pos, state);
    }

    @Override
    public int getTransfer()
    {
        return 1;
    }
}