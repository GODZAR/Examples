package com.godzar.rpgmod.Tabs;

import com.godzar.rpgmod.Items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TestTab extends CreativeTabs
{
	public TestTab(String label)
	{
		super(label);
		//this.setBackgroundImageName("testtab.png"); //tab_testtab.png
	}

	@Override
	public Item getTabIconItem()
	{
		return ModItems.ebonyingot;
	}
}