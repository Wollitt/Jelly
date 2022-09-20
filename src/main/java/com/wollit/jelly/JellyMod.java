package com.wollit.jelly;

import com.mojang.logging.LogUtils;
import com.wollit.jelly.Init.*;
import com.wollit.jelly.screen.BasicCrusherScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(JellyMod.MOD_ID)
public class JellyMod
{
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "jellymod";

    public JellyMod()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);

        ItemInit.ITEMS.register(modEventBus);
        EntityBlockInit.BlOCK_ENTITY_TYPES.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        MenuTypeInit.MENU_TYPES.register(modEventBus);
        RecipeInit.SERIALIZERS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        MenuScreens.register(MenuTypeInit.BASIC_CRUSHER_MENU.get(), BasicCrusherScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("JELLY JELLY JELLY!");
    }
}
