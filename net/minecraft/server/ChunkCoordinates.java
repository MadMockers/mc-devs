/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class ChunkCoordinates
/*		 */	 implements Comparable
/*		 */ {
/*		 */	 public int x;
/*		 */	 public int y;
/*		 */	 public int z;
/*		 */ 
/*		 */	 public ChunkCoordinates()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkCoordinates(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	14 */		 this.x = paramInt1;
/*	15 */		 this.y = paramInt2;
/*	16 */		 this.z = paramInt3;
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkCoordinates(ChunkCoordinates paramChunkCoordinates) {
/*	20 */		 this.x = paramChunkCoordinates.x;
/*	21 */		 this.y = paramChunkCoordinates.y;
/*	22 */		 this.z = paramChunkCoordinates.z;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean equals(Object paramObject)
/*		 */	 {
/*	27 */		 if (!(paramObject instanceof ChunkCoordinates)) {
/*	28 */			 return false;
/*		 */		 }
/*		 */ 
/*	31 */		 ChunkCoordinates localChunkCoordinates = (ChunkCoordinates)paramObject;
/*	32 */		 return (this.x == localChunkCoordinates.x) && (this.y == localChunkCoordinates.y) && (this.z == localChunkCoordinates.z);
/*		 */	 }
/*		 */ 
/*		 */	 public int hashCode()
/*		 */	 {
/*	37 */		 return this.x + this.z << 8 + this.y << 16;
/*		 */	 }
/*		 */ 
/*		 */	 public int compareTo(ChunkCoordinates paramChunkCoordinates) {
/*	41 */		 if (this.y == paramChunkCoordinates.y) {
/*	42 */			 if (this.z == paramChunkCoordinates.z) {
/*	43 */				 return this.x - paramChunkCoordinates.x;
/*		 */			 }
/*	45 */			 return this.z - paramChunkCoordinates.z;
/*		 */		 }
/*	47 */		 return this.y - paramChunkCoordinates.y;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	55 */		 this.x = paramInt1;
/*	56 */		 this.y = paramInt2;
/*	57 */		 this.z = paramInt3;
/*		 */	 }
/*		 */ 
/*		 */	 public float e(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 199 */		 int i = this.x - paramInt1;
/* 200 */		 int j = this.y - paramInt2;
/* 201 */		 int k = this.z - paramInt3;
/* 202 */		 return i * i + j * j + k * k;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ChunkCoordinates
 * JD-Core Version:		0.6.0
 */