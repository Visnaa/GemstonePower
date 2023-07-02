package com.visnaa.gemstonepower;

import com.mojang.logging.LogUtils;
import com.visnaa.gemstonepower.config.ClientConfig;
import com.visnaa.gemstonepower.config.CommonConfig;
import com.visnaa.gemstonepower.config.ConfigScreen;
import com.visnaa.gemstonepower.registry.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.Bindings;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Map;
import java.util.function.BiFunction;

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
        ModContainers.CONTAINERS.register(bus);
        ModRecipes.RECIPE_SERIALIZERS.register(bus);
        ModEntities.ENTITIES.register(bus);

        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> new ConfigScreen()));
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.CONFIG, "gemstonepower/gemstonepower-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.CONFIG, "gemstonepower/gemstonepower-common.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation getId(String name)
    {
        return new ResourceLocation(GemstonePower.MOD_ID, name);
    }
}
