/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.UUID;
/*		 */ import org.bukkit.Bukkit;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.map.CraftMapView;
/*		 */ 
/*		 */ public class WorldMap extends WorldMapBase
/*		 */ {
/*		 */	 public int centerX;
/*		 */	 public int centerZ;
/*		 */	 public byte map;
/*		 */	 public byte scale;
/*	22 */	 public byte[] colors = new byte[16384];
/*		 */	 public int f;
/*	24 */	 public List g = new ArrayList();
/*	25 */	 private Map j = new HashMap();
/*	26 */	 public List decorations = new ArrayList();
/*		 */	 public final CraftMapView mapView;
/*		 */	 private CraftServer server;
/*	31 */	 private UUID uniqueId = null;
/*		 */ 
/*		 */	 public WorldMap(String s)
/*		 */	 {
/*	35 */		 super(s);
/*		 */ 
/*	37 */		 this.mapView = new CraftMapView(this);
/*	38 */		 this.server = ((CraftServer)Bukkit.getServer());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound)
/*		 */	 {
/*	44 */		 byte dimension = nbttagcompound.getByte("dimension");
/*		 */ 
/*	46 */		 if (dimension >= 10) {
/*	47 */			 long least = nbttagcompound.getLong("UUIDLeast");
/*	48 */			 long most = nbttagcompound.getLong("UUIDMost");
/*		 */ 
/*	50 */			 if ((least != 0L) && (most != 0L)) {
/*	51 */				 this.uniqueId = new UUID(most, least);
/*		 */ 
/*	53 */				 CraftWorld world = (CraftWorld)this.server.getWorld(this.uniqueId);
/*		 */ 
/*	55 */				 if (world == null)
/*		 */				 {
/*	58 */					 dimension = 127;
/*		 */				 }
/*	60 */				 else dimension = (byte)world.getHandle().dimension;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/*	65 */		 this.map = dimension;
/*		 */ 
/*	67 */		 this.centerX = nbttagcompound.getInt("xCenter");
/*	68 */		 this.centerZ = nbttagcompound.getInt("zCenter");
/*	69 */		 this.scale = nbttagcompound.getByte("scale");
/*	70 */		 if (this.scale < 0) {
/*	71 */			 this.scale = 0;
/*		 */		 }
/*		 */ 
/*	74 */		 if (this.scale > 4) {
/*	75 */			 this.scale = 4;
/*		 */		 }
/*		 */ 
/*	78 */		 short short1 = nbttagcompound.getShort("width");
/*	79 */		 short short2 = nbttagcompound.getShort("height");
/*		 */ 
/*	81 */		 if ((short1 == 128) && (short2 == 128)) {
/*	82 */			 this.colors = nbttagcompound.getByteArray("colors");
/*		 */		 } else {
/*	84 */			 byte[] abyte = nbttagcompound.getByteArray("colors");
/*		 */ 
/*	86 */			 this.colors = new byte[16384];
/*	87 */			 int i = (128 - short1) / 2;
/*	88 */			 int j = (128 - short2) / 2;
/*		 */ 
/*	90 */			 for (int k = 0; k < short2; k++) {
/*	91 */				 int l = k + j;
/*		 */ 
/*	93 */				 if ((l >= 0) || (l < 128))
/*	94 */					 for (int i1 = 0; i1 < short1; i1++) {
/*	95 */						 int j1 = i1 + i;
/*		 */ 
/*	97 */						 if ((j1 >= 0) || (j1 < 128))
/*	98 */							 this.colors[(j1 + l * 128)] = abyte[(i1 + k * short1)];
/*		 */					 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound)
/*		 */	 {
/* 108 */		 if (this.map >= 10) {
/* 109 */			 if (this.uniqueId == null) {
/* 110 */				 for (org.bukkit.World world : this.server.getWorlds()) {
/* 111 */					 CraftWorld cWorld = (CraftWorld)world;
/* 112 */					 if (cWorld.getHandle().dimension == this.map) {
/* 113 */						 this.uniqueId = cWorld.getUID();
/* 114 */						 break;
/*		 */					 }
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 120 */			 if (this.uniqueId != null) {
/* 121 */				 nbttagcompound.setLong("UUIDLeast", this.uniqueId.getLeastSignificantBits());
/* 122 */				 nbttagcompound.setLong("UUIDMost", this.uniqueId.getMostSignificantBits());
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 126 */		 nbttagcompound.setByte("dimension", this.map);
/* 127 */		 nbttagcompound.setInt("xCenter", this.centerX);
/* 128 */		 nbttagcompound.setInt("zCenter", this.centerZ);
/* 129 */		 nbttagcompound.setByte("scale", this.scale);
/* 130 */		 nbttagcompound.setShort("width", 128);
/* 131 */		 nbttagcompound.setShort("height", 128);
/* 132 */		 nbttagcompound.setByteArray("colors", this.colors);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityHuman entityhuman, ItemStack itemstack) {
/* 136 */		 if (!this.j.containsKey(entityhuman)) {
/* 137 */			 WorldMapHumanTracker worldmaphumantracker = new WorldMapHumanTracker(this, entityhuman);
/*		 */ 
/* 139 */			 this.j.put(entityhuman, worldmaphumantracker);
/* 140 */			 this.g.add(worldmaphumantracker);
/*		 */		 }
/*		 */ 
/* 143 */		 this.decorations.clear();
/*		 */ 
/* 145 */		 for (int i = 0; i < this.g.size(); i++) {
/* 146 */			 WorldMapHumanTracker worldmaphumantracker1 = (WorldMapHumanTracker)this.g.get(i);
/*		 */ 
/* 148 */			 if ((!worldmaphumantracker1.trackee.dead) && (worldmaphumantracker1.trackee.inventory.c(itemstack))) {
/* 149 */				 float f = (float)(worldmaphumantracker1.trackee.locX - this.centerX) / (1 << this.scale);
/* 150 */				 float f1 = (float)(worldmaphumantracker1.trackee.locZ - this.centerZ) / (1 << this.scale);
/* 151 */				 byte b0 = 64;
/* 152 */				 byte b1 = 64;
/*		 */ 
/* 154 */				 if ((f >= -b0) && (f1 >= -b1) && (f <= b0) && (f1 <= b1)) {
/* 155 */					 byte b2 = 0;
/* 156 */					 byte b3 = (byte)(int)(f * 2.0F + 0.5D);
/* 157 */					 byte b4 = (byte)(int)(f1 * 2.0F + 0.5D);
/* 158 */					 byte b5 = (byte)(int)(worldmaphumantracker1.trackee.yaw * 16.0D / 360.0D);
/*		 */ 
/* 160 */					 if (this.map < 0) {
/* 161 */						 int j = this.f / 10;
/*		 */ 
/* 163 */						 b5 = (byte)(j * j * 34187121 + j * 121 >> 15 & 0xF);
/*		 */					 }
/*		 */ 
/* 166 */					 if (worldmaphumantracker1.trackee.dimension == this.map)
/* 167 */						 this.decorations.add(new WorldMapDecoration(this, b2, b3, b4, b5));
/*		 */				 }
/*		 */			 }
/*		 */			 else {
/* 171 */				 this.j.remove(worldmaphumantracker1.trackee);
/* 172 */				 this.g.remove(worldmaphumantracker1);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public byte[] getUpdatePacket(ItemStack itemstack, World world, EntityHuman entityhuman) {
/* 178 */		 WorldMapHumanTracker worldmaphumantracker = (WorldMapHumanTracker)this.j.get(entityhuman);
/*		 */ 
/* 180 */		 return worldmaphumantracker == null ? null : worldmaphumantracker.a(itemstack);
/*		 */	 }
/*		 */ 
/*		 */	 public void flagDirty(int i, int j, int k) {
/* 184 */		 super.a();
/*		 */ 
/* 186 */		 for (int l = 0; l < this.g.size(); l++) {
/* 187 */			 WorldMapHumanTracker worldmaphumantracker = (WorldMapHumanTracker)this.g.get(l);
/*		 */ 
/* 189 */			 if ((worldmaphumantracker.b[i] < 0) || (worldmaphumantracker.b[i] > j)) {
/* 190 */				 worldmaphumantracker.b[i] = j;
/*		 */			 }
/*		 */ 
/* 193 */			 if ((worldmaphumantracker.c[i] < 0) || (worldmaphumantracker.c[i] < k))
/* 194 */				 worldmaphumantracker.c[i] = k;
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldMap
 * JD-Core Version:		0.6.0
 */