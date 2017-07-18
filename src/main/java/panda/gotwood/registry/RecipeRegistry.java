package panda.gotwood.registry;


import java.util.Arrays;
import java.util.List;

import panda.gotwood.events.ConfigurationHandler;
import panda.gotwood.util.WoodMaterial;
import panda.gotwood.util.WoodMaterials;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;


public abstract class RecipeRegistry {
	
	public static void init() {
		
		for (final WoodMaterial wood : WoodMaterials.getAllWoods()) {
			if(wood == WoodMaterials.bamboo ){
				final String baseName = wood.getName() + "_";
				final Item door = ItemRegistry.getItemByName(baseName + "door_item");
				final Item pole = ItemRegistry.getItemByName(baseName + "pole");
				final Block planks = BlockRegistry.getBlockByName(baseName + "planks");
				final Block log = BlockRegistry.getBlockByName(baseName + "log");
				final Block trapdoor = BlockRegistry.getBlockByName(baseName + "trapdoor");	


				

				if ((log != null)&& (planks != null)){
					OreDictionary.registerOre("plankWood",planks);
					OreDictionary.registerOre("treeLeaves",BlockRegistry.getBlockByName(baseName + "leaves"));
				}

				if ((planks != null) && (door != null)) {
					OreDictionary.registerOre("door", door);
				}
				if ((planks != null) && (trapdoor != null)) {
					OreDictionary.registerOre("trapdoor", trapdoor);
				}

			}else{
			final String baseName = wood.getName() + "_";
			final Item door = ItemRegistry.getItemByName(baseName + "door_item");
			final Block planks = BlockRegistry.getBlockByName(baseName + "planks");
			final Block log = BlockRegistry.getBlockByName(baseName + "log");
			final Block trapdoor = BlockRegistry.getBlockByName(baseName + "trapdoor");	
			//final Item slab = ItemRegistry.getItemByName(baseName + "_slab");


			if ((log != null)&& (planks != null)){
				GameRegistry.addSmelting(log, new ItemStack(Items.COAL,1, 1), 0.2f);
				OreDictionary.registerOre("plankWood",planks);
				OreDictionary.registerOre("treeLeaves",BlockRegistry.getBlockByName(baseName + "leaves"));
			}

			if ((planks != null) && (door != null)) {
				OreDictionary.registerOre("door", door);
			}
			if ((planks != null) && (trapdoor != null)) {
				OreDictionary.registerOre("trapdoor", trapdoor);
			}
			}
		}
		
		GameRegistry.addSmelting( new ItemStack(Items.COAL,1, 1),new ItemStack(ItemRegistry.ash), 0.1f);
		GameRegistry.addSmelting( new ItemStack(ItemRegistry.bamboo_pole),new ItemStack(ItemRegistry.bamboo_charcoal), 0.1f);
		
		
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
		for (final String oreDictEntry : oreDictEntries)
			OreDictionary.registerOre(oreDictEntry, itemStackName);
	}
}