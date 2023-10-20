package com.wollit.jellymod.event;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.capability.ClassAbilityTimerCapabilityProvider;
import com.wollit.jellymod.capability.PlayerClassCapabilityProvider;
import com.wollit.jellymod.client.ClientPlayerClassData;
import com.wollit.jellymod.network.ModNetwork;
import com.wollit.jellymod.network.SyncPlayerClassDataPacketS2C;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = JellyMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            if (!player.getCapability(PlayerClassCapabilityProvider.CLASS).isPresent()) {
                event.addCapability(new ResourceLocation(JellyMod.MOD_ID, "class"), new PlayerClassCapabilityProvider());
                event.addCapability(new ResourceLocation(JellyMod.MOD_ID, "ability_timer"), new ClassAbilityTimerCapabilityProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerAttack(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            player.getCapability(PlayerClassCapabilityProvider.CLASS).ifPresent(playerClass -> {
                // Think about this - if change is needed for other weapons
                List<ItemStack> handSlots = (List<ItemStack>) player.getHandSlots();
                if (handSlots.get(0).getItem() instanceof SwordItem) {
                    if (ClientPlayerClassData.getClassName().equals("warrior")) {
                        event.setAmount(event.getAmount() * ClientPlayerClassData.getDamageMultiplayer());
                        player.sendSystemMessage(Component.literal("DMG: " + event.getAmount()));
                    }
                } else if (event.getSource().getMsgId().equals("arrow")) {
                    if (ClientPlayerClassData.getClassName().equals("archer")) {
                        event.setAmount(event.getAmount() * ClientPlayerClassData.getDamageMultiplayer());
                        player.sendSystemMessage(Component.literal("DMG: " + event.getAmount()));
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
            event.getOriginal().reviveCaps();
            event.getOriginal().getCapability(PlayerClassCapabilityProvider.CLASS).ifPresent(oldPlayer -> {
                event.getEntity().getCapability(PlayerClassCapabilityProvider.CLASS).ifPresent(newPlayer -> {
                    newPlayer.copyFrom(oldPlayer);
                    ModNetwork.sendToPlayer(new SyncPlayerClassDataPacketS2C(newPlayer.className, newPlayer.damageMultiplayer), (ServerPlayer) event.getEntity());
                });
            });
        event.getOriginal().invalidateCaps();
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

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerClassCapabilityProvider.CLASS).ifPresent(playerClass -> {
                    ModNetwork.sendToPlayer(new SyncPlayerClassDataPacketS2C(playerClass.getClassName(), playerClass.damageMultiplayer), player);
                });
            }
        }
    }
}
