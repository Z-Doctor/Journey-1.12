package net.slayer.api.block;

import net.journey.JourneyTabs;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;

public class BlockModSlab extends BlockMod {

	public boolean isFull;
    protected static final AxisAlignedBB AABB_BOTTOM_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

	public BlockModSlab(boolean full, String name, String finalName) {
		super(name, finalName, 1.0F);
		isFull = full;
		setCreativeTab(JourneyTabs.decoration);
		setSoundType(EnumMaterialTypes.WOOD.getSound());
		setHardness(1.0F);
		this.useNeighborBrightness = true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (this.isFull) {
			return FULL_BLOCK_AABB;
		} else {
			return AABB_BOTTOM_HALF;
		}
	}
}