package com.godzar.rpgmod.Blocks;

import com.godzar.rpgmod.Help.RegisterHelper;

import net.minecraft.block.Block;

public class Blocks
{
    public static Block titaniumore = new TitaniumOre();        

    public static void init()
    {
          RegisterHelper.registerBlock(titaniumore);
    }
}