/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ 
/*		 */ public class Packet202Abilities extends Packet
/*		 */ {
/*	16 */	 private boolean a = false;
/*	17 */	 private boolean b = false;
/*	18 */	 private boolean c = false;
/*	19 */	 private boolean d = false;
/*		 */	 private float e;
/*		 */	 private float f;
/*		 */ 
/*		 */	 public Packet202Abilities()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public Packet202Abilities(PlayerAbilities paramPlayerAbilities)
/*		 */	 {
/*	27 */		 a(paramPlayerAbilities.isInvulnerable);
/*	28 */		 b(paramPlayerAbilities.isFlying);
/*	29 */		 c(paramPlayerAbilities.canFly);
/*	30 */		 d(paramPlayerAbilities.canInstantlyBuild);
/*	31 */		 a(paramPlayerAbilities.a());
/*	32 */		 b(paramPlayerAbilities.b());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataInputStream paramDataInputStream)
/*		 */	 {
/*	37 */		 int i = paramDataInputStream.readByte();
/*		 */ 
/*	39 */		 a((i & 0x1) > 0);
/*	40 */		 b((i & 0x2) > 0);
/*	41 */		 c((i & 0x4) > 0);
/*	42 */		 d((i & 0x8) > 0);
/*	43 */		 a(paramDataInputStream.readByte() / 255.0F);
/*	44 */		 b(paramDataInputStream.readByte() / 255.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataOutputStream paramDataOutputStream)
/*		 */	 {
/*	49 */		 int i = 0;
/*		 */ 
/*	51 */		 if (d()) i = (byte)(i | 0x1);
/*	52 */		 if (f()) i = (byte)(i | 0x2);
/*	53 */		 if (g()) i = (byte)(i | 0x4);
/*	54 */		 if (h()) i = (byte)(i | 0x8);
/*		 */ 
/*	56 */		 paramDataOutputStream.writeByte(i);
/*	57 */		 paramDataOutputStream.writeByte((int)(this.e * 255.0F));
/*	58 */		 paramDataOutputStream.writeByte((int)(this.f * 255.0F));
/*		 */	 }
/*		 */ 
/*		 */	 public void handle(NetHandler paramNetHandler)
/*		 */	 {
/*	63 */		 paramNetHandler.a(this);
/*		 */	 }
/*		 */ 
/*		 */	 public int a()
/*		 */	 {
/*	68 */		 return 2;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	77 */		 return this.a;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(boolean paramBoolean) {
/*	81 */		 this.a = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean f() {
/*	85 */		 return this.b;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(boolean paramBoolean) {
/*	89 */		 this.b = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean g() {
/*	93 */		 return this.c;
/*		 */	 }
/*		 */ 
/*		 */	 public void c(boolean paramBoolean) {
/*	97 */		 this.c = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean h() {
/* 101 */		 return this.d;
/*		 */	 }
/*		 */ 
/*		 */	 public void d(boolean paramBoolean) {
/* 105 */		 this.d = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(float paramFloat)
/*		 */	 {
/* 113 */		 this.e = paramFloat;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(float paramFloat)
/*		 */	 {
/* 121 */		 this.f = paramFloat;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean e()
/*		 */	 {
/* 126 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(Packet paramPacket)
/*		 */	 {
/* 131 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet202Abilities
 * JD-Core Version:		0.6.0
 */