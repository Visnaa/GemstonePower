package com.visnaa.gemstonepower.networking.packet;

import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

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

    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof MachineBE<?> blockEntity)
            {
                blockEntity.setProcessingProgress(progress);
                blockEntity.setProcessingTotalTime(totalTime);
            }
        });
        return true;
    }
}
