package com.godzar.rpgmod.Blocks;

import java.util.Random;
import com.godzar.rpgmod.TileEntity.TileEntityModChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ModChest extends BlockContainer
{
	//public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public ModChest(Material material)
    {
    	super(material);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
    }
	
	public int getRenderType()
	{
		return -1;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityModChest();
	}
}