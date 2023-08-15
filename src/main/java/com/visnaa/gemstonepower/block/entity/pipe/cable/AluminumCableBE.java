package com.visnaa.gemstonepower.block.entity.pipe.cable;

import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AluminumCableBE extends CableBE
{
    public AluminumCableBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ALUMINUM_CABLE.get(), pos, state, ServerConfig.ENERGY_TRANSFER_RATE.get());
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state)
    {
        refreshNetwork(level, pos, state, AluminumCableBE.class);
        updateConnections(level, pos, state, AluminumCableBE.class);
        super.tick(level, pos, state);
    }
}
