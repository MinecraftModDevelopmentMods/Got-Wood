package panda.gotwood.util;

import java.util.Locale;

import net.minecraft.util.math.MathHelper;

import org.apache.commons.lang3.StringUtils;
import panda.gotwood.GotWood;

public final class WoodMaterial {
	private final float hardness;

	private final float strength;

	private final String identifier;

	private final String titleName;

	private final String enumName;

	private float blastResistance;

	public int meta;

<<<<<<< HEAD
	/**
	 * @param name
	 *            String used to identify items and blocks using this material
	 * @param hardness
	 *            hardness on a scale from 0 to 10 (or more), where 0 is
	 *            non-solid and diamond is 10. For reference, wood is 3, stone
	 *            is 5, iron is 8, diamond is 10. Used for damage, armor
	 *            protection, and tool effectiveness calculations
	 * @param strength
	 *            durability on a scale from 0 to 10 (or more). For reference,
	 *            leather is 2.5, gold is 3, wood is 2, stone is 4, iron is 8,
	 *            minecraft diamond is 10. Used for item durability calculations
	 *            and blast resistance
	 */
=======
>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc
	public WoodMaterial(String name, float hardness, float strength) {
		this(name, hardness, strength, -1);
	}

	public WoodMaterial(String name, float hardness, float strength, int meta) {
		this.hardness = hardness;
		this.strength = strength;
		this.identifier = name;
		this.titleName = StringUtils.capitalize(name);
		this.enumName = (GotWood.ID + "_" + name).toUpperCase(Locale.ENGLISH);
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
<<<<<<< HEAD
		if (o == this)
			return true;
		if ((o.hashCode() == this.hashCode()) && (o instanceof WoodMaterial)) {
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
	 * @return an integer from -1 (equivalent to no tool) to 3 (diamond tool
	 *         equivalent)
	 */
	public int getToolHarvestLevel() {
		return (int) (this.hardness / 3f);
	}

	/**
	 * Gets the tool harvest level needed from a tool trying to mine this
	 * metal's ore and other blocks
	 * 
	 * @return an integer from -1 (equivalent to no tool) to 3 (diamond tool
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
=======
		return o == this || o instanceof WoodMaterial && this.identifier.equals(((WoodMaterial) o).identifier);
	}

	public int getMeta() {
		return this.meta;
>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc
	}

	public int getToolHarvestLevel() {
		return (int) (this.hardness / 3f);
	}

	public int getRequiredHarvestLevel() {
		return (int) MathHelper.clamp(((0.9f * this.hardness) / 3f), -1, 3);
	}

<<<<<<< HEAD
	/**
	 * Gets the resistance of blocks made from this material to explosions
	 * 
	 * @return the blast resistance score
	 */
=======
>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc
	public float getBlastResistance() {
		return this.blastResistance;
	}

<<<<<<< HEAD
	/**
	 * Gets the number used to determine how quickly a block is mined with a
	 * tool made from this material
	 * 
	 * @return the number used to determine how quickly a block is mined
	 */
=======
>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc
	public float getToolEfficiency() {
		return this.hardness;
	}

<<<<<<< HEAD
	/**
	 * Gets the hardness of the log block for this material
	 * 
	 * @return the hardness of the log block for this material
	 */
=======
>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc
	public float getLogBlockHardness() {
		return 2.0f * this.hardness;
	}

<<<<<<< HEAD
	/**
	 * Gets the hardness for planks made from this material
	 * 
	 * @return the hardness for planks made from this material
	 */
=======
>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc
	public float getPlankBlockHardness() {
		return 0.5f * this.hardness;
	}

	public String getEnumName() {
		return this.enumName;
	}

<<<<<<< HEAD
	/**
	 * Sets the blast resistance of the material. Should only be used as a
	 * builder method.
	 * 
	 * @param resistance
	 *            The resistance for the material.
	 * @return An instance of the material, for quality of life.
	 */
=======
>>>>>>> 87abbf6cf6d1218e06bd1a18365c8d25836ce6dc
	public WoodMaterial setBlastResistance(float resistance) {
		this.blastResistance = resistance;
		return this;
	}
}
