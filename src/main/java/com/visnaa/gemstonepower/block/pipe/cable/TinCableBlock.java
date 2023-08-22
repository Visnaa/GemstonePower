package com.visnaa.gemstonepower.block.pipe.cable;

import com.visnaa.gemstonepower.block.entity.pipe.cable.TinCableBE;
import com.visnaa.gemstonepower.block.machine.MachineBlock;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class TinCableBlock extends CableBlock
{
    public TinCableBlock(Properties properties, boolean isIsolated)
    {
        super(properties, isIsolated, Tints.TIN);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new TinCableBE(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity)
    {
        return MachineBlock.createTicker(level, blockEntity, ModBlockEntities.TIN_CABLE.get());
    }
}
