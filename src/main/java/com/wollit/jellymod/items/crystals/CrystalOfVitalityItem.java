package com.wollit.jellymod.items.crystals;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class CrystalOfVitalityItem extends AbstractCrystalItem {

    private final static AttributeModifier healthBoost = new AttributeModifier("healthBoost", 6.0F, AttributeModifier.Operation.ADDITION);

    public CrystalOfVitalityItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void activateCrystal(ItemStack itemStack, Player player) {
        player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(healthBoost);
    }

    @Override
    public void deactivateCrystal(ItemStack itemStack, Player player) {
        player.getAttribute(Attributes.MAX_HEALTH).removeModifier(healthBoost);
        player.hurt(player.getLevel().damageSources().magic(), 0.0001F);
    }


}
