package panda.gotwood.items;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import panda.gotwood.GotWood;
import panda.gotwood.registry.BlockRegistry;

public class ItemDates extends ItemFood{

	public ItemDates() {
		super(2, 0.1f, false);
		this.setRegistryName("dates");
	}
	
	
	@Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState state = worldIn.getBlockState(pos);
        ItemStack heldItem = playerIn.getHeldItem(hand);
		
		if(heldItem.isEmpty()){
			return super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		}
        if (facing == EnumFacing.UP && playerIn.canPlayerEdit(pos.offset(facing), facing, heldItem) && (state.getBlock()== net.minecraft.init.Blocks.GRASS ||state.getBlock()==net.minecraft.init.Blocks.DIRT|| state.getBlock()==net.minecraft.init.Blocks.SAND) && worldIn.isAirBlock(pos.up()))
        {
        	worldIn.setBlockState(pos.up(), BlockRegistry.palm_sapling.getDefaultState());
        	heldItem.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

}
