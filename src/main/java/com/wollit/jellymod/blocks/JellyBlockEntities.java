package com.wollit.jellymod.blocks;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.blocks.crystal_assembler.CrystalAssemblerBlockEntity;
import com.wollit.jellymod.blocks.gear_amplifier.GearAmplifierBlockEntity;
import com.wollit.jellymod.blocks.identification_table.IdentificationTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JellyBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, JellyMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<IdentificationTableBlockEntity>> IDENTIFICATION_TABLE_ENTITY =
            BLOCK_ENTITIES.register("identification_table_entity",
                    () -> BlockEntityType.Builder.of(IdentificationTableBlockEntity::new,
                            JellyBlocks.IDENTIFICATION_TABLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<GearAmplifierBlockEntity>> GEAR_AMPLIFIER_ENTITY =
            BLOCK_ENTITIES.register("gear_amplifier_entity",
                    () -> BlockEntityType.Builder.of(GearAmplifierBlockEntity::new,
                            JellyBlocks.GEAR_AMPLIFIER.get()).build(null));

    public static final RegistryObject<BlockEntityType<CrystalAssemblerBlockEntity>> CRYSTAL_ASSEMBLER_ENTITY =
            BLOCK_ENTITIES.register("crystal_assembler_entity",
                    () -> BlockEntityType.Builder.of(CrystalAssemblerBlockEntity::new,
                            JellyBlocks.CRYSTAL_ASSEMBLER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
