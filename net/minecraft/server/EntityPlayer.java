/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.ByteArrayOutputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ import java.io.IOException;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.EnumSet;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.LinkedList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import java.util.TreeMap;
/*		 */ import org.bukkit.Bukkit;
/*		 */ import org.bukkit.Location;
/*		 */ import org.bukkit.Server;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.entity.CraftPlayer;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftItemStack;
/*		 */ import org.bukkit.event.entity.PlayerDeathEvent;
/*		 */ import org.bukkit.event.inventory.InventoryType;
/*		 */ import org.bukkit.inventory.InventoryView;
/*		 */ 
/*		 */ public class EntityPlayer extends EntityHuman
/*		 */	 implements ICrafting
/*		 */ {
/*	22 */	 private LocaleLanguage locale = new LocaleLanguage("en_US");
/*		 */	 public NetServerHandler netServerHandler;
/*		 */	 public MinecraftServer server;
/*		 */	 public ItemInWorldManager itemInWorldManager;
/*		 */	 public double d;
/*		 */	 public double e;
/*	28 */	 public final List chunkCoordIntPairQueue = new LinkedList();
/*	29 */	 public final List g = new LinkedList();
/*	30 */	 private int ch = -99999999;
/*	31 */	 private int ci = -99999999;
/*	32 */	 private boolean cj = true;
/*	33 */	 public int lastSentExp = -99999999;
/*	34 */	 public int invulnerableTicks = 60;
/*	35 */	 private int cm = 0;
/*	36 */	 private int cn = 0;
/*	37 */	 private boolean co = true;
/*	38 */	 private ItemStack[] cp = { null, null, null, null, null };
/*	39 */	 private int containerCounter = 0;
/*		 */	 public boolean h;
/*		 */	 public int ping;
/*	42 */	 public boolean viewingCredits = false;
/*		 */	 public String displayName;
/*		 */	 public String listName;
/*		 */	 public Location compassTarget;
/*	47 */	 public int newExp = 0;
/*	48 */	 public int newLevel = 0;
/*	49 */	 public int newTotalExp = 0;
/*	50 */	 public boolean keepLevel = false;
/*		 */ 
/* 768 */	 public long timeOffset = 0L;
/* 769 */	 public boolean relativeTime = true;
/*		 */ 
/*		 */	 public EntityPlayer(MinecraftServer minecraftserver, World world, String s, ItemInWorldManager iteminworldmanager)
/*		 */	 {
/*	54 */		 super(world);
/*	55 */		 iteminworldmanager.player = this;
/*	56 */		 this.itemInWorldManager = iteminworldmanager;
/*	57 */		 this.cm = minecraftserver.getServerConfigurationManager().o();
/*	58 */		 ChunkCoordinates chunkcoordinates = world.getSpawn();
/*	59 */		 int i = chunkcoordinates.x;
/*	60 */		 int j = chunkcoordinates.z;
/*	61 */		 int k = chunkcoordinates.y;
/*		 */ 
/*	63 */		 if ((!world.worldProvider.e) && (world.getWorldData().getGameType() != EnumGamemode.ADVENTURE)) {
/*	64 */			 i += this.random.nextInt(20) - 10;
/*	65 */			 k = world.h(i, j);
/*	66 */			 j += this.random.nextInt(20) - 10;
/*		 */		 }
/*		 */ 
/*	69 */		 setPositionRotation(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);
/*	70 */		 this.server = minecraftserver;
/*	71 */		 this.W = 0.0F;
/*	72 */		 this.name = s;
/*	73 */		 this.height = 0.0F;
/*	74 */		 this.displayName = this.name;
/*	75 */		 this.listName = this.name;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/*	79 */		 super.a(nbttagcompound);
/*	80 */		 if (nbttagcompound.hasKey("playerGameType")) {
/*	81 */			 this.itemInWorldManager.setGameMode(EnumGamemode.a(nbttagcompound.getInt("playerGameType")));
/*		 */		 }
/*	83 */		 getBukkitEntity().readExtraData(nbttagcompound);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/*	87 */		 super.b(nbttagcompound);
/*	88 */		 nbttagcompound.setInt("playerGameType", this.itemInWorldManager.getGameMode().a());
/*	89 */		 getBukkitEntity().setExtraData(nbttagcompound);
/*		 */	 }
/*		 */ 
/*		 */	 public void spawnIn(World world)
/*		 */	 {
/*	94 */		 super.spawnIn(world);
/*	95 */		 if (world == null) {
/*	96 */			 this.dead = false;
/*	97 */			 ChunkCoordinates position = null;
/*	98 */			 if ((this.spawnWorld != null) && (!this.spawnWorld.equals(""))) {
/*	99 */				 CraftWorld cworld = (CraftWorld)Bukkit.getServer().getWorld(this.spawnWorld);
/* 100 */				 if ((cworld != null) && (getBed() != null)) {
/* 101 */					 world = cworld.getHandle();
/* 102 */					 position = EntityHuman.getBed(cworld.getHandle(), getBed());
/*		 */				 }
/*		 */			 }
/* 105 */			 if ((world == null) || (position == null)) {
/* 106 */				 world = ((CraftWorld)Bukkit.getServer().getWorlds().get(0)).getHandle();
/* 107 */				 position = world.getSpawn();
/*		 */			 }
/* 109 */			 this.world = world;
/* 110 */			 setPosition(position.x + 0.5D, position.y, position.z + 0.5D);
/*		 */		 }
/* 112 */		 this.dimension = ((WorldServer)this.world).dimension;
/* 113 */		 this.itemInWorldManager.a((WorldServer)world);
/*		 */	 }
/*		 */ 
/*		 */	 public void levelDown(int i)
/*		 */	 {
/* 118 */		 super.levelDown(i);
/* 119 */		 this.lastSentExp = -1;
/*		 */	 }
/*		 */ 
/*		 */	 public void syncInventory() {
/* 123 */		 this.activeContainer.addSlotListener(this);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack[] getEquipment() {
/* 127 */		 return this.cp;
/*		 */	 }
/*		 */ 
/*		 */	 protected void d_() {
/* 131 */		 this.height = 0.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public float getHeadHeight() {
/* 135 */		 return 1.62F;
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/* 139 */		 this.itemInWorldManager.a();
/* 140 */		 this.invulnerableTicks -= 1;
/* 141 */		 this.activeContainer.b();
/*		 */ 
/* 145 */		 for (int i = 0; i < 5; i++) {
/* 146 */			 ItemStack itemstack = b(i);
/*		 */ 
/* 148 */			 if (itemstack != this.cp[i]) {
/* 149 */				 q().getTracker().a(this, new Packet5EntityEquipment(this.id, i, itemstack));
/* 150 */				 this.cp[i] = itemstack;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 154 */		 if (!this.chunkCoordIntPairQueue.isEmpty()) {
/* 155 */			 ArrayList arraylist = new ArrayList();
/* 156 */			 Iterator iterator = this.chunkCoordIntPairQueue.iterator();
/* 157 */			 ArrayList arraylist1 = new ArrayList();
/*		 */ 
/* 159 */			 while ((iterator.hasNext()) && (arraylist.size() < 5)) {
/* 160 */				 ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair)iterator.next();
/*		 */ 
/* 162 */				 iterator.remove();
/* 163 */				 if ((chunkcoordintpair != null) && (this.world.isLoaded(chunkcoordintpair.x << 4, 0, chunkcoordintpair.z << 4))) {
/* 164 */					 arraylist.add(this.world.getChunkAt(chunkcoordintpair.x, chunkcoordintpair.z));
/* 165 */					 arraylist1.addAll(((WorldServer)this.world).getTileEntities(chunkcoordintpair.x * 16, 0, chunkcoordintpair.z * 16, chunkcoordintpair.x * 16 + 16, 256, chunkcoordintpair.z * 16 + 16));
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 169 */			 if (!arraylist.isEmpty())
/*		 */			 {
/* 171 */				 for (Iterator i$ = arraylist.iterator(); i$.hasNext(); ) { Object object = i$.next();
/* 172 */					 this.netServerHandler.sendPacket(new Packet51MapChunk((Chunk)object, true, 65535));
/*		 */				 }
/*		 */ 
/* 177 */				 Iterator iterator1 = arraylist1.iterator();
/*		 */ 
/* 179 */				 while (iterator1.hasNext()) {
/* 180 */					 TileEntity tileentity = (TileEntity)iterator1.next();
/*		 */ 
/* 182 */					 a(tileentity);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 187 */		 if (!this.g.isEmpty()) {
/* 188 */			 i = Math.min(this.g.size(), 127);
/* 189 */			 int[] aint = new int[i];
/* 190 */			 Iterator iterator2 = this.g.iterator();
/* 191 */			 int j = 0;
/*		 */ 
/* 193 */			 while ((iterator2.hasNext()) && (j < i)) {
/* 194 */				 aint[(j++)] = ((Integer)iterator2.next()).intValue();
/* 195 */				 iterator2.remove();
/*		 */			 }
/*		 */ 
/* 198 */			 this.netServerHandler.sendPacket(new Packet29DestroyEntity(aint));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void g() {
/* 203 */		 super.h_();
/*		 */ 
/* 205 */		 for (int i = 0; i < this.inventory.getSize(); i++) {
/* 206 */			 ItemStack itemstack = this.inventory.getItem(i);
/*		 */ 
/* 208 */			 if ((itemstack != null) && (Item.byId[itemstack.id].m_()) && (this.netServerHandler.lowPriorityCount() <= 2)) {
/* 209 */				 Packet packet = ((ItemWorldMapBase)Item.byId[itemstack.id]).c(itemstack, this.world, this);
/*		 */ 
/* 211 */				 if (packet != null) {
/* 212 */					 this.netServerHandler.sendPacket(packet);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 217 */		 if (this.bX)
/*		 */		 {
/* 219 */			 if (this.activeContainer != this.defaultContainer) {
/* 220 */				 closeInventory();
/*		 */			 }
/*		 */ 
/* 223 */			 if (this.vehicle != null) {
/* 224 */				 mount(this.vehicle);
/*		 */			 } else {
/* 226 */				 this.bY += 0.0125F;
/* 227 */				 if (this.bY >= 1.0F) {
/* 228 */					 this.bY = 1.0F;
/* 229 */					 this.bW = 10;
/* 230 */					 boolean flag = false;
/*		 */					 byte b0;
/*		 */					 byte b0;
/* 233 */					 if (this.dimension == -1)
/* 234 */						 b0 = 0;
/*		 */					 else {
/* 236 */						 b0 = -1;
/*		 */					 }
/*		 */ 
/* 239 */					 this.server.getServerConfigurationManager().changeDimension(this, b0);
/* 240 */					 this.lastSentExp = -1;
/* 241 */					 this.ch = -1;
/* 242 */					 this.ci = -1;
/* 243 */					 a(AchievementList.x);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 247 */			 this.bX = false;
/*		 */		 }
/*		 */		 else {
/* 250 */			 if (this.bY > 0.0F) {
/* 251 */				 this.bY -= 0.05F;
/*		 */			 }
/*		 */ 
/* 254 */			 if (this.bY < 0.0F) {
/* 255 */				 this.bY = 0.0F;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 259 */		 if (this.bW > 0) {
/* 260 */			 this.bW -= 1;
/*		 */		 }
/*		 */ 
/* 263 */		 if ((getHealth() == this.ch) && (this.ci == this.foodData.a())) { if ((this.foodData.e() == 0.0F) == this.cj); } else { this.netServerHandler.sendPacket(new Packet8UpdateHealth(getHealth(), this.foodData.a(), this.foodData.e()));
/* 265 */			 this.ch = getHealth();
/* 266 */			 this.ci = this.foodData.a();
/* 267 */			 this.cj = (this.foodData.e() == 0.0F);
/*		 */		 }
/*		 */ 
/* 270 */		 if (this.expTotal != this.lastSentExp) {
/* 271 */			 this.lastSentExp = this.expTotal;
/* 272 */			 this.netServerHandler.sendPacket(new Packet43SetExperience(this.exp, this.expTotal, this.expLevel));
/*		 */		 }
/*		 */ 
/* 276 */		 if (this.oldLevel == -1) {
/* 277 */			 this.oldLevel = this.expLevel;
/*		 */		 }
/*		 */ 
/* 280 */		 if (this.oldLevel != this.expLevel) {
/* 281 */			 CraftEventFactory.callPlayerLevelChangeEvent(this.world.getServer().getPlayer(this), this.oldLevel, this.expLevel);
/* 282 */			 this.oldLevel = this.expLevel;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack b(int i)
/*		 */	 {
/* 288 */		 return i == 0 ? this.inventory.getItemInHand() : this.inventory.armor[(i - 1)];
/*		 */	 }
/*		 */ 
/*		 */	 public void die(DamageSource damagesource)
/*		 */	 {
/* 293 */		 List loot = new ArrayList();
/*		 */ 
/* 295 */		 for (int i = 0; i < this.inventory.items.length; i++) {
/* 296 */			 if (this.inventory.items[i] != null) {
/* 297 */				 loot.add(new CraftItemStack(this.inventory.items[i]));
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 301 */		 for (int i = 0; i < this.inventory.armor.length; i++) {
/* 302 */			 if (this.inventory.armor[i] != null) {
/* 303 */				 loot.add(new CraftItemStack(this.inventory.armor[i]));
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 307 */		 PlayerDeathEvent event = CraftEventFactory.callPlayerDeathEvent(this, loot, damagesource.getLocalizedDeathMessage(this));
/*		 */ 
/* 309 */		 String deathMessage = event.getDeathMessage();
/*		 */ 
/* 311 */		 if ((deathMessage != null) && (deathMessage.length() > 0)) {
/* 312 */			 this.server.getServerConfigurationManager().sendAll(new Packet3Chat(event.getDeathMessage()));
/*		 */		 }
/*		 */ 
/* 316 */		 for (int i = 0; i < this.inventory.items.length; i++) {
/* 317 */			 this.inventory.items[i] = null;
/*		 */		 }
/*		 */ 
/* 320 */		 for (int i = 0; i < this.inventory.armor.length; i++) {
/* 321 */			 this.inventory.armor[i] = null;
/*		 */		 }
/*		 */ 
/* 324 */		 closeInventory();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i)
/*		 */	 {
/* 329 */		 if (this.invulnerableTicks > 0) {
/* 330 */			 return false;
/*		 */		 }
/*		 */ 
/* 333 */		 if ((!this.world.pvpMode) && ((damagesource instanceof EntityDamageSource))) {
/* 334 */			 Entity entity = damagesource.getEntity();
/*		 */ 
/* 336 */			 if ((entity instanceof EntityHuman)) {
/* 337 */				 return false;
/*		 */			 }
/*		 */ 
/* 340 */			 if ((entity instanceof EntityArrow)) {
/* 341 */				 EntityArrow entityarrow = (EntityArrow)entity;
/*		 */ 
/* 343 */				 if ((entityarrow.shooter instanceof EntityHuman)) {
/* 344 */					 return false;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 349 */		 return super.damageEntity(damagesource, i);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean h()
/*		 */	 {
/* 354 */		 return this.server.getPvP();
/*		 */	 }
/*		 */ 
/*		 */	 public void c(int i) {
/* 358 */		 if ((this.dimension == 1) && (i == 1)) {
/* 359 */			 a(AchievementList.C);
/* 360 */			 this.world.kill(this);
/* 361 */			 this.viewingCredits = true;
/* 362 */			 this.netServerHandler.sendPacket(new Packet70Bed(4, 0));
/*		 */		 } else {
/* 364 */			 a(AchievementList.B);
/*		 */ 
/* 373 */			 this.server.getServerConfigurationManager().changeDimension(this, 1);
/* 374 */			 this.lastSentExp = -1;
/* 375 */			 this.ch = -1;
/* 376 */			 this.ci = -1;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void a(TileEntity tileentity) {
/* 381 */		 if (tileentity != null) {
/* 382 */			 Packet packet = tileentity.e();
/*		 */ 
/* 384 */			 if (packet != null)
/* 385 */				 this.netServerHandler.sendPacket(packet);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void receive(Entity entity, int i)
/*		 */	 {
/* 391 */		 if (!entity.dead) {
/* 392 */			 EntityTracker entitytracker = q().getTracker();
/*		 */ 
/* 394 */			 if ((entity instanceof EntityItem)) {
/* 395 */				 entitytracker.a(entity, new Packet22Collect(entity.id, this.id));
/*		 */			 }
/*		 */ 
/* 398 */			 if ((entity instanceof EntityArrow)) {
/* 399 */				 entitytracker.a(entity, new Packet22Collect(entity.id, this.id));
/*		 */			 }
/*		 */ 
/* 402 */			 if ((entity instanceof EntityExperienceOrb)) {
/* 403 */				 entitytracker.a(entity, new Packet22Collect(entity.id, this.id));
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 407 */		 super.receive(entity, i);
/* 408 */		 this.activeContainer.b();
/*		 */	 }
/*		 */ 
/*		 */	 public void i() {
/* 412 */		 if (!this.bH) {
/* 413 */			 this.bI = -1;
/* 414 */			 this.bH = true;
/* 415 */			 q().getTracker().a(this, new Packet18ArmAnimation(this, 1));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public EnumBedResult a(int i, int j, int k) {
/* 420 */		 EnumBedResult enumbedresult = super.a(i, j, k);
/*		 */ 
/* 422 */		 if (enumbedresult == EnumBedResult.OK) {
/* 423 */			 Packet17EntityLocationAction packet17entitylocationaction = new Packet17EntityLocationAction(this, 0, i, j, k);
/*		 */ 
/* 425 */			 q().getTracker().a(this, packet17entitylocationaction);
/* 426 */			 this.netServerHandler.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
/* 427 */			 this.netServerHandler.sendPacket(packet17entitylocationaction);
/*		 */		 }
/*		 */ 
/* 430 */		 return enumbedresult;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(boolean flag, boolean flag1, boolean flag2) {
/* 434 */		 if ((this.fauxSleeping) && (!this.sleeping)) return;
/*		 */ 
/* 436 */		 if (isSleeping()) {
/* 437 */			 q().getTracker().sendPacketToEntity(this, new Packet18ArmAnimation(this, 3));
/*		 */		 }
/*		 */ 
/* 440 */		 super.a(flag, flag1, flag2);
/* 441 */		 if (this.netServerHandler != null)
/* 442 */			 this.netServerHandler.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
/*		 */	 }
/*		 */ 
/*		 */	 public void mount(Entity entity)
/*		 */	 {
/* 448 */		 setPassengerOf(entity);
/*		 */	 }
/*		 */ 
/*		 */	 public void setPassengerOf(Entity entity)
/*		 */	 {
/* 455 */		 super.setPassengerOf(entity);
/*		 */ 
/* 458 */		 this.netServerHandler.sendPacket(new Packet39AttachEntity(this, this.vehicle));
/* 459 */		 this.netServerHandler.a(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
/*		 */	 }
/*		 */	 protected void a(double d0, boolean flag) {
/*		 */	 }
/*		 */ 
/*		 */	 public void b(double d0, boolean flag) {
/* 465 */		 super.a(d0, flag);
/*		 */	 }
/*		 */ 
/*		 */	 public int nextContainerCounter() {
/* 469 */		 this.containerCounter = (this.containerCounter % 100 + 1);
/* 470 */		 return this.containerCounter;
/*		 */	 }
/*		 */ 
/*		 */	 public void startCrafting(int i, int j, int k)
/*		 */	 {
/* 475 */		 Container container = CraftEventFactory.callInventoryOpenEvent(this, new ContainerWorkbench(this.inventory, this.world, i, j, k));
/* 476 */		 if (container == null) return;
/*		 */ 
/* 479 */		 nextContainerCounter();
/* 480 */		 this.netServerHandler.sendPacket(new Packet100OpenWindow(this.containerCounter, 1, "Crafting", 9));
/* 481 */		 this.activeContainer = container;
/* 482 */		 this.activeContainer.windowId = this.containerCounter;
/* 483 */		 this.activeContainer.addSlotListener(this);
/*		 */	 }
/*		 */ 
/*		 */	 public void startEnchanting(int i, int j, int k)
/*		 */	 {
/* 488 */		 Container container = CraftEventFactory.callInventoryOpenEvent(this, new ContainerEnchantTable(this.inventory, this.world, i, j, k));
/* 489 */		 if (container == null) return;
/*		 */ 
/* 492 */		 nextContainerCounter();
/* 493 */		 this.netServerHandler.sendPacket(new Packet100OpenWindow(this.containerCounter, 4, "Enchanting", 9));
/* 494 */		 this.activeContainer = container;
/* 495 */		 this.activeContainer.windowId = this.containerCounter;
/* 496 */		 this.activeContainer.addSlotListener(this);
/*		 */	 }
/*		 */ 
/*		 */	 public void openContainer(IInventory iinventory) {
/* 500 */		 if (this.activeContainer != this.defaultContainer) {
/* 501 */			 closeInventory();
/*		 */		 }
/*		 */ 
/* 505 */		 Container container = CraftEventFactory.callInventoryOpenEvent(this, new ContainerChest(this.inventory, iinventory));
/* 506 */		 if (container == null) return;
/*		 */ 
/* 509 */		 nextContainerCounter();
/* 510 */		 this.netServerHandler.sendPacket(new Packet100OpenWindow(this.containerCounter, 0, iinventory.getName(), iinventory.getSize()));
/* 511 */		 this.activeContainer = container;
/* 512 */		 this.activeContainer.windowId = this.containerCounter;
/* 513 */		 this.activeContainer.addSlotListener(this);
/*		 */	 }
/*		 */ 
/*		 */	 public void openFurnace(TileEntityFurnace tileentityfurnace)
/*		 */	 {
/* 518 */		 Container container = CraftEventFactory.callInventoryOpenEvent(this, new ContainerFurnace(this.inventory, tileentityfurnace));
/* 519 */		 if (container == null) return;
/*		 */ 
/* 522 */		 nextContainerCounter();
/* 523 */		 this.netServerHandler.sendPacket(new Packet100OpenWindow(this.containerCounter, 2, tileentityfurnace.getName(), tileentityfurnace.getSize()));
/* 524 */		 this.activeContainer = container;
/* 525 */		 this.activeContainer.windowId = this.containerCounter;
/* 526 */		 this.activeContainer.addSlotListener(this);
/*		 */	 }
/*		 */ 
/*		 */	 public void openDispenser(TileEntityDispenser tileentitydispenser)
/*		 */	 {
/* 531 */		 Container container = CraftEventFactory.callInventoryOpenEvent(this, new ContainerDispenser(this.inventory, tileentitydispenser));
/* 532 */		 if (container == null) return;
/*		 */ 
/* 535 */		 nextContainerCounter();
/* 536 */		 this.netServerHandler.sendPacket(new Packet100OpenWindow(this.containerCounter, 3, tileentitydispenser.getName(), tileentitydispenser.getSize()));
/* 537 */		 this.activeContainer = container;
/* 538 */		 this.activeContainer.windowId = this.containerCounter;
/* 539 */		 this.activeContainer.addSlotListener(this);
/*		 */	 }
/*		 */ 
/*		 */	 public void openBrewingStand(TileEntityBrewingStand tileentitybrewingstand)
/*		 */	 {
/* 544 */		 Container container = CraftEventFactory.callInventoryOpenEvent(this, new ContainerBrewingStand(this.inventory, tileentitybrewingstand));
/* 545 */		 if (container == null) return;
/*		 */ 
/* 548 */		 nextContainerCounter();
/* 549 */		 this.netServerHandler.sendPacket(new Packet100OpenWindow(this.containerCounter, 5, tileentitybrewingstand.getName(), tileentitybrewingstand.getSize()));
/* 550 */		 this.activeContainer = container;
/* 551 */		 this.activeContainer.windowId = this.containerCounter;
/* 552 */		 this.activeContainer.addSlotListener(this);
/*		 */	 }
/*		 */ 
/*		 */	 public void openTrade(IMerchant imerchant)
/*		 */	 {
/* 557 */		 Container container = CraftEventFactory.callInventoryOpenEvent(this, new ContainerMerchant(this.inventory, imerchant, this.world));
/* 558 */		 if (container == null) return;
/*		 */ 
/* 561 */		 nextContainerCounter();
/* 562 */		 this.activeContainer = container;
/* 563 */		 this.activeContainer.windowId = this.containerCounter;
/* 564 */		 this.activeContainer.addSlotListener(this);
/* 565 */		 InventoryMerchant inventorymerchant = ((ContainerMerchant)this.activeContainer).getMerchantInventory();
/*		 */ 
/* 567 */		 this.netServerHandler.sendPacket(new Packet100OpenWindow(this.containerCounter, 6, inventorymerchant.getName(), inventorymerchant.getSize()));
/* 568 */		 MerchantRecipeList merchantrecipelist = imerchant.getOffers(this);
/*		 */ 
/* 570 */		 if (merchantrecipelist != null)
/*		 */			 try {
/* 572 */				 ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
/* 573 */				 DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
/*		 */ 
/* 575 */				 dataoutputstream.writeInt(this.containerCounter);
/* 576 */				 merchantrecipelist.a(dataoutputstream);
/* 577 */				 this.netServerHandler.sendPacket(new Packet250CustomPayload("MC|TrList", bytearrayoutputstream.toByteArray()));
/*		 */			 } catch (IOException ioexception) {
/* 579 */				 ioexception.printStackTrace();
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Container container, int i, ItemStack itemstack)
/*		 */	 {
/* 585 */		 if ((!(container.getSlot(i) instanceof SlotResult)) && 
/* 586 */			 (!this.h))
/* 587 */			 this.netServerHandler.sendPacket(new Packet103SetSlot(container.windowId, i, itemstack));
/*		 */	 }
/*		 */ 
/*		 */	 public void updateInventory(Container container)
/*		 */	 {
/* 593 */		 a(container, container.a());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Container container, List list) {
/* 597 */		 this.netServerHandler.sendPacket(new Packet104WindowItems(container.windowId, list));
/* 598 */		 this.netServerHandler.sendPacket(new Packet103SetSlot(-1, -1, this.inventory.getCarried()));
/*		 */ 
/* 600 */		 if (EnumSet.of(InventoryType.CRAFTING, InventoryType.WORKBENCH).contains(container.getBukkitView().getType()))
/* 601 */			 this.netServerHandler.sendPacket(new Packet103SetSlot(container.windowId, 0, container.getSlot(0).getItem()));
/*		 */	 }
/*		 */ 
/*		 */	 public void setContainerData(Container container, int i, int j)
/*		 */	 {
/* 607 */		 this.netServerHandler.sendPacket(new Packet105CraftProgressBar(container.windowId, i, j));
/*		 */	 }
/*		 */ 
/*		 */	 public void closeInventory() {
/* 611 */		 this.netServerHandler.sendPacket(new Packet101CloseWindow(this.activeContainer.windowId));
/* 612 */		 l();
/*		 */	 }
/*		 */ 
/*		 */	 public void broadcastCarriedItem() {
/* 616 */		 if (!this.h)
/* 617 */			 this.netServerHandler.sendPacket(new Packet103SetSlot(-1, -1, this.inventory.getCarried()));
/*		 */	 }
/*		 */ 
/*		 */	 public void l()
/*		 */	 {
/* 622 */		 this.activeContainer.a(this);
/* 623 */		 this.activeContainer = this.defaultContainer;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Statistic statistic, int i) {
/* 627 */		 if ((statistic != null) && 
/* 628 */			 (!statistic.f)) {
/* 629 */			 while (i > 100) {
/* 630 */				 this.netServerHandler.sendPacket(new Packet200Statistic(statistic.e, 100));
/* 631 */				 i -= 100;
/*		 */			 }
/*		 */ 
/* 634 */			 this.netServerHandler.sendPacket(new Packet200Statistic(statistic.e, i));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void m()
/*		 */	 {
/* 640 */		 if (this.vehicle != null) {
/* 641 */			 mount(this.vehicle);
/*		 */		 }
/*		 */ 
/* 644 */		 if (this.passenger != null) {
/* 645 */			 this.passenger.mount(this);
/*		 */		 }
/*		 */ 
/* 648 */		 if (this.sleeping)
/* 649 */			 a(true, false, false);
/*		 */	 }
/*		 */ 
/*		 */	 public void n()
/*		 */	 {
/* 654 */		 this.ch = -99999999;
/* 655 */		 this.lastSentExp = -1;
/*		 */	 }
/*		 */ 
/*		 */	 public void c(String s) {
/* 659 */		 LocaleLanguage localelanguage = LocaleLanguage.a();
/* 660 */		 String s1 = localelanguage.b(s);
/*		 */ 
/* 662 */		 this.netServerHandler.sendPacket(new Packet3Chat(s1));
/*		 */	 }
/*		 */ 
/*		 */	 protected void o() {
/* 666 */		 this.netServerHandler.sendPacket(new Packet38EntityStatus(this.id, 9));
/* 667 */		 super.o();
/*		 */	 }
/*		 */ 
/*		 */	 public void a(ItemStack itemstack, int i) {
/* 671 */		 super.a(itemstack, i);
/* 672 */		 if ((itemstack != null) && (itemstack.getItem() != null) && (itemstack.getItem().b(itemstack) == EnumAnimation.b))
/* 673 */			 q().getTracker().sendPacketToEntity(this, new Packet18ArmAnimation(this, 5));
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(MobEffect mobeffect)
/*		 */	 {
/* 678 */		 super.a(mobeffect);
/* 679 */		 this.netServerHandler.sendPacket(new Packet41MobEffect(this.id, mobeffect));
/*		 */	 }
/*		 */ 
/*		 */	 protected void b(MobEffect mobeffect) {
/* 683 */		 super.b(mobeffect);
/* 684 */		 this.netServerHandler.sendPacket(new Packet41MobEffect(this.id, mobeffect));
/*		 */	 }
/*		 */ 
/*		 */	 protected void c(MobEffect mobeffect) {
/* 688 */		 super.c(mobeffect);
/* 689 */		 this.netServerHandler.sendPacket(new Packet42RemoveMobEffect(this.id, mobeffect));
/*		 */	 }
/*		 */ 
/*		 */	 public void enderTeleportTo(double d0, double d1, double d2) {
/* 693 */		 this.netServerHandler.a(d0, d1, d2, this.yaw, this.pitch);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(Entity entity) {
/* 697 */		 q().getTracker().sendPacketToEntity(this, new Packet18ArmAnimation(entity, 6));
/*		 */	 }
/*		 */ 
/*		 */	 public void c(Entity entity) {
/* 701 */		 q().getTracker().sendPacketToEntity(this, new Packet18ArmAnimation(entity, 7));
/*		 */	 }
/*		 */ 
/*		 */	 public void updateAbilities() {
/* 705 */		 if (this.netServerHandler != null)
/* 706 */			 this.netServerHandler.sendPacket(new Packet202Abilities(this.abilities));
/*		 */	 }
/*		 */ 
/*		 */	 public WorldServer q()
/*		 */	 {
/* 711 */		 return (WorldServer)this.world;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EnumGamemode enumgamemode) {
/* 715 */		 this.itemInWorldManager.setGameMode(enumgamemode);
/* 716 */		 this.netServerHandler.sendPacket(new Packet70Bed(3, enumgamemode.a()));
/*		 */	 }
/*		 */ 
/*		 */	 public void sendMessage(String s) {
/* 720 */		 this.netServerHandler.sendPacket(new Packet3Chat(s));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b(String s) {
/* 724 */		 return ("seed".equals(s)) && (!this.server.S()) ? true : this.server.getServerConfigurationManager().isOp(this.name);
/*		 */	 }
/*		 */ 
/*		 */	 public String r() {
/* 728 */		 String s = this.netServerHandler.networkManager.getSocketAddress().toString();
/*		 */ 
/* 730 */		 s = s.substring(s.indexOf("/") + 1);
/* 731 */		 s = s.substring(0, s.indexOf(":"));
/* 732 */		 return s;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet204LocaleAndViewDistance packet204localeandviewdistance) {
/* 736 */		 if (this.locale.b().containsKey(packet204localeandviewdistance.d())) {
/* 737 */			 this.locale.a(packet204localeandviewdistance.d());
/*		 */		 }
/*		 */ 
/* 740 */		 int i = 256 >> packet204localeandviewdistance.f();
/*		 */ 
/* 742 */		 if ((i > 3) && (i < 15)) {
/* 743 */			 this.cm = i;
/*		 */		 }
/*		 */ 
/* 746 */		 this.cn = packet204localeandviewdistance.g();
/* 747 */		 this.co = packet204localeandviewdistance.h();
/* 748 */		 if ((this.server.H()) && (this.server.G().equals(this.name)))
/* 749 */			 this.server.c(packet204localeandviewdistance.i());
/*		 */	 }
/*		 */ 
/*		 */	 public LocaleLanguage getLocale()
/*		 */	 {
/* 754 */		 return this.locale;
/*		 */	 }
/*		 */ 
/*		 */	 public int getChatFlags() {
/* 758 */		 return this.cn;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String s, int i) {
/* 762 */		 String s1 = s + "" + i;
/*		 */ 
/* 764 */		 this.netServerHandler.sendPacket(new Packet250CustomPayload("MC|TPack", s1.getBytes()));
/*		 */	 }
/*		 */ 
/*		 */	 public long getPlayerTime()
/*		 */	 {
/* 772 */		 if (this.relativeTime)
/*		 */		 {
/* 774 */			 return this.world.getTime() + this.timeOffset;
/*		 */		 }
/*		 */ 
/* 777 */		 return this.world.getTime() - this.world.getTime() % 24000L + this.timeOffset;
/*		 */	 }
/*		 */ 
/*		 */	 public String toString()
/*		 */	 {
/* 783 */		 return super.toString() + "(" + this.name + " at " + this.locX + "," + this.locY + "," + this.locZ + ")";
/*		 */	 }
/*		 */ 
/*		 */	 public void reset() {
/* 787 */		 float exp = 0.0F;
/* 788 */		 if (this.keepLevel) {
/* 789 */			 exp = this.exp;
/* 790 */			 this.newTotalExp = this.expTotal;
/* 791 */			 this.newLevel = this.expLevel;
/*		 */		 }
/*		 */ 
/* 794 */		 this.health = 20;
/* 795 */		 this.fireTicks = 0;
/* 796 */		 this.fallDistance = 0.0F;
/* 797 */		 this.foodData = new FoodMetaData();
/* 798 */		 this.expLevel = this.newLevel;
/* 799 */		 this.expTotal = this.newTotalExp;
/* 800 */		 this.exp = 0.0F;
/* 801 */		 this.deathTicks = 0;
/* 802 */		 this.effects.clear();
/* 803 */		 this.activeContainer = this.defaultContainer;
/* 804 */		 this.lastSentExp = -1;
/* 805 */		 if (this.keepLevel)
/* 806 */			 this.exp = exp;
/*		 */		 else {
/* 808 */			 giveExp(this.newExp);
/*		 */		 }
/* 810 */		 this.keepLevel = false;
/*		 */	 }
/*		 */ 
/*		 */	 public CraftPlayer getBukkitEntity()
/*		 */	 {
/* 815 */		 return (CraftPlayer)super.getBukkitEntity();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityPlayer
 * JD-Core Version:		0.6.0
 */