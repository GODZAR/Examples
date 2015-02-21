package com.godzar.rpgmod.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.godzar.rpgmod.TileEntity.TileEntityModChest;

public class ModChest extends Block implements ITileEntityProvider
{
	public ModChest(Material material)
    {
    	super(material);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypeWood);
		this.maxY = 0.75F;
    }
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean isNormalCube()
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityModChest();
	}
}