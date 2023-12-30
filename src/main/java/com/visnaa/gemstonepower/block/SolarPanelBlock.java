package com.visnaa.gemstonepower.block;

import com.mojang.serialization.MapCodec;
import com.visnaa.gemstonepower.block.entity.SolarPanelBE;
import com.visnaa.gemstonepower.block.machine.MachineBlock;
import com.visnaa.gemstonepower.init.ModBlockEntities;
import com.visnaa.gemstonepower.init.ModItems;
import com.visnaa.gemstonepower.util.Tier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nullable;

public class SolarPanelBlock extends BaseEntityBlock implements TieredBlock<SolarPanelBlock>
{
    public static final EnumProperty<Tier> TIER = Tier.TIER;

    public SolarPanelBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(TIER, Tier.STANDARD));
        this.registerColors(this);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState().setValue(TIER, Tier.STANDARD);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new SolarPanelBE(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity)
    {
        return MachineBlock.createTicker(level, blockEntity, ModBlockEntities.SOLAR_PANEL.get());
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid)
    {
        if (state.is(state.getBlock()))
        {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof SolarPanelBE)
            {
                if (level instanceof ServerLevel && !player.isCreative() && willHarvest)
                {
                    Containers.dropContents(level, pos, NonNullList.withSize(1, new ItemStack(ModItems.SOLAR_PANEL.get())));
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

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(TIER);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec()
    {
        return simpleCodec(SolarPanelBlock::new);
    }
}