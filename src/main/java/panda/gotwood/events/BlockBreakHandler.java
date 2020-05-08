package panda.gotwood.events;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import panda.gotwood.registry.ItemRegistry;

public final class BlockBreakHandler {

    @SubscribeEvent
    public void onDrops(BlockEvent.HarvestDropsEvent event) {
        Block theblock = event.getState().getBlock();
        int fortune = event.getFortuneLevel();
        if (theblock instanceof BlockSapling || theblock instanceof BlockLeaves) {
            for (int i = 0; i < event.getDrops().size(); i++) {
                if (Block.getBlockFromItem(event.getDrops().get(i).getItem()) instanceof BlockBush && ((theblock == Blocks.LEAVES) || (theblock == Blocks.LEAVES2))) {
                    event.getDrops().remove(i);
                }

                if (theblock instanceof BlockSapling) {
                    if (event.getHarvester() != net.minecraftforge.common.util.FakePlayerFactory.getMinecraft((net.minecraft.world.WorldServer) event.getWorld())) {
                        if (event.getHarvester() != null) {
                            if (!event.getHarvester().getHeldItemMainhand().isEmpty()) {
                                if ((!(event.getHarvester().getHeldItemMainhand().getItem() instanceof ItemShears) && ConfigurationHandler.retrieveSaplingsMode == 1) || ConfigurationHandler.retrieveSaplingsMode == 0) {
                                    if (Block.getBlockFromItem(event.getDrops().get(i).getItem()) instanceof BlockBush) {
                                        event.getDrops().remove(i);
                                    }
                                    event.getDrops().add(new ItemStack(Items.STICK, event.getWorld().rand.nextBoolean() ? 2 : 1));
                                }
                            }
                        }
                    } else {
                        if (Block.getBlockFromItem(event.getDrops().get(i).getItem()) instanceof BlockBush && (ConfigurationHandler.retrieveSaplingsMode == 1 || ConfigurationHandler.retrieveSaplingsMode == 0)) {
                            event.getDrops().remove(i);
                        }
                        event.getDrops().add(new ItemStack(Items.STICK, event.getWorld().rand.nextBoolean() ? 2 : 1));
                    }
                }
            }

            //TODO compact
            if (theblock == Blocks.LEAVES) {
                int s = event.getDrops().size();
                for (int i = 0; i < s; i++) {
                    if (event.getDrops().get(i).getItem() == Items.APPLE && !ConfigurationHandler.removeAppleDrops) {
                        event.getDrops().remove(i);
                    }
                }

                //Jungle
                if (theblock.getMetaFromState(event.getState()) == 11) {
                    event.getDrops().remove(new ItemStack(Item.getItemFromBlock(Blocks.SAPLING.getStateFromMeta(3).getBlock())));
                    if (event.getWorld().rand.nextInt(getModifiedChance(ConfigurationHandler.jungleChance, fortune)) == 0) {
                        event.getDrops().add(new ItemStack(ItemRegistry.jungle_seed));
                    }
                } else {
                    //Birch
                    if (theblock.getMetaFromState(event.getState()) == 10) {
                        event.getDrops().remove(new ItemStack(Item.getItemFromBlock(Blocks.SAPLING.getStateFromMeta(2).getBlock())));
                        if (event.getWorld().rand.nextInt(getModifiedChance(ConfigurationHandler.birchChance, fortune)) == 0) {
                            event.getDrops().add(new ItemStack(ItemRegistry.birch_seed));
                        }
                    } else {
                        //Spruce
                        if (theblock.getMetaFromState(event.getState()) == 9) {
                            event.getDrops().remove(new ItemStack(Item.getItemFromBlock(Blocks.SAPLING.getStateFromMeta(1).getBlock())));
                            if (event.getWorld().rand.nextInt(getModifiedChance(ConfigurationHandler.spruceChance, fortune)) == 0) {
                                event.getDrops().add(new ItemStack(ItemRegistry.spruce_seed));
                            }
                        } else {
                            //Oak
                            if (theblock.getMetaFromState(event.getState()) == 8) {
                                event.getDrops().remove(new ItemStack(Item.getItemFromBlock(Blocks.SAPLING.getStateFromMeta(0).getBlock())));
                                if (event.getWorld().rand.nextInt(getModifiedChance(ConfigurationHandler.oakChance, fortune)) == 0) {
                                    event.getDrops().add(new ItemStack(ItemRegistry.oak_seed));
                                }
                            }
                        }
                    }
                }
            } else {
                if (theblock == Blocks.LEAVES2) {
                    int s = event.getDrops().size();
                    for (int i = 0; i < s; i++) {
                        if (event.getDrops().get(i).getItem() == Items.APPLE) {
                            event.getDrops().remove(i);
                        }
                    }

                    //Acacia
                    if (theblock.getMetaFromState(event.getState()) == 8) {
                        event.getDrops().remove(new ItemStack(Item.getItemFromBlock(Blocks.SAPLING.getStateFromMeta(4).getBlock())));
                        if (event.getWorld().rand.nextInt(getModifiedChance(ConfigurationHandler.acaciaChance, fortune)) == 0) {
                            event.getDrops().add(new ItemStack(ItemRegistry.acacia_seed));
                        }
                    } else {
                        //Dark Oak
                        if (theblock.getMetaFromState(event.getState()) == 9) {
                            event.getDrops().remove(new ItemStack(Item.getItemFromBlock(Blocks.SAPLING.getStateFromMeta(5).getBlock())));
                            if (event.getWorld().rand.nextInt(getModifiedChance(ConfigurationHandler.darkoakChance, fortune)) == 0) {
                                event.getDrops().add(new ItemStack(ItemRegistry.dark_oak_seed));
                            }
                        }
                    }
                }
            }
        }
    }

    private int getModifiedChance(int chance, int fortune) {
        int modifiedChance = chance;
        if (fortune > 0) {
            modifiedChance -= ConfigurationHandler.seedDropFortuneDecrement << fortune;
            if (modifiedChance < 10) {
                modifiedChance = 10;
            }
        }
        return modifiedChance;
    }
}
