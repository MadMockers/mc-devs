/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ 
/*		 */ public class VillageCollection
/*		 */ {
/*		 */	 private World world;
/*	12 */	 private final List b = new ArrayList();
/*	13 */	 private final List c = new ArrayList();
/*	14 */	 private final List villages = new ArrayList();
/*	15 */	 private int time = 0;
/*		 */ 
/*		 */	 public VillageCollection(World paramWorld) {
/*	18 */		 this.world = paramWorld;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int paramInt1, int paramInt2, int paramInt3) {
/*	22 */		 if (this.b.size() > 64) return;
/*	23 */		 if (!d(paramInt1, paramInt2, paramInt3)) this.b.add(new ChunkCoordinates(paramInt1, paramInt2, paramInt3)); 
/*		 */	 }
/*		 */ 
/*		 */	 public void tick()
/*		 */	 {
/*	27 */		 this.time += 1;
/*	28 */		 for (Village localVillage : this.villages)
/*	29 */			 localVillage.tick(this.time);
/*	30 */		 c();
/*	31 */		 d();
/*	32 */		 e();
/*		 */	 }
/*		 */ 
/*		 */	 private void c() {
/*	36 */		 for (Iterator localIterator = this.villages.iterator(); localIterator.hasNext(); ) {
/*	37 */			 Village localVillage = (Village)localIterator.next();
/*	38 */			 if (localVillage.isAbandoned()) localIterator.remove(); 
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public List getVillages()
/*		 */	 {
/*	43 */		 return this.villages;
/*		 */	 }
/*		 */ 
/*		 */	 public Village getClosestVillage(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*	47 */		 Object localObject = null;
/*	48 */		 float f1 = 3.4028235E+38F;
/*	49 */		 for (Village localVillage : this.villages)
/*		 */		 {
/*	51 */			 float f2 = localVillage.getCenter().e(paramInt1, paramInt2, paramInt3);
/*	52 */			 if (f2 >= f1)
/*		 */				 continue;
/*	54 */			 int i = paramInt4 + localVillage.getSize();
/*	55 */			 if (f2 > i * i)
/*		 */				 continue;
/*	57 */			 localObject = localVillage;
/*	58 */			 f1 = f2;
/*		 */		 }
/*	60 */		 return localObject;
/*		 */	 }
/*		 */ 
/*		 */	 private void d() {
/*	64 */		 if (this.b.isEmpty()) return;
/*	65 */		 a((ChunkCoordinates)this.b.remove(0));
/*		 */	 }
/*		 */ 
/*		 */	 private void e()
/*		 */	 {
/*	70 */		 for (VillageDoor localVillageDoor : this.c) {
/*	71 */			 int i = 0;
/*	72 */			 for (Object localObject = this.villages.iterator(); ((Iterator)localObject).hasNext(); ) { Village localVillage = (Village)((Iterator)localObject).next();
/*	73 */				 int j = (int)localVillage.getCenter().e(localVillageDoor.locX, localVillageDoor.locY, localVillageDoor.locZ);
/*	74 */				 int k = 32 + localVillage.getSize();
/*	75 */				 if (j <= k * k) {
/*	76 */					 localVillage.addDoor(localVillageDoor);
/*	77 */					 i = 1;
/*		 */				 }
/*		 */			 }
/*	80 */			 if (i != 0) {
/*		 */				 continue;
/*		 */			 }
/*	83 */			 localObject = new Village(this.world);
/*	84 */			 ((Village)localObject).addDoor(localVillageDoor);
/*	85 */			 this.villages.add(localObject);
/*		 */		 }
/*	87 */		 this.c.clear();
/*		 */	 }
/*		 */ 
/*		 */	 private void a(ChunkCoordinates paramChunkCoordinates) {
/*	91 */		 int i = 16; int j = 4; int k = 16;
/*	92 */		 for (int m = paramChunkCoordinates.x - i; m < paramChunkCoordinates.x + i; m++)
/*	93 */			 for (int n = paramChunkCoordinates.y - j; n < paramChunkCoordinates.y + j; n++)
/*	94 */				 for (int i1 = paramChunkCoordinates.z - k; i1 < paramChunkCoordinates.z + k; i1++) {
/*	95 */					 if (!e(m, n, i1))
/*		 */						 continue;
/*	97 */					 VillageDoor localVillageDoor = b(m, n, i1);
/*	98 */					 if (localVillageDoor == null) c(m, n, i1); else
/*	99 */						 localVillageDoor.addedTime = this.time;
/*		 */				 }
/*		 */	 }
/*		 */ 
/*		 */	 private VillageDoor b(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 107 */		 for (Iterator localIterator = this.c.iterator(); localIterator.hasNext(); ) { localObject = (VillageDoor)localIterator.next();
/* 108 */			 if ((((VillageDoor)localObject).locX == paramInt1) && (((VillageDoor)localObject).locZ == paramInt3) && (Math.abs(((VillageDoor)localObject).locY - paramInt2) <= 1)) return localObject;
/*		 */		 }
/* 109 */		 Object localObject;
/* 109 */		 for (localIterator = this.villages.iterator(); localIterator.hasNext(); ) { localObject = (Village)localIterator.next();
/* 110 */			 VillageDoor localVillageDoor = ((Village)localObject).e(paramInt1, paramInt2, paramInt3);
/* 111 */			 if (localVillageDoor != null) return localVillageDoor;
/*		 */		 }
/* 113 */		 return (VillageDoor)null;
/*		 */	 }
/*		 */ 
/*		 */	 private void c(int paramInt1, int paramInt2, int paramInt3) {
/* 117 */		 int i = ((BlockDoor)Block.WOODEN_DOOR).d(this.world, paramInt1, paramInt2, paramInt3);
/*		 */		 int j;
/*		 */		 int k;
/* 118 */		 if ((i == 0) || (i == 2)) {
/* 119 */			 j = 0;
/* 120 */			 for (k = -5; k < 0; k++) {
/* 121 */				 if (!this.world.j(paramInt1 + k, paramInt2, paramInt3)) continue; j--;
/* 122 */			 }for (k = 1; k <= 5; k++) {
/* 123 */				 if (!this.world.j(paramInt1 + k, paramInt2, paramInt3)) continue; j++;
/* 124 */			 }if (j != 0) this.c.add(new VillageDoor(paramInt1, paramInt2, paramInt3, j > 0 ? -2 : 2, 0, this.time)); 
/*		 */		 }
/*		 */		 else {
/* 126 */			 j = 0;
/* 127 */			 for (k = -5; k < 0; k++) {
/* 128 */				 if (!this.world.j(paramInt1, paramInt2, paramInt3 + k)) continue; j--;
/* 129 */			 }for (k = 1; k <= 5; k++) {
/* 130 */				 if (!this.world.j(paramInt1, paramInt2, paramInt3 + k)) continue; j++;
/* 131 */			 }if (j != 0) this.c.add(new VillageDoor(paramInt1, paramInt2, paramInt3, 0, j > 0 ? -2 : 2, this.time)); 
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean d(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 136 */		 for (ChunkCoordinates localChunkCoordinates : this.b)
/* 137 */			 if ((localChunkCoordinates.x == paramInt1) && (localChunkCoordinates.y == paramInt2) && (localChunkCoordinates.z == paramInt3)) return true;
/* 138 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean e(int paramInt1, int paramInt2, int paramInt3) {
/* 142 */		 int i = this.world.getTypeId(paramInt1, paramInt2, paramInt3);
/* 143 */		 return i == Block.WOODEN_DOOR.id;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.VillageCollection
 * JD-Core Version:		0.6.0
 */