/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ 
/*		 */ public final class ItemStack
/*		 */ {
/*		 */	 public int count;
/*		 */	 public int b;
/*		 */	 public int id;
/*		 */	 public NBTTagCompound tag;
/*		 */	 private int damage;
/*		 */ 
/*		 */	 public ItemStack(Block block)
/*		 */	 {
/*	12 */		 this(block, 1);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack(Block block, int i) {
/*	16 */		 this(block.id, i, 0);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack(Block block, int i, int j) {
/*	20 */		 this(block.id, i, j);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack(Item item) {
/*	24 */		 this(item.id, 1, 0);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack(Item item, int i) {
/*	28 */		 this(item.id, i, 0);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack(Item item, int i, int j) {
/*	32 */		 this(item.id, i, j);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack(int i, int j, int k) {
/*	36 */		 this.count = 0;
/*	37 */		 this.id = i;
/*	38 */		 this.count = j;
/*	39 */		 setData(k);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack(int id, int count, int data, NBTTagList enchantments)
/*		 */	 {
/*	44 */		 this(id, count, data);
/*		 */ 
/*	46 */		 if ((enchantments != null) && (Item.byId[this.id].getMaxStackSize() == 1)) {
/*	47 */			 if (this.tag == null) {
/*	48 */				 setTag(new NBTTagCompound());
/*		 */			 }
/*		 */ 
/*	51 */			 this.tag.set("ench", enchantments.clone());
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public static ItemStack a(NBTTagCompound nbttagcompound)
/*		 */	 {
/*	58 */		 ItemStack itemstack = new ItemStack();
/*		 */ 
/*	60 */		 itemstack.c(nbttagcompound);
/*	61 */		 return itemstack.getItem() != null ? itemstack : null;
/*		 */	 }
/*		 */ 
/*		 */	 private ItemStack() {
/*	65 */		 this.count = 0;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack a(int i) {
/*	69 */		 ItemStack itemstack = new ItemStack(this.id, i, this.damage);
/*		 */ 
/*	71 */		 if (this.tag != null) {
/*	72 */			 itemstack.tag = ((NBTTagCompound)this.tag.clone());
/*		 */		 }
/*		 */ 
/*	75 */		 this.count -= i;
/*	76 */		 return itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 public Item getItem() {
/*	80 */		 return Item.byId[this.id];
/*		 */	 }
/*		 */ 
/*		 */	 public boolean placeItem(EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
/*	84 */		 boolean flag = getItem().interactWith(this, entityhuman, world, i, j, k, l, f, f1, f2);
/*		 */ 
/*	86 */		 if (flag) {
/*	87 */			 entityhuman.a(StatisticList.E[this.id], 1);
/*		 */		 }
/*		 */ 
/*	90 */		 return flag;
/*		 */	 }
/*		 */ 
/*		 */	 public float a(Block block) {
/*	94 */		 return getItem().getDestroySpeed(this, block);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack a(World world, EntityHuman entityhuman) {
/*	98 */		 return getItem().a(this, world, entityhuman);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack b(World world, EntityHuman entityhuman) {
/* 102 */		 return getItem().b(this, world, entityhuman);
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 106 */		 nbttagcompound.setShort("id", (short)this.id);
/* 107 */		 nbttagcompound.setByte("Count", (byte)this.count);
/* 108 */		 nbttagcompound.setShort("Damage", (short)this.damage);
/* 109 */		 if (this.tag != null) {
/* 110 */			 nbttagcompound.set("tag", this.tag);
/*		 */		 }
/*		 */ 
/* 113 */		 return nbttagcompound;
/*		 */	 }
/*		 */ 
/*		 */	 public void c(NBTTagCompound nbttagcompound) {
/* 117 */		 this.id = nbttagcompound.getShort("id");
/* 118 */		 this.count = nbttagcompound.getByte("Count");
/* 119 */		 this.damage = nbttagcompound.getShort("Damage");
/* 120 */		 if (nbttagcompound.hasKey("tag"))
/* 121 */			 this.tag = nbttagcompound.getCompound("tag");
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxStackSize()
/*		 */	 {
/* 126 */		 return getItem().getMaxStackSize();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isStackable() {
/* 130 */		 return (getMaxStackSize() > 1) && ((!f()) || (!h()));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean f() {
/* 134 */		 return Item.byId[this.id].getMaxDurability() > 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean usesData() {
/* 138 */		 return Item.byId[this.id].k();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean h() {
/* 142 */		 return (f()) && (this.damage > 0);
/*		 */	 }
/*		 */ 
/*		 */	 public int i() {
/* 146 */		 return this.damage;
/*		 */	 }
/*		 */ 
/*		 */	 public int getData() {
/* 150 */		 return this.damage;
/*		 */	 }
/*		 */ 
/*		 */	 public void setData(int i) {
/* 154 */		 this.damage = ((this.id > 0) && (this.id < 256) ? Item.byId[this.id].filterData(i) : i);
/*		 */	 }
/*		 */ 
/*		 */	 public int k() {
/* 158 */		 return Item.byId[this.id].getMaxDurability();
/*		 */	 }
/*		 */ 
/*		 */	 public void damage(int i, EntityLiving entityliving) {
/* 162 */		 if (f()) {
/* 163 */			 if ((i > 0) && ((entityliving instanceof EntityHuman))) {
/* 164 */				 int j = EnchantmentManager.getDurabilityEnchantmentLevel(((EntityHuman)entityliving).inventory);
/*		 */ 
/* 166 */				 if ((j > 0) && (entityliving.world.random.nextInt(j + 1) > 0)) {
/* 167 */					 return;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 171 */			 if ((!(entityliving instanceof EntityHuman)) || (!((EntityHuman)entityliving).abilities.canInstantlyBuild)) {
/* 172 */				 this.damage += i;
/*		 */			 }
/*		 */ 
/* 175 */			 if (this.damage > k()) {
/* 176 */				 entityliving.a(this);
/* 177 */				 if ((entityliving instanceof EntityHuman)) {
/* 178 */					 ((EntityHuman)entityliving).a(StatisticList.F[this.id], 1);
/*		 */				 }
/*		 */ 
/* 181 */				 this.count -= 1;
/* 182 */				 if (this.count < 0) {
/* 183 */					 this.count = 0;
/*		 */				 }
/*		 */ 
/* 187 */				 if ((this.count == 0) && ((entityliving instanceof EntityHuman))) {
/* 188 */					 CraftEventFactory.callPlayerItemBreakEvent((EntityHuman)entityliving, this);
/*		 */				 }
/*		 */ 
/* 192 */				 this.damage = 0;
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityLiving entityliving, EntityHuman entityhuman) {
/* 198 */		 boolean flag = Item.byId[this.id].a(this, entityliving, entityhuman);
/*		 */ 
/* 200 */		 if (flag)
/* 201 */			 entityhuman.a(StatisticList.E[this.id], 1);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, int i, int j, int k, int l, EntityHuman entityhuman)
/*		 */	 {
/* 206 */		 boolean flag = Item.byId[this.id].a(this, world, i, j, k, l, entityhuman);
/*		 */ 
/* 208 */		 if (flag)
/* 209 */			 entityhuman.a(StatisticList.E[this.id], 1);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Entity entity)
/*		 */	 {
/* 214 */		 return Item.byId[this.id].a(entity);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b(Block block) {
/* 218 */		 return Item.byId[this.id].canDestroySpecialBlock(block);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityLiving entityliving) {
/* 222 */		 return Item.byId[this.id].a(this, entityliving);
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack cloneItemStack() {
/* 226 */		 ItemStack itemstack = new ItemStack(this.id, this.count, this.damage);
/*		 */ 
/* 228 */		 if (this.tag != null) {
/* 229 */			 itemstack.tag = ((NBTTagCompound)this.tag.clone());
/*		 */		 }
/*		 */ 
/* 232 */		 return itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean equals(ItemStack itemstack, ItemStack itemstack1) {
/* 236 */		 return (itemstack == null) && (itemstack1 == null);
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean matches(ItemStack itemstack, ItemStack itemstack1) {
/* 240 */		 return (itemstack == null) && (itemstack1 == null);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean d(ItemStack itemstack) {
/* 244 */		 return this.count == itemstack.count;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean doMaterialsMatch(ItemStack itemstack) {
/* 248 */		 return (this.id == itemstack.id) && (this.damage == itemstack.damage);
/*		 */	 }
/*		 */ 
/*		 */	 public String a() {
/* 252 */		 return Item.byId[this.id].c(this);
/*		 */	 }
/*		 */ 
/*		 */	 public static ItemStack b(ItemStack itemstack) {
/* 256 */		 return itemstack == null ? null : itemstack.cloneItemStack();
/*		 */	 }
/*		 */ 
/*		 */	 public String toString() {
/* 260 */		 return this.count + "x" + Item.byId[this.id].getName() + "@" + this.damage;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, Entity entity, int i, boolean flag) {
/* 264 */		 if (this.b > 0) {
/* 265 */			 this.b -= 1;
/*		 */		 }
/*		 */ 
/* 268 */		 Item.byId[this.id].a(this, world, entity, i, flag);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, EntityHuman entityhuman, int i) {
/* 272 */		 entityhuman.a(StatisticList.D[this.id], i);
/* 273 */		 Item.byId[this.id].d(this, world, entityhuman);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(ItemStack itemstack) {
/* 277 */		 return (this.id == itemstack.id) && (this.count == itemstack.count) && (this.damage == itemstack.damage);
/*		 */	 }
/*		 */ 
/*		 */	 public int m() {
/* 281 */		 return getItem().a(this);
/*		 */	 }
/*		 */ 
/*		 */	 public EnumAnimation n() {
/* 285 */		 return getItem().b(this);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, EntityHuman entityhuman, int i) {
/* 289 */		 getItem().a(this, world, entityhuman, i);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean hasTag() {
/* 293 */		 return this.tag != null;
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagCompound getTag() {
/* 297 */		 return this.tag;
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagList getEnchantments() {
/* 301 */		 return this.tag == null ? null : (NBTTagList)this.tag.get("ench");
/*		 */	 }
/*		 */ 
/*		 */	 public void setTag(NBTTagCompound nbttagcompound) {
/* 305 */		 this.tag = nbttagcompound;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean u() {
/* 309 */		 return getItem().k(this);
/*		 */	 }
/*		 */ 
/*		 */	 public void addEnchantment(Enchantment enchantment, int i) {
/* 313 */		 if (this.tag == null) {
/* 314 */			 setTag(new NBTTagCompound());
/*		 */		 }
/*		 */ 
/* 317 */		 if (!this.tag.hasKey("ench")) {
/* 318 */			 this.tag.set("ench", new NBTTagList("ench"));
/*		 */		 }
/*		 */ 
/* 321 */		 NBTTagList nbttaglist = (NBTTagList)this.tag.get("ench");
/* 322 */		 NBTTagCompound nbttagcompound = new NBTTagCompound();
/*		 */ 
/* 324 */		 nbttagcompound.setShort("id", (short)enchantment.id);
/* 325 */		 nbttagcompound.setShort("lvl", (short)(byte)i);
/* 326 */		 nbttaglist.add(nbttagcompound);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean hasEnchantments() {
/* 330 */		 return (this.tag != null) && (this.tag.hasKey("ench"));
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemStack
 * JD-Core Version:		0.6.0
 */