package com.godzar.rpgmod.TileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class TileEntityModChest extends TileEntity implements IInventory
{
	private ItemStack[] cabinetContents = new ItemStack[15];
	private String customName;

	public int getSizeInventory()
	{
		return 15;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return this.cabinetContents[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.cabinetContents[par1] != null)
		{
			ItemStack var3;

			if (this.cabinetContents[par1].stackSize <= par2)
			{
				var3 = this.cabinetContents[par1];
				this.cabinetContents[par1] = null;
				this.markDirty();
				return var3;
			}
			var3 = this.cabinetContents[par1].splitStack(par2);

			if (this.cabinetContents[par1].stackSize == 0)
			{
				this.cabinetContents[par1] = null;
			}

			this.markDirty();
			return var3;
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.cabinetContents[par1] != null)
		{
			ItemStack var2 = this.cabinetContents[par1];
			this.cabinetContents[par1] = null;
			return var2;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.cabinetContents[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList var2 = (NBTTagList) par1NBTTagCompound.getTag("cabinetItems");
		this.cabinetContents = new ItemStack[this.getSizeInventory()];

		for (int var3 = 0; var3 < var2.tagCount(); ++var3)
		{
			NBTTagCompound var4 = (NBTTagCompound) var2.getCompoundTagAt(var3);
			int var5 = var4.getByte("cabinetSlot") & 255;

			if (var5 >= 0 && var5 < this.cabinetContents.length)
			{
				this.cabinetContents[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		NBTTagList var2 = new NBTTagList();

		for (int var3 = 0; var3 < this.cabinetContents.length; ++var3)
		{
			if (this.cabinetContents[var3] != null)
			{
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("cabinetSlot", (byte) var3);
				this.cabinetContents[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}

		par1NBTTagCompound.setTag("cabinetItems", var2);
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return this.worldObj.getTileEntity(pos) != this ? false : entityplayer.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void invalidate()
	{
		this.updateContainingBlockInfo();
		super.invalidate();
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return true;
	}

	@Override
	public String getName()
	{
		return hasCustomName() ? customName : "Kitchen Cabinet";
	}

	@Override
	public boolean hasCustomName()
	{
		return customName != null;
	}

	@Override
	public IChatComponent getDisplayName()
	{
		return new ChatComponentText(getName());
	}

	@Override
	public void openInventory(EntityPlayer player)
	{
		worldObj.playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "cfm:cabinetopen", 0.75F, 0.9F);
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
		worldObj.playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "cfm:cabinetclose", 0.75F, 0.8F);
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{		
	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < cabinetContents.length; i++)
		{
			cabinetContents[i] = null;
		}	
	}
}