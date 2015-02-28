package com.godzar.rpgmod.TileEntity;

import com.godzar.rpgmod.Blocks.ModFurnace;
import com.godzar.rpgmod.Container.ContainerModFurnace;
import com.godzar.rpgmod.Items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityModFurnace extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
	private String localizedName;
	private static final int[] slots_top = new int[]{0};
	private static final int[] slots_bottom = new int[]{2, 1};
	private static final int[] slots_side = new int[]{1};
	private ItemStack[] slots = new ItemStack[3];
	public int furnaceSpeed = 150;
	public int burnTime;
	public int currentItemBurnTime;
	public int cookTime;
	public void setGuiDisplayName(String displayName)
	{
		this.localizedName = displayName;
	}
	
	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.localizedName : "container.modFurnace";
	}

	@Override
	public boolean hasCustomName()
	{
		return this.localizedName != null && this.localizedName.length() > 0;
	}
	
	@Override
	public int getSizeInventory()
	{
		return this.slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return this.slots[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		if(this.slots[index] != null)
		{
			ItemStack stack;
			if(this.slots[index].stackSize <= count)
			{
				stack = this.slots[index];
				this.slots[index] = null;
				return stack;
			}
			else
			{
				stack = this.slots[index].splitStack(count);
				if(this.slots[index].stackSize == 0)
				{
					this.slots[index] = null;
				}
			}
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		if(this.slots[index] != null)
		{
			ItemStack stack = this.slots[index];
			this.slots[index] = null;
			return stack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		this.slots[index] = stack;
		if(stack != null && stack.stackSize > this.getInventoryStackLimit())
		{
			stack.stackSize = this.getInventoryStackLimit();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.slots.length)
            {
                this.slots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        this.burnTime = compound.getShort("BurnTime");
        this.cookTime = compound.getShort("CookTime");
        this.furnaceSpeed = compound.getShort("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.slots[1]);
        if (compound.hasKey("CustomName", 8))
        {
            this.localizedName = compound.getString("CustomName");
        }
    }

	@Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("BurnTime", (short)this.burnTime);
        compound.setShort("CookTime", (short)this.cookTime);
        compound.setShort("CookTimeTotal", (short)this.furnaceSpeed);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.slots.length; ++i)
        {
            if (this.slots[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.slots[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        compound.setTag("Items", nbttaglist);
        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.localizedName);
        }
    }

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	public void openInventory(EntityPlayer player){}
	public void closeInventory(EntityPlayer player){}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return index == 2 ? false : (index == 1 ? isItemFuel(stack) : true);
	}

	public static boolean isItemFuel(ItemStack stack)
	{
		return getItemBurnTime(stack) > 0;
	}

	private static int getItemBurnTime(ItemStack stack)
	{
		if(stack == null)
		{
			return 0;
		}
		else
		{
			Item item = stack.getItem();
			if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
			{
				Block block = Block.getBlockFromItem(item);
				if(block == Blocks.coal_block) return 16000;
				if(block == Blocks.wooden_slab) return 150;
				if(block.getMaterial() == Material.wood) return 300;
				if(block == Blocks.sapling) return 100;
			}
			if(item == Items.coal) return 1600;
			if(item == Items.stick) return 100;
			if(item == Items.lava_bucket) return 20000;
			if(item == Items.blaze_rod) return 2400;
		}
		return GameRegistry.getFuelValue(stack);
	}
	
	public boolean isBurning()
	{
		return this.burnTime > 0;
	}
	
	@Override
	public void update()
	{
		boolean flag = this.burnTime > 0;
		boolean flag1 = false;
		if(this.isBurning())
		{
			this.burnTime--;		
		}
		if(!this.worldObj.isRemote)
		{
			if(this.burnTime == 0 && this.canSmelt())
			{
				this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.slots[1]);
				if(this.isBurning())
				{
					flag1 = true;
					if(this.slots[1] != null)
					{
						this.slots[1].stackSize--;
						if(this.slots[1].stackSize == 0)
						{
							this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);
						}
					}
				}
			}
			if(this.isBurning() && this.canSmelt())
			{
				this.cookTime++;
				if(this.cookTime == this.furnaceSpeed)
				{
					this.cookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}
			else
			{
				this.cookTime = 0;
			}
			if(flag != this.isBurning())
			{
				flag1 = true;
				ModFurnace.setState(this.burnTime > 0, this.worldObj, this.pos);
			}
		}
		if(flag1)
		{
			this.markDirty();
		}
	}
	
	public boolean canSmelt()
	{
		if(this.slots[0] == null)
		{
			return false;
		}
		else
		{
			ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(this.slots[0]);
			if(stack == null) return false;
			if(this.slots[2] == null) return true;
			if(!this.slots[2].isItemEqual(stack)) return false;
			int result = this.slots[2].stackSize + stack.stackSize;
			return(result <= getInventoryStackLimit() && result <= stack.getMaxStackSize());
		}
	}
	
	public void smeltItem()
	{
		if(this.canSmelt())
		{
			ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(this.slots[0]);
			if(this.slots[2] == null)
			{
				this.slots[2] = stack.copy();
			}
			else if(this.slots[2].isItemEqual(stack))
			{
				this.slots[2].stackSize += stack.stackSize;
			}
			this.slots[0].stackSize--;
			if(this.slots[0].stackSize <= 0)
			{
				this.slots[0] = null;
			}
		}
	}

	@Override
	public int getField(int id)
	{
        switch (id)
        {
            case 0:
                return this.burnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.furnaceSpeed;
            default:
                return 0;
        }
	}

	@Override
	public void setField(int id, int value)
	{
        switch (id)
        {
            case 0:
                this.burnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.furnaceSpeed = value;
        }
	}

	@Override
	public int getFieldCount()
	{
		return 4;
	}

	@Override
	public void clear()
	{
        for (int i = 0; i < this.slots.length; ++i)
        {
            this.slots[i] = null;
        }
	}

	@Override
	public IChatComponent getDisplayName()
	{
		return null;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		return side == EnumFacing.DOWN ? slots_bottom : (side == EnumFacing.UP ? slots_top : slots_side);
	}

	@Override
	public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction)
	{
		return this.isItemValidForSlot(index, stack);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
	{
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();
            if (item != Items.water_bucket && item != Items.bucket)
            {
                return false;
            }
        }
        return true;
    }
	
	@Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player)
    {
        return new ContainerModFurnace(playerInventory, this);
    }

	@Override
	public String getGuiID()
	{
		return "minecraft:modfurnace";
	}
}