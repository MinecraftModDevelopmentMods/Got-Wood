package panda.gotwood.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;

import panda.gotwood.block.BlockBambooFence;
import panda.gotwood.block.BlockBambooLog;
import panda.gotwood.block.BlockDates;
import panda.gotwood.block.BlockDoubleSlab;
import panda.gotwood.block.BlockFruitSapling;
import panda.gotwood.block.BlockFruitingLeaves;
import panda.gotwood.block.BlockHalfSlab;
import panda.gotwood.block.BlockPalmLeaves;
import panda.gotwood.block.BlockPlanks;
import panda.gotwood.block.BlockSappyLog;
import panda.gotwood.block.BlockSpecialFire;
import panda.gotwood.block.BlockTreeTap;
import panda.gotwood.block.BlockWoodDoor;
import panda.gotwood.block.BlockWoodFence;
import panda.gotwood.block.BlockWoodFenceGate;
import panda.gotwood.block.BlockWoodLeaves;
import panda.gotwood.block.BlockWoodLog;
import panda.gotwood.block.BlockWoodSapling;
import panda.gotwood.block.BlockWoodStairs;
import panda.gotwood.block.BlockWoodTrapdoor;
import panda.gotwood.util.MaterialNames;

public final class BlockRegistry {
	private BlockRegistry() {}


	public static final Block apple_leaves = new BlockFruitingLeaves(MaterialNames.apple);

	public static final Block apple_sapling = new BlockFruitSapling(MaterialNames.apple);

	public static final BlockSpecialFire specialfire = new BlockSpecialFire();

	public static final Block bamboo_log = new BlockBambooLog(MaterialNames.bamboo);

	public static final Block bamboo_fence = new BlockBambooFence(MaterialNames.bamboo);

	public static final Block palm_leaves = new BlockPalmLeaves(MaterialNames.palm);

	public static final Block dates_block = new BlockDates();

	public static final Block rubber_log = new BlockSappyLog(MaterialNames.rubber);

}
