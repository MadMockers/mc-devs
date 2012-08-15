/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import org.bukkit.craftbukkit.entity.CraftHumanEntity;
/*		 */ import org.bukkit.entity.HumanEntity;
/*		 */ import org.bukkit.inventory.InventoryHolder;
/*		 */ 
/*		 */ public class InventoryMerchant
/*		 */	 implements IInventory
/*		 */ {
/*		 */	 private final IMerchant merchant;
/*	12 */	 private ItemStack[] itemsInSlots = new ItemStack[3];
/*		 */	 private final EntityHuman player;
/*		 */	 private MerchantRecipe recipe;
/*		 */	 private int e;
/*	18 */	 public List<HumanEntity> transaction = new ArrayList();
/*	19 */	 private int maxStack = 64;
/*		 */ 
/*		 */	 public ItemStack[] getContents() {
/*	22 */		 return this.itemsInSlots;
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
/*		 */	 public void setMaxStackSize(int i) {
/*	38 */		 this.maxStack = i;
/*		 */	 }
/*		 */ 
/*		 */	 public InventoryHolder getOwner() {
/*	42 */		 return this.player.getBukkitEntity();
/*		 */	 }
/*		 */ 
/*		 */	 public InventoryMerchant(EntityHuman entityhuman, IMerchant imerchant)
/*		 */	 {
/*	47 */		 this.player = entityhuman;
/*	48 */		 this.merchant = imerchant;
/*		 */	 }
/*		 */ 
/*		 */	 public int getSize() {
/*	52 */		 return this.itemsInSlots.length;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack getItem(int i) {
/*	56 */		 return this.itemsInSlots[i];
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitStack(int i, int j) {
/*	60 */		 if (this.itemsInSlots[i] != null)
/*		 */		 {
/*	63 */			 if (i == 2) {
/*	64 */				 ItemStack itemstack = this.itemsInSlots[i];
/*	65 */				 this.itemsInSlots[i] = null;
/*	66 */				 return itemstack;
/*	67 */			 }if (this.itemsInSlots[i].count <= j) {
/*	68 */				 ItemStack itemstack = this.itemsInSlots[i];
/*	69 */				 this.itemsInSlots[i] = null;
/*	70 */				 if (d(i)) {
/*	71 */					 g();
/*		 */				 }
/*		 */ 
/*	74 */				 return itemstack;
/*		 */			 }
/*	76 */			 ItemStack itemstack = this.itemsInSlots[i].a(j);
/*	77 */			 if (this.itemsInSlots[i].count == 0) {
/*	78 */				 this.itemsInSlots[i] = null;
/*		 */			 }
/*		 */ 
/*	81 */			 if (d(i)) {
/*	82 */				 g();
/*		 */			 }
/*		 */ 
/*	85 */			 return itemstack;
/*		 */		 }
/*		 */ 
/*	88 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean d(int i)
/*		 */	 {
/*	93 */		 return (i == 0) || (i == 1);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitWithoutUpdate(int i) {
/*	97 */		 if (this.itemsInSlots[i] != null) {
/*	98 */			 ItemStack itemstack = this.itemsInSlots[i];
/*		 */ 
/* 100 */			 this.itemsInSlots[i] = null;
/* 101 */			 return itemstack;
/*		 */		 }
/* 103 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void setItem(int i, ItemStack itemstack)
/*		 */	 {
/* 108 */		 this.itemsInSlots[i] = itemstack;
/* 109 */		 if ((itemstack != null) && (itemstack.count > getMaxStackSize())) {
/* 110 */			 itemstack.count = getMaxStackSize();
/*		 */		 }
/*		 */ 
/* 113 */		 if (d(i))
/* 114 */			 g();
/*		 */	 }
/*		 */ 
/*		 */	 public String getName()
/*		 */	 {
/* 119 */		 return "mob.villager";
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxStackSize() {
/* 123 */		 return this.maxStack;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityHuman entityhuman) {
/* 127 */		 return this.merchant.l_() == entityhuman;
/*		 */	 }
/*		 */	 public void startOpen() {
/*		 */	 }
/*		 */	 public void f() {
/*		 */	 }
/*		 */ 
/*		 */	 public void update() {
/* 135 */		 g();
/*		 */	 }
/*		 */ 
/*		 */	 public void g() {
/* 139 */		 this.recipe = null;
/* 140 */		 ItemStack itemstack = this.itemsInSlots[0];
/* 141 */		 ItemStack itemstack1 = this.itemsInSlots[1];
/*		 */ 
/* 143 */		 if (itemstack == null) {
/* 144 */			 itemstack = itemstack1;
/* 145 */			 itemstack1 = null;
/*		 */		 }
/*		 */ 
/* 148 */		 if (itemstack == null) {
/* 149 */			 setItem(2, (ItemStack)null);
/*		 */		 } else {
/* 151 */			 MerchantRecipeList merchantrecipelist = this.merchant.getOffers(this.player);
/*		 */ 
/* 153 */			 if (merchantrecipelist != null) {
/* 154 */				 MerchantRecipe merchantrecipe = merchantrecipelist.a(itemstack, itemstack1, this.e);
/*		 */ 
/* 156 */				 if (merchantrecipe != null) {
/* 157 */					 this.recipe = merchantrecipe;
/* 158 */					 setItem(2, merchantrecipe.getBuyItem3().cloneItemStack());
/* 159 */				 } else if (itemstack1 != null) {
/* 160 */					 merchantrecipe = merchantrecipelist.a(itemstack1, itemstack, this.e);
/* 161 */					 if (merchantrecipe != null) {
/* 162 */						 this.recipe = merchantrecipe;
/* 163 */						 setItem(2, merchantrecipe.getBuyItem3().cloneItemStack());
/*		 */					 } else {
/* 165 */						 setItem(2, (ItemStack)null);
/*		 */					 }
/*		 */				 } else {
/* 168 */					 setItem(2, (ItemStack)null);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public MerchantRecipe getRecipe() {
/* 175 */		 return this.recipe;
/*		 */	 }
/*		 */ 
/*		 */	 public void c(int i) {
/* 179 */		 this.e = i;
/* 180 */		 g();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.InventoryMerchant
 * JD-Core Version:		0.6.0
 */