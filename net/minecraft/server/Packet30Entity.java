/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ 
/*		 */ public class Packet30Entity extends Packet
/*		 */ {
/*		 */	 public int a;
/*		 */	 public byte b;
/*		 */	 public byte c;
/*		 */	 public byte d;
/*		 */	 public byte e;
/*		 */	 public byte f;
/* 129 */	 public boolean g = false;
/*		 */ 
/*		 */	 public Packet30Entity() {
/*		 */	 }
/*		 */ 
/*		 */	 public Packet30Entity(int paramInt) {
/* 135 */		 this.a = paramInt;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataInputStream paramDataInputStream)
/*		 */	 {
/* 140 */		 this.a = paramDataInputStream.readInt();
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataOutputStream paramDataOutputStream)
/*		 */	 {
/* 145 */		 paramDataOutputStream.writeInt(this.a);
/*		 */	 }
/*		 */ 
/*		 */	 public void handle(NetHandler paramNetHandler)
/*		 */	 {
/* 150 */		 paramNetHandler.a(this);
/*		 */	 }
/*		 */ 
/*		 */	 public int a()
/*		 */	 {
/* 155 */		 return 4;
/*		 */	 }
/*		 */ 
/*		 */	 public String toString()
/*		 */	 {
/* 165 */		 return "Entity_" + super.toString();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean e()
/*		 */	 {
/* 170 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(Packet paramPacket)
/*		 */	 {
/* 175 */		 Packet30Entity localPacket30Entity = (Packet30Entity)paramPacket;
/* 176 */		 return localPacket30Entity.a == this.a;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet30Entity
 * JD-Core Version:		0.6.0
 */