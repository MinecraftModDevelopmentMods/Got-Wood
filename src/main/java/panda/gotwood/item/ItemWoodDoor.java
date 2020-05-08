package panda.gotwood.item;

import net.minecraft.block.BlockDoor;
import net.minecraft.item.ItemDoor;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;

public final class ItemWoodDoor extends ItemDoor implements IOreDictionaryEntry {

    private final WoodMaterial wood;
    private final String oreDict;

    public ItemWoodDoor(BlockDoor block, WoodMaterial wood) {
        super(block);
        this.wood = wood;
        this.oreDict = "door" + wood.getCapitalizedName();
        this.setRegistryName(wood.getName() + "_door_item");
    }

    @Override
    public String getOreDictionaryName() {
        return this.oreDict;
    }

    public WoodMaterial getWoodMaterial() {
        return this.wood;
    }
}
