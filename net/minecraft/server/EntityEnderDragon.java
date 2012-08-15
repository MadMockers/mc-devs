/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Collections;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Bukkit;
/*		 */ import org.bukkit.PortalType;
/*		 */ import org.bukkit.block.BlockState;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.util.BlockStateListPopulator;
/*		 */ import org.bukkit.entity.LivingEntity;
/*		 */ import org.bukkit.event.entity.EntityCreatePortalEvent;
/*		 */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*		 */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*		 */ import org.bukkit.event.entity.EntityExplodeEvent;
/*		 */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*		 */ import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntityEnderDragon extends EntityComplex
/*		 */ {
/*		 */	 public double b;
/*		 */	 public double c;
/*		 */	 public double d;
/*	21 */	 public double[][] e = new double[64][3];
/*	22 */	 public int f = -1;
/*		 */	 public EntityComplexPart[] children;
/*		 */	 public EntityComplexPart h;
/*		 */	 public EntityComplexPart i;
/*		 */	 public EntityComplexPart j;
/*		 */	 public EntityComplexPart by;
/*		 */	 public EntityComplexPart bz;
/*		 */	 public EntityComplexPart bA;
/*		 */	 public EntityComplexPart bB;
/*	31 */	 public float bC = 0.0F;
/*	32 */	 public float bD = 0.0F;
/*	33 */	 public boolean bE = false;
/*	34 */	 public boolean bF = false;
/*		 */	 private Entity bI;
/*	36 */	 public int bG = 0;
/*	37 */	 public EntityEnderCrystal bH = null;
/*		 */ 
/*		 */	 public EntityEnderDragon(World world) {
/*	40 */		 super(world);
/*		 */		 EntityComplexPart[] tmp57_54 = new EntityComplexPart[7];
/*		 */			tmp74_71 = new EntityComplexPart(this, "head", 6.0F, 6.0F); this.h = tmp74_71; tmp57_54[0] = tmp74_71;
/*		 */		 EntityComplexPart[] tmp79_57 = tmp57_54;
/*		 */			tmp96_93 = new EntityComplexPart(this, "body", 8.0F, 8.0F); this.i = tmp96_93; tmp79_57[1] = tmp96_93;
/*		 */		 EntityComplexPart[] tmp101_79 = tmp79_57;
/*		 */			tmp118_115 = new EntityComplexPart(this, "tail", 4.0F, 4.0F); this.j = tmp118_115; tmp101_79[2] = tmp118_115;
/*		 */		 EntityComplexPart[] tmp123_101 = tmp101_79;
/*		 */			tmp140_137 = new EntityComplexPart(this, "tail", 4.0F, 4.0F); this.by = tmp140_137; tmp123_101[3] = tmp140_137;
/*		 */		 EntityComplexPart[] tmp145_123 = tmp123_101;
/*		 */			tmp162_159 = new EntityComplexPart(this, "tail", 4.0F, 4.0F); this.bz = tmp162_159; tmp145_123[4] = tmp162_159;
/*		 */		 EntityComplexPart[] tmp167_145 = tmp145_123;
/*		 */			tmp184_181 = new EntityComplexPart(this, "wing", 4.0F, 4.0F); this.bA = tmp184_181; tmp167_145[5] = tmp184_181;
/*		 */		 EntityComplexPart[] tmp189_167 = tmp167_145;
/*		 */			tmp207_204 = new EntityComplexPart(this, "wing", 4.0F, 4.0F); this.bB = tmp207_204; tmp189_167[6] = tmp207_204; this.children = tmp189_167;
/*	42 */		 this.a = 200;
/*	43 */		 setHealth(this.a);
/*	44 */		 this.texture = "/mob/enderdragon/ender.png";
/*	45 */		 a(16.0F, 8.0F);
/*	46 */		 this.X = true;
/*	47 */		 this.fireProof = true;
/*	48 */		 this.c = 100.0D;
/*	49 */		 this.ak = true;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	53 */		 super.a();
/*	54 */		 this.datawatcher.a(16, new Integer(this.a));
/*		 */	 }
/*		 */ 
/*		 */	 public double[] a(int i, float f) {
/*	58 */		 if (this.health <= 0) {
/*	59 */			 f = 0.0F;
/*		 */		 }
/*		 */ 
/*	62 */		 f = 1.0F - f;
/*	63 */		 int j = this.f - i * 1 & 0x3F;
/*	64 */		 int k = this.f - i * 1 - 1 & 0x3F;
/*	65 */		 double[] adouble = new double[3];
/*	66 */		 double d0 = this.e[j][0];
/*	67 */		 double d1 = MathHelper.g(this.e[k][0] - d0);
/*		 */ 
/*	69 */		 adouble[0] = (d0 + d1 * f);
/*	70 */		 d0 = this.e[j][1];
/*	71 */		 d1 = this.e[k][1] - d0;
/*	72 */		 adouble[1] = (d0 + d1 * f);
/*	73 */		 adouble[2] = (this.e[j][2] + (this.e[k][2] - this.e[j][2]) * f);
/*	74 */		 return adouble;
/*		 */	 }
/*		 */ 
/*		 */	 public void d() {
/*	78 */		 this.bC = this.bD;
/*	79 */		 if (!this.world.isStatic) {
/*	80 */			 this.datawatcher.watch(16, Integer.valueOf(this.health));
/*		 */		 }
/*		 */ 
/*	87 */		 if (this.health <= 0) {
/*	88 */			 float f = (this.random.nextFloat() - 0.5F) * 8.0F;
/*	89 */			 float d05 = (this.random.nextFloat() - 0.5F) * 4.0F;
/*	90 */			 float f1 = (this.random.nextFloat() - 0.5F) * 8.0F;
/*	91 */			 this.world.a("largeexplode", this.locX + f, this.locY + 2.0D + d05, this.locZ + f1, 0.0D, 0.0D, 0.0D);
/*		 */		 } else {
/*	93 */			 j();
/*	94 */			 float f = 0.2F / (MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ) * 10.0F + 1.0F);
/*	95 */			 f *= (float)Math.pow(2.0D, this.motY);
/*	96 */			 if (this.bF)
/*	97 */				 this.bD += f * 0.5F;
/*		 */			 else {
/*	99 */				 this.bD += f;
/*		 */			 }
/*		 */ 
/* 102 */			 this.yaw = MathHelper.g(this.yaw);
/* 103 */			 if (this.f < 0) {
/* 104 */				 for (int i = 0; i < this.e.length; i++) {
/* 105 */					 this.e[i][0] = this.yaw;
/* 106 */					 this.e[i][1] = this.locY;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 110 */			 if (++this.f == this.e.length) {
/* 111 */				 this.f = 0;
/*		 */			 }
/*		 */ 
/* 114 */			 this.e[this.f][0] = this.yaw;
/* 115 */			 this.e[this.f][1] = this.locY;
/*		 */ 
/* 122 */			 if (this.world.isStatic) {
/* 123 */				 if (this.bi > 0) {
/* 124 */					 double d0 = this.locX + (this.bj - this.locX) / this.bi;
/* 125 */					 double d1 = this.locY + (this.bk - this.locY) / this.bi;
/* 126 */					 double d2 = this.locZ + (this.bl - this.locZ) / this.bi;
/* 127 */					 double d3 = MathHelper.g(this.bm - this.yaw);
/* 128 */					 this.yaw = (float)(this.yaw + d3 / this.bi);
/* 129 */					 this.pitch = (float)(this.pitch + (this.bn - this.pitch) / this.bi);
/* 130 */					 this.bi -= 1;
/* 131 */					 setPosition(d0, d1, d2);
/* 132 */					 b(this.yaw, this.pitch);
/*		 */				 }
/*		 */			 } else {
/* 135 */				 double d0 = this.b - this.locX;
/* 136 */				 double d1 = this.c - this.locY;
/* 137 */				 double d2 = this.d - this.locZ;
/* 138 */				 double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/* 139 */				 if (this.bI != null) {
/* 140 */					 this.b = this.bI.locX;
/* 141 */					 this.d = this.bI.locZ;
/* 142 */					 double d4 = this.b - this.locX;
/* 143 */					 double d5 = this.d - this.locZ;
/* 144 */					 double d6 = Math.sqrt(d4 * d4 + d5 * d5);
/* 145 */					 double d7 = 0.4000000059604645D + d6 / 80.0D - 1.0D;
/*		 */ 
/* 147 */					 if (d7 > 10.0D) {
/* 148 */						 d7 = 10.0D;
/*		 */					 }
/*		 */ 
/* 151 */					 this.c = (this.bI.boundingBox.b + d7);
/*		 */				 } else {
/* 153 */					 this.b += this.random.nextGaussian() * 2.0D;
/* 154 */					 this.d += this.random.nextGaussian() * 2.0D;
/*		 */				 }
/*		 */ 
/* 157 */				 if ((this.bE) || (d3 < 100.0D) || (d3 > 22500.0D) || (this.positionChanged) || (this.G)) {
/* 158 */					 k();
/*		 */				 }
/*		 */ 
/* 161 */				 d1 /= MathHelper.sqrt(d0 * d0 + d2 * d2);
/* 162 */				 float f3 = 0.6F;
/* 163 */				 if (d1 < -f3) {
/* 164 */					 d1 = -f3;
/*		 */				 }
/*		 */ 
/* 167 */				 if (d1 > f3) {
/* 168 */					 d1 = f3;
/*		 */				 }
/*		 */ 
/* 171 */				 this.motY += d1 * 0.1000000014901161D;
/* 172 */				 this.yaw = MathHelper.g(this.yaw);
/* 173 */				 double d8 = 180.0D - Math.atan2(d0, d2) * 180.0D / 3.141592741012573D;
/* 174 */				 double d9 = MathHelper.g(d8 - this.yaw);
/*		 */ 
/* 176 */				 if (d9 > 50.0D) {
/* 177 */					 d9 = 50.0D;
/*		 */				 }
/*		 */ 
/* 180 */				 if (d9 < -50.0D) {
/* 181 */					 d9 = -50.0D;
/*		 */				 }
/*		 */ 
/* 184 */				 Vec3D vec3d = Vec3D.a().create(this.b - this.locX, this.c - this.locY, this.d - this.locZ).b();
/* 185 */				 Vec3D vec3d1 = Vec3D.a().create(MathHelper.sin(this.yaw * 3.141593F / 180.0F), this.motY, -MathHelper.cos(this.yaw * 3.141593F / 180.0F)).b();
/* 186 */				 float f4 = (float)(vec3d1.b(vec3d) + 0.5D) / 1.5F;
/*		 */ 
/* 188 */				 if (f4 < 0.0F) {
/* 189 */					 f4 = 0.0F;
/*		 */				 }
/*		 */ 
/* 192 */				 this.bt *= 0.8F;
/* 193 */				 float f5 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ) * 1.0F + 1.0F;
/* 194 */				 double d10 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ) * 1.0D + 1.0D;
/*		 */ 
/* 196 */				 if (d10 > 40.0D) {
/* 197 */					 d10 = 40.0D;
/*		 */				 }
/*		 */ 
/* 200 */				 this.bt = (float)(this.bt + d9 * (0.699999988079071D / d10 / f5));
/* 201 */				 this.yaw += this.bt * 0.1F;
/* 202 */				 float f6 = (float)(2.0D / (d10 + 1.0D));
/* 203 */				 float f7 = 0.06F;
/*		 */ 
/* 205 */				 a(0.0F, -1.0F, f7 * (f4 * f6 + (1.0F - f6)));
/* 206 */				 if (this.bF)
/* 207 */					 move(this.motX * 0.800000011920929D, this.motY * 0.800000011920929D, this.motZ * 0.800000011920929D);
/*		 */				 else {
/* 209 */					 move(this.motX, this.motY, this.motZ);
/*		 */				 }
/*		 */ 
/* 212 */				 Vec3D vec3d2 = Vec3D.a().create(this.motX, this.motY, this.motZ).b();
/* 213 */				 float f8 = (float)(vec3d2.b(vec3d1) + 1.0D) / 2.0F;
/*		 */ 
/* 215 */				 f8 = 0.8F + 0.15F * f8;
/* 216 */				 this.motX *= f8;
/* 217 */				 this.motZ *= f8;
/* 218 */				 this.motY *= 0.910000026226044D;
/*		 */			 }
/*		 */ 
/* 221 */			 this.aq = this.yaw;
/* 222 */			 this.h.width = (this.h.length = 3.0F);
/* 223 */			 this.j.width = (this.j.length = 2.0F);
/* 224 */			 this.by.width = (this.by.length = 2.0F);
/* 225 */			 this.bz.width = (this.bz.length = 2.0F);
/* 226 */			 this.i.length = 3.0F;
/* 227 */			 this.i.width = 5.0F;
/* 228 */			 this.bA.length = 2.0F;
/* 229 */			 this.bA.width = 4.0F;
/* 230 */			 this.bB.length = 3.0F;
/* 231 */			 this.bB.width = 4.0F;
/* 232 */			 float d05 = (float)(a(5, 1.0F)[1] - a(10, 1.0F)[1]) * 10.0F / 180.0F * 3.141593F;
/* 233 */			 float f1 = MathHelper.cos(d05);
/* 234 */			 float f9 = -MathHelper.sin(d05);
/* 235 */			 float f10 = this.yaw * 3.141593F / 180.0F;
/* 236 */			 float f11 = MathHelper.sin(f10);
/* 237 */			 float f12 = MathHelper.cos(f10);
/*		 */ 
/* 239 */			 this.i.h_();
/* 240 */			 this.i.setPositionRotation(this.locX + f11 * 0.5F, this.locY, this.locZ - f12 * 0.5F, 0.0F, 0.0F);
/* 241 */			 this.bA.h_();
/* 242 */			 this.bA.setPositionRotation(this.locX + f12 * 4.5F, this.locY + 2.0D, this.locZ + f11 * 4.5F, 0.0F, 0.0F);
/* 243 */			 this.bB.h_();
/* 244 */			 this.bB.setPositionRotation(this.locX - f12 * 4.5F, this.locY + 2.0D, this.locZ - f11 * 4.5F, 0.0F, 0.0F);
/* 245 */			 if ((!this.world.isStatic) && (this.hurtTicks == 0)) {
/* 246 */				 a(this.world.getEntities(this, this.bA.boundingBox.grow(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D)));
/* 247 */				 a(this.world.getEntities(this, this.bB.boundingBox.grow(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D)));
/* 248 */				 b(this.world.getEntities(this, this.h.boundingBox.grow(1.0D, 1.0D, 1.0D)));
/*		 */			 }
/*		 */ 
/* 251 */			 double[] adouble = a(5, 1.0F);
/* 252 */			 double[] adouble1 = a(0, 1.0F);
/*		 */ 
/* 254 */			 float f3 = MathHelper.sin(this.yaw * 3.141593F / 180.0F - this.bt * 0.01F);
/* 255 */			 float f13 = MathHelper.cos(this.yaw * 3.141593F / 180.0F - this.bt * 0.01F);
/*		 */ 
/* 257 */			 this.h.h_();
/* 258 */			 this.h.setPositionRotation(this.locX + f3 * 5.5F * f1, this.locY + (adouble1[1] - adouble[1]) * 1.0D + f9 * 5.5F, this.locZ - f13 * 5.5F * f1, 0.0F, 0.0F);
/*		 */ 
/* 260 */			 for (int j = 0; j < 3; j++) {
/* 261 */				 EntityComplexPart entitycomplexpart = null;
/*		 */ 
/* 263 */				 if (j == 0) {
/* 264 */					 entitycomplexpart = this.j;
/*		 */				 }
/*		 */ 
/* 267 */				 if (j == 1) {
/* 268 */					 entitycomplexpart = this.by;
/*		 */				 }
/*		 */ 
/* 271 */				 if (j == 2) {
/* 272 */					 entitycomplexpart = this.bz;
/*		 */				 }
/*		 */ 
/* 275 */				 double[] adouble2 = a(12 + j * 2, 1.0F);
/* 276 */				 float f14 = this.yaw * 3.141593F / 180.0F + b(adouble2[0] - adouble[0]) * 3.141593F / 180.0F * 1.0F;
/* 277 */				 float f15 = MathHelper.sin(f14);
/* 278 */				 float f16 = MathHelper.cos(f14);
/* 279 */				 float f17 = 1.5F;
/* 280 */				 float f18 = (j + 1) * 2.0F;
/*		 */ 
/* 282 */				 entitycomplexpart.h_();
/* 283 */				 entitycomplexpart.setPositionRotation(this.locX - (f11 * f17 + f15 * f18) * f1, this.locY + (adouble2[1] - adouble[1]) * 1.0D - (f18 + f17) * f9 + 1.5D, this.locZ + (f12 * f17 + f16 * f18) * f1, 0.0F, 0.0F);
/*		 */			 }
/*		 */ 
/* 286 */			 if (!this.world.isStatic)
/* 287 */				 this.bF = (a(this.h.boundingBox) | a(this.i.boundingBox));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void j()
/*		 */	 {
/* 293 */		 if (this.bH != null) {
/* 294 */			 if (this.bH.dead) {
/* 295 */				 if (!this.world.isStatic) {
/* 296 */					 a(this.h, DamageSource.EXPLOSION, 10);
/*		 */				 }
/*		 */ 
/* 299 */				 this.bH = null;
/* 300 */			 } else if ((this.ticksLived % 10 == 0) && (this.health < this.a))
/*		 */			 {
/* 302 */				 EntityRegainHealthEvent event = new EntityRegainHealthEvent(getBukkitEntity(), 1, EntityRegainHealthEvent.RegainReason.ENDER_CRYSTAL);
/* 303 */				 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 305 */				 if (!event.isCancelled()) {
/* 306 */					 this.health += event.getAmount();
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 312 */		 if (this.random.nextInt(10) == 0) {
/* 313 */			 float f = 32.0F;
/* 314 */			 List list = this.world.a(EntityEnderCrystal.class, this.boundingBox.grow(f, f, f));
/* 315 */			 EntityEnderCrystal entityendercrystal = null;
/* 316 */			 double d0 = 1.7976931348623157E+308D;
/* 317 */			 Iterator iterator = list.iterator();
/*		 */ 
/* 319 */			 while (iterator.hasNext()) {
/* 320 */				 EntityEnderCrystal entityendercrystal1 = (EntityEnderCrystal)iterator.next();
/* 321 */				 double d1 = entityendercrystal1.e(this);
/*		 */ 
/* 323 */				 if (d1 < d0) {
/* 324 */					 d0 = d1;
/* 325 */					 entityendercrystal = entityendercrystal1;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 329 */			 this.bH = entityendercrystal;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void a(List list) {
/* 334 */		 double d0 = (this.i.boundingBox.a + this.i.boundingBox.d) / 2.0D;
/* 335 */		 double d1 = (this.i.boundingBox.c + this.i.boundingBox.f) / 2.0D;
/* 336 */		 Iterator iterator = list.iterator();
/*		 */ 
/* 338 */		 while (iterator.hasNext()) {
/* 339 */			 Entity entity = (Entity)iterator.next();
/*		 */ 
/* 341 */			 if ((entity instanceof EntityLiving)) {
/* 342 */				 double d2 = entity.locX - d0;
/* 343 */				 double d3 = entity.locZ - d1;
/* 344 */				 double d4 = d2 * d2 + d3 * d3;
/*		 */ 
/* 346 */				 entity.g(d2 / d4 * 4.0D, 0.2000000029802322D, d3 / d4 * 4.0D);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void b(List list) {
/* 352 */		 Iterator iterator = list.iterator();
/*		 */ 
/* 354 */		 while (iterator.hasNext()) {
/* 355 */			 Entity entity = (Entity)iterator.next();
/*		 */ 
/* 357 */			 if ((entity instanceof EntityLiving))
/*		 */			 {
/* 360 */				 if (!(entity instanceof EntityHuman)) {
/* 361 */					 EntityDamageByEntityEvent damageEvent = new EntityDamageByEntityEvent(getBukkitEntity(), entity.getBukkitEntity(), EntityDamageEvent.DamageCause.ENTITY_ATTACK, 10);
/* 362 */					 Bukkit.getPluginManager().callEvent(damageEvent);
/*		 */ 
/* 364 */					 if (!damageEvent.isCancelled()) {
/* 365 */						 entity.getBukkitEntity().setLastDamageCause(damageEvent);
/* 366 */						 entity.damageEntity(DamageSource.mobAttack(this), damageEvent.getDamage());
/*		 */					 }
/*		 */				 } else {
/* 369 */					 entity.damageEntity(DamageSource.mobAttack(this), 10);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void k()
/*		 */	 {
/* 377 */		 this.bE = false;
/* 378 */		 if ((this.random.nextInt(2) == 0) && (!this.world.players.isEmpty())) {
/* 379 */			 this.bI = ((Entity)this.world.players.get(this.random.nextInt(this.world.players.size())));
/*		 */		 } else {
/* 381 */			 boolean flag = false;
/*		 */			 do
/*		 */			 {
/* 384 */				 this.b = 0.0D;
/* 385 */				 this.c = (70.0F + this.random.nextFloat() * 50.0F);
/* 386 */				 this.d = 0.0D;
/* 387 */				 this.b += this.random.nextFloat() * 120.0F - 60.0F;
/* 388 */				 this.d += this.random.nextFloat() * 120.0F - 60.0F;
/* 389 */				 double d0 = this.locX - this.b;
/* 390 */				 double d1 = this.locY - this.c;
/* 391 */				 double d2 = this.locZ - this.d;
/*		 */ 
/* 393 */				 flag = d0 * d0 + d1 * d1 + d2 * d2 > 100.0D;
/* 394 */			 }while (!flag);
/*		 */ 
/* 396 */			 this.bI = null;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private float b(double d0) {
/* 401 */		 return (float)MathHelper.g(d0);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(AxisAlignedBB axisalignedbb) {
/* 405 */		 int i = MathHelper.floor(axisalignedbb.a);
/* 406 */		 int j = MathHelper.floor(axisalignedbb.b);
/* 407 */		 int k = MathHelper.floor(axisalignedbb.c);
/* 408 */		 int l = MathHelper.floor(axisalignedbb.d);
/* 409 */		 int i1 = MathHelper.floor(axisalignedbb.e);
/* 410 */		 int j1 = MathHelper.floor(axisalignedbb.f);
/* 411 */		 boolean flag = false;
/* 412 */		 boolean flag1 = false;
/*		 */ 
/* 415 */		 List destroyedBlocks = new ArrayList();
/* 416 */		 CraftWorld craftWorld = this.world.getWorld();
/*		 */ 
/* 419 */		 for (int k1 = i; k1 <= l; k1++) {
/* 420 */			 for (int l1 = j; l1 <= i1; l1++) {
/* 421 */				 for (int i2 = k; i2 <= j1; i2++) {
/* 422 */					 int j2 = this.world.getTypeId(k1, l1, i2);
/*		 */ 
/* 424 */					 if (j2 != 0) {
/* 425 */						 if ((j2 != Block.OBSIDIAN.id) && (j2 != Block.WHITESTONE.id) && (j2 != Block.BEDROCK.id)) {
/* 426 */							 flag1 = true;
/*		 */ 
/* 429 */							 destroyedBlocks.add(craftWorld.getBlockAt(k1, l1, i2));
/*		 */						 }
/*		 */						 else {
/* 432 */							 flag = true;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 439 */		 if (flag1)
/*		 */		 {
/* 441 */			 org.bukkit.entity.Entity bukkitEntity = getBukkitEntity();
/* 442 */			 EntityExplodeEvent event = new EntityExplodeEvent(bukkitEntity, bukkitEntity.getLocation(), destroyedBlocks, 0.0F);
/* 443 */			 Bukkit.getPluginManager().callEvent(event);
/* 444 */			 if (event.isCancelled())
/*		 */			 {
/* 447 */				 return flag;
/*		 */			 }
/* 449 */			 for (org.bukkit.block.Block block : event.blockList()) {
/* 450 */				 craftWorld.explodeBlock(block, event.getYield());
/*		 */			 }
/*		 */ 
/* 455 */			 double d0 = axisalignedbb.a + (axisalignedbb.d - axisalignedbb.a) * this.random.nextFloat();
/* 456 */			 double d1 = axisalignedbb.b + (axisalignedbb.e - axisalignedbb.b) * this.random.nextFloat();
/* 457 */			 double d2 = axisalignedbb.c + (axisalignedbb.f - axisalignedbb.c) * this.random.nextFloat();
/*		 */ 
/* 459 */			 this.world.a("largeexplode", d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*		 */		 }
/*		 */ 
/* 462 */		 return flag;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityComplexPart entitycomplexpart, DamageSource damagesource, int i) {
/* 466 */		 if (entitycomplexpart != this.h) {
/* 467 */			 i = i / 4 + 1;
/*		 */		 }
/*		 */ 
/* 470 */		 float f = this.yaw * 3.141593F / 180.0F;
/* 471 */		 float f1 = MathHelper.sin(f);
/* 472 */		 float f2 = MathHelper.cos(f);
/*		 */ 
/* 474 */		 this.b = (this.locX + f1 * 5.0F + (this.random.nextFloat() - 0.5F) * 2.0F);
/* 475 */		 this.c = (this.locY + this.random.nextFloat() * 3.0F + 1.0D);
/* 476 */		 this.d = (this.locZ - f2 * 5.0F + (this.random.nextFloat() - 0.5F) * 2.0F);
/* 477 */		 this.bI = null;
/* 478 */		 if (((damagesource.getEntity() instanceof EntityHuman)) || (damagesource == DamageSource.EXPLOSION)) {
/* 479 */			 dealDamage(damagesource, i);
/*		 */		 }
/*		 */ 
/* 482 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 protected void aI() {
/* 486 */		 this.bG += 1;
/* 487 */		 if ((this.bG >= 180) && (this.bG <= 200)) {
/* 488 */			 float f = (this.random.nextFloat() - 0.5F) * 8.0F;
/* 489 */			 float f1 = (this.random.nextFloat() - 0.5F) * 4.0F;
/* 490 */			 float f2 = (this.random.nextFloat() - 0.5F) * 8.0F;
/*		 */ 
/* 492 */			 this.world.a("hugeexplosion", this.locX + f, this.locY + 2.0D + f1, this.locZ + f2, 0.0D, 0.0D, 0.0D);
/*		 */		 }
/*		 */ 
/* 498 */		 if ((!this.world.isStatic) && (this.bG > 150) && (this.bG % 5 == 0)) {
/* 499 */			 int i = this.expToDrop / 12;
/*		 */ 
/* 501 */			 while (i > 0) {
/* 502 */				 int j = EntityExperienceOrb.getOrbValue(i);
/* 503 */				 i -= j;
/* 504 */				 this.world.addEntity(new EntityExperienceOrb(this.world, this.locX, this.locY, this.locZ, j));
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 508 */		 move(0.0D, 0.1000000014901161D, 0.0D);
/* 509 */		 this.aq = (this.yaw += 20.0F);
/* 510 */		 if ((this.bG == 200) && (!this.world.isStatic)) {
/* 511 */			 int i = this.expToDrop - 10 * (this.expToDrop / 12);
/*		 */ 
/* 513 */			 while (i > 0) {
/* 514 */				 int j = EntityExperienceOrb.getOrbValue(i);
/* 515 */				 i -= j;
/* 516 */				 this.world.addEntity(new EntityExperienceOrb(this.world, this.locX, this.locY, this.locZ, j));
/*		 */			 }
/*		 */ 
/* 519 */			 a(MathHelper.floor(this.locX), MathHelper.floor(this.locZ));
/* 520 */			 die();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void a(int i, int j) {
/* 525 */		 byte b0 = 64;
/*		 */ 
/* 527 */		 BlockEnderPortal.a = true;
/* 528 */		 byte b1 = 4;
/*		 */ 
/* 531 */		 BlockStateListPopulator world = new BlockStateListPopulator(this.world.getWorld());
/*		 */ 
/* 533 */		 for (int k = b0 - 1; k <= b0 + 32; k++) {
/* 534 */			 for (int l = i - b1; l <= i + b1; l++) {
/* 535 */				 for (int i1 = j - b1; i1 <= j + b1; i1++) {
/* 536 */					 double d0 = l - i;
/* 537 */					 double d1 = i1 - j;
/* 538 */					 double d2 = d0 * d0 + d1 * d1;
/*		 */ 
/* 540 */					 if (d2 <= (b1 - 0.5D) * (b1 - 0.5D)) {
/* 541 */						 if (k < b0) {
/* 542 */							 if (d2 <= (b1 - 1 - 0.5D) * (b1 - 1 - 0.5D))
/* 543 */								 world.setTypeId(l, k, i1, Block.BEDROCK.id);
/*		 */						 }
/* 545 */						 else if (k > b0)
/* 546 */							 world.setTypeId(l, k, i1, 0);
/* 547 */						 else if (d2 > (b1 - 1 - 0.5D) * (b1 - 1 - 0.5D))
/* 548 */							 world.setTypeId(l, k, i1, Block.BEDROCK.id);
/*		 */						 else {
/* 550 */							 world.setTypeId(l, k, i1, Block.ENDER_PORTAL.id);
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 557 */		 world.setTypeId(i, b0 + 0, j, Block.BEDROCK.id);
/* 558 */		 world.setTypeId(i, b0 + 1, j, Block.BEDROCK.id);
/* 559 */		 world.setTypeId(i, b0 + 2, j, Block.BEDROCK.id);
/* 560 */		 world.setTypeId(i - 1, b0 + 2, j, Block.TORCH.id);
/* 561 */		 world.setTypeId(i + 1, b0 + 2, j, Block.TORCH.id);
/* 562 */		 world.setTypeId(i, b0 + 2, j - 1, Block.TORCH.id);
/* 563 */		 world.setTypeId(i, b0 + 2, j + 1, Block.TORCH.id);
/* 564 */		 world.setTypeId(i, b0 + 3, j, Block.BEDROCK.id);
/* 565 */		 world.setTypeId(i, b0 + 4, j, Block.DRAGON_EGG.id);
/*		 */ 
/* 567 */		 EntityCreatePortalEvent event = new EntityCreatePortalEvent((LivingEntity)getBukkitEntity(), Collections.unmodifiableList(world.getList()), PortalType.ENDER);
/* 568 */		 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 570 */		 if (!event.isCancelled()) {
/* 571 */			 for (BlockState state : event.getBlocks())
/* 572 */				 state.update(true);
/*		 */		 }
/*		 */		 else
/* 575 */			 for (BlockState state : event.getBlocks()) {
/* 576 */				 packet = new Packet53BlockChange(state.getX(), state.getY(), state.getZ(), this.world);
/* 577 */				 for (it = this.world.players.iterator(); it.hasNext(); ) {
/* 578 */					 EntityHuman entity = (EntityHuman)it.next();
/* 579 */					 if ((entity instanceof EntityPlayer))
/* 580 */						 ((EntityPlayer)entity).netServerHandler.sendPacket(packet);
/*		 */				 }
/*		 */			 }
/*		 */		 Packet53BlockChange packet;
/*		 */		 Iterator it;
/* 587 */		 BlockEnderPortal.a = false;
/*		 */	 }
/*		 */	 protected void bb() {
/*		 */	 }
/*		 */ 
/*		 */	 public Entity[] al() {
/* 593 */		 return this.children;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean L() {
/* 597 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int getExpReward()
/*		 */	 {
/* 604 */		 return 12000;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityEnderDragon
 * JD-Core Version:		0.6.0
 */