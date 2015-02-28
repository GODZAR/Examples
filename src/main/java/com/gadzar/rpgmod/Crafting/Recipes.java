package com.gadzar.rpgmod.Crafting;

import com.gadzar.rpgmod.Blocks.ModBlocks;
import com.gadzar.rpgmod.Items.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class Recipes
{
	public static void init()
	{
	GameRegistry.addShapedRecipe(new ItemStack(Blocks.torch, 2), new Object[] {"A", "B", 'A', Items.coal, 'B', Items.stick});
	GameRegistry.addSmelting(ModBlocks.titaniumore, new ItemStack(ModItems.ebonyingot), 0.8F);
	GameRegistry.addRecipe(new ItemStack(ModItems.ebony, 2), new Object[] {"CD", 'C', ModItems.ebonyingot, 'D', new ItemStack(ModItems.ironaxe, 1, OreDictionary.WILDCARD_VALUE)});
	}
}