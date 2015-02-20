package com.godzar.rpgmod.Handler;

import com.godzar.rpgmod.Items.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CraftingHandler
{
	public static void init()
	{
		FMLCommonHandler.instance().bus().register(new CraftingHandler());
	}
	
	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent event)
	{
		final IInventory craftMatrix = null;
		for(int i = 0; i < event.craftMatrix.getSizeInventory(); i++)
		{
			if(event.craftMatrix.getStackInSlot(i) != null)
			{
				ItemStack item0 = event.craftMatrix.getStackInSlot(i);
				if(item0 != null && item0.getItem() == ModItems.ironaxe)
				{
					ItemStack k = new ItemStack(ModItems.ironaxe, 2, (item0.getItemDamage() + 1));
					if(k.getItemDamage() >= k.getMaxDamage())
					{
						k.stackSize--;
					}
					event.craftMatrix.setInventorySlotContents(i, k);
				}
			}
		}
	}
}