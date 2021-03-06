package net.journey.client.server;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;

public class EssenceBar implements IEssence {

	private int essenceValue = 10;
	private int regenValue = 0;

	@Override
	public boolean useEssence(int points) {
		if(getEssenceValue() < points) 
			return false;
		essenceValue -= points;
		return true;
	}

	@Override
	public void addEssence(int points) {
		essenceValue += points;
		if(getEssenceValue() > 10) setEssence(10);
	}

	@Override
	public void setEssence(int essence) {
		essenceValue = essence;
		if(getEssenceValue() > 10) essenceValue = 10;
	}

	@Override
	public int getEssenceValue() {
		return essenceValue;
	}

	@Override
	public void update() {
		if(getEssenceValue() > 10) setEssence(10);
		if(regenValue-- <= 0) regenValue = 30;
		if(regenValue >= 30) regen();
	}

	@Override
	public void regen() {
		addEssence(1);
	}

	@Override
	public NBTBase writeNBT(IEssence essence, NBTTagCompound tag) {
		tag.setInteger("regen", 30);
		return new NBTTagInt(essence.getEssenceValue());
	}

	@Override
	public void readNBT(NBTBase nbt, IEssence essence, NBTTagCompound tag) {
		essence.setEssence(((NBTTagInt)nbt).getInt());
		regenValue = tag.getInteger("regen");
	}
}