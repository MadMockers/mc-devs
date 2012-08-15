/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Collection;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.LinkedList;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public abstract class StructureGenerator extends WorldGenBase
/*		 */ {
/*	10 */	 protected Map d = new HashMap();
/*		 */ 
/*		 */	 protected void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte)
/*		 */	 {
/*	18 */		 if (this.d.containsKey(Long.valueOf(ChunkCoordIntPair.a(paramInt1, paramInt2)))) {
/*	19 */			 return;
/*		 */		 }
/*		 */ 
/*	23 */		 this.b.nextInt();
/*	24 */		 if (a(paramInt1, paramInt2)) {
/*	25 */			 StructureStart localStructureStart = b(paramInt1, paramInt2);
/*	26 */			 this.d.put(Long.valueOf(ChunkCoordIntPair.a(paramInt1, paramInt2)), localStructureStart);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2)
/*		 */	 {
/*	32 */		 int i = (paramInt1 << 4) + 8;
/*	33 */		 int j = (paramInt2 << 4) + 8;
/*		 */ 
/*	35 */		 int k = 0;
/*	36 */		 for (StructureStart localStructureStart : this.d.values()) {
/*	37 */			 if ((localStructureStart.d()) && 
/*	38 */				 (localStructureStart.a().a(i, j, i + 15, j + 15))) {
/*	39 */				 localStructureStart.a(paramWorld, paramRandom, new StructureBoundingBox(i, j, i + 15, j + 15));
/*	40 */				 k = 1;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/*	45 */		 return k;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	67 */		 for (StructureStart localStructureStart : this.d.values()) {
/*	68 */			 if ((localStructureStart.d()) && 
/*	69 */				 (localStructureStart.a().a(paramInt1, paramInt3, paramInt1, paramInt3)))
/*		 */			 {
/*	71 */				 Iterator localIterator2 = localStructureStart.b().iterator();
/*	72 */				 while (localIterator2.hasNext()) {
/*	73 */					 StructurePiece localStructurePiece = (StructurePiece)localIterator2.next();
/*	74 */					 if (localStructurePiece.b().b(paramInt1, paramInt2, paramInt3)) {
/*	75 */						 return true;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	81 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkPosition getNearestGeneratedFeature(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	88 */		 this.c = paramWorld;
/*		 */ 
/*	90 */		 this.b.setSeed(paramWorld.getSeed());
/*	91 */		 long l1 = this.b.nextLong();
/*	92 */		 long l2 = this.b.nextLong();
/*	93 */		 long l3 = (paramInt1 >> 4) * l1;
/*	94 */		 long l4 = (paramInt3 >> 4) * l2;
/*	95 */		 this.b.setSeed(l3 ^ l4 ^ paramWorld.getSeed());
/*		 */ 
/*	97 */		 a(paramWorld, paramInt1 >> 4, paramInt3 >> 4, 0, 0, null);
/*		 */ 
/*	99 */		 double d1 = 1.7976931348623157E+308D;
/* 100 */		 Object localObject1 = null;
/*		 */ 
/* 102 */		 for (Object localObject2 = this.d.values().iterator(); ((Iterator)localObject2).hasNext(); ) { localObject3 = (StructureStart)((Iterator)localObject2).next();
/* 103 */			 if (((StructureStart)localObject3).d())
/*		 */			 {
/* 105 */				 localObject4 = (StructurePiece)((StructureStart)localObject3).b().get(0);
/* 106 */				 localChunkPosition = ((StructurePiece)localObject4).a();
/*		 */ 
/* 108 */				 i = localChunkPosition.x - paramInt1;
/* 109 */				 j = localChunkPosition.y - paramInt2;
/* 110 */				 k = localChunkPosition.z - paramInt3;
/* 111 */				 d2 = i + i * j * j + k * k;
/*		 */ 
/* 113 */				 if (d2 < d1) {
/* 114 */					 d1 = d2;
/* 115 */					 localObject1 = localChunkPosition;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */		 Object localObject3;
/*		 */		 Object localObject4;
/*		 */		 ChunkPosition localChunkPosition;
/*		 */		 int i;
/*		 */		 int j;
/*		 */		 int k;
/*		 */		 double d2;
/* 119 */		 if (localObject1 != null) {
/* 120 */			 return localObject1;
/*		 */		 }
/* 122 */		 localObject2 = o_();
/* 123 */		 if (localObject2 != null) {
/* 124 */			 localObject3 = null;
/* 125 */			 for (localObject4 = ((List)localObject2).iterator(); ((Iterator)localObject4).hasNext(); ) { localChunkPosition = (ChunkPosition)((Iterator)localObject4).next();
/*		 */ 
/* 127 */				 i = localChunkPosition.x - paramInt1;
/* 128 */				 j = localChunkPosition.y - paramInt2;
/* 129 */				 k = localChunkPosition.z - paramInt3;
/* 130 */				 d2 = i + i * j * j + k * k;
/*		 */ 
/* 132 */				 if (d2 < d1) {
/* 133 */					 d1 = d2;
/* 134 */					 localObject3 = localChunkPosition;
/*		 */				 }
/*		 */			 }
/* 137 */			 return localObject3;
/*		 */		 }
/*		 */ 
/* 140 */		 return (ChunkPosition)(ChunkPosition)(ChunkPosition)null;
/*		 */	 }
/*		 */ 
/*		 */	 protected List o_() {
/* 144 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected abstract boolean a(int paramInt1, int paramInt2);
/*		 */ 
/*		 */	 protected abstract StructureStart b(int paramInt1, int paramInt2);
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.StructureGenerator
 * JD-Core Version:		0.6.0
 */