package com.visnaa.gemstonepower.network.packet;

import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import com.visnaa.gemstonepower.util.MachineUtil.MachineConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class MachineConfigSyncS2C
{
    private final MachineConfigs left, right, back, up, down;
    private final BlockPos pos;

    public MachineConfigSyncS2C(MachineConfigs left, MachineConfigs right, MachineConfigs back, MachineConfigs up, MachineConfigs down, BlockPos pos)
    {
        this.left = left;
        this.right = right;
        this.back = back;
        this.up = up;
        this.down = down;
        this.pos = pos;
    }

    public MachineConfigSyncS2C(FriendlyByteBuf buffer)
    {
        this.left = buffer.readEnum(MachineConfigs.class);
        this.right = buffer.readEnum(MachineConfigs.class);
        this.back = buffer.readEnum(MachineConfigs.class);
        this.up = buffer.readEnum(MachineConfigs.class);
        this.down = buffer.readEnum(MachineConfigs.class);
        this.pos = buffer.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buffer)
    {
        buffer.writeEnum(left);
        buffer.writeEnum(right);
        buffer.writeEnum(back);
        buffer.writeEnum(up);
        buffer.writeEnum(down);
        buffer.writeBlockPos(pos);
    }

    public void handle(CustomPayloadEvent.Context context)
    {
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.getBlockEntity(pos) instanceof MachineBE<?> blockEntity)
            {
                blockEntity.setConfig(left, right, back, up, down);
            }
        });
        context.setPacketHandled(true);
    }
}
