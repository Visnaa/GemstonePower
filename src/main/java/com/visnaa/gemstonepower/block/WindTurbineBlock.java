package com.visnaa.gemstonepower.block;

import com.visnaa.gemstonepower.block.entity.WindTurbineBE;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import com.visnaa.gemstonepower.registry.ModItems;
import com.visnaa.gemstonepower.util.Tier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nullable;

public class WindTurbineBlock extends BaseEntityBlock implements TieredBlock<WindTurbineBlock>
{
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<Tier> TIER = Tier.TIER;

    public WindTurbineBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TIER, Tier.STANDARD));
        this.registerColors(this);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new WindTurbineBE(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity)
    {
        return createTicker(level, blockEntity, ModBlockEntities.WIND_TURBINE.get());
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid)
    {
        if (state.is(state.getBlock()))
        {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof WindTurbineBE)
            {
                if (level instanceof ServerLevel && !player.isCreative() && willHarvest)
                {
                    Containers.dropContents(level, pos, NonNullList.withSize(1, new ItemStack(ModItems.WIND_TURBINE.get())));
                    ItemStack upgrade = Tier.getTierUpgrade(state.getValue(TIER));
                    if (!upgrade.isEmpty()) Containers.dropContents(level, pos, NonNullList.withSize(1, upgrade));
                }
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> blockEntity, BlockEntityType<? extends WindTurbineBE> generator)
    {
        return level.isClientSide ? null : createTickerHelper(blockEntity, generator, WindTurbineBE::serverTick);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(TIER, Tier.STANDARD);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING).add(TIER);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation)
    {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror)
    {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}