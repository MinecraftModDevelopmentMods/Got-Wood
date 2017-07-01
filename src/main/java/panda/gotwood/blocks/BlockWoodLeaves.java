package panda.gotwood.blocks;

import java.util.List;
import java.util.Random;

import panda.gotwood.GotWood;
import panda.gotwood.events.ConfigurationHandler;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWoodLeaves extends BlockLeaves implements IOreDictionaryEntry{
	public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");
    public static final PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");
	final WoodMaterial wood;
	
	public BlockWoodLeaves( WoodMaterial wood){
		this.wood = wood;
		Blocks.FIRE.setFireInfo(this, 30, 60);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DECAYABLE, false).withProperty(CHECK_DECAY, false));
		this.setRegistryName(wood.getName()+"_leaves");
	}
	
	//Should never be called for apple trees
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		return ForgeRegistries.ITEMS.getValue(new ResourceLocation(GotWood.MODID, wood+"_seed"));
    }


	@Override
    public java.util.List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        java.util.List<ItemStack> ret = new java.util.ArrayList<>();
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        int chance = -1;									
        	//20,2,10
        	chance = getModifiedSeedChance(this.wood,fortune);
            if (rand.nextInt(chance) == 0){
                ret.add(new ItemStack(getItemDropped(state, rand, fortune), 1, damageDropped(state)));    
            }
        this.captureDrops(true);

        ret.addAll(this.captureDrops(false));
        return ret;
    }
	
	private int getModifiedSeedChance(WoodMaterial wood, int fortune) {
		//should never happen but fall back to vanilla if so.

		int dec = ConfigurationHandler.seedDropFortuneDecrement;
		int min = ConfigurationHandler.mapleChance;
		int ch;
		
		switch(wood.getName()){
			case "maple":
				ch = ConfigurationHandler.mapleChance;
				break;
			case "pine":
				ch = ConfigurationHandler.pineChance;
				break;
			case "willow":
				ch = ConfigurationHandler.willowChance;
				break;
			case "yew":
				ch = ConfigurationHandler.yewChance;
				break;
			case "ebony":
				ch = ConfigurationHandler.ebonyChance;
				break;
			case "fir":
				ch = ConfigurationHandler.firChance;
				break;
			case "bamboo":
				ch = ConfigurationHandler.bambooChance;
				break;
			case "rubber":
				ch = ConfigurationHandler.rubberChance;
				break;
			default:
				ch = 20;
		}
		return getModifiedChance(ch, fortune,dec,min);
	}

	private int getModifiedChance(int chance, int fortune,int decrement,int minchance){
        int modifiedchance = chance;
		if (fortune > 0)
        {
			modifiedchance -= decrement << fortune;
            if (modifiedchance < minchance){
            	modifiedchance = minchance;
            }
        }
        return modifiedchance;
}
   
    
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 16777215;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        return 16777215;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        return 16777215;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED ;
    }

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState,
			IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFoliage(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}
	
	public WoodMaterial getWoodMaterial() {
		return this.wood;
	}

@Override
	public String getOreDictionaryName() {
		return "leaves" + this.wood.getCapitalizedName();
	}
@Override
public IBlockState getStateFromMeta(int meta)
{
    return this.getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf(meta > 0));
}

/**
 * Convert the BlockState into the correct metadata value
 */
@Override
public int getMetaFromState(IBlockState state)
{
    int i = 0;

    if (!state.getValue(DECAYABLE))
    {
        i |= 1;
    }

    if (state.getValue(CHECK_DECAY))
    {
        i |= 2;
    }

    return i;
}
@Override
protected BlockStateContainer createBlockState()
{
    return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
}

@Override
public List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
{
    return java.util.Arrays.asList(new ItemStack(this, 1));
}



}
