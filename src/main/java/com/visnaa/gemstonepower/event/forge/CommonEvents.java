package com.visnaa.gemstonepower.event.forge;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModBiomes;
import com.visnaa.gemstonepower.world.biome.CustomBiome;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.FeatureSorter;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = GemstonePower.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents
{
    @SubscribeEvent
    public static void serverAboutToStart(ServerAboutToStartEvent event)
    {
        MinecraftServer server = event.getServer();
        Registry<DimensionType> dimensionTypeRegistry = server.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE);
        Registry<LevelStem> levelStemTypeRegistry = server.registryAccess().registryOrThrow(Registries.LEVEL_STEM);
        Registry<Biome> biomeRegistry = server.registryAccess().registryOrThrow(Registries.BIOME);
        for (LevelStem levelStem : levelStemTypeRegistry.stream().toList())
        {
            DimensionType dimensionType = levelStem.type().value();
            if (dimensionType == dimensionTypeRegistry.getOrThrow(BuiltinDimensionTypes.OVERWORLD))
            {
                GemstonePower.LOGGER.debug("Adding custom overworld biomes");
                ChunkGenerator chunkGenerator = levelStem.generator();
                if (chunkGenerator.getBiomeSource() instanceof MultiNoiseBiomeSource noiseSource)
                {
                    List<Pair<Climate.ParameterPoint, Holder<Biome>>> parameters = new ArrayList<>(noiseSource.parameters().values());
                    for (CustomBiome biome : ModBiomes.OVERWORLD_BIOMES)
                    {
                        parameters.add(new Pair<>(biome.getParameterPoint(), biomeRegistry.getHolderOrThrow(biome.key())));
                        parameters.add(new Pair<>(biome.getParameterPoint(), biomeRegistry.getHolderOrThrow(biome.key())));
                    }
                    chunkGenerator.biomeSource = MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(parameters));
                    chunkGenerator.featuresPerStep = Suppliers.memoize(() -> FeatureSorter.buildFeaturesPerStep(List.copyOf(chunkGenerator.biomeSource.possibleBiomes()), biome -> chunkGenerator.generationSettingsGetter.apply(biome).features(), true));
                }
                if (chunkGenerator instanceof NoiseBasedChunkGenerator noiseGenerator)
                {
                    NoiseGeneratorSettings noiseGeneratorSettings = noiseGenerator.generatorSettings().value();
                    SurfaceRules.RuleSource currentRuleSource = noiseGeneratorSettings.surfaceRule();
                    if (currentRuleSource instanceof SurfaceRules.SequenceRuleSource sequenceRuleSource)
                    {
                        List<SurfaceRules.RuleSource> surfaceRules = new ArrayList<>(sequenceRuleSource.sequence());
                        for (CustomBiome biome : ModBiomes.OVERWORLD_BIOMES)
                            surfaceRules.add(1, biome.getSurfaceRule());

                        NoiseGeneratorSettings moddedNoiseGeneratorSettings = new NoiseGeneratorSettings(
                                noiseGeneratorSettings.noiseSettings(),
                                noiseGeneratorSettings.defaultBlock(),
                                noiseGeneratorSettings.defaultFluid(),
                                noiseGeneratorSettings.noiseRouter(),
                                SurfaceRules.sequence(surfaceRules.toArray(SurfaceRules.RuleSource[]::new)),
                                noiseGeneratorSettings.spawnTarget(),
                                noiseGeneratorSettings.seaLevel(),
                                noiseGeneratorSettings.disableMobGeneration(),
                                noiseGeneratorSettings.aquifersEnabled(),
                                noiseGeneratorSettings.oreVeinsEnabled(),
                                noiseGeneratorSettings.useLegacyRandomSource());
                        noiseGenerator.settings = new Holder.Direct<>(moddedNoiseGeneratorSettings);
                    }
                }
            }
        }
    }
}
