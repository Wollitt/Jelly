package com.wollit.jelly.items.weapons;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public abstract class AbstractMagicSword extends SwordItem {

    public static final String CRYSTALS_TAG = "magic_sword.crystals";
    public static final String RUNES_TAG = "magic_sword.runes";

    public AbstractMagicSword(Tier toolMaterial, int damage, float attackSpeed, Properties properties) {
        super(toolMaterial, damage, attackSpeed, properties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack itemStack, Enchantment enchantment) {
        return super.canApplyAtEnchantingTable(itemStack, enchantment);
    }

    @Override
    public boolean isBookEnchantable(ItemStack itemStack, ItemStack book) {
        return super.isBookEnchantable(itemStack, book);
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity p_43279_, LivingEntity p_43280_) {
        Random rand = new Random();
        int crystalSlots = rand.nextInt(5) + 1;
        int runeSlots = rand.nextInt(2) + 1;
        if (itemStack.hasTag()) {
            itemStack.getTag().putInt(CRYSTALS_TAG, crystalSlots);
        }
        return super.hurtEnemy(itemStack, p_43279_, p_43280_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        return super.use(p_41432_, p_41433_, p_41434_);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TextComponent("§7Crystals: " + itemStack.getOrCreateTag().getInt(CRYSTALS_TAG)));
            tooltip.add(new TextComponent(itemStack.getTag().toString()));
//            tooltip.add(new TextComponent("§7Runes: " + runeSlots));
        } else {
            tooltip.add(new TextComponent("Press §l§2SHIFT§r for more information!"));
        }

        super.appendHoverText(itemStack, level, tooltip, flag);
    }

    public static void setCrystals(ItemStack itemStack, int crystals) {
        if (itemStack.hasTag()) {
            itemStack.getOrCreateTag().putInt(CRYSTALS_TAG, crystals);
        }
    }
}
