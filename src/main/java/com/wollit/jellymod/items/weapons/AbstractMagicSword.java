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

abstract public class AbstractMagicSword extends SwordItem {

    private final String CRYSTAL_SLOTS_TAG = "crystals_slots_tag";
    private final String IDENTIFICATION_STATUS_TAG = "identification_status_tag";
    private final String RUNE_SLOTS_TAG = "rune_slots_tag";

    public AbstractMagicSword(Tier toolMaterial, int damage, float attackSpeed, Properties properties) {
        super(toolMaterial, damage, attackSpeed, properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            if (!itemStack.getOrCreateTag().getBoolean(IDENTIFICATION_STATUS_TAG)) {
                tooltip.add(Component.literal("§4 UNIDENTIFIED"));
            } else {
                switch (itemStack.getOrCreateTag().getInt(CRYSTAL_SLOTS_TAG)) {
                    case 0:
                        tooltip.add(Component.literal("§7Quality: §2COMMON"));
                        tooltip.add(Component.literal("§7Crystals: " + itemStack.getOrCreateTag().getInt(CRYSTAL_SLOTS_TAG)));
                        break;
                    case 1:
                        tooltip.add(Component.literal("§7Quality: §9RARE"));
                        tooltip.add(Component.literal("§7Crystals: " + itemStack.getOrCreateTag().getInt(CRYSTAL_SLOTS_TAG)));
                        break;
                    case 2:
                        tooltip.add(Component.literal("§7Quality: §dEPIC"));
                        tooltip.add(Component.literal("§7Crystals: " + itemStack.getOrCreateTag().getInt(CRYSTAL_SLOTS_TAG)));
                        break;
                    case 3:
                        tooltip.add(Component.literal("§7Quality: §6LEGENDARY"));
                        tooltip.add(Component.literal("§7Crystals: " + itemStack.getOrCreateTag().getInt(CRYSTAL_SLOTS_TAG)));
                        break;
                    case 4:
                        tooltip.add(Component.literal("§7Quality: §bLUMINOUS"));
                        tooltip.add(Component.literal("§7Crystals: " + itemStack.getOrCreateTag().getInt(CRYSTAL_SLOTS_TAG)));
                        break;
                }
            }
        }
    }
}
