/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.Iterator;
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ import org.bukkit.Bukkit;
/*			*/ import org.bukkit.craftbukkit.CraftServer;
/*			*/ import org.bukkit.craftbukkit.CraftWorld;
/*			*/ import org.bukkit.craftbukkit.TrigMath;
/*			*/ import org.bukkit.craftbukkit.entity.CraftItem;
/*			*/ import org.bukkit.entity.HumanEntity;
/*			*/ import org.bukkit.entity.Player;
/*			*/ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*			*/ import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
/*			*/ import org.bukkit.event.player.PlayerBedEnterEvent;
/*			*/ import org.bukkit.event.player.PlayerBedLeaveEvent;
/*			*/ import org.bukkit.event.player.PlayerDropItemEvent;
/*			*/ import org.bukkit.plugin.PluginManager;
/*			*/ 
/*			*/ public abstract class EntityHuman extends EntityLiving
/*			*/	 implements ICommandListener
/*			*/ {
/*	 18 */	 public PlayerInventory inventory = new PlayerInventory(this);
/*	 19 */	 private InventoryEnderChest enderChest = new InventoryEnderChest();
/*			*/	 public Container defaultContainer;
/*			*/	 public Container activeContainer;
/*	 22 */	 protected FoodMetaData foodData = new FoodMetaData();
/*	 23 */	 protected int bC = 0;
/*	 24 */	 public byte bD = 0;
/*	 25 */	 public int bE = 0;
/*			*/	 public float bF;
/*			*/	 public float bG;
/*	 28 */	 public boolean bH = false;
/*	 29 */	 public int bI = 0;
/*			*/	 public String name;
/*			*/	 public int dimension;
/*	 32 */	 public int bL = 0;
/*			*/	 public double bM;
/*			*/	 public double bN;
/*			*/	 public double bO;
/*			*/	 public double bP;
/*			*/	 public double bQ;
/*			*/	 public double bR;
/*			*/	 public boolean sleeping;
/*			*/	 public boolean fauxSleeping;
/*	 42 */	 public String spawnWorld = "";
/*			*/	 public ChunkCoordinates bT;
/*			*/	 public int sleepTicks;
/*			*/	 public float bU;
/*			*/	 public float bV;
/*			*/	 private ChunkCoordinates c;
/*			*/	 private ChunkCoordinates d;
/*	 55 */	 public int bW = 20;
/*	 56 */	 protected boolean bX = false;
/*			*/	 public float bY;
/*	 58 */	 public PlayerAbilities abilities = new PlayerAbilities();
/*	 59 */	 public int oldLevel = -1;
/*			*/	 public int expLevel;
/*			*/	 public int expTotal;
/*			*/	 public float exp;
/*			*/	 private ItemStack e;
/*			*/	 private int f;
/*	 65 */	 protected float cd = 0.1F;
/*	 66 */	 protected float ce = 0.02F;
/*	 67 */	 public EntityFishingHook hookedFish = null;
/*			*/ 
/*			*/	 public HumanEntity getBukkitEntity()
/*			*/	 {
/*	 45 */		 return (HumanEntity)super.getBukkitEntity();
/*			*/	 }
/*			*/ 
/*			*/	 public EntityHuman(World world)
/*			*/	 {
/*	 70 */		 super(world);
/*	 71 */		 this.defaultContainer = new ContainerPlayer(this.inventory, !world.isStatic);
/*	 72 */		 this.activeContainer = this.defaultContainer;
/*	 73 */		 this.height = 1.62F;
/*	 74 */		 ChunkCoordinates chunkcoordinates = world.getSpawn();
/*			*/ 
/*	 76 */		 setPositionRotation(chunkcoordinates.x + 0.5D, chunkcoordinates.y + 1, chunkcoordinates.z + 0.5D, 0.0F, 0.0F);
/*	 77 */		 this.aC = "humanoid";
/*	 78 */		 this.aB = 180.0F;
/*	 79 */		 this.maxFireTicks = 20;
/*	 80 */		 this.texture = "/mob/char.png";
/*			*/	 }
/*			*/ 
/*			*/	 public int getMaxHealth() {
/*	 84 */		 return 20;
/*			*/	 }
/*			*/ 
/*			*/	 protected void a() {
/*	 88 */		 super.a();
/*	 89 */		 this.datawatcher.a(16, Byte.valueOf(0));
/*	 90 */		 this.datawatcher.a(17, Byte.valueOf(0));
/*			*/	 }
/*			*/ 
/*			*/	 public boolean bw() {
/*	 94 */		 return this.e != null;
/*			*/	 }
/*			*/ 
/*			*/	 public void by() {
/*	 98 */		 if (this.e != null) {
/*	 99 */			 this.e.b(this.world, this, this.f);
/*			*/		 }
/*			*/ 
/*	102 */		 bz();
/*			*/	 }
/*			*/ 
/*			*/	 public void bz() {
/*	106 */		 this.e = null;
/*	107 */		 this.f = 0;
/*	108 */		 if (!this.world.isStatic)
/*	109 */			 c(false);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean aY()
/*			*/	 {
/*	114 */		 return (bw()) && (Item.byId[this.e.id].b(this.e) == EnumAnimation.d);
/*			*/	 }
/*			*/ 
/*			*/	 public void h_() {
/*	118 */		 if (this.e != null) {
/*	119 */			 ItemStack itemstack = this.inventory.getItemInHand();
/*			*/ 
/*	121 */			 if (itemstack == this.e) {
/*	122 */				 if ((this.f <= 25) && (this.f % 4 == 0)) {
/*	123 */					 c(itemstack, 5);
/*			*/				 }
/*			*/ 
/*	126 */				 if ((--this.f == 0) && (!this.world.isStatic))
/*	127 */					 o();
/*			*/			 }
/*			*/			 else {
/*	130 */				 bz();
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	134 */		 if (this.bL > 0) {
/*	135 */			 this.bL -= 1;
/*			*/		 }
/*			*/ 
/*	138 */		 if (isSleeping()) {
/*	139 */			 this.sleepTicks += 1;
/*	140 */			 if (this.sleepTicks > 100) {
/*	141 */				 this.sleepTicks = 100;
/*			*/			 }
/*			*/ 
/*	144 */			 if (!this.world.isStatic) {
/*	145 */				 if (!l())
/*	146 */					 a(true, true, false);
/*	147 */				 else if (this.world.r())
/*	148 */					 a(false, true, true);
/*			*/			 }
/*			*/		 }
/*	151 */		 else if (this.sleepTicks > 0) {
/*	152 */			 this.sleepTicks += 1;
/*	153 */			 if (this.sleepTicks >= 110) {
/*	154 */				 this.sleepTicks = 0;
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	158 */		 super.h_();
/*	159 */		 if ((!this.world.isStatic) && (this.activeContainer != null) && (!this.activeContainer.c(this))) {
/*	160 */			 closeInventory();
/*	161 */			 this.activeContainer = this.defaultContainer;
/*			*/		 }
/*			*/ 
/*	164 */		 if ((isBurning()) && (this.abilities.isInvulnerable)) {
/*	165 */			 extinguish();
/*			*/		 }
/*			*/ 
/*	168 */		 this.bM = this.bP;
/*	169 */		 this.bN = this.bQ;
/*	170 */		 this.bO = this.bR;
/*	171 */		 double d0 = this.locX - this.bP;
/*	172 */		 double d1 = this.locY - this.bQ;
/*	173 */		 double d2 = this.locZ - this.bR;
/*	174 */		 double d3 = 10.0D;
/*			*/ 
/*	176 */		 if (d0 > d3) {
/*	177 */			 this.bM = (this.bP = this.locX);
/*			*/		 }
/*			*/ 
/*	180 */		 if (d2 > d3) {
/*	181 */			 this.bO = (this.bR = this.locZ);
/*			*/		 }
/*			*/ 
/*	184 */		 if (d1 > d3) {
/*	185 */			 this.bN = (this.bQ = this.locY);
/*			*/		 }
/*			*/ 
/*	188 */		 if (d0 < -d3) {
/*	189 */			 this.bM = (this.bP = this.locX);
/*			*/		 }
/*			*/ 
/*	192 */		 if (d2 < -d3) {
/*	193 */			 this.bO = (this.bR = this.locZ);
/*			*/		 }
/*			*/ 
/*	196 */		 if (d1 < -d3) {
/*	197 */			 this.bN = (this.bQ = this.locY);
/*			*/		 }
/*			*/ 
/*	200 */		 this.bP += d0 * 0.25D;
/*	201 */		 this.bR += d2 * 0.25D;
/*	202 */		 this.bQ += d1 * 0.25D;
/*	203 */		 a(StatisticList.k, 1);
/*	204 */		 if (this.vehicle == null) {
/*	205 */			 this.d = null;
/*			*/		 }
/*			*/ 
/*	208 */		 if (!this.world.isStatic)
/*	209 */			 this.foodData.a(this);
/*			*/	 }
/*			*/ 
/*			*/	 protected void c(ItemStack itemstack, int i)
/*			*/	 {
/*	214 */		 if (itemstack.n() == EnumAnimation.c) {
/*	215 */			 this.world.makeSound(this, "random.drink", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
/*			*/		 }
/*			*/ 
/*	218 */		 if (itemstack.n() == EnumAnimation.b) {
/*	219 */			 for (int j = 0; j < i; j++) {
/*	220 */				 Vec3D vec3d = Vec3D.a().create((this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/*			*/ 
/*	222 */				 vec3d.a(-this.pitch * 3.141593F / 180.0F);
/*	223 */				 vec3d.b(-this.yaw * 3.141593F / 180.0F);
/*	224 */				 Vec3D vec3d1 = Vec3D.a().create((this.random.nextFloat() - 0.5D) * 0.3D, -this.random.nextFloat() * 0.6D - 0.3D, 0.6D);
/*			*/ 
/*	226 */				 vec3d1.a(-this.pitch * 3.141593F / 180.0F);
/*	227 */				 vec3d1.b(-this.yaw * 3.141593F / 180.0F);
/*	228 */				 vec3d1 = vec3d1.add(this.locX, this.locY + getHeadHeight(), this.locZ);
/*	229 */				 this.world.a("iconcrack_" + itemstack.getItem().id, vec3d1.a, vec3d1.b, vec3d1.c, vec3d.a, vec3d.b + 0.05D, vec3d.c);
/*			*/			 }
/*			*/ 
/*	232 */			 this.world.makeSound(this, "random.eat", 0.5F + 0.5F * this.random.nextInt(2), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected void o() {
/*	237 */		 if (this.e != null) {
/*	238 */			 c(this.e, 16);
/*	239 */			 int i = this.e.count;
/*	240 */			 ItemStack itemstack = this.e.b(this.world, this);
/*			*/ 
/*	242 */			 if ((itemstack != this.e) || ((itemstack != null) && (itemstack.count != i))) {
/*	243 */				 this.inventory.items[this.inventory.itemInHandIndex] = itemstack;
/*	244 */				 if (itemstack.count == 0) {
/*	245 */					 this.inventory.items[this.inventory.itemInHandIndex] = null;
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	249 */			 bz();
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean aX() {
/*	254 */		 return (getHealth() <= 0) || (isSleeping());
/*			*/	 }
/*			*/ 
/*			*/	 public void closeInventory()
/*			*/	 {
/*	259 */		 this.activeContainer = this.defaultContainer;
/*			*/	 }
/*			*/ 
/*			*/	 public void U() {
/*	263 */		 double d0 = this.locX;
/*	264 */		 double d1 = this.locY;
/*	265 */		 double d2 = this.locZ;
/*			*/ 
/*	267 */		 super.U();
/*	268 */		 this.bF = this.bG;
/*	269 */		 this.bG = 0.0F;
/*	270 */		 k(this.locX - d0, this.locY - d1, this.locZ - d2);
/*			*/	 }
/*			*/ 
/*			*/	 private int k() {
/*	274 */		 return hasEffect(MobEffectList.SLOWER_DIG) ? 6 + (1 + getEffect(MobEffectList.SLOWER_DIG).getAmplifier()) * 2 : hasEffect(MobEffectList.FASTER_DIG) ? 6 - (1 + getEffect(MobEffectList.FASTER_DIG).getAmplifier()) * 1 : 6;
/*			*/	 }
/*			*/ 
/*			*/	 protected void be() {
/*	278 */		 int i = k();
/*			*/ 
/*	280 */		 if (this.bH) {
/*	281 */			 this.bI += 1;
/*	282 */			 if (this.bI >= i) {
/*	283 */				 this.bI = 0;
/*	284 */				 this.bH = false;
/*			*/			 }
/*			*/		 } else {
/*	287 */			 this.bI = 0;
/*			*/		 }
/*			*/ 
/*	290 */		 this.aJ = (this.bI / i);
/*			*/	 }
/*			*/ 
/*			*/	 public void d() {
/*	294 */		 if (this.bC > 0) {
/*	295 */			 this.bC -= 1;
/*			*/		 }
/*			*/ 
/*	298 */		 if ((this.world.difficulty == 0) && (getHealth() < getMaxHealth()) && (this.ticksLived % 20 * 12 == 0))
/*			*/		 {
/*	300 */			 heal(1, EntityRegainHealthEvent.RegainReason.REGEN);
/*			*/		 }
/*			*/ 
/*	303 */		 this.inventory.k();
/*	304 */		 this.bF = this.bG;
/*	305 */		 super.d();
/*	306 */		 this.aG = this.abilities.b();
/*	307 */		 this.aH = this.ce;
/*	308 */		 if (isSprinting()) {
/*	309 */			 this.aG = (float)(this.aG + this.abilities.b() * 0.3D);
/*	310 */			 this.aH = (float)(this.aH + this.ce * 0.3D);
/*			*/		 }
/*			*/ 
/*	313 */		 float f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*			*/ 
/*	315 */		 float f1 = (float)TrigMath.atan(-this.motY * 0.2000000029802322D) * 15.0F;
/*			*/ 
/*	317 */		 if (f > 0.1F) {
/*	318 */			 f = 0.1F;
/*			*/		 }
/*			*/ 
/*	321 */		 if ((!this.onGround) || (getHealth() <= 0)) {
/*	322 */			 f = 0.0F;
/*			*/		 }
/*			*/ 
/*	325 */		 if ((this.onGround) || (getHealth() <= 0)) {
/*	326 */			 f1 = 0.0F;
/*			*/		 }
/*			*/ 
/*	329 */		 this.bG += (f - this.bG) * 0.4F;
/*	330 */		 this.aT += (f1 - this.aT) * 0.8F;
/*	331 */		 if (getHealth() > 0) {
/*	332 */			 List list = this.world.getEntities(this, this.boundingBox.grow(1.0D, 0.0D, 1.0D));
/*			*/ 
/*	334 */			 if (list != null) {
/*	335 */				 Iterator iterator = list.iterator();
/*			*/ 
/*	337 */				 while (iterator.hasNext()) {
/*	338 */					 Entity entity = (Entity)iterator.next();
/*			*/ 
/*	340 */					 if (!entity.dead)
/*	341 */						 o(entity);
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 private void o(Entity entity)
/*			*/	 {
/*	349 */		 entity.b_(this);
/*			*/	 }
/*			*/ 
/*			*/	 public void die(DamageSource damagesource) {
/*	353 */		 super.die(damagesource);
/*	354 */		 a(0.2F, 0.2F);
/*	355 */		 setPosition(this.locX, this.locY, this.locZ);
/*	356 */		 this.motY = 0.1000000014901161D;
/*	357 */		 if (this.name.equals("Notch")) {
/*	358 */			 a(new ItemStack(Item.APPLE, 1), true);
/*			*/		 }
/*			*/ 
/*	361 */		 this.inventory.m();
/*	362 */		 if (damagesource != null) {
/*	363 */			 this.motX = (-MathHelper.cos((this.aP + this.yaw) * 3.141593F / 180.0F) * 0.1F);
/*	364 */			 this.motZ = (-MathHelper.sin((this.aP + this.yaw) * 3.141593F / 180.0F) * 0.1F);
/*			*/		 } else {
/*	366 */			 this.motX = (this.motZ = 0.0D);
/*			*/		 }
/*			*/ 
/*	369 */		 this.height = 0.1F;
/*	370 */		 a(StatisticList.y, 1);
/*			*/	 }
/*			*/ 
/*			*/	 public void c(Entity entity, int i) {
/*	374 */		 this.bE += i;
/*	375 */		 if ((entity instanceof EntityHuman))
/*	376 */			 a(StatisticList.A, 1);
/*			*/		 else
/*	378 */			 a(StatisticList.z, 1);
/*			*/	 }
/*			*/ 
/*			*/	 protected int h(int i)
/*			*/	 {
/*	383 */		 int j = EnchantmentManager.getOxygenEnchantmentLevel(this.inventory);
/*			*/ 
/*	385 */		 return (j > 0) && (this.random.nextInt(j + 1) > 0) ? i : super.h(i);
/*			*/	 }
/*			*/ 
/*			*/	 public EntityItem bB() {
/*	389 */		 return a(this.inventory.splitStack(this.inventory.itemInHandIndex, 1), false);
/*			*/	 }
/*			*/ 
/*			*/	 public EntityItem drop(ItemStack itemstack) {
/*	393 */		 return a(itemstack, false);
/*			*/	 }
/*			*/ 
/*			*/	 public EntityItem a(ItemStack itemstack, boolean flag) {
/*	397 */		 if (itemstack == null) {
/*	398 */			 return null;
/*			*/		 }
/*	400 */		 EntityItem entityitem = new EntityItem(this.world, this.locX, this.locY - 0.300000011920929D + getHeadHeight(), this.locZ, itemstack);
/*			*/ 
/*	402 */		 entityitem.pickupDelay = 40;
/*	403 */		 float f = 0.1F;
/*			*/ 
/*	406 */		 if (flag) {
/*	407 */			 float f1 = this.random.nextFloat() * 0.5F;
/*	408 */			 float f2 = this.random.nextFloat() * 3.141593F * 2.0F;
/*			*/ 
/*	410 */			 entityitem.motX = (-MathHelper.sin(f2) * f1);
/*	411 */			 entityitem.motZ = (MathHelper.cos(f2) * f1);
/*	412 */			 entityitem.motY = 0.2000000029802322D;
/*			*/		 } else {
/*	414 */			 f = 0.3F;
/*	415 */			 entityitem.motX = (-MathHelper.sin(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F) * f);
/*	416 */			 entityitem.motZ = (MathHelper.cos(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F) * f);
/*	417 */			 entityitem.motY = (-MathHelper.sin(this.pitch / 180.0F * 3.141593F) * f + 0.1F);
/*	418 */			 f = 0.02F;
/*	419 */			 float f1 = this.random.nextFloat() * 3.141593F * 2.0F;
/*	420 */			 f *= this.random.nextFloat();
/*	421 */			 entityitem.motX += Math.cos(f1) * f;
/*	422 */			 entityitem.motY += (this.random.nextFloat() - this.random.nextFloat()) * 0.1F;
/*	423 */			 entityitem.motZ += Math.sin(f1) * f;
/*			*/		 }
/*			*/ 
/*	427 */		 Player player = (Player)getBukkitEntity();
/*	428 */		 CraftItem drop = new CraftItem(this.world.getServer(), entityitem);
/*			*/ 
/*	430 */		 PlayerDropItemEvent event = new PlayerDropItemEvent(player, drop);
/*	431 */		 this.world.getServer().getPluginManager().callEvent(event);
/*			*/ 
/*	433 */		 if (event.isCancelled()) {
/*	434 */			 player.getInventory().addItem(new org.bukkit.inventory.ItemStack[] { drop.getItemStack() });
/*	435 */			 return null;
/*			*/		 }
/*			*/ 
/*	439 */		 a(entityitem);
/*	440 */		 a(StatisticList.v, 1);
/*	441 */		 return entityitem;
/*			*/	 }
/*			*/ 
/*			*/	 protected void a(EntityItem entityitem)
/*			*/	 {
/*	446 */		 this.world.addEntity(entityitem);
/*			*/	 }
/*			*/ 
/*			*/	 public float a(Block block) {
/*	450 */		 float f = this.inventory.a(block);
/*	451 */		 int i = EnchantmentManager.getDigSpeedEnchantmentLevel(this.inventory);
/*			*/ 
/*	453 */		 if ((i > 0) && (this.inventory.b(block))) {
/*	454 */			 f += i * i + 1;
/*			*/		 }
/*			*/ 
/*	457 */		 if (hasEffect(MobEffectList.FASTER_DIG)) {
/*	458 */			 f *= (1.0F + (getEffect(MobEffectList.FASTER_DIG).getAmplifier() + 1) * 0.2F);
/*			*/		 }
/*			*/ 
/*	461 */		 if (hasEffect(MobEffectList.SLOWER_DIG)) {
/*	462 */			 f *= (1.0F - (getEffect(MobEffectList.SLOWER_DIG).getAmplifier() + 1) * 0.2F);
/*			*/		 }
/*			*/ 
/*	465 */		 if ((a(Material.WATER)) && (!EnchantmentManager.hasWaterWorkerEnchantment(this.inventory))) {
/*	466 */			 f /= 5.0F;
/*			*/		 }
/*			*/ 
/*	469 */		 if (!this.onGround) {
/*	470 */			 f /= 5.0F;
/*			*/		 }
/*			*/ 
/*	473 */		 return f;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean b(Block block) {
/*	477 */		 return this.inventory.b(block);
/*			*/	 }
/*			*/ 
/*			*/	 public void a(NBTTagCompound nbttagcompound) {
/*	481 */		 super.a(nbttagcompound);
/*	482 */		 NBTTagList nbttaglist = nbttagcompound.getList("Inventory");
/*			*/ 
/*	484 */		 this.inventory.b(nbttaglist);
/*	485 */		 this.dimension = nbttagcompound.getInt("Dimension");
/*	486 */		 this.sleeping = nbttagcompound.getBoolean("Sleeping");
/*	487 */		 this.sleepTicks = nbttagcompound.getShort("SleepTimer");
/*	488 */		 this.exp = nbttagcompound.getFloat("XpP");
/*	489 */		 this.expLevel = nbttagcompound.getInt("XpLevel");
/*	490 */		 this.expTotal = nbttagcompound.getInt("XpTotal");
/*	491 */		 if (this.sleeping) {
/*	492 */			 this.bT = new ChunkCoordinates(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ));
/*	493 */			 a(true, true, false);
/*			*/		 }
/*			*/ 
/*	497 */		 this.spawnWorld = nbttagcompound.getString("SpawnWorld");
/*	498 */		 if ("".equals(this.spawnWorld)) {
/*	499 */			 this.spawnWorld = ((org.bukkit.World)this.world.getServer().getWorlds().get(0)).getName();
/*			*/		 }
/*			*/ 
/*	503 */		 if ((nbttagcompound.hasKey("SpawnX")) && (nbttagcompound.hasKey("SpawnY")) && (nbttagcompound.hasKey("SpawnZ"))) {
/*	504 */			 this.c = new ChunkCoordinates(nbttagcompound.getInt("SpawnX"), nbttagcompound.getInt("SpawnY"), nbttagcompound.getInt("SpawnZ"));
/*			*/		 }
/*			*/ 
/*	507 */		 this.foodData.a(nbttagcompound);
/*	508 */		 this.abilities.b(nbttagcompound);
/*	509 */		 if (nbttagcompound.hasKey("EnderItems")) {
/*	510 */			 NBTTagList nbttaglist1 = nbttagcompound.getList("EnderItems");
/*			*/ 
/*	512 */			 this.enderChest.a(nbttaglist1);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void b(NBTTagCompound nbttagcompound) {
/*	517 */		 super.b(nbttagcompound);
/*	518 */		 nbttagcompound.set("Inventory", this.inventory.a(new NBTTagList()));
/*	519 */		 nbttagcompound.setInt("Dimension", this.dimension);
/*	520 */		 nbttagcompound.setBoolean("Sleeping", this.sleeping);
/*	521 */		 nbttagcompound.setShort("SleepTimer", (short)this.sleepTicks);
/*	522 */		 nbttagcompound.setFloat("XpP", this.exp);
/*	523 */		 nbttagcompound.setInt("XpLevel", this.expLevel);
/*	524 */		 nbttagcompound.setInt("XpTotal", this.expTotal);
/*	525 */		 if (this.c != null) {
/*	526 */			 nbttagcompound.setInt("SpawnX", this.c.x);
/*	527 */			 nbttagcompound.setInt("SpawnY", this.c.y);
/*	528 */			 nbttagcompound.setInt("SpawnZ", this.c.z);
/*	529 */			 nbttagcompound.setString("SpawnWorld", this.spawnWorld);
/*			*/		 }
/*			*/ 
/*	532 */		 this.foodData.b(nbttagcompound);
/*	533 */		 this.abilities.a(nbttagcompound);
/*	534 */		 nbttagcompound.set("EnderItems", this.enderChest.g());
/*			*/	 }
/*			*/	 public void openContainer(IInventory iinventory) {
/*			*/	 }
/*			*/	 public void startEnchanting(int i, int j, int k) {
/*			*/	 }
/*			*/	 public void startCrafting(int i, int j, int k) {
/*			*/	 }
/*			*/	 public void receive(Entity entity, int i) {
/*			*/	 }
/*			*/ 
/*			*/	 public float getHeadHeight() {
/*	546 */		 return 0.12F;
/*			*/	 }
/*			*/ 
/*			*/	 protected void d_() {
/*	550 */		 this.height = 1.62F;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean damageEntity(DamageSource damagesource, int i) {
/*	554 */		 if ((this.abilities.isInvulnerable) && (!damagesource.ignoresInvulnerability())) {
/*	555 */			 return false;
/*			*/		 }
/*	557 */		 this.bq = 0;
/*	558 */		 if (getHealth() <= 0) {
/*	559 */			 return false;
/*			*/		 }
/*	561 */		 if ((isSleeping()) && (!this.world.isStatic)) {
/*	562 */			 a(true, true, false);
/*			*/		 }
/*			*/ 
/*	565 */		 Entity entity = damagesource.getEntity();
/*			*/ 
/*	567 */		 if (damagesource.n()) {
/*	568 */			 if (this.world.difficulty == 0) {
/*	569 */				 return false;
/*			*/			 }
/*			*/ 
/*	572 */			 if (this.world.difficulty == 1) {
/*	573 */				 i = i / 2 + 1;
/*			*/			 }
/*			*/ 
/*	576 */			 if (this.world.difficulty == 3) {
/*	577 */				 i = i * 3 / 2;
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/*	584 */		 Entity entity1 = damagesource.getEntity();
/*			*/ 
/*	586 */		 if (((entity1 instanceof EntityArrow)) && (((EntityArrow)entity1).shooter != null)) {
/*	587 */			 entity1 = ((EntityArrow)entity1).shooter;
/*			*/		 }
/*			*/ 
/*	590 */		 if ((entity1 instanceof EntityLiving)) {
/*	591 */			 a((EntityLiving)entity1, false);
/*			*/		 }
/*			*/ 
/*	594 */		 a(StatisticList.x, i);
/*	595 */		 return super.damageEntity(damagesource, i);
/*			*/	 }
/*			*/ 
/*			*/	 protected int c(DamageSource damagesource, int i)
/*			*/	 {
/*	602 */		 int j = super.c(damagesource, i);
/*			*/ 
/*	604 */		 if (j <= 0) {
/*	605 */			 return 0;
/*			*/		 }
/*	607 */		 int k = EnchantmentManager.a(this.inventory, damagesource);
/*			*/ 
/*	609 */		 if (k > 20) {
/*	610 */			 k = 20;
/*			*/		 }
/*			*/ 
/*	613 */		 if ((k > 0) && (k <= 20)) {
/*	614 */			 int l = 25 - k;
/*	615 */			 int i1 = j * l + this.aM;
/*			*/ 
/*	617 */			 j = i1 / 25;
/*	618 */			 this.aM = (i1 % 25);
/*			*/		 }
/*			*/ 
/*	621 */		 return j;
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean h()
/*			*/	 {
/*	626 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 protected void a(EntityLiving entityliving, boolean flag) {
/*	630 */		 if ((!(entityliving instanceof EntityCreeper)) && (!(entityliving instanceof EntityGhast))) {
/*	631 */			 if ((entityliving instanceof EntityWolf)) {
/*	632 */				 EntityWolf entitywolf = (EntityWolf)entityliving;
/*			*/ 
/*	634 */				 if ((entitywolf.isTamed()) && (this.name.equals(entitywolf.getOwnerName()))) {
/*	635 */					 return;
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	639 */			 if ((!(entityliving instanceof EntityHuman)) || (h())) {
/*	640 */				 List list = this.world.a(EntityWolf.class, AxisAlignedBB.a().a(this.locX, this.locY, this.locZ, this.locX + 1.0D, this.locY + 1.0D, this.locZ + 1.0D).grow(16.0D, 4.0D, 16.0D));
/*	641 */				 Iterator iterator = list.iterator();
/*			*/ 
/*	643 */				 while (iterator.hasNext()) {
/*	644 */					 EntityWolf entitywolf1 = (EntityWolf)iterator.next();
/*			*/ 
/*	646 */					 if ((entitywolf1.isTamed()) && (entitywolf1.m() == null) && (this.name.equals(entitywolf1.getOwnerName())) && ((!flag) || (!entitywolf1.isSitting()))) {
/*	647 */						 entitywolf1.setSitting(false);
/*	648 */						 entitywolf1.setTarget(entityliving);
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected void k(int i) {
/*	656 */		 this.inventory.g(i);
/*			*/	 }
/*			*/ 
/*			*/	 public int aO() {
/*	660 */		 return this.inventory.l();
/*			*/	 }
/*			*/ 
/*			*/	 protected void d(DamageSource damagesource, int i) {
/*	664 */		 if ((!damagesource.ignoresArmor()) && (aY())) {
/*	665 */			 i = 1 + i >> 1;
/*			*/		 }
/*			*/ 
/*	668 */		 i = b(damagesource, i);
/*	669 */		 i = c(damagesource, i);
/*	670 */		 j(damagesource.d());
/*	671 */		 this.health -= i;
/*			*/	 }
/*			*/	 public void openFurnace(TileEntityFurnace tileentityfurnace) {
/*			*/	 }
/*			*/	 public void openDispenser(TileEntityDispenser tileentitydispenser) {
/*			*/	 }
/*			*/	 public void a(TileEntitySign tileentitysign) {
/*			*/	 }
/*			*/	 public void openBrewingStand(TileEntityBrewingStand tileentitybrewingstand) {
/*			*/	 }
/*			*/	 public void openTrade(IMerchant imerchant) {
/*			*/	 }
/*			*/	 public void c(ItemStack itemstack) {
/*			*/	 }
/*			*/ 
/*			*/	 public boolean m(Entity entity) {
/*	687 */		 if (entity.c(this)) {
/*	688 */			 return true;
/*			*/		 }
/*	690 */		 ItemStack itemstack = bC();
/*			*/ 
/*	692 */		 if ((itemstack != null) && ((entity instanceof EntityLiving))) {
/*	693 */			 if (this.abilities.canInstantlyBuild) {
/*	694 */				 itemstack = itemstack.cloneItemStack();
/*			*/			 }
/*			*/ 
/*	697 */			 if (itemstack.a((EntityLiving)entity))
/*			*/			 {
/*	699 */				 if ((itemstack.count == 0) && (!this.abilities.canInstantlyBuild)) {
/*	700 */					 bD();
/*			*/				 }
/*			*/ 
/*	703 */				 return true;
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	707 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public ItemStack bC()
/*			*/	 {
/*	712 */		 return this.inventory.getItemInHand();
/*			*/	 }
/*			*/ 
/*			*/	 public void bD() {
/*	716 */		 this.inventory.setItem(this.inventory.itemInHandIndex, (ItemStack)null);
/*			*/	 }
/*			*/ 
/*			*/	 public double W() {
/*	720 */		 return this.height - 0.5F;
/*			*/	 }
/*			*/ 
/*			*/	 public void i() {
/*	724 */		 if ((!this.bH) || (this.bI >= k() / 2) || (this.bI < 0)) {
/*	725 */			 this.bI = -1;
/*	726 */			 this.bH = true;
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void attack(Entity entity) {
/*	731 */		 if (entity.an()) {
/*	732 */			 int i = this.inventory.a(entity);
/*			*/ 
/*	734 */			 if (hasEffect(MobEffectList.INCREASE_DAMAGE)) {
/*	735 */				 i += (3 << getEffect(MobEffectList.INCREASE_DAMAGE).getAmplifier());
/*			*/			 }
/*			*/ 
/*	738 */			 if (hasEffect(MobEffectList.WEAKNESS)) {
/*	739 */				 i -= (2 << getEffect(MobEffectList.WEAKNESS).getAmplifier());
/*			*/			 }
/*			*/ 
/*	742 */			 int j = 0;
/*	743 */			 int k = 0;
/*			*/ 
/*	745 */			 if ((entity instanceof EntityLiving)) {
/*	746 */				 k = EnchantmentManager.a(this.inventory, (EntityLiving)entity);
/*	747 */				 j += EnchantmentManager.getKnockbackEnchantmentLevel(this.inventory, (EntityLiving)entity);
/*			*/			 }
/*			*/ 
/*	750 */			 if (isSprinting()) {
/*	751 */				 j++;
/*			*/			 }
/*			*/ 
/*	754 */			 if ((i > 0) || (k > 0)) {
/*	755 */				 boolean flag = (this.fallDistance > 0.0F) && (!this.onGround) && (!f_()) && (!H()) && (!hasEffect(MobEffectList.BLINDNESS)) && (this.vehicle == null) && ((entity instanceof EntityLiving));
/*			*/ 
/*	757 */				 if (flag) {
/*	758 */					 i += this.random.nextInt(i / 2 + 2);
/*			*/				 }
/*			*/ 
/*	761 */				 i += k;
/*	762 */				 boolean flag1 = entity.damageEntity(DamageSource.playerAttack(this), i);
/*			*/ 
/*	765 */				 if (!flag1) {
/*	766 */					 return;
/*			*/				 }
/*			*/ 
/*	770 */				 if (flag1) {
/*	771 */					 if (j > 0) {
/*	772 */						 entity.g(-MathHelper.sin(this.yaw * 3.141593F / 180.0F) * j * 0.5F, 0.1D, MathHelper.cos(this.yaw * 3.141593F / 180.0F) * j * 0.5F);
/*	773 */						 this.motX *= 0.6D;
/*	774 */						 this.motZ *= 0.6D;
/*	775 */						 setSprinting(false);
/*			*/					 }
/*			*/ 
/*	778 */					 if (flag) {
/*	779 */						 b(entity);
/*			*/					 }
/*			*/ 
/*	782 */					 if (k > 0) {
/*	783 */						 c(entity);
/*			*/					 }
/*			*/ 
/*	786 */					 if (i >= 18) {
/*	787 */						 a(AchievementList.E);
/*			*/					 }
/*			*/ 
/*	790 */					 j(entity);
/*			*/				 }
/*			*/ 
/*	793 */				 ItemStack itemstack = bC();
/*			*/ 
/*	795 */				 if ((itemstack != null) && ((entity instanceof EntityLiving))) {
/*	796 */					 itemstack.a((EntityLiving)entity, this);
/*			*/ 
/*	798 */					 if (itemstack.count == 0) {
/*	799 */						 bD();
/*			*/					 }
/*			*/				 }
/*			*/ 
/*	803 */				 if ((entity instanceof EntityLiving)) {
/*	804 */					 if (entity.isAlive()) {
/*	805 */						 a((EntityLiving)entity, true);
/*			*/					 }
/*			*/ 
/*	808 */					 a(StatisticList.w, i);
/*	809 */					 int l = EnchantmentManager.getFireAspectEnchantmentLevel(this.inventory, (EntityLiving)entity);
/*			*/ 
/*	811 */					 if (l > 0)
/*			*/					 {
/*	813 */						 EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent(getBukkitEntity(), entity.getBukkitEntity(), l * 4);
/*	814 */						 Bukkit.getPluginManager().callEvent(combustEvent);
/*			*/ 
/*	816 */						 if (!combustEvent.isCancelled()) {
/*	817 */							 entity.setOnFire(combustEvent.getDuration());
/*			*/						 }
/*			*/					 }
/*			*/ 
/*			*/				 }
/*			*/ 
/*	823 */				 j(0.3F);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void b(Entity entity) {
/*			*/	 }
/*			*/ 
/*			*/	 public void c(Entity entity) {
/*			*/	 }
/*			*/ 
/*			*/	 public void die() {
/*	833 */		 super.die();
/*	834 */		 this.defaultContainer.a(this);
/*	835 */		 if (this.activeContainer != null)
/*	836 */			 this.activeContainer.a(this);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean inBlock()
/*			*/	 {
/*	841 */		 return (!this.sleeping) && (super.inBlock());
/*			*/	 }
/*			*/ 
/*			*/	 public boolean bF() {
/*	845 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public EnumBedResult a(int i, int j, int k) {
/*	849 */		 if (!this.world.isStatic) {
/*	850 */			 if ((isSleeping()) || (!isAlive())) {
/*	851 */				 return EnumBedResult.OTHER_PROBLEM;
/*			*/			 }
/*			*/ 
/*	854 */			 if (!this.world.worldProvider.d()) {
/*	855 */				 return EnumBedResult.NOT_POSSIBLE_HERE;
/*			*/			 }
/*			*/ 
/*	858 */			 if (this.world.r()) {
/*	859 */				 return EnumBedResult.NOT_POSSIBLE_NOW;
/*			*/			 }
/*			*/ 
/*	862 */			 if ((Math.abs(this.locX - i) > 3.0D) || (Math.abs(this.locY - j) > 2.0D) || (Math.abs(this.locZ - k) > 3.0D)) {
/*	863 */				 return EnumBedResult.TOO_FAR_AWAY;
/*			*/			 }
/*			*/ 
/*	866 */			 double d0 = 8.0D;
/*	867 */			 double d1 = 5.0D;
/*	868 */			 List list = this.world.a(EntityMonster.class, AxisAlignedBB.a().a(i - d0, j - d1, k - d0, i + d0, j + d1, k + d0));
/*			*/ 
/*	870 */			 if (!list.isEmpty()) {
/*	871 */				 return EnumBedResult.NOT_SAFE;
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/*	876 */		 if ((getBukkitEntity() instanceof Player)) {
/*	877 */			 Player player = (Player)getBukkitEntity();
/*	878 */			 org.bukkit.block.Block bed = this.world.getWorld().getBlockAt(i, j, k);
/*			*/ 
/*	880 */			 PlayerBedEnterEvent event = new PlayerBedEnterEvent(player, bed);
/*	881 */			 this.world.getServer().getPluginManager().callEvent(event);
/*			*/ 
/*	883 */			 if (event.isCancelled()) {
/*	884 */				 return EnumBedResult.OTHER_PROBLEM;
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/*	889 */		 a(0.2F, 0.2F);
/*	890 */		 this.height = 0.2F;
/*	891 */		 if (this.world.isLoaded(i, j, k)) {
/*	892 */			 int l = this.world.getData(i, j, k);
/*	893 */			 int i1 = BlockBed.d(l);
/*	894 */			 float f = 0.5F;
/*	895 */			 float f1 = 0.5F;
/*			*/ 
/*	897 */			 switch (i1) {
/*			*/			 case 0:
/*	899 */				 f1 = 0.9F;
/*	900 */				 break;
/*			*/			 case 1:
/*	903 */				 f = 0.1F;
/*	904 */				 break;
/*			*/			 case 2:
/*	907 */				 f1 = 0.1F;
/*	908 */				 break;
/*			*/			 case 3:
/*	911 */				 f = 0.9F;
/*			*/			 }
/*			*/ 
/*	914 */			 b(i1);
/*	915 */			 setPosition(i + f, j + 0.9375F, k + f1);
/*			*/		 } else {
/*	917 */			 setPosition(i + 0.5F, j + 0.9375F, k + 0.5F);
/*			*/		 }
/*			*/ 
/*	920 */		 this.sleeping = true;
/*	921 */		 this.sleepTicks = 0;
/*	922 */		 this.bT = new ChunkCoordinates(i, j, k);
/*	923 */		 this.motX = (this.motZ = this.motY = 0.0D);
/*	924 */		 if (!this.world.isStatic) {
/*	925 */			 this.world.everyoneSleeping();
/*			*/		 }
/*			*/ 
/*	928 */		 return EnumBedResult.OK;
/*			*/	 }
/*			*/ 
/*			*/	 private void b(int i) {
/*	932 */		 this.bU = 0.0F;
/*	933 */		 this.bV = 0.0F;
/*	934 */		 switch (i) {
/*			*/		 case 0:
/*	936 */			 this.bV = -1.8F;
/*	937 */			 break;
/*			*/		 case 1:
/*	940 */			 this.bU = 1.8F;
/*	941 */			 break;
/*			*/		 case 2:
/*	944 */			 this.bV = 1.8F;
/*	945 */			 break;
/*			*/		 case 3:
/*	948 */			 this.bU = -1.8F;
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(boolean flag, boolean flag1, boolean flag2) {
/*	953 */		 a(0.6F, 1.8F);
/*	954 */		 d_();
/*	955 */		 ChunkCoordinates chunkcoordinates = this.bT;
/*	956 */		 ChunkCoordinates chunkcoordinates1 = this.bT;
/*			*/ 
/*	958 */		 if ((chunkcoordinates != null) && (this.world.getTypeId(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z) == Block.BED.id)) {
/*	959 */			 BlockBed.a(this.world, chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, false);
/*	960 */			 chunkcoordinates1 = BlockBed.b(this.world, chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, 0);
/*	961 */			 if (chunkcoordinates1 == null) {
/*	962 */				 chunkcoordinates1 = new ChunkCoordinates(chunkcoordinates.x, chunkcoordinates.y + 1, chunkcoordinates.z);
/*			*/			 }
/*			*/ 
/*	965 */			 setPosition(chunkcoordinates1.x + 0.5F, chunkcoordinates1.y + this.height + 0.1F, chunkcoordinates1.z + 0.5F);
/*			*/		 }
/*			*/ 
/*	968 */		 this.sleeping = false;
/*	969 */		 if ((!this.world.isStatic) && (flag1)) {
/*	970 */			 this.world.everyoneSleeping();
/*			*/		 }
/*			*/ 
/*	974 */		 if ((getBukkitEntity() instanceof Player)) {
/*	975 */			 Player player = (Player)getBukkitEntity();
/*			*/			 org.bukkit.block.Block bed;
/*			*/			 org.bukkit.block.Block bed;
/*	978 */			 if (chunkcoordinates != null)
/*	979 */				 bed = this.world.getWorld().getBlockAt(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z);
/*			*/			 else {
/*	981 */				 bed = this.world.getWorld().getBlockAt(player.getLocation());
/*			*/			 }
/*			*/ 
/*	984 */			 PlayerBedLeaveEvent event = new PlayerBedLeaveEvent(player, bed);
/*	985 */			 this.world.getServer().getPluginManager().callEvent(event);
/*			*/		 }
/*			*/ 
/*	989 */		 if (flag)
/*	990 */			 this.sleepTicks = 0;
/*			*/		 else {
/*	992 */			 this.sleepTicks = 100;
/*			*/		 }
/*			*/ 
/*	995 */		 if (flag2)
/*	996 */			 setRespawnPosition(this.bT);
/*			*/	 }
/*			*/ 
/*			*/	 private boolean l()
/*			*/	 {
/* 1001 */		 return this.world.getTypeId(this.bT.x, this.bT.y, this.bT.z) == Block.BED.id;
/*			*/	 }
/*			*/ 
/*			*/	 public static ChunkCoordinates getBed(World world, ChunkCoordinates chunkcoordinates) {
/* 1005 */		 IChunkProvider ichunkprovider = world.F();
/*			*/ 
/* 1007 */		 ichunkprovider.getChunkAt(chunkcoordinates.x - 3 >> 4, chunkcoordinates.z - 3 >> 4);
/* 1008 */		 ichunkprovider.getChunkAt(chunkcoordinates.x + 3 >> 4, chunkcoordinates.z - 3 >> 4);
/* 1009 */		 ichunkprovider.getChunkAt(chunkcoordinates.x - 3 >> 4, chunkcoordinates.z + 3 >> 4);
/* 1010 */		 ichunkprovider.getChunkAt(chunkcoordinates.x + 3 >> 4, chunkcoordinates.z + 3 >> 4);
/* 1011 */		 if (world.getTypeId(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z) != Block.BED.id) {
/* 1012 */			 return null;
/*			*/		 }
/* 1014 */		 ChunkCoordinates chunkcoordinates1 = BlockBed.b(world, chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, 0);
/*			*/ 
/* 1016 */		 return chunkcoordinates1;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isSleeping()
/*			*/	 {
/* 1021 */		 return this.sleeping;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isDeeplySleeping() {
/* 1025 */		 return (this.sleeping) && (this.sleepTicks >= 100);
/*			*/	 }
/*			*/	 public void c(String s) {
/*			*/	 }
/*			*/ 
/*			*/	 public ChunkCoordinates getBed() {
/* 1031 */		 return this.c;
/*			*/	 }
/*			*/ 
/*			*/	 public void setRespawnPosition(ChunkCoordinates chunkcoordinates) {
/* 1035 */		 if (chunkcoordinates != null) {
/* 1036 */			 this.c = new ChunkCoordinates(chunkcoordinates);
/* 1037 */			 this.spawnWorld = this.world.worldData.getName();
/*			*/		 } else {
/* 1039 */			 this.c = null;
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Statistic statistic) {
/* 1044 */		 a(statistic, 1);
/*			*/	 }
/*			*/	 public void a(Statistic statistic, int i) {
/*			*/	 }
/*			*/ 
/*			*/	 protected void aZ() {
/* 1050 */		 super.aZ();
/* 1051 */		 a(StatisticList.u, 1);
/* 1052 */		 if (isSprinting())
/* 1053 */			 j(0.8F);
/*			*/		 else
/* 1055 */			 j(0.2F);
/*			*/	 }
/*			*/ 
/*			*/	 public void e(float f, float f1)
/*			*/	 {
/* 1060 */		 double d0 = this.locX;
/* 1061 */		 double d1 = this.locY;
/* 1062 */		 double d2 = this.locZ;
/*			*/ 
/* 1064 */		 if ((this.abilities.isFlying) && (this.vehicle == null)) {
/* 1065 */			 double d3 = this.motY;
/* 1066 */			 float f2 = this.aH;
/*			*/ 
/* 1068 */			 this.aH = this.abilities.a();
/* 1069 */			 super.e(f, f1);
/* 1070 */			 this.motY = (d3 * 0.6D);
/* 1071 */			 this.aH = f2;
/*			*/		 } else {
/* 1073 */			 super.e(f, f1);
/*			*/		 }
/*			*/ 
/* 1076 */		 checkMovement(this.locX - d0, this.locY - d1, this.locZ - d2);
/*			*/	 }
/*			*/ 
/*			*/	 public void checkMovement(double d0, double d1, double d2) {
/* 1080 */		 if (this.vehicle == null)
/*			*/		 {
/* 1083 */			 if (a(Material.WATER)) {
/* 1084 */				 int i = Math.round(MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
/* 1085 */				 if (i > 0) {
/* 1086 */					 a(StatisticList.q, i);
/* 1087 */					 j(0.015F * i * 0.01F);
/*			*/				 }
/* 1089 */			 } else if (H()) {
/* 1090 */				 int i = Math.round(MathHelper.sqrt(d0 * d0 + d2 * d2) * 100.0F);
/* 1091 */				 if (i > 0) {
/* 1092 */					 a(StatisticList.m, i);
/* 1093 */					 j(0.015F * i * 0.01F);
/*			*/				 }
/* 1095 */			 } else if (f_()) {
/* 1096 */				 if (d1 > 0.0D)
/* 1097 */					 a(StatisticList.o, (int)Math.round(d1 * 100.0D));
/*			*/			 }
/* 1099 */			 else if (this.onGround) {
/* 1100 */				 int i = Math.round(MathHelper.sqrt(d0 * d0 + d2 * d2) * 100.0F);
/* 1101 */				 if (i > 0) {
/* 1102 */					 a(StatisticList.l, i);
/* 1103 */					 if (isSprinting())
/* 1104 */						 j(0.09999999F * i * 0.01F);
/*			*/					 else
/* 1106 */						 j(0.01F * i * 0.01F);
/*			*/				 }
/*			*/			 }
/*			*/			 else {
/* 1110 */				 int i = Math.round(MathHelper.sqrt(d0 * d0 + d2 * d2) * 100.0F);
/* 1111 */				 if (i > 25)
/* 1112 */					 a(StatisticList.p, i);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 private void k(double d0, double d1, double d2)
/*			*/	 {
/* 1119 */		 if (this.vehicle != null) {
/* 1120 */			 int i = Math.round(MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
/*			*/ 
/* 1122 */			 if (i > 0)
/* 1123 */				 if ((this.vehicle instanceof EntityMinecart)) {
/* 1124 */					 a(StatisticList.r, i);
/* 1125 */					 if (this.d == null)
/* 1126 */						 this.d = new ChunkCoordinates(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ));
/* 1127 */					 else if (this.d.e(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)) >= 1000000.0D)
/* 1128 */						 a(AchievementList.q, 1);
/*			*/				 }
/* 1130 */				 else if ((this.vehicle instanceof EntityBoat)) {
/* 1131 */					 a(StatisticList.s, i);
/* 1132 */				 } else if ((this.vehicle instanceof EntityPig)) {
/* 1133 */					 a(StatisticList.t, i);
/*			*/				 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected void a(float f)
/*			*/	 {
/* 1140 */		 if (!this.abilities.canFly) {
/* 1141 */			 if (f >= 2.0F) {
/* 1142 */				 a(StatisticList.n, (int)Math.round(f * 100.0D));
/*			*/			 }
/*			*/ 
/* 1145 */			 super.a(f);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(EntityLiving entityliving) {
/* 1150 */		 if ((entityliving instanceof EntityMonster))
/* 1151 */			 a(AchievementList.s);
/*			*/	 }
/*			*/ 
/*			*/	 public void aa()
/*			*/	 {
/* 1156 */		 if (this.bW > 0)
/* 1157 */			 this.bW = 10;
/*			*/		 else
/* 1159 */			 this.bX = true;
/*			*/	 }
/*			*/ 
/*			*/	 public void giveExp(int i)
/*			*/	 {
/* 1164 */		 this.bE += i;
/* 1165 */		 int j = 2147483647 - this.expTotal;
/*			*/ 
/* 1167 */		 if (i > j) {
/* 1168 */			 i = j;
/*			*/		 }
/*			*/ 
/* 1171 */		 this.exp += i / getExpToLevel();
/*			*/ 
/* 1173 */		 for (this.expTotal += i; this.exp >= 1.0F; this.exp /= getExpToLevel()) {
/* 1174 */			 this.exp = ((this.exp - 1.0F) * getExpToLevel());
/* 1175 */			 levelUp();
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void levelDown(int i) {
/* 1180 */		 this.expLevel -= i;
/* 1181 */		 if (this.expLevel < 0)
/* 1182 */			 this.expLevel = 0;
/*			*/	 }
/*			*/ 
/*			*/	 public int getExpToLevel()
/*			*/	 {
/* 1187 */		 return this.expLevel >= 15 ? 17 + (this.expLevel - 15) * 3 : this.expLevel >= 30 ? 62 + (this.expLevel - 30) * 7 : 17;
/*			*/	 }
/*			*/ 
/*			*/	 private void levelUp() {
/* 1191 */		 this.expLevel += 1;
/*			*/	 }
/*			*/ 
/*			*/	 public void j(float f) {
/* 1195 */		 if ((!this.abilities.isInvulnerable) && 
/* 1196 */			 (!this.world.isStatic))
/* 1197 */			 this.foodData.a(f);
/*			*/	 }
/*			*/ 
/*			*/	 public FoodMetaData getFoodData()
/*			*/	 {
/* 1203 */		 return this.foodData;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean e(boolean flag) {
/* 1207 */		 return ((flag) || (this.foodData.c())) && (!this.abilities.isInvulnerable);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean bM() {
/* 1211 */		 return (getHealth() > 0) && (getHealth() < getMaxHealth());
/*			*/	 }
/*			*/ 
/*			*/	 public void a(ItemStack itemstack, int i) {
/* 1215 */		 if (itemstack != this.e) {
/* 1216 */			 this.e = itemstack;
/* 1217 */			 this.f = i;
/* 1218 */			 if (!this.world.isStatic)
/* 1219 */				 c(true);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public boolean e(int i, int j, int k)
/*			*/	 {
/* 1225 */		 return this.abilities.mayBuild;
/*			*/	 }
/*			*/ 
/*			*/	 protected int getExpValue(EntityHuman entityhuman) {
/* 1229 */		 int i = this.expLevel * 7;
/*			*/ 
/* 1231 */		 return i > 100 ? 100 : i;
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean alwaysGivesExp() {
/* 1235 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 public String getLocalizedName() {
/* 1239 */		 return this.name;
/*			*/	 }
/*			*/	 public void c(int i) {
/*			*/	 }
/*			*/ 
/*			*/	 public void copyTo(EntityHuman entityhuman, boolean flag) {
/* 1245 */		 if (flag) {
/* 1246 */			 this.inventory.b(entityhuman.inventory);
/* 1247 */			 this.health = entityhuman.health;
/* 1248 */			 this.foodData = entityhuman.foodData;
/* 1249 */			 this.expLevel = entityhuman.expLevel;
/* 1250 */			 this.expTotal = entityhuman.expTotal;
/* 1251 */			 this.exp = entityhuman.exp;
/* 1252 */			 this.bE = entityhuman.bE;
/*			*/		 }
/*			*/ 
/* 1255 */		 this.enderChest = entityhuman.enderChest;
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean e_() {
/* 1259 */		 return !this.abilities.isFlying;
/*			*/	 }
/*			*/	 public void updateAbilities() {
/*			*/	 }
/*			*/	 public void a(EnumGamemode enumgamemode) {
/*			*/	 }
/*			*/ 
/*			*/	 public String getName() {
/* 1267 */		 return this.name;
/*			*/	 }
/*			*/ 
/*			*/	 public LocaleLanguage getLocale() {
/* 1271 */		 return LocaleLanguage.a();
/*			*/	 }
/*			*/ 
/*			*/	 public String a(String s, Object[] aobject) {
/* 1275 */		 return getLocale().a(s, aobject);
/*			*/	 }
/*			*/ 
/*			*/	 public InventoryEnderChest getEnderChest() {
/* 1279 */		 return this.enderChest;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityHuman
 * JD-Core Version:		0.6.0
 */