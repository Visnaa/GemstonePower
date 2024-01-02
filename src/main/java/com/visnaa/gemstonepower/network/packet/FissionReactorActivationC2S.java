package com.visnaa.gemstonepower.network.packet;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.FissionReactorBE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record FissionReactorActivationC2S(boolean activated, BlockPos pos) implements CustomPacketPayload
{
    public static final ResourceLocation ID = GemstonePower.getId("packet_fission_reactor_activation_sync_c2s");

    public FissionReactorActivationC2S(FriendlyByteBuf buffer)
    {
        this(buffer.readBoolean(), buffer.readBlockPos());
    }

    @Override
    public void write(FriendlyByteBuf buffer)
    {
        buffer.writeBoolean(activated);
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

        public void handle(FissionReactorActivationC2S packet, PlayPayloadContext context)
        {
            if (!context.flow().isServerbound())
                return;

            context.workHandler().submitAsync(() -> {
                if (context.player().isPresent() && context.player().get().level().getBlockEntity(packet.pos()) instanceof FissionReactorBE reactor)
                {
                    reactor.setFromClientPacket(packet.activated());
                }
            }).exceptionally(e -> {
                context.packetHandler().disconnect(Component.translatable(GemstonePower.MOD_ID + ".networking.failed", e.getMessage()));
                return null;
            });
        }
    }
}
