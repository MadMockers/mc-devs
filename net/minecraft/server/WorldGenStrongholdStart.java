package net.minecraft.server;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenStrongholdStart extends WorldGenStrongholdStairs2
{
	public WorldGenStrongholdPieceWeight a;
	public WorldGenStrongholdPortalRoom b;
	public ArrayList c = new ArrayList();

	public WorldGenStrongholdStart(int paramInt1, Random paramRandom, int paramInt2, int paramInt3) {
		super(0, paramRandom, paramInt2, paramInt3);
	}

	public ChunkPosition a()
	{
		if (this.b != null) {
			return this.b.a();
		}
		return super.a();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdStart
 * JD-Core Version:		0.6.0
 */