package com.visnaa.gemstonepower.block.pipe.cable;

import com.visnaa.gemstonepower.block.entity.pipe.cable.AluminumCableBE;
import com.visnaa.gemstonepower.registry.ModDamageTypes;
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

import java.util.Objects;

public class AluminumWireBlock extends AluminumCableBlock
{
    public static final BooleanProperty ISOLATED = BooleanProperty.create("isolated");

    public AluminumWireBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ISOLATED, false));
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity)
    {
        if (((AluminumCableBE) Objects.requireNonNull(level.getBlockEntity(pos))).network.getEnergy() > 0)
            entity.hurt(level.damageSources().source(ModDamageTypes.ELECTROCUTED, null, null), 7f);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context)
    {
        VoxelShape shape = Block.box(7, 7, 7, 9, 9, 9),
                north = Block.box(7, 7, 0, 9, 9, 7),
                south = Block.box(7, 7, 9, 9, 9, 16),
                east = Block.box(9, 7, 7, 16, 9, 9),
                west = Block.box(0, 7, 7, 7, 9, 9),
                up = Block.box(7, 9, 7, 9, 16, 9),
                down = Block.box(7, 0, 7, 9, 9, 9);
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
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return super.getStateForPlacement(context).setValue(ISOLATED, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(ISOLATED);
    }
}
