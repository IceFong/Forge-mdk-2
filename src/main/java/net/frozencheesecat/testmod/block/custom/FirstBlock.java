package net.frozencheesecat.testmod.block.custom;

import javax.annotation.Nullable;

import net.frozencheesecat.testmod.blockentity.RegisteryOfBlockEntities;
import net.frozencheesecat.testmod.blockentity.firstBlock.FirstBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class FirstBlock extends BaseEntityBlock {

    public FirstBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return RegisteryOfBlockEntities.FIRST_BLOCK.get().create(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
            BlockEntityType<T> entityType) {
        // // return EntityBlock.super.getTicker(level, state, entityType);
        return createTickerHelper(entityType, RegisteryOfBlockEntities.FIRST_BLOCK.get(), FirstBlockEntity::tick);
        // return entityType == RegisteryOfBlockEntities.FIRST_BLOCK_ENTITY.get() ? (BlockEntityTicker<T>) FirstBlockEntity::tick : null;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            BlockEntity be = level.getBlockEntity(blockPos);
            if (be instanceof FirstBlockEntity) {

                NetworkHooks.openScreen((ServerPlayer) player, (FirstBlockEntity) be, blockPos);

                return InteractionResult.SUCCESS;
            }

        }

        return super.use(blockState, level, blockPos, player, hand, hitResult);

    }
    
    
}
