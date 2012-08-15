package net.minecraft.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class StructureGenerator extends WorldGenBase
{
	protected Map d = new HashMap();

	protected void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte)
	{
		if (this.d.containsKey(Long.valueOf(ChunkCoordIntPair.a(paramInt1, paramInt2)))) {
			return;
		}

		this.b.nextInt();
		if (a(paramInt1, paramInt2)) {
			StructureStart localStructureStart = b(paramInt1, paramInt2);
			this.d.put(Long.valueOf(ChunkCoordIntPair.a(paramInt1, paramInt2)), localStructureStart);
		}
	}

	public boolean a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2)
	{
		int i = (paramInt1 << 4) + 8;
		int j = (paramInt2 << 4) + 8;

		int k = 0;
		for (StructureStart localStructureStart : this.d.values()) {
			if ((localStructureStart.d()) && 
				(localStructureStart.a().a(i, j, i + 15, j + 15))) {
				localStructureStart.a(paramWorld, paramRandom, new StructureBoundingBox(i, j, i + 15, j + 15));
				k = 1;
			}

		}

		return k;
	}

	public boolean a(int paramInt1, int paramInt2, int paramInt3)
	{
		for (StructureStart localStructureStart : this.d.values()) {
			if ((localStructureStart.d()) && 
				(localStructureStart.a().a(paramInt1, paramInt3, paramInt1, paramInt3)))
			{
				Iterator localIterator2 = localStructureStart.b().iterator();
				while (localIterator2.hasNext()) {
					StructurePiece localStructurePiece = (StructurePiece)localIterator2.next();
					if (localStructurePiece.b().b(paramInt1, paramInt2, paramInt3)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public ChunkPosition getNearestGeneratedFeature(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		this.c = paramWorld;

		this.b.setSeed(paramWorld.getSeed());
		long l1 = this.b.nextLong();
		long l2 = this.b.nextLong();
		long l3 = (paramInt1 >> 4) * l1;
		long l4 = (paramInt3 >> 4) * l2;
		this.b.setSeed(l3 ^ l4 ^ paramWorld.getSeed());

		a(paramWorld, paramInt1 >> 4, paramInt3 >> 4, 0, 0, null);

		double d1 = 1.7976931348623157E+308D;
		Object localObject1 = null;

		for (Object localObject2 = this.d.values().iterator(); ((Iterator)localObject2).hasNext(); ) { localObject3 = (StructureStart)((Iterator)localObject2).next();
			if (((StructureStart)localObject3).d())
			{
				localObject4 = (StructurePiece)((StructureStart)localObject3).b().get(0);
				localChunkPosition = ((StructurePiece)localObject4).a();

				i = localChunkPosition.x - paramInt1;
				j = localChunkPosition.y - paramInt2;
				k = localChunkPosition.z - paramInt3;
				d2 = i + i * j * j + k * k;

				if (d2 < d1) {
					d1 = d2;
					localObject1 = localChunkPosition;
				}
			}
		}
		Object localObject3;
		Object localObject4;
		ChunkPosition localChunkPosition;
		int i;
		int j;
		int k;
		double d2;
		if (localObject1 != null) {
			return localObject1;
		}
		localObject2 = o_();
		if (localObject2 != null) {
			localObject3 = null;
			for (localObject4 = ((List)localObject2).iterator(); ((Iterator)localObject4).hasNext(); ) { localChunkPosition = (ChunkPosition)((Iterator)localObject4).next();

				i = localChunkPosition.x - paramInt1;
				j = localChunkPosition.y - paramInt2;
				k = localChunkPosition.z - paramInt3;
				d2 = i + i * j * j + k * k;

				if (d2 < d1) {
					d1 = d2;
					localObject3 = localChunkPosition;
				}
			}
			return localObject3;
		}

		return (ChunkPosition)(ChunkPosition)(ChunkPosition)null;
	}

	protected List o_() {
		return null;
	}

	protected abstract boolean a(int paramInt1, int paramInt2);

	protected abstract StructureStart b(int paramInt1, int paramInt2);
}

/* 
 * Qualified Name:		 net.minecraft.server.StructureGenerator
 * JD-Core Version:		0.6.0
 */