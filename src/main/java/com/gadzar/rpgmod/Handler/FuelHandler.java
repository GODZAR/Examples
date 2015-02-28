package com.gadzar.rpgmod.Handler;

import com.gadzar.rpgmod.Items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FuelHandler implements IFuelHandler
{
	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if(fuel.getItem() == ModItems.treefuel) return 800;
		return 0;
	}
	
	public static void register()
	{
		GameRegistry.registerFuelHandler(new FuelHandler());
	}
}