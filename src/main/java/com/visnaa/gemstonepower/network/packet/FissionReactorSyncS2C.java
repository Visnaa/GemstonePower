package com.visnaa.gemstonepower.network.packet;

import com.visnaa.gemstonepower.block.entity.FissionReactorBE;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FissionReactorSyncS2C
{
    private final boolean activated;
    private final int progress;
    private final int totalTime;
    private final int energyGeneration;
    private final int heatGeneration;
    private final int heat;
    private final int maxHeat;
    private final BlockPos pos;

    public FissionReactorSyncS2C(boolean activated, int progress, int totalTime, int energyGeneration, int heatGeneration, int heat, int maxHeat, BlockPos pos)
    {
        this.activated = activated;
        this.progress = progress;
        this.totalTime = totalTime;
        this.energyGeneration = energyGeneration;
        this.heatGeneration = heatGeneration;
        this.heat = heat;
        this.maxHeat = maxHeat;
        this.pos = pos;
    }

    public FissionReactorSyncS2C(FriendlyByteBuf buffer)
    {
        this.activated = buffer.readBoolean();
        this.progress = buffer.readInt();
        this.totalTime = buffer.readInt();
        this.energyGeneration = buffer.readInt();
        this.heatGeneration = buffer.readInt();
        this.heat = buffer.readInt();
        this.maxHeat = buffer.readInt();
        this.pos = buffer.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buffer)
    {
        buffer.writeBoolean(activated);
        buffer.writeInt(progress);
        buffer.writeInt(totalTime);
        buffer.writeInt(energyGeneration);
        buffer.writeInt(heatGeneration);
        buffer.writeInt(heat);
        buffer.writeInt(maxHeat);
        buffer.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.getBlockEntity(pos) instanceof FissionReactorBE reactor)
            {
                reactor.setProcessingProgress(progress);
                reactor.setProcessingTotalTime(totalTime);
                reactor.setFromServerPacket(activated, energyGeneration, heatGeneration, heat, maxHeat);
            }
        });
        context.setPacketHandled(true);
    }
}
