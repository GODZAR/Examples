package com.gadzar.rpgmod.Proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.gadzar.rpgmod.Blocks.ModBlocks;
import com.gadzar.rpgmod.Blocks.ModChest;
import com.gadzar.rpgmod.Handler.GuiHandler;
import com.gadzar.rpgmod.Items.ModItems;
import com.gadzar.rpgmod.Renderer.ModChestRender;
import com.gadzar.rpgmod.TileEntity.TileEntityModChest;
import com.gadzar.rpgmod.TileEntity.TileEntityModFurnace;

public class Clientproxy extends Serverproxy
{
	@Override
	public void registerRenders()
	{
		ModBlocks.registerRenders();
		ModItems.registerRenders();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityModChest.class, new ModChestRender());
		GameRegistry.registerTileEntity(TileEntityModFurnace.class, "ModFurnace");
	}
	
	@Override
	public void registerTileEntitySpecialRenderer()
	{
		
	}
}