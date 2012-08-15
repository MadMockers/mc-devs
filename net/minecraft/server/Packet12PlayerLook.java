/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ 
/*		 */ public class Packet12PlayerLook extends Packet10Flying
/*		 */ {
/*		 */	 public Packet12PlayerLook()
/*		 */	 {
/*	92 */		 this.hasLook = true;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataInputStream paramDataInputStream)
/*		 */	 {
/* 104 */		 this.yaw = paramDataInputStream.readFloat();
/* 105 */		 this.pitch = paramDataInputStream.readFloat();
/* 106 */		 super.a(paramDataInputStream);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataOutputStream paramDataOutputStream)
/*		 */	 {
/* 111 */		 paramDataOutputStream.writeFloat(this.yaw);
/* 112 */		 paramDataOutputStream.writeFloat(this.pitch);
/* 113 */		 super.a(paramDataOutputStream);
/*		 */	 }
/*		 */ 
/*		 */	 public int a()
/*		 */	 {
/* 118 */		 return 9;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet12PlayerLook
 * JD-Core Version:		0.6.0
 */