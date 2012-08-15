package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VillageCollection
{
	private World world;
	private final List b = new ArrayList();
	private final List c = new ArrayList();
	private final List villages = new ArrayList();
	private int time = 0;

	public VillageCollection(World paramWorld) {
		this.world = paramWorld;
	}

	public void a(int paramInt1, int paramInt2, int paramInt3) {
		if (this.b.size() > 64) return;
		if (!d(paramInt1, paramInt2, paramInt3)) this.b.add(new ChunkCoordinates(paramInt1, paramInt2, paramInt3)); 
	}

	public void tick()
	{
		this.time += 1;
		for (Village localVillage : this.villages)
			localVillage.tick(this.time);
		c();
		d();
		e();
	}

	private void c() {
		for (Iterator localIterator = this.villages.iterator(); localIterator.hasNext(); ) {
			Village localVillage = (Village)localIterator.next();
			if (localVillage.isAbandoned()) localIterator.remove(); 
		}
	}

	public List getVillages()
	{
		return this.villages;
	}

	public Village getClosestVillage(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		Object localObject = null;
		float f1 = 3.4028235E+38F;
		for (Village localVillage : this.villages)
		{
			float f2 = localVillage.getCenter().e(paramInt1, paramInt2, paramInt3);
			if (f2 >= f1)
				continue;
			int i = paramInt4 + localVillage.getSize();
			if (f2 > i * i)
				continue;
			localObject = localVillage;
			f1 = f2;
		}
		return localObject;
	}

	private void d() {
		if (this.b.isEmpty()) return;
		a((ChunkCoordinates)this.b.remove(0));
	}

	private void e()
	{
		for (VillageDoor localVillageDoor : this.c) {
			int i = 0;
			for (Object localObject = this.villages.iterator(); ((Iterator)localObject).hasNext(); ) { Village localVillage = (Village)((Iterator)localObject).next();
				int j = (int)localVillage.getCenter().e(localVillageDoor.locX, localVillageDoor.locY, localVillageDoor.locZ);
				int k = 32 + localVillage.getSize();
				if (j <= k * k) {
					localVillage.addDoor(localVillageDoor);
					i = 1;
				}
			}
			if (i != 0) {
				continue;
			}
			localObject = new Village(this.world);
			((Village)localObject).addDoor(localVillageDoor);
			this.villages.add(localObject);
		}
		this.c.clear();
	}

	private void a(ChunkCoordinates paramChunkCoordinates) {
		int i = 16; int j = 4; int k = 16;
		for (int m = paramChunkCoordinates.x - i; m < paramChunkCoordinates.x + i; m++)
			for (int n = paramChunkCoordinates.y - j; n < paramChunkCoordinates.y + j; n++)
				for (int i1 = paramChunkCoordinates.z - k; i1 < paramChunkCoordinates.z + k; i1++) {
					if (!e(m, n, i1))
						continue;
					VillageDoor localVillageDoor = b(m, n, i1);
					if (localVillageDoor == null) c(m, n, i1); else
						localVillageDoor.addedTime = this.time;
				}
	}

	private VillageDoor b(int paramInt1, int paramInt2, int paramInt3)
	{
		for (Iterator localIterator = this.c.iterator(); localIterator.hasNext(); ) { localObject = (VillageDoor)localIterator.next();
			if ((((VillageDoor)localObject).locX == paramInt1) && (((VillageDoor)localObject).locZ == paramInt3) && (Math.abs(((VillageDoor)localObject).locY - paramInt2) <= 1)) return localObject;
		}
		Object localObject;
		for (localIterator = this.villages.iterator(); localIterator.hasNext(); ) { localObject = (Village)localIterator.next();
			VillageDoor localVillageDoor = ((Village)localObject).e(paramInt1, paramInt2, paramInt3);
			if (localVillageDoor != null) return localVillageDoor;
		}
		return (VillageDoor)null;
	}

	private void c(int paramInt1, int paramInt2, int paramInt3) {
		int i = ((BlockDoor)Block.WOODEN_DOOR).d(this.world, paramInt1, paramInt2, paramInt3);
		int j;
		int k;
		if ((i == 0) || (i == 2)) {
			j = 0;
			for (k = -5; k < 0; k++) {
				if (!this.world.j(paramInt1 + k, paramInt2, paramInt3)) continue; j--;
			}for (k = 1; k <= 5; k++) {
				if (!this.world.j(paramInt1 + k, paramInt2, paramInt3)) continue; j++;
			}if (j != 0) this.c.add(new VillageDoor(paramInt1, paramInt2, paramInt3, j > 0 ? -2 : 2, 0, this.time)); 
		}
		else {
			j = 0;
			for (k = -5; k < 0; k++) {
				if (!this.world.j(paramInt1, paramInt2, paramInt3 + k)) continue; j--;
			}for (k = 1; k <= 5; k++) {
				if (!this.world.j(paramInt1, paramInt2, paramInt3 + k)) continue; j++;
			}if (j != 0) this.c.add(new VillageDoor(paramInt1, paramInt2, paramInt3, 0, j > 0 ? -2 : 2, this.time)); 
		}
	}

	private boolean d(int paramInt1, int paramInt2, int paramInt3)
	{
		for (ChunkCoordinates localChunkCoordinates : this.b)
			if ((localChunkCoordinates.x == paramInt1) && (localChunkCoordinates.y == paramInt2) && (localChunkCoordinates.z == paramInt3)) return true;
		return false;
	}

	private boolean e(int paramInt1, int paramInt2, int paramInt3) {
		int i = this.world.getTypeId(paramInt1, paramInt2, paramInt3);
		return i == Block.WOODEN_DOOR.id;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.VillageCollection
 * JD-Core Version:		0.6.0
 */