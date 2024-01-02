package com.visnaa.gemstonepower.network.packet;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.FissionReactorBE;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record FissionReactorSyncS2C(boolean activated, int progress, int totalTime, int energyGeneration, int heatGeneration, int heat, int maxHeat, BlockPos pos) implements CustomPacketPayload
{
    public static final ResourceLocation ID = GemstonePower.getId("packet_fission_reactor_sync_s2c");

    public FissionReactorSyncS2C(FriendlyByteBuf buffer)
    {
        this(buffer.readBoolean(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readBlockPos());
    }

    @Override
    public void write(FriendlyByteBuf buffer)
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

    @Override
    public ResourceLocation id()
    {
        return ID;
    }

    public static class Handler
    {
        private static Handler INSTANCE = new Handler();

        public static FissionReactorSyncS2C.Handler getInstance()
        {
            return INSTANCE;
        }

        public void handle(FissionReactorSyncS2C packet, PlayPayloadContext context)
        {
            if (!context.flow().isClientbound())
                return;

            context.workHandler().submitAsync(() -> {
                if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.getBlockEntity(packet.pos()) instanceof FissionReactorBE reactor)
                {
                    reactor.setFromServerPacket(packet.activated(), packet.energyGeneration(), packet.heatGeneration(), packet.heat(), packet.maxHeat());
                }
            }).exceptionally(e -> {
                context.packetHandler().disconnect(Component.translatable(GemstonePower.MOD_ID + ".networking.failed", e.getMessage()));
                return null;
            });
        }
    }
}
