/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.entity.Entity;
/*		 */ import org.bukkit.event.entity.EntityDamageEvent;
/*		 */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*		 */ import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
/*		 */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class FoodMetaData
/*		 */ {
/*	 8 */	 public int foodLevel = 20;
/*	 9 */	 public float saturationLevel = 5.0F;
/*		 */	 public float exhaustionLevel;
/*	11 */	 public int foodTickTimer = 0;
/*		 */ 
/*	13 */	 private int e = 20;
/*		 */ 
/*		 */	 public void eat(int i, float f)
/*		 */	 {
/*	18 */		 this.foodLevel = Math.min(i + this.foodLevel, 20);
/*	19 */		 this.saturationLevel = Math.min(this.saturationLevel + i * f * 2.0F, this.foodLevel);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(ItemFood itemfood) {
/*	23 */		 eat(itemfood.getNutrition(), itemfood.getSaturationModifier());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityHuman entityhuman) {
/*	27 */		 int i = entityhuman.world.difficulty;
/*		 */ 
/*	29 */		 this.e = this.foodLevel;
/*	30 */		 if (this.exhaustionLevel > 4.0F) {
/*	31 */			 this.exhaustionLevel -= 4.0F;
/*	32 */			 if (this.saturationLevel > 0.0F) {
/*	33 */				 this.saturationLevel = Math.max(this.saturationLevel - 1.0F, 0.0F);
/*	34 */			 } else if (i > 0)
/*		 */			 {
/*	36 */				 FoodLevelChangeEvent event = CraftEventFactory.callFoodLevelChangeEvent(entityhuman, Math.max(this.foodLevel - 1, 0));
/*		 */ 
/*	38 */				 if (!event.isCancelled()) {
/*	39 */					 this.foodLevel = event.getFoodLevel();
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/*	45 */		 if ((this.foodLevel >= 18) && (entityhuman.bM())) {
/*	46 */			 this.foodTickTimer += 1;
/*	47 */			 if (this.foodTickTimer >= 80)
/*		 */			 {
/*	49 */				 entityhuman.heal(1, EntityRegainHealthEvent.RegainReason.SATIATED);
/*	50 */				 this.foodTickTimer = 0;
/*		 */			 }
/*	52 */		 } else if (this.foodLevel <= 0) {
/*	53 */			 this.foodTickTimer += 1;
/*	54 */			 if (this.foodTickTimer >= 80) {
/*	55 */				 if ((entityhuman.getHealth() > 10) || (i >= 3) || ((entityhuman.getHealth() > 1) && (i >= 2)))
/*		 */				 {
/*	57 */					 EntityDamageEvent event = new EntityDamageEvent(entityhuman.getBukkitEntity(), EntityDamageEvent.DamageCause.STARVATION, 1);
/*	58 */					 entityhuman.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	60 */					 if (!event.isCancelled()) {
/*	61 */						 event.getEntity().setLastDamageCause(event);
/*	62 */						 entityhuman.damageEntity(DamageSource.STARVE, event.getDamage());
/*		 */					 }
/*		 */ 
/*		 */				 }
/*		 */ 
/*	67 */				 this.foodTickTimer = 0;
/*		 */			 }
/*		 */		 } else {
/*	70 */			 this.foodTickTimer = 0;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/*	75 */		 if (nbttagcompound.hasKey("foodLevel")) {
/*	76 */			 this.foodLevel = nbttagcompound.getInt("foodLevel");
/*	77 */			 this.foodTickTimer = nbttagcompound.getInt("foodTickTimer");
/*	78 */			 this.saturationLevel = nbttagcompound.getFloat("foodSaturationLevel");
/*	79 */			 this.exhaustionLevel = nbttagcompound.getFloat("foodExhaustionLevel");
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/*	84 */		 nbttagcompound.setInt("foodLevel", this.foodLevel);
/*	85 */		 nbttagcompound.setInt("foodTickTimer", this.foodTickTimer);
/*	86 */		 nbttagcompound.setFloat("foodSaturationLevel", this.saturationLevel);
/*	87 */		 nbttagcompound.setFloat("foodExhaustionLevel", this.exhaustionLevel);
/*		 */	 }
/*		 */ 
/*		 */	 public int a() {
/*	91 */		 return this.foodLevel;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	95 */		 return this.foodLevel < 20;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(float f) {
/*	99 */		 this.exhaustionLevel = Math.min(this.exhaustionLevel + f, 40.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public float e() {
/* 103 */		 return this.saturationLevel;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.FoodMetaData
 * JD-Core Version:		0.6.0
 */