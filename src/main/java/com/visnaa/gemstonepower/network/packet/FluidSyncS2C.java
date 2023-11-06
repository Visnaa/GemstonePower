package com.visnaa.gemstonepower.network.packet;

import com.visnaa.gemstonepower.block.entity.FluidStorageBE;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.fluids.FluidStack;

public class FluidSyncS2C
{
    private final FluidStack stack;
    private final int capacity;
    private final int tankId;
    private final BlockPos pos;

    public FluidSyncS2C(FluidStack stack, int capacity, int tankId, BlockPos pos)
    {
        this.stack = stack;
        this.capacity = capacity;
        this.tankId = tankId;
        this.pos = pos;
    }

    public FluidSyncS2C(FriendlyByteBuf buffer)
    {
        this.stack = buffer.readFluidStack();
        this.capacity = buffer.readInt();
        this.tankId = buffer.readInt();
        this.pos = buffer.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buffer)
    {
        buffer.writeFluidStack(stack);
        buffer.writeInt(capacity);
        buffer.writeInt(tankId);
        buffer.writeBlockPos(pos);
    }

    public void handle(CustomPayloadEvent.Context context)
    {
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.getBlockEntity(pos) instanceof FluidStorageBE blockEntity)
            {
                blockEntity.setFluid(tankId, stack);
                blockEntity.setCapacity(tankId, capacity);
            }
        });
        context.setPacketHandled(true);
    }
}
