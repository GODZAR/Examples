package com.godzar.rpgmod.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class TitaniumOre extends Block
{
    public TitaniumOre(Material material)
    {
    	super(material);
		this.setStepSound(soundTypeStone);
		this.setHardness(2.5F);
		this.setResistance(10.0F);
		this.setHarvestLevel("pickaxe", 2);
    }
}