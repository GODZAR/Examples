package com.gadzar.rpgmod.Handler;

import com.gadzar.rpgmod.Container.ContainerModFurnace;
import com.gadzar.rpgmod.Gui.GuiModFurnace;
import com.gadzar.rpgmod.TileEntity.TileEntityModFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity entity = world.getTileEntity(pos);
		if(entity != null)
		{
			switch(ID)
			{
			case 0:
				if(entity instanceof TileEntityModFurnace)
				{
					return new ContainerModFurnace(player.inventory, (TileEntityModFurnace) entity);
				}
				return null;
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity entity = world.getTileEntity(pos);
		if(entity != null)
		{
			switch(ID)
			{
			case 0:
				if(entity instanceof TileEntityModFurnace)
				{
					return new GuiModFurnace(player.inventory, (TileEntityModFurnace) entity);
				}
				return null;
			}
		}
		return null;
	}
}