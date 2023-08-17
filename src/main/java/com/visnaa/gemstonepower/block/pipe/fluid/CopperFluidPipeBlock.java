package com.visnaa.gemstonepower.block.pipe.fluid;

import com.visnaa.gemstonepower.block.entity.TickingBlockEntity;
import com.visnaa.gemstonepower.block.entity.pipe.fluid.CopperFluidPipeBE;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CopperFluidPipeBlock extends FluidPipeBlock
{
    public CopperFluidPipeBlock(Properties properties)
    {
        super(properties, Tints.COPPER);
    }

    @Override
    public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new CopperFluidPipeBE(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity)
    {
        return createTicker(level, blockEntity, ModBlockEntities.COPPER_FLUID_PIPE.get());
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> blockEntity, BlockEntityType<? extends CopperFluidPipeBE> pipe)
    {
        return level.isClientSide ? null : createTickerHelper(blockEntity, pipe, TickingBlockEntity::serverTick);
    }
}
