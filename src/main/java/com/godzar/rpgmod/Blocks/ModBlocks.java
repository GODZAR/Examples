package com.godzar.rpgmod.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.godzar.rpgmod.Main;
import com.godzar.rpgmod.Items.EbonyIngot;
import com.godzar.rpgmod.Items.ModItems;
import com.godzar.rpgmod.Tabs.CreativeTab;

public class ModBlocks
{
	public static Block titaniumore;
    public static Block modchest;
    public static Block modfurnaceoff;
    public static Block modfurnaceon;
    
    public static void init()
    {
    	titaniumore = new TitaniumOre(Material.iron).setUnlocalizedName("TitaniumOre").setCreativeTab(CreativeTab.testtab);
    	modchest = new ModChest(Material.wood).setUnlocalizedName("ModChest").setCreativeTab(CreativeTab.testtab);
    	modfurnaceoff = new ModFurnace(false).setUnlocalizedName("ModFurnaceOff").setCreativeTab(CreativeTab.testtab);
    	modfurnaceon = new ModFurnace(true).setUnlocalizedName("ModFurnaceOn").setLightLevel(0.625F);
    }
    
    public static void register()
	{
		GameRegistry.registerBlock(titaniumore, titaniumore.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(modchest, modchest.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(modfurnaceoff, modfurnaceoff.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(modfurnaceon, modfurnaceon.getUnlocalizedName().substring(5));
	}
    
    public static void registerRenders()
	{
		registerRender(titaniumore);
		registerRender(modchest);
		registerRender(modfurnaceoff);
	}
	
	public static void registerRender(Block block)
	{	
		Item item = Item.getItemFromBlock(titaniumore);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Main.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		Item item1 = Item.getItemFromBlock(modchest);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item1, 0, new ModelResourceLocation(Main.MODID + ":" + item1.getUnlocalizedName().substring(5), "inventory"));
		Item item2 = Item.getItemFromBlock(modfurnaceoff);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item2, 0, new ModelResourceLocation(Main.MODID + ":" + item2.getUnlocalizedName().substring(5), "inventory"));
	}
}