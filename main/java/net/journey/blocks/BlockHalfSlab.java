package net.journey.blocks;

import java.util.Random;

import net.journey.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

public class BlockHalfSlab extends BlockMod {

    protected static final AxisAlignedBB AABB_BOTTOM_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

	
	public BlockHalfSlab(String name, String f) {
		super(name, f, 1.0F);
		setCreativeTab(JourneyTabs.blocks);
		setLightOpacity(255);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_BOTTOM_HALF;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return SlayerAPI.toItem(this);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(SlayerAPI.toItem(this));
	}
}