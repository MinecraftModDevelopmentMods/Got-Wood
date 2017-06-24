package panda.gotwood.blocks;

import java.util.Random;

import panda.gotwood.util.WoodMaterial;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class BlockWoodDoor extends BlockDoor  {

	
	private final WoodMaterial wood;

	public Item doorItem;

	/**
	 *
	 * @param metal
	 */
	public BlockWoodDoor(WoodMaterial wood) {
		super(Material.WOOD);
		this.setSoundType(SoundType.WOOD);
		this.wood = wood;
		this.blockHardness = wood.getPlankBlockHardness();
		this.blockResistance = wood.getBlastResistance();
		this.setHarvestLevel("axe", wood.getRequiredHarvestLevel());
		this.disableStats();
		this.setRegistryName(wood.getName()+"_door");
		//this.setDoorItem(door);
	}
	
	

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target,
			World world, BlockPos pos, EntityPlayer player) {
		// TODO Auto-generated method stub
		return new ItemStack(getDoorItem());
	}
	
	public void setDoorItem(Item door){
		this.doorItem = door;
	}



	private Item getDoorItem() {
		if (this.doorItem == null) {
			FMLLog.severe("getting item for door: %s, %s", this.getRegistryName().getResourceDomain(), this.wood.getName() + "_door");
			this.doorItem = Item.REGISTRY.getObject(new ResourceLocation(this.getRegistryName().getResourceDomain(), this.wood.getName() + "_door"));
		}

		return this.doorItem;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getItem(final World w, final BlockPos c, final IBlockState bs) {
		return new ItemStack(this.getDoorItem());
	}

	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return (state.getValue(BlockDoor.HALF) == EnumDoorHalf.UPPER) ? null : this.getDoorItem();
	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos coord, IBlockState blockstate,
									final EntityPlayer player,
									final EnumHand hand, ItemStack heldItem,
									final EnumFacing face,
									final float partialX, final float partialY, final float partialZ) {

		final BlockPos pos = (blockstate.getValue(BlockDoor.HALF) == EnumDoorHalf.LOWER) ? coord : coord.down();
		final IBlockState bs = coord.equals(pos) ? blockstate : world.getBlockState(pos);
		if (bs.getBlock() != this)
			return false;
		blockstate = bs.cycleProperty(BlockDoor.OPEN);
		world.setBlockState(pos, blockstate, 2);
		world.markBlockRangeForRenderUpdate(pos, coord);
		world.playEvent(player, ((Boolean) blockstate.getValue(BlockDoor.OPEN)) ? 1012 : 1006, coord, 0);
		return true;
	}

	public WoodMaterial getWoodMaterial() {
		return this.wood;
	}
}