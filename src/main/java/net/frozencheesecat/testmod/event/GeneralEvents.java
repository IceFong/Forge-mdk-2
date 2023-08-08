package net.frozencheesecat.testmod.event;

import java.lang.reflect.Type;

import net.frozencheesecat.testmod.entity.ModEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GeneralEvents {

    private static final Type Entity = null;

    @SubscribeEvent
    public static void playerClickOnEntity(PlayerInteractEvent.EntityInteract event) {

        Entity entity = event.getTarget();
        if (entity.getType() == ModEntities.TIGER.get()) {
            entity.setNoGravity(true);
        }

    }

    @SubscribeEvent
    public static void entityInventory(AttachCapabilitiesEvent<Entity> event) {
        if (event.getGenericType() == Entity)
        event.addCapability(new ResourceLocation("tiger"),
            null);
    }

    

}
 