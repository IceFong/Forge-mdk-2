package net.frozencheesecat.testmod.datagen;

import java.util.Set;

import net.frozencheesecat.testmod.block.ModBlock;
import net.frozencheesecat.testmod.item.ModItem;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;


public class ModBlockLootTable extends BlockLootSubProvider {

    public ModBlockLootTable() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlock.BLACK_OPAL_BLOCK.get());

        add(ModBlock.BLACK_OPAL_ORE.get(),
            (block) -> createOreDrop(ModBlock.BLACK_OPAL_ORE.get(), ModItem.RAW_BLACK_OPAL.get())
        );
        add(ModBlock.DEEPSLATE_BLACK_OPAL_ORE.get(),
            (block) -> createOreDrop(ModBlock.DEEPSLATE_BLACK_OPAL_ORE.get(), ModItem.RAW_BLACK_OPAL.get())
        );
        add(ModBlock.NETHERRACK_BLACK_OPAL_ORE.get(),
            (block) -> createOreDrop(ModBlock.NETHERRACK_BLACK_OPAL_ORE.get(), ModItem.RAW_BLACK_OPAL.get())
        );
        add(ModBlock.ENDSTONE_BLACK_OPAL_ORE.get(),
            (block) -> createOreDrop(ModBlock.ENDSTONE_BLACK_OPAL_ORE.get(), ModItem.RAW_BLACK_OPAL.get())
        );

        this.dropSelf(ModBlock.EBONY_LOG.get());
        this.dropSelf(ModBlock.EBONY_WOOD.get());
        this.dropSelf(ModBlock.EBONY_PLANKS.get());
        this.dropSelf(ModBlock.STRIPPED_EBONY_LOG.get());
        this.dropSelf(ModBlock.STRIPPED_EBONY_WOOD.get());
        this.dropSelf(ModBlock.EBONY_SAPLING.get());

        this.add(ModBlock.EBONY_LEAVES.get(),
            (block) -> createLeavesDrops(block, ModBlock.EBONY_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlock.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
    
}
