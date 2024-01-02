package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.config.ServerConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class AwakeningStoneItem extends Item
{
    private boolean grantsKnowledge;

    public AwakeningStoneItem(Properties properties, boolean grantsKnowledge)
    {
        super(properties);
        this.grantsKnowledge = grantsKnowledge;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        if (ServerConfig.AWAKENED_PLAYERS.get().contains(player.getStringUUID()) && !grantsKnowledge)
        {
            if (!level.isClientSide())
            {
                List<String> list = new ArrayList<>();
                for (Object o : ServerConfig.AWAKENED_PLAYERS.get())
                    if (o instanceof String uuid)
                        list.add(uuid);
                list.remove(player.getStringUUID());
                ServerConfig.AWAKENED_PLAYERS.set(list);
                ServerConfig.AWAKENED_PLAYERS.save();
                player.getItemInHand(hand).shrink(1);
                player.sendSystemMessage(Component.literal("You have been unawakened").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.ITALIC));
            }
            else
            {
                for (int i = 0; i < 360; i++)
                    level.addParticle(ParticleTypes.FIREWORK, player.position().x(), player.position().y(), player.position().z(), level.getRandom().nextGaussian() * 0.5F, Math.sin(i) * 0.5F, level.getRandom().nextGaussian() * 0.5F);
                level.playLocalSound(player.blockPosition(), SoundEvents.WITHER_DEATH, SoundSource.AMBIENT, 1, 2F, true);
            }
            return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
        }
        else if (!ServerConfig.AWAKENED_PLAYERS.get().contains(player.getStringUUID()) && grantsKnowledge)
        {
            if (!level.isClientSide())
            {
                List<String> newList = new ArrayList<>();
                for (Object o : ServerConfig.AWAKENED_PLAYERS.get())
                    if (o instanceof String uuid)
                        newList.add(uuid);
                newList.add(player.getStringUUID());
                ServerConfig.AWAKENED_PLAYERS.set(newList);
                ServerConfig.AWAKENED_PLAYERS.save();
                player.getItemInHand(hand).shrink(1);
                player.sendSystemMessage(Component.literal("You have been awakened").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));
            } else
            {
                for (int i = 0; i < 360; i++)
                    level.addParticle(ParticleTypes.FIREWORK, player.position().x(), player.position().y(), player.position().z(), level.getRandom().nextGaussian() * 0.5F, Math.sin(i) * 0.5F, level.getRandom().nextGaussian() * 0.5F);
                level.playLocalSound(player.blockPosition(), SoundEvents.ENDER_DRAGON_DEATH, SoundSource.AMBIENT, 1, 2F, true);
            }
            return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
        }
        return super.use(level, player, hand);
    }
}
