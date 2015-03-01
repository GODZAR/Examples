package com.gadzar.mobs.handler;

import java.util.Random;
import com.gadzar.mobs.Main;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityHandler
{
	public static void registerAnimals(Class entityClass, String name)
	{
		int entiyId = EntityRegistry.findGlobalUniqueEntityId();
		long x = name.hashCode();
		Random random = new Random(x);
		int mainColor = random.nextInt() * 16777215;
		int subColor = random.nextInt() * 16777215;
		EntityRegistry.registerGlobalEntityID(entityClass, name, entiyId);
		EntityRegistry.addSpawn(entityClass, 50, 2, 4, EnumCreatureType.CREATURE, BiomeGenBase.desert.beach);
		EntityRegistry.registerModEntity(entityClass, name, entiyId, Main.instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entiyId), new EntityList.EntityEggInfo(entiyId, mainColor, subColor));
	}
}