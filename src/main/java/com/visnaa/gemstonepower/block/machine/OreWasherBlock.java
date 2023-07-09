package com.visnaa.gemstonepower.block.machine;

import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import com.visnaa.gemstonepower.block.entity.machine.OreWasherBE;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class OreWasherBlock extends MachineBlock<OreWasherBlock>
{
    public OreWasherBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new OreWasherBE(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity)
    {
        return createTicker(level, blockEntity, ModBlockEntities.ORE_WASHER.get());
    }

    @Nullable
    @Override
    protected <B extends BlockEntity> BlockEntityTicker<B> createTicker(Level level, BlockEntityType<B> blockEntity, BlockEntityType<? extends MachineBE<?>> machine)
    {
        return createTickerHelper(blockEntity, machine, OreWasherBE::serverTick);
    }
}