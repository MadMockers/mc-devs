/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.entity.Player;
/*		 */ import org.bukkit.event.entity.ItemDespawnEvent;
/*		 */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntityItem extends Entity
/*		 */ {
/*		 */	 public ItemStack itemStack;
/*	10 */	 public int age = 0;
/*		 */	 public int pickupDelay;
/*	12 */	 private int e = 5;
/*	13 */	 public float d = (float)(Math.random() * 3.141592653589793D * 2.0D);
/*	14 */	 private int lastTick = (int)(System.currentTimeMillis() / 50L);
/*		 */ 
/*		 */	 public EntityItem(World world, double d0, double d1, double d2, ItemStack itemstack) {
/*	17 */		 super(world);
/*	18 */		 a(0.25F, 0.25F);
/*	19 */		 this.height = (this.length / 2.0F);
/*	20 */		 setPosition(d0, d1, d2);
/*	21 */		 this.itemStack = itemstack;
/*		 */ 
/*	23 */		 if (this.itemStack == null) {
/*	24 */			 throw new IllegalArgumentException("Can't create an EntityItem for a null item");
/*		 */		 }
/*	26 */		 if (this.itemStack.count <= -1) {
/*	27 */			 this.itemStack.count = 1;
/*		 */		 }
/*		 */ 
/*	30 */		 this.yaw = (float)(Math.random() * 360.0D);
/*	31 */		 this.motX = (float)(Math.random() * 0.2000000029802322D - 0.1000000014901161D);
/*	32 */		 this.motY = 0.2000000029802322D;
/*	33 */		 this.motZ = (float)(Math.random() * 0.2000000029802322D - 0.1000000014901161D);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean e_() {
/*	37 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityItem(World world) {
/*	41 */		 super(world);
/*	42 */		 a(0.25F, 0.25F);
/*	43 */		 this.height = (this.length / 2.0F);
/*		 */	 }
/*		 */	 protected void a() {
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/*	49 */		 super.h_();
/*		 */ 
/*	51 */		 int currentTick = (int)(System.currentTimeMillis() / 50L);
/*	52 */		 this.pickupDelay -= currentTick - this.lastTick;
/*	53 */		 this.lastTick = currentTick;
/*		 */ 
/*	56 */		 this.lastX = this.locX;
/*	57 */		 this.lastY = this.locY;
/*	58 */		 this.lastZ = this.locZ;
/*	59 */		 this.motY -= 0.03999999910593033D;
/*	60 */		 i(this.locX, (this.boundingBox.b + this.boundingBox.e) / 2.0D, this.locZ);
/*	61 */		 move(this.motX, this.motY, this.motZ);
/*	62 */		 boolean flag = ((int)this.lastX != (int)this.locX) || ((int)this.lastY != (int)this.locY) || ((int)this.lastZ != (int)this.locZ);
/*		 */ 
/*	64 */		 if (flag) {
/*	65 */			 if (this.world.getMaterial(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)) == Material.LAVA) {
/*	66 */				 this.motY = 0.2000000029802322D;
/*	67 */				 this.motX = ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*	68 */				 this.motZ = ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*	69 */				 this.world.makeSound(this, "random.fizz", 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
/*		 */			 }
/*		 */ 
/*	72 */			 if (!this.world.isStatic) {
/*	73 */				 Iterator iterator = this.world.a(EntityItem.class, this.boundingBox.grow(0.5D, 0.0D, 0.5D)).iterator();
/*		 */ 
/*	75 */				 while (iterator.hasNext()) {
/*	76 */					 EntityItem entityitem = (EntityItem)iterator.next();
/*		 */ 
/*	78 */					 a(entityitem);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	83 */		 float f = 0.98F;
/*		 */ 
/*	85 */		 if (this.onGround) {
/*	86 */			 f = 0.5880001F;
/*	87 */			 int i = this.world.getTypeId(MathHelper.floor(this.locX), MathHelper.floor(this.boundingBox.b) - 1, MathHelper.floor(this.locZ));
/*		 */ 
/*	89 */			 if (i > 0) {
/*	90 */				 f = Block.byId[i].frictionFactor * 0.98F;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	94 */		 this.motX *= f;
/*	95 */		 this.motY *= 0.9800000190734863D;
/*	96 */		 this.motZ *= f;
/*	97 */		 if (this.onGround) {
/*	98 */			 this.motY *= -0.5D;
/*		 */		 }
/*		 */ 
/* 101 */		 this.age += 1;
/* 102 */		 if (this.age >= 6000)
/*		 */		 {
/* 104 */			 if (CraftEventFactory.callItemDespawnEvent(this).isCancelled()) {
/* 105 */				 this.age = 0;
/* 106 */				 return;
/*		 */			 }
/*		 */ 
/* 109 */			 die();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityItem entityitem) {
/* 114 */		 if (entityitem == this)
/* 115 */			 return false;
/* 116 */		 if ((entityitem.isAlive()) && (isAlive())) {
/* 117 */			 if (entityitem.itemStack.getItem() != this.itemStack.getItem())
/* 118 */				 return false;
/* 119 */			 if ((entityitem.itemStack.getItem().k()) && (entityitem.itemStack.getData() != this.itemStack.getData()))
/* 120 */				 return false;
/* 121 */			 if (entityitem.itemStack.count < this.itemStack.count)
/* 122 */				 return entityitem.a(this);
/* 123 */			 if (entityitem.itemStack.count + this.itemStack.count > entityitem.itemStack.getMaxStackSize()) {
/* 124 */				 return false;
/*		 */			 }
/* 126 */			 if ((entityitem.itemStack.hasEnchantments()) || (this.itemStack.hasEnchantments())) {
/* 127 */				 return false;
/*		 */			 }
/*		 */ 
/* 130 */			 entityitem.itemStack.count += this.itemStack.count;
/* 131 */			 entityitem.pickupDelay = Math.max(entityitem.pickupDelay, this.pickupDelay);
/* 132 */			 entityitem.age = Math.min(entityitem.age, this.age);
/* 133 */			 die();
/* 134 */			 return true;
/*		 */		 }
/*		 */ 
/* 137 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void d()
/*		 */	 {
/* 142 */		 this.age = 4800;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean I() {
/* 146 */		 return this.world.a(this.boundingBox, Material.WATER, this);
/*		 */	 }
/*		 */ 
/*		 */	 protected void burn(int i) {
/* 150 */		 damageEntity(DamageSource.FIRE, i);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i) {
/* 154 */		 K();
/* 155 */		 this.e -= i;
/* 156 */		 if (this.e <= 0) {
/* 157 */			 die();
/*		 */		 }
/*		 */ 
/* 160 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/* 164 */		 nbttagcompound.setShort("Health", (short)(byte)this.e);
/* 165 */		 nbttagcompound.setShort("Age", (short)this.age);
/* 166 */		 if (this.itemStack != null)
/* 167 */			 nbttagcompound.setCompound("Item", this.itemStack.save(new NBTTagCompound()));
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound)
/*		 */	 {
/* 172 */		 this.e = (nbttagcompound.getShort("Health") & 0xFF);
/* 173 */		 this.age = nbttagcompound.getShort("Age");
/* 174 */		 NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Item");
/*		 */ 
/* 176 */		 this.itemStack = ItemStack.a(nbttagcompound1);
/* 177 */		 if (this.itemStack == null)
/* 178 */			 die();
/*		 */	 }
/*		 */ 
/*		 */	 public void b_(EntityHuman entityhuman)
/*		 */	 {
/* 183 */		 if ((!this.world.isStatic) && (this.itemStack != null)) {
/* 184 */			 int i = this.itemStack.count;
/*		 */ 
/* 187 */			 int canHold = entityhuman.inventory.canHold(this.itemStack);
/* 188 */			 int remaining = this.itemStack.count - canHold;
/*		 */ 
/* 190 */			 if ((this.pickupDelay <= 0) && (canHold > 0)) {
/* 191 */				 this.itemStack.count = canHold;
/* 192 */				 PlayerPickupItemEvent event = new PlayerPickupItemEvent((Player)entityhuman.getBukkitEntity(), (org.bukkit.entity.Item)getBukkitEntity(), remaining);
/* 193 */				 this.world.getServer().getPluginManager().callEvent(event);
/* 194 */				 this.itemStack.count = (canHold + remaining);
/*		 */ 
/* 196 */				 if (event.isCancelled()) {
/* 197 */					 return;
/*		 */				 }
/*		 */ 
/* 201 */				 this.pickupDelay = 0;
/*		 */			 }
/*		 */ 
/* 205 */			 if ((this.pickupDelay == 0) && (entityhuman.inventory.pickup(this.itemStack))) {
/* 206 */				 if (this.itemStack.id == Block.LOG.id) {
/* 207 */					 entityhuman.a(AchievementList.g);
/*		 */				 }
/*		 */ 
/* 210 */				 if (this.itemStack.id == Item.LEATHER.id) {
/* 211 */					 entityhuman.a(AchievementList.t);
/*		 */				 }
/*		 */ 
/* 214 */				 if (this.itemStack.id == Item.DIAMOND.id) {
/* 215 */					 entityhuman.a(AchievementList.w);
/*		 */				 }
/*		 */ 
/* 218 */				 if (this.itemStack.id == Item.BLAZE_ROD.id) {
/* 219 */					 entityhuman.a(AchievementList.z);
/*		 */				 }
/*		 */ 
/* 222 */				 this.world.makeSound(this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 223 */				 entityhuman.receive(this, i);
/* 224 */				 if (this.itemStack.count <= 0)
/* 225 */					 die();
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public String getLocalizedName()
/*		 */	 {
/* 232 */		 if (this.itemStack == null) return LocaleI18n.get("item.unknown");
/* 233 */		 return LocaleI18n.get("item." + this.itemStack.a());
/*		 */	 }
/*		 */ 
/*		 */	 public boolean an() {
/* 237 */		 return false;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityItem
 * JD-Core Version:		0.6.0
 */