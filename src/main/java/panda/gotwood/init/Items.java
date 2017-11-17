package panda.gotwood.init;


import net.minecraft.item.Item;
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


public class Items extends com.mcmoddev.lib.init.Items {

	private static boolean initDone = false;

	protected Items() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		Blocks.init();

		if (Options.isMaterialEnabled(MaterialNames.APPLE)) {
			createItemsFullWood(MaterialNames.APPLE, ItemGroups.myTabs);
			//createItemsModSupport(MaterialNames.APPLE, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.MAPLE)) {
			createItemsFullWood(MaterialNames.MAPLE, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.PINE)) {
			createItemsFullWood(MaterialNames.PINE, ItemGroups.myTabs);
			//createItemsModSupport(MaterialNames.PINE, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.WILLOW)) {
			createItemsFullWood(MaterialNames.WILLOW, ItemGroups.myTabs);
			//createItemsModSupport(MaterialNames.WILLOW, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.YEW)) {
			createItemsFullWood(MaterialNames.YEW, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.EBONY)) {
			createItemsFullWood(MaterialNames.EBONY, ItemGroups.myTabs);
			//createItemsModSupport(MaterialNames.EBONY, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.FIR)) {
			createItemsFullWood(MaterialNames.FIR, ItemGroups.myTabs);
			//createItemsModSupport(MaterialNames.FIR, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.BAMBOO)) {
			createItemsFullWood(MaterialNames.BAMBOO, ItemGroups.myTabs);
			//FuelRegistry.addFuel(Oredicts.bamboo_charcoal, 800);
			//createItemsModSupport(MaterialNames.BAMBOO, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.RUBBER)) {
			createItemsFullWood(MaterialNames.RUBBER, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.PALM)) {
			createItemsFullWood(MaterialNames.PALM, ItemGroups.myTabs);
			//createItemsModIC2(MaterialNames.PALM, ItemGroups.myTabs);
		}

		
		
		
		
		if (Options.isMaterialEnabled(MaterialNames.OAK)) {
			createItemsSeeds(MaterialNames.OAK, ItemGroups.myTabs);
			//createItemsModSupport(MaterialNames.OAK, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.SPRUCE)) {
			createItemsSeeds(MaterialNames.SPRUCE, ItemGroups.myTabs);
			//createItemsModSupport(MaterialNames.SPRUCE, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.DARKOAK)) {
			createItemsSeeds(MaterialNames.DARKOAK, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.JUNGLE)) {
			createItemsSeeds(MaterialNames.JUNGLE, ItemGroups.myTabs);
			//createItemsModSupport(MaterialNames.JUNGLE, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.ACACIA)) {
			createItemsSeeds(MaterialNames.ACACIA, ItemGroups.myTabs);
			//createItemsModSupport(MaterialNames.ACACIA, ItemGroups.myTabs);
		}

		if (Options.isMaterialEnabled(MaterialNames.BIRCH)) {
			createItemsSeeds(MaterialNames.BIRCH, ItemGroups.myTabs);
		}

		initDone = true;
	}
	
	
	
	protected static void createItemsFullWood(@Nonnull final String materialName, @Nonnull final TabContainer tabs) {
		MMDMaterial material = Materials.getMaterialByName(materialName);
		if ((material == null) || (tabs == null)) {
			return;
		}

		//TODO
		create(Names.DOOR, material, tabs.blocksTab);
		create(Names.SLAB, material, tabs.blocksTab);
		createItemsSeeds(materialName,tabs);

		//What to do with items that dont belong to a material?
	}
	
	protected static void createItemsSeeds(@Nonnull final String materialName, @Nonnull final TabContainer tabs) {
		MMDMaterial material = Materials.getMaterialByName(materialName);
		if ((material == null) || (tabs == null)) {
			return;
		}
		create(Names.SEED, material, tabs.itemsTab);
	}
	
	
	
	
	
	
	
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
            for( MMDMaterial mat : Materials.getMaterialsByMod(GotWood.MODID) ) {
    			GotWood.logger.fatal("Trying to register itemss for material %s", mat.getCapitalizedName());
                    for( Item item : mat.getItems() ) {
                            if( item.getRegistryName().getResourceDomain().equals(GotWood.MODID) ) {
                            	GotWood.logger.fatal("Trying to register item %s of material %s", item.getRegistryName(), mat.getCapitalizedName());
                                    event.getRegistry().register(item);
                            }
                    }
            }
    }

}