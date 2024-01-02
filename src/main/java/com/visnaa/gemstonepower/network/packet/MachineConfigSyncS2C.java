package com.visnaa.gemstonepower.network.packet;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import com.visnaa.gemstonepower.util.MachineUtil.MachineConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record MachineConfigSyncS2C(MachineConfigs left, MachineConfigs right, MachineConfigs back, MachineConfigs up, MachineConfigs down, BlockPos pos) implements CustomPacketPayload
{
    public static final ResourceLocation ID = GemstonePower.getId("packet_machine_config_sync_s2c");

    public MachineConfigSyncS2C(FriendlyByteBuf buffer)
    {
        this(buffer.readEnum(MachineConfigs.class), buffer.readEnum(MachineConfigs.class), buffer.readEnum(MachineConfigs.class), buffer.readEnum(MachineConfigs.class), buffer.readEnum(MachineConfigs.class), buffer.readBlockPos());
    }

    @Override
    public void write(FriendlyByteBuf buffer)
    {
        buffer.writeEnum(left);
        buffer.writeEnum(right);
        buffer.writeEnum(back);
        buffer.writeEnum(up);
        buffer.writeEnum(down);
        buffer.writeBlockPos(pos);
    }

    @Override
    public ResourceLocation id()
    {
        return ID;
    }

    public static class Handler
    {
        private static final Handler INSTANCE = new Handler();

        public static Handler getInstance()
        {
            return INSTANCE;
        }

        public void handle(MachineConfigSyncS2C packet, PlayPayloadContext context)
        {
            if (!context.flow().isClientbound())
                return;

            context.workHandler().submitAsync(() -> {
                if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.getBlockEntity(packet.pos()) instanceof MachineBE<?> blockEntity)
                {
                    blockEntity.setConfig(packet.left(), packet.right(), packet.back(), packet.up(), packet.down());
                }
            }).exceptionally(e -> {
                context.packetHandler().disconnect(Component.translatable(GemstonePower.MOD_ID + ".networking.failed", e.getMessage()));
                return null;
            });
        }
    }
}
