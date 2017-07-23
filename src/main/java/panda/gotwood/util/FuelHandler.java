package panda.gotwood.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

import panda.gotwood.registry.ItemRegistry;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		return fuel.getItem() == ItemRegistry.bamboo_charcoal ? 800 : 0;
	}

}
