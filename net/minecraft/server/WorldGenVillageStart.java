package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

class WorldGenVillageStart extends StructureStart
{
	private boolean c = false;

	public WorldGenVillageStart(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
	{
		ArrayList localArrayList1 = WorldGenVillagePieces.a(paramRandom, paramInt3);

		WorldGenVillageStartPiece localWorldGenVillageStartPiece = new WorldGenVillageStartPiece(paramWorld.getWorldChunkManager(), 0, paramRandom, (paramInt1 << 4) + 2, (paramInt2 << 4) + 2, localArrayList1, paramInt3);
		this.a.add(localWorldGenVillageStartPiece);
		localWorldGenVillageStartPiece.a(localWorldGenVillageStartPiece, this.a, paramRandom);

		ArrayList localArrayList2 = localWorldGenVillageStartPiece.j;
		ArrayList localArrayList3 = localWorldGenVillageStartPiece.i;
		while ((!localArrayList2.isEmpty()) || (!localArrayList3.isEmpty()))
		{
			if (localArrayList2.isEmpty()) {
				i = paramRandom.nextInt(localArrayList3.size());
				localObject = (StructurePiece)localArrayList3.remove(i);
				((StructurePiece)localObject).a(localWorldGenVillageStartPiece, this.a, paramRandom);
				continue;
			}i = paramRandom.nextInt(localArrayList2.size());
			localObject = (StructurePiece)localArrayList2.remove(i);
			((StructurePiece)localObject).a(localWorldGenVillageStartPiece, this.a, paramRandom);
		}

		c();

		int i = 0;
		for (Object localObject = this.a.iterator(); ((Iterator)localObject).hasNext(); ) { StructurePiece localStructurePiece = (StructurePiece)((Iterator)localObject).next();
			if (!(localStructurePiece instanceof WorldGenVillageRoadPiece)) {
				i++;
			}
		}
		this.c = (i > 2);
	}

	public boolean d()
	{
		return this.c;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageStart
 * JD-Core Version:		0.6.0
 */