package com.visnaa.gemstonepower.block.machine;

import com.mojang.serialization.MapCodec;
import com.visnaa.gemstonepower.block.entity.machine.GemstoneCellBE;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class GemstoneCellBlock extends MachineBlock<GemstoneCellBlock>
{
    public GemstoneCellBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new GemstoneCellBE(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity)
    {
        return MachineBlock.createTicker(level, blockEntity, ModBlockEntities.GEMSTONE_CELL.get());
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec()
    {
        return simpleCodec(GemstoneCellBlock::new);
    }
}