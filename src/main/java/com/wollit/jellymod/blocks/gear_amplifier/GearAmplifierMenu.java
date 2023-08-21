package com.wollit.jellymod.blocks.gear_amplifier;

import com.wollit.jellymod.blocks.JellyBlocks;
import com.wollit.jellymod.blocks.JellyMenuTypes;
import com.wollit.jellymod.items.weapons.AbstractMagicSword;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class GearAmplifierMenu extends AbstractContainerMenu {

    public final GearAmplifierBlockEntity blockEntity;
    private final Level level;

    public GearAmplifierMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
        this(id, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(5));
    }

    public GearAmplifierMenu(int id, Inventory inventory, BlockEntity blockEntity, ContainerData data) {
        super(JellyMenuTypes.GEAR_AMPLIFIER_MENU.get(), id);
        checkContainerSize(inventory, 5);
        this.blockEntity = (GearAmplifierBlockEntity) blockEntity;
        this.level = inventory.player.level;

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {

            this.addSlot(new SlotItemHandler(handler, 0, 80, 32) {
                @Override
                public boolean isActive() {
                    return super.isActive();
                }

                @Override
                public boolean mayPickup(Player playerIn) {
                    if (handler.getStackInSlot(1).getItem() != Items.AIR) {
                        return false;
                    } else if (handler.getStackInSlot(2).getItem() != Items.AIR) {
                        return false;
                    } else if (handler.getStackInSlot(3).getItem() != Items.AIR) {
                        return false;
                    } else if (handler.getStackInSlot(4).getItem() != Items.AIR) {
                        return false;
                    } else {
                        return super.mayPickup(playerIn);
                    }
                }
            });

            this.addSlot(new SlotItemHandler(handler, 1, 123, 48) {
                @Override
                public boolean isActive() {
                    if (handler.getStackInSlot(0).getItem() instanceof AbstractMagicSword sword) {
                        return handler.getStackInSlot(0).getOrCreateTag().getInt(sword.getCRYSTAL_SLOTS_TAG()) >= 1 && handler.getStackInSlot(0).getOrCreateTag().getString(sword.getCRYSTAL_SLOT_1()).equals("empty");
                    } else {
                        return false;
                    }
                }

                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if (handler.getStackInSlot(0).getItem() instanceof AbstractMagicSword sword) {
                        return handler.getStackInSlot(0).getOrCreateTag().getInt(sword.getCRYSTAL_SLOTS_TAG()) >= 1 && handler.getStackInSlot(0).getOrCreateTag().getString(sword.getCRYSTAL_SLOT_1()).equals("empty");
                    } else {
                        return false;
                    }
                }
            });

            this.addSlot(new SlotItemHandler(handler, 2, 37, 48) {
                @Override
                public boolean isActive() {
                    if (handler.getStackInSlot(0).getItem() instanceof AbstractMagicSword sword) {
                        return handler.getStackInSlot(0).getOrCreateTag().getInt(sword.getCRYSTAL_SLOTS_TAG()) >= 2 && handler.getStackInSlot(0).getOrCreateTag().getString(sword.getCRYSTAL_SLOT_2()).equals("empty");
                    } else {
                        return false;
                    }
                }

                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if (handler.getStackInSlot(0).getItem() instanceof AbstractMagicSword sword) {
                        return handler.getStackInSlot(0).getOrCreateTag().getInt(sword.getCRYSTAL_SLOTS_TAG()) >= 2 && handler.getStackInSlot(0).getOrCreateTag().getString(sword.getCRYSTAL_SLOT_2()).equals("empty");
                    } else {
                        return false;
                    }
                }
            });

            this.addSlot(new SlotItemHandler(handler, 3, 123, 16) {
                @Override
                public boolean isActive() {
                    if (handler.getStackInSlot(0).getItem() instanceof AbstractMagicSword sword) {
                        return handler.getStackInSlot(0).getOrCreateTag().getInt(sword.getCRYSTAL_SLOTS_TAG()) >= 3 && handler.getStackInSlot(0).getOrCreateTag().getString(sword.getCRYSTAL_SLOT_3()).equals("empty");
                    } else {
                        return false;
                    }
                }

                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if (handler.getStackInSlot(0).getItem() instanceof AbstractMagicSword sword) {
                        return handler.getStackInSlot(0).getOrCreateTag().getInt(sword.getCRYSTAL_SLOTS_TAG()) >= 3 && handler.getStackInSlot(0).getOrCreateTag().getString(sword.getCRYSTAL_SLOT_3()).equals("empty");
                    } else {
                        return false;
                    }
                }
            });

            this.addSlot(new SlotItemHandler(handler, 4, 37, 16) {
                @Override
                public boolean isActive() {
                    if (handler.getStackInSlot(0).getItem() instanceof AbstractMagicSword sword) {
                        return handler.getStackInSlot(0).getOrCreateTag().getInt(sword.getCRYSTAL_SLOTS_TAG()) >= 4 && handler.getStackInSlot(0).getOrCreateTag().getString(sword.getCRYSTAL_SLOT_4()).equals("empty");
                    } else {
                        return false;
                    }
                }

                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    if (handler.getStackInSlot(0).getItem() instanceof AbstractMagicSword sword) {
                        return handler.getStackInSlot(0).getOrCreateTag().getInt(sword.getCRYSTAL_SLOTS_TAG()) >= 4 && handler.getStackInSlot(0).getOrCreateTag().getString(sword.getCRYSTAL_SLOT_4()).equals("empty");
                    } else {
                        return false;
                    }
                }
            });

        });
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 5;  // must be the number of slots you have!

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, JellyBlocks.GEAR_AMPLIFIER.get());
    }

    private void addPlayerInventory(Inventory inventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 144));
        }
    }
}
