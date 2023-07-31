package com.wollit.jellymod.event;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.capability.ClassAbilityTimerCapabilityProvider;
import com.wollit.jellymod.capability.classes.AbstractClassCapabilityProvider;
import com.wollit.jellymod.capability.classes.warrior.paladin.PaladinCapabilityProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JellyMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            if (!player.getCapability(AbstractClassCapabilityProvider.CLASS).isPresent()) {
                event.addCapability(new ResourceLocation(JellyMod.MOD_ID, "class"), new PaladinCapabilityProvider());
                event.addCapability(new ResourceLocation(JellyMod.MOD_ID, "ability_timer"), new ClassAbilityTimerCapabilityProvider());

            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(AbstractClassCapabilityProvider.CLASS).ifPresent(oldPlayer -> {
                event.getOriginal().getCapability(AbstractClassCapabilityProvider.CLASS).ifPresent(newPlayer -> {
                    newPlayer.copyFrom(oldPlayer);
                });
            });

            event.getOriginal().getCapability(ClassAbilityTimerCapabilityProvider.ABILITY_TIMER).ifPresent(oldPlayer -> {
                event.getOriginal().getCapability(ClassAbilityTimerCapabilityProvider.ABILITY_TIMER).ifPresent(newPlayer -> {
                    newPlayer.copyFrom(oldPlayer);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            event.player.getCapability(ClassAbilityTimerCapabilityProvider.ABILITY_TIMER).ifPresent(abilityTimer -> {
                if (abilityTimer.getAbilityTimer() != 0) {
                    abilityTimer.setAbilityTimer(abilityTimer.getAbilityTimer() - 1);
                    if (abilityTimer.getAbilityTimer() % 20 == 0) {
                        event.player.sendSystemMessage(Component.literal(String.valueOf(abilityTimer.getAbilityTimer() / 20)));
                    }
                }
            });
        }
    }
}
