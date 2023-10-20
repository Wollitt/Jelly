package com.wollit.jellymod.network;

import com.wollit.jellymod.capability.PlayerClassCapabilityProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketClassSelectionC2S {

    private int selectedClass;

    public PacketClassSelectionC2S(int selectedClass) {
        this.selectedClass = selectedClass;
    }

    public PacketClassSelectionC2S(FriendlyByteBuf buf) {
        this.selectedClass = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(selectedClass);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            player.getCapability(PlayerClassCapabilityProvider.CLASS).ifPresent(playerClass -> {
                switch (selectedClass) {
                    case 0:
                        playerClass.setClassName("warrior");
                        playerClass.setDamageMultiplayer(1.1F);
                        break;
                    case 1:
                        playerClass.setClassName("archer");
                        playerClass.setDamageMultiplayer(1.1F);
                        break;
                }
                ModNetwork.sendToPlayer(new SyncPlayerClassDataPacketS2C(playerClass.getClassName(), playerClass.damageMultiplayer), player);
            });
        });
    }
}
