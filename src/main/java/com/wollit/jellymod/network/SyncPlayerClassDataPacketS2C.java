package com.wollit.jellymod.network;

import com.wollit.jellymod.client.ClientPlayerClassData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncPlayerClassDataPacketS2C {

    private final String className;
    private final Float damageMultiplayer;

    public SyncPlayerClassDataPacketS2C(String className, Float damageMultiplayer) {
        this.className = className;
        this.damageMultiplayer = damageMultiplayer;
    }

    public SyncPlayerClassDataPacketS2C(FriendlyByteBuf buf) {
        this.className = buf.readUtf();
        this.damageMultiplayer = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(className);
        buf.writeFloat(damageMultiplayer);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();

        ctx.enqueueWork(() -> {
            ClientPlayerClassData.setClassName(className);
            ClientPlayerClassData.setDamageMultiplayer(damageMultiplayer);
        });
    }
}
