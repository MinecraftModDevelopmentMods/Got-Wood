package panda.gotwood.block;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;

public class BlockWoodLog extends BlockLog implements IOreDictionaryEntry {

    public static final PropertyEnum<BlockLog.EnumAxis> LOG_AXIS = PropertyEnum.create("axis", BlockLog.EnumAxis.class);
    private final WoodMaterial wood;

    public BlockWoodLog(WoodMaterial wood) {
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
        Blocks.FIRE.setFireInfo(this, 5, 5);
        this.wood = wood;
        this.blockHardness = wood.getPlankBlockHardness();
        this.blockResistance = wood.getBlastResistance();
        this.setHarvestLevel("axe", wood.getRequiredHarvestLevel());
        this.setRegistryName(wood.getName() + "_log");
    }

    @Override
    public boolean isWood(IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState();
        switch (meta) {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 1:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 2:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }
        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        switch (state.getValue(LOG_AXIS)) {
            case X:
                i = 0;
                break;
            case Z:
                i |= 1;
                break;
            case NONE:
                i |= 2;
                break;
            default:
                i = 0;
                break;
        }
        return i;
    }

    public WoodMaterial getWoodMaterial() {
        return this.wood;
    }

    @Override
    public String getOreDictionaryName() {
        return "log" + this.wood.getCapitalizedName();
    }
}
