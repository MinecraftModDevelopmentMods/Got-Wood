package panda.gotwood.init;

import com.mcmoddev.lib.util.Config.Options;
import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.init.MMDCreativeTab;
import com.mcmoddev.lib.util.TabContainer;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import panda.gotwood.util.MaterialNames;


public class ItemGroups extends com.mcmoddev.lib.init.ItemGroups {

	private static boolean initDone = false;
	private static final int BLOCKS_TAB_ID  = addTab("blocks", true);
	private static final int ITEMS_TAB_ID = addTab("items", true);
	private static final MMDCreativeTab blocksTab = getTab(BLOCKS_TAB_ID);
	private static final MMDCreativeTab itemsTab = getTab(ITEMS_TAB_ID);
	public static final TabContainer myTabs = new TabContainer(blocksTab, itemsTab);

	private ItemGroups() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		initDone = true;
	}
	
	public static void setupIcons() {
		//TODO
		Item blocksTabIconItem = Item.getItemFromBlock(Materials.getMaterialByName(MaterialNames.APPLE).getBlock(Names.BLOCK));
		Item itemsTabIconItem = Materials.getMaterialByName(MaterialNames.OAK).getItem(Names.GEAR);
		
		
		blocksTab.setTabIconItem(new ItemStack(blocksTabIconItem));
		itemsTab.setTabIconItem(new ItemStack(itemsTabIconItem));
	}
}
