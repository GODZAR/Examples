package com.godzar.rpgmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import com.godzar.rpgmod.Blocks.ModBlocks;
import com.godzar.rpgmod.Crafting.Recipes;
import com.godzar.rpgmod.Handler.CraftingHandler;
import com.godzar.rpgmod.Handler.FuelHandler;
import com.godzar.rpgmod.Handler.GuiHandler;
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
	
	@SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
	public static Serverproxy proxy;
	
	@Instance(MODID)
	public static Main instance;
    
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ModItems.init();
		ModItems.register();
		ModBlocks.init();
		ModBlocks.register();
		OreGen.register();
		FuelHandler.register();
	}
	
	@Mod.EventHandler
	public void Init(FMLInitializationEvent event)
	{
		proxy.registerRenders();
		proxy.registerTileEntitySpecialRenderer();
		Recipes.init();
		CraftingHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}