/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import org.bukkit.craftbukkit.entity.CraftHumanEntity;
/*		 */ import org.bukkit.entity.HumanEntity;
/*		 */ import org.bukkit.inventory.InventoryHolder;
/*		 */ 
/*		 */ public class PlayerInventory
/*		 */	 implements IInventory
/*		 */ {
/*	12 */	 public ItemStack[] items = new ItemStack[36];
/*	13 */	 public ItemStack[] armor = new ItemStack[4];
/*	14 */	 public int itemInHandIndex = 0;
/*		 */	 public EntityHuman player;
/*		 */	 private ItemStack g;
/*	17 */	 public boolean e = false;
/*		 */ 
/*	20 */	 public List<HumanEntity> transaction = new ArrayList();
/*	21 */	 private int maxStack = 64;
/*		 */ 
/*		 */	 public ItemStack[] getContents() {
/*	24 */		 return this.items;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack[] getArmorContents() {
/*	28 */		 return this.armor;
/*		 */	 }
/*		 */ 
/*		 */	 public void onOpen(CraftHumanEntity who) {
/*	32 */		 this.transaction.add(who);
/*		 */	 }
/*		 */ 
/*		 */	 public void onClose(CraftHumanEntity who) {
/*	36 */		 this.transaction.remove(who);
/*		 */	 }
/*		 */ 
/*		 */	 public List<HumanEntity> getViewers() {
/*	40 */		 return this.transaction;
/*		 */	 }
/*		 */ 
/*		 */	 public InventoryHolder getOwner() {
/*	44 */		 return this.player.getBukkitEntity();
/*		 */	 }
/*		 */ 
/*		 */	 public void setMaxStackSize(int size) {
/*	48 */		 this.maxStack = size;
/*		 */	 }
/*		 */ 
/*		 */	 public PlayerInventory(EntityHuman entityhuman)
/*		 */	 {
/*	53 */		 this.player = entityhuman;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack getItemInHand() {
/*	57 */		 return (this.itemInHandIndex < 9) && (this.itemInHandIndex >= 0) ? this.items[this.itemInHandIndex] : null;
/*		 */	 }
/*		 */ 
/*		 */	 public static int getHotbarSize() {
/*	61 */		 return 9;
/*		 */	 }
/*		 */ 
/*		 */	 private int h(int i) {
/*	65 */		 for (int j = 0; j < this.items.length; j++) {
/*	66 */			 if ((this.items[j] != null) && (this.items[j].id == i)) {
/*	67 */				 return j;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	71 */		 return -1;
/*		 */	 }
/*		 */ 
/*		 */	 private int firstPartial(ItemStack itemstack) {
/*	75 */		 for (int i = 0; i < this.items.length; i++) {
/*	76 */			 if ((this.items[i] != null) && (this.items[i].id == itemstack.id) && (this.items[i].isStackable()) && (this.items[i].count < this.items[i].getMaxStackSize()) && (this.items[i].count < getMaxStackSize()) && ((!this.items[i].usesData()) || (this.items[i].getData() == itemstack.getData())) && (ItemStack.equals(this.items[i], itemstack))) {
/*	77 */				 return i;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	81 */		 return -1;
/*		 */	 }
/*		 */ 
/*		 */	 public int canHold(ItemStack itemstack)
/*		 */	 {
/*	86 */		 int remains = itemstack.count;
/*	87 */		 for (int i = 0; i < this.items.length; i++) {
/*	88 */			 if (this.items[i] == null) return itemstack.count;
/*		 */ 
/*	91 */			 if ((this.items[i] != null) && (this.items[i].id == itemstack.id) && (this.items[i].isStackable()) && (this.items[i].count < this.items[i].getMaxStackSize()) && (this.items[i].count < getMaxStackSize()) && ((!this.items[i].usesData()) || (this.items[i].getData() == itemstack.getData()))) {
/*	92 */				 remains -= (this.items[i].getMaxStackSize() < getMaxStackSize() ? this.items[i].getMaxStackSize() : getMaxStackSize()) - this.items[i].count;
/*		 */			 }
/*	94 */			 if (remains <= 0) return itemstack.count;
/*		 */		 }
/*	96 */		 return itemstack.count - remains;
/*		 */	 }
/*		 */ 
/*		 */	 public int i()
/*		 */	 {
/* 101 */		 for (int i = 0; i < this.items.length; i++) {
/* 102 */			 if (this.items[i] == null) {
/* 103 */				 return i;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 107 */		 return -1;
/*		 */	 }
/*		 */ 
/*		 */	 private int e(ItemStack itemstack) {
/* 111 */		 int i = itemstack.id;
/* 112 */		 int j = itemstack.count;
/*		 */ 
/* 115 */		 if (itemstack.getMaxStackSize() == 1) {
/* 116 */			 int k = i();
/* 117 */			 if (k < 0) {
/* 118 */				 return j;
/*		 */			 }
/* 120 */			 if (this.items[k] == null) {
/* 121 */				 this.items[k] = ItemStack.b(itemstack);
/*		 */			 }
/*		 */ 
/* 124 */			 return 0;
/*		 */		 }
/*		 */ 
/* 127 */		 int k = firstPartial(itemstack);
/* 128 */		 if (k < 0) {
/* 129 */			 k = i();
/*		 */		 }
/*		 */ 
/* 132 */		 if (k < 0) {
/* 133 */			 return j;
/*		 */		 }
/* 135 */		 if (this.items[k] == null) {
/* 136 */			 this.items[k] = new ItemStack(i, 0, itemstack.getData());
/* 137 */			 if (itemstack.hasTag()) {
/* 138 */				 this.items[k].setTag((NBTTagCompound)itemstack.getTag().clone());
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 142 */		 int l = j;
/*		 */ 
/* 144 */		 if (j > this.items[k].getMaxStackSize() - this.items[k].count) {
/* 145 */			 l = this.items[k].getMaxStackSize() - this.items[k].count;
/*		 */		 }
/*		 */ 
/* 148 */		 if (l > getMaxStackSize() - this.items[k].count) {
/* 149 */			 l = getMaxStackSize() - this.items[k].count;
/*		 */		 }
/*		 */ 
/* 152 */		 if (l == 0) {
/* 153 */			 return j;
/*		 */		 }
/* 155 */		 j -= l;
/* 156 */		 this.items[k].count += l;
/* 157 */		 this.items[k].b = 5;
/* 158 */		 return j;
/*		 */	 }
/*		 */ 
/*		 */	 public void k()
/*		 */	 {
/* 165 */		 for (int i = 0; i < this.items.length; i++)
/* 166 */			 if (this.items[i] != null)
/* 167 */				 this.items[i].a(this.player.world, this.player, i, this.itemInHandIndex == i);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d(int i)
/*		 */	 {
/* 173 */		 int j = h(i);
/*		 */ 
/* 175 */		 if (j < 0) {
/* 176 */			 return false;
/*		 */		 }
/* 178 */		 if (--this.items[j].count <= 0) {
/* 179 */			 this.items[j] = null;
/*		 */		 }
/*		 */ 
/* 182 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean e(int i)
/*		 */	 {
/* 187 */		 int j = h(i);
/*		 */ 
/* 189 */		 return j >= 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean pickup(ItemStack itemstack)
/*		 */	 {
/* 195 */		 if (itemstack.h()) {
/* 196 */			 int i = i();
/* 197 */			 if (i >= 0) {
/* 198 */				 this.items[i] = ItemStack.b(itemstack);
/* 199 */				 this.items[i].b = 5;
/* 200 */				 itemstack.count = 0;
/* 201 */				 return true;
/* 202 */			 }if (this.player.abilities.canInstantlyBuild) {
/* 203 */				 itemstack.count = 0;
/* 204 */				 return true;
/*		 */			 }
/* 206 */			 return false;
/*		 */		 }int i;
/*		 */		 do {
/* 210 */			 i = itemstack.count;
/* 211 */			 itemstack.count = e(itemstack);
/* 212 */		 }while ((itemstack.count > 0) && (itemstack.count < i));
/*		 */ 
/* 214 */		 if ((itemstack.count == i) && (this.player.abilities.canInstantlyBuild)) {
/* 215 */			 itemstack.count = 0;
/* 216 */			 return true;
/*		 */		 }
/* 218 */		 return itemstack.count < i;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitStack(int i, int j)
/*		 */	 {
/* 224 */		 ItemStack[] aitemstack = this.items;
/*		 */ 
/* 226 */		 if (i >= this.items.length) {
/* 227 */			 aitemstack = this.armor;
/* 228 */			 i -= this.items.length;
/*		 */		 }
/*		 */ 
/* 231 */		 if (aitemstack[i] != null)
/*		 */		 {
/* 234 */			 if (aitemstack[i].count <= j) {
/* 235 */				 ItemStack itemstack = aitemstack[i];
/* 236 */				 aitemstack[i] = null;
/* 237 */				 return itemstack;
/*		 */			 }
/* 239 */			 ItemStack itemstack = aitemstack[i].a(j);
/* 240 */			 if (aitemstack[i].count == 0) {
/* 241 */				 aitemstack[i] = null;
/*		 */			 }
/*		 */ 
/* 244 */			 return itemstack;
/*		 */		 }
/*		 */ 
/* 247 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitWithoutUpdate(int i)
/*		 */	 {
/* 252 */		 ItemStack[] aitemstack = this.items;
/*		 */ 
/* 254 */		 if (i >= this.items.length) {
/* 255 */			 aitemstack = this.armor;
/* 256 */			 i -= this.items.length;
/*		 */		 }
/*		 */ 
/* 259 */		 if (aitemstack[i] != null) {
/* 260 */			 ItemStack itemstack = aitemstack[i];
/*		 */ 
/* 262 */			 aitemstack[i] = null;
/* 263 */			 return itemstack;
/*		 */		 }
/* 265 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void setItem(int i, ItemStack itemstack)
/*		 */	 {
/* 270 */		 ItemStack[] aitemstack = this.items;
/*		 */ 
/* 272 */		 if (i >= aitemstack.length) {
/* 273 */			 i -= aitemstack.length;
/* 274 */			 aitemstack = this.armor;
/*		 */		 }
/*		 */ 
/* 277 */		 aitemstack[i] = itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 public float a(Block block) {
/* 281 */		 float f = 1.0F;
/*		 */ 
/* 283 */		 if (this.items[this.itemInHandIndex] != null) {
/* 284 */			 f *= this.items[this.itemInHandIndex].a(block);
/*		 */		 }
/*		 */ 
/* 287 */		 return f;
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagList a(NBTTagList nbttaglist)
/*		 */	 {
/* 294 */		 for (int i = 0; i < this.items.length; i++) {
/* 295 */			 if (this.items[i] != null) {
/* 296 */				 NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 297 */				 nbttagcompound.setByte("Slot", (byte)i);
/* 298 */				 this.items[i].save(nbttagcompound);
/* 299 */				 nbttaglist.add(nbttagcompound);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 303 */		 for (i = 0; i < this.armor.length; i++) {
/* 304 */			 if (this.armor[i] != null) {
/* 305 */				 NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 306 */				 nbttagcompound.setByte("Slot", (byte)(i + 100));
/* 307 */				 this.armor[i].save(nbttagcompound);
/* 308 */				 nbttaglist.add(nbttagcompound);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 312 */		 return nbttaglist;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagList nbttaglist) {
/* 316 */		 this.items = new ItemStack[36];
/* 317 */		 this.armor = new ItemStack[4];
/*		 */ 
/* 319 */		 for (int i = 0; i < nbttaglist.size(); i++) {
/* 320 */			 NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.get(i);
/* 321 */			 int j = nbttagcompound.getByte("Slot") & 0xFF;
/* 322 */			 ItemStack itemstack = ItemStack.a(nbttagcompound);
/*		 */ 
/* 324 */			 if (itemstack != null) {
/* 325 */				 if ((j >= 0) && (j < this.items.length)) {
/* 326 */					 this.items[j] = itemstack;
/*		 */				 }
/*		 */ 
/* 329 */				 if ((j >= 100) && (j < this.armor.length + 100))
/* 330 */					 this.armor[(j - 100)] = itemstack;
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int getSize()
/*		 */	 {
/* 337 */		 return this.items.length + 4;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack getItem(int i) {
/* 341 */		 ItemStack[] aitemstack = this.items;
/*		 */ 
/* 343 */		 if (i >= aitemstack.length) {
/* 344 */			 i -= aitemstack.length;
/* 345 */			 aitemstack = this.armor;
/*		 */		 }
/*		 */ 
/* 348 */		 return aitemstack[i];
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/* 352 */		 return "container.inventory";
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxStackSize() {
/* 356 */		 return this.maxStack;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Entity entity) {
/* 360 */		 ItemStack itemstack = getItem(this.itemInHandIndex);
/*		 */ 
/* 362 */		 return itemstack != null ? itemstack.a(entity) : 1;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b(Block block) {
/* 366 */		 if (block.material.isAlwaysDestroyable()) {
/* 367 */			 return true;
/*		 */		 }
/* 369 */		 ItemStack itemstack = getItem(this.itemInHandIndex);
/*		 */ 
/* 371 */		 return itemstack != null ? itemstack.b(block) : false;
/*		 */	 }
/*		 */ 
/*		 */	 public int l()
/*		 */	 {
/* 376 */		 int i = 0;
/* 377 */		 ItemStack[] aitemstack = this.armor;
/* 378 */		 int j = aitemstack.length;
/*		 */ 
/* 380 */		 for (int k = 0; k < j; k++) {
/* 381 */			 ItemStack itemstack = aitemstack[k];
/*		 */ 
/* 383 */			 if ((itemstack != null) && ((itemstack.getItem() instanceof ItemArmor))) {
/* 384 */				 int l = ((ItemArmor)itemstack.getItem()).b;
/*		 */ 
/* 386 */				 i += l;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 390 */		 return i;
/*		 */	 }
/*		 */ 
/*		 */	 public void g(int i) {
/* 394 */		 i /= 4;
/* 395 */		 if (i < 1) {
/* 396 */			 i = 1;
/*		 */		 }
/*		 */ 
/* 399 */		 for (int j = 0; j < this.armor.length; j++)
/* 400 */			 if ((this.armor[j] != null) && ((this.armor[j].getItem() instanceof ItemArmor))) {
/* 401 */				 this.armor[j].damage(i, this.player);
/* 402 */				 if (this.armor[j].count == 0)
/* 403 */					 this.armor[j] = null;
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public void m()
/*		 */	 {
/* 412 */		 for (int i = 0; i < this.items.length; i++) {
/* 413 */			 if (this.items[i] != null) {
/* 414 */				 this.player.a(this.items[i], true);
/* 415 */				 this.items[i] = null;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 419 */		 for (i = 0; i < this.armor.length; i++)
/* 420 */			 if (this.armor[i] != null) {
/* 421 */				 this.player.a(this.armor[i], true);
/* 422 */				 this.armor[i] = null;
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public void update()
/*		 */	 {
/* 428 */		 this.e = true;
/*		 */	 }
/*		 */ 
/*		 */	 public void setCarried(ItemStack itemstack) {
/* 432 */		 this.g = itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack getCarried()
/*		 */	 {
/* 437 */		 if ((this.g != null) && (this.g.count == 0)) {
/* 438 */			 setCarried(null);
/*		 */		 }
/*		 */ 
/* 441 */		 return this.g;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityHuman entityhuman) {
/* 445 */		 return !this.player.dead;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(ItemStack itemstack) {
/* 449 */		 ItemStack[] aitemstack = this.armor;
/* 450 */		 int i = aitemstack.length;
/*		 */ 
/* 455 */		 for (int j = 0; j < i; j++) {
/* 456 */			 ItemStack itemstack1 = aitemstack[j];
/* 457 */			 if ((itemstack1 != null) && (itemstack1.c(itemstack))) {
/* 458 */				 return true;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 462 */		 aitemstack = this.items;
/* 463 */		 i = aitemstack.length;
/*		 */ 
/* 465 */		 for (j = 0; j < i; j++) {
/* 466 */			 ItemStack itemstack1 = aitemstack[j];
/* 467 */			 if ((itemstack1 != null) && (itemstack1.c(itemstack))) {
/* 468 */				 return true;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 472 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void startOpen() {
/*		 */	 }
/*		 */ 
/*		 */	 public void f() {
/*		 */	 }
/*		 */ 
/*		 */	 public void b(PlayerInventory playerinventory) {
/* 482 */		 for (int i = 0; i < this.items.length; i++) {
/* 483 */			 this.items[i] = ItemStack.b(playerinventory.items[i]);
/*		 */		 }
/*		 */ 
/* 486 */		 for (i = 0; i < this.armor.length; i++)
/* 487 */			 this.armor[i] = ItemStack.b(playerinventory.armor[i]);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PlayerInventory
 * JD-Core Version:		0.6.0
 */