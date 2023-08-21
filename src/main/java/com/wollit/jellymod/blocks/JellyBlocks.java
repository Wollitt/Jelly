package com.wollit.jellymod.blocks;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.blocks.crystal_assembler.CrystalAssemblerBlock;
import com.wollit.jellymod.blocks.gear_amplifier.GearAmplifierBlock;
import com.wollit.jellymod.blocks.identification_table.IdentificationTableBlock;
import com.wollit.jellymod.items.JellyItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class JellyBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JellyMod.MOD_ID);

    public static final RegistryObject<Block> IDENTIFICATION_TABLE = registerBlock("identification_table",
            () -> new IdentificationTableBlock(BlockBehaviour.Properties.of(Material.METAL).strength(6F).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<Block> GEAR_AMPLIFIER = registerBlock("gear_amplifier",
            () -> new GearAmplifierBlock(BlockBehaviour.Properties.of(Material.METAL).strength(6F).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<Block> CRYSTAL_ASSEMBLER = registerBlock("crystal_assembler",
            () -> new CrystalAssemblerBlock(BlockBehaviour.Properties.of(Material.METAL).strength(6F).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<Block> JUNGLE_COBBLESTONE = registerBlock("jungle_cobblestone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return JellyItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
