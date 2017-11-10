package panda.gotwood.events;

import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFire;
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

import panda.gotwood.block.BlockSpecialFire;
import panda.gotwood.registry.BlockRegistry;

public final class ConsumedByFireListener implements IWorldEventListener {
	@Override
	public void notifyBlockUpdate(World worldIn, BlockPos pos, IBlockState oldState, IBlockState currentState, int flags) {
		if (currentState.getBlock() == Blocks.FIRE && !(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockObsidian) && !(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockBush) && !(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockTallGrass)) {
			IBlockState newStateBlock = BlockRegistry.specialfire.getDefaultState().withProperty(BlockSpecialFire.AGE, currentState.getValue(BlockFire.AGE)).withProperty(BlockSpecialFire.NORTH, currentState.getValue(BlockFire.NORTH)).withProperty(BlockSpecialFire.EAST, currentState.getValue(BlockFire.EAST)).withProperty(BlockSpecialFire.SOUTH, currentState.getValue(BlockFire.SOUTH)).withProperty(BlockSpecialFire.WEST, currentState.getValue(BlockFire.WEST)).withProperty(BlockSpecialFire.UPPER, currentState.getValue(BlockFire.UPPER));
			worldIn.setBlockState(pos, newStateBlock, flags);
		}
	}

	@Override
	public void notifyLightSet(BlockPos pos) {}

	@Override
	public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {}

	@Override
	public void playSoundToAllNearExcept(EntityPlayer player, SoundEvent soundIn, SoundCategory category, double x, double y, double z, float volume, float pitch) {}

	@Override
	public void playRecord(SoundEvent soundIn, BlockPos pos) {}

	@Override
	public void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... parameters) {}

	@Override
	public void onEntityAdded(Entity entityIn) {}

	@Override
	public void onEntityRemoved(Entity entityIn) {}

	@Override
	public void broadcastSound(int soundID, BlockPos pos, int data) {}

	@Override
	public void playEvent(EntityPlayer player, int type, BlockPos blockPosIn, int data) {}

	@Override
	public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {}

<<<<<<< HEAD
=======
	@Override
	public void spawnParticle(int one, boolean two, boolean three, double four, double five, double six, double seven, double eight, double nine, int... ten) {}
>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc
}
