package panda.gotwood.block;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import panda.gotwood.generation.WorldGenApple;
import panda.gotwood.generation.WorldGenBamboo;
import panda.gotwood.generation.WorldGenEbony;
import panda.gotwood.generation.WorldGenFir;
import panda.gotwood.generation.WorldGenMaple;
import panda.gotwood.generation.WorldGenPalm;
import panda.gotwood.generation.WorldGenPine;
import panda.gotwood.generation.WorldGenRubber;
import panda.gotwood.generation.WorldGenWillow;
import panda.gotwood.generation.WorldGenYew;
import panda.gotwood.registry.BlockRegistry;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;
import panda.gotwood.util.WoodMaterials;

public final class BlockWoodSapling extends BlockBush implements IOreDictionaryEntry, IGrowable, IPlantable {
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

	private static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

	private final WoodMaterial wood;

	public BlockWoodSapling(WoodMaterial wood) {
		this.wood = wood;
		this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0));
		this.setTickRandomly(true);
		this.setHardness(0.0F);
		this.setSoundType(SoundType.PLANT);
		this.setRegistryName(wood.getName() + "_sapling");

	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SAPLING_AABB;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return wood == WoodMaterials.bamboo ? Item.getItemFromBlock(BlockRegistry.bamboo_sapling) : Items.STICK;
	}

	@Override
	public int quantityDropped(Random random) {
		return random.nextBoolean() ? 2 : 1;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			super.updateTick(worldIn, pos, state, rand);

			if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
				this.grow(worldIn, pos, state, rand);
			}
		}
	}

	public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (state.getValue(STAGE) == 0) {
			worldIn.setBlockState(pos, this.getStateFromMeta(1), 4);
		} else {
			this.generateTree(worldIn, pos, state, rand);
		}
	}

	public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		
		if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos))
			return;

		WorldGenerator worldgenerator;
		int i = 0;
		int j = 0;
		boolean flag = false; // for bamboo spreading

		switch (wood.getName()) {
			case "maple":
				worldgenerator = new WorldGenMaple(true);
				break;
			case "willow":
				worldgenerator = new WorldGenWillow();
				break;
			case "yew":
				worldgenerator = new WorldGenYew();
				break;
			case "ebony":
				worldgenerator = new WorldGenEbony(true);
				break;
			case "fir":
				worldgenerator = new WorldGenFir(true);
				break;
			case "pine":
				worldgenerator = new WorldGenPine(true);
				break;
			case "bamboo":
				worldgenerator = new WorldGenBamboo(true);
				break;
			case "palm":
				worldgenerator = new WorldGenPalm(true);
				break;
			case "rubber":
				worldgenerator = new WorldGenRubber(true);
				break;
			default:
				worldgenerator = new WorldGenApple(true);
		}

		IBlockState iblockstate2 = Blocks.AIR.getDefaultState();

		if (flag) {
			worldIn.setBlockState(pos.add(i, 0, j), iblockstate2, 4);
			worldIn.setBlockState(pos.add(i + 1, 0, j), iblockstate2, 4);
			worldIn.setBlockState(pos.add(i, 0, j + 1), iblockstate2, 4);
			worldIn.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate2, 4);
		} else {
			
			worldIn.setBlockToAir(pos);
		}
		
		if (!worldgenerator.generate(worldIn, rand, pos.add(i, 0, j))) {
			
			if (flag) {
				worldIn.setBlockState(pos.add(i, 0, j), state, 4);
				worldIn.setBlockState(pos.add(i + 1, 0, j), state, 4);
				worldIn.setBlockState(pos.add(i, 0, j + 1), state, 4);
				worldIn.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
			} else {
				worldIn.setBlockState(pos, state, 4);
			}
		}
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		
		return wood.getName().equals("palm") ? EnumPlantType.Desert : EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != this) {
			return getDefaultState();
		}
		return state;
	}

	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;//(double)worldIn.rand.nextFloat() < 0.45D
	}

	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		this.grow(worldIn, pos, state, rand);
	}
	
	@Override
	protected boolean canSustainBush(IBlockState state)
    {
        return false;
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(STAGE);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, STAGE);
	}

	public WoodMaterial getWoodMaterial() {
		return this.wood;
	}

	@Override
	public String getOreDictionaryName() {
		return "sapling" + this.wood.getCapitalizedName();
	}

}
