package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.GemstonePower;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGenerator extends ItemModelProvider
{
    public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, GemstonePower.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
    // CRYSTALS
        this.singleTexture("azurite_crystal", mcLoc("item/generated"), "layer0", modLoc("item/azurite_crystal"));
        this.singleTexture("azurite_crystal_charged", mcLoc("item/generated"), "layer0", modLoc("item/azurite_crystal"));
        this.singleTexture("azurite_crystal_seed", mcLoc("item/generated"), "layer0", modLoc("item/azurite_crystal_seed"));

        this.singleTexture("cuprite_crystal", mcLoc("item/generated"), "layer0", modLoc("item/cuprite_crystal"));
        this.singleTexture("cuprite_crystal_charged", mcLoc("item/generated"), "layer0", modLoc("item/cuprite_crystal"));
        this.singleTexture("cuprite_crystal_seed", mcLoc("item/generated"), "layer0", modLoc("item/cuprite_crystal_seed"));

        this.singleTexture("ruby", mcLoc("item/generated"), "layer0", modLoc("item/ruby"));
        this.singleTexture("sapphire", mcLoc("item/generated"), "layer0", modLoc("item/sapphire"));
        this.singleTexture("aquamarine", mcLoc("item/generated"), "layer0", modLoc("item/aquamarine"));
        this.singleTexture("jade", mcLoc("item/generated"), "layer0", modLoc("item/jade"));
        this.singleTexture("opal", mcLoc("item/generated"), "layer0", modLoc("item/opal"));
        this.singleTexture("yellow_diamond", mcLoc("item/generated"), "layer0", modLoc("item/yellow_diamond"));
        this.singleTexture("amber", mcLoc("item/generated"), "layer0", modLoc("item/amber"));
        this.singleTexture("topaz", mcLoc("item/generated"), "layer0", modLoc("item/topaz"));
        this.singleTexture("beryllium", mcLoc("item/generated"), "layer0", modLoc("item/beryllium"));
        this.singleTexture("bixbit", mcLoc("item/generated"), "layer0", modLoc("item/bixbit"));
        this.singleTexture("malachite", mcLoc("item/generated"), "layer0", modLoc("item/malachite"));
        this.singleTexture("onyx", mcLoc("item/generated"), "layer0", modLoc("item/onyx"));
        this.singleTexture("peridot", mcLoc("item/generated"), "layer0", modLoc("item/peridot"));
        this.singleTexture("moon_stone", mcLoc("item/generated"), "layer0", modLoc("item/moon_stone"));
        this.singleTexture("sun_stone", mcLoc("item/generated"), "layer0", modLoc("item/sun_stone"));
        this.singleTexture("citrine", mcLoc("item/generated"), "layer0", modLoc("item/citrine"));
        this.singleTexture("dolomite", mcLoc("item/generated"), "layer0", modLoc("item/dolomite"));
        this.singleTexture("tanzanite", mcLoc("item/generated"), "layer0", modLoc("item/tanzanite"));

        this.withExistingParent("ruby_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("sapphire_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("aquamarine_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("jade_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("opal_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("yellow_diamond_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("amber_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("topaz_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("beryllium_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("bixbit_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("malachite_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("onyx_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("peridot_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("moon_stone_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("sun_stone_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("citrine_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("dolomite_seed", modLoc("item/crystal_seed"));
        this.withExistingParent("tanzanite_seed", modLoc("item/crystal_seed"));

        this.singleTexture("ruby_charged", mcLoc("item/generated"), "layer0", modLoc("item/ruby"));
        this.singleTexture("sapphire_charged", mcLoc("item/generated"), "layer0", modLoc("item/sapphire"));
        this.singleTexture("aquamarine_charged", mcLoc("item/generated"), "layer0", modLoc("item/aquamarine"));
        this.singleTexture("jade_charged", mcLoc("item/generated"), "layer0", modLoc("item/jade"));
        this.singleTexture("opal_charged", mcLoc("item/generated"), "layer0", modLoc("item/opal"));
        this.singleTexture("yellow_diamond_charged", mcLoc("item/generated"), "layer0", modLoc("item/yellow_diamond"));
        this.singleTexture("amber_charged", mcLoc("item/generated"), "layer0", modLoc("item/amber"));
        this.singleTexture("topaz_charged", mcLoc("item/generated"), "layer0", modLoc("item/topaz"));
        this.singleTexture("beryllium_charged", mcLoc("item/generated"), "layer0", modLoc("item/beryllium"));
        this.singleTexture("bixbit_charged", mcLoc("item/generated"), "layer0", modLoc("item/bixbit"));
        this.singleTexture("malachite_charged", mcLoc("item/generated"), "layer0", modLoc("item/malachite"));
        this.singleTexture("onyx_charged", mcLoc("item/generated"), "layer0", modLoc("item/onyx"));
        this.singleTexture("peridot_charged", mcLoc("item/generated"), "layer0", modLoc("item/peridot"));
        this.singleTexture("moon_stone_charged", mcLoc("item/generated"), "layer0", modLoc("item/moon_stone"));
        this.singleTexture("sun_stone_charged", mcLoc("item/generated"), "layer0", modLoc("item/sun_stone"));
        this.singleTexture("citrine_charged", mcLoc("item/generated"), "layer0", modLoc("item/citrine"));
        this.singleTexture("dolomite_charged", mcLoc("item/generated"), "layer0", modLoc("item/dolomite"));
        this.singleTexture("tanzanite_charged", mcLoc("item/generated"), "layer0", modLoc("item/tanzanite"));

    // GEMSTONE
        this.singleTexture("light_gemstone", mcLoc("item/generated"), "layer0", modLoc("item/light_gemstone"));
        this.singleTexture("dark_gemstone", mcLoc("item/generated"), "layer0", modLoc("item/dark_gemstone"));

        this.singleTexture("gemstone_helmet", mcLoc("item/generated"), "layer0", modLoc("item/gemstone_helmet"));
        this.singleTexture("gemstone_chestplate", mcLoc("item/generated"), "layer0", modLoc("item/gemstone_chestplate"));
        this.singleTexture("gemstone_leggings", mcLoc("item/generated"), "layer0", modLoc("item/gemstone_leggings"));
        this.singleTexture("gemstone_boots", mcLoc("item/generated"), "layer0", modLoc("item/gemstone_boots"));

        this.singleTexture("gemstone_sword", mcLoc("item/handheld"), "layer0", modLoc("item/gemstone_sword"));
        this.singleTexture("gemstone_shovel", mcLoc("item/handheld"), "layer0", modLoc("item/gemstone_shovel"));
        this.singleTexture("gemstone_pickaxe", mcLoc("item/handheld"), "layer0", modLoc("item/gemstone_pickaxe"));
        this.singleTexture("gemstone_axe", mcLoc("item/handheld"), "layer0", modLoc("item/gemstone_axe"));
        this.singleTexture("gemstone_hoe", mcLoc("item/handheld"), "layer0", modLoc("item/gemstone_hoe"));

    // IRON
        this.withExistingParent("iron_dust", modLoc("item/dust"));
        this.withExistingParent("iron_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("iron_ore_dust", modLoc("item/ore_dust"));
        this.withExistingParent("iron_plate", modLoc("item/plate"));
        this.withExistingParent("iron_rod", modLoc("item/rod"));
        this.withExistingParent("iron_gear", modLoc("item/gear"));

    // GOLD
        this.withExistingParent("gold_dust", modLoc("item/dust"));
        this.withExistingParent("gold_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("gold_ore_dust", modLoc("item/ore_dust"));
        this.withExistingParent("gold_plate", modLoc("item/plate"));
        this.withExistingParent("gold_rod", modLoc("item/rod"));
        this.withExistingParent("gold_gear", modLoc("item/gear"));

    // COPPER
        this.withExistingParent("copper_nugget", modLoc("item/nugget"));
        this.withExistingParent("copper_dust", modLoc("item/dust"));
        this.withExistingParent("copper_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("copper_ore_dust", modLoc("item/ore_dust"));
        this.withExistingParent("copper_plate", modLoc("item/plate"));
        this.withExistingParent("copper_rod", modLoc("item/rod"));
        this.withExistingParent("copper_gear", modLoc("item/gear"));

        this.withExistingParent("copper_helmet", modLoc("item/helmet"));
        this.withExistingParent("copper_chestplate", modLoc("item/chestplate"));
        this.withExistingParent("copper_leggings", modLoc("item/leggings"));
        this.withExistingParent("copper_boots", modLoc("item/boots"));

        this.withExistingParent("copper_sword", modLoc("item/sword"));
        this.withExistingParent("copper_shovel", modLoc("item/shovel"));
        this.withExistingParent("copper_pickaxe", modLoc("item/pickaxe"));
        this.withExistingParent("copper_axe", modLoc("item/axe"));
        this.withExistingParent("copper_hoe", modLoc("item/hoe"));

    // ALUMINIUM
        this.withExistingParent("aluminum_ingot", modLoc("item/ingot"));
        this.withExistingParent("aluminum_nugget", modLoc("item/nugget"));
        this.withExistingParent("raw_aluminum", modLoc("item/raw"));
        this.withExistingParent("aluminum_dust", modLoc("item/dust"));
        this.withExistingParent("aluminum_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("aluminum_ore_dust", modLoc("item/ore_dust"));
        this.withExistingParent("aluminum_plate", modLoc("item/plate"));
        this.withExistingParent("aluminum_rod", modLoc("item/rod"));
        this.withExistingParent("aluminum_gear", modLoc("item/gear"));

        this.withExistingParent("aluminum_helmet", modLoc("item/helmet"));
        this.withExistingParent("aluminum_chestplate", modLoc("item/chestplate"));
        this.withExistingParent("aluminum_leggings", modLoc("item/leggings"));
        this.withExistingParent("aluminum_boots", modLoc("item/boots"));

        this.withExistingParent("aluminum_sword", modLoc("item/sword"));
        this.withExistingParent("aluminum_shovel", modLoc("item/shovel"));
        this.withExistingParent("aluminum_pickaxe", modLoc("item/pickaxe"));
        this.withExistingParent("aluminum_axe", modLoc("item/axe"));
        this.withExistingParent("aluminum_hoe", modLoc("item/hoe"));

    // TIN
        this.withExistingParent("tin_ingot", modLoc("item/ingot"));
        this.withExistingParent("tin_nugget", modLoc("item/nugget"));
        this.withExistingParent("raw_tin", modLoc("item/raw"));
        this.withExistingParent("tin_dust", modLoc("item/dust"));
        this.withExistingParent("tin_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("tin_ore_dust", modLoc("item/ore_dust"));
        this.withExistingParent("tin_plate", modLoc("item/plate"));
        this.withExistingParent("tin_rod", modLoc("item/rod"));
        this.withExistingParent("tin_gear", modLoc("item/gear"));

    // BRONZE
        this.withExistingParent("bronze_ingot", modLoc("item/ingot"));
        this.withExistingParent("bronze_nugget", modLoc("item/nugget"));
        this.withExistingParent("bronze_dust", modLoc("item/dust"));
        this.withExistingParent("bronze_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("bronze_plate", modLoc("item/plate"));
        this.withExistingParent("bronze_rod", modLoc("item/rod"));
        this.withExistingParent("bronze_gear", modLoc("item/gear"));

        this.withExistingParent("bronze_helmet", modLoc("item/helmet"));
        this.withExistingParent("bronze_chestplate", modLoc("item/chestplate"));
        this.withExistingParent("bronze_leggings", modLoc("item/leggings"));
        this.withExistingParent("bronze_boots", modLoc("item/boots"));

        this.withExistingParent("bronze_sword", modLoc("item/sword"));
        this.withExistingParent("bronze_shovel", modLoc("item/shovel"));
        this.withExistingParent("bronze_pickaxe", modLoc("item/pickaxe"));
        this.withExistingParent("bronze_axe", modLoc("item/axe"));
        this.withExistingParent("bronze_hoe", modLoc("item/hoe"));

    // SILVER
        this.withExistingParent("silver_ingot", modLoc("item/ingot"));
        this.withExistingParent("silver_nugget", modLoc("item/nugget"));
        this.withExistingParent("raw_silver", modLoc("item/raw"));
        this.withExistingParent("silver_dust", modLoc("item/dust"));
        this.withExistingParent("silver_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("silver_ore_dust", modLoc("item/ore_dust"));
        this.withExistingParent("silver_plate", modLoc("item/plate"));
        this.withExistingParent("silver_rod", modLoc("item/rod"));
        this.withExistingParent("silver_gear", modLoc("item/gear"));

        this.withExistingParent("silver_helmet", modLoc("item/helmet"));
        this.withExistingParent("silver_chestplate", modLoc("item/chestplate"));
        this.withExistingParent("silver_leggings", modLoc("item/leggings"));
        this.withExistingParent("silver_boots", modLoc("item/boots"));

        this.withExistingParent("silver_sword", modLoc("item/sword"));
        this.withExistingParent("silver_shovel", modLoc("item/shovel"));
        this.withExistingParent("silver_pickaxe", modLoc("item/pickaxe"));
        this.withExistingParent("silver_axe", modLoc("item/axe"));
        this.withExistingParent("silver_hoe", modLoc("item/hoe"));

    // ELECTRUM
        this.withExistingParent("electrum_ingot", modLoc("item/ingot"));
        this.withExistingParent("electrum_nugget", modLoc("item/nugget"));
        this.withExistingParent("electrum_dust", modLoc("item/dust"));
        this.withExistingParent("electrum_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("electrum_plate", modLoc("item/plate"));
        this.withExistingParent("electrum_rod", modLoc("item/rod"));
        this.withExistingParent("electrum_gear", modLoc("item/gear"));

    // NICKEL
        this.withExistingParent("nickel_ingot", modLoc("item/ingot"));
        this.withExistingParent("nickel_nugget", modLoc("item/nugget"));
        this.withExistingParent("raw_nickel", modLoc("item/raw"));
        this.withExistingParent("nickel_dust", modLoc("item/dust"));
        this.withExistingParent("nickel_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("nickel_ore_dust", modLoc("item/ore_dust"));
        this.withExistingParent("nickel_plate", modLoc("item/plate"));
        this.withExistingParent("nickel_rod", modLoc("item/rod"));
        this.withExistingParent("nickel_gear", modLoc("item/gear"));

    // INVAR
        this.withExistingParent("invar_ingot", modLoc("item/ingot"));
        this.withExistingParent("invar_nugget", modLoc("item/nugget"));
        this.withExistingParent("invar_dust", modLoc("item/dust"));
        this.withExistingParent("invar_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("invar_plate", modLoc("item/plate"));
        this.withExistingParent("invar_rod", modLoc("item/rod"));
        this.withExistingParent("invar_gear", modLoc("item/gear"));

        this.withExistingParent("invar_helmet", modLoc("item/helmet"));
        this.withExistingParent("invar_chestplate", modLoc("item/chestplate"));
        this.withExistingParent("invar_leggings", modLoc("item/leggings"));
        this.withExistingParent("invar_boots", modLoc("item/boots"));

        this.withExistingParent("invar_sword", modLoc("item/sword"));
        this.withExistingParent("invar_shovel", modLoc("item/shovel"));
        this.withExistingParent("invar_pickaxe", modLoc("item/pickaxe"));
        this.withExistingParent("invar_axe", modLoc("item/axe"));
        this.withExistingParent("invar_hoe", modLoc("item/hoe"));

    // CONSTANTAN
        this.withExistingParent("constantan_ingot", modLoc("item/ingot"));
        this.withExistingParent("constantan_nugget", modLoc("item/nugget"));
        this.withExistingParent("constantan_dust", modLoc("item/dust"));
        this.withExistingParent("constantan_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("constantan_plate", modLoc("item/plate"));
        this.withExistingParent("constantan_rod", modLoc("item/rod"));
        this.withExistingParent("constantan_gear", modLoc("item/gear"));

    // PLATINUM
        this.withExistingParent("platinum_ingot", modLoc("item/ingot"));
        this.withExistingParent("platinum_nugget", modLoc("item/nugget"));
        this.withExistingParent("raw_platinum", modLoc("item/raw"));
        this.withExistingParent("platinum_dust", modLoc("item/dust"));
        this.withExistingParent("platinum_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("platinum_ore_dust", modLoc("item/ore_dust"));
        this.withExistingParent("platinum_plate", modLoc("item/plate"));
        this.withExistingParent("platinum_rod", modLoc("item/rod"));
        this.withExistingParent("platinum_gear", modLoc("item/gear"));

    // STEEL
        this.withExistingParent("steel_ingot", modLoc("item/ingot"));
        this.withExistingParent("steel_nugget", modLoc("item/nugget"));
        this.withExistingParent("steel_dust", modLoc("item/dust"));
        this.withExistingParent("steel_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("steel_plate", modLoc("item/plate"));
        this.withExistingParent("steel_rod", modLoc("item/rod"));
        this.withExistingParent("steel_gear", modLoc("item/gear"));

        this.withExistingParent("steel_helmet", modLoc("item/helmet"));
        this.withExistingParent("steel_chestplate", modLoc("item/chestplate"));
        this.withExistingParent("steel_leggings", modLoc("item/leggings"));
        this.withExistingParent("steel_boots", modLoc("item/boots"));

        this.withExistingParent("steel_sword", modLoc("item/sword"));
        this.withExistingParent("steel_shovel", modLoc("item/shovel"));
        this.withExistingParent("steel_pickaxe", modLoc("item/pickaxe"));
        this.withExistingParent("steel_axe", modLoc("item/axe"));
        this.withExistingParent("steel_hoe", modLoc("item/hoe"));

    // LITHIUM
        this.withExistingParent("lithium_ingot", modLoc("item/ingot"));
        this.withExistingParent("lithium_nugget", modLoc("item/nugget"));
        this.withExistingParent("raw_lithium", modLoc("item/raw"));
        this.withExistingParent("lithium_dust", modLoc("item/dust"));
        this.withExistingParent("lithium_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("lithium_ore_dust", modLoc("item/ore_dust"));
        this.withExistingParent("lithium_plate", modLoc("item/plate"));
        this.withExistingParent("lithium_rod", modLoc("item/rod"));
        this.withExistingParent("lithium_gear", modLoc("item/gear"));

    // MAGNESIUM
        this.withExistingParent("magnesium_ingot", modLoc("item/ingot"));
        this.withExistingParent("magnesium_nugget", modLoc("item/nugget"));
        this.withExistingParent("raw_magnesium", modLoc("item/raw"));
        this.withExistingParent("magnesium_dust", modLoc("item/dust"));
        this.withExistingParent("magnesium_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("magnesium_ore_dust", modLoc("item/ore_dust"));
        this.withExistingParent("magnesium_plate", modLoc("item/plate"));
        this.withExistingParent("magnesium_rod", modLoc("item/rod"));
        this.withExistingParent("magnesium_gear", modLoc("item/gear"));

    // URANIUM
        this.withExistingParent("uranium_ingot", modLoc("item/ingot"));
        this.withExistingParent("uranium_nugget", modLoc("item/nugget"));
        this.withExistingParent("raw_uranium", modLoc("item/raw"));
        this.withExistingParent("uranium_dust", modLoc("item/dust"));
        this.withExistingParent("uranium_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("uranium_ore_dust", modLoc("item/ore_dust"));
        this.withExistingParent("uranium_plate", modLoc("item/plate"));
        this.withExistingParent("uranium_rod", modLoc("item/rod"));
        this.withExistingParent("uranium_gear", modLoc("item/gear"));

    // LEAD
        this.withExistingParent("lead_ingot", modLoc("item/ingot"));
        this.withExistingParent("lead_nugget", modLoc("item/nugget"));
        this.withExistingParent("raw_lead", modLoc("item/raw"));
        this.withExistingParent("lead_dust", modLoc("item/dust"));
        this.withExistingParent("lead_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("lead_ore_dust", modLoc("item/ore_dust"));
        this.withExistingParent("lead_plate", modLoc("item/plate"));
        this.withExistingParent("lead_rod", modLoc("item/rod"));
        this.withExistingParent("lead_gear", modLoc("item/gear"));

    // ZINC
        this.withExistingParent("zinc_ingot", modLoc("item/ingot"));
        this.withExistingParent("zinc_nugget", modLoc("item/nugget"));
        this.withExistingParent("raw_zinc", modLoc("item/raw"));
        this.withExistingParent("zinc_dust", modLoc("item/dust"));
        this.withExistingParent("zinc_tiny_pile", modLoc("item/tiny_pile"));
        this.withExistingParent("zinc_ore_dust", modLoc("item/ore_dust"));
        this.withExistingParent("zinc_plate", modLoc("item/plate"));
        this.withExistingParent("zinc_rod", modLoc("item/rod"));
        this.withExistingParent("zinc_gear", modLoc("item/gear"));

    // ARROWS
        this.withExistingParent("ruby_arrow", modLoc("item/arrow"));
        this.withExistingParent("sapphire_arrow", modLoc("item/arrow"));
        this.withExistingParent("aquamarine_arrow", modLoc("item/arrow"));
        this.withExistingParent("jade_arrow", modLoc("item/arrow"));
        this.withExistingParent("opal_arrow", modLoc("item/arrow"));
        this.withExistingParent("yellow_diamond_arrow", modLoc("item/arrow"));
        this.withExistingParent("amber_arrow", modLoc("item/arrow"));
        this.withExistingParent("topaz_arrow", modLoc("item/arrow"));
        this.withExistingParent("beryllium_arrow", modLoc("item/arrow"));
        this.withExistingParent("bixbit_arrow", modLoc("item/arrow"));
        this.withExistingParent("malachite_arrow", modLoc("item/arrow"));
        this.withExistingParent("onyx_arrow", modLoc("item/arrow"));
        this.withExistingParent("peridot_arrow", modLoc("item/arrow"));
        this.withExistingParent("moon_stone_arrow", modLoc("item/arrow"));
        this.withExistingParent("sun_stone_arrow", modLoc("item/arrow"));
        this.withExistingParent("citrine_arrow", modLoc("item/arrow"));
        this.withExistingParent("dolomite_arrow", modLoc("item/arrow"));
        this.withExistingParent("tanzanite_arrow", modLoc("item/arrow"));

        this.withExistingParent("charged_ruby_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_sapphire_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_aquamarine_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_jade_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_opal_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_yellow_diamond_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_amber_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_topaz_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_beryllium_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_bixbit_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_malachite_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_onyx_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_peridot_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_moon_stone_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_sun_stone_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_citrine_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_dolomite_arrow", modLoc("item/arrow"));
        this.withExistingParent("charged_tanzanite_arrow", modLoc("item/arrow"));

    // MICS
        this.singleTexture("plate_preset", mcLoc("item/generated"), "layer0", modLoc("item/plate_preset"));
        this.singleTexture("rod_preset", mcLoc("item/generated"), "layer0", modLoc("item/rod_preset"));
        this.singleTexture("wire_preset", mcLoc("item/generated"), "layer0", modLoc("item/wire_preset"));
        this.singleTexture("resin", mcLoc("item/generated"), "layer0", modLoc("item/resin"));
        this.singleTexture("rubber", mcLoc("item/generated"), "layer0", modLoc("item/rubber"));
        this.withExistingParent("coal_dust", modLoc("item/dust"));
        this.singleTexture("tree_tap", mcLoc("item/generated"), "layer0", modLoc("item/tree_tap"));
        this.singleTexture("resin_oak_sapling", mcLoc("item/generated"), "layer0", modLoc("block/resin_oak_sapling"));

        this.withExistingParent("water_mill", modLoc("block/water_mill"));
        this.withExistingParent("wind_turbine", modLoc("block/wind_turbine"));

        this.withExistingParent("copper_wire", modLoc("item/wire"));
        this.withExistingParent("aluminum_wire", modLoc("item/wire"));
        this.withExistingParent("tin_wire", modLoc("item/wire"));
        this.withExistingParent("electrum_wire", modLoc("item/wire"));
    }
}
