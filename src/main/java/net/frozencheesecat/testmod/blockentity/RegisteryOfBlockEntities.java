package net.frozencheesecat.testmod.blockentity;

import java.util.function.Supplier;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.block.ModBlock;
import net.frozencheesecat.testmod.block.custom.FirstBlock;
import net.frozencheesecat.testmod.blockentity.firstBlock.FirstBlockEntity;
import net.frozencheesecat.testmod.item.ModCreativeTab;
import net.frozencheesecat.testmod.item.ModItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Mod;

public class RegisteryOfBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY =
        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TestMod.MODID);

    public static void register(IEventBus event) {
        BLOCK_ENTITY.register(event);
    }

///////////////////////
///////////////////////
///////////////////////
    public static final RegistryObject<BlockEntityType<FirstBlockEntity>> FIRST_BLOCK = 
        BLOCK_ENTITY.register("first_block", 
            () -> BlockEntityType.Builder.of(FirstBlockEntity::new, ModBlock.FIRST_BLOCK.get()).build(null));
    // public static final RegistryObject<Block> FIRST_BLOCK_ENTITY = registerBlock("first_block_entity", 
    //         () -> new FirstBlock(BlockBehaviour.Properties.of(Material.STONE)
    //             .strength(6f).requiresCorrectToolForDrops().noCollission()), ModCreativeTab.TEST_TAB);


    // private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
    //     RegistryObject<T> toReturn = ModBlock.BLOCKS.register(name, block);
    //     registerBlockItem(name, toReturn, tab);
    //     return toReturn;
    // }

    // private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
    //                                                                         CreativeModeTab tab) {
    //     return ModItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    // }

}
