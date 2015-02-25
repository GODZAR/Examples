package com.godzar.rpgmod.Gui;

import org.lwjgl.opengl.GL11;
import com.godzar.rpgmod.Main;
import com.godzar.rpgmod.Container.ContainerModFurnace;
import com.godzar.rpgmod.TileEntity.TileEntityModFurnace;
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
	}	
}