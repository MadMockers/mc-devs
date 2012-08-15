package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WorldGenVillagePieces
{
	public static ArrayList a(Random paramRandom, int paramInt)
	{
		ArrayList localArrayList = new ArrayList();

		localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageHouse.class, 4, MathHelper.a(paramRandom, 2 + paramInt, 4 + paramInt * 2)));
		localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageTemple.class, 20, MathHelper.a(paramRandom, 0 + paramInt, 1 + paramInt)));
		localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageLibrary.class, 20, MathHelper.a(paramRandom, 0 + paramInt, 2 + paramInt)));
		localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageHut.class, 3, MathHelper.a(paramRandom, 2 + paramInt, 5 + paramInt * 3)));
		localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageButcher.class, 15, MathHelper.a(paramRandom, 0 + paramInt, 2 + paramInt)));
		localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageFarm2.class, 3, MathHelper.a(paramRandom, 1 + paramInt, 4 + paramInt)));
		localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageFarm.class, 3, MathHelper.a(paramRandom, 2 + paramInt, 4 + paramInt * 2)));
		localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageBlacksmith.class, 15, MathHelper.a(paramRandom, 0, 1 + paramInt)));
		localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageHouse2.class, 8, MathHelper.a(paramRandom, 0 + paramInt, 3 + paramInt * 2)));

		Iterator localIterator = localArrayList.iterator();
		while (localIterator.hasNext()) {
			if (((WorldGenVillagePieceWeight)localIterator.next()).d == 0) {
				localIterator.remove();
			}
		}

		return localArrayList;
	}

	private static int a(List paramList) {
		int i = 0;
		int j = 0;
		for (WorldGenVillagePieceWeight localWorldGenVillagePieceWeight : paramList) {
			if ((localWorldGenVillagePieceWeight.d > 0) && (localWorldGenVillagePieceWeight.c < localWorldGenVillagePieceWeight.d)) {
				i = 1;
			}
			j += localWorldGenVillagePieceWeight.b;
		}
		return i != 0 ? j : -1;
	}

	private static WorldGenVillagePiece a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, WorldGenVillagePieceWeight paramWorldGenVillagePieceWeight, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		Class localClass = paramWorldGenVillagePieceWeight.a;
		Object localObject = null;

		if (localClass == WorldGenVillageHouse.class)
			localObject = WorldGenVillageHouse.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
		else if (localClass == WorldGenVillageTemple.class)
			localObject = WorldGenVillageTemple.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
		else if (localClass == WorldGenVillageLibrary.class)
			localObject = WorldGenVillageLibrary.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
		else if (localClass == WorldGenVillageHut.class)
			localObject = WorldGenVillageHut.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
		else if (localClass == WorldGenVillageButcher.class)
			localObject = WorldGenVillageButcher.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
		else if (localClass == WorldGenVillageFarm2.class)
			localObject = WorldGenVillageFarm2.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
		else if (localClass == WorldGenVillageFarm.class)
			localObject = WorldGenVillageFarm.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
		else if (localClass == WorldGenVillageBlacksmith.class)
			localObject = WorldGenVillageBlacksmith.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
		else if (localClass == WorldGenVillageHouse2.class) {
			localObject = WorldGenVillageHouse2.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
		}

		return (WorldGenVillagePiece)localObject;
	}

	private static WorldGenVillagePiece c(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		int i = a(paramWorldGenVillageStartPiece.h);
		if (i <= 0) {
			return null;
		}

		int j = 0;
		int k;
		while (j < 5) {
			j++;

			k = paramRandom.nextInt(i);
			for (WorldGenVillagePieceWeight localWorldGenVillagePieceWeight : paramWorldGenVillageStartPiece.h) {
				k -= localWorldGenVillagePieceWeight.b;
				if (k < 0)
				{
					if ((!localWorldGenVillagePieceWeight.a(paramInt5)) || ((localWorldGenVillagePieceWeight == paramWorldGenVillageStartPiece.d) && (paramWorldGenVillageStartPiece.h.size() > 1)))
					{
						break;
					}
					WorldGenVillagePiece localWorldGenVillagePiece = a(paramWorldGenVillageStartPiece, localWorldGenVillagePieceWeight, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
					if (localWorldGenVillagePiece != null) {
						localWorldGenVillagePieceWeight.c += 1;
						paramWorldGenVillageStartPiece.d = localWorldGenVillagePieceWeight;

						if (!localWorldGenVillagePieceWeight.a()) {
							paramWorldGenVillageStartPiece.h.remove(localWorldGenVillagePieceWeight);
						}
						return localWorldGenVillagePiece;
					}
				}

			}

		}

		StructureBoundingBox localStructureBoundingBox = WorldGenVillageLight.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4);
		if (localStructureBoundingBox != null) {
			return new WorldGenVillageLight(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
		}

		return null;
	}

	private static StructurePiece d(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
		if (paramInt5 > 50) {
			return null;
		}
		if ((Math.abs(paramInt1 - paramWorldGenVillageStartPiece.b().a) > 112) || (Math.abs(paramInt3 - paramWorldGenVillageStartPiece.b().c) > 112)) {
			return null;
		}

		WorldGenVillagePiece localWorldGenVillagePiece = c(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5 + 1);
		if (localWorldGenVillagePiece != null) {
			int i = (localWorldGenVillagePiece.e.a + localWorldGenVillagePiece.e.d) / 2;
			int j = (localWorldGenVillagePiece.e.c + localWorldGenVillagePiece.e.f) / 2;
			int k = localWorldGenVillagePiece.e.d - localWorldGenVillagePiece.e.a;
			int m = localWorldGenVillagePiece.e.f - localWorldGenVillagePiece.e.c;
			int n = k > m ? k : m;
			if (paramWorldGenVillageStartPiece.d().a(i, j, n / 2 + 4, WorldGenVillage.e)) {
				paramList.add(localWorldGenVillagePiece);
				paramWorldGenVillageStartPiece.i.add(localWorldGenVillagePiece);
				return localWorldGenVillagePiece;
			}
		}
		return null;
	}

	private static StructurePiece e(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
		if (paramInt5 > 3 + paramWorldGenVillageStartPiece.c) {
			return null;
		}
		if ((Math.abs(paramInt1 - paramWorldGenVillageStartPiece.b().a) > 112) || (Math.abs(paramInt3 - paramWorldGenVillageStartPiece.b().c) > 112)) {
			return null;
		}

		StructureBoundingBox localStructureBoundingBox = WorldGenVillageRoad.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4);
		if ((localStructureBoundingBox != null) && (localStructureBoundingBox.b > 10)) {
			WorldGenVillageRoad localWorldGenVillageRoad = new WorldGenVillageRoad(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
			int i = (localWorldGenVillageRoad.e.a + localWorldGenVillageRoad.e.d) / 2;
			int j = (localWorldGenVillageRoad.e.c + localWorldGenVillageRoad.e.f) / 2;
			int k = localWorldGenVillageRoad.e.d - localWorldGenVillageRoad.e.a;
			int m = localWorldGenVillageRoad.e.f - localWorldGenVillageRoad.e.c;
			int n = k > m ? k : m;
			if (paramWorldGenVillageStartPiece.d().a(i, j, n / 2 + 4, WorldGenVillage.e)) {
				paramList.add(localWorldGenVillageRoad);
				paramWorldGenVillageStartPiece.j.add(localWorldGenVillageRoad);
				return localWorldGenVillageRoad;
			}
		}

		return null;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenVillagePieces
 * JD-Core Version:		0.6.0
 */