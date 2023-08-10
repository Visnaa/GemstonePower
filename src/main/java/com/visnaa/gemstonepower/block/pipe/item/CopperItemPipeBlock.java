package com.visnaa.gemstonepower.block.pipe.item;

import com.visnaa.gemstonepower.block.entity.pipe.item.CopperItemPipeBE;
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

public class CopperItemPipeBlock extends ItemPipeBlock
{
    public CopperItemPipeBlock(Properties properties)
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
        return new CopperItemPipeBE(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity)
    {
        return createTicker(level, blockEntity, ModBlockEntities.COPPER_ITEM_PIPE.get());
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> blockEntity, BlockEntityType<? extends CopperItemPipeBE> pipe)
    {
        return level.isClientSide ? null : createTickerHelper(blockEntity, pipe, CopperItemPipeBE::serverTick);
    }
}
