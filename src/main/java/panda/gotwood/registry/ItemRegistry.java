package panda.gotwood.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.Item;

import panda.gotwood.item.ItemDates;
import panda.gotwood.item.ItemSeed;
import panda.gotwood.item.ItemWoodDoor;
import panda.gotwood.item.ItemWoodSlab;
import panda.gotwood.util.MaterialNames;

public final class ItemRegistry {

	public static final Item ash = new Item().setRegistryName("ash");

	public static final Item bamboo_pole = new Item().setRegistryName("bamboo_pole");

	public static final Item bamboo_charcoal = new Item().setRegistryName("bamboo_charcoal");

	public static final Item dates = new ItemDates();

	public static final Item maple_sap = new Item().setRegistryName("maple_sap").setMaxStackSize(1);

	public static final Item rubber_sap = new Item().setRegistryName("rubber_sap").setMaxStackSize(1);


}
