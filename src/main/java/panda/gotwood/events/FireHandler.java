package panda.gotwood.events;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.gotwood.registry.BlockRegistry;

public class FireHandler {
	@SubscribeEvent
	//@SideOnly(Side.SERVER)
	public void loadEvent(WorldEvent.Load event)
	{
		World world = event.getWorld();
		world.addEventListener(new ConsumedByFireListener());
	}


	@SubscribeEvent
	public void onLeftClickEvent(PlayerInteractEvent.EntityInteract.LeftClickBlock event) {

		if (event.getEntityPlayer().isCreative())
		{
			extinguishFire((EntityPlayer)null, event.getPos(), event.getFace(),event.getWorld());
		}
		else
		{
			IBlockState iblockstate = event.getWorld().getBlockState(event.getPos());
			Block block = iblockstate.getBlock();

			if (!iblockstate.getBlock().isAir(iblockstate, event.getWorld(), event.getPos()))
			{

				block.onBlockClicked(event.getWorld(), event.getPos(), event.getEntityPlayer());
				extinguishFire((EntityPlayer)null, event.getPos(), event.getFace(),event.getWorld());

			}
		}
	}

	@SubscribeEvent
	public void onRightClickEvent(PlayerInteractEvent.EntityInteract.RightClickBlock event) {

		if (event.getEntityPlayer().isCreative())
		{
			extinguishFire((EntityPlayer)null, event.getPos(), event.getFace(),event.getWorld());
		}
		else
		{
			IBlockState iblockstate = event.getWorld().getBlockState(event.getPos());
			Block block = iblockstate.getBlock();

			if (!iblockstate.getBlock().isAir(iblockstate, event.getWorld(), event.getPos()))
			{
				block.onBlockClicked(event.getWorld(), event.getPos(), event.getEntityPlayer());
				extinguishFire((EntityPlayer)null, event.getPos(), event.getFace(),event.getWorld());
			}
		}
	}




	public boolean extinguishFire(@Nullable EntityPlayer player, BlockPos pos, EnumFacing side, World world)
	{
		pos = pos.offset(side);

		if (world.getBlockState(pos).getBlock() == BlockRegistry.specialfire)
		{
			world.playEvent(player, 1009, pos, 0);
			world.setBlockToAir(pos);
			return true;
		}
		else
		{
			return false;
		}
	}

}
