package net.journey.items;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.util.LangHelper;
import net.journey.util.LangRegistry;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemModRecord extends ItemRecord {

    private static final Map RECORDS = Maps.newHashMap();
    private final SoundEvent sound;
    private String soundName;

	public ItemModRecord(String name, String finalName, SoundEvent sound) {
		super(name, sound);
		setUnlocalizedName(name + "Record");
		soundName = name;
		LangRegistry.addItem(SlayerAPI.PREFIX + name + "Record", finalName);
		setCreativeTab(JourneyTabs.util);
		JourneyItems.itemNames.add(SlayerAPI.PREFIX + name + "Record");
		this.sound = sound;
        this.maxStackSize = 1;
		RECORDS.put(name, this);
		JourneyItems.items.add(this);
		setRegistryName(SlayerAPI.MOD_ID, name + "Record");
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        if (iblockstate.getBlock() == Blocks.JUKEBOX && !((Boolean)iblockstate.getValue(BlockJukebox.HAS_RECORD)).booleanValue()) {
            if (!worldIn.isRemote) {
                ItemStack itemstack = player.getHeldItem(hand);
                ((BlockJukebox)Blocks.JUKEBOX).insertRecord(worldIn, pos, iblockstate, itemstack);
                worldIn.playEvent((EntityPlayer)null, 1010, pos, Item.getIdFromItem(this));
                itemstack.shrink(1);
                player.addStat(StatList.RECORD_PLAYED);
            }
            return EnumActionResult.SUCCESS;
        }
        else {
            return EnumActionResult.PASS;
        }
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.getRecordNameLocal());
    }

    @Override
	@SideOnly(Side.CLIENT)
    public String getRecordNameLocal() {
        return LangHelper.getFormattedText("item.record." + this.soundName + ".desc");
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    @SideOnly(Side.CLIENT)
    public static ItemModRecord getRecord(String name) {
        return (ItemModRecord)RECORDS.get(name);
    }
	
    @Override
    public SoundEvent getSound() {
    	return this.sound;
    }
}