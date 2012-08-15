package net.minecraft.server;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WorldGenMineshaftRoom extends StructurePiece
{
	private List a = new LinkedList();

	public WorldGenMineshaftRoom(int paramInt1, Random paramRandom, int paramInt2, int paramInt3) {
		super(paramInt1);

		this.e = new StructureBoundingBox(paramInt2, 50, paramInt3, paramInt2 + 7 + paramRandom.nextInt(6), 54 + paramRandom.nextInt(6), paramInt3 + 7 + paramRandom.nextInt(6));
	}

	public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
	{
		int i = c();

		int j = this.e.c() - 3 - 1;
		if (j <= 0) {
			j = 1;
		}

		int k = 0;
		StructurePiece localStructurePiece;
		StructureBoundingBox localStructureBoundingBox;
		while (k < this.e.b()) {
			k += paramRandom.nextInt(this.e.b());
			if (k + 3 > this.e.b()) {
				break;
			}
			localStructurePiece = WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a + k, this.e.b + paramRandom.nextInt(j) + 1, this.e.c - 1, 2, i);

			if (localStructurePiece != null) {
				localStructureBoundingBox = localStructurePiece.b();
				this.a.add(new StructureBoundingBox(localStructureBoundingBox.a, localStructureBoundingBox.b, this.e.c, localStructureBoundingBox.d, localStructureBoundingBox.e, this.e.c + 1));
			}
			k += 4;
		}

		k = 0;
		while (k < this.e.b()) {
			k += paramRandom.nextInt(this.e.b());
			if (k + 3 > this.e.b()) {
				break;
			}
			localStructurePiece = WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a + k, this.e.b + paramRandom.nextInt(j) + 1, this.e.f + 1, 0, i);

			if (localStructurePiece != null) {
				localStructureBoundingBox = localStructurePiece.b();
				this.a.add(new StructureBoundingBox(localStructureBoundingBox.a, localStructureBoundingBox.b, this.e.f - 1, localStructureBoundingBox.d, localStructureBoundingBox.e, this.e.f));
			}
			k += 4;
		}

		k = 0;
		while (k < this.e.d()) {
			k += paramRandom.nextInt(this.e.d());
			if (k + 3 > this.e.d()) {
				break;
			}
			localStructurePiece = WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b + paramRandom.nextInt(j) + 1, this.e.c + k, 1, i);

			if (localStructurePiece != null) {
				localStructureBoundingBox = localStructurePiece.b();
				this.a.add(new StructureBoundingBox(this.e.a, localStructureBoundingBox.b, localStructureBoundingBox.c, this.e.a + 1, localStructureBoundingBox.e, localStructureBoundingBox.f));
			}
			k += 4;
		}

		k = 0;
		while (k < this.e.d()) {
			k += paramRandom.nextInt(this.e.d());
			if (k + 3 > this.e.d()) {
				break;
			}
			localStructurePiece = WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b + paramRandom.nextInt(j) + 1, this.e.c + k, 3, i);

			if (localStructurePiece != null) {
				localStructureBoundingBox = localStructurePiece.b();
				this.a.add(new StructureBoundingBox(this.e.d - 1, localStructureBoundingBox.b, localStructureBoundingBox.c, this.e.d, localStructureBoundingBox.e, localStructureBoundingBox.f));
			}
			k += 4;
		}
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		if (a(paramWorld, paramStructureBoundingBox)) {
			return false;
		}

		a(paramWorld, paramStructureBoundingBox, this.e.a, this.e.b, this.e.c, this.e.d, this.e.b, this.e.f, Block.DIRT.id, 0, true);

		a(paramWorld, paramStructureBoundingBox, this.e.a, this.e.b + 1, this.e.c, this.e.d, Math.min(this.e.b + 3, this.e.e), this.e.f, 0, 0, false);
		for (StructureBoundingBox localStructureBoundingBox : this.a) {
			a(paramWorld, paramStructureBoundingBox, localStructureBoundingBox.a, localStructureBoundingBox.e - 2, localStructureBoundingBox.c, localStructureBoundingBox.d, localStructureBoundingBox.e, localStructureBoundingBox.f, 0, 0, false);
		}
		a(paramWorld, paramStructureBoundingBox, this.e.a, this.e.b + 4, this.e.c, this.e.d, this.e.e, this.e.f, 0, false);

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenMineshaftRoom
 * JD-Core Version:		0.6.0
 */