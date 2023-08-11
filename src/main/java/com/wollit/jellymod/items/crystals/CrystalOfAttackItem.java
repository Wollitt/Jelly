package com.wollit.jellymod.items.crystals;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CrystalOfAttackItem extends AbstractCrystalItem {


    @Override
    public void activateCrystal(ItemStack itemStack, Player player) {
        itemStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 2F, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
    }

    @Override
    public void deactivateCrystal(ItemStack itemStack, Player player) {
        itemStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", -2F, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
    }
}
