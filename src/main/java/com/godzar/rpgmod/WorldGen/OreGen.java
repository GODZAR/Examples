package com.godzar.rpgmod.WorldGen;

import java.util.Random;
import com.godzar.rpgmod.Blocks.ModBlocks;
import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class OreGen implements IWorldGenerator
{
	public static OreGen eventoregen = new OreGen();
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.getDimensionId())
		{
		case 0:
			generateSurface(world, random, chunkX*16, chunkZ*16);
		case -1:
			generateNether(world, random, chunkX*16, chunkZ*16);
		case 1:
			generateEnd(world, random, chunkX*16, chunkZ*16);
		}
	}
	private void generateSurface(World world, Random random, int x, int z)
	{
		this.addOreSpawn(ModBlocks.titaniumore, world, random, x, z, 16, 16, 4+random.nextInt(6), 25, 38, 100);
	}

	private void generateNether(World world, Random random, int x, int z)
	{
		
	}
	
	private void generateEnd(World world, Random random, int x, int z)
	{
		
	}
	
	private void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chanceToSpawn, int minY, int maxY)
	{
		for(int i = 0; i < chanceToSpawn; i++)
		{
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = blockZPos + random.nextInt(maxZ);
			IBlockState state = block.getDefaultState();
			BlockPos blockPos = new BlockPos(posX, posY, posZ);
			(new WorldGenMinable(state, maxVeinSize)).generate(world, random, blockPos);
		}
	}
	
	public static void register()
	{
		GameRegistry.registerWorldGenerator(eventoregen, 0);
	}
}