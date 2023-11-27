package com.visnaa.gemstonepower.network.packet;

import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.neoforged.neoforge.network.NetworkEvent;

public class RecipeProgressSyncS2C
{
    private final int progress;
    private final int totalTime;
    private final BlockPos pos;

    public RecipeProgressSyncS2C(int progress, int totalTime, BlockPos pos)
    {
        this.progress = progress;
        this.totalTime = totalTime;
        this.pos = pos;
    }

    public RecipeProgressSyncS2C(FriendlyByteBuf buffer)
    {
        this.progress = buffer.readInt();
        this.totalTime = buffer.readInt();
        this.pos = buffer.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buffer)
    {
        buffer.writeInt(progress);
        buffer.writeInt(totalTime);
        buffer.writeBlockPos(pos);
    }

    public void handle(NetworkEvent.Context context)
    {
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.getBlockEntity(pos) instanceof MachineBE<?> blockEntity)
            {
                blockEntity.setProcessingProgress(progress);
                blockEntity.setProcessingTotalTime(totalTime);
            }
        });
        context.setPacketHandled(true);
    }
}
