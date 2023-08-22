package com.visnaa.gemstonepower.block.pipe.cable;

import com.visnaa.gemstonepower.block.entity.pipe.cable.CableBE;
import com.visnaa.gemstonepower.block.pipe.PipeBlock;
import com.visnaa.gemstonepower.client.render.Tintable;
import com.visnaa.gemstonepower.client.render.Tints;
import com.visnaa.gemstonepower.init.ModDamageTypes;
import com.visnaa.gemstonepower.util.StringProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public abstract class CableBlock extends PipeBlock implements Tintable
{
    public static final BooleanProperty ISOLATED = BooleanProperty.create("isolated");
    private Tints color;
    private boolean isIsolated;

    public CableBlock(Properties properties, boolean isIsolated, Tints color)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ISOLATED, isIsolated));
        this.isIsolated = isIsolated;
        Tints.TINTED_BLOCKS.add(this);
        this.color = color;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context)
    {
        if (!state.getValue(ISOLATED))
        {
            VoxelShape shape = Block.box(7, 7, 7, 9, 9, 9),
                    north = Block.box(7, 7, 0, 9, 9, 7),
                    south = Block.box(7, 7, 9, 9, 9, 16),
                    east = Block.box(9, 7, 7, 16, 9, 9),
                    west = Block.box(0, 7, 7, 7, 9, 9),
                    up = Block.box(7, 9, 7, 9, 16, 9),
                    down = Block.box(7, 0, 7, 9, 9, 9);
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

        VoxelShape shape = Block.box(6, 6, 6, 10, 10, 10),
                north = Block.box(6, 6, 0, 10, 10, 6),
                south = Block.box(6, 6, 10, 10, 10, 16),
                east = Block.box(10, 6, 6, 16, 10, 10),
                west = Block.box(0, 6, 6, 6, 10, 10),
                up = Block.box(6, 10, 6, 10, 16, 10),
                down = Block.box(6, 0, 6, 10, 10, 10);
        if (state.getValue(CONNECTIONS.get(Direction.NORTH)).equals("true"))
            shape = Shapes.or(shape, north);
        if (state.getValue(CONNECTIONS.get(Direction.SOUTH)).equals("true"))
            shape = Shapes.or(shape, south);
        if (state.getValue(CONNECTIONS.get(Direction.EAST)).equals("true"))
            shape = Shapes.or(shape, east);
        if (state.getValue(CONNECTIONS.get(Direction.WEST)).equals("true"))
            shape = Shapes.or(shape, west);
        if (state.getValue(CONNECTIONS.get(Direction.UP)).equals("true"))
            shape = Shapes.or(shape, up);
        if (state.getValue(CONNECTIONS.get(Direction.DOWN)).equals("true"))
            shape = Shapes.or(shape, down);

        return shape;
    }

    @Override
    public void cycleConnection(Level level, BlockPos pos, BlockState state, Direction direction)
    {
        if (level == null || level.isClientSide() || !(level.getBlockEntity(pos) instanceof CableBE cable) || !state.hasProperty(CONNECTIONS.get(direction)))
            return;

        StringProperty connection = CONNECTIONS.get(direction);
        switch (state.getValue(connection))
        {
            case "false" -> state = state.setValue(connection, "true");
            case "true", "extracts" -> state = state.setValue(connection, "false");
        }
        cable.playerChangedConnection(level, pos, direction, state.getValue(connection).equals("false"));
        level.setBlockAndUpdate(pos, state);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity)
    {
        if (!level.isClientSide() && !state.getValue(ISOLATED) && level.getBlockEntity(pos) instanceof CableBE be && be.network != null && be.network.isWorking())
            entity.hurt(level.damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7f);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return super.getStateForPlacement(context).setValue(ISOLATED, isIsolated);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(ISOLATED);
    }

    @Override
    public int getColor()
    {
        return color.getColor();
    }
}
