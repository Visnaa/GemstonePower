package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.block.entity.pipe.item.BaseItemPipeBE;
import com.visnaa.gemstonepower.block.pipe.item.BaseItemPipeBlock;
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
        if (state.getBlock() instanceof BaseItemPipeBlock)
        {
            if (state.hasProperty(BaseItemPipeBlock.EXTRACTS))
            {
                if (!state.getValue(BaseItemPipeBlock.EXTRACTS))
                {
                    context.getLevel().playLocalSound(context.getClickedPos(), SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
                    if (!context.getLevel().isClientSide())
                    {
                        BaseItemPipeBE pipe = (BaseItemPipeBE) context.getLevel().getBlockEntity(context.getClickedPos());
                        pipe.network.destroy(pipe);
                        context.getItemInHand().shrink(1);
                        CompoundTag tag = context.getLevel().getBlockEntity(context.getClickedPos()).saveWithFullMetadata();
                        context.getLevel().setBlock(context.getClickedPos(), state.setValue(BaseItemPipeBlock.EXTRACTS, true), 18);
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
