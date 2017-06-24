package panda.gotwood.events;

import panda.gotwood.blocks.SpecialFire;
import panda.gotwood.registry.BlockRegistry;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.World;

public class ConsumedByFireListener implements IWorldEventListener {

	@Override
	public void notifyBlockUpdate(World worldIn, BlockPos pos,IBlockState oldState, IBlockState currentState, int flags) {
		// TODO Auto-generated method stub
		
		if(currentState.getBlock() == Blocks.FIRE && !(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockObsidian) && !(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockBush)&& !(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockTallGrass)){
			//System.out.println(pos+", "+oldState+" -> " + currentState + ": "+flags);
			//System.out.println("replaced");
			IBlockState newStateBlock = BlockRegistry.specialfire.getDefaultState()
					.withProperty(SpecialFire.AGE, currentState.getValue(BlockFire.AGE))
					.withProperty(SpecialFire.NORTH, currentState.getValue(BlockFire.NORTH))
					.withProperty(SpecialFire.EAST, currentState.getValue(BlockFire.EAST))
					.withProperty(SpecialFire.SOUTH,currentState.getValue(BlockFire.SOUTH))
					.withProperty(SpecialFire.WEST, currentState.getValue(BlockFire.WEST))
					.withProperty(SpecialFire.UPPER, currentState.getValue(BlockFire.UPPER));
			
			worldIn.setBlockState(pos, newStateBlock, flags);
			
		}
	}
	
	@Override
	public void notifyLightSet(BlockPos pos) {}
	@Override
	public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2,int y2, int z2) {}
	@Override
	public void playSoundToAllNearExcept(EntityPlayer player,SoundEvent soundIn, SoundCategory category, double x, double y,double z, float volume, float pitch) {}
	@Override
	public void playRecord(SoundEvent soundIn, BlockPos pos) {}
	@Override
	public void spawnParticle(int particleID, boolean ignoreRange,double xCoord, double yCoord, double zCoord, double xSpeed,double ySpeed, double zSpeed, int... parameters) {}
	@Override
	public void onEntityAdded(Entity entityIn) {}
	@Override
	public void onEntityRemoved(Entity entityIn) {}
	@Override
	public void broadcastSound(int soundID, BlockPos pos, int data) {}
	@Override
	public void playEvent(EntityPlayer player, int type, BlockPos blockPosIn,int data) {}
	@Override
	public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {}

	@Override
	public void spawnParticle(int p_190570_1_, boolean p_190570_2_, boolean p_190570_3_, double p_190570_4_,
			double p_190570_6_, double p_190570_8_, double p_190570_10_, double p_190570_12_, double p_190570_14_,
			int... p_190570_16_) {
		// TODO Auto-generated method stub
		
	}

}
