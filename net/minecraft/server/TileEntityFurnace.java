/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.entity.CraftHumanEntity;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftItemStack;
/*		 */ import org.bukkit.entity.HumanEntity;
/*		 */ import org.bukkit.event.inventory.FurnaceBurnEvent;
/*		 */ import org.bukkit.event.inventory.FurnaceSmeltEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class TileEntityFurnace extends TileEntity
/*		 */	 implements IInventory
/*		 */ {
/*	15 */	 private ItemStack[] items = new ItemStack[3];
/*	16 */	 public int burnTime = 0;
/*	17 */	 public int ticksForCurrentFuel = 0;
/*	18 */	 public int cookTime = 0;
/*		 */ 
/*	21 */	 private int lastTick = (int)(System.currentTimeMillis() / 50L);
/*	22 */	 private int maxStack = 64;
/*	23 */	 public List<HumanEntity> transaction = new ArrayList();
/*		 */ 
/*		 */	 public ItemStack[] getContents() {
/*	26 */		 return this.items;
/*		 */	 }
/*		 */ 
/*		 */	 public void onOpen(CraftHumanEntity who) {
/*	30 */		 this.transaction.add(who);
/*		 */	 }
/*		 */ 
/*		 */	 public void onClose(CraftHumanEntity who) {
/*	34 */		 this.transaction.remove(who);
/*		 */	 }
/*		 */ 
/*		 */	 public List<HumanEntity> getViewers() {
/*	38 */		 return this.transaction;
/*		 */	 }
/*		 */ 
/*		 */	 public void setMaxStackSize(int size) {
/*	42 */		 this.maxStack = size;
/*		 */	 }
/*		 */ 
/*		 */	 public int getSize()
/*		 */	 {
/*	49 */		 return this.items.length;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack getItem(int i) {
/*	53 */		 return this.items[i];
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitStack(int i, int j) {
/*	57 */		 if (this.items[i] != null)
/*		 */		 {
/*	60 */			 if (this.items[i].count <= j) {
/*	61 */				 ItemStack itemstack = this.items[i];
/*	62 */				 this.items[i] = null;
/*	63 */				 return itemstack;
/*		 */			 }
/*	65 */			 ItemStack itemstack = this.items[i].a(j);
/*	66 */			 if (this.items[i].count == 0) {
/*	67 */				 this.items[i] = null;
/*		 */			 }
/*		 */ 
/*	70 */			 return itemstack;
/*		 */		 }
/*		 */ 
/*	73 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitWithoutUpdate(int i)
/*		 */	 {
/*	78 */		 if (this.items[i] != null) {
/*	79 */			 ItemStack itemstack = this.items[i];
/*		 */ 
/*	81 */			 this.items[i] = null;
/*	82 */			 return itemstack;
/*		 */		 }
/*	84 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void setItem(int i, ItemStack itemstack)
/*		 */	 {
/*	89 */		 this.items[i] = itemstack;
/*	90 */		 if ((itemstack != null) && (itemstack.count > getMaxStackSize()))
/*	91 */			 itemstack.count = getMaxStackSize();
/*		 */	 }
/*		 */ 
/*		 */	 public String getName()
/*		 */	 {
/*	96 */		 return "container.furnace";
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/* 100 */		 super.a(nbttagcompound);
/* 101 */		 NBTTagList nbttaglist = nbttagcompound.getList("Items");
/*		 */ 
/* 103 */		 this.items = new ItemStack[getSize()];
/*		 */ 
/* 105 */		 for (int i = 0; i < nbttaglist.size(); i++) {
/* 106 */			 NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.get(i);
/* 107 */			 byte b0 = nbttagcompound1.getByte("Slot");
/*		 */ 
/* 109 */			 if ((b0 >= 0) && (b0 < this.items.length)) {
/* 110 */				 this.items[b0] = ItemStack.a(nbttagcompound1);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 114 */		 this.burnTime = nbttagcompound.getShort("BurnTime");
/* 115 */		 this.cookTime = nbttagcompound.getShort("CookTime");
/* 116 */		 this.ticksForCurrentFuel = fuelTime(this.items[1]);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/* 120 */		 super.b(nbttagcompound);
/* 121 */		 nbttagcompound.setShort("BurnTime", (short)this.burnTime);
/* 122 */		 nbttagcompound.setShort("CookTime", (short)this.cookTime);
/* 123 */		 NBTTagList nbttaglist = new NBTTagList();
/*		 */ 
/* 125 */		 for (int i = 0; i < this.items.length; i++) {
/* 126 */			 if (this.items[i] != null) {
/* 127 */				 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*		 */ 
/* 129 */				 nbttagcompound1.setByte("Slot", (byte)i);
/* 130 */				 this.items[i].save(nbttagcompound1);
/* 131 */				 nbttaglist.add(nbttagcompound1);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 135 */		 nbttagcompound.set("Items", nbttaglist);
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxStackSize() {
/* 139 */		 return this.maxStack;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isBurning() {
/* 143 */		 return this.burnTime > 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void g() {
/* 147 */		 boolean flag = this.burnTime > 0;
/* 148 */		 boolean flag1 = false;
/*		 */ 
/* 151 */		 int currentTick = (int)(System.currentTimeMillis() / 50L);
/* 152 */		 int elapsedTicks = currentTick - this.lastTick;
/* 153 */		 this.lastTick = currentTick;
/*		 */ 
/* 156 */		 if ((isBurning()) && (canBurn())) {
/* 157 */			 this.cookTime += elapsedTicks;
/* 158 */			 if (this.cookTime >= 200) {
/* 159 */				 this.cookTime %= 200;
/* 160 */				 burn();
/* 161 */				 flag1 = true;
/*		 */			 }
/*		 */		 } else {
/* 164 */			 this.cookTime = 0;
/*		 */		 }
/*		 */ 
/* 168 */		 if (this.burnTime > 0) {
/* 169 */			 this.burnTime -= elapsedTicks;
/*		 */		 }
/*		 */ 
/* 172 */		 if (!this.world.isStatic)
/*		 */		 {
/* 174 */			 if ((this.burnTime <= 0) && (canBurn()) && (this.items[1] != null)) {
/* 175 */				 CraftItemStack fuel = new CraftItemStack(this.items[1]);
/*		 */ 
/* 177 */				 FurnaceBurnEvent furnaceBurnEvent = new FurnaceBurnEvent(this.world.getWorld().getBlockAt(this.x, this.y, this.z), fuel, fuelTime(this.items[1]));
/* 178 */				 this.world.getServer().getPluginManager().callEvent(furnaceBurnEvent);
/*		 */ 
/* 180 */				 if (furnaceBurnEvent.isCancelled()) {
/* 181 */					 return;
/*		 */				 }
/*		 */ 
/* 184 */				 this.ticksForCurrentFuel = furnaceBurnEvent.getBurnTime();
/* 185 */				 this.burnTime += this.ticksForCurrentFuel;
/* 186 */				 if ((this.burnTime > 0) && (furnaceBurnEvent.isBurning()))
/*		 */				 {
/* 188 */					 flag1 = true;
/* 189 */					 if (this.items[1] != null) {
/* 190 */						 this.items[1].count -= 1;
/* 191 */						 if (this.items[1].count == 0) {
/* 192 */							 Item item = this.items[1].getItem().q();
/*		 */ 
/* 194 */							 this.items[1] = (item != null ? new ItemStack(item) : null);
/*		 */						 }
/*		 */ 
/*		 */					 }
/*		 */ 
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 213 */			 if (flag != this.burnTime > 0) {
/* 214 */				 flag1 = true;
/* 215 */				 BlockFurnace.a(this.burnTime > 0, this.world, this.x, this.y, this.z);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 219 */		 if (flag1)
/* 220 */			 update();
/*		 */	 }
/*		 */ 
/*		 */	 private boolean canBurn()
/*		 */	 {
/* 225 */		 if (this.items[0] == null) {
/* 226 */			 return false;
/*		 */		 }
/* 228 */		 ItemStack itemstack = RecipesFurnace.getInstance().getResult(this.items[0].getItem().id);
/*		 */ 
/* 231 */		 return itemstack != null;
/*		 */	 }
/*		 */ 
/*		 */	 public void burn()
/*		 */	 {
/* 236 */		 if (canBurn()) {
/* 237 */			 ItemStack itemstack = RecipesFurnace.getInstance().getResult(this.items[0].getItem().id);
/*		 */ 
/* 240 */			 CraftItemStack source = new CraftItemStack(this.items[0]);
/* 241 */			 CraftItemStack result = new CraftItemStack(itemstack.cloneItemStack());
/*		 */ 
/* 243 */			 FurnaceSmeltEvent furnaceSmeltEvent = new FurnaceSmeltEvent(this.world.getWorld().getBlockAt(this.x, this.y, this.z), source, result);
/* 244 */			 this.world.getServer().getPluginManager().callEvent(furnaceSmeltEvent);
/*		 */ 
/* 246 */			 if (furnaceSmeltEvent.isCancelled()) {
/* 247 */				 return;
/*		 */			 }
/*		 */ 
/* 250 */			 itemstack = CraftItemStack.createNMSItemStack(furnaceSmeltEvent.getResult());
/*		 */ 
/* 252 */			 if (this.items[2] == null)
/* 253 */				 this.items[2] = itemstack.cloneItemStack();
/* 254 */			 else if (this.items[2].id == itemstack.id)
/*		 */			 {
/* 256 */				 if (this.items[2].getData() == itemstack.getData()) {
/* 257 */					 this.items[2].count += itemstack.count;
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 262 */			 this.items[0].count -= 1;
/* 263 */			 if (this.items[0].count <= 0)
/* 264 */				 this.items[0] = null;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public static int fuelTime(ItemStack itemstack)
/*		 */	 {
/* 270 */		 if (itemstack == null) {
/* 271 */			 return 0;
/*		 */		 }
/* 273 */		 int i = itemstack.getItem().id;
/* 274 */		 Item item = itemstack.getItem();
/*		 */ 
/* 276 */		 if ((i < 256) && (Block.byId[i] != null)) {
/* 277 */			 Block block = Block.byId[i];
/*		 */ 
/* 279 */			 if (block == Block.WOOD_STEP) {
/* 280 */				 return 150;
/*		 */			 }
/*		 */ 
/* 283 */			 if (block.material == Material.WOOD) {
/* 284 */				 return 300;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 288 */		 return i == Item.BLAZE_ROD.id ? 2400 : i == Block.SAPLING.id ? 100 : i == Item.LAVA_BUCKET.id ? 20000 : i == Item.COAL.id ? 1600 : i == Item.STICK.id ? 100 : ((item instanceof ItemHoe)) && (((ItemHoe)item).f().equals("WOOD")) ? 200 : ((item instanceof ItemSword)) && (((ItemSword)item).f().equals("WOOD")) ? 200 : ((item instanceof ItemTool)) && (((ItemTool)item).e().equals("WOOD")) ? 200 : 0;
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean isFuel(ItemStack itemstack)
/*		 */	 {
/* 293 */		 return fuelTime(itemstack) > 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityHuman entityhuman) {
/* 297 */		 return this.world.getTileEntity(this.x, this.y, this.z) == this;
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
 * Qualified Name:		 net.minecraft.server.TileEntityFurnace
 * JD-Core Version:		0.6.0
 */