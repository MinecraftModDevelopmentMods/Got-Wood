package panda.gotwood;

import panda.gotwood.blocks.BlockPlanks;
import panda.gotwood.blocks.SpecialFire;
import panda.gotwood.blocks.tileentities.TileTreeTap;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ExistingSubstitutionException;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = GotWood.MODID, name = GotWood.NAME, version = GotWood.VERSION)


public class GotWood
{
	public static final String MODID = "gotwood";
	public static final String VERSION = "0.20.0";

	public static final String NAME = "Got Wood?";
	@SidedProxy(serverSide = "panda.gotwood.proxy.ProxyServer", clientSide = "panda.gotwood.proxy.ProxyClient")
	public static CommonProxy Commonproxy;

	public static ProxyClient Clientproxy = null;
	
	@SubscribeEvent
    public void registerSpecialRecipes(RegistryEvent.Register<IRecipe> event){
		if(ConfigurationHandler.retrieveSaplingsMode == 2){
			ResourceLocation group = new ResourceLocation(GotWood.MODID);
			GameRegistry.addShapelessRecipe(new ResourceLocation("oak_seed"),group,new ItemStack(Blocks.SAPLING,1,0), Ingredient.fromStacks(new ItemStack(ItemRegistry.oak_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("spruce_seed"),group,new ItemStack(Blocks.SAPLING,1,1), Ingredient.fromStacks(new ItemStack(ItemRegistry.spruce_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("birch_seed"),group,new ItemStack(Blocks.SAPLING,1,2), Ingredient.fromStacks(new ItemStack(ItemRegistry.birch_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("jungle_seed"),group,new ItemStack(Blocks.SAPLING,1,3), Ingredient.fromStacks(new ItemStack(ItemRegistry.jungle_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("acacia_seed"),group,new ItemStack(Blocks.SAPLING,1,4), Ingredient.fromStacks(new ItemStack(ItemRegistry.acacia_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("dark_oak_seed"),group,new ItemStack(Blocks.SAPLING,1,5), Ingredient.fromStacks(new ItemStack(ItemRegistry.dark_oak_seed)));
			
			GameRegistry.addShapelessRecipe(new ResourceLocation("apple_seed"),group,new ItemStack(BlockRegistry.apple_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.apple_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("maple_seed"),group,new ItemStack(BlockRegistry.maple_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.maple_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("pine_seed"),group,new ItemStack(BlockRegistry.pine_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.pine_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("willow_seed"),group,new ItemStack(BlockRegistry.willow_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.willow_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("yew_seed"),group,new ItemStack(BlockRegistry.yew_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.yew_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("ebony_seed"),group,new ItemStack(BlockRegistry.ebony_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.ebony_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("fir_seed"),group,new ItemStack(BlockRegistry.fir_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.fir_seed)));
		}
	}
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{Loader.isModLoaded("");
		MasterRegistrar.callRegistry(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) throws ExistingSubstitutionException
	{ 
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
		((SpecialFire)BlockRegistry.specialfire).init();
		GameRegistry.registerTileEntity(TileTreeTap.class, "gotwood:tree_tap");
	}  
	
	public static final CreativeTabs TreeTab = new CreativeTabs(GotWood.MODID) {
	    @Override public ItemStack getTabIconItem() {
	        return new ItemStack(ItemRegistry.oak_seed);
	    }
	};

}