package com.visnaa.gemstonepower.networking.packet;

import com.visnaa.gemstonepower.block.entity.EnergyStorageBE;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

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

    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof EnergyStorageBE blockEntity)
            {
                blockEntity.setEnergy(energy);
                blockEntity.setCapacity(capacity);
            }
        });
        return true;
    }
}
