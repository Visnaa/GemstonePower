package com.visnaa.gemstonepower.network.packet;

import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class MachineModeSyncC2S
{
    private int mode;
    private BlockPos pos;

    public MachineModeSyncC2S(int mode, BlockPos pos)
    {
        this.mode = mode;
        this.pos = pos;
    }

    public MachineModeSyncC2S(FriendlyByteBuf buffer)
    {
        this.mode = buffer.readInt();
        this.pos = buffer.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buffer)
    {
        buffer.writeInt(mode);
        buffer.writeBlockPos(pos);
    }

    public void handle(CustomPayloadEvent.Context context)
    {
        context.enqueueWork(() -> {
            if (context.getSender() != null && context.getSender().level().getBlockEntity(pos) instanceof MachineBE<?> blockEntity)
            {
                blockEntity.setMode(mode);
            }
        });
        context.setPacketHandled(true);
    }
}
