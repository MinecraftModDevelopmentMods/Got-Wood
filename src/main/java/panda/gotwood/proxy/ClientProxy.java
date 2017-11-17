package panda.gotwood.proxy;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import panda.gotwood.GotWood;
import panda.gotwood.init.Blocks;
import panda.gotwood.init.Fluids;
import panda.gotwood.init.Items;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		for (final String name : Fluids.getFluidBlockRegistry().keySet()) {
			final Block block = Fluids.getFluidBlockByName(name);
			final Item item = Item.getItemFromBlock(block);
			if (!item.getRegistryName().getResourceDomain().equals(GotWood.MODID))
				continue;
			final ModelResourceLocation fluidModelLocation = new ModelResourceLocation(item.getRegistryName().getResourceDomain() + ":" + name, "fluid");
			ModelBakery.registerItemVariants(item);
			ModelLoader.setCustomMeshDefinition(item, stack -> fluidModelLocation);
			ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
				@Override
				protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
					return fluidModelLocation;
				}
			});
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);

		for (final String name : Items.getItemRegistry().keySet()) {
			registerRenderOuter(Items.getItemByName(name));
		}

		for (final String name : Blocks.getBlockRegistry().keySet()) {
			registerRenderOuter(Blocks.getBlockByName(name));
		}
	}

	public void registerRenderOuter(Item item) {
		if (item != null) {
			registerRender(item, Items.getNameOfItem(item));
		}
	}

	public void registerRenderOuter(Block block) {
		if ((block instanceof BlockDoor) || (block instanceof BlockSlab))
			return; // do not add door blocks or slabs

		if (block != null) {
			registerRender(Item.getItemFromBlock(block), Blocks.getNameOfBlock(block));
		}
	}

	public void registerRender(Item item, String name) {
		final ItemModelMesher itemModelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		if (!item.getRegistryName().getResourceDomain().equals(GotWood.MODID))
			return;
		itemModelMesher.register(item, 0, new ModelResourceLocation(new ResourceLocation(item.getRegistryName().getResourceDomain(), name), "inventory"));
	}
}
