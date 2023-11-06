package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.data.gen.builder.*;
import com.visnaa.gemstonepower.data.tag.CommonTags;
import com.visnaa.gemstonepower.init.*;
import com.visnaa.gemstonepower.item.CrystalArrowItem;
import com.visnaa.gemstonepower.item.metal.MetalGroup;
import com.visnaa.gemstonepower.item.metal.MetalGroups;
import com.visnaa.gemstonepower.util.MachineUtil.MachineModes;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.EnterBlockTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Optional;

public class RecipeGenerator extends RecipeProvider
{
    public RecipeGenerator(PackOutput output)
    {
        super(output);
    }

    @Override
    protected void buildRecipes(RecipeOutput output)
    {
        this.mics(output);

        this.gemstoneItems(output);
        this.machines(output);
        this.gemstoneGrower(output);
        this.gemstoneCharger(output);
        this.sawmill(output);
        this.polarizer(output);
        this.gemstoneManipulator(output);
        this.fissionReactor(output);

        this.upgrades(output);

        this.armour(output, Items.COPPER_INGOT, ModArmors.COPPER_HELMET.get(), ModArmors.COPPER_CHESTPLATE.get(), ModArmors.COPPER_LEGGINGS.get(), ModArmors.COPPER_BOOTS.get());
        this.armour(output, ModItems.ALUMINUM_INGOT.get(), ModArmors.ALUMINUM_HELMET.get(), ModArmors.ALUMINUM_CHESTPLATE.get(), ModArmors.ALUMINUM_LEGGINGS.get(), ModArmors.ALUMINUM_BOOTS.get());
        this.armour(output, ModItems.BRONZE_INGOT.get(), ModArmors.BRONZE_HELMET.get(), ModArmors.BRONZE_CHESTPLATE.get(), ModArmors.BRONZE_LEGGINGS.get(), ModArmors.BRONZE_BOOTS.get());
        this.armour(output, ModItems.SILVER_INGOT.get(), ModArmors.SILVER_HELMET.get(), ModArmors.SILVER_CHESTPLATE.get(), ModArmors.SILVER_LEGGINGS.get(), ModArmors.SILVER_BOOTS.get());
        this.armour(output, ModItems.INVAR_INGOT.get(), ModArmors.INVAR_HELMET.get(), ModArmors.INVAR_CHESTPLATE.get(), ModArmors.INVAR_LEGGINGS.get(), ModArmors.INVAR_BOOTS.get());
        this.armour(output, ModItems.STEEL_INGOT.get(), ModArmors.STEEL_HELMET.get(), ModArmors.STEEL_CHESTPLATE.get(), ModArmors.STEEL_LEGGINGS.get(), ModArmors.STEEL_BOOTS.get());

        this.tools(output, Items.COPPER_INGOT, ModTools.COPPER_SWORD.get(), ModTools.COPPER_SHOVEL.get(), ModTools.COPPER_PICKAXE.get(), ModTools.COPPER_AXE.get(), ModTools.COPPER_HOE.get());
        this.tools(output, ModItems.ALUMINUM_INGOT.get(), ModTools.ALUMINUM_SWORD.get(), ModTools.ALUMINUM_SHOVEL.get(), ModTools.ALUMINUM_PICKAXE.get(), ModTools.ALUMINUM_AXE.get(), ModTools.ALUMINUM_HOE.get());
        this.tools(output, ModItems.BRONZE_INGOT.get(), ModTools.BRONZE_SWORD.get(), ModTools.BRONZE_SHOVEL.get(), ModTools.BRONZE_PICKAXE.get(), ModTools.BRONZE_AXE.get(), ModTools.BRONZE_HOE.get());
        this.tools(output, ModItems.SILVER_INGOT.get(), ModTools.SILVER_SWORD.get(), ModTools.SILVER_SHOVEL.get(), ModTools.SILVER_PICKAXE.get(), ModTools.SILVER_AXE.get(), ModTools.SILVER_HOE.get());
        this.tools(output, ModItems.INVAR_INGOT.get(), ModTools.INVAR_SWORD.get(), ModTools.INVAR_SHOVEL.get(), ModTools.INVAR_PICKAXE.get(), ModTools.INVAR_AXE.get(), ModTools.INVAR_HOE.get());
        this.tools(output, ModItems.STEEL_INGOT.get(), ModTools.STEEL_SWORD.get(), ModTools.STEEL_SHOVEL.get(), ModTools.STEEL_PICKAXE.get(), ModTools.STEEL_AXE.get(), ModTools.STEEL_HOE.get());

        this.metalDefault(MetalGroups.IRON.getGroup(), output);
        this.metalDefault(MetalGroups.GOLD.getGroup(), output);
        this.metalDefault(MetalGroups.COPPER.getGroup(), output);
        this.metalDefault(MetalGroups.ALUMINUM.getGroup(), output);
        this.metalDefault(MetalGroups.TIN.getGroup(), output);
        this.metalDefault(MetalGroups.BRONZE.getGroup(), output);
        this.metalDefault(MetalGroups.SILVER.getGroup(), output);
        this.metalDefault(MetalGroups.ELECTRUM.getGroup(), output);
        this.metalDefault(MetalGroups.NICKEL.getGroup(), output);
        this.metalDefault(MetalGroups.INVAR.getGroup(), output);
        this.metalDefault(MetalGroups.CONSTANTAN.getGroup(), output);
        this.metalDefault(MetalGroups.PLATINUM.getGroup(), output);
        this.metalDefault(MetalGroups.STEEL.getGroup(), output);
        this.metalDefault(MetalGroups.LITHIUM.getGroup(), output);
        this.metalDefault(MetalGroups.MAGNESIUM.getGroup(), output);
        this.metalDefault(MetalGroups.URANIUM.getGroup(), output);
        this.metalDefault(MetalGroups.LEAD.getGroup(), output);
        this.metalDefault(MetalGroups.ZINC.getGroup(), output);

        this.pipes(output);
        
        this.crystalArrows(output, ModItems.RUBY.get(), ModItems.RUBY_CHARGED.get(), ModItems.RUBY_ARROW.get(), ModItems.CHARGED_RUBY_ARROW.get());
        this.crystalArrows(output, ModItems.SAPPHIRE.get(), ModItems.SAPPHIRE_CHARGED.get(), ModItems.SAPPHIRE_ARROW.get(), ModItems.CHARGED_SAPPHIRE_ARROW.get());
        this.crystalArrows(output, ModItems.AQUAMARINE.get(), ModItems.AQUAMARINE_CHARGED.get(), ModItems.AQUAMARINE_ARROW.get(), ModItems.CHARGED_AQUAMARINE_ARROW.get());
        this.crystalArrows(output, ModItems.JADE.get(), ModItems.JADE_CHARGED.get(), ModItems.JADE_ARROW.get(), ModItems.CHARGED_JADE_ARROW.get());
        this.crystalArrows(output, ModItems.OPAL.get(), ModItems.OPAL_CHARGED.get(), ModItems.OPAL_ARROW.get(), ModItems.CHARGED_OPAL_ARROW.get());
        this.crystalArrows(output, ModItems.YELLOW_DIAMOND.get(), ModItems.YELLOW_DIAMOND_CHARGED.get(), ModItems.YELLOW_DIAMOND_ARROW.get(), ModItems.CHARGED_YELLOW_DIAMOND_ARROW.get());
        this.crystalArrows(output, ModItems.AMBER.get(), ModItems.AMBER_CHARGED.get(), ModItems.AMBER_ARROW.get(), ModItems.CHARGED_AMBER_ARROW.get());
        this.crystalArrows(output, ModItems.TOPAZ.get(), ModItems.TOPAZ_CHARGED.get(), ModItems.TOPAZ_ARROW.get(), ModItems.CHARGED_TOPAZ_ARROW.get());
        this.crystalArrows(output, ModItems.BERYLLIUM.get(), ModItems.BERYLLIUM_CHARGED.get(), ModItems.BERYLLIUM_ARROW.get(), ModItems.CHARGED_BERYLLIUM_ARROW.get());
        this.crystalArrows(output, ModItems.BIXBIT.get(), ModItems.BIXBIT_CHARGED.get(), ModItems.BIXBIT_ARROW.get(), ModItems.CHARGED_BIXBIT_ARROW.get());
        this.crystalArrows(output, ModItems.MALACHITE.get(), ModItems.MALACHITE_CHARGED.get(), ModItems.MALACHITE_ARROW.get(), ModItems.CHARGED_MALACHITE_ARROW.get());
        this.crystalArrows(output, ModItems.ONYX.get(), ModItems.ONYX_CHARGED.get(), ModItems.ONYX_ARROW.get(), ModItems.CHARGED_ONYX_ARROW.get());
        this.crystalArrows(output, ModItems.PERIDOT.get(), ModItems.PERIDOT_CHARGED.get(), ModItems.PERIDOT_ARROW.get(), ModItems.CHARGED_PERIDOT_ARROW.get());
        this.crystalArrows(output, ModItems.MOON_STONE.get(), ModItems.MOON_STONE_CHARGED.get(), ModItems.MOON_STONE_ARROW.get(), ModItems.CHARGED_MOON_STONE_ARROW.get());
        this.crystalArrows(output, ModItems.SUN_STONE.get(), ModItems.SUN_STONE_CHARGED.get(), ModItems.SUN_STONE_ARROW.get(), ModItems.CHARGED_SUN_STONE_ARROW.get());
        this.crystalArrows(output, ModItems.CITRINE.get(), ModItems.CITRINE_CHARGED.get(), ModItems.CITRINE_ARROW.get(), ModItems.CHARGED_CITRINE_ARROW.get());
        this.crystalArrows(output, ModItems.DOLOMITE.get(), ModItems.DOLOMITE_CHARGED.get(), ModItems.DOLOMITE_ARROW.get(), ModItems.CHARGED_DOLOMITE_ARROW.get());
        this.crystalArrows(output, ModItems.TANZANITE.get(), ModItems.TANZANITE_CHARGED.get(), ModItems.TANZANITE_ARROW.get(), ModItems.CHARGED_TANZANITE_ARROW.get());
    }

    private void mics(RecipeOutput output)
    {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemTags.LOGS), RecipeCategory.MISC, ModItems.RESIN.get(), 0.1F, 200)
                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.LOGS).build()))
                .save(output, getFileName(ModItems.RESIN.get()));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RESIN.get()), RecipeCategory.MISC, ModItems.RUBBER.get(), 0.1F, 200)
                .unlockedBy("has_resin", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RESIN.get()).build()))
                .save(output, getFileName(ModItems.RUBBER.get(), ModItems.RESIN.get()));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.COAL_DUST.get()), RecipeCategory.MISC, Items.COAL, 0.1F, 200)
                .unlockedBy(hasName(ModItems.COAL_DUST.get()), has(ModItems.COAL_DUST.get()))
                .save(output, getFileName(Items.COAL, ModItems.COAL_DUST.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LIGHT_GEMSTONE.get(), 1)
                .requires(ModItems.AQUAMARINE.get())
                .requires(ModItems.JADE.get())
                .requires(ModItems.AMBER.get())
                .requires(ModItems.TOPAZ.get())
                .requires(ModItems.PERIDOT.get())
                .requires(ModItems.MOON_STONE.get())
                .requires(ModItems.SUN_STONE.get())
                .requires(ModItems.CITRINE.get())
                .requires(ModItems.DOLOMITE.get())
                .unlockedBy("unlocked", insideOf(Blocks.AIR))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DARK_GEMSTONE.get(), 1)
                .requires(ModItems.RUBY.get())
                .requires(ModItems.SAPPHIRE.get())
                .requires(ModItems.OPAL.get())
                .requires(ModItems.YELLOW_DIAMOND.get())
                .requires(ModItems.BERYLLIUM.get())
                .requires(ModItems.BIXBIT.get())
                .requires(ModItems.MALACHITE.get())
                .requires(ModItems.ONYX.get())
                .requires(ModItems.TANZANITE.get())
                .unlockedBy("unlocked", insideOf(Blocks.AIR))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FORTUNE_CRYSTAL.get(), 1)
                .pattern("LDL")
                .pattern("DSD")
                .pattern("LDL")
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .define('S', Tags.Items.NETHER_STARS)
                .unlockedBy("has_nether_star", inventoryTrigger(ItemPredicate.Builder.item().of(Tags.Items.NETHER_STARS).build()))
                .save(output, getFileName(ModItems.FORTUNE_CRYSTAL.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_OF_KNOWLEDGE.get(), 1)
                .pattern(" C ")
                .pattern("CGC")
                .pattern(" C ")
                .define('C', Tags.Items.COBBLESTONE)
                .define('G', ModItems.LIGHT_GEMSTONE.get())
                .unlockedBy("unlocked", insideOf(Blocks.AIR))
                .save(output, getFileName(ModItems.STONE_OF_KNOWLEDGE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_OF_OBLIVION.get(), 1)
                .pattern(" C ")
                .pattern("CGC")
                .pattern(" C ")
                .define('C', Tags.Items.COBBLESTONE)
                .define('G', ModItems.DARK_GEMSTONE.get())
                .unlockedBy("unlocked", insideOf(Blocks.AIR))
                .save(output, getFileName(ModItems.STONE_OF_OBLIVION.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.METAL_WRENCH.get(), 1)
                .pattern("I I")
                .pattern("III")
                .pattern(" S ")
                .define('I', Tags.Items.INGOTS)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_ingots", inventoryTrigger(ItemPredicate.Builder.item().of(Tags.Items.INGOTS).build()))
                .save(output, getFileName(ModItems.METAL_WRENCH.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CRYSTAL_WRENCH.get(), 1)
                .pattern("G G")
                .pattern("GGG")
                .pattern(" S ")
                .define('G', ModTags.GEMS)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_gems", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.GEMS).build()))
                .save(output, getFileName(ModItems.CRYSTAL_WRENCH.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PORTABLE_TANK.get(), 1)
                .pattern("IBI")
                .pattern("IGI")
                .pattern("IBI")
                .define('G', Tags.Items.GLASS)
                .define('B', Items.BUCKET)
                .define('I', Tags.Items.INGOTS_IRON)
                .unlockedBy("has_bucket", inventoryTrigger(ItemPredicate.Builder.item().of(Items.BUCKET).build()))
                .save(output, getFileName(ModItems.PORTABLE_TANK.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TREE_TAP.get(), 1)
                .pattern(" I ")
                .pattern("PPP")
                .pattern("  P")
                .define('P', ItemTags.PLANKS)
                .define('I', Tags.Items.INGOTS_IRON)
                .unlockedBy("has_iron_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(Tags.Items.INGOTS_IRON).build()))
                .save(output, getFileName(ModItems.TREE_TAP.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, 4)
                .requires(ModItems.RESIN_OAK_LOG.get())
                .unlockedBy(hasName(ModItems.RESIN_OAK_LOG.get()), has(ModItems.RESIN_OAK_LOG.get()))
                .save(output, getFileName(Items.OAK_PLANKS, ModItems.RESIN_OAK_LOG.get()));

        GemstoneGeneratorRecipeBuilder.create(Ingredient.of(ModItems.AZURITE_CRYSTAL.get()), 40, 1600)
                .unlockedBy("has_azurite_crystal", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.AZURITE_CRYSTAL.get()).build()))
                .save(output, getFileName(ModItems.AZURITE_CRYSTAL.get()));
        GemstoneGeneratorRecipeBuilder.create(Ingredient.of(ModItems.CUPRITE_CRYSTAL.get()), 40, 1600)
                .unlockedBy("has_cuprite_crystal", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.CUPRITE_CRYSTAL.get()).build()))
                .save(output, getFileName(ModItems.CUPRITE_CRYSTAL.get()));
        GemstoneGeneratorRecipeBuilder.create(Ingredient.of(ItemTags.LOGS_THAT_BURN), 40, 1600)
                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.LOGS_THAT_BURN).build()))
                .save(output, ItemTags.LOGS_THAT_BURN.location());
        GemstoneGeneratorRecipeBuilder.create(Ingredient.of(ItemTags.COALS), 40, 3200)
                .unlockedBy("has_coals", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.COALS).build()))
                .save(output, ItemTags.COALS.location());
        GemstoneGeneratorRecipeBuilder.create(Ingredient.of(Items.COAL_BLOCK), 380, 15200)
                .unlockedBy("has_coal_block", inventoryTrigger(ItemPredicate.Builder.item().of(Items.COAL_BLOCK).build()))
                .save(output, getFileName(Items.COAL_BLOCK));

        PulverizerRecipeBuilder.create(Ingredient.of(Items.COAL), ModItems.COAL_DUST.get(), 1, 200, 40)
                .unlockedBy(hasName(Items.COAL), has(Items.COAL))
                .save(output, getFileName(ModItems.COAL_DUST.get(), Items.COAL));
        PulverizerRecipeBuilder.create(Ingredient.of(Blocks.COAL_BLOCK), ModItems.COAL_DUST.get(), 9, 1600, 40)
                .unlockedBy(hasName(net.minecraft.world.level.block.Blocks.COPPER_BLOCK), has(net.minecraft.world.level.block.Blocks.COPPER_BLOCK))
                .save(output, getFileName(ModItems.COAL_DUST.get(), net.minecraft.world.level.block.Blocks.COPPER_BLOCK));

        ExtractorRecipeBuilder.create(Ingredient.of(ItemTags.LOGS), ModItems.RESIN.get(), 1, 200, 40)
                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.LOGS).build()))
                .save(output);

        ExtractorRecipeBuilder.create(Ingredient.of(ModItems.RESIN_OAK_LOG.get()), ModItems.RESIN.get(), 5, 200, 40)
                .unlockedBy(hasName(ModItems.RESIN_OAK_LOG.get()), has(ModItems.RESIN_OAK_LOG.get()))
                .save(output, getFileName(ModItems.RESIN.get(), ModItems.RESIN_OAK_LOG.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TANK.get())
                .pattern("SGS")
                .pattern("GGG")
                .pattern("SGS")
                .define('G', Tags.Items.GLASS)
                .define('S', CommonTags.Items.INGOTS_STEEL)
                .unlockedBy(hasName(Blocks.GLASS), has(Blocks.GLASS))
                .save(output, getFileName(ModItems.TANK.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GEMSTONE_CABLE.get(), 2)
                .pattern("GGG")
                .pattern("###")
                .pattern("GGG")
                .define('#', ModTags.GEMSTONE)
                .define('G', Tags.Items.GLASS)
                .unlockedBy("has_gemstones", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.GEMSTONE).build()))
                .save(output, getFileName(ModItems.GEMSTONE_CABLE.get()));
    }

    private void metalDefault(MetalGroup group, RecipeOutput output)
    {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getIngot(), 9)
                .requires(group.getBlock(), 1)
                .unlockedBy(hasName(group.getBlock()), has(group.getBlock()))
                .save(output, getFileName(group.getIngot(), group.getBlock()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getBlock(), 1)
                .requires(group.getIngot(), 9)
                .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                .save(output, getFileName(group.getBlock(), group.getIngot()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getIngot(), 1)
                .requires(group.getNugget(), 9)
                .unlockedBy(hasName(group.getNugget()), has(group.getNugget()))
                .save(output, getFileName(group.getIngot(), group.getNugget()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getNugget(), 9)
                .requires(group.getIngot(), 1)
                .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                .save(output, getFileName(group.getNugget(), group.getIngot()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getDust(), 1)
                .requires(group.getTinyPile(), 9)
                .unlockedBy(hasName(group.getTinyPile()), has(group.getTinyPile()))
                .save(output, getFileName(group.getDust(), group.getTinyPile()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getTinyPile(), 9)
                .requires(group.getDust(), 1)
                .unlockedBy(hasName(group.getDust()), has(group.getDust()))
                .save(output, getFileName(group.getTinyPile(), group.getDust()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, group.getGear(), 1)
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .define('#', group.getIngot())
                .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                .save(output, getFileName(group.getGear(), group.getIngot()));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(group.getDust()), RecipeCategory.MISC, group.getIngot(), 0.3F, 200)
                .unlockedBy(hasName(group.getDust()), has(group.getDust()))
                .save(output, getFileName(group.getIngot(), group.getDust()) + "_smelting");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(group.getTinyPile()), RecipeCategory.MISC, group.getNugget(), 0.3F, 200)
                .unlockedBy(hasName(group.getTinyPile()), has(group.getTinyPile()))
                .save(output, getFileName(group.getNugget(), group.getTinyPile()) + "_smelting");

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(group.getDust()), RecipeCategory.MISC, group.getIngot(), 0.3F, 150)
                .unlockedBy(hasName(group.getDust()), has(group.getDust()))
                .save(output, getFileName(group.getIngot(), group.getDust()) + "_blasting");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(group.getTinyPile()), RecipeCategory.MISC, group.getNugget(), 0.3F, 150)
                .unlockedBy(hasName(group.getTinyPile()), has(group.getTinyPile()))
                .save(output, getFileName(group.getNugget(), group.getTinyPile()) + "_blasting");

        PulverizerRecipeBuilder.create(Ingredient.of(group.getIngot()), group.getDust(), 1, 200, 40)
                .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                .save(output, getFileName(group.getDust(), group.getIngot()));
        PulverizerRecipeBuilder.create(Ingredient.of(group.getBlock()), group.getDust(), 9, 1600, 40)
                .unlockedBy(hasName(group.getBlock()), has(group.getBlock()))
                .save(output, getFileName(group.getDust(), group.getBlock()));
        PulverizerRecipeBuilder.create(Ingredient.of(group.getPlate()), group.getDust(), 1, 200, 40)
                .unlockedBy(hasName(group.getPlate()), has(group.getPlate()))
                .save(output, getFileName(group.getDust(), group.getPlate()));
        PulverizerRecipeBuilder.create(Ingredient.of(group.getRod()), group.getDust(), 1, 200, 40)
                .unlockedBy(hasName(group.getRod()), has(group.getRod()))
                .save(output, getFileName(group.getDust(), group.getRod()));
        PulverizerRecipeBuilder.create(Ingredient.of(group.getGear()), group.getDust(), 4, 200, 40)
                .unlockedBy(hasName(group.getGear()), has(group.getGear()))
                .save(output, getFileName(group.getDust(), group.getGear()));
        PulverizerRecipeBuilder.create(Ingredient.of(group.getNugget()), group.getTinyPile(), 1, 200, 40)
                .unlockedBy(hasName(group.getNugget()), has(group.getNugget()))
                .save(output, getFileName(group.getTinyPile(), group.getNugget()));

        MetalFormerRecipeBuilder.create(Ingredient.of(group.getIngot()), group.getPlate(), 1, MachineModes.PRESSING, 200, 40)
                .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                .save(output, getFileName(group.getPlate(), group.getIngot()));
        MetalFormerRecipeBuilder.create(Ingredient.of(group.getIngot()), group.getRod(), 1, MachineModes.ROLLING, 200, 40)
                .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                .save(output, getFileName(group.getRod(), group.getIngot()));

        if (group.getWire() != null && group.getCable() != null)
        {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, group.getWire(), 1)
                    .pattern("###")
                    .define('#', group.getIngot())
                    .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                    .save(output, getFileName(group.getWire(), group.getIngot()));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getCable(), 1)
                    .requires(group.getWire(), 1)
                    .requires(ModItems.RUBBER.get(), 1)
                    .unlockedBy(hasName(group.getWire()), has(group.getWire()))
                    .save(output, getFileName(group.getCable(), group.getWire(), ModItems.RUBBER.get()));

            MetalFormerRecipeBuilder.create(Ingredient.of(group.getIngot()), group.getWire().asItem(), 3, MachineModes.EXTRUDING, 200, 40)
                    .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                    .save(output, getFileName(group.getWire(), group.getIngot()));
        }

        if (!group.isAlloy())
        {
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(group.getOreStone()), RecipeCategory.MISC, group.getIngot(), 0.3F, 200)
                    .unlockedBy(hasName(group.getOreStone()), has(group.getOreStone()))
                    .save(output, getFileName(group.getIngot(), group.getOreStone()) + "_smelting");
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(group.getOreDeepslate()), RecipeCategory.MISC, group.getIngot(), 0.3F, 200)
                    .unlockedBy(hasName(group.getOreDeepslate()), has(group.getOreDeepslate()))
                    .save(output, getFileName(group.getIngot(), group.getOreDeepslate()) + "_smelting");
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(group.getRaw()), RecipeCategory.MISC, group.getIngot(), 0.3F, 200)
                    .unlockedBy(hasName(group.getRaw()), has(group.getRaw()))
                    .save(output, getFileName(group.getIngot(), group.getRaw()) + "_smelting");

            SimpleCookingRecipeBuilder.blasting(Ingredient.of(group.getOreStone()), RecipeCategory.MISC, group.getIngot(), 0.3F, 150)
                    .unlockedBy(hasName(group.getOreStone()), has(group.getOreStone()))
                    .save(output, getFileName(group.getIngot(), group.getOreStone()) + "_blasting");
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(group.getOreDeepslate()), RecipeCategory.MISC, group.getIngot(), 0.3F, 150)
                    .unlockedBy(hasName(group.getOreDeepslate()), has(group.getOreDeepslate()))
                    .save(output, getFileName(group.getIngot(), group.getOreDeepslate()) + "_blasting");
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(group.getRaw()), RecipeCategory.MISC, group.getIngot(), 0.3F, 150)
                    .unlockedBy(hasName(group.getRaw()), has(group.getRaw()))
                    .save(output, getFileName(group.getIngot(), group.getRaw()) + "_blasting");

            PulverizerRecipeBuilder.create(Ingredient.of(group.getOreStone()), group.getOreDust(), 1, 200, 40)
                    .unlockedBy(hasName(group.getOreStone()), has(group.getOreStone()))
                    .save(output, getFileName(group.getOreDust(), group.getOreStone()));
            PulverizerRecipeBuilder.create(Ingredient.of(group.getOreDeepslate()), group.getOreDust(), 1, 200, 40)
                    .unlockedBy(hasName(group.getOreDeepslate()), has(group.getOreDeepslate()))
                    .save(output, getFileName(group.getOreDust(), group.getOreDeepslate()));
            PulverizerRecipeBuilder.create(Ingredient.of(group.getRaw()), group.getOreDust(), 1, 200, 40)
                    .unlockedBy(hasName(group.getRaw()), has(group.getRaw()))
                    .save(output, getFileName(group.getOreDust(), group.getRaw()));

            OreWasherRecipeBuilder.create(Ingredient.of(group.getOreDust()), new FluidStack(Fluids.WATER, 250), group.getOreDustWashing(), new int[]{ 1, 1, 1, 1 }, 400, 40)
                    .unlockedBy(hasName(group.getOreDust()), has(group.getOreDust()))
                    .save(output, getFileName(group.getDust(), group.getOreDust()));
        }
        else
        {
            if (group.getAlloyIngots() != null)
            {
                AlloySmelterRecipeBuilder.create(List.of(Ingredient.of(group.getAlloyIngots().get(0)), Ingredient.of(group.getAlloyIngots().get(1))), group.getAmount1(), group.getAmount2(), group.getIngot(), group.getAmount(), 400, 80)
                        .unlockedBy(hasName(group.getAlloyIngots().get(0)), has(group.getAlloyIngots().get(0)))
                        .save(output, getFileName(group.getIngot(), group.getAlloyIngots().get(0), group.getAlloyIngots().get(1)));
            }
            if (group.getAlloyBlocks() != null)
            {
                AlloySmelterRecipeBuilder.create(List.of(Ingredient.of(group.getAlloyBlocks().get(0)), Ingredient.of(group.getAlloyBlocks().get(1))), group.getAmount1(), group.getAmount2(), group.getBlock().asItem(), group.getAmount(), 3400, 80)
                        .unlockedBy(hasName(group.getAlloyBlocks().get(0)), has(group.getAlloyBlocks().get(0)))
                        .save(output, getFileName(group.getIngot(), group.getAlloyBlocks().get(0), group.getAlloyBlocks().get(1)));
            }
            if (group.getAlloyNuggets() != null)
            {
                AlloySmelterRecipeBuilder.create(List.of(Ingredient.of(group.getAlloyNuggets().get(0)), Ingredient.of(group.getAlloyNuggets().get(1))), group.getAmount1(), group.getAmount2(), group.getNugget(), group.getAmount(), 400, 80)
                        .unlockedBy(hasName(group.getAlloyNuggets().get(0)), has(group.getAlloyNuggets().get(0)))
                        .save(output, getFileName(group.getIngot(), group.getAlloyNuggets().get(0), group.getAlloyNuggets().get(1)));
            }
            if (group.getAlloyDusts() != null)
            {
                AlloySmelterRecipeBuilder.create(List.of(Ingredient.of(group.getAlloyDusts().get(0)), Ingredient.of(group.getAlloyDusts().get(1))), group.getAmount1(), group.getAmount2(), group.getDust(), group.getAmount(), 400, 80)
                        .unlockedBy(hasName(group.getAlloyDusts().get(0)), has(group.getAlloyDusts().get(0)))
                        .save(output, getFileName(group.getIngot(), group.getAlloyDusts().get(0), group.getAlloyDusts().get(1)));
            }
            if (group.getAlloyTinyPiles() != null)
            {
                AlloySmelterRecipeBuilder.create(List.of(Ingredient.of(group.getAlloyTinyPiles().get(0)), Ingredient.of(group.getAlloyTinyPiles().get(1))), group.getAmount1(), group.getAmount2(), group.getTinyPile(), group.getAmount(), 400, 80)
                        .unlockedBy(hasName(group.getAlloyTinyPiles().get(0)), has(group.getAlloyTinyPiles().get(0)))
                        .save(output, getFileName(group.getIngot(), group.getAlloyTinyPiles().get(0), group.getAlloyTinyPiles().get(1)));
            }
        }
    }

    private void armour(RecipeOutput output, ItemLike ingredient, ItemLike helmet, ItemLike chestplate, ItemLike leggings, ItemLike boots)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
                .pattern("###")
                .pattern("# #")
                .define('#', ingredient)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(output, getFileName(helmet, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(output, getFileName(chestplate, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ingredient)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(output, getFileName(leggings, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
                .pattern("# #")
                .pattern("# #")
                .define('#', ingredient)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(output, getFileName(boots, ingredient));
    }

    private void tools(RecipeOutput output, ItemLike ingredient, ItemLike sword, ItemLike shovel, ItemLike pickaxe, ItemLike axe, ItemLike hoe)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
                .pattern("#")
                .pattern("#")
                .pattern("S")
                .define('#', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(output, getFileName(sword, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                .pattern("#")
                .pattern("S")
                .pattern("S")
                .define('#', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(output, getFileName(shovel, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .define('#', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(output, getFileName(pickaxe, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                .pattern("##")
                .pattern("S#")
                .pattern("S ")
                .define('#', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(output, getFileName(axe, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
                .pattern("##")
                .pattern("S ")
                .pattern("S ")
                .define('#', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(output, getFileName(hoe, ingredient));
    }

    private void pipes(RecipeOutput output)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_ITEM_PIPE.get(), 8)
                .pattern("I")
                .pattern("R")
                .pattern("I")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy(hasName(Items.IRON_INGOT), has(Tags.Items.INGOTS_IRON))
                .save(output, getFileName(ModItems.IRON_ITEM_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_ITEM_PIPE.get(), 8)
                .pattern("I")
                .pattern("R")
                .pattern("I")
                .define('I', Tags.Items.INGOTS_GOLD)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy(hasName(Items.GOLD_INGOT), has(Tags.Items.INGOTS_GOLD))
                .save(output, getFileName(ModItems.GOLD_ITEM_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_ITEM_PIPE.get(), 8)
                .pattern("I")
                .pattern("R")
                .pattern("I")
                .define('I', Tags.Items.INGOTS_COPPER)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy(hasName(Items.COPPER_INGOT), has(Tags.Items.INGOTS_COPPER))
                .save(output, getFileName(ModItems.COPPER_ITEM_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PLATINUM_ITEM_PIPE.get(), 8)
                .pattern("I")
                .pattern("R")
                .pattern("I")
                .define('I', CommonTags.Items.INGOTS_PLATINUM)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy(hasName(ModItems.PLATINUM_INGOT.get()), has(CommonTags.Items.INGOTS_PLATINUM))
                .save(output, getFileName(ModItems.PLATINUM_ITEM_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LEAD_ITEM_PIPE.get(), 8)
                .pattern("I")
                .pattern("R")
                .pattern("I")
                .define('I', CommonTags.Items.INGOTS_LEAD)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy(hasName(ModItems.LEAD_INGOT.get()), has(CommonTags.Items.INGOTS_LEAD))
                .save(output, getFileName(ModItems.LEAD_ITEM_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_FLUID_PIPE.get(), 8)
                .pattern("I")
                .pattern("B")
                .pattern("I")
                .define('I', Tags.Items.INGOTS_GOLD)
                .define('B', Items.BUCKET)
                .unlockedBy(hasName(Items.GOLD_INGOT), has(Tags.Items.INGOTS_GOLD))
                .save(output, getFileName(ModItems.GOLD_FLUID_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_FLUID_PIPE.get(), 8)
                .pattern("I")
                .pattern("B")
                .pattern("I")
                .define('I', Tags.Items.INGOTS_COPPER)
                .define('B', Items.BUCKET)
                .unlockedBy(hasName(Items.COPPER_INGOT), has(Tags.Items.INGOTS_COPPER))
                .save(output, getFileName(ModItems.COPPER_FLUID_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INVAR_FLUID_PIPE.get(), 8)
                .pattern("I")
                .pattern("B")
                .pattern("I")
                .define('I', CommonTags.Items.INGOTS_INVAR)
                .define('B', Items.BUCKET)
                .unlockedBy(hasName(ModItems.INVAR_INGOT.get()), has(CommonTags.Items.INGOTS_INVAR))
                .save(output, getFileName(ModItems.INVAR_FLUID_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_FLUID_PIPE.get(), 8)
                .pattern("I")
                .pattern("B")
                .pattern("I")
                .define('I', CommonTags.Items.INGOTS_STEEL)
                .define('B', Items.BUCKET)
                .unlockedBy(hasName(ModItems.STEEL_INGOT.get()), has(CommonTags.Items.INGOTS_STEEL))
                .save(output, getFileName(ModItems.STEEL_FLUID_PIPE.get()));
    }

    private void crystalArrows(RecipeOutput output, ItemLike crystal, ItemLike charged, CrystalArrowItem crystalArrow, CrystalArrowItem chargedArrow)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, crystalArrow, 1)
                .pattern("C")
                .pattern("S")
                .pattern("F")
                .define('C', crystal)
                .define('S', Tags.Items.RODS_WOODEN)
                .define('F', Items.FEATHER)
                .unlockedBy(hasName(crystal), has(crystal))
                .save(output, getFileName(crystalArrow, crystal));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chargedArrow, 1)
                .pattern("C")
                .pattern("S")
                .pattern("F")
                .define('C', charged)
                .define('S', Tags.Items.RODS_WOODEN)
                .define('F', Items.FEATHER)
                .unlockedBy(hasName(charged), has(charged))
                .save(output, getFileName(chargedArrow, charged));

        CrystalChargerRecipeBuilder.create(Ingredient.of(crystalArrow), chargedArrow, 1, 250, 120)
                .unlockedBy(hasName(crystalArrow), has(crystalArrow))
                .save(output, getFileName(chargedArrow, crystalArrow));
    }

    private void gemstoneItems(RecipeOutput output)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModArmors.GEMSTONE_HELMET.get(), 1)
                .pattern("LDL")
                .pattern("D D")
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .unlockedBy(hasName(ModItems.LIGHT_GEMSTONE.get()), has(ModItems.LIGHT_GEMSTONE.get()))
                .unlockedBy(hasName(ModItems.DARK_GEMSTONE.get()), has(ModItems.DARK_GEMSTONE.get()))
                .save(output, getFileName(ModArmors.GEMSTONE_HELMET.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModArmors.GEMSTONE_CHESTPLATE.get(), 1)
                .pattern("L L")
                .pattern("DLD")
                .pattern("DLD")
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .unlockedBy(hasName(ModItems.LIGHT_GEMSTONE.get()), has(ModItems.LIGHT_GEMSTONE.get()))
                .unlockedBy(hasName(ModItems.DARK_GEMSTONE.get()), has(ModItems.DARK_GEMSTONE.get()))
                .save(output, getFileName(ModArmors.GEMSTONE_CHESTPLATE.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModArmors.GEMSTONE_LEGGINGS.get(), 1)
                .pattern("DDD")
                .pattern("L L")
                .pattern("L L")
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .unlockedBy(hasName(ModItems.LIGHT_GEMSTONE.get()), has(ModItems.LIGHT_GEMSTONE.get()))
                .unlockedBy(hasName(ModItems.DARK_GEMSTONE.get()), has(ModItems.DARK_GEMSTONE.get()))
                .save(output, getFileName(ModArmors.GEMSTONE_LEGGINGS.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModArmors.GEMSTONE_BOOTS.get(), 1)
                .pattern("L L")
                .pattern("D D")
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .unlockedBy(hasName(ModItems.LIGHT_GEMSTONE.get()), has(ModItems.LIGHT_GEMSTONE.get()))
                .unlockedBy(hasName(ModItems.DARK_GEMSTONE.get()), has(ModItems.DARK_GEMSTONE.get()))
                .save(output, getFileName(ModArmors.GEMSTONE_BOOTS.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModTools.GEMSTONE_SWORD.get(), 1)
                .pattern("L")
                .pattern("D")
                .pattern("#")
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .define('#', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ModItems.LIGHT_GEMSTONE.get()), has(ModItems.LIGHT_GEMSTONE.get()))
                .unlockedBy(hasName(ModItems.DARK_GEMSTONE.get()), has(ModItems.DARK_GEMSTONE.get()))
                .unlockedBy(hasName(Items.STICK), has(Tags.Items.RODS_WOODEN))
                .save(output, getFileName(ModTools.GEMSTONE_SWORD.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get(), Items.STICK));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModTools.GEMSTONE_SHOVEL.get(), 1)
                .pattern("G")
                .pattern("#")
                .pattern("#")
                .define('G', ModTags.GEMSTONE)
                .define('#', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_gemstone", has(ModTags.GEMSTONE))
                .unlockedBy(hasName(Items.STICK), has(Tags.Items.RODS_WOODEN))
                .save(output, getFileName(ModTools.GEMSTONE_SHOVEL.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModTools.GEMSTONE_PICKAXE.get(), 1)
                .pattern("DLD")
                .pattern(" # ")
                .pattern(" # ")
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .define('#', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ModItems.LIGHT_GEMSTONE.get()), has(ModItems.LIGHT_GEMSTONE.get()))
                .unlockedBy(hasName(ModItems.DARK_GEMSTONE.get()), has(ModItems.DARK_GEMSTONE.get()))
                .unlockedBy(hasName(Items.STICK), has(Tags.Items.RODS_WOODEN))
                .save(output, getFileName(ModTools.GEMSTONE_PICKAXE.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get(), Items.STICK));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModTools.GEMSTONE_AXE.get(), 1)
                .pattern("LD")
                .pattern("#L")
                .pattern("# ")
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .define('#', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ModItems.LIGHT_GEMSTONE.get()), has(ModItems.LIGHT_GEMSTONE.get()))
                .unlockedBy(hasName(ModItems.DARK_GEMSTONE.get()), has(ModItems.DARK_GEMSTONE.get()))
                .unlockedBy(hasName(Items.STICK), has(Tags.Items.RODS_WOODEN))
                .save(output, getFileName(ModTools.GEMSTONE_AXE.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get(), Items.STICK));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModTools.GEMSTONE_HOE.get(), 1)
                .pattern("LD")
                .pattern("# ")
                .pattern("# ")
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .define('#', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ModItems.LIGHT_GEMSTONE.get()), has(ModItems.LIGHT_GEMSTONE.get()))
                .unlockedBy(hasName(ModItems.DARK_GEMSTONE.get()), has(ModItems.DARK_GEMSTONE.get()))
                .unlockedBy(hasName(Items.STICK), has(Tags.Items.RODS_WOODEN))
                .save(output, getFileName(ModTools.GEMSTONE_HOE.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get(), Items.STICK));
    }

    private void machines(RecipeOutput output)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GEMSTONE_GENERATOR.get(), 1)
                .pattern("ICI")
                .pattern("CBC")
                .pattern("ICI")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.COPPER_INGOT)
                .define('B', net.minecraft.world.level.block.Blocks.IRON_BLOCK)
                .unlockedBy("has_iron_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(Items.IRON_INGOT).build()))
                .save(output, getFileName(ModBlocks.GEMSTONE_GENERATOR.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GEMSTONE_CELL.get(), 1)
                .pattern("ICI")
                .pattern("CBC")
                .pattern("ICI")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.COPPER_INGOT)
                .define('B', net.minecraft.world.level.block.Blocks.COPPER_BLOCK)
                .unlockedBy("has_copper_block", inventoryTrigger(ItemPredicate.Builder.item().of(net.minecraft.world.level.block.Blocks.COPPER_BLOCK).build()))
                .save(output, getFileName(ModBlocks.GEMSTONE_CELL.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRYSTAL_GROWER.get(), 1)
                .pattern("SSS")
                .pattern("WGW")
                .pattern("SSS")
                .define('S', ModItems.SILVER_INGOT.get())
                .define('W', Items.WATER_BUCKET)
                .define('G', ModItems.GOLD_GEAR.get())
                .unlockedBy("has_gold_gear", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.GOLD_GEAR.get()).build()))
                .save(output, getFileName(ModBlocks.CRYSTAL_GROWER.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRYSTAL_CHARGER.get(), 1)
                .pattern("SRS")
                .pattern("CTC")
                .pattern("SGS")
                .define('S', ModItems.SILVER_INGOT.get())
                .define('T', ModBlocks.TIN_CABLE.get())
                .define('C', ModBlocks.COPPER_CABLE.get())
                .define('R', ModItems.RUBY.get())
                .define('G', ModBlocks.GEMSTONE_CELL.get())
                .unlockedBy("has_gemstone_cell", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.GEMSTONE_CELL.get()).build()))
                .save(output, getFileName(ModBlocks.CRYSTAL_CHARGER.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ELECTRIC_FURNACE.get(), 1)
                .pattern("IGI")
                .pattern("SFS")
                .pattern("ISI")
                .define('I', Items.IRON_INGOT)
                .define('G', Items.GOLD_INGOT)
                .define('S', ModItems.SILVER_INGOT.get())
                .define('F', net.minecraft.world.level.block.Blocks.FURNACE)
                .unlockedBy("has_furnace", inventoryTrigger(ItemPredicate.Builder.item().of(net.minecraft.world.level.block.Blocks.FURNACE).build()))
                .save(output, getFileName(ModBlocks.ELECTRIC_FURNACE.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.METAL_FORMER.get(), 1)
                .pattern("IRI")
                .pattern("CGB")
                .pattern("III")
                .define('I', Items.IRON_INGOT)
                .define('R', ModItems.RUBY.get())
                .define('C', ModItems.COPPER_GEAR.get())
                .define('G', ModItems.GOLD_GEAR.get())
                .define('B', ModItems.BRONZE_GEAR.get())
                .unlockedBy("has_ruby", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RUBY.get()).build()))
                .save(output, getFileName(ModBlocks.METAL_FORMER.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PULVERIZER.get(), 1)
                .pattern("PCP")
                .pattern("RDR")
                .pattern("PEP")
                .define('P', ModItems.IRON_PLATE.get())
                .define('C', Items.COPPER_INGOT)
                .define('R', ModItems.RUBY.get())
                .define('D', Items.DIAMOND)
                .define('E', ModItems.ELECTRUM_ROD.get())
                .unlockedBy("has_diamond", inventoryTrigger(ItemPredicate.Builder.item().of(Items.DIAMOND).build()))
                .save(output, getFileName(ModBlocks.PULVERIZER.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALLOY_SMELTER.get(), 1)
                .pattern("PIP")
                .pattern("SGS")
                .pattern("PEP")
                .define('P', ModItems.SILVER_PLATE.get())
                .define('I', ModItems.IRON_GEAR.get())
                .define('S', ModBlocks.SILVER_BLOCK.get())
                .define('G', net.minecraft.world.level.block.Blocks.GOLD_BLOCK)
                .define('E', ModItems.ELECTRUM_ROD.get())
                .unlockedBy("has_iron_gear", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.IRON_GEAR.get()).build()))
                .save(output, getFileName(ModBlocks.ALLOY_SMELTER.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EXTRACTOR.get(), 1)
                .pattern("PRP")
                .pattern("RCR")
                .pattern("PRP")
                .define('R', ModItems.RUBBER.get())
                .define('P', ModItems.PLATINUM_PLATE.get())
                .define('C', ModBlocks.COPPER_CABLE.get())
                .unlockedBy("has_rubber", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RUBBER.get()).build()))
                .save(output, getFileName(ModBlocks.EXTRACTOR.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ORE_WASHER.get(), 1)
                .pattern("IEI")
                .pattern("WWW")
                .pattern("IBI")
                .define('E', ModItems.PLATINUM_GEAR.get())
                .define('I', ModItems.INVAR_PLATE.get())
                .define('W', Items.WATER_BUCKET)
                .define('B', ModBlocks.INVAR_BLOCK.get())
                .unlockedBy("has_water_bucket", inventoryTrigger(ItemPredicate.Builder.item().of(Items.WATER_BUCKET).build()))
                .save(output, getFileName(ModBlocks.ORE_WASHER.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COBBLESTONE_GENERATOR.get(), 1)
                .pattern("ISI")
                .pattern("WGL")
                .pattern("ICI")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('S', CommonTags.Items.INGOTS_STEEL)
                .define('W', Items.WATER_BUCKET)
                .define('G', Tags.Items.GLASS)
                .define('L', Items.LAVA_BUCKET)
                .define('C', ModItems.COPPER_WIRE.get())
                .unlockedBy("has_copper_wire", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.COPPER_WIRE.get()).build()))
                .save(output, getFileName(ModItems.COBBLESTONE_GENERATOR.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAWMILL.get(), 1)
                .pattern("AAA")
                .pattern("GGG")
                .pattern("AEA")
                .define('A', CommonTags.Items.INGOTS_ALUMNUM)
                .define('G', CommonTags.Items.GEARS_STEEL)
                .define('E', CommonTags.Items.WIRES_ELECTRUM)
                .unlockedBy("has_aluminum_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(CommonTags.Items.INGOTS_ALUMNUM).build()))
                .save(output, getFileName(ModItems.SAWMILL.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.POLARIZER.get(), 1)
                .pattern("ZZZ")
                .pattern("INC")
                .pattern("WVW")
                .define('Z', CommonTags.Items.INGOTS_ZINC)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('N', CommonTags.Items.INGOTS_NICKEL)
                .define('C', CommonTags.Items.INGOTS_CONSTANTAN)
                .define('V', CommonTags.Items.INGOTS_INVAR)
                .define('W', ModBlocks.COPPER_WIRE.get())
                .unlockedBy("has_copper_wire", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.COPPER_WIRE.get()).build()))
                .save(output, getFileName(ModItems.POLARIZER.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GEMSTONE_MANIPULATOR.get(), 1)
                .pattern("LFL")
                .pattern("FRF")
                .pattern("DFD")
                .define('F', ModItems.FORTUNE_CRYSTAL.get())
                .define('R', ModItems.STEEL_ROD_POLARIZED.get())
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .unlockedBy("has_fortune_crystal", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.FORTUNE_CRYSTAL.get()).build()))
                .save(output, getFileName(ModItems.GEMSTONE_MANIPULATOR.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SOLAR_PANEL.get(), 1)
                .pattern("GGG")
                .pattern("LLL")
                .pattern("ICI")
                .define('C', ModItems.COPPER_WIRE.get())
                .define('G', Tags.Items.GLASS)
                .define('L', Tags.Items.GEMS_LAPIS)
                .define('I', Tags.Items.INGOTS_IRON)
                .unlockedBy("has_copper_wire", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.COPPER_WIRE.get()).build()))
                .save(output, getFileName(ModItems.SOLAR_PANEL.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WATER_MILL.get(), 1)
                .pattern("PSP")
                .pattern("TIT")
                .pattern("CRC")
                .define('P', ItemTags.PLANKS)
                .define('S', Tags.Items.RODS_WOODEN)
                .define('T', CommonTags.Items.INGOTS_TIN)
                .define('I', CommonTags.Items.RODS_IRON)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .define('C', ModItems.COPPER_WIRE.get())
                .unlockedBy("has_copper_wire", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.COPPER_WIRE.get()).build()))
                .save(output, getFileName(ModItems.WATER_MILL.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WIND_TURBINE.get(), 1)
                .pattern("ARA")
                .pattern("RGR")
                .pattern("CRC")
                .define('A', ModItems.ALUMINUM_BLOCK.get())
                .define('G', CommonTags.Items.GEARS_IRON)
                .define('R', CommonTags.Items.RODS_INVAR)
                .define('C', ModItems.COPPER_WIRE.get())
                .unlockedBy("has_copper_wire", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.COPPER_WIRE.get()).build()))
                .save(output, getFileName(ModItems.WIND_TURBINE.get()));
    }

    private void gemstoneGrower(RecipeOutput output)
    {
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.AZURITE_CRYSTAL_SEED.get()), ModItems.AZURITE_CRYSTAL.get(), 1, 200, 20)
                .unlockedBy("has_azurite_crystal_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.AZURITE_CRYSTAL_SEED.get()).build()))
                .save(output, getFileName(ModItems.AZURITE_CRYSTAL.get(), ModItems.AZURITE_CRYSTAL_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.CUPRITE_CRYSTAL_SEED.get()), ModItems.CUPRITE_CRYSTAL.get(), 1, 200, 20)
                .unlockedBy("has_cuprite_crystal_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.CUPRITE_CRYSTAL_SEED.get()).build()))
                .save(output, getFileName(ModItems.CUPRITE_CRYSTAL.get(), ModItems.CUPRITE_CRYSTAL_SEED.get()));

        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.RUBY_SEED.get()), ModItems.RUBY.get(), 1, 200, 20)
                .unlockedBy("has_ruby_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RUBY_SEED.get()).build()))
                .save(output, getFileName(ModItems.RUBY.get(), ModItems.RUBY_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.SAPPHIRE_SEED.get()), ModItems.SAPPHIRE.get(), 1, 200, 20)
                .unlockedBy("has_sapphire_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.SAPPHIRE_SEED.get()).build()))
                .save(output, getFileName(ModItems.SAPPHIRE.get(), ModItems.SAPPHIRE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.AQUAMARINE_SEED.get()), ModItems.AQUAMARINE.get(), 1, 200, 20)
                .unlockedBy("has_aquamarine_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.AQUAMARINE_SEED.get()).build()))
                .save(output, getFileName(ModItems.AQUAMARINE.get(), ModItems.AQUAMARINE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.JADE_SEED.get()), ModItems.JADE.get(), 1, 200, 20)
                .unlockedBy("has_jade_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JADE_SEED.get()).build()))
                .save(output, getFileName(ModItems.JADE.get(), ModItems.JADE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.OPAL_SEED.get()), ModItems.OPAL.get(), 1, 200, 20)
                .unlockedBy("has_opal_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.OPAL_SEED.get()).build()))
                .save(output, getFileName(ModItems.OPAL.get(), ModItems.OPAL_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.YELLOW_DIAMOND_SEED.get()), ModItems.YELLOW_DIAMOND.get(), 1, 200, 20)
                .unlockedBy("has_yellow_diamond_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.YELLOW_DIAMOND_SEED.get()).build()))
                .save(output, getFileName(ModItems.YELLOW_DIAMOND.get(), ModItems.YELLOW_DIAMOND_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.AMBER_SEED.get()), ModItems.AMBER.get(), 1, 200, 20)
                .unlockedBy("has_amber_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.AMBER_SEED.get()).build()))
                .save(output, getFileName(ModItems.AMBER.get(), ModItems.AMBER_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.TOPAZ_SEED.get()), ModItems.TOPAZ.get(), 1, 200, 20)
                .unlockedBy("has_topaz_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TOPAZ_SEED.get()).build()))
                .save(output, getFileName(ModItems.TOPAZ.get(), ModItems.TOPAZ_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.BERYLLIUM_SEED.get()), ModItems.BERYLLIUM.get(), 1, 200, 20)
                .unlockedBy("has_beryllium_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.BERYLLIUM_SEED.get()).build()))
                .save(output, getFileName(ModItems.BERYLLIUM.get(), ModItems.BERYLLIUM_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.BIXBIT_SEED.get()), ModItems.BIXBIT.get(), 1, 200, 20)
                .unlockedBy("has_bixbit_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.BIXBIT_SEED.get()).build()))
                .save(output, getFileName(ModItems.BIXBIT.get(), ModItems.BIXBIT_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.MALACHITE_SEED.get()), ModItems.MALACHITE.get(), 1, 200, 20)
                .unlockedBy("has_malachite_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.MALACHITE_SEED.get()).build()))
                .save(output, getFileName(ModItems.MALACHITE.get(), ModItems.MALACHITE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.ONYX_SEED.get()), ModItems.ONYX.get(), 1, 200, 20)
                .unlockedBy("has_onyx_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.ONYX_SEED.get()).build()))
                .save(output, getFileName(ModItems.ONYX.get(), ModItems.ONYX_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.PERIDOT_SEED.get()), ModItems.PERIDOT.get(), 1, 200, 20)
                .unlockedBy("has_peridot_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.PERIDOT_SEED.get()).build()))
                .save(output, getFileName(ModItems.PERIDOT.get(), ModItems.PERIDOT_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.MOON_STONE_SEED.get()), ModItems.MOON_STONE.get(), 1, 200, 20)
                .unlockedBy("has_moon_stone_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.MOON_STONE_SEED.get()).build()))
                .save(output, getFileName(ModItems.MOON_STONE.get(), ModItems.MOON_STONE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.SUN_STONE_SEED.get()), ModItems.SUN_STONE.get(), 1, 200, 20)
                .unlockedBy("has_sun_stone_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.SUN_STONE_SEED.get()).build()))
                .save(output, getFileName(ModItems.SUN_STONE.get(), ModItems.SUN_STONE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.CITRINE_SEED.get()), ModItems.CITRINE.get(), 1, 200, 20)
                .unlockedBy("has_citrine_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.CITRINE_SEED.get()).build()))
                .save(output, getFileName(ModItems.CITRINE.get(), ModItems.CITRINE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.DOLOMITE_SEED.get()), ModItems.DOLOMITE.get(), 1, 200, 20)
                .unlockedBy("has_dolomite_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.DOLOMITE_SEED.get()).build()))
                .save(output, getFileName(ModItems.DOLOMITE.get(), ModItems.DOLOMITE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.TANZANITE_SEED.get()), ModItems.TANZANITE.get(), 1, 200, 20)
                .unlockedBy("has_tanzanite_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TANZANITE_SEED.get()).build()))
                .save(output, getFileName(ModItems.TANZANITE.get(), ModItems.TANZANITE_SEED.get()));
    }

    private void gemstoneCharger(RecipeOutput output)
    {
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.RUBY.get()), ModItems.RUBY_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_ruby", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RUBY.get()).build()))
                .save(output, getFileName(ModItems.RUBY_CHARGED.get(), ModItems.RUBY.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.SAPPHIRE.get()), ModItems.SAPPHIRE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_sapphire", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.SAPPHIRE.get()).build()))
                .save(output, getFileName(ModItems.SAPPHIRE_CHARGED.get(), ModItems.SAPPHIRE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.AQUAMARINE.get()), ModItems.AQUAMARINE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_aquamarine", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.AQUAMARINE.get()).build()))
                .save(output, getFileName(ModItems.AQUAMARINE_CHARGED.get(), ModItems.AQUAMARINE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.JADE.get()), ModItems.JADE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_jade", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JADE.get()).build()))
                .save(output, getFileName(ModItems.JADE_CHARGED.get(), ModItems.JADE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.OPAL.get()), ModItems.OPAL_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_opal", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.OPAL.get()).build()))
                .save(output, getFileName(ModItems.OPAL_CHARGED.get(), ModItems.OPAL.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.YELLOW_DIAMOND.get()), ModItems.YELLOW_DIAMOND_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_yellow_diamond", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.YELLOW_DIAMOND.get()).build()))
                .save(output, getFileName(ModItems.YELLOW_DIAMOND_CHARGED.get(), ModItems.YELLOW_DIAMOND.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.AMBER.get()), ModItems.AMBER_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_amber", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.AMBER.get()).build()))
                .save(output, getFileName(ModItems.AMBER_CHARGED.get(), ModItems.AMBER.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.TOPAZ.get()), ModItems.TOPAZ_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_topaz", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TOPAZ.get()).build()))
                .save(output, getFileName(ModItems.TOPAZ_CHARGED.get(), ModItems.TOPAZ.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.BERYLLIUM.get()), ModItems.BERYLLIUM_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_beryllium", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.BERYLLIUM.get()).build()))
                .save(output, getFileName(ModItems.BERYLLIUM_CHARGED.get(), ModItems.BERYLLIUM.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.BIXBIT.get()), ModItems.BIXBIT_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_bixbit", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.BIXBIT.get()).build()))
                .save(output, getFileName(ModItems.BIXBIT_CHARGED.get(), ModItems.BIXBIT.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.MALACHITE.get()), ModItems.MALACHITE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_malachite", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.MALACHITE.get()).build()))
                .save(output, getFileName(ModItems.MALACHITE_CHARGED.get(), ModItems.MALACHITE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.ONYX.get()), ModItems.ONYX_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_onyx", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.ONYX.get()).build()))
                .save(output, getFileName(ModItems.ONYX_CHARGED.get(), ModItems.ONYX.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.PERIDOT.get()), ModItems.PERIDOT_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_peridot", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.PERIDOT.get()).build()))
                .save(output, getFileName(ModItems.PERIDOT_CHARGED.get(), ModItems.PERIDOT.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.MOON_STONE.get()), ModItems.MOON_STONE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_moon_stone", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.MOON_STONE.get()).build()))
                .save(output, getFileName(ModItems.MOON_STONE_CHARGED.get(), ModItems.MOON_STONE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.SUN_STONE.get()), ModItems.SUN_STONE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_sun_stone", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.SUN_STONE.get()).build()))
                .save(output, getFileName(ModItems.SUN_STONE_CHARGED.get(), ModItems.SUN_STONE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.CITRINE.get()), ModItems.CITRINE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_citrine", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.CITRINE.get()).build()))
                .save(output, getFileName(ModItems.CITRINE_CHARGED.get(), ModItems.CITRINE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.DOLOMITE.get()), ModItems.DOLOMITE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_dolomite", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.DOLOMITE.get()).build()))
                .save(output, getFileName(ModItems.DOLOMITE_CHARGED.get(), ModItems.DOLOMITE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.TANZANITE.get()), ModItems.TANZANITE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_tanzanite", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TANZANITE.get()).build()))
                .save(output, getFileName(ModItems.TANZANITE_CHARGED.get(), ModItems.TANZANITE.get()));
    }

    private void sawmill(RecipeOutput output)
    {
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.OAK_LOGS), Items.OAK_PLANKS, 6, 150, 40)
                .unlockedBy("has_oak_log", has(ItemTags.OAK_LOGS))
                .save(output, getFileName(Items.OAK_PLANKS, Items.OAK_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.SPRUCE_LOGS), Items.SPRUCE_PLANKS, 6, 150, 40)
                .unlockedBy("has_spruce_log", has(ItemTags.SPRUCE_LOGS))
                .save(output, getFileName(Items.SPRUCE_PLANKS, Items.SPRUCE_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.BIRCH_LOGS), Items.BIRCH_PLANKS, 6, 150, 40)
                .unlockedBy("has_birch_log", has(ItemTags.BIRCH_LOGS))
                .save(output, getFileName(Items.BIRCH_PLANKS, Items.BIRCH_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.JUNGLE_LOGS), Items.JUNGLE_PLANKS, 6, 150, 40)
                .unlockedBy("has_jungle_log", has(ItemTags.JUNGLE_LOGS))
                .save(output, getFileName(Items.JUNGLE_PLANKS, Items.JUNGLE_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.ACACIA_LOGS), Items.ACACIA_PLANKS, 6, 150, 40)
                .unlockedBy("has_acacia_log", has(ItemTags.ACACIA_LOGS))
                .save(output, getFileName(Items.ACACIA_PLANKS, Items.ACACIA_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.DARK_OAK_LOGS), Items.DARK_OAK_PLANKS, 6, 150, 40)
                .unlockedBy("has_dark_oak_log", has(ItemTags.DARK_OAK_LOGS))
                .save(output, getFileName(Items.DARK_OAK_PLANKS, Items.DARK_OAK_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.MANGROVE_LOGS), Items.MANGROVE_PLANKS, 6, 150, 40)
                .unlockedBy("has_mangrove_log", has(ItemTags.MANGROVE_LOGS))
                .save(output, getFileName(Items.MANGROVE_PLANKS, Items.MANGROVE_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.CHERRY_LOGS), Items.CHERRY_PLANKS, 6, 150, 40)
                .unlockedBy("has_cherry_log", has(ItemTags.CHERRY_LOGS))
                .save(output, getFileName(Items.CHERRY_PLANKS, Items.CHERRY_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.CRIMSON_STEMS), Items.CRIMSON_PLANKS, 6, 150, 40)
                .unlockedBy("has_crimson_stem", has(ItemTags.CRIMSON_STEMS))
                .save(output, getFileName(Items.CRIMSON_PLANKS, Items.CRIMSON_STEM));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WARPED_STEMS), Items.WARPED_PLANKS, 6, 150, 40)
                .unlockedBy("has_warped_log", has(ItemTags.WARPED_STEMS))
                .save(output, getFileName(Items.WARPED_PLANKS, Items.WARPED_STEM));

        SawmillRecipeBuilder.create(Ingredient.of(ModItems.RESIN_OAK_LOG.get()), Items.OAK_PLANKS, 6, 150, 40)
                .unlockedBy("has_resin_oak_log", has(ModItems.RESIN_OAK_LOG.get()))
                .save(output, getFileName(Items.OAK_PLANKS, ModItems.RESIN_OAK_LOG.get()));

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.PLANKS), Items.STICK, 4, 150, 40)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(output, "stick_from_planks");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_SLABS), Items.STICK, 2, 150, 40)
                .unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
                .save(output, "stick_from_wooden_slab");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_STAIRS), Items.STICK, 9, 150, 40)
                .unlockedBy("has_wooden_stairs", has(ItemTags.WOODEN_STAIRS))
                .save(output, "stick_from_wooden_stairs");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_FENCES), Items.STICK, 7, 150, 40)
                .unlockedBy("has_wooden_fence", has(ItemTags.WOODEN_FENCES))
                .save(output, "stick_from_wooden_fence");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.FENCE_GATES), Items.STICK, 12, 150, 40)
                .unlockedBy("has_fence_gate", has(ItemTags.FENCE_GATES))
                .save(output, "stick_from_fence_gate");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_BUTTONS), Items.STICK, 2, 150, 40)
                .unlockedBy("has_wooden_button", has(ItemTags.WOODEN_BUTTONS))
                .save(output, "stick_from_wooden_button");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_PRESSURE_PLATES), Items.STICK, 4, 150, 40)
                .unlockedBy("has_wooden_pressure_plate", has(ItemTags.WOODEN_PRESSURE_PLATES))
                .save(output, "stick_from_wooden_pressure_plate");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_DOORS), Items.STICK, 8, 150, 40)
                .unlockedBy("has_wooden_door", has(ItemTags.WOODEN_DOORS))
                .save(output, "stick_from_wooden_door");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_TRAPDOORS), Items.STICK, 12, 150, 40)
                .unlockedBy("has_wooden_trapdoor", has(ItemTags.WOODEN_TRAPDOORS))
                .save(output, "stick_from_wooden_trapdoor");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.BOATS), Items.STICK, 16, 150, 40)
                .unlockedBy("has_boat", has(ItemTags.BOATS))
                .save(output, "stick_from_boats");

        SawmillRecipeBuilder.create(Ingredient.of(Items.CRAFTING_TABLE), Items.STICK, 16, 150, 40)
                .unlockedBy("has_crafting_table", has(Items.CRAFTING_TABLE))
                .save(output, getFileName(Items.STICK, Items.CRAFTING_TABLE));

        SawmillRecipeBuilder.create(Ingredient.of(Items.CHEST), Items.STICK, 24, 150, 40)
                .unlockedBy("has_chest", has(Items.CHEST))
                .save(output, getFileName(Items.STICK, Items.CHEST));

        SawmillRecipeBuilder.create(Ingredient.of(Items.BOWL), Items.STICK, 3, 150, 40)
                .unlockedBy("has_bowl", has(Items.BOWL))
                .save(output, getFileName(Items.STICK, Items.BOWL));
    }

    private void polarizer(RecipeOutput output)
    {
        PolarizerRecipeBuilder.create(Ingredient.of(ModItems.IRON_ROD.get()), ModItems.IRON_ROD_POLARIZED.get(), 1, 200, 80)
                .unlockedBy(hasName(ModItems.IRON_ROD.get()), has(ModItems.IRON_ROD.get()))
                .save(output, getFileName(ModItems.IRON_ROD_POLARIZED.get(), ModItems.IRON_ROD.get()));

        PolarizerRecipeBuilder.create(Ingredient.of(ModItems.NICKEL_ROD.get()), ModItems.NICKEL_ROD_POLARIZED.get(), 1, 200, 80)
                .unlockedBy(hasName(ModItems.NICKEL_ROD.get()), has(ModItems.NICKEL_ROD.get()))
                .save(output, getFileName(ModItems.NICKEL_ROD_POLARIZED.get(), ModItems.NICKEL_ROD.get()));

        PolarizerRecipeBuilder.create(Ingredient.of(ModItems.INVAR_ROD.get()), ModItems.INVAR_ROD_POLARIZED.get(), 1, 200, 80)
                .unlockedBy(hasName(ModItems.INVAR_ROD.get()), has(ModItems.INVAR_ROD.get()))
                .save(output, getFileName(ModItems.INVAR_ROD_POLARIZED.get(), ModItems.INVAR_ROD.get()));

        PolarizerRecipeBuilder.create(Ingredient.of(ModItems.CONSTANTAN_ROD.get()), ModItems.CONSTANTAN_ROD_POLARIZED.get(), 1, 200, 80)
                .unlockedBy(hasName(ModItems.CONSTANTAN_ROD.get()), has(ModItems.CONSTANTAN_ROD.get()))
                .save(output, getFileName(ModItems.CONSTANTAN_ROD_POLARIZED.get(), ModItems.CONSTANTAN_ROD.get()));

        PolarizerRecipeBuilder.create(Ingredient.of(ModItems.STEEL_ROD.get()), ModItems.STEEL_ROD_POLARIZED.get(), 1, 200, 80)
                .unlockedBy(hasName(ModItems.STEEL_ROD.get()), has(ModItems.STEEL_ROD.get()))
                .save(output, getFileName(ModItems.STEEL_ROD_POLARIZED.get(), ModItems.STEEL_ROD.get()));
    }

    private void gemstoneManipulator(RecipeOutput output)
    {
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.RUBY.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.RUBY_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(output, getFileName(ModItems.RUBY_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.SAPPHIRE.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.SAPPHIRE_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(output, getFileName(ModItems.SAPPHIRE_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.AQUAMARINE.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.AQUAMARINE_SEED.get(), 2, 160, 80)
            .unlockedBy(hasName(ModItems.AQUAMARINE.get()), has(ModItems.AQUAMARINE.get()))
            .save(output, getFileName(ModItems.AQUAMARINE_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.JADE.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.JADE_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.JADE.get()), has(ModItems.JADE.get()))
                .save(output, getFileName(ModItems.JADE_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.OPAL.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.OPAL_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.OPAL.get()), has(ModItems.OPAL.get()))
                .save(output, getFileName(ModItems.OPAL_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.YELLOW_DIAMOND.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.YELLOW_DIAMOND_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.YELLOW_DIAMOND.get()), has(ModItems.YELLOW_DIAMOND.get()))
                .save(output, getFileName(ModItems.YELLOW_DIAMOND_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.AMBER.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.AMBER_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.AMBER.get()), has(ModItems.AMBER.get()))
                .save(output, getFileName(ModItems.AMBER_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.TOPAZ.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.TOPAZ_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.TOPAZ.get()), has(ModItems.TOPAZ.get()))
                .save(output, getFileName(ModItems.TOPAZ_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.BERYLLIUM.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.BERYLLIUM_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.BERYLLIUM.get()), has(ModItems.BERYLLIUM.get()))
                .save(output, getFileName(ModItems.BERYLLIUM_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.BIXBIT.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.BIXBIT_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.BIXBIT.get()), has(ModItems.BIXBIT.get()))
                .save(output, getFileName(ModItems.BIXBIT_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.MALACHITE.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.MALACHITE_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.MALACHITE.get()), has(ModItems.MALACHITE.get()))
                .save(output, getFileName(ModItems.MALACHITE_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.ONYX.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.ONYX_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.ONYX.get()), has(ModItems.ONYX.get()))
                .save(output, getFileName(ModItems.ONYX_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.PERIDOT.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.PERIDOT_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.PERIDOT.get()), has(ModItems.PERIDOT.get()))
                .save(output, getFileName(ModItems.PERIDOT_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.MOON_STONE.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.MOON_STONE_SEED.get(), 2, 160, 80)
            .unlockedBy(hasName(ModItems.MOON_STONE.get()), has(ModItems.MOON_STONE.get()))
            .save(output, getFileName(ModItems.MOON_STONE_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.SUN_STONE.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.SUN_STONE_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.SUN_STONE.get()), has(ModItems.SUN_STONE.get()))
                .save(output, getFileName(ModItems.SUN_STONE_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.CITRINE.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.CITRINE_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.CITRINE.get()), has(ModItems.CITRINE.get()))
                .save(output, getFileName(ModItems.CITRINE_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.DOLOMITE.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.DOLOMITE_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.DOLOMITE.get()), has(ModItems.DOLOMITE.get()))
                .save(output, getFileName(ModItems.DOLOMITE_SEED.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.TANZANITE.get()), Ingredient.of(Tags.Items.COBBLESTONE)), ModItems.TANZANITE_SEED.get(), 2, 160, 80)
                .unlockedBy(hasName(ModItems.TANZANITE.get()), has(ModItems.TANZANITE.get()))
                .save(output, getFileName(ModItems.TANZANITE_SEED.get()));

        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.RUBY_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.RUBY.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.RUBY_CHARGED.get()), has(ModItems.RUBY_CHARGED.get()))
                .save(output, getFileName(ModItems.RUBY.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.SAPPHIRE_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.SAPPHIRE.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.SAPPHIRE_CHARGED.get()), has(ModItems.SAPPHIRE_CHARGED.get()))
                .save(output, getFileName(ModItems.SAPPHIRE.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.AQUAMARINE_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.AQUAMARINE.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.AQUAMARINE_CHARGED.get()), has(ModItems.AQUAMARINE_CHARGED.get()))
                .save(output, getFileName(ModItems.AQUAMARINE.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.JADE_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.JADE.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.JADE_CHARGED.get()), has(ModItems.JADE_CHARGED.get()))
                .save(output, getFileName(ModItems.JADE.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.OPAL_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.OPAL.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.OPAL_CHARGED.get()), has(ModItems.OPAL_CHARGED.get()))
                .save(output, getFileName(ModItems.OPAL.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.YELLOW_DIAMOND_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.YELLOW_DIAMOND.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.YELLOW_DIAMOND_CHARGED.get()), has(ModItems.YELLOW_DIAMOND_CHARGED.get()))
                .save(output, getFileName(ModItems.YELLOW_DIAMOND.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.AMBER_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.AMBER.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.AMBER_CHARGED.get()), has(ModItems.AMBER_CHARGED.get()))
                .save(output, getFileName(ModItems.AMBER.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.TOPAZ_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.TOPAZ.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.TOPAZ_CHARGED.get()), has(ModItems.TOPAZ_CHARGED.get()))
                .save(output, getFileName(ModItems.TOPAZ.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.BERYLLIUM_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.BERYLLIUM.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.BERYLLIUM_CHARGED.get()), has(ModItems.BERYLLIUM_CHARGED.get()))
                .save(output, getFileName(ModItems.BERYLLIUM.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.BIXBIT_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.BIXBIT.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.BIXBIT_CHARGED.get()), has(ModItems.BIXBIT_CHARGED.get()))
                .save(output, getFileName(ModItems.BIXBIT.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.MALACHITE_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.MALACHITE.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.MALACHITE_CHARGED.get()), has(ModItems.MALACHITE_CHARGED.get()))
                .save(output, getFileName(ModItems.MALACHITE.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.ONYX_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.ONYX.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.ONYX_CHARGED.get()), has(ModItems.ONYX_CHARGED.get()))
                .save(output, getFileName(ModItems.ONYX.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.PERIDOT_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.PERIDOT.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.PERIDOT_CHARGED.get()), has(ModItems.PERIDOT_CHARGED.get()))
                .save(output, getFileName(ModItems.PERIDOT.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.MOON_STONE_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.MOON_STONE.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.MOON_STONE_CHARGED.get()), has(ModItems.MOON_STONE_CHARGED.get()))
                .save(output, getFileName(ModItems.MOON_STONE.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.SUN_STONE_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.SUN_STONE.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.SUN_STONE_CHARGED.get()), has(ModItems.SUN_STONE_CHARGED.get()))
                .save(output, getFileName(ModItems.SUN_STONE.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.CITRINE_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.CITRINE.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.CITRINE_CHARGED.get()), has(ModItems.CITRINE_CHARGED.get()))
                .save(output, getFileName(ModItems.CITRINE.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.DOLOMITE_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.DOLOMITE.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.DOLOMITE_CHARGED.get()), has(ModItems.DOLOMITE_CHARGED.get()))
                .save(output, getFileName(ModItems.DOLOMITE.get()));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(ModItems.TANZANITE_CHARGED.get()), Ingredient.of(Tags.Items.GLASS)), ModItems.TANZANITE.get(), 1, 160, 80)
                .unlockedBy(hasName(ModItems.TANZANITE_CHARGED.get()), has(ModItems.TANZANITE_CHARGED.get()))
                .save(output, getFileName(ModItems.TANZANITE.get()));

        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(Tags.Items.GEMS_EMERALD), Ingredient.of(Tags.Items.GEMS_EMERALD)), Items.DIAMOND, 1, 800, 160)
                .unlockedBy("has_emerald", has(Tags.Items.GEMS_EMERALD))
                .save(output, getFileName(Items.DIAMOND));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(Tags.Items.GEMS_DIAMOND), Ingredient.of(Tags.Items.GEMS_DIAMOND)), Items.EMERALD, 4, 800, 160)
                .unlockedBy("has_diamond", has(Tags.Items.GEMS_DIAMOND))
                .save(output, getFileName(Items.EMERALD));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(Tags.Items.GEMS_QUARTZ), Ingredient.of(Tags.Items.GEMS_QUARTZ)), Items.LAPIS_LAZULI, 2, 300, 160)
                .unlockedBy("has_quartz", has(Tags.Items.GEMS_QUARTZ))
                .save(output, getFileName(Items.LAPIS_LAZULI));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(Tags.Items.GEMS_LAPIS), Ingredient.of(Tags.Items.GEMS_LAPIS)), Items.QUARTZ, 4, 300, 160)
                .unlockedBy("has_lapis", has(Tags.Items.GEMS_LAPIS))
                .save(output, getFileName(Items.QUARTZ));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(Tags.Items.GEMS_AMETHYST), Ingredient.of(Tags.Items.GEMS_AMETHYST)), Items.PRISMARINE, 2, 800, 160)
                .unlockedBy("has_amethyst", has(Tags.Items.GEMS_AMETHYST))
                .save(output, getFileName(Items.PRISMARINE));
        GemstoneManipulatorRecipeBuilder.create(List.of(Ingredient.of(Tags.Items.GEMS_PRISMARINE), Ingredient.of(Tags.Items.GEMS_PRISMARINE)), Items.AMETHYST_SHARD, 4, 800, 160)
                .unlockedBy("has_prismarine", has(Tags.Items.GEMS_PRISMARINE))
                .save(output, getFileName(Items.AMETHYST_SHARD));
    }

    private void fissionReactor(RecipeOutput output)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FISSION_REACTOR.get(), 1)
                .pattern("LOL")
                .pattern("LGL")
                .pattern("LOL")
                .define('L', CommonTags.Items.INGOTS_LEAD)
                .define('O', Tags.Items.OBSIDIAN)
                .define('G', ModItems.GEMSTONE_GENERATOR.get())
                .unlockedBy(hasName(ModItems.GEMSTONE_GENERATOR.get()), has(ModItems.GEMSTONE_GENERATOR.get()))
                .save(output, getFileName(ModItems.FISSION_REACTOR.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REACTOR_FRAME.get(), 1)
                .pattern("LTL")
                .pattern("LGL")
                .pattern("LTL")
                .define('L', CommonTags.Items.INGOTS_LEAD)
                .define('T', Tags.Items.GLASS_TINTED)
                .define('G', Tags.Items.GLASS)
                .unlockedBy(hasName(ModItems.LEAD_INGOT.get()), has(CommonTags.Items.INGOTS_LEAD))
                .save(output, getFileName(ModItems.REACTOR_FRAME.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REACTOR_WALL.get(), 1)
                .pattern("LOL")
                .pattern("OBO")
                .pattern("LOL")
                .define('L', CommonTags.Items.PLATES_LEAD)
                .define('O', Tags.Items.OBSIDIAN)
                .define('B', ModItems.LEAD_BLOCK.get())
                .unlockedBy(hasName(ModItems.LEAD_PLATE.get()), has(CommonTags.Items.PLATES_LEAD))
                .save(output, getFileName(ModItems.REACTOR_WALL.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WATER_COOLING.get(), 1)
                .pattern(" # ")
                .pattern("#F#")
                .pattern(" # ")
                .define('#', Items.WATER_BUCKET)
                .define('F', ModItems.REACTOR_FRAME.get())
                .unlockedBy(hasName(ModItems.REACTOR_FRAME.get()), has(ModItems.REACTOR_FRAME.get()))
                .save(output, getFileName(ModItems.WATER_COOLING.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SNOW_COOLING.get(), 1)
                .pattern(" # ")
                .pattern("#F#")
                .pattern(" # ")
                .define('#', Items.SNOWBALL)
                .define('F', ModItems.REACTOR_FRAME.get())
                .unlockedBy(hasName(ModItems.REACTOR_FRAME.get()), has(ModItems.REACTOR_FRAME.get()))
                .save(output, getFileName(ModItems.SNOW_COOLING.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ICE_COOLING.get(), 1)
                .pattern(" # ")
                .pattern("#F#")
                .pattern(" # ")
                .define('#', Items.ICE)
                .define('F', ModItems.REACTOR_FRAME.get())
                .unlockedBy(hasName(ModItems.REACTOR_FRAME.get()), has(ModItems.REACTOR_FRAME.get()))
                .save(output, getFileName(ModItems.ICE_COOLING.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PACKED_ICE_COOLING.get(), 1)
                .pattern(" # ")
                .pattern("#F#")
                .pattern(" # ")
                .define('#', Items.PACKED_ICE)
                .define('F', ModItems.REACTOR_FRAME.get())
                .unlockedBy(hasName(ModItems.REACTOR_FRAME.get()), has(ModItems.REACTOR_FRAME.get()))
                .save(output, getFileName(ModItems.PACKED_ICE_COOLING.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BLUE_ICE_COOLING.get(), 1)
                .pattern(" # ")
                .pattern("#F#")
                .pattern(" # ")
                .define('#', Items.BLUE_ICE)
                .define('F', ModItems.REACTOR_FRAME.get())
                .unlockedBy(hasName(ModItems.REACTOR_FRAME.get()), has(ModItems.REACTOR_FRAME.get()))
                .save(output, getFileName(ModItems.BLUE_ICE_COOLING.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PRISMARINE_COOLING.get(), 1)
                .pattern(" # ")
                .pattern("#F#")
                .pattern(" # ")
                .define('#', Items.PRISMARINE)
                .define('F', ModItems.REACTOR_FRAME.get())
                .unlockedBy(hasName(ModItems.REACTOR_FRAME.get()), has(ModItems.REACTOR_FRAME.get()))
                .save(output, getFileName(ModItems.PRISMARINE_COOLING.get()));

        FissionReactorRecipeBuilder.create(Ingredient.of(ModItems.URANIUM_INGOT.get()), ModItems.LEAD_INGOT.get(), 1200, 120, 60)
                .unlockedBy(hasName(ModItems.URANIUM_INGOT.get()), has(ModItems.URANIUM_INGOT.get()))
                .save(output, getFileName(ModItems.LEAD_INGOT.get()));
    }

    private void upgrades(RecipeOutput output)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INTERMEDIATE_UPGRADE.get(), 1)
                .pattern("MLM")
                .pattern("LDL")
                .pattern("MLM")
                .define('M', CommonTags.Items.INGOTS_MAGNESIUM)
                .define('L', CommonTags.Items.INGOTS_LITHIUM)
                .define('D', Tags.Items.GEMS_DIAMOND)
                .unlockedBy("has_diamond", has(Tags.Items.GEMS_DIAMOND))
                .save(output, getFileName(ModItems.INTERMEDIATE_UPGRADE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADVANCED_UPGRADE.get(), 1)
                .pattern("ZCZ")
                .pattern("CUC")
                .pattern("ZCZ")
                .define('Z', CommonTags.Items.INGOTS_ZINC)
                .define('C', ModItems.CITRINE.get())
                .define('U', ModItems.INTERMEDIATE_UPGRADE.get())
                .unlockedBy(hasName(ModItems.INTERMEDIATE_UPGRADE.get()), has(ModItems.INTERMEDIATE_UPGRADE.get()))
                .save(output, getFileName(ModItems.ADVANCED_UPGRADE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ULTRA_UPGRADE.get(), 1)
                .pattern("LTL")
                .pattern("TUT")
                .pattern("LTL")
                .define('L', CommonTags.Items.INGOTS_LEAD)
                .define('T', ModItems.TANZANITE.get())
                .define('U', ModItems.ADVANCED_UPGRADE.get())
                .unlockedBy(hasName(ModItems.ADVANCED_UPGRADE.get()), has(ModItems.ADVANCED_UPGRADE.get()))
                .save(output, getFileName(ModItems.ULTRA_UPGRADE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.EXTREME_UPGRADE.get(), 1)
                .pattern("BIB")
                .pattern("PUP")
                .pattern("MIM")
                .define('I', CommonTags.Items.INGOTS_URANIUM)
                .define('B', ModItems.BERYLLIUM.get())
                .define('P', ModItems.PERIDOT.get())
                .define('M', ModItems.MALACHITE.get())
                .define('U', ModItems.ULTRA_UPGRADE.get())
                .unlockedBy(hasName(ModItems.ULTRA_UPGRADE.get()), has(ModItems.ULTRA_UPGRADE.get()))
                .save(output, getFileName(ModItems.EXTREME_UPGRADE.get()));
    }

    private String hasName(ItemLike item)
    {
        return "has_" + ForgeRegistries.ITEMS.getKey(item.asItem()).getPath();
    }

    private static Criterion<InventoryChangeTrigger.TriggerInstance> inventoryTrigger(ItemPredicate... predicates)
    {
        return CriteriaTriggers.INVENTORY_CHANGED.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, List.of(predicates)));
    }

    private static Criterion<EnterBlockTrigger.TriggerInstance> insideOf(Block block)
    {
        return CriteriaTriggers.ENTER_BLOCK.createCriterion(new EnterBlockTrigger.TriggerInstance(Optional.empty(), block, Optional.empty()));
    }

    private ResourceLocation getFileName(ItemLike result, ItemLike input)
    {
        StringBuilder path = new StringBuilder();
        path.append(ForgeRegistries.ITEMS.getKey(result.asItem()).getPath());
        path.append("_from_");
        path.append(ForgeRegistries.ITEMS.getKey(input.asItem()).getPath());
        return new ResourceLocation(GemstonePower.MOD_ID, path.toString());
    }

    private ResourceLocation getFileName(ItemLike... items)
    {
        StringBuilder path = new StringBuilder();
        path.append(ForgeRegistries.ITEMS.getKey(items[0].asItem()).getPath());
        if (items.length > 1)
        {
            path.append("_from_");
            for (ItemLike item : items)
            {
                if (item == items[items.length - 1])
                    path.append(ForgeRegistries.ITEMS.getKey(item.asItem()).getPath());
                else if (item != items[0])
                    path.append(ForgeRegistries.ITEMS.getKey(item.asItem()).getPath()).append("_and_");
            }
        }
        return new ResourceLocation(GemstonePower.MOD_ID, path.toString());
    }
}
