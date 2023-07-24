package com.wollit.jellymod.network;

import com.wollit.jellymod.JellyMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetwork {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(JellyMod.MOD_ID, "network"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(PacketUsePaladinClassAbilityC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketUsePaladinClassAbilityC2S::new)
                .encoder(PacketUsePaladinClassAbilityC2S::toBytes)
                .consumerMainThread(PacketUsePaladinClassAbilityC2S::handle)
                .add();

        net.messageBuilder(PacketIdentifyItemC2S.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketIdentifyItemC2S::new)
                .encoder(PacketIdentifyItemC2S::toBytes)
                .consumerMainThread(PacketIdentifyItemC2S::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
