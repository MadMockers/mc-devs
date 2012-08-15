/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftInventoryCrafting;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftInventoryView;
/*		 */ 
/*		 */ public class ContainerPlayer extends Container
/*		 */ {
/*		 */	 public InventoryCrafting craftInventory;
/*		 */	 public IInventory resultInventory;
/*		 */	 public boolean g;
/*	14 */	 private CraftInventoryView bukkitEntity = null;
/*		 */	 private PlayerInventory player;
/*		 */ 
/*		 */	 public ContainerPlayer(PlayerInventory playerinventory)
/*		 */	 {
/*	19 */		 this(playerinventory, true);
/*		 */	 }
/*		 */ 
/*		 */	 public ContainerPlayer(PlayerInventory playerinventory, boolean flag) {
/*	23 */		 this.resultInventory = new InventoryCraftResult();
/*	24 */		 this.craftInventory = new InventoryCrafting(this, 2, 2, playerinventory.player);
/*	25 */		 this.craftInventory.resultInventory = this.resultInventory;
/*	26 */		 this.player = playerinventory;
/*	27 */		 this.g = false;
/*	28 */		 this.g = flag;
/*	29 */		 a(new SlotResult(playerinventory.player, this.craftInventory, this.resultInventory, 0, 144, 36));
/*		 */ 
/*	34 */		 for (int i = 0; i < 2; i++) {
/*	35 */			 for (int j = 0; j < 2; j++) {
/*	36 */				 a(new Slot(this.craftInventory, j + i * 2, 88 + j * 18, 26 + i * 18));
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	40 */		 for (i = 0; i < 4; i++) {
/*	41 */			 a(new SlotArmor(this, playerinventory, playerinventory.getSize() - 1 - i, 8, 8 + i * 18, i));
/*		 */		 }
/*		 */ 
/*	44 */		 for (i = 0; i < 3; i++) {
/*	45 */			 for (int j = 0; j < 9; j++) {
/*	46 */				 a(new Slot(playerinventory, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	50 */		 for (i = 0; i < 9; i++)
/*	51 */			 a(new Slot(playerinventory, i, 8 + i * 18, 142));
/*		 */	 }
/*		 */ 
/*		 */	 public void a(IInventory iinventory)
/*		 */	 {
/*	59 */		 CraftingManager.getInstance().lastCraftView = getBukkitView();
/*	60 */		 ItemStack craftResult = CraftingManager.getInstance().craft(this.craftInventory);
/*	61 */		 this.resultInventory.setItem(0, craftResult);
/*	62 */		 if (this.listeners.size() < 1) {
/*	63 */			 return;
/*		 */		 }
/*		 */ 
/*	66 */		 EntityPlayer player = (EntityPlayer)this.listeners.get(0);
/*	67 */		 player.netServerHandler.sendPacket(new Packet103SetSlot(player.activeContainer.windowId, 0, craftResult));
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityHuman entityhuman)
/*		 */	 {
/*	72 */		 super.a(entityhuman);
/*		 */ 
/*	74 */		 for (int i = 0; i < 4; i++) {
/*	75 */			 ItemStack itemstack = this.craftInventory.splitWithoutUpdate(i);
/*		 */ 
/*	77 */			 if (itemstack != null) {
/*	78 */				 entityhuman.drop(itemstack);
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	82 */		 this.resultInventory.setItem(0, (ItemStack)null);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman entityhuman) {
/*	86 */		 return true;
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
/*	97 */			 if (i == 0) {
/*	98 */				 if (!a(itemstack1, 9, 45, true)) {
/*	99 */					 return null;
/*		 */				 }
/*		 */ 
/* 102 */				 slot.a(itemstack1, itemstack);
/* 103 */			 } else if ((i >= 1) && (i < 5)) {
/* 104 */				 if (!a(itemstack1, 9, 45, false))
/* 105 */					 return null;
/*		 */			 }
/* 107 */			 else if ((i >= 5) && (i < 9)) {
/* 108 */				 if (!a(itemstack1, 9, 45, false))
/* 109 */					 return null;
/*		 */			 }
/* 111 */			 else if (((itemstack.getItem() instanceof ItemArmor)) && (!((Slot)this.b.get(5 + ((ItemArmor)itemstack.getItem()).a)).d())) {
/* 112 */				 int j = 5 + ((ItemArmor)itemstack.getItem()).a;
/*		 */ 
/* 114 */				 if (!a(itemstack1, j, j + 1, false))
/* 115 */					 return null;
/*		 */			 }
/* 117 */			 else if ((i >= 9) && (i < 36)) {
/* 118 */				 if (!a(itemstack1, 36, 45, false))
/* 119 */					 return null;
/*		 */			 }
/* 121 */			 else if ((i >= 36) && (i < 45)) {
/* 122 */				 if (!a(itemstack1, 9, 36, false))
/* 123 */					 return null;
/*		 */			 }
/* 125 */			 else if (!a(itemstack1, 9, 45, false)) {
/* 126 */				 return null;
/*		 */			 }
/*		 */ 
/* 129 */			 if (itemstack1.count == 0)
/* 130 */				 slot.set((ItemStack)null);
/*		 */			 else {
/* 132 */				 slot.e();
/*		 */			 }
/*		 */ 
/* 135 */			 if (itemstack1.count == itemstack.count) {
/* 136 */				 return null;
/*		 */			 }
/*		 */ 
/* 139 */			 slot.b(itemstack1);
/*		 */		 }
/*		 */ 
/* 142 */		 return itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 public CraftInventoryView getBukkitView()
/*		 */	 {
/* 147 */		 if (this.bukkitEntity != null) {
/* 148 */			 return this.bukkitEntity;
/*		 */		 }
/*		 */ 
/* 151 */		 CraftInventoryCrafting inventory = new CraftInventoryCrafting(this.craftInventory, this.resultInventory);
/* 152 */		 this.bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, this);
/* 153 */		 return this.bukkitEntity;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ContainerPlayer
 * JD-Core Version:		0.6.0
 */