package panda.gotwood.init;

import com.mcmoddev.lib.util.ConfigBase.Options;

import panda.gotwood.util.MaterialNames;

public class Fluids extends com.mcmoddev.lib.init.Fluids {

	private static boolean initDone = false;

	private Fluids() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}
//TODO
		if (Options.isMaterialEnabled(MaterialNames.MAPLE)) {
			addFluid(MaterialNames.MAPLE, 2000, 10000, 80, 10);
			addFluidBlock(MaterialNames.MAPLE);
		}

		if (Options.isMaterialEnabled(MaterialNames.RUBBER)) {
			addFluid(MaterialNames.RUBBER, 2000, 10000, 80, 10);
			addFluidBlock(MaterialNames.RUBBER);
		}

		initDone = true;
	}
}
