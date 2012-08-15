/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftInventoryMerchant;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftInventoryView;
/*		 */ 
/*		 */ public class ContainerMerchant extends Container
/*		 */ {
/*		 */	 private IMerchant merchant;
/*		 */	 private InventoryMerchant f;
/*		 */	 private final World g;
/*	12 */	 private CraftInventoryView bukkitEntity = null;
/*		 */	 private PlayerInventory player;
/*		 */ 
/*		 */	 public CraftInventoryView getBukkitView()
/*		 */	 {
/*	17 */		 if (this.bukkitEntity == null) {
/*	18 */			 this.bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), new CraftInventoryMerchant(getMerchantInventory()), this);
/*		 */		 }
/*	20 */		 return this.bukkitEntity;
/*		 */	 }
/*		 */ 
/*		 */	 public ContainerMerchant(PlayerInventory playerinventory, IMerchant imerchant, World world)
/*		 */	 {
/*	25 */		 this.merchant = imerchant;
/*	26 */		 this.g = world;
/*	27 */		 this.f = new InventoryMerchant(playerinventory.player, imerchant);
/*	28 */		 a(new Slot(this.f, 0, 36, 53));
/*	29 */		 a(new Slot(this.f, 1, 62, 53));
/*	30 */		 a(new SlotMerchantResult(playerinventory.player, imerchant, this.f, 2, 120, 53));
/*	31 */		 this.player = playerinventory;
/*		 */ 
/*	35 */		 for (int i = 0; i < 3; i++) {
/*	36 */			 for (int j = 0; j < 9; j++) {
/*	37 */				 a(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	41 */		 for (i = 0; i < 9; i++)
/*	42 */			 a(new Slot(playerinventory, i, 8 + i * 18, 142));
/*		 */	 }
/*		 */ 
/*		 */	 public InventoryMerchant getMerchantInventory()
/*		 */	 {
/*	47 */		 return this.f;
/*		 */	 }
/*		 */ 
/*		 */	 public void addSlotListener(ICrafting icrafting) {
/*	51 */		 super.addSlotListener(icrafting);
/*		 */	 }
/*		 */ 
/*		 */	 public void b() {
/*	55 */		 super.b();
/*		 */	 }
/*		 */ 
/*		 */	 public void a(IInventory iinventory) {
/*	59 */		 this.f.g();
/*	60 */		 super.a(iinventory);
/*		 */	 }
/*		 */ 
/*		 */	 public void c(int i) {
/*	64 */		 this.f.c(i);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman entityhuman) {
/*	68 */		 return this.merchant.l_() == entityhuman;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack b(int i) {
/*	72 */		 ItemStack itemstack = null;
/*	73 */		 Slot slot = (Slot)this.b.get(i);
/*		 */ 
/*	75 */		 if ((slot != null) && (slot.d())) {
/*	76 */			 ItemStack itemstack1 = slot.getItem();
/*		 */ 
/*	78 */			 itemstack = itemstack1.cloneItemStack();
/*	79 */			 if (i == 2) {
/*	80 */				 if (!a(itemstack1, 3, 39, true)) {
/*	81 */					 return null;
/*		 */				 }
/*		 */ 
/*	84 */				 slot.a(itemstack1, itemstack);
/*	85 */			 } else if ((i != 0) && (i != 1)) {
/*	86 */				 if ((i >= 3) && (i < 30)) {
/*	87 */					 if (!a(itemstack1, 30, 39, false))
/*	88 */						 return null;
/*		 */				 }
/*	90 */				 else if ((i >= 30) && (i < 39) && (!a(itemstack1, 3, 30, false)))
/*	91 */					 return null;
/*		 */			 }
/*	93 */			 else if (!a(itemstack1, 3, 39, false)) {
/*	94 */				 return null;
/*		 */			 }
/*		 */ 
/*	97 */			 if (itemstack1.count == 0)
/*	98 */				 slot.set((ItemStack)null);
/*		 */			 else {
/* 100 */				 slot.e();
/*		 */			 }
/*		 */ 
/* 103 */			 if (itemstack1.count == itemstack.count) {
/* 104 */				 return null;
/*		 */			 }
/*		 */ 
/* 107 */			 slot.b(itemstack1);
/*		 */		 }
/*		 */ 
/* 110 */		 return itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityHuman entityhuman) {
/* 114 */		 super.a(entityhuman);
/* 115 */		 this.merchant.a_((EntityHuman)null);
/* 116 */		 super.a(entityhuman);
/* 117 */		 if (!this.g.isStatic) {
/* 118 */			 ItemStack itemstack = this.f.splitWithoutUpdate(0);
/*		 */ 
/* 120 */			 if (itemstack != null) {
/* 121 */				 entityhuman.drop(itemstack);
/*		 */			 }
/*		 */ 
/* 124 */			 itemstack = this.f.splitWithoutUpdate(1);
/* 125 */			 if (itemstack != null)
/* 126 */				 entityhuman.drop(itemstack);
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ContainerMerchant
 * JD-Core Version:		0.6.0
 */