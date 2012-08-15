/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Bukkit;
/*		 */ import org.bukkit.entity.Projectile;
/*		 */ import org.bukkit.event.entity.ProjectileHitEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public abstract class EntityProjectile extends Entity
/*		 */ {
/*	10 */	 private int blockX = -1;
/*	11 */	 private int blockY = -1;
/*	12 */	 private int blockZ = -1;
/*	13 */	 private int inBlockId = 0;
/*	14 */	 protected boolean inGround = false;
/*	15 */	 public int shake = 0;
/*		 */	 public EntityLiving shooter;
/*		 */	 private int h;
/*	18 */	 private int i = 0;
/*		 */ 
/*		 */	 public EntityProjectile(World world) {
/*	21 */		 super(world);
/*	22 */		 a(0.25F, 0.25F);
/*		 */	 }
/*		 */	 protected void a() {
/*		 */	 }
/*		 */ 
/*		 */	 public EntityProjectile(World world, EntityLiving entityliving) {
/*	28 */		 super(world);
/*	29 */		 this.shooter = entityliving;
/*	30 */		 a(0.25F, 0.25F);
/*	31 */		 setPositionRotation(entityliving.locX, entityliving.locY + entityliving.getHeadHeight(), entityliving.locZ, entityliving.yaw, entityliving.pitch);
/*	32 */		 this.locX -= MathHelper.cos(this.yaw / 180.0F * 3.141593F) * 0.16F;
/*	33 */		 this.locY -= 0.1000000014901161D;
/*	34 */		 this.locZ -= MathHelper.sin(this.yaw / 180.0F * 3.141593F) * 0.16F;
/*	35 */		 setPosition(this.locX, this.locY, this.locZ);
/*	36 */		 this.height = 0.0F;
/*	37 */		 float f = 0.4F;
/*		 */ 
/*	39 */		 this.motX = (-MathHelper.sin(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F) * f);
/*	40 */		 this.motZ = (MathHelper.cos(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F) * f);
/*	41 */		 this.motY = (-MathHelper.sin((this.pitch + g()) / 180.0F * 3.141593F) * f);
/*	42 */		 c(this.motX, this.motY, this.motZ, d(), 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityProjectile(World world, double d0, double d1, double d2) {
/*	46 */		 super(world);
/*	47 */		 this.h = 0;
/*	48 */		 a(0.25F, 0.25F);
/*	49 */		 setPosition(d0, d1, d2);
/*	50 */		 this.height = 0.0F;
/*		 */	 }
/*		 */ 
/*		 */	 protected float d() {
/*	54 */		 return 1.5F;
/*		 */	 }
/*		 */ 
/*		 */	 protected float g() {
/*	58 */		 return 0.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public void c(double d0, double d1, double d2, float f, float f1) {
/*	62 */		 float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*		 */ 
/*	64 */		 d0 /= f2;
/*	65 */		 d1 /= f2;
/*	66 */		 d2 /= f2;
/*	67 */		 d0 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/*	68 */		 d1 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/*	69 */		 d2 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/*	70 */		 d0 *= f;
/*	71 */		 d1 *= f;
/*	72 */		 d2 *= f;
/*	73 */		 this.motX = d0;
/*	74 */		 this.motY = d1;
/*	75 */		 this.motZ = d2;
/*	76 */		 float f3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/*		 */ 
/*	78 */		 this.lastYaw = (this.yaw = (float)(Math.atan2(d0, d2) * 180.0D / 3.141592741012573D));
/*	79 */		 this.lastPitch = (this.pitch = (float)(Math.atan2(d1, f3) * 180.0D / 3.141592741012573D));
/*	80 */		 this.h = 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/*	84 */		 this.S = this.locX;
/*	85 */		 this.T = this.locY;
/*	86 */		 this.U = this.locZ;
/*	87 */		 super.h_();
/*	88 */		 if (this.shake > 0) {
/*	89 */			 this.shake -= 1;
/*		 */		 }
/*		 */ 
/*	92 */		 if (this.inGround) {
/*	93 */			 int i = this.world.getTypeId(this.blockX, this.blockY, this.blockZ);
/*		 */ 
/*	95 */			 if (i == this.inBlockId) {
/*	96 */				 this.h += 1;
/*	97 */				 if (this.h == 1200) {
/*	98 */					 die();
/*		 */				 }
/*		 */ 
/* 101 */				 return;
/*		 */			 }
/*		 */ 
/* 104 */			 this.inGround = false;
/* 105 */			 this.motX *= this.random.nextFloat() * 0.2F;
/* 106 */			 this.motY *= this.random.nextFloat() * 0.2F;
/* 107 */			 this.motZ *= this.random.nextFloat() * 0.2F;
/* 108 */			 this.h = 0;
/* 109 */			 this.i = 0;
/*		 */		 } else {
/* 111 */			 this.i += 1;
/*		 */		 }
/*		 */ 
/* 114 */		 Vec3D vec3d = Vec3D.a().create(this.locX, this.locY, this.locZ);
/* 115 */		 Vec3D vec3d1 = Vec3D.a().create(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 116 */		 MovingObjectPosition movingobjectposition = this.world.a(vec3d, vec3d1);
/*		 */ 
/* 118 */		 vec3d = Vec3D.a().create(this.locX, this.locY, this.locZ);
/* 119 */		 vec3d1 = Vec3D.a().create(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 120 */		 if (movingobjectposition != null) {
/* 121 */			 vec3d1 = Vec3D.a().create(movingobjectposition.pos.a, movingobjectposition.pos.b, movingobjectposition.pos.c);
/*		 */		 }
/*		 */ 
/* 124 */		 if (!this.world.isStatic) {
/* 125 */			 Entity entity = null;
/* 126 */			 List list = this.world.getEntities(this, this.boundingBox.a(this.motX, this.motY, this.motZ).grow(1.0D, 1.0D, 1.0D));
/* 127 */			 double d0 = 0.0D;
/* 128 */			 Iterator iterator = list.iterator();
/*		 */ 
/* 130 */			 while (iterator.hasNext()) {
/* 131 */				 Entity entity1 = (Entity)iterator.next();
/*		 */ 
/* 133 */				 if ((entity1.L()) && ((entity1 != this.shooter) || (this.i >= 5))) {
/* 134 */					 float f = 0.3F;
/* 135 */					 AxisAlignedBB axisalignedbb = entity1.boundingBox.grow(f, f, f);
/* 136 */					 MovingObjectPosition movingobjectposition1 = axisalignedbb.a(vec3d, vec3d1);
/*		 */ 
/* 138 */					 if (movingobjectposition1 != null) {
/* 139 */						 double d1 = vec3d.distanceSquared(movingobjectposition1.pos);
/*		 */ 
/* 141 */						 if ((d1 < d0) || (d0 == 0.0D)) {
/* 142 */							 entity = entity1;
/* 143 */							 d0 = d1;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 149 */			 if (entity != null) {
/* 150 */				 movingobjectposition = new MovingObjectPosition(entity);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 154 */		 if (movingobjectposition != null) {
/* 155 */			 a(movingobjectposition);
/*		 */ 
/* 157 */			 if (this.dead) {
/* 158 */				 ProjectileHitEvent hitEvent = new ProjectileHitEvent((Projectile)getBukkitEntity());
/* 159 */				 Bukkit.getPluginManager().callEvent(hitEvent);
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 164 */		 this.locX += this.motX;
/* 165 */		 this.locY += this.motY;
/* 166 */		 this.locZ += this.motZ;
/* 167 */		 float f1 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*		 */ 
/* 169 */		 this.yaw = (float)(Math.atan2(this.motX, this.motZ) * 180.0D / 3.141592741012573D);
/*		 */ 
/* 171 */		 for (this.pitch = (float)(Math.atan2(this.motY, f1) * 180.0D / 3.141592741012573D); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F);
/* 175 */		 while (this.pitch - this.lastPitch >= 180.0F) {
/* 176 */			 this.lastPitch += 360.0F;
/*		 */		 }
/*		 */ 
/* 179 */		 while (this.yaw - this.lastYaw < -180.0F) {
/* 180 */			 this.lastYaw -= 360.0F;
/*		 */		 }
/*		 */ 
/* 183 */		 while (this.yaw - this.lastYaw >= 180.0F) {
/* 184 */			 this.lastYaw += 360.0F;
/*		 */		 }
/*		 */ 
/* 187 */		 this.pitch = (this.lastPitch + (this.pitch - this.lastPitch) * 0.2F);
/* 188 */		 this.yaw = (this.lastYaw + (this.yaw - this.lastYaw) * 0.2F);
/* 189 */		 float f2 = 0.99F;
/* 190 */		 float f3 = h();
/*		 */ 
/* 192 */		 if (H()) {
/* 193 */			 for (int j = 0; j < 4; j++) {
/* 194 */				 float f4 = 0.25F;
/*		 */ 
/* 196 */				 this.world.a("bubble", this.locX - this.motX * f4, this.locY - this.motY * f4, this.locZ - this.motZ * f4, this.motX, this.motY, this.motZ);
/*		 */			 }
/*		 */ 
/* 199 */			 f2 = 0.8F;
/*		 */		 }
/*		 */ 
/* 202 */		 this.motX *= f2;
/* 203 */		 this.motY *= f2;
/* 204 */		 this.motZ *= f2;
/* 205 */		 this.motY -= f3;
/* 206 */		 setPosition(this.locX, this.locY, this.locZ);
/*		 */	 }
/*		 */ 
/*		 */	 protected float h() {
/* 210 */		 return 0.03F;
/*		 */	 }
/*		 */	 protected abstract void a(MovingObjectPosition paramMovingObjectPosition);
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/* 216 */		 nbttagcompound.setShort("xTile", (short)this.blockX);
/* 217 */		 nbttagcompound.setShort("yTile", (short)this.blockY);
/* 218 */		 nbttagcompound.setShort("zTile", (short)this.blockZ);
/* 219 */		 nbttagcompound.setByte("inTile", (byte)this.inBlockId);
/* 220 */		 nbttagcompound.setByte("shake", (byte)this.shake);
/* 221 */		 nbttagcompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/* 225 */		 this.blockX = nbttagcompound.getShort("xTile");
/* 226 */		 this.blockY = nbttagcompound.getShort("yTile");
/* 227 */		 this.blockZ = nbttagcompound.getShort("zTile");
/* 228 */		 this.inBlockId = (nbttagcompound.getByte("inTile") & 0xFF);
/* 229 */		 this.shake = (nbttagcompound.getByte("shake") & 0xFF);
/* 230 */		 this.inGround = (nbttagcompound.getByte("inGround") == 1);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityProjectile
 * JD-Core Version:		0.6.0
 */