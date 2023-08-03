package net.frozencheesecat.testmod.event;

import net.frozencheesecat.testmod.entity.ModEntities;
import net.frozencheesecat.testmod.entity.custom.TigerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GeneralEvents {

    @SubscribeEvent
    public static void playerClickOnEntity(PlayerInteractEvent.EntityInteract event) {
        
        EntityType<TigerEntity> Tiger = ModEntities.TIGER.get();
        if (event.getTarget().getType() == Tiger) {
            event.getTarget().kill();
        }
        else {
            System.out.println("not a tiger");
        }
        // ModEntities.TIGER.get();
    }



}
 