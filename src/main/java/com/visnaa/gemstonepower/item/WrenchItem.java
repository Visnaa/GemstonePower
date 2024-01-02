package com.visnaa.gemstonepower.item;

import com.visnaa.gemstonepower.block.pipe.PipeBlock;
import com.visnaa.gemstonepower.block.pipe.cable.CableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WrenchItem extends Item
{
    public WrenchItem(Properties properties, int durability)
    {
        super(properties.stacksTo(1).durability(durability));
    }

    @Override
    @NotNull
    public InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        BlockState state = level.getBlockState(context.getClickedPos());
        BlockEntity be = level.getBlockEntity(context.getClickedPos());

        if (context.getPlayer() == null || be == null)
            return super.useOn(context);

        if (state.getBlock() instanceof PipeBlock pipe && !context.getPlayer().isShiftKeyDown())
        {
            Direction direction = getArm(context, state.getBlock() instanceof CableBlock);
            if (state.hasProperty(PipeBlock.CONNECTIONS.get(direction)))
                pipe.cycleConnection(level, context.getClickedPos(), state, direction);
            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), player -> player.broadcastBreakEvent(context.getHand()));
            return InteractionResult.CONSUME;
        }
        else if (state.hasProperty(HorizontalDirectionalBlock.FACING) && context.getPlayer().isShiftKeyDown())
        {
            level.setBlockAndUpdate(context.getClickedPos(), state.setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING).getClockWise()));
            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), player -> player.broadcastBreakEvent(context.getHand()));
            return InteractionResult.CONSUME;
        }
        return super.useOn(context);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity)
    {
        stack.hurtAndBreak(state.is(state.getBlock()) && state.getBlock() instanceof EntityBlock ? 1 : 5, entity, lEntity -> lEntity.broadcastBreakEvent(lEntity.getUsedItemHand()));
        return super.mineBlock(stack, level, state, pos, entity);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state)
    {
        return state.is(state.getBlock()) && state.getBlock() instanceof EntityBlock ? 10.0F : super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState state)
    {
        return state.is(state.getBlock()) && state.getBlock() instanceof EntityBlock || super.isCorrectToolForDrops(state);
    }

    private Direction getArm(UseOnContext context, boolean isThin)
    {
        double x = context.getClickedPos().getCenter().x() - context.getClickLocation().x();
        double y = context.getClickedPos().getCenter().y() - context.getClickLocation().y();
        double z = context.getClickedPos().getCenter().z() - context.getClickLocation().z();
        Direction direction = context.getClickedFace();
        if (isThin)
        {
            if (z >= -0.125 && z <= 0.125 && y >= -0.125 && y <= 0.125 && x <= -0.125)
                direction = Direction.EAST;
            else if (z >= -0.125 && z <= 0.125 && y >= -0.125 && y <= 0.125 && x >= 0.125)
                direction = Direction.WEST;
            else if (x >= -0.125 && x <= 0.125 && y >= -0.125 && y <= 0.125 && z <= -0.125)
                direction = Direction.SOUTH;
            else if (x >= -0.125 && x <= 0.125 && y >= -0.125 && y <= 0.125 && z >= 0.125)
                direction = Direction.NORTH;
            else if (y >= 0.125 && x >= -0.125 && x <= 0.125 && z >= -0.125 && z <= 0.125)
                direction = Direction.DOWN;
            else if (y <= -0.125 && x >= -0.125 && x <= 0.125 && z >= -0.125 && z <= 0.125)
                direction = Direction.UP;
            return direction;
        }
        if (z >= -0.1875 && z <= 0.1875 && y >= -0.1875 && y <= 0.1875 && x <= -0.1875)
            direction = Direction.EAST;
        else if (z >= -0.1875 && z <= 0.1875 && y >= -0.1875 && y <= 0.1875 && x >= 0.1875)
            direction = Direction.WEST;
        else if (x >= -0.1875 && x <= 0.1875 && y >= -0.1875 && y <= 0.1875 && z <= -0.1875)
            direction = Direction.SOUTH;
        else if (x >= -0.1875 && x <= 0.1875 && y >= -0.1875 && y <= 0.1875 && z >= 0.1875)
            direction = Direction.NORTH;
        else if (y >= 0.1875 && x >= -0.1875 && x <= 0.1875 && z >= -0.1875 && z <= 0.1875)
            direction = Direction.DOWN;
        else if (y <= -0.1875 && x >= -0.1875 && x <= 0.1875 && z >= -0.1875 && z <= 0.1875)
            direction = Direction.UP;
        return direction;
    }
}
