package panda.gotwood.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import panda.gotwood.registry.BlockRegistry;
import panda.gotwood.registry.ItemRegistry;
import panda.gotwood.util.IFireDrops;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpecialFire extends BlockFire {
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool UPPER = PropertyBool.create("up");
    private final Map<Block, Integer> encouragements = Maps.<Block, Integer>newIdentityHashMap();
    private final Map<Block, Integer> flammabilities = Maps.<Block, Integer>newIdentityHashMap();

    /**
     * Get the actual Block state of this Block at the given position. This applies properties not visible in the
     * metadata, such as fence connections.
     */
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP) && !((BlockFire) BlockRegistry.specialfire).canCatchFire(worldIn, pos.down(), EnumFacing.UP))
        {
            return state.withProperty(NORTH, this.canCatchFire(worldIn, pos.north(), EnumFacing.SOUTH))
                        .withProperty(EAST,  this.canCatchFire(worldIn, pos.east(), EnumFacing.WEST))
                        .withProperty(SOUTH, this.canCatchFire(worldIn, pos.south(), EnumFacing.NORTH))
                        .withProperty(WEST,  this.canCatchFire(worldIn, pos.west(), EnumFacing.EAST))
                        .withProperty(UPPER, this.canCatchFire(worldIn, pos.up(), EnumFacing.DOWN));
        }
        return this.getDefaultState();
    }

    public SpecialFire()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(UPPER, Boolean.valueOf(false)));
        this.setTickRandomly(true);
        this.setHardness(0.0F);
        this.setLightLevel(1.0F);
        this.setSoundType(SoundType.CLOTH);
        this.disableStats();
        this.setRegistryName("fire");

    }

    @Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos,IBlockState state, Entity entityIn) {
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		
		
		 boolean flag1 = entityIn.isWet();


             if (!flag1)
             {
                entityIn.setFire(8);
             }

	}

	public static void init()
    {
    	((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.PLANKS, 5, 20);
    	((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.DOUBLE_WOODEN_SLAB, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.WOODEN_SLAB, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.OAK_FENCE_GATE, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.SPRUCE_FENCE_GATE, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.BIRCH_FENCE_GATE, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.JUNGLE_FENCE_GATE, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.DARK_OAK_FENCE_GATE, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.ACACIA_FENCE_GATE, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.OAK_FENCE, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.SPRUCE_FENCE, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.BIRCH_FENCE, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.JUNGLE_FENCE, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.DARK_OAK_FENCE, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.ACACIA_FENCE, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.OAK_STAIRS, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.BIRCH_STAIRS, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.SPRUCE_STAIRS, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.JUNGLE_STAIRS, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.ACACIA_STAIRS, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.DARK_OAK_STAIRS, 5, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.LOG, 5, 5);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.LOG2, 5, 5);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.LEAVES, 30, 60);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.LEAVES2, 30, 60);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.BOOKSHELF, 30, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.TNT, 15, 100);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.TALLGRASS, 60, 100);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.DOUBLE_PLANT, 60, 100);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.YELLOW_FLOWER, 60, 100);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.RED_FLOWER, 60, 100);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.DEADBUSH, 60, 100);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.WOOL, 30, 60);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.VINE, 15, 100);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.COAL_BLOCK, 5, 5);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.HAY_BLOCK, 60, 20);
        ((BlockFire) BlockRegistry.specialfire).setFireInfo(Blocks.CARPET, 60, 20);
       // ReflectionHelper.getPrivateValue(BlockFire.class, Blocks.FIRE, "encouragements");
        //ReflectionHelper.getPrivateValue(BlockFire.class, Blocks.FIRE, "flammabilities");
    }

    public void setFireInfo(Block blockIn, int encouragement, int flammability)
    {
        if (blockIn == Blocks.AIR) throw new IllegalArgumentException("Tried to set air on fire... This is bad.");
        this.encouragements.put(blockIn, Integer.valueOf(encouragement));
        this.flammabilities.put(blockIn, Integer.valueOf(flammability));
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 0;
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World worldIn)
    {
        return 30;
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (worldIn.getGameRules().getBoolean("doFireTick"))
        {
        	
            if (!this.canPlaceBlockAt(worldIn, pos))
            {
                worldIn.setBlockToAir(pos);
            }

            Block block = worldIn.getBlockState(pos.down()).getBlock();
            boolean flag = block.isFireSource(worldIn, pos.down(), EnumFacing.UP);

            int i = ((Integer)state.getValue(AGE)).intValue();

            if (!flag && worldIn.isRaining() && this.canDie(worldIn, pos) && rand.nextFloat() < 0.2F + (float)i * 0.03F)
            {
                worldIn.setBlockToAir(pos);
            }
            else
            {
                if (i < 15)
                {
                    state = state.withProperty(AGE, Integer.valueOf(i + rand.nextInt(3) / 2));
                    worldIn.setBlockState(pos, state, 4);
                }

                worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn) + rand.nextInt(10));

                if (!flag)
                {
                    if (!this.canNeighborCatchFire(worldIn, pos))
                    {
                        if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP) || i > 3)
                        {
                            worldIn.setBlockToAir(pos);
                        }

                        return;
                    }

                    if (!this.canCatchFire(worldIn, pos.down(), EnumFacing.UP) && i == 15 && rand.nextInt(4) == 0)
                    {
                        worldIn.setBlockToAir(pos);
                        return;
                    }
                }

                boolean flag1 = worldIn.isBlockinHighHumidity(pos);
                int j = 0;

                if (flag1)
                {
                    j = -50;
                }

                this.tryCatchFire(worldIn, pos.east(), 300 + j, rand, i, EnumFacing.WEST);
                this.tryCatchFire(worldIn, pos.west(), 300 + j, rand, i, EnumFacing.EAST);
                this.tryCatchFire(worldIn, pos.down(), 250 + j, rand, i, EnumFacing.UP);
                this.tryCatchFire(worldIn, pos.up(), 250 + j, rand, i, EnumFacing.DOWN);
                this.tryCatchFire(worldIn, pos.north(), 300 + j, rand, i, EnumFacing.SOUTH);
                this.tryCatchFire(worldIn, pos.south(), 300 + j, rand, i, EnumFacing.NORTH);

                for (int k = -1; k <= 1; ++k)
                {
                    for (int l = -1; l <= 1; ++l)
                    {
                        for (int i1 = -1; i1 <= 4; ++i1)
                        {
                            if (k != 0 || i1 != 0 || l != 0)
                            {
                                int j1 = 100;

                                if (i1 > 1)
                                {
                                    j1 += (i1 - 1) * 100;
                                }

                                BlockPos blockpos = pos.add(k, i1, l);
                                int k1 = this.getNeighborEncouragement(worldIn, blockpos);

                                if (k1 > 0)
                                {
                                    int l1 = (k1 + 40 + worldIn.getDifficulty().getDifficultyId() * 7) / (i + 30);

                                    if (flag1)
                                    {
                                        l1 /= 2;
                                    }

                                    if (l1 > 0 && rand.nextInt(j1) <= l1 && (!worldIn.isRaining() || !this.canDie(worldIn, blockpos)))
                                    {
                                        int i2 = i + rand.nextInt(5) / 4;

                                        if (i2 > 15)
                                        {
                                            i2 = 15;
                                        }

                                        worldIn.setBlockState(blockpos, state.withProperty(AGE, Integer.valueOf(i2)), 3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected boolean canDie(World worldIn, BlockPos pos)
    {
        return worldIn.isRainingAt(pos) || worldIn.isRainingAt(pos.west()) || worldIn.isRainingAt(pos.east()) || worldIn.isRainingAt(pos.north()) || worldIn.isRainingAt(pos.south());
    }

    public boolean requiresUpdates()
    {
        return false;
    }

    @Deprecated // Use Block.getFlammability
    public int getFlammability(Block blockIn)
    {
        Integer integer = (Integer)this.flammabilities.get(blockIn);
        return integer == null ? 0 : integer.intValue();
    }

    @Deprecated // Use Block.getFlammability
    public int getEncouragement(Block blockIn)
    {
        Integer integer = (Integer)this.encouragements.get(blockIn);
        return integer == null ? 0 : integer.intValue();
    }

    @Deprecated // Use tryCatchFire with face below
    private void catchOnFire(World worldIn, BlockPos pos, int chance, Random random, int age)
    {
        this.tryCatchFire(worldIn, pos, chance, random, age, EnumFacing.UP);
    }

    private void tryCatchFire(World worldIn, BlockPos pos, int chance, Random random, int age, EnumFacing face)
    {
        int i = worldIn.getBlockState(pos).getBlock().getFlammability(worldIn, pos, face);

        if (random.nextInt(chance) < i)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);

            if (random.nextInt(age + 10) < 5 && !worldIn.isRainingAt(pos))
            {
                int j = age + random.nextInt(5) / 4;

                if (j > 15)
                {
                    j = 15;
                }

                worldIn.setBlockState(pos, this.getDefaultState().withProperty(AGE, Integer.valueOf(j)), 3);
            }
            else
            {
            	
                worldIn.setBlockToAir(pos);
                Block block = iblockstate.getBlock();
                if( block instanceof BlockLog && !(block instanceof IFireDrops) ){
                	ItemStack burnitem = worldIn.rand.nextBoolean()? new ItemStack(ItemRegistry.ash): new ItemStack(Items.COAL,1,1);
                	EntityItem entityitem = new EntityItem(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, burnitem);

    				worldIn.spawnEntity(entityitem);
            	}else
            		if (block instanceof IFireDrops) {
            		    IFireDrops fireDrops = (IFireDrops) block;
            		    if (fireDrops.hasFireDrops()){
            		    	///use NonNullList for 1.11
            		    	//also add an event that allows the modification of that same list.
            		        List<ItemStack> drops = new ArrayList<ItemStack>();
            		        fireDrops.addFireDrops(drops, random);
            		        for (ItemStack drop : drops){
            		            EntityItem entityitem = new EntityItem(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, drop);
            		            worldIn.spawnEntity(entityitem);
            		        }
            		        
            		        
            		    }
            		}
            }

            if (iblockstate.getBlock() == Blocks.TNT)
            {
                Blocks.TNT.onBlockDestroyedByPlayer(worldIn, pos, iblockstate.withProperty(BlockTNT.EXPLODE, Boolean.valueOf(true)));
            }
        }
    }

    private boolean canNeighborCatchFire(World worldIn, BlockPos pos)
    {
        for (EnumFacing enumfacing : EnumFacing.values())
        {
            if (this.canCatchFire(worldIn, pos.offset(enumfacing), enumfacing.getOpposite()))
            {
                return true;
            }
        }

        return false;
    }

    private int getNeighborEncouragement(World worldIn, BlockPos pos)
    {
        if (!worldIn.isAirBlock(pos))
        {
            return 0;
        }
        else
        {
            int i = 0;

            for (EnumFacing enumfacing : EnumFacing.values())
            {
                i = Math.max(worldIn.getBlockState(pos.offset(enumfacing)).getBlock().getFireSpreadSpeed(worldIn, pos.offset(enumfacing), enumfacing.getOpposite()), i);
            }

            return i;
        }
    }

    /**
     * Returns if this block is collidable. Only used by fire, although stairs return that of the block that the stair
     * is made of (though nobody's going to make fire stairs, right?)
     */
    public boolean isCollidable()
    {
        return false;
    }

    /**
     * Checks if the block can be caught on fire
     */
     // Use canCatchFire with face sensitive version below
    public boolean canCatchFire(IBlockAccess worldIn, BlockPos pos)
    {
        return canCatchFire(worldIn, pos, EnumFacing.UP);
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.down()).isFullyOpaque() || this.canNeighborCatchFire(worldIn, pos);
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {
        if (!worldIn.getBlockState(pos.down()).isFullyOpaque() && !this.canNeighborCatchFire(worldIn, pos))
        {
            worldIn.setBlockToAir(pos);
        }
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (worldIn.provider.getDimensionType().getId() > 0 || !Blocks.PORTAL.trySpawnPortal(worldIn, pos))
        {
            if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP) && !this.canNeighborCatchFire(worldIn, pos))
            {
                worldIn.setBlockToAir(pos);
            }
            else
            {
                worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn) + worldIn.rand.nextInt(10));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (rand.nextInt(24) == 0)
        {
            worldIn.playSound((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
        }

        if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP) && !((BlockFire) BlockRegistry.specialfire).canCatchFire(worldIn, pos.down(), EnumFacing.UP))
        {
            if (((BlockFire) BlockRegistry.specialfire).canCatchFire(worldIn, pos.west(), EnumFacing.EAST))
            {
                for (int j = 0; j < 2; ++j)
                {
                    double d3 = (double)pos.getX() + rand.nextDouble() * 0.10000000149011612D;
                    double d8 = (double)pos.getY() + rand.nextDouble();
                    double d13 = (double)pos.getZ() + rand.nextDouble();
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d3, d8, d13, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }

            if (((BlockFire) BlockRegistry.specialfire).canCatchFire(worldIn, pos.east(), EnumFacing.WEST))
            {
                for (int k = 0; k < 2; ++k)
                {
                    double d4 = (double)(pos.getX() + 1) - rand.nextDouble() * 0.10000000149011612D;
                    double d9 = (double)pos.getY() + rand.nextDouble();
                    double d14 = (double)pos.getZ() + rand.nextDouble();
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d4, d9, d14, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }

            if (((BlockFire) BlockRegistry.specialfire).canCatchFire(worldIn, pos.north(), EnumFacing.SOUTH))
            {
                for (int l = 0; l < 2; ++l)
                {
                    double d5 = (double)pos.getX() + rand.nextDouble();
                    double d10 = (double)pos.getY() + rand.nextDouble();
                    double d15 = (double)pos.getZ() + rand.nextDouble() * 0.10000000149011612D;
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d5, d10, d15, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }

            if (((BlockFire) BlockRegistry.specialfire).canCatchFire(worldIn, pos.south(), EnumFacing.NORTH))
            {
                for (int i1 = 0; i1 < 2; ++i1)
                {
                    double d6 = (double)pos.getX() + rand.nextDouble();
                    double d11 = (double)pos.getY() + rand.nextDouble();
                    double d16 = (double)(pos.getZ() + 1) - rand.nextDouble() * 0.10000000149011612D;
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d6, d11, d16, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }

            if (((BlockFire) BlockRegistry.specialfire).canCatchFire(worldIn, pos.up(), EnumFacing.DOWN))
            {
                for (int j1 = 0; j1 < 2; ++j1)
                {
                    double d7 = (double)pos.getX() + rand.nextDouble();
                    double d12 = (double)(pos.getY() + 1) - rand.nextDouble() * 0.10000000149011612D;
                    double d17 = (double)pos.getZ() + rand.nextDouble();
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d7, d12, d17, 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }
        }
        else
        {
            for (int i = 0; i < 3; ++i)
            {
                double d0 = (double)pos.getX() + rand.nextDouble();
                double d1 = (double)pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
                double d2 = (double)pos.getZ() + rand.nextDouble();
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state)
    {
        return MapColor.TNT;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AGE, NORTH, EAST, SOUTH, WEST, UPPER});
    }
    
    public boolean canCatchFire(IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return world.getBlockState(pos).getBlock().isFlammable(world, pos, face);
    }
}
