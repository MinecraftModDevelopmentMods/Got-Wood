package panda.gotwood.item;

import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;

public final class ItemWoodSlab extends ItemSlab implements IOreDictionaryEntry {

	private final WoodMaterial wood;

    public ItemWoodSlab(WoodMaterial wood, BlockSlab slab, BlockSlab doubleslab) {
        super(slab, slab, doubleslab);
        this.wood = wood;
        this.setRegistryName(slab.getRegistryName());
    }

    public WoodMaterial getWoodMaterial() {
        return this.wood;
    }

    @Override
    public String getOreDictionaryName() {
        return "slab" + this.wood.getCapitalizedName();
    }
}
