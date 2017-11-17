package panda.gotwood.init;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import panda.gotwood.GotWood;
import panda.gotwood.util.MaterialNames;

import javax.annotation.Nonnull;

import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.init.Materials;
import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.util.TabContainer;
import com.mcmoddev.lib.util.ConfigBase.Options;


public class Blocks extends com.mcmoddev.lib.init.Blocks{
	private static boolean initDone = false;

	protected Blocks() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		Materials.init();
		ItemGroups.init();

		if (Options.isMaterialEnabled(MaterialNames.APPLE)) {
			createBlocksFullWood(MaterialNames.APPLE, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.MAPLE)) {
			createBlocksFullWood(MaterialNames.MAPLE, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.PINE)) {
			createBlocksFullWood(MaterialNames.PINE, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.WILLOW)) {
			createBlocksFullWood(MaterialNames.WILLOW, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.YEW)) {
			createBlocksFullWood(MaterialNames.YEW, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.EBONY)) {
			createBlocksFullWood(MaterialNames.EBONY, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.FIR)) {
			createBlocksFullWood(MaterialNames.FIR, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.BAMBOO)) {
			createBlocksFullWood(MaterialNames.BAMBOO, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.RUBBER)) {
			createBlocksFullWood(MaterialNames.RUBBER, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.PALM)) {
			createBlocksFullWood(MaterialNames.PALM, ItemGroups.myTabs);
		}
		
		
		
		
		if (Options.isMaterialEnabled(MaterialNames.OAK)) {
			//createBlocksFull(MaterialNames.OAK, ItemGroups.myTabs);
			//final MMDMaterial material = Materials.getMaterialByName(MaterialNames.OAK);

			//material.addNewBlock(Names.BLOCK, net.minecraft.init.Blocks.LAPIS_BLOCK);
			//material.addNewBlock(Names.ORE, net.minecraft.init.Blocks.LAPIS_ORE);
		}
		
		if (Options.isMaterialEnabled(MaterialNames.SPRUCE)) {
			//createBlocksFull(MaterialNames.SPRUCE, ItemGroups.myTabs);
		}
		
		if (Options.isMaterialEnabled(MaterialNames.DARKOAK)) {
			//createBlocksFull(MaterialNames.DARKOAK, ItemGroups.myTabs);
		}
		
		if (Options.isMaterialEnabled(MaterialNames.JUNGLE)) {
			//createBlocksFull(MaterialNames.JUNGLE, ItemGroups.myTabs);
		}
		
		if (Options.isMaterialEnabled(MaterialNames.ACACIA)) {
			//createBlocksFull(MaterialNames.ACACIA, ItemGroups.myTabs);
		}
		
		if (Options.isMaterialEnabled(MaterialNames.BIRCH)) {
			//createBlocksFull(MaterialNames.BIRCH, ItemGroups.myTabs);
		}
		


		initDone = true;
	}
	
	protected static void createBlocksFullWood(@Nonnull final String materialName, @Nonnull final TabContainer tabs) {
		MMDMaterial material = Materials.getMaterialByName(materialName);
		if ((material == null) || (tabs == null)) {
			return;
		}

		//TODO
		create(Names.LOG, material, tabs.blocksTab);
		create(Names.PLANKS, material, tabs.blocksTab);
		create(Names.DOOR, material, tabs.blocksTab);
		create(Names.TRAPDOOR, material, tabs.blocksTab);
		create(Names.LEAVES, material, tabs.blocksTab);
		create(Names.SAPLING, material, tabs.blocksTab);
		create(Names.FENCE_GATE, material, tabs.blocksTab);
		create(Names.SLAB, material, tabs.blocksTab);
		create(Names.DOUBLE_SLAB, material, tabs.blocksTab);
		create(Names.STAIRS, material, tabs.blocksTab);
		create(Names.FENCE, material, tabs.blocksTab);		
		//What to do with blockdates and specialFire?
	}
	
	
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for( MMDMaterial mat : Materials.getMaterialsByMod(GotWood.MODID) ) {
			GotWood.logger.fatal("Trying to register blocks for material %s", mat.getCapitalizedName());
			for( Block block : mat.getBlocks() ) {
				if( block.getRegistryName().getResourceDomain().equals(GotWood.MODID) ) {
					GotWood.logger.fatal("Trying to register block %s of material %s", block.getRegistryName(), mat.getCapitalizedName());
					event.getRegistry().register(block);
				}
			}
		}
	}
}
