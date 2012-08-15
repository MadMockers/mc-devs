/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.entity.CraftHumanEntity;
/*		 */ import org.bukkit.entity.HumanEntity;
/*		 */ 
/*		 */ public class TileEntityDispenser extends TileEntity
/*		 */	 implements IInventory
/*		 */ {
/*	14 */	 private ItemStack[] items = new ItemStack[9];
/*	15 */	 private Random b = new Random();
/*		 */ 
/*	18 */	 public List<HumanEntity> transaction = new ArrayList();
/*	19 */	 private int maxStack = 64;
/*		 */ 
/*		 */	 public ItemStack[] getContents() {
/*	22 */		 return this.items;
/*		 */	 }
/*		 */ 
/*		 */	 public void onOpen(CraftHumanEntity who) {
/*	26 */		 this.transaction.add(who);
/*		 */	 }
/*		 */ 
/*		 */	 public void onClose(CraftHumanEntity who) {
/*	30 */		 this.transaction.remove(who);
/*		 */	 }
/*		 */ 
/*		 */	 public List<HumanEntity> getViewers() {
/*	34 */		 return this.transaction;
/*		 */	 }
/*		 */ 
/*		 */	 public void setMaxStackSize(int size) {
/*	38 */		 this.maxStack = size;
/*		 */	 }
/*		 */ 
/*		 */	 public int getSize()
/*		 */	 {
/*	45 */		 return 9;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack getItem(int i) {
/*	49 */		 return this.items[i];
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitStack(int i, int j) {
/*	53 */		 if (this.items[i] != null)
/*		 */		 {
/*	56 */			 if (this.items[i].count <= j) {
/*	57 */				 ItemStack itemstack = this.items[i];
/*	58 */				 this.items[i] = null;
/*	59 */				 update();
/*	60 */				 return itemstack;
/*		 */			 }
/*	62 */			 ItemStack itemstack = this.items[i].a(j);
/*	63 */			 if (this.items[i].count == 0) {
/*	64 */				 this.items[i] = null;
/*		 */			 }
/*		 */ 
/*	67 */			 update();
/*	68 */			 return itemstack;
/*		 */		 }
/*		 */ 
/*	71 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitWithoutUpdate(int i)
/*		 */	 {
/*	76 */		 if (this.items[i] != null) {
/*	77 */			 ItemStack itemstack = this.items[i];
/*		 */ 
/*	79 */			 this.items[i] = null;
/*	80 */			 return itemstack;
/*		 */		 }
/*	82 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public int i()
/*		 */	 {
/*	87 */		 int i = -1;
/*	88 */		 int j = 1;
/*		 */ 
/*	90 */		 for (int k = 0; k < this.items.length; k++) {
/*	91 */			 if ((this.items[k] == null) || (this.b.nextInt(j++) != 0) || 
/*	92 */				 (this.items[k].count == 0)) continue;
/*	93 */			 i = k;
/*		 */		 }
/*		 */ 
/*	97 */		 return i;
/*		 */	 }
/*		 */ 
/*		 */	 public void setItem(int i, ItemStack itemstack) {
/* 101 */		 this.items[i] = itemstack;
/* 102 */		 if ((itemstack != null) && (itemstack.count > getMaxStackSize())) {
/* 103 */			 itemstack.count = getMaxStackSize();
/*		 */		 }
/*		 */ 
/* 106 */		 update();
/*		 */	 }
/*		 */ 
/*		 */	 public int a(ItemStack itemstack) {
/* 110 */		 for (int i = 0; i < this.items.length; i++) {
/* 111 */			 if ((this.items[i] == null) || (this.items[i].id == 0)) {
/* 112 */				 this.items[i] = itemstack;
/* 113 */				 return i;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 117 */		 return -1;
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/* 121 */		 return "container.dispenser";
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/* 125 */		 super.a(nbttagcompound);
/* 126 */		 NBTTagList nbttaglist = nbttagcompound.getList("Items");
/*		 */ 
/* 128 */		 this.items = new ItemStack[getSize()];
/*		 */ 
/* 130 */		 for (int i = 0; i < nbttaglist.size(); i++) {
/* 131 */			 NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.get(i);
/* 132 */			 int j = nbttagcompound1.getByte("Slot") & 0xFF;
/*		 */ 
/* 134 */			 if ((j >= 0) && (j < this.items.length))
/* 135 */				 this.items[j] = ItemStack.a(nbttagcompound1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound)
/*		 */	 {
/* 141 */		 super.b(nbttagcompound);
/* 142 */		 NBTTagList nbttaglist = new NBTTagList();
/*		 */ 
/* 144 */		 for (int i = 0; i < this.items.length; i++) {
/* 145 */			 if (this.items[i] != null) {
/* 146 */				 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*		 */ 
/* 148 */				 nbttagcompound1.setByte("Slot", (byte)i);
/* 149 */				 this.items[i].save(nbttagcompound1);
/* 150 */				 nbttaglist.add(nbttagcompound1);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 154 */		 nbttagcompound.set("Items", nbttaglist);
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxStackSize() {
/* 158 */		 return this.maxStack;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityHuman entityhuman) {
/* 162 */		 return this.world.getTileEntity(this.x, this.y, this.z) == this;
/*		 */	 }
/*		 */ 
/*		 */	 public void startOpen()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void f()
/*		 */	 {
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.TileEntityDispenser
 * JD-Core Version:		0.6.0
 */