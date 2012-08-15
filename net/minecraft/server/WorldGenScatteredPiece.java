package net.minecraft.server;

import java.util.Random;

abstract class WorldGenScatteredPiece extends StructurePiece
{
	protected final int a;
	protected final int b;
	protected final int c;
	protected int d = -1;

	protected WorldGenScatteredPiece(Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
		super(0);

		this.a = paramInt4;
		this.b = paramInt5;
		this.c = paramInt6;

		this.f = paramRandom.nextInt(4);

		switch (this.f) {
		case 0:
		case 2:
			this.e = new StructureBoundingBox(paramInt1, paramInt2, paramInt3, paramInt1 + paramInt4 - 1, paramInt2 + paramInt5 - 1, paramInt3 + paramInt6 - 1);
			break;
		default:
			this.e = new StructureBoundingBox(paramInt1, paramInt2, paramInt3, paramInt1 + paramInt6 - 1, paramInt2 + paramInt5 - 1, paramInt3 + paramInt4 - 1);
		}
	}

	protected boolean a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt)
	{
		if (this.d >= 0) {
			return true;
		}

		int i = 0;
		int j = 0;
		for (int k = this.e.c; k <= this.e.f; k++) {
			for (int m = this.e.a; m <= this.e.d; m++) {
				if (paramStructureBoundingBox.b(m, 64, k)) {
					i += Math.max(paramWorld.h(m, k), paramWorld.worldProvider.getSeaLevel());
					j++;
				}
			}
		}

		if (j == 0) {
			return false;
		}
		this.d = (i / j);
		this.e.a(0, this.d - this.e.b + paramInt, 0);
		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenScatteredPiece
 * JD-Core Version:		0.6.0
 */