package net.frozencheesecat.testmod.blockentity;

import net.frozencheesecat.testmod.TestMod;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisteryOfBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY =
        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TestMod.MODID);

    public static void register(IEventBus event) {
        BLOCK_ENTITY.register(event);
    }

///////////////////////
///////////////////////
///////////////////////
    public static final RegistryObject<BlockEntityType<FirstBlockEntity>> FIRST_BLOCK_ENTITY = 
        BLOCK_ENTITY.register("first_block_entity", () -> BlockEntityType.Builder.of(FirstBlockEntity::new, Blocks.STONE).build(null));



}
