package panda.gotwood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;

import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;

public final class BlockWoodStairs extends BlockStairs implements IOreDictionaryEntry {
	private final WoodMaterial wood;

	public BlockWoodStairs(WoodMaterial wood, Block modelBlock) {
		super(modelBlock.getDefaultState());
		this.setSoundType(SoundType.WOOD);
		this.wood = wood;
		this.useNeighborBrightness = true;
		this.blockHardness = wood.getPlankBlockHardness();
		this.blockResistance = wood.getBlastResistance();
		this.setHarvestLevel("axe", wood.getRequiredHarvestLevel());
		Blocks.FIRE.setFireInfo(this, 5, 20);
		this.setRegistryName(wood.getName() + "_stairs");
	}

	public WoodMaterial getWoodMaterial() {
		return this.wood;
	}

	@Override
	public String getOreDictionaryName() {
		return "stairs" + this.wood.getCapitalizedName();
	}
}
