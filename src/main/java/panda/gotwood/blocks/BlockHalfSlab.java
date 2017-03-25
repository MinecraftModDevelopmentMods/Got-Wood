package panda.gotwood.blocks;

import panda.gotwood.util.WoodMaterial;


public class BlockHalfSlab extends BlockWoodSlab {

	public BlockHalfSlab(WoodMaterial wood) {
		super(wood);
		this.setRegistryName(wood.getName()+"_slab");
	}

	@Override
	public boolean isDouble() {
		return false;
	}
}
