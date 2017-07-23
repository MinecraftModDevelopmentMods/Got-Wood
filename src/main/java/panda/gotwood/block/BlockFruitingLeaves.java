package panda.gotwood.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import panda.gotwood.events.ConfigurationHandler;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;
import panda.gotwood.util.WoodMaterials;

public class BlockFruitingLeaves extends BlockLeaves implements IOreDictionaryEntry {
	public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");

	public static final PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");

	public static final PropertyBool FRUITING = PropertyBool.create("fruit");

	final WoodMaterial wood;

	public BlockFruitingLeaves(WoodMaterial wood) {
		this.wood = wood;
		Blocks.FIRE.setFireInfo(this, 30, 60);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DECAYABLE, false).withProperty(CHECK_DECAY, false).withProperty(FRUITING, false));
		this.setRegistryName(wood.getName() + "_leaves");
		this.setTickRandomly(true);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.AIR;
	}

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		super.randomTick(worldIn, pos, state, random);
		if (state.getValue(FRUITING)) {
			return;
		}

		if (state.getValue(DECAYABLE) && random.nextInt(ConfigurationHandler.appleFruitingChance) == 0) {
			int adjacent = 0;
			if (worldIn.getBlockState(pos.up()).getBlock() == this) {
				if (worldIn.getBlockState(pos.up()).getValue(FRUITING)) {
					adjacent++;
				}
			}
			if (worldIn.getBlockState(pos.down()).getBlock() == this) {
				if (worldIn.getBlockState(pos.down()).getValue(FRUITING)) {
					adjacent++;
				}
			}
			if (worldIn.getBlockState(pos.east()).getBlock() == this) {
				if (worldIn.getBlockState(pos.east()).getValue(FRUITING)) {
					adjacent++;
				}
			}
			if (worldIn.getBlockState(pos.south()).getBlock() == this) {
				if (worldIn.getBlockState(pos.south()).getValue(FRUITING)) {
					adjacent++;
				}
			}
			if (worldIn.getBlockState(pos.north()).getBlock() == this) {
				if (worldIn.getBlockState(pos.north()).getValue(FRUITING)) {
					adjacent++;
				}
			}
			if (worldIn.getBlockState(pos.west()).getBlock() == this) {
				if (worldIn.getBlockState(pos.west()).getValue(FRUITING)) {
					adjacent++;
				}
			}

			if (adjacent <= 2) {
				worldIn.setBlockState(pos, state.withProperty(FRUITING, true), 2);
			}
		}

	}

	@Override
	public java.util.List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		java.util.List<ItemStack> ret = new java.util.ArrayList<>();
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		int chance = -1;
		if (state.getValue(FRUITING)) {
			if (wood == WoodMaterials.apple) {//Allow for other fruit trees later
				chance = getModifiedChance(ConfigurationHandler.appleChance, fortune, ConfigurationHandler.appleDropFortuneDecrement, ConfigurationHandler.appleDropMinChance);

				ret.add(new ItemStack(Items.APPLE));

				if (rand.nextInt(chance) == 0) {
					ret.add(new ItemStack(Items.APPLE));
				}

				chance = getModifiedChance(ConfigurationHandler.goldenDropChance, fortune, ConfigurationHandler.goldenDropFortuneDecrement, ConfigurationHandler.goldenDropMinChance);
				if (rand.nextInt(chance) == 0) {
					ret.add(new ItemStack(Items.GOLDEN_APPLE));
				}
			}
		}

		this.captureDrops(true);

		ret.addAll(this.captureDrops(false));
		return ret;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if (!worldIn.isRemote) {
			for (ItemStack item : this.getDrops(worldIn, pos, state, 0)) {
				worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.NEUTRAL, 0.6F, 0.8F / (worldIn.rand.nextFloat() * 0.4F + 0.8F));
				EntityItem entityitem = new EntityItem(worldIn, pos.getX() + 0.5 + facing.getFrontOffsetX() * 0.7, pos.getY() + 0.5 + facing.getFrontOffsetY() * 0.7, pos.getZ() + 0.5 + facing.getFrontOffsetZ() * 0.7, item);
				entityitem.motionX *= 0.2;
				entityitem.motionY = 0;
				entityitem.motionZ *= 0.2;
				worldIn.spawnEntity(entityitem);
			}
		}
		worldIn.setBlockState(pos, state.withProperty(FRUITING, false), 2);
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	private int getModifiedChance(int chance, int fortune, int decrement, int minchance) {

		int modifiedchance = chance;
		if (fortune > 0) {
			modifiedchance -= decrement << fortune;
			if (modifiedchance < minchance) {
				modifiedchance = minchance;
			}
		}
		return modifiedchance;
	}

	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return 16777215;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(IBlockState state) {
		return 16777215;
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
		return 16777215;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFoliage(IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}

	public WoodMaterial getWoodMaterial() {
		return this.wood;
	}

	@Override
	public String getOreDictionaryName() {
		return "leavesFruit" + this.wood.getCapitalizedName();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FRUITING, meta % 2 == 1).withProperty(DECAYABLE, meta == 2 || meta == 3 || meta == 6 || meta == 7).withProperty(CHECK_DECAY, meta > 3);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i += state.getValue(FRUITING) ? 1 : 0;
		i += state.getValue(DECAYABLE) ? 2 : 0;
		i += state.getValue(CHECK_DECAY) ? 4 : 0;
		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE, FRUITING);
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		return java.util.Arrays.asList(new ItemStack(this, 1));
	}

}
