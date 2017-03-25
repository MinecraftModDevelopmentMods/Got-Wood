package panda.gotwood.registry;

import java.util.Iterator;
import java.util.List;

import panda.gotwood.GotWood;
import panda.gotwood.blocks.BlockWoodDoor;
import panda.gotwood.blocks.SpecialFire;
import panda.gotwood.events.BlockBreakHandler;
import panda.gotwood.events.ConfigurationHandler;
import panda.gotwood.events.FireHandler;
import panda.gotwood.items.itemblocks.ItemWoodDoor;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterials;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

public final class MasterRegistrar {
	
	public static void register(FMLPreInitializationEvent e, List<?> list) {
		Iterator<?> iterator = list.iterator();

		while (iterator.hasNext()) {
			Object k = iterator.next();
			if (k instanceof Block) {
				Block block = (Block) k;
				GameRegistry.register(block);
				if(!(k instanceof SpecialFire)&& !(k instanceof BlockWoodDoor)){
					((Block) k).setCreativeTab(GotWood.TreeTab);
				}
				block.setUnlocalizedName(GotWood.MODID + "." + block.getRegistryName().getResourcePath());
				if (Item.getItemFromBlock(block) == null)
					GameRegistry.register(new ItemBlock(block), block.getRegistryName());
			} else if (k instanceof Item) {
				GameRegistry.register((Item) k);
				
					((Item) k).setCreativeTab(GotWood.TreeTab);
				
				
				((Item) k).setUnlocalizedName(GotWood.MODID + "." + ((Item) k).getRegistryName().getResourcePath());
			}
			
		}
		
		if (e.getSide() == Side.CLIENT)
			registerModels(list);
	}
	
	public static void registerModels(List<?> list) {
		Iterator<?> iterator = list.iterator();

		while (iterator.hasNext()) {
			Object k = iterator.next();
			
			Item item = null;
			if (k instanceof Block) {
				item = Item.getItemFromBlock((Block) k);
				BlockRegistry.blockRegistry.put(item.getUnlocalizedName().substring(13, item.getUnlocalizedName().length()), (Block) k);
				System.out.println(item.getUnlocalizedName().substring(13, item.getUnlocalizedName().length()));
			} else if (k instanceof Item) {
				item = (Item) k;
				ItemRegistry.itemRegistry.put(item.getUnlocalizedName().substring(13, item.getUnlocalizedName().length()), item);
				System.out.println(item.getUnlocalizedName().substring(13, item.getUnlocalizedName().length()));
			}

			if (item != null ) {
				
				ModelLoader.setCustomModelResourceLocation(item, 0,new ModelResourceLocation(item.getRegistryName(), "inventory"));
				// TODO: Make this support multiple oredicts
				if (item instanceof IOreDictionaryEntry){
					OreDictionary.registerOre(((IOreDictionaryEntry) item).getOreDictionaryName(), item);
				}
			}
		}

	}

	public static void callRegistry(FMLPreInitializationEvent e) {
		ConfigurationHandler.init(e);
		
		MinecraftForge.EVENT_BUS.register(new BlockBreakHandler());
		MinecraftForge.EVENT_BUS.register(new FireHandler());

		WoodMaterials.init();
		
		register(e, BlockRegistry.getBlockList());
		register(e, ItemRegistry.getItemList());
		
		RecipeRegistry.init();
		
		
		
		//RenderingRegistry.registerEntityRenderingHandler(MyEntityFireworkRocket.class, RenderFireworkEntity.INSTANCE);
		//EntityRegistry.registerModEntity(new ResourceLocation("simplecorn:entitypopfirework"),MyEntityFireworkRocket.class, "entitypopfirework", 132, Corn.instance, 64, 3, true);
		
		//MapGenStructureIO.registerStructureComponent(ComponentCornField.class, "Vicf");
		//VillagerRegistry.instance().registerVillageCreationHandler(new CornWorldGen());
		//RecipeSorter.register("simplecorn:fireworks", MyRecipeFireworks.class, Category.SHAPELESS, "after:minecraft:fireworks");
	}
}