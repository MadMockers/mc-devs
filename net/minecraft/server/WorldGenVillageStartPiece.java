package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenVillageStartPiece extends WorldGenVillageWell
{
	public final WorldChunkManager a;
	public final boolean b;
	public final int c;
	public WorldGenVillagePieceWeight d;
	public ArrayList h;
	public ArrayList i = new ArrayList();
	public ArrayList j = new ArrayList();

	public WorldGenVillageStartPiece(WorldChunkManager paramWorldChunkManager, int paramInt1, Random paramRandom, int paramInt2, int paramInt3, ArrayList paramArrayList, int paramInt4) {
		super(null, 0, paramRandom, paramInt2, paramInt3);
		this.a = paramWorldChunkManager;
		this.h = paramArrayList;
		this.c = paramInt4;

		BiomeBase localBiomeBase = paramWorldChunkManager.getBiome(paramInt2, paramInt3);
		this.b = ((localBiomeBase == BiomeBase.DESERT) || (localBiomeBase == BiomeBase.DESERT_HILLS));
		this.k = this;
	}

	public WorldChunkManager d() {
		return this.a;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenVillageStartPiece
 * JD-Core Version:		0.6.0
 */