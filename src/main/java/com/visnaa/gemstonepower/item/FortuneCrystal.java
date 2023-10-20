package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.config.ServerConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class FortuneCrystal extends Item
{
    public FortuneCrystal(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        long lastUse = stack.getTag().getLong("LastUse");
        if (ServerConfig.AWAKENED_PLAYERS.get().contains(player.getStringUUID()) && lastUse <= level.getGameTime())
        {
            if (level.isClientSide())
            {
                level.playLocalSound(player.blockPosition(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS , 1, 1.5F, false);
                for (int i = 0; i < 360; i++)
                    level.addParticle(ParticleTypes.SCRAPE, player.position().x(), player.position().y(), player.position().z(), level.getRandom().nextGaussian() * 0.5F, Math.sin(i) * 0.5F, level.getRandom().nextGaussian() * 0.5F);
            }
            else
            {
                HashMap<MobEffect, Integer> effects = new HashMap<>();
                int count = level.getRandom().nextIntBetweenInclusive(1, 5);
                for (int i = 0; i < count; i++)
                {
                    int rnd = level.getRandom().nextIntBetweenInclusive(0, 16);
                    switch (rnd)
                    {
                        case 1 -> effects.put(MobEffects.SATURATION, 2);
                        case 2 -> effects.put(MobEffects.HEALTH_BOOST, 4);
                        case 3 -> effects.put(MobEffects.WATER_BREATHING, 0);
                        case 4 -> effects.put(MobEffects.CONDUIT_POWER, 0);
                        case 5 -> effects.put(MobEffects.DAMAGE_BOOST, 2);
                        case 6 -> effects.put(MobEffects.REGENERATION, 4);
                        case 7 -> effects.put(MobEffects.ABSORPTION, 4);
                        case 8 -> effects.put(MobEffects.DAMAGE_RESISTANCE, 2);
                        case 9 -> effects.put(MobEffects.DOLPHINS_GRACE, 0);
                        case 10 -> effects.put(MobEffects.DIG_SPEED, 4);
                        case 11 -> effects.put(MobEffects.FIRE_RESISTANCE, 0);
                        case 12 -> effects.put(MobEffects.INVISIBILITY, 0);
                        case 13 -> effects.put(MobEffects.JUMP, 2);
                        case 14 -> effects.put(MobEffects.LUCK, 4);
                        case 15 -> effects.put(MobEffects.MOVEMENT_SPEED, 2);
                        case 16 -> effects.put(MobEffects.NIGHT_VISION, 0);
                        default -> effects.put(MobEffects.SLOW_FALLING, 0);
                    }
                }
                stack.getOrCreateTag().putLong("LastUse", level.getGameTime() + ServerConfig.FORTUNE_CRYSTAL_COOLDOWN.get());
                System.out.println(level.getGameTime() + ServerConfig.FORTUNE_CRYSTAL_COOLDOWN.get());
                for (MobEffect effect : effects.keySet())
                    player.addEffect(new MobEffectInstance(effect, ServerConfig.FORTUNE_CRYSTAL_COOLDOWN.get() / 2, effects.get(effect)));
                if (level.getRandom().nextIntBetweenInclusive(1, 1000) == 1)
                    player.getItemInHand(hand).shrink(1);
            }
            return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
        }
        if (!level.isClientSide())
        {
            if (ServerConfig.AWAKENED_PLAYERS.get().contains(player.getStringUUID()))
                player.sendSystemMessage(Component.literal("You can't use this item now").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
            else
                player.sendSystemMessage(Component.literal("You need to be awakened to use this item").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
        }
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag)
    {
        super.appendHoverText(stack, level, components, flag);
        if (level == null)
            return;
        if (!stack.getOrCreateTag().contains("LastUse"))
            stack.getOrCreateTag().putLong("LastUse", level.getGameTime());
        if (stack.getTag().getLong("LastUse") > level.getGameTime())
            components.add(Component.literal("Cooldown: " + ((int) ((stack.getTag().getLong("LastUse") - level.getGameTime()) / 20)) + " seconds").withStyle(ChatFormatting.GOLD));
        else if (stack.getTag().getLong("LastUse") <= level.getGameTime())
            components.add(Component.literal("Ready to use").withStyle(ChatFormatting.GREEN));
    }
}
