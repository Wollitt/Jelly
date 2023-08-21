package com.wollit.jellymod.entity;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.entity.gorilla.GorillaEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JellyEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JellyMod.MOD_ID);

    public static final RegistryObject<EntityType<GorillaEntity>> GORILLA =
            ENTITY_TYPES.register("gorilla",
                    () -> EntityType.Builder.of(GorillaEntity::new, MobCategory.MONSTER)
                            .sized(2.8F, 4.0F)
                            .build(new ResourceLocation(JellyMod.MOD_ID, "gorilla").toString()));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
