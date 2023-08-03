package com.wollit.jellymod.network;

import com.wollit.jellymod.blocks.crystal_assembler.CrystalAssemblerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketCraftItemC2S {

    private final BlockPos pos;

    public PacketCraftItemC2S(BlockEntity blockEntity) {
        pos = blockEntity.getBlockPos();
    }

    public PacketCraftItemC2S(FriendlyByteBuf buf) {
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

            if (blockEntity instanceof CrystalAssemblerBlockEntity entity) {
                CrystalAssemblerBlockEntity.craftItem(entity);
            }
        });

        return true;
    }
}
