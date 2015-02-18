package com.godzar.rpgmod.Blocks;

import com.godzar.rpgmod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class TitaniumOre extends Block
{
    public TitaniumOre()
    {
    	super(Material.iron);
		setBlockName("TitaniumOre");
		setBlockTextureName(Main.MODID + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(CreativeTabs.tabBlock);
    }
}