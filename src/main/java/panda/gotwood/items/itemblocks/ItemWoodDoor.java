package panda.gotwood.items.itemblocks;

import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;
import net.minecraft.block.BlockDoor;
import net.minecraft.item.ItemDoor;

/**
 * Doors
 */
public class ItemWoodDoor extends ItemDoor implements IOreDictionaryEntry{

	private final WoodMaterial wood;
	private final String oreDict;

	/**
	 *
	 * @param block
	 * @param metal
	 */
	public ItemWoodDoor(BlockDoor block, WoodMaterial wood) {
		super(block);
		this.wood = wood;
		this.oreDict = "door" + wood.getCapitalizedName();
		this.setRegistryName(wood.getName()+"_door_item");
	}

@Override
	public String getOreDictionaryName() {
		return this.oreDict;
	}

	public WoodMaterial getWoodMaterial() {
		return this.wood;
	}
}