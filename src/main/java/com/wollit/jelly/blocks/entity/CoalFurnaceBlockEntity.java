package com.wollit.jelly.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CoalFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    public CoalFurnaceBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityType.FURNACE, blockPos, blockState, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName() {
        return null;
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
        return new FurnaceMenu(p_58627_, p_58628_, this, this.dataAccess);
    }
}
