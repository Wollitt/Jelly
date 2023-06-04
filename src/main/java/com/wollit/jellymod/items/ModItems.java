package com.wollit.jellymod.items;

import com.wollit.jellymod.JellyMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, JellyMod.MOD_ID);

    public static final RegistryObject<Item> HELL_BOW = ITEMS.register("hell_bow",
            () -> new HellBow(new Item.Properties().durability(500), 5.0F));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
