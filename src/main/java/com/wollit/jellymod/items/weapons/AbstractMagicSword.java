package com.wollit.jellymod.items.weapons;

import com.wollit.jellymod.client.ClientPlayerClassData;
import com.wollit.jellymod.items.crystals.AbstractCrystalItem;
import lombok.Getter;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Getter
abstract public class AbstractMagicSword extends SwordItem {

    private final String CRYSTAL_SLOTS_TAG = "crystals_slots_tag";
    private final String IDENTIFICATION_STATUS_TAG = "identification_status_tag";
    private final String RUNE_SLOTS_TAG = "rune_slots_tag";
    private final String HAS_GIVEN_BOOSTS = "has_given_boosts_tag";
    private final String CRYSTAL_SLOT_1 = "crystal_slot_1_tag";
    private final String CRYSTAL_SLOT_2 = "crystal_slot_2_tag";
    private final String CRYSTAL_SLOT_3 = "crystal_slot_3_tag";
    private final String CRYSTAL_SLOT_4 = "crystal_slot_4_tag";

    public AbstractMagicSword(Tier toolMaterial, int damage, float attackSpeed, Properties properties) {
        super(toolMaterial, damage, attackSpeed, properties);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int inventorySlot, boolean isSelected) {
            if (entity instanceof Player player) {
                if (isSelected && !itemStack.getOrCreateTag().getBoolean(HAS_GIVEN_BOOSTS) && itemStack.getOrCreateTag().getBoolean(IDENTIFICATION_STATUS_TAG)) {
                    activateCrystalBuffs(itemStack, player);
                    itemStack.getOrCreateTag().putBoolean(HAS_GIVEN_BOOSTS, true);
                } else if (!isSelected && itemStack.getOrCreateTag().getBoolean(HAS_GIVEN_BOOSTS)) {
                    deactivateCrystalBuffs(itemStack, player);
                    itemStack.getOrCreateTag().putBoolean(HAS_GIVEN_BOOSTS, false);
                }
        }
        super.inventoryTick(itemStack, level, entity, inventorySlot, isSelected);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        return super.onEntityItemUpdate(stack, entity);
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
                        tooltip.add(Component.literal("§7Quality: §3HOLY"));
                        tooltip.add(Component.literal("§7Crystals: " + itemStack.getOrCreateTag().getInt(CRYSTAL_SLOTS_TAG)));
                        break;
                }

                switch (itemStack.getOrCreateTag().getInt(CRYSTAL_SLOTS_TAG)) {
                    case 4:
                        tooltip.add(Component.literal("§7Slot4: " + itemStack.getOrCreateTag().getString(CRYSTAL_SLOT_4)));
                    case 3:
                        tooltip.add(Component.literal("§7Slot3: " + itemStack.getOrCreateTag().getString(CRYSTAL_SLOT_3)));
                    case 2:
                        tooltip.add(Component.literal("§7Slot2: " + itemStack.getOrCreateTag().getString(CRYSTAL_SLOT_2)));
                    case 1:
                        tooltip.add(Component.literal("§7Slot1: " + itemStack.getOrCreateTag().getString(CRYSTAL_SLOT_1)));
                        break;
                }
            }
        }
    }

    private void activateCrystalBuffs(ItemStack itemStack, Player player) {
        switch (itemStack.getOrCreateTag().getInt(CRYSTAL_SLOTS_TAG)) {
            case 4:
                if (ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemStack.getOrCreateTag().getString(CRYSTAL_SLOT_4))) instanceof AbstractCrystalItem crystal) {
                    System.out.println("activated 4");
                    crystal.activateCrystal(itemStack, player);
                }
            case 3:
                if (ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemStack.getOrCreateTag().getString(CRYSTAL_SLOT_3))) instanceof AbstractCrystalItem crystal) {
                    System.out.println("activated 3");
                    crystal.activateCrystal(itemStack, player);
                }
            case 2:
                if (ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemStack.getOrCreateTag().getString(CRYSTAL_SLOT_2))) instanceof AbstractCrystalItem crystal) {
                    System.out.println("activated 2");
                    crystal.activateCrystal(itemStack, player);
                }
            case 1:
                if (ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemStack.getOrCreateTag().getString(CRYSTAL_SLOT_1))) instanceof AbstractCrystalItem crystal) {
                    System.out.println("activated 1");
                    crystal.activateCrystal(itemStack, player);
                }
                break;
        }
    }

    private void deactivateCrystalBuffs(ItemStack itemStack, Player player) {
        switch (itemStack.getOrCreateTag().getInt(CRYSTAL_SLOTS_TAG)) {
            case 4:
                if (ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemStack.getOrCreateTag().getString(CRYSTAL_SLOT_4))) instanceof AbstractCrystalItem crystal) {
                    System.out.println("deactivated 4");
                    crystal.deactivateCrystal(itemStack, player);
                }
            case 3:
                if (ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemStack.getOrCreateTag().getString(CRYSTAL_SLOT_3))) instanceof AbstractCrystalItem crystal) {
                    System.out.println("deactivated 3");
                    crystal.deactivateCrystal(itemStack, player);
                }
            case 2:
                if (ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemStack.getOrCreateTag().getString(CRYSTAL_SLOT_2))) instanceof AbstractCrystalItem crystal) {
                    System.out.println("deactivated 2");
                    crystal.deactivateCrystal(itemStack, player);
                }
            case 1:
                if (ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemStack.getOrCreateTag().getString(CRYSTAL_SLOT_1))) instanceof AbstractCrystalItem crystal) {
                    System.out.println("deactivated 1");
                    crystal.deactivateCrystal(itemStack, player);
                }
                break;
        }
    }
}
