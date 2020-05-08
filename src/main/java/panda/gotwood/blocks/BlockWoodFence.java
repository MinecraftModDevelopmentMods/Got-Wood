package panda.gotwood.blocks;

import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;

/**
 * @author Jasmine Iwanek
 */
public class BlockWoodFence extends BlockFence implements IOreDictionaryEntry {

    final WoodMaterial wood;

    public BlockWoodFence(WoodMaterial wood) {
        //TODO maptypes
        super(Material.WOOD, BlockPlanks.EnumType.OAK.getMapColor());
        this.setSoundType(SoundType.WOOD);
        this.wood = wood;
        this.blockHardness = wood.getPlankBlockHardness();
        this.blockResistance = wood.getBlastResistance();
        this.setHarvestLevel("axe", wood.getRequiredHarvestLevel());
        Blocks.FIRE.setFireInfo(this, 5, 20);
        this.setRegistryName(wood.getName() + "_fence");
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
        return "fence" + this.wood.getCapitalizedName();
    }
}