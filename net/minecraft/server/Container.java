/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.HashSet;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Set;
/*		 */ import org.bukkit.craftbukkit.entity.CraftHumanEntity;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftInventory;
/*		 */ import org.bukkit.inventory.InventoryView;
/*		 */ 
/*		 */ public abstract class Container
/*		 */ {
/*	16 */	 public List a = new ArrayList();
/*	17 */	 public List b = new ArrayList();
/*	18 */	 public int windowId = 0;
/*	19 */	 private short e = 0;
/*	20 */	 protected List listeners = new ArrayList();
/*	21 */	 private Set f = new HashSet();
/*		 */ 
/*	24 */	 public boolean checkReachable = true;
/*		 */ 
/*		 */	 public abstract InventoryView getBukkitView();
/*		 */ 
/*	27 */	 public void transferTo(Container other, CraftHumanEntity player) { InventoryView source = getBukkitView(); InventoryView destination = other.getBukkitView();
/*	28 */		 ((CraftInventory)source.getTopInventory()).getInventory().onClose(player);
/*	29 */		 ((CraftInventory)source.getBottomInventory()).getInventory().onClose(player);
/*	30 */		 ((CraftInventory)destination.getTopInventory()).getInventory().onOpen(player);
/*	31 */		 ((CraftInventory)destination.getBottomInventory()).getInventory().onOpen(player);
/*		 */	 }
/*		 */ 
/*		 */	 protected Slot a(Slot slot)
/*		 */	 {
/*	38 */		 slot.d = this.b.size();
/*	39 */		 this.b.add(slot);
/*	40 */		 this.a.add(null);
/*	41 */		 return slot;
/*		 */	 }
/*		 */ 
/*		 */	 public void addSlotListener(ICrafting icrafting) {
/*	45 */		 if (this.listeners.contains(icrafting)) {
/*	46 */			 throw new IllegalArgumentException("Listener already listening");
/*		 */		 }
/*	48 */		 this.listeners.add(icrafting);
/*	49 */		 icrafting.a(this, a());
/*	50 */		 b();
/*		 */	 }
/*		 */ 
/*		 */	 public List a()
/*		 */	 {
/*	55 */		 ArrayList arraylist = new ArrayList();
/*	56 */		 Iterator iterator = this.b.iterator();
/*		 */ 
/*	58 */		 while (iterator.hasNext()) {
/*	59 */			 Slot slot = (Slot)iterator.next();
/*		 */ 
/*	61 */			 arraylist.add(slot.getItem());
/*		 */		 }
/*		 */ 
/*	64 */		 return arraylist;
/*		 */	 }
/*		 */ 
/*		 */	 public void b() {
/*	68 */		 for (int i = 0; i < this.b.size(); i++) {
/*	69 */			 ItemStack itemstack = ((Slot)this.b.get(i)).getItem();
/*	70 */			 ItemStack itemstack1 = (ItemStack)this.a.get(i);
/*		 */ 
/*	72 */			 if (!ItemStack.matches(itemstack1, itemstack)) {
/*	73 */				 itemstack1 = itemstack == null ? null : itemstack.cloneItemStack();
/*	74 */				 this.a.set(i, itemstack1);
/*	75 */				 Iterator iterator = this.listeners.iterator();
/*		 */ 
/*	77 */				 while (iterator.hasNext()) {
/*	78 */					 ICrafting icrafting = (ICrafting)iterator.next();
/*		 */ 
/*	80 */					 icrafting.a(this, i, itemstack1);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityHuman entityhuman, int i) {
/*	87 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public Slot a(IInventory iinventory, int i) {
/*	91 */		 Iterator iterator = this.b.iterator();
/*		 */		 Slot slot;
/*		 */		 do {
/*	96 */			 if (!iterator.hasNext()) {
/*	97 */				 return null;
/*		 */			 }
/*		 */ 
/* 100 */			 slot = (Slot)iterator.next();
/* 101 */		 }while (!slot.a(iinventory, i));
/*		 */ 
/* 103 */		 return slot;
/*		 */	 }
/*		 */ 
/*		 */	 public Slot getSlot(int i) {
/* 107 */		 return (Slot)this.b.get(i);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack b(int i) {
/* 111 */		 Slot slot = (Slot)this.b.get(i);
/*		 */ 
/* 113 */		 return slot != null ? slot.getItem() : null;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack clickItem(int i, int j, boolean flag, EntityHuman entityhuman) {
/* 117 */		 ItemStack itemstack = null;
/*		 */ 
/* 119 */		 if (j > 1) {
/* 120 */			 return null;
/*		 */		 }
/* 122 */		 if ((j == 0) || (j == 1)) {
/* 123 */			 PlayerInventory playerinventory = entityhuman.inventory;
/*		 */ 
/* 125 */			 if (i == -999) {
/* 126 */				 if ((playerinventory.getCarried() != null) && (i == -999)) {
/* 127 */					 if (j == 0) {
/* 128 */						 entityhuman.drop(playerinventory.getCarried());
/* 129 */						 playerinventory.setCarried((ItemStack)null);
/*		 */					 }
/*		 */ 
/* 132 */					 if (j == 1)
/*		 */					 {
/* 134 */						 ItemStack itemstack1 = playerinventory.getCarried();
/* 135 */						 if (itemstack1.count > 0) {
/* 136 */							 entityhuman.drop(itemstack1.a(1));
/*		 */						 }
/*		 */ 
/* 139 */						 if (itemstack1.count == 0)
/*		 */						 {
/* 141 */							 playerinventory.setCarried((ItemStack)null);
/*		 */						 }
/*		 */					 }
/*		 */				 }
/* 145 */			 } else if (flag) {
/* 146 */				 ItemStack itemstack1 = b(i);
/*		 */ 
/* 148 */				 if (itemstack1 != null) {
/* 149 */					 int k = itemstack1.id;
/*		 */ 
/* 151 */					 itemstack = itemstack1.cloneItemStack();
/* 152 */					 Slot slot = (Slot)this.b.get(i);
/*		 */ 
/* 154 */					 if ((slot != null) && (slot.getItem() != null) && (slot.getItem().id == k))
/* 155 */						 b(i, j, flag, entityhuman);
/*		 */				 }
/*		 */			 }
/*		 */			 else {
/* 159 */				 if (i < 0) {
/* 160 */					 return null;
/*		 */				 }
/*		 */ 
/* 163 */				 Slot slot1 = (Slot)this.b.get(i);
/*		 */ 
/* 165 */				 if (slot1 != null) {
/* 166 */					 ItemStack itemstack2 = slot1.getItem();
/* 167 */					 ItemStack itemstack3 = playerinventory.getCarried();
/*		 */ 
/* 169 */					 if (itemstack2 != null) {
/* 170 */						 itemstack = itemstack2.cloneItemStack();
/*		 */					 }
/*		 */ 
/* 175 */					 if (itemstack2 == null) {
/* 176 */						 if ((itemstack3 != null) && (slot1.isAllowed(itemstack3))) {
/* 177 */							 int l = j == 0 ? itemstack3.count : 1;
/* 178 */							 if (l > slot1.a()) {
/* 179 */								 l = slot1.a();
/*		 */							 }
/*		 */ 
/* 183 */							 if (itemstack3.count >= l) {
/* 184 */								 slot1.set(itemstack3.a(l));
/*		 */							 }
/*		 */ 
/* 188 */							 if (itemstack3.count == 0)
/* 189 */								 playerinventory.setCarried((ItemStack)null);
/*		 */						 }
/*		 */					 }
/* 192 */					 else if (itemstack3 == null) {
/* 193 */						 int l = j == 0 ? itemstack2.count : (itemstack2.count + 1) / 2;
/* 194 */						 ItemStack itemstack4 = slot1.a(l);
/*		 */ 
/* 196 */						 playerinventory.setCarried(itemstack4);
/* 197 */						 if (itemstack2.count == 0) {
/* 198 */							 slot1.set((ItemStack)null);
/*		 */						 }
/*		 */ 
/* 201 */						 slot1.b(playerinventory.getCarried());
/* 202 */					 } else if (slot1.isAllowed(itemstack3)) {
/* 203 */						 if ((itemstack2.id == itemstack3.id) && ((!itemstack2.usesData()) || (itemstack2.getData() == itemstack3.getData())) && (ItemStack.equals(itemstack2, itemstack3))) {
/* 204 */							 int l = j == 0 ? itemstack3.count : 1;
/* 205 */							 if (l > slot1.a() - itemstack2.count) {
/* 206 */								 l = slot1.a() - itemstack2.count;
/*		 */							 }
/*		 */ 
/* 209 */							 if (l > itemstack3.getMaxStackSize() - itemstack2.count) {
/* 210 */								 l = itemstack3.getMaxStackSize() - itemstack2.count;
/*		 */							 }
/*		 */ 
/* 213 */							 itemstack3.a(l);
/* 214 */							 if (itemstack3.count == 0) {
/* 215 */								 playerinventory.setCarried((ItemStack)null);
/*		 */							 }
/*		 */ 
/* 218 */							 itemstack2.count += l;
/* 219 */						 } else if (itemstack3.count <= slot1.a()) {
/* 220 */							 slot1.set(itemstack3);
/* 221 */							 playerinventory.setCarried(itemstack2);
/*		 */						 }
/* 223 */					 } else if ((itemstack2.id == itemstack3.id) && (itemstack3.getMaxStackSize() > 1) && ((!itemstack2.usesData()) || (itemstack2.getData() == itemstack3.getData())) && (ItemStack.equals(itemstack2, itemstack3))) {
/* 224 */						 int l = itemstack2.count;
/* 225 */						 if ((l > 0) && (l + itemstack3.count <= itemstack3.getMaxStackSize())) {
/* 226 */							 itemstack3.count += l;
/* 227 */							 itemstack2 = slot1.a(l);
/* 228 */							 if (itemstack2.count == 0) {
/* 229 */								 slot1.set((ItemStack)null);
/*		 */							 }
/*		 */ 
/* 232 */							 slot1.b(playerinventory.getCarried());
/*		 */						 }
/*		 */					 }
/*		 */ 
/* 236 */					 slot1.e();
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 241 */		 return itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 protected void b(int i, int j, boolean flag, EntityHuman entityhuman)
/*		 */	 {
/* 246 */		 clickItem(i, j, flag, entityhuman);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityHuman entityhuman) {
/* 250 */		 PlayerInventory playerinventory = entityhuman.inventory;
/*		 */ 
/* 252 */		 if (playerinventory.getCarried() != null) {
/* 253 */			 entityhuman.drop(playerinventory.getCarried());
/* 254 */			 playerinventory.setCarried((ItemStack)null);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(IInventory iinventory) {
/* 259 */		 b();
/*		 */	 }
/*		 */ 
/*		 */	 public void setItem(int i, ItemStack itemstack) {
/* 263 */		 getSlot(i).set(itemstack);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b(EntityHuman entityhuman) {
/* 267 */		 return !this.f.contains(entityhuman);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityHuman entityhuman, boolean flag) {
/* 271 */		 if (flag)
/* 272 */			 this.f.remove(entityhuman);
/*		 */		 else
/* 274 */			 this.f.add(entityhuman);
/*		 */	 }
/*		 */ 
/*		 */	 public abstract boolean c(EntityHuman paramEntityHuman);
/*		 */ 
/*		 */	 protected boolean a(ItemStack itemstack, int i, int j, boolean flag) {
/* 281 */		 boolean flag1 = false;
/* 282 */		 int k = i;
/*		 */ 
/* 284 */		 if (flag) {
/* 285 */			 k = j - 1;
/*		 */		 }
/*		 */ 
/* 291 */		 if (itemstack.isStackable()) {
/* 292 */			 while ((itemstack.count > 0) && (((!flag) && (k < j)) || ((flag) && (k >= i)))) {
/* 293 */				 Slot slot = (Slot)this.b.get(k);
/* 294 */				 ItemStack itemstack1 = slot.getItem();
/* 295 */				 if ((itemstack1 != null) && (itemstack1.id == itemstack.id) && ((!itemstack.usesData()) || (itemstack.getData() == itemstack1.getData())) && (ItemStack.equals(itemstack, itemstack1))) {
/* 296 */					 int l = itemstack1.count + itemstack.count;
/*		 */ 
/* 298 */					 if (l <= itemstack.getMaxStackSize()) {
/* 299 */						 itemstack.count = 0;
/* 300 */						 itemstack1.count = l;
/* 301 */						 slot.e();
/* 302 */						 flag1 = true;
/* 303 */					 } else if (itemstack1.count < itemstack.getMaxStackSize()) {
/* 304 */						 itemstack.count -= itemstack.getMaxStackSize() - itemstack1.count;
/* 305 */						 itemstack1.count = itemstack.getMaxStackSize();
/* 306 */						 slot.e();
/* 307 */						 flag1 = true;
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 311 */				 if (flag) {
/* 312 */					 k--; continue;
/*		 */				 }
/* 314 */				 k++;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 319 */		 if (itemstack.count > 0) {
/* 320 */			 if (flag)
/* 321 */				 k = j - 1;
/*		 */			 else {
/* 323 */				 k = i;
/*		 */			 }
/*		 */ 
/* 326 */			 while (((!flag) && (k < j)) || ((flag) && (k >= i))) {
/* 327 */				 Slot slot = (Slot)this.b.get(k);
/* 328 */				 ItemStack itemstack1 = slot.getItem();
/* 329 */				 if (itemstack1 == null) {
/* 330 */					 slot.set(itemstack.cloneItemStack());
/* 331 */					 slot.e();
/* 332 */					 itemstack.count = 0;
/* 333 */					 flag1 = true;
/* 334 */					 break;
/*		 */				 }
/*		 */ 
/* 337 */				 if (flag) {
/* 338 */					 k--; continue;
/*		 */				 }
/* 340 */				 k++;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 345 */		 return flag1;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Container
 * JD-Core Version:		0.6.0
 */