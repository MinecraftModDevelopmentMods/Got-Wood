package panda.gotwood.blocks;

import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;

public class BlockWoodFenceGate extends BlockFenceGate implements IOreDictionaryEntry{

	final WoodMaterial wood;


	public BlockWoodFenceGate(WoodMaterial wood) {
		//TODO maptypes
		super(BlockPlanks.EnumType.OAK);
		this.setSoundType(SoundType.WOOD);
		this.wood = wood;
		this.blockHardness = wood.getPlankBlockHardness();
		this.blockResistance = wood.getBlastResistance();
		this.setHarvestLevel("axe", wood.getRequiredHarvestLevel());
		Blocks.FIRE.setFireInfo(this, 5, 20);
		this.setRegistryName(wood.getName()+"_fence_gate");
	}

	/*@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(itemIn, 1, BlockFence..EnumType.NORMAL.getMetadata()));
	}*/


	public WoodMaterial getWoodMaterial() {
		return this.wood;
	}

@Override
	public String getOreDictionaryName() {
		return "gate" + this.wood.getCapitalizedName();
	}
}