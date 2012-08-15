/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.PrintStream;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Map;
/*		 */ import org.bukkit.block.BlockState;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.inventory.InventoryHolder;
/*		 */ 
/*		 */ public class TileEntity
/*		 */ {
/*	10 */	 private static Map a = new HashMap();
/*	11 */	 private static Map b = new HashMap();
/*		 */	 protected World world;
/*		 */	 public int x;
/*		 */	 public int y;
/*		 */	 public int z;
/*		 */	 protected boolean o;
/*	17 */	 public int p = -1;
/*		 */	 public Block q;
/*		 */ 
/*		 */	 private static void a(Class oclass, String s)
/*		 */	 {
/*	23 */		 if (a.containsKey(s)) {
/*	24 */			 throw new IllegalArgumentException("Duplicate id: " + s);
/*		 */		 }
/*	26 */		 a.put(s, oclass);
/*	27 */		 b.put(oclass, s);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world)
/*		 */	 {
/*	32 */		 this.world = world;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean m() {
/*	36 */		 return this.world != null;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/*	40 */		 this.x = nbttagcompound.getInt("x");
/*	41 */		 this.y = nbttagcompound.getInt("y");
/*	42 */		 this.z = nbttagcompound.getInt("z");
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/*	46 */		 String s = (String)b.get(getClass());
/*		 */ 
/*	48 */		 if (s == null) {
/*	49 */			 throw new RuntimeException(getClass() + " is missing a mapping! This is a bug!");
/*		 */		 }
/*	51 */		 nbttagcompound.setString("id", s);
/*	52 */		 nbttagcompound.setInt("x", this.x);
/*	53 */		 nbttagcompound.setInt("y", this.y);
/*	54 */		 nbttagcompound.setInt("z", this.z);
/*		 */	 }
/*		 */ 
/*		 */	 public void g() {
/*		 */	 }
/*		 */ 
/*		 */	 public static TileEntity c(NBTTagCompound nbttagcompound) {
/*	61 */		 TileEntity tileentity = null;
/*		 */		 try
/*		 */		 {
/*	64 */			 Class oclass = (Class)a.get(nbttagcompound.getString("id"));
/*		 */ 
/*	66 */			 if (oclass != null)
/*	67 */				 tileentity = (TileEntity)oclass.newInstance();
/*		 */		 }
/*		 */		 catch (Exception exception) {
/*	70 */			 exception.printStackTrace();
/*		 */		 }
/*		 */ 
/*	73 */		 if (tileentity != null)
/*	74 */			 tileentity.a(nbttagcompound);
/*		 */		 else {
/*	76 */			 System.out.println("Skipping TileEntity with id " + nbttagcompound.getString("id"));
/*		 */		 }
/*		 */ 
/*	79 */		 return tileentity;
/*		 */	 }
/*		 */ 
/*		 */	 public int n() {
/*	83 */		 if (this.p == -1) {
/*	84 */			 this.p = this.world.getData(this.x, this.y, this.z);
/*		 */		 }
/*		 */ 
/*	87 */		 return this.p;
/*		 */	 }
/*		 */ 
/*		 */	 public void update() {
/*	91 */		 if (this.world != null) {
/*	92 */			 this.p = this.world.getData(this.x, this.y, this.z);
/*	93 */			 this.world.b(this.x, this.y, this.z, this);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public Packet e() {
/*	98 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean p() {
/* 102 */		 return this.o;
/*		 */	 }
/*		 */ 
/*		 */	 public void j() {
/* 106 */		 this.o = true;
/*		 */	 }
/*		 */ 
/*		 */	 public void q() {
/* 110 */		 this.o = false;
/*		 */	 }
/*		 */	 public void b(int i, int j) {
/*		 */	 }
/*		 */ 
/*		 */	 public void h() {
/* 116 */		 this.q = null;
/* 117 */		 this.p = -1;
/*		 */	 }
/*		 */ 
/*		 */	 public InventoryHolder getOwner()
/*		 */	 {
/* 137 */		 BlockState state = this.world.getWorld().getBlockAt(this.x, this.y, this.z).getState();
/* 138 */		 if ((state instanceof InventoryHolder)) return (InventoryHolder)state;
/* 139 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 static
/*		 */	 {
/* 121 */		 a(TileEntityFurnace.class, "Furnace");
/* 122 */		 a(TileEntityChest.class, "Chest");
/* 123 */		 a(TileEntityEnderChest.class, "EnderChest");
/* 124 */		 a(TileEntityRecordPlayer.class, "RecordPlayer");
/* 125 */		 a(TileEntityDispenser.class, "Trap");
/* 126 */		 a(TileEntitySign.class, "Sign");
/* 127 */		 a(TileEntityMobSpawner.class, "MobSpawner");
/* 128 */		 a(TileEntityNote.class, "Music");
/* 129 */		 a(TileEntityPiston.class, "Piston");
/* 130 */		 a(TileEntityBrewingStand.class, "Cauldron");
/* 131 */		 a(TileEntityEnchantTable.class, "EnchantTable");
/* 132 */		 a(TileEntityEnderPortal.class, "Airportal");
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.TileEntity
 * JD-Core Version:		0.6.0
 */