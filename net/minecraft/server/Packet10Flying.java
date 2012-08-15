/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ 
/*		 */ public class Packet10Flying extends Packet
/*		 */ {
/*		 */	 public double x;
/*		 */	 public double y;
/*		 */	 public double z;
/*		 */	 public double stance;
/*		 */	 public float yaw;
/*		 */	 public float pitch;
/*		 */	 public boolean g;
/*		 */	 public boolean hasPos;
/*		 */	 public boolean hasLook;
/*		 */ 
/*		 */	 public void handle(NetHandler paramNetHandler)
/*		 */	 {
/* 136 */		 paramNetHandler.a(this);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataInputStream paramDataInputStream)
/*		 */	 {
/* 141 */		 this.g = (paramDataInputStream.read() != 0);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataOutputStream paramDataOutputStream)
/*		 */	 {
/* 146 */		 paramDataOutputStream.write(this.g ? 1 : 0);
/*		 */	 }
/*		 */ 
/*		 */	 public int a()
/*		 */	 {
/* 151 */		 return 1;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean e()
/*		 */	 {
/* 156 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(Packet paramPacket)
/*		 */	 {
/* 161 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet10Flying
 * JD-Core Version:		0.6.0
 */