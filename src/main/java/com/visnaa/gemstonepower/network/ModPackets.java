package com.visnaa.gemstonepower.network;

import com.visnaa.gemstonepower.GemstonePower;
import com.visnaa.gemstonepower.network.packet.*;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

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
        SimpleChannel channel = ChannelBuilder
                .named(GemstonePower.getId("packets"))
                .networkProtocolVersion(1)
                .clientAcceptedVersions((status, version) -> true)
                .serverAcceptedVersions((status, version) -> true)
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

        channel.messageBuilder(FluidSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FluidSyncS2C::new)
                .encoder(FluidSyncS2C::toBytes)
                .consumerMainThread(FluidSyncS2C::handle)
                .add();

        channel.messageBuilder(FissionReactorSyncS2C.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FissionReactorSyncS2C::new)
                .encoder(FissionReactorSyncS2C::toBytes)
                .consumerMainThread(FissionReactorSyncS2C::handle)
                .add();

        channel.messageBuilder(MachineModeSyncC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(MachineModeSyncC2S::new)
                .encoder(MachineModeSyncC2S::toBytes)
                .consumerMainThread(MachineModeSyncC2S::handle)
                .add();

        channel.messageBuilder(FissionReactorActivationC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(FissionReactorActivationC2S::new)
                .encoder(FissionReactorActivationC2S::toBytes)
                .consumerMainThread(FissionReactorActivationC2S::handle)
                .add();
    }

    public static <P> void sendToClient(P packet)
    {
        CHANNEL.send(packet, PacketDistributor.ALL.noArg());
    }

    public static <P> void sendToServer(P packet)
    {
        CHANNEL.send(packet, PacketDistributor.SERVER.noArg());
    }
}
