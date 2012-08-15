/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.entity.EntityTameEvent;
/*		 */ 
/*		 */ public class EntityOcelot extends EntityTameableAnimal
/*		 */ {
/*		 */	 private PathfinderGoalTempt e;
/*		 */ 
/*		 */	 public EntityOcelot(World world)
/*		 */	 {
/*	 8 */		 super(world);
/*	 9 */		 this.texture = "/mob/ozelot.png";
/*	10 */		 a(0.6F, 0.8F);
/*	11 */		 getNavigation().a(true);
/*	12 */		 this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*	13 */		 this.goalSelector.a(2, this.d);
/*	14 */		 this.goalSelector.a(3, this.e = new PathfinderGoalTempt(this, 0.18F, Item.RAW_FISH.id, true));
/*	15 */		 this.goalSelector.a(4, new PathfinderGoalAvoidPlayer(this, EntityHuman.class, 16.0F, 0.23F, 0.4F));
/*	16 */		 this.goalSelector.a(5, new PathfinderGoalFollowOwner(this, 0.3F, 10.0F, 5.0F));
/*	17 */		 this.goalSelector.a(6, new PathfinderGoalJumpOnBlock(this, 0.4F));
/*	18 */		 this.goalSelector.a(7, new PathfinderGoalLeapAtTarget(this, 0.3F));
/*	19 */		 this.goalSelector.a(8, new PathfinderGoalOzelotAttack(this));
/*	20 */		 this.goalSelector.a(9, new PathfinderGoalBreed(this, 0.23F));
/*	21 */		 this.goalSelector.a(10, new PathfinderGoalRandomStroll(this, 0.23F));
/*	22 */		 this.goalSelector.a(11, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 10.0F));
/*	23 */		 this.targetSelector.a(1, new PathfinderGoalRandomTargetNonTamed(this, EntityChicken.class, 14.0F, 750, false));
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	27 */		 super.a();
/*	28 */		 this.datawatcher.a(18, Byte.valueOf(0));
/*		 */	 }
/*		 */ 
/*		 */	 public void bd() {
/*	32 */		 if (getControllerMove().a()) {
/*	33 */			 float f = getControllerMove().b();
/*		 */ 
/*	35 */			 if (f == 0.18F) {
/*	36 */				 setSneaking(true);
/*	37 */				 setSprinting(false);
/*	38 */			 } else if (f == 0.4F) {
/*	39 */				 setSneaking(false);
/*	40 */				 setSprinting(true);
/*		 */			 } else {
/*	42 */				 setSneaking(false);
/*	43 */				 setSprinting(false);
/*		 */			 }
/*		 */		 } else {
/*	46 */			 setSneaking(false);
/*	47 */			 setSprinting(false);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean ba() {
/*	52 */		 return !isTamed();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean aV() {
/*	56 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	60 */		 return 10;
/*		 */	 }
/*		 */	 protected void a(float f) {
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/*	66 */		 super.b(nbttagcompound);
/*	67 */		 nbttagcompound.setInt("CatType", getCatType());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/*	71 */		 super.a(nbttagcompound);
/*	72 */		 setCatType(nbttagcompound.getInt("CatType"));
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ() {
/*	76 */		 return isTamed() ? "mob.cat.meow" : this.random.nextInt(4) == 0 ? "mob.cat.purreow" : s() ? "mob.cat.purr" : "";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/*	80 */		 return "mob.cat.hitt";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/*	84 */		 return "mob.cat.hitt";
/*		 */	 }
/*		 */ 
/*		 */	 protected float aP() {
/*	88 */		 return 0.4F;
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId() {
/*	92 */		 return Item.LEATHER.id;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean k(Entity entity) {
/*	96 */		 return entity.damageEntity(DamageSource.mobAttack(this), 3);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i) {
/* 100 */		 this.d.a(false);
/* 101 */		 return super.damageEntity(damagesource, i);
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i) {
/* 105 */		 CraftEventFactory.callEntityDeathEvent(this);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman entityhuman) {
/* 109 */		 ItemStack itemstack = entityhuman.inventory.getItemInHand();
/*		 */ 
/* 111 */		 if (isTamed()) {
/* 112 */			 if ((entityhuman.name.equalsIgnoreCase(getOwnerName())) && (!this.world.isStatic) && (!b(itemstack)))
/* 113 */				 this.d.a(!isSitting());
/*		 */		 }
/* 115 */		 else if ((this.e.f()) && (itemstack != null) && (itemstack.id == Item.RAW_FISH.id) && (entityhuman.e(this) < 9.0D)) {
/* 116 */			 if (!entityhuman.abilities.canInstantlyBuild) {
/* 117 */				 itemstack.count -= 1;
/*		 */			 }
/*		 */ 
/* 120 */			 if (itemstack.count <= 0) {
/* 121 */				 entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack)null);
/*		 */			 }
/*		 */ 
/* 124 */			 if (!this.world.isStatic)
/*		 */			 {
/* 126 */				 if ((this.random.nextInt(3) == 0) && (!CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled())) {
/* 127 */					 setTamed(true);
/* 128 */					 setCatType(1 + this.world.random.nextInt(3));
/* 129 */					 setOwnerName(entityhuman.name);
/* 130 */					 e(true);
/* 131 */					 this.d.a(true);
/* 132 */					 this.world.broadcastEntityEffect(this, 7);
/*		 */				 } else {
/* 134 */					 e(false);
/* 135 */					 this.world.broadcastEntityEffect(this, 6);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 139 */			 return true;
/*		 */		 }
/*		 */ 
/* 142 */		 return super.c(entityhuman);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityAnimal createChild(EntityAnimal entityanimal) {
/* 146 */		 EntityOcelot entityocelot = new EntityOcelot(this.world);
/*		 */ 
/* 148 */		 if (isTamed()) {
/* 149 */			 entityocelot.setOwnerName(getOwnerName());
/* 150 */			 entityocelot.setTamed(true);
/* 151 */			 entityocelot.setCatType(getCatType());
/*		 */		 }
/*		 */ 
/* 154 */		 return entityocelot;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b(ItemStack itemstack) {
/* 158 */		 return (itemstack != null) && (itemstack.id == Item.RAW_FISH.id);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean mate(EntityAnimal entityanimal) {
/* 162 */		 if (entityanimal == this)
/* 163 */			 return false;
/* 164 */		 if (!isTamed())
/* 165 */			 return false;
/* 166 */		 if (!(entityanimal instanceof EntityOcelot)) {
/* 167 */			 return false;
/*		 */		 }
/* 169 */		 EntityOcelot entityocelot = (EntityOcelot)entityanimal;
/*		 */ 
/* 171 */		 return entityocelot.isTamed();
/*		 */	 }
/*		 */ 
/*		 */	 public int getCatType()
/*		 */	 {
/* 176 */		 return this.datawatcher.getByte(18);
/*		 */	 }
/*		 */ 
/*		 */	 public void setCatType(int i) {
/* 180 */		 this.datawatcher.watch(18, Byte.valueOf((byte)i));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSpawn() {
/* 184 */		 if (this.world.random.nextInt(3) == 0) {
/* 185 */			 return false;
/*		 */		 }
/* 187 */		 if ((this.world.b(this.boundingBox)) && (this.world.getCubes(this, this.boundingBox).isEmpty()) && (!this.world.containsLiquid(this.boundingBox))) {
/* 188 */			 int i = MathHelper.floor(this.locX);
/* 189 */			 int j = MathHelper.floor(this.boundingBox.b);
/* 190 */			 int k = MathHelper.floor(this.locZ);
/*		 */ 
/* 192 */			 if (j < 63) {
/* 193 */				 return false;
/*		 */			 }
/*		 */ 
/* 196 */			 int l = this.world.getTypeId(i, j - 1, k);
/*		 */ 
/* 198 */			 if ((l == Block.GRASS.id) || (l == Block.LEAVES.id)) {
/* 199 */				 return true;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 203 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public String getLocalizedName()
/*		 */	 {
/* 208 */		 return isTamed() ? "entity.Cat.name" : super.getLocalizedName();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityOcelot
 * JD-Core Version:		0.6.0
 */