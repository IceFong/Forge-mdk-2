package net.frozencheesecat.testmod.block;

import java.util.function.Supplier;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.block.custom.ModFlammableRotatedPillarBlock;
import net.frozencheesecat.testmod.item.ModItem;
import net.frozencheesecat.testmod.worldgen.tree.EbonyTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
// import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
// import net.minecraftforge.event.CreativeModeTabEvent.Register;
// import net.minecraftforge.common.CreativeModeTabRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

public class ModBlock {

    public static final DeferredRegister<Block> BLOCKS = 
        DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MODID);

    //CUSTOM BLOCKS
    public static final RegistryObject<Block> BLACK_OPAL_BLOCK = registerBlock("black_opal_block",
        () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
            .strength(6f)
            .requiresCorrectToolForDrops()));
            
    
            
    //ORES
    public static final RegistryObject<Block> BLACK_OPAL_ORE = registerBlock("black_opal_ore",
        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
            .strength(5f).requiresCorrectToolForDrops(),
            UniformInt.of(2,6)));
    public static final RegistryObject<Block> DEEPSLATE_BLACK_OPAL_ORE = registerBlock("deepslate_black_opal_ore",
        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
            .strength(8f).requiresCorrectToolForDrops(),
            UniformInt.of(2,6)));
    public static final RegistryObject<Block> NETHERRACK_BLACK_OPAL_ORE = registerBlock("netherrack_black_opal_ore",
        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
            .strength(4f).requiresCorrectToolForDrops(),
            UniformInt.of(2,6)));
    public static final RegistryObject<Block> ENDSTONE_BLACK_OPAL_ORE = registerBlock("endstone_black_opal_ore",
        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
            .strength(9f).requiresCorrectToolForDrops(),
            UniformInt.of(2,6)));



    //TREE BLOCKS
    public static final RegistryObject<Block> EBONY_LOG = registerBlock("ebony_log",
        () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
            .strength(2f)));
    public static final RegistryObject<Block> EBONY_WOOD = registerBlock("ebony_wood",
        () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
            .strength(2f)));
    public static final RegistryObject<Block> STRIPPED_EBONY_LOG = registerBlock("stripped_ebony_log",
        () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
            .strength(2f)));
    public static final RegistryObject<Block> STRIPPED_EBONY_WOOD = registerBlock("stripped_ebony_wood",
        () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)
            .strength(2f)));

    public static final RegistryObject<Block> EBONY_PLANKS = registerBlock("ebony_planks",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
            .strength(2f)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    // return super.isFlammable(state, level, pos, direction);
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    // return super.getFlammability(state, level, pos, direction);
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    // return super.getFireSpreadSpeed(state, level, pos, direction);
                    return 20;
                }
            });
    public static final RegistryObject<Block> EBONY_LEAVES = registerBlock("ebony_leaves",
        () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
            .strength(0.2f)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    // return super.isFlammable(state, level, pos, direction);
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    // return super.getFlammability(state, level, pos, direction);
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    // return super.getFireSpreadSpeed(state, level, pos, direction);
                    return 60;
                }
            });
    public static final RegistryObject<Block> EBONY_SAPLING = registerBlock("ebony_sapling",
        () -> new SaplingBlock(new EbonyTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItem.ITEMS.register(name, () -> new BlockItem(block.get(),
            new Item.Properties()));
    }

    

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


    @SubscribeEvent
    public void register(RegisterEvent event) {
    event.register(ForgeRegistries.Keys.BLOCKS,
        helper -> {
            helper.register(new ResourceLocation(TestMod.MODID, "example_block_1"), new Block(BlockBehaviour.Properties.of(Material.LAVA, MaterialColor.METAL)));
            // helper.register(new ResourceLocation(TestMod.MODID, "example_block_2"), new Block(null));
            // helper.register(new ResourceLocation(TestMod.MODID, "example_block_3"), new Block(null));
            }
        );
    }

}
