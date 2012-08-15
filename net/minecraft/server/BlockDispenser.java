/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftItemStack;
/*		 */ import org.bukkit.event.block.BlockDispenseEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ import org.bukkit.util.Vector;
/*		 */ 
/*		 */ public class BlockDispenser extends BlockContainer
/*		 */ {
/*	12 */	 private Random a = new Random();
/*		 */ 
/*		 */	 protected BlockDispenser(int i) {
/*	15 */		 super(i, Material.STONE);
/*	16 */		 this.textureId = 45;
/*	17 */		 a(CreativeModeTab.d);
/*		 */	 }
/*		 */ 
/*		 */	 public int p_() {
/*	21 */		 return 4;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int i, Random random, int j) {
/*	25 */		 return Block.DISPENSER.id;
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World world, int i, int j, int k) {
/*	29 */		 super.onPlace(world, i, j, k);
/*	30 */		 l(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 private void l(World world, int i, int j, int k) {
/*	34 */		 if (!world.isStatic) {
/*	35 */			 int l = world.getTypeId(i, j, k - 1);
/*	36 */			 int i1 = world.getTypeId(i, j, k + 1);
/*	37 */			 int j1 = world.getTypeId(i - 1, j, k);
/*	38 */			 int k1 = world.getTypeId(i + 1, j, k);
/*	39 */			 byte b0 = 3;
/*		 */ 
/*	41 */			 if ((Block.n[l] != 0) && (Block.n[i1] == 0)) {
/*	42 */				 b0 = 3;
/*		 */			 }
/*		 */ 
/*	45 */			 if ((Block.n[i1] != 0) && (Block.n[l] == 0)) {
/*	46 */				 b0 = 2;
/*		 */			 }
/*		 */ 
/*	49 */			 if ((Block.n[j1] != 0) && (Block.n[k1] == 0)) {
/*	50 */				 b0 = 5;
/*		 */			 }
/*		 */ 
/*	53 */			 if ((Block.n[k1] != 0) && (Block.n[j1] == 0)) {
/*	54 */				 b0 = 4;
/*		 */			 }
/*		 */ 
/*	57 */			 world.setData(i, j, k, b0);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i) {
/*	62 */		 return i == 3 ? this.textureId + 1 : i == 0 ? this.textureId + 17 : i == 1 ? this.textureId + 17 : this.textureId;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
/*	66 */		 if (world.isStatic) {
/*	67 */			 return true;
/*		 */		 }
/*	69 */		 TileEntityDispenser tileentitydispenser = (TileEntityDispenser)world.getTileEntity(i, j, k);
/*		 */ 
/*	71 */		 if (tileentitydispenser != null) {
/*	72 */			 entityhuman.openDispenser(tileentitydispenser);
/*		 */		 }
/*		 */ 
/*	75 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void dispense(World world, int i, int j, int k, Random random)
/*		 */	 {
/*	81 */		 int l = world.getData(i, j, k);
/*	82 */		 byte b0 = 0;
/*	83 */		 byte b1 = 0;
/*		 */ 
/*	85 */		 if (l == 3)
/*	86 */			 b1 = 1;
/*	87 */		 else if (l == 2)
/*	88 */			 b1 = -1;
/*	89 */		 else if (l == 5)
/*	90 */			 b0 = 1;
/*		 */		 else {
/*	92 */			 b0 = -1;
/*		 */		 }
/*		 */ 
/*	95 */		 TileEntityDispenser tileentitydispenser = (TileEntityDispenser)world.getTileEntity(i, j, k);
/*		 */ 
/*	97 */		 if (tileentitydispenser != null) {
/*	98 */			 int i1 = tileentitydispenser.i();
/*		 */ 
/* 100 */			 if (i1 < 0) {
/* 101 */				 world.triggerEffect(1001, i, j, k, 0);
/*		 */			 } else {
/* 103 */				 double d0 = i + b0 * 0.6D + 0.5D;
/* 104 */				 double d1 = j + 0.5D;
/* 105 */				 double d2 = k + b1 * 0.6D + 0.5D;
/* 106 */				 ItemStack itemstack = tileentitydispenser.getItem(i1);
/*		 */ 
/* 110 */				 itemstack = itemstack.cloneItemStack();
/* 111 */				 itemstack.count = 1;
/*		 */ 
/* 113 */				 double d3 = random.nextDouble() * 0.1D + 0.2D;
/* 114 */				 double motX = b0 * d3;
/* 115 */				 double motY = 0.2000000029802322D;
/* 116 */				 double motZ = b1 * d3;
/* 117 */				 motX += random.nextGaussian() * 0.007499999832361937D * 6.0D;
/* 118 */				 motY += random.nextGaussian() * 0.007499999832361937D * 6.0D;
/* 119 */				 motZ += random.nextGaussian() * 0.007499999832361937D * 6.0D;
/*		 */ 
/* 121 */				 org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
/* 122 */				 org.bukkit.inventory.ItemStack bukkitItem = new CraftItemStack(itemstack).clone();
/*		 */ 
/* 124 */				 BlockDispenseEvent event = new BlockDispenseEvent(block, bukkitItem, new Vector(motX, motY, motZ));
/* 125 */				 world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 127 */				 if (event.isCancelled()) {
/* 128 */					 return;
/*		 */				 }
/*		 */ 
/* 131 */				 itemstack = CraftItemStack.createNMSItemStack(event.getItem());
/*		 */ 
/* 134 */				 int j1 = a(tileentitydispenser, world, itemstack, random, i, j, k, b0, b1, d0, d1, d2);
/*		 */ 
/* 136 */				 if (j1 == 1)
/*		 */				 {
/* 138 */					 if (event.getItem().equals(bukkitItem))
/*		 */					 {
/* 140 */						 tileentitydispenser.splitStack(i1, 1);
/*		 */					 }
/*		 */				 }
/* 143 */				 else if (j1 == 0)
/*		 */				 {
/* 145 */					 motX = event.getVelocity().getX();
/* 146 */					 motY = event.getVelocity().getY();
/* 147 */					 motZ = event.getVelocity().getZ();
/*		 */ 
/* 149 */					 if (event.getItem().equals(bukkitItem))
/*		 */					 {
/* 151 */						 tileentitydispenser.splitStack(i1, 1);
/*		 */					 }
/*		 */ 
/* 154 */					 EntityItem entityitem = new EntityItem(world, d0, d1 - 0.3D, d2, itemstack);
/* 155 */					 entityitem.motX = event.getVelocity().getX();
/* 156 */					 entityitem.motY = event.getVelocity().getY();
/* 157 */					 entityitem.motZ = event.getVelocity().getZ();
/* 158 */					 world.addEntity(entityitem);
/*		 */ 
/* 161 */					 world.triggerEffect(1000, i, j, k, 0);
/*		 */				 }
/* 163 */				 else if (j1 == 2) {
/* 164 */					 ItemStack old = tileentitydispenser.getItem(i1);
/* 165 */					 if ((old.id == Item.BUCKET.id) && (old.count > 1)) {
/* 166 */						 old.count -= 1;
/* 167 */						 if (tileentitydispenser.a(itemstack) < 0)
/* 168 */							 a(world, itemstack, random, 6, l, i1, d0, d1, d2);
/*		 */					 }
/*		 */					 else {
/* 171 */						 tileentitydispenser.setItem(i1, itemstack);
/*		 */					 }
/*		 */ 
/*		 */				 }
/*		 */ 
/* 176 */				 world.triggerEffect(2000, i, j, k, b0 + 1 + (b1 + 1) * 3);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/* 182 */		 if ((l > 0) && (Block.byId[l].isPowerSource())) {
/* 183 */			 boolean flag = (world.isBlockIndirectlyPowered(i, j, k)) || (world.isBlockIndirectlyPowered(i, j + 1, k));
/*		 */ 
/* 185 */			 if (flag)
/* 186 */				 world.a(i, j, k, this.id, p_());
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random)
/*		 */	 {
/* 192 */		 if ((!world.isStatic) && ((world.isBlockIndirectlyPowered(i, j, k)) || (world.isBlockIndirectlyPowered(i, j + 1, k))))
/* 193 */			 dispense(world, i, j, k, random);
/*		 */	 }
/*		 */ 
/*		 */	 public TileEntity a(World world)
/*		 */	 {
/* 198 */		 return new TileEntityDispenser();
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
/* 202 */		 int l = MathHelper.floor(entityliving.yaw * 4.0F / 360.0F + 0.5D) & 0x3;
/*		 */ 
/* 204 */		 if (l == 0) {
/* 205 */			 world.setData(i, j, k, 2);
/*		 */		 }
/*		 */ 
/* 208 */		 if (l == 1) {
/* 209 */			 world.setData(i, j, k, 5);
/*		 */		 }
/*		 */ 
/* 212 */		 if (l == 2) {
/* 213 */			 world.setData(i, j, k, 3);
/*		 */		 }
/*		 */ 
/* 216 */		 if (l == 3)
/* 217 */			 world.setData(i, j, k, 4);
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World world, int i, int j, int k, int l, int i1)
/*		 */	 {
/* 222 */		 TileEntityDispenser tileentitydispenser = (TileEntityDispenser)world.getTileEntity(i, j, k);
/*		 */ 
/* 224 */		 if (tileentitydispenser != null) {
/* 225 */			 for (int j1 = 0; j1 < tileentitydispenser.getSize(); j1++) {
/* 226 */				 ItemStack itemstack = tileentitydispenser.getItem(j1);
/*		 */ 
/* 228 */				 if (itemstack != null) {
/* 229 */					 float f = this.a.nextFloat() * 0.8F + 0.1F;
/* 230 */					 float f1 = this.a.nextFloat() * 0.8F + 0.1F;
/* 231 */					 float f2 = this.a.nextFloat() * 0.8F + 0.1F;
/*		 */ 
/* 233 */					 while (itemstack.count > 0) {
/* 234 */						 int k1 = this.a.nextInt(21) + 10;
/*		 */ 
/* 236 */						 if (k1 > itemstack.count) {
/* 237 */							 k1 = itemstack.count;
/*		 */						 }
/*		 */ 
/* 240 */						 itemstack.count -= k1;
/* 241 */						 EntityItem entityitem = new EntityItem(world, i + f, j + f1, k + f2, new ItemStack(itemstack.id, k1, itemstack.getData()));
/*		 */ 
/* 243 */						 if (itemstack.hasTag()) {
/* 244 */							 entityitem.itemStack.setTag((NBTTagCompound)itemstack.getTag().clone());
/*		 */						 }
/*		 */ 
/* 247 */						 float f3 = 0.05F;
/*		 */ 
/* 249 */						 entityitem.motX = ((float)this.a.nextGaussian() * f3);
/* 250 */						 entityitem.motY = ((float)this.a.nextGaussian() * f3 + 0.2F);
/* 251 */						 entityitem.motZ = ((float)this.a.nextGaussian() * f3);
/* 252 */						 world.addEntity(entityitem);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 258 */		 super.remove(world, i, j, k, l, i1);
/*		 */	 }
/*		 */ 
/*		 */	 private static void a(World world, ItemStack itemstack, Random random, int i, int j, int k, double d0, double d1, double d2) {
/* 262 */		 EntityItem entityitem = new EntityItem(world, d0, d1 - 0.3D, d2, itemstack);
/* 263 */		 double d3 = random.nextDouble() * 0.1D + 0.2D;
/*		 */ 
/* 265 */		 entityitem.motX = (j * d3);
/* 266 */		 entityitem.motY = 0.2000000029802322D;
/* 267 */		 entityitem.motZ = (k * d3);
/* 268 */		 entityitem.motX += random.nextGaussian() * 0.007499999832361937D * i;
/* 269 */		 entityitem.motY += random.nextGaussian() * 0.007499999832361937D * i;
/* 270 */		 entityitem.motZ += random.nextGaussian() * 0.007499999832361937D * i;
/* 271 */		 world.addEntity(entityitem);
/*		 */	 }
/*		 */ 
/*		 */	 private static int a(TileEntityDispenser tileentitydispenser, World world, ItemStack itemstack, Random random, int i, int j, int k, int l, int i1, double d0, double d1, double d2) {
/* 275 */		 float f = 1.1F;
/* 276 */		 byte b0 = 6;
/*		 */ 
/* 278 */		 if (itemstack.id == Item.ARROW.id) {
/* 279 */			 EntityArrow entityarrow = new EntityArrow(world, d0, d1, d2);
/*		 */ 
/* 281 */			 entityarrow.shoot(l, 0.1000000014901161D, i1, f, b0);
/* 282 */			 entityarrow.fromPlayer = 1;
/* 283 */			 world.addEntity(entityarrow);
/* 284 */			 world.triggerEffect(1002, i, j, k, 0);
/* 285 */			 return 1;
/* 286 */		 }if (itemstack.id == Item.EGG.id) {
/* 287 */			 EntityEgg entityegg = new EntityEgg(world, d0, d1, d2);
/*		 */ 
/* 289 */			 entityegg.c(l, 0.1000000014901161D, i1, f, b0);
/* 290 */			 world.addEntity(entityegg);
/* 291 */			 world.triggerEffect(1002, i, j, k, 0);
/* 292 */			 return 1;
/* 293 */		 }if (itemstack.id == Item.SNOW_BALL.id) {
/* 294 */			 EntitySnowball entitysnowball = new EntitySnowball(world, d0, d1, d2);
/*		 */ 
/* 296 */			 entitysnowball.c(l, 0.1000000014901161D, i1, f, b0);
/* 297 */			 world.addEntity(entitysnowball);
/* 298 */			 world.triggerEffect(1002, i, j, k, 0);
/* 299 */			 return 1;
/* 300 */		 }if ((itemstack.id == Item.POTION.id) && (ItemPotion.g(itemstack.getData()))) {
/* 301 */			 EntityPotion entitypotion = new EntityPotion(world, d0, d1, d2, itemstack.getData());
/*		 */ 
/* 303 */			 entitypotion.c(l, 0.1000000014901161D, i1, f * 1.25F, b0 * 0.5F);
/* 304 */			 world.addEntity(entitypotion);
/* 305 */			 world.triggerEffect(1002, i, j, k, 0);
/* 306 */			 return 1;
/* 307 */		 }if (itemstack.id == Item.EXP_BOTTLE.id) {
/* 308 */			 EntityThrownExpBottle entitythrownexpbottle = new EntityThrownExpBottle(world, d0, d1, d2);
/*		 */ 
/* 310 */			 entitythrownexpbottle.c(l, 0.1000000014901161D, i1, f * 1.25F, b0 * 0.5F);
/* 311 */			 world.addEntity(entitythrownexpbottle);
/* 312 */			 world.triggerEffect(1002, i, j, k, 0);
/* 313 */			 return 1;
/* 314 */		 }if (itemstack.id == Item.MONSTER_EGG.id) {
/* 315 */			 ItemMonsterEgg.a(world, itemstack.getData(), d0 + l * 0.3D, d1 - 0.3D, d2 + i1 * 0.3D);
/* 316 */			 world.triggerEffect(1002, i, j, k, 0);
/* 317 */			 return 1;
/* 318 */		 }if (itemstack.id == Item.FIREBALL.id) {
/* 319 */			 EntitySmallFireball entitysmallfireball = new EntitySmallFireball(world, d0 + l * 0.3D, d1, d2 + i1 * 0.3D, l + random.nextGaussian() * 0.05D, random.nextGaussian() * 0.05D, i1 + random.nextGaussian() * 0.05D);
/*		 */ 
/* 321 */			 world.addEntity(entitysmallfireball);
/* 322 */			 world.triggerEffect(1009, i, j, k, 0);
/* 323 */			 return 1;
/* 324 */		 }if ((itemstack.id != Item.LAVA_BUCKET.id) && (itemstack.id != Item.WATER_BUCKET.id)) {
/* 325 */			 if (itemstack.id == Item.BUCKET.id) {
/* 326 */				 int j1 = i + l;
/* 327 */				 int k1 = k + i1;
/* 328 */				 Material material = world.getMaterial(j1, j, k1);
/* 329 */				 int l1 = world.getData(j1, j, k1);
/*		 */ 
/* 331 */				 if ((material == Material.WATER) && (l1 == 0)) {
/* 332 */					 world.setTypeId(j1, j, k1, 0);
/* 333 */					 if (--itemstack.count == 0) {
/* 334 */						 itemstack.id = Item.WATER_BUCKET.id;
/* 335 */						 itemstack.count = 1;
/* 336 */					 } else if (tileentitydispenser.a(new ItemStack(Item.WATER_BUCKET)) < 0) {
/* 337 */						 a(world, new ItemStack(Item.WATER_BUCKET), random, 6, l, i1, d0, d1, d2);
/*		 */					 }
/*		 */ 
/* 340 */					 return 2;
/* 341 */				 }if ((material == Material.LAVA) && (l1 == 0)) {
/* 342 */					 world.setTypeId(j1, j, k1, 0);
/* 343 */					 if (--itemstack.count == 0) {
/* 344 */						 itemstack.id = Item.LAVA_BUCKET.id;
/* 345 */						 itemstack.count = 1;
/* 346 */					 } else if (tileentitydispenser.a(new ItemStack(Item.LAVA_BUCKET)) < 0) {
/* 347 */						 a(world, new ItemStack(Item.LAVA_BUCKET), random, 6, l, i1, d0, d1, d2);
/*		 */					 }
/*		 */ 
/* 350 */					 return 2;
/*		 */				 }
/* 352 */				 return 0;
/*		 */			 }
/* 354 */			 if ((itemstack.getItem() instanceof ItemMinecart)) {
/* 355 */				 d0 = i + l * 1.8F + Math.abs(i1) * 0.5F;
/* 356 */				 d2 = k + i1 * 1.8F + Math.abs(l) * 0.5F;
/* 357 */				 if (BlockMinecartTrack.d_(world, i + l, j, k + i1)) {
/* 358 */					 d1 = j + 0.5F;
/*		 */				 } else {
/* 360 */					 if ((!world.isEmpty(i + l, j, k + i1)) || (!BlockMinecartTrack.d_(world, i + l, j - 1, k + i1))) {
/* 361 */						 return 0;
/*		 */					 }
/*		 */ 
/* 364 */					 d1 = j - 0.5F;
/*		 */				 }
/*		 */ 
/* 367 */				 EntityMinecart entityminecart = new EntityMinecart(world, d0, d1, d2, ((ItemMinecart)itemstack.getItem()).a);
/*		 */ 
/* 369 */				 world.addEntity(entityminecart);
/* 370 */				 world.triggerEffect(1000, i, j, k, 0);
/* 371 */				 return 1;
/* 372 */			 }if (itemstack.id == Item.BOAT.id) {
/* 373 */				 d0 = i + l * 1.8F + Math.abs(i1) * 0.5F;
/* 374 */				 d2 = k + i1 * 1.8F + Math.abs(l) * 0.5F;
/* 375 */				 if (world.getMaterial(i + l, j, k + i1) == Material.WATER) {
/* 376 */					 d1 = j + 1.0F;
/*		 */				 } else {
/* 378 */					 if ((!world.isEmpty(i + l, j, k + i1)) || (world.getMaterial(i + l, j - 1, k + i1) != Material.WATER)) {
/* 379 */						 return 0;
/*		 */					 }
/*		 */ 
/* 382 */					 d1 = j;
/*		 */				 }
/*		 */ 
/* 385 */				 EntityBoat entityboat = new EntityBoat(world, d0, d1, d2);
/*		 */ 
/* 387 */				 world.addEntity(entityboat);
/* 388 */				 world.triggerEffect(1000, i, j, k, 0);
/* 389 */				 return 1;
/*		 */			 }
/* 391 */			 return 0;
/*		 */		 }
/*		 */ 
/* 394 */		 ItemBucket itembucket = (ItemBucket)itemstack.getItem();
/*		 */ 
/* 396 */		 if (itembucket.a(world, i, j, k, i + l, j, k + i1)) {
/* 397 */			 itemstack.id = Item.BUCKET.id;
/* 398 */			 itemstack.count = 1;
/* 399 */			 return 2;
/*		 */		 }
/* 401 */		 return 0;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockDispenser
 * JD-Core Version:		0.6.0
 */