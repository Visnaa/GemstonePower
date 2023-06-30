package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.registry.ModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EntityTagGenerator extends IntrinsicHolderTagsProvider<EntityType<?>>
{
    public EntityTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, Registries.ENTITY_TYPE, completableFuture, (type) -> type.builtInRegistryHolder().key(), GemstonePower.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        this.tag(EntityTypeTags.ARROWS)
                .add(ModEntities.RUBY_ARROW.get())
                .add(ModEntities.SAPPHIRE_ARROW.get())
                .add(ModEntities.AQUAMARINE_ARROW.get())
                .add(ModEntities.JADE_ARROW.get())
                .add(ModEntities.OPAL_ARROW.get())
                .add(ModEntities.YELLOW_DIAMOND_ARROW.get())
                .add(ModEntities.AMBER_ARROW.get())
                .add(ModEntities.TOPAZ_ARROW.get())
                .add(ModEntities.BERYLLIUM_ARROW.get())
                .add(ModEntities.BIXBIT_ARROW.get())
                .add(ModEntities.MALACHITE_ARROW.get())
                .add(ModEntities.ONYX_ARROW.get())
                .add(ModEntities.PERIDOT_ARROW.get())
                .add(ModEntities.MOON_STONE_ARROW.get())
                .add(ModEntities.SUN_STONE_ARROW.get())
                .add(ModEntities.CITRINE_ARROW.get())
                .add(ModEntities.DOLOMITE_ARROW.get())
                .add(ModEntities.TANZANITE_ARROW.get());
    }
}
