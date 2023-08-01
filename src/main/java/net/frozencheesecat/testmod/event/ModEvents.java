package net.frozencheesecat.testmod.event;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.entity.ModEntities;
import net.frozencheesecat.testmod.entity.custom.TigerEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.TIGER.get(), TigerEntity.setAttributes());
    }

    @SubscribeEvent
    public static void entitySpawnRestriction(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.TIGER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }

}
