package panda.gotwood.items;

import net.minecraft.block.Block;
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

public class ItemDates extends ItemFood{

	public ItemDates() {
		super(2, 0.1f, false);
		this.setRegistryName("dates");
	}
	
	
	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);

        if (facing == EnumFacing.UP && playerIn.canPlayerEdit(pos.offset(facing), facing, stack) && (state.getBlock()== net.minecraft.init.Blocks.GRASS ||state.getBlock()==net.minecraft.init.Blocks.DIRT|| state.getBlock()==net.minecraft.init.Blocks.SAND) && worldIn.isAirBlock(pos.up()))
        {
        	worldIn.setBlockState(pos.up(), Block.REGISTRY.getObject(new ResourceLocation(GotWood.MODID, "palm_sapling")).getDefaultState());
            --stack.stackSize;
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

}
