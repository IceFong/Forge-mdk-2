package net.frozencheesecat.testmod.event;

import org.lwjgl.glfw.GLFW;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.simpleImpl.TestModPacketHandler;
import net.frozencheesecat.testmod.simpleImpl.packet.ExampleC2SPacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvent {
    
    @Mod.EventBusSubscriber(modid = TestMod.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (event.getKey() == GLFW.GLFW_KEY_Z && event.getAction() == GLFW.GLFW_PRESS) // input key z
            {
                // System.out.println("send");
                TestModPacketHandler.sendToServer(new ExampleC2SPacket());
            }
        }
    }

}
