/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.entity.CraftEntity;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftItemStack;
/*		 */ import org.bukkit.event.entity.EntityTargetEvent;
/*		 */ import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntityGhast extends EntityFlying
/*		 */	 implements IMonster
/*		 */ {
/*	11 */	 public int a = 0;
/*		 */	 public double b;
/*		 */	 public double c;
/*		 */	 public double d;
/*	15 */	 private Entity target = null;
/*	16 */	 private int h = 0;
/*	17 */	 public int e = 0;
/*	18 */	 public int f = 0;
/*		 */ 
/*		 */	 public EntityGhast(World world) {
/*	21 */		 super(world);
/*	22 */		 this.texture = "/mob/ghast.png";
/*	23 */		 a(4.0F, 4.0F);
/*	24 */		 this.fireProof = true;
/*	25 */		 this.aV = 5;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i) {
/*	29 */		 if (("fireball".equals(damagesource.l())) && ((damagesource.getEntity() instanceof EntityHuman))) {
/*	30 */			 super.damageEntity(damagesource, 1000);
/*	31 */			 ((EntityHuman)damagesource.getEntity()).a(AchievementList.y);
/*	32 */			 return true;
/*		 */		 }
/*	34 */		 return super.damageEntity(damagesource, i);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a()
/*		 */	 {
/*	39 */		 super.a();
/*	40 */		 this.datawatcher.a(16, Byte.valueOf(0));
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	44 */		 return 10;
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/*	48 */		 super.h_();
/*	49 */		 byte b0 = this.datawatcher.getByte(16);
/*		 */ 
/*	51 */		 this.texture = (b0 == 1 ? "/mob/ghast_fire.png" : "/mob/ghast.png");
/*		 */	 }
/*		 */ 
/*		 */	 protected void be() {
/*	55 */		 if ((!this.world.isStatic) && (this.world.difficulty == 0)) {
/*	56 */			 die();
/*		 */		 }
/*		 */ 
/*	59 */		 bb();
/*	60 */		 this.e = this.f;
/*	61 */		 double d0 = this.b - this.locX;
/*	62 */		 double d1 = this.c - this.locY;
/*	63 */		 double d2 = this.d - this.locZ;
/*	64 */		 double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*		 */ 
/*	66 */		 if ((d3 < 1.0D) || (d3 > 3600.0D)) {
/*	67 */			 this.b = (this.locX + (this.random.nextFloat() * 2.0F - 1.0F) * 16.0F);
/*	68 */			 this.c = (this.locY + (this.random.nextFloat() * 2.0F - 1.0F) * 16.0F);
/*	69 */			 this.d = (this.locZ + (this.random.nextFloat() * 2.0F - 1.0F) * 16.0F);
/*		 */		 }
/*		 */ 
/*	72 */		 if (this.a-- <= 0) {
/*	73 */			 this.a += this.random.nextInt(5) + 2;
/*	74 */			 d3 = MathHelper.sqrt(d3);
/*	75 */			 if (a(this.b, this.c, this.d, d3)) {
/*	76 */				 this.motX += d0 / d3 * 0.1D;
/*	77 */				 this.motY += d1 / d3 * 0.1D;
/*	78 */				 this.motZ += d2 / d3 * 0.1D;
/*		 */			 } else {
/*	80 */				 this.b = this.locX;
/*	81 */				 this.c = this.locY;
/*	82 */				 this.d = this.locZ;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	86 */		 if ((this.target != null) && (this.target.dead))
/*		 */		 {
/*	88 */			 EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), null, EntityTargetEvent.TargetReason.TARGET_DIED);
/*	89 */			 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	91 */			 if (!event.isCancelled()) {
/*	92 */				 if (event.getTarget() == null)
/*	93 */					 this.target = null;
/*		 */				 else {
/*	95 */					 this.target = ((CraftEntity)event.getTarget()).getHandle();
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 101 */		 if ((this.target == null) || (this.h-- <= 0))
/*		 */		 {
/* 103 */			 Entity target = this.world.findNearbyVulnerablePlayer(this, 100.0D);
/* 104 */			 if (target != null) {
/* 105 */				 EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), target.getBukkitEntity(), EntityTargetEvent.TargetReason.CLOSEST_PLAYER);
/* 106 */				 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 108 */				 if (!event.isCancelled()) {
/* 109 */					 if (event.getTarget() == null)
/* 110 */						 this.target = null;
/*		 */					 else {
/* 112 */						 this.target = ((CraftEntity)event.getTarget()).getHandle();
/*		 */					 }
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 118 */			 if (this.target != null) {
/* 119 */				 this.h = 20;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 123 */		 double d4 = 64.0D;
/*		 */ 
/* 125 */		 if ((this.target != null) && (this.target.e(this) < d4 * d4)) {
/* 126 */			 double d5 = this.target.locX - this.locX;
/* 127 */			 double d6 = this.target.boundingBox.b + this.target.length / 2.0F - (this.locY + this.length / 2.0F);
/* 128 */			 double d7 = this.target.locZ - this.locZ;
/*		 */ 
/* 130 */			 this.aq = (this.yaw = -(float)Math.atan2(d5, d7) * 180.0F / 3.141593F);
/* 131 */			 if (l(this.target)) {
/* 132 */				 if (this.f == 10) {
/* 133 */					 this.world.a((EntityHuman)null, 1007, (int)this.locX, (int)this.locY, (int)this.locZ, 0);
/*		 */				 }
/*		 */ 
/* 136 */				 this.f += 1;
/* 137 */				 if (this.f == 20) {
/* 138 */					 this.world.a((EntityHuman)null, 1008, (int)this.locX, (int)this.locY, (int)this.locZ, 0);
/* 139 */					 EntityFireball entityfireball = new EntityFireball(this.world, this, d5, d6, d7);
/* 140 */					 double d8 = 4.0D;
/* 141 */					 Vec3D vec3d = i(1.0F);
/*		 */ 
/* 143 */					 entityfireball.locX = (this.locX + vec3d.a * d8);
/* 144 */					 entityfireball.locY = (this.locY + this.length / 2.0F + 0.5D);
/* 145 */					 entityfireball.locZ = (this.locZ + vec3d.c * d8);
/* 146 */					 this.world.addEntity(entityfireball);
/* 147 */					 this.f = -40;
/*		 */				 }
/* 149 */			 } else if (this.f > 0) {
/* 150 */				 this.f -= 1;
/*		 */			 }
/*		 */		 } else {
/* 153 */			 this.aq = (this.yaw = -(float)Math.atan2(this.motX, this.motZ) * 180.0F / 3.141593F);
/* 154 */			 if (this.f > 0) {
/* 155 */				 this.f -= 1;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 159 */		 if (!this.world.isStatic) {
/* 160 */			 byte b0 = this.datawatcher.getByte(16);
/* 161 */			 byte b1 = (byte)(this.f > 10 ? 1 : 0);
/*		 */ 
/* 163 */			 if (b0 != b1)
/* 164 */				 this.datawatcher.watch(16, Byte.valueOf(b1));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(double d0, double d1, double d2, double d3)
/*		 */	 {
/* 170 */		 double d4 = (this.b - this.locX) / d3;
/* 171 */		 double d5 = (this.c - this.locY) / d3;
/* 172 */		 double d6 = (this.d - this.locZ) / d3;
/* 173 */		 AxisAlignedBB axisalignedbb = this.boundingBox.clone();
/*		 */ 
/* 175 */		 for (int i = 1; i < d3; i++) {
/* 176 */			 axisalignedbb.d(d4, d5, d6);
/* 177 */			 if (!this.world.getCubes(this, axisalignedbb).isEmpty()) {
/* 178 */				 return false;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 182 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ() {
/* 186 */		 return "mob.ghast.moan";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/* 190 */		 return "mob.ghast.scream";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/* 194 */		 return "mob.ghast.death";
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId() {
/* 198 */		 return Item.SULPHUR.id;
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i)
/*		 */	 {
/* 203 */		 List loot = new ArrayList();
/* 204 */		 int j = this.random.nextInt(2) + this.random.nextInt(1 + i);
/*		 */ 
/* 208 */		 if (j > 0) {
/* 209 */			 loot.add(new CraftItemStack(Item.GHAST_TEAR.id, j));
/*		 */		 }
/*		 */ 
/* 212 */		 j = this.random.nextInt(3) + this.random.nextInt(1 + i);
/*		 */ 
/* 214 */		 if (j > 0) {
/* 215 */			 loot.add(new CraftItemStack(Item.SULPHUR.id, j));
/*		 */		 }
/*		 */ 
/* 218 */		 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */	 }
/*		 */ 
/*		 */	 protected float aP()
/*		 */	 {
/* 223 */		 return 10.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSpawn() {
/* 227 */		 return (this.random.nextInt(20) == 0) && (super.canSpawn()) && (this.world.difficulty > 0);
/*		 */	 }
/*		 */ 
/*		 */	 public int bl() {
/* 231 */		 return 1;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityGhast
 * JD-Core Version:		0.6.0
 */