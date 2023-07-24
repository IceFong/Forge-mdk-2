package net.frozencheesecat.testmod.datagen;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.block.ModBlock;
import net.frozencheesecat.testmod.item.ModItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TestMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItem.BLACK_OPAL);
        simpleItem(ModItem.RAW_BLACK_OPAL);
        saplingItem(ModBlock.EBONY_SAPLING);

        withExistingParent(ModItem.TIGER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(), 
            new ResourceLocation("item/generated")).texture("layer0", 
            new ResourceLocation(TestMod.MODID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
            new ResourceLocation("item/generated")).texture("layer0",
            new ResourceLocation(TestMod.MODID, "item/" + item.getId().getPath())
        );
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
            new ResourceLocation("item/handheld")).texture("layer0",
            new ResourceLocation(TestMod.MODID, "item/" + item.getId().getPath())
        );
    }
    
}
