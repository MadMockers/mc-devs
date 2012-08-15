/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ 
/*		 */ public class TileEntityPiston extends TileEntity
/*		 */ {
/*		 */	 private int a;
/*		 */	 private int b;
/*		 */	 private int c;
/*		 */	 private boolean d;
/*		 */	 private boolean e;
/*		 */	 private float f;
/*		 */	 private float g;
/*	16 */	 private List h = new ArrayList();
/*		 */ 
/*		 */	 public TileEntityPiston() {
/*		 */	 }
/*		 */	 public TileEntityPiston(int i, int j, int k, boolean flag, boolean flag1) {
/*	21 */		 this.a = i;
/*	22 */		 this.b = j;
/*	23 */		 this.c = k;
/*	24 */		 this.d = flag;
/*	25 */		 this.e = flag1;
/*		 */	 }
/*		 */ 
/*		 */	 public int a() {
/*	29 */		 return this.a;
/*		 */	 }
/*		 */ 
/*		 */	 public int n() {
/*	33 */		 return this.b;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b() {
/*	37 */		 return this.d;
/*		 */	 }
/*		 */ 
/*		 */	 public int c() {
/*	41 */		 return this.c;
/*		 */	 }
/*		 */ 
/*		 */	 public float a(float f) {
/*	45 */		 if (f > 1.0F) {
/*	46 */			 f = 1.0F;
/*		 */		 }
/*		 */ 
/*	49 */		 return this.g + (this.f - this.g) * f;
/*		 */	 }
/*		 */ 
/*		 */	 private void a(float f, float f1) {
/*	53 */		 if (this.d)
/*	54 */			 f = 1.0F - f;
/*		 */		 else {
/*	56 */			 f -= 1.0F;
/*		 */		 }
/*		 */ 
/*	59 */		 AxisAlignedBB axisalignedbb = Block.PISTON_MOVING.b(this.world, this.x, this.y, this.z, this.a, f, this.c);
/*		 */ 
/*	61 */		 if (axisalignedbb != null) {
/*	62 */			 List list = this.world.getEntities((Entity)null, axisalignedbb);
/*		 */ 
/*	64 */			 if (!list.isEmpty()) {
/*	65 */				 this.h.addAll(list);
/*	66 */				 Iterator iterator = this.h.iterator();
/*		 */ 
/*	68 */				 while (iterator.hasNext()) {
/*	69 */					 Entity entity = (Entity)iterator.next();
/*		 */ 
/*	71 */					 entity.move(f1 * Facing.b[this.c], f1 * Facing.c[this.c], f1 * Facing.d[this.c]);
/*		 */				 }
/*		 */ 
/*	74 */				 this.h.clear();
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void i() {
/*	80 */		 if ((this.g < 1.0F) && (this.world != null)) {
/*	81 */			 this.g = (this.f = 1.0F);
/*	82 */			 this.world.q(this.x, this.y, this.z);
/*	83 */			 j();
/*	84 */			 if (this.world.getTypeId(this.x, this.y, this.z) == Block.PISTON_MOVING.id)
/*	85 */				 this.world.setTypeIdAndData(this.x, this.y, this.z, this.a, this.b);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void g()
/*		 */	 {
/*	91 */		 if (this.world == null) return;
/*		 */ 
/*	93 */		 this.g = this.f;
/*	94 */		 if (this.g >= 1.0F) {
/*	95 */			 a(1.0F, 0.25F);
/*	96 */			 this.world.q(this.x, this.y, this.z);
/*	97 */			 j();
/*	98 */			 if (this.world.getTypeId(this.x, this.y, this.z) == Block.PISTON_MOVING.id)
/*	99 */				 this.world.setTypeIdAndData(this.x, this.y, this.z, this.a, this.b);
/*		 */		 }
/*		 */		 else {
/* 102 */			 this.f += 0.5F;
/* 103 */			 if (this.f >= 1.0F) {
/* 104 */				 this.f = 1.0F;
/*		 */			 }
/*		 */ 
/* 107 */			 if (this.d)
/* 108 */				 a(this.f, this.f - this.g + 0.0625F);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound)
/*		 */	 {
/* 114 */		 super.a(nbttagcompound);
/* 115 */		 this.a = nbttagcompound.getInt("blockId");
/* 116 */		 this.b = nbttagcompound.getInt("blockData");
/* 117 */		 this.c = nbttagcompound.getInt("facing");
/* 118 */		 this.g = (this.f = nbttagcompound.getFloat("progress"));
/* 119 */		 this.d = nbttagcompound.getBoolean("extending");
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/* 123 */		 super.b(nbttagcompound);
/* 124 */		 nbttagcompound.setInt("blockId", this.a);
/* 125 */		 nbttagcompound.setInt("blockData", this.b);
/* 126 */		 nbttagcompound.setInt("facing", this.c);
/* 127 */		 nbttagcompound.setFloat("progress", this.g);
/* 128 */		 nbttagcompound.setBoolean("extending", this.d);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.TileEntityPiston
 * JD-Core Version:		0.6.0
 */