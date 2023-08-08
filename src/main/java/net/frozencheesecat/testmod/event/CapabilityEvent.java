package net.frozencheesecat.testmod.event;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.entity.ModEntities;
import net.frozencheesecat.testmod.entity.client.TigerInventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityEvent {
    
    @SubscribeEvent
    public static void EntityInventory(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();

        if (entity.getType() == ModEntities.TIGER.get()) {
            System.out.println("Add Capability");
            event.addCapability(new ResourceLocation(TestMod.MODID, "tiger_inventory"), new TigerInventory(entity.getType(), null, 1));
        }
    }

}
