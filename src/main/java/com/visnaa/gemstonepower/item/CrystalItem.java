package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.config.ServerConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CrystalItem extends TintedItem
{
    private MobEffect effect;
    private boolean displayRealName = true;
    private int counter = -1;

    public CrystalItem(Properties properties, Tints tint)
    {
        super(properties, tint);
        this.effect = null;
    }

    public CrystalItem(Properties properties, MobEffect effect)
    {
        super(properties, Tints.NONE);
        this.effect = effect;
    }



    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected)
    {
        super.inventoryTick(stack, level, entity, slot, selected);
        displayRealName = ServerConfig.AWAKENED_PLAYERS.get().contains(entity.getStringUUID());
        if (effect == null)
            return;

        if (counter < 0)
            counter = level.getRandom().nextInt(2400, 12000);

        if (counter <= 0 && !level.isClientSide() && entity instanceof ServerPlayer player && !player.hasEffect(effect) && ServerConfig.AWAKENED_PLAYERS.get().contains(player.getStringUUID()))
        {
            player.addEffect(new MobEffectInstance(effect, level.getRandom().nextInt(100, 1200), level.getRandom().nextInt(1, effect.isBeneficial() ? 5 : 2)));
            counter = level.getRandom().nextInt(2400, 12000);
        }
        else if (!level.isClientSide() && entity instanceof ServerPlayer player && player.hasEffect(effect))
            counter = level.getRandom().nextInt(2400, 12000);
        else
            counter--;
    }

    @Override
    public Component getName(ItemStack stack)
    {
        if (!displayRealName)
            return Component.literal("Crystal");
        return super.getName(stack);
    }
}
