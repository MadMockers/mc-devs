/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.entity.CraftHumanEntity;
/*		 */ import org.bukkit.entity.HumanEntity;
/*		 */ 
/*		 */ public class TileEntityChest extends TileEntity
/*		 */	 implements IInventory
/*		 */ {
/*	12 */	 private ItemStack[] items = new ItemStack[27];
/*	13 */	 public boolean a = false;
/*		 */	 public TileEntityChest b;
/*		 */	 public TileEntityChest c;
/*		 */	 public TileEntityChest d;
/*		 */	 public TileEntityChest e;
/*		 */	 public float f;
/*		 */	 public float g;
/*		 */	 public int h;
/*		 */	 private int ticks;
/*	26 */	 public List<HumanEntity> transaction = new ArrayList();
/*	27 */	 private int maxStack = 64;
/*		 */ 
/*		 */	 public ItemStack[] getContents() {
/*	30 */		 return this.items;
/*		 */	 }
/*		 */ 
/*		 */	 public void onOpen(CraftHumanEntity who) {
/*	34 */		 this.transaction.add(who);
/*		 */	 }
/*		 */ 
/*		 */	 public void onClose(CraftHumanEntity who) {
/*	38 */		 this.transaction.remove(who);
/*		 */	 }
/*		 */ 
/*		 */	 public List<HumanEntity> getViewers() {
/*	42 */		 return this.transaction;
/*		 */	 }
/*		 */ 
/*		 */	 public void setMaxStackSize(int size) {
/*	46 */		 this.maxStack = size;
/*		 */	 }
/*		 */ 
/*		 */	 public int getSize()
/*		 */	 {
/*	51 */		 return 27;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack getItem(int i) {
/*	55 */		 return this.items[i];
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitStack(int i, int j) {
/*	59 */		 if (this.items[i] != null)
/*		 */		 {
/*	62 */			 if (this.items[i].count <= j) {
/*	63 */				 ItemStack itemstack = this.items[i];
/*	64 */				 this.items[i] = null;
/*	65 */				 update();
/*	66 */				 return itemstack;
/*		 */			 }
/*	68 */			 ItemStack itemstack = this.items[i].a(j);
/*	69 */			 if (this.items[i].count == 0) {
/*	70 */				 this.items[i] = null;
/*		 */			 }
/*		 */ 
/*	73 */			 update();
/*	74 */			 return itemstack;
/*		 */		 }
/*		 */ 
/*	77 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitWithoutUpdate(int i)
/*		 */	 {
/*	82 */		 if (this.items[i] != null) {
/*	83 */			 ItemStack itemstack = this.items[i];
/*		 */ 
/*	85 */			 this.items[i] = null;
/*	86 */			 return itemstack;
/*		 */		 }
/*	88 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void setItem(int i, ItemStack itemstack)
/*		 */	 {
/*	93 */		 this.items[i] = itemstack;
/*	94 */		 if ((itemstack != null) && (itemstack.count > getMaxStackSize())) {
/*	95 */			 itemstack.count = getMaxStackSize();
/*		 */		 }
/*		 */ 
/*	98 */		 update();
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/* 102 */		 return "container.chest";
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/* 106 */		 super.a(nbttagcompound);
/* 107 */		 NBTTagList nbttaglist = nbttagcompound.getList("Items");
/*		 */ 
/* 109 */		 this.items = new ItemStack[getSize()];
/*		 */ 
/* 111 */		 for (int i = 0; i < nbttaglist.size(); i++) {
/* 112 */			 NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.get(i);
/* 113 */			 int j = nbttagcompound1.getByte("Slot") & 0xFF;
/*		 */ 
/* 115 */			 if ((j >= 0) && (j < this.items.length))
/* 116 */				 this.items[j] = ItemStack.a(nbttagcompound1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound)
/*		 */	 {
/* 122 */		 super.b(nbttagcompound);
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
/*		 */	 public boolean a(EntityHuman entityhuman) {
/* 143 */		 if (this.world == null) return true;
/* 144 */		 return this.world.getTileEntity(this.x, this.y, this.z) == this;
/*		 */	 }
/*		 */ 
/*		 */	 public void h() {
/* 148 */		 super.h();
/* 149 */		 this.a = false;
/*		 */	 }
/*		 */ 
/*		 */	 public void i() {
/* 153 */		 if (!this.a) {
/* 154 */			 this.a = true;
/* 155 */			 this.b = null;
/* 156 */			 this.c = null;
/* 157 */			 this.d = null;
/* 158 */			 this.e = null;
/* 159 */			 if (this.world.getTypeId(this.x - 1, this.y, this.z) == Block.CHEST.id) {
/* 160 */				 this.d = ((TileEntityChest)this.world.getTileEntity(this.x - 1, this.y, this.z));
/*		 */			 }
/*		 */ 
/* 163 */			 if (this.world.getTypeId(this.x + 1, this.y, this.z) == Block.CHEST.id) {
/* 164 */				 this.c = ((TileEntityChest)this.world.getTileEntity(this.x + 1, this.y, this.z));
/*		 */			 }
/*		 */ 
/* 167 */			 if (this.world.getTypeId(this.x, this.y, this.z - 1) == Block.CHEST.id) {
/* 168 */				 this.b = ((TileEntityChest)this.world.getTileEntity(this.x, this.y, this.z - 1));
/*		 */			 }
/*		 */ 
/* 171 */			 if (this.world.getTypeId(this.x, this.y, this.z + 1) == Block.CHEST.id) {
/* 172 */				 this.e = ((TileEntityChest)this.world.getTileEntity(this.x, this.y, this.z + 1));
/*		 */			 }
/*		 */ 
/* 175 */			 if (this.b != null) {
/* 176 */				 this.b.h();
/*		 */			 }
/*		 */ 
/* 179 */			 if (this.e != null) {
/* 180 */				 this.e.h();
/*		 */			 }
/*		 */ 
/* 183 */			 if (this.c != null) {
/* 184 */				 this.c.h();
/*		 */			 }
/*		 */ 
/* 187 */			 if (this.d != null)
/* 188 */				 this.d.h();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void g()
/*		 */	 {
/* 194 */		 super.g();
/* 195 */		 if (this.world == null) return;
/* 196 */		 i();
/* 197 */		 if (++this.ticks % 80 == 0);
/* 201 */		 this.g = this.f;
/* 202 */		 float f = 0.1F;
/*		 */ 
/* 205 */		 if ((this.h > 0) && (this.f == 0.0F) && (this.b == null) && (this.d == null)) {
/* 206 */			 double d1 = this.x + 0.5D;
/*		 */ 
/* 208 */			 double d0 = this.z + 0.5D;
/* 209 */			 if (this.e != null) {
/* 210 */				 d0 += 0.5D;
/*		 */			 }
/*		 */ 
/* 213 */			 if (this.c != null) {
/* 214 */				 d1 += 0.5D;
/*		 */			 }
/*		 */ 
/* 217 */			 this.world.makeSound(d1, this.y + 0.5D, d0, "random.chestopen", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
/*		 */		 }
/*		 */ 
/* 220 */		 if (((this.h == 0) && (this.f > 0.0F)) || ((this.h > 0) && (this.f < 1.0F))) {
/* 221 */			 float f1 = this.f;
/*		 */ 
/* 223 */			 if (this.h > 0)
/* 224 */				 this.f += f;
/*		 */			 else {
/* 226 */				 this.f -= f;
/*		 */			 }
/*		 */ 
/* 229 */			 if (this.f > 1.0F) {
/* 230 */				 this.f = 1.0F;
/*		 */			 }
/*		 */ 
/* 233 */			 float f2 = 0.5F;
/*		 */ 
/* 235 */			 if ((this.f < f2) && (f1 >= f2) && (this.b == null) && (this.d == null)) {
/* 236 */				 double d0 = this.x + 0.5D;
/* 237 */				 double d2 = this.z + 0.5D;
/*		 */ 
/* 239 */				 if (this.e != null) {
/* 240 */					 d2 += 0.5D;
/*		 */				 }
/*		 */ 
/* 243 */				 if (this.c != null) {
/* 244 */					 d0 += 0.5D;
/*		 */				 }
/*		 */ 
/* 247 */				 this.world.makeSound(d0, this.y + 0.5D, d2, "random.chestclosed", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
/*		 */			 }
/*		 */ 
/* 250 */			 if (this.f < 0.0F)
/* 251 */				 this.f = 0.0F;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(int i, int j)
/*		 */	 {
/* 257 */		 if (i == 1)
/* 258 */			 this.h = j;
/*		 */	 }
/*		 */ 
/*		 */	 public void startOpen()
/*		 */	 {
/* 263 */		 this.h += 1;
/* 264 */		 if (this.world == null) return;
/* 265 */		 this.world.playNote(this.x, this.y, this.z, Block.CHEST.id, 1, this.h);
/*		 */	 }
/*		 */ 
/*		 */	 public void f() {
/* 269 */		 this.h -= 1;
/* 270 */		 if (this.world == null) return;
/* 271 */		 this.world.playNote(this.x, this.y, this.z, Block.CHEST.id, 1, this.h);
/*		 */	 }
/*		 */ 
/*		 */	 public void j() {
/* 275 */		 h();
/* 276 */		 i();
/* 277 */		 super.j();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.TileEntityChest
 * JD-Core Version:		0.6.0
 */