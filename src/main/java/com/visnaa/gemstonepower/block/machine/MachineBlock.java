package com.visnaa.gemstonepower.block.machine;

import com.visnaa.gemstonepower.block.TieredBlock;
import com.visnaa.gemstonepower.block.entity.TickingBlockEntity;
import com.visnaa.gemstonepower.block.entity.machine.FluidMachineBE;
import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import com.visnaa.gemstonepower.util.Tier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nullable;

public abstract class MachineBlock<T extends MachineBlock<T>> extends BaseEntityBlock implements TieredBlock<T>
{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<Tier> TIER = Tier.TIER;

    public MachineBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TIER, Tier.STANDARD));

        this.registerColors((T) this);
    }

    public MachineBlock(Properties properties, boolean tierable)
    {
        super(properties);
        if (tierable)
        {
            this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TIER, Tier.STANDARD));
            this.registerColors((T) this);
        }
        else
            this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    protected void openContainer(Level level, BlockPos pos, Player player)
    {
        if (level.getBlockEntity(pos) instanceof MachineBE<?> machine && !level.isClientSide() && player instanceof ServerPlayer serverPlayer)
        {
            serverPlayer.openMenu(machine, pos);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if (level.getBlockEntity(pos) instanceof FluidMachineBE<?> machine && FluidUtil.getFluidHandler(player.getItemInHand(hand)).isPresent())
            return machine.fillFromItem(level, player, hand);
        else if (!level.isClientSide())
        {
            this.openContainer(level, pos, player);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(TIER, Tier.STANDARD);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack itemStack)
    {
        if (itemStack.hasCustomHoverName())
        {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof MachineBE<?> machine)
            {
                machine.setCustomName(itemStack.getHoverName());
            }
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid)
    {
        if (state.is(state.getBlock()))
        {
            if (level.getBlockEntity(pos) instanceof MachineBE<?> machine)
            {
                if (level instanceof ServerLevel && !player.isCreative() && willHarvest)
                {
                    Containers.dropContents(level, pos, machine);
                    Containers.dropContents(level, pos, NonNullList.withSize(1, new ItemStack(this.asItem())));
                    ItemStack upgrade = Tier.getTierUpgrade(state.getValue(TIER));
                    if (!upgrade.isEmpty())
                        Containers.dropContents(level, pos, NonNullList.withSize(1, upgrade));
                }
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state)
    {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos)
    {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
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

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING).add(TIER);
    }

    @Nullable
    @Override
    abstract public <B extends BlockEntity> BlockEntityTicker<B> getTicker(Level level, BlockState state, BlockEntityType<B> blockEntity);

    @Nullable
    public static <B extends BlockEntity> BlockEntityTicker<B> createTicker(Level level, BlockEntityType<B> blockEntity, BlockEntityType<? extends BlockEntity> machine)
    {
        return createTickerHelper(blockEntity, machine, (lev, pos, state, be) -> TickingBlockEntity.serverTick(level, pos, state, (TickingBlockEntity) be));
    }
}
