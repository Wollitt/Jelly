package com.wollit.jellymod.util;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.capability.classes.AbstractClassCapabilityProvider;
import com.wollit.jellymod.capability.classes.range.sniper.SniperClassProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JellyMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(AbstractClassCapabilityProvider.CLASS).isPresent()) {
                event.addCapability(new ResourceLocation(JellyMod.MOD_ID, "class"), new SniperClassProvider());
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
        }
    }
}
