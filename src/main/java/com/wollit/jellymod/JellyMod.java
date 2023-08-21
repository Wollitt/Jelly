package com.wollit.jellymod;

import com.wollit.jellymod.blocks.JellyBlockEntities;
import com.wollit.jellymod.blocks.JellyBlocks;
import com.wollit.jellymod.blocks.JellyMenuTypes;
import com.wollit.jellymod.blocks.JellyRecipes;
import com.wollit.jellymod.blocks.crystal_assembler.CrystalAssemblerScreen;
import com.wollit.jellymod.blocks.gear_amplifier.GearAmplifierScreen;
import com.wollit.jellymod.blocks.identification_table.IdentificationTableScreen;
import com.wollit.jellymod.entity.JellyEntities;
import com.wollit.jellymod.entity.gorilla.GorillaRenderer;
import com.wollit.jellymod.items.JellyItems;
import com.wollit.jellymod.network.ModNetwork;
import com.wollit.jellymod.util.ModCreativeModeTabs;
import com.wollit.jellymod.util.ModItemProperties;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JellyMod.MOD_ID)
public class JellyMod {

    public static final String MOD_ID = "jellymod";

    public JellyMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        JellyItems.register(modEventBus);
        JellyBlocks.register(modEventBus);
        JellyBlockEntities.register(modEventBus);
        JellyMenuTypes.register(modEventBus);
        JellyRecipes.register(modEventBus);
        JellyEntities.register(modEventBus);

        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModNetwork.register();
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == ModCreativeModeTabs.JELLY_MOD_TAB) {
            event.accept(JellyItems.HELL_BOW);
//            event.accept(ModItems.MINER_HELMET);
//            event.accept(ModItems.MINER_CHESTPLATE);
//            event.accept(ModItems.MINER_LEGGINGS);
//            event.accept(ModItems.MINER_BOOTS);
            event.accept(JellyItems.BRUG_SWORD);
            event.accept(JellyItems.CRYSTAL_OF_VITALITY);
            event.accept(JellyItems.CRYSTAL_OF_ATTACK);
            event.accept(JellyItems.GORILLA_SPAWN_EGG);

            event.accept(JellyBlocks.IDENTIFICATION_TABLE);
            event.accept(JellyBlocks.GEAR_AMPLIFIER);
            event.accept(JellyBlocks.CRYSTAL_ASSEMBLER);
        }

        if (event.getTab() == ModCreativeModeTabs.JELLY_MOD_BLOCKS) {
            event.accept(JellyBlocks.JUNGLE_COBBLESTONE);
        }
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ModItemProperties.addCustomItemProperties();
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(JellyMenuTypes.IDENTIFICATION_TABLE_MENU.get(), IdentificationTableScreen::new);
            MenuScreens.register(JellyMenuTypes.GEAR_AMPLIFIER_MENU.get(), GearAmplifierScreen::new);
            MenuScreens.register(JellyMenuTypes.CRYSTAL_ASSEMBLER_MENU.get(), CrystalAssemblerScreen::new);

            EntityRenderers.register(JellyEntities.GORILLA.get(), GorillaRenderer::new);
        }
    }
}


//Set movement speed
//player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(player.getAbilities().getWalkingSpeed() * ((SniperClassCapability) playerClass).getMovementSpeedMultiplayer());
