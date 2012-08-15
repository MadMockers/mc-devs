/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.lang.reflect.Constructor;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.util.EntryBase;
/*		 */ import org.bukkit.craftbukkit.util.LongBaseHashtable;
/*		 */ import org.bukkit.craftbukkit.util.LongHash;
/*		 */ import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
/*		 */ 
/*		 */ public final class SpawnerCreature
/*		 */ {
/*	37 */	 protected static final Class[] a = { EntitySpider.class, EntityZombie.class, EntitySkeleton.class };
/*		 */ 
/*		 */	 protected static ChunkPosition getRandomPosition(World world, int i, int j) {
/*	40 */		 Chunk chunk = world.getChunkAt(i, j);
/*	41 */		 int k = i * 16 + world.random.nextInt(16);
/*	42 */		 int l = j * 16 + world.random.nextInt(16);
/*	43 */		 int i1 = world.random.nextInt(chunk == null ? world.L() : chunk.h() + 16 - 1);
/*		 */ 
/*	45 */		 return new ChunkPosition(k, i1, l);
/*		 */	 }
/*		 */ 
/*		 */	 public static final int spawnEntities(WorldServer worldserver, boolean flag, boolean flag1) {
/*	49 */		 if ((!flag) && (!flag1)) {
/*	50 */			 return 0;
/*		 */		 }
/*		 */ 
/*	54 */		 LongBaseHashtable chunkCoords = new LongBaseHashtable();
/*		 */ 
/*	60 */		 for (int i = 0; i < worldserver.players.size(); i++) {
/*	61 */			 EntityHuman entityhuman = (EntityHuman)worldserver.players.get(i);
/*	62 */			 int k = MathHelper.floor(entityhuman.locX / 16.0D);
/*		 */ 
/*	64 */			 int j = MathHelper.floor(entityhuman.locZ / 16.0D);
/*	65 */			 byte b0 = 8;
/*		 */ 
/*	67 */			 for (int l = -b0; l <= b0; l++) {
/*	68 */				 for (int i1 = -b0; i1 <= b0; i1++) {
/*	69 */					 boolean flag2 = (l == -b0) || (l == b0) || (i1 == -b0) || (i1 == b0);
/*		 */ 
/*	71 */					 long chunkCoord = LongHash.toLong(l + k, i1 + j);
/*		 */ 
/*	73 */					 if (!flag2)
/*	74 */						 chunkCoords.put(new ChunkEntry(l + k, i1 + j, false));
/*	75 */					 else if (!chunkCoords.containsKey(chunkCoord)) {
/*	76 */						 chunkCoords.put(new ChunkEntry(l + k, i1 + j, true));
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/*	83 */		 i = 0;
/*	84 */		 ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
/*	85 */		 ArrayList b = chunkCoords.entries();
/*	86 */		 EnumCreatureType[] aenumcreaturetype = EnumCreatureType.values();
/*		 */ 
/*	88 */		 int j = aenumcreaturetype.length;
/*		 */		 EnumCreatureType enumcreaturetype;
/*	90 */		 label903: label912: for (int j1 = 0; j1 < j; j1++) {
/*	91 */			 enumcreaturetype = aenumcreaturetype[j1];
/*		 */ 
/*	94 */			 int limit = 0;
/*	95 */			 switch (1.$SwitchMap$net$minecraft$server$EnumCreatureType[enumcreaturetype.ordinal()]) {
/*		 */			 case 1:
/*	97 */				 limit = worldserver.getWorld().getMonsterSpawnLimit();
/*	98 */				 break;
/*		 */			 case 2:
/* 100 */				 limit = worldserver.getWorld().getAnimalSpawnLimit();
/* 101 */				 break;
/*		 */			 case 3:
/* 103 */				 limit = worldserver.getWorld().getWaterAnimalSpawnLimit();
/*		 */			 }
/*		 */ 
/* 107 */			 if (limit == 0) {
/* 108 */				 return 0;
/*		 */			 }
/*		 */ 
/* 112 */			 if (((enumcreaturetype.d()) && (!flag1)) || ((!enumcreaturetype.d()) && (!flag)) || (worldserver.a(enumcreaturetype.a()) > limit * b.size() / 256))
/*		 */			 {
/*		 */				 continue;
/*		 */			 }
/* 116 */			 for (EntryBase base : b) {
/* 117 */				 ChunkEntry entry = (ChunkEntry)base;
/* 118 */				 if (!entry.spawn) {
/* 119 */					 ChunkPosition chunkposition = getRandomPosition(worldserver, entry.getX(), entry.getZ());
/*		 */ 
/* 121 */					 int k1 = chunkposition.x;
/* 122 */					 int l1 = chunkposition.y;
/* 123 */					 int i2 = chunkposition.z;
/*		 */ 
/* 125 */					 if ((!worldserver.s(k1, l1, i2)) && (worldserver.getMaterial(k1, l1, i2) == enumcreaturetype.c())) {
/* 126 */						 int j2 = 0;
/* 127 */						 int k2 = 0;
/*		 */						 while (true) {
/* 129 */							 if (k2 >= 3) break label912; int l2 = k1;
/* 131 */							 int i3 = l1;
/* 132 */							 int j3 = i2;
/* 133 */							 byte b1 = 6;
/* 134 */							 BiomeMeta biomemeta = null;
/* 135 */							 int k3 = 0;
/*		 */							 while (true)
/*		 */							 {
/* 138 */								 if (k3 >= 4) break label903;
/* 140 */								 l2 += worldserver.random.nextInt(b1) - worldserver.random.nextInt(b1);
/* 141 */								 i3 += worldserver.random.nextInt(1) - worldserver.random.nextInt(1);
/* 142 */								 j3 += worldserver.random.nextInt(b1) - worldserver.random.nextInt(b1);
/* 143 */								 if (a(enumcreaturetype, worldserver, l2, i3, j3)) {
/* 144 */									 float f = l2 + 0.5F;
/* 145 */									 float f1 = i3;
/* 146 */									 float f2 = j3 + 0.5F;
/*		 */ 
/* 148 */									 if (worldserver.findNearbyPlayer(f, f1, f2, 24.0D) == null) {
/* 149 */										 float f3 = f - chunkcoordinates.x;
/* 150 */										 float f4 = f1 - chunkcoordinates.y;
/* 151 */										 float f5 = f2 - chunkcoordinates.z;
/* 152 */										 float f6 = f3 * f3 + f4 * f4 + f5 * f5;
/*		 */ 
/* 154 */										 if (f6 >= 576.0F) {
/* 155 */											 if (biomemeta == null) {
/* 156 */												 biomemeta = worldserver.a(enumcreaturetype, l2, i3, j3);
/* 157 */												 if (biomemeta == null)
/*		 */													 break label903;
/*		 */											 }
/*		 */											 EntityLiving entityliving;
/*		 */											 try
/*		 */											 {
/* 165 */												 entityliving = (EntityLiving)biomemeta.b.getConstructor(new Class[] { World.class }).newInstance(new Object[] { worldserver });
/*		 */											 } catch (Exception exception) {
/* 167 */												 exception.printStackTrace();
/* 168 */												 return i;
/*		 */											 }
/*		 */ 
/* 171 */											 entityliving.setPositionRotation(f, f1, f2, worldserver.random.nextFloat() * 360.0F, 0.0F);
/* 172 */											 if (entityliving.canSpawn()) {
/* 173 */												 j2++;
/*		 */ 
/* 175 */												 worldserver.addEntity(entityliving, CreatureSpawnEvent.SpawnReason.NATURAL);
/* 176 */												 a(entityliving, worldserver, f, f1, f2);
/* 177 */												 if (j2 >= entityliving.bl())
/*		 */												 {
/*		 */													 break;
/*		 */												 }
/*		 */											 }
/* 182 */											 i += j2;
/*		 */										 }
/*		 */									 }
/*		 */								 }
/*		 */ 
/* 187 */								 k3++;
/*		 */							 }
/*		 */ 
/* 192 */							 k2++;
/*		 */						 }
/*		 */					 }
/*		 */ 
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 202 */		 return i;
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean a(EnumCreatureType enumcreaturetype, World world, int i, int j, int k)
/*		 */	 {
/* 207 */		 if (enumcreaturetype.c() == Material.WATER)
/* 208 */			 return (world.getMaterial(i, j, k).isLiquid()) && (!world.s(i, j + 1, k));
/* 209 */		 if (!world.t(i, j - 1, k)) {
/* 210 */			 return false;
/*		 */		 }
/* 212 */		 int l = world.getTypeId(i, j - 1, k);
/*		 */ 
/* 214 */		 return (l != Block.BEDROCK.id) && (!world.s(i, j, k)) && (!world.getMaterial(i, j, k).isLiquid()) && (!world.s(i, j + 1, k));
/*		 */	 }
/*		 */ 
/*		 */	 private static void a(EntityLiving entityliving, World world, float f, float f1, float f2)
/*		 */	 {
/* 219 */		 if (entityliving.dead) return;
/* 220 */		 if (((entityliving instanceof EntitySpider)) && (world.random.nextInt(100) == 0)) {
/* 221 */			 EntitySkeleton entityskeleton = new EntitySkeleton(world);
/*		 */ 
/* 223 */			 entityskeleton.setPositionRotation(f, f1, f2, entityliving.yaw, 0.0F);
/*		 */ 
/* 225 */			 world.addEntity(entityskeleton, CreatureSpawnEvent.SpawnReason.JOCKEY);
/* 226 */			 entityskeleton.mount(entityliving);
/* 227 */		 } else if ((entityliving instanceof EntitySheep)) {
/* 228 */			 ((EntitySheep)entityliving).setColor(EntitySheep.a(world.random));
/* 229 */		 } else if (((entityliving instanceof EntityOcelot)) && (world.random.nextInt(7) == 0)) {
/* 230 */			 for (int i = 0; i < 2; i++) {
/* 231 */				 EntityOcelot entityocelot = new EntityOcelot(world);
/*		 */ 
/* 233 */				 entityocelot.setPositionRotation(f, f1, f2, entityliving.yaw, 0.0F);
/* 234 */				 entityocelot.setAge(-24000);
/* 235 */				 world.addEntity(entityocelot, CreatureSpawnEvent.SpawnReason.NATURAL);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public static void a(World world, BiomeBase biomebase, int i, int j, int k, int l, Random random) {
/* 241 */		 List list = biomebase.getMobs(EnumCreatureType.CREATURE);
/*		 */ 
/* 243 */		 if (!list.isEmpty())
/* 244 */			 while (random.nextFloat() < biomebase.f()) {
/* 245 */				 BiomeMeta biomemeta = (BiomeMeta)WeightedRandom.a(world.random, list);
/* 246 */				 int i1 = biomemeta.c + random.nextInt(1 + biomemeta.d - biomemeta.c);
/* 247 */				 int j1 = i + random.nextInt(k);
/* 248 */				 int k1 = j + random.nextInt(l);
/* 249 */				 int l1 = j1;
/* 250 */				 int i2 = k1;
/*		 */ 
/* 252 */				 for (int j2 = 0; j2 < i1; j2++) {
/* 253 */					 boolean flag = false;
/*		 */ 
/* 255 */					 for (int k2 = 0; (!flag) && (k2 < 4); k2++) {
/* 256 */						 int l2 = world.h(j1, k1);
/*		 */ 
/* 258 */						 if (a(EnumCreatureType.CREATURE, world, j1, l2, k1)) { float f = j1 + 0.5F;
/* 260 */							 float f1 = l2;
/* 261 */							 float f2 = k1 + 0.5F;
/*		 */							 EntityLiving entityliving;
/*		 */							 try { entityliving = (EntityLiving)biomemeta.b.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
/*		 */							 } catch (Exception exception) {
/* 268 */								 exception.printStackTrace();
/* 269 */								 continue;
/*		 */							 }
/*		 */ 
/* 272 */							 entityliving.setPositionRotation(f, f1, f2, random.nextFloat() * 360.0F, 0.0F);
/*		 */ 
/* 274 */							 world.addEntity(entityliving, CreatureSpawnEvent.SpawnReason.CHUNK_GEN);
/* 275 */							 a(entityliving, world, f, f1, f2);
/* 276 */							 flag = true;
/*		 */						 }
/*		 */ 
/* 279 */						 j1 += random.nextInt(5) - random.nextInt(5);
/*		 */ 
/* 281 */						 for (k1 += random.nextInt(5) - random.nextInt(5); (j1 < i) || (j1 >= i + k) || (k1 < j) || (k1 >= j + k); k1 = i2 + random.nextInt(5) - random.nextInt(5))
/* 282 */							 j1 = l1 + random.nextInt(5) - random.nextInt(5);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 private static class ChunkEntry extends EntryBase
/*		 */	 {
/*		 */		 public boolean spawn;
/*		 */ 
/*		 */		 public ChunkEntry(int x, int z, boolean spawn)
/*		 */		 {
/*	23 */			 super();
/*	24 */			 this.spawn = spawn;
/*		 */		 }
/*		 */ 
/*		 */		 int getX() {
/*	28 */			 return LongHash.msw(this.key);
/*		 */		 }
/*		 */ 
/*		 */		 int getZ() {
/*	32 */			 return LongHash.lsw(this.key);
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.SpawnerCreature
 * JD-Core Version:		0.6.0
 */