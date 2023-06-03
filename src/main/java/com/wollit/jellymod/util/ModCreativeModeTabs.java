package com.wollit.jellymod.util;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.items.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JellyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    public static CreativeModeTab JELLY_MOD_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        JELLY_MOD_TAB = event.registerCreativeModeTab(new ResourceLocation(JellyMod.MOD_ID, "jelly_mod_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.HELL_BOW.get())).title(Component.translatable("creativemodetab.jelly_mod_tab")));
    }
}
