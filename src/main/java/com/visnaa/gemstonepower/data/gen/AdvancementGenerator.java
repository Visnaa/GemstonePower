package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModItems;
import com.visnaa.gemstonepower.init.ModTags;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementGenerator implements AdvancementSubProvider
{
    public static AdvancementProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        return new AdvancementProvider(output, completableFuture, List.of(new AdvancementGenerator()));
    }

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer)
    {
        Advancement root = Advancement.Builder.advancement()
                .display(ModItems.SAPPHIRE_SEED.get(),
                        getTitleLocation("root"), getDescLocation("root"), GemstonePower.getId("textures/gui/advancement_gui.png"),
                        FrameType.TASK, true, true, false)
                .addCriterion("has_gem_seed", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModTags.GEM_SEEDS).build()))
                .save(consumer, getName("root"));

        Advancement gemstoneGenerator = Advancement.Builder.advancement().parent(root)
                .display(ModItems.GEMSTONE_GENERATOR.get(),
                        getTitleLocation("gemstone_generator"), getDescLocation("gemstone_generator"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_gemstone_generator", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.GEMSTONE_GENERATOR.get()).build()))
                .save(consumer, getName("gemstone_generator"));

        Advancement gemstoneCell = Advancement.Builder.advancement().parent(gemstoneGenerator)
                .display(ModItems.GEMSTONE_CELL.get(),
                        getTitleLocation("gemstone_cell"), getDescLocation("gemstone_cell"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_gemstone_cell", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.GEMSTONE_CELL.get()).build()))
                .save(consumer, getName("gemstone_cell"));

        Advancement alloySmelter = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.ALLOY_SMELTER.get(),
                        getTitleLocation("alloy_smelter"), getDescLocation("alloy_smelter"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_alloy_smelter", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.ALLOY_SMELTER.get()).build()))
                .save(consumer, getName("alloy_smelter"));

        Advancement cobblestoneGenerator = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.COBBLESTONE_GENERATOR.get(),
                        getTitleLocation("cobblestone_generator"), getDescLocation("cobblestone_generator"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_cobblestone_generator", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.COBBLESTONE_GENERATOR.get()).build()))
                .save(consumer, getName("cobblestone_generator"));

        Advancement crystalCharger = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.CRYSTAL_CHARGER.get(),
                        getTitleLocation("crystal_charger"), getDescLocation("crystal_charger"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_crystal_charger", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.CRYSTAL_CHARGER.get()).build()))
                .save(consumer, getName("crystal_charger"));

        Advancement crystalGrower = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.CRYSTAL_GROWER.get(),
                        getTitleLocation("crystal_grower"), getDescLocation("crystal_grower"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_crystal_grower", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.CRYSTAL_GROWER.get()).build()))
                .save(consumer, getName("crystal_grower"));

        Advancement electricFurnace = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.ELECTRIC_FURNACE.get(),
                        getTitleLocation("electric_furnace"), getDescLocation("electric_furnace"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_electric_furnace", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.ELECTRIC_FURNACE.get()).build()))
                .save(consumer, getName("electric_furnace"));

        Advancement extractor = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.EXTRACTOR.get(),
                        getTitleLocation("extractor"), getDescLocation("extractor"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_extractor", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.EXTRACTOR.get()).build()))
                .save(consumer, getName("extractor"));

        Advancement metalFormer = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.METAL_FORMER.get(),
                        getTitleLocation("metal_former"), getDescLocation("metal_former"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_metal_former", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.METAL_FORMER.get()).build()))
                .save(consumer, getName("metal_former"));

        Advancement oreWasher = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.ORE_WASHER.get(),
                        getTitleLocation("ore_washer"), getDescLocation("ore_washer"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_ore_washer", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.ORE_WASHER.get()).build()))
                .save(consumer, getName("ore_washer"));

        Advancement polarizer = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.POLARIZER.get(),
                        getTitleLocation("polarizer"), getDescLocation("polarizer"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_polarizer", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.POLARIZER.get()).build()))
                .save(consumer, getName("polarizer"));

        Advancement pulverizer = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.PULVERIZER.get(),
                        getTitleLocation("pulverizer"), getDescLocation("pulverizer"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_pulverizer", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.PULVERIZER.get()).build()))
                .save(consumer, getName("pulverizer"));

        Advancement sawmill = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.SAWMILL.get(),
                        getTitleLocation("sawmill"), getDescLocation("sawmill"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_sawmill", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.SAWMILL.get()).build()))
                .save(consumer, getName("sawmill"));

        Advancement gemstone_manipulator = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.GEMSTONE_MANIPULATOR.get(),
                        getTitleLocation("gemstone_manipulator"), getDescLocation("gemstone_manipulator"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_gemstone_manipulator", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.GEMSTONE_MANIPULATOR.get()).build()))
                .save(consumer, getName("gemstone_manipulator"));

        Advancement intermediateUpgrade = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.INTERMEDIATE_UPGRADE.get(),
                        getTitleLocation("intermediate_upgrade"), getDescLocation("intermediate_upgrade"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_intermediate_upgrade", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.INTERMEDIATE_UPGRADE.get()).build()))
                .save(consumer, getName("intermediate_upgrade"));

        Advancement advancedUpgrade = Advancement.Builder.advancement().parent(intermediateUpgrade)
                .display(ModItems.ADVANCED_UPGRADE.get(),
                        getTitleLocation("advanced_upgrade"), getDescLocation("advanced_upgrade"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_advanced_upgrade", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.ADVANCED_UPGRADE.get()).build()))
                .save(consumer, getName("advanced_upgrade"));

        Advancement ultraUpgrade = Advancement.Builder.advancement().parent(advancedUpgrade)
                .display(ModItems.ULTRA_UPGRADE.get(),
                        getTitleLocation("ultra_upgrade"), getDescLocation("ultra_upgrade"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_ultra_upgrade", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.ULTRA_UPGRADE.get()).build()))
                .save(consumer, getName("ultra_upgrade"));

        Advancement extremeUpgrade = Advancement.Builder.advancement().parent(ultraUpgrade)
                .display(ModItems.EXTREME_UPGRADE.get(),
                        getTitleLocation("extreme_upgrade"), getDescLocation("extreme_upgrade"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_extreme_upgrade", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.EXTREME_UPGRADE.get()).build()))
                .save(consumer, getName("extreme_upgrade"));

        Advancement solarPanel = Advancement.Builder.advancement().parent(gemstoneGenerator)
                .display(ModItems.SOLAR_PANEL.get(),
                        getTitleLocation("solar_panel"), getDescLocation("solar_panel"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_solar_panel", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.SOLAR_PANEL.get()).build()))
                .save(consumer, getName("solar_panel"));

        Advancement waterMill = Advancement.Builder.advancement().parent(gemstoneGenerator)
                .display(ModItems.WATER_MILL.get(),
                        getTitleLocation("water_mill"), getDescLocation("water_mill"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_water_mill", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.WATER_MILL.get()).build()))
                .save(consumer, getName("water_mill"));

        Advancement windTurbine = Advancement.Builder.advancement().parent(gemstoneGenerator)
                .display(ModItems.WATER_MILL.get(),
                        getTitleLocation("wind_turbine"), getDescLocation("wind_turbine"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_wind_turbine", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.WIND_TURBINE.get()).build()))
                .save(consumer, getName("wind_turbine"));

        Advancement gem = Advancement.Builder.advancement().parent(crystalGrower)
                .display(ModItems.RUBY.get(),
                        getTitleLocation("gem"), getDescLocation("gem"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_gem", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModTags.GEMS).build()))
                .save(consumer, getName("gem"));

        Advancement gemCharged = Advancement.Builder.advancement().parent(gem)
                .display(ModItems.AQUAMARINE_CHARGED.get(),
                        getTitleLocation("charged_gem"), getDescLocation("charged_gem"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_charged_gem", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModTags.CHARGED_GEMS).build()))
                .save(consumer, getName("charged_gem"));

        Advancement resin = Advancement.Builder.advancement().parent(root)
                .display(ModItems.RESIN.get(),
                        getTitleLocation("resin"), getDescLocation("resin"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_resin", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.RESIN.get()).build()))
                .save(consumer, getName("resin"));

        Advancement rubber = Advancement.Builder.advancement().parent(resin)
                .display(ModItems.RUBBER.get(),
                        getTitleLocation("rubber"), getDescLocation("rubber"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_rubber", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.RUBBER.get()).build()))
                .save(consumer, getName("rubber"));

        Advancement cable = Advancement.Builder.advancement().parent(rubber)
                .display(ModItems.COPPER_CABLE.get(),
                        getTitleLocation("cable"), getDescLocation("cable"), null,
                        FrameType.TASK, true, true, false)
                .addCriterion("has_cable", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModTags.CABLES).build()))
                .save(consumer, getName("cable"));
    }

    private static String getName(String name)
    {
        return GemstonePower.MOD_ID + "/" + name;
    }

    private static Component getTitleLocation(String name)
    {
        return Component.translatable("advancement." + GemstonePower.MOD_ID + "." + name + ".title");
    }

    private static Component getDescLocation(String name)
    {
        return Component.translatable("advancement." + GemstonePower.MOD_ID + "." + name + ".description");
    }
}
