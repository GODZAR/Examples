package com.gadzar.mobs;

import com.gadzar.mobs.entity.EntityTortoise;
import com.gadzar.mobs.handler.EntityHandler;
import com.gadzar.mobs.proxy.Serverproxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
    public static final String MODID = "mobs";
    public static final String VERSION = "1.0.0";
    public static final String CLIENT_PROXY = "com.gadzar.mobs.proxy.Clientproxy";
	public static final String SERVER_PROXY = "com.gadzar.mobs.proxy.Serverproxy";
	
	@SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
	public static Serverproxy proxy;
	
	@Instance(MODID)
	public static Main instance;
    
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

	}
	
	@Mod.EventHandler
	public void Init(FMLInitializationEvent event)
	{
		proxy.registerRenderThings();
		EntityHandler.registerAnimals(EntityTortoise.class, "Tortoise");
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}