package net.frozencheesecat.testmod.block.custom;

import javax.annotation.Nullable;

import net.frozencheesecat.testmod.blockentity.FirstBlockEntity;
import net.frozencheesecat.testmod.blockentity.RegisteryOfBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class FirstBlock extends BaseEntityBlock {

    protected FirstBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FirstBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
            BlockEntityType<T> entityType) {
        // // return EntityBlock.super.getTicker(level, state, entityType);
        return createTickerHelper(entityType, RegisteryOfBlockEntities.FIRST_BLOCK_ENTITY.get(), FirstBlockEntity::tick);
        // return entityType == RegisteryOfBlockEntities.FIRST_BLOCK_ENTITY.get() ? (BlockEntityTicker<T>) FirstBlockEntity::tick : null;

    }
    
}
