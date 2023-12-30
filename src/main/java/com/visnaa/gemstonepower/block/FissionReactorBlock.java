package com.visnaa.gemstonepower.block;

import com.mojang.serialization.MapCodec;
import com.visnaa.gemstonepower.block.entity.FissionReactorBE;
import com.visnaa.gemstonepower.block.machine.MachineBlock;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class FissionReactorBlock extends MachineBlock<FissionReactorBlock>
{
    public FissionReactorBlock(Properties properties)
    {
        super(properties, false);
        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
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
        return new FissionReactorBE(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return MachineBlock.createTicker(level, type, ModBlockEntities.FISSION_REACTOR.get());
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean flag)
    {
        if (level.getBlockEntity(pos) instanceof FissionReactorBE reactor)
            reactor.broken(level);
        super.onRemove(state, level, pos, state1, flag);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec()
    {
        return simpleCodec(FissionReactorBlock::new);
    }
}
