package com.godzar.rpgmod.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.godzar.rpgmod.TileEntity.TileEntityModChest;

public class ModChest extends BlockContainer
{
	public ModChest(Material material)
    {
    	super(material);
		this.setHardness(1.5F);
		this.setResistance(5.0F);
		this.setHarvestLevel("axe", 0);
		this.setStepSound(Block.soundTypeWood);
		this.setBlockBounds(0, 0, 0, 1.0F, 0.75F, 1.0F);
    }
	
	@Override
    public int getRenderType()
    {
        return -1;
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