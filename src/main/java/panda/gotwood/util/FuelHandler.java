package panda.gotwood.util;


import panda.gotwood.registry.ItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		return fuel.getItem() == ItemRegistry.bamboo_charcoal? 800:0;
	}

}
