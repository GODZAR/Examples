package com.gadzar.rpgmod.Gui;

import org.lwjgl.opengl.GL11;

import com.gadzar.rpgmod.Main;
import com.gadzar.rpgmod.Container.ContainerModFurnace;
import com.gadzar.rpgmod.TileEntity.TileEntityModFurnace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiModFurnace extends GuiContainer
{
	private static final ResourceLocation bground = new ResourceLocation(Main.MODID + ":" + "textures/gui/GuiModFurnace.png");
	public TileEntityModFurnace modFurnace;
	public GuiModFurnace(InventoryPlayer inventoryPlayer, TileEntityModFurnace entity)
	{
		super(new ContainerModFurnace(inventoryPlayer, entity));
		this.modFurnace = entity;
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String name = "Mod Furnace";
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 6, this.ySize - 92, 4210752);
	}	

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		if(this.modFurnace.isBurning())
		{
			int k = this.modFurnace.getBurnTimeRemainingScaled(14);
			this.drawTexturedModalRect(guiLeft + 74, guiTop + 58 + 14 - k, 176, 26 - k, 14, k + 1);
		}		
		int k = this.modFurnace.getCookProgressScaled(31);
		this.drawTexturedModalRect(guiLeft + 76, guiTop + 36, 176, 0, k + 1, 14);
	}
}