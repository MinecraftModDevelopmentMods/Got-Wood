package panda.gotwood.blocks;


import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.gotwood.block.entity.TileEntityTreeTap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockTreeTap extends BlockContainer {

    public static final PropertyBool HASBUCKET = PropertyBool.create("hasbucket");
    public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>() {
        @Override
        public boolean apply(@Nullable EnumFacing input) {
            return input != EnumFacing.DOWN && input != EnumFacing.UP;
        }
    });
    private static final ImmutableMap<EnumFacing, AxisAlignedBB> BOUNDS;
    public static int FLUIDPERTICK = 0;

    static {
        ImmutableMap.Builder<EnumFacing, AxisAlignedBB> builder = ImmutableMap.builder();
        builder.put(EnumFacing.UP, FULL_BLOCK_AABB);
        builder.put(EnumFacing.NORTH, new AxisAlignedBB(0.25, 0.25, 0, 0.75, 0.625, 0.375));
        builder.put(EnumFacing.SOUTH, new AxisAlignedBB(0.25, 0.25, 0.625, 0.75, 0.625, 1.0));
        builder.put(EnumFacing.EAST, new AxisAlignedBB(0.625, 0.25, 0.25, 1, 0.625, 0.75));
        builder.put(EnumFacing.WEST, new AxisAlignedBB(0, 0.25, 0.25, 0.375, 0.625, 0.75));
        builder.put(EnumFacing.DOWN, FULL_BLOCK_AABB);

        BOUNDS = builder.build();
    }

    public BlockTreeTap() {
        super(Material.WOOD);
        this.setHardness(3F);
        this.setResistance(2F);
        this.setSoundType(SoundType.WOOD);
        this.setRegistryName("tree_tap");
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HASBUCKET, false));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
        if (worldIn.isRemote) {
            return false;
        }

        if (this.hasTileEntity(this.getDefaultState())) {
            if (playerIn.getActiveHand() != null) {
                ItemStack stack = playerIn.getHeldItem(playerIn.getActiveHand());
                if (stack != null) {
                    System.out.println("yep");
                    if (stack.getItem() == Items.BUCKET) {
                        if (stack.getCount() > 0) {
                            stack.shrink(1);

                            TileEntity te = worldIn.getTileEntity(pos);
                            if (te instanceof TileEntityTreeTap) {
                                ((TileEntityTreeTap) te).hasBucket = true;
                                worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(HASBUCKET, true), 3);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, HASBUCKET);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta) {
        if (meta >= EnumFacing.HORIZONTALS.length) {
            meta = 1;
        }

        EnumFacing face = EnumFacing.values()[meta >> 1];
        if (face == EnumFacing.DOWN || face == EnumFacing.UP) {
            face = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(FACING, face);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).ordinal() << (state.getValue(HASBUCKET) ? 1 : 0);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (worldIn.isRemote) {
            return;
        }

        Block block = worldIn.getBlockState(pos).getBlock();
        if (block instanceof BlockTreeTap) {
            this.breakBlock(worldIn, pos, state);
        }
    }

    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDS.get(state.getValue(FACING));
    }

    @Nonnull
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, @Nonnull IBlockAccess blockAccess, @Nonnull BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Nonnull
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        System.out.println(FLUIDPERTICK);
        return new TileEntityTreeTap().setLiquidAmount(FLUIDPERTICK);
    }

    //gets the amount of sap transfered to the bucket per tick, determined by the number of log blocks and the tree type
    public int getAmountPerTick(World world, BlockPos pos, EnumFacing facing) {
        int amtPerBlock = 2; //mb
        BlockPos start = pos.offset(facing);
        int totalAmount = 0;
        if (world.getBlockState(start) instanceof BlockLog) {
            for (int k = -1; k < 14; k++) {
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (world.getBlockState(start.add(i, j, k)) instanceof BlockLog) {
                            totalAmount += amtPerBlock;
                        }
                    }
                }
            }
        }
        return totalAmount;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        FLUIDPERTICK = getAmountPerTick(worldIn, pos, state.getValue(FACING));
        super.onBlockAdded(worldIn, pos, state);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        EnumFacing enumfacing = facing.getOpposite();
        if (enumfacing == EnumFacing.DOWN || enumfacing == EnumFacing.UP) {
            enumfacing = placer.getHorizontalFacing().getOpposite();
        }
        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
}
