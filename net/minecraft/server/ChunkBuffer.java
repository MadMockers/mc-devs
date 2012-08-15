/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.ByteArrayOutputStream;
/*		 */ 
/*		 */ class ChunkBuffer extends ByteArrayOutputStream
/*		 */ {
/*		 */	 private int b;
/*		 */	 private int c;
/*		 */ 
/*		 */	 public ChunkBuffer(RegionFile paramRegionFile, int paramInt1, int paramInt2)
/*		 */	 {
/* 223 */		 super(8096);
/* 224 */		 this.b = paramInt1;
/* 225 */		 this.c = paramInt2;
/*		 */	 }
/*		 */ 
/*		 */	 public void close()
/*		 */	 {
/* 230 */		 this.a.a(this.b, this.c, this.buf, this.count);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ChunkBuffer
 * JD-Core Version:		0.6.0
 */