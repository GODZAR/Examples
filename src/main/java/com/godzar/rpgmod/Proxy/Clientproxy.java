package com.godzar.rpgmod.Proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import com.godzar.rpgmod.Blocks.ModBlocks;
import com.godzar.rpgmod.Blocks.ModChest;
import com.godzar.rpgmod.Items.ModItems;
import com.godzar.rpgmod.Renderer.ItemModChestRender;
import com.godzar.rpgmod.Renderer.ModChestRender;
import com.godzar.rpgmod.TileEntity.TileEntityModChest;

public class Clientproxy extends Serverproxy
{
	@Override
	public void registerRenders()
	{
		ModBlocks.registerRenders();
		ModItems.registerRenders();
		TileEntitySpecialRenderer render = new ModChestRender();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityModChest.class, render);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.modchest), new ItemModChestRender(render, new TileEntityModChest()));
	}
	
	@Override
	public void registerTileEntitySpecialRenderer()
	{
		
	}
}