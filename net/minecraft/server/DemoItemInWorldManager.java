/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class DemoItemInWorldManager extends ItemInWorldManager
/*		 */ {
/*	13 */	 private boolean c = false;
/*	14 */	 private boolean d = false;
/*	15 */	 private int e = 0;
/*	16 */	 private int f = 0;
/*		 */ 
/*		 */	 public DemoItemInWorldManager(World paramWorld) {
/*	19 */		 super(paramWorld);
/*		 */	 }
/*		 */ 
/*		 */	 public void a()
/*		 */	 {
/*	24 */		 super.a();
/*	25 */		 this.f += 1;
/*		 */ 
/*	27 */		 long l1 = this.world.getTime();
/*	28 */		 long l2 = l1 / 24000L + 1L;
/*		 */ 
/*	30 */		 if ((!this.c) && (this.f > 20)) {
/*	31 */			 this.c = true;
/*	32 */			 this.player.netServerHandler.sendPacket(new Packet70Bed(5, 0));
/*		 */		 }
/*		 */ 
/*	35 */		 this.d = (l1 > 120500L);
/*	36 */		 if (this.d) {
/*	37 */			 this.e += 1;
/*		 */		 }
/*		 */ 
/*	40 */		 if (l1 % 24000L == 500L) {
/*	41 */			 if (l2 <= 6L)
/*	42 */				 this.player.sendMessage(this.player.a("demo.day." + l2, new Object[0]));
/*		 */		 }
/*	44 */		 else if (l2 == 1L) {
/*	45 */			 if (l1 == 100L)
/*	46 */				 this.player.netServerHandler.sendPacket(new Packet70Bed(5, 101));
/*	47 */			 else if (l1 == 175L)
/*	48 */				 this.player.netServerHandler.sendPacket(new Packet70Bed(5, 102));
/*	49 */			 else if (l1 == 250L)
/*	50 */				 this.player.netServerHandler.sendPacket(new Packet70Bed(5, 103));
/*		 */		 }
/*	52 */		 else if ((l2 == 5L) && 
/*	53 */			 (l1 % 24000L == 22000L))
/*	54 */			 this.player.sendMessage(this.player.a("demo.day.warning", new Object[0]));
/*		 */	 }
/*		 */ 
/*		 */	 private void e()
/*		 */	 {
/*	60 */		 if (this.e > 100) {
/*	61 */			 this.player.sendMessage(this.player.a("demo.reminder", new Object[0]));
/*	62 */			 this.e = 0;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void dig(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*	68 */		 if (this.d) {
/*	69 */			 e();
/*	70 */			 return;
/*		 */		 }
/*	72 */		 super.dig(paramInt1, paramInt2, paramInt3, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	77 */		 if (this.d) {
/*	78 */			 return;
/*		 */		 }
/*	80 */		 super.a(paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean breakBlock(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	85 */		 if (this.d) {
/*	86 */			 return false;
/*		 */		 }
/*	88 */		 return super.breakBlock(paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean useItem(EntityHuman paramEntityHuman, World paramWorld, ItemStack paramItemStack)
/*		 */	 {
/*	93 */		 if (this.d) {
/*	94 */			 e();
/*	95 */			 return false;
/*		 */		 }
/*	97 */		 return super.useItem(paramEntityHuman, paramWorld, paramItemStack);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(EntityHuman paramEntityHuman, World paramWorld, ItemStack paramItemStack, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/* 102 */		 if (this.d) {
/* 103 */			 e();
/* 104 */			 return false;
/*		 */		 }
/* 106 */		 return super.interact(paramEntityHuman, paramWorld, paramItemStack, paramInt1, paramInt2, paramInt3, paramInt4, paramFloat1, paramFloat2, paramFloat3);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.DemoItemInWorldManager
 * JD-Core Version:		0.6.0
 */