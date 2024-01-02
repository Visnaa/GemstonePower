package com.visnaa.gemstonepower.network.packet;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.block.entity.machine.MachineBE;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record RecipeProgressSyncS2C(int progress, int totalTime, BlockPos pos) implements CustomPacketPayload
{
    public static final ResourceLocation ID = GemstonePower.getId("packet_recipe_sync_s2c");

    public RecipeProgressSyncS2C(FriendlyByteBuf buffer)
    {
        this(buffer.readInt(), buffer.readInt(), buffer.readBlockPos());
    }

    @Override
    public void write(FriendlyByteBuf buffer)
    {
        buffer.writeInt(progress);
        buffer.writeInt(totalTime);
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

        public void handle(RecipeProgressSyncS2C packet, PlayPayloadContext context)
        {
            if (!context.flow().isClientbound())
                return;

            context.workHandler().submitAsync(() -> {
                if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.getBlockEntity(packet.pos()) instanceof MachineBE<?> blockEntity)
                {
                    blockEntity.setProcessingProgress(packet.progress());
                    blockEntity.setProcessingTotalTime(packet.totalTime());
                }
            }).exceptionally(e -> {
                context.packetHandler().disconnect(Component.translatable(GemstonePower.MOD_ID + ".networking.failed", e.getMessage()));
                return null;
            });
        }
    }
}
