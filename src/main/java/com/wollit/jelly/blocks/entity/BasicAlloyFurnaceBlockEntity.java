package com.wollit.jelly.blocks.entity;

import com.wollit.jelly.recipe.BasicAlloyFurnaceRecipe;
import com.wollit.jelly.screen.basic_alloy_furnace.BasicAlloyFurnaceMenu;
import com.wollit.jelly.setup.registration.JBlockEntities;
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
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
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

    private ItemStackHandler inputSlots;
    private final LazyOptional<IItemHandler> inputSlotHolder = LazyOptional.of(() -> inputSlots);
    private ItemStackHandler fuelSlot;
    private final LazyOptional<IItemHandler> fuelSlotHolder = LazyOptional.of(() -> fuelSlot);
    private ItemStackHandler outputSlot;
    private final LazyOptional<IItemHandler> outputSlotHolder = LazyOptional.of(() -> outputSlot);

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.of(
            () -> new CombinedInvWrapper(inputSlots, fuelSlot, outputSlot));

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 188;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public BasicAlloyFurnaceBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(JBlockEntities.BASIC_ALLOY_FURNACE_ENTITY.get(), blockPos, blockState);

        inputSlots = new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                setChanged();
            }
        };

        fuelSlot = new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                setChanged();
            }
        };

        outputSlot = new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                setChanged();
            }
        };

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
                    case 0: BasicAlloyFurnaceBlockEntity.this.progress = value; break;
                    case 1: BasicAlloyFurnaceBlockEntity.this.maxProgress = value; break;
                    case 2: BasicAlloyFurnaceBlockEntity.this.fuelTime = value; break;
                    case 3: BasicAlloyFurnaceBlockEntity.this.maxFuelTime = value; break;

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
        if (isConsumingFuel(blockEntity)) {
            blockEntity.fuelTime--;
        }

        if(hasRecipe(blockEntity)) {
            if(hasFuelInFuelSlot(blockEntity) && !isConsumingFuel(blockEntity)) {
                blockEntity.consumeFuel();
                setChanged(level, blockPos, blockState);
            }
            if (isConsumingFuel(blockEntity)) {
                blockEntity.progress++;
                setChanged(level, blockPos, blockState);
                if (blockEntity.progress > blockEntity.maxProgress) {
                    craftItem(blockEntity);
                }
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
        Optional<SmeltingRecipe> match_smelting = level.getRecipeManager().getRecipeFor(BasicAlloyFurnaceRecipe.Type.SMELTING, inventory, level);


        if (match.isPresent()) {
            entity.itemHandler.extractItem(0, 1, false);
            entity.itemHandler.extractItem(1, 1, false);

            entity.itemHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem().getItem(), entity.itemHandler.getStackInSlot(3).getCount() + 1));

            entity.resetProgress();
        }
        else if (match_smelting.isPresent()) {
            if (entity.itemHandler.getStackInSlot(0).getItem() == entity.itemHandler.getStackInSlot(1).getItem()) {
                entity.itemHandler.extractItem(0, 1, false);
                entity.itemHandler.extractItem(1, 1, false);

                entity.itemHandler.setStackInSlot(3, new ItemStack(match_smelting.get().getResultItem().getItem(), entity.itemHandler.getStackInSlot(3).getCount() + 2));
            }
            else {
                if (entity.itemHandler.getStackInSlot(0).isEmpty()) {
                    entity.itemHandler.extractItem(1, 1, false);
                }
                else {
                    entity.itemHandler.extractItem(0, 1, false);
                }

                entity.itemHandler.setStackInSlot(3, new ItemStack(match_smelting.get().getResultItem().getItem(), entity.itemHandler.getStackInSlot(3).getCount() + 1));
            }
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
        Optional<SmeltingRecipe> match_smelting = level.getRecipeManager().getRecipeFor(BasicAlloyFurnaceRecipe.Type.SMELTING, inventory, level);

        if (match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())) {
            return true;
        }
        else if (match_smelting.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match_smelting.get().getResultItem())) {
            return true;
        }
        else {
            return false;
        }
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
        return inventory.getItem(3).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
    }
}
