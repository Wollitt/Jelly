package com.wollit.jelly.blocks.entity.custom;

import com.wollit.jelly.blocks.entity.ModBlockEntities;
import com.wollit.jelly.recipe.BasicAlloyFurnaceRecipe;
import com.wollit.jelly.screen.basic_alloy_furnace.BasicAlloyFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BasicAlloyFurnaceBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 0;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public BasicAlloyFurnaceBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.BASIC_ALLOY_FURNACE_ENTITY.get(), blockPos, blockState);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BasicAlloyFurnaceBlockEntity.this.progress;
                    case 1 -> BasicAlloyFurnaceBlockEntity.this.maxProgress;
                    case 2 -> BasicAlloyFurnaceBlockEntity.this.fuelTime;
                    case 3 -> BasicAlloyFurnaceBlockEntity.this.maxFuelTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BasicAlloyFurnaceBlockEntity.this.progress = value;
                    case 1 -> BasicAlloyFurnaceBlockEntity.this.maxProgress = value;
                    case 2 -> BasicAlloyFurnaceBlockEntity.this.fuelTime = value;
                    case 3 -> BasicAlloyFurnaceBlockEntity.this.maxFuelTime = value;
                }

            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Basic Alloy Furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new BasicAlloyFurnaceMenu(containerId, inventory, this, this.data);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("basic_alloy_furnace.progress", progress);
        tag.putInt("basic_alloy_furnace.fuelTime", fuelTime);
        tag.putInt("basic_alloy_furnace.maxFuelTime", maxFuelTime);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("basic_alloy_furnace.progress");
        fuelTime = nbt.getInt("basic_alloy_furnace.fuelTime");
        maxFuelTime = nbt.getInt("basic_alloy_furnace.maxFuelTime");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i< itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, BasicAlloyFurnaceBlockEntity blockEntity) {
        if(hasRecipe(blockEntity)) {
            blockEntity.progress++;
            setChanged(level, blockPos, blockState);
            if(blockEntity.progress > blockEntity.maxProgress) {
                craftItem(blockEntity);
            }
        } else {
            blockEntity.resetProgress();
            setChanged(level, blockPos, blockState);
        }
    }

    private static void craftItem(BasicAlloyFurnaceBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); ++i) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<BasicAlloyFurnaceRecipe> match = level.getRecipeManager().getRecipeFor(BasicAlloyFurnaceRecipe.Type.INSTANCE, inventory, level);

        if (match.isPresent()) {
            entity.itemHandler.extractItem(0, 1, false);

            entity.itemHandler.setStackInSlot(1, new ItemStack(match.get().getResultItem().getItem(), entity.itemHandler.getStackInSlot(1).getCount() + 1));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(BasicAlloyFurnaceBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); ++i) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<BasicAlloyFurnaceRecipe> match = level.getRecipeManager().getRecipeFor(BasicAlloyFurnaceRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }

    private static boolean hasFuel(BasicAlloyFurnaceBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(2).getItem() == Items.COAL ||
                entity.itemHandler.getStackInSlot(2).getItem() == Items.CHARCOAL ||
                entity.itemHandler.getStackInSlot(2).getItem() == Items.COAL_BLOCK.asItem() ||
                entity.itemHandler.getStackInSlot(2).getItem() == Items.LAVA_BUCKET;
    }

    private static boolean hasFuelInFuelSlot(BasicAlloyFurnaceBlockEntity entity) {
        return !entity.itemHandler.getStackInSlot(2).isEmpty();
    }

    private static boolean isConsumingFuel(BasicAlloyFurnaceBlockEntity entity) {
        return entity.fuelTime > 0;
    }

    private void consumeFuel() {
        if (!itemHandler.getStackInSlot(2).isEmpty()) {
            this.fuelTime = ForgeHooks.getBurnTime(this.itemHandler.extractItem(2, 1, false), RecipeType.SMELTING);
            this.maxFuelTime = this.fuelTime;
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(1).getItem() == output.getItem() || inventory.getItem(1).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(1).getMaxStackSize() > inventory.getItem(1).getCount();
    }
}
