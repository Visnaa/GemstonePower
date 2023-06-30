package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.registry.ModBlocks;
import com.visnaa.gemstonepower.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class TreeTapItem extends Item
{
    public TreeTapItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if (state.is(ModBlocks.RESIN_OAK_LOG.get()))
        {
            level.playLocalSound(pos, SoundEvents.SLIME_BLOCK_PLACE, SoundSource.MASTER, 1.0F, 1.0F, false);
            level.setBlock(pos, Blocks.OAK_LOG.withPropertiesOf(state), 11);
            Containers.dropContents(level, pos, NonNullList.withSize(1, new ItemStack(ModItems.RESIN.get(), level.getRandom().nextInt(1, 4))));
            context.getItemInHand().hurtAndBreak(1, player, p -> p.broadcastBreakEvent(context.getHand()));
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return InteractionResult.FAIL;
    }
}
