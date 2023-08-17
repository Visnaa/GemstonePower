package com.visnaa.gemstonepower.block.pipe.fluid;

import com.visnaa.gemstonepower.block.entity.TickingBlockEntity;
import com.visnaa.gemstonepower.block.entity.pipe.fluid.GoldFluidPipeBE;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class GoldFluidPipeBlock extends FluidPipeBlock
{
    public GoldFluidPipeBlock(Properties properties)
    {
        super(properties, Tints.GOLD);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new GoldFluidPipeBE(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity)
    {
        return createTicker(level, blockEntity, ModBlockEntities.GOLD_FLUID_PIPE.get());
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> blockEntity, BlockEntityType<? extends GoldFluidPipeBE> pipe)
    {
        return level.isClientSide ? null : createTickerHelper(blockEntity, pipe, TickingBlockEntity::serverTick);
    }
}
