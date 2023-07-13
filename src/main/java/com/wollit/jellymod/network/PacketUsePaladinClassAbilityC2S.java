package com.wollit.jellymod.network;

import com.wollit.jellymod.capability.ClassAbilityTimerCapabilityProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUsePaladinClassAbilityC2S {

    public PacketUsePaladinClassAbilityC2S() {}

    public PacketUsePaladinClassAbilityC2S(FriendlyByteBuf buf) {}

    public void toBytes(FriendlyByteBuf buf) {}

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                player.getCapability(ClassAbilityTimerCapabilityProvider.ABILITY_TIMER).ifPresent(abilityTimer -> {
                    if (abilityTimer.getAbilityTimer() == 0) {
                        player.sendSystemMessage(Component.literal("Negative effects were cured"));
                        abilityTimer.setAbilityTimer(400);
                        player.getActiveEffects().forEach(mobEffectInstance -> {
                            if (mobEffectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL) {
                                player.removeEffect(mobEffectInstance.getEffect());
                            }
                        });
                    } else {
                        player.sendSystemMessage(Component.literal(String.format("You need to wait %d before using this ability", abilityTimer.getAbilityTimer()/20)));
                    }
                });
                ctx.get().setPacketHandled(true);
            } else {
                ctx.get().setPacketHandled(false);
            }
        });
    }
}
