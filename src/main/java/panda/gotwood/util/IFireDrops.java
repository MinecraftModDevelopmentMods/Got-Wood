package panda.gotwood.util;

import java.util.List;
import java.util.Random;

import net.minecraft.item.ItemStack;

public interface IFireDrops{
	
	public abstract boolean hasFireDrops();
	
	public abstract List<ItemStack> addFireDrops(List<ItemStack> stacks,Random random);
	
}
