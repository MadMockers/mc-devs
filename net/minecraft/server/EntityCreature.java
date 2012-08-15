/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.TrigMath;
/*		 */ import org.bukkit.craftbukkit.entity.CraftEntity;
/*		 */ import org.bukkit.event.entity.EntityTargetEvent;
/*		 */ import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public abstract class EntityCreature extends EntityLiving
/*		 */ {
/*		 */	 public PathEntity pathEntity;
/*		 */	 public Entity target;
/*	12 */	 protected boolean b = false;
/*	13 */	 protected int c = 0;
/*		 */ 
/*		 */	 public EntityCreature(World world) {
/*	16 */		 super(world);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean i() {
/*	20 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected void be()
/*		 */	 {
/*	25 */		 if (this.c > 0) {
/*	26 */			 this.c -= 1;
/*		 */		 }
/*		 */ 
/*	29 */		 this.b = i();
/*	30 */		 float f = 16.0F;
/*		 */ 
/*	32 */		 if (this.target == null)
/*		 */		 {
/*	34 */			 Entity target = findTarget();
/*	35 */			 if (target != null) {
/*	36 */				 EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), target.getBukkitEntity(), EntityTargetEvent.TargetReason.CLOSEST_PLAYER);
/*	37 */				 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	39 */				 if (!event.isCancelled()) {
/*	40 */					 if (event.getTarget() == null)
/*	41 */						 this.target = null;
/*		 */					 else {
/*	43 */						 this.target = ((CraftEntity)event.getTarget()).getHandle();
/*		 */					 }
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*	49 */			 if (this.target != null)
/*	50 */				 this.pathEntity = this.world.findPath(this, this.target, f, true, false, false, true);
/*		 */		 }
/*	52 */		 else if (this.target.isAlive()) {
/*	53 */			 float f1 = this.target.d(this);
/*		 */ 
/*	55 */			 if (l(this.target))
/*	56 */				 a(this.target, f1);
/*		 */		 }
/*		 */		 else
/*		 */		 {
/*	60 */			 EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), null, EntityTargetEvent.TargetReason.TARGET_DIED);
/*	61 */			 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	63 */			 if (!event.isCancelled()) {
/*	64 */				 if (event.getTarget() == null)
/*	65 */					 this.target = null;
/*		 */				 else {
/*	67 */					 this.target = ((CraftEntity)event.getTarget()).getHandle();
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/*	74 */		 if ((!this.b) && (this.target != null) && ((this.pathEntity == null) || (this.random.nextInt(20) == 0)))
/*	75 */			 this.pathEntity = this.world.findPath(this, this.target, f, true, false, false, true);
/*	76 */		 else if ((!this.b) && (((this.pathEntity == null) && (this.random.nextInt(180) == 0)) || (((this.random.nextInt(120) == 0) || (this.c > 0)) && (this.bq < 100)))) {
/*	77 */			 j();
/*		 */		 }
/*		 */ 
/*	80 */		 int i = MathHelper.floor(this.boundingBox.b + 0.5D);
/*	81 */		 boolean flag = H();
/*	82 */		 boolean flag1 = J();
/*		 */ 
/*	84 */		 this.pitch = 0.0F;
/*	85 */		 if ((this.pathEntity != null) && (this.random.nextInt(100) != 0))
/*		 */		 {
/*	87 */			 Vec3D vec3d = this.pathEntity.a(this);
/*	88 */			 double d0 = this.width * 2.0F;
/*		 */ 
/*	90 */			 while ((vec3d != null) && (vec3d.d(this.locX, vec3d.b, this.locZ) < d0 * d0)) {
/*	91 */				 this.pathEntity.a();
/*	92 */				 if (this.pathEntity.b()) {
/*	93 */					 vec3d = null;
/*	94 */					 this.pathEntity = null; continue;
/*		 */				 }
/*	96 */				 vec3d = this.pathEntity.a(this);
/*		 */			 }
/*		 */ 
/* 100 */			 this.bu = false;
/* 101 */			 if (vec3d != null) {
/* 102 */				 double d1 = vec3d.a - this.locX;
/* 103 */				 double d2 = vec3d.c - this.locZ;
/* 104 */				 double d3 = vec3d.b - i;
/*		 */ 
/* 106 */				 float f2 = (float)(TrigMath.atan2(d2, d1) * 180.0D / 3.141592741012573D) - 90.0F;
/* 107 */				 float f3 = MathHelper.g(f2 - this.yaw);
/*		 */ 
/* 109 */				 this.bs = this.bw;
/* 110 */				 if (f3 > 30.0F) {
/* 111 */					 f3 = 30.0F;
/*		 */				 }
/*		 */ 
/* 114 */				 if (f3 < -30.0F) {
/* 115 */					 f3 = -30.0F;
/*		 */				 }
/*		 */ 
/* 118 */				 this.yaw += f3;
/* 119 */				 if ((this.b) && (this.target != null)) {
/* 120 */					 double d4 = this.target.locX - this.locX;
/* 121 */					 double d5 = this.target.locZ - this.locZ;
/* 122 */					 float f4 = this.yaw;
/*		 */ 
/* 124 */					 this.yaw = ((float)(Math.atan2(d5, d4) * 180.0D / 3.141592741012573D) - 90.0F);
/* 125 */					 f3 = (f4 - this.yaw + 90.0F) * 3.141593F / 180.0F;
/* 126 */					 this.br = (-MathHelper.sin(f3) * this.bs * 1.0F);
/* 127 */					 this.bs = (MathHelper.cos(f3) * this.bs * 1.0F);
/*		 */				 }
/*		 */ 
/* 130 */				 if (d3 > 0.0D) {
/* 131 */					 this.bu = true;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 135 */			 if (this.target != null) {
/* 136 */				 a(this.target, 30.0F, 30.0F);
/*		 */			 }
/*		 */ 
/* 139 */			 if ((this.positionChanged) && (!l())) {
/* 140 */				 this.bu = true;
/*		 */			 }
/*		 */ 
/* 143 */			 if ((this.random.nextFloat() < 0.8F) && ((flag) || (flag1))) {
/* 144 */				 this.bu = true;
/*		 */			 }
/*		 */		 }
/*		 */		 else
/*		 */		 {
/* 149 */			 super.be();
/* 150 */			 this.pathEntity = null;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void j()
/*		 */	 {
/* 156 */		 boolean flag = false;
/* 157 */		 int i = -1;
/* 158 */		 int j = -1;
/* 159 */		 int k = -1;
/* 160 */		 float f = -99999.0F;
/*		 */ 
/* 162 */		 for (int l = 0; l < 10; l++) {
/* 163 */			 int i1 = MathHelper.floor(this.locX + this.random.nextInt(13) - 6.0D);
/* 164 */			 int j1 = MathHelper.floor(this.locY + this.random.nextInt(7) - 3.0D);
/* 165 */			 int k1 = MathHelper.floor(this.locZ + this.random.nextInt(13) - 6.0D);
/* 166 */			 float f1 = a(i1, j1, k1);
/*		 */ 
/* 168 */			 if (f1 > f) {
/* 169 */				 f = f1;
/* 170 */				 i = i1;
/* 171 */				 j = j1;
/* 172 */				 k = k1;
/* 173 */				 flag = true;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 177 */		 if (flag)
/* 178 */			 this.pathEntity = this.world.a(this, i, j, k, 10.0F, true, false, false, true);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(Entity entity, float f)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public float a(int i, int j, int k)
/*		 */	 {
/* 187 */		 return 0.0F;
/*		 */	 }
/*		 */ 
/*		 */	 protected Entity findTarget() {
/* 191 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSpawn() {
/* 195 */		 int i = MathHelper.floor(this.locX);
/* 196 */		 int j = MathHelper.floor(this.boundingBox.b);
/* 197 */		 int k = MathHelper.floor(this.locZ);
/*		 */ 
/* 199 */		 return (super.canSpawn()) && (a(i, j, k) >= 0.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean l() {
/* 203 */		 return this.pathEntity != null;
/*		 */	 }
/*		 */ 
/*		 */	 public void setPathEntity(PathEntity pathentity) {
/* 207 */		 this.pathEntity = pathentity;
/*		 */	 }
/*		 */ 
/*		 */	 public Entity m() {
/* 211 */		 return this.target;
/*		 */	 }
/*		 */ 
/*		 */	 public void setTarget(Entity entity) {
/* 215 */		 this.target = entity;
/*		 */	 }
/*		 */ 
/*		 */	 protected float bs() {
/* 219 */		 if (aV()) {
/* 220 */			 return 1.0F;
/*		 */		 }
/* 222 */		 float f = super.bs();
/*		 */ 
/* 224 */		 if (this.c > 0) {
/* 225 */			 f *= 2.0F;
/*		 */		 }
/*		 */ 
/* 228 */		 return f;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityCreature
 * JD-Core Version:		0.6.0
 */