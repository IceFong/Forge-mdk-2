package net.frozencheesecat.testmod.datagen;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.block.ModBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TestMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        BlockWithItem(ModBlock.BLACK_OPAL_BLOCK);
        BlockWithItem(ModBlock.BLACK_OPAL_ORE);
        BlockWithItem(ModBlock.DEEPSLATE_BLACK_OPAL_ORE);
        BlockWithItem(ModBlock.NETHERRACK_BLACK_OPAL_ORE);
        BlockWithItem(ModBlock.ENDSTONE_BLACK_OPAL_ORE);

        //TREE BLOCKS
        logBlock(((RotatedPillarBlock) ModBlock.EBONY_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlock.EBONY_WOOD.get()),
            blockTexture(ModBlock.EBONY_LOG.get()),
            blockTexture(ModBlock.EBONY_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlock.STRIPPED_EBONY_LOG.get()),
            new ResourceLocation(TestMod.MODID, "block/stripped_ebony_log"),
            new ResourceLocation(TestMod.MODID, "block/stripped_ebony_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlock.STRIPPED_EBONY_WOOD.get()),
            new ResourceLocation(TestMod.MODID, "block/stripped_ebony_log"),
            new ResourceLocation(TestMod.MODID, "block/stripped_ebony_log"));

        BlockWithItem(ModBlock.EBONY_PLANKS);
        BlockWithItem(ModBlock.EBONY_LEAVES);
        saplingBlock(ModBlock.EBONY_SAPLING);

        simpleBlockItem(ModBlock.EBONY_LOG.get(),
            models().withExistingParent("testmod:ebony_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlock.EBONY_WOOD.get(),
            models().withExistingParent("testmod:ebony_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlock.STRIPPED_EBONY_LOG.get(),
            models().withExistingParent("testmod:stripped_ebony_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlock.STRIPPED_EBONY_WOOD.get(),
            models().withExistingParent("testmod:stripped_ebony_wood", "minecraft:block/cube_column"));
        
    }

    private void BlockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    
    private void saplingBlock( RegistryObject<Block> blockRegistryObject ) {
        simpleBlock(blockRegistryObject.get(),
            models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get()))
                .renderType("cutout"));
    }

}
