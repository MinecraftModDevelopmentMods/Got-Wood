package panda.gotwood.block;

import panda.gotwood.util.WoodMaterial;

public final class BlockHalfSlab extends BlockWoodSlab {

    public BlockHalfSlab(WoodMaterial wood) {
        super(wood);
        this.setRegistryName(wood.getName() + "_slab");
    }

    @Override
    public boolean isDouble() {
        return false;
    }
}
