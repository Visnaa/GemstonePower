package com.visnaa.gemstonepower.block;

import com.visnaa.gemstonepower.block.entity.AlloySmelterBlockEntity;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import com.visnaa.gemstonepower.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class AlloySmelterBlock extends BaseEntityBlock
{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public AlloySmelterBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new AlloySmelterBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity)
    {
        return createTicker(level, blockEntity, ModBlockEntities.ALLOY_SMELTER.get());
    }

    protected void openContainer(Level level, BlockPos pos, Player player)
    {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof AlloySmelterBlockEntity)
        {
            player.openMenu((MenuProvider) blockEntity);
        }
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            this.openContainer(level, pos, player);
            return InteractionResult.CONSUME;
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack itemStack)
    {
        if (itemStack.hasCustomHoverName())
        {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AlloySmelterBlockEntity)
            {
                ((AlloySmelterBlockEntity) blockEntity).setCustomName(itemStack.getHoverName());
            }
        }

    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity entity, ItemStack stack)
    {
        if (!state.is(state.getBlock()))
        {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AlloySmelterBlockEntity)
            {
                if (level instanceof ServerLevel && !player.isCreative())
                {
                    Containers.dropContents(level, pos, (AlloySmelterBlockEntity) blockEntity);
                    Containers.dropContents(level, pos, NonNullList.withSize(1, new ItemStack(ModItems.ALLOY_SMELTER.get())));
                }
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.playerDestroy(level, player, pos, state, entity, stack);
        }
    }

    public boolean hasAnalogOutputSignal(BlockState state)
    {
        return true;
    }

    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos)
    {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirror)
    {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> blockEntity, BlockEntityType<? extends AlloySmelterBlockEntity> generator)
    {
        return level.isClientSide ? null : createTickerHelper(blockEntity, generator, AlloySmelterBlockEntity::serverTick);
    }
}