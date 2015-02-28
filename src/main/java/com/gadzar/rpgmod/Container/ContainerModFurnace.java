package com.gadzar.rpgmod.Container;

import com.gadzar.rpgmod.TileEntity.TileEntityModFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
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
		if(slot == 0)
		{
			this.modFurnace.cookTime = newValue;
		}
		if(slot == 1)
		{
			this.modFurnace.burnTime = newValue;
		}
		if(slot == 2)
		{
			this.modFurnace.currentItemBurnTime = newValue;
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (FurnaceRecipes.instance().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemstack1);
        }
        return itemstack;
    }
}