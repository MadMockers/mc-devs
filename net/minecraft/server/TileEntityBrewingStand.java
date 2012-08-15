/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import org.bukkit.Bukkit;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.entity.CraftHumanEntity;
/*		 */ import org.bukkit.entity.HumanEntity;
/*		 */ import org.bukkit.event.inventory.BrewEvent;
/*		 */ import org.bukkit.inventory.BrewerInventory;
/*		 */ import org.bukkit.inventory.InventoryHolder;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class TileEntityBrewingStand extends TileEntity
/*		 */	 implements IInventory
/*		 */ {
/*	13 */	 public ItemStack[] items = new ItemStack[4];
/*		 */	 public int brewTime;
/*		 */	 private int c;
/*		 */	 private int d;
/*	21 */	 public List<HumanEntity> transaction = new ArrayList();
/*	22 */	 private int maxStack = 1;
/*		 */ 
/*		 */	 public void onOpen(CraftHumanEntity who) {
/*	25 */		 this.transaction.add(who);
/*		 */	 }
/*		 */ 
/*		 */	 public void onClose(CraftHumanEntity who) {
/*	29 */		 this.transaction.remove(who);
/*		 */	 }
/*		 */ 
/*		 */	 public List<HumanEntity> getViewers() {
/*	33 */		 return this.transaction;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack[] getContents() {
/*	37 */		 return this.items;
/*		 */	 }
/*		 */ 
/*		 */	 public void setMaxStackSize(int size) {
/*	41 */		 this.maxStack = size;
/*		 */	 }
/*		 */ 
/*		 */	 public String getName()
/*		 */	 {
/*	46 */		 return "container.brewing";
/*		 */	 }
/*		 */ 
/*		 */	 public int getSize() {
/*	50 */		 return this.items.length;
/*		 */	 }
/*		 */ 
/*		 */	 public void g() {
/*	54 */		 if (this.brewTime > 0) {
/*	55 */			 this.brewTime -= 1;
/*	56 */			 if (this.brewTime == 0) {
/*	57 */				 r();
/*	58 */				 update();
/*	59 */			 } else if (!k()) {
/*	60 */				 this.brewTime = 0;
/*	61 */				 update();
/*	62 */			 } else if (this.d != this.items[3].id) {
/*	63 */				 this.brewTime = 0;
/*	64 */				 update();
/*		 */			 }
/*	66 */		 } else if (k()) {
/*	67 */			 this.brewTime = 400;
/*	68 */			 this.d = this.items[3].id;
/*		 */		 }
/*		 */ 
/*	71 */		 int i = i();
/*		 */ 
/*	73 */		 if (i != this.c) {
/*	74 */			 this.c = i;
/*	75 */			 this.world.setData(this.x, this.y, this.z, i);
/*		 */		 }
/*		 */ 
/*	78 */		 super.g();
/*		 */	 }
/*		 */ 
/*		 */	 public int t_() {
/*	82 */		 return this.brewTime;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean k() {
/*	86 */		 if ((this.items[3] != null) && (this.items[3].count > 0)) {
/*	87 */			 ItemStack itemstack = this.items[3];
/*		 */ 
/*	89 */			 if (!Item.byId[itemstack.id].u()) {
/*	90 */				 return false;
/*		 */			 }
/*	92 */			 boolean flag = false;
/*		 */ 
/*	94 */			 for (int i = 0; i < 3; i++) {
/*	95 */				 if ((this.items[i] != null) && (this.items[i].id == Item.POTION.id)) {
/*	96 */					 int j = this.items[i].getData();
/*	97 */					 int k = b(j, itemstack);
/*		 */ 
/*	99 */					 if ((!ItemPotion.g(j)) && (ItemPotion.g(k))) {
/* 100 */						 flag = true;
/* 101 */						 break;
/*		 */					 }
/*		 */ 
/* 104 */					 List list = Item.POTION.f(j);
/* 105 */					 List list1 = Item.POTION.f(k);
/*		 */ 
/* 107 */					 if (((j <= 0) || (list != list1)) && ((list == null) || ((!list.equals(list1)) && (list1 != null))) && (j != k)) {
/* 108 */						 flag = true;
/* 109 */						 break;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 114 */			 return flag;
/*		 */		 }
/*		 */ 
/* 117 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 private void r()
/*		 */	 {
/* 122 */		 if (k()) {
/* 123 */			 ItemStack itemstack = this.items[3];
/*		 */ 
/* 126 */			 if (getOwner() != null) {
/* 127 */				 BrewEvent event = new BrewEvent(this.world.getWorld().getBlockAt(this.x, this.y, this.z), (BrewerInventory)getOwner().getInventory());
/* 128 */				 Bukkit.getPluginManager().callEvent(event);
/* 129 */				 if (event.isCancelled()) {
/* 130 */					 return;
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 135 */			 for (int i = 0; i < 3; i++) {
/* 136 */				 if ((this.items[i] != null) && (this.items[i].id == Item.POTION.id)) {
/* 137 */					 int j = this.items[i].getData();
/* 138 */					 int k = b(j, itemstack);
/* 139 */					 List list = Item.POTION.f(j);
/* 140 */					 List list1 = Item.POTION.f(k);
/*		 */ 
/* 142 */					 if (((j <= 0) || (list != list1)) && ((list == null) || ((!list.equals(list1)) && (list1 != null)))) {
/* 143 */						 if (j != k)
/* 144 */							 this.items[i].setData(k);
/*		 */					 }
/* 146 */					 else if ((!ItemPotion.g(j)) && (ItemPotion.g(k))) {
/* 147 */						 this.items[i].setData(k);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 152 */			 if (Item.byId[itemstack.id].r()) {
/* 153 */				 this.items[3] = new ItemStack(Item.byId[itemstack.id].q());
/*		 */			 } else {
/* 155 */				 this.items[3].count -= 1;
/* 156 */				 if (this.items[3].count <= 0)
/* 157 */					 this.items[3] = null;
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private int b(int i, ItemStack itemstack)
/*		 */	 {
/* 164 */		 return Item.byId[itemstack.id].u() ? PotionBrewer.a(i, Item.byId[itemstack.id].t()) : itemstack == null ? i : i;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/* 168 */		 super.a(nbttagcompound);
/* 169 */		 NBTTagList nbttaglist = nbttagcompound.getList("Items");
/*		 */ 
/* 171 */		 this.items = new ItemStack[getSize()];
/*		 */ 
/* 173 */		 for (int i = 0; i < nbttaglist.size(); i++) {
/* 174 */			 NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.get(i);
/* 175 */			 byte b0 = nbttagcompound1.getByte("Slot");
/*		 */ 
/* 177 */			 if ((b0 >= 0) && (b0 < this.items.length)) {
/* 178 */				 this.items[b0] = ItemStack.a(nbttagcompound1);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 182 */		 this.brewTime = nbttagcompound.getShort("BrewTime");
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/* 186 */		 super.b(nbttagcompound);
/* 187 */		 nbttagcompound.setShort("BrewTime", (short)this.brewTime);
/* 188 */		 NBTTagList nbttaglist = new NBTTagList();
/*		 */ 
/* 190 */		 for (int i = 0; i < this.items.length; i++) {
/* 191 */			 if (this.items[i] != null) {
/* 192 */				 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*		 */ 
/* 194 */				 nbttagcompound1.setByte("Slot", (byte)i);
/* 195 */				 this.items[i].save(nbttagcompound1);
/* 196 */				 nbttaglist.add(nbttagcompound1);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 200 */		 nbttagcompound.set("Items", nbttaglist);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack getItem(int i) {
/* 204 */		 return (i >= 0) && (i < this.items.length) ? this.items[i] : null;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitStack(int i, int j) {
/* 208 */		 if ((i >= 0) && (i < this.items.length)) {
/* 209 */			 ItemStack itemstack = this.items[i];
/*		 */ 
/* 211 */			 this.items[i] = null;
/* 212 */			 return itemstack;
/*		 */		 }
/* 214 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitWithoutUpdate(int i)
/*		 */	 {
/* 219 */		 if ((i >= 0) && (i < this.items.length)) {
/* 220 */			 ItemStack itemstack = this.items[i];
/*		 */ 
/* 222 */			 this.items[i] = null;
/* 223 */			 return itemstack;
/*		 */		 }
/* 225 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void setItem(int i, ItemStack itemstack)
/*		 */	 {
/* 230 */		 if ((i >= 0) && (i < this.items.length))
/* 231 */			 this.items[i] = itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxStackSize()
/*		 */	 {
/* 236 */		 return this.maxStack;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityHuman entityhuman) {
/* 240 */		 return this.world.getTileEntity(this.x, this.y, this.z) == this;
/*		 */	 }
/*		 */	 public void startOpen() {
/*		 */	 }
/*		 */	 public void f() {
/*		 */	 }
/*		 */ 
/*		 */	 public int i() {
/* 248 */		 int i = 0;
/*		 */ 
/* 250 */		 for (int j = 0; j < 3; j++) {
/* 251 */			 if (this.items[j] != null) {
/* 252 */				 i |= 1 << j;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 256 */		 return i;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.TileEntityBrewingStand
 * JD-Core Version:		0.6.0
 */