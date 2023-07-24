package net.frozencheesecat.testmod.item;

// import org.apache.logging.log4j.core.config.builder.api.Component;

import net.frozencheesecat.testmod.TestMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTab {
    public static  CreativeModeTab TEST_TAB;
    public static  CreativeModeTab TEST_TAB_2;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        
        TEST_TAB = event.registerCreativeModeTab( new ResourceLocation(TestMod.MODID, "test_tab"),
            builder -> builder.icon(() -> new ItemStack(ModItem.BLACK_OPAL.get()))
                .title( Component.translatable("creativemodetab.test_tab") ));

        TEST_TAB_2 = event.registerCreativeModeTab( new ResourceLocation(TestMod.MODID, "test_tab_2"),
            builder -> builder.icon(() -> new ItemStack(ModItem.RAW_BLACK_OPAL.get()))
                .title( Component.translatable("creativemodetab.test_tab_2") ));

    }

}