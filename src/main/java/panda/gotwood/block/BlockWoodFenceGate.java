package panda.gotwood.block;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;

import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;

public class BlockWoodFenceGate extends BlockFenceGate implements IOreDictionaryEntry {

	final WoodMaterial wood;

	public BlockWoodFenceGate(WoodMaterial wood) {
		super(BlockPlanks.EnumType.OAK);
		this.setSoundType(SoundType.WOOD);
		this.wood = wood;
		this.blockHardness = wood.getPlankBlockHardness();
		this.blockResistance = wood.getBlastResistance();
		this.setHarvestLevel("axe", wood.getRequiredHarvestLevel());
		Blocks.FIRE.setFireInfo(this, 5, 20);
		this.setRegistryName(wood.getName() + "_fence_gate");
	}

	public WoodMaterial getWoodMaterial() {
		return this.wood;
	}

	@Override
	public String getOreDictionaryName() {
		return "gate" + this.wood.getCapitalizedName();
	}
}
