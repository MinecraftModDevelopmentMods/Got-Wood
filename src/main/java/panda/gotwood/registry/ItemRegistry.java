package panda.gotwood.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import panda.gotwood.blocks.BlockPlanks;
import panda.gotwood.items.ItemBase;
import panda.gotwood.items.ItemDates;
import panda.gotwood.items.ItemSeed;
import panda.gotwood.items.itemblocks.ItemWoodDoor;
import panda.gotwood.items.itemblocks.ItemWoodSlab;
import panda.gotwood.util.WoodMaterials;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ItemRegistry {
	
	public static final Item apple_door = new ItemWoodDoor(BlockRegistry.apple_door, WoodMaterials.apple);
	public static final Item maple_door = new ItemWoodDoor(BlockRegistry.maple_door, WoodMaterials.maple);
	public static final Item pine_door = new ItemWoodDoor(BlockRegistry.pine_door, WoodMaterials.pine);
	public static final Item willow_door = new ItemWoodDoor(BlockRegistry.willow_door, WoodMaterials.willow);
	public static final Item yew_door = new ItemWoodDoor(BlockRegistry.yew_door, WoodMaterials.yew);
	public static final Item ebony_door = new ItemWoodDoor(BlockRegistry.ebony_door, WoodMaterials.ebony);
	public static final Item fir_door = new ItemWoodDoor(BlockRegistry.fir_door, WoodMaterials.fir);
	public static final Item bamboo_door = new ItemWoodDoor(BlockRegistry.bamboo_door, WoodMaterials.bamboo);
	public static final Item palm_door = new ItemWoodDoor(BlockRegistry.palm_door, WoodMaterials.palm);
	
	
	//public static final Item apple_slab = new ItemWoodSlab(WoodMaterials.apple,BlockRegistry.apple_planks,BlockRegistry.apple_slab,BlockRegistry.double_apple_slab);
	public static final Item apple_seed = new ItemSeed(WoodMaterials.apple);
	public static final Item maple_seed = new ItemSeed(WoodMaterials.maple);
	public static final Item pine_seed = new ItemSeed(WoodMaterials.pine);
	public static final Item willow_seed = new ItemSeed(WoodMaterials.willow);
	public static final Item yew_seed = new ItemSeed(WoodMaterials.yew);
	public static final Item ebony_seed = new ItemSeed(WoodMaterials.ebony);
	public static final Item fir_seed = new ItemSeed(WoodMaterials.fir);
	public static final Item bamboo_seed = new ItemSeed(WoodMaterials.bamboo);
	public static final Item rubber_seed = new ItemSeed(WoodMaterials.rubber);
	
	public static final Item oak_seed = new ItemSeed(WoodMaterials.oak);
	public static final Item birch_seed = new ItemSeed(WoodMaterials.birch);
	public static final Item spruce_seed = new ItemSeed(WoodMaterials.spruce);
	public static final Item dark_oak_seed = new ItemSeed(WoodMaterials.darkOak);
	public static final Item jungle_seed = new ItemSeed(WoodMaterials.jungle);
	public static final Item acacia_seed = new ItemSeed(WoodMaterials.acacia);
	/*
	public static final Item maple_slab;
	public static final Item maple_seed;
	
	
	public static final Item pine_slab;
	public static final Item pine_seed;
	
	
	public static final Item willow_slab;
	public static final Item willow_seed;
	
	
	public static final Item yew_slab;
	public static final Item yew_seed;
	
	
	public static final Item ebony_slab;
	public static final Item ebony_seed;
	
	
	public static final Item fir_slab;
	public static final Item fir_seed;
	
	
	
	
	*/
	
	public static final Item ash = new ItemBase().setRegistryName("ash");
	public static final Item bamboo_pole = new ItemBase().setRegistryName("bamboo_pole");
	public static final Item bamboo_charcoal = new ItemBase().setRegistryName("bamboo_charcoal");
	public static final Item dates = new ItemDates();
	
	public static final Item maple_sap = new ItemBase().setRegistryName("maple_sap").setMaxStackSize(1);
	public static final Item rubber_sap = new ItemBase().setRegistryName("rubber_sap").setMaxStackSize(1);
	
	
	public static final List<Item> getItemList() {
		List<Item> list = new ArrayList<Item>();
		list.add(apple_seed);
		list.add(maple_seed);
		list.add(pine_seed);
		list.add(willow_seed);
		list.add(yew_seed);
		list.add(fir_seed);
		list.add(ebony_seed);
		list.add(dates);
		list.add(bamboo_seed);
		list.add(rubber_seed);
		list.add(maple_sap);
		list.add(rubber_sap);
		
		
		
		
		list.add(apple_door);
		list.add(maple_door);
		list.add(pine_door);
		list.add(willow_door);
		list.add(yew_door);
		list.add(fir_door);
		list.add(ebony_door);
		list.add(bamboo_door);
		list.add(palm_door);
		
		
		list.add(oak_seed);
		list.add(birch_seed);
		list.add(dark_oak_seed);
		list.add(jungle_seed);
		list.add(acacia_seed);
		list.add(spruce_seed);
		
		list.add(bamboo_charcoal);
		list.add(bamboo_pole);
		list.add(ash);




		return list;
	}
	
static final Map<String,Item> itemRegistry = new HashMap<>();
	
	public static Item getItemByName(String name) {
		return itemRegistry.get(name);
	}
}
