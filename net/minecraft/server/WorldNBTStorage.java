/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ import java.io.File;
/*		 */ import java.io.FileInputStream;
/*		 */ import java.io.FileOutputStream;
/*		 */ import java.io.IOException;
/*		 */ import java.util.UUID;
/*		 */ import java.util.logging.Logger;
/*		 */ import org.bukkit.craftbukkit.entity.CraftPlayer;
/*		 */ 
/*		 */ public class WorldNBTStorage
/*		 */	 implements IDataManager, PlayerFileData
/*		 */ {
/*	21 */	 private static final Logger log = Logger.getLogger("Minecraft");
/*		 */	 private final File baseDir;
/*		 */	 private final File playerDir;
/*		 */	 private final File dataDir;
/*	25 */	 private final long sessionId = System.currentTimeMillis();
/*		 */	 private final String f;
/*	27 */	 private UUID uuid = null;
/*		 */ 
/*		 */	 public WorldNBTStorage(File file1, String s, boolean flag) {
/*	30 */		 this.baseDir = new File(file1, s);
/*	31 */		 this.baseDir.mkdirs();
/*	32 */		 this.playerDir = new File(this.baseDir, "players");
/*	33 */		 this.dataDir = new File(this.baseDir, "data");
/*	34 */		 this.dataDir.mkdirs();
/*	35 */		 this.f = s;
/*	36 */		 if (flag) {
/*	37 */			 this.playerDir.mkdirs();
/*		 */		 }
/*		 */ 
/*	40 */		 h();
/*		 */	 }
/*		 */ 
/*		 */	 private void h() {
/*		 */		 try {
/*	45 */			 File file1 = new File(this.baseDir, "session.lock");
/*	46 */			 DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(file1));
/*		 */			 try
/*		 */			 {
/*	49 */				 dataoutputstream.writeLong(this.sessionId);
/*		 */			 } finally {
/*	51 */				 dataoutputstream.close();
/*		 */			 }
/*		 */		 } catch (IOException ioexception) {
/*	54 */			 ioexception.printStackTrace();
/*	55 */			 throw new RuntimeException("Failed to check session lock, aborting");
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public File getDirectory() {
/*	60 */		 return this.baseDir;
/*		 */	 }
/*		 */ 
/*		 */	 public void checkSession() throws ExceptionWorldConflict {
/*		 */		 try {
/*	65 */			 File file1 = new File(this.baseDir, "session.lock");
/*	66 */			 DataInputStream datainputstream = new DataInputStream(new FileInputStream(file1));
/*		 */			 try
/*		 */			 {
/*	69 */				 if (datainputstream.readLong() != this.sessionId)
/*	70 */					 throw new ExceptionWorldConflict("The save is being accessed from another location, aborting");
/*		 */			 }
/*		 */			 finally {
/*	73 */				 datainputstream.close();
/*		 */			 }
/*		 */		 } catch (IOException ioexception) {
/*	76 */			 throw new ExceptionWorldConflict("Failed to check session lock, aborting");
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public IChunkLoader createChunkLoader(WorldProvider worldprovider) {
/*	81 */		 throw new RuntimeException("Old Chunk Storage is no longer supported.");
/*		 */	 }
/*		 */ 
/*		 */	 public WorldData getWorldData() {
/*	85 */		 File file1 = new File(this.baseDir, "level.dat");
/*		 */		 NBTTagCompound nbttagcompound;
/*		 */		 NBTTagCompound nbttagcompound1;
/*	89 */		 if (file1.exists()) {
/*		 */			 try {
/*	91 */				 nbttagcompound = NBTCompressedStreamTools.a(new FileInputStream(file1));
/*	92 */				 nbttagcompound1 = nbttagcompound.getCompound("Data");
/*	93 */				 return new WorldData(nbttagcompound1);
/*		 */			 } catch (Exception exception) {
/*	95 */				 exception.printStackTrace();
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	99 */		 file1 = new File(this.baseDir, "level.dat_old");
/* 100 */		 if (file1.exists()) {
/*		 */			 try {
/* 102 */				 nbttagcompound = NBTCompressedStreamTools.a(new FileInputStream(file1));
/* 103 */				 nbttagcompound1 = nbttagcompound.getCompound("Data");
/* 104 */				 return new WorldData(nbttagcompound1);
/*		 */			 } catch (Exception exception1) {
/* 106 */				 exception1.printStackTrace();
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 110 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void saveWorldData(WorldData worlddata, NBTTagCompound nbttagcompound) {
/* 114 */		 NBTTagCompound nbttagcompound1 = worlddata.a(nbttagcompound);
/* 115 */		 NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/*		 */ 
/* 117 */		 nbttagcompound2.set("Data", nbttagcompound1);
/*		 */		 try
/*		 */		 {
/* 120 */			 File file1 = new File(this.baseDir, "level.dat_new");
/* 121 */			 File file2 = new File(this.baseDir, "level.dat_old");
/* 122 */			 File file3 = new File(this.baseDir, "level.dat");
/*		 */ 
/* 124 */			 NBTCompressedStreamTools.a(nbttagcompound2, new FileOutputStream(file1));
/* 125 */			 if (file2.exists()) {
/* 126 */				 file2.delete();
/*		 */			 }
/*		 */ 
/* 129 */			 file3.renameTo(file2);
/* 130 */			 if (file3.exists()) {
/* 131 */				 file3.delete();
/*		 */			 }
/*		 */ 
/* 134 */			 file1.renameTo(file3);
/* 135 */			 if (file1.exists())
/* 136 */				 file1.delete();
/*		 */		 }
/*		 */		 catch (Exception exception) {
/* 139 */			 exception.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void saveWorldData(WorldData worlddata) {
/* 144 */		 NBTTagCompound nbttagcompound = worlddata.a();
/* 145 */		 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*		 */ 
/* 147 */		 nbttagcompound1.set("Data", nbttagcompound);
/*		 */		 try
/*		 */		 {
/* 150 */			 File file1 = new File(this.baseDir, "level.dat_new");
/* 151 */			 File file2 = new File(this.baseDir, "level.dat_old");
/* 152 */			 File file3 = new File(this.baseDir, "level.dat");
/*		 */ 
/* 154 */			 NBTCompressedStreamTools.a(nbttagcompound1, new FileOutputStream(file1));
/* 155 */			 if (file2.exists()) {
/* 156 */				 file2.delete();
/*		 */			 }
/*		 */ 
/* 159 */			 file3.renameTo(file2);
/* 160 */			 if (file3.exists()) {
/* 161 */				 file3.delete();
/*		 */			 }
/*		 */ 
/* 164 */			 file1.renameTo(file3);
/* 165 */			 if (file1.exists())
/* 166 */				 file1.delete();
/*		 */		 }
/*		 */		 catch (Exception exception) {
/* 169 */			 exception.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void save(EntityHuman entityhuman) {
/*		 */		 try {
/* 175 */			 NBTTagCompound nbttagcompound = new NBTTagCompound();
/*		 */ 
/* 177 */			 entityhuman.d(nbttagcompound);
/* 178 */			 File file1 = new File(this.playerDir, entityhuman.name + ".dat.tmp");
/* 179 */			 File file2 = new File(this.playerDir, entityhuman.name + ".dat");
/*		 */ 
/* 181 */			 NBTCompressedStreamTools.a(nbttagcompound, new FileOutputStream(file1));
/* 182 */			 if (file2.exists()) {
/* 183 */				 file2.delete();
/*		 */			 }
/*		 */ 
/* 186 */			 file1.renameTo(file2);
/*		 */		 } catch (Exception exception) {
/* 188 */			 log.warning("Failed to save player data for " + entityhuman.name);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void load(EntityHuman entityhuman) {
/* 193 */		 NBTTagCompound nbttagcompound = getPlayerData(entityhuman.name);
/*		 */ 
/* 195 */		 if (nbttagcompound != null)
/*		 */		 {
/* 197 */			 if ((entityhuman instanceof EntityPlayer)) {
/* 198 */				 CraftPlayer player = (CraftPlayer)entityhuman.bukkitEntity;
/* 199 */				 player.setFirstPlayed(new File(this.playerDir, entityhuman.name + ".dat").lastModified());
/*		 */			 }
/*		 */ 
/* 202 */			 entityhuman.e(nbttagcompound);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagCompound getPlayerData(String s) {
/*		 */		 try {
/* 208 */			 File file1 = new File(this.playerDir, s + ".dat");
/*		 */ 
/* 210 */			 if (file1.exists())
/* 211 */				 return NBTCompressedStreamTools.a(new FileInputStream(file1));
/*		 */		 }
/*		 */		 catch (Exception exception) {
/* 214 */			 log.warning("Failed to load player data for " + s);
/*		 */		 }
/*		 */ 
/* 217 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public PlayerFileData getPlayerFileData() {
/* 221 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public String[] getSeenPlayers() {
/* 225 */		 String[] astring = this.playerDir.list();
/*		 */ 
/* 227 */		 for (int i = 0; i < astring.length; i++) {
/* 228 */			 if (astring[i].endsWith(".dat")) {
/* 229 */				 astring[i] = astring[i].substring(0, astring[i].length() - 4);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 233 */		 return astring;
/*		 */	 }
/*		 */	 public void a() {
/*		 */	 }
/*		 */ 
/*		 */	 public File getDataFile(String s) {
/* 239 */		 return new File(this.dataDir, s + ".dat");
/*		 */	 }
/*		 */ 
/*		 */	 public String g() {
/* 243 */		 return this.f;
/*		 */	 }
/*		 */ 
/*		 */	 public UUID getUUID()
/*		 */	 {
/* 248 */		 if (this.uuid != null) return this.uuid; try
/*		 */		 {
/* 250 */			 File file1 = new File(this.baseDir, "uid.dat");
/* 251 */			 if (!file1.exists()) {
/* 252 */				 DataOutputStream dos = new DataOutputStream(new FileOutputStream(file1));
/* 253 */				 this.uuid = UUID.randomUUID();
/* 254 */				 dos.writeLong(this.uuid.getMostSignificantBits());
/* 255 */				 dos.writeLong(this.uuid.getLeastSignificantBits());
/* 256 */				 dos.close();
/*		 */			 }
/*		 */			 else {
/* 259 */				 DataInputStream dis = new DataInputStream(new FileInputStream(file1));
/* 260 */				 this.uuid = new UUID(dis.readLong(), dis.readLong());
/* 261 */				 dis.close();
/*		 */			 }
/* 263 */			 return this.uuid;
/*		 */		 } catch (IOException ex) {
/*		 */		 }
/* 266 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public File getPlayerDir()
/*		 */	 {
/* 271 */		 return this.playerDir;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldNBTStorage
 * JD-Core Version:		0.6.0
 */