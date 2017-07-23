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
		return o == this || o instanceof WoodMaterial && this.identifier.equals(((WoodMaterial) o).identifier);
	}

	public int getMeta() {
		return this.meta;
	}

	public int getToolHarvestLevel() {
		return (int) (this.hardness / 3f);
	}

	public int getRequiredHarvestLevel() {
		return (int) MathHelper.clamp(((0.9f * this.hardness) / 3f), -1, 3);
	}

	public float getBlastResistance() {
		return this.blastResistance;
	}

	public float getToolEfficiency() {
		return this.hardness;
	}

	public float getLogBlockHardness() {
		return 2.0f * this.hardness;
	}

	public float getPlankBlockHardness() {
		return 0.5f * this.hardness;
	}

	public String getEnumName() {
		return this.enumName;
	}

	public WoodMaterial setBlastResistance(float resistance) {
		this.blastResistance = resistance;
		return this;
	}
}
