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

			final String baseName = wood.getName() + "_";
			final String oreDictName = wood.getCapitalizedName();
			final Item door = ItemRegistry.getItemByName(baseName + "door_item");
			final Block planks = BlockRegistry.getBlockByName(baseName + "planks");
			final Block log = BlockRegistry.getBlockByName(baseName + "log");
			final Block trapdoor = BlockRegistry.getBlockByName(baseName + "trapdoor");	
			//final Item slab = ItemRegistry.getItemByName(baseName + "_slab");
			final Block stairs = BlockRegistry.getBlockByName(baseName + "stairs");
			final Block fence = BlockRegistry.getBlockByName(baseName + "fence");
			final Block gate = BlockRegistry.getBlockByName(baseName + "fence_gate");
			
			System.out.println(baseName+"/"+door+"/"+planks+"/"+log+"/"+trapdoor+"/"+stairs+"/"+fence+"/"+gate);
			//

			if ((log != null)&& (planks != null)){
				GameRegistry.addSmelting(log, new ItemStack(Items.COAL,1, 1), 0.2f);
				GameRegistry.addShapelessRecipe(new ItemStack(planks,4), new ItemStack(log));
				OreDictionary.registerOre("plankWood",planks);
				//OreDictionary.registerOre("treeLeaves",BlockRegistry.getBlockByName(baseName + "leaves"));
			}

			if ((planks != null) && (door != null)) {
				GameRegistry.addRecipe(new ItemStack(door,3), new Object[] {"##", "##", "##", '#', new ItemStack(planks)});
				OreDictionary.registerOre("door", door);
			}
			if ((planks != null) && (trapdoor != null)) {
				GameRegistry.addRecipe(new ItemStack(trapdoor,2), new Object[] {"###", "###", '#', new ItemStack(planks)});
				OreDictionary.registerOre("trapdoor", trapdoor);
			}

			if (planks != null){
				//GameRegistry.addRecipe(new ItemStack(slab,6), new Object[] {"###", '#', new ItemStack(planks)});
				//GameRegistry.addRecipe(new ItemStack(planks), new Object[] {"#","#", '#', new ItemStack(slab)});
				GameRegistry.addRecipe(new ItemStack(stairs,ConfigurationHandler.numStairs), new Object[] {"#  ", "## ", "###", '#', new ItemStack(planks)});
				GameRegistry.addRecipe(new ItemStack(fence,3), new Object[] {"#s#", "#s#", '#', new ItemStack(planks),'s', new ItemStack(Items.STICK)});
				GameRegistry.addRecipe(new ItemStack(gate), new Object[] {"s#s", "s#s", '#', new ItemStack(planks),'s', new ItemStack(Items.STICK)});
			}
		}
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.apple_seed,2), new ItemStack(Items.APPLE));
		GameRegistry.addSmelting( new ItemStack(Items.COAL,1, 1),new ItemStack(ItemRegistry.ash), 0.1f);
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
	public static void addOredicts(String[] oreDictEntries, Block name) {
		addOredicts(oreDictEntries, new ItemStack(name));
		// for(int i = 0; i < oreDictEntries.length; i++)
		// 	OreDictionary.registerOre(oreDictEntries[i], name);
	}

	/**
	 *
	 * @param oreDictEntries
	 * @param name
	 */
	public static void addOredicts(String[] oreDictEntries, Item name) {
		addOredicts(oreDictEntries, new ItemStack(name));
		// for(int i = 0; i < oreDictEntries.length; i++)
		// 	OreDictionary.registerOre(oreDictEntries[i], name);
	}

	/**
	 *
	 * @param oreDictEntries
	 * @param itemStackName
	 */
	public static void addOredicts(String[] oreDictEntries, ItemStack itemStackName) {
		// for(int i = 0; i < oreDictEntries.length; i++)
		// 	OreDictionary.registerOre(oreDictEntries[i], itemStackName);
		for (final String oreDictEntry : oreDictEntries)
			OreDictionary.registerOre(oreDictEntry, itemStackName);
	}
}