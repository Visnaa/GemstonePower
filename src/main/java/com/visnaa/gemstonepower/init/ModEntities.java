package com.visnaa.gemstonepower.init;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.entity.projectile.CrystalArrow;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, GemstonePower.MOD_ID);

    public static final Supplier<EntityType<CrystalArrow>> RUBY_ARROW = ENTITIES.register("ruby_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.RUBY_ARROW.get(), Tints.RUBY, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("ruby_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> SAPPHIRE_ARROW = ENTITIES.register("sapphire_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.SAPPHIRE_ARROW.get(), Tints.SAPPHIRE, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("sapphire_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> AQUAMARINE_ARROW = ENTITIES.register("aquamarine_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.AQUAMARINE_ARROW.get(), Tints.AQUAMARINE, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("aquamarine_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> JADE_ARROW = ENTITIES.register("jade_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.JADE_ARROW.get(), Tints.JADE, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("jade_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> OPAL_ARROW = ENTITIES.register("opal_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.OPAL_ARROW.get(), Tints.OPAL, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("opal_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> YELLOW_DIAMOND_ARROW = ENTITIES.register("yellow_diamond_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.YELLOW_DIAMOND_ARROW.get(), Tints.YELLOW_DIAMOND, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("yellow_diamond_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> AMBER_ARROW = ENTITIES.register("amber_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.AMBER_ARROW.get(), Tints.AMBER, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("amber_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> TOPAZ_ARROW = ENTITIES.register("topaz_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.TOPAZ_ARROW.get(), Tints.TOPAZ, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("topaz_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> BERYLLIUM_ARROW = ENTITIES.register("beryllium_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.BERYLLIUM_ARROW.get(), Tints.BERYLLIUM, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("beryllium_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> BIXBIT_ARROW = ENTITIES.register("bixbit_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.BIXBIT_ARROW.get(), Tints.BIXBIT, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("bixbit_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> MALACHITE_ARROW = ENTITIES.register("malachite_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.MALACHITE_ARROW.get(), Tints.MALACHITE, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("malachite_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> ONYX_ARROW = ENTITIES.register("onyx_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.ONYX_ARROW.get(), Tints.ONYX, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("onyx_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> PERIDOT_ARROW = ENTITIES.register("peridot_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.PERIDOT_ARROW.get(), Tints.PERIDOT, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("peridot_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> MOON_STONE_ARROW = ENTITIES.register("moon_stone_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.MOON_STONE_ARROW.get(), Tints.MOON_STONE, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("moon_stone_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> SUN_STONE_ARROW = ENTITIES.register("sun_stone_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.SUN_STONE_ARROW.get(), Tints.SUN_STONE, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("sun_stone_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> CITRINE_ARROW = ENTITIES.register("citrine_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.CITRINE_ARROW.get(), Tints.CITRINE, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("citrine_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> DOLOMITE_ARROW = ENTITIES.register("dolomite_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.DOLOMITE_ARROW.get(), Tints.DOLOMITE, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("dolomite_arrow"));
    public static final Supplier<EntityType<CrystalArrow>> TANZANITE_ARROW = ENTITIES.register("tanzanite_arrow", () -> EntityType.Builder.<CrystalArrow>of((arrow, level) -> new CrystalArrow(arrow, ModItems.TANZANITE_ARROW.get(), Tints.TANZANITE, level), MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("tanzanite_arrow"));
}
