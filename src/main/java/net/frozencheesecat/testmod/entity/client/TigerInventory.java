package net.frozencheesecat.testmod.entity.client;

import net.frozencheesecat.testmod.simpleImpl.TestModPacketHandler;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;

public class TigerInventory extends Entity {

    //networking
    /////
    /////
    /////
    /////

    protected final int size;
    protected int timer;
    protected boolean requiresUpdate;

    protected final ItemStackHandler inventory;
    protected LazyOptional<ItemStackHandler> inventoryHandlerLazyOptional;

    public TigerInventory(EntityType<?> entity, Level level, int size) {
        super(entity, level);
        
        if (size <= 0) {
            size = 1;
        }
        this.size = size;   
        this.inventory = createInventory();
        this.inventoryHandlerLazyOptional = LazyOptional.of(() -> this.inventory);
    }

    private ItemStackHandler createInventory() {
        return new ItemStackHandler(this.size) {
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return super.extractItem(slot, amount, simulate);
            }

            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return ForgeCapabilities.ITEM_HANDLER.orEmpty(cap, inventoryHandlerLazyOptional.cast());
    }

    public LazyOptional<ItemStackHandler> getHandle() {
        return this.inventoryHandlerLazyOptional;
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        inventoryHandlerLazyOptional.invalidate();
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
    }
    
}
