package com.visnaa.gemstonepower.network.packet;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record MachineModeSyncC2S(int mode, BlockPos pos) implements CustomPacketPayload
{
    public static final ResourceLocation ID = GemstonePower.getId("packet_machine_mode_sync_c2s");

    public MachineModeSyncC2S(FriendlyByteBuf buffer)
    {
        this(buffer.readInt(), buffer.readBlockPos());
    }

    @Override
    public void write(FriendlyByteBuf buffer)
    {
        buffer.writeInt(mode);
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

        public void handle(MachineModeSyncC2S packet, PlayPayloadContext context)
        {
            if (!context.flow().isServerbound())
                return;

            context.workHandler().submitAsync(() -> {
                if (context.player().isPresent() && context.player().get().level().getBlockEntity(packet.pos()) instanceof MachineBE<?> machine)
                {
                    machine.setMode(packet.mode());
                }
            }).exceptionally(e -> {
                context.packetHandler().disconnect(Component.translatable(GemstonePower.MOD_ID + ".networking.failed", e.getMessage()));
                return null;
            });
        }
    }
}
