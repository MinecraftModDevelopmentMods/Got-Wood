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

	private static boolean initDone = false;

	public static void init() {
		if (initDone)
			return;

		panda.gotwood.util.WoodMaterials.init();
		panda.varietytrees.init.Blocks.init();
		panda.varietytrees.init.Items.init();

		initWoodRecipes();

		initDone = true;
	}


	private static void initWoodRecipes() {
		
		for (final WoodMaterial wood : WoodMaterials.getAllWoods()) {

			final String baseName = wood.getName() + "_";
			final String oreDictName = wood.getCapitalizedName();
			final Item door = panda.varietytrees.init.Items.getItemByName(baseName + "door");
			final Block planks = panda.varietytrees.init.Blocks.getBlockByName(baseName + "planks");
			final Block log = panda.varietytrees.init.Blocks.getBlockByName(baseName + "log");
			final Block trapdoor = panda.varietytrees.init.Blocks.getBlockByName(baseName + "trapdoor");	
			final Item slab = panda.varietytrees.init.Items.getItemByName(baseName + "slab");
			final Block stairs = panda.varietytrees.init.Blocks.getBlockByName(baseName + "stairs");
			final Block fence = panda.varietytrees.init.Blocks.getBlockByName(baseName + "fence");
			final Block gate = panda.varietytrees.init.Blocks.getBlockByName(baseName + "fence_gate");
			
			//

			if ((log != null)&& (planks != null)){
				GameRegistry.addSmelting(log, new ItemStack(Items.COAL,1, 1), 0.2f);
				GameRegistry.addShapelessRecipe(new ItemStack(planks,4), new ItemStack(log));
				OreDictionary.registerOre("plankWood",planks);
				OreDictionary.registerOre("treeLeaves",panda.varietytrees.init.Blocks.getBlockByName(baseName + "leaves"));
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
				GameRegistry.addRecipe(new ItemStack(slab,6), new Object[] {"###", '#', new ItemStack(planks)});
				GameRegistry.addRecipe(new ItemStack(planks), new Object[] {"#","#", '#', new ItemStack(slab)});
				GameRegistry.addRecipe(new ItemStack(stairs,ConfigurationHandler.numStairs), new Object[] {"#  ", "## ", "###", '#', new ItemStack(planks)});
				GameRegistry.addRecipe(new ItemStack(fence,3), new Object[] {"#s#", "#s#", '#', new ItemStack(planks),'s', new ItemStack(Items.STICK)});
				GameRegistry.addRecipe(new ItemStack(gate), new Object[] {"s#s", "s#s", '#', new ItemStack(planks),'s', new ItemStack(Items.STICK)});
			}
		}
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(GameRegistry.findItem("varietytrees", "apple_seed"),2), new ItemStack(Items.APPLE));
	
		if(ConfigurationHandler.retrieveSaplingsMode == 2){
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAPLING,1,0), new ItemStack(GameRegistry.findItem("varietytrees", "oak_seed")));
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAPLING,1,1), new ItemStack(GameRegistry.findItem("varietytrees", "spruce_seed")));
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAPLING,1,2), new ItemStack(GameRegistry.findItem("varietytrees", "birch_seed")));
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAPLING,1,3), new ItemStack(GameRegistry.findItem("varietytrees", "jungle_seed")));
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAPLING,1,4), new ItemStack(GameRegistry.findItem("varietytrees", "acacia_seed")));
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.SAPLING,1,5), new ItemStack(GameRegistry.findItem("varietytrees", "dark_oak_seed")));
			
			GameRegistry.addShapelessRecipe(new ItemStack(panda.varietytrees.init.Blocks.apple_sapling), new ItemStack(panda.varietytrees.init.Items.apple_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(panda.varietytrees.init.Blocks.maple_sapling), new ItemStack(panda.varietytrees.init.Items.maple_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(panda.varietytrees.init.Blocks.pine_sapling), new ItemStack(panda.varietytrees.init.Items.pine_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(panda.varietytrees.init.Blocks.willow_sapling), new ItemStack(panda.varietytrees.init.Items.willow_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(panda.varietytrees.init.Blocks.yew_sapling), new ItemStack(panda.varietytrees.init.Items.yew_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(panda.varietytrees.init.Blocks.ebony_sapling), new ItemStack(panda.varietytrees.init.Items.ebony_seed));
			GameRegistry.addShapelessRecipe(new ItemStack(panda.varietytrees.init.Blocks.fir_sapling), new ItemStack(panda.varietytrees.init.Items.fir_seed));
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