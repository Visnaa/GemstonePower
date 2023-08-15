package com.visnaa.gemstonepower.block;

import com.visnaa.gemstonepower.block.entity.TankBE;
import com.visnaa.gemstonepower.block.entity.TickingBlockEntity;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import com.visnaa.gemstonepower.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

public class TankBlock extends BaseEntityBlock
{
    public TankBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        if (player.isShiftKeyDown())
            return InteractionResult.SUCCESS;

        if (level.getBlockEntity(pos) instanceof TankBE tank)
            return tank.fillFromItem(level, player, hand);

        return InteractionResult.FAIL;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluidState)
    {
        if (state.is(state.getBlock()))
        {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof TankBE tank)
            {
                if (level instanceof ServerLevel && !player.isCreative() && willHarvest)
                {
                    ItemStack stack = new ItemStack(ModItems.TANK.get(), 1);
                    tank.getCapability(ForgeCapabilities.FLUID_HANDLER).map(h -> h.getFluidInTank(0)).orElse(FluidStack.EMPTY).writeToNBT(stack.getTag());
                    stack.getItem().setDamage(stack, 20001 - stack.getTag().getInt("Amount"));
                    Containers.dropContents(level, pos, NonNullList.withSize(1, stack));
                }
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluidState);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack)
    {
        if (state.is(state.getBlock()))
        {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof TankBE tank)
            {
                if (level instanceof ServerLevel && entity instanceof ServerPlayer player && !player.isCreative())
                {
                    if (stack.getItem().getDamage(stack) >= 0 && stack.getItem().getDamage(stack) <= 20000)
                    {
                        tank.setFluid(0, FluidStack.loadFluidStackFromNBT(stack.getTag()));
                    }
                }
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.setPlacedBy(level, pos, state, entity, stack);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_)
    {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new TankBE(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity)
    {
        return createTicker(level, blockEntity, ModBlockEntities.TANK.get());
    }

    @javax.annotation.Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level, BlockEntityType<T> blockEntity, BlockEntityType<? extends TankBE> generator)
    {
        return level.isClientSide ? null : createTickerHelper(blockEntity, generator, TickingBlockEntity::serverTick);
    }
}
