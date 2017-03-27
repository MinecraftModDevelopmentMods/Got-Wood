package panda.gotwood.generation;


import java.util.Random;

import panda.gotwood.blocks.BlockWoodLeaves;
import panda.gotwood.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;


public class WorldGenBamboo extends WorldGenAbstractTree
{
  private final int density = 64;
  private final int minTreeHeight = 12;
  private final int maxTreeHeight = 24;
  private final IBlockState leaves = BlockRegistry.bamboo_leaves.getDefaultState().withProperty(BlockWoodLeaves.DECAYABLE, Boolean.valueOf(true)).withProperty(BlockWoodLeaves.CHECK_DECAY, Boolean.valueOf(false));
  private final IBlockState log = BlockRegistry.bamboo_log.getDefaultState();
  
  public WorldGenBamboo(boolean doblocknotify)
  {
    super(doblocknotify);
  }
  
  public boolean generateClumps(World world, Random rand, BlockPos pos)
  {
    for (int loop = 0; loop < this.density; loop++)
    {
    	BlockPos newpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

      generate(world, rand, newpos);
    }
    return true;
  }
  
  public boolean generate(World world, Random rand, BlockPos position)
  {
    int height = rand.nextInt(3) + this.minTreeHeight;
    boolean flag = true;
    int i = position.getX();
    int j = position.getY();
    int k = position.getZ();
    if ((j >= 1) && (j + height + 1 <= this.maxTreeHeight))
    {
      for (int y = j; y <= j + 1 + height; y++)
      {
        byte b0 = 0;
        if (y >= j + height - 3) {
          b0 = 1;
        }
        for (int x = i - b0; (x <= i + b0) && (flag); x++) {
          for (int z = k - b0; (z <= k + b0) && (flag); z++) {
            if ((y >= 0) && (y < this.maxTreeHeight))
            {
              Block checkBlock = world.getBlockState(new BlockPos(x,y,z)).getBlock();
              if (!isReplaceable(world, x, y, z)) {
                flag = false;
              }
            }
            else
            {
              flag = false;
            }
          }
        }
      }
      if (!flag) {
        return false;
      }
      Block soil = world.getBlockState(new BlockPos(i,j-1,k)).getBlock();
      boolean isSoil = (soil != null) && (soil.canSustainPlant(Blocks.SAPLING.getDefaultState(), world,new BlockPos(i,j-1,k), EnumFacing.UP, (BlockSapling)Blocks.SAPLING));
      if ((isSoil) && (j < this.maxTreeHeight - height - 1))
      {
        soil.onPlantGrow(world.getBlockState(new BlockPos(i,j-1,k)),world, new BlockPos(i, j - 1, k),new BlockPos( i, j, k));
        for (int it = 0; it <= 3; it++)
        {
          int y = j + 5 + height - this.minTreeHeight + it * 2;
          for (int x = i - 1; x <= i + 1; x++)
          {
            int x2 = x - i;
            for (int z = k - 1; z <= k + 1; z++)
            {
              int z2 = z - k;
              
              Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
              if (((Math.abs(x2) != 1) || (Math.abs(z2) != 1)) && ((block == null) || (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(x,y,z)), world, new BlockPos(x,y,z))))) {
            	  world.setBlockState( new BlockPos(x, y, z), this.leaves);
              }
            }
          }
        }
        int y = j + height - 2;
        for (int x = i - 1; x <= i + 1; x++) {
          for (int z = k - 1; z <= k + 1; z++)
          {
            Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            if ((block == null) || (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(x,y,z)),world, new BlockPos(x,y,z)))) {
            	world.setBlockState( new BlockPos(x, y, z), this.leaves);
            }
          }
        }
        int x = i;
        int z = k;
        int it;
        for (it = 0; it <= 3; it++)
        {
          switch (it)
          {
          case 0: 
            x = i - 2;z = k; break;
          case 1: 
            x = i + 2;z = k; break;
          case 2: 
            x = i;z = k - 2; break;
          case 3: 
            x = i;z = k + 2; break;
          }
          Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
          if ((block == null) || (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(x,y,z)),world, new BlockPos(x, y, z)))) {
        	  world.setBlockState( new BlockPos(x, y, z), this.leaves);
          }
        }
        y = j + height;
        Block block = world.getBlockState(new BlockPos(i,y,k)).getBlock();
        if ((block == null) || (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(i,y,k)),world, new BlockPos(i, y, k)))) {
        	world.setBlockState( new BlockPos(i, y, k), this.leaves);
        }
        for (y = 0; y < height - 1; y++)
        {
          block = world.getBlockState(new BlockPos(i, j + y, k)).getBlock();
          if ((block.isAir(world.getBlockState(new BlockPos(i, j + y, k)),world, new BlockPos(it, j + y, k))) || (block.isLeaves(world.getBlockState(new BlockPos(i, j + y, k)),world, new BlockPos(i, j + y, k)))) {
        	  world.setBlockState( new BlockPos(i, j + y, k), this.log);
          }
        }
        return true;
      }
      return false;
    }
    return false;
  }
  
  protected boolean func_150523_a(Block block)
  {
    return (block.getMaterial(null) == Material.AIR) || (block.getMaterial(null) == Material.LEAVES) || (block == Blocks.GRASS) || (block == Blocks.DIRT) || (block == Blocks.LOG) || (block == Blocks.LOG2) || (block == Blocks.SAPLING) || (block == Blocks.VINE);
  }
  
  protected boolean isReplaceable(World world, int x, int y, int z)
  {
	  IBlockState state = world.getBlockState(new BlockPos(x,y,z));
	  Block block = state.getBlock();
      
    return (block.isAir(state,world, new BlockPos(x, y, z))) || (block.isLeaves(state,world, new BlockPos(x, y, z))) || (block.isWood(world, new BlockPos(x, y, z))) || (func_150523_a(block));
  }
}
