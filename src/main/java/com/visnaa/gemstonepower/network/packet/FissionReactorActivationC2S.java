package com.visnaa.gemstonepower.network.packet;

import com.visnaa.gemstonepower.block.entity.FissionReactorBE;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FissionReactorActivationC2S
{
    private final boolean activated;
    private final BlockPos pos;

    public FissionReactorActivationC2S(boolean activated, BlockPos pos)
    {
        this.activated = activated;
        this.pos = pos;
    }

    public FissionReactorActivationC2S(FriendlyByteBuf buffer)
    {
        this.activated = buffer.readBoolean();
        this.pos = buffer.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buffer)
    {
        buffer.writeBoolean(activated);
        buffer.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (context.getSender() != null && context.getSender().level().getBlockEntity(pos) instanceof FissionReactorBE reactor)
            {
                reactor.setFromClientPacket(activated);
            }
        });
        context.setPacketHandled(true);
    }
}
