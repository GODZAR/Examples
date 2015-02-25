package com.godzar.rpgmod.Container;

import com.godzar.rpgmod.TileEntity.TileEntityModFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerModFurnace extends Container
{
	private TileEntityModFurnace modFurnace;
	private int lastburnTime;
	private int lastcurrentItemBurnTime;
	private int lastcookTime;
	public ContainerModFurnace(InventoryPlayer inventory, TileEntityModFurnace entity)
	{
		this.modFurnace = entity;
		this.addSlotToContainer(new Slot(entity, 0, 56, 34));
		this.addSlotToContainer(new SlotFurnaceFuel(entity, 1, 56, 58));
		this.addSlotToContainer(new SlotFurnaceOutput(inventory.player, entity, 2, 116, 34));
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(inventory, j + i*9 + 9, 8 + j*18, 84 + i*18));
			}
		}
		for(int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(inventory, i, 8 + i*18, 142));
		}
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting icrafting)
	{
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.modFurnace.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.modFurnace.burnTime);
		icrafting.sendProgressBarUpdate(this, 2, this.modFurnace.currentItemBurnTime);
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for(int i = 0; i < this.crafters.size(); i++)
		{
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
			if(this.lastcookTime != this.modFurnace.cookTime)
			{
				icrafting.sendProgressBarUpdate(this, 0, this.modFurnace.cookTime);
			}
			if(this.lastburnTime != this.modFurnace.burnTime)
			{
				icrafting.sendProgressBarUpdate(this, 0, this.modFurnace.burnTime);
			}
			if(this.lastcurrentItemBurnTime != this.modFurnace.currentItemBurnTime)
			{
				icrafting.sendProgressBarUpdate(this, 0, this.modFurnace.currentItemBurnTime);
			}
		}
		this.lastcookTime = this.modFurnace.cookTime;
		this.lastburnTime = this.modFurnace.burnTime;
		this.lastcurrentItemBurnTime = this.modFurnace.currentItemBurnTime;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int newValue)
	{
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
}