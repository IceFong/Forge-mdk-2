package net.frozencheesecat.testmod.event;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.entity.ModEntities;
import net.frozencheesecat.testmod.entity.custom.TigerEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.TIGER.get(), TigerEntity.setAttributes());
    }

}
