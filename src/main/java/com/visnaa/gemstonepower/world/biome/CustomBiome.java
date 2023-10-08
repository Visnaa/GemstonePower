package com.visnaa.gemstonepower.world.biome;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate.Parameter;
import net.minecraft.world.level.biome.Climate.ParameterPoint;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public record CustomBiome(ResourceKey<Biome> key, Block surface, Block underground, Block underwater, Parameter temperature, Parameter humidity, Parameter continentalness, Parameter erosion, Parameter depth, Parameter weirdness, long offset)
{
    public ParameterPoint getParameterPoint()
    {
        return new ParameterPoint(temperature(), humidity(), continentalness(), erosion(), depth(), weirdness(), offset());
    }

    public SurfaceRules.RuleSource getSurfaceRule()
    {
        return SurfaceRules.ifTrue(SurfaceRules.isBiome(key()), SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), SurfaceRules.state(surface().defaultBlockState())), SurfaceRules.state(underwater().defaultBlockState()))), SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), SurfaceRules.state(underground().defaultBlockState())))));
    }

    public static class Builder
    {
        private final Block surface;
        private final Block underground;
        private final Block underwater;
        private Parameter temperature = Parameter.span(-0.5F, 0.5F);
        private Parameter humidity = Parameter.span(-0.5F, 0.5F);
        private Parameter continentalness = Parameter.span(0.3F, 1F);
        private Parameter erosion = Parameter.span(-0.5F, 0.5F);
        private Parameter depth = Parameter.point(0F);
        private Parameter weirdness = Parameter.span(-1F, 1F);
        private long offset = 0;

        public Builder(Block surface, Block underground, Block underwater)
        {
            this.surface = surface;
            this.underground = underground;
            this.underwater = underwater;
        }

        public Builder temperature(float min, float max)
        {
            this.temperature = parameter(min, max);
            return this;
        }

        public Builder humidity(float min, float max)
        {
            this.humidity = parameter(min, max);
            return this;
        }

        public Builder continentalness(float min, float max)
        {
            this.continentalness = parameter(min, max);
            return this;
        }

        public Builder erosion(float min, float max)
        {
            this.erosion = parameter(min, max);
            return this;
        }

        public Builder depth(float min, float max)
        {
            this.depth = parameter(min, max);
            return this;
        }

        public Builder weirdness(float min, float max)
        {
            this.weirdness = parameter(min, max);
            return this;
        }

        public Builder offset(long offset)
        {
            this.offset = offset;
            return this;
        }

        public Parameter parameter(float min, float max)
        {
            if (min == max)
                return Parameter.point(min);
            return Parameter.span(min, max);
        }

        public CustomBiome build(ResourceKey<Biome> key)
        {
            return new CustomBiome(key, surface, underground, underwater, temperature, humidity, continentalness, erosion, depth, weirdness, offset);
        }
    }
}
