package net.journey.dimension.terrania;

import net.journey.dimension.DimensionHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderTerrania extends WorldProvider {

	@Override
	public void init() {
		this.biomeProvider = new BiomeProviderSingle(DimensionHelper.terrania);
		hasSkyLight = true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float x, float z) {
        return new Vec3d(0.6, 0, 1.0);
    }
	
	@Override
	public boolean canBlockFreeze(BlockPos pos, boolean byWater) {
		return false;
	}
	
	@Override
	public boolean canDoRainSnowIce(Chunk chunk) {
		return false;

	}
	@Override
	public boolean canSnowAt(BlockPos pos, boolean checkLight) {
		return false;
	}

	@Override
	public String getSaveFolder() {
		return "Terrania";
	}

	@Override
	public float getCloudHeight() {
		return 128.0F;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkProviderTerrania(this.world, this.world.getSeed());
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	public float calculateCelestialAngle(long var1, float var3) {
		return 0.5F; 
	}

    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }
    
	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public DimensionType getDimensionType() {
		return DimensionHelper.terraniaType;
	}
}