package com.visnaa.gemstonepower.network.packet;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import com.visnaa.gemstonepower.util.MachineUtil.MachineConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record MachineConfigSyncC2S(MachineConfigs left, MachineConfigs right, MachineConfigs back, MachineConfigs up, MachineConfigs down, BlockPos pos) implements CustomPacketPayload
{
    public static final ResourceLocation ID = GemstonePower.getId("packet_machine_config_sync_c2s");

    public MachineConfigSyncC2S(FriendlyByteBuf buffer)
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

        public void handle(MachineConfigSyncC2S packet, PlayPayloadContext context)
        {
            if (!context.flow().isServerbound())
                return;

            context.workHandler().submitAsync(() -> {
                if (context.player().isPresent() && context.player().get().level().getBlockEntity(packet.pos()) instanceof MachineBE<?> machine)
                {
                    machine.sendConfigToClients(packet.left(), packet.right(), packet.back(), packet.up(), packet.down());
                }
            }).exceptionally(e -> {
                context.packetHandler().disconnect(Component.translatable(GemstonePower.MOD_ID + ".networking.failed", e.getMessage()));
                return null;
            });
        }
    }
}
