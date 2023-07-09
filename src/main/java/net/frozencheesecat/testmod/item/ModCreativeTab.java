package net.frozencheesecat.testmod.item;

// import org.apache.logging.log4j.core.config.builder.api.Component;

import net.frozencheesecat.testmod.TestMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.network.chat.Component;

@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTab {
    public static  CreativeModeTab TEST_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        
        TEST_TAB = event.registerCreativeModeTab( new ResourceLocation(TestMod.MODID, "test_tab"),
            builder -> builder.icon(() -> new ItemStack(ModItem.ZIRCON.get()))
                .title( Component.translatable("creativemodetab.test_tab") ));

    }

}