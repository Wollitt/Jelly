package com.wollit.jelly.setup;

import com.mojang.logging.LogUtils;
import com.wollit.jelly.JellyMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = JellyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static final String TAB_NAME = "jellymod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
        @Override
        public ItemStack makeIcon() {return new ItemStack(Items.DIAMOND); }
    };

    public static void setup() {
        LOGGER.info("JELLY JELLY JELLY!");
        IEventBus bus = MinecraftForge.EVENT_BUS;
    }

    public static void init(FMLCommonSetupEvent event) {
        Messages.register();
    }

}
