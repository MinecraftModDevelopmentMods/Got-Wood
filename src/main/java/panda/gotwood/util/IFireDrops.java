package panda.gotwood.util;

import java.util.List;
import java.util.Random;

import net.minecraft.item.ItemStack;

public interface IFireDrops {

	boolean hasFireDrops();

	List<ItemStack> addFireDrops(List<ItemStack> stacks, Random random);

}
