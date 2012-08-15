/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Queue;
/*		 */ 
/*		 */ class PlayerInstance
/*		 */ {
/*		 */	 private final List b;
/*		 */	 private final ChunkCoordIntPair location;
/*		 */	 private short[] dirtyBlocks;
/*		 */	 private int dirtyCount;
/*		 */	 private int f;
/*		 */	 final PlayerManager playerManager;
/*		 */ 
/*		 */	 public PlayerInstance(PlayerManager playermanager, int i, int j)
/*		 */	 {
/*	18 */		 this.playerManager = playermanager;
/*	19 */		 this.b = new ArrayList();
/*	20 */		 this.dirtyBlocks = new short[64];
/*	21 */		 this.dirtyCount = 0;
/*	22 */		 this.location = new ChunkCoordIntPair(i, j);
/*	23 */		 playermanager.a().chunkProviderServer.getChunkAt(i, j);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityPlayer entityplayer) {
/*	27 */		 if (this.b.contains(entityplayer)) {
/*	28 */			 throw new IllegalStateException("Failed to add player. " + entityplayer + " already is in chunk " + this.location.x + ", " + this.location.z);
/*		 */		 }
/*	30 */		 this.b.add(entityplayer);
/*	31 */		 entityplayer.chunkCoordIntPairQueue.add(this.location);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(EntityPlayer entityplayer)
/*		 */	 {
/*	36 */		 if (this.b.contains(entityplayer)) {
/*	37 */			 entityplayer.netServerHandler.sendPacket(new Packet51MapChunk(PlayerManager.a(this.playerManager).getChunkAt(this.location.x, this.location.z), true, 0));
/*	38 */			 this.b.remove(entityplayer);
/*	39 */			 entityplayer.chunkCoordIntPairQueue.remove(this.location);
/*	40 */			 if (this.b.isEmpty()) {
/*	41 */				 long i = this.location.x + 2147483647L | this.location.z + 2147483647L << 32;
/*		 */ 
/*	43 */				 PlayerManager.b(this.playerManager).remove(i);
/*	44 */				 if (this.dirtyCount > 0) {
/*	45 */					 PlayerManager.c(this.playerManager).remove(this);
/*		 */				 }
/*		 */ 
/*	48 */				 this.playerManager.a().chunkProviderServer.queueUnload(this.location.x, this.location.z);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int i, int j, int k) {
/*	54 */		 if (this.dirtyCount == 0) {
/*	55 */			 PlayerManager.c(this.playerManager).add(this);
/*		 */		 }
/*		 */ 
/*	58 */		 this.f |= 1 << (j >> 4);
/*	59 */		 if (this.dirtyCount < 64) {
/*	60 */			 short short1 = (short)(i << 12 | k << 8 | j);
/*		 */ 
/*	62 */			 for (int l = 0; l < this.dirtyCount; l++) {
/*	63 */				 if (this.dirtyBlocks[l] == short1) {
/*	64 */					 return;
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	68 */			 this.dirtyBlocks[(this.dirtyCount++)] = short1;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void sendAll(Packet packet) {
/*	73 */		 Iterator iterator = this.b.iterator();
/*		 */ 
/*	75 */		 while (iterator.hasNext()) {
/*	76 */			 EntityPlayer entityplayer = (EntityPlayer)iterator.next();
/*		 */ 
/*	78 */			 if (!entityplayer.chunkCoordIntPairQueue.contains(this.location))
/*	79 */				 entityplayer.netServerHandler.sendPacket(packet);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a()
/*		 */	 {
/*	85 */		 if (this.dirtyCount != 0)
/*		 */		 {
/*	90 */			 if (this.dirtyCount == 1) {
/*	91 */				 int i = this.location.x * 16 + (this.dirtyBlocks[0] >> 12 & 0xF);
/*	92 */				 int j = this.dirtyBlocks[0] & 0xFF;
/*	93 */				 int k = this.location.z * 16 + (this.dirtyBlocks[0] >> 8 & 0xF);
/*	94 */				 sendAll(new Packet53BlockChange(i, j, k, PlayerManager.a(this.playerManager)));
/*	95 */				 if (PlayerManager.a(this.playerManager).isTileEntity(i, j, k)) {
/*	96 */					 sendTileEntity(PlayerManager.a(this.playerManager).getTileEntity(i, j, k));
/*		 */				 }
/*		 */			 }
/*		 */			 else
/*		 */			 {
/* 101 */				 if (this.dirtyCount == 64) {
/* 102 */					 int i = this.location.x * 16;
/* 103 */					 int j = this.location.z * 16;
/* 104 */					 sendAll(new Packet51MapChunk(PlayerManager.a(this.playerManager).getChunkAt(this.location.x, this.location.z), this.f == 65535, this.f));
/*		 */ 
/* 106 */					 for (int k = 0; k < 16; k++) {
/* 107 */						 if ((this.f & 1 << k) != 0) {
/* 108 */							 int l = k << 4;
/* 109 */							 List list = PlayerManager.a(this.playerManager).getTileEntities(i, l, j, i + 16, l + 16, j + 16);
/* 110 */							 Iterator iterator = list.iterator();
/*		 */ 
/* 112 */							 while (iterator.hasNext()) {
/* 113 */								 TileEntity tileentity = (TileEntity)iterator.next();
/*		 */ 
/* 115 */								 sendTileEntity(tileentity);
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/* 120 */				 sendAll(new Packet52MultiBlockChange(this.location.x, this.location.z, this.dirtyBlocks, this.dirtyCount, PlayerManager.a(this.playerManager)));
/*		 */ 
/* 122 */				 for (int i = 0; i < this.dirtyCount; i++) {
/* 123 */					 int j = this.location.x * 16 + (this.dirtyBlocks[i] >> 12 & 0xF);
/* 124 */					 int k = this.dirtyBlocks[i] & 0xFF;
/* 125 */					 int l = this.location.z * 16 + (this.dirtyBlocks[i] >> 8 & 0xF);
/* 126 */					 if (PlayerManager.a(this.playerManager).isTileEntity(j, k, l)) {
/* 127 */						 sendTileEntity(PlayerManager.a(this.playerManager).getTileEntity(j, k, l));
/*		 */					 }
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 133 */			 this.dirtyCount = 0;
/* 134 */			 this.f = 0;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void sendTileEntity(TileEntity tileentity) {
/* 139 */		 if (tileentity != null) {
/* 140 */			 Packet packet = tileentity.e();
/*		 */ 
/* 142 */			 if (packet != null)
/* 143 */				 sendAll(packet);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 static ChunkCoordIntPair a(PlayerInstance playerinstance)
/*		 */	 {
/* 149 */		 return playerinstance.location;
/*		 */	 }
/*		 */ 
/*		 */	 static List b(PlayerInstance playerinstance) {
/* 153 */		 return playerinstance.b;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PlayerInstance
 * JD-Core Version:		0.6.0
 */