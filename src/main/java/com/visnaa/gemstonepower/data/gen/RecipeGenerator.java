package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.data.gen.builder.*;
import com.visnaa.gemstonepower.data.tag.CommonTags;
import com.visnaa.gemstonepower.init.*;
import com.visnaa.gemstonepower.item.CrystalArrowItem;
import com.visnaa.gemstonepower.item.metal.MetalGroup;
import com.visnaa.gemstonepower.item.metal.MetalGroups;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider
{
    public RecipeGenerator(PackOutput output)
    {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        this.mics(consumer);

        this.gemstoneItems(consumer);
        this.machines(consumer);
        this.gemstoneGrower(consumer);
        this.gemstoneCharger(consumer);
        this.sawmill(consumer);
        this.polarizer(consumer);

        this.upgrades(consumer);

        this.armour(consumer, Items.COPPER_INGOT, ModArmors.COPPER_HELMET.get(), ModArmors.COPPER_CHESTPLATE.get(), ModArmors.COPPER_LEGGINGS.get(), ModArmors.COPPER_BOOTS.get());
        this.armour(consumer, ModItems.ALUMINUM_INGOT.get(), ModArmors.ALUMINUM_HELMET.get(), ModArmors.ALUMINUM_CHESTPLATE.get(), ModArmors.ALUMINUM_LEGGINGS.get(), ModArmors.ALUMINUM_BOOTS.get());
        this.armour(consumer, ModItems.BRONZE_INGOT.get(), ModArmors.BRONZE_HELMET.get(), ModArmors.BRONZE_CHESTPLATE.get(), ModArmors.BRONZE_LEGGINGS.get(), ModArmors.BRONZE_BOOTS.get());
        this.armour(consumer, ModItems.SILVER_INGOT.get(), ModArmors.SILVER_HELMET.get(), ModArmors.SILVER_CHESTPLATE.get(), ModArmors.SILVER_LEGGINGS.get(), ModArmors.SILVER_BOOTS.get());
        this.armour(consumer, ModItems.INVAR_INGOT.get(), ModArmors.INVAR_HELMET.get(), ModArmors.INVAR_CHESTPLATE.get(), ModArmors.INVAR_LEGGINGS.get(), ModArmors.INVAR_BOOTS.get());
        this.armour(consumer, ModItems.STEEL_INGOT.get(), ModArmors.STEEL_HELMET.get(), ModArmors.STEEL_CHESTPLATE.get(), ModArmors.STEEL_LEGGINGS.get(), ModArmors.STEEL_BOOTS.get());

        this.tools(consumer, Items.COPPER_INGOT, ModTools.COPPER_SWORD.get(), ModTools.COPPER_SHOVEL.get(), ModTools.COPPER_PICKAXE.get(), ModTools.COPPER_AXE.get(), ModTools.COPPER_HOE.get());
        this.tools(consumer, ModItems.ALUMINUM_INGOT.get(), ModTools.ALUMINUM_SWORD.get(), ModTools.ALUMINUM_SHOVEL.get(), ModTools.ALUMINUM_PICKAXE.get(), ModTools.ALUMINUM_AXE.get(), ModTools.ALUMINUM_HOE.get());
        this.tools(consumer, ModItems.BRONZE_INGOT.get(), ModTools.BRONZE_SWORD.get(), ModTools.BRONZE_SHOVEL.get(), ModTools.BRONZE_PICKAXE.get(), ModTools.BRONZE_AXE.get(), ModTools.BRONZE_HOE.get());
        this.tools(consumer, ModItems.SILVER_INGOT.get(), ModTools.SILVER_SWORD.get(), ModTools.SILVER_SHOVEL.get(), ModTools.SILVER_PICKAXE.get(), ModTools.SILVER_AXE.get(), ModTools.SILVER_HOE.get());
        this.tools(consumer, ModItems.INVAR_INGOT.get(), ModTools.INVAR_SWORD.get(), ModTools.INVAR_SHOVEL.get(), ModTools.INVAR_PICKAXE.get(), ModTools.INVAR_AXE.get(), ModTools.INVAR_HOE.get());
        this.tools(consumer, ModItems.STEEL_INGOT.get(), ModTools.STEEL_SWORD.get(), ModTools.STEEL_SHOVEL.get(), ModTools.STEEL_PICKAXE.get(), ModTools.STEEL_AXE.get(), ModTools.STEEL_HOE.get());

        this.metalDefault(MetalGroups.IRON.getGroup(), consumer);
        this.metalDefault(MetalGroups.GOLD.getGroup(), consumer);
        this.metalDefault(MetalGroups.COPPER.getGroup(), consumer);
        this.metalDefault(MetalGroups.ALUMINUM.getGroup(), consumer);
        this.metalDefault(MetalGroups.TIN.getGroup(), consumer);
        this.metalDefault(MetalGroups.BRONZE.getGroup(), consumer);
        this.metalDefault(MetalGroups.SILVER.getGroup(), consumer);
        this.metalDefault(MetalGroups.ELECTRUM.getGroup(), consumer);
        this.metalDefault(MetalGroups.NICKEL.getGroup(), consumer);
        this.metalDefault(MetalGroups.INVAR.getGroup(), consumer);
        this.metalDefault(MetalGroups.CONSTANTAN.getGroup(), consumer);
        this.metalDefault(MetalGroups.PLATINUM.getGroup(), consumer);
        this.metalDefault(MetalGroups.STEEL.getGroup(), consumer);
        this.metalDefault(MetalGroups.LITHIUM.getGroup(), consumer);
        this.metalDefault(MetalGroups.MAGNESIUM.getGroup(), consumer);
        this.metalDefault(MetalGroups.URANIUM.getGroup(), consumer);
        this.metalDefault(MetalGroups.LEAD.getGroup(), consumer);
        this.metalDefault(MetalGroups.ZINC.getGroup(), consumer);

        this.pipes(consumer);
        
        this.crystalArrows(consumer, ModItems.RUBY.get(), ModItems.RUBY_CHARGED.get(), ModItems.RUBY_ARROW.get(), ModItems.CHARGED_RUBY_ARROW.get());
        this.crystalArrows(consumer, ModItems.SAPPHIRE.get(), ModItems.SAPPHIRE_CHARGED.get(), ModItems.SAPPHIRE_ARROW.get(), ModItems.CHARGED_SAPPHIRE_ARROW.get());
        this.crystalArrows(consumer, ModItems.AQUAMARINE.get(), ModItems.AQUAMARINE_CHARGED.get(), ModItems.AQUAMARINE_ARROW.get(), ModItems.CHARGED_AQUAMARINE_ARROW.get());
        this.crystalArrows(consumer, ModItems.JADE.get(), ModItems.JADE_CHARGED.get(), ModItems.JADE_ARROW.get(), ModItems.CHARGED_JADE_ARROW.get());
        this.crystalArrows(consumer, ModItems.OPAL.get(), ModItems.OPAL_CHARGED.get(), ModItems.OPAL_ARROW.get(), ModItems.CHARGED_OPAL_ARROW.get());
        this.crystalArrows(consumer, ModItems.YELLOW_DIAMOND.get(), ModItems.YELLOW_DIAMOND_CHARGED.get(), ModItems.YELLOW_DIAMOND_ARROW.get(), ModItems.CHARGED_YELLOW_DIAMOND_ARROW.get());
        this.crystalArrows(consumer, ModItems.AMBER.get(), ModItems.AMBER_CHARGED.get(), ModItems.AMBER_ARROW.get(), ModItems.CHARGED_AMBER_ARROW.get());
        this.crystalArrows(consumer, ModItems.TOPAZ.get(), ModItems.TOPAZ_CHARGED.get(), ModItems.TOPAZ_ARROW.get(), ModItems.CHARGED_TOPAZ_ARROW.get());
        this.crystalArrows(consumer, ModItems.BERYLLIUM.get(), ModItems.BERYLLIUM_CHARGED.get(), ModItems.BERYLLIUM_ARROW.get(), ModItems.CHARGED_BERYLLIUM_ARROW.get());
        this.crystalArrows(consumer, ModItems.BIXBIT.get(), ModItems.BIXBIT_CHARGED.get(), ModItems.BIXBIT_ARROW.get(), ModItems.CHARGED_BIXBIT_ARROW.get());
        this.crystalArrows(consumer, ModItems.MALACHITE.get(), ModItems.MALACHITE_CHARGED.get(), ModItems.MALACHITE_ARROW.get(), ModItems.CHARGED_MALACHITE_ARROW.get());
        this.crystalArrows(consumer, ModItems.ONYX.get(), ModItems.ONYX_CHARGED.get(), ModItems.ONYX_ARROW.get(), ModItems.CHARGED_ONYX_ARROW.get());
        this.crystalArrows(consumer, ModItems.PERIDOT.get(), ModItems.PERIDOT_CHARGED.get(), ModItems.PERIDOT_ARROW.get(), ModItems.CHARGED_PERIDOT_ARROW.get());
        this.crystalArrows(consumer, ModItems.MOON_STONE.get(), ModItems.MOON_STONE_CHARGED.get(), ModItems.MOON_STONE_ARROW.get(), ModItems.CHARGED_MOON_STONE_ARROW.get());
        this.crystalArrows(consumer, ModItems.SUN_STONE.get(), ModItems.SUN_STONE_CHARGED.get(), ModItems.SUN_STONE_ARROW.get(), ModItems.CHARGED_SUN_STONE_ARROW.get());
        this.crystalArrows(consumer, ModItems.CITRINE.get(), ModItems.CITRINE_CHARGED.get(), ModItems.CITRINE_ARROW.get(), ModItems.CHARGED_CITRINE_ARROW.get());
        this.crystalArrows(consumer, ModItems.DOLOMITE.get(), ModItems.DOLOMITE_CHARGED.get(), ModItems.DOLOMITE_ARROW.get(), ModItems.CHARGED_DOLOMITE_ARROW.get());
        this.crystalArrows(consumer, ModItems.TANZANITE.get(), ModItems.TANZANITE_CHARGED.get(), ModItems.TANZANITE_ARROW.get(), ModItems.CHARGED_TANZANITE_ARROW.get());
    }

    private void mics(Consumer<FinishedRecipe> consumer)
    {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemTags.LOGS), RecipeCategory.MISC, ModItems.RESIN.get(), 0.1F, 200)
                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.LOGS).build()))
                .save(consumer, getFileName(ModItems.RESIN.get()));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.RESIN.get()), RecipeCategory.MISC, ModItems.RUBBER.get(), 0.1F, 200)
                .unlockedBy("has_resin", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RESIN.get()).build()))
                .save(consumer, getFileName(ModItems.RUBBER.get(), ModItems.RESIN.get()));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.COAL_DUST.get()), RecipeCategory.MISC, Items.COAL, 0.1F, 200)
                .unlockedBy(hasName(ModItems.COAL_DUST.get()), has(ModItems.COAL_DUST.get()))
                .save(consumer, getFileName(Items.COAL, ModItems.COAL_DUST.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.METAL_WRENCH.get(), 1)
                .pattern("I I")
                .pattern("III")
                .pattern(" S ")
                .define('I', Tags.Items.INGOTS)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_ingots", inventoryTrigger(ItemPredicate.Builder.item().of(Tags.Items.INGOTS).build()))
                .save(consumer, getFileName(ModItems.METAL_WRENCH.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CRYSTAL_WRENCH.get(), 1)
                .pattern("G G")
                .pattern("GGG")
                .pattern(" S ")
                .define('G', ModTags.GEMS)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_gems", inventoryTrigger(ItemPredicate.Builder.item().of(ModTags.GEMS).build()))
                .save(consumer, getFileName(ModItems.CRYSTAL_WRENCH.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PLATE_PRESET.get(), 1)
                .pattern("SPS")
                .pattern("P#P")
                .pattern("SPS")
                .define('#', CommonTags.Items.PLATES)
                .define('P', ItemTags.PLANKS)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.PLANKS).build()))
                .save(consumer, getFileName(ModItems.PLATE_PRESET.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ROD_PRESET.get(), 1)
                .pattern("SPS")
                .pattern("P#P")
                .pattern("SPS")
                .define('#', Tags.Items.RODS)
                .define('P', ItemTags.PLANKS)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.PLANKS).build()))
                .save(consumer, getFileName(ModItems.ROD_PRESET.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WIRE_PRESET.get(), 1)
                .pattern("SPS")
                .pattern("P#P")
                .pattern("SPS")
                .define('#', CommonTags.Items.WIRES)
                .define('P', ItemTags.PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.PLANKS).build()))
                .save(consumer, getFileName(ModItems.WIRE_PRESET.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TREE_TAP.get(), 1)
                .pattern(" I ")
                .pattern("PPP")
                .pattern("  P")
                .define('P', ItemTags.PLANKS)
                .define('I', Tags.Items.INGOTS_IRON)
                .unlockedBy("has_iron_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(Tags.Items.INGOTS_IRON).build()))
                .save(consumer, getFileName(ModItems.TREE_TAP.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS, 4)
                .requires(ModItems.RESIN_OAK_LOG.get())
                .unlockedBy(hasName(ModItems.RESIN_OAK_LOG.get()), has(ModItems.RESIN_OAK_LOG.get()))
                .save(consumer, getFileName(Items.OAK_PLANKS, ModItems.RESIN_OAK_LOG.get()));

        GemstoneGeneratorRecipeBuilder.create(Ingredient.of(ModItems.AZURITE_CRYSTAL.get()), 40, 1600)
                .unlockedBy("has_azurite_crystal", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.AZURITE_CRYSTAL.get()).build()))
                .save(consumer, getFileName(ModItems.AZURITE_CRYSTAL.get()));
        GemstoneGeneratorRecipeBuilder.create(Ingredient.of(ModItems.CUPRITE_CRYSTAL.get()), 40, 1600)
                .unlockedBy("has_cuprite_crystal", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.CUPRITE_CRYSTAL.get()).build()))
                .save(consumer, getFileName(ModItems.CUPRITE_CRYSTAL.get()));
        GemstoneGeneratorRecipeBuilder.create(Ingredient.of(ItemTags.LOGS_THAT_BURN), 40, 1600)
                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.LOGS_THAT_BURN).build()))
                .save(consumer, ItemTags.LOGS_THAT_BURN.location());
        GemstoneGeneratorRecipeBuilder.create(Ingredient.of(ItemTags.COALS), 40, 3200)
                .unlockedBy("has_coals", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.COALS).build()))
                .save(consumer, ItemTags.COALS.location());
        GemstoneGeneratorRecipeBuilder.create(Ingredient.of(Items.COAL_BLOCK), 380, 15200)
                .unlockedBy("has_coal_block", inventoryTrigger(ItemPredicate.Builder.item().of(Items.COAL_BLOCK).build()))
                .save(consumer, getFileName(Items.COAL_BLOCK));

        PulverizerRecipeBuilder.create(Ingredient.of(Items.COAL), ModItems.COAL_DUST.get(), 1, 200, 40)
                .unlockedBy(hasName(Items.COAL), has(Items.COAL))
                .save(consumer, getFileName(ModItems.COAL_DUST.get(), Items.COAL));
        PulverizerRecipeBuilder.create(Ingredient.of(Blocks.COAL_BLOCK), ModItems.COAL_DUST.get(), 9, 1600, 40)
                .unlockedBy(hasName(net.minecraft.world.level.block.Blocks.COPPER_BLOCK), has(net.minecraft.world.level.block.Blocks.COPPER_BLOCK))
                .save(consumer, getFileName(ModItems.COAL_DUST.get(), net.minecraft.world.level.block.Blocks.COPPER_BLOCK));

        ExtractorRecipeBuilder.create(Ingredient.of(ItemTags.LOGS), ModItems.RESIN.get(), 1, 200, 40)
                .unlockedBy("has_log", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.LOGS).build()))
                .save(consumer);

        ExtractorRecipeBuilder.create(Ingredient.of(ModItems.RESIN_OAK_LOG.get()), ModItems.RESIN.get(), 5, 200, 40)
                .unlockedBy(hasName(ModItems.RESIN_OAK_LOG.get()), has(ModItems.RESIN_OAK_LOG.get()))
                .save(consumer, getFileName(ModItems.RESIN.get(), ModItems.RESIN_OAK_LOG.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TANK.get())
                .pattern("SGS")
                .pattern("GGG")
                .pattern("SGS")
                .define('G', Tags.Items.GLASS)
                .define('S', CommonTags.Items.INGOTS_STEEL)
                .unlockedBy(hasName(Blocks.GLASS), has(Blocks.GLASS))
                .save(consumer, getFileName(ModItems.TANK.get()));
    }

    private void metalDefault(MetalGroup group, Consumer<FinishedRecipe> consumer)
    {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getIngot(), 9)
                .requires(group.getBlock(), 1)
                .unlockedBy(hasName(group.getBlock()), has(group.getBlock()))
                .save(consumer, getFileName(group.getIngot(), group.getBlock()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getBlock(), 1)
                .requires(group.getIngot(), 9)
                .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                .save(consumer, getFileName(group.getBlock(), group.getIngot()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getIngot(), 1)
                .requires(group.getNugget(), 9)
                .unlockedBy(hasName(group.getNugget()), has(group.getNugget()))
                .save(consumer, getFileName(group.getIngot(), group.getNugget()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getNugget(), 9)
                .requires(group.getIngot(), 1)
                .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                .save(consumer, getFileName(group.getNugget(), group.getIngot()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getDust(), 1)
                .requires(group.getTinyPile(), 9)
                .unlockedBy(hasName(group.getTinyPile()), has(group.getTinyPile()))
                .save(consumer, getFileName(group.getDust(), group.getTinyPile()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getTinyPile(), 9)
                .requires(group.getDust(), 1)
                .unlockedBy(hasName(group.getDust()), has(group.getDust()))
                .save(consumer, getFileName(group.getTinyPile(), group.getDust()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, group.getGear(), 1)
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .define('#', group.getIngot())
                .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                .save(consumer, getFileName(group.getGear(), group.getIngot()));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(group.getDust()), RecipeCategory.MISC, group.getIngot(), 0.3F, 200)
                .unlockedBy(hasName(group.getDust()), has(group.getDust()))
                .save(consumer, getFileName(group.getIngot(), group.getDust()) + "_smelting");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(group.getTinyPile()), RecipeCategory.MISC, group.getNugget(), 0.3F, 200)
                .unlockedBy(hasName(group.getTinyPile()), has(group.getTinyPile()))
                .save(consumer, getFileName(group.getNugget(), group.getTinyPile()) + "_smelting");

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(group.getDust()), RecipeCategory.MISC, group.getIngot(), 0.3F, 150)
                .unlockedBy(hasName(group.getDust()), has(group.getDust()))
                .save(consumer, getFileName(group.getIngot(), group.getDust()) + "_blasting");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(group.getTinyPile()), RecipeCategory.MISC, group.getNugget(), 0.3F, 150)
                .unlockedBy(hasName(group.getTinyPile()), has(group.getTinyPile()))
                .save(consumer, getFileName(group.getNugget(), group.getTinyPile()) + "_blasting");

        PulverizerRecipeBuilder.create(Ingredient.of(group.getIngot()), group.getDust(), 1, 200, 40)
                .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                .save(consumer, getFileName(group.getDust(), group.getIngot()));
        PulverizerRecipeBuilder.create(Ingredient.of(group.getBlock()), group.getDust(), 9, 1600, 40)
                .unlockedBy(hasName(group.getBlock()), has(group.getBlock()))
                .save(consumer, getFileName(group.getDust(), group.getBlock()));
        PulverizerRecipeBuilder.create(Ingredient.of(group.getPlate()), group.getDust(), 1, 200, 40)
                .unlockedBy(hasName(group.getPlate()), has(group.getPlate()))
                .save(consumer, getFileName(group.getDust(), group.getPlate()));
        PulverizerRecipeBuilder.create(Ingredient.of(group.getRod()), group.getDust(), 1, 200, 40)
                .unlockedBy(hasName(group.getRod()), has(group.getRod()))
                .save(consumer, getFileName(group.getDust(), group.getRod()));
        PulverizerRecipeBuilder.create(Ingredient.of(group.getGear()), group.getDust(), 4, 200, 40)
                .unlockedBy(hasName(group.getGear()), has(group.getGear()))
                .save(consumer, getFileName(group.getDust(), group.getGear()));
        PulverizerRecipeBuilder.create(Ingredient.of(group.getNugget()), group.getTinyPile(), 1, 200, 40)
                .unlockedBy(hasName(group.getNugget()), has(group.getNugget()))
                .save(consumer, getFileName(group.getTinyPile(), group.getNugget()));

        MetalFormerRecipeBuilder.create(Ingredient.of(group.getIngot()), group.getPlate(), 1, "plate", 200, 40)
                .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                .save(consumer, getFileName(group.getPlate(), group.getIngot()));
        MetalFormerRecipeBuilder.create(Ingredient.of(group.getIngot()), group.getRod(), 1, "rod", 200, 40)
                .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                .save(consumer, getFileName(group.getRod(), group.getIngot()));

        if (group.getWire() != null && group.getCable() != null)
        {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, group.getWire(), 1)
                    .pattern("###")
                    .define('#', group.getIngot())
                    .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                    .save(consumer, getFileName(group.getWire(), group.getIngot()));

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, group.getCable(), 1)
                    .requires(group.getWire(), 1)
                    .requires(ModItems.RUBBER.get(), 1)
                    .unlockedBy(hasName(group.getWire()), has(group.getWire()))
                    .save(consumer, getFileName(group.getCable(), group.getWire(), ModItems.RUBBER.get()));

            MetalFormerRecipeBuilder.create(Ingredient.of(group.getIngot()), group.getWire().asItem(), 3, "wire", 200, 40)
                    .unlockedBy(hasName(group.getIngot()), has(group.getIngot()))
                    .save(consumer, getFileName(group.getWire(), group.getIngot()));
        }

        if (!group.isAlloy())
        {
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(group.getOreStone()), RecipeCategory.MISC, group.getIngot(), 0.3F, 200)
                    .unlockedBy(hasName(group.getOreStone()), has(group.getOreStone()))
                    .save(consumer, getFileName(group.getIngot(), group.getOreStone()) + "_smelting");
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(group.getOreDeepslate()), RecipeCategory.MISC, group.getIngot(), 0.3F, 200)
                    .unlockedBy(hasName(group.getOreDeepslate()), has(group.getOreDeepslate()))
                    .save(consumer, getFileName(group.getIngot(), group.getOreDeepslate()) + "_smelting");
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(group.getRaw()), RecipeCategory.MISC, group.getIngot(), 0.3F, 200)
                    .unlockedBy(hasName(group.getRaw()), has(group.getRaw()))
                    .save(consumer, getFileName(group.getIngot(), group.getRaw()) + "_smelting");

            SimpleCookingRecipeBuilder.blasting(Ingredient.of(group.getOreStone()), RecipeCategory.MISC, group.getIngot(), 0.3F, 150)
                    .unlockedBy(hasName(group.getOreStone()), has(group.getOreStone()))
                    .save(consumer, getFileName(group.getIngot(), group.getOreStone()) + "_blasting");
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(group.getOreDeepslate()), RecipeCategory.MISC, group.getIngot(), 0.3F, 150)
                    .unlockedBy(hasName(group.getOreDeepslate()), has(group.getOreDeepslate()))
                    .save(consumer, getFileName(group.getIngot(), group.getOreDeepslate()) + "_blasting");
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(group.getRaw()), RecipeCategory.MISC, group.getIngot(), 0.3F, 150)
                    .unlockedBy(hasName(group.getRaw()), has(group.getRaw()))
                    .save(consumer, getFileName(group.getIngot(), group.getRaw()) + "_blasting");

            PulverizerRecipeBuilder.create(Ingredient.of(group.getOreStone()), group.getOreDust(), 1, 200, 40)
                    .unlockedBy(hasName(group.getOreStone()), has(group.getOreStone()))
                    .save(consumer, getFileName(group.getOreDust(), group.getOreStone()));
            PulverizerRecipeBuilder.create(Ingredient.of(group.getOreDeepslate()), group.getOreDust(), 1, 200, 40)
                    .unlockedBy(hasName(group.getOreDeepslate()), has(group.getOreDeepslate()))
                    .save(consumer, getFileName(group.getOreDust(), group.getOreDeepslate()));
            PulverizerRecipeBuilder.create(Ingredient.of(group.getRaw()), group.getOreDust(), 1, 200, 40)
                    .unlockedBy(hasName(group.getRaw()), has(group.getRaw()))
                    .save(consumer, getFileName(group.getOreDust(), group.getRaw()));

            OreWasherRecipeBuilder.create(Ingredient.of(group.getOreDust()), new FluidStack(Fluids.WATER, 250), group.getOreDustWashing(), new int[]{ 1, 1, 1, 1 }, 400, 40)
                    .unlockedBy(hasName(group.getOreDust()), has(group.getOreDust()))
                    .save(consumer, getFileName(group.getDust(), group.getOreDust()));
        }
        else
        {
            if (group.getAlloyIngots() != null)
            {
                AlloySmelterRecipeBuilder.create(List.of(Ingredient.of(group.getAlloyIngots().get(0)), Ingredient.of(group.getAlloyIngots().get(1))), group.getAmount1(), group.getAmount2(), group.getIngot(), group.getAmount(), 400, 80)
                        .unlockedBy(hasName(group.getAlloyIngots().get(0)), has(group.getAlloyIngots().get(0)))
                        .save(consumer, getFileName(group.getIngot(), group.getAlloyIngots().get(0), group.getAlloyIngots().get(1)));
            }
            if (group.getAlloyBlocks() != null)
            {
                AlloySmelterRecipeBuilder.create(List.of(Ingredient.of(group.getAlloyBlocks().get(0)), Ingredient.of(group.getAlloyBlocks().get(1))), group.getAmount1(), group.getAmount2(), group.getBlock().asItem(), group.getAmount(), 3400, 80)
                        .unlockedBy(hasName(group.getAlloyBlocks().get(0)), has(group.getAlloyBlocks().get(0)))
                        .save(consumer, getFileName(group.getIngot(), group.getAlloyBlocks().get(0), group.getAlloyBlocks().get(1)));
            }
            if (group.getAlloyNuggets() != null)
            {
                AlloySmelterRecipeBuilder.create(List.of(Ingredient.of(group.getAlloyNuggets().get(0)), Ingredient.of(group.getAlloyNuggets().get(1))), group.getAmount1(), group.getAmount2(), group.getNugget(), group.getAmount(), 400, 80)
                        .unlockedBy(hasName(group.getAlloyNuggets().get(0)), has(group.getAlloyNuggets().get(0)))
                        .save(consumer, getFileName(group.getIngot(), group.getAlloyNuggets().get(0), group.getAlloyNuggets().get(1)));
            }
            if (group.getAlloyDusts() != null)
            {
                AlloySmelterRecipeBuilder.create(List.of(Ingredient.of(group.getAlloyDusts().get(0)), Ingredient.of(group.getAlloyDusts().get(1))), group.getAmount1(), group.getAmount2(), group.getDust(), group.getAmount(), 400, 80)
                        .unlockedBy(hasName(group.getAlloyDusts().get(0)), has(group.getAlloyDusts().get(0)))
                        .save(consumer, getFileName(group.getIngot(), group.getAlloyDusts().get(0), group.getAlloyDusts().get(1)));
            }
            if (group.getAlloyTinyPiles() != null)
            {
                AlloySmelterRecipeBuilder.create(List.of(Ingredient.of(group.getAlloyTinyPiles().get(0)), Ingredient.of(group.getAlloyTinyPiles().get(1))), group.getAmount1(), group.getAmount2(), group.getTinyPile(), group.getAmount(), 400, 80)
                        .unlockedBy(hasName(group.getAlloyTinyPiles().get(0)), has(group.getAlloyTinyPiles().get(0)))
                        .save(consumer, getFileName(group.getIngot(), group.getAlloyTinyPiles().get(0), group.getAlloyTinyPiles().get(1)));
            }
        }
    }

    private void armour(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike helmet, ItemLike chestplate, ItemLike leggings, ItemLike boots)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
                .pattern("###")
                .pattern("# #")
                .define('#', ingredient)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(consumer, getFileName(helmet, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(consumer, getFileName(chestplate, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ingredient)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(consumer, getFileName(leggings, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
                .pattern("# #")
                .pattern("# #")
                .define('#', ingredient)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(consumer, getFileName(boots, ingredient));
    }

    private void tools(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike sword, ItemLike shovel, ItemLike pickaxe, ItemLike axe, ItemLike hoe)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
                .pattern("#")
                .pattern("#")
                .pattern("S")
                .define('#', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(consumer, getFileName(sword, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                .pattern("#")
                .pattern("S")
                .pattern("S")
                .define('#', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(consumer, getFileName(shovel, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .define('#', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(consumer, getFileName(pickaxe, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                .pattern("##")
                .pattern("S#")
                .pattern("S ")
                .define('#', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(consumer, getFileName(axe, ingredient));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
                .pattern("##")
                .pattern("S ")
                .pattern("S ")
                .define('#', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy(hasName(ingredient), has(ingredient))
                .save(consumer, getFileName(hoe, ingredient));
    }

    private void pipes(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_ITEM_PIPE.get(), 8)
                .pattern("I")
                .pattern("R")
                .pattern("I")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy(hasName(Items.IRON_INGOT), has(Tags.Items.INGOTS_IRON))
                .save(consumer, getFileName(ModItems.IRON_ITEM_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_ITEM_PIPE.get(), 8)
                .pattern("I")
                .pattern("R")
                .pattern("I")
                .define('I', Tags.Items.INGOTS_GOLD)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy(hasName(Items.GOLD_INGOT), has(Tags.Items.INGOTS_GOLD))
                .save(consumer, getFileName(ModItems.GOLD_ITEM_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_ITEM_PIPE.get(), 8)
                .pattern("I")
                .pattern("R")
                .pattern("I")
                .define('I', Tags.Items.INGOTS_COPPER)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy(hasName(Items.COPPER_INGOT), has(Tags.Items.INGOTS_COPPER))
                .save(consumer, getFileName(ModItems.COPPER_ITEM_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PLATINUM_ITEM_PIPE.get(), 8)
                .pattern("I")
                .pattern("R")
                .pattern("I")
                .define('I', CommonTags.Items.INGOTS_PLATINUM)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy(hasName(ModItems.PLATINUM_INGOT.get()), has(CommonTags.Items.INGOTS_PLATINUM))
                .save(consumer, getFileName(ModItems.PLATINUM_ITEM_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LEAD_ITEM_PIPE.get(), 8)
                .pattern("I")
                .pattern("R")
                .pattern("I")
                .define('I', CommonTags.Items.INGOTS_LEAD)
                .define('R', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy(hasName(ModItems.LEAD_INGOT.get()), has(CommonTags.Items.INGOTS_LEAD))
                .save(consumer, getFileName(ModItems.LEAD_ITEM_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_FLUID_PIPE.get(), 8)
                .pattern("I")
                .pattern("B")
                .pattern("I")
                .define('I', Tags.Items.INGOTS_GOLD)
                .define('B', Items.BUCKET)
                .unlockedBy(hasName(Items.GOLD_INGOT), has(Tags.Items.INGOTS_GOLD))
                .save(consumer, getFileName(ModItems.GOLD_FLUID_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_FLUID_PIPE.get(), 8)
                .pattern("I")
                .pattern("B")
                .pattern("I")
                .define('I', Tags.Items.INGOTS_COPPER)
                .define('B', Items.BUCKET)
                .unlockedBy(hasName(Items.COPPER_INGOT), has(Tags.Items.INGOTS_COPPER))
                .save(consumer, getFileName(ModItems.COPPER_FLUID_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INVAR_FLUID_PIPE.get(), 8)
                .pattern("I")
                .pattern("B")
                .pattern("I")
                .define('I', CommonTags.Items.INGOTS_INVAR)
                .define('B', Items.BUCKET)
                .unlockedBy(hasName(ModItems.INVAR_INGOT.get()), has(CommonTags.Items.INGOTS_INVAR))
                .save(consumer, getFileName(ModItems.INVAR_FLUID_PIPE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_FLUID_PIPE.get(), 8)
                .pattern("I")
                .pattern("B")
                .pattern("I")
                .define('I', CommonTags.Items.INGOTS_STEEL)
                .define('B', Items.BUCKET)
                .unlockedBy(hasName(ModItems.STEEL_INGOT.get()), has(CommonTags.Items.INGOTS_STEEL))
                .save(consumer, getFileName(ModItems.STEEL_FLUID_PIPE.get()));
    }

    private void crystalArrows(Consumer<FinishedRecipe> consumer, ItemLike crystal, ItemLike charged, CrystalArrowItem crystalArrow, CrystalArrowItem chargedArrow)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, crystalArrow, 1)
                .pattern("C")
                .pattern("S")
                .pattern("F")
                .define('C', crystal)
                .define('S', Tags.Items.RODS_WOODEN)
                .define('F', Items.FEATHER)
                .unlockedBy(hasName(crystal), has(crystal))
                .save(consumer, getFileName(crystalArrow, crystal));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chargedArrow, 1)
                .pattern("C")
                .pattern("S")
                .pattern("F")
                .define('C', charged)
                .define('S', Tags.Items.RODS_WOODEN)
                .define('F', Items.FEATHER)
                .unlockedBy(hasName(charged), has(charged))
                .save(consumer, getFileName(chargedArrow, charged));

        CrystalChargerRecipeBuilder.create(Ingredient.of(crystalArrow), chargedArrow, 1, 250, 120)
                .unlockedBy(hasName(crystalArrow), has(crystalArrow))
                .save(consumer, getFileName(chargedArrow, crystalArrow));
    }

    private void gemstoneItems(Consumer<FinishedRecipe> consumer)
    {
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
                .save(consumer);

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
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModArmors.GEMSTONE_HELMET.get(), 1)
                .pattern("LDL")
                .pattern("D D")
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .unlockedBy(hasName(ModItems.LIGHT_GEMSTONE.get()), has(ModItems.LIGHT_GEMSTONE.get()))
                .unlockedBy(hasName(ModItems.DARK_GEMSTONE.get()), has(ModItems.DARK_GEMSTONE.get()))
                .save(consumer, getFileName(ModArmors.GEMSTONE_HELMET.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModArmors.GEMSTONE_CHESTPLATE.get(), 1)
                .pattern("L L")
                .pattern("DLD")
                .pattern("DLD")
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .unlockedBy(hasName(ModItems.LIGHT_GEMSTONE.get()), has(ModItems.LIGHT_GEMSTONE.get()))
                .unlockedBy(hasName(ModItems.DARK_GEMSTONE.get()), has(ModItems.DARK_GEMSTONE.get()))
                .save(consumer, getFileName(ModArmors.GEMSTONE_CHESTPLATE.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModArmors.GEMSTONE_LEGGINGS.get(), 1)
                .pattern("DDD")
                .pattern("L L")
                .pattern("L L")
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .unlockedBy(hasName(ModItems.LIGHT_GEMSTONE.get()), has(ModItems.LIGHT_GEMSTONE.get()))
                .unlockedBy(hasName(ModItems.DARK_GEMSTONE.get()), has(ModItems.DARK_GEMSTONE.get()))
                .save(consumer, getFileName(ModArmors.GEMSTONE_LEGGINGS.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModArmors.GEMSTONE_BOOTS.get(), 1)
                .pattern("L L")
                .pattern("D D")
                .define('L', ModItems.LIGHT_GEMSTONE.get())
                .define('D', ModItems.DARK_GEMSTONE.get())
                .unlockedBy(hasName(ModItems.LIGHT_GEMSTONE.get()), has(ModItems.LIGHT_GEMSTONE.get()))
                .unlockedBy(hasName(ModItems.DARK_GEMSTONE.get()), has(ModItems.DARK_GEMSTONE.get()))
                .save(consumer, getFileName(ModArmors.GEMSTONE_BOOTS.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get()));
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
                .save(consumer, getFileName(ModTools.GEMSTONE_SWORD.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get(), Items.STICK));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModTools.GEMSTONE_SHOVEL.get(), 1)
                .pattern("G")
                .pattern("#")
                .pattern("#")
                .define('G', ModTags.GEMSTONE)
                .define('#', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_gemstone", has(ModTags.GEMSTONE))
                .unlockedBy(hasName(Items.STICK), has(Tags.Items.RODS_WOODEN))
                .save(consumer, getFileName(ModTools.GEMSTONE_SHOVEL.get()));
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
                .save(consumer, getFileName(ModTools.GEMSTONE_PICKAXE.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get(), Items.STICK));
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
                .save(consumer, getFileName(ModTools.GEMSTONE_AXE.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get(), Items.STICK));
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
                .save(consumer, getFileName(ModTools.GEMSTONE_HOE.get(), ModItems.LIGHT_GEMSTONE.get(), ModItems.DARK_GEMSTONE.get(), Items.STICK));
    }

    private void machines(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GEMSTONE_GENERATOR.get(), 1)
                .pattern("ICI")
                .pattern("CBC")
                .pattern("ICI")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.COPPER_INGOT)
                .define('B', net.minecraft.world.level.block.Blocks.IRON_BLOCK)
                .unlockedBy("has_iron_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(Items.IRON_INGOT).build()))
                .save(consumer, getFileName(ModBlocks.GEMSTONE_GENERATOR.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GEMSTONE_CELL.get(), 1)
                .pattern("ICI")
                .pattern("CBC")
                .pattern("ICI")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.COPPER_INGOT)
                .define('B', net.minecraft.world.level.block.Blocks.COPPER_BLOCK)
                .unlockedBy("has_copper_block", inventoryTrigger(ItemPredicate.Builder.item().of(net.minecraft.world.level.block.Blocks.COPPER_BLOCK).build()))
                .save(consumer, getFileName(ModBlocks.GEMSTONE_CELL.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRYSTAL_GROWER.get(), 1)
                .pattern("SSS")
                .pattern("WGW")
                .pattern("SSS")
                .define('S', ModItems.SILVER_INGOT.get())
                .define('W', Items.WATER_BUCKET)
                .define('G', ModItems.GOLD_GEAR.get())
                .unlockedBy("has_gold_gear", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.GOLD_GEAR.get()).build()))
                .save(consumer, getFileName(ModBlocks.CRYSTAL_GROWER.get()));
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
                .save(consumer, getFileName(ModBlocks.CRYSTAL_CHARGER.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ELECTRIC_FURNACE.get(), 1)
                .pattern("IGI")
                .pattern("SFS")
                .pattern("ISI")
                .define('I', Items.IRON_INGOT)
                .define('G', Items.GOLD_INGOT)
                .define('S', ModItems.SILVER_INGOT.get())
                .define('F', net.minecraft.world.level.block.Blocks.FURNACE)
                .unlockedBy("has_furnace", inventoryTrigger(ItemPredicate.Builder.item().of(net.minecraft.world.level.block.Blocks.FURNACE).build()))
                .save(consumer, getFileName(ModBlocks.ELECTRIC_FURNACE.get()));
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
                .save(consumer, getFileName(ModBlocks.METAL_FORMER.get()));
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
                .save(consumer, getFileName(ModBlocks.PULVERIZER.get()));
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
                .save(consumer, getFileName(ModBlocks.ALLOY_SMELTER.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EXTRACTOR.get(), 1)
                .pattern("PRP")
                .pattern("RCR")
                .pattern("PRP")
                .define('R', ModItems.RUBBER.get())
                .define('P', ModItems.PLATINUM_PLATE.get())
                .define('C', ModBlocks.COPPER_CABLE.get())
                .unlockedBy("has_rubber", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RUBBER.get()).build()))
                .save(consumer, getFileName(ModBlocks.EXTRACTOR.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ORE_WASHER.get(), 1)
                .pattern("IEI")
                .pattern("WWW")
                .pattern("IBI")
                .define('E', ModItems.PLATINUM_GEAR.get())
                .define('I', ModItems.INVAR_PLATE.get())
                .define('W', Items.WATER_BUCKET)
                .define('B', ModBlocks.INVAR_BLOCK.get())
                .unlockedBy("has_water_bucket", inventoryTrigger(ItemPredicate.Builder.item().of(Items.WATER_BUCKET).build()))
                .save(consumer, getFileName(ModBlocks.ORE_WASHER.get()));
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
                .save(consumer, getFileName(ModItems.COBBLESTONE_GENERATOR.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAWMILL.get(), 1)
                .pattern("AAA")
                .pattern("GGG")
                .pattern("AEA")
                .define('A', CommonTags.Items.INGOTS_ALUMNUM)
                .define('G', CommonTags.Items.GEARS_STEEL)
                .define('E', CommonTags.Items.WIRES_ELECTRUM)
                .unlockedBy("has_aluminum_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(CommonTags.Items.INGOTS_ALUMNUM).build()))
                .save(consumer, getFileName(ModItems.SAWMILL.get()));
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
                .save(consumer, getFileName(ModItems.POLARIZER.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SOLAR_PANEL.get(), 1)
                .pattern("GGG")
                .pattern("LLL")
                .pattern("ICI")
                .define('C', ModItems.COPPER_WIRE.get())
                .define('G', Tags.Items.GLASS)
                .define('L', Tags.Items.GEMS_LAPIS)
                .define('I', Tags.Items.INGOTS_IRON)
                .unlockedBy("has_copper_wire", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.COPPER_WIRE.get()).build()))
                .save(consumer, getFileName(ModItems.SOLAR_PANEL.get()));
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
                .save(consumer, getFileName(ModItems.WATER_MILL.get()));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WIND_TURBINE.get(), 1)
                .pattern("ARA")
                .pattern("RGR")
                .pattern("CRC")
                .define('A', ModItems.ALUMINUM_BLOCK.get())
                .define('G', CommonTags.Items.GEARS_IRON)
                .define('R', CommonTags.Items.RODS_INVAR)
                .define('C', ModItems.COPPER_WIRE.get())
                .unlockedBy("has_copper_wire", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.COPPER_WIRE.get()).build()))
                .save(consumer, getFileName(ModItems.WIND_TURBINE.get()));
    }

    private void gemstoneGrower(Consumer<FinishedRecipe> consumer)
    {
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.AZURITE_CRYSTAL_SEED.get()), ModItems.AZURITE_CRYSTAL.get(), 1, 200, 20)
                .unlockedBy("has_azurite_crystal_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.AZURITE_CRYSTAL_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.AZURITE_CRYSTAL.get(), ModItems.AZURITE_CRYSTAL_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.CUPRITE_CRYSTAL_SEED.get()), ModItems.CUPRITE_CRYSTAL.get(), 1, 200, 20)
                .unlockedBy("has_cuprite_crystal_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.CUPRITE_CRYSTAL_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.CUPRITE_CRYSTAL.get(), ModItems.CUPRITE_CRYSTAL_SEED.get()));

        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.RUBY_SEED.get()), ModItems.RUBY.get(), 1, 200, 20)
                .unlockedBy("has_ruby_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RUBY_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.RUBY.get(), ModItems.RUBY_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.SAPPHIRE_SEED.get()), ModItems.SAPPHIRE.get(), 1, 200, 20)
                .unlockedBy("has_sapphire_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.SAPPHIRE_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.SAPPHIRE.get(), ModItems.SAPPHIRE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.AQUAMARINE_SEED.get()), ModItems.AQUAMARINE.get(), 1, 200, 20)
                .unlockedBy("has_aquamarine_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.AQUAMARINE_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.AQUAMARINE.get(), ModItems.AQUAMARINE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.JADE_SEED.get()), ModItems.JADE.get(), 1, 200, 20)
                .unlockedBy("has_jade_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JADE_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.JADE.get(), ModItems.JADE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.OPAL_SEED.get()), ModItems.OPAL.get(), 1, 200, 20)
                .unlockedBy("has_opal_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.OPAL_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.OPAL.get(), ModItems.OPAL_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.YELLOW_DIAMOND_SEED.get()), ModItems.YELLOW_DIAMOND.get(), 1, 200, 20)
                .unlockedBy("has_yellow_diamond_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.YELLOW_DIAMOND_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.YELLOW_DIAMOND.get(), ModItems.YELLOW_DIAMOND_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.AMBER_SEED.get()), ModItems.AMBER.get(), 1, 200, 20)
                .unlockedBy("has_amber_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.AMBER_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.AMBER.get(), ModItems.AMBER_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.TOPAZ_SEED.get()), ModItems.TOPAZ.get(), 1, 200, 20)
                .unlockedBy("has_topaz_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TOPAZ_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.TOPAZ.get(), ModItems.TOPAZ_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.BERYLLIUM_SEED.get()), ModItems.BERYLLIUM.get(), 1, 200, 20)
                .unlockedBy("has_beryllium_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.BERYLLIUM_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.BERYLLIUM.get(), ModItems.BERYLLIUM_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.BIXBIT_SEED.get()), ModItems.BIXBIT.get(), 1, 200, 20)
                .unlockedBy("has_bixbit_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.BIXBIT_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.BIXBIT.get(), ModItems.BIXBIT_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.MALACHITE_SEED.get()), ModItems.MALACHITE.get(), 1, 200, 20)
                .unlockedBy("has_malachite_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.MALACHITE_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.MALACHITE.get(), ModItems.MALACHITE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.ONYX_SEED.get()), ModItems.ONYX.get(), 1, 200, 20)
                .unlockedBy("has_onyx_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.ONYX_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.ONYX.get(), ModItems.ONYX_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.PERIDOT_SEED.get()), ModItems.PERIDOT.get(), 1, 200, 20)
                .unlockedBy("has_peridot_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.PERIDOT_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.PERIDOT.get(), ModItems.PERIDOT_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.MOON_STONE_SEED.get()), ModItems.MOON_STONE.get(), 1, 200, 20)
                .unlockedBy("has_moon_stone_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.MOON_STONE_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.MOON_STONE.get(), ModItems.MOON_STONE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.SUN_STONE_SEED.get()), ModItems.SUN_STONE.get(), 1, 200, 20)
                .unlockedBy("has_sun_stone_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.SUN_STONE_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.SUN_STONE.get(), ModItems.SUN_STONE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.CITRINE_SEED.get()), ModItems.CITRINE.get(), 1, 200, 20)
                .unlockedBy("has_citrine_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.CITRINE_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.CITRINE.get(), ModItems.CITRINE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.DOLOMITE_SEED.get()), ModItems.DOLOMITE.get(), 1, 200, 20)
                .unlockedBy("has_dolomite_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.DOLOMITE_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.DOLOMITE.get(), ModItems.DOLOMITE_SEED.get()));
        CrystalGrowerRecipeBuilder.create(Ingredient.of(ModItems.TANZANITE_SEED.get()), ModItems.TANZANITE.get(), 1, 200, 20)
                .unlockedBy("has_tanzanite_seed", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TANZANITE_SEED.get()).build()))
                .save(consumer, getFileName(ModItems.TANZANITE.get(), ModItems.TANZANITE_SEED.get()));
    }

    private void gemstoneCharger(Consumer<FinishedRecipe> consumer)
    {
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.RUBY.get()), ModItems.RUBY_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_ruby", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RUBY.get()).build()))
                .save(consumer, getFileName(ModItems.RUBY_CHARGED.get(), ModItems.RUBY.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.SAPPHIRE.get()), ModItems.SAPPHIRE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_sapphire", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.SAPPHIRE.get()).build()))
                .save(consumer, getFileName(ModItems.SAPPHIRE_CHARGED.get(), ModItems.SAPPHIRE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.AQUAMARINE.get()), ModItems.AQUAMARINE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_aquamarine", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.AQUAMARINE.get()).build()))
                .save(consumer, getFileName(ModItems.AQUAMARINE_CHARGED.get(), ModItems.AQUAMARINE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.JADE.get()), ModItems.JADE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_jade", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JADE.get()).build()))
                .save(consumer, getFileName(ModItems.JADE_CHARGED.get(), ModItems.JADE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.OPAL.get()), ModItems.OPAL_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_opal", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.OPAL.get()).build()))
                .save(consumer, getFileName(ModItems.OPAL_CHARGED.get(), ModItems.OPAL.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.YELLOW_DIAMOND.get()), ModItems.YELLOW_DIAMOND_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_yellow_diamond", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.YELLOW_DIAMOND.get()).build()))
                .save(consumer, getFileName(ModItems.YELLOW_DIAMOND_CHARGED.get(), ModItems.YELLOW_DIAMOND.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.AMBER.get()), ModItems.AMBER_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_amber", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.AMBER.get()).build()))
                .save(consumer, getFileName(ModItems.AMBER_CHARGED.get(), ModItems.AMBER.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.TOPAZ.get()), ModItems.TOPAZ_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_topaz", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TOPAZ.get()).build()))
                .save(consumer, getFileName(ModItems.TOPAZ_CHARGED.get(), ModItems.TOPAZ.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.BERYLLIUM.get()), ModItems.BERYLLIUM_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_beryllium", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.BERYLLIUM.get()).build()))
                .save(consumer, getFileName(ModItems.BERYLLIUM_CHARGED.get(), ModItems.BERYLLIUM.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.BIXBIT.get()), ModItems.BIXBIT_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_bixbit", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.BIXBIT.get()).build()))
                .save(consumer, getFileName(ModItems.BIXBIT_CHARGED.get(), ModItems.BIXBIT.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.MALACHITE.get()), ModItems.MALACHITE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_malachite", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.MALACHITE.get()).build()))
                .save(consumer, getFileName(ModItems.MALACHITE_CHARGED.get(), ModItems.MALACHITE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.ONYX.get()), ModItems.ONYX_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_onyx", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.ONYX.get()).build()))
                .save(consumer, getFileName(ModItems.ONYX_CHARGED.get(), ModItems.ONYX.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.PERIDOT.get()), ModItems.PERIDOT_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_peridot", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.PERIDOT.get()).build()))
                .save(consumer, getFileName(ModItems.PERIDOT_CHARGED.get(), ModItems.PERIDOT.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.MOON_STONE.get()), ModItems.MOON_STONE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_moon_stone", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.MOON_STONE.get()).build()))
                .save(consumer, getFileName(ModItems.MOON_STONE_CHARGED.get(), ModItems.MOON_STONE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.SUN_STONE.get()), ModItems.SUN_STONE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_sun_stone", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.SUN_STONE.get()).build()))
                .save(consumer, getFileName(ModItems.SUN_STONE_CHARGED.get(), ModItems.SUN_STONE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.CITRINE.get()), ModItems.CITRINE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_citrine", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.CITRINE.get()).build()))
                .save(consumer, getFileName(ModItems.CITRINE_CHARGED.get(), ModItems.CITRINE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.DOLOMITE.get()), ModItems.DOLOMITE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_dolomite", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.DOLOMITE.get()).build()))
                .save(consumer, getFileName(ModItems.DOLOMITE_CHARGED.get(), ModItems.DOLOMITE.get()));
        CrystalChargerRecipeBuilder.create(Ingredient.of(ModItems.TANZANITE.get()), ModItems.TANZANITE_CHARGED.get(), 1, 200, 120)
                .unlockedBy("has_tanzanite", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TANZANITE.get()).build()))
                .save(consumer, getFileName(ModItems.TANZANITE_CHARGED.get(), ModItems.TANZANITE.get()));
    }

    private void sawmill(Consumer<FinishedRecipe> consumer)
    {
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.OAK_LOGS), Items.OAK_PLANKS, 6, 150, 40)
                .unlockedBy("has_oak_log", has(ItemTags.OAK_LOGS))
                .save(consumer, getFileName(Items.OAK_PLANKS, Items.OAK_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.SPRUCE_LOGS), Items.SPRUCE_PLANKS, 6, 150, 40)
                .unlockedBy("has_spruce_log", has(ItemTags.SPRUCE_LOGS))
                .save(consumer, getFileName(Items.SPRUCE_PLANKS, Items.SPRUCE_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.BIRCH_LOGS), Items.BIRCH_PLANKS, 6, 150, 40)
                .unlockedBy("has_birch_log", has(ItemTags.BIRCH_LOGS))
                .save(consumer, getFileName(Items.BIRCH_PLANKS, Items.BIRCH_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.JUNGLE_LOGS), Items.JUNGLE_PLANKS, 6, 150, 40)
                .unlockedBy("has_jungle_log", has(ItemTags.JUNGLE_LOGS))
                .save(consumer, getFileName(Items.JUNGLE_PLANKS, Items.JUNGLE_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.ACACIA_LOGS), Items.ACACIA_PLANKS, 6, 150, 40)
                .unlockedBy("has_acacia_log", has(ItemTags.ACACIA_LOGS))
                .save(consumer, getFileName(Items.ACACIA_PLANKS, Items.ACACIA_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.DARK_OAK_LOGS), Items.DARK_OAK_PLANKS, 6, 150, 40)
                .unlockedBy("has_dark_oak_log", has(ItemTags.DARK_OAK_LOGS))
                .save(consumer, getFileName(Items.DARK_OAK_PLANKS, Items.DARK_OAK_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.MANGROVE_LOGS), Items.MANGROVE_PLANKS, 6, 150, 40)
                .unlockedBy("has_mangrove_log", has(ItemTags.MANGROVE_LOGS))
                .save(consumer, getFileName(Items.MANGROVE_PLANKS, Items.MANGROVE_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.CHERRY_LOGS), Items.CHERRY_PLANKS, 6, 150, 40)
                .unlockedBy("has_cherry_log", has(ItemTags.CHERRY_LOGS))
                .save(consumer, getFileName(Items.CHERRY_PLANKS, Items.CHERRY_LOG));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.CRIMSON_STEMS), Items.CRIMSON_PLANKS, 6, 150, 40)
                .unlockedBy("has_crimson_stem", has(ItemTags.CRIMSON_STEMS))
                .save(consumer, getFileName(Items.CRIMSON_PLANKS, Items.CRIMSON_STEM));
        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WARPED_STEMS), Items.WARPED_PLANKS, 6, 150, 40)
                .unlockedBy("has_warped_log", has(ItemTags.WARPED_STEMS))
                .save(consumer, getFileName(Items.WARPED_PLANKS, Items.WARPED_STEM));

        SawmillRecipeBuilder.create(Ingredient.of(ModItems.RESIN_OAK_LOG.get()), Items.OAK_PLANKS, 6, 150, 40)
                .unlockedBy("has_resin_oak_log", has(ModItems.RESIN_OAK_LOG.get()))
                .save(consumer, getFileName(Items.OAK_PLANKS, ModItems.RESIN_OAK_LOG.get()));

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.PLANKS), Items.STICK, 4, 150, 40)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(consumer, "stick_from_planks");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_SLABS), Items.STICK, 2, 150, 40)
                .unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
                .save(consumer, "stick_from_wooden_slab");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_STAIRS), Items.STICK, 9, 150, 40)
                .unlockedBy("has_wooden_stairs", has(ItemTags.WOODEN_STAIRS))
                .save(consumer, "stick_from_wooden_stairs");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_FENCES), Items.STICK, 7, 150, 40)
                .unlockedBy("has_wooden_fence", has(ItemTags.WOODEN_FENCES))
                .save(consumer, "stick_from_wooden_fence");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.FENCE_GATES), Items.STICK, 12, 150, 40)
                .unlockedBy("has_fence_gate", has(ItemTags.FENCE_GATES))
                .save(consumer, "stick_from_fence_gate");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_BUTTONS), Items.STICK, 2, 150, 40)
                .unlockedBy("has_wooden_button", has(ItemTags.WOODEN_BUTTONS))
                .save(consumer, "stick_from_wooden_button");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_PRESSURE_PLATES), Items.STICK, 4, 150, 40)
                .unlockedBy("has_wooden_pressure_plate", has(ItemTags.WOODEN_PRESSURE_PLATES))
                .save(consumer, "stick_from_wooden_pressure_plate");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_DOORS), Items.STICK, 8, 150, 40)
                .unlockedBy("has_wooden_door", has(ItemTags.WOODEN_DOORS))
                .save(consumer, "stick_from_wooden_door");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.WOODEN_TRAPDOORS), Items.STICK, 12, 150, 40)
                .unlockedBy("has_wooden_trapdoor", has(ItemTags.WOODEN_TRAPDOORS))
                .save(consumer, "stick_from_wooden_trapdoor");

        SawmillRecipeBuilder.create(Ingredient.of(ItemTags.BOATS), Items.STICK, 16, 150, 40)
                .unlockedBy("has_boat", has(ItemTags.BOATS))
                .save(consumer, "stick_from_boats");

        SawmillRecipeBuilder.create(Ingredient.of(Items.CRAFTING_TABLE), Items.STICK, 16, 150, 40)
                .unlockedBy("has_crafting_table", has(Items.CRAFTING_TABLE))
                .save(consumer, getFileName(Items.STICK, Items.CRAFTING_TABLE));

        SawmillRecipeBuilder.create(Ingredient.of(Items.CHEST), Items.STICK, 24, 150, 40)
                .unlockedBy("has_chest", has(Items.CHEST))
                .save(consumer, getFileName(Items.STICK, Items.CHEST));

        SawmillRecipeBuilder.create(Ingredient.of(Items.BOWL), Items.STICK, 3, 150, 40)
                .unlockedBy("has_bowl", has(Items.BOWL))
                .save(consumer, getFileName(Items.STICK, Items.BOWL));
    }

    private void polarizer(Consumer<FinishedRecipe> consumer)
    {
        PolarizerRecipeBuilder.create(Ingredient.of(ModItems.IRON_ROD.get()), ModItems.IRON_ROD_POLARIZED.get(), 1, 200, 80)
                .unlockedBy(hasName(ModItems.IRON_ROD.get()), has(ModItems.IRON_ROD.get()))
                .save(consumer, getFileName(ModItems.IRON_ROD_POLARIZED.get(), ModItems.IRON_ROD.get()));

        PolarizerRecipeBuilder.create(Ingredient.of(ModItems.NICKEL_ROD.get()), ModItems.NICKEL_ROD_POLARIZED.get(), 1, 200, 80)
                .unlockedBy(hasName(ModItems.NICKEL_ROD.get()), has(ModItems.NICKEL_ROD.get()))
                .save(consumer, getFileName(ModItems.NICKEL_ROD_POLARIZED.get(), ModItems.NICKEL_ROD.get()));

        PolarizerRecipeBuilder.create(Ingredient.of(ModItems.INVAR_ROD.get()), ModItems.INVAR_ROD_POLARIZED.get(), 1, 200, 80)
                .unlockedBy(hasName(ModItems.INVAR_ROD.get()), has(ModItems.INVAR_ROD.get()))
                .save(consumer, getFileName(ModItems.INVAR_ROD_POLARIZED.get(), ModItems.INVAR_ROD.get()));

        PolarizerRecipeBuilder.create(Ingredient.of(ModItems.CONSTANTAN_ROD.get()), ModItems.CONSTANTAN_ROD_POLARIZED.get(), 1, 200, 80)
                .unlockedBy(hasName(ModItems.CONSTANTAN_ROD.get()), has(ModItems.CONSTANTAN_ROD.get()))
                .save(consumer, getFileName(ModItems.CONSTANTAN_ROD_POLARIZED.get(), ModItems.CONSTANTAN_ROD.get()));

        PolarizerRecipeBuilder.create(Ingredient.of(ModItems.STEEL_ROD.get()), ModItems.STEEL_ROD_POLARIZED.get(), 1, 200, 80)
                .unlockedBy(hasName(ModItems.STEEL_ROD.get()), has(ModItems.STEEL_ROD.get()))
                .save(consumer, getFileName(ModItems.STEEL_ROD_POLARIZED.get(), ModItems.STEEL_ROD.get()));
    }

    private void upgrades(Consumer<FinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INTERMEDIATE_UPGRADE.get(), 1)
                .pattern("MLM")
                .pattern("LDL")
                .pattern("MLM")
                .define('M', CommonTags.Items.INGOTS_MAGNESIUM)
                .define('L', CommonTags.Items.INGOTS_LITHIUM)
                .define('D', Tags.Items.GEMS_DIAMOND)
                .unlockedBy("has_diamond", has(Tags.Items.GEMS_DIAMOND))
                .save(consumer, getFileName(ModItems.INTERMEDIATE_UPGRADE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADVANCED_UPGRADE.get(), 1)
                .pattern("ZCZ")
                .pattern("CUC")
                .pattern("ZCZ")
                .define('Z', CommonTags.Items.INGOTS_ZINC)
                .define('C', ModItems.CITRINE.get())
                .define('U', ModItems.INTERMEDIATE_UPGRADE.get())
                .unlockedBy(hasName(ModItems.INTERMEDIATE_UPGRADE.get()), has(ModItems.INTERMEDIATE_UPGRADE.get()))
                .save(consumer, getFileName(ModItems.ADVANCED_UPGRADE.get()));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ULTRA_UPGRADE.get(), 1)
                .pattern("LTL")
                .pattern("TUT")
                .pattern("LTL")
                .define('L', CommonTags.Items.INGOTS_LEAD)
                .define('T', ModItems.TANZANITE.get())
                .define('U', ModItems.ADVANCED_UPGRADE.get())
                .unlockedBy(hasName(ModItems.ADVANCED_UPGRADE.get()), has(ModItems.ADVANCED_UPGRADE.get()))
                .save(consumer, getFileName(ModItems.ULTRA_UPGRADE.get()));

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
                .save(consumer, getFileName(ModItems.EXTREME_UPGRADE.get()));
    }

    private String hasName(ItemLike item)
    {
        return "has_" + ForgeRegistries.ITEMS.getKey(item.asItem()).getPath();
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
