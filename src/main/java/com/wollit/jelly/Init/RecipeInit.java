package com.wollit.jelly.Init;

import com.wollit.jelly.JellyMod;
import com.wollit.jelly.recipe.SteelFurnaceRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeInit {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, JellyMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<SteelFurnaceRecipe>> GEM_CUTTING_SERIALIZER =
            SERIALIZERS.register("steel_furnace", () -> SteelFurnaceRecipe.Serializer.INSTANCE);

}
