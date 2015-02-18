package com.godzar.rpgmod.Items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.godzar.rpgmod.Main;
import com.godzar.rpgmod.Tabs.CreativeTab;

public class Items
{
	public static Item ebonyingot;
	
    public static void init()
    {
    	ebonyingot = new EbonyIngot().setUnlocalizedName("EbonyIngot").setCreativeTab(CreativeTab.testtab);
    }
    
    public static void register()
	{
		GameRegistry.registerItem(ebonyingot, ebonyingot.getUnlocalizedName().substring(5));
	}
    
    public static void registerRenders()
	{
		registerRender(ebonyingot);
	}
	
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Main.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}