package com.wollit.jellymod.network;

import com.wollit.jellymod.blocks.identification_table.IdentificationTableBlockEntity;
import com.wollit.jellymod.items.weapons.AbstractMagicSword;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

public class PacketIdentifyItemC2S {

    private final BlockPos pos;

    public PacketIdentifyItemC2S(BlockEntity blockEntity) {
        pos = blockEntity.getBlockPos();
    }

    public PacketIdentifyItemC2S(FriendlyByteBuf buf) {
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
            if (blockEntity instanceof IdentificationTableBlockEntity) {
                ItemStack itemStack = ((IdentificationTableBlockEntity) blockEntity).getItemStackHandler().getStackInSlot(0);
                if (itemStack.getItem() instanceof AbstractMagicSword sword) {
                    if (!itemStack.getOrCreateTag().getBoolean("identification_status_tag")) {
                        int slots;
                        Random rand = new Random();
                        int quality = rand.nextInt(1000) + 1;
                        if (quality <= 500) {
                            slots = 0;
                        } else if (quality <= 800) {
                            slots = 1;
                        } else if (quality <= 950) {
                            slots = 2;
                        } else if (quality < 990) {
                            slots = 3;
                        } else {
                            slots = 4;
                        }


                        itemStack.getOrCreateTag().putBoolean(sword.getIDENTIFICATION_STATUS_TAG(), true);
                        itemStack.getOrCreateTag().putInt(sword.getCRYSTAL_SLOTS_TAG(), slots);
                        switch (slots) {
                            case 4:
                                itemStack.getOrCreateTag().putString(sword.getCRYSTAL_SLOT_4(), "empty");
                            case 3:
                                itemStack.getOrCreateTag().putString(sword.getCRYSTAL_SLOT_3(), "empty");
                            case 2:
                                itemStack.getOrCreateTag().putString(sword.getCRYSTAL_SLOT_2(), "empty");
                            case 1:
                                itemStack.getOrCreateTag().putString(sword.getCRYSTAL_SLOT_1(), "empty");
                                break;
                        }
                    }
                }
            }
        });

        return true;
    }
}
