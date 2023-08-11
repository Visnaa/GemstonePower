package com.visnaa.gemstonepower.block.pipe.cable;

import com.visnaa.gemstonepower.block.entity.TickingBlockEntity;
import com.visnaa.gemstonepower.block.entity.pipe.cable.CopperCableBE;
import com.visnaa.gemstonepower.client.render.Tintable;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class CopperCableBlock extends BaseEntityBlock implements Tintable
{
    public static final HashMap<Direction, BooleanProperty> CONNECTIONS = createConnections();

    public CopperCableBlock(Properties properties)
    {
        super(properties);
        registerStates();
        Tints.TINTED_BLOCKS.add(this);
    }

    private void registerStates()
    {
        registerDefaultState(defaultBlockState().setValue(CONNECTIONS.get(Direction.NORTH), false));
        registerDefaultState(defaultBlockState().setValue(CONNECTIONS.get(Direction.SOUTH), false));
        registerDefaultState(defaultBlockState().setValue(CONNECTIONS.get(Direction.EAST), false));
        registerDefaultState(defaultBlockState().setValue(CONNECTIONS.get(Direction.WEST), false));
        registerDefaultState(defaultBlockState().setValue(CONNECTIONS.get(Direction.UP), false));
        registerDefaultState(defaultBlockState().setValue(CONNECTIONS.get(Direction.DOWN), false));
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

        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
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
        VoxelShape shape = Block.box(6, 6, 6, 10, 10, 10),
                north = Block.box(6, 6, 0, 10, 10, 6),
                south = Block.box(6, 6, 10, 10, 10, 16),
                east = Block.box(10, 6, 6, 16, 10, 10),
                west = Block.box(0, 6, 6, 6, 10, 10),
                up = Block.box(6, 10, 6, 10, 16, 10),
                down = Block.box(6, 0, 6, 10, 10, 10);
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
    public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new CopperCableBE(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity)
    {
        return createTicker(level, blockEntity, ModBlockEntities.COPPER_CABLE.get());
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> blockEntity, BlockEntityType<? extends CopperCableBE> cable)
    {
        return level.isClientSide ? null : createTickerHelper(blockEntity, cable, TickingBlockEntity::serverTick);
    }

    @Override
    public int getColor()
    {
        return Tints.COPPER.getColor();
    }
}
