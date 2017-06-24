package panda.gotwood.blocks;

import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Metal Block
 */
public class BlockPlanks extends Block implements IOreDictionaryEntry{

	private final WoodMaterial wood;
	private final String oreDict;

	public BlockPlanks(WoodMaterial wood) {
		super(Material.WOOD);
		this.setSoundType(SoundType.WOOD);
		this.fullBlock = true;
		this.lightOpacity = 255;
		this.translucent = false;
		this.wood = wood;
		this.oreDict = "plank" + wood.getCapitalizedName();
		this.blockHardness = wood.getPlankBlockHardness();
		this.blockResistance = wood.getBlastResistance();
		this.setHarvestLevel("axe", wood.getRequiredHarvestLevel());
		this.setDefaultState(this.blockState.getBaseState());
		Blocks.FIRE.setFireInfo(this, 5, 20);
		this.setRegistryName(wood.getName()+"_planks");
		
	}

	///// OVERRIDE OF ALL METHODS THAT DEPEND ON BLOCK MATERIAL: /////
	@Override
	public MapColor getMapColor(final IBlockState p_getMapColor_1_) {
		return MapColor.WOOD;
	}

	@Override
	public boolean isFullBlock(IBlockState bs) {
		return true;
	}

	@Override
	public boolean isNormalCube(IBlockState bs) {
		return true;
	}

	@Override
	public boolean isVisuallyOpaque() {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState bs) {
		return true;
	}

	@Override
	public boolean isPassable(final IBlockAccess p_isPassable_1_, final BlockPos p_isPassable_2_) {
		return false;
	}

	@Override
	public boolean isReplaceable(final IBlockAccess w, final BlockPos p) {
		return false;
	}

	@Override
	public boolean isNormalCube(final IBlockState bs, final IBlockAccess w, final BlockPos coord) {
		return this.isNormalCube(bs);
	}
@Override
	public String getOreDictionaryName() {
		return this.oreDict;
	}

	public WoodMaterial getWoodMaterial() {
		return this.wood;
	}
}