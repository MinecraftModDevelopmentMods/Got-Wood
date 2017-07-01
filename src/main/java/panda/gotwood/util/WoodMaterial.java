package panda.gotwood.util;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import panda.gotwood.GotWood;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.Item;

public class WoodMaterial {

	/**
	 * hardness on a scale from 0 to 10 (or more), where 0 is non-solid and
	 * diamond is 10. For reference, wood is 3, stone is 5, iron is 8, diamond
	 * is 10.
	 */
	public final float hardness;

	/**
	 * durability on a scale from 0 to 10 (or more). For reference, leather is
	 * 2.5, gold is 3, wood is 2, stone is 4, iron is 8, minecraft diamond is
	 * 10.
	 */
	public final float strength;
	final String identifier;
	final String titleName;
	private final String enumName;
	private float blastResistance;


	
	public Item door;
	public Item slab;

	public Block planks;
	public BlockDoor doorBlock;
	public BlockSlab double_slab;
	public BlockSlab half_slab;
	public Block log;
	public Block stairs;
	public Block trapdoor;
	public Block fence;
	public int meta;


	/**
	 * name
	 *            String used to identify items and blocks using this material
	 * ardness
	 *            hardness on a scale from 0 to 10 (or more), where 0 is
	 *            non-solid and diamond is 10. For reference, wood is 3, stone
	 *            is 5, iron is 8, diamond is 10. Used for damage, armor
	 *            protection, and tool effectiveness calculations
	 * strength
	 *            durability on a scale from 0 to 10 (or more). For reference,
	 *            leather is 2.5, gold is 3, wood is 2, stone is 4, iron is 8,
	 *            minecraft diamond is 10. Used for item durability calculations
	 *            and blast resistance
	 */
	public WoodMaterial(String name, float hardness, float strength) {
		this(name,hardness,strength,-1);
	}
	
	public WoodMaterial(String name, float hardness, float strength, int meta) {
		this.hardness = hardness;
		this.strength = strength;
		this.identifier = name;
		this.titleName = StringUtils.capitalize(name);
		this.enumName = (GotWood.MODID + "_" + name).toUpperCase(Locale.ENGLISH);
		this.blastResistance = 2.5f * this.strength;
		this.meta = meta;
	}

	public String getName() {
		return this.identifier;
	}

	public String getCapitalizedName() {
		return this.titleName;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public int hashCode() {
		return this.identifier.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if(o != null && (o.hashCode() == this.hashCode()) && (o instanceof WoodMaterial)) {
			final WoodMaterial other = (WoodMaterial) o;
			return this.identifier.equals(other.identifier);
		}
		
		return false;
	}
	
	//for vanilla ONLY
		public int getMeta(){
			return this.meta;
			
		}

	/**
	 * Gets the tool harvest level
	 * 
	 * an integer from -1 (equivalent to no tool) to 3 (diamond tool
	 *         equivalent)
	 */
	public int getToolHarvestLevel() {
		return (int) (this.hardness / 3f);
	}

	/**
	 * Gets the tool harvest level needed from a tool trying to mine this
	 * metal's ore and other blocks
	 * 
	 * an integer from -1 (equivalent to no tool) to 3 (diamond tool
	 *         equivalent)
	 */
	public int getRequiredHarvestLevel() {
		return (int) clamp(((0.9f * this.hardness) / 3f), -1, 3);
	}

	static int clamp(int x, int min, int max) {
		if (x < min)
			return min;
		if (x > max)
			return max;
		return x;
	}

	static float clamp(float x, float min, float max) {
		if (x < min)
			return min;
		if (x > max)
			return max;
		return x;
	}

	static double clamp(double x, double min, double max) {
		if (x < min)
			return min;
		if (x > max)
			return max;
		return x;
	}

	/**
	 * Gets the resistance of blocks made from this material to explosions
	 * 
	 *  the blast resistance score
	 */
	public float getBlastResistance() {
		return this.blastResistance;
	}

	/**
	 * Gets the number used to determine how quickly a block is mined with a
	 * tool made from this material
	 * 
	 * the number used to determine how quickly a block is mined
	 */
	public float getToolEfficiency() {
		return this.hardness;
	}

	/**
	 * Gets the hardness of the log block for this material
	 * 
	 *  the hardness of the log block for this material
	 */
	public float getLogBlockHardness() {
		return 2.0f * this.hardness;
	}

	/**
	 * Gets the hardness for planks made from this material
	 * 
	 *  the hardness for planks made from this material
	 */
	public float getPlankBlockHardness() {
		return 0.5f * this.hardness;
	}


	private float round(float number, int numDecimalPlaces) {
		int x = 1;
		for (int i = 0; i < numDecimalPlaces; i++)
			x *= 10;
		return (float) Math.round(number * x) / (float) x;
	}


	public String getEnumName() {
		return this.enumName;
	}

	/**
	 * Sets the blast resistance of the material. Should only be used as a
	 * builder method.
	 * 
	 *  resistance
	 *            The resistance for the material.
	 *  An instance of the material, for quality of life.
	 */
	public WoodMaterial setBlastResistance(float resistance) {
		this.blastResistance = resistance;
		return this;
	}

}