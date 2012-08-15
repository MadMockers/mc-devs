/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import org.bukkit.craftbukkit.entity.CraftLivingEntity;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.entity.LivingEntity;
/*		 */ import org.bukkit.event.entity.PotionSplashEvent;
/*		 */ 
/*		 */ public class EntityPotion extends EntityProjectile
/*		 */ {
/*		 */	 private int d;
/*		 */ 
/*		 */	 public EntityPotion(World world)
/*		 */	 {
/*	18 */		 super(world);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityPotion(World world, EntityLiving entityliving, int i) {
/*	22 */		 super(world, entityliving);
/*	23 */		 this.d = i;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityPotion(World world, double d0, double d1, double d2, int i) {
/*	27 */		 super(world, d0, d1, d2);
/*	28 */		 this.d = i;
/*		 */	 }
/*		 */ 
/*		 */	 protected float h() {
/*	32 */		 return 0.05F;
/*		 */	 }
/*		 */ 
/*		 */	 protected float d() {
/*	36 */		 return 0.5F;
/*		 */	 }
/*		 */ 
/*		 */	 protected float g() {
/*	40 */		 return -20.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public int getPotionValue() {
/*	44 */		 return this.d;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(MovingObjectPosition movingobjectposition) {
/*	48 */		 if (!this.world.isStatic) {
/*	49 */			 List list = Item.POTION.f(this.d);
/*		 */			 PotionSplashEvent event;
/*	51 */			 if ((list != null) && (!list.isEmpty())) {
/*	52 */				 AxisAlignedBB axisalignedbb = this.boundingBox.grow(4.0D, 2.0D, 4.0D);
/*	53 */				 List list1 = this.world.a(EntityLiving.class, axisalignedbb);
/*		 */ 
/*	55 */				 if ((list1 != null) && (!list1.isEmpty())) {
/*	56 */					 Iterator iterator = list1.iterator();
/*		 */ 
/*	59 */					 HashMap affected = new HashMap();
/*		 */ 
/*	61 */					 while (iterator.hasNext()) {
/*	62 */						 EntityLiving entityliving = (EntityLiving)iterator.next();
/*	63 */						 double d0 = e(entityliving);
/*		 */ 
/*	65 */						 if (d0 < 16.0D) {
/*	66 */							 double d1 = 1.0D - Math.sqrt(d0) / 4.0D;
/*		 */ 
/*	68 */							 if (entityliving == movingobjectposition.entity) {
/*	69 */								 d1 = 1.0D;
/*		 */							 }
/*		 */ 
/*	73 */							 affected.put((LivingEntity)entityliving.getBukkitEntity(), Double.valueOf(d1));
/*		 */						 }
/*		 */					 }
/*		 */ 
/*	77 */					 event = CraftEventFactory.callPotionSplashEvent(this, affected);
/*	78 */					 if (!event.isCancelled()) {
/*	79 */						 for (LivingEntity victim : event.getAffectedEntities()) {
/*	80 */							 if (!(victim instanceof CraftLivingEntity))
/*		 */							 {
/*		 */								 continue;
/*		 */							 }
/*	84 */							 EntityLiving entityliving = ((CraftLivingEntity)victim).getHandle();
/*	85 */							 double d1 = event.getIntensity(victim);
/*		 */ 
/*	88 */							 Iterator iterator1 = list.iterator();
/*		 */ 
/*	90 */							 while (iterator1.hasNext()) {
/*	91 */								 MobEffect mobeffect = (MobEffect)iterator1.next();
/*	92 */								 int i = mobeffect.getEffectId();
/*		 */ 
/*	95 */								 if ((!this.world.pvpMode) && ((entityliving instanceof EntityPlayer)) && (entityliving != this.shooter) && (
/*	97 */									 (i == 2) || (i == 4) || (i == 7) || (i == 15) || (i == 17) || (i == 18) || (i == 19)))
/*		 */								 {
/*		 */									 continue;
/*		 */								 }
/* 101 */								 if (MobEffectList.byId[i].isInstant())
/*		 */								 {
/* 103 */									 MobEffectList.byId[i].applyInstantEffect(this.shooter, entityliving, mobeffect.getAmplifier(), d1, this);
/*		 */								 } else {
/* 105 */									 int j = (int)(d1 * mobeffect.getDuration() + 0.5D);
/*		 */ 
/* 107 */									 if (j > 20) {
/* 108 */										 entityliving.addEffect(new MobEffect(i, j, mobeffect.getAmplifier()));
/*		 */									 }
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 117 */			 this.world.triggerEffect(2002, (int)Math.round(this.locX), (int)Math.round(this.locY), (int)Math.round(this.locZ), this.d);
/* 118 */			 die();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/* 123 */		 super.a(nbttagcompound);
/* 124 */		 this.d = nbttagcompound.getInt("potionValue");
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/* 128 */		 super.b(nbttagcompound);
/* 129 */		 nbttagcompound.setInt("potionValue", this.d);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityPotion
 * JD-Core Version:		0.6.0
 */