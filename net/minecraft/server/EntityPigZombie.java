/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Iterator;
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
/*		 */ public class EntityPigZombie extends EntityZombie
/*		 */ {
/*	13 */	 public int angerLevel = 0;
/*	14 */	 private int soundDelay = 0;
/*	15 */	 private static final ItemStack g = new ItemStack(Item.GOLD_SWORD, 1);
/*		 */ 
/*		 */	 public EntityPigZombie(World world) {
/*	18 */		 super(world);
/*	19 */		 this.texture = "/mob/pigzombie.png";
/*	20 */		 this.bw = 0.5F;
/*	21 */		 this.damage = 5;
/*	22 */		 this.fireProof = true;
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean aV() {
/*	26 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/*	30 */		 this.bw = (this.target != null ? 0.95F : 0.5F);
/*	31 */		 if ((this.soundDelay > 0) && (--this.soundDelay == 0)) {
/*	32 */			 this.world.makeSound(this, "mob.zombiepig.zpigangry", aP() * 2.0F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
/*		 */		 }
/*		 */ 
/*	35 */		 super.h_();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSpawn() {
/*	39 */		 return (this.world.difficulty > 0) && (this.world.b(this.boundingBox)) && (this.world.getCubes(this, this.boundingBox).isEmpty()) && (!this.world.containsLiquid(this.boundingBox));
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/*	43 */		 super.b(nbttagcompound);
/*	44 */		 nbttagcompound.setShort("Anger", (short)this.angerLevel);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/*	48 */		 super.a(nbttagcompound);
/*	49 */		 this.angerLevel = nbttagcompound.getShort("Anger");
/*		 */	 }
/*		 */ 
/*		 */	 protected Entity findTarget() {
/*	53 */		 return this.angerLevel == 0 ? null : super.findTarget();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i) {
/*	57 */		 Entity entity = damagesource.getEntity();
/*		 */ 
/*	59 */		 if ((entity instanceof EntityHuman)) {
/*	60 */			 List list = this.world.getEntities(this, this.boundingBox.grow(32.0D, 32.0D, 32.0D));
/*	61 */			 Iterator iterator = list.iterator();
/*		 */ 
/*	63 */			 while (iterator.hasNext()) {
/*	64 */				 Entity entity1 = (Entity)iterator.next();
/*		 */ 
/*	66 */				 if ((entity1 instanceof EntityPigZombie)) {
/*	67 */					 EntityPigZombie entitypigzombie = (EntityPigZombie)entity1;
/*		 */ 
/*	69 */					 entitypigzombie.c(entity);
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	73 */			 c(entity);
/*		 */		 }
/*		 */ 
/*	76 */		 return super.damageEntity(damagesource, i);
/*		 */	 }
/*		 */ 
/*		 */	 private void c(Entity entity)
/*		 */	 {
/*	81 */		 org.bukkit.entity.Entity bukkitTarget = entity == null ? null : entity.getBukkitEntity();
/*		 */ 
/*	83 */		 EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), bukkitTarget, EntityTargetEvent.TargetReason.PIG_ZOMBIE_TARGET);
/*	84 */		 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	86 */		 if (event.isCancelled()) {
/*	87 */			 return;
/*		 */		 }
/*		 */ 
/*	90 */		 if (event.getTarget() == null) {
/*	91 */			 this.target = null;
/*	92 */			 return;
/*		 */		 }
/*	94 */		 entity = ((CraftEntity)event.getTarget()).getHandle();
/*		 */ 
/*	97 */		 this.target = entity;
/*	98 */		 this.angerLevel = (400 + this.random.nextInt(400));
/*	99 */		 this.soundDelay = this.random.nextInt(40);
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ() {
/* 103 */		 return "mob.zombiepig.zpig";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/* 107 */		 return "mob.zombiepig.zpighurt";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/* 111 */		 return "mob.zombiepig.zpigdeath";
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i)
/*		 */	 {
/* 116 */		 List loot = new ArrayList();
/* 117 */		 int j = this.random.nextInt(2 + i);
/*		 */ 
/* 119 */		 if (j > 0) {
/* 120 */			 loot.add(new CraftItemStack(Item.ROTTEN_FLESH.id, j));
/*		 */		 }
/*		 */ 
/* 123 */		 j = this.random.nextInt(2 + i);
/*		 */ 
/* 125 */		 if (j > 0) {
/* 126 */			 loot.add(new CraftItemStack(Item.GOLD_NUGGET.id, j));
/*		 */		 }
/*		 */ 
/* 130 */		 if (this.lastDamageByPlayerTime > 0) {
/* 131 */			 int k = this.random.nextInt(200) - i;
/*		 */ 
/* 133 */			 if (k < 5) {
/* 134 */				 ItemStack itemstack = l(k <= 0 ? 1 : 0);
/* 135 */				 if (itemstack != null) {
/* 136 */					 loot.add(new CraftItemStack(itemstack));
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 141 */		 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */	 }
/*		 */ 
/*		 */	 protected ItemStack l(int i)
/*		 */	 {
/* 147 */		 if (i > 0) {
/* 148 */			 ItemStack itemstack = new ItemStack(Item.GOLD_SWORD);
/*		 */ 
/* 150 */			 EnchantmentManager.a(this.random, itemstack, 5);
/* 151 */			 return itemstack;
/*		 */		 }
/* 153 */		 int j = this.random.nextInt(3);
/*		 */ 
/* 155 */		 if (j == 0)
/* 156 */			 return new ItemStack(Item.GOLD_INGOT.id, 1, 0);
/* 157 */		 if (j == 1)
/* 158 */			 return new ItemStack(Item.GOLD_SWORD.id, 1, 0);
/* 159 */		 if (j == 2) {
/* 160 */			 return new ItemStack(Item.GOLD_HELMET.id, 1, 0);
/*		 */		 }
/* 162 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId()
/*		 */	 {
/* 169 */		 return Item.ROTTEN_FLESH.id;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityPigZombie
 * JD-Core Version:		0.6.0
 */