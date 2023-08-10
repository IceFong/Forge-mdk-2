package net.frozencheesecat.testmod.event;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.entity.ModEntities;
import net.frozencheesecat.testmod.entity.client.TigerInventory;
import net.frozencheesecat.testmod.entity.client.TigerInventoryProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TestMod.MODID)
public class CapabilityEvent {
    
    @SubscribeEvent
    public static void onAttachCapabilityInventory(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();

        if (entity.getType() == ModEntities.TIGER.get()) {
            // System.out.println("Add Capability");
            // event.addCapability(new ResourceLocation(TestMod.MODID, "tiger_inventory"), new tiger_inventory(entity.getType(), null, 1));
            if (!entity.getCapability(TigerInventoryProvider.TIGER_INVENTORY).isPresent()) {
                event.addCapability(new ResourceLocation(TestMod.MODID, "properties"), new TigerInventoryProvider());
            }
        }
    }


    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(TigerInventory.class);
    }

    @SubscribeEvent
    public static void onTigerInventoryTick(TickEvent.ServerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            event.getServer().
        }
    }


}
