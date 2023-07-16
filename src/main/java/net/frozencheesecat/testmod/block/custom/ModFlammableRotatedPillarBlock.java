package net.frozencheesecat.testmod.block.custom;

// import java.util.HashMap;
// import java.util.Map;

import javax.annotation.Nullable;

import net.frozencheesecat.testmod.block.ModBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {

    public ModFlammableRotatedPillarBlock(Properties properties) {
        super(properties);
    }
    
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
        return 5;
    }
    
    // Map<Block, BlockState> StrippedWoodMap = new HashMap<>();
    @Override
    public @Nullable BlockState getToolModifiedState (BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        // return super.getToolModifiedState(state, context, toolAction, simulate);

        if (context.getItemInHand().getItem() instanceof AxeItem) {
            if (state.is(ModBlock.EBONY_LOG.get())) {
                return ModBlock.STRIPPED_EBONY_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(ModBlock.EBONY_WOOD.get())) {
                return ModBlock.STRIPPED_EBONY_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

}
