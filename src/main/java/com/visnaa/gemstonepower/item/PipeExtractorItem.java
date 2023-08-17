package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.block.entity.pipe.PipeBE;
import com.visnaa.gemstonepower.block.pipe.PipeBlock;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PipeExtractorItem extends Item
{
    public PipeExtractorItem(Properties properties)
    {
        super(properties);
    }

    @Override
    @NotNull
    public InteractionResult useOn(UseOnContext context)
    {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        if (state.getBlock() instanceof PipeBlock)
        {
            if (state.hasProperty(PipeBlock.EXTRACTS))
            {
                if (!state.getValue(PipeBlock.EXTRACTS))
                {
                    context.getLevel().playLocalSound(context.getClickedPos(), SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
                    if (!context.getLevel().isClientSide())
                    {
                        PipeBE pipe = (PipeBE) context.getLevel().getBlockEntity(context.getClickedPos());
                        pipe.getNetwork().destroy(pipe);
                        context.getItemInHand().shrink(1);
                        CompoundTag tag = context.getLevel().getBlockEntity(context.getClickedPos()).saveWithFullMetadata();
                        context.getLevel().setBlock(context.getClickedPos(), state.setValue(PipeBlock.EXTRACTS, true), 18);
                        context.getLevel().getBlockEntity(context.getClickedPos()).setRemoved();
                        context.getLevel().getBlockEntity(context.getClickedPos()).load(tag);
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.useOn(context);
    }
}
