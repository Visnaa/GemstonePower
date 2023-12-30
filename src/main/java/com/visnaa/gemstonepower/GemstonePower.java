package com.visnaa.gemstonepower;

import com.mojang.logging.LogUtils;
import com.visnaa.gemstonepower.config.ClientConfig;
import com.visnaa.gemstonepower.config.ServerConfig;
import com.visnaa.gemstonepower.init.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.net.SocketException;

@Mod(GemstonePower.MOD_ID)
public class GemstonePower
{
    public static final String MOD_ID = "gemstonepower";
    public static final Logger LOGGER = LogUtils.getLogger();

    public GemstonePower()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModArmors.ARMOUR_PIECES.register(bus);
        ModTools.TOOLS.register(bus);
        ModFeatures.FEATURES.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModMenus.CONTAINERS.register(bus);
        ModRecipes.RECIPE_SERIALIZERS.register(bus);
        ModEntities.ENTITIES.register(bus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.CONFIG, "gemstonepower/gemstonepower-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.CONFIG, "gemstonepower-server.toml");
    }

    public static ResourceLocation getId(String name)
    {
        return new ResourceLocation(GemstonePower.MOD_ID, name);
    }
}
