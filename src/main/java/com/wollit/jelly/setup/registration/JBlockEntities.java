package com.wollit.jelly.setup.registration;

import com.wollit.jelly.blocks.BasicAlloyFurnaceBlock;
import com.wollit.jelly.blocks.BasicCrusherBlock;
import com.wollit.jelly.blocks.IdentificationStationBlock;
import com.wollit.jelly.blocks.entity.BasicAlloyFurnaceBlockEntity;
import com.wollit.jelly.blocks.entity.BasicCrusherBlockEntity;
import com.wollit.jelly.blocks.entity.IdentificationStationBlockEntity;
import com.wollit.jelly.screen.basic_alloy_furnace.BasicAlloyFurnaceMenu;
import com.wollit.jelly.screen.basic_crusher.BasicCrusherMenu;
import com.wollit.jelly.screen.identification_station.IdentificationStationMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.wollit.jelly.JellyMod.MOD_ID;
import static com.wollit.jelly.setup.Registration.ITEM_PROPERTIES;

public class JBlockEntities {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MOD_ID);
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BASIC_CRUSHER
    public static final RegistryObject<BasicCrusherBlock> BASIC_CRUSHER =
            BLOCKS.register("basic_crusher", BasicCrusherBlock::new);
    public static final RegistryObject<Item> BASIC_CRUSHER_ITEM = fromBlock(BASIC_CRUSHER);
    public static final RegistryObject<BlockEntityType<BasicCrusherBlockEntity>> BASIC_CRUSHER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("basic_crusher_entity", () -> BlockEntityType.Builder.of
                    (BasicCrusherBlockEntity::new, BASIC_CRUSHER.get()).build(null));
    public static final RegistryObject<MenuType<BasicCrusherMenu>> BASIC_CRUSHER_MENU =
            registerMenuType(BasicCrusherMenu::new, "basic_crusher_menu");

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BASIC_ALLOY_FURNACE
    public static final RegistryObject<BasicAlloyFurnaceBlock> BASIC_ALLOY_FURNACE =
            BLOCKS.register("basic_alloy_furnace", BasicAlloyFurnaceBlock::new);
    public static final RegistryObject<Item> BASIC_ALLOY_FURNACE_ITEM = fromBlock(BASIC_ALLOY_FURNACE);
    public static final RegistryObject<BlockEntityType<BasicAlloyFurnaceBlockEntity>> BASIC_ALLOY_FURNACE_ENTITY =
            BLOCK_ENTITIES.register("basic_alloy_furnace_entity", () -> BlockEntityType.Builder.of
                    (BasicAlloyFurnaceBlockEntity::new, BASIC_ALLOY_FURNACE.get()).build(null));
    public static final RegistryObject<MenuType<BasicAlloyFurnaceMenu>> BASIC_ALLOY_FURNACE_MENU =
            registerMenuType(BasicAlloyFurnaceMenu::new, "basic_alloy_furnace_menu");

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // IDENTIFICATION_STATION
    public static final RegistryObject<IdentificationStationBlock> IDENTIFICATION_STATION =
            BLOCKS.register("identification_station", IdentificationStationBlock::new);
    public static final RegistryObject<Item> IDENTIFICATION_STATION_ITEM = fromBlock(IDENTIFICATION_STATION);
    public static final RegistryObject<BlockEntityType<IdentificationStationBlockEntity>> IDENTIFICATION_STATION_ENTITY =
            BLOCK_ENTITIES.register("identification_station_entity", () -> BlockEntityType.Builder.of
                    (IdentificationStationBlockEntity::new, IDENTIFICATION_STATION.get()).build(null));
    public static final RegistryObject<MenuType<IdentificationStationMenu>> IDENTIFICATION_STATION_MENU =
            registerMenuType(IdentificationStationMenu::new, "identification_station_menu");


    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return CONTAINERS.register(name, () -> IForgeMenuType.create(factory));
    }

        public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}
