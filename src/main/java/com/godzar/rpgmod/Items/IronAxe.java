package com.godzar.rpgmod.Items;

import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class IronAxe extends ItemSword
{
	public static ToolMaterial axematerial = EnumHelper.addToolMaterial("axematerial", 0, 750, 2.0F, 10.0F, 10);
	public IronAxe()
	{
		super(axematerial);
		this.setMaxStackSize(1);
		this.setMaxDamage(64);
		this.setFull3D();
		this.setNoRepair();
	}
}