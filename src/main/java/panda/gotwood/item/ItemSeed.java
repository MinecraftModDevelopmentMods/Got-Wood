package panda.gotwood.item;

import com.mcmoddev.lib.item.GenericMMDItem;
import com.mcmoddev.lib.material.MMDMaterial;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import panda.gotwood.GotWood;
import panda.gotwood.util.MaterialNames;

public class ItemSeed extends GenericMMDItem  implements IPlantable {


	public final IBlockState sapling;

	public ItemSeed(MMDMaterial material) {
		super(material);
		this.sapling = getSaplingState();
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = playerIn.getHeldItem(hand);

		if (heldItem.isEmpty()) {
			return super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		}
		System.out.println(this.getSaplingState());
		if (facing == EnumFacing.UP && playerIn.canPlayerEdit(pos.offset(facing), facing, heldItem) && worldIn.isAirBlock(pos.up()) && worldIn.getBlockState(pos).getBlock().canSustainPlant(worldIn.getBlockState(pos), worldIn, pos, facing, (IPlantable) this.getSaplingState().getBlock())) {
			worldIn.setBlockState(pos.up(), this.getSaplingState());
			worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.NEUTRAL, 1F, 1F);
			heldItem.shrink(1);
			return EnumActionResult.SUCCESS;
		} else {
			if (playerIn.canPlayerEdit(pos.offset(facing), facing, heldItem) && worldIn.isAirBlock(pos.up()) && worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos)) {
				worldIn.setBlockState(pos, this.getSaplingState());
				worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.NEUTRAL, 1F, 1F);
				heldItem.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		}
	}

	private IBlockState getSaplingState() {
		if (this.getMMDMaterial().getName() == MaterialNames.ACACIA || this.getMMDMaterial().getName() == MaterialNames.BIRCH || this.getMMDMaterial().getName() == MaterialNames.DARKOAK || this.getMMDMaterial().getName() == MaterialNames.JUNGLE || this.getMMDMaterial().getName() == MaterialNames.OAK || this.getMMDMaterial().getName() == MaterialNames.SPRUCE) {
			return Blocks.SAPLING.getStateFromMeta(wood.getMeta());//TODO
		} else
			return Block.REGISTRY.getObject(new ResourceLocation(GotWood.MODID, this.getMMDMaterial().getName() + "_sapling")).getDefaultState();
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return getSaplingState();
	}
}
