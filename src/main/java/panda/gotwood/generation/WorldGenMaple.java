package panda.gotwood.generation;

import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import panda.gotwood.block.BlockWoodLeaves;
import panda.gotwood.registry.BlockRegistry;

public class WorldGenMaple extends WorldGenAbstractTree {
	private static final IBlockState DEFAULT_TRUNK = BlockRegistry.maple_log.getDefaultState();

	private static final IBlockState DEFAULT_LEAF = BlockRegistry.maple_leaves.getDefaultState().withProperty(BlockWoodLeaves.DECAYABLE, Boolean.valueOf(true));

	/**
	 * The metadata value of the wood to use in tree generation.
	 */
	private final IBlockState metaWood;

	/**
	 * The metadata value of the leaves to use in tree generation.
	 */
	private final IBlockState metaLeaves;

	/**
	 * Sets wither or not the generator should notify blocks of blocks it changes. When the world is first generated,
	 * this is false, when saplings grow, this is true.
	 */
	public WorldGenMaple(boolean notify) {
		this(notify, DEFAULT_TRUNK, DEFAULT_LEAF);
	}

	public WorldGenMaple(boolean notify, IBlockState metawood, IBlockState metaleaves) {
		super(notify);
		this.metaWood = metawood;
		this.metaLeaves = metaleaves;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		byte type = chooseTreeGenType(world);
		if (type == 1) {
			generateNormal(world, rand, pos);
			return generateNormal(world, rand, pos);
		} else if (type == 2) {
			generateMini(world, rand, pos);
			return generateMini(world, rand, pos);
		} else if (type == 3) {
			generateBig(world, rand, pos);
			return generateBig(world, rand, pos);
		} else
			return generateNormal(world, rand, pos);
	}

	private boolean generateNormal(World worldIn, Random rand, BlockPos position) {
		int i = rand.nextInt(3) + 5;
		boolean flag = true;

		if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getHeight()) {

			flag = isAreaClear(worldIn, position, i);

			if (!flag) {
				return false;
			} else {
				IBlockState state = worldIn.getBlockState(position.down());

				if (state.getBlock().canSustainPlant(state, worldIn, position.down(), net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling) net.minecraft.init.Blocks.SAPLING) && position.getY() < worldIn.getHeight() - i - 1) {
					this.setDirtAt(worldIn, position.down());

					for (int i3 = position.getY() - 3 + i; i3 <= position.getY() + i; ++i3) {
						int i4 = i3 - (position.getY() + i);
						int j1 = 1 - i4 / 2;

						for (int k1 = position.getX() - j1; k1 <= position.getX() + j1; ++k1) {
							int l1 = k1 - position.getX();

							for (int i2 = position.getZ() - j1; i2 <= position.getZ() + j1; ++i2) {
								int j2 = i2 - position.getZ();

								if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0) {
									BlockPos blockpos = new BlockPos(k1, i3, i2);
									state = worldIn.getBlockState(blockpos);

									if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos) || state.getMaterial() == Material.VINE) {
										this.setBlockAndNotifyAdequately(worldIn, blockpos, this.metaLeaves);
									}
								}
							}
						}
					}

					for (int j3 = 0; j3 < i; ++j3) {
						BlockPos upN = position.up(j3);
						state = worldIn.getBlockState(upN);

						if (state.getBlock().isAir(state, worldIn, upN) || state.getBlock().isLeaves(state, worldIn, upN) || state.getMaterial() == Material.VINE) {
							this.setBlockAndNotifyAdequately(worldIn, position.up(j3), this.metaWood);
						}
					}

					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	private boolean generateBig(World worldIn, Random rand, BlockPos position) {
		int i = rand.nextInt(2) + 9;
		boolean flag = true;

		if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getHeight()) {
			flag = isAreaClear(worldIn, position, i);

			if (!flag) {
				return false;
			}

			//If we can actually make the tree
			else {
				IBlockState state = worldIn.getBlockState(position.down());

				if (state.getBlock().canSustainPlant(state, worldIn, position.down(), net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling) net.minecraft.init.Blocks.SAPLING) && position.getY() < worldIn.getHeight() - i - 1) {
					this.setDirtAt(worldIn, position.down());

					//MAKE LEAVES
					final int[][] leafPos = new int[][] { { -1, 0, 0 }, { 0, 0, 1 }, { 0, 0, -1 }, { 1, 0, 0 }, { -1, 1, 1 }, { -1, 1, 0 }, { -1, 1, -1 }, { 0, 1, 1 }, { 0, 1, -1 }, { 1, 1, 1 }, { 1, 1, 0 }, { 1, 1, -1 }, { 2, 1, 1 }, { -2, 1, 1 }, { -2, 1, 0 }, { -2, 1, -1 }, { -1, 1, 2 }, { -1, 1, -2 }, { 0, 1, 2 }, { 0, 1, -2 }, { 2, 1, 0 }, { 2, 1, -1 }, { 1, 1, 2 }, { 1, 1, -2 }, { -3, 1, 0 }, { 3, 1, 0 }, { 0, 1, -3 }, { 0, 1, 3 }, { -2, 1, 2 }, { -2, 1, -2 }, { 2, 1, 2 }, { 2, 1, -2 }, { -1, 2, 1 }, { -1, 2, 0 }, { -1, 2, -1 }, { 0, 2, 1 }, { 0, 2, -1 }, { 1, 2, 1 }, { 1, 2, 0 }, { 1, 2, -1 }, { 2, 2, 1 }, { -2, 2, 1 }, { -2, 2, 0 }, { -2, 2, -1 }, { -1, 2, 2 }, { -1, 2, -2 }, { 0, 2, 2 }, { 0, 2, -2 }, { 2, 2, 0 }, { 2, 2, -1 }, { 1, 2, 2 }, { 1, 2, -2 }, { -3, 2, 0 }, { 3, 2, 0 },
						{ 0, 2, -3 }, { 0, 2, 3 }, { -2, 2, 2 }, { -2, 2, -2 }, { 2, 2, 2 }, { 2, 2, -2 }, { -3, 2, -1 }, { -3, 2, 1 }, { -1, 2, -3 }, { -1, 2, 3 }, { 1, 2, 3 }, { 3, 2, 1 }, { 1, 2, -3 }, { 3, 2, -1 }, { -1, 3, 1 }, { -1, 3, 0 }, { -1, 3, -1 }, { 0, 3, 1 }, { 0, 3, -1 }, { 1, 3, 1 }, { 1, 3, 0 }, { 1, 3, -1 }, { 2, 3, 1 }, { -2, 3, 1 }, { -2, 3, 0 }, { -2, 3, -1 }, { -1, 3, 2 }, { -1, 3, -2 }, { 0, 3, 2 }, { 0, 3, -2 }, { 2, 3, 0 }, { 2, 3, -1 }, { 1, 3, 2 }, { 1, 3, -2 }, { -3, 3, 0 }, { 3, 3, 0 }, { 0, 3, -3 }, { 0, 3, 3 }, { -2, 3, 2 }, { -2, 3, -2 }, { 2, 3, 2 }, { 2, 3, -2 }, { -3, 3, -1 }, { -3, 3, 1 }, { -1, 3, -3 }, { -1, 3, 3 }, { 1, 3, 3 }, { 3, 3, 1 }, { 1, 3, -3 }, { 3, 3, -1 }, { -1, 4, 1 }, { -1, 4, 0 }, { -1, 4, -1 }, { 0, 4, 1 }, { 0, 4, -1 }, { 1, 4, 1 },
						{ 1, 4, 0 }, { 1, 4, -1 }, { 2, 4, 1 }, { -2, 4, 1 }, { -2, 4, 0 }, { -2, 4, -1 }, { -1, 4, 2 }, { -1, 4, -2 }, { 0, 4, 2 }, { 0, 4, -2 }, { 2, 4, 0 }, { 2, 4, -1 }, { 1, 4, 2 }, { 1, 4, -2 }, { -3, 4, 0 }, { 3, 4, 0 }, { 0, 4, -3 }, { 0, 4, 3 }, { -1, 5, 1 }, { -1, 5, 0 }, { -1, 5, -1 }, { 0, 5, 1 }, { 0, 5, -1 }, { 1, 5, 1 }, { 1, 5, 0 }, { 1, 5, -1 }, { 2, 5, 1 }, { -2, 5, 1 }, { -2, 5, 0 }, { -2, 5, -1 }, { -1, 5, 2 }, { -1, 5, -2 }, { 0, 5, 2 }, { 0, 5, -2 }, { 2, 5, 0 }, { 2, 5, -1 }, { 1, 5, 2 }, { 1, 5, -2 }, { -1, 6, 0 }, { 0, 6, 1 }, { 0, 6, -1 }, { 1, 6, 0 }, { 0, 7, 0 } };

					for (int[] coord : leafPos) {
						BlockPos blockpos = position.add(coord[0], coord[1] + (i - 7), coord[2]);
						state = worldIn.getBlockState(blockpos);
						if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos) || state.getMaterial() == Material.VINE) {
							this.setBlockAndNotifyAdequately(worldIn, blockpos, this.metaLeaves);
						}
					}

					//MAKE TRUNK
					for (int j3 = 0; j3 < i; ++j3) {
						BlockPos upN = position.up(j3);
						state = worldIn.getBlockState(upN);

						if (state.getBlock().isAir(state, worldIn, upN) || state.getBlock().isLeaves(state, worldIn, upN) || state.getMaterial() == Material.VINE) {
							this.setBlockAndNotifyAdequately(worldIn, position.up(j3), this.metaWood);
							if (j3 == i - 4) {

								this.setBlockAndNotifyAdequately(worldIn, position.up(j3).north(), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z));
								this.setBlockAndNotifyAdequately(worldIn, position.up(j3).south(), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z));
								this.setBlockAndNotifyAdequately(worldIn, position.up(j3).north(2), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z));
								this.setBlockAndNotifyAdequately(worldIn, position.up(j3).south(2), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z));
								if (rand.nextBoolean()) {
									this.setBlockAndNotifyAdequately(worldIn, position.up(j3).east(), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X));
									this.setBlockAndNotifyAdequately(worldIn, position.up(j3).west(), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X));
									this.setBlockAndNotifyAdequately(worldIn, position.up(j3).east(2), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X));
									this.setBlockAndNotifyAdequately(worldIn, position.up(j3).west(2), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X));
								}

							} else if (j3 == i - 6) {
								this.setBlockAndNotifyAdequately(worldIn, position.up(j3).north(), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z));
								this.setBlockAndNotifyAdequately(worldIn, position.up(j3).south(), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z));
								if (rand.nextBoolean()) {
									this.setBlockAndNotifyAdequately(worldIn, position.up(j3).east(), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X));
									this.setBlockAndNotifyAdequately(worldIn, position.up(j3).west(), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X));
								}
							}
						}
					}

					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	private boolean generateMini(World worldIn, Random rand, BlockPos position) {
		int i = rand.nextInt(2) + 6;
		boolean flag = true;

		if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getHeight()) {
			flag = isAreaClear(worldIn, position, i);

			if (!flag) {
				return false;
			}

			//If we can actually make the tree
			else {
				IBlockState state = worldIn.getBlockState(position.down());

				if (state.getBlock().canSustainPlant(state, worldIn, position.down(), net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling) net.minecraft.init.Blocks.SAPLING) && position.getY() < worldIn.getHeight() - i - 1) {
					this.setDirtAt(worldIn, position.down());

					//MAKE LEAVES
					final int[][] leafPos = new int[][] { { -1, 0, 1 }, { -1, 0, 0 }, { -1, 0, -1 }, { 0, 0, 1 }, { 0, 0, -1 }, { 1, 0, 1 }, { 1, 0, 0 }, { 1, 0, -1 }, { -1, 1, 1 }, { -1, 1, 0 }, { -1, 1, -1 }, { 0, 1, 1 }, { 0, 1, -1 }, { 0, 1, -2 }, { 1, 1, 1 }, { 1, 1, 0 }, { 1, 1, -1 }, { -1, 1, 0 }, { -1, 1, -1 }, { -2, 1, 1 }, { -2, 1, 0 }, { -2, 1, -1 }, { 2, 1, -1 }, { 2, 1, 0 }, { 2, 1, 1 }, { -1, 1, 2 }, { 0, 1, 2 }, { 1, 1, 2 }, { -1, 1, -2 }, { 1, 1, -2 }, { 1, 1, -2 }, { -1, 2, 1 }, { -1, 2, 0 }, { -1, 2, -1 }, { 0, 2, 1 }, { 0, 2, -1 }, { 1, 2, 1 }, { 1, 2, 0 }, { 1, 2, -1 }, { -2, 2, 0 }, { 2, 2, 0 }, { 0, 2, 2 }, { 0, 2, -2 }, { -1, 3, 1 }, { -1, 3, 0 }, { -1, 3, -1 }, { 0, 3, 1 }, { 0, 3, -1 }, { 1, 3, 1 }, { 1, 3, 0 }, { 1, 3, -1 }, { -1, 4, 0 }, { 0, 4, 1 },
						{ 0, 4, -1 }, { 1, 4, 0 }, { 0, 5, 0 } };

					for (int[] coord : leafPos) {
						BlockPos blockpos = position.add(coord[0], coord[1] + (i - 5), coord[2]);
						state = worldIn.getBlockState(blockpos);
						if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos) || state.getMaterial() == Material.VINE) {
							this.setBlockAndNotifyAdequately(worldIn, blockpos, this.metaLeaves);
						}
					}
					//MAKE TRUNK
					for (int j3 = 0; j3 < i; ++j3) {
						BlockPos upN = position.up(j3);
						state = worldIn.getBlockState(upN);

						if (state.getBlock().isAir(state, worldIn, upN) || state.getBlock().isLeaves(state, worldIn, upN) || state.getMaterial() == Material.VINE) {
							this.setBlockAndNotifyAdequately(worldIn, position.up(j3), this.metaWood);
							if (j3 == i - 4) {

								this.setBlockAndNotifyAdequately(worldIn, position.up(j3).north(), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z));
								this.setBlockAndNotifyAdequately(worldIn, position.up(j3).south(), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z));
								if (rand.nextBoolean()) {
									this.setBlockAndNotifyAdequately(worldIn, position.up(j3).east(), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X));
									this.setBlockAndNotifyAdequately(worldIn, position.up(j3).west(), this.metaWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X));
								}

							}
						}
					}

					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	public byte chooseTreeGenType(World world) {
		byte r = (byte) world.rand.nextInt(4);
		//big maple
		if (r == 0) {
			return 3;
		} else
			//mini maple
			if (r == 1 || r == 2) {
				return 2;
			} else
				//regular
				return 1;
	}

	private boolean isAreaClear(World worldIn, BlockPos position, int treeheight) {
		boolean flag = false;
		for (int j = position.getY(); j <= position.getY() + 1 + treeheight; ++j) {
			int k = 1;

			if (j == position.getY()) {
				k = 0;
			}

			if (j >= position.getY() + 1 + treeheight - 2) {
				k = 1;
			}

			BlockPos.MutableBlockPos blockposmutableblockpos = new BlockPos.MutableBlockPos();

			for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
				for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
					if (j >= 0 && j < worldIn.getHeight()) {
						if (!this.isReplaceable(worldIn, blockposmutableblockpos.setPos(l, j, i1))) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}

}
