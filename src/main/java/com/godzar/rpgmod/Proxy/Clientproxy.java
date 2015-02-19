package com.godzar.rpgmod.Proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import com.godzar.rpgmod.Blocks.ModBlocks;
import com.godzar.rpgmod.Items.ModItems;
import com.godzar.rpgmod.Renderer.ModChestRender;
import com.godzar.rpgmod.TileEntity.TileEntityModChest;

public class Clientproxy extends Serverproxy
{
	@Override
	public void registerRenders()
	{
		ModBlocks.registerRenders();
		ModItems.registerRenders();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityModChest.class, new ModChestRender());
	}
}