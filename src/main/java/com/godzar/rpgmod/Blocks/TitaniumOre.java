package com.godzar.rpgmod.Blocks;

import java.util.Random;

import com.godzar.rpgmod.Main;
import com.godzar.rpgmod.Items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

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