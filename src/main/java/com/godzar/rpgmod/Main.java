package com.godzar.rpgmod;

import com.godzar.rpgmod.Blocks.Blocks;
import com.godzar.rpgmod.Items.Items;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
    public static final String MODID = "rpgmod";
    public static final String VERSION = "0.0.1";
    
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Items.init();
		Blocks.init();
	}
	
	@Mod.EventHandler
	public void Init(FMLInitializationEvent event)
	{

	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}