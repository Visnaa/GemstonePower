package com.visnaa.gemstonepower.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.visnaa.gemstonepower.block.entity.ReactorFrameBE;
import com.visnaa.gemstonepower.client.render.Tintable;
import com.visnaa.gemstonepower.client.render.Tints;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ReactorFrameBlock extends BaseEntityBlock implements Tintable
{
    private final ReactorFrameBE.Type type;

    public ReactorFrameBlock(Properties properties, ReactorFrameBE.Type type)
    {
        super(properties);
        this.type = type;
        Tints.TINTED_BLOCKS.add(this);
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
        return new ReactorFrameBE(pos, state, type);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean flag)
    {
        if (level.getBlockEntity(pos) instanceof ReactorFrameBE frame)
            frame.broken(level);
        super.onRemove(state, level, pos, state1, flag);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec()
    {
        return RecordCodecBuilder.mapCodec((builder) -> builder.group(
                propertiesCodec(),
                Codec.STRING.fieldOf("type").forGetter((be) -> this.type.getName())
        ).apply(builder, (properties, type) -> new ReactorFrameBlock(properties, ReactorFrameBE.Type.getByName(type))));
    }
}
