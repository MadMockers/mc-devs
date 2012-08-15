/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftInventoryCrafting;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftInventoryView;
/*		 */ 
/*		 */ public class ContainerWorkbench extends Container
/*		 */ {
/*		 */	 public InventoryCrafting craftInventory;
/*		 */	 public IInventory resultInventory;
/*		 */	 private World g;
/*		 */	 private int h;
/*		 */	 private int i;
/*		 */	 private int j;
/*	17 */	 private CraftInventoryView bukkitEntity = null;
/*		 */	 private PlayerInventory player;
/*		 */ 
/*		 */	 public ContainerWorkbench(PlayerInventory playerinventory, World world, int i, int j, int k)
/*		 */	 {
/*	23 */		 this.resultInventory = new InventoryCraftResult();
/*	24 */		 this.craftInventory = new InventoryCrafting(this, 3, 3, playerinventory.player);
/*	25 */		 this.craftInventory.resultInventory = this.resultInventory;
/*	26 */		 this.player = playerinventory;
/*		 */ 
/*	28 */		 this.g = world;
/*	29 */		 this.h = i;
/*	30 */		 this.i = j;
/*	31 */		 this.j = k;
/*	32 */		 a(new SlotResult(playerinventory.player, this.craftInventory, this.resultInventory, 0, 124, 35));
/*		 */ 
/*	37 */		 for (int l = 0; l < 3; l++) {
/*	38 */			 for (int i1 = 0; i1 < 3; i1++) {
/*	39 */				 a(new Slot(this.craftInventory, i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	43 */		 for (l = 0; l < 3; l++) {
/*	44 */			 for (int i1 = 0; i1 < 9; i1++) {
/*	45 */				 a(new Slot(playerinventory, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	49 */		 for (l = 0; l < 9; l++) {
/*	50 */			 a(new Slot(playerinventory, l, 8 + l * 18, 142));
/*		 */		 }
/*		 */ 
/*	53 */		 a(this.craftInventory);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(IInventory iinventory)
/*		 */	 {
/*	58 */		 CraftingManager.getInstance().lastCraftView = getBukkitView();
/*	59 */		 ItemStack craftResult = CraftingManager.getInstance().craft(this.craftInventory);
/*	60 */		 this.resultInventory.setItem(0, craftResult);
/*	61 */		 if (this.listeners.size() < 1) {
/*	62 */			 return;
/*		 */		 }
/*		 */ 
/*	65 */		 EntityPlayer player = (EntityPlayer)this.listeners.get(0);
/*	66 */		 player.netServerHandler.sendPacket(new Packet103SetSlot(player.activeContainer.windowId, 0, craftResult));
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityHuman entityhuman)
/*		 */	 {
/*	71 */		 super.a(entityhuman);
/*	72 */		 if (!this.g.isStatic)
/*	73 */			 for (int i = 0; i < 9; i++) {
/*	74 */				 ItemStack itemstack = this.craftInventory.splitWithoutUpdate(i);
/*		 */ 
/*	76 */				 if (itemstack != null)
/*	77 */					 entityhuman.drop(itemstack);
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman entityhuman)
/*		 */	 {
/*	84 */		 if (!this.checkReachable) return true;
/*	85 */		 return this.g.getTypeId(this.h, this.i, this.j) == Block.WORKBENCH.id;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack b(int i) {
/*	89 */		 ItemStack itemstack = null;
/*	90 */		 Slot slot = (Slot)this.b.get(i);
/*		 */ 
/*	92 */		 if ((slot != null) && (slot.d())) {
/*	93 */			 ItemStack itemstack1 = slot.getItem();
/*		 */ 
/*	95 */			 itemstack = itemstack1.cloneItemStack();
/*	96 */			 if (i == 0) {
/*	97 */				 if (!a(itemstack1, 10, 46, true)) {
/*	98 */					 return null;
/*		 */				 }
/*		 */ 
/* 101 */				 slot.a(itemstack1, itemstack);
/* 102 */			 } else if ((i >= 10) && (i < 37)) {
/* 103 */				 if (!a(itemstack1, 37, 46, false))
/* 104 */					 return null;
/*		 */			 }
/* 106 */			 else if ((i >= 37) && (i < 46)) {
/* 107 */				 if (!a(itemstack1, 10, 37, false))
/* 108 */					 return null;
/*		 */			 }
/* 110 */			 else if (!a(itemstack1, 10, 46, false)) {
/* 111 */				 return null;
/*		 */			 }
/*		 */ 
/* 114 */			 if (itemstack1.count == 0)
/* 115 */				 slot.set((ItemStack)null);
/*		 */			 else {
/* 117 */				 slot.e();
/*		 */			 }
/*		 */ 
/* 120 */			 if (itemstack1.count == itemstack.count) {
/* 121 */				 return null;
/*		 */			 }
/*		 */ 
/* 124 */			 slot.b(itemstack1);
/*		 */		 }
/*		 */ 
/* 127 */		 return itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 public CraftInventoryView getBukkitView()
/*		 */	 {
/* 132 */		 if (this.bukkitEntity != null) {
/* 133 */			 return this.bukkitEntity;
/*		 */		 }
/*		 */ 
/* 136 */		 CraftInventoryCrafting inventory = new CraftInventoryCrafting(this.craftInventory, this.resultInventory);
/* 137 */		 this.bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, this);
/* 138 */		 return this.bukkitEntity;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ContainerWorkbench
 * JD-Core Version:		0.6.0
 */