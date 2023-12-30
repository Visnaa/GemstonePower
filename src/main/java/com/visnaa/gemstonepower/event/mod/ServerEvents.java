package com.visnaa.gemstonepower.event.mod;

import com.visnaa.gemstonepower.GemstonePower;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

@Mod.EventBusSubscriber(value = Dist.DEDICATED_SERVER, modid = GemstonePower.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ServerEvents
{
    @SubscribeEvent
    public static void serverSetup(FMLDedicatedServerSetupEvent event)
    {
        GemstonePower.LOGGER.info("Hello from Gemstone Power!");
    }
}
