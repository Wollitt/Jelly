package com.wollit.jellymod.event;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.capability.classes.AbstractClassCapabilityProvider;
import com.wollit.jellymod.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = JellyMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.USE_CLASS_ABILITY_KEY.consumeClick()) {
                assert Minecraft.getInstance().player != null;
                Minecraft.getInstance().player.getCapability(AbstractClassCapabilityProvider.CLASS).ifPresent(playerClass -> {
                    playerClass.useClassAbility(Minecraft.getInstance().player);
                });
            }
        }
    }

    @Mod.EventBusSubscriber(modid = JellyMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.USE_CLASS_ABILITY_KEY);
        }
    }
}
