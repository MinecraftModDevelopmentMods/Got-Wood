package panda.gotwood.block.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidStack;
import panda.gotwood.block.BlockTreeTap;
import panda.gotwood.util.TapRegistry;

import javax.annotation.Nonnull;

public final class TileEntityTreeTap extends TileEntity implements ITickable {

    private static int LIQUID_AMOUNT = 0;
    public boolean hasBucket;
    public EnumFacing direction;
    public FluidStack sapInBucket; //fluid In bucket

    public TileEntityTreeTap() {
        reset();
    }

    @Override
    public void update() {
        if (getWorld().isRemote) {
            return;
        }

        if (!hasBucket) {
            return;
        }

        System.out.println(sapInBucket);
        if (LIQUID_AMOUNT == 0 || sapInBucket == null) {
            return;
        }

        if (sapInBucket.amount < 1000) {
            addToBucket();
        }
    }

    protected void addToBucket() {
        sapInBucket.amount += LIQUID_AMOUNT;
        System.out.println("Adding " + LIQUID_AMOUNT + " mb");
        if (sapInBucket.amount > 1000) {
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
        if (sapInBucket != null) {
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
        if (sapInBucket != null) {
            direction = EnumFacing.values()[compound.getInteger("direction")];
            LIQUID_AMOUNT = compound.getInteger("sappertick");
            hasBucket = true;
        } else {
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
        LIQUID_AMOUNT = amountPerTick;
        return this;
    }
}
