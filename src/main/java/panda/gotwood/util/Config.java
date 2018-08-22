package panda.gotwood.util;

import java.io.File;

import com.mcmoddev.basemetals.data.MaterialNames;
import com.mcmoddev.lib.registry.CrusherRecipeRegistry;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import panda.gotwood.GotWood;

//https://github.com/MinecraftModDevelopmentMods/BaseMetals/blob/1.12/src/main/java/com/mcmoddev/basemetals/util/Config.java

public class Config extends com.mcmoddev.lib.util.Config {

	private static Configuration configuration;
	private static final String CONFIG_FILE = "config/GotWood.cfg";
	private static final String MATERIALS_CAT = "Woods";

	@SubscribeEvent
	public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent e) {
		if (e.getModID().equals(GotWood.MODID)) {
			init();
		}
	}

	public static void init() {
		if (configuration == null) {
			configuration = new Configuration(new File(CONFIG_FILE));
			MinecraftForge.EVENT_BUS.register(new Config());
		}

		// METALS
		/*Options.materialEnabled(MaterialNames.ALUMINUM, configuration.getBoolean("EnableAluminum", MATERIALS_CAT, true, "Enable Aluminum Items and Materials"));
		Options.materialEnabled(MaterialNames.ALUMINUM_BRASS, configuration.getBoolean("EnableAluminumBrass", MATERIALS_CAT, true, "Enable Aluminum Brass Items and Materials"));
		Options.materialEnabled(MaterialNames.CADMIUM, configuration.getBoolean("EnableCadmium", MATERIALS_CAT, true, "Enable Cadmium Items and Materials"));
		Options.materialEnabled(MaterialNames.CHROMIUM, configuration.getBoolean("EnableChromium", MATERIALS_CAT, true, "Enable Chromium Items and Materials"));
		Options.materialEnabled(MaterialNames.GALVANIZED_STEEL, configuration.getBoolean("EnableGalvanizedSteel", MATERIALS_CAT, true, "Enable Galvanized Steel Items and Materials"));
		Options.materialEnabled(MaterialNames.IRIDIUM, configuration.getBoolean("EnableIridium", MATERIALS_CAT, true, "Enable Iridium Items and Materials"));
		Options.materialEnabled(MaterialNames.MAGNESIUM, configuration.getBoolean("EnableMagnesium", MATERIALS_CAT, true, "Enable Magnesium Items and Materials"));
		Options.materialEnabled(MaterialNames.MANGANESE, configuration.getBoolean("EnableManganese", MATERIALS_CAT, true, "Enable Manganese Items and Materials"));
		Options.materialEnabled(MaterialNames.NICHROME, configuration.getBoolean("EnableNichrome", MATERIALS_CAT, true, "Enable Nichrome Items and Materials"));
		Options.materialEnabled(MaterialNames.OSMIUM, configuration.getBoolean("EnableOsmium", MATERIALS_CAT, true, "Enable Osmium Items and Materials"));
		Options.materialEnabled(MaterialNames.PLUTONIUM, configuration.getBoolean("EnablePlutonium", MATERIALS_CAT, true, "Enable Plutonium Items and Materials"));
		Options.materialEnabled(MaterialNames.RUTILE, configuration.getBoolean("EnableRutile", MATERIALS_CAT, true, "Enable Rutile Items and Materials"));
		Options.materialEnabled(MaterialNames.STAINLESS_STEEL, configuration.getBoolean("EnableStainlessSteel", MATERIALS_CAT, true, "Enable Stainless Steel Items and Materials"));
		Options.materialEnabled(MaterialNames.TANTALUM, configuration.getBoolean("EnableTantalum", MATERIALS_CAT, true, "Enable Tantalum Items and Materials"));
		Options.materialEnabled(MaterialNames.TITANIUM, configuration.getBoolean("EnableTitanium", MATERIALS_CAT, true, "Enable Titanium Items and Materials"));
		Options.materialEnabled(MaterialNames.TUNGSTEN, configuration.getBoolean("EnableTungsten", MATERIALS_CAT, true, "Enable Tungsten Items and Materials"));
		Options.materialEnabled(MaterialNames.URANIUM, configuration.getBoolean("EnableUranium", MATERIALS_CAT, true, "Enable Uranium Items and Materials"));
		Options.materialEnabled(MaterialNames.ZIRCONIUM, configuration.getBoolean("EnableZirconium", MATERIALS_CAT, true, "Enable Zirconium Items and Materials"));
*/
		if (configuration.hasChanged()) {
			configuration.save();
		}
	}
	
	public static void postInit() {
		CrusherRecipeRegistry.getInstance().clearCache();
	}
}
