package com.wollit.jelly.setup;

import com.wollit.jelly.setup.registration.JBlockEntities;
import com.wollit.jelly.setup.registration.JBlocks;
import com.wollit.jelly.setup.registration.JItems;
import com.wollit.jelly.setup.registration.JSerializers;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class Registration {
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP);

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        JBlockEntities.BLOCKS.register(modEventBus);
        JBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        JBlockEntities.CONTAINERS.register(modEventBus);
        JBlockEntities.ITEMS.register(modEventBus);

        JItems.ITEMS.register(modEventBus);
        JBlocks.BLOCKS.register(modEventBus);
        JSerializers.SERIALIZERS.register(modEventBus);
    }

//    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
//        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
//    }

}
