package panda.gotwood.proxy;

import com.mcmoddev.lib.integration.IntegrationManager;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import panda.gotwood.GotWood;
import panda.gotwood.init.Blocks;
import panda.gotwood.init.Fluids;
import panda.gotwood.init.ItemGroups;
import panda.gotwood.init.Items;
import panda.gotwood.init.Materials;
import panda.gotwood.init.Recipes;
import panda.gotwood.util.Config;
import panda.gotwood.util.EventHandler;

public abstract class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		GotWood.logger.debug("CommonProxy preInit() with event %s", event.description());
		MinecraftForge.EVENT_BUS.register(panda.gotwood.init.Items.class);
		MinecraftForge.EVENT_BUS.register(panda.gotwood.init.Blocks.class);

		Config.init();

		/*if ((Options.requireMMDOreSpawn()) && (!Loader.isModLoaded("orespawn"))) {
			final HashSet<ArtifactVersion> orespawnMod = new HashSet<>();
			orespawnMod.add(new DefaultArtifactVersion("3.1.0"));
			throw new MissingModsException(orespawnMod, "orespawn", "MMD Ore Spawn Mod");
		}*/

		Materials.init();
		Fluids.init();
		ItemGroups.init();
		Blocks.init();
		Items.init();
		ItemGroups.setupIcons();
		
		IntegrationManager.INSTANCE.preInit(event);
		MinecraftForge.EVENT_BUS.register(panda.gotwood.proxy.CommonProxy.class);
	}


	public void init(FMLInitializationEvent event) {
		GotWood.logger.debug("CommonProxy init() with event %s", event.description());
		Recipes.init();

		//Achievements.init();

		MinecraftForge.EVENT_BUS.register(new EventHandler());
		//register other events here 
	}

	public void postInit(FMLPostInitializationEvent event) {
		GotWood.logger.debug("CommonProxy postInit() with event %s", event.description());
		Config.postInit();
	}
}
