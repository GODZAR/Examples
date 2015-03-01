package com.gadzar.mobs.proxy;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import com.gadzar.mobs.entity.EntityTortoise;
import com.gadzar.mobs.models.ModelTortoise;
import com.gadzar.mobs.renderer.RenderTortoise;

public class Clientproxy extends Serverproxy
{
	public void registerRenderThings()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityTortoise.class, new RenderTortoise(null, new ModelTortoise(), 0.3F));
	}
}