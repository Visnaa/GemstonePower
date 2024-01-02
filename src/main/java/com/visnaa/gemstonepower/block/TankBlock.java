package com.visnaa.gemstonepower.block;

import com.mojang.serialization.MapCodec;
import com.visnaa.gemstonepower.block.entity.TankBE;
import com.visnaa.gemstonepower.block.machine.MachineBlock;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
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
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
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
                    IFluidHandler handlerTank = level.getCapability(Capabilities.FluidHandler.BLOCK, tank.getBlockPos(), tank.getBlockState(), tank, null);
                    IFluidHandlerItem handlerItem = stack.getCapability(Capabilities.FluidHandler.ITEM);
                    if (handlerTank == null || handlerItem == null)
                        return super.onDestroyedByPlayer(state, level, pos, player, true, fluidState);
                    handlerItem.fill(handlerTank.getFluidInTank(0), IFluidHandler.FluidAction.EXECUTE);
                    stack.getOrCreateTag().put("FluidTank", tank.getTank(0).writeToNBT(new CompoundTag()));
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
                    if (stack.getItem().getDamage(stack) >= 0 && stack.getItem().getDamage(stack) <= 20000 && stack.getCapability(Capabilities.FluidHandler.ITEM) != null)
                    {
                        tank.setFluid(0, stack.getCapability(Capabilities.FluidHandler.ITEM).getFluidInTank(0));
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
        return MachineBlock.createTicker(level, blockEntity, ModBlockEntities.TANK.get());
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec()
    {
        return simpleCodec(TankBlock::new);
    }
}
