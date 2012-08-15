/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ 
/*		 */ public class Packet23VehicleSpawn extends Packet
/*		 */ {
/*		 */	 public int a;
/*		 */	 public int b;
/*		 */	 public int c;
/*		 */	 public int d;
/*		 */	 public int e;
/*		 */	 public int f;
/*		 */	 public int g;
/*		 */	 public int h;
/*		 */	 public int i;
/*		 */ 
/*		 */	 public Packet23VehicleSpawn()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public Packet23VehicleSpawn(Entity paramEntity, int paramInt)
/*		 */	 {
/*	37 */		 this(paramEntity, paramInt, 0);
/*		 */	 }
/*		 */ 
/*		 */	 public Packet23VehicleSpawn(Entity paramEntity, int paramInt1, int paramInt2) {
/*	41 */		 this.a = paramEntity.id;
/*	42 */		 this.b = MathHelper.floor(paramEntity.locX * 32.0D);
/*	43 */		 this.c = MathHelper.floor(paramEntity.locY * 32.0D);
/*	44 */		 this.d = MathHelper.floor(paramEntity.locZ * 32.0D);
/*	45 */		 this.h = paramInt1;
/*	46 */		 this.i = paramInt2;
/*	47 */		 if (paramInt2 > 0) {
/*	48 */			 double d1 = paramEntity.motX;
/*	49 */			 double d2 = paramEntity.motY;
/*	50 */			 double d3 = paramEntity.motZ;
/*	51 */			 double d4 = 3.9D;
/*	52 */			 if (d1 < -d4) d1 = -d4;
/*	53 */			 if (d2 < -d4) d2 = -d4;
/*	54 */			 if (d3 < -d4) d3 = -d4;
/*	55 */			 if (d1 > d4) d1 = d4;
/*	56 */			 if (d2 > d4) d2 = d4;
/*	57 */			 if (d3 > d4) d3 = d4;
/*	58 */			 this.e = (int)(d1 * 8000.0D);
/*	59 */			 this.f = (int)(d2 * 8000.0D);
/*	60 */			 this.g = (int)(d3 * 8000.0D);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataInputStream paramDataInputStream)
/*		 */	 {
/*	66 */		 this.a = paramDataInputStream.readInt();
/*	67 */		 this.h = paramDataInputStream.readByte();
/*	68 */		 this.b = paramDataInputStream.readInt();
/*	69 */		 this.c = paramDataInputStream.readInt();
/*	70 */		 this.d = paramDataInputStream.readInt();
/*	71 */		 this.i = paramDataInputStream.readInt();
/*	72 */		 if (this.i > 0) {
/*	73 */			 this.e = paramDataInputStream.readShort();
/*	74 */			 this.f = paramDataInputStream.readShort();
/*	75 */			 this.g = paramDataInputStream.readShort();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataOutputStream paramDataOutputStream)
/*		 */	 {
/*	81 */		 paramDataOutputStream.writeInt(this.a);
/*	82 */		 paramDataOutputStream.writeByte(this.h);
/*	83 */		 paramDataOutputStream.writeInt(this.b);
/*	84 */		 paramDataOutputStream.writeInt(this.c);
/*	85 */		 paramDataOutputStream.writeInt(this.d);
/*	86 */		 paramDataOutputStream.writeInt(this.i);
/*	87 */		 if (this.i > 0) {
/*	88 */			 paramDataOutputStream.writeShort(this.e);
/*	89 */			 paramDataOutputStream.writeShort(this.f);
/*	90 */			 paramDataOutputStream.writeShort(this.g);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void handle(NetHandler paramNetHandler)
/*		 */	 {
/*	96 */		 paramNetHandler.a(this);
/*		 */	 }
/*		 */ 
/*		 */	 public int a()
/*		 */	 {
/* 101 */		 return 21 + this.i > 0 ? 6 : 0;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet23VehicleSpawn
 * JD-Core Version:		0.6.0
 */