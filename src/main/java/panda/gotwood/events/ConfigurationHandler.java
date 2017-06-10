package panda.gotwood.events;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigurationHandler {
	
	
	
	public static int numStairs;
	
	//Seed Drop Chances
	public static int oakChance;
	public static int birchChance;
	public static int spruceChance;
	public static int jungleChance;
	public static int acaciaChance;
	public static int darkoakChance;
	
	public static int mapleChance;
	public static int pineChance;
	public static int willowChance;
	public static int yewChance;
	public static int ebonyChance;
	public static int firChance;
	
	
	public static int seedDropFortuneDecrement;
	public static int seedDropMinChance;
	
	//Apple stuff
	//40,5,10
	public static int appleChance;
	public static int appleDropFortuneDecrement;
	public static int appleDropMinChance;
	//1000,50,200
	public static int goldenDropChance;
	public static int goldenDropFortuneDecrement;
	public static int goldenDropMinChance;
	
	public static boolean removeAppleDrops;
	public static int retrieveSaplingsMode;
	
	public static String[] mega;
	public static String dL = "";

	public static int bambooChance;

	
	
	
	
	public static void init(FMLPreInitializationEvent event){
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		config.load();

		config.addCustomCategoryComment("Drops", "Tweaks for leaf drops\n Drop chances are 1/n. Fortune decrements are how much it changes for each level of fortune.");
			oakChance = config.getInt("oakSeedChance", "Drops", 30, 1, 32767, "");
			birchChance = config.getInt("birchSeedChance", "Drops", 30, 1, 32767, "");
			spruceChance = config.getInt("spruceSeedChance", "Drops", 30, 1, 32767, "");
			jungleChance = config.getInt("jungleSeedChance", "Drops", 60, 1, 32767, "");
			acaciaChance = config.getInt("acaciaSeedChance", "Drops", 30, 1, 32767, "");
			darkoakChance = config.getInt("darkoakSeedChance", "Drops", 30, 1, 32767, "");
			appleChance = config.getInt("appleChance", "Drops", 23, 1, 32767, "");
			mapleChance = config.getInt("mapleSeedChance", "Drops", 30, 1, 32767, "");
			pineChance = config.getInt("pineSeedChance", "Drops", 27, 1, 32767, "");
			willowChance = config.getInt("willowSeedChance", "Drops", 52, 1, 32767, "");
			yewChance = config.getInt("yewSeedChance", "Drops", 24, 1, 32767, "");
			ebonyChance = config.getInt("ebonySeedChance", "Drops", 27, 1, 32767, "");
			firChance = config.getInt("firSeedChance", "Drops", 34, 1, 32767, "");
			bambooChance = config.getInt("bambooSeedChance", "Drops", 10, 1, 32767, "");
			goldenDropChance = config.getInt("goldenAppleChance", "Drops", 1600, 1, 32767, "");
			goldenDropFortuneDecrement = config.getInt("goldenAppleFortuneDecrement", "Drops", 60, 1, 32767, "Must be less than the chance");
			goldenDropMinChance = config.getInt("goldenAppleMinChance", "Drops", 400, 1, 32767, "Must be less than the chance");
			appleDropFortuneDecrement = config.getInt("appleFortuneDecrement", "Drops", 2, 1, 32767, "Must be less than the chance");
			appleDropMinChance = config.getInt("appleMinChance", "Drops", 5, 1, 32767, "Must be less than the chance");
			seedDropFortuneDecrement = config.getInt("seedFortuneDecrement", "Drops", 2, 1, 32767, "Must be less than every seed chance. Effects all tree seed chances");
			seedDropMinChance = config.getInt("seedFortuneDecrement", "Drops", 10, 1, 32767, "Must be less than every seed chance. Effects all tree seed chances");
			removeAppleDrops = config.getBoolean("removeAppleDrops", "Drops", true, "Make apple trees the only natural source of apples besides loot");
			config.addCustomCategoryComment("Generation", "Tweaks for tree generation");
		
		config.addCustomCategoryComment("Misc", "Misc Tweaks");
		numStairs = config.getInt("NumStairsCrafting", "Misc", 4, 1, 8, "");
		retrieveSaplingsMode = config.getInt("goldenAppleChance", "Misc", 1, 0, 2, "0: Can't get sapling items normally/n1:Shear or silktouch a sapling block/n 2:Place a seed in a crafting grid");
		//can't get saplings
				//shear or silktouch sapling block
				//seed in crafting grid
		
		//dL = dL.concat("corn:kernels,0,18;");
		//dL = dL.concat("exnihiloomnia:silkworm,0,16-18;");
		
		//System.out.println(dL.toString());
		//config.addCustomCategoryComment("Custom Seeds", "");
		//mega = config.get("Custom Seeds", "List", dL, " ").getString().split(";");
		
		config.save();
	}
}
