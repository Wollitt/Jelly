package com.wollit.jellymod.blocks;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.blocks.gear_amplifier.GearAmplifierMenu;
import com.wollit.jellymod.blocks.identification_table.IdentificationTableMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, JellyMod.MOD_ID);

    public static final RegistryObject<MenuType<IdentificationTableMenu>> IDENTIFICATION_TABLE_MENU =
            registerMenuType("identification_table_menu", IdentificationTableMenu::new);

    public static final RegistryObject<MenuType<GearAmplifierMenu>> GEAR_AMPLIFIER_MENU =
            registerMenuType("gear_amplifier_menu", GearAmplifierMenu::new);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
