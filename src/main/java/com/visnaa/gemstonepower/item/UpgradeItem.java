package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.block.TieredBlock;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.util.Tier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

public class UpgradeItem extends TintedItem
{
    private final Tier tier;

    public UpgradeItem(Properties properties, Tier tier, Tints color)
    {
        super(properties, color);
        this.tier = tier;
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        if (state.getBlock() instanceof TieredBlock<?>)
        {
            if (state.hasProperty(Tier.TIER))
            {
                Tier entityTier = state.getValue(Tier.TIER);
                if (entityTier.id() + 1 == this.tier.id())
                {
                    context.getLevel().playLocalSound(context.getClickedPos(), SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
                    if (!context.getLevel().isClientSide())
                    {
                        context.getItemInHand().shrink(1);
                        CompoundTag tag = context.getLevel().getBlockEntity(context.getClickedPos()).saveWithFullMetadata();
                        context.getLevel().setBlock(context.getClickedPos(), state.setValue(Tier.TIER, this.tier), 18);
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
