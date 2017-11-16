package panda.gotwood.block;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import panda.gotwood.events.ConfigurationHandler;
import panda.gotwood.generation.WorldGenApple;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;

public class BlockFruitSapling extends BlockBush implements IOreDictionaryEntry, IGrowable, IPlantable{

    public static final PropertyInteger AGE = PropertyInteger.create("stage", 0, 4);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1, 0.0D, 0.1, 0.9, 0.8, 0.9D);
    protected final WoodMaterial wood;
    
    public BlockFruitSapling(WoodMaterial wood)
    {
    	this.wood = wood;
		this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
		this.setTickRandomly(true);
		this.setHardness(0.0F);
		this.setSoundType(SoundType.PLANT);
		this.setRegistryName(wood.getName() + "_sapling");
    }
    
    @Override
	protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == this;
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SAPLING_AABB;
    }


    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                this.grow(worldIn,rand, pos, state);
            }
        }
    }
    
    @Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) 
	{
		if(world.getBlockState(pos.up()).getBlock() == this){
			world.setBlockToAir(pos.up());
			return;
		}
		if(world.getBlockState(pos.down()).getBlock() == this){
			world.setBlockToAir(pos.down());
			return;
		}
		
	}

    
    @Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		return super.canBlockStay(worldIn, pos, state)||worldIn.getBlockState(pos.down()).getBlock() == this;
	}

	@Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
    }
    
    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
    	int s = state.getValue(AGE);
        if (s < 2)
        {
            worldIn.setBlockState(pos, state.cycleProperty(AGE));
        }
    	else if(s == 2)
    	{
    		if(worldIn.getBlockState(pos.up()).getMaterial().isReplaceable()){
    			worldIn.setBlockState(pos, state.cycleProperty(AGE));
    			worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(AGE, 4));
    		}
    	}
        else
        {
        	if(s==4){
        		pos = pos.down();
        	}
        	this.generateTree(worldIn, pos, state, rand);
        }
    }

   
    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
        
        
        WorldGenerator worldgenerator;

		switch (wood.getName()) {
			case "banana":
			default:
				worldgenerator = new WorldGenApple(true);
		}
		
		worldIn.setBlockToAir(pos);
		worldIn.setBlockToAir(pos.up());
	
		if (!worldgenerator.generate(worldIn, rand, pos)) {
			worldIn.setBlockState(pos, state, 4);
		}
    }
    
    @Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state==this.getStateFromMeta(3)?null:Items.STICK;
	}

	@Override
	public int quantityDropped(Random random) {
		return random.nextBoolean() ? 2 : 1;
	}


    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }
    
    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return ConfigurationHandler.allowBonemealFruitSaplings?(double)worldIn.rand.nextFloat() < 0.40D : false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
    	return this.getDefaultState().withProperty(AGE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
    	return state.getValue(AGE);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this,AGE);
    }
    public WoodMaterial getWoodMaterial() {
		return this.wood;
	}

	@Override
	public String getOreDictionaryName() {
		return "saplingFruit";
	}
}
