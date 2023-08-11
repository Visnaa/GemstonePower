package com.visnaa.gemstonepower.block.entity;

import com.google.common.collect.Maps;
import com.visnaa.gemstonepower.network.ModPackets;
import com.visnaa.gemstonepower.network.packet.EnergySyncS2C;
import com.visnaa.gemstonepower.network.packet.FluidSyncS2C;
import com.visnaa.gemstonepower.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;

public class TankBE extends FluidEnergyStorageBE
{
    private static final HashMap<Fluid, Integer> TANK;

    static
    {
        TANK = new HashMap<>();
        TANK.put(Fluids.EMPTY, 20000);
    }

    public TankBE(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.TANK.get(), pos, state, TANK);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state)
    {
        transferDown(level, pos, state);
    }

    public void transferDown(Level level, BlockPos pos, BlockState state)
    {
        if (level.isClientSide() || !state.is(state.getBlock()))
            return;

        if (level.getBlockEntity(pos.below()) instanceof TankBE other)
        {
            if (!other.getCapability(ForgeCapabilities.FLUID_HANDLER).isPresent() || !getCapability(ForgeCapabilities.FLUID_HANDLER).isPresent())
                return;

            if (tanks.getFluidInTank(0).isEmpty())
                return;

            int maxTransfer = Math.min(other.getTank(0).getSpace(), tanks.getFluidInTank(0).getAmount());
            if (maxTransfer <= 0)
                return;

            FluidUtil.tryFluidTransfer(other.getTank(0), getTank(0), maxTransfer, true);
        }
    }

    @NotNull
    @Override
    public<T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction)
    {
        if (capability == ForgeCapabilities.FLUID_HANDLER)
            return LazyOptional.of(() -> tanks).cast();
        return LazyOptional.empty();
    }
}
