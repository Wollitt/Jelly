package com.wollit.jelly.Init;

import com.wollit.jelly.JellyMod;
import com.wollit.jelly.blocks.entity.SteelFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityBlockInit {
    public static final DeferredRegister<BlockEntityType<?>> BlOCK_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, JellyMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<SteelFurnaceBlockEntity>> STEEL_FURNACE_ENTITY =
            BlOCK_ENTITY_TYPES.register("steel_furnace_entity", () -> 
                    BlockEntityType.Builder.of(SteelFurnaceBlockEntity::new, BlockInit.STEEL_FURNACE.get()).build(null));
}
