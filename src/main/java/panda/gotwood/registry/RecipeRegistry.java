package panda.gotwood.registry;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import panda.gotwood.util.WoodMaterial;
import panda.gotwood.util.WoodMaterials;

public final class RecipeRegistry {
	private RecipeRegistry() {}

	public static void init() {
		for (WoodMaterial wood : WoodMaterials.getAllWoods()) {
			if (wood == WoodMaterials.bamboo) {
				String baseName = wood.getName() + "_";
				Item door = ItemRegistry.getItemByName(baseName + "door_item");
				Item pole = ItemRegistry.getItemByName(baseName + "pole");
				Block planks = BlockRegistry.getBlockByName(baseName + "planks");
				Block log = BlockRegistry.getBlockByName(baseName + "log");
				Block trapdoor = BlockRegistry.getBlockByName(baseName + "trapdoor");
				if ((log != null) && (planks != null)) {
					OreDictionary.registerOre("plankWood", planks);
					OreDictionary.registerOre("treeLeaves", BlockRegistry.getBlockByName(baseName + "leaves"));
				}
				if ((planks != null) && (door != null)) {
					OreDictionary.registerOre("door", door);
				}
				if ((planks != null) && (trapdoor != null)) {
					OreDictionary.registerOre("trapdoor", trapdoor);
				}
			} else {
				String baseName = wood.getName() + "_";
				Item door = ItemRegistry.getItemByName(baseName + "door_item");
				Block planks = BlockRegistry.getBlockByName(baseName + "planks");
				Block log = BlockRegistry.getBlockByName(baseName + "log");
				Block trapdoor = BlockRegistry.getBlockByName(baseName + "trapdoor");
				//Item slab = ItemRegistry.getItemByName(baseName + "_slab");
				if ((log != null) && (planks != null)) {
					GameRegistry.addSmelting(log, new ItemStack(Items.COAL, 1, 1), 0.2f);
					OreDictionary.registerOre("plankWood", planks);
					OreDictionary.registerOre("treeLeaves", BlockRegistry.getBlockByName(baseName + "leaves"));
				}
				if ((planks != null) && (door != null)) {
					OreDictionary.registerOre("door", door);
				}
				if ((planks != null) && (trapdoor != null)) {
					OreDictionary.registerOre("trapdoor", trapdoor);
				}
			}
		}
<<<<<<< HEAD
		
		
		
		
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.apple_seed,2), new ItemStack(Items.APPLE));
		GameRegistry.addSmelting( new ItemStack(Items.COAL,1, 1),new ItemStack(ItemRegistry.ash), 0.1f);
		GameRegistry.addSmelting( new ItemStack(ItemRegistry.bamboo_pole),new ItemStack(ItemRegistry.bamboo_charcoal), 0.1f);
		if(ConfigurationHandler.retrieveSaplingsMode == 2){
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAPLING,1,0), new ItemStack(ItemRegistry.oak_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAPLING,1,1), new ItemStack(ItemRegistry.spruce_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAPLING,1,2), new ItemStack(ItemRegistry.birch_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAPLING,1,3), new ItemStack(ItemRegistry.jungle_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAPLING,1,4), new ItemStack(ItemRegistry.acacia_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAPLING,1,5), new ItemStack(ItemRegistry.dark_oak_seed));
			
			GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.apple_sapling), new ItemStack(ItemRegistry.apple_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.maple_sapling), new ItemStack(ItemRegistry.maple_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.pine_sapling), new ItemStack(ItemRegistry.pine_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.willow_sapling), new ItemStack(ItemRegistry.willow_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.yew_sapling), new ItemStack(ItemRegistry.yew_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.ebony_sapling), new ItemStack(ItemRegistry.ebony_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.fir_sapling), new ItemStack(ItemRegistry.fir_seed));
		}
	}

	/**
	 *
	 * @param oreDictEntries
	 * @param name
	 */
=======
		//GameRegistry.addSmelting(new ItemStack(Items.COAL, 1, 1), new ItemStack(ItemRegistry.ash), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemRegistry.bamboo_pole), new ItemStack(ItemRegistry.bamboo_charcoal), 0.1f);

	}

>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc
	public static void addOredicts(String[] oreDictEntries, Block name) {
		addOredicts(oreDictEntries, new ItemStack(name));
		// for(int i = 0; i < oreDictEntries.length; i++)
		// 	OreDictionary.registerOre(oreDictEntries[i], name);
	}

<<<<<<< HEAD
	/**
	 *
	 * @param oreDictEntries
	 * @param name
	 */
=======
>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc
	public static void addOredicts(String[] oreDictEntries, Item name) {
		addOredicts(oreDictEntries, new ItemStack(name));
		// for(int i = 0; i < oreDictEntries.length; i++)
		// 	OreDictionary.registerOre(oreDictEntries[i], name);
	}

<<<<<<< HEAD
	/**
	 *
	 * @param oreDictEntries
	 * @param itemStackName
	 */
=======
>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc
	public static void addOredicts(String[] oreDictEntries, ItemStack itemStackName) {
		// for(int i = 0; i < oreDictEntries.length; i++)
		// 	OreDictionary.registerOre(oreDictEntries[i], itemStackName);
		for (final String oreDictEntry : oreDictEntries) {
			OreDictionary.registerOre(oreDictEntry, itemStackName);
		}
	}
}
