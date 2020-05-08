package panda.gotwood;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import panda.gotwood.block.entity.TileEntityTreeTap;
import panda.gotwood.events.ConfigurationHandler;
import panda.gotwood.generation.WorldGenerator;
import panda.gotwood.registry.BlockRegistry;
import panda.gotwood.registry.ItemRegistry;
import panda.gotwood.registry.MasterRegistrar;

@Mod(
        modid = GotWood.MODID,
        name = GotWood.NAME,
        version = GotWood.VERSION)
public class GotWood {

    //TODO Setup new gradle scripts and mod info -ProxyNeko
    public static final String MODID = "gotwood";
    public static final String VERSION = "0.26.0";
    public static final String NAME = "Got Wood?";

    public static final CreativeTabs TREE_TAB = new CreativeTabs(GotWood.MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.oak_seed);
        }
    };

    @SubscribeEvent
    public void registerSpecialRecipes(RegistryEvent.Register<IRecipe> event) {
        if (ConfigurationHandler.retrieveSaplingsMode == 2) {
            ResourceLocation group = new ResourceLocation(GotWood.MODID);
            GameRegistry.addShapelessRecipe(new ResourceLocation("oak_seed"), group, new ItemStack(Blocks.SAPLING, 1, 0), Ingredient.fromStacks(new ItemStack(ItemRegistry.oak_seed)));
            GameRegistry.addShapelessRecipe(new ResourceLocation("spruce_seed"), group, new ItemStack(Blocks.SAPLING, 1, 1), Ingredient.fromStacks(new ItemStack(ItemRegistry.spruce_seed)));
            GameRegistry.addShapelessRecipe(new ResourceLocation("birch_seed"), group, new ItemStack(Blocks.SAPLING, 1, 2), Ingredient.fromStacks(new ItemStack(ItemRegistry.birch_seed)));
            GameRegistry.addShapelessRecipe(new ResourceLocation("jungle_seed"), group, new ItemStack(Blocks.SAPLING, 1, 3), Ingredient.fromStacks(new ItemStack(ItemRegistry.jungle_seed)));
            GameRegistry.addShapelessRecipe(new ResourceLocation("acacia_seed"), group, new ItemStack(Blocks.SAPLING, 1, 4), Ingredient.fromStacks(new ItemStack(ItemRegistry.acacia_seed)));
            GameRegistry.addShapelessRecipe(new ResourceLocation("dark_oak_seed"), group, new ItemStack(Blocks.SAPLING, 1, 5), Ingredient.fromStacks(new ItemStack(ItemRegistry.dark_oak_seed)));

            GameRegistry.addShapelessRecipe(new ResourceLocation("apple_seed"), group, new ItemStack(BlockRegistry.apple_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.apple_seed)));
            GameRegistry.addShapelessRecipe(new ResourceLocation("maple_seed"), group, new ItemStack(BlockRegistry.maple_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.maple_seed)));
            GameRegistry.addShapelessRecipe(new ResourceLocation("pine_seed"), group, new ItemStack(BlockRegistry.pine_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.pine_seed)));
            GameRegistry.addShapelessRecipe(new ResourceLocation("willow_seed"), group, new ItemStack(BlockRegistry.willow_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.willow_seed)));
            GameRegistry.addShapelessRecipe(new ResourceLocation("yew_seed"), group, new ItemStack(BlockRegistry.yew_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.yew_seed)));
            GameRegistry.addShapelessRecipe(new ResourceLocation("ebony_seed"), group, new ItemStack(BlockRegistry.ebony_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.ebony_seed)));
            GameRegistry.addShapelessRecipe(new ResourceLocation("fir_seed"), group, new ItemStack(BlockRegistry.fir_sapling), Ingredient.fromStacks(new ItemStack(ItemRegistry.fir_seed)));
        }
    }

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        MasterRegistrar.callRegistry(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
        BlockRegistry.specialfire.initInfo();
        GameRegistry.registerTileEntity(TileEntityTreeTap.class, "gotwood:tree_tap");
    }
}
