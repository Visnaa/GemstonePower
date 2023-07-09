package com.visnaa.gemstonepower.block.machine;

import com.visnaa.gemstonepower.block.entity.machine.ExtractorBE;
import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class ExtractorBlock extends MachineBlock<ExtractorBlock>
{
    public ExtractorBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new ExtractorBE(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity)
    {
        return createTicker(level, blockEntity, ModBlockEntities.EXTRACTOR.get());
    }

    @Nullable
    @Override
    protected <B extends BlockEntity> BlockEntityTicker<B> createTicker(Level level, BlockEntityType<B> blockEntity, BlockEntityType<? extends MachineBE<?>> machine)
    {
        return createTickerHelper(blockEntity, machine, ExtractorBE::serverTick);
    }
}