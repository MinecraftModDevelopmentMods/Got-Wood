package panda.gotwood.registry;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import panda.gotwood.util.WoodMaterial;
import panda.gotwood.util.MaterialNames;

public final class RecipeRegistry {
	private RecipeRegistry() {}

	public static void init() {
		for (WoodMaterial wood : MaterialNames.getAllWoods()) {
			if (wood == MaterialNames.bamboo) {
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
		//GameRegistry.addSmelting(new ItemStack(Items.COAL, 1, 1), new ItemStack(ItemRegistry.ash), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ItemRegistry.bamboo_pole), new ItemStack(ItemRegistry.bamboo_charcoal), 0.1f);

	}

	public static void addOredicts(String[] oreDictEntries, Block name) {
		addOredicts(oreDictEntries, new ItemStack(name));
		// for(int i = 0; i < oreDictEntries.length; i++)
		// 	OreDictionary.registerOre(oreDictEntries[i], name);
	}

	public static void addOredicts(String[] oreDictEntries, Item name) {
		addOredicts(oreDictEntries, new ItemStack(name));
		// for(int i = 0; i < oreDictEntries.length; i++)
		// 	OreDictionary.registerOre(oreDictEntries[i], name);
	}

	public static void addOredicts(String[] oreDictEntries, ItemStack itemStackName) {
		// for(int i = 0; i < oreDictEntries.length; i++)
		// 	OreDictionary.registerOre(oreDictEntries[i], itemStackName);
		for (final String oreDictEntry : oreDictEntries) {
			OreDictionary.registerOre(oreDictEntry, itemStackName);
		}
	}
}
