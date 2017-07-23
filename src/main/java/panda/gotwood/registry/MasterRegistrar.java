package panda.gotwood.registry;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import panda.gotwood.GotWood;
import panda.gotwood.block.BlockDoubleSlab;
import panda.gotwood.block.BlockSpecialFire;
import panda.gotwood.block.BlockWoodDoor;
import panda.gotwood.events.BlockBreakHandler;
import panda.gotwood.events.ConfigurationHandler;
import panda.gotwood.events.FireHandler;
import panda.gotwood.util.FuelHandler;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterials;

public final class MasterRegistrar {
	private MasterRegistrar() {}

	public static void register(FMLPreInitializationEvent e, List<?> list) {
		Iterator<?> iterator = list.iterator();
		while (iterator.hasNext()) {
			Object k = iterator.next();
			if (k instanceof Block) {
				Block block = (Block) k;
				ForgeRegistries.BLOCKS.register(block);
				BlockRegistry.registry.put(block.getRegistryName().getResourcePath(), block);
				if (!(k instanceof BlockSpecialFire) && !(k instanceof BlockWoodDoor)) {
					((Block) k).setCreativeTab(GotWood.TREE_TAB);
				}
				block.setUnlocalizedName(GotWood.ID + "." + block.getRegistryName().getResourcePath());
				if (!(k instanceof BlockDoubleSlab) && !ForgeRegistries.ITEMS.containsKey(block.getRegistryName())) {
					ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
				}
			} else if (k instanceof Item) {
				Item item = (Item) k;
				ForgeRegistries.ITEMS.register((Item) k);
				ItemRegistry.registry.put(item.getRegistryName().getResourcePath(), item);
				((Item) k).setCreativeTab(GotWood.TREE_TAB);
				((Item) k).setUnlocalizedName(GotWood.ID + "." + ((Item) k).getRegistryName().getResourcePath());
			}
		}
		if (e.getSide() == Side.CLIENT) {
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
			if (item != null) {
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
				if (item instanceof IOreDictionaryEntry) {
					OreDictionary.registerOre(((IOreDictionaryEntry) item).getOreDictionaryName(), item); // FIXME: will only happen on client side
				}
			}
		}
	}

	public static void callRegistry(FMLPreInitializationEvent e) {
		ConfigurationHandler.init(e);
		MinecraftForge.EVENT_BUS.register(new BlockBreakHandler());
		MinecraftForge.EVENT_BUS.register(new FireHandler());
		WoodMaterials.init();
		List<Block> blocks = BlockRegistry.getBlockList();
		register(e, ItemRegistry.getItemList());
		register(e, blocks);
		RecipeRegistry.init();
		GameRegistry.registerFuelHandler(new FuelHandler());
	}
}
