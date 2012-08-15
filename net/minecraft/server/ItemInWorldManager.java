/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.Event.Result;
/*		 */ import org.bukkit.event.block.Action;
/*		 */ import org.bukkit.event.block.BlockBreakEvent;
/*		 */ import org.bukkit.event.block.BlockDamageEvent;
/*		 */ import org.bukkit.event.player.PlayerInteractEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class ItemInWorldManager
/*		 */ {
/*		 */	 public World world;
/*		 */	 public EntityPlayer player;
/*		 */	 private EnumGamemode gamemode;
/*		 */	 private boolean d;
/*		 */	 private int lastDigTick;
/*		 */	 private int f;
/*		 */	 private int g;
/*		 */	 private int h;
/*		 */	 private int currentTick;
/*		 */	 private boolean j;
/*		 */	 private int k;
/*		 */	 private int l;
/*		 */	 private int m;
/*		 */	 private int n;
/*		 */	 private int o;
/*		 */ 
/*		 */	 public ItemInWorldManager(World world)
/*		 */	 {
/*	30 */		 this.gamemode = EnumGamemode.NONE;
/*	31 */		 this.o = -1;
/*	32 */		 this.world = world;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemInWorldManager(WorldServer world)
/*		 */	 {
/*	37 */		 this(world);
/*		 */	 }
/*		 */ 
/*		 */	 public void setGameMode(EnumGamemode enumgamemode)
/*		 */	 {
/*	42 */		 this.gamemode = enumgamemode;
/*	43 */		 enumgamemode.a(this.player.abilities);
/*	44 */		 this.player.updateAbilities();
/*		 */	 }
/*		 */ 
/*		 */	 public EnumGamemode getGameMode() {
/*	48 */		 return this.gamemode;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isCreative() {
/*	52 */		 return this.gamemode.d();
/*		 */	 }
/*		 */ 
/*		 */	 public void b(EnumGamemode enumgamemode) {
/*	56 */		 if (this.gamemode == EnumGamemode.NONE) {
/*	57 */			 this.gamemode = enumgamemode;
/*		 */		 }
/*		 */ 
/*	60 */		 setGameMode(this.gamemode);
/*		 */	 }
/*		 */ 
/*		 */	 public void a() {
/*	64 */		 this.currentTick = (int)(System.currentTimeMillis() / 50L);
/*		 */ 
/*	69 */		 if (this.j) {
/*	70 */			 int i = this.currentTick - this.n;
/*	71 */			 int k = this.world.getTypeId(this.k, this.l, this.m);
/*		 */ 
/*	73 */			 if (k == 0) {
/*	74 */				 this.j = false;
/*		 */			 } else {
/*	76 */				 Block block = Block.byId[k];
/*		 */ 
/*	78 */				 float f = block.getDamage(this.player, this.player.world, this.k, this.l, this.m) * (i + 1);
/*	79 */				 int j = (int)(f * 10.0F);
/*	80 */				 if (j != this.o) {
/*	81 */					 this.world.f(this.player.id, this.k, this.l, this.m, j);
/*	82 */					 this.o = j;
/*		 */				 }
/*		 */ 
/*	85 */				 if (f >= 1.0F) {
/*	86 */					 this.j = false;
/*	87 */					 breakBlock(this.k, this.l, this.m);
/*		 */				 }
/*		 */			 }
/*	90 */		 } else if (this.d) {
/*	91 */			 int i = this.world.getTypeId(this.f, this.g, this.h);
/*	92 */			 Block block1 = Block.byId[i];
/*		 */ 
/*	94 */			 if (block1 == null) {
/*	95 */				 this.world.f(this.player.id, this.f, this.g, this.h, -1);
/*	96 */				 this.o = -1;
/*	97 */				 this.d = false;
/*		 */			 } else {
/*	99 */				 int l = this.currentTick - this.lastDigTick;
/*		 */ 
/* 101 */				 float f = block1.getDamage(this.player, this.player.world, this.f, this.g, this.h) * (l + 1);
/* 102 */				 int j = (int)(f * 10.0F);
/* 103 */				 if (j != this.o) {
/* 104 */					 this.world.f(this.player.id, this.f, this.g, this.h, j);
/* 105 */					 this.o = j;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void dig(int i, int j, int k, int l)
/*		 */	 {
/* 114 */		 PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(this.player, Action.LEFT_CLICK_BLOCK, i, j, k, l, this.player.inventory.getItemInHand());
/*		 */ 
/* 116 */		 if (!this.gamemode.isAdventure())
/*		 */		 {
/* 118 */			 if (event.isCancelled())
/*		 */			 {
/* 120 */				 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
/* 121 */				 return;
/*		 */			 }
/*		 */ 
/* 124 */			 if (isCreative()) {
/* 125 */				 if (!this.world.douseFire((EntityHuman)null, i, j, k, l))
/* 126 */					 breakBlock(i, j, k);
/*		 */			 }
/*		 */			 else {
/* 129 */				 this.world.douseFire(this.player, i, j, k, l);
/* 130 */				 this.lastDigTick = this.currentTick;
/* 131 */				 float f = 1.0F;
/* 132 */				 int i1 = this.world.getTypeId(i, j, k);
/*		 */ 
/* 134 */				 if (i1 <= 0) {
/* 135 */					 return;
/*		 */				 }
/*		 */ 
/* 138 */				 if (event.useInteractedBlock() == Event.Result.DENY)
/*		 */				 {
/* 140 */					 if (i1 == Block.WOODEN_DOOR.id)
/*		 */					 {
/* 142 */						 boolean bottom = (this.world.getData(i, j, k) & 0x8) == 0;
/* 143 */						 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
/* 144 */						 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j + (bottom ? 1 : -1), k, this.world));
/* 145 */					 } else if (i1 == Block.TRAP_DOOR.id) {
/* 146 */						 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
/*		 */					 }
/*		 */				 } else {
/* 149 */					 Block.byId[i1].attack(this.world, i, j, k, this.player);
/*		 */ 
/* 151 */					 this.world.douseFire((EntityHuman)null, i, j, k, l);
/*		 */				 }
/*		 */ 
/* 155 */				 float toolDamage = Block.byId[i1].getDamage(this.player, this.world, i, j, k);
/* 156 */				 if (event.useItemInHand() == Event.Result.DENY)
/*		 */				 {
/* 158 */					 if (toolDamage > 1.0F) {
/* 159 */						 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
/*		 */					 }
/* 161 */					 return;
/*		 */				 }
/* 163 */				 BlockDamageEvent blockEvent = CraftEventFactory.callBlockDamageEvent(this.player, i, j, k, this.player.inventory.getItemInHand(), toolDamage >= 1.0F);
/*		 */ 
/* 165 */				 if (blockEvent.isCancelled())
/*		 */				 {
/* 167 */					 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
/* 168 */					 return;
/*		 */				 }
/*		 */ 
/* 171 */				 if (blockEvent.getInstaBreak()) {
/* 172 */					 toolDamage = 2.0F;
/*		 */				 }
/*		 */ 
/* 175 */				 if (toolDamage >= 1.0F)
/*		 */				 {
/* 177 */					 breakBlock(i, j, k);
/*		 */				 } else {
/* 179 */					 this.d = true;
/* 180 */					 this.f = i;
/* 181 */					 this.g = j;
/* 182 */					 this.h = k;
/* 183 */					 int j1 = (int)(f * 10.0F);
/*		 */ 
/* 185 */					 this.world.f(this.player.id, i, j, k, j1);
/* 186 */					 this.o = j1;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int i, int j, int k) {
/* 193 */		 if ((i == this.f) && (j == this.g) && (k == this.h)) {
/* 194 */			 this.currentTick = (int)(System.currentTimeMillis() / 50L);
/* 195 */			 int l = this.currentTick - this.lastDigTick;
/* 196 */			 int i1 = this.world.getTypeId(i, j, k);
/*		 */ 
/* 198 */			 if (i1 != 0) {
/* 199 */				 Block block = Block.byId[i1];
/* 200 */				 float f = block.getDamage(this.player, this.player.world, i, j, k) * (l + 1);
/*		 */ 
/* 202 */				 if (f >= 0.7F) {
/* 203 */					 this.d = false;
/* 204 */					 this.world.f(this.player.id, i, j, k, -1);
/* 205 */					 breakBlock(i, j, k);
/* 206 */				 } else if (!this.j) {
/* 207 */					 this.d = false;
/* 208 */					 this.j = true;
/* 209 */					 this.k = i;
/* 210 */					 this.l = j;
/* 211 */					 this.m = k;
/* 212 */					 this.n = this.lastDigTick;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */		 else {
/* 217 */			 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void c(int i, int j, int k)
/*		 */	 {
/* 223 */		 this.d = false;
/* 224 */		 this.world.f(this.player.id, this.f, this.g, this.h, -1);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean d(int i, int j, int k) {
/* 228 */		 Block block = Block.byId[this.world.getTypeId(i, j, k)];
/* 229 */		 int l = this.world.getData(i, j, k);
/*		 */ 
/* 231 */		 if (block != null) {
/* 232 */			 block.a(this.world, i, j, k, l, this.player);
/*		 */		 }
/*		 */ 
/* 235 */		 boolean flag = this.world.setTypeId(i, j, k, 0);
/*		 */ 
/* 237 */		 if ((block != null) && (flag)) {
/* 238 */			 block.postBreak(this.world, i, j, k, l);
/*		 */		 }
/*		 */ 
/* 241 */		 return flag;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean breakBlock(int i, int j, int k)
/*		 */	 {
/* 246 */		 BlockBreakEvent event = null;
/*		 */ 
/* 248 */		 if ((this.player instanceof EntityPlayer)) {
/* 249 */			 org.bukkit.block.Block block = this.world.getWorld().getBlockAt(i, j, k);
/*		 */ 
/* 252 */			 if (this.world.getTileEntity(i, j, k) == null) {
/* 253 */				 Packet53BlockChange packet = new Packet53BlockChange(i, j, k, this.world);
/*		 */ 
/* 255 */				 packet.material = 0;
/* 256 */				 packet.data = 0;
/* 257 */				 this.player.netServerHandler.sendPacket(packet);
/*		 */			 }
/*		 */ 
/* 260 */			 event = new BlockBreakEvent(block, this.player.getBukkitEntity());
/*		 */ 
/* 263 */			 event.setCancelled(this.gamemode.isAdventure());
/*		 */ 
/* 266 */			 Block nmsBlock = Block.byId[block.getTypeId()];
/*		 */ 
/* 268 */			 if ((nmsBlock != null) && (!event.isCancelled()) && (!isCreative()) && (this.player.b(nmsBlock)))
/*		 */			 {
/* 270 */				 if ((!nmsBlock.q_()) || (!EnchantmentManager.hasSilkTouchEnchantment(this.player.inventory))) {
/* 271 */					 int data = block.getData();
/* 272 */					 int bonusLevel = EnchantmentManager.getBonusBlockLootEnchantmentLevel(this.player.inventory);
/*		 */ 
/* 274 */					 event.setExpToDrop(nmsBlock.getExpDrop(this.world, data, bonusLevel));
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 278 */			 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 280 */			 if (event.isCancelled())
/*		 */			 {
/* 282 */				 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
/* 283 */				 return false;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 291 */		 int l = this.world.getTypeId(i, j, k);
/* 292 */		 if (Block.byId[l] == null) return false;
/* 293 */		 int i1 = this.world.getData(i, j, k);
/*		 */ 
/* 295 */		 this.world.a(this.player, 2001, i, j, k, l + (this.world.getData(i, j, k) << 12));
/* 296 */		 boolean flag = d(i, j, k);
/*		 */ 
/* 298 */		 if (isCreative()) {
/* 299 */			 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, this.world));
/*		 */		 } else {
/* 301 */			 ItemStack itemstack = this.player.bC();
/* 302 */			 boolean flag1 = this.player.b(Block.byId[l]);
/*		 */ 
/* 304 */			 if (itemstack != null) {
/* 305 */				 itemstack.a(this.world, l, i, j, k, this.player);
/* 306 */				 if (itemstack.count == 0) {
/* 307 */					 this.player.bD();
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 311 */			 if ((flag) && (flag1)) {
/* 312 */				 Block.byId[l].a(this.world, this.player, i, j, k, i1);
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 317 */		 if ((flag) && (event != null)) {
/* 318 */			 Block.byId[l].g(this.world, i, j, k, event.getExpToDrop());
/*		 */		 }
/*		 */ 
/* 322 */		 return flag;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean useItem(EntityHuman entityhuman, World world, ItemStack itemstack)
/*		 */	 {
/* 327 */		 int i = itemstack.count;
/* 328 */		 int j = itemstack.getData();
/* 329 */		 ItemStack itemstack1 = itemstack.a(world, entityhuman);
/*		 */ 
/* 331 */		 if ((itemstack1 == itemstack) && ((itemstack1 == null) || (itemstack1.count == i)) && ((itemstack1 == null) || (itemstack1.m() <= 0))) {
/* 332 */			 return false;
/*		 */		 }
/* 334 */		 entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = itemstack1;
/* 335 */		 if (isCreative()) {
/* 336 */			 itemstack1.count = i;
/* 337 */			 itemstack1.setData(j);
/*		 */		 }
/*		 */ 
/* 340 */		 if (itemstack1.count == 0) {
/* 341 */			 entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = null;
/*		 */		 }
/*		 */ 
/* 344 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(EntityHuman entityhuman, World world, ItemStack itemstack, int i, int j, int k, int l, float f, float f1, float f2)
/*		 */	 {
/* 350 */		 int i1 = world.getTypeId(i, j, k);
/*		 */ 
/* 353 */		 boolean result = false;
/* 354 */		 if (i1 > 0) {
/* 355 */			 PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(entityhuman, Action.RIGHT_CLICK_BLOCK, i, j, k, l, itemstack);
/* 356 */			 if (event.useInteractedBlock() == Event.Result.DENY)
/*		 */			 {
/* 358 */				 if (i1 == Block.WOODEN_DOOR.id) {
/* 359 */					 boolean bottom = (world.getData(i, j, k) & 0x8) == 0;
/* 360 */					 ((EntityPlayer)entityhuman).netServerHandler.sendPacket(new Packet53BlockChange(i, j + (bottom ? 1 : -1), k, world));
/*		 */				 }
/* 362 */				 result = event.useItemInHand() != Event.Result.ALLOW;
/*		 */			 } else {
/* 364 */				 result = Block.byId[i1].interact(world, i, j, k, entityhuman, l, f, f1, f2);
/*		 */			 }
/*		 */ 
/* 367 */			 if ((itemstack != null) && (!result)) {
/* 368 */				 int j1 = itemstack.getData();
/* 369 */				 int k1 = itemstack.count;
/*		 */ 
/* 371 */				 result = itemstack.placeItem(entityhuman, world, i, j, k, l, f, f1, f2);
/*		 */ 
/* 374 */				 if (isCreative()) {
/* 375 */					 itemstack.setData(j1);
/* 376 */					 itemstack.count = k1;
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 381 */			 if ((itemstack != null) && (((!result) && (event.useItemInHand() != Event.Result.DENY)) || (event.useItemInHand() == Event.Result.ALLOW))) {
/* 382 */				 useItem(entityhuman, world, itemstack);
/*		 */			 }
/*		 */		 }
/* 385 */		 return result;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(WorldServer worldserver)
/*		 */	 {
/* 390 */		 this.world = worldserver;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemInWorldManager
 * JD-Core Version:		0.6.0
 */