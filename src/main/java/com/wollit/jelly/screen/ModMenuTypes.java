package com.wollit.jelly.screen;

import com.wollit.jelly.JellyMod;
import com.wollit.jelly.screen.basic_crusher.BasicCrusherMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, JellyMod.MOD_ID);

    public static final RegistryObject<MenuType<BasicCrusherMenu>> BASIC_CRUSHER_MENU =
            registerMenuType(BasicCrusherMenu::new, "basic_crusher_menu");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENU_TYPES.register(name, () -> IForgeMenuType.create(factory));
    }
}