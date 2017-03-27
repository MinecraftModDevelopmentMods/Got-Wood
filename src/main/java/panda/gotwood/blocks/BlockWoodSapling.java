package panda.gotwood.blocks;

import java.util.List;
import java.util.Random;

import panda.gotwood.generation.WorldGenApple;
import panda.gotwood.generation.WorldGenBamboo;
import panda.gotwood.generation.WorldGenEbony;
import panda.gotwood.generation.WorldGenFir;
import panda.gotwood.generation.WorldGenMaple;
import panda.gotwood.generation.WorldGenPine;
import panda.gotwood.generation.WorldGenWillow;
import panda.gotwood.generation.WorldGenYew;
import panda.gotwood.registry.BlockRegistry;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;
import panda.gotwood.util.WoodMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWoodSapling extends BlockBush implements IOreDictionaryEntry, IGrowable, IPlantable 
{
	    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
	   
	    final WoodMaterial wood;
	    
	    public BlockWoodSapling(WoodMaterial wood)
	    {
	    	this.wood = wood;
	        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));
	        this.setTickRandomly(true);
	        this.setHardness(0.0F);
	        this.setSoundType(SoundType.PLANT);
	        this.setRegistryName(wood.getName()+"_sapling");
	        
	    }

	    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	    {
	        return SAPLING_AABB;
	    }
	    
	    @Override
	    public Item getItemDropped(IBlockState state, Random rand, int fortune)
	    {
	        return wood == WoodMaterials.bamboo? Item.getItemFromBlock(BlockRegistry.bamboo_sapling):Items.STICK;
	    }
	    
	    @Override
	    public int quantityDropped(Random random)
	    {
	        return random.nextBoolean() ? 2:1;
	    }


	    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	    {
	        if (!worldIn.isRemote)
	        {
	            super.updateTick(worldIn, pos, state, rand);

	            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
	            {
	                this.grow(worldIn, pos, state, rand);
	            }
	        }
	    }
	    

	    public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand)
	    {
	        if (((Integer)state.getValue(STAGE)).intValue() == 0)
	        {
	            worldIn.setBlockState(pos, this.getStateFromMeta(1), 4);
	        }
	        else
	        {
	        	this.generateTree(worldIn, pos, state, rand);
	        }
	    }

	    //TODO
	    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand)
	    {
	        
	    	if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
	        
	    	WorldGenerator worldgenerator = (WorldGenerator)( new WorldGenApple(true));///apple
	        int i = 0;
	        int j = 0;
	        boolean flag = false;

	        switch (wood.getName())
	        {
	            /*case SPRUCE:
	                label114:
	                if (!flag)
	                {
	                    i = 0;
	                    j = 0;
	                    worldgenerator = new WorldGenTaiga2(true);
	                }

	                break;
	            case BIRCH:
	                worldgenerator = new WorldGenBirchTree(true, false);
	                break;
					*/
	        case "apple":
	        	//already handled
	        	worldgenerator = new WorldGenApple(true);
                break;
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
	        	System.out.println("doot");
	        	worldgenerator = new WorldGenBamboo(true);
                break;
	            
	            	
	            	
	        }

	        IBlockState iblockstate2 = Blocks.AIR.getDefaultState();

	        if (flag)
	        {
	            worldIn.setBlockState(pos.add(i, 0, j), iblockstate2, 4);
	            worldIn.setBlockState(pos.add(i + 1, 0, j), iblockstate2, 4);
	            worldIn.setBlockState(pos.add(i, 0, j + 1), iblockstate2, 4);
	            worldIn.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate2, 4);
	        }
	        else
	        {
	            worldIn.setBlockState(pos, iblockstate2, 4);
	        }

	        if (!worldgenerator.generate(worldIn, rand, pos.add(i, 0, j)))
	        {
	            if (flag)
	            {
	                worldIn.setBlockState(pos.add(i, 0, j), state, 4);
	                worldIn.setBlockState(pos.add(i + 1, 0, j), state, 4);
	                worldIn.setBlockState(pos.add(i, 0, j + 1), state, 4);
	                worldIn.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
	            }
	            else
	            {
	                worldIn.setBlockState(pos, state, 4);
	            }
	        }
	    }


	    /**
	     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
	     * returns the metadata of the dropped item based on the old metadata of the block.
	     */
	    public int damageDropped(IBlockState state)
	    {
	        return 0;
	    }
	    
	    @Override
	    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
	        return EnumPlantType.Plains;
	    }

	    @Override
	    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
	        IBlockState state = world.getBlockState(pos);
	        if (state.getBlock() != this) return getDefaultState();
	        return state;
	    }

	    /**
	     * Whether this IGrowable can grow
	     */
	    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
	    {
	        return true;
	    }

	    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
	    {
	        return true;//(double)worldIn.rand.nextFloat() < 0.45D
	    }

	    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
	    {
	        this.grow(worldIn, pos, state, rand);
	    }

	    /**
	     * Convert the given metadata into a BlockState for this Block
	     */
	    public IBlockState getStateFromMeta(int meta)
	    {
	        return this.getDefaultState().withProperty(STAGE, Integer.valueOf(meta));
	    }

	    /**
	     * Convert the BlockState into the correct metadata value
	     */
	    public int getMetaFromState(IBlockState state)
	    { 
	        return ((Integer)state.getValue(STAGE)).intValue();
	    }

	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {STAGE});
	    }
	    
	    public WoodMaterial getWoodMaterial() {
			return this.wood;
		}
	    
	    @Override
		public String getOreDictionaryName() {
			return "sapling" + this.wood.getCapitalizedName();
		}
	   
}
