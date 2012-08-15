/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import org.bukkit.Bukkit;
/*		 */ import org.bukkit.Server;
/*		 */ import org.bukkit.event.server.MapInitializeEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class ItemWorldMap extends ItemWorldMapBase
/*		 */ {
/*		 */	 protected ItemWorldMap(int i)
/*		 */	 {
/*	11 */		 super(i);
/*	12 */		 d(1);
/*	13 */		 a(CreativeModeTab.f);
/*		 */	 }
/*		 */ 
/*		 */	 public WorldMap getSavedMap(ItemStack itemstack, World world)
/*		 */	 {
/*	18 */		 WorldMap worldmap = (WorldMap)world.a(WorldMap.class, "map_" + itemstack.getData());
/*		 */ 
/*	20 */		 if (worldmap == null) {
/*	21 */			 itemstack.setData(world.b("map"));
/*	22 */			 String s = "map_" + itemstack.getData();
/*		 */ 
/*	24 */			 worldmap = new WorldMap(s);
/*	25 */			 worldmap.centerX = world.getWorldData().c();
/*	26 */			 worldmap.centerZ = world.getWorldData().e();
/*	27 */			 worldmap.scale = 3;
/*	28 */			 worldmap.map = (byte)((WorldServer)world).dimension;
/*	29 */			 worldmap.a();
/*	30 */			 world.a(s, worldmap);
/*		 */ 
/*	33 */			 MapInitializeEvent event = new MapInitializeEvent(worldmap.mapView);
/*	34 */			 Bukkit.getServer().getPluginManager().callEvent(event);
/*		 */		 }
/*		 */ 
/*	38 */		 return worldmap;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, Entity entity, WorldMap worldmap) {
/*	42 */		 if (((WorldServer)world).dimension == worldmap.map) {
/*	43 */			 short short1 = 128;
/*	44 */			 short short2 = 128;
/*	45 */			 int i = 1 << worldmap.scale;
/*	46 */			 int j = worldmap.centerX;
/*	47 */			 int k = worldmap.centerZ;
/*	48 */			 int l = MathHelper.floor(entity.locX - j) / i + short1 / 2;
/*	49 */			 int i1 = MathHelper.floor(entity.locZ - k) / i + short2 / 2;
/*	50 */			 int j1 = 128 / i;
/*		 */ 
/*	52 */			 if (world.worldProvider.e) {
/*	53 */				 j1 /= 2;
/*		 */			 }
/*		 */ 
/*	56 */			 worldmap.f += 1;
/*		 */ 
/*	58 */			 for (int k1 = l - j1 + 1; k1 < l + j1; k1++)
/*	59 */				 if ((k1 & 0xF) == (worldmap.f & 0xF)) {
/*	60 */					 int l1 = 255;
/*	61 */					 int i2 = 0;
/*	62 */					 double d0 = 0.0D;
/*		 */ 
/*	64 */					 for (int j2 = i1 - j1 - 1; j2 < i1 + j1; j2++) {
/*	65 */						 if ((k1 >= 0) && (j2 >= -1) && (k1 < short1) && (j2 < short2)) {
/*	66 */							 int k2 = k1 - l;
/*	67 */							 int l2 = j2 - i1;
/*	68 */							 boolean flag = k2 * k2 + l2 * l2 > (j1 - 2) * (j1 - 2);
/*	69 */							 int i3 = (j / i + k1 - short1 / 2) * i;
/*	70 */							 int j3 = (k / i + j2 - short2 / 2) * i;
/*	71 */							 byte b0 = 0;
/*	72 */							 byte b1 = 0;
/*	73 */							 byte b2 = 0;
/*	74 */							 int[] aint = new int[256];
/*	75 */							 Chunk chunk = world.getChunkAtWorldCoords(i3, j3);
/*		 */ 
/*	77 */							 if (!chunk.isEmpty()) {
/*	78 */								 int k3 = i3 & 0xF;
/*	79 */								 int l3 = j3 & 0xF;
/*	80 */								 int i4 = 0;
/*	81 */								 double d1 = 0.0D;
/*		 */ 
/*	87 */								 if (world.worldProvider.e) {
/*	88 */									 int l4 = i3 + j3 * 231871;
/*	89 */									 l4 = l4 * l4 * 31287121 + l4 * 11;
/*	90 */									 if ((l4 >> 20 & 0x1) == 0)
/*	91 */										 aint[Block.DIRT.id] += 10;
/*		 */									 else {
/*	93 */										 aint[Block.STONE.id] += 10;
/*		 */									 }
/*		 */ 
/*	96 */									 d1 = 100.0D;
/*		 */								 } else {
/*	98 */									 for (l4 = 0; l4 < i; l4++) {
/*	99 */										 for (int j4 = 0; j4 < i; j4++) {
/* 100 */											 int k4 = chunk.b(l4 + k3, j4 + l3) + 1;
/* 101 */											 int j5 = 0;
/*		 */ 
/* 103 */											 if (k4 > 1) {
/* 104 */												 boolean flag1 = false;
/*		 */												 do
/*		 */												 {
/* 107 */													 flag1 = true;
/* 108 */													 j5 = chunk.getTypeId(l4 + k3, k4 - 1, j4 + l3);
/* 109 */													 if (j5 == 0)
/* 110 */														 flag1 = false;
/* 111 */													 else if ((k4 > 0) && (j5 > 0) && (Block.byId[j5].material.F == MaterialMapColor.b)) {
/* 112 */														 flag1 = false;
/*		 */													 }
/*		 */ 
/* 115 */													 if (!flag1) {
/* 116 */														 k4--;
/* 117 */														 if (k4 <= 0)
/*		 */														 {
/*		 */															 break;
/*		 */														 }
/* 121 */														 j5 = chunk.getTypeId(l4 + k3, k4 - 1, j4 + l3);
/*		 */													 }
/*		 */												 }
/* 123 */												 while ((k4 > 0) && (!flag1));
/*		 */ 
/* 125 */												 if ((k4 > 0) && (j5 != 0) && (Block.byId[j5].material.isLiquid())) { int i5 = k4 - 1;
/* 127 */													 boolean flag2 = false;
/*		 */													 int k5;
/*		 */													 do {
/* 132 */														 k5 = chunk.getTypeId(l4 + k3, i5--, j4 + l3);
/* 133 */														 i4++;
/* 134 */													 }while ((i5 > 0) && (k5 != 0) && (Block.byId[k5].material.isLiquid()));
/*		 */												 }
/*		 */											 }
/*		 */ 
/* 138 */											 d1 += k4 / (i * i);
/* 139 */											 aint[j5] += 1;
/*		 */										 }
/*		 */									 }
/*		 */								 }
/*		 */ 
/* 144 */								 i4 /= i * i;
/* 145 */								 int l5 = b0 / (i * i);
/*		 */ 
/* 147 */								 l5 = b1 / (i * i);
/* 148 */								 l5 = b2 / (i * i);
/* 149 */								 int l4 = 0;
/* 150 */								 int j4 = 0;
/*		 */ 
/* 152 */								 for (int k4 = 0; k4 < 256; k4++) {
/* 153 */									 if (aint[k4] > l4) {
/* 154 */										 j4 = k4;
/* 155 */										 l4 = aint[k4];
/*		 */									 }
/*		 */								 }
/*		 */ 
/* 159 */								 double d2 = (d1 - d0) * 4.0D / (i + 4) + ((k1 + j2 & 0x1) - 0.5D) * 0.4D;
/* 160 */								 byte b3 = 1;
/*		 */ 
/* 162 */								 if (d2 > 0.6D) {
/* 163 */									 b3 = 2;
/*		 */								 }
/*		 */ 
/* 166 */								 if (d2 < -0.6D) {
/* 167 */									 b3 = 0;
/*		 */								 }
/*		 */ 
/* 170 */								 int i5 = 0;
/* 171 */								 if (j4 > 0) {
/* 172 */									 MaterialMapColor materialmapcolor = Block.byId[j4].material.F;
/*		 */ 
/* 174 */									 if (materialmapcolor == MaterialMapColor.n) {
/* 175 */										 d2 = i4 * 0.1D + (k1 + j2 & 0x1) * 0.2D;
/* 176 */										 b3 = 1;
/* 177 */										 if (d2 < 0.5D) {
/* 178 */											 b3 = 2;
/*		 */										 }
/*		 */ 
/* 181 */										 if (d2 > 0.9D) {
/* 182 */											 b3 = 0;
/*		 */										 }
/*		 */									 }
/*		 */ 
/* 186 */									 i5 = materialmapcolor.q;
/*		 */								 }
/*		 */ 
/* 189 */								 d0 = d1;
/* 190 */								 if ((j2 >= 0) && (k2 * k2 + l2 * l2 < j1 * j1) && ((!flag) || ((k1 + j2 & 0x1) != 0))) {
/* 191 */									 byte b4 = worldmap.colors[(k1 + j2 * short1)];
/* 192 */									 byte b5 = (byte)(i5 * 4 + b3);
/*		 */ 
/* 194 */									 if (b4 != b5) {
/* 195 */										 if (l1 > j2) {
/* 196 */											 l1 = j2;
/*		 */										 }
/*		 */ 
/* 199 */										 if (i2 < j2) {
/* 200 */											 i2 = j2;
/*		 */										 }
/*		 */ 
/* 203 */										 worldmap.colors[(k1 + j2 * short1)] = b5;
/*		 */									 }
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */ 
/* 210 */					 if (l1 <= i2)
/* 211 */						 worldmap.flagDirty(k1, l1, i2);
/*		 */				 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(ItemStack itemstack, World world, Entity entity, int i, boolean flag)
/*		 */	 {
/* 219 */		 if (!world.isStatic) {
/* 220 */			 WorldMap worldmap = getSavedMap(itemstack, world);
/*		 */ 
/* 222 */			 if ((entity instanceof EntityHuman)) {
/* 223 */				 EntityHuman entityhuman = (EntityHuman)entity;
/*		 */ 
/* 225 */				 worldmap.a(entityhuman, itemstack);
/*		 */			 }
/*		 */ 
/* 228 */			 if (flag)
/* 229 */				 a(world, entity, worldmap);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void d(ItemStack itemstack, World world, EntityHuman entityhuman)
/*		 */	 {
/* 235 */		 itemstack.setData(world.b("map"));
/* 236 */		 String s = "map_" + itemstack.getData();
/* 237 */		 WorldMap worldmap = new WorldMap(s);
/*		 */ 
/* 239 */		 world.a(s, worldmap);
/* 240 */		 worldmap.centerX = MathHelper.floor(entityhuman.locX);
/* 241 */		 worldmap.centerZ = MathHelper.floor(entityhuman.locZ);
/* 242 */		 worldmap.scale = 3;
/* 243 */		 worldmap.map = (byte)((WorldServer)world).dimension;
/* 244 */		 worldmap.a();
/*		 */ 
/* 247 */		 MapInitializeEvent event = new MapInitializeEvent(worldmap.mapView);
/* 248 */		 Bukkit.getServer().getPluginManager().callEvent(event);
/*		 */	 }
/*		 */ 
/*		 */	 public Packet c(ItemStack itemstack, World world, EntityHuman entityhuman)
/*		 */	 {
/* 253 */		 byte[] abyte = getSavedMap(itemstack, world).getUpdatePacket(itemstack, world, entityhuman);
/*		 */ 
/* 255 */		 return abyte == null ? null : new Packet131ItemData((short)Item.MAP.id, (short)itemstack.getData(), abyte);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemWorldMap
 * JD-Core Version:		0.6.0
 */