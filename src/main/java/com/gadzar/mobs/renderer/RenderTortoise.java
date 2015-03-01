package com.gadzar.mobs.renderer;

import com.gadzar.mobs.Main;
import com.gadzar.mobs.entity.EntityTortoise;
import com.gadzar.mobs.models.ModelTortoise;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderTortoise extends RenderLiving
{
	private static final ResourceLocation texture = new ResourceLocation(Main.MODID + ":" + "textures/models/Tortoise.png");
	protected ModelTortoise modelEntity;
	public RenderTortoise(RenderManager renderManager, ModelBase model, float x)
	{
		super(renderManager, model, x);
		modelEntity = ((ModelTortoise)mainModel);
	}
	
	public void renderTortoise(EntityTortoise entity, double x, double y, double z, float u, float v)
	{
		super.doRender(entity, x, y, z, u, v);
	}
	
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float u, float v)
	{
		renderTortoise((EntityTortoise)entityLiving, x, y, z, u, v);
	}
	
	public void doRender(Entity entity, double x, double y, double z, float u, float v)
	{
		renderTortoise((EntityTortoise)entity, x, y, z, u, v);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}