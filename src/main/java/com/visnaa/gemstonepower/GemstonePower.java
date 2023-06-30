package com.visnaa.gemstonepower;

import com.mojang.logging.LogUtils;
import com.visnaa.gemstonepower.registry.*;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.Bindings;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Map;

@Mod(GemstonePower.MOD_ID)
public class GemstonePower
{
    public static final String MOD_ID = "gemstonepower";
    public static final Logger LOGGER = LogUtils.getLogger();

    public GemstonePower()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        IEventBus forgeBus = Bindings.getForgeBus().get();
        forgeBus.addListener(this::serverStart);

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModArmors.ARMOUR_PIECES.register(bus);
        ModTools.TOOLS.register(bus);
        ModFeatures.FEATURES.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModContainers.CONTAINERS.register(bus);
        ModRecipes.RECIPE_SERIALIZERS.register(bus);
        ModEntities.ENTITIES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation getId(String name)
    {
        return new ResourceLocation(GemstonePower.MOD_ID, name);
    }

    private void serverStart(ServerAboutToStartEvent event)
    {
        RegistryAccess access = event.getServer().registryAccess();
        for (Map.Entry<ResourceKey<LevelStem>, LevelStem> entry : event.getServer().registryAccess().registryOrThrow(Registries.LEVEL_STEM).entrySet())
        {
            if (entry.getKey() == LevelStem.OVERWORLD)
            {
                LOGGER.info("GemstonePower generating biomes for overworld");

                ChunkGenerator generator = entry.getValue().generator();
                BiomeSource source = generator.getBiomeSource();

                source.possibleBiomes();
            }
        }
    }
}
