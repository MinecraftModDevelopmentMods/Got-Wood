package panda.gotwood.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import panda.gotwood.blocks.tileentities.TileTreeTap;
import panda.gotwood.util.TreeTapRenderer;

public class RenderProxy {
	public static void init(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileTreeTap.class, new TreeTapRenderer());
	}
}
