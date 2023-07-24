package com.wollit.jellymod.blocks;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.blocks.identification_table.IdentificationTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, JellyMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<IdentificationTableBlockEntity>> IDENTIFICATION_TABLE_ENTITY =
            BLOCK_ENTITIES.register("identification_table_entity",
                    () -> BlockEntityType.Builder.of(IdentificationTableBlockEntity::new,
                            ModBlocks.IDENTIFICATION_TABLE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
