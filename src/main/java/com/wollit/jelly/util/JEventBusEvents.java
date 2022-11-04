package com.wollit.jelly.util;

import com.wollit.jelly.JellyMod;
import com.wollit.jelly.recipe.BasicCrusherRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = JellyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {

        event.getRegistry().registerAll();
    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, BasicCrusherRecipe.Type.ID, BasicCrusherRecipe.Type.INSTANCE);
    }
}
