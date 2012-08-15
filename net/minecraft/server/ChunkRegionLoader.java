/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ import java.io.File;
/*		 */ import java.io.IOException;
/*		 */ import java.io.PrintStream;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Collection;
/*		 */ import java.util.HashSet;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Set;
/*		 */ 
/*		 */ public class ChunkRegionLoader
/*		 */	 implements IAsyncChunkSaver, IChunkLoader
/*		 */ {
/*	16 */	 private List a = new ArrayList();
/*	17 */	 private Set b = new HashSet();
/*	18 */	 private Object c = new Object();
/*		 */	 private final File d;
/*		 */ 
/*		 */	 public ChunkRegionLoader(File file1)
/*		 */	 {
/*	22 */		 this.d = file1;
/*		 */	 }
/*		 */ 
/*		 */	 public Chunk a(World world, int i, int j) {
/*	26 */		 NBTTagCompound nbttagcompound = null;
/*	27 */		 ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);
/*	28 */		 Object object = this.c;
/*		 */ 
/*	30 */		 synchronized (this.c) {
/*	31 */			 if (this.b.contains(chunkcoordintpair)) {
/*	32 */				 Iterator iterator = this.a.iterator();
/*		 */ 
/*	34 */				 while (iterator.hasNext()) {
/*	35 */					 PendingChunkToSave pendingchunktosave = (PendingChunkToSave)iterator.next();
/*		 */ 
/*	37 */					 if (pendingchunktosave.a.equals(chunkcoordintpair)) {
/*	38 */						 nbttagcompound = pendingchunktosave.b;
/*	39 */						 break;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	45 */		 if (nbttagcompound == null) {
/*	46 */			 DataInputStream datainputstream = RegionFileCache.c(this.d, i, j);
/*		 */ 
/*	48 */			 if (datainputstream == null) {
/*	49 */				 return null;
/*		 */			 }
/*		 */ 
/*	52 */			 nbttagcompound = NBTCompressedStreamTools.a(datainputstream);
/*		 */		 }
/*		 */ 
/*	55 */		 return a(world, i, j, nbttagcompound);
/*		 */	 }
/*		 */ 
/*		 */	 protected Chunk a(World world, int i, int j, NBTTagCompound nbttagcompound) {
/*	59 */		 if (!nbttagcompound.hasKey("Level")) {
/*	60 */			 System.out.println("Chunk file at " + i + "," + j + " is missing level data, skipping");
/*	61 */			 return null;
/*	62 */		 }if (!nbttagcompound.getCompound("Level").hasKey("Sections")) {
/*	63 */			 System.out.println("Chunk file at " + i + "," + j + " is missing block data, skipping");
/*	64 */			 return null;
/*		 */		 }
/*	66 */		 Chunk chunk = a(world, nbttagcompound.getCompound("Level"));
/*		 */ 
/*	68 */		 if (!chunk.a(i, j)) {
/*	69 */			 System.out.println("Chunk file at " + i + "," + j + " is in the wrong location; relocating. (Expected " + i + ", " + j + ", got " + chunk.x + ", " + chunk.z + ")");
/*	70 */			 nbttagcompound.getCompound("Level").setInt("xPos", i);
/*	71 */			 nbttagcompound.getCompound("Level").setInt("zPos", j);
/*	72 */			 chunk = a(world, nbttagcompound.getCompound("Level"));
/*		 */		 }
/*		 */ 
/*	75 */		 return chunk;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, Chunk chunk)
/*		 */	 {
/*		 */		 try
/*		 */		 {
/*	82 */			 world.B();
/*		 */		 } catch (ExceptionWorldConflict ex) {
/*	84 */			 ex.printStackTrace();
/*		 */		 }
/*		 */ 
/*		 */		 try
/*		 */		 {
/*	89 */			 NBTTagCompound nbttagcompound = new NBTTagCompound();
/*	90 */			 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*		 */ 
/*	92 */			 nbttagcompound.set("Level", nbttagcompound1);
/*	93 */			 a(chunk, world, nbttagcompound1);
/*	94 */			 a(chunk.l(), nbttagcompound);
/*		 */		 } catch (Exception exception) {
/*	96 */			 exception.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) {
/* 101 */		 Object object = this.c;
/*		 */ 
/* 103 */		 synchronized (this.c) {
/* 104 */			 if (this.b.contains(chunkcoordintpair)) {
/* 105 */				 for (int i = 0; i < this.a.size(); i++) {
/* 106 */					 if (((PendingChunkToSave)this.a.get(i)).a.equals(chunkcoordintpair)) {
/* 107 */						 this.a.set(i, new PendingChunkToSave(chunkcoordintpair, nbttagcompound));
/* 108 */						 return;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 113 */			 this.a.add(new PendingChunkToSave(chunkcoordintpair, nbttagcompound));
/* 114 */			 this.b.add(chunkcoordintpair);
/* 115 */			 FileIOThread.a.a(this);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/* 120 */		 PendingChunkToSave pendingchunktosave = null;
/* 121 */		 Object object = this.c;
/*		 */ 
/* 123 */		 synchronized (this.c) {
/* 124 */			 if (this.a.isEmpty()) {
/* 125 */				 return false;
/*		 */			 }
/*		 */ 
/* 128 */			 pendingchunktosave = (PendingChunkToSave)this.a.remove(0);
/* 129 */			 this.b.remove(pendingchunktosave.a);
/*		 */		 }
/*		 */ 
/* 132 */		 if (pendingchunktosave != null) {
/*		 */			 try {
/* 134 */				 a(pendingchunktosave);
/*		 */			 } catch (Exception exception) {
/* 136 */				 exception.printStackTrace();
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 140 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(PendingChunkToSave pendingchunktosave) throws IOException {
/* 144 */		 DataOutputStream dataoutputstream = RegionFileCache.d(this.d, pendingchunktosave.a.x, pendingchunktosave.a.z);
/*		 */ 
/* 146 */		 NBTCompressedStreamTools.a(pendingchunktosave.b, dataoutputstream);
/* 147 */		 dataoutputstream.close();
/*		 */	 }
/*		 */	 public void b(World world, Chunk chunk) {
/*		 */	 }
/*		 */	 public void a() {
/*		 */	 }
/*		 */	 public void b() {
/*		 */	 }
/*		 */ 
/*		 */	 private void a(Chunk chunk, World world, NBTTagCompound nbttagcompound) {
/* 157 */		 nbttagcompound.setInt("xPos", chunk.x);
/* 158 */		 nbttagcompound.setInt("zPos", chunk.z);
/* 159 */		 nbttagcompound.setLong("LastUpdate", world.getTime());
/* 160 */		 nbttagcompound.setIntArray("HeightMap", chunk.heightMap);
/* 161 */		 nbttagcompound.setBoolean("TerrainPopulated", chunk.done);
/* 162 */		 ChunkSection[] achunksection = chunk.i();
/* 163 */		 NBTTagList nbttaglist = new NBTTagList("Sections");
/* 164 */		 ChunkSection[] achunksection1 = achunksection;
/* 165 */		 int i = achunksection.length;
/*		 */ 
/* 169 */		 for (int j = 0; j < i; j++) {
/* 170 */			 ChunkSection chunksection = achunksection1[j];
/*		 */ 
/* 172 */			 if (chunksection != null) {
/* 173 */				 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 174 */				 nbttagcompound1.setByte("Y", (byte)(chunksection.d() >> 4 & 0xFF));
/* 175 */				 nbttagcompound1.setByteArray("Blocks", chunksection.g());
/* 176 */				 if (chunksection.i() != null) {
/* 177 */					 nbttagcompound1.setByteArray("Add", chunksection.i().a);
/*		 */				 }
/*		 */ 
/* 180 */				 nbttagcompound1.setByteArray("Data", chunksection.j().a);
/* 181 */				 nbttagcompound1.setByteArray("SkyLight", chunksection.l().a);
/* 182 */				 nbttagcompound1.setByteArray("BlockLight", chunksection.k().a);
/* 183 */				 nbttaglist.add(nbttagcompound1);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 187 */		 nbttagcompound.set("Sections", nbttaglist);
/* 188 */		 nbttagcompound.setByteArray("Biomes", chunk.m());
/* 189 */		 chunk.m = false;
/* 190 */		 NBTTagList nbttaglist1 = new NBTTagList();
/*		 */ 
/* 194 */		 for (i = 0; i < chunk.entitySlices.length; i++) {
/* 195 */			 Iterator iterator = chunk.entitySlices[i].iterator();
/*		 */ 
/* 197 */			 while (iterator.hasNext()) {
/* 198 */				 Entity entity = (Entity)iterator.next();
/*		 */ 
/* 200 */				 chunk.m = true;
/* 201 */				 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 202 */				 if (entity.c(nbttagcompound1)) {
/* 203 */					 nbttaglist1.add(nbttagcompound1);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 208 */		 nbttagcompound.set("Entities", nbttaglist1);
/* 209 */		 NBTTagList nbttaglist2 = new NBTTagList();
/*		 */ 
/* 211 */		 Iterator iterator = chunk.tileEntities.values().iterator();
/*		 */ 
/* 213 */		 while (iterator.hasNext()) {
/* 214 */			 TileEntity tileentity = (TileEntity)iterator.next();
/*		 */ 
/* 216 */			 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 217 */			 tileentity.b(nbttagcompound1);
/* 218 */			 nbttaglist2.add(nbttagcompound1);
/*		 */		 }
/*		 */ 
/* 221 */		 nbttagcompound.set("TileEntities", nbttaglist2);
/* 222 */		 List list = world.a(chunk, false);
/*		 */ 
/* 224 */		 if (list != null) {
/* 225 */			 long k = world.getTime();
/* 226 */			 NBTTagList nbttaglist3 = new NBTTagList();
/* 227 */			 Iterator iterator1 = list.iterator();
/*		 */ 
/* 229 */			 while (iterator1.hasNext()) {
/* 230 */				 NextTickListEntry nextticklistentry = (NextTickListEntry)iterator1.next();
/* 231 */				 NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/*		 */ 
/* 233 */				 nbttagcompound2.setInt("i", nextticklistentry.d);
/* 234 */				 nbttagcompound2.setInt("x", nextticklistentry.a);
/* 235 */				 nbttagcompound2.setInt("y", nextticklistentry.b);
/* 236 */				 nbttagcompound2.setInt("z", nextticklistentry.c);
/* 237 */				 nbttagcompound2.setInt("t", (int)(nextticklistentry.e - k));
/* 238 */				 nbttaglist3.add(nbttagcompound2);
/*		 */			 }
/*		 */ 
/* 241 */			 nbttagcompound.set("TileTicks", nbttaglist3);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private Chunk a(World world, NBTTagCompound nbttagcompound) {
/* 246 */		 int i = nbttagcompound.getInt("xPos");
/* 247 */		 int j = nbttagcompound.getInt("zPos");
/* 248 */		 Chunk chunk = new Chunk(world, i, j);
/*		 */ 
/* 250 */		 chunk.heightMap = nbttagcompound.getIntArray("HeightMap");
/* 251 */		 chunk.done = nbttagcompound.getBoolean("TerrainPopulated");
/* 252 */		 NBTTagList nbttaglist = nbttagcompound.getList("Sections");
/* 253 */		 byte b0 = 16;
/* 254 */		 ChunkSection[] achunksection = new ChunkSection[b0];
/*		 */ 
/* 256 */		 for (int k = 0; k < nbttaglist.size(); k++) {
/* 257 */			 NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.get(k);
/* 258 */			 byte b1 = nbttagcompound1.getByte("Y");
/* 259 */			 ChunkSection chunksection = new ChunkSection(b1 << 4);
/*		 */ 
/* 261 */			 chunksection.a(nbttagcompound1.getByteArray("Blocks"));
/* 262 */			 if (nbttagcompound1.hasKey("Add")) {
/* 263 */				 chunksection.a(new NibbleArray(nbttagcompound1.getByteArray("Add"), 4));
/*		 */			 }
/*		 */ 
/* 266 */			 chunksection.b(new NibbleArray(nbttagcompound1.getByteArray("Data"), 4));
/* 267 */			 chunksection.d(new NibbleArray(nbttagcompound1.getByteArray("SkyLight"), 4));
/* 268 */			 chunksection.c(new NibbleArray(nbttagcompound1.getByteArray("BlockLight"), 4));
/* 269 */			 chunksection.e();
/* 270 */			 achunksection[b1] = chunksection;
/*		 */		 }
/*		 */ 
/* 273 */		 chunk.a(achunksection);
/* 274 */		 if (nbttagcompound.hasKey("Biomes")) {
/* 275 */			 chunk.a(nbttagcompound.getByteArray("Biomes"));
/*		 */		 }
/*		 */ 
/* 278 */		 NBTTagList nbttaglist1 = nbttagcompound.getList("Entities");
/*		 */ 
/* 280 */		 if (nbttaglist1 != null) {
/* 281 */			 for (int l = 0; l < nbttaglist1.size(); l++) {
/* 282 */				 NBTTagCompound nbttagcompound2 = (NBTTagCompound)nbttaglist1.get(l);
/* 283 */				 Entity entity = EntityTypes.a(nbttagcompound2, world);
/*		 */ 
/* 285 */				 chunk.m = true;
/* 286 */				 if (entity != null) {
/* 287 */					 chunk.a(entity);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 292 */		 NBTTagList nbttaglist2 = nbttagcompound.getList("TileEntities");
/*		 */ 
/* 294 */		 if (nbttaglist2 != null) {
/* 295 */			 for (int i1 = 0; i1 < nbttaglist2.size(); i1++) {
/* 296 */				 NBTTagCompound nbttagcompound3 = (NBTTagCompound)nbttaglist2.get(i1);
/* 297 */				 TileEntity tileentity = TileEntity.c(nbttagcompound3);
/*		 */ 
/* 299 */				 if (tileentity != null) {
/* 300 */					 chunk.a(tileentity);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 305 */		 if (nbttagcompound.hasKey("TileTicks")) {
/* 306 */			 NBTTagList nbttaglist3 = nbttagcompound.getList("TileTicks");
/*		 */ 
/* 308 */			 if (nbttaglist3 != null) {
/* 309 */				 for (int j1 = 0; j1 < nbttaglist3.size(); j1++) {
/* 310 */					 NBTTagCompound nbttagcompound4 = (NBTTagCompound)nbttaglist3.get(j1);
/*		 */ 
/* 312 */					 world.b(nbttagcompound4.getInt("x"), nbttagcompound4.getInt("y"), nbttagcompound4.getInt("z"), nbttagcompound4.getInt("i"), nbttagcompound4.getInt("t"));
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 317 */		 return chunk;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ChunkRegionLoader
 * JD-Core Version:		0.6.0
 */