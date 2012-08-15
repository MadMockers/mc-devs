/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.entity.CreeperPowerEvent;
/*		 */ import org.bukkit.event.entity.CreeperPowerEvent.PowerCause;
/*		 */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*		 */ import org.bukkit.inventory.ItemStack;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntityCreeper extends EntityMonster
/*		 */ {
/*		 */	 int fuseTicks;
/*		 */	 int e;
/*	12 */	 private int record = -1;
/*		 */ 
/*		 */	 public EntityCreeper(World world) {
/*	15 */		 super(world);
/*	16 */		 this.texture = "/mob/creeper.png";
/*	17 */		 this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*	18 */		 this.goalSelector.a(2, new PathfinderGoalSwell(this));
/*	19 */		 this.goalSelector.a(3, new PathfinderGoalAvoidPlayer(this, EntityOcelot.class, 6.0F, 0.25F, 0.3F));
/*	20 */		 this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 0.25F, false));
/*	21 */		 this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.2F));
/*	22 */		 this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
/*	23 */		 this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
/*	24 */		 this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 16.0F, 0, true));
/*	25 */		 this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean aV() {
/*	29 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	33 */		 return 20;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	37 */		 super.a();
/*	38 */		 this.datawatcher.a(16, Byte.valueOf(-1));
/*	39 */		 this.datawatcher.a(17, Byte.valueOf(0));
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/*	43 */		 super.b(nbttagcompound);
/*	44 */		 if (this.datawatcher.getByte(17) == 1)
/*	45 */			 nbttagcompound.setBoolean("powered", true);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound)
/*		 */	 {
/*	50 */		 super.a(nbttagcompound);
/*	51 */		 this.datawatcher.watch(17, Byte.valueOf((byte)(nbttagcompound.getBoolean("powered") ? 1 : 0)));
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/*	55 */		 if (isAlive()) {
/*	56 */			 this.e = this.fuseTicks;
/*	57 */			 int i = p();
/*		 */ 
/*	59 */			 if ((i > 0) && (this.fuseTicks == 0)) {
/*	60 */				 this.world.makeSound(this, "random.fuse", 1.0F, 0.5F);
/*		 */			 }
/*		 */ 
/*	63 */			 this.fuseTicks += i;
/*	64 */			 if (this.fuseTicks < 0) {
/*	65 */				 this.fuseTicks = 0;
/*		 */			 }
/*		 */ 
/*	68 */			 if (this.fuseTicks >= 30) {
/*	69 */				 this.fuseTicks = 30;
/*	70 */				 if (!this.world.isStatic)
/*		 */				 {
/*	72 */					 float radius = isPowered() ? 6.0F : 3.0F;
/*		 */ 
/*	74 */					 ExplosionPrimeEvent event = new ExplosionPrimeEvent(getBukkitEntity(), radius, false);
/*	75 */					 this.world.getServer().getPluginManager().callEvent(event);
/*	76 */					 if (!event.isCancelled()) {
/*	77 */						 this.world.createExplosion(this, this.locX, this.locY, this.locZ, event.getRadius(), event.getFire());
/*	78 */						 die();
/*		 */					 } else {
/*	80 */						 this.fuseTicks = 0;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/*	87 */		 super.h_();
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/*	91 */		 return "mob.creeper";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/*	95 */		 return "mob.creeperdeath";
/*		 */	 }
/*		 */ 
/*		 */	 public void die(DamageSource damagesource)
/*		 */	 {
/* 100 */		 if ((damagesource.getEntity() instanceof EntitySkeleton))
/*		 */		 {
/* 102 */			 this.record = (Item.RECORD_1.id + this.random.nextInt(10));
/*		 */		 }
/*		 */ 
/* 105 */		 super.die(damagesource);
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i)
/*		 */	 {
/* 111 */		 int j = getLootId();
/*		 */ 
/* 113 */		 List loot = new ArrayList();
/*		 */ 
/* 115 */		 if (j > 0) {
/* 116 */			 int k = this.random.nextInt(3);
/*		 */ 
/* 118 */			 if (i > 0) {
/* 119 */				 k += this.random.nextInt(i + 1);
/*		 */			 }
/*		 */ 
/* 122 */			 if (k > 0) {
/* 123 */				 loot.add(new ItemStack(j, k));
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 128 */		 if (this.record != -1) {
/* 129 */			 loot.add(new ItemStack(this.record, 1));
/* 130 */			 this.record = -1;
/*		 */		 }
/*		 */ 
/* 133 */		 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean k(Entity entity)
/*		 */	 {
/* 138 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isPowered() {
/* 142 */		 return this.datawatcher.getByte(17) == 1;
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId() {
/* 146 */		 return Item.SULPHUR.id;
/*		 */	 }
/*		 */ 
/*		 */	 public int p() {
/* 150 */		 return this.datawatcher.getByte(16);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int i) {
/* 154 */		 this.datawatcher.watch(16, Byte.valueOf((byte)i));
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityLightning entitylightning) {
/* 158 */		 super.a(entitylightning);
/*		 */ 
/* 160 */		 if (CraftEventFactory.callCreeperPowerEvent(this, entitylightning, CreeperPowerEvent.PowerCause.LIGHTNING).isCancelled()) {
/* 161 */			 return;
/*		 */		 }
/*		 */ 
/* 164 */		 setPowered(true);
/*		 */	 }
/*		 */ 
/*		 */	 public void setPowered(boolean powered) {
/* 168 */		 if (!powered)
/* 169 */			 this.datawatcher.watch(17, Byte.valueOf(0));
/*		 */		 else
/* 171 */			 this.datawatcher.watch(17, Byte.valueOf(1));
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityCreeper
 * JD-Core Version:		0.6.0
 */