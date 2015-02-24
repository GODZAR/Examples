package com.godzar.rpgmod.TileEntity;

import com.godzar.rpgmod.Blocks.ModFurnace;
import com.godzar.rpgmod.Container.ContainerModFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;

public class TileEntityModFurnace extends TileEntityLockable implements ISidedInventory
{
	private String localizedName;
	private static final int[] slots_top = new int[]{0};
	private static final int[] slots_bottom = new int[]{2, 1};
	private static final int[] slots_side = new int[]{1};
	private ItemStack[] slots = new ItemStack[3];
	private int totalCookTime;
	public int furnaceBurnTime;
	public int currentItemBurnTime;
	public int cookTime;
	public void setCustomInventoryName(String displayName)
	{
		this.localizedName = displayName;
	}
	
	public String getInventoryName()
	{
		return this.hasCustomInventoryName() ? this.localizedName : "container.modFurnace";
	}

	public boolean hasCustomInventoryName()
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
        if (this.slots[index] != null)
        {
            ItemStack itemstack;
            if (this.slots[index].stackSize <= count)
            {
                itemstack = this.slots[index];
                this.slots[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.slots[index].splitStack(count);

                if (this.slots[index].stackSize == 0)
                {
                    this.slots[index] = null;
                }
                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
        if (this.slots[index] != null)
        {
            ItemStack itemstack = this.slots[index];
            this.slots[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
        boolean flag = stack != null && stack.isItemEqual(this.slots[index]) && ItemStack.areItemStackTagsEqual(stack, this.slots[index]);
        this.slots[index] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
        if (index == 0 && !flag)
        {
            this.totalCookTime = this.total(stack);
            this.cookTime = 0;
            this.markDirty();
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

	@Override
	public void openInventory(EntityPlayer player){}
	@Override
	public void closeInventory(EntityPlayer player){}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return index == 2 ? false : (index != 1 ? true : isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack));
	}
	
	public static boolean isItemFuel(ItemStack stack)
	{
		return getItemBurnTime(stack) > 0;	
	}
	
    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack == null)
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();
            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);
                if (block == Blocks.wooden_slab)
                {
                    return 150;
                }
                if (block.getMaterial() == Material.wood)
                {
                    return 300;
                }
                if (block == Blocks.coal_block)
                {
                    return 16000;
                }
            }
            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe)item).getMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(stack);
        }
    }

	@Override
	public int getField(int id)
	{
        switch (id)
        {
            case 0:
                return this.furnaceBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
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
                this.furnaceBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
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
	
	public void update()
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;
        if (this.isBurning())
        {
            --this.furnaceBurnTime;
        }
        if (!this.worldObj.isRemote)
        {
            if (!this.isBurning() && (this.slots[1] == null || this.slots[0] == null))
            {
                if (!this.isBurning() && this.cookTime > 0)
                {
                    this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
                }
            }
            else
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.slots[1]);
                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (this.slots[1] != null)
                        {
                            --this.slots[1].stackSize;

                            if (this.slots[1].stackSize == 0)
                            {
                                this.slots[1] = slots[1].getItem().getContainerItem(slots[1]);
                            }
                        }
                    }
                }
                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;
                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.total(this.slots[0]);
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            if (flag != this.isBurning())
            {
                flag1 = true;
                ModFurnace.setState(this.isBurning(), this.worldObj, this.pos);
            }
        }
        if (flag1)
        {
            this.markDirty();
        }
    }
	
    public int total(ItemStack stack)
    {
    	return 200;
	}

	public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }
	
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.slots[0]);
            if (this.slots[2] == null)
            {
                this.slots[2] = itemstack.copy();
            }
            else if (this.slots[2].getItem() == itemstack.getItem())
            {
                this.slots[2].stackSize += itemstack.stackSize;
            }
            if (this.slots[0].getItem() == Item.getItemFromBlock(Blocks.sponge) && this.slots[0].getMetadata() == 1 && this.slots[1] != null && this.slots[1].getItem() == Items.bucket)
            {
                this.slots[1] = new ItemStack(Items.water_bucket);
            }
            --this.slots[0].stackSize;
            if (this.slots[0].stackSize <= 0)
            {
                this.slots[0] = null;
            }
        }
    }
    
    private boolean canSmelt()
    {
        if (this.slots[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.slots[0]);
            if (itemstack == null) return false;
            if (this.slots[2] == null) return true;
            if (!this.slots[2].isItemEqual(itemstack)) return false;
            int result = slots[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.slots[2].getMaxStackSize();
        }
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
	public String getName()
	{
		return this.hasCustomInventoryName() ? this.localizedName : "container.modFurnace";
	}

	@Override
	public boolean hasCustomName()
	{
		return this.localizedName != null && this.localizedName.length() > 0;
	}
	
	@Override
    public Container createContainer(InventoryPlayer inventory, EntityPlayer player)
    {
        return new ContainerModFurnace(inventory, this);
    }

	@Override
	public String getGuiID()
	{
		return null;
	}
}