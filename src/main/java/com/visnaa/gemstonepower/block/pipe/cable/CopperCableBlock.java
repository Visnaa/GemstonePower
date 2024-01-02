package com.visnaa.gemstonepower.block.pipe.cable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.visnaa.gemstonepower.block.entity.pipe.cable.CopperCableBE;
import com.visnaa.gemstonepower.block.machine.MachineBlock;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CopperCableBlock extends CableBlock
{
    public CopperCableBlock(Properties properties, boolean isIsolated)
    {
        super(properties, isIsolated, Tints.COPPER);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new CopperCableBE(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity)
    {
        return MachineBlock.createTicker(level, blockEntity, ModBlockEntities.COPPER_CABLE.get());
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec()
    {
        return RecordCodecBuilder.mapCodec((builder) -> builder.group(
                propertiesCodec(),
                Codec.BOOL.fieldOf("type").forGetter((be) -> isIsolated())
        ).apply(builder, CopperCableBlock::new));
    }
}
