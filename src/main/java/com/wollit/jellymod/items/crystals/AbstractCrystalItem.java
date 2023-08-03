package com.wollit.jellymod.items.crystals;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

abstract public class AbstractCrystalItem extends Item {

    public AbstractCrystalItem() {
        super(new Item.Properties().stacksTo(1));
    }

    public abstract void activateCrystal(ItemStack itemStack, Player player);

    public abstract void deactivateCrystal(ItemStack itemStack, Player player);




}
