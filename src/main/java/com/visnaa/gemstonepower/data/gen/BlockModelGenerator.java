package com.visnaa.gemstonepower.data.gen;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.pipe.PipeBlock;
import com.visnaa.gemstonepower.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.*;

public class BlockModelGenerator extends BlockStateProvider
{
    public BlockModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, GemstonePower.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        this.crystal(ModBlocks.RUBY_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.SAPPHIRE_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.AQUAMARINE_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.JADE_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.OPAL_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.YELLOW_DIAMOND_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.AMBER_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.TOPAZ_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.BERYLLIUM_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.BIXBIT_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.MALACHITE_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.ONYX_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.PERIDOT_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.MOON_STONE_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.SUN_STONE_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.CITRINE_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.DOLOMITE_CRYSTALS.get(), "crystals");
        this.crystal(ModBlocks.TANZANITE_CRYSTALS.get(), "crystals");

        this.block(ModBlocks.ALUMINUM_BLOCK.get(), "storage_block");
        this.ore(ModBlocks.ALUMINUM_ORE.get(), "ore");
        this.ore(ModBlocks.DEEPSLATE_ALUMINUM_ORE.get(), "ore_deepslate");

        this.block(ModBlocks.TIN_BLOCK.get(), "storage_block");
        this.ore(ModBlocks.TIN_ORE.get(), "ore");
        this.ore(ModBlocks.DEEPSLATE_TIN_ORE.get(), "ore_deepslate");

        this.block(ModBlocks.BRONZE_BLOCK.get(), "storage_block");

        this.block(ModBlocks.SILVER_BLOCK.get(), "storage_block");
        this.ore(ModBlocks.SILVER_ORE.get(), "ore");
        this.ore(ModBlocks.DEEPSLATE_SILVER_ORE.get(), "ore_deepslate");

        this.block(ModBlocks.NICKEL_BLOCK.get(), "storage_block");
        this.ore(ModBlocks.NICKEL_ORE.get(), "ore");
        this.ore(ModBlocks.DEEPSLATE_NICKEL_ORE.get(), "ore_deepslate");

        this.block(ModBlocks.ELECTRUM_BLOCK.get(), "storage_block");

        this.block(ModBlocks.INVAR_BLOCK.get(), "storage_block");

        this.block(ModBlocks.CONSTANTAN_BLOCK.get(), "storage_block");

        this.block(ModBlocks.PLATINUM_BLOCK.get(), "storage_block");
        this.ore(ModBlocks.PLATINUM_ORE.get(), "ore");
        this.ore(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(), "ore_deepslate");

        this.block(ModBlocks.STEEL_BLOCK.get(), "storage_block");

        this.block(ModBlocks.LITHIUM_BLOCK.get(), "storage_block");
        this.ore(ModBlocks.LITHIUM_ORE.get(), "ore");
        this.ore(ModBlocks.DEEPSLATE_LITHIUM_ORE.get(), "ore_deepslate");

        this.block(ModBlocks.MAGNESIUM_BLOCK.get(), "storage_block");
        this.ore(ModBlocks.MAGNESIUM_ORE.get(), "ore");
        this.ore(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get(), "ore_deepslate");

        this.block(ModBlocks.URANIUM_BLOCK.get(), "storage_block");
        this.ore(ModBlocks.URANIUM_ORE.get(), "ore");
        this.ore(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), "ore_deepslate");

        this.block(ModBlocks.LEAD_BLOCK.get(), "storage_block");
        this.ore(ModBlocks.LEAD_ORE.get(), "ore");
        this.ore(ModBlocks.DEEPSLATE_LEAD_ORE.get(), "ore_deepslate");

        this.block(ModBlocks.ZINC_BLOCK.get(), "storage_block");
        this.ore(ModBlocks.ZINC_ORE.get(), "ore");
        this.ore(ModBlocks.DEEPSLATE_ZINC_ORE.get(), "ore_deepslate");

        this.blockWithItem(ModBlocks.GEMSTONE_GENERATOR.get(),"gemstone_generator");
        this.getVariantBuilder(ModBlocks.GEMSTONE_GENERATOR.get())
            .forAllStates(state -> ConfiguredModel.builder()
                    .modelFile(models().cube(state.getValue(POWERED) ? "gemstone_generator_powered" : "gemstone_generator",
                            modLoc("block/gemstone_bottom"),
                            modLoc("block/gemstone_top"),
                            state.getValue(POWERED) ? modLoc("block/gemstone_generator_powered") : modLoc("block/gemstone_generator"),
                            modLoc("block/gemstone_back"),
                            modLoc("block/gemstone_side"),
                            modLoc("block/gemstone_side")).texture("particle", modLoc("block/gemstone_generator")))
                    .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                    .build());

        this.simpleBlockWithItem(ModBlocks.GEMSTONE_CELL.get(), models().cube("gemstone_cell",
                modLoc("block/gemstone_bottom"),
                modLoc("block/gemstone_top"),
                modLoc("block/gemstone_cell_ns"),
                modLoc("block/gemstone_cell_ns"),
                modLoc("block/gemstone_cell_we"),
                modLoc("block/gemstone_cell_we")).texture("particle", modLoc("block/gemstone_cell_ns")));

        this.blockWithItem(ModBlocks.CRYSTAL_GROWER.get(),"crystal_grower");
        this.getVariantBuilder(ModBlocks.CRYSTAL_GROWER.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().withExistingParent("crystal_grower", modLoc("block/machine"))
                                .texture("machine", modLoc("block/crystal_grower"))
                                .texture("particle", modLoc("block/crystal_grower")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.blockWithItem(ModBlocks.CRYSTAL_CHARGER.get(),"crystal_charger");
        this.getVariantBuilder(ModBlocks.CRYSTAL_CHARGER.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().withExistingParent("crystal_charger", modLoc("block/machine"))
                                .texture("machine", modLoc("block/crystal_charger"))
                                .texture("particle", modLoc("block/crystal_charger")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.blockWithItem(ModBlocks.ELECTRIC_FURNACE.get(),"electric_furnace");
        this.getVariantBuilder(ModBlocks.ELECTRIC_FURNACE.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().withExistingParent("electric_furnace", modLoc("block/machine"))
                                .texture("machine", modLoc("block/electric_furnace"))
                                .texture("particle", modLoc("block/electric_furnace")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.blockWithItem(ModBlocks.METAL_FORMER.get(),"metal_former");
        this.getVariantBuilder(ModBlocks.METAL_FORMER.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().withExistingParent("metal_former", modLoc("block/machine"))
                                .texture("machine", modLoc("block/metal_former"))
                                .texture("particle", modLoc("block/metal_former")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.blockWithItem(ModBlocks.PULVERIZER.get(),"pulverizer");
        this.getVariantBuilder(ModBlocks.PULVERIZER.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().withExistingParent("pulverizer", modLoc("block/machine"))
                                .texture("machine", modLoc("block/pulverizer"))
                                .texture("particle", modLoc("block/pulverizer")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.blockWithItem(ModBlocks.ALLOY_SMELTER.get(),"alloy_smelter");
        this.getVariantBuilder(ModBlocks.ALLOY_SMELTER.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().withExistingParent("alloy_smelter", modLoc("block/machine"))
                                .texture("machine", modLoc("block/alloy_smelter"))
                                .texture("particle", modLoc("block/alloy_smelter")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.blockWithItem(ModBlocks.EXTRACTOR.get(),"extractor");
        this.getVariantBuilder(ModBlocks.EXTRACTOR.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().withExistingParent("extractor", modLoc("block/machine"))
                                .texture("machine", modLoc("block/extractor"))
                                .texture("particle", modLoc("block/extractor")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.blockWithItem(ModBlocks.ORE_WASHER.get(),"ore_washer");
        this.getVariantBuilder(ModBlocks.ORE_WASHER.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().withExistingParent("ore_washer", modLoc("block/machine"))
                                .texture("machine", modLoc("block/ore_washer"))
                                .texture("particle", modLoc("block/ore_washer")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.blockWithItem(ModBlocks.COBBLESTONE_GENERATOR.get(),"cobblestone_generator");
        this.getVariantBuilder(ModBlocks.COBBLESTONE_GENERATOR.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().withExistingParent("cobblestone_generator", modLoc("block/machine"))
                                .texture("machine", modLoc("block/cobblestone_generator"))
                                .texture("particle", modLoc("block/cobblestone_generator")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.blockWithItem(ModBlocks.SAWMILL.get(),"sawmill");
        this.getVariantBuilder(ModBlocks.SAWMILL.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().withExistingParent("sawmill", modLoc("block/machine"))
                                .texture("machine", modLoc("block/sawmill"))
                                .texture("particle", modLoc("block/sawmill")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.blockWithItem(ModBlocks.POLARIZER.get(),"polarizer");
        this.getVariantBuilder(ModBlocks.POLARIZER.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().withExistingParent("polarizer", modLoc("block/machine"))
                                .texture("machine", modLoc("block/polarizer"))
                                .texture("particle", modLoc("block/polarizer")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.blockWithItem(ModBlocks.GEMSTONE_MANIPULATOR.get(),"gemstone_manipulator");
        this.getVariantBuilder(ModBlocks.GEMSTONE_MANIPULATOR.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().withExistingParent("gemstone_manipulator", modLoc("block/machine"))
                                .texture("machine", modLoc("block/gemstone_manipulator"))
                                .texture("particle", modLoc("block/gemstone_manipulator")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.getVariantBuilder(ModBlocks.SOLAR_PANEL.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().getExistingFile(modLoc("solar_panel")))
                        .build());

        this.getVariantBuilder(ModBlocks.WATER_MILL.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().getExistingFile(modLoc("water_mill")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.getVariantBuilder(ModBlocks.WIND_TURBINE.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(models().getExistingFile(modLoc("wind_turbine")))
                        .rotationY((int) state.getValue(HORIZONTAL_FACING).getOpposite().toYRot())
                        .build());

        this.custom(ModBlocks.TANK.get(), "tank");

        this.blockWithItem(ModBlocks.FISSION_REACTOR.get(), "fission_reactor");
        this.horizontalBlock(ModBlocks.FISSION_REACTOR.get(), modLoc("block/reactor_wall"), modLoc("block/fission_reactor"), modLoc("block/reactor_wall"));
        this.simpleBlockWithItem(ModBlocks.REACTOR_WALL.get(), models().cubeAll("reactor_wall", modLoc("block/reactor_wall")));
        this.simpleBlockWithItem(ModBlocks.REACTOR_FRAME.get(), models().cubeAll("reactor_frame", modLoc("block/reactor_frame")).renderType("cutout"));
        this.cooling(ModBlocks.WATER_COOLING.get(), mcLoc("block/water_still"));
        this.cooling(ModBlocks.SNOW_COOLING.get(), mcLoc("block/snow"));
        this.cooling(ModBlocks.ICE_COOLING.get(), mcLoc("block/ice"));
        this.cooling(ModBlocks.PACKED_ICE_COOLING.get(), mcLoc("block/packed_ice"));
        this.cooling(ModBlocks.BLUE_ICE_COOLING.get(), mcLoc("block/blue_ice"));
        this.cooling(ModBlocks.PRISMARINE_COOLING.get(), mcLoc("block/prismarine"));

        this.simpleBlock(ModBlocks.RESIN_OAK_SAPLING.get(), models().cross("resin_oak_sapling", modLoc("block/resin_oak_sapling")).renderType("cutout"));
        this.blockWithItem(ModBlocks.RESIN_OAK_LOG.get(), "resin_oak_log");
        this.logBlock((RotatedPillarBlock) ModBlocks.RESIN_OAK_LOG.get());
        this.simpleBlockWithItem(ModBlocks.RESIN_OAK_LEAVES.get(), models().getExistingFile(mcLoc("block/oak_leaves")));

        this.wire(ModBlocks.COPPER_WIRE.get());
        this.cable(ModBlocks.COPPER_CABLE.get());
        this.wire(ModBlocks.ALUMINUM_WIRE.get());
        this.cable(ModBlocks.ALUMINUM_CABLE.get());
        this.wire(ModBlocks.TIN_WIRE.get());
        this.cable(ModBlocks.TIN_CABLE.get());
        this.wire(ModBlocks.ELECTRUM_WIRE.get());
        this.cable(ModBlocks.ELECTRUM_CABLE.get());
        this.cable(ModBlocks.GEMSTONE_CABLE.get(), "block/gemstone_cable_rubber","block/gemstone_cable_wire");

        this.pipe(ModBlocks.IRON_ITEM_PIPE.get(), "item");
        this.pipe(ModBlocks.GOLD_ITEM_PIPE.get(), "item");
        this.pipe(ModBlocks.COPPER_ITEM_PIPE.get(), "item");
        this.pipe(ModBlocks.PLATINUM_ITEM_PIPE.get(), "item");
        this.pipe(ModBlocks.LEAD_ITEM_PIPE.get(), "item");

        this.pipe(ModBlocks.GOLD_FLUID_PIPE.get(), "fluid");
        this.pipe(ModBlocks.COPPER_FLUID_PIPE.get(), "fluid");
        this.pipe(ModBlocks.INVAR_FLUID_PIPE.get(), "fluid");
        this.pipe(ModBlocks.STEEL_FLUID_PIPE.get(), "fluid");
    }

    public void block(Block block, String parentName)
    {
        this.simpleBlockWithItem(block, parent(block, parentName));
    }

    public void blockWithItem(Block block, String parentName)
    {
        this.simpleBlockItem(block, parent(block, parentName));
    }

    public void blockItem(Block block, String name)
    {
        this.simpleBlockItem(block, models().getExistingFile(modLoc(name)));
    }

    public void ore(Block block, String parentName)
    {
        this.simpleBlockWithItem(block, parent(block, parentName).renderType("cutout"));
    }

    public void custom(Block block, String parentName)
    {
        this.simpleBlockWithItem(block, model(parentName));
    }

    public void cooling(Block block, ResourceLocation type)
    {
        this.simpleBlockWithItem(block, parent(block, "cooling").texture("type", type));
    }

    public BlockModelBuilder parent(Block block, String parentName)
    {
        return parent(BuiltInRegistries.BLOCK.getKey(block).getPath(), parentName);
    }

    public BlockModelBuilder parent(String block, String parentName)
    {
        return models().withExistingParent(block, modLoc(parentName));
    }

    public ModelFile.ExistingModelFile model(String parentName)
    {
        return models().getExistingFile(modLoc(parentName));
    }

    public void crystal(Block block, String texture)
    {
        this.blockWithItem(block, "crystals");
        this.getMultipartBuilder(block)
            .part()
                .modelFile(parent(block, texture))
                .rotationX(270)
                .addModel()
                .condition(UP, true)
                .end()
            .part()
                .modelFile(parent(block, texture))
                .rotationX(90)
                .addModel()
                .condition(DOWN, true)
                .end()
            .part()
                .modelFile(parent(block, texture))
                .addModel()
                .condition(NORTH, true)
                .end()
            .part()
                .modelFile(parent(block, texture))
                .rotationY(180)
                .addModel()
                .condition(SOUTH, true)
                .end()
            .part()
                .modelFile(parent(block, texture))
                .rotationY(270)
                .addModel()
                .condition(WEST, true)
                .end()
            .part()
                .modelFile(parent(block, texture))
                .rotationY(90)
                .addModel()
                .condition(EAST, true)
                .end();
    }

    public void pipe(Block block, String modelPrefix)
    {
        this.blockItem(block, modelPrefix + "_pipe_dot");
        this.getMultipartBuilder(block)
            .part()
                .modelFile(models().getExistingFile(modLoc(modelPrefix + "_pipe_dot")))
                .addModel()
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc(modelPrefix + "_pipe_arm")))
                .rotationX(270)
                .addModel()
                .condition(PipeBlock.CONNECTIONS.get(Direction.UP), "true")
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc(modelPrefix + "_pipe_ext")))
                .rotationX(270)
                .addModel()
                .condition(PipeBlock.CONNECTIONS.get(Direction.UP), "extracts")
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc(modelPrefix + "_pipe_arm")))
                .rotationX(90)
                .addModel()
                .condition(PipeBlock.CONNECTIONS.get(Direction.DOWN), "true")
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc(modelPrefix + "_pipe_ext")))
                .rotationX(90)
                .addModel()
                .condition(PipeBlock.CONNECTIONS.get(Direction.DOWN), "extracts")
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc(modelPrefix + "_pipe_arm")))
                .addModel()
                .condition(PipeBlock.CONNECTIONS.get(Direction.NORTH), "true")
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc(modelPrefix + "_pipe_ext")))
                .addModel()
                .condition(PipeBlock.CONNECTIONS.get(Direction.NORTH), "extracts")
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc(modelPrefix + "_pipe_arm")))
                .rotationY(180)
                .addModel()
                .condition(PipeBlock.CONNECTIONS.get(Direction.SOUTH), "true")
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc(modelPrefix + "_pipe_ext")))
                .rotationY(180)
                .addModel()
                .condition(PipeBlock.CONNECTIONS.get(Direction.SOUTH), "extracts")
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc(modelPrefix + "_pipe_arm")))
                .rotationY(270)
                .addModel()
                .condition(PipeBlock.CONNECTIONS.get(Direction.WEST), "true")
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc(modelPrefix + "_pipe_ext")))
                .rotationY(270)
                .addModel()
                .condition(PipeBlock.CONNECTIONS.get(Direction.WEST), "extracts")
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc(modelPrefix + "_pipe_arm")))
                .rotationY(90)
                .addModel()
                .condition(PipeBlock.CONNECTIONS.get(Direction.EAST), "true")
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc(modelPrefix + "_pipe_ext")))
                .rotationY(90)
                .addModel()
                .condition(PipeBlock.CONNECTIONS.get(Direction.EAST), "extracts")
                .end();
    }

    public void cable(Block block)
    {
        cable(block, "block/cable_rubber", "block/cable_wire");
    }

    public void cable(Block block, String rubberTexture, String wireTexture)
    {
        this.blockItem(block, "cable_dot");
        String blockName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        this.getMultipartBuilder(block)
            .part()
                .modelFile(parent(blockName + "_dot", "cable_dot")
                        .texture("particle", modLoc(rubberTexture))
                        .texture("rubber", modLoc(rubberTexture))
                        .texture("wire", modLoc(wireTexture)))
                .addModel()
                .end()
            .part()
                .modelFile(parent(blockName + "_arm", "cable_arm")
                        .texture("particle", modLoc(rubberTexture))
                        .texture("rubber", modLoc(rubberTexture))
                        .texture("wire", modLoc(wireTexture)))
                .rotationX(270)
                .addModel()
                .condition(UP, true)
                .end()
            .part()
                .modelFile(parent(blockName + "_arm", "cable_arm")
                        .texture("particle", modLoc(rubberTexture))
                        .texture("rubber", modLoc(rubberTexture))
                        .texture("wire", modLoc(wireTexture)))
                .rotationX(90)
                .addModel()
                .condition(DOWN, true)
                .end()
            .part()
                .modelFile(parent(blockName + "_arm", "cable_arm")
                        .texture("particle", modLoc(rubberTexture))
                        .texture("rubber", modLoc(rubberTexture))
                        .texture("wire", modLoc(wireTexture)))
                .addModel()
                .condition(NORTH, true)
                .end()
            .part()
                .modelFile(parent(blockName + "_arm", "cable_arm")
                        .texture("particle", modLoc(rubberTexture))
                        .texture("rubber", modLoc(rubberTexture))
                        .texture("wire", modLoc(wireTexture)))
                .rotationY(180)
                .addModel()
                .condition(SOUTH, true)
                .end()
            .part()
                .modelFile(parent(blockName + "_arm", "cable_arm")
                        .texture("particle", modLoc(rubberTexture))
                        .texture("rubber", modLoc(rubberTexture))
                        .texture("wire", modLoc(wireTexture)))
                .rotationY(270)
                .addModel()
                .condition(WEST, true)
                .end()
            .part()
                .modelFile(parent(blockName + "_arm", "cable_arm")
                        .texture("particle", modLoc(rubberTexture))
                        .texture("rubber", modLoc(rubberTexture))
                        .texture("wire", modLoc(wireTexture)))
                .rotationY(90)
                .addModel()
                .condition(EAST, true)
                .end();
    }

    public void wire(Block block)
    {
        this.blockItem(block, "wire_dot");
        this.getMultipartBuilder(block)
            .part()
                .modelFile(models().getExistingFile(modLoc("wire_dot")))
                .addModel()
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc("wire_arm")))
                .rotationX(270)
                .addModel()
                .condition(UP, true)
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc("wire_arm")))
                .rotationX(90)
                .addModel()
                .condition(DOWN, true)
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc("wire_arm")))
                .addModel()
                .condition(NORTH, true)
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc("wire_arm")))
                .rotationY(180)
                .addModel()
                .condition(SOUTH, true)
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc("wire_arm")))
                .rotationY(270)
                .addModel()
                .condition(WEST, true)
                .end()
            .part()
                .modelFile(models().getExistingFile(modLoc("wire_arm")))
                .rotationY(90)
                .addModel()
                .condition(EAST, true)
                .end();
    }
}
