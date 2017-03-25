package panda.gotwood.events;

import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FireHandler {
	@SubscribeEvent
	//@SideOnly(Side.SERVER)
    public void loadEvent(WorldEvent.Load event)
    {
		//System.out.println("doot");
		World world = event.getWorld();
		world.addEventListener(new ConsumedByFireListener());
    }
}
