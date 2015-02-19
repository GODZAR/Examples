package com.godzar.rpgmod.Crafting;

import com.godzar.rpgmod.Blocks.ModBlocks;
import com.godzar.rpgmod.Items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes
{
	public static void init()
	{
	GameRegistry.addShapedRecipe(new ItemStack(Blocks.torch), new Object[] {"A", "B", 'A', Items.coal, 'B', Items.stick});
	GameRegistry.addSmelting(ModBlocks.titaniumore, new ItemStack(ModItems.ebonyingot), 0.8F);
	}
}