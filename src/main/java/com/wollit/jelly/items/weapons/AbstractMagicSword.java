package com.wollit.jelly.items.weapons.custom;

import com.wollit.jelly.util.KeyboardHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FairySword extends SwordItem {
    protected final int RUNE_SLOTS;
    protected final int CRYSTAL_SLOTS;
    protected final int RUNES;
    protected final int CRYSTALS;

    public FairySword(Tier toolMaterial, Properties properties) {
        super(toolMaterial, 3, -2.4F, properties);
        RUNE_SLOTS = 0;
        RUNES = 0;
        CRYSTAL_SLOTS = 0;
        CRYSTALS = 0;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return super.isBookEnchantable(stack, book);
    }

    @Override
    public boolean hurtEnemy(ItemStack p_43278_, LivingEntity p_43279_, LivingEntity p_43280_) {
        return super.hurtEnemy(p_43278_, p_43279_, p_43280_);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (KeyboardHandler.isHoldingShift()) {
            tooltip.add(new TextComponent("Crystals: " + RUNES));
            tooltip.add(new TextComponent("Runes: " + CRYSTALS));
        }
        super.appendHoverText(itemStack, level, tooltip, flag);
    }
}
