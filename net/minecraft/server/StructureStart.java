package net.minecraft.server;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public abstract class StructureStart
{
	protected LinkedList a = new LinkedList();
	protected StructureBoundingBox b;

	public StructureBoundingBox a()
	{
		return this.b;
	}

	public LinkedList b() {
		return this.a;
	}

	public void a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox) {
		Iterator localIterator = this.a.iterator();
		while (localIterator.hasNext()) {
			StructurePiece localStructurePiece = (StructurePiece)localIterator.next();
			if ((localStructurePiece.b().a(paramStructureBoundingBox)) && 
				(!localStructurePiece.a(paramWorld, paramRandom, paramStructureBoundingBox)))
			{
				localIterator.remove();
			}
		}
	}

	protected void c()
	{
		this.b = StructureBoundingBox.a();

		for (StructurePiece localStructurePiece : this.a)
			this.b.b(localStructurePiece.b());
	}

	protected void a(World paramWorld, Random paramRandom, int paramInt)
	{
		int i = 63 - paramInt;

		int j = this.b.c() + 1;

		if (j < i) {
			j += paramRandom.nextInt(i - j);
		}

		int k = j - this.b.e;
		this.b.a(0, k, 0);
		for (StructurePiece localStructurePiece : this.a)
			localStructurePiece.b().a(0, k, 0);
	}

	protected void a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2)
	{
		int i = paramInt2 - paramInt1 + 1 - this.b.c();
		int j = 1;

		if (i > 1)
			j = paramInt1 + paramRandom.nextInt(i);
		else {
			j = paramInt1;
		}

		int k = j - this.b.b;
		this.b.a(0, k, 0);
		for (StructurePiece localStructurePiece : this.a)
			localStructurePiece.b().a(0, k, 0);
	}

	public boolean d()
	{
		return true;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.StructureStart
 * JD-Core Version:		0.6.0
 */