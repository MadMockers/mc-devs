/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.entity.CraftEntity;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.entity.EntityTargetEvent;
/*		 */ import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
/*		 */ import org.bukkit.event.player.PlayerExpChangeEvent;
/*		 */ 
/*		 */ public class EntityExperienceOrb extends Entity
/*		 */ {
/*		 */	 public int a;
/*	11 */	 public int b = 0;
/*		 */	 public int c;
/*	13 */	 private int d = 5;
/*		 */	 public int value;
/*		 */ 
/*		 */	 public EntityExperienceOrb(World world, double d0, double d1, double d2, int i)
/*		 */	 {
/*	17 */		 super(world);
/*	18 */		 a(0.5F, 0.5F);
/*	19 */		 this.height = (this.length / 2.0F);
/*	20 */		 setPosition(d0, d1, d2);
/*	21 */		 this.yaw = (float)(Math.random() * 360.0D);
/*	22 */		 this.motX = ((float)(Math.random() * 0.2000000029802322D - 0.1000000014901161D) * 2.0F);
/*	23 */		 this.motY = ((float)(Math.random() * 0.2D) * 2.0F);
/*	24 */		 this.motZ = ((float)(Math.random() * 0.2000000029802322D - 0.1000000014901161D) * 2.0F);
/*	25 */		 this.value = i;
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean e_() {
/*	29 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityExperienceOrb(World world) {
/*	33 */		 super(world);
/*	34 */		 a(0.25F, 0.25F);
/*	35 */		 this.height = (this.length / 2.0F);
/*		 */	 }
/*		 */	 protected void a() {
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/*	41 */		 super.h_();
/*	42 */		 if (this.c > 0) {
/*	43 */			 this.c -= 1;
/*		 */		 }
/*		 */ 
/*	46 */		 this.lastX = this.locX;
/*	47 */		 this.lastY = this.locY;
/*	48 */		 this.lastZ = this.locZ;
/*	49 */		 this.motY -= 0.02999999932944775D;
/*	50 */		 if (this.world.getMaterial(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)) == Material.LAVA) {
/*	51 */			 this.motY = 0.2000000029802322D;
/*	52 */			 this.motX = ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*	53 */			 this.motZ = ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*	54 */			 this.world.makeSound(this, "random.fizz", 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
/*		 */		 }
/*		 */ 
/*	57 */		 i(this.locX, (this.boundingBox.b + this.boundingBox.e) / 2.0D, this.locZ);
/*	58 */		 double d0 = 8.0D;
/*	59 */		 EntityHuman entityhuman = this.world.findNearbyPlayer(this, d0);
/*		 */ 
/*	61 */		 if (entityhuman != null)
/*		 */		 {
/*	63 */			 EntityTargetEvent event = CraftEventFactory.callEntityTargetEvent(this, entityhuman, EntityTargetEvent.TargetReason.CLOSEST_PLAYER);
/*	64 */			 Entity target = event.getTarget() == null ? null : ((CraftEntity)event.getTarget()).getHandle();
/*		 */ 
/*	66 */			 if ((!event.isCancelled()) && (target != null)) {
/*	67 */				 double d1 = (target.locX - this.locX) / d0;
/*	68 */				 double d2 = (target.locY + target.getHeadHeight() - this.locY) / d0;
/*	69 */				 double d3 = (target.locZ - this.locZ) / d0;
/*	70 */				 double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
/*	71 */				 double d5 = 1.0D - d4;
/*	72 */				 if (d5 > 0.0D) {
/*	73 */					 d5 *= d5;
/*	74 */					 this.motX += d1 / d4 * d5 * 0.1D;
/*	75 */					 this.motY += d2 / d4 * d5 * 0.1D;
/*	76 */					 this.motZ += d3 / d4 * d5 * 0.1D;
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/*	82 */		 move(this.motX, this.motY, this.motZ);
/*	83 */		 float f = 0.98F;
/*		 */ 
/*	85 */		 if (this.onGround) {
/*	86 */			 f = 0.5880001F;
/*	87 */			 int i = this.world.getTypeId(MathHelper.floor(this.locX), MathHelper.floor(this.boundingBox.b) - 1, MathHelper.floor(this.locZ));
/*		 */ 
/*	89 */			 if (i > 0) {
/*	90 */				 f = Block.byId[i].frictionFactor * 0.98F;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	94 */		 this.motX *= f;
/*	95 */		 this.motY *= 0.9800000190734863D;
/*	96 */		 this.motZ *= f;
/*	97 */		 if (this.onGround) {
/*	98 */			 this.motY *= -0.8999999761581421D;
/*		 */		 }
/*		 */ 
/* 101 */		 this.a += 1;
/* 102 */		 this.b += 1;
/* 103 */		 if (this.b >= 6000)
/* 104 */			 die();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean I()
/*		 */	 {
/* 109 */		 return this.world.a(this.boundingBox, Material.WATER, this);
/*		 */	 }
/*		 */ 
/*		 */	 protected void burn(int i) {
/* 113 */		 damageEntity(DamageSource.FIRE, i);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i) {
/* 117 */		 K();
/* 118 */		 this.d -= i;
/* 119 */		 if (this.d <= 0) {
/* 120 */			 die();
/*		 */		 }
/*		 */ 
/* 123 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/* 127 */		 nbttagcompound.setShort("Health", (short)(byte)this.d);
/* 128 */		 nbttagcompound.setShort("Age", (short)this.b);
/* 129 */		 nbttagcompound.setShort("Value", (short)this.value);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/* 133 */		 this.d = (nbttagcompound.getShort("Health") & 0xFF);
/* 134 */		 this.b = nbttagcompound.getShort("Age");
/* 135 */		 this.value = nbttagcompound.getShort("Value");
/*		 */	 }
/*		 */ 
/*		 */	 public void b_(EntityHuman entityhuman) {
/* 139 */		 if ((!this.world.isStatic) && 
/* 140 */			 (this.c == 0) && (entityhuman.bL == 0)) {
/* 141 */			 entityhuman.bL = 2;
/* 142 */			 this.world.makeSound(this, "random.orb", 0.1F, 0.5F * ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.8F));
/* 143 */			 entityhuman.receive(this, 1);
/* 144 */			 entityhuman.giveExp(CraftEventFactory.callPlayerExpChangeEvent(entityhuman, this.value).getAmount());
/* 145 */			 die();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int d()
/*		 */	 {
/* 151 */		 return this.value;
/*		 */	 }
/*		 */ 
/*		 */	 public static int getOrbValue(int i)
/*		 */	 {
/* 156 */		 if (i > 162670129) return i - 100000;
/* 157 */		 if (i > 81335063) return 81335063;
/* 158 */		 if (i > 40667527) return 40667527;
/* 159 */		 if (i > 20333759) return 20333759;
/* 160 */		 if (i > 10166857) return 10166857;
/* 161 */		 if (i > 5083423) return 5083423;
/* 162 */		 if (i > 2541701) return 2541701;
/* 163 */		 if (i > 1270849) return 1270849;
/* 164 */		 if (i > 635413) return 635413;
/* 165 */		 if (i > 317701) return 317701;
/* 166 */		 if (i > 158849) return 158849;
/* 167 */		 if (i > 79423) return 79423;
/* 168 */		 if (i > 39709) return 39709;
/* 169 */		 if (i > 19853) return 19853;
/* 170 */		 if (i > 9923) return 9923;
/* 171 */		 if (i > 4957) return 4957;
/*		 */ 
/* 174 */		 return i >= 3 ? 3 : i >= 7 ? 7 : i >= 17 ? 17 : i >= 37 ? 37 : i >= 73 ? 73 : i >= 149 ? 149 : i >= 307 ? 307 : i >= 617 ? 617 : i >= 1237 ? 1237 : i >= 2477 ? 2477 : 1;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean an() {
/* 178 */		 return false;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityExperienceOrb
 * JD-Core Version:		0.6.0
 */