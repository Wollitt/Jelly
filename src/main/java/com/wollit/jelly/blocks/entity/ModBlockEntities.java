package com.wollit.jelly.blocks.entity;

import com.wollit.jelly.JellyMod;
import com.wollit.jelly.blocks.ModBlocks;
import com.wollit.jelly.blocks.entity.custom.BasicCrusherBlockEntity;
import com.wollit.jelly.blocks.entity.custom.BasicAlloyFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BlOCK_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, JellyMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<BasicCrusherBlockEntity>> BASIC_CRUSHER_ENTITY =
            BlOCK_ENTITY_TYPES.register("basic_crusher_entity", () ->
                    BlockEntityType.Builder.of(BasicCrusherBlockEntity::new, ModBlocks.BASIC_CRUSHER.get()).build(null));

    public static final RegistryObject<BlockEntityType<BasicAlloyFurnaceBlockEntity>> BASIC_ALLOY_FURNACE_ENTITY =
            BlOCK_ENTITY_TYPES.register("basic_alloy_furnace_entity", () ->
                    BlockEntityType.Builder.of(BasicAlloyFurnaceBlockEntity::new, ModBlocks.BASIC_ALLOY_FURNACE.get()).build(null));
}