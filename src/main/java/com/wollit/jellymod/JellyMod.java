package com.wollit.jellymod;

import com.wollit.jellymod.blocks.ModBlockEntities;
import com.wollit.jellymod.blocks.ModBlocks;
import com.wollit.jellymod.blocks.ModMenuTypes;
import com.wollit.jellymod.blocks.identification_table.IdentificationTableScreen;
import com.wollit.jellymod.items.ModItems;
import com.wollit.jellymod.network.ModNetwork;
import com.wollit.jellymod.util.ModCreativeModeTabs;
import com.wollit.jellymod.util.ModItemProperties;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(com.wollit.jellymod.JellyMod.MOD_ID)
public class JellyMod {

    public static final String MOD_ID = "jellymod";

    public JellyMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModNetwork.register();
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == ModCreativeModeTabs.JELLY_MOD_TAB) {
            event.accept(ModItems.HELL_BOW);
            event.accept(ModItems.MINER_HELMET);
            event.accept(ModItems.MINER_CHESTPLATE);
            event.accept(ModItems.MINER_LEGGINGS);
            event.accept(ModItems.MINER_BOOTS);
            event.accept(ModItems.BRUG_SWORD);
            event.accept(ModBlocks.IDENTIFICATION_TABLE);
        }
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ModItemProperties.addCustomItemProperties();
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.IDENTIFICATION_TABLE_MENU.get(), IdentificationTableScreen::new);
        }
    }
}


//Set movement speed
//player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(player.getAbilities().getWalkingSpeed() * ((SniperClassCapability) playerClass).getMovementSpeedMultiplayer());
