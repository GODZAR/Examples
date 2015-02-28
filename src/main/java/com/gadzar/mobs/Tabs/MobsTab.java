package com.gadzar.mobs.Tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class MobsTab extends CreativeTabs
{
	public MobsTab(String label)
	{
		super(label);
	}

	@Override
	public Item getTabIconItem()
	{
		return Items.egg;
	}
}