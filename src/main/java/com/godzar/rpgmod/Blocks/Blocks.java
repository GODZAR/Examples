package com.godzar.rpgmod.Blocks;

import com.godzar.rpgmod.Main;
import com.godzar.rpgmod.Tabs.CreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Blocks
{
    public static Block titaniumore;
    
    public static void init()
    {
    	titaniumore = new TitaniumOre(Material.iron).setUnlocalizedName("TitaniumOre").setCreativeTab(CreativeTab.testtab);
    }
    
    public static void register()
	{
		GameRegistry.registerBlock(titaniumore, titaniumore.getUnlocalizedName().substring(5));
	}
    
    public static void registerRenders()
	{
		registerRender(titaniumore);
	}
	
	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Main.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}