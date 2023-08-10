package net.frozencheesecat.testmod.entity.client;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class TigerInventoryProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<TigerInventory> TIGER_INVENTORY = CapabilityManager.get(new CapabilityToken<TigerInventory>() {});
    
    private TigerInventory inventory = null;
    private final LazyOptional<TigerInventory> optional = LazyOptional.of(this::createInventory);

    private TigerInventory createInventory() {
        if (this.inventory == null) {
            this.inventory = new TigerInventory();
        }

        return this.inventory;
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return ForgeCapabilities.ITEM_HANDLER.orEmpty(cap, optional.cast());
    }



    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createInventory().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createInventory().loadNBTData(nbt);
    }

    
    
}
