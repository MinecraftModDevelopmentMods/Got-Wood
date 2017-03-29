package panda.gotwood.util;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class TapRegistry {

	public static FluidStack find(Block block) {
		// TODO 
		//Just return water for now
		return new FluidStack(FluidRegistry.WATER,0);
	}

}
