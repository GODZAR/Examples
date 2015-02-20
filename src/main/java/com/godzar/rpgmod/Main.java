package com.godzar.rpgmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.godzar.rpgmod.Blocks.ModBlocks;
import com.godzar.rpgmod.Crafting.Recipes;
import com.godzar.rpgmod.Items.ModItems;
import com.godzar.rpgmod.Proxy.Serverproxy;
import com.godzar.rpgmod.WorldGen.OreGen;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
    public static final String MODID = "rpgmod";
    public static final String VERSION = "0.0.1";
    public static final String CLIENT_PROXY = "com.godzar.rpgmod.Proxy.Clientproxy";
	public static final String SERVER_PROXY = "com.godzar.rpgmod.Proxy.Serverproxy";
	
	@SidedProxy(clientSide = Main.CLIENT_PROXY, serverSide = Main.SERVER_PROXY)
	public static Serverproxy proxy;
    
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ModItems.init();
		ModItems.register();
		ModBlocks.init();
		ModBlocks.register();
		OreGen.register();
	}
	
	@Mod.EventHandler
	public void Init(FMLInitializationEvent event)
	{
		proxy.registerRenders();
		proxy.registerTileEntitySpecialRenderer();
		Recipes.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}