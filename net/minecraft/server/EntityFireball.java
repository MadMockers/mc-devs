/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.entity.CraftEntity;
/*		 */ import org.bukkit.entity.Explosive;
/*		 */ import org.bukkit.entity.Projectile;
/*		 */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*		 */ import org.bukkit.event.entity.ProjectileHitEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntityFireball extends Entity
/*		 */ {
/*	13 */	 private int e = -1;
/*	14 */	 private int f = -1;
/*	15 */	 private int g = -1;
/*	16 */	 private int h = 0;
/*	17 */	 private boolean i = false;
/*		 */	 public EntityLiving shooter;
/*		 */	 private int j;
/*	20 */	 private int an = 0;
/*		 */	 public double dirX;
/*		 */	 public double dirY;
/*		 */	 public double dirZ;
/*	24 */	 public float yield = 1.0F;
/*	25 */	 public boolean isIncendiary = true;
/*		 */ 
/*		 */	 public EntityFireball(World world) {
/*	28 */		 super(world);
/*	29 */		 a(1.0F, 1.0F);
/*		 */	 }
/*		 */	 protected void a() {
/*		 */	 }
/*		 */ 
/*		 */	 public EntityFireball(World world, double d0, double d1, double d2, double d3, double d4, double d5) {
/*	35 */		 super(world);
/*	36 */		 a(1.0F, 1.0F);
/*	37 */		 setPositionRotation(d0, d1, d2, this.yaw, this.pitch);
/*	38 */		 setPosition(d0, d1, d2);
/*	39 */		 double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*		 */ 
/*	41 */		 this.dirX = (d3 / d6 * 0.1D);
/*	42 */		 this.dirY = (d4 / d6 * 0.1D);
/*	43 */		 this.dirZ = (d5 / d6 * 0.1D);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityFireball(World world, EntityLiving entityliving, double d0, double d1, double d2) {
/*	47 */		 super(world);
/*	48 */		 this.shooter = entityliving;
/*	49 */		 a(1.0F, 1.0F);
/*	50 */		 setPositionRotation(entityliving.locX, entityliving.locY, entityliving.locZ, entityliving.yaw, entityliving.pitch);
/*	51 */		 setPosition(this.locX, this.locY, this.locZ);
/*	52 */		 this.height = 0.0F;
/*	53 */		 this.motX = (this.motY = this.motZ = 0.0D);
/*		 */ 
/*	55 */		 setDirection(d0, d1, d2);
/*		 */	 }
/*		 */ 
/*		 */	 public void setDirection(double d0, double d1, double d2)
/*		 */	 {
/*	60 */		 d0 += this.random.nextGaussian() * 0.4D;
/*	61 */		 d1 += this.random.nextGaussian() * 0.4D;
/*	62 */		 d2 += this.random.nextGaussian() * 0.4D;
/*	63 */		 double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*		 */ 
/*	65 */		 this.dirX = (d0 / d3 * 0.1D);
/*	66 */		 this.dirY = (d1 / d3 * 0.1D);
/*	67 */		 this.dirZ = (d2 / d3 * 0.1D);
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/*	71 */		 if ((!this.world.isStatic) && (((this.shooter != null) && (this.shooter.dead)) || (!this.world.isLoaded((int)this.locX, (int)this.locY, (int)this.locZ)))) {
/*	72 */			 die();
/*		 */		 } else {
/*	74 */			 super.h_();
/*	75 */			 setOnFire(1);
/*	76 */			 if (this.i) {
/*	77 */				 int i = this.world.getTypeId(this.e, this.f, this.g);
/*		 */ 
/*	79 */				 if (i == this.h) {
/*	80 */					 this.j += 1;
/*	81 */					 if (this.j == 600) {
/*	82 */						 die();
/*		 */					 }
/*		 */ 
/*	85 */					 return;
/*		 */				 }
/*		 */ 
/*	88 */				 this.i = false;
/*	89 */				 this.motX *= this.random.nextFloat() * 0.2F;
/*	90 */				 this.motY *= this.random.nextFloat() * 0.2F;
/*	91 */				 this.motZ *= this.random.nextFloat() * 0.2F;
/*	92 */				 this.j = 0;
/*	93 */				 this.an = 0;
/*		 */			 } else {
/*	95 */				 this.an += 1;
/*		 */			 }
/*		 */ 
/*	98 */			 Vec3D vec3d = Vec3D.a().create(this.locX, this.locY, this.locZ);
/*	99 */			 Vec3D vec3d1 = Vec3D.a().create(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 100 */			 MovingObjectPosition movingobjectposition = this.world.a(vec3d, vec3d1);
/*		 */ 
/* 102 */			 vec3d = Vec3D.a().create(this.locX, this.locY, this.locZ);
/* 103 */			 vec3d1 = Vec3D.a().create(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 104 */			 if (movingobjectposition != null) {
/* 105 */				 vec3d1 = Vec3D.a().create(movingobjectposition.pos.a, movingobjectposition.pos.b, movingobjectposition.pos.c);
/*		 */			 }
/*		 */ 
/* 108 */			 Entity entity = null;
/* 109 */			 List list = this.world.getEntities(this, this.boundingBox.a(this.motX, this.motY, this.motZ).grow(1.0D, 1.0D, 1.0D));
/* 110 */			 double d0 = 0.0D;
/* 111 */			 Iterator iterator = list.iterator();
/*		 */ 
/* 113 */			 while (iterator.hasNext()) {
/* 114 */				 Entity entity1 = (Entity)iterator.next();
/*		 */ 
/* 116 */				 if ((entity1.L()) && ((!entity1.i(this.shooter)) || (this.an >= 25))) {
/* 117 */					 float f = 0.3F;
/* 118 */					 AxisAlignedBB axisalignedbb = entity1.boundingBox.grow(f, f, f);
/* 119 */					 MovingObjectPosition movingobjectposition1 = axisalignedbb.a(vec3d, vec3d1);
/*		 */ 
/* 121 */					 if (movingobjectposition1 != null) {
/* 122 */						 double d1 = vec3d.distanceSquared(movingobjectposition1.pos);
/*		 */ 
/* 124 */						 if ((d1 < d0) || (d0 == 0.0D)) {
/* 125 */							 entity = entity1;
/* 126 */							 d0 = d1;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 132 */			 if (entity != null) {
/* 133 */				 movingobjectposition = new MovingObjectPosition(entity);
/*		 */			 }
/*		 */ 
/* 136 */			 if (movingobjectposition != null) {
/* 137 */				 a(movingobjectposition);
/*		 */ 
/* 140 */				 if (this.dead) {
/* 141 */					 ProjectileHitEvent phe = new ProjectileHitEvent((Projectile)getBukkitEntity());
/* 142 */					 this.world.getServer().getPluginManager().callEvent(phe);
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 147 */			 this.locX += this.motX;
/* 148 */			 this.locY += this.motY;
/* 149 */			 this.locZ += this.motZ;
/* 150 */			 float f1 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*		 */ 
/* 152 */			 this.yaw = (float)(Math.atan2(this.motX, this.motZ) * 180.0D / 3.141592741012573D);
/*		 */ 
/* 154 */			 for (this.pitch = (float)(Math.atan2(this.motY, f1) * 180.0D / 3.141592741012573D); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F);
/* 158 */			 while (this.pitch - this.lastPitch >= 180.0F) {
/* 159 */				 this.lastPitch += 360.0F;
/*		 */			 }
/*		 */ 
/* 162 */			 while (this.yaw - this.lastYaw < -180.0F) {
/* 163 */				 this.lastYaw -= 360.0F;
/*		 */			 }
/*		 */ 
/* 166 */			 while (this.yaw - this.lastYaw >= 180.0F) {
/* 167 */				 this.lastYaw += 360.0F;
/*		 */			 }
/*		 */ 
/* 170 */			 this.pitch = (this.lastPitch + (this.pitch - this.lastPitch) * 0.2F);
/* 171 */			 this.yaw = (this.lastYaw + (this.yaw - this.lastYaw) * 0.2F);
/* 172 */			 float f2 = 0.95F;
/*		 */ 
/* 174 */			 if (H()) {
/* 175 */				 for (int j = 0; j < 4; j++) {
/* 176 */					 float f3 = 0.25F;
/*		 */ 
/* 178 */					 this.world.a("bubble", this.locX - this.motX * f3, this.locY - this.motY * f3, this.locZ - this.motZ * f3, this.motX, this.motY, this.motZ);
/*		 */				 }
/*		 */ 
/* 181 */				 f2 = 0.8F;
/*		 */			 }
/*		 */ 
/* 184 */			 this.motX += this.dirX;
/* 185 */			 this.motY += this.dirY;
/* 186 */			 this.motZ += this.dirZ;
/* 187 */			 this.motX *= f2;
/* 188 */			 this.motY *= f2;
/* 189 */			 this.motZ *= f2;
/* 190 */			 this.world.a("smoke", this.locX, this.locY + 0.5D, this.locZ, 0.0D, 0.0D, 0.0D);
/* 191 */			 setPosition(this.locX, this.locY, this.locZ);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(MovingObjectPosition movingobjectposition) {
/* 196 */		 if (!this.world.isStatic) {
/* 197 */			 if (movingobjectposition.entity != null) {
/* 198 */				 movingobjectposition.entity.damageEntity(DamageSource.fireball(this, this.shooter), 6);
/*		 */			 }
/*		 */ 
/* 202 */			 ExplosionPrimeEvent event = new ExplosionPrimeEvent((Explosive)CraftEntity.getEntity(this.world.getServer(), this));
/* 203 */			 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 205 */			 if (!event.isCancelled())
/*		 */			 {
/* 207 */				 this.world.createExplosion(this, this.locX, this.locY, this.locZ, event.getRadius(), event.getFire());
/*		 */			 }
/*		 */ 
/* 210 */			 die();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/* 215 */		 nbttagcompound.setShort("xTile", (short)this.e);
/* 216 */		 nbttagcompound.setShort("yTile", (short)this.f);
/* 217 */		 nbttagcompound.setShort("zTile", (short)this.g);
/* 218 */		 nbttagcompound.setByte("inTile", (byte)this.h);
/* 219 */		 nbttagcompound.setByte("inGround", (byte)(this.i ? 1 : 0));
/* 220 */		 nbttagcompound.set("direction", a(new double[] { this.motX, this.motY, this.motZ }));
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/* 224 */		 this.e = nbttagcompound.getShort("xTile");
/* 225 */		 this.f = nbttagcompound.getShort("yTile");
/* 226 */		 this.g = nbttagcompound.getShort("zTile");
/* 227 */		 this.h = (nbttagcompound.getByte("inTile") & 0xFF);
/* 228 */		 this.i = (nbttagcompound.getByte("inGround") == 1);
/* 229 */		 if (nbttagcompound.hasKey("direction")) {
/* 230 */			 NBTTagList nbttaglist = nbttagcompound.getList("direction");
/*		 */ 
/* 232 */			 this.motX = ((NBTTagDouble)nbttaglist.get(0)).data;
/* 233 */			 this.motY = ((NBTTagDouble)nbttaglist.get(1)).data;
/* 234 */			 this.motZ = ((NBTTagDouble)nbttaglist.get(2)).data;
/*		 */		 } else {
/* 236 */			 die();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean L() {
/* 241 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public float Y() {
/* 245 */		 return 1.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i) {
/* 249 */		 K();
/* 250 */		 if (damagesource.getEntity() != null) {
/* 251 */			 Vec3D vec3d = damagesource.getEntity().Z();
/*		 */ 
/* 253 */			 if (vec3d != null) {
/* 254 */				 this.motX = vec3d.a;
/* 255 */				 this.motY = vec3d.b;
/* 256 */				 this.motZ = vec3d.c;
/* 257 */				 this.dirX = (this.motX * 0.1D);
/* 258 */				 this.dirY = (this.motY * 0.1D);
/* 259 */				 this.dirZ = (this.motZ * 0.1D);
/*		 */			 }
/*		 */ 
/* 262 */			 if ((damagesource.getEntity() instanceof EntityLiving)) {
/* 263 */				 this.shooter = ((EntityLiving)damagesource.getEntity());
/*		 */			 }
/*		 */ 
/* 266 */			 return true;
/*		 */		 }
/* 268 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public float c(float f)
/*		 */	 {
/* 273 */		 return 1.0F;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityFireball
 * JD-Core Version:		0.6.0
 */