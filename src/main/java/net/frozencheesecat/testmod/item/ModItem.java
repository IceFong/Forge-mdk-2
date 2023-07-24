package net.frozencheesecat.testmod.item;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.entity.ModEntities;
// import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
// import net.minecraftforge.common.CreativeModeTabRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItem {
    // public static final DeferredRegister<Item> ITEMS = 
    //     DeferredRegister.create(ForgeRegistries.ITEMS, testmod.MODID);
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MODID);

    // public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon", 
    //     () -> new Item( new Item.Properties()));
    
    // public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon", 
    //     () -> new Item( new Item.Properties()));

    public static final RegistryObject<Item> RAW_BLACK_OPAL = ITEMS.register( "raw_black_opal",
        () -> new Item( new Item.Properties())
    );
    
    public static final RegistryObject<Item> BLACK_OPAL = ITEMS.register( "black_opal",
        () -> new Item( new Item.Properties())
    );
    
    public static final RegistryObject<Item> TIGER_SPAWN_EGG = ITEMS.register( "tiger_spwan_egg",
        () -> new ForgeSpawnEggItem(ModEntities.TIGER, 0xD57E36, 0x1D0D00, 
            new Item.Properties())
    );



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
