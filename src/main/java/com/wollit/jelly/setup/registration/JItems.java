package com.wollit.jelly.setup.registration;

import com.wollit.jelly.items.armor.JArmorMaterial;
import com.wollit.jelly.items.armor.custom.ModArmorItem;
import com.wollit.jelly.items.weapons.JItemTier;
import com.wollit.jelly.items.weapons.custom.FairySword;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.wollit.jelly.JellyMod.MOD_ID;
import static com.wollit.jelly.setup.Registration.ITEM_PROPERTIES;

public class JItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(ITEM_PROPERTIES));

    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
            () -> new Item(ITEM_PROPERTIES));

    public static final RegistryObject<Item> BRONZE_DUST = ITEMS.register("bronze_dust",
            () -> new Item(ITEM_PROPERTIES));

    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(ITEM_PROPERTIES));

    public static final RegistryObject<Item> TIN_DUST = ITEMS.register("tin_dust",
            () -> new Item(ITEM_PROPERTIES));

    public static final RegistryObject<Item> IRON_DUST = ITEMS.register("iron_dust",
            () -> new Item(ITEM_PROPERTIES));

    public static final RegistryObject<Item> COAL_POWDER = ITEMS.register("coal_dust",
            () -> new Item(ITEM_PROPERTIES));

    public static final RegistryObject<Item> MINER_HELMET = ITEMS.register("miner_helmet",
            () -> new ModArmorItem(JArmorMaterial.MINER, EquipmentSlot.HEAD,
                    ITEM_PROPERTIES));

    public static final RegistryObject<Item> MINER_CHESTPLATE = ITEMS.register("miner_chestplate",
            () -> new ModArmorItem(JArmorMaterial.MINER, EquipmentSlot.CHEST,
                    ITEM_PROPERTIES));

    public static final RegistryObject<Item> MINER_LEGGINGS = ITEMS.register("miner_leggings",
            () -> new ModArmorItem(JArmorMaterial.MINER, EquipmentSlot.LEGS,
                    ITEM_PROPERTIES));

    public static final RegistryObject<Item> MINER_BOOTS = ITEMS.register("miner_boots",
            () -> new ModArmorItem(JArmorMaterial.MINER, EquipmentSlot.FEET,
                    ITEM_PROPERTIES));

    public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ModArmorItem(JArmorMaterial.STEEL, EquipmentSlot.HEAD,
                    ITEM_PROPERTIES));

    public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ModArmorItem(JArmorMaterial.STEEL, EquipmentSlot.CHEST,
                    ITEM_PROPERTIES));

    public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ModArmorItem(JArmorMaterial.STEEL, EquipmentSlot.LEGS,
                    ITEM_PROPERTIES));

    public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ModArmorItem(JArmorMaterial.STEEL, EquipmentSlot.FEET,
                    ITEM_PROPERTIES));

    public static final RegistryObject<Item> FAIRY_SWORD = ITEMS.register("fairy_sword",
            () -> new FairySword(JItemTier.FAIRY, ITEM_PROPERTIES));
}
