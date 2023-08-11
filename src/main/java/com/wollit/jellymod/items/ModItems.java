package com.wollit.jellymod.items;

import com.wollit.jellymod.JellyMod;
import com.wollit.jellymod.items.armor.ModArmorItem;
import com.wollit.jellymod.items.armor.ModArmorMaterial;
import com.wollit.jellymod.items.crystals.*;
import com.wollit.jellymod.items.weapons.HellBow;
import com.wollit.jellymod.items.weapons.ModItemTier;
import com.wollit.jellymod.items.weapons.custom.BrugSword;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, JellyMod.MOD_ID);

    public static final RegistryObject<Item> HELL_BOW = ITEMS.register("hell_bow",
            () -> new HellBow(new Item.Properties().durability(500), 5.0F));

    public static final RegistryObject<Item> MINER_HELMET = ITEMS.register("miner_helmet",
            () -> new ModArmorItem(ModArmorMaterial.MINER, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> MINER_CHESTPLATE = ITEMS.register("miner_chestplate",
            () -> new ModArmorItem(ModArmorMaterial.MINER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> MINER_LEGGINGS = ITEMS.register("miner_leggings",
            () -> new ModArmorItem(ModArmorMaterial.MINER, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> MINER_BOOTS = ITEMS.register("miner_boots",
            () -> new ModArmorItem(ModArmorMaterial.MINER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> BRUG_SWORD = ITEMS.register("brug_sword",
            () -> new BrugSword(ModItemTier.BRUG, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_OF_VITALITY = ITEMS.register("crystal_of_vitality",
            CrystalOfVitalityItem::new);

    public static final RegistryObject<Item> CRYSTAL_OF_ATTACK = ITEMS.register("crystal_of_attack",
            CrystalOfAttackItem::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
