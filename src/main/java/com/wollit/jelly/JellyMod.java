package com.wollit.jelly;

import com.wollit.jelly.screen.basic_crusher.BasicCrusherScreen;
import com.wollit.jelly.screen.basic_alloy_furnace.BasicAlloyFurnaceScreen;
import com.wollit.jelly.screen.identification_station.IdentificationStationScreen;
import com.wollit.jelly.setup.registration.JBlockEntities;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import com.wollit.jelly.setup.ModSetup;
import com.wollit.jelly.setup.Registration;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(JellyMod.MOD_ID)
public class JellyMod
{
    public static final String MOD_ID = "jellymod";
    public JellyMod()
    {

        ModSetup.setup();
        Registration.init();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(ModSetup::init);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        MenuScreens.register(JBlockEntities.BASIC_CRUSHER_MENU.get(), BasicCrusherScreen::new);
        MenuScreens.register(JBlockEntities.BASIC_ALLOY_FURNACE_MENU.get(), BasicAlloyFurnaceScreen::new);
        MenuScreens.register(JBlockEntities.IDENTIFICATION_STATION_MENU.get(), IdentificationStationScreen::new);
    }

}
