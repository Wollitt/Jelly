package com.wollit.jellymod.event;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.client.ClientPlayerClassData;
import com.wollit.jellymod.screens.ClassSelectionScreen;
import com.wollit.jellymod.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
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
            }
//            } else if (KeyBinding.CHOOSE_CLASS_KEY.consumeClick()) {
//                Minecraft.getInstance().player.getCapability(PlayerClassCapabilityProvider.CLASS).ifPresent(playerClass -> {
//                    Minecraft.getInstance().player.sendSystemMessage(Component.literal("Twoja klasa to:" + ClientPlayerClassData.getClassName() ));
//                });
//            }

            if (KeyBinding.CHOOSE_CLASS_KEY.consumeClick()) {
                if (ClientPlayerClassData.getClassName().equals("")) {
                    Minecraft.getInstance().setScreen(new ClassSelectionScreen(Component.literal("")));
                } else {
                    Minecraft.getInstance().player.sendSystemMessage(Component.literal("Class: " + ClientPlayerClassData.getClassName()));
                }
            }
        }
    }

    @Mod.EventBusSubscriber(modid = JellyMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.USE_CLASS_ABILITY_KEY);
            event.register(KeyBinding.CHOOSE_CLASS_KEY);
            event.register(KeyBinding.TEST_KEY);
        }
    }
}
