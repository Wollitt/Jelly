package com.wollit.jellymod.items.weapons;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModItemTier implements Tier {
    BRUG(3, 3000, 10.0F, 5.0F, 5, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    });

    private final int level;
    private final int durability;
    private final float miningSpeed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    ModItemTier(int level, int durability, float miningSpeed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return this.durability;
    }

    @Override
    public float getSpeed() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
