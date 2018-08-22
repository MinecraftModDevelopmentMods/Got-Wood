package panda.gotwood.init;

import com.mcmoddev.lib.util.Config.Options;

import panda.gotwood.util.MaterialNames;

import com.mcmoddev.lib.material.MMDMaterial.MaterialType;

public class Materials extends com.mcmoddev.lib.init.Materials {

	private static boolean initDone = false;

	protected Materials() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}/**
		 * Create a oreless material
		 * 
		 * @param name
		 *            Name of the material
		 * @param type
		 *            the type of the material (metal, gem, mineral, etc...)
		 * @param hardness
		 *            Scaled hardness of the material, based on the Mohs scale
		 * @param strength
		 *            material strength
		 * @param magic
		 *            material magic affinity
		 * @param tintColor
		 *            material tint color - used in several places, including in the
		 *            TiC plugin, where it determines tool-part color*/
		//TODO properties
		
		if (Options.isMaterialEnabled(MaterialNames.APPLE)) {
			createOrelessMaterial(MaterialNames.APPLE, MaterialType.WOOD, 2.5, 3.75, 4.5, 0xFFC5C8C1);
		}

		if (Options.isMaterialEnabled(MaterialNames.MAPLE)) {
			createOrelessMaterial(MaterialNames.MAPLE, MaterialType.WOOD, 1, 7.5, 4.5, 0xFFEBAA56);
		}

		if (Options.isMaterialEnabled(MaterialNames.PINE)) {
			createOrelessMaterial(MaterialNames.PINE, MaterialType.WOOD, 2, 1, 4.5, 0xFFC9D4DA);
		}

		if (Options.isMaterialEnabled(MaterialNames.WILLOW)) {
			createOrelessMaterial(MaterialNames.WILLOW, MaterialType.WOOD, 9, 3, 4.5, 0xFFCDCDCF);
		}

		if (Options.isMaterialEnabled(MaterialNames.YEW)) {
			createOrelessMaterial(MaterialNames.YEW, MaterialType.WOOD, 5.5, 15.25, 4.5, 0xFF9BA6A2);
		}

		if (Options.isMaterialEnabled(MaterialNames.EBONY)) {
			createOrelessMaterial(MaterialNames.EBONY, MaterialType.WOOD, 6.5, 3, 4.5, 0xFFF8EDCC);
		}

		if (Options.isMaterialEnabled(MaterialNames.FIR)) {
			createOrelessMaterial(MaterialNames.FIR, MaterialType.WOOD, 2.5, 3.5, 4.5, 0xFF7F7F77);
		}

		if (Options.isMaterialEnabled(MaterialNames.BAMBOO)) {
			createOrelessMaterial(MaterialNames.BAMBOO, MaterialType.WOOD, 5, 2.75, 4.5, 0xFFF5CFDA);
		}

		if (Options.isMaterialEnabled(MaterialNames.RUBBER)) {
			createOrelessMaterial(MaterialNames.RUBBER, MaterialType.WOOD, 6, 15.5, 4.5, 0xFFDEA054);
		}

		if (Options.isMaterialEnabled(MaterialNames.PALM)) {
			createOrelessMaterial(MaterialNames.PALM, MaterialType.WOOD, 7, 2.75, 4.5, 0xFF7C8E99);
		}
		
		
		
		
		
		if (Options.isMaterialEnabled(MaterialNames.OAK)) {
			createOrelessMaterial(MaterialNames.OAK, MaterialType.WOOD, 5.5, 15.25, 4.5, 0xFF9BA6A2);
		}

		if (Options.isMaterialEnabled(MaterialNames.SPRUCE)) {
			createOrelessMaterial(MaterialNames.SPRUCE, MaterialType.WOOD, 6.5, 3, 4.5, 0xFFF8EDCC);
		}

		if (Options.isMaterialEnabled(MaterialNames.DARKOAK)) {
			createOrelessMaterial(MaterialNames.DARKOAK, MaterialType.WOOD, 2.5, 3.5, 4.5, 0xFF7F7F77);
		}

		if (Options.isMaterialEnabled(MaterialNames.JUNGLE)) {
			createOrelessMaterial(MaterialNames.JUNGLE, MaterialType.WOOD, 5, 2.75, 4.5, 0xFFF5CFDA);
		}

		if (Options.isMaterialEnabled(MaterialNames.ACACIA)) {
			createOrelessMaterial(MaterialNames.ACACIA, MaterialType.WOOD, 6, 15.5, 4.5, 0xFFDEA054);
		}

		if (Options.isMaterialEnabled(MaterialNames.BIRCH)) {
			createOrelessMaterial(MaterialNames.BIRCH, MaterialType.WOOD, 7, 2.75, 4.5, 0xFF7C8E99);
		}

		initDone = true;
	}
}
