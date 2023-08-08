package net.frozencheesecat.testmod.simpleImpl;

import java.util.Optional;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.simpleImpl.packet.ExampleC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class TestModPacketHandler {
    
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        INSTANCE = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(TestMod.MODID, "messages"))
            .networkProtocolVersion(() -> "1.0")
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .simpleChannel();

        // INSTANCE.messageBuilder(ExampleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
        //     .decoder(ExampleC2SPacket::new)
        //     .encoder(ExampleC2SPacket::toBytes)
        //     .consumerMainThread(ExampleC2SPacket::handle)
        //     .add();
        
        INSTANCE.registerMessage(
            id(),
            ExampleC2SPacket.class,
            ExampleC2SPacket::toBytes,
            ExampleC2SPacket::new,
            ExampleC2SPacket::handle,
            Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }
    
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
     
}
