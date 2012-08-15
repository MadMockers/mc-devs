/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class PathfinderGoalJumpOnBlock extends PathfinderGoal
/*		 */ {
/*		 */	 private final EntityOcelot a;
/*		 */	 private final float b;
/*	19 */	 private int c = 0;
/*	20 */	 private int d = 0;
/*	21 */	 private int e = 0;
/*	22 */	 private int f = 0;
/*	23 */	 private int g = 0;
/*	24 */	 private int h = 0;
/*		 */ 
/*		 */	 public PathfinderGoalJumpOnBlock(EntityOcelot paramEntityOcelot, float paramFloat) {
/*	27 */		 this.a = paramEntityOcelot;
/*	28 */		 this.b = paramFloat;
/*	29 */		 a(5);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a()
/*		 */	 {
/*	34 */		 return (this.a.isTamed()) && (!this.a.isSitting()) && (this.a.au().nextDouble() <= 0.006500000134110451D) && (f());
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b()
/*		 */	 {
/*	39 */		 return (this.c <= this.e) && (this.d <= 60) && (a(this.a.world, this.f, this.g, this.h));
/*		 */	 }
/*		 */ 
/*		 */	 public void e()
/*		 */	 {
/*	44 */		 this.a.getNavigation().a(this.f + 0.5D, this.g + 1, this.h + 0.5D, this.b);
/*	45 */		 this.c = 0;
/*	46 */		 this.d = 0;
/*	47 */		 this.e = (this.a.au().nextInt(this.a.au().nextInt(1200) + 1200) + 1200);
/*	48 */		 this.a.r().a(false);
/*		 */	 }
/*		 */ 
/*		 */	 public void c()
/*		 */	 {
/*	53 */		 this.a.setSitting(false);
/*		 */	 }
/*		 */ 
/*		 */	 public void d()
/*		 */	 {
/*	58 */		 this.c += 1;
/*	59 */		 this.a.r().a(false);
/*	60 */		 if (this.a.e(this.f, this.g + 1, this.h) > 1.0D) {
/*	61 */			 this.a.setSitting(false);
/*	62 */			 this.a.getNavigation().a(this.f + 0.5D, this.g + 1, this.h + 0.5D, this.b);
/*	63 */			 this.d += 1;
/*	64 */		 } else if (!this.a.isSitting()) {
/*	65 */			 this.a.setSitting(true);
/*		 */		 } else {
/*	67 */			 this.d -= 1;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean f() {
/*	72 */		 int i = (int)this.a.locY;
/*	73 */		 double d1 = 2147483647.0D;
/*		 */ 
/*	75 */		 for (int j = (int)this.a.locX - 8; j < this.a.locX + 8.0D; j++) {
/*	76 */			 for (int k = (int)this.a.locZ - 8; k < this.a.locZ + 8.0D; k++) {
/*	77 */				 if ((a(this.a.world, j, i, k)) && (this.a.world.isEmpty(j, i + 1, k))) {
/*	78 */					 double d2 = this.a.e(j, i, k);
/*		 */ 
/*	80 */					 if (d2 < d1) {
/*	81 */						 this.f = j;
/*	82 */						 this.g = i;
/*	83 */						 this.h = k;
/*	84 */						 d1 = d2;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	90 */		 return d1 < 2147483647.0D;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
/*	94 */		 int i = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3);
/*	95 */		 int j = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*		 */ 
/*	97 */		 if (i == Block.CHEST.id) {
/*	98 */			 TileEntityChest localTileEntityChest = (TileEntityChest)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
/*		 */ 
/* 100 */			 if (localTileEntityChest.h < 1)
/* 101 */				 return true;
/*		 */		 } else {
/* 103 */			 if (i == Block.BURNING_FURNACE.id)
/* 104 */				 return true;
/* 105 */			 if ((i == Block.BED.id) && (!BlockBed.a_(j))) {
/* 106 */				 return true;
/*		 */			 }
/*		 */		 }
/* 109 */		 return false;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalJumpOnBlock
 * JD-Core Version:		0.6.0
 */