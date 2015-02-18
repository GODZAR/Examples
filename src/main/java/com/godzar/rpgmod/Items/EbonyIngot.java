package com.godzar.rpgmod.Items;

import com.godzar.rpgmod.Main;
import com.godzar.rpgmod.Help.RegisterHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class EbonyIngot extends Item
{
	public EbonyIngot()
	{
		super();
		setUnlocalizedName("EbonyIngot");
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		itemIcon = par1IconRegister.registerIcon(Main.MODID + ":" + getUnlocalizedName().substring(5));
	}
}