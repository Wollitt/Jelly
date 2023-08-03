package com.wollit.jellymod.items.crystals;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class LesserCrystalOfVitalityItem extends AbstractCrystalItem {

    private final static AttributeModifier healthBoost = new AttributeModifier("healthBoost", 2.0F, AttributeModifier.Operation.ADDITION);

    @Override
    public void activateCrystal(ItemStack itemStack, Player player) {
        player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(healthBoost);

    }

    @Override
    public void deactivateCrystal(ItemStack itemStack, Player player) {
        player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(healthBoost);
        player.hurt(player.getLevel().damageSources().magic(), 0.0000001F);
    }
}
