package com.wollit.jellymod.event;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.capability.ClassAbilityTimerCapabilityProvider;
import com.wollit.jellymod.capability.classes.AbstractClassCapabilityProvider;
import com.wollit.jellymod.capability.classes.archer.ArcherCapabilityProvider;
import com.wollit.jellymod.capability.classes.archer.ArcherClassCapability;
import com.wollit.jellymod.capability.classes.warrior.WarriorCapabilityProvider;
import com.wollit.jellymod.capability.classes.warrior.WarriorClassCapability;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
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
                event.addCapability(new ResourceLocation(JellyMod.MOD_ID, "class"), new WarriorCapabilityProvider());
                event.addCapability(new ResourceLocation(JellyMod.MOD_ID, "ability_timer"), new ClassAbilityTimerCapabilityProvider());

            }
        }
    }

    @SubscribeEvent
    public static void onPlayerAttack(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            player.getCapability(AbstractClassCapabilityProvider.CLASS).ifPresent(playerClass -> {
                if (event.getSource().getMsgId().equals("arrow")) {
                    if (playerClass instanceof ArcherClassCapability archer) {
                        player.sendSystemMessage(Component.literal("Arrow dmg before multiplayer: " + event.getAmount()));
                        event.setAmount(event.getAmount() * archer.getRangeDmg());
                        player.sendSystemMessage(Component.literal("Arrow dmg after multiplayer: " + event.getAmount()));
                    }
                } else if (event.getSource().getMsgId().equals("player")) {
                    if (playerClass instanceof WarriorClassCapability warrior) {
                        player.sendSystemMessage(Component.literal("Melee dmg before multiplayer: " + event.getAmount()));
                        event.setAmount(event.getAmount() * warrior.getMelleDmg());
                        player.sendSystemMessage(Component.literal("Melee dmg after multiplayer: " + event.getAmount()));
                    }
                }
            });
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
