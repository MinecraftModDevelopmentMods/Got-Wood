package panda.gotwood.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBerryBush extends BlockBush{

	public static final PropertyInteger GROWTH = PropertyInteger.create("growth", 0, 1);
	
	public BlockBerryBush(){
		this.setDefaultState(this.blockState.getBaseState().withProperty(GROWTH, Integer.valueOf(0)));
        this.setTickRandomly(true);
	}
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn,BlockPos fromPos)
    {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        this.checkAndDropBlock(worldIn, pos, state);
    }
	@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }
	@Override
    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state)|| areaCleared( worldIn,pos))
        {
            if(areaCleared(worldIn,pos)){
            	this.dropBlockAsItem(worldIn, pos, state, 0);
            }
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }
    
    public boolean areaCleared(World world, BlockPos pos){
    	if((world.getBlockState(pos.down(2)).getBlock() == Blocks.AIR)&&
    		(world.getBlockState(pos.east()).getBlock() == Blocks.AIR)&&
    		(world.getBlockState(pos.east().down()).getBlock() == Blocks.AIR)&&
    		(world.getBlockState(pos.west()).getBlock() == Blocks.AIR)&&
    		(world.getBlockState(pos.west().down()).getBlock() == Blocks.AIR)&&
    		(world.getBlockState(pos.north()).getBlock() == Blocks.AIR)&&
    	    (world.getBlockState(pos.north().down()).getBlock() == Blocks.AIR)&&
    	    (world.getBlockState(pos.south()).getBlock() == Blocks.AIR)&&
    	    (world.getBlockState(pos.south().down()).getBlock() == Blocks.AIR)&&
    	    (world.getBlockState(pos.down()).getBlock() != Blocks.AIR)){
    		return true;
    			}
    	return false;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        return this.canSustainBush(worldIn.getBlockState(pos.down()));
        
    }
    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack)
    {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.setBlockToAir(pos);
    }
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(GROWTH, Integer.valueOf(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(GROWTH).intValue();
    }
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, GROWTH);
    }
}
