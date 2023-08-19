// package net.frozencheesecat.testmod.entity.client;

// import org.jetbrains.annotations.NotNull;
// import org.jetbrains.annotations.Nullable;

// import net.minecraft.core.Direction;
// import net.minecraft.nbt.CompoundTag;
// import net.minecraftforge.common.capabilities.Capability;
// import net.minecraftforge.common.capabilities.CapabilityManager;
// import net.minecraftforge.common.capabilities.CapabilityToken;
// import net.minecraftforge.common.capabilities.ForgeCapabilities;
// import net.minecraftforge.common.capabilities.ICapabilityProvider;
// import net.minecraftforge.common.util.INBTSerializable;
// import net.minecraftforge.common.util.LazyOptional;

// public class TigerInventoryProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

//     public static Capability<TigerEntityInventory> TIGER_INVENTORY = CapabilityManager.get(new CapabilityToken<TigerEntityInventory>() {});
    
//     private TigerEntityInventory inventory = null;
//     private final LazyOptional<TigerInventoryProvider> optional = LazyOptional.of(this::createInventory);

//     private TigerInventoryProvider createInventory() {
//         if (this.inventory == null) {
//             this.inventory = new TigerEntityInventory();
//         }

//         return this.inventory;
//     }

//     @Override
//     public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
//         return ForgeCapabilities.ITEM_HANDLER.orEmpty(cap, optional.cast());
//     }



//     @Override
//     public CompoundTag serializeNBT() {
//         CompoundTag nbt = new CompoundTag();
//         createInventory().(nbt);
//         return nbt;
//     }

//     @Override
//     public void deserializeNBT(CompoundTag nbt) {
//         createInventory().readAdditionalSaveData(nbt);
//     }

    
    
// }
