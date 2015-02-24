package com.godzar.rpgmod.Proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.godzar.rpgmod.Blocks.ModBlocks;
import com.godzar.rpgmod.Blocks.ModChest;
import com.godzar.rpgmod.Handler.GuiHandler;
import com.godzar.rpgmod.Items.ModItems;
import com.godzar.rpgmod.Renderer.ModChestRender;
import com.godzar.rpgmod.TileEntity.TileEntityModChest;
import com.godzar.rpgmod.TileEntity.TileEntityModFurnace;

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