package com.wollit.jellymod.event;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.capability.classes.AbstractClassCapabilityProvider;
import com.wollit.jellymod.capability.classes.archer.ArcherClassCapability;
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
                assert Minecraft.getInstance().player != null;
                Minecraft.getInstance().player.getCapability(AbstractClassCapabilityProvider.CLASS).ifPresent(playerClass -> {
                    playerClass.useClassAbility(Minecraft.getInstance().player);
                });
            } else if (KeyBinding.CHOOSE_CLASS_KEY.consumeClick()) {
                Minecraft.getInstance().player.getCapability(AbstractClassCapabilityProvider.CLASS).ifPresent(playerClass -> {
                    Minecraft.getInstance().player.sendSystemMessage(Component.literal("Your class is " + playerClass.getClassName()));
                    if (playerClass instanceof ArcherClassCapability archer) {
                        Minecraft.getInstance().player.sendSystemMessage(Component.literal("Your dmg multiplayer is " + archer.getRangeDmg()));
                    }
                });
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
