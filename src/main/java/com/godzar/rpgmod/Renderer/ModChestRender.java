package com.godzar.rpgmod.Renderer;

import org.lwjgl.opengl.GL11;
import com.godzar.rpgmod.Main;
import com.godzar.rpgmod.Models.ModelModChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class ModChestRender extends TileEntitySpecialRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f, int i)
	{
		GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			GL11.glRotatef(180, 0F, 0F, 1F);					
			GL11.glPushMatrix();
			GL11.glPopMatrix();	
		GL11.glPopMatrix();
	}	
}