package panda.gotwood;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
<<<<<<< HEAD
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Loader;
=======
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import panda.gotwood.block.entity.TileEntityTreeTap;
import panda.gotwood.events.ConfigurationHandler;
import panda.gotwood.generation.WorldGenerator;
import panda.gotwood.proxy.CommonProxy;
import panda.gotwood.registry.BlockRegistry;
import panda.gotwood.registry.ItemRegistry;
import panda.gotwood.registry.MasterRegistrar;

@Mod(modid = GotWood.ID, name = GotWood.NAME, version = GotWood.VERSION)
@Mod.EventBusSubscriber
public final class GotWood {
	public static final String ID = "gotwood";

<<<<<<< HEAD
public class GotWood
{
	public static final String MODID = "gotwood";
	public static final String VERSION = "0.16.3";
=======
	public static final String VERSION = "0.24.0";
>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc

	public static final String NAME = "Got Wood?";

	@SidedProxy(clientSide = "panda.gotwood.proxy.ClientProxy", serverSide = "panda.gotwood.proxy.ServerProxy")
	public static CommonProxy proxy;

	@SubscribeEvent
	public void registerSpecialRecipes(RegistryEvent.Register<IRecipe> event) {
		if (ConfigurationHandler.retrieveSaplingsMode == 2) {
			ResourceLocation group = new ResourceLocation(GotWood.ID);
			GameRegistry.addShapelessRecipe(new ResourceLocation("oak_seed"), group, new ItemStack(Blocks.SAPLING, 1, 0), Ingredient.fromStacks(new ItemStack(ItemRegistry.oak_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("spruce_seed"), group, new ItemStack(Blocks.SAPLING, 1, 1), Ingredient.fromStacks(new ItemStack(ItemRegistry.spruce_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("birch_seed"), group, new ItemStack(Blocks.SAPLING, 1, 2), Ingredient.fromStacks(new ItemStack(ItemRegistry.birch_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("jungle_seed"), group, new ItemStack(Blocks.SAPLING, 1, 3), Ingredient.fromStacks(new ItemStack(ItemRegistry.jungle_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("acacia_seed"), group, new ItemStack(Blocks.SAPLING, 1, 4), Ingredient.fromStacks(new ItemStack(ItemRegistry.acacia_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("dark_oak_seed"), group, new ItemStack(Blocks.SAPLING, 1, 5), Ingredient.fromStacks(new ItemStack(ItemRegistry.dark_oak_seed)));

			GameRegistry.addShapelessRecipe(new ResourceLocation("apple_seed"), group, new ItemStack(BlockRegistry.apple_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.apple_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("maple_seed"), group, new ItemStack(BlockRegistry.maple_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.maple_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("pine_seed"), group, new ItemStack(BlockRegistry.pine_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.pine_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("willow_seed"), group, new ItemStack(BlockRegistry.willow_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.willow_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("yew_seed"), group, new ItemStack(BlockRegistry.yew_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.yew_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("ebony_seed"), group, new ItemStack(BlockRegistry.ebony_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.ebony_seed)));
			GameRegistry.addShapelessRecipe(new ResourceLocation("fir_seed"), group, new ItemStack(BlockRegistry.fir_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.fir_seed)));
		}
	}

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		MasterRegistrar.callRegistry(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
<<<<<<< HEAD
		((SpecialFire)BlockRegistry.specialfire).init();
		GameRegistry.registerTileEntity(TileTreeTap.class, "gotwood:tree_tap");
	}  
	
	public static final CreativeTabs TreeTab = new CreativeTabs(GotWood.MODID) {
	    @Override public Item getTabIconItem() {
	        return ItemRegistry.oak_seed;
	    }
	};
=======
		BlockRegistry.specialfire.initInfo();
		GameRegistry.registerTileEntity(TileEntityTreeTap.class, "gotwood:tree_tap");
	}
>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc

	public static final CreativeTabs TREE_TAB = new CreativeTabs(GotWood.ID) {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemRegistry.oak_seed);
		}
	};
}
