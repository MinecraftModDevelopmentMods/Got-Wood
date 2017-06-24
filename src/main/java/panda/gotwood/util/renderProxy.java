package panda.gotwood.util;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import panda.gotwood.blocks.tileentities.TileTreeTap;

public class renderProxy {
	public static void init(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileTreeTap.class, new TreeTapRenderer());
	}
}
