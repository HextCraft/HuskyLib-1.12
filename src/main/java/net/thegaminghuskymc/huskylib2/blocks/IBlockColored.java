package net.thegaminghuskymc.huskylib2.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public interface IBlockColored {

    int getColorMultiplier(IBlockState state, @Nullable IBlockAccess access, @Nullable BlockPos pos, int tintIndex);

}
