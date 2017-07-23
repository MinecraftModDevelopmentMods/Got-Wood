package panda.gotwood.block;

import panda.gotwood.util.WoodMaterial;

public final class BlockDoubleSlab extends BlockWoodSlab {
	public BlockDoubleSlab(WoodMaterial wood) {
		super(wood);
		this.setRegistryName("double_" + wood.getName() + "_slab");
	}

	@Override
	public boolean isDouble() {
		return true;
	}
}
