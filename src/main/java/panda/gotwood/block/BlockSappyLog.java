package panda.gotwood.block;

import com.mcmoddev.lib.material.MMDMaterial;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import panda.gotwood.registry.ItemRegistry;

public class BlockSappyLog extends BlockWoodLog {
	public static final PropertyInteger GENERATED = PropertyInteger.create("generated", 0, 1);

	public BlockSappyLog(MMDMaterial material) {
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(GENERATED, 0).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));

	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, LOG_AXIS, GENERATED);
	}

	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState();

		switch (meta) {
			case 0:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y).withProperty(GENERATED, 0);
				break;
			case 1:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X).withProperty(GENERATED, 0);
				break;
			case 2:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z).withProperty(GENERATED, 0);
				break;
			case 3:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y).withProperty(GENERATED, 1);
				break;
			case 4:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X).withProperty(GENERATED, 1);
				break;
			case 5:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z).withProperty(GENERATED, 1);
				break;
			default:
				iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE).withProperty(GENERATED, 0);
		}

		return iblockstate;
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

		switch (state.getValue(LOG_AXIS)) {
			case X:
				i = 0;
				break;
			case Z:
				i |= 1;
				break;
			default:
				i |= 2;
		}

		int j = state.getValue(GENERATED);
		i += j * 3;

		return i;
	}

	@Override //&& state.getValue(GENERATED)==1
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = playerIn.getHeldItem(hand);

		if (heldItem.isEmpty()) {
			return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
		}

		if (!worldIn.isRemote && heldItem.getItem() == Items.BOWL && facing != EnumFacing.UP && facing != EnumFacing.DOWN) {

			ItemStack itemstack1 = new ItemStack(ItemRegistry.rubber_sap);

			heldItem.shrink(1);
			if (heldItem.isEmpty()) {
				playerIn.setHeldItem(hand, itemstack1);
			} else if (!playerIn.inventory.addItemStackToInventory(itemstack1)) {
				playerIn.dropItem(itemstack1, false);
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);

	}

}
