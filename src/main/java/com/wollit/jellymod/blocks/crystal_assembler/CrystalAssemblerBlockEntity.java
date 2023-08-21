package com.wollit.jellymod.blocks.crystal_assembler;

import com.wollit.jellymod.blocks.JellyBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CrystalAssemblerBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return super.isItemValid(slot, stack);
        }
    };

    private ItemStackHandler inputSlots;
    private final LazyOptional<IItemHandler> inputSlotHolder = LazyOptional.of(() -> inputSlots);
    private ItemStackHandler outputSlot;
    private final LazyOptional<IItemHandler> outputSlotHolder = LazyOptional.of(() -> outputSlot);

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(inputSlots, outputSlot));

    public CrystalAssemblerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(JellyBlockEntities.CRYSTAL_ASSEMBLER_ENTITY.get(), blockPos, blockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Crystal Assembler");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new CrystalAssemblerMenu(containerId, inventory, this, null);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemStackHandler.serializeNBT());
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());

        for (int i = 0; i < itemStackHandler.getSlots(); ++i) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void craftItem(CrystalAssemblerBlockEntity entity) {
        if (hasRecipe(entity)) {
            Level level = entity.level;
            SimpleContainer inventory = new SimpleContainer(entity.itemStackHandler.getSlots());
            for (int i = 0; i < entity.itemStackHandler.getSlots(); ++i) {
                inventory.setItem(i, entity.itemStackHandler.getStackInSlot(i));
            }

            Optional<CrystalAssemblerRecipe> match = level.getRecipeManager().getRecipeFor(CrystalAssemblerRecipe.Type.INSTANCE, inventory, level);

            if (match.isPresent()) {
                entity.itemStackHandler.extractItem(0, 1, false);
                entity.itemStackHandler.extractItem(1, 1, false);
                entity.itemStackHandler.extractItem(2, 1, false);

                entity.itemStackHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem(null).getItem(), entity.itemStackHandler.getStackInSlot(3).getCount() + 1));
            }
        }
    }

    private static boolean hasRecipe(CrystalAssemblerBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemStackHandler.getSlots());

        for (int i = 0; i < entity.itemStackHandler.getSlots(); ++i) {
            inventory.setItem(i, entity.itemStackHandler.getStackInSlot(i));
        }

        Optional<CrystalAssemblerRecipe> match = level.getRecipeManager().getRecipeFor(CrystalAssemblerRecipe.Type.INSTANCE, inventory, level);

        if (match.isPresent() && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem(level.registryAccess()))) {
            return true;
        }

        return false;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(3).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
    }
}
