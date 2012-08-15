/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class EmptyChunk extends Chunk
/*		 */ {
/*		 */	 public EmptyChunk(World paramWorld, int paramInt1, int paramInt2)
/*		 */	 {
/*	12 */		 super(paramWorld, paramInt1, paramInt2);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int paramInt1, int paramInt2)
/*		 */	 {
/*	17 */		 return (paramInt1 == this.x) && (paramInt2 == this.z);
/*		 */	 }
/*		 */ 
/*		 */	 public int b(int paramInt1, int paramInt2)
/*		 */	 {
/*	22 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void initLighting()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public int getTypeId(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	35 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public int b(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	40 */		 return 255;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/*	45 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*	50 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public int getData(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	55 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*	60 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int getBrightness(EnumSkyBlock paramEnumSkyBlock, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	65 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EnumSkyBlock paramEnumSkyBlock, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public int c(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*	74 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Entity paramEntity)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void b(Entity paramEntity)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Entity paramEntity, int paramInt)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	91 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public TileEntity e(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	96 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(TileEntity paramTileEntity)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int paramInt1, int paramInt2, int paramInt3, TileEntity paramTileEntity)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void f(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void addEntities()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void removeEntities()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void e()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Entity paramEntity, AxisAlignedBB paramAxisAlignedBB, List paramList)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Class paramClass, AxisAlignedBB paramAxisAlignedBB, List paramList)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(boolean paramBoolean)
/*		 */	 {
/* 138 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public Random a(long paramLong)
/*		 */	 {
/* 164 */		 return new Random(this.world.getSeed() + this.x * this.x * 4987142 + this.x * 5947611 + this.z * this.z * 4392871L + this.z * 389711 ^ paramLong);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isEmpty()
/*		 */	 {
/* 169 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(int paramInt1, int paramInt2)
/*		 */	 {
/* 174 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EmptyChunk
 * JD-Core Version:		0.6.0
 */