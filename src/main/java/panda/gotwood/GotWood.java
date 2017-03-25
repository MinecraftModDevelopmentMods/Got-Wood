package panda.gotwood;

import panda.gotwood.blocks.BlockPlanks;
import panda.gotwood.blocks.SpecialFire;
import panda.gotwood.events.BlockBreakHandler;
import panda.gotwood.events.ConfigurationHandler;
import panda.gotwood.events.ConsumedByFireListener;
import panda.gotwood.events.FireHandler;
import panda.gotwood.generation.WorldGenerator;
import panda.gotwood.proxy.CommonProxy;
import panda.gotwood.proxy.ProxyClient;
import panda.gotwood.registry.BlockRegistry;
import panda.gotwood.registry.ItemRegistry;
import panda.gotwood.registry.MasterRegistrar;
import panda.gotwood.util.WoodMaterial;
import panda.gotwood.util.WoodMaterials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ExistingSubstitutionException;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = GotWood.MODID, name = GotWood.NAME, version = GotWood.VERSION)


public class GotWood
{
	public static final String MODID = "gotwood";
	public static final String VERSION = "0.12.0";

	public static final String NAME = "Got Wood?";
	@SidedProxy(serverSide = "panda.gotwood.proxy.ProxyServer", clientSide = "panda.gotwood.proxy.ProxyClient")
	public static CommonProxy proxy;

	public static ProxyClient PROXY = null;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		MasterRegistrar.callRegistry(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) throws ExistingSubstitutionException
	{ 
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
		((SpecialFire)BlockRegistry.specialfire).init();
	}  
	
	public static final CreativeTabs TreeTab = new CreativeTabs(GotWood.MODID) {
	    @Override public Item getTabIconItem() {
	        return ItemRegistry.oak_seed;
	    }
	};

}