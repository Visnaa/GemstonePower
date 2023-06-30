package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.registry.ModArmors;
import com.visnaa.gemstonepower.registry.ModItems;
import com.visnaa.gemstonepower.registry.ModTools;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.visnaa.gemstonepower.data.tag.ForgeTags.Items.*;
import static com.visnaa.gemstonepower.registry.ModTags.GEMSTONE;
import static net.minecraft.tags.ItemTags.*;
import static net.minecraftforge.common.Tags.Items.*;

public class ItemTagGenerator extends TagsProvider<Item>
{
    public ItemTagGenerator(PackOutput output, ResourceKey<? extends Registry<Item>> registry, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, registry, completableFuture, GemstonePower.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        this.tag(GEMSTONE)
                .add(ModItems.LIGHT_GEMSTONE.getKey())
                .add(ModItems.DARK_GEMSTONE.getKey());

        this.tag(SWORDS)
                .add(ModTools.GEMSTONE_SWORD.getKey())
                .add(ModTools.COPPER_SWORD.getKey())
                .add(ModTools.ALUMINUM_SWORD.getKey())
                .add(ModTools.BRONZE_SWORD.getKey())
                .add(ModTools.SILVER_SWORD.getKey())
                .add(ModTools.INVAR_SWORD.getKey())
                .add(ModTools.STEEL_SWORD.getKey());
        this.tag(SHOVELS)
                .add(ModTools.GEMSTONE_SHOVEL.getKey())
                .add(ModTools.COPPER_SHOVEL.getKey())
                .add(ModTools.ALUMINUM_SHOVEL.getKey())
                .add(ModTools.BRONZE_SHOVEL.getKey())
                .add(ModTools.SILVER_SHOVEL.getKey())
                .add(ModTools.INVAR_SHOVEL.getKey())
                .add(ModTools.STEEL_SHOVEL.getKey());
        this.tag(PICKAXES)
                .add(ModTools.GEMSTONE_PICKAXE.getKey())
                .add(ModTools.COPPER_PICKAXE.getKey())
                .add(ModTools.ALUMINUM_PICKAXE.getKey())
                .add(ModTools.BRONZE_PICKAXE.getKey())
                .add(ModTools.SILVER_PICKAXE.getKey())
                .add(ModTools.INVAR_PICKAXE.getKey())
                .add(ModTools.STEEL_PICKAXE.getKey());
        this.tag(AXES)
                .add(ModTools.GEMSTONE_AXE.getKey())
                .add(ModTools.COPPER_AXE.getKey())
                .add(ModTools.ALUMINUM_AXE.getKey())
                .add(ModTools.BRONZE_AXE.getKey())
                .add(ModTools.SILVER_AXE.getKey())
                .add(ModTools.INVAR_AXE.getKey())
                .add(ModTools.STEEL_AXE.getKey());
        this.tag(HOES)
                .add(ModTools.GEMSTONE_HOE.getKey())
                .add(ModTools.COPPER_HOE.getKey())
                .add(ModTools.ALUMINUM_HOE.getKey())
                .add(ModTools.BRONZE_HOE.getKey())
                .add(ModTools.SILVER_HOE.getKey())
                .add(ModTools.INVAR_HOE.getKey())
                .add(ModTools.STEEL_HOE.getKey());
        this.tag(CLUSTER_MAX_HARVESTABLES)
                .add(ModTools.GEMSTONE_PICKAXE.getKey())
                .add(ModTools.COPPER_PICKAXE.getKey())
                .add(ModTools.ALUMINUM_PICKAXE.getKey())
                .add(ModTools.BRONZE_PICKAXE.getKey())
                .add(ModTools.SILVER_PICKAXE.getKey())
                .add(ModTools.INVAR_PICKAXE.getKey())
                .add(ModTools.STEEL_PICKAXE.getKey());

        this.tag(INGOTS)
                .addTag(INGOTS_ALUMNUM)
                .addTag(INGOTS_TIN)
                .addTag(INGOTS_BRONZE)
                .addTag(INGOTS_SILVER)
                .addTag(INGOTS_ELECTRUM)
                .addTag(INGOTS_NICKEL)
                .addTag(INGOTS_INVAR)
                .addTag(INGOTS_CONSTANTAN)
                .addTag(INGOTS_PLATINUM)
                .addTag(INGOTS_STEEL)
                .addTag(INGOTS_LITHIUM)
                .addTag(INGOTS_MAGNESIUM)
                .addTag(INGOTS_URANIUM)
                .addTag(INGOTS_LEAD)
                .addTag(INGOTS_ZINC);
        this.tag(INGOTS_ALUMNUM)
                .add(ModItems.ALUMINUM_INGOT.getKey());
        this.tag(INGOTS_TIN)
                .add(ModItems.TIN_INGOT.getKey());
        this.tag(INGOTS_BRONZE)
                .add(ModItems.BRONZE_INGOT.getKey());
        this.tag(INGOTS_SILVER)
                .add(ModItems.SILVER_INGOT.getKey());
        this.tag(INGOTS_ELECTRUM)
                .add(ModItems.ELECTRUM_INGOT.getKey());
        this.tag(INGOTS_NICKEL)
                .add(ModItems.NICKEL_INGOT.getKey());
        this.tag(INGOTS_INVAR)
                .add(ModItems.INVAR_INGOT.getKey());
        this.tag(INGOTS_CONSTANTAN)
                .add(ModItems.CONSTANTAN_INGOT.getKey());
        this.tag(INGOTS_PLATINUM)
                .add(ModItems.PLATINUM_INGOT.getKey());
        this.tag(INGOTS_STEEL)
                .add(ModItems.STEEL_INGOT.getKey());
        this.tag(INGOTS_LITHIUM)
                .add(ModItems.LITHIUM_INGOT.getKey());
        this.tag(INGOTS_MAGNESIUM)
                .add(ModItems.MAGNESIUM_INGOT.getKey());
        this.tag(INGOTS_URANIUM)
                .add(ModItems.URANIUM_INGOT.getKey());
        this.tag(INGOTS_LEAD)
                .add(ModItems.LEAD_INGOT.getKey());
        this.tag(INGOTS_ZINC)
                .add(ModItems.ZINC_INGOT.getKey());

        this.tag(RAW_MATERIALS)
                .addTag(RAW_MATERIALS_ALUMINUM)
                .addTag(RAW_MATERIALS_TIN)
                .addTag(RAW_MATERIALS_SILVER)
                .addTag(RAW_MATERIALS_NICKEL)
                .addTag(RAW_MATERIALS_PLATINUM)
                .addTag(RAW_MATERIALS_LITHIUM)
                .addTag(RAW_MATERIALS_MAGNESIUM)
                .addTag(RAW_MATERIALS_URANIUM)
                .addTag(RAW_MATERIALS_LEAD)
                .addTag(RAW_MATERIALS_ZINC);
        this.tag(RAW_MATERIALS_ALUMINUM)
                .add(ModItems.RAW_ALUMINUM.getKey());
        this.tag(RAW_MATERIALS_TIN)
                .add(ModItems.RAW_TIN.getKey());
        this.tag(RAW_MATERIALS_SILVER)
                .add(ModItems.RAW_SILVER.getKey());
        this.tag(RAW_MATERIALS_NICKEL)
                .add(ModItems.RAW_NICKEL.getKey());
        this.tag(RAW_MATERIALS_PLATINUM)
                .add(ModItems.RAW_PLATINUM.getKey());
        this.tag(RAW_MATERIALS_LITHIUM)
                .add(ModItems.RAW_LITHIUM.getKey());
        this.tag(RAW_MATERIALS_MAGNESIUM)
                .add(ModItems.RAW_MAGNESIUM.getKey());
        this.tag(RAW_MATERIALS_URANIUM)
                .add(ModItems.RAW_URANIUM.getKey());
        this.tag(RAW_MATERIALS_LEAD)
                .add(ModItems.RAW_LEAD.getKey());
        this.tag(RAW_MATERIALS_ZINC)
                .add(ModItems.RAW_ZINC.getKey());

        this.tag(NUGGETS)
                .addTag(NUGGETS_COPPER)
                .addTag(NUGGETS_ALUMNUM)
                .addTag(NUGGETS_TIN)
                .addTag(NUGGETS_BRONZE)
                .addTag(NUGGETS_SILVER)
                .addTag(NUGGETS_ELECTRUM)
                .addTag(NUGGETS_NICKEL)
                .addTag(NUGGETS_INVAR)
                .addTag(NUGGETS_CONSTANTAN)
                .addTag(NUGGETS_PLATINUM)
                .addTag(NUGGETS_STEEL)
                .addTag(NUGGETS_LITHIUM)
                .addTag(NUGGETS_MAGNESIUM)
                .addTag(NUGGETS_URANIUM)
                .addTag(NUGGETS_LEAD)
                .addTag(NUGGETS_ZINC);
        this.tag(NUGGETS_COPPER)
                .add(ModItems.COPPER_NUGGET.getKey());
        this.tag(NUGGETS_ALUMNUM)
                .add(ModItems.ALUMINUM_NUGGET.getKey());
        this.tag(NUGGETS_TIN)
                .add(ModItems.TIN_NUGGET.getKey());
        this.tag(NUGGETS_BRONZE)
                .add(ModItems.BRONZE_NUGGET.getKey());
        this.tag(NUGGETS_SILVER)
                .add(ModItems.SILVER_NUGGET.getKey());
        this.tag(NUGGETS_ELECTRUM)
                .add(ModItems.ELECTRUM_NUGGET.getKey());
        this.tag(NUGGETS_NICKEL)
                .add(ModItems.NICKEL_NUGGET.getKey());
        this.tag(NUGGETS_INVAR)
                .add(ModItems.INVAR_NUGGET.getKey());
        this.tag(NUGGETS_CONSTANTAN)
                .add(ModItems.CONSTANTAN_NUGGET.getKey());
        this.tag(NUGGETS_PLATINUM)
                .add(ModItems.PLATINUM_NUGGET.getKey());
        this.tag(NUGGETS_STEEL)
                .add(ModItems.STEEL_NUGGET.getKey());
        this.tag(NUGGETS_LITHIUM)
                .add(ModItems.LITHIUM_NUGGET.getKey());
        this.tag(NUGGETS_MAGNESIUM)
                .add(ModItems.MAGNESIUM_NUGGET.getKey());
        this.tag(NUGGETS_URANIUM)
                .add(ModItems.URANIUM_NUGGET.getKey());
        this.tag(NUGGETS_LEAD)
                .add(ModItems.LEAD_NUGGET.getKey());
        this.tag(NUGGETS_ZINC)
                .add(ModItems.ZINC_NUGGET.getKey());

        this.tag(DUSTS)
                .addTag(DUSTS_IRON)
                .addTag(DUSTS_GOLD)
                .addTag(DUSTS_COPPER)
                .addTag(DUSTS_ALUMINUM)
                .addTag(DUSTS_TIN)
                .addTag(DUSTS_BRONZE)
                .addTag(DUSTS_SILVER)
                .addTag(DUSTS_ELECTRUM)
                .addTag(DUSTS_NICKEL)
                .addTag(DUSTS_INVAR)
                .addTag(DUSTS_CONSTANTAN)
                .addTag(DUSTS_PLATINUM)
                .addTag(DUSTS_STEEL)
                .addTag(DUSTS_LITHIUM)
                .addTag(DUSTS_MAGNESIUM)
                .addTag(DUSTS_URANIUM)
                .addTag(DUSTS_LEAD)
                .addTag(DUSTS_ZINC);
        this.tag(DUSTS_IRON)
                .add(ModItems.IRON_DUST.getKey());
        this.tag(DUSTS_GOLD)
                .add(ModItems.GOLD_DUST.getKey());
        this.tag(DUSTS_COPPER)
                .add(ModItems.COPPER_DUST.getKey());
        this.tag(DUSTS_ALUMINUM)
                .add(ModItems.ALUMINUM_DUST.getKey());
        this.tag(DUSTS_TIN)
                .add(ModItems.TIN_DUST.getKey());
        this.tag(DUSTS_BRONZE)
                .add(ModItems.BRONZE_DUST.getKey());
        this.tag(DUSTS_SILVER)
                .add(ModItems.SILVER_DUST.getKey());
        this.tag(DUSTS_ELECTRUM)
                .add(ModItems.ELECTRUM_DUST.getKey());
        this.tag(DUSTS_NICKEL)
                .add(ModItems.NICKEL_DUST.getKey());
        this.tag(DUSTS_INVAR)
                .add(ModItems.INVAR_DUST.getKey());
        this.tag(DUSTS_CONSTANTAN)
                .add(ModItems.CONSTANTAN_DUST.getKey());
        this.tag(DUSTS_PLATINUM)
                .add(ModItems.PLATINUM_DUST.getKey());
        this.tag(DUSTS_STEEL)
                .add(ModItems.STEEL_DUST.getKey());
        this.tag(DUSTS_LITHIUM)
                .add(ModItems.LITHIUM_DUST.getKey());
        this.tag(DUSTS_MAGNESIUM)
                .add(ModItems.MAGNESIUM_DUST.getKey());
        this.tag(DUSTS_URANIUM)
                .add(ModItems.URANIUM_DUST.getKey());
        this.tag(DUSTS_LEAD)
                .add(ModItems.LEAD_DUST.getKey());
        this.tag(DUSTS_ZINC)
                .add(ModItems.ZINC_DUST.getKey());

        this.tag(PLATES)
                .addTag(PLATES_IRON)
                .addTag(PLATES_GOLD)
                .addTag(PLATES_COPPER)
                .addTag(PLATES_ALUMINUM)
                .addTag(PLATES_TIN)
                .addTag(PLATES_BRONZE)
                .addTag(PLATES_SILVER)
                .addTag(PLATES_ELECTRUM)
                .addTag(PLATES_NICKEL)
                .addTag(PLATES_INVAR)
                .addTag(PLATES_CONSTANTAN)
                .addTag(PLATES_PLATINUM)
                .addTag(PLATES_STEEL)
                .addTag(PLATES_LITHIUM)
                .addTag(PLATES_MAGNESIUM)
                .addTag(PLATES_URANIUM)
                .addTag(PLATES_LEAD)
                .addTag(PLATES_ZINC);
        this.tag(PLATES_IRON)
                .add(ModItems.IRON_PLATE.getKey());
        this.tag(PLATES_GOLD)
                .add(ModItems.GOLD_PLATE.getKey());
        this.tag(PLATES_COPPER)
                .add(ModItems.COPPER_PLATE.getKey());
        this.tag(PLATES_ALUMINUM)
                .add(ModItems.ALUMINUM_PLATE.getKey());
        this.tag(PLATES_TIN)
                .add(ModItems.TIN_PLATE.getKey());
        this.tag(PLATES_BRONZE)
                .add(ModItems.BRONZE_PLATE.getKey());
        this.tag(PLATES_SILVER)
                .add(ModItems.SILVER_PLATE.getKey());
        this.tag(PLATES_ELECTRUM)
                .add(ModItems.ELECTRUM_PLATE.getKey());
        this.tag(PLATES_NICKEL)
                .add(ModItems.NICKEL_PLATE.getKey());
        this.tag(PLATES_INVAR)
                .add(ModItems.INVAR_PLATE.getKey());
        this.tag(PLATES_CONSTANTAN)
                .add(ModItems.CONSTANTAN_PLATE.getKey());
        this.tag(PLATES_PLATINUM)
                .add(ModItems.PLATINUM_PLATE.getKey());
        this.tag(PLATES_STEEL)
                .add(ModItems.STEEL_PLATE.getKey());
        this.tag(PLATES_LITHIUM)
                .add(ModItems.LITHIUM_PLATE.getKey());
        this.tag(PLATES_MAGNESIUM)
                .add(ModItems.MAGNESIUM_PLATE.getKey());
        this.tag(PLATES_URANIUM)
                .add(ModItems.URANIUM_PLATE.getKey());
        this.tag(PLATES_LEAD)
                .add(ModItems.LEAD_PLATE.getKey());
        this.tag(PLATES_ZINC)
                .add(ModItems.ZINC_PLATE.getKey());

        this.tag(RODS)
                .addTag(RODS_IRON)
                .addTag(RODS_GOLD)
                .addTag(RODS_COPPER)
                .addTag(RODS_ALUMINUM)
                .addTag(RODS_TIN)
                .addTag(RODS_BRONZE)
                .addTag(RODS_SILVER)
                .addTag(RODS_ELECTRUM)
                .addTag(RODS_NICKEL)
                .addTag(RODS_INVAR)
                .addTag(RODS_CONSTANTAN)
                .addTag(RODS_PLATINUM)
                .addTag(RODS_STEEL)
                .addTag(RODS_LITHIUM)
                .addTag(RODS_MAGNESIUM)
                .addTag(RODS_URANIUM)
                .addTag(RODS_LEAD)
                .addTag(RODS_ZINC);
        this.tag(RODS_IRON)
                .add(ModItems.IRON_ROD.getKey());
        this.tag(RODS_GOLD)
                .add(ModItems.GOLD_ROD.getKey());
        this.tag(RODS_COPPER)
                .add(ModItems.COPPER_ROD.getKey());
        this.tag(RODS_ALUMINUM)
                .add(ModItems.ALUMINUM_ROD.getKey());
        this.tag(RODS_TIN)
                .add(ModItems.TIN_ROD.getKey());
        this.tag(RODS_BRONZE)
                .add(ModItems.BRONZE_ROD.getKey());
        this.tag(RODS_SILVER)
                .add(ModItems.SILVER_ROD.getKey());
        this.tag(RODS_ELECTRUM)
                .add(ModItems.ELECTRUM_ROD.getKey());
        this.tag(RODS_NICKEL)
                .add(ModItems.NICKEL_ROD.getKey());
        this.tag(RODS_INVAR)
                .add(ModItems.INVAR_ROD.getKey());
        this.tag(RODS_CONSTANTAN)
                .add(ModItems.CONSTANTAN_ROD.getKey());
        this.tag(RODS_PLATINUM)
                .add(ModItems.PLATINUM_ROD.getKey());
        this.tag(RODS_STEEL)
                .add(ModItems.STEEL_ROD.getKey());
        this.tag(RODS_LITHIUM)
                .add(ModItems.LITHIUM_ROD.getKey());
        this.tag(RODS_MAGNESIUM)
                .add(ModItems.MAGNESIUM_ROD.getKey());
        this.tag(RODS_URANIUM)
                .add(ModItems.URANIUM_ROD.getKey());
        this.tag(RODS_LEAD)
                .add(ModItems.LEAD_ROD.getKey());
        this.tag(RODS_ZINC)
                .add(ModItems.ZINC_ROD.getKey());

        this.tag(GEARS)
                .addTag(GEARS_IRON)
                .addTag(GEARS_GOLD)
                .addTag(GEARS_COPPER)
                .addTag(GEARS_ALUMINUM)
                .addTag(GEARS_TIN)
                .addTag(GEARS_BRONZE)
                .addTag(GEARS_SILVER)
                .addTag(GEARS_ELECTRUM)
                .addTag(GEARS_NICKEL)
                .addTag(GEARS_INVAR)
                .addTag(GEARS_CONSTANTAN)
                .addTag(GEARS_PLATINUM)
                .addTag(GEARS_STEEL)
                .addTag(GEARS_LITHIUM)
                .addTag(GEARS_MAGNESIUM)
                .addTag(GEARS_URANIUM)
                .addTag(GEARS_LEAD)
                .addTag(GEARS_ZINC);
        this.tag(GEARS_IRON)
                .add(ModItems.IRON_GEAR.getKey());
        this.tag(GEARS_GOLD)
                .add(ModItems.GOLD_GEAR.getKey());
        this.tag(GEARS_COPPER)
                .add(ModItems.COPPER_GEAR.getKey());
        this.tag(GEARS_ALUMINUM)
                .add(ModItems.ALUMINUM_GEAR.getKey());
        this.tag(GEARS_TIN)
                .add(ModItems.TIN_GEAR.getKey());
        this.tag(GEARS_BRONZE)
                .add(ModItems.BRONZE_GEAR.getKey());
        this.tag(GEARS_SILVER)
                .add(ModItems.SILVER_GEAR.getKey());
        this.tag(GEARS_ELECTRUM)
                .add(ModItems.ELECTRUM_GEAR.getKey());
        this.tag(GEARS_NICKEL)
                .add(ModItems.NICKEL_GEAR.getKey());
        this.tag(GEARS_INVAR)
                .add(ModItems.INVAR_GEAR.getKey());
        this.tag(GEARS_CONSTANTAN)
                .add(ModItems.CONSTANTAN_GEAR.getKey());
        this.tag(GEARS_PLATINUM)
                .add(ModItems.PLATINUM_GEAR.getKey());
        this.tag(GEARS_STEEL)
                .add(ModItems.STEEL_GEAR.getKey());
        this.tag(GEARS_LITHIUM)
                .add(ModItems.LITHIUM_GEAR.getKey());
        this.tag(GEARS_MAGNESIUM)
                .add(ModItems.MAGNESIUM_GEAR.getKey());
        this.tag(GEARS_URANIUM)
                .add(ModItems.URANIUM_GEAR.getKey());
        this.tag(GEARS_LEAD)
                .add(ModItems.LEAD_GEAR.getKey());
        this.tag(GEARS_ZINC)
                .add(ModItems.ZINC_GEAR.getKey());

        this.tag(ARMORS)
                .add(ModArmors.GEMSTONE_HELMET.getKey())
                .add(ModArmors.GEMSTONE_CHESTPLATE.getKey())
                .add(ModArmors.GEMSTONE_LEGGINGS.getKey())
                .add(ModArmors.GEMSTONE_BOOTS.getKey())
                .addTag(ARMOR_COPPER)
                .addTag(ARMOR_ALUMINUM)
                .addTag(ARMOR_SILVER)
                .addTag(ARMOR_BRONZE)
                .addTag(ARMOR_INVAR)
                .addTag(ARMOR_STEEL);
        this.tag(ARMORS_HELMETS)
                .add(ModArmors.GEMSTONE_HELMET.getKey())
                .addTag(ARMOR_HELMETS_COPPER)
                .addTag(ARMOR_HELMETS_ALUMINUM)
                .addTag(ARMOR_HELMETS_BRONZE)
                .addTag(ARMOR_HELMETS_SILVER)
                .addTag(ARMOR_HELMETS_INVAR)
                .addTag(ARMOR_HELMETS_STEEL);
        this.tag(ARMORS_CHESTPLATES)
                .add(ModArmors.GEMSTONE_CHESTPLATE.getKey())
                .addTag(ARMOR_CHESTPLATES_COPPER)
                .addTag(ARMOR_CHESTPLATES_ALUMINUM)
                .addTag(ARMOR_CHESTPLATES_BRONZE)
                .addTag(ARMOR_CHESTPLATES_SILVER)
                .addTag(ARMOR_CHESTPLATES_INVAR)
                .addTag(ARMOR_CHESTPLATES_STEEL);
        this.tag(ARMORS_LEGGINGS)
                .add(ModArmors.GEMSTONE_LEGGINGS.getKey())
                .addTag(ARMOR_LEGGINGS_COPPER)
                .addTag(ARMOR_LEGGINGS_ALUMINUM)
                .addTag(ARMOR_LEGGINGS_BRONZE)
                .addTag(ARMOR_LEGGINGS_SILVER)
                .addTag(ARMOR_LEGGINGS_INVAR)
                .addTag(ARMOR_LEGGINGS_STEEL);
        this.tag(ARMORS_BOOTS)
                .add(ModArmors.GEMSTONE_BOOTS.getKey())
                .addTag(ARMOR_BOOTS_COPPER)
                .addTag(ARMOR_BOOTS_ALUMINUM)
                .addTag(ARMOR_BOOTS_BRONZE)
                .addTag(ARMOR_BOOTS_SILVER)
                .addTag(ARMOR_BOOTS_SILVER)
                .addTag(ARMOR_BOOTS_INVAR)
                .addTag(ARMOR_BOOTS_STEEL);

        this.tag(TRIMMABLE_ARMOR)
                .add(ModArmors.GEMSTONE_HELMET.getKey())
                .add(ModArmors.GEMSTONE_CHESTPLATE.getKey())
                .add(ModArmors.GEMSTONE_LEGGINGS.getKey())
                .add(ModArmors.GEMSTONE_BOOTS.getKey())
                .addTag(ARMOR_COPPER)
                .addTag(ARMOR_ALUMINUM)
                .addTag(ARMOR_BRONZE)
                .addTag(ARMOR_SILVER)
                .addTag(ARMOR_INVAR)
                .addTag(ARMOR_STEEL);
        this.tag(TRIM_MATERIALS)
                .add(ModItems.ALUMINUM_INGOT.getKey())
                .add(ModItems.TIN_INGOT.getKey())
                .add(ModItems.BRONZE_INGOT.getKey())
                .add(ModItems.SILVER_INGOT.getKey())
                .add(ModItems.ELECTRUM_INGOT.getKey())
                .add(ModItems.NICKEL_INGOT.getKey())
                .add(ModItems.INVAR_INGOT.getKey())
                .add(ModItems.CONSTANTAN_INGOT.getKey())
                .add(ModItems.PLATINUM_INGOT.getKey())
                .add(ModItems.STEEL_INGOT.getKey())
                .add(ModItems.LITHIUM_INGOT.getKey())
                .add(ModItems.MAGNESIUM_INGOT.getKey())
                .add(ModItems.URANIUM_INGOT.getKey())
                .add(ModItems.LEAD_INGOT.getKey())
                .add(ModItems.ZINC_INGOT.getKey())

                .add(ModItems.RUBY.getKey())
                .add(ModItems.SAPPHIRE.getKey())
                .add(ModItems.AQUAMARINE.getKey())
                .add(ModItems.JADE.getKey())
                .add(ModItems.OPAL.getKey())
                .add(ModItems.YELLOW_DIAMOND.getKey())
                .add(ModItems.AMBER.getKey())
                .add(ModItems.TOPAZ.getKey())
                .add(ModItems.BERYLLIUM.getKey())
                .add(ModItems.BIXBIT.getKey())
                .add(ModItems.MALACHITE.getKey())
                .add(ModItems.ONYX.getKey())
                .add(ModItems.PERIDOT.getKey())
                .add(ModItems.MOON_STONE.getKey())
                .add(ModItems.SUN_STONE.getKey())
                .add(ModItems.CITRINE.getKey())
                .add(ModItems.DOLOMITE.getKey())
                .add(ModItems.TANZANITE.getKey());

        this.tag(ARMOR_COPPER)
                .addTag(ARMOR_HELMETS_COPPER)
                .addTag(ARMOR_CHESTPLATES_COPPER)
                .addTag(ARMOR_LEGGINGS_COPPER)
                .addTag(ARMOR_BOOTS_COPPER);
        this.tag(ARMOR_ALUMINUM)
                .addTag(ARMOR_HELMETS_ALUMINUM)
                .addTag(ARMOR_CHESTPLATES_ALUMINUM)
                .addTag(ARMOR_LEGGINGS_ALUMINUM)
                .addTag(ARMOR_BOOTS_ALUMINUM);
        this.tag(ARMOR_BRONZE)
                .addTag(ARMOR_HELMETS_BRONZE)
                .addTag(ARMOR_CHESTPLATES_BRONZE)
                .addTag(ARMOR_LEGGINGS_BRONZE)
                .addTag(ARMOR_BOOTS_BRONZE);
        this.tag(ARMOR_SILVER)
                .addTag(ARMOR_HELMETS_SILVER)
                .addTag(ARMOR_CHESTPLATES_SILVER)
                .addTag(ARMOR_LEGGINGS_SILVER)
                .addTag(ARMOR_BOOTS_SILVER);
        this.tag(ARMOR_INVAR)
                .addTag(ARMOR_HELMETS_INVAR)
                .addTag(ARMOR_CHESTPLATES_INVAR)
                .addTag(ARMOR_LEGGINGS_INVAR)
                .addTag(ARMOR_BOOTS_INVAR);
        this.tag(ARMOR_STEEL)
                .addTag(ARMOR_HELMETS_STEEL)
                .addTag(ARMOR_CHESTPLATES_STEEL)
                .addTag(ARMOR_LEGGINGS_STEEL)
                .addTag(ARMOR_BOOTS_STEEL);

        this.tag(ARMOR_HELMETS_COPPER)
                .add(ModArmors.COPPER_HELMET.getKey());
        this.tag(ARMOR_CHESTPLATES_COPPER)
                .add(ModArmors.COPPER_CHESTPLATE.getKey());
        this.tag(ARMOR_LEGGINGS_COPPER)
                .add(ModArmors.COPPER_LEGGINGS.getKey());
        this.tag(ARMOR_BOOTS_COPPER)
                .add(ModArmors.COPPER_BOOTS.getKey());

        this.tag(ARMOR_HELMETS_ALUMINUM)
                .add(ModArmors.ALUMINUM_HELMET.getKey());
        this.tag(ARMOR_CHESTPLATES_ALUMINUM)
                .add(ModArmors.ALUMINUM_CHESTPLATE.getKey());
        this.tag(ARMOR_LEGGINGS_ALUMINUM)
                .add(ModArmors.ALUMINUM_LEGGINGS.getKey());
        this.tag(ARMOR_BOOTS_ALUMINUM)
                .add(ModArmors.ALUMINUM_BOOTS.getKey());

        this.tag(ARMOR_HELMETS_BRONZE)
                .add(ModArmors.BRONZE_HELMET.getKey());
        this.tag(ARMOR_CHESTPLATES_BRONZE)
                .add(ModArmors.BRONZE_CHESTPLATE.getKey());
        this.tag(ARMOR_LEGGINGS_BRONZE)
                .add(ModArmors.BRONZE_LEGGINGS.getKey());
        this.tag(ARMOR_BOOTS_BRONZE)
                .add(ModArmors.BRONZE_BOOTS.getKey());

        this.tag(ARMOR_HELMETS_SILVER)
                .add(ModArmors.SILVER_HELMET.getKey());
        this.tag(ARMOR_CHESTPLATES_SILVER)
                .add(ModArmors.SILVER_CHESTPLATE.getKey());
        this.tag(ARMOR_LEGGINGS_SILVER)
                .add(ModArmors.SILVER_LEGGINGS.getKey());
        this.tag(ARMOR_BOOTS_SILVER)
                .add(ModArmors.SILVER_BOOTS.getKey());

        this.tag(ARMOR_HELMETS_INVAR)
                .add(ModArmors.INVAR_HELMET.getKey());
        this.tag(ARMOR_CHESTPLATES_INVAR)
                .add(ModArmors.INVAR_CHESTPLATE.getKey());
        this.tag(ARMOR_LEGGINGS_INVAR)
                .add(ModArmors.INVAR_LEGGINGS.getKey());
        this.tag(ARMOR_BOOTS_INVAR)
                .add(ModArmors.INVAR_BOOTS.getKey());

        this.tag(ARMOR_HELMETS_STEEL)
                .add(ModArmors.STEEL_HELMET.getKey());
        this.tag(ARMOR_CHESTPLATES_STEEL)
                .add(ModArmors.STEEL_CHESTPLATE.getKey());
        this.tag(ARMOR_LEGGINGS_STEEL)
                .add(ModArmors.STEEL_LEGGINGS.getKey());
        this.tag(ARMOR_BOOTS_STEEL)
                .add(ModArmors.STEEL_BOOTS.getKey());

        this.tag(ARROWS)
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.RUBY_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.SAPPHIRE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.AQUAMARINE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.JADE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.OPAL_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.YELLOW_DIAMOND_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.AMBER_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.TOPAZ_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.BERYLLIUM_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.BIXBIT_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.MALACHITE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.ONYX_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.PERIDOT_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.MOON_STONE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.SUN_STONE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CITRINE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.DOLOMITE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.TANZANITE_ARROW.get()).get())

                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_RUBY_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_SAPPHIRE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_AQUAMARINE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_JADE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_OPAL_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_YELLOW_DIAMOND_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_AMBER_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_TOPAZ_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_BERYLLIUM_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_BIXBIT_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_MALACHITE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_ONYX_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_PERIDOT_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_MOON_STONE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_SUN_STONE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_CITRINE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_DOLOMITE_ARROW.get()).get())
                .add(ForgeRegistries.ITEMS.getResourceKey(ModItems.CHARGED_TANZANITE_ARROW.get()).get());
    }
}
