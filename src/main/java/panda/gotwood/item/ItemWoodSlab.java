package panda.gotwood.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;

import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;

public final class ItemWoodSlab extends ItemSlab implements IOreDictionaryEntry {
	private final WoodMaterial wood;

	public ItemWoodSlab(WoodMaterial wood, Block block, BlockSlab slab, BlockSlab doubleslab) {
		super(block, slab, doubleslab);
		this.wood = wood;
		this.setRegistryName(wood.getName() + "_slab_item");
	}

	public WoodMaterial getWoodMaterial() {
		return this.wood;
	}

	@Override
	public String getOreDictionaryName() {
		return "slab" + this.wood.getCapitalizedName();
	}
}
