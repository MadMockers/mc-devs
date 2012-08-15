/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftInventoryFurnace;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftInventoryView;
/*		 */ 
/*		 */ public class ContainerFurnace extends Container
/*		 */ {
/*		 */	 private TileEntityFurnace furnace;
/*	13 */	 private int f = 0;
/*	14 */	 private int g = 0;
/*	15 */	 private int h = 0;
/*		 */ 
/*	18 */	 private CraftInventoryView bukkitEntity = null;
/*		 */	 private PlayerInventory player;
/*		 */ 
/*		 */	 public CraftInventoryView getBukkitView()
/*		 */	 {
/*	22 */		 if (this.bukkitEntity != null) {
/*	23 */			 return this.bukkitEntity;
/*		 */		 }
/*		 */ 
/*	26 */		 CraftInventoryFurnace inventory = new CraftInventoryFurnace(this.furnace);
/*	27 */		 this.bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, this);
/*	28 */		 return this.bukkitEntity;
/*		 */	 }
/*		 */ 
/*		 */	 public ContainerFurnace(PlayerInventory playerinventory, TileEntityFurnace tileentityfurnace)
/*		 */	 {
/*	33 */		 this.furnace = tileentityfurnace;
/*	34 */		 a(new Slot(tileentityfurnace, 0, 56, 17));
/*	35 */		 a(new Slot(tileentityfurnace, 1, 56, 53));
/*	36 */		 a(new SlotFurnaceResult(playerinventory.player, tileentityfurnace, 2, 116, 35));
/*	37 */		 this.player = playerinventory;
/*		 */ 
/*	41 */		 for (int i = 0; i < 3; i++) {
/*	42 */			 for (int j = 0; j < 9; j++) {
/*	43 */				 a(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	47 */		 for (i = 0; i < 9; i++)
/*	48 */			 a(new Slot(playerinventory, i, 8 + i * 18, 142));
/*		 */	 }
/*		 */ 
/*		 */	 public void addSlotListener(ICrafting icrafting)
/*		 */	 {
/*	53 */		 super.addSlotListener(icrafting);
/*	54 */		 icrafting.setContainerData(this, 0, this.furnace.cookTime);
/*	55 */		 icrafting.setContainerData(this, 1, this.furnace.burnTime);
/*	56 */		 icrafting.setContainerData(this, 2, this.furnace.ticksForCurrentFuel);
/*		 */	 }
/*		 */ 
/*		 */	 public void b() {
/*	60 */		 super.b();
/*	61 */		 Iterator iterator = this.listeners.iterator();
/*		 */ 
/*	63 */		 while (iterator.hasNext()) {
/*	64 */			 ICrafting icrafting = (ICrafting)iterator.next();
/*		 */ 
/*	66 */			 if (this.f != this.furnace.cookTime) {
/*	67 */				 icrafting.setContainerData(this, 0, this.furnace.cookTime);
/*		 */			 }
/*		 */ 
/*	70 */			 if (this.g != this.furnace.burnTime) {
/*	71 */				 icrafting.setContainerData(this, 1, this.furnace.burnTime);
/*		 */			 }
/*		 */ 
/*	74 */			 if (this.h != this.furnace.ticksForCurrentFuel) {
/*	75 */				 icrafting.setContainerData(this, 2, this.furnace.ticksForCurrentFuel);
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	79 */		 this.f = this.furnace.cookTime;
/*	80 */		 this.g = this.furnace.burnTime;
/*	81 */		 this.h = this.furnace.ticksForCurrentFuel;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman entityhuman) {
/*	85 */		 if (!this.checkReachable) return true;
/*	86 */		 return this.furnace.a(entityhuman);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack b(int i) {
/*	90 */		 ItemStack itemstack = null;
/*	91 */		 Slot slot = (Slot)this.b.get(i);
/*		 */ 
/*	93 */		 if ((slot != null) && (slot.d())) {
/*	94 */			 ItemStack itemstack1 = slot.getItem();
/*		 */ 
/*	96 */			 itemstack = itemstack1.cloneItemStack();
/*	97 */			 if (i == 2) {
/*	98 */				 if (!a(itemstack1, 3, 39, true)) {
/*	99 */					 return null;
/*		 */				 }
/*		 */ 
/* 102 */				 slot.a(itemstack1, itemstack);
/* 103 */			 } else if ((i != 1) && (i != 0)) {
/* 104 */				 if (RecipesFurnace.getInstance().getResult(itemstack1.getItem().id) != null) {
/* 105 */					 if (!a(itemstack1, 0, 1, false))
/* 106 */						 return null;
/*		 */				 }
/* 108 */				 else if (TileEntityFurnace.isFuel(itemstack1)) {
/* 109 */					 if (!a(itemstack1, 1, 2, false))
/* 110 */						 return null;
/*		 */				 }
/* 112 */				 else if ((i >= 3) && (i < 30)) {
/* 113 */					 if (!a(itemstack1, 30, 39, false))
/* 114 */						 return null;
/*		 */				 }
/* 116 */				 else if ((i >= 30) && (i < 39) && (!a(itemstack1, 3, 30, false)))
/* 117 */					 return null;
/*		 */			 }
/* 119 */			 else if (!a(itemstack1, 3, 39, false)) {
/* 120 */				 return null;
/*		 */			 }
/*		 */ 
/* 123 */			 if (itemstack1.count == 0)
/* 124 */				 slot.set((ItemStack)null);
/*		 */			 else {
/* 126 */				 slot.e();
/*		 */			 }
/*		 */ 
/* 129 */			 if (itemstack1.count == itemstack.count) {
/* 130 */				 return null;
/*		 */			 }
/*		 */ 
/* 133 */			 slot.b(itemstack1);
/*		 */		 }
/*		 */ 
/* 136 */		 return itemstack;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ContainerFurnace
 * JD-Core Version:		0.6.0
 */