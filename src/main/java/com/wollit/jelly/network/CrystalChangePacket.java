package com.wollit.jelly.network;

import com.wollit.jelly.blocks.entity.IdentificationStationBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

public class CrystalChangePacket {

    private final BlockPos pos;
    private final CompoundTag nbt;

    public CrystalChangePacket(BlockEntity entity, CompoundTag nbt) {
        this.pos = entity.getBlockPos();
        this.nbt = nbt;
    }

    public CrystalChangePacket(FriendlyByteBuf buf) {
        this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        this.nbt = buf.readNbt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(pos.getX()).writeInt(pos.getY()).writeInt(pos.getZ());
        buf.writeNbt(this.nbt);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            ServerLevel world = Objects.requireNonNull(ctx.getSender()).getLevel();
            BlockEntity entity = world.getBlockEntity(pos);
            if (entity instanceof IdentificationStationBlockEntity) {
                ItemStack itemStack = ((IdentificationStationBlockEntity) entity).itemHandler.getStackInSlot(0);
                if (itemStack.hasTag()) {
                    Random rand = new Random();
                    int crystals = rand.nextInt(5) + 1;
                    itemStack.getOrCreateTag().putInt("magic_sword.crystals", crystals);
                }
            }
        });


        return true;
    }
}