package com.visnaa.gemstonepower.network.packet;

import com.visnaa.gemstonepower.block.entity.EnergyStorageBE;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.neoforged.neoforge.network.NetworkEvent;

public class EnergySyncS2C
{
    private final int energy;
    private final int capacity;
    private final BlockPos pos;

    public EnergySyncS2C(int energy, int capacity, BlockPos pos)
    {
        this.energy = energy;
        this.capacity = capacity;
        this.pos = pos;
    }

    public EnergySyncS2C(FriendlyByteBuf buffer)
    {
        this.energy = buffer.readInt();
        this.capacity = buffer.readInt();
        this.pos = buffer.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buffer)
    {
        buffer.writeInt(energy);
        buffer.writeInt(capacity);
        buffer.writeBlockPos(pos);
    }

    public void handle(NetworkEvent.Context context)
    {
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.getBlockEntity(pos) instanceof EnergyStorageBE blockEntity)
            {
                blockEntity.setEnergy(energy);
                blockEntity.setCapacity(capacity);
            }
        });
        context.setPacketHandled(true);
    }
}
