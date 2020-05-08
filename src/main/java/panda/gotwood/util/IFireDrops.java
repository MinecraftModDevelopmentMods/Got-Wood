package panda.gotwood.util;

import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Random;

public interface IFireDrops {

    boolean hasFireDrops();

    List<ItemStack> addFireDrops(List<ItemStack> stacks, Random random);
}
