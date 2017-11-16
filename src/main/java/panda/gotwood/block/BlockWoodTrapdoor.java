package panda.gotwood.block;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;

import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;

public final class BlockWoodTrapdoor extends BlockTrapDoor implements IOreDictionaryEntry {
	private final WoodMaterial wood;

	private final String oreDict;

	public BlockWoodTrapdoor(WoodMaterial wood) {
		super(Material.WOOD);
		this.wood = wood;
		this.oreDict = "trapdoor" + wood.getCapitalizedName();
		this.blockHardness = wood.getPlankBlockHardness();
		this.blockResistance = wood.getBlastResistance();
		this.blockSoundType = SoundType.WOOD;
		this.setHarvestLevel("axe", wood.getRequiredHarvestLevel());
		this.disableStats();
		this.setRegistryName(wood.getName() + "_trapdoor");
	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos coord, IBlockState state,
									final EntityPlayer player, EnumHand hand, ItemStack heldItem, final EnumFacing facing,
									final float partialX, final float partialY, final float partialZ) {
		if (this.wood.getToolHarvestLevel() > 1)
			return true;

		IBlockState newState = state.cycleProperty(BlockTrapDoor.OPEN);
		worldIn.setBlockState(pos, newState, 2);
		worldIn.playEvent(playerIn, newState.getValue(BlockTrapDoor.OPEN) ? 1012 : 1006, pos, 0);
		return true;
	}

	@Override
	public String getOreDictionaryName() {
		return this.oreDict;
	}

	public WoodMaterial getWoodMaterial() {
		return this.wood;
	}
}
