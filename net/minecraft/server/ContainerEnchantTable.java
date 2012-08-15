/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Map.Entry;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftInventoryEnchanting;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftInventoryView;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftItemStack;
/*		 */ import org.bukkit.entity.Player;
/*		 */ import org.bukkit.event.enchantment.EnchantItemEvent;
/*		 */ import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class ContainerEnchantTable extends Container
/*		 */ {
/*	21 */	 public ContainerEnchantTableInventory enchantSlots = new ContainerEnchantTableInventory(this, "Enchant", 1);
/*		 */	 private World world;
/*		 */	 private int x;
/*		 */	 private int y;
/*		 */	 private int z;
/*	26 */	 private Random l = new Random();
/*		 */	 public long f;
/*	28 */	 public int[] costs = new int[3];
/*		 */ 
/*	30 */	 private CraftInventoryView bukkitEntity = null;
/*		 */	 private Player player;
/*		 */ 
/*		 */	 public ContainerEnchantTable(PlayerInventory playerinventory, World world, int i, int j, int k)
/*		 */	 {
/*	35 */		 this.world = world;
/*	36 */		 this.x = i;
/*	37 */		 this.y = j;
/*	38 */		 this.z = k;
/*	39 */		 a(new SlotEnchant(this, this.enchantSlots, 0, 25, 47));
/*		 */ 
/*	43 */		 for (int l = 0; l < 3; l++) {
/*	44 */			 for (int i1 = 0; i1 < 9; i1++) {
/*	45 */				 a(new Slot(playerinventory, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	49 */		 for (l = 0; l < 9; l++) {
/*	50 */			 a(new Slot(playerinventory, l, 8 + l * 18, 142));
/*		 */		 }
/*		 */ 
/*	54 */		 this.player = ((Player)playerinventory.player.getBukkitEntity());
/*	55 */		 this.enchantSlots.player = this.player;
/*		 */	 }
/*		 */ 
/*		 */	 public void addSlotListener(ICrafting icrafting)
/*		 */	 {
/*	60 */		 super.addSlotListener(icrafting);
/*	61 */		 icrafting.setContainerData(this, 0, this.costs[0]);
/*	62 */		 icrafting.setContainerData(this, 1, this.costs[1]);
/*	63 */		 icrafting.setContainerData(this, 2, this.costs[2]);
/*		 */	 }
/*		 */ 
/*		 */	 public void b() {
/*	67 */		 super.b();
/*	68 */		 Iterator iterator = this.listeners.iterator();
/*		 */ 
/*	70 */		 while (iterator.hasNext()) {
/*	71 */			 ICrafting icrafting = (ICrafting)iterator.next();
/*		 */ 
/*	73 */			 icrafting.setContainerData(this, 0, this.costs[0]);
/*	74 */			 icrafting.setContainerData(this, 1, this.costs[1]);
/*	75 */			 icrafting.setContainerData(this, 2, this.costs[2]);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(IInventory iinventory) {
/*	80 */		 if (iinventory == this.enchantSlots) {
/*	81 */			 ItemStack itemstack = iinventory.getItem(0);
/*		 */ 
/*	84 */			 if ((itemstack != null) && (itemstack.u())) {
/*	85 */				 this.f = this.l.nextLong();
/*	86 */				 if (!this.world.isStatic) {
/*	87 */					 int i = 0;
/*		 */ 
/*	91 */					 for (int j = -1; j <= 1; j++) {
/*	92 */						 for (int k = -1; k <= 1; k++) {
/*	93 */							 if (((j != 0) || (k != 0)) && (this.world.isEmpty(this.x + k, this.y, this.z + j)) && (this.world.isEmpty(this.x + k, this.y + 1, this.z + j))) {
/*	94 */								 if (this.world.getTypeId(this.x + k * 2, this.y, this.z + j * 2) == Block.BOOKSHELF.id) {
/*	95 */									 i++;
/*		 */								 }
/*		 */ 
/*	98 */								 if (this.world.getTypeId(this.x + k * 2, this.y + 1, this.z + j * 2) == Block.BOOKSHELF.id) {
/*	99 */									 i++;
/*		 */								 }
/*		 */ 
/* 102 */								 if ((k != 0) && (j != 0)) {
/* 103 */									 if (this.world.getTypeId(this.x + k * 2, this.y, this.z + j) == Block.BOOKSHELF.id) {
/* 104 */										 i++;
/*		 */									 }
/*		 */ 
/* 107 */									 if (this.world.getTypeId(this.x + k * 2, this.y + 1, this.z + j) == Block.BOOKSHELF.id) {
/* 108 */										 i++;
/*		 */									 }
/*		 */ 
/* 111 */									 if (this.world.getTypeId(this.x + k, this.y, this.z + j * 2) == Block.BOOKSHELF.id) {
/* 112 */										 i++;
/*		 */									 }
/*		 */ 
/* 115 */									 if (this.world.getTypeId(this.x + k, this.y + 1, this.z + j * 2) == Block.BOOKSHELF.id) {
/* 116 */										 i++;
/*		 */									 }
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */ 
/* 123 */					 for (j = 0; j < 3; j++) {
/* 124 */						 this.costs[j] = EnchantmentManager.a(this.l, j, i, itemstack);
/*		 */					 }
/*		 */ 
/* 128 */					 CraftItemStack item = new CraftItemStack(itemstack);
/* 129 */					 PrepareItemEnchantEvent event = new PrepareItemEnchantEvent(this.player, getBukkitView(), this.world.getWorld().getBlockAt(this.x, this.y, this.z), item, this.costs, i);
/* 130 */					 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 132 */					 if (event.isCancelled()) {
/* 133 */						 for (i = 0; i < 3; i++) {
/* 134 */							 this.costs[i] = 0;
/*		 */						 }
/* 136 */						 return;
/*		 */					 }
/*		 */ 
/* 140 */					 b();
/*		 */				 }
/*		 */			 } else {
/* 143 */				 for (int i = 0; i < 3; i++)
/* 144 */					 this.costs[i] = 0;
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityHuman entityhuman, int i)
/*		 */	 {
/* 151 */		 ItemStack itemstack = this.enchantSlots.getItem(0);
/*		 */ 
/* 153 */		 if ((this.costs[i] > 0) && (itemstack != null) && ((entityhuman.expLevel >= this.costs[i]) || (entityhuman.abilities.canInstantlyBuild))) {
/* 154 */			 if (!this.world.isStatic) {
/* 155 */				 List list = EnchantmentManager.b(this.l, itemstack, this.costs[i]);
/*		 */ 
/* 157 */				 if (list != null)
/*		 */				 {
/* 159 */					 Map enchants = new HashMap();
/* 160 */					 for (Iterator i$ = list.iterator(); i$.hasNext(); ) { Object obj = i$.next();
/* 161 */						 EnchantmentInstance instance = (EnchantmentInstance)obj;
/* 162 */						 enchants.put(org.bukkit.enchantments.Enchantment.getById(instance.enchantment.id), Integer.valueOf(instance.level));
/*		 */					 }
/* 164 */					 CraftItemStack item = new CraftItemStack(itemstack);
/*		 */ 
/* 166 */					 EnchantItemEvent event = new EnchantItemEvent((Player)entityhuman.getBukkitEntity(), getBukkitView(), this.world.getWorld().getBlockAt(this.x, this.y, this.z), item, this.costs[i], enchants, i);
/* 167 */					 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 169 */					 int level = event.getExpLevelCost();
/* 170 */					 if ((event.isCancelled()) || ((level > entityhuman.expLevel) && (!entityhuman.abilities.canInstantlyBuild)) || (enchants.isEmpty())) {
/* 171 */						 return false;
/*		 */					 }
/*		 */ 
/* 174 */					 entityhuman.levelDown(level);
/* 175 */					 for (Map.Entry entry : event.getEnchantsToAdd().entrySet()) {
/*		 */						 try {
/* 177 */							 item.addEnchantment((org.bukkit.enchantments.Enchantment)entry.getKey(), ((Integer)entry.getValue()).intValue());
/*		 */						 }
/*		 */						 catch (IllegalArgumentException e)
/*		 */						 {
/*		 */						 }
/*		 */					 }
/*		 */ 
/* 184 */					 a(this.enchantSlots);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 188 */			 return true;
/*		 */		 }
/* 190 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityHuman entityhuman)
/*		 */	 {
/* 195 */		 super.a(entityhuman);
/* 196 */		 if (!this.world.isStatic) {
/* 197 */			 ItemStack itemstack = this.enchantSlots.splitWithoutUpdate(0);
/*		 */ 
/* 199 */			 if (itemstack != null)
/* 200 */				 entityhuman.drop(itemstack);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman entityhuman)
/*		 */	 {
/* 206 */		 if (!this.checkReachable) return true;
/* 207 */		 return this.world.getTypeId(this.x, this.y, this.z) == Block.ENCHANTMENT_TABLE.id;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack b(int i) {
/* 211 */		 ItemStack itemstack = null;
/* 212 */		 Slot slot = (Slot)this.b.get(i);
/*		 */ 
/* 214 */		 if ((slot != null) && (slot.d())) {
/* 215 */			 ItemStack itemstack1 = slot.getItem();
/*		 */ 
/* 217 */			 itemstack = itemstack1.cloneItemStack();
/* 218 */			 if (i == 0) {
/* 219 */				 if (!a(itemstack1, 1, 37, true))
/* 220 */					 return null;
/*		 */			 }
/*		 */			 else {
/* 223 */				 if ((((Slot)this.b.get(0)).d()) || (!((Slot)this.b.get(0)).isAllowed(itemstack1))) {
/* 224 */					 return null;
/*		 */				 }
/*		 */ 
/* 227 */				 if ((itemstack1.hasTag()) && (itemstack1.count == 1)) {
/* 228 */					 ((Slot)this.b.get(0)).set(itemstack1.cloneItemStack());
/* 229 */					 itemstack1.count = 0;
/* 230 */				 } else if (itemstack1.count >= 1) {
/* 231 */					 ((Slot)this.b.get(0)).set(new ItemStack(itemstack1.id, 1, itemstack1.getData()));
/* 232 */					 itemstack1.count -= 1;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 236 */			 if (itemstack1.count == 0)
/* 237 */				 slot.set((ItemStack)null);
/*		 */			 else {
/* 239 */				 slot.e();
/*		 */			 }
/*		 */ 
/* 242 */			 if (itemstack1.count == itemstack.count) {
/* 243 */				 return null;
/*		 */			 }
/*		 */ 
/* 246 */			 slot.b(itemstack1);
/*		 */		 }
/*		 */ 
/* 249 */		 return itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 public CraftInventoryView getBukkitView()
/*		 */	 {
/* 254 */		 if (this.bukkitEntity != null) {
/* 255 */			 return this.bukkitEntity;
/*		 */		 }
/*		 */ 
/* 258 */		 CraftInventoryEnchanting inventory = new CraftInventoryEnchanting(this.enchantSlots);
/* 259 */		 this.bukkitEntity = new CraftInventoryView(this.player, inventory, this);
/* 260 */		 return this.bukkitEntity;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ContainerEnchantTable
 * JD-Core Version:		0.6.0
 */