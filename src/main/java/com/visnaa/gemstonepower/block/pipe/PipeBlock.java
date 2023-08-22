package com.visnaa.gemstonepower.block.pipe;

import com.visnaa.gemstonepower.block.entity.pipe.PipeBE;
import com.visnaa.gemstonepower.block.entity.pipe.item.ItemPipeBE;
import com.visnaa.gemstonepower.block.pipe.cable.CableBlock;
import com.visnaa.gemstonepower.block.pipe.fluid.FluidPipeBlock;
import com.visnaa.gemstonepower.block.pipe.item.ItemPipeBlock;
import com.visnaa.gemstonepower.pipe.energy.EnergyStorage;
import com.visnaa.gemstonepower.util.StringProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.EmptyFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.EmptyHandler;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public abstract class PipeBlock extends BaseEntityBlock
{
    public static final HashMap<Direction, StringProperty> CONNECTIONS = createConnections();

    public PipeBlock(Properties properties)
    {
        super(properties);
        registerStates();
    }

    private void registerStates()
    {
        for (Direction direction : Direction.values())
            registerDefaultState(this.stateDefinition.any().setValue(CONNECTIONS.get(direction), "false"));
    }

    private static HashMap<Direction, StringProperty> createConnections()
    {
        HashMap<Direction, StringProperty> map = new HashMap<>();
        for (Direction direction : Direction.values())
            map.put(direction, StringProperty.create(direction.getName(), "false", "true", "extracts"));
        return map;
    }

    public void cycleConnection(Level level, BlockPos pos, BlockState state, Direction direction)
    {
        if (level == null || level.isClientSide() || !(level.getBlockEntity(pos) instanceof PipeBE pipe) || !state.hasProperty(CONNECTIONS.get(direction)))
            return;

        StringProperty connection = CONNECTIONS.get(direction);
        BlockEntity be = level.getBlockEntity(pos.relative(direction));
        switch (state.getValue(connection))
        {
            case "false" -> state = state.setValue(connection, "true");
            case "true" -> state = be instanceof PipeBE ? state.setValue(connection, "false") : state.setValue(connection, "extracts");
            case "extracts" -> state = state.setValue(connection, "false");
        }
        pipe.playerChangedConnection(level, pos, direction, state.getValue(connection).equals("false"));
        level.setBlockAndUpdate(pos, state);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        for (StringProperty connection : CONNECTIONS.values())
            defaultBlockState().setValue(connection, "false");

        BlockState state = defaultBlockState();
        for (Direction direction : Direction.values())
        {
            BlockEntity be = context.getLevel().getBlockEntity(context.getClickedPos().relative(direction.getOpposite()));
            if (be == null)
                continue;

            if (be.getCapability(ForgeCapabilities.ENERGY).isPresent() && this instanceof CableBlock)
            {
                IEnergyStorage handler = be.getCapability(ForgeCapabilities.ENERGY, direction).orElse(EnergyStorage.EMPTY);
                if (handler != EnergyStorage.EMPTY && (handler.canReceive() || handler.canExtract()))
                    state = state.setValue(CONNECTIONS.get(direction.getOpposite()), "true");
            }
            else if (be.getCapability(ForgeCapabilities.ITEM_HANDLER).isPresent() && this instanceof ItemPipeBlock)
            {
                IItemHandler handler = be.getCapability(ForgeCapabilities.ITEM_HANDLER, direction).orElse(EmptyHandler.INSTANCE);
                if (handler != EmptyHandler.INSTANCE && handler.getSlots() > 0)
                    state = state.setValue(CONNECTIONS.get(direction.getOpposite()), "true");
            }
            else if (be.getCapability(ForgeCapabilities.FLUID_HANDLER).isPresent() && this instanceof FluidPipeBlock)
            {
                IFluidHandler handler = be.getCapability(ForgeCapabilities.FLUID_HANDLER, direction).orElse(EmptyFluidHandler.INSTANCE);
                if (handler != EmptyFluidHandler.INSTANCE)
                    state = state.setValue(CONNECTIONS.get(direction.getOpposite()), "true");
            }
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        for (Direction direction : Direction.values())
            builder.add(CONNECTIONS.get(direction));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context)
    {
        VoxelShape shape = Block.box(5, 5, 5, 11, 11, 11),
                north = Block.box(5, 5, 0, 11, 11, 5),
                south = Block.box(5, 5, 11, 11, 11, 16),
                east = Block.box(11, 5, 5, 16, 11, 11),
                west = Block.box(0, 5, 5, 5, 11, 11),
                up = Block.box(5, 11, 5, 11, 16, 11),
                down = Block.box(5, 0, 5, 11, 11, 11);
        if (!state.getValue(CONNECTIONS.get(Direction.NORTH)).equals("false"))
            shape = Shapes.or(shape, north);
        if (!state.getValue(CONNECTIONS.get(Direction.SOUTH)).equals("false"))
            shape = Shapes.or(shape, south);
        if (!state.getValue(CONNECTIONS.get(Direction.EAST)).equals("false"))
            shape = Shapes.or(shape, east);
        if (!state.getValue(CONNECTIONS.get(Direction.WEST)).equals("false"))
            shape = Shapes.or(shape, west);
        if (!state.getValue(CONNECTIONS.get(Direction.UP)).equals("false"))
            shape = Shapes.or(shape, up);
        if (!state.getValue(CONNECTIONS.get(Direction.DOWN)).equals("false"))
            shape = Shapes.or(shape, down);

        return shape;
    }

    @Override
    public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid)
    {
        if (state.is(state.getBlock()))
        {
            if (level.getBlockEntity(pos) instanceof ItemPipeBE)
            {
                if (level instanceof ServerLevel && !player.isCreative() && willHarvest)
                    Containers.dropContents(level, pos, NonNullList.withSize(1, new ItemStack(this.asItem())));
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
