package panda.gotwood.registry;

import java.util.Iterator;
import java.util.List;

import panda.gotwood.GotWood;
import panda.gotwood.blocks.BlockWoodDoor;
import panda.gotwood.blocks.SpecialFire;
import panda.gotwood.events.BlockBreakHandler;
import panda.gotwood.events.ConfigurationHandler;
import panda.gotwood.events.FireHandler;
import panda.gotwood.proxy.RenderProxy;
import panda.gotwood.util.FuelHandler;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterials;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

public final class MasterRegistrar {
	
	public static void register(FMLPreInitializationEvent e, List<?> list) {
		Iterator<?> iterator = list.iterator();

		while (iterator.hasNext()) {
			Object k = iterator.next();
			if (k instanceof Block) {
				Block block = (Block) k;
				GameRegistry.register(block);
				BlockRegistry.Registry.put(block.getRegistryName().getResourcePath(), block);
				if(!(k instanceof SpecialFire)&& !(k instanceof BlockWoodDoor)){
					((Block) k).setCreativeTab(GotWood.TreeTab);
				}
				block.setUnlocalizedName(GotWood.MODID + "." + block.getRegistryName().getResourcePath());
				if (Item.getItemFromBlock(block).equals(Items.AIR)){
					GameRegistry.register(new ItemBlock(block), block.getRegistryName());
				}
			} else if (k instanceof Item) {
				Item item = (Item) k;
				GameRegistry.register((Item) k);
				ItemRegistry.Registry.put(item.getRegistryName().getResourcePath(), item);
				((Item) k).setCreativeTab(GotWood.TreeTab);		
				((Item) k).setUnlocalizedName(GotWood.MODID + "." + ((Item) k).getRegistryName().getResourcePath());
			}
		}
		
		if (e.getSide() == Side.CLIENT){
			registerModels(list);
		}
	}
	
	public static void registerModels(List<?> list) {
		Iterator<?> iterator = list.iterator();

		while (iterator.hasNext()) {
			Object k = iterator.next();
			
			Item item = null;
			if (k instanceof Block) {
				item = Item.getItemFromBlock((Block) k);
				
				System.out.println(item.getRegistryName().getResourcePath());
			} else if (k instanceof Item) {
				item = (Item) k;
				
				System.out.println(item.getRegistryName().getResourcePath());
			}

			if (item != null ) {
				ModelLoader.setCustomModelResourceLocation(item, 0,new ModelResourceLocation(item.getRegistryName(), "inventory"));
				if (item instanceof IOreDictionaryEntry){
					OreDictionary.registerOre(((IOreDictionaryEntry) item).getOreDictionaryName(), item);
				}
			}
		}
	}

	public static void callRegistry(FMLPreInitializationEvent e) {
		ConfigurationHandler.init(e);
		
		MinecraftForge.EVENT_BUS.register(new BlockBreakHandler());
		MinecraftForge.EVENT_BUS.register(new FireHandler());

		WoodMaterials.init();
		
		register(e, BlockRegistry.getBlockList());
		register(e, ItemRegistry.getItemList());
		
		RecipeRegistry.init();
		GameRegistry.registerFuelHandler(new FuelHandler());
		if(e.getSide() == Side.CLIENT){
			RenderProxy.init();
		}
	}
}