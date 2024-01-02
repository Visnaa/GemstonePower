package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.data.gen.provider.TrimMaterialsProvider;
import com.visnaa.gemstonepower.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

public class TrimMaterialGenerator extends TrimMaterialsProvider
{
    public TrimMaterialGenerator(PackOutput output)
    {
        super(output, GemstonePower.MOD_ID);
    }

    @Override
    protected void addTrimMaterials()
    {
        this.trim("ruby", Tints.RUBY.getColor(), ModItems.RUBY.get(), 1.1F);
        this.trim("sapphire", Tints.SAPPHIRE.getColor(), ModItems.SAPPHIRE.get(), 1.2F);
        this.trim("aquamarine", Tints.AQUAMARINE.getColor(), ModItems.AQUAMARINE.get(), 1.3F);
        this.trim("jade", Tints.JADE.getColor(), ModItems.JADE.get(), 1.4F);
        this.trim("opal", Tints.OPAL.getColor(), ModItems.OPAL.get(), 1.5F);
        this.trim("yellow_diamond", Tints.YELLOW_DIAMOND.getColor(), ModItems.YELLOW_DIAMOND.get(), 1.6F);
        this.trim("amber", Tints.AMBER.getColor(), ModItems.AMBER.get(), 1.7F);
        this.trim("topaz", Tints.TOPAZ.getColor(), ModItems.TOPAZ.get(), 1.8F);
        this.trim("beryllium", Tints.BERYLLIUM.getColor(), ModItems.BERYLLIUM.get(), 1.9F);
        this.trim("bixbit", Tints.BIXBIT.getColor(), ModItems.BIXBIT.get(), 2F);
        this.trim("malachite", Tints.MALACHITE.getColor(), ModItems.MALACHITE.get(), 2.1F);
        this.trim("onyx", Tints.ONYX.getColor(), ModItems.ONYX.get(), 2.2F);
        this.trim("peridot", Tints.PERIDOT.getColor(), ModItems.PERIDOT.get(), 2.3F);
        this.trim("moon_stone", Tints.MOON_STONE.getColor(), ModItems.MOON_STONE.get(), 2.4F);
        this.trim("sun_stone", Tints.SUN_STONE.getColor(), ModItems.SUN_STONE.get(), 2.5F);
        this.trim("citrine", Tints.CITRINE.getColor(), ModItems.CITRINE.get(), 2.6F);
        this.trim("dolomite", Tints.DOLOMITE.getColor(), ModItems.DOLOMITE.get(), 2.7F);
        this.trim("tanzanite", Tints.TANZANITE.getColor(), ModItems.TANZANITE.get(), 2.8F);

        this.trim("copper", Tints.COPPER.getColor(), "minecraft", Items.COPPER_INGOT, 2.9F, true);
        this.trim("aluminum", Tints.ALUMINUM.getColor(), GemstonePower.MOD_ID, ModItems.ALUMINUM_INGOT.get(), 3.0F, true);
        this.trim("tin", Tints.TIN.getColor(), ModItems.TIN_INGOT.get(), 3.1F);
        this.trim("bronze", Tints.BRONZE.getColor(), GemstonePower.MOD_ID, ModItems.BRONZE_INGOT.get(), 3.2F, true);
        this.trim("silver", Tints.SILVER.getColor(), GemstonePower.MOD_ID, ModItems.SILVER_INGOT.get(), 3.3F, true);
        this.trim("electrum", Tints.ELECTRUM.getColor(), ModItems.ELECTRUM_INGOT.get(), 3.4F);
        this.trim("nickel", Tints.NICKEL.getColor(), ModItems.NICKEL_INGOT.get(), 3.5F);
        this.trim("invar", Tints.INVAR.getColor(), GemstonePower.MOD_ID, ModItems.INVAR_INGOT.get(), 3.6F, true);
        this.trim("constantan", Tints.CONSTANTAN.getColor(), ModItems.CONSTANTAN_INGOT.get(), 3.7F);
        this.trim("platinum", Tints.PLATINUM.getColor(), ModItems.PLATINUM_INGOT.get(), 3.8F);
        this.trim("steel", Tints.STEEL.getColor(), GemstonePower.MOD_ID, ModItems.STEEL_INGOT.get(), 3.9F, true);
        this.trim("lithium", Tints.LITHIUM.getColor(), ModItems.LITHIUM_INGOT.get(), 4.0F);
        this.trim("magnesium", Tints.MAGNESIUM.getColor(), ModItems.MAGNESIUM_INGOT.get(), 4.1F);
        this.trim("uranium", Tints.URANIUM.getColor(), ModItems.URANIUM_INGOT.get(), 4.2F);
        this.trim("lead", Tints.LEAD.getColor(), ModItems.LEAD_INGOT.get(), 4.3F);
        this.trim("zinc", Tints.ZINC.getColor(), ModItems.ZINC_INGOT.get(), 4.4F);
    }
}
