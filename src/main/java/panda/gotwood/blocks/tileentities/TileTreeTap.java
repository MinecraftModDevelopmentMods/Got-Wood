package panda.gotwood.blocks.tileentities;


	import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;

import panda.gotwood.blocks.BlockTreeTap;
import panda.gotwood.util.TapRegistry;

	public class TileTreeTap extends TileEntity implements ITickable {

		
	  public static int LIQUID_AMOUNT = 0;

	  public EnumFacing direction; 
	  public boolean hasBucket;
	  public FluidStack sapInBucket; //fluid In bucket
	  

	  public TileTreeTap() {
	    reset();
	    
	  }
	  

	  @Override
	  public void update() {
	    if(getWorld().isRemote) {
	      return;
	    }
	    
	    if(!hasBucket){
	    	return;
	    }
	    System.out.println(sapInBucket);
	    if(LIQUID_AMOUNT == 0 || sapInBucket == null) {
	      return;
	    }

	    if(sapInBucket.amount < 1000) {
	      addToBucket();      
	    }
	  }
	  
	  protected void addToBucket() {
		  sapInBucket.amount += LIQUID_AMOUNT;
		  System.out.println("Adding "+LIQUID_AMOUNT+" mb");
	      if(sapInBucket.amount > 1000) {
	    	  sapInBucket.amount = 1000;
	      }
	  }

	  protected void reset() {
		hasBucket = false;
		sapInBucket = null;
	    direction = EnumFacing.DOWN; // invalid direction
	  }

	  @Nonnull
	  @Override
	  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
	    compound = super.writeToNBT(compound);
	    if(sapInBucket != null) {
	    	sapInBucket.writeToNBT(compound);
	      compound.setInteger("direction", direction.getIndex());
	      compound.setInteger("sappertick", LIQUID_AMOUNT);
	    }
	    return compound;
	  }

	  @Override
	  public void readFromNBT(NBTTagCompound compound) {
	    super.readFromNBT(compound);
	    sapInBucket = FluidStack.loadFluidStackFromNBT(compound);

	    if(sapInBucket != null) {
	      direction = EnumFacing.values()[compound.getInteger("direction")];
	      LIQUID_AMOUNT = compound.getInteger("sappertick");
		  hasBucket = true;
		    
	    }
	    else {
	      reset();
	    }
	  }

	  @Override
	  public void handleUpdateTag(@Nonnull NBTTagCompound tag) {
	    readFromNBT(tag);
	  }
	  
	  @Nonnull
	  @Override
	  public NBTTagCompound getUpdateTag() {
	    return writeToNBT(new NBTTagCompound());
	  }

	@Override
	public void onLoad() {
		sapInBucket = TapRegistry.find(getWorld().getBlockState(pos).getBlock());
	    direction = getWorld().getBlockState(pos).getValue(BlockTreeTap.FACING);
	}


	public TileEntity setLiquidAmount(int amountPerTick) {
		this.LIQUID_AMOUNT = amountPerTick;
		return this;
	}
	}