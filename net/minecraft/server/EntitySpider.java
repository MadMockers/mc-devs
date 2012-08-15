/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.entity.CraftEntity;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.entity.EntityTargetEvent;
/*		 */ import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
/*		 */ import org.bukkit.inventory.ItemStack;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntitySpider extends EntityMonster
/*		 */ {
/*		 */	 public EntitySpider(World world)
/*		 */	 {
/*	 8 */		 super(world);
/*	 9 */		 this.texture = "/mob/spider.png";
/*	10 */		 a(1.4F, 0.9F);
/*	11 */		 this.bw = 0.8F;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	15 */		 super.a();
/*	16 */		 this.datawatcher.a(16, new Byte(0));
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/*	20 */		 super.h_();
/*	21 */		 if (!this.world.isStatic)
/*	22 */			 e(this.positionChanged);
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth()
/*		 */	 {
/*	27 */		 return 16;
/*		 */	 }
/*		 */ 
/*		 */	 public double X() {
/*	31 */		 return this.length * 0.75D - 0.5D;
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean e_() {
/*	35 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected Entity findTarget() {
/*	39 */		 float f = c(1.0F);
/*		 */ 
/*	41 */		 if (f < 0.5F) {
/*	42 */			 double d0 = 16.0D;
/*		 */ 
/*	44 */			 return this.world.findNearbyVulnerablePlayer(this, d0);
/*		 */		 }
/*	46 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ()
/*		 */	 {
/*	51 */		 return "mob.spider";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/*	55 */		 return "mob.spider";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/*	59 */		 return "mob.spiderdeath";
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(Entity entity, float f) {
/*	63 */		 float f1 = c(1.0F);
/*		 */ 
/*	65 */		 if ((f1 > 0.5F) && (this.random.nextInt(100) == 0))
/*		 */		 {
/*	67 */			 EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), null, EntityTargetEvent.TargetReason.FORGOT_TARGET);
/*	68 */			 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	70 */			 if (!event.isCancelled()) {
/*	71 */				 if (event.getTarget() == null)
/*	72 */					 this.target = null;
/*		 */				 else {
/*	74 */					 this.target = ((CraftEntity)event.getTarget()).getHandle();
/*		 */				 }
/*	76 */				 return;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*	80 */		 else if ((f > 2.0F) && (f < 6.0F) && (this.random.nextInt(10) == 0)) {
/*	81 */			 if (this.onGround) {
/*	82 */				 double d0 = entity.locX - this.locX;
/*	83 */				 double d1 = entity.locZ - this.locZ;
/*	84 */				 float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1);
/*		 */ 
/*	86 */				 this.motX = (d0 / f2 * 0.5D * 0.800000011920929D + this.motX * 0.2000000029802322D);
/*	87 */				 this.motZ = (d1 / f2 * 0.5D * 0.800000011920929D + this.motZ * 0.2000000029802322D);
/*	88 */				 this.motY = 0.4000000059604645D;
/*		 */			 }
/*		 */		 } else {
/*	91 */			 super.a(entity, f);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId()
/*		 */	 {
/*	97 */		 return Item.STRING.id;
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i)
/*		 */	 {
/* 102 */		 List loot = new ArrayList();
/*		 */ 
/* 104 */		 int k = this.random.nextInt(3);
/*		 */ 
/* 106 */		 if (i > 0) {
/* 107 */			 k += this.random.nextInt(i + 1);
/*		 */		 }
/*		 */ 
/* 110 */		 if (k > 0) {
/* 111 */			 loot.add(new ItemStack(Item.STRING.id, k));
/*		 */		 }
/*		 */ 
/* 114 */		 if ((flag) && ((this.random.nextInt(3) == 0) || (this.random.nextInt(1 + i) > 0))) {
/* 115 */			 loot.add(new ItemStack(Item.SPIDER_EYE.id, 1));
/*		 */		 }
/*		 */ 
/* 118 */		 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean f_()
/*		 */	 {
/* 123 */		 return p();
/*		 */	 }
/*		 */	 public void aj() {
/*		 */	 }
/*		 */ 
/*		 */	 public EnumMonsterType getMonsterType() {
/* 129 */		 return EnumMonsterType.ARTHROPOD;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean e(MobEffect mobeffect) {
/* 133 */		 return mobeffect.getEffectId() == MobEffectList.POISON.id ? false : super.e(mobeffect);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean p() {
/* 137 */		 return (this.datawatcher.getByte(16) & 0x1) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void e(boolean flag) {
/* 141 */		 byte b0 = this.datawatcher.getByte(16);
/*		 */ 
/* 143 */		 if (flag)
/* 144 */			 b0 = (byte)(b0 | 0x1);
/*		 */		 else {
/* 146 */			 b0 = (byte)(b0 & 0xFFFFFFFE);
/*		 */		 }
/*		 */ 
/* 149 */		 this.datawatcher.watch(16, Byte.valueOf(b0));
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntitySpider
 * JD-Core Version:		0.6.0
 */