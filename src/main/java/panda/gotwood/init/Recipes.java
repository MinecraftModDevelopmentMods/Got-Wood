package panda.gotwood.init;

import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.util.Config.Options;

import panda.gotwood.util.MaterialNames;


public class Recipes extends com.mcmoddev.lib.init.Recipes {

	private static boolean initDone = false;

	private Recipes() {
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
		Blocks.init();
		Items.init();

		initModSpecificRecipes();

		initDone = true;
	}

	private static void initModSpecificRecipes() {
		//TODO
		if (Options.isMaterialEnabled(MaterialNames.APPLE)) {
			final MMDMaterial material = Materials.getMaterialByName(MaterialNames.APPLE);
			addAdditionalOredicts(material, "Aluminium");
			addAdditionalOredicts(material, "Bauxite");
		}

	}
}
