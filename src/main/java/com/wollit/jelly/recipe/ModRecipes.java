package com.wollit.jelly.recipe;

import com.wollit.jelly.JellyMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, JellyMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<BasicCrusherRecipe>> BASIC_CRUSHER_SERIALIZER =
            SERIALIZERS.register("basic_crusher", () -> BasicCrusherRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<BasicAlloyFurnaceRecipe>> BASIC_ALLOY_FURNACE_SERIALIZER =
            SERIALIZERS.register("basic_alloy_furnace_serializer", () -> BasicAlloyFurnaceRecipe.Serializer.INSTANCE);

}
