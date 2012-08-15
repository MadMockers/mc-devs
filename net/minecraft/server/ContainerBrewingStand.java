/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftInventory;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftInventoryView;
/*		 */ 
/*		 */ public class ContainerBrewingStand extends Container
/*		 */ {
/*		 */	 private TileEntityBrewingStand brewingStand;
/*		 */	 private final Slot f;
/*	14 */	 private int g = 0;
/*		 */ 
/*	16 */	 private CraftInventoryView bukkitEntity = null;
/*		 */	 private PlayerInventory player;
/*		 */ 
/*		 */	 public ContainerBrewingStand(PlayerInventory playerinventory, TileEntityBrewingStand tileentitybrewingstand)
/*		 */	 {
/*	21 */		 this.player = playerinventory;
/*	22 */		 this.brewingStand = tileentitybrewingstand;
/*	23 */		 a(new SlotPotionBottle(playerinventory.player, tileentitybrewingstand, 0, 56, 46));
/*	24 */		 a(new SlotPotionBottle(playerinventory.player, tileentitybrewingstand, 1, 79, 53));
/*	25 */		 a(new SlotPotionBottle(playerinventory.player, tileentitybrewingstand, 2, 102, 46));
/*	26 */		 this.f = a(new SlotBrewing(this, tileentitybrewingstand, 3, 79, 17));
/*		 */ 
/*	30 */		 for (int i = 0; i < 3; i++) {
/*	31 */			 for (int j = 0; j < 9; j++) {
/*	32 */				 a(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	36 */		 for (i = 0; i < 9; i++)
/*	37 */			 a(new Slot(playerinventory, i, 8 + i * 18, 142));
/*		 */	 }
/*		 */ 
/*		 */	 public void addSlotListener(ICrafting icrafting)
/*		 */	 {
/*	42 */		 super.addSlotListener(icrafting);
/*	43 */		 icrafting.setContainerData(this, 0, this.brewingStand.t_());
/*		 */	 }
/*		 */ 
/*		 */	 public void b() {
/*	47 */		 super.b();
/*	48 */		 Iterator iterator = this.listeners.iterator();
/*		 */ 
/*	50 */		 while (iterator.hasNext()) {
/*	51 */			 ICrafting icrafting = (ICrafting)iterator.next();
/*		 */ 
/*	53 */			 if (this.g != this.brewingStand.t_()) {
/*	54 */				 icrafting.setContainerData(this, 0, this.brewingStand.t_());
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	58 */		 this.g = this.brewingStand.t_();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman entityhuman) {
/*	62 */		 if (!this.checkReachable) return true;
/*	63 */		 return this.brewingStand.a(entityhuman);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack b(int i) {
/*	67 */		 ItemStack itemstack = null;
/*	68 */		 Slot slot = (Slot)this.b.get(i);
/*		 */ 
/*	70 */		 if ((slot != null) && (slot.d())) {
/*	71 */			 ItemStack itemstack1 = slot.getItem();
/*		 */ 
/*	73 */			 itemstack = itemstack1.cloneItemStack();
/*	74 */			 if (((i < 0) || (i > 2)) && (i != 3)) {
/*	75 */				 if ((!this.f.d()) && (this.f.isAllowed(itemstack1))) {
/*	76 */					 if (!a(itemstack1, 3, 4, false))
/*	77 */						 return null;
/*		 */				 }
/*	79 */				 else if (SlotPotionBottle.a_(itemstack)) {
/*	80 */					 if (!a(itemstack1, 0, 3, false))
/*	81 */						 return null;
/*		 */				 }
/*	83 */				 else if ((i >= 4) && (i < 31)) {
/*	84 */					 if (!a(itemstack1, 31, 40, false))
/*	85 */						 return null;
/*		 */				 }
/*	87 */				 else if ((i >= 31) && (i < 40)) {
/*	88 */					 if (!a(itemstack1, 4, 31, false))
/*	89 */						 return null;
/*		 */				 }
/*	91 */				 else if (!a(itemstack1, 4, 40, false))
/*	92 */					 return null;
/*		 */			 }
/*		 */			 else {
/*	95 */				 if (!a(itemstack1, 4, 40, true)) {
/*	96 */					 return null;
/*		 */				 }
/*		 */ 
/*	99 */				 slot.a(itemstack1, itemstack);
/*		 */			 }
/*		 */ 
/* 102 */			 if (itemstack1.count == 0)
/* 103 */				 slot.set((ItemStack)null);
/*		 */			 else {
/* 105 */				 slot.e();
/*		 */			 }
/*		 */ 
/* 108 */			 if (itemstack1.count == itemstack.count) {
/* 109 */				 return null;
/*		 */			 }
/*		 */ 
/* 112 */			 slot.b(itemstack1);
/*		 */		 }
/*		 */ 
/* 115 */		 return itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 public CraftInventoryView getBukkitView()
/*		 */	 {
/* 120 */		 if (this.bukkitEntity != null) {
/* 121 */			 return this.bukkitEntity;
/*		 */		 }
/*		 */ 
/* 124 */		 CraftInventory inventory = new CraftInventory(this.brewingStand);
/* 125 */		 this.bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, this);
/* 126 */		 return this.bukkitEntity;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ContainerBrewingStand
 * JD-Core Version:		0.6.0
 */