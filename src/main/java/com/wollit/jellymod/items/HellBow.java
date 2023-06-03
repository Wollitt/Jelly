package com.wollit.jellymod.items;

import com.wollit.jellymod.capability.classes.range.RangeClassCapability;
import com.wollit.jellymod.capability.classes.warrior.WarriorCapabilityProvider;
import com.wollit.jellymod.capability.classes.warrior.WarriorClassCapability;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.concurrent.atomic.AtomicReference;

public class HellBow extends BowItem {

    public HellBow(Properties p_40660_, float arrowDamageMultiplier) {
        super(p_40660_);
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int p_40670_) {
        if (livingEntity instanceof Player player) {
            boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getTagEnchantmentLevel(Enchantments.INFINITY_ARROWS, itemStack) > 0;
            ItemStack itemstack = player.getProjectile(itemStack);

            int i = this.getUseDuration(itemStack) - p_40670_;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(itemStack, level, player, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getPowerForTime(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, itemStack, player));
                    if (!level.isClientSide) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrow abstractarrow = arrowitem.createArrow(level, itemstack, player);
                        abstractarrow = customArrow(abstractarrow);
                        abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);

                        player.getCapability(WarriorCapabilityProvider.CLASS).ifPresent(playerClass -> {
                            if (playerClass instanceof WarriorClassCapability) {
                                player.sendSystemMessage(Component.literal("Dmg multiplayer on warrior is " + ((WarriorClassCapability) playerClass).getSwordDmg()));
                            }

                            if (playerClass instanceof RangeClassCapability) {
                                player.sendSystemMessage(Component.literal("Dmg multiplayer on ranger is " + ((RangeClassCapability) playerClass).getRangeDmg()));
                            }
                        });

                        if (f == 1.0F) {
                            abstractarrow.setCritArrow(true);
                        }
                        AtomicReference<Float> pDamage = new AtomicReference<>(1F);

                        int j = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.POWER_ARROWS, itemStack);
                        if (j > 0) {
                            abstractarrow.setBaseDamage((abstractarrow.getBaseDamage() + (double)j * 0.5D + 0.5D) * pDamage.get());
                        }

                        int k = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PUNCH_ARROWS, itemStack);
                        if (k > 0) {
                            abstractarrow.setKnockback(k);
                        }

                        if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemStack) > 0) {
                            abstractarrow.setSecondsOnFire(100);
                        }

                        itemStack.hurtAndBreak(1, player, (p_276007_) -> {
                            p_276007_.broadcastBreakEvent(player.getUsedItemHand());
                        });
                        if (flag1 || player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
                            abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }

                        abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() * pDamage.get());

                        level.addFreshEntity(abstractarrow);
                    }

                    level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.getInventory().removeItem(itemstack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }


}
