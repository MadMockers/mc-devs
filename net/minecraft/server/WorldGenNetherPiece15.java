package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece15 extends WorldGenNetherPiece1
{
	public WorldGenNetherPieceWeight a;
	public List b = new ArrayList();
	public List c;
	public ArrayList d = new ArrayList();

	public WorldGenNetherPiece15(Random paramRandom, int paramInt1, int paramInt2) {
		super(paramRandom, paramInt1, paramInt2);
		WorldGenNetherPieceWeight localWorldGenNetherPieceWeight;
		for (localWorldGenNetherPieceWeight : WorldGenNetherPieces.a()) {
			localWorldGenNetherPieceWeight.c = 0;
			this.b.add(localWorldGenNetherPieceWeight);
		}

		this.c = new ArrayList();
		for (localWorldGenNetherPieceWeight : WorldGenNetherPieces.b()) {
			localWorldGenNetherPieceWeight.c = 0;
			this.c.add(localWorldGenNetherPieceWeight);
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece15
 * JD-Core Version:		0.6.0
 */