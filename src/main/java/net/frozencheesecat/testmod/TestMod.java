
package net.frozencheesecat.testmod;

import net.frozencheesecat.testmod.block.ModBlock;
import net.frozencheesecat.testmod.entity.ModEntities;
import net.frozencheesecat.testmod.entity.client.TigerRenderer;
import net.frozencheesecat.testmod.item.ModCreativeTab;
import net.frozencheesecat.testmod.item.ModItem;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
        
        ModEntities.register(modEventBus);

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

            // event.accept(ModItem.ZIRCON);
            // event.accept(ModItem.RAW_ZIRCON);
            event.accept(ModItem.RAW_BLACK_OPAL);
            event.accept(ModItem.BLACK_OPAL);
            event.accept(ModItem.TIGER_SPAWN_EGG);

            event.accept(ModBlock.BLACK_OPAL_BLOCK);
            event.accept(ModBlock.BLACK_OPAL_ORE);
            event.accept(ModBlock.DEEPSLATE_BLACK_OPAL_ORE);
            event.accept(ModBlock.NETHERRACK_BLACK_OPAL_ORE);
            event.accept(ModBlock.ENDSTONE_BLACK_OPAL_ORE);

        }

        if (event.getTab() == ModCreativeTab.TEST_TAB_2) {

            event.accept(ModBlock.EBONY_LOG);
            event.accept(ModBlock.EBONY_WOOD);
            event.accept(ModBlock.STRIPPED_EBONY_LOG);
            event.accept(ModBlock.STRIPPED_EBONY_WOOD);
            event.accept(ModBlock.EBONY_PLANKS);
            event.accept(ModBlock.EBONY_LEAVES);
            event.accept(ModBlock.EBONY_SAPLING);
        
        }
            
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.TIGER.get(), TigerRenderer::new);
        }
    }
}
