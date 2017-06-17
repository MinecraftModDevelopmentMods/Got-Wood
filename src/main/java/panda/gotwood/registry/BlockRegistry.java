package panda.gotwood.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import panda.gotwood.blocks.BlockBambooFence;
import panda.gotwood.blocks.BlockBambooLog;
import panda.gotwood.blocks.BlockDates;
import panda.gotwood.blocks.BlockDoubleSlab;
import panda.gotwood.blocks.BlockHalfSlab;
import panda.gotwood.blocks.BlockPalmLeaves;
import panda.gotwood.blocks.BlockPlanks;
import panda.gotwood.blocks.BlockTreeTap;
import panda.gotwood.blocks.BlockWoodDoor;
import panda.gotwood.blocks.BlockWoodFence;
import panda.gotwood.blocks.BlockWoodFenceGate;
import panda.gotwood.blocks.BlockWoodLeaves;
import panda.gotwood.blocks.BlockWoodLog;
import panda.gotwood.blocks.BlockWoodSapling;
import panda.gotwood.blocks.BlockWoodStairs;
import panda.gotwood.blocks.BlockWoodTrapdoor;
import panda.gotwood.blocks.SappyLog;
import panda.gotwood.blocks.SpecialFire;
import panda.gotwood.util.WoodMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.Item;

public class BlockRegistry {
	/*
	 * Declare items here and then subsequently add them to getList(); They will be automatically moved to registration and model loading.
	 */
	//this.setRegistryName("log_"+wood.getName());
	public static final Block apple_log = new BlockWoodLog(WoodMaterials.apple);
	public static final Block apple_planks = new BlockPlanks(WoodMaterials.apple);
	public static final BlockDoor apple_door = new BlockWoodDoor(WoodMaterials.apple);
	public static final Block apple_trapdoor = new BlockWoodTrapdoor(WoodMaterials.apple);
	public static final Block apple_leaves = new BlockWoodLeaves(WoodMaterials.apple);
	public static final Block apple_sapling = new BlockWoodSapling(WoodMaterials.apple);
	public static final Block apple_gate = new BlockWoodFenceGate(WoodMaterials.apple);
	//public static final BlockSlab apple_slab = new BlockHalfSlab(WoodMaterials.apple);
	//public static final BlockSlab double_apple_slab = new BlockDoubleSlab(WoodMaterials.apple);
	public static final Block apple_stairs = new BlockWoodStairs(WoodMaterials.apple,apple_planks);
	public static final Block apple_fence = new BlockWoodFence(WoodMaterials.apple);
	
	public static final Block maple_log = new BlockWoodLog(WoodMaterials.maple);
	public static final Block maple_planks = new BlockPlanks(WoodMaterials.maple);
	public static final BlockDoor maple_door = new BlockWoodDoor(WoodMaterials.maple);
	public static final Block maple_trapdoor = new BlockWoodTrapdoor(WoodMaterials.maple);
	public static final Block maple_leaves = new BlockWoodLeaves(WoodMaterials.maple);
	public static final Block maple_sapling = new BlockWoodSapling(WoodMaterials.maple);
	public static final Block maple_gate = new BlockWoodFenceGate(WoodMaterials.maple);
	//public static final BlockSlab maple_slab;
	//public static final BlockSlab double_maple_slab;
	public static final Block maple_stairs = new BlockWoodStairs(WoodMaterials.maple,maple_planks);
	public static final Block maple_fence = new BlockWoodFence(WoodMaterials.maple);
	
	public static final Block pine_log = new BlockWoodLog(WoodMaterials.pine);
	public static final Block pine_planks = new BlockPlanks(WoodMaterials.pine);
	public static final BlockDoor pine_door = new BlockWoodDoor(WoodMaterials.pine);
	public static final Block pine_trapdoor = new BlockWoodTrapdoor(WoodMaterials.pine);
	public static final Block pine_leaves = new BlockWoodLeaves(WoodMaterials.pine);
	public static final Block pine_sapling = new BlockWoodSapling(WoodMaterials.pine);
	public static final Block pine_gate = new BlockWoodFenceGate(WoodMaterials.pine);
	//public static final BlockSlab pine_slab;
	//public static final BlockSlab double_pine_slab;
	public static final Block pine_stairs = new BlockWoodStairs(WoodMaterials.pine,pine_planks);
	public static final Block pine_fence = new BlockWoodFence(WoodMaterials.pine);
	
	public static final Block willow_log = new BlockWoodLog(WoodMaterials.willow);
	public static final Block willow_planks = new BlockPlanks(WoodMaterials.willow);
	public static final BlockDoor willow_door = new BlockWoodDoor(WoodMaterials.willow);
	public static final Block willow_trapdoor = new BlockWoodTrapdoor(WoodMaterials.willow);
	public static final Block willow_leaves = new BlockWoodLeaves(WoodMaterials.willow);
	public static final Block willow_sapling = new BlockWoodSapling(WoodMaterials.willow);
	public static final Block willow_gate = new BlockWoodFenceGate(WoodMaterials.willow);
	//public static final BlockSlab willow_slab;
	//public static final BlockSlab double_willow_slab;
	public static final Block willow_stairs = new BlockWoodStairs(WoodMaterials.willow,willow_planks);
	public static final Block willow_fence = new BlockWoodFence(WoodMaterials.willow);
	
	public static final Block yew_log  = new BlockWoodLog(WoodMaterials.yew);
	public static final Block yew_planks = new BlockPlanks(WoodMaterials.yew);
	public static final BlockDoor yew_door = new BlockWoodDoor(WoodMaterials.yew);
	public static final Block yew_trapdoor = new BlockWoodTrapdoor(WoodMaterials.yew);
	public static final Block yew_leaves = new BlockWoodLeaves(WoodMaterials.yew);
	public static final Block yew_sapling = new BlockWoodSapling(WoodMaterials.yew);
	public static final Block yew_gate = new BlockWoodFenceGate(WoodMaterials.yew);
	//public static final BlockSlab yew_slab;
	//public static final BlockSlab double_yew_slab;
	public static final Block yew_stairs = new BlockWoodStairs(WoodMaterials.yew,yew_planks);
	public static final Block yew_fence = new BlockWoodFence(WoodMaterials.yew);
	
	public static final Block ebony_log  = new BlockWoodLog(WoodMaterials.ebony);
	public static final Block ebony_planks = new BlockPlanks(WoodMaterials.ebony);
	public static final BlockDoor ebony_door = new BlockWoodDoor(WoodMaterials.ebony);
	public static final Block ebony_trapdoor = new BlockWoodTrapdoor(WoodMaterials.ebony);
	public static final Block ebony_leaves = new BlockWoodLeaves(WoodMaterials.ebony);
	public static final Block ebony_sapling = new BlockWoodSapling(WoodMaterials.ebony);
	public static final Block ebony_gate = new BlockWoodFenceGate(WoodMaterials.ebony);
	//public static final BlockSlab ebony_slab;
	//public static final BlockSlab double_ebony_slab;
	public static final Block ebony_stairs = new BlockWoodStairs(WoodMaterials.ebony,ebony_planks);
	public static final Block ebony_fence = new BlockWoodFence(WoodMaterials.ebony);
	
	public static final Block fir_log = new BlockWoodLog(WoodMaterials.fir);
	public static final Block fir_planks = new BlockPlanks(WoodMaterials.fir);
	public static final BlockDoor fir_door = new BlockWoodDoor(WoodMaterials.fir);
	public static final Block fir_trapdoor = new BlockWoodTrapdoor(WoodMaterials.fir);
	public static final Block fir_leaves = new BlockWoodLeaves(WoodMaterials.fir);
	public static final Block fir_sapling = new BlockWoodSapling(WoodMaterials.fir);
	public static final Block fir_gate = new BlockWoodFenceGate(WoodMaterials.fir);
	//public static final BlockSlab fir_slab;
	//public static final BlockSlab double_fir_slab;
	public static final Block fir_stairs = new BlockWoodStairs(WoodMaterials.fir,fir_planks);
	public static final Block fir_fence = new BlockWoodFence(WoodMaterials.fir);
	
	
	public static final Block specialfire = new SpecialFire();
	public static final Block treetap = new BlockTreeTap();
	public static final Block bamboo_sapling = new BlockWoodSapling(WoodMaterials.bamboo).setHardness(1.0F);;
	public static final Block bamboo_leaves = new BlockWoodLeaves(WoodMaterials.bamboo);
	public static final Block bamboo_log = new BlockBambooLog(WoodMaterials.bamboo);
	public static final Block bamboo_planks = new BlockPlanks(WoodMaterials.bamboo);
	public static final BlockDoor bamboo_door = new BlockWoodDoor(WoodMaterials.bamboo);
	public static final Block bamboo_trapdoor = new BlockWoodTrapdoor(WoodMaterials.bamboo);
	public static final Block bamboo_stairs = new BlockWoodStairs(WoodMaterials.bamboo,bamboo_planks);
	public static final Block bamboo_fence = new BlockBambooFence(WoodMaterials.bamboo);
	public static final Block bamboo_gate = new BlockWoodFenceGate(WoodMaterials.bamboo);
	
	public static final Block palm_log = new BlockWoodLog(WoodMaterials.palm);
	public static final Block palm_planks = new BlockPlanks(WoodMaterials.palm);
	public static final BlockDoor palm_door = new BlockWoodDoor(WoodMaterials.palm);
	public static final Block palm_trapdoor = new BlockWoodTrapdoor(WoodMaterials.palm);
	public static final Block palm_leaves = new BlockPalmLeaves(WoodMaterials.palm);
	public static final Block palm_sapling = new BlockWoodSapling(WoodMaterials.palm);
	public static final Block palm_gate = new BlockWoodFenceGate(WoodMaterials.palm);
	//public static final BlockSlab palm_slab;
	//public static final BlockSlab double_palm_slab;
	public static final Block palm_stairs = new BlockWoodStairs(WoodMaterials.palm,palm_planks);
	public static final Block palm_fence = new BlockWoodFence(WoodMaterials.palm);
	public static final Block dates_block = new BlockDates();
	
	
	public static final Block rubber_log = new SappyLog(WoodMaterials.rubber);
	public static final Block rubber_leaves = new BlockWoodLeaves(WoodMaterials.rubber);
	public static final Block rubber_sapling = new BlockWoodSapling(WoodMaterials.rubber);
	public static final List<Block> getBlockList() {
		List<Block> list = new ArrayList<Block>();
		list.add(specialfire);

		list.add(apple_sapling);
		list.add(maple_sapling);
		list.add(pine_sapling);
		list.add(willow_sapling);
		list.add(yew_sapling);
		list.add(fir_sapling);
		list.add(ebony_sapling);
		list.add(bamboo_sapling);
		list.add(palm_sapling);
		
		list.add(apple_log);
		list.add(maple_log);
		list.add(pine_log);
		list.add(willow_log);
		list.add(yew_log);
		list.add(fir_log);
		list.add(ebony_log);
		list.add(bamboo_log);
		list.add(palm_log);
		
		list.add(apple_leaves);
		list.add(maple_leaves);
		list.add(pine_leaves);
		list.add(willow_leaves);
		list.add(yew_leaves);
		list.add(fir_leaves);
		list.add(ebony_leaves);
		list.add(bamboo_leaves);
		list.add(palm_leaves);
		
		list.add(apple_planks);
		list.add(maple_planks);
		list.add(pine_planks);
		list.add(willow_planks);
		list.add(yew_planks);
		list.add(fir_planks);
		list.add(ebony_planks);
		list.add(bamboo_planks);
		list.add(palm_planks);
		
		list.add(apple_door);
		list.add(maple_door);
		list.add(pine_door);
		list.add(willow_door);
		list.add(yew_door);
		list.add(fir_door);
		list.add(ebony_door);
		list.add(bamboo_door);
		list.add(palm_door);
		
		list.add(apple_trapdoor);
		list.add(maple_trapdoor);
		list.add(pine_trapdoor);
		list.add(willow_trapdoor);
		list.add(yew_trapdoor);
		list.add(fir_trapdoor);
		list.add(ebony_trapdoor);
		list.add(bamboo_trapdoor);
		list.add(palm_trapdoor);
		
		list.add(apple_gate);
		list.add(maple_gate);
		list.add(pine_gate);
		list.add(willow_gate);
		list.add(yew_gate);
		list.add(fir_gate);
		list.add(ebony_gate);
		list.add(bamboo_gate);
		list.add(palm_gate);
		
		list.add(apple_stairs);
		list.add(maple_stairs);
		list.add(pine_stairs);
		list.add(willow_stairs);
		list.add(yew_stairs);
		list.add(fir_stairs);
		list.add(ebony_stairs);
		list.add(bamboo_stairs);
		list.add(palm_stairs);
		
		list.add(apple_fence);
		list.add(maple_fence);
		list.add(pine_fence);
		list.add(willow_fence);
		list.add(yew_fence);
		list.add(fir_fence);
		list.add(ebony_fence);
		list.add(bamboo_fence);
		list.add(palm_fence);
		list.add(rubber_log);
		list.add(rubber_leaves);
		list.add(rubber_sapling);
		
		
		list.add(dates_block);
		
		
		list.add(treetap);

		

		return list;
	}
	static final Map<String, Block> blockRegistry = new HashMap<>();
	
	public static Block getBlockByName(String name) {
		return blockRegistry.get(name);
	}
}
