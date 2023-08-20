package net.frozencheesecat.testmod.simpleImpl.packet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import net.frozencheesecat.testmod.blockentity.firstBlock.FirstBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkEvent;

public class ItemStackC2SPacket {
    private final ItemStackHandler itemStackHandler;
    private final BlockPos pos;

    public ItemStackC2SPacket(ItemStackHandler itemStackHandler, BlockPos pos) {
        this.itemStackHandler = itemStackHandler;
        this.pos = pos;
    }
    
    public ItemStackC2SPacket(FriendlyByteBuf buf) {
        List<ItemStack> list = buf.readList(FriendlyByteBuf::readItem);
        itemStackHandler = new ItemStackHandler(list.size());
    
        for (int i = 0; i < list.size(); i++) {
            itemStackHandler.insertItem(i, list.get(i), false);
        }

        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        List<ItemStack> list = new ArrayList<>();
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            list.add(itemStackHandler.getStackInSlot(i));
        }

        buf.writeCollection(list, FriendlyByteBuf::writeItem);
        buf.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //HERE WE ARE ON THE SERVER
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof FirstBlockEntity blockEntity) {
                blockEntity.setHandler(this.itemStackHandler);
            }
            /////
        });
    }

}
