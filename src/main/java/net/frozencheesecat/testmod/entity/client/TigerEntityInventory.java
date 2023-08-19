package net.frozencheesecat.testmod.entity.client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.google.common.graph.Network;

import net.frozencheesecat.testmod.entity.ModEntities;
import net.frozencheesecat.testmod.entity.custom.TigerEntity;
import net.frozencheesecat.testmod.screen.TigerInventoryMenu;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;

public class TigerEntityInventory extends TigerEntity implements MenuProvider {
    
    private final ItemStackHandler itemHandler = new ItemStackHandler(9) {};
    private LazyOptional<IItemHandler> lazyItemHandler =  LazyOptional.empty();

    protected final ContainerData data;
    // private static final List<EntityDataAccessor<ItemStack>> dataAccessor = new ArrayList<>(); 
    
    // private CompoundTag personalTag = new CompoundTag();

    public TigerEntityInventory(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                // switch (index) {
                //     case 0 -> GemInfusingStationBlockEntity.this.progress = value;
                //     case 1 -> GemInfusingStationBlockEntity.this.maxProgress = value;
                // }
            }

            @Override
            public int getCount() {
                return 8;
            }
        };

        // for (int i = 0; i < armorDropChances.length; i++) {
        //     dataAccessor.add(SynchedEntityData.defineId(TigerEntityInventory.class, EntityDataSerializers.ITEM_STACK));
        // }
        
    }



    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        return ForgeCapabilities.ITEM_HANDLER.orEmpty(cap, lazyItemHandler.cast());

    }

   

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }


    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());

        super.addAdditionalSaveData(nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.blockPosition(), inventory);
    }

    @Override
    public void remove(RemovalReason reason) {
        drops();
        super.remove(reason);   
    }


    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {

        super.mobInteract(player, hand);

        if (!this.level.isClientSide) {
            // System.out.println(player.getItemInHand(hand).getDisplayName().getString());
            if (player.getItemInHand(hand) == ItemStack.EMPTY) {
                // player.sendSystemMessage(Component.literal("Open Tiger Inventory"));

                NetworkHooks.openScreen((ServerPlayer)player, (MenuProvider) this);
                // NetworkHooks.openScreen((ServerPlayer)player, (MenuProvider) this, dataAccessor);
            }
        }

        return InteractionResult.SUCCESS;
        
    }




    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        // player.sendSystemMessage(Component.literal("Create menu!"));
        // this.dataAccessor.get(id);

        return new TigerInventoryMenu(id, inventory, this.data, this.itemHandler, this);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Tiger Inventory");
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();

        // for (int i = 0; i < 9; i++) {
        //     this.entityData.define(dataAccessor.get(i), itemHandler.getStackInSlot(i));
        // }
    }

    // public ItemStackHandler

    // private List<ItemStack> inventory = new ArrayList<ItemStack>();
    // private int slotCount = 9; //default slot numbers

    // public TigerInventory() {
    //     for (int i = 0; i < slotCount; i++)
    //         inventory.add(ItemStack.EMPTY);
    // }
    
    // public TigerInventory(int slotCount) {
    //     for (int i = 0; i < slotCount; i++)
    //         inventory.add(ItemStack.EMPTY);
    // }

    // public TigerInventory(List<ItemStack> inventory) {
    //     this.inventory = inventory;
    // }

    // public TigerInventory(int slotCount, List<ItemStack> inventory) {
    //     this.slotCount = slotCount;
    //     this.inventory = inventory;
    // } 

    // private int amount;
    // private final int MIN_AMOUNT = 0;
    // private final int MAX_AMOUNT = 64;

    // public int getAmount() {
    //     return amount;
    // }

    // public void addAmount(int num) {
    //     this.amount = Math.min(this.amount + num, MAX_AMOUNT);
    // }

    // public void subAmount(int num) {
    //     this.amount = Math.max(this.amount - num, MIN_AMOUNT);
    // }

    // public void copyFrom(TigerInventory source) {
    //     this.amount = source.amount;
    // }

    // public void saveNBTData(CompoundTag nbt) {
    //     nbt.putInt("amount", amount);
    // }

    // public void loadNBTData(CompoundTag nbt) {
    //     amount = nbt.getInt("amount");
    // }

    // @Override
    // public int getSlots() {
    //     if (inventory.isEmpty()) {
    //         return -1;
    //     }

    //     return inventory.size();
    // }

    // @Override
    // public @NotNull ItemStack getStackInSlot(int slot) {
    //     if (!(slot >= 0 && slot < inventory.size())) 
    //         return ItemStack.EMPTY;
        
    //     return inventory.get(slot);
    // }

    // @Override
    // public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {

    //     ItemStack insertSlot = getStackInSlot(slot);
    //     ItemStack tempSlot = insertSlot.copy();

    //     int totalItems = (insertSlot.sameItem(stack)) ? stack.getCount() + insertSlot.getCount() : stack.getCount(); //Item slot has or hasn't the same item
    //     int totalInsertItems = Math.min(totalItems, insertSlot.getMaxStackSize()); //bound to stack limit
            
    //     insertSlot = new ItemStack(stack.getItem(), totalInsertItems, stack.getTag());

    
    //     if (tempSlot.isEmpty()) {
    //         return ItemStack.EMPTY;
    //     }
    //     else if (ItemStack.isSameItemSameTags(tempSlot, stack)) {
    //         return new ItemStack(tempSlot.getItem(), tempSlot.getCount(), tempSlot.getTag());
    //     }
    //     else {
    //         if (totalItems > totalInsertItems) {
    //             return new ItemStack(stack.getItem(), totalItems - totalInsertItems, stack.getTag());
    //         }
    //         else {
    //             return ItemStack.EMPTY;
    //         }
    //     }

    // }

    // @Override
    // public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        
    //     ItemStack insertSlot = getStackInSlot(slot);
    //     ItemStack tempSlot = insertSlot.copy();

    //     //get all
    //     if (amount >= insertSlot.getCount()) {
    //         insertSlot = ItemStack.EMPTY;
    //         return tempSlot;
    //     }
    //     //get some
    //     else {
    //         insertSlot.setCount(insertSlot.getCount() - amount);
    //         return new ItemStack(insertSlot.getItem(), amount, insertSlot.getTag());
    //     }

    // }

    // @Override
    // public int getSlotLimit(int slot) {
    //     return getStackInSlot(slot).getMaxStackSize();
    // }

    // @Override
    // public boolean isItemValid(int slot, @NotNull ItemStack stack) {
    //     return true;
    // }

    // public TigerInventory copy() {
    //     return new TigerInventory(this.inventory);
    // }

    // public void saveNBTData(CompoundTag nbt) {
    //     nbt.
    // }

    // public void loadNBTData(CompoundTag nbt) {
    //     amount = nbt.getInt("amount");
    // }

}
