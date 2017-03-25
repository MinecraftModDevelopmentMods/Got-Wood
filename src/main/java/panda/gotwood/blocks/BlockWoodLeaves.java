package panda.gotwood.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import panda.gotwood.events.ConfigurationHandler;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;
import panda.gotwood.util.WoodMaterials;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
	
	//protected int getSaplingDropChance(IBlockState state)
    //{
    //    return state.getValue(VARIANT) == BlockPlanks.EnumType.JUNGLE ? 40 : super.getSaplingDropChance(state);
    //}
	
	//Should never be called for apple trees
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		return GameRegistry.findItem("varietytrees", wood+"_seed");
    }


	@Override
    public java.util.List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        java.util.List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        int chance = -1;									
        if(wood != WoodMaterials.apple){
        	//20,2,10
        	chance = getModifiedSeedChance(this.wood,fortune);
            if (rand.nextInt(chance) == 0){
                ret.add(new ItemStack(getItemDropped(state, rand, fortune), 1, damageDropped(state)));    
            }
        }
        

        if(wood == WoodMaterials.apple){    		
        	chance = getModifiedChance(ConfigurationHandler.appleChance, fortune, ConfigurationHandler.appleDropFortuneDecrement,ConfigurationHandler.appleDropMinChance);
        	if (rand.nextInt(chance) == 0){
        		ret.add(new ItemStack(Items.APPLE));
        	}
        										
        	chance = getModifiedChance(ConfigurationHandler.goldenDropChance, fortune, ConfigurationHandler.goldenDropFortuneDecrement,ConfigurationHandler.goldenDropMinChance);
            if (rand.nextInt(chance) == 0){
            	ret.add(new ItemStack(Items.GOLDEN_APPLE));
            }
        }
        
        this.captureDrops(true);

        ret.addAll(this.captureDrops(false));
        return ret;
    }
	
	private int getModifiedSeedChance(WoodMaterial wood, int fortune) {
		//should never happen but fallback to vanilla if so.
		int ch = 20;
		int dec = ConfigurationHandler.seedDropFortuneDecrement;
		int min = ConfigurationHandler.mapleChance;
		
		switch(wood.getName()){
			case "maple":
				ch = ConfigurationHandler.mapleChance;
			case "pine":
				ch = ConfigurationHandler.pineChance;
			case "willow":
				ch = ConfigurationHandler.willowChance;
			case "yew":
				ch = ConfigurationHandler.yewChance;
			case "ebony":
				ch = ConfigurationHandler.ebonyChance;
			case "fir":
				ch = ConfigurationHandler.firChance;


		
		}
		return getModifiedChance(ch, fortune,dec,min);
	}

	private int getModifiedChance(int chance, int fortune,int decrement,int minchance){
        if (fortune > 0)
        {
            chance -= decrement << fortune;
            if (chance < minchance){
            	chance = minchance;
            }
        }
        return chance;
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

    if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
    {
        i |= 1;
    }

    if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
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
