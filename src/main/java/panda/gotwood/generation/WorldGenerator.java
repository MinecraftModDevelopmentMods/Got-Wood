package panda.gotwood.generation;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeForestMutated;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.biome.BiomeRiver;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.biome.BiomeTaiga;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator{
	//private int min=-1,max=-1,numTrees=-1,

	private void makeTree(WorldGenAbstractTree tree,int chunkX, int chunkZ,Random random, World world,int min, int max){

		int num = min + random.nextInt(max - min);
		for(int i = 0; i < num; i++)
		{
			int randX = chunkX*16 +8+ random.nextInt(16);
			int randZ = chunkZ*16 +8+ random.nextInt(16);
			tree.generate(world, random, world.getHeight(new BlockPos(randX,0,randZ)));
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension()==0){

			WorldGenAbstractTree apple = new WorldGenApple(false);
			WorldGenAbstractTree maple = new WorldGenMaple(false);
			WorldGenAbstractTree ebony = new WorldGenEbony(false);
			WorldGenAbstractTree fir = new WorldGenFir(false);
			WorldGenAbstractTree pine = new WorldGenPine(false);
			WorldGenAbstractTree willow = new WorldGenWillow();
			WorldGenAbstractTree yew = new WorldGenYew();
			WorldGenAbstractTree palm = new WorldGenPalm(false);
			WorldGenAbstractTree rubber = new WorldGenRubber(false);
			WorldGenAbstractTree bamboo = new WorldGenBamboo(false);

			// get the biome
			Biome biome = world.getBiome(new BlockPos(chunkX*16, 64, chunkZ*16));

			if(biome instanceof BiomeDesert)
			{
				makeTree(ebony,chunkX,chunkZ,random, world,0,1);
			}
			
			if(biome instanceof BiomeForest || biome instanceof BiomeForestMutated)
			{
				if(random.nextInt(4) == 0){
					makeTree(maple,chunkX,chunkZ,random, world,0,3);
				}

				if(random.nextInt(12) == 0){
					makeTree(apple,chunkX,chunkZ,random, world,1,2);
				}

				makeTree(pine,chunkX,chunkZ,random, world,0,3);
			}

			if(biome instanceof BiomeHills)
			{
				makeTree(maple,chunkX,chunkZ,random, world,0,3);
				makeTree(fir,chunkX,chunkZ,random, world,0,3);
			}
			
			if(biome instanceof BiomeMesa && random.nextBoolean())
			{
				makeTree(ebony,chunkX,chunkZ,random, world,1,3);
			}
			
			if(biome instanceof BiomePlains)
			{
				makeTree(apple,chunkX,chunkZ,random, world,0,1);
			}
			
			if(biome instanceof BiomeRiver && random.nextInt(3)==0)
			{
				makeTree(yew,chunkX,chunkZ,random, world,0,3);
			}
			
			if(biome instanceof BiomeSavanna)
			{
				makeTree(ebony,chunkX,chunkZ,random, world,0,3);
			}
			
			if(biome instanceof BiomeSwamp)
			{
				if(random.nextInt(2)==0){
					makeTree(willow,chunkX,chunkZ,random, world,0,3);
				}
				if(random.nextInt(1)==0){
					makeTree(yew,chunkX,chunkZ,random, world,0,3);
				}			
			}
			
			if(biome instanceof BiomeTaiga)
			{
				makeTree(pine,chunkX,chunkZ,random, world,0,3);
				makeTree(fir,chunkX,chunkZ,random, world,1,4);
			}
			if(biome instanceof BiomeJungle)
			{
				makeTree(rubber,chunkX,chunkZ,random, world,0,3);
			}
		}
	}


}
