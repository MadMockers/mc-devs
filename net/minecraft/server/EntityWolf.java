/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.entity.EntityTameEvent;
/*		 */ 
/*		 */ public class EntityWolf extends EntityTameableAnimal
/*		 */ {
/*		 */	 private float e;
/*		 */	 private float f;
/*		 */	 private boolean g;
/*		 */	 private boolean h;
/*		 */	 private float i;
/*		 */	 private float j;
/*		 */ 
/*		 */	 public EntityWolf(World world)
/*		 */	 {
/*	13 */		 super(world);
/*	14 */		 this.texture = "/mob/wolf.png";
/*	15 */		 a(0.6F, 0.8F);
/*	16 */		 this.bw = 0.3F;
/*	17 */		 getNavigation().a(true);
/*	18 */		 this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*	19 */		 this.goalSelector.a(2, this.d);
/*	20 */		 this.goalSelector.a(3, new PathfinderGoalLeapAtTarget(this, 0.4F));
/*	21 */		 this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, this.bw, true));
/*	22 */		 this.goalSelector.a(5, new PathfinderGoalFollowOwner(this, this.bw, 10.0F, 2.0F));
/*	23 */		 this.goalSelector.a(6, new PathfinderGoalBreed(this, this.bw));
/*	24 */		 this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, this.bw));
/*	25 */		 this.goalSelector.a(8, new PathfinderGoalBeg(this, 8.0F));
/*	26 */		 this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
/*	27 */		 this.goalSelector.a(9, new PathfinderGoalRandomLookaround(this));
/*	28 */		 this.targetSelector.a(1, new PathfinderGoalOwnerHurtByTarget(this));
/*	29 */		 this.targetSelector.a(2, new PathfinderGoalOwnerHurtTarget(this));
/*	30 */		 this.targetSelector.a(3, new PathfinderGoalHurtByTarget(this, true));
/*	31 */		 this.targetSelector.a(4, new PathfinderGoalRandomTargetNonTamed(this, EntitySheep.class, 16.0F, 200, false));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean aV() {
/*	35 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(EntityLiving entityliving) {
/*	39 */		 super.b(entityliving);
/*	40 */		 if ((entityliving instanceof EntityHuman))
/*	41 */			 setAngry(true);
/*		 */	 }
/*		 */ 
/*		 */	 protected void bd()
/*		 */	 {
/*	46 */		 this.datawatcher.watch(18, Integer.valueOf(getHealth()));
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	50 */		 return isTamed() ? 20 : 8;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	54 */		 super.a();
/*	55 */		 this.datawatcher.a(18, new Integer(getHealth()));
/*	56 */		 this.datawatcher.a(19, new Byte(0));
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean e_() {
/*	60 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/*	64 */		 super.b(nbttagcompound);
/*	65 */		 nbttagcompound.setBoolean("Angry", isAngry());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/*	69 */		 super.a(nbttagcompound);
/*	70 */		 setAngry(nbttagcompound.getBoolean("Angry"));
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean ba() {
/*	74 */		 return isAngry();
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ() {
/*	78 */		 return this.random.nextInt(3) == 0 ? "mob.wolf.panting" : (isTamed()) && (this.datawatcher.getInt(18) < 10) ? "mob.wolf.whine" : isAngry() ? "mob.wolf.growl" : "mob.wolf.bark";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/*	82 */		 return "mob.wolf.hurt";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/*	86 */		 return "mob.wolf.death";
/*		 */	 }
/*		 */ 
/*		 */	 protected float aP() {
/*	90 */		 return 0.4F;
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId() {
/*	94 */		 return -1;
/*		 */	 }
/*		 */ 
/*		 */	 public void d() {
/*	98 */		 super.d();
/*	99 */		 if ((!this.world.isStatic) && (this.g) && (!this.h) && (!l()) && (this.onGround)) {
/* 100 */			 this.h = true;
/* 101 */			 this.i = 0.0F;
/* 102 */			 this.j = 0.0F;
/* 103 */			 this.world.broadcastEntityEffect(this, 8);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/* 108 */		 super.h_();
/* 109 */		 this.f = this.e;
/* 110 */		 if (bv())
/* 111 */			 this.e += (1.0F - this.e) * 0.4F;
/*		 */		 else {
/* 113 */			 this.e += (0.0F - this.e) * 0.4F;
/*		 */		 }
/*		 */ 
/* 116 */		 if (bv()) {
/* 117 */			 this.bx = 10;
/*		 */		 }
/*		 */ 
/* 120 */		 if (G()) {
/* 121 */			 this.g = true;
/* 122 */			 this.h = false;
/* 123 */			 this.i = 0.0F;
/* 124 */			 this.j = 0.0F;
/* 125 */		 } else if (((this.g) || (this.h)) && (this.h)) {
/* 126 */			 if (this.i == 0.0F) {
/* 127 */				 this.world.makeSound(this, "mob.wolf.shake", aP(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*		 */			 }
/*		 */ 
/* 130 */			 this.j = this.i;
/* 131 */			 this.i += 0.05F;
/* 132 */			 if (this.j >= 2.0F) {
/* 133 */				 this.g = false;
/* 134 */				 this.h = false;
/* 135 */				 this.j = 0.0F;
/* 136 */				 this.i = 0.0F;
/*		 */			 }
/*		 */ 
/* 139 */			 if (this.i > 0.4F) {
/* 140 */				 float f = (float)this.boundingBox.b;
/* 141 */				 int i = (int)(MathHelper.sin((this.i - 0.4F) * 3.141593F) * 7.0F);
/*		 */ 
/* 143 */				 for (int j = 0; j < i; j++) {
/* 144 */					 float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
/* 145 */					 float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
/*		 */ 
/* 147 */					 this.world.a("splash", this.locX + f1, f + 0.8F, this.locZ + f2, this.motX, this.motY, this.motZ);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public float getHeadHeight() {
/* 154 */		 return this.length * 0.8F;
/*		 */	 }
/*		 */ 
/*		 */	 public int bf() {
/* 158 */		 return isSitting() ? 20 : super.bf();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i) {
/* 162 */		 Entity entity = damagesource.getEntity();
/*		 */ 
/* 164 */		 this.d.a(false);
/* 165 */		 if ((entity != null) && (!(entity instanceof EntityHuman)) && (!(entity instanceof EntityArrow))) {
/* 166 */			 i = (i + 1) / 2;
/*		 */		 }
/*		 */ 
/* 169 */		 return super.damageEntity(damagesource, i);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean k(Entity entity) {
/* 173 */		 int i = isTamed() ? 4 : 2;
/*		 */ 
/* 175 */		 return entity.damageEntity(DamageSource.mobAttack(this), i);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman entityhuman) {
/* 179 */		 ItemStack itemstack = entityhuman.inventory.getItemInHand();
/*		 */ 
/* 181 */		 if (isTamed()) {
/* 182 */			 if ((itemstack != null) && ((Item.byId[itemstack.id] instanceof ItemFood))) {
/* 183 */				 ItemFood itemfood = (ItemFood)Item.byId[itemstack.id];
/*		 */ 
/* 185 */				 if ((itemfood.h()) && (this.datawatcher.getInt(18) < 20)) {
/* 186 */					 if (!entityhuman.abilities.canInstantlyBuild) {
/* 187 */						 itemstack.count -= 1;
/*		 */					 }
/*		 */ 
/* 190 */					 heal(itemfood.getNutrition());
/* 191 */					 if (itemstack.count <= 0) {
/* 192 */						 entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack)null);
/*		 */					 }
/*		 */ 
/* 195 */					 return true;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 199 */			 if ((entityhuman.name.equalsIgnoreCase(getOwnerName())) && (!this.world.isStatic) && (!b(itemstack))) {
/* 200 */				 this.d.a(!isSitting());
/* 201 */				 this.bu = false;
/* 202 */				 setPathEntity((PathEntity)null);
/*		 */			 }
/* 204 */		 } else if ((itemstack != null) && (itemstack.id == Item.BONE.id) && (!isAngry())) {
/* 205 */			 if (!entityhuman.abilities.canInstantlyBuild) {
/* 206 */				 itemstack.count -= 1;
/*		 */			 }
/*		 */ 
/* 209 */			 if (itemstack.count <= 0) {
/* 210 */				 entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack)null);
/*		 */			 }
/*		 */ 
/* 213 */			 if (!this.world.isStatic)
/*		 */			 {
/* 215 */				 if ((this.random.nextInt(3) == 0) && (!CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled())) {
/* 216 */					 setTamed(true);
/* 217 */					 setPathEntity((PathEntity)null);
/* 218 */					 b((EntityLiving)null);
/* 219 */					 this.d.a(true);
/* 220 */					 setHealth(20);
/* 221 */					 setOwnerName(entityhuman.name);
/* 222 */					 e(true);
/* 223 */					 this.world.broadcastEntityEffect(this, 7);
/*		 */				 } else {
/* 225 */					 e(false);
/* 226 */					 this.world.broadcastEntityEffect(this, 6);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 230 */			 return true;
/*		 */		 }
/*		 */ 
/* 233 */		 return super.c(entityhuman);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b(ItemStack itemstack) {
/* 237 */		 return !(Item.byId[itemstack.id] instanceof ItemFood) ? false : itemstack == null ? false : ((ItemFood)Item.byId[itemstack.id]).h();
/*		 */	 }
/*		 */ 
/*		 */	 public int bl() {
/* 241 */		 return 8;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isAngry() {
/* 245 */		 return (this.datawatcher.getByte(16) & 0x2) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void setAngry(boolean flag) {
/* 249 */		 byte b0 = this.datawatcher.getByte(16);
/*		 */ 
/* 251 */		 if (flag)
/* 252 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(b0 | 0x2)));
/*		 */		 else
/* 254 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFD)));
/*		 */	 }
/*		 */ 
/*		 */	 public EntityAnimal createChild(EntityAnimal entityanimal)
/*		 */	 {
/* 259 */		 EntityWolf entitywolf = new EntityWolf(this.world);
/*		 */ 
/* 261 */		 entitywolf.setOwnerName(getOwnerName());
/* 262 */		 entitywolf.setTamed(true);
/* 263 */		 return entitywolf;
/*		 */	 }
/*		 */ 
/*		 */	 public void i(boolean flag) {
/* 267 */		 byte b0 = this.datawatcher.getByte(19);
/*		 */ 
/* 269 */		 if (flag)
/* 270 */			 this.datawatcher.watch(19, Byte.valueOf(1));
/*		 */		 else
/* 272 */			 this.datawatcher.watch(19, Byte.valueOf(0));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean mate(EntityAnimal entityanimal)
/*		 */	 {
/* 277 */		 if (entityanimal == this)
/* 278 */			 return false;
/* 279 */		 if (!isTamed())
/* 280 */			 return false;
/* 281 */		 if (!(entityanimal instanceof EntityWolf)) {
/* 282 */			 return false;
/*		 */		 }
/* 284 */		 EntityWolf entitywolf = (EntityWolf)entityanimal;
/*		 */ 
/* 286 */		 return entitywolf.isTamed();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean bv()
/*		 */	 {
/* 291 */		 return this.datawatcher.getByte(19) == 1;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityWolf
 * JD-Core Version:		0.6.0
 */