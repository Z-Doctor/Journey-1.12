package net.journey;

import java.util.ArrayList;
import java.util.List;

import net.journey.client.IHasModel;
import net.journey.util.recipes.JourneySmeltingRecipes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;

@EventBusSubscriber(modid=SlayerAPI.MOD_ID)
public class Registrys {

    public static final List<SoundEvent> SOUNDS = new ArrayList<>();
    
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for(int i = 0; i < JourneyItems.items.size(); i++)
			event.getRegistry().registerAll(JourneyItems.items.get(i));
	}
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(JourneyItems.items.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for(int i = 0; i < JourneyBlocks.blocks.size(); i++)
			event.getRegistry().registerAll(JourneyBlocks.blocks.get(i));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(JourneyBlocks.blocks.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Block block : JourneyBlocks.blocks)
			if(block instanceof IHasModel)
				((IHasModel)block).registerModels(event);
		for(Item item : JourneyItems.items)
			if(item instanceof IHasModel)
				((IHasModel)item).registerModels(event);
	}
	
    @SubscribeEvent
    public void onSoundRegistry(Register<SoundEvent> event){
        JourneySounds.init();

        for(SoundEvent sound : SOUNDS){
            event.getRegistry().register(sound);
        }
        SOUNDS.clear();
    }
	
	public static void otherRegistries() {
		JourneySmeltingRecipes.init();
	}
}