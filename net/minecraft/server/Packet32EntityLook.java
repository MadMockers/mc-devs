/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ 
/*		 */ public class Packet32EntityLook extends Packet30Entity
/*		 */ {
/*		 */	 public Packet32EntityLook()
/*		 */	 {
/*	92 */		 this.g = true;
/*		 */	 }
/*		 */ 
/*		 */	 public Packet32EntityLook(int paramInt, byte paramByte1, byte paramByte2) {
/*	96 */		 super(paramInt);
/*	97 */		 this.e = paramByte1;
/*	98 */		 this.f = paramByte2;
/*	99 */		 this.g = true;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataInputStream paramDataInputStream)
/*		 */	 {
/* 104 */		 super.a(paramDataInputStream);
/* 105 */		 this.e = paramDataInputStream.readByte();
/* 106 */		 this.f = paramDataInputStream.readByte();
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataOutputStream paramDataOutputStream)
/*		 */	 {
/* 111 */		 super.a(paramDataOutputStream);
/* 112 */		 paramDataOutputStream.writeByte(this.e);
/* 113 */		 paramDataOutputStream.writeByte(this.f);
/*		 */	 }
/*		 */ 
/*		 */	 public int a()
/*		 */	 {
/* 118 */		 return 6;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet32EntityLook
 * JD-Core Version:		0.6.0
 */