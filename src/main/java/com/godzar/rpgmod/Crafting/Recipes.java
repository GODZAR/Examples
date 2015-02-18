package com.godzar.rpgmod.Crafting;

import com.godzar.rpgmod.Blocks.Blocks;
import com.godzar.rpgmod.Items.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes
{
	public static void init()
	{
	//GameRegistry.addShapedRecipe(new ItemStack(Blocks.torch), new Object[] {"A", "B", 'A', Items.coal, 'B', Items.stick});
	GameRegistry.addSmelting(Blocks.titaniumore, new ItemStack(Items.ebonyingot), 0.8F);
	}
}