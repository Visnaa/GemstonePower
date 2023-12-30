package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.init.ModItems;
import com.visnaa.gemstonepower.init.ModTags;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator
{
    public static ForgeAdvancementProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, ExistingFileHelper existingFileHelper)
    {
        return new ForgeAdvancementProvider(output, completableFuture, existingFileHelper, List.of(new AdvancementGenerator()));
    }

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper)
    {
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(ModItems.SAPPHIRE_SEED.get(),
                        getTitleLocation("root"), getDescLocation("root"), GemstonePower.getId("textures/gui/advancement_gui.png"),
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_gem_seed", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModTags.GEM_SEEDS).build()))
                .save(consumer, getName("root"));

        AdvancementHolder gemstoneGenerator = Advancement.Builder.advancement().parent(root)
                .display(ModItems.GEMSTONE_GENERATOR.get(),
                        getTitleLocation("gemstone_generator"), getDescLocation("gemstone_generator"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_gemstone_generator", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.GEMSTONE_GENERATOR.get()).build()))
                .save(consumer, getName("gemstone_generator"));

        AdvancementHolder gemstoneCell = Advancement.Builder.advancement().parent(gemstoneGenerator)
                .display(ModItems.GEMSTONE_CELL.get(),
                        getTitleLocation("gemstone_cell"), getDescLocation("gemstone_cell"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_gemstone_cell", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.GEMSTONE_CELL.get()).build()))
                .save(consumer, getName("gemstone_cell"));

        AdvancementHolder alloySmelter = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.ALLOY_SMELTER.get(),
                        getTitleLocation("alloy_smelter"), getDescLocation("alloy_smelter"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_alloy_smelter", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.ALLOY_SMELTER.get()).build()))
                .save(consumer, getName("alloy_smelter"));

        AdvancementHolder cobblestoneGenerator = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.COBBLESTONE_GENERATOR.get(),
                        getTitleLocation("cobblestone_generator"), getDescLocation("cobblestone_generator"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_cobblestone_generator", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.COBBLESTONE_GENERATOR.get()).build()))
                .save(consumer, getName("cobblestone_generator"));

        AdvancementHolder crystalCharger = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.CRYSTAL_CHARGER.get(),
                        getTitleLocation("crystal_charger"), getDescLocation("crystal_charger"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_crystal_charger", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.CRYSTAL_CHARGER.get()).build()))
                .save(consumer, getName("crystal_charger"));

        AdvancementHolder crystalGrower = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.CRYSTAL_GROWER.get(),
                        getTitleLocation("crystal_grower"), getDescLocation("crystal_grower"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_crystal_grower", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.CRYSTAL_GROWER.get()).build()))
                .save(consumer, getName("crystal_grower"));

        AdvancementHolder electricFurnace = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.ELECTRIC_FURNACE.get(),
                        getTitleLocation("electric_furnace"), getDescLocation("electric_furnace"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_electric_furnace", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.ELECTRIC_FURNACE.get()).build()))
                .save(consumer, getName("electric_furnace"));

        AdvancementHolder extractor = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.EXTRACTOR.get(),
                        getTitleLocation("extractor"), getDescLocation("extractor"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_extractor", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.EXTRACTOR.get()).build()))
                .save(consumer, getName("extractor"));

        AdvancementHolder metalFormer = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.METAL_FORMER.get(),
                        getTitleLocation("metal_former"), getDescLocation("metal_former"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_metal_former", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.METAL_FORMER.get()).build()))
                .save(consumer, getName("metal_former"));

        AdvancementHolder oreWasher = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.ORE_WASHER.get(),
                        getTitleLocation("ore_washer"), getDescLocation("ore_washer"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_ore_washer", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.ORE_WASHER.get()).build()))
                .save(consumer, getName("ore_washer"));

        AdvancementHolder polarizer = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.POLARIZER.get(),
                        getTitleLocation("polarizer"), getDescLocation("polarizer"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_polarizer", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.POLARIZER.get()).build()))
                .save(consumer, getName("polarizer"));

        AdvancementHolder pulverizer = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.PULVERIZER.get(),
                        getTitleLocation("pulverizer"), getDescLocation("pulverizer"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_pulverizer", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.PULVERIZER.get()).build()))
                .save(consumer, getName("pulverizer"));

        AdvancementHolder sawmill = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.SAWMILL.get(),
                        getTitleLocation("sawmill"), getDescLocation("sawmill"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_sawmill", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.SAWMILL.get()).build()))
                .save(consumer, getName("sawmill"));

        AdvancementHolder gemstone_manipulator = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.GEMSTONE_MANIPULATOR.get(),
                        getTitleLocation("gemstone_manipulator"), getDescLocation("gemstone_manipulator"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_gemstone_manipulator", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.GEMSTONE_MANIPULATOR.get()).build()))
                .save(consumer, getName("gemstone_manipulator"));

        AdvancementHolder intermediateUpgrade = Advancement.Builder.advancement().parent(gemstoneCell)
                .display(ModItems.INTERMEDIATE_UPGRADE.get(),
                        getTitleLocation("intermediate_upgrade"), getDescLocation("intermediate_upgrade"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_intermediate_upgrade", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.INTERMEDIATE_UPGRADE.get()).build()))
                .save(consumer, getName("intermediate_upgrade"));

        AdvancementHolder advancedUpgrade = Advancement.Builder.advancement().parent(intermediateUpgrade)
                .display(ModItems.ADVANCED_UPGRADE.get(),
                        getTitleLocation("advanced_upgrade"), getDescLocation("advanced_upgrade"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_advanced_upgrade", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.ADVANCED_UPGRADE.get()).build()))
                .save(consumer, getName("advanced_upgrade"));

        AdvancementHolder ultraUpgrade = Advancement.Builder.advancement().parent(advancedUpgrade)
                .display(ModItems.ULTRA_UPGRADE.get(),
                        getTitleLocation("ultra_upgrade"), getDescLocation("ultra_upgrade"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_ultra_upgrade", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.ULTRA_UPGRADE.get()).build()))
                .save(consumer, getName("ultra_upgrade"));

        AdvancementHolder extremeUpgrade = Advancement.Builder.advancement().parent(ultraUpgrade)
                .display(ModItems.EXTREME_UPGRADE.get(),
                        getTitleLocation("extreme_upgrade"), getDescLocation("extreme_upgrade"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_extreme_upgrade", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.EXTREME_UPGRADE.get()).build()))
                .save(consumer, getName("extreme_upgrade"));

        AdvancementHolder solarPanel = Advancement.Builder.advancement().parent(gemstoneGenerator)
                .display(ModItems.SOLAR_PANEL.get(),
                        getTitleLocation("solar_panel"), getDescLocation("solar_panel"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_solar_panel", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.SOLAR_PANEL.get()).build()))
                .save(consumer, getName("solar_panel"));

        AdvancementHolder waterMill = Advancement.Builder.advancement().parent(gemstoneGenerator)
                .display(ModItems.WATER_MILL.get(),
                        getTitleLocation("water_mill"), getDescLocation("water_mill"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_water_mill", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.WATER_MILL.get()).build()))
                .save(consumer, getName("water_mill"));

        AdvancementHolder windTurbine = Advancement.Builder.advancement().parent(gemstoneGenerator)
                .display(ModItems.WATER_MILL.get(),
                        getTitleLocation("wind_turbine"), getDescLocation("wind_turbine"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_wind_turbine", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.WIND_TURBINE.get()).build()))
                .save(consumer, getName("wind_turbine"));

        AdvancementHolder gem = Advancement.Builder.advancement().parent(crystalGrower)
                .display(ModItems.RUBY.get(),
                        getTitleLocation("gem"), getDescLocation("gem"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_gem", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModTags.GEMS).build()))
                .save(consumer, getName("gem"));

        AdvancementHolder gemCharged = Advancement.Builder.advancement().parent(gem)
                .display(ModItems.AQUAMARINE_CHARGED.get(),
                        getTitleLocation("charged_gem"), getDescLocation("charged_gem"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_charged_gem", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModTags.CHARGED_GEMS).build()))
                .save(consumer, getName("charged_gem"));

        AdvancementHolder resin = Advancement.Builder.advancement().parent(root)
                .display(ModItems.RESIN.get(),
                        getTitleLocation("resin"), getDescLocation("resin"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_resin", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.RESIN.get()).build()))
                .save(consumer, getName("resin"));

        AdvancementHolder rubber = Advancement.Builder.advancement().parent(resin)
                .display(ModItems.RUBBER.get(),
                        getTitleLocation("rubber"), getDescLocation("rubber"), null,
                        AdvancementType.TASK, true, true, false)
                .addCriterion("has_rubber", InventoryChangeTrigger.TriggerInstance
                        .hasItems(ItemPredicate.Builder.item().of(ModItems.RUBBER.get()).build()))
                .save(consumer, getName("rubber"));

        AdvancementHolder cable = Advancement.Builder.advancement().parent(rubber)
                .display(ModItems.COPPER_CABLE.get(),
                        getTitleLocation("cable"), getDescLocation("cable"), null,
                        AdvancementType.TASK, true, true, false)
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
