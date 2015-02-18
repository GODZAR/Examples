package com.godzar.rpgmod.Help;

import com.godzar.rpgmod.Main;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class RegisterHelper
{
	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, Main.MODID + block.getUnlocalizedName().substring(5));
	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, Main.MODID + item.getUnlocalizedName().substring(5));
	}
}