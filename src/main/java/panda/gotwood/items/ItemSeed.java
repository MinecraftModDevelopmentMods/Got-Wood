package panda.gotwood.items;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import panda.gotwood.GotWood;
import panda.gotwood.util.IOreDictionaryEntry;
import panda.gotwood.util.WoodMaterial;
import panda.gotwood.util.WoodMaterials;

public class ItemSeed extends Item implements IOreDictionaryEntry, IPlantable{
	
	final WoodMaterial wood;
	public final IBlockState sapling;
	
	public ItemSeed(WoodMaterial wood)
    {
    	this.wood = wood;
    	this.sapling = getSaplingState();
    	this.setRegistryName(wood.getName()+"_seed");
    }
	
	/**
     * Called when a Block is right-clicked with this Item
     */
	@Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState state = worldIn.getBlockState(pos);
        ItemStack heldItem = playerIn.getHeldItem(hand);
		
		if(heldItem.isEmpty()){
			return super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		}
        if (facing == EnumFacing.UP && playerIn.canPlayerEdit(pos.offset(facing), facing, heldItem) && (state.getBlock()== net.minecraft.init.Blocks.GRASS ||state.getBlock()==net.minecraft.init.Blocks.DIRT|| state.getBlock()==net.minecraft.init.Blocks.FARMLAND) && worldIn.isAirBlock(pos.up()))
        {
        	worldIn.setBlockState(pos.up(), this.getSaplingState());
        	heldItem.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
	
	private IBlockState getSaplingState(){
		if(wood ==WoodMaterials.acacia || wood ==WoodMaterials.birch || wood ==WoodMaterials.darkOak || wood ==WoodMaterials.jungle || wood ==WoodMaterials.oak || wood ==WoodMaterials.spruce){
			return Blocks.SAPLING.getStateFromMeta(wood.getMeta());
		}else
			return Block.REGISTRY.getObject(new ResourceLocation(GotWood.MODID, wood+"_sapling")).getDefaultState();
	}

	@Override
	public String getOreDictionaryName() {
		return "seed" + this.wood.getCapitalizedName();
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return getSaplingState();
	}
}
