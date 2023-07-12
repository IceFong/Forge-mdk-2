
package net.frozencheesecat.testmod;

import net.frozencheesecat.testmod.block.ModBlock;
import net.frozencheesecat.testmod.item.ModCreativeTab;
import net.frozencheesecat.testmod.item.ModItem;
// import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
// import com.mojang.logging.LogUtils;
// import net.minecraft.client.Minecraft;
// import net.minecraft.world.item.BlockItem;
// import net.minecraft.world.item.CreativeModeTabs;
// import net.minecraft.world.item.Item;
// import net.minecraft.world.level.block.Block;
// import net.minecraft.world.level.block.Blocks;
// import net.minecraft.world.level.block.state.BlockBehaviour;
// import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
// import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
// import net.minecraftforge.registries.DeferredRegister;
// import net.minecraftforge.registries.ForgeRegistries;
// import net.minecraftforge.registries.RegistryObject;
// import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TestMod.MODID)
public class TestMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "testmod";
    // Directly reference a slf4j logger
    // private static final Logger LOGGER = LogUtils.getLogger();
   

    public TestMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItem.register(modEventBus);
        ModBlock.register(modEventBus);
        

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        if (event.getTab() == ModCreativeTab.TEST_TAB) {

            event.accept(ModItem.ZIRCON);
            event.accept(ModItem.RAW_ZIRCON);

            event.accept(ModBlock.BLACK_OPAL_BLOCK);
            event.accept(ModBlock.BLACK_OPAL_ORE);
            event.accept(ModBlock.DEEPSLATE_BLACK_OPAL_ORE);
            event.accept(ModBlock.NETHERRACK_BLACK_OPAL_ORE);
            event.accept(ModBlock.ENDSTONE_BLACK_OPAL_ORE);

        }

        if (event.getTab() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItem.ZIRCON);
        }
            
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
        }
    }
}
