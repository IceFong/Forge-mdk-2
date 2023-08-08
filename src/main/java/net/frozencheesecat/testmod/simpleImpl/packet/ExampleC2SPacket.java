package net.frozencheesecat.testmod.simpleImpl.packet;
import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkEvent;

public class ExampleC2SPacket {
    public ExampleC2SPacket() {
    }
    
    public ExampleC2SPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //HERE WE ARE ON THE SERVER
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            EntityType.COW.spawn(level, player.blockPosition(), MobSpawnType.COMMAND);
            // System.out.println("summon");
            // .spawn(level, null, null, player.blockPosition(),
            //     MobSpawnType.COMMAND, true, false);

        });
    }

}
