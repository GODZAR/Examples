package com.godzar.rpgmod.Items;

import net.minecraft.item.Item;
import com.godzar.rpgmod.Help.RegisterHelper;

public class Items
{
	public static Item ebonyingot = new EbonyIngot();
	
    public static void init()
    {
        RegisterHelper.registerItem(ebonyingot);
    }
}