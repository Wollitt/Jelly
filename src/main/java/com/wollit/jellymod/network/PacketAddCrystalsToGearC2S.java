package com.wollit.jellymod.network;

import com.wollit.jellymod.blocks.gear_amplifier.GearAmplifierBlockEntity;
import com.wollit.jellymod.items.crystals.AbstractCrystalItem;
import com.wollit.jellymod.items.weapons.AbstractMagicSword;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class PacketAddCrystalsToGearC2S {

    private final BlockPos pos;

    public PacketAddCrystalsToGearC2S(BlockEntity blockEntity) {
        pos = blockEntity.getBlockPos();
    }

    public PacketAddCrystalsToGearC2S(FriendlyByteBuf buf) {
        this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(pos.getX()).writeInt(pos.getY()).writeInt(pos.getZ());
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();

        ctx.enqueueWork(() -> {
            ServerLevel level = ctx.getSender().getLevel();
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof GearAmplifierBlockEntity entity) {
                ItemStack itemStack = entity.getItemStackHandler().getStackInSlot(0);

                if (itemStack.getItem() instanceof AbstractMagicSword sword) {
                    switch (itemStack.getOrCreateTag().getInt(sword.getCRYSTAL_SLOTS_TAG())) {
                        case 4:
                            if (itemStack.getOrCreateTag().getString(sword.getCRYSTAL_SLOT_4()).equals("empty")) {
                                if (entity.getItemStackHandler().getStackInSlot(4).getItem() instanceof AbstractCrystalItem crystalItem) {
                                    itemStack.getOrCreateTag().putString(sword.getCRYSTAL_SLOT_4(),
                                            ForgeRegistries.ITEMS.getKey(crystalItem).toString());
                                    entity.getItemStackHandler().extractItem(4, 1, false);
                                }
                            }
                        case 3:
                            if (itemStack.getOrCreateTag().getString(sword.getCRYSTAL_SLOT_3()).equals("empty")) {
                                if (entity.getItemStackHandler().getStackInSlot(3).getItem() instanceof AbstractCrystalItem crystalItem) {
                                    itemStack.getOrCreateTag().putString(sword.getCRYSTAL_SLOT_3(),
                                            ForgeRegistries.ITEMS.getKey(crystalItem).toString());
                                    entity.getItemStackHandler().extractItem(3, 1, false);
                                }
                            }
                        case 2:
                            if (itemStack.getOrCreateTag().getString(sword.getCRYSTAL_SLOT_2()).equals("empty")) {
                                if (entity.getItemStackHandler().getStackInSlot(2).getItem() instanceof AbstractCrystalItem crystalItem) {
                                    itemStack.getOrCreateTag().putString(sword.getCRYSTAL_SLOT_2(),
                                            ForgeRegistries.ITEMS.getKey(crystalItem).toString());
                                    entity.getItemStackHandler().extractItem(2, 1, false);
                                }
                            }
                        case 1:
                            if (itemStack.getOrCreateTag().getString(sword.getCRYSTAL_SLOT_1()).equals("empty")) {
                                if (entity.getItemStackHandler().getStackInSlot(1).getItem() instanceof AbstractCrystalItem crystalItem) {
                                    itemStack.getOrCreateTag().putString(sword.getCRYSTAL_SLOT_1(),
                                            ForgeRegistries.ITEMS.getKey(crystalItem).toString());
                                    entity.getItemStackHandler().extractItem(1, 1, false);
                                }
                            }
                            break;
                    }
                }
            }
        });

        return true;
    }
}
