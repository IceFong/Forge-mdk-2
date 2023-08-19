package net.frozencheesecat.testmod.blockentity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.frozencheesecat.testmod.simpleImpl.TestModPacketHandler;
import net.frozencheesecat.testmod.simpleImpl.packet.ItemStackC2SPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class FirstBlockEntity extends BlockEntity implements MenuProvider {

    private int progress = 0;
    private final int maxProgress = 80;

    private CRAFT_STATE crafState = CRAFT_STATE.IDLE;

    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int i) {
            setChanged();
            if (!level.isClientSide && level != null) {
                TestModPacketHandler.sendToPlayers(new ItemStackC2SPacket(this, worldPosition));
            }
        }
    };

    public FirstBlockEntity(BlockPos pos, BlockState state) {
        super(RegisteryOfBlockEntities.FIRST_BLOCK_ENTITY.get(), pos, state);
    }


    //STORE DATA
    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        //write here
        itemHandler.deserializeNBT(nbt.getCompound("FBE_INVENTORY"));
        progress = nbt.getInt("FBE.progress");
        //
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        //write here
        nbt.put("FBE_ITEMHANDLER", itemHandler.serializeNBT());
        nbt.putInt("FBE.progress", this.progress);
        //
        super.saveAdditional(nbt);
    }
    /////
    

    //SYNCHRONIZE DATA TO CLIENT
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = new CompoundTag();

        nbt.put("FBE_ITEMHANDLER", itemHandler.serializeNBT());
        nbt.putInt("FBE.progress", this.progress);

        return nbt;
    }

    @Override
    public void handleUpdateTag(CompoundTag nbt) {
        super.handleUpdateTag(nbt);

        itemHandler.deserializeNBT(nbt.getCompound("FBE_INVENTORY"));
        progress = nbt.getInt("FBE.progress");
    }
    /////

    //DO PROCESSES
    public static void tick(Level level, BlockPos pos, BlockState state, FirstBlockEntity blockEntity) {
        
        if (level.isClientSide) {
            return;
        }

        CRAFTING_ITEM crafting = hasRecipe(blockEntity);
        CRAFT_STATE craftState = blockEntity.getCraftState();
        
        //Do check
        //1. idle has put something in
        if (craftState == CRAFT_STATE.IDLE && crafting != CRAFTING_ITEM.NONE) {
            blockEntity.setCraftState(CRAFT_STATE.CRAFTING);
            return;
        }
        //2. crafting something, not interrupted
        else if (craftState == CRAFT_STATE.CRAFTING) {
            if (crafting == CRAFTING_ITEM.NONE) {
                blockEntity.setCraftState(CRAFT_STATE.IDLE);
                blockEntity.resetProgress();
                return;
            }

            if (!canInsertToOutput(blockEntity.itemHandler, getProduct(crafting))) {
                blockEntity.setCraftState(CRAFT_STATE.IDLE);
                blockEntity.resetProgress();
                return;
            }
            
            blockEntity.progress++;
            if (blockEntity.progress >= blockEntity.maxProgress) {
                blockEntity.setCraftState(CRAFT_STATE.DONE);
            }
        }
        //3. done
        else if (craftState == CRAFT_STATE.DONE) {

            if (!canInsertToOutput(blockEntity.itemHandler, getProduct(crafting))) {
                blockEntity.setCraftState(CRAFT_STATE.IDLE);
                blockEntity.resetProgress();
                return;
            }

            ItemStackHandler newItemHandler = InsertAndGetResult(blockEntity, getProduct(crafting));
            for (int i = 0; i < newItemHandler.getSlots(); i++) {
                blockEntity.itemHandler.setStackInSlot(i, newItemHandler.getStackInSlot(i));
            }
            blockEntity.setCraftState(CRAFT_STATE.IDLE);

        }

    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static ItemStackHandler InsertAndGetResult(FirstBlockEntity blockEntity, List<ItemStack> inputList) {
        
        ItemStackHandler inventory = blockEntity.itemHandler;

        for (int i = 0; i < inputList.size(); i++) {
            int outputSlotsCount = 4;
            for (int j = 1; j <= outputSlotsCount; j++) {
                inputList.set(i, inventory.insertItem(j, inputList.get(i), false));
            }
        }
        
        //needs modify
        inventory.extractItem(0, 1, false);
        /////
        return inventory;

    }


    private static CRAFTING_ITEM hasRecipe(FirstBlockEntity blockEntity) {
        
        ItemStackHandler inventory = blockEntity.itemHandler;
        Item item = inventory.getStackInSlot(0).getItem();

        if (item == Items.OAK_WOOD) 
            return CRAFTING_ITEM.OAK_PLANK;
        if (item == Items.DARK_OAK_WOOD) 
            return CRAFTING_ITEM.DARK_OAK_PLANK;

        
        return CRAFTING_ITEM.NONE;
        
    }

    private static boolean canInsertToOutput(ItemStackHandler inventory, List<ItemStack> inputList) {

        int outputSlotsCount = 4;
        for (int i = 0; i < inputList.size(); i++) {

            // ItemStack leftOver = inventory.getStackInSlot(i);
            for (int j = 1; j <= outputSlotsCount; j++) {
                inputList.set(i, inventory.insertItem(j, inputList.get(i), true));

                if (inputList.get(i).getCount() == 0) {
                    break;
                }
            }

            if (inputList.get(i).getCount() != 0) {
                return false;
            }

        }

        return true;
        
    }


    private static List<ItemStack> getProduct(CRAFTING_ITEM crafting) {

        List<ItemStack> returnList = new ArrayList<ItemStack>();
        int woodCount = 6;

        switch(crafting) {
            case OAK_PLANK -> returnList.add(new ItemStack(Items.OAK_PLANKS, woodCount));
            case DARK_OAK_PLANK -> returnList.add(new ItemStack(Items.DARK_OAK_PLANKS, woodCount));
        }

        return returnList;

    }
    
    enum CRAFTING_ITEM {
       OAK_PLANK,
       DARK_OAK_PLANK,
       NONE; 
    }

    enum CRAFT_STATE {
        IDLE,
        CRAFTING,
        DONE
    }

    public CRAFT_STATE getCraftState() {
        return crafState;
    }

    private void setCraftState(CRAFT_STATE crafting) {
        this.crafState = crafting;
    }

    //Menu
    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createMenu'");
    }


    @Override
    public Component getDisplayName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDisplayName'");
    }
    /////

    //Renderer
    public ItemStack getRendererStack() {
        return itemHandler.getStackInSlot(0);
    }

    public void setHandler(ItemStackHandler itemStackHandler) {
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            itemHandler.setStackInSlot(i, itemStackHandler.getStackInSlot(i));
        }
    }
    /////

}
