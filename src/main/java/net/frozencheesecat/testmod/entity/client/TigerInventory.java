package net.frozencheesecat.testmod.entity.client;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;

public class TigerInventory implements IItemHandler {

    private List<ItemStack> inventory = new ArrayList<ItemStack>();
    private int slotCount = 9; //default slot numbers

    public TigerInventory() {
        for (int i = 0; i < slotCount; i++)
            inventory.add(ItemStack.EMPTY);
    }
    
    public TigerInventory(int slotCount) {
        for (int i = 0; i < slotCount; i++)
            inventory.add(ItemStack.EMPTY);
    }

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

    @Override
    public int getSlots() {
        if (inventory.isEmpty()) {
            return -1;
        }

        return inventory.size();
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        if (!(slot >= 0 && slot < inventory.size())) 
            return ItemStack.EMPTY;
        
        return inventory.get(slot);
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (!simulate) {
            ItemStack insertSlot = inventory.get(slot);
            boolean isEmpty = false;
            //Item slot is empty
            if (insertSlot.isEmpty()) {
                isEmpty = true;
            }
            //Item slot is not the same item
            if (!insertSlot.sameItem(stack)) {
                
                //check can insert all to the same slot
                int notTaken = stack.getCount() - insertSlot.getMaxStackSize();

                if (insertSlot.getMaxStackSize() < stack.getCount()) {
                    insertSlot = new ItemStack(stack.getItem(), insertSlot.getMaxStackSize(), stack.getTag());
                }
                else {
                    insertSlot = stack;
                }

                //handle return
                if (isEmpty) {
                    return ItemStack.EMPTY;
                }
                else {
                    return new ItemStack(stack.getItem(), notTaken, stack.getTag());
                }
                

            }
                //Item slot has the same
            else {

                int fullStack = insertSlot.getCount() + stack.getCount();

                if (fullStack > insertSlot.getMaxStackSize()) {
                    insertSlot = new ItemStack(stack.getItem(), fullStack, null)
                }

            }


            


        }
        else {

        }
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'extractItem'");
    }

    @Override
    public int getSlotLimit(int slot) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSlotLimit'");
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isItemValid'");
    }

}
