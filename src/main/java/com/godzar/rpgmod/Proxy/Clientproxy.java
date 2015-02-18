package com.godzar.rpgmod.Proxy;

import com.godzar.rpgmod.Blocks.Blocks;
import com.godzar.rpgmod.Items.Items;

public class Clientproxy extends Serverproxy
{
	@Override
	public void registerRenders()
	{
		Blocks.registerRenders();
		Items.registerRenders();
	}
}