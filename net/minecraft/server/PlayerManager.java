/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Collections;
/*		 */ import java.util.Comparator;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Queue;
/*		 */ import java.util.concurrent.ConcurrentLinkedQueue;
/*		 */ 
/*		 */ public class PlayerManager
/*		 */ {
/*		 */	 private final WorldServer world;
/*	12 */	 private final List managedPlayers = new ArrayList();
/*	13 */	 private final LongHashMap c = new LongHashMap();
/*	14 */	 private final Queue d = new ConcurrentLinkedQueue();
/*		 */	 private final int e;
/*	16 */	 private final int[][] f = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
/*		 */	 private boolean wasNotEmpty;
/*		 */ 
/*		 */	 public PlayerManager(WorldServer worldserver, int i)
/*		 */	 {
/*	20 */		 if (i > 15)
/*	21 */			 throw new IllegalArgumentException("Too big view radius!");
/*	22 */		 if (i < 3) {
/*	23 */			 throw new IllegalArgumentException("Too small view radius!");
/*		 */		 }
/*	25 */		 this.e = i;
/*	26 */		 this.world = worldserver;
/*		 */	 }
/*		 */ 
/*		 */	 public WorldServer a()
/*		 */	 {
/*	31 */		 return this.world;
/*		 */	 }
/*		 */ 
/*		 */	 public void flush() {
/*	35 */		 Iterator iterator = this.d.iterator();
/*		 */ 
/*	37 */		 while (iterator.hasNext()) {
/*	38 */			 PlayerInstance playerinstance = (PlayerInstance)iterator.next();
/*		 */ 
/*	40 */			 playerinstance.a();
/*		 */ 
/*	42 */			 iterator.remove();
/*		 */		 }
/*		 */ 
/*	46 */		 if (this.managedPlayers.isEmpty()) {
/*	47 */			 if (!this.wasNotEmpty) return;
/*	48 */			 WorldProvider worldprovider = this.world.worldProvider;
/*		 */ 
/*	50 */			 if (!worldprovider.e()) {
/*	51 */				 this.world.chunkProviderServer.a();
/*		 */			 }
/*		 */ 
/*	54 */			 this.wasNotEmpty = false;
/*		 */		 } else {
/*	56 */			 this.wasNotEmpty = true;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private PlayerInstance a(int i, int j, boolean flag)
/*		 */	 {
/*	62 */		 long k = i + 2147483647L | j + 2147483647L << 32;
/*	63 */		 PlayerInstance playerinstance = (PlayerInstance)this.c.getEntry(k);
/*		 */ 
/*	65 */		 if ((playerinstance == null) && (flag)) {
/*	66 */			 playerinstance = new PlayerInstance(this, i, j);
/*	67 */			 this.c.put(k, playerinstance);
/*		 */		 }
/*		 */ 
/*	70 */		 return playerinstance;
/*		 */	 }
/*		 */ 
/*		 */	 public void flagDirty(int i, int j, int k) {
/*	74 */		 int l = i >> 4;
/*	75 */		 int i1 = k >> 4;
/*	76 */		 PlayerInstance playerinstance = a(l, i1, false);
/*		 */ 
/*	78 */		 if (playerinstance != null)
/*	79 */			 playerinstance.a(i & 0xF, j, k & 0xF);
/*		 */	 }
/*		 */ 
/*		 */	 public void addPlayer(EntityPlayer entityplayer)
/*		 */	 {
/*	84 */		 int i = (int)entityplayer.locX >> 4;
/*	85 */		 int j = (int)entityplayer.locZ >> 4;
/*		 */ 
/*	87 */		 entityplayer.d = entityplayer.locX;
/*	88 */		 entityplayer.e = entityplayer.locZ;
/*		 */ 
/*	90 */		 for (int k = i - this.e; k <= i + this.e; k++) {
/*	91 */			 for (int l = j - this.e; l <= j + this.e; l++) {
/*	92 */				 a(k, l, true).a(entityplayer);
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	96 */		 this.managedPlayers.add(entityplayer);
/*	97 */		 b(entityplayer);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(EntityPlayer entityplayer) {
/* 101 */		 ArrayList arraylist = new ArrayList(entityplayer.chunkCoordIntPairQueue);
/* 102 */		 int i = 0;
/* 103 */		 int j = this.e;
/* 104 */		 int k = (int)entityplayer.locX >> 4;
/* 105 */		 int l = (int)entityplayer.locZ >> 4;
/* 106 */		 int i1 = 0;
/* 107 */		 int j1 = 0;
/* 108 */		 ChunkCoordIntPair chunkcoordintpair = PlayerInstance.a(a(k, l, true));
/*		 */ 
/* 110 */		 entityplayer.chunkCoordIntPairQueue.clear();
/* 111 */		 if (arraylist.contains(chunkcoordintpair)) {
/* 112 */			 entityplayer.chunkCoordIntPairQueue.add(chunkcoordintpair);
/*		 */		 }
/*		 */ 
/* 117 */		 for (int k1 = 1; k1 <= j * 2; k1++) {
/* 118 */			 for (int l1 = 0; l1 < 2; l1++) {
/* 119 */				 int[] aint = this.f[(i++ % 4)];
/*		 */ 
/* 121 */				 for (int i2 = 0; i2 < k1; i2++) {
/* 122 */					 i1 += aint[0];
/* 123 */					 j1 += aint[1];
/* 124 */					 chunkcoordintpair = PlayerInstance.a(a(k + i1, l + j1, true));
/* 125 */					 if (arraylist.contains(chunkcoordintpair)) {
/* 126 */						 entityplayer.chunkCoordIntPairQueue.add(chunkcoordintpair);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 132 */		 i %= 4;
/*		 */ 
/* 134 */		 for (k1 = 0; k1 < j * 2; k1++) {
/* 135 */			 i1 += this.f[i][0];
/* 136 */			 j1 += this.f[i][1];
/* 137 */			 chunkcoordintpair = PlayerInstance.a(a(k + i1, l + j1, true));
/* 138 */			 if (arraylist.contains(chunkcoordintpair))
/* 139 */				 entityplayer.chunkCoordIntPairQueue.add(chunkcoordintpair);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void removePlayer(EntityPlayer entityplayer)
/*		 */	 {
/* 145 */		 int i = (int)entityplayer.d >> 4;
/* 146 */		 int j = (int)entityplayer.e >> 4;
/*		 */ 
/* 148 */		 for (int k = i - this.e; k <= i + this.e; k++) {
/* 149 */			 for (int l = j - this.e; l <= j + this.e; l++) {
/* 150 */				 PlayerInstance playerinstance = a(k, l, false);
/*		 */ 
/* 152 */				 if (playerinstance != null) {
/* 153 */					 playerinstance.b(entityplayer);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 158 */		 this.managedPlayers.remove(entityplayer);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(int i, int j, int k, int l, int i1) {
/* 162 */		 int j1 = i - k;
/* 163 */		 int k1 = j - l;
/*		 */ 
/* 165 */		 return (k1 >= -i1) && (k1 <= i1);
/*		 */	 }
/*		 */ 
/*		 */	 public void movePlayer(EntityPlayer entityplayer) {
/* 169 */		 int i = (int)entityplayer.locX >> 4;
/* 170 */		 int j = (int)entityplayer.locZ >> 4;
/* 171 */		 double d0 = entityplayer.d - entityplayer.locX;
/* 172 */		 double d1 = entityplayer.e - entityplayer.locZ;
/* 173 */		 double d2 = d0 * d0 + d1 * d1;
/*		 */ 
/* 175 */		 if (d2 >= 64.0D) {
/* 176 */			 int k = (int)entityplayer.d >> 4;
/* 177 */			 int l = (int)entityplayer.e >> 4;
/* 178 */			 int i1 = this.e;
/* 179 */			 int j1 = i - k;
/* 180 */			 int k1 = j - l;
/*		 */ 
/* 182 */			 if ((j1 != 0) || (k1 != 0)) {
/* 183 */				 for (int l1 = i - i1; l1 <= i + i1; l1++) {
/* 184 */					 for (int i2 = j - i1; i2 <= j + i1; i2++) {
/* 185 */						 if (!a(l1, i2, k, l, i1)) {
/* 186 */							 a(l1, i2, true).a(entityplayer);
/*		 */						 }
/*		 */ 
/* 189 */						 if (!a(l1 - j1, i2 - k1, i, j, i1)) {
/* 190 */							 PlayerInstance playerinstance = a(l1 - j1, i2 - k1, false);
/*		 */ 
/* 192 */							 if (playerinstance != null) {
/* 193 */								 playerinstance.b(entityplayer);
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 199 */				 b(entityplayer);
/* 200 */				 entityplayer.d = entityplayer.locX;
/* 201 */				 entityplayer.e = entityplayer.locZ;
/*		 */ 
/* 204 */				 if ((i1 > 1) || (i1 < -1) || (j1 > 1) || (j1 < -1)) {
/* 205 */					 int x = i;
/* 206 */					 int z = j;
/* 207 */					 List chunksToSend = entityplayer.chunkCoordIntPairQueue;
/*		 */ 
/* 209 */					 Collections.sort(chunksToSend, new Comparator(x, z) {
/*		 */						 public int compare(ChunkCoordIntPair a, ChunkCoordIntPair b) {
/* 211 */							 return Math.max(Math.abs(a.x - this.val$x), Math.abs(a.z - this.val$z)) - Math.max(Math.abs(b.x - this.val$x), Math.abs(b.z - this.val$z));
/*		 */						 }
/*		 */					 });
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityPlayer entityplayer, int i, int j) {
/* 221 */		 PlayerInstance playerinstance = a(i, j, false);
/*		 */ 
/* 223 */		 return playerinstance == null ? false : PlayerInstance.b(playerinstance).contains(entityplayer);
/*		 */	 }
/*		 */ 
/*		 */	 public static int getFurthestViewableBlock(int i) {
/* 227 */		 return i * 16 - 16;
/*		 */	 }
/*		 */ 
/*		 */	 static WorldServer a(PlayerManager playermanager) {
/* 231 */		 return playermanager.world;
/*		 */	 }
/*		 */ 
/*		 */	 static LongHashMap b(PlayerManager playermanager) {
/* 235 */		 return playermanager.c;
/*		 */	 }
/*		 */ 
/*		 */	 static Queue c(PlayerManager playermanager) {
/* 239 */		 return playermanager.d;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PlayerManager
 * JD-Core Version:		0.6.0
 */