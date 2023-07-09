package com.visnaa.gemstonepower.networking;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.networking.packet.EnergySyncS2C;
import com.visnaa.gemstonepower.networking.packet.RecipeProgressSyncS2C;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPackets
{
    private static SimpleChannel CHANNEL;

    private static int packetId = 0;
    private static int id()
    {
        return packetId++;
    }

    public static void register()
    {
        SimpleChannel channel = NetworkRegistry.ChannelBuilder
                .named(GemstonePower.getId("packets"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        CHANNEL = channel;

        channel.messageBuilder(EnergySyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnergySyncS2C::new)
                .encoder(EnergySyncS2C::toBytes)
                .consumerMainThread(EnergySyncS2C::handle)
                .add();

        channel.messageBuilder(RecipeProgressSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(RecipeProgressSyncS2C::new)
                .encoder(RecipeProgressSyncS2C::toBytes)
                .consumerMainThread(RecipeProgressSyncS2C::handle)
                .add();
    }

    public static <P> void sendToClient(P packet)
    {
        CHANNEL.send(PacketDistributor.ALL.noArg(), packet);
    }
}
