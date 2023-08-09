package com.visnaa.gemstonepower.block.pipe.item;

import com.visnaa.gemstonepower.block.entity.pipe.item.BaseItemPipeBE;
import com.visnaa.gemstonepower.registry.ModItems;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public abstract class BaseItemPipeBlock extends BaseEntityBlock
{
    public static final BooleanProperty EXTRACTS = BooleanProperty.create("extracts");
    public static final HashMap<Direction, BooleanProperty> CONNECTIONS = createConnections();

    public BaseItemPipeBlock(Properties properties)
    {
        super(properties);
        registerStates();
    }

    private void registerStates()
    {
        registerDefaultState(this.stateDefinition.any().setValue(CONNECTIONS.get(Direction.NORTH), false));
        registerDefaultState(this.stateDefinition.any().setValue(CONNECTIONS.get(Direction.SOUTH), false));
        registerDefaultState(this.stateDefinition.any().setValue(CONNECTIONS.get(Direction.EAST), false));
        registerDefaultState(this.stateDefinition.any().setValue(CONNECTIONS.get(Direction.WEST), false));
        registerDefaultState(this.stateDefinition.any().setValue(CONNECTIONS.get(Direction.UP), false));
        registerDefaultState(this.stateDefinition.any().setValue(CONNECTIONS.get(Direction.DOWN), false));
    }

    private static HashMap<Direction, BooleanProperty> createConnections()
    {
        HashMap<Direction, BooleanProperty> map = new HashMap<>();
        map.put(Direction.NORTH, BooleanProperty.create("north"));
        map.put(Direction.SOUTH, BooleanProperty.create("south"));
        map.put(Direction.EAST, BooleanProperty.create("east"));
        map.put(Direction.WEST, BooleanProperty.create("west"));
        map.put(Direction.UP, BooleanProperty.create("up"));
        map.put(Direction.DOWN, BooleanProperty.create("down"));
        return map;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        for (BooleanProperty connection : CONNECTIONS.values())
        {
            defaultBlockState().setValue(connection, false);
        }

        BlockState state = defaultBlockState();
        for (Direction direction : Direction.values())
        {
            BlockEntity be = context.getLevel().getBlockEntity(context.getClickedPos().relative(direction.getOpposite()));
            if (be != null)
            {
                be.getCapability(ForgeCapabilities.ENERGY, direction).map(handler ->
                {
                    if (handler.getMaxEnergyStored() > 0)
                    {
                        state.setValue(CONNECTIONS.get(direction.getOpposite()), true);
                    }
                    return false;
                });
            }
        }

        return state.setValue(EXTRACTS, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(EXTRACTS);
        builder.add(CONNECTIONS.get(Direction.NORTH));
        builder.add(CONNECTIONS.get(Direction.SOUTH));
        builder.add(CONNECTIONS.get(Direction.EAST));
        builder.add(CONNECTIONS.get(Direction.WEST));
        builder.add(CONNECTIONS.get(Direction.UP));
        builder.add(CONNECTIONS.get(Direction.DOWN));
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
        if (state.getValue(CONNECTIONS.get(Direction.NORTH)))
            shape = Shapes.or(shape, north);
        if (state.getValue(CONNECTIONS.get(Direction.SOUTH)))
            shape = Shapes.or(shape, south);
        if (state.getValue(CONNECTIONS.get(Direction.EAST)))
            shape = Shapes.or(shape, east);
        if (state.getValue(CONNECTIONS.get(Direction.WEST)))
            shape = Shapes.or(shape, west);
        if (state.getValue(CONNECTIONS.get(Direction.UP)))
            shape = Shapes.or(shape, up);
        if (state.getValue(CONNECTIONS.get(Direction.DOWN)))
            shape = Shapes.or(shape, down);

        return shape;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid)
    {
        if (state.is(state.getBlock()))
        {
            if (level.getBlockEntity(pos) instanceof BaseItemPipeBE)
            {
                if (level instanceof ServerLevel && !player.isCreative() && willHarvest)
                {
                    Containers.dropContents(level, pos, NonNullList.withSize(1, new ItemStack(ModItems.PIPE_EXTRACTOR_UPGRADE.get())));
                    Containers.dropContents(level, pos, NonNullList.withSize(1, new ItemStack(this.asItem())));
                }
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
