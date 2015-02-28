package com.gadzar.rpgmod.Items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.gadzar.rpgmod.Main;
import com.gadzar.rpgmod.Tabs.CreativeTab;

public class ModItems
{
	public static Item ebonyingot;
	public static Item treefuel;
	public static Item ironaxe;
	public static Item ebony;
	
    public static void init()
    {
    	ebonyingot = new EbonyIngot().setUnlocalizedName("EbonyIngot").setCreativeTab(CreativeTab.testtab);
    	treefuel = new EbonyIngot().setUnlocalizedName("TreeFuel").setCreativeTab(CreativeTab.testtab);
    	ironaxe = new IronAxe().setUnlocalizedName("IronAxe").setCreativeTab(CreativeTab.testtab);
    	ebony = new EbonyIngot().setUnlocalizedName("Ebony").setCreativeTab(CreativeTab.testtab);
    }
    
    public static void register()
	{
		GameRegistry.registerItem(ebonyingot, ebonyingot.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(treefuel, treefuel.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(ironaxe, ironaxe.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(ebony, ebony.getUnlocalizedName().substring(5));
	}
    
    public static void registerRenders()
	{
		registerRender(ebonyingot);
		registerRender(treefuel);
		registerRender(ironaxe);
		registerRender(ebony);
	}
	
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Main.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}