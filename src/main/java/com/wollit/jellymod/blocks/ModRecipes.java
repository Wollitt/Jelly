package com.wollit.jellymod.blocks;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.blocks.crystal_assembler.CrystalAssemblerRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, JellyMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<CrystalAssemblerRecipe>> CRYSTAL_ASSEMBLER_SERIALIZER =
            SERIALIZERS.register("crystal_assembling", () -> CrystalAssemblerRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
