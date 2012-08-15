/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Location;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftItemStack;
/*		 */ import org.bukkit.event.player.PlayerBucketEmptyEvent;
/*		 */ import org.bukkit.event.player.PlayerBucketFillEvent;
/*		 */ 
/*		 */ public class ItemBucket extends Item
/*		 */ {
/*		 */	 private int a;
/*		 */ 
/*		 */	 public ItemBucket(int i, int j)
/*		 */	 {
/*	15 */		 super(i);
/*	16 */		 this.maxStackSize = 1;
/*	17 */		 this.a = j;
/*	18 */		 a(CreativeModeTab.f);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
/*	22 */		 float f = 1.0F;
/*	23 */		 double d0 = entityhuman.lastX + (entityhuman.locX - entityhuman.lastX) * f;
/*	24 */		 double d1 = entityhuman.lastY + (entityhuman.locY - entityhuman.lastY) * f + 1.62D - entityhuman.height;
/*	25 */		 double d2 = entityhuman.lastZ + (entityhuman.locZ - entityhuman.lastZ) * f;
/*	26 */		 boolean flag = this.a == 0;
/*	27 */		 MovingObjectPosition movingobjectposition = a(world, entityhuman, flag);
/*		 */ 
/*	29 */		 if (movingobjectposition == null) {
/*	30 */			 return itemstack;
/*		 */		 }
/*	32 */		 if (movingobjectposition.type == EnumMovingObjectType.TILE) {
/*	33 */			 int i = movingobjectposition.b;
/*	34 */			 int j = movingobjectposition.c;
/*	35 */			 int k = movingobjectposition.d;
/*		 */ 
/*	37 */			 if (!world.a(entityhuman, i, j, k)) {
/*	38 */				 return itemstack;
/*		 */			 }
/*		 */ 
/*	41 */			 if (this.a == 0) {
/*	42 */				 if (!entityhuman.e(i, j, k)) {
/*	43 */					 return itemstack;
/*		 */				 }
/*		 */ 
/*	46 */				 if ((world.getMaterial(i, j, k) == Material.WATER) && (world.getData(i, j, k) == 0))
/*		 */				 {
/*	48 */					 PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(entityhuman, i, j, k, -1, itemstack, Item.WATER_BUCKET);
/*		 */ 
/*	50 */					 if (event.isCancelled()) {
/*	51 */						 return itemstack;
/*		 */					 }
/*		 */ 
/*	54 */					 world.setTypeId(i, j, k, 0);
/*	55 */					 if (entityhuman.abilities.canInstantlyBuild) {
/*	56 */						 return itemstack;
/*		 */					 }
/*		 */ 
/*	59 */					 ItemStack result = CraftItemStack.createNMSItemStack(event.getItemStack());
/*	60 */					 if (--itemstack.count <= 0) {
/*	61 */						 return result;
/*		 */					 }
/*		 */ 
/*	64 */					 if (!entityhuman.inventory.pickup(result)) {
/*	65 */						 entityhuman.drop(CraftItemStack.createNMSItemStack(event.getItemStack()));
/*		 */					 }
/*		 */ 
/*	68 */					 return itemstack;
/*		 */				 }
/*		 */ 
/*	71 */				 if ((world.getMaterial(i, j, k) == Material.LAVA) && (world.getData(i, j, k) == 0))
/*		 */				 {
/*	73 */					 PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(entityhuman, i, j, k, -1, itemstack, Item.LAVA_BUCKET);
/*		 */ 
/*	75 */					 if (event.isCancelled()) {
/*	76 */						 return itemstack;
/*		 */					 }
/*		 */ 
/*	79 */					 world.setTypeId(i, j, k, 0);
/*	80 */					 if (entityhuman.abilities.canInstantlyBuild) {
/*	81 */						 return itemstack;
/*		 */					 }
/*		 */ 
/*	84 */					 ItemStack result = CraftItemStack.createNMSItemStack(event.getItemStack());
/*	85 */					 if (--itemstack.count <= 0) {
/*	86 */						 return result;
/*		 */					 }
/*		 */ 
/*	89 */					 if (!entityhuman.inventory.pickup(result)) {
/*	90 */						 entityhuman.drop(CraftItemStack.createNMSItemStack(event.getItemStack()));
/*		 */					 }
/*		 */ 
/*	93 */					 return itemstack;
/*		 */				 }
/*		 */			 } else {
/*	96 */				 if (this.a < 0)
/*		 */				 {
/*	98 */					 PlayerBucketEmptyEvent event = CraftEventFactory.callPlayerBucketEmptyEvent(entityhuman, i, j, k, movingobjectposition.face, itemstack);
/*		 */ 
/* 100 */					 if (event.isCancelled()) {
/* 101 */						 return itemstack;
/*		 */					 }
/*		 */ 
/* 104 */					 return CraftItemStack.createNMSItemStack(event.getItemStack());
/*		 */				 }
/*		 */ 
/* 107 */				 int clickedX = i; int clickedY = j; int clickedZ = k;
/*		 */ 
/* 110 */				 if (movingobjectposition.face == 0) {
/* 111 */					 j--;
/*		 */				 }
/*		 */ 
/* 114 */				 if (movingobjectposition.face == 1) {
/* 115 */					 j++;
/*		 */				 }
/*		 */ 
/* 118 */				 if (movingobjectposition.face == 2) {
/* 119 */					 k--;
/*		 */				 }
/*		 */ 
/* 122 */				 if (movingobjectposition.face == 3) {
/* 123 */					 k++;
/*		 */				 }
/*		 */ 
/* 126 */				 if (movingobjectposition.face == 4) {
/* 127 */					 i--;
/*		 */				 }
/*		 */ 
/* 130 */				 if (movingobjectposition.face == 5) {
/* 131 */					 i++;
/*		 */				 }
/*		 */ 
/* 134 */				 if (!entityhuman.e(i, j, k)) {
/* 135 */					 return itemstack;
/*		 */				 }
/*		 */ 
/* 139 */				 PlayerBucketEmptyEvent event = CraftEventFactory.callPlayerBucketEmptyEvent(entityhuman, clickedX, clickedY, clickedZ, movingobjectposition.face, itemstack);
/*		 */ 
/* 141 */				 if (event.isCancelled()) {
/* 142 */					 return itemstack;
/*		 */				 }
/*		 */ 
/* 146 */				 if ((a(world, d0, d1, d2, i, j, k)) && (!entityhuman.abilities.canInstantlyBuild))
/* 147 */					 return CraftItemStack.createNMSItemStack(event.getItemStack());
/*		 */			 }
/*		 */		 }
/* 150 */		 else if ((this.a == 0) && ((movingobjectposition.entity instanceof EntityCow)))
/*		 */		 {
/* 152 */			 Location loc = movingobjectposition.entity.getBukkitEntity().getLocation();
/* 153 */			 PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(entityhuman, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), -1, itemstack, Item.MILK_BUCKET);
/*		 */ 
/* 155 */			 if (event.isCancelled()) {
/* 156 */				 return itemstack;
/*		 */			 }
/*		 */ 
/* 159 */			 return CraftItemStack.createNMSItemStack(event.getItemStack());
/*		 */		 }
/*		 */ 
/* 163 */		 return itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World world, double d0, double d1, double d2, int i, int j, int k)
/*		 */	 {
/* 168 */		 if (this.a <= 0)
/* 169 */			 return false;
/* 170 */		 if ((!world.isEmpty(i, j, k)) && (world.getMaterial(i, j, k).isBuildable())) {
/* 171 */			 return false;
/*		 */		 }
/* 173 */		 if ((world.worldProvider.d) && (this.a == Block.WATER.id)) {
/* 174 */			 world.makeSound(d0 + 0.5D, d1 + 0.5D, d2 + 0.5D, "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
/*		 */ 
/* 176 */			 for (int l = 0; l < 8; l++)
/* 177 */				 world.a("largesmoke", i + Math.random(), j + Math.random(), k + Math.random(), 0.0D, 0.0D, 0.0D);
/*		 */		 }
/*		 */		 else {
/* 180 */			 world.setTypeIdAndData(i, j, k, this.a, 0);
/*		 */		 }
/*		 */ 
/* 183 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemBucket
 * JD-Core Version:		0.6.0
 */