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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (this.wood.getToolHarvestLevel() > 1) {
            return true;
        }

        IBlockState newState = state.cycleProperty(BlockTrapDoor.OPEN);
        world.setBlockState(pos, newState, 2);
        world.playEvent(player, newState.getValue(BlockTrapDoor.OPEN) ? 1012 : 1006, pos, 0);
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
