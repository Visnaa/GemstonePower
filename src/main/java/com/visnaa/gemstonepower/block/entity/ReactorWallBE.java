package com.visnaa.gemstonepower.block.entity;

import com.visnaa.gemstonepower.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ReactorWallBE extends BlockEntity
{
    private FissionReactorBE reactor;

    public ReactorWallBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.REACTOR_WALL.get(), pos, state);
    }

    public void broken(Level level)
    {
        if (reactor != null)
            reactor.broken(level);
    }

    public void setReactor(FissionReactorBE reactor)
    {
        this.reactor = reactor;
    }
}
