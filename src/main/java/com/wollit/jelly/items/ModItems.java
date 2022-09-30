package com.wollit.jelly.items;

import com.wollit.jelly.JellyMod;
import com.wollit.jelly.items.armor.ModArmorMaterial;
import com.wollit.jelly.items.armor.custom.ModArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, JellyMod.MOD_ID);

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> COAL_POWDER = ITEMS.register("coal_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> BRONZE_DUST = ITEMS.register("bronze_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> IRON_DUST = ITEMS.register("iron_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> MINER_HELMET = ITEMS.register("miner_helmet",
            () -> new ModArmorItem(ModArmorMaterial.MINER, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> MINER_CHESTPLATE = ITEMS.register("miner_chestplate",
            () -> new ModArmorItem(ModArmorMaterial.MINER, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> MINER_LEGGINGS = ITEMS.register("miner_leggings",
            () -> new ModArmorItem(ModArmorMaterial.MINER, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> MINER_BOOTS = ITEMS.register("miner_boots",
            () -> new ModArmorItem(ModArmorMaterial.MINER, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ModArmorItem(ModArmorMaterial.STEEL, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ModArmorItem(ModArmorMaterial.STEEL, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ModArmorItem(ModArmorMaterial.STEEL, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ModArmorItem(ModArmorMaterial.STEEL, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeTab.instance)));


    public static class ModCreativeTab extends CreativeModeTab {
        private ModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.FURNACE);
        }

        public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, "jellymod");
    }
}
