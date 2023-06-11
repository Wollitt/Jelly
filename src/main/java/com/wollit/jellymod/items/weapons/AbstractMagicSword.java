package com.wollit.jellymod.items.weapons;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AbstractMagicSword extends SwordItem {

    private final String CRYSTAL_SLOTS_TAG = "crystals_slog_tag";
    private final String RUNE_SLOTS_TAG = "rune_slots_tag";
    private final String IDENTIFICATION_STATUS_TAG = "identification_status_tag";

    public AbstractMagicSword(Tier toolMaterial, int damage, float attackSpeed, Properties properties) {
        super(toolMaterial, damage, attackSpeed, properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        itemStack.getOrCreateTag().putInt(CRYSTAL_SLOTS_TAG,3);
        itemStack.getOrCreateTag().putBoolean(IDENTIFICATION_STATUS_TAG, false);
        if (Screen.hasShiftDown()) {
            if (itemStack.getOrCreateTag().getBoolean(IDENTIFICATION_STATUS_TAG)) {
                tooltip.add(Component.literal("§7Crystals: " + itemStack.getOrCreateTag().getInt(CRYSTAL_SLOTS_TAG)));
            } else {
                tooltip.add(Component.literal("§4 UNIDENTIFIED"));
            }
        }
    }
}
