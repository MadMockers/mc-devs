/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import org.bukkit.craftbukkit.entity.CraftHumanEntity;
/*		 */ import org.bukkit.entity.HumanEntity;
/*		 */ import org.bukkit.event.inventory.InventoryType;
/*		 */ import org.bukkit.inventory.InventoryHolder;
/*		 */ 
/*		 */ public class InventoryCrafting
/*		 */	 implements IInventory
/*		 */ {
/*		 */	 private ItemStack[] items;
/*		 */	 private int b;
/*		 */	 private Container c;
/*	18 */	 public List<HumanEntity> transaction = new ArrayList();
/*		 */	 public IRecipe currentRecipe;
/*		 */	 public IInventory resultInventory;
/*		 */	 private EntityHuman owner;
/*	22 */	 private int maxStack = 64;
/*		 */ 
/*		 */	 public ItemStack[] getContents() {
/*	25 */		 return this.items;
/*		 */	 }
/*		 */ 
/*		 */	 public void onOpen(CraftHumanEntity who) {
/*	29 */		 this.transaction.add(who);
/*		 */	 }
/*		 */ 
/*		 */	 public InventoryType getInvType() {
/*	33 */		 return this.items.length == 4 ? InventoryType.CRAFTING : InventoryType.WORKBENCH;
/*		 */	 }
/*		 */ 
/*		 */	 public void onClose(CraftHumanEntity who) {
/*	37 */		 this.transaction.remove(who);
/*		 */	 }
/*		 */ 
/*		 */	 public List<HumanEntity> getViewers() {
/*	41 */		 return this.transaction;
/*		 */	 }
/*		 */ 
/*		 */	 public InventoryHolder getOwner() {
/*	45 */		 return this.owner.getBukkitEntity();
/*		 */	 }
/*		 */ 
/*		 */	 public void setMaxStackSize(int size) {
/*	49 */		 this.maxStack = size;
/*	50 */		 this.resultInventory.setMaxStackSize(size);
/*		 */	 }
/*		 */ 
/*		 */	 public InventoryCrafting(Container container, int i, int j, EntityHuman player) {
/*	54 */		 this(container, i, j);
/*	55 */		 this.owner = player;
/*		 */	 }
/*		 */ 
/*		 */	 public InventoryCrafting(Container container, int i, int j)
/*		 */	 {
/*	60 */		 int k = i * j;
/*		 */ 
/*	62 */		 this.items = new ItemStack[k];
/*	63 */		 this.c = container;
/*	64 */		 this.b = i;
/*		 */	 }
/*		 */ 
/*		 */	 public int getSize() {
/*	68 */		 return this.items.length;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack getItem(int i) {
/*	72 */		 return i >= getSize() ? null : this.items[i];
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack b(int i, int j) {
/*	76 */		 if ((i >= 0) && (i < this.b)) {
/*	77 */			 int k = i + j * this.b;
/*		 */ 
/*	79 */			 return getItem(k);
/*		 */		 }
/*	81 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public String getName()
/*		 */	 {
/*	86 */		 return "container.crafting";
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitWithoutUpdate(int i) {
/*	90 */		 if (this.items[i] != null) {
/*	91 */			 ItemStack itemstack = this.items[i];
/*		 */ 
/*	93 */			 this.items[i] = null;
/*	94 */			 return itemstack;
/*		 */		 }
/*	96 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitStack(int i, int j)
/*		 */	 {
/* 101 */		 if (this.items[i] != null)
/*		 */		 {
/* 104 */			 if (this.items[i].count <= j) {
/* 105 */				 ItemStack itemstack = this.items[i];
/* 106 */				 this.items[i] = null;
/* 107 */				 this.c.a(this);
/* 108 */				 return itemstack;
/*		 */			 }
/* 110 */			 ItemStack itemstack = this.items[i].a(j);
/* 111 */			 if (this.items[i].count == 0) {
/* 112 */				 this.items[i] = null;
/*		 */			 }
/*		 */ 
/* 115 */			 this.c.a(this);
/* 116 */			 return itemstack;
/*		 */		 }
/*		 */ 
/* 119 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void setItem(int i, ItemStack itemstack)
/*		 */	 {
/* 124 */		 this.items[i] = itemstack;
/* 125 */		 this.c.a(this);
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxStackSize() {
/* 129 */		 return this.maxStack;
/*		 */	 }
/*		 */	 public void update() {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityHuman entityhuman) {
/* 135 */		 return true;
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
 * Qualified Name:		 net.minecraft.server.InventoryCrafting
 * JD-Core Version:		0.6.0
 */