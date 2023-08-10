package net.frozencheesecat.testmod.entity.client;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.item.ItemStack;
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
        
        if (inventory.get(slot) == ItemStack.EMPTY) {
            
        }
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertItem'");
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
