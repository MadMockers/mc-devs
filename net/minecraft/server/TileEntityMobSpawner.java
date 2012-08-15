/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Collection;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
/*		 */ 
/*		 */ public class TileEntityMobSpawner extends TileEntity
/*		 */ {
/*	 9 */	 public int spawnDelay = -1;
/*	10 */	 public String mobName = "Pig";
/*	11 */	 private NBTTagCompound spawnData = null;
/*		 */	 public double b;
/*	13 */	 public double c = 0.0D;
/*	14 */	 private int minSpawnDelay = 200;
/*	15 */	 private int maxSpawnDelay = 800;
/*	16 */	 private int spawnCount = 4;
/*		 */ 
/*		 */	 public TileEntityMobSpawner() {
/*	19 */		 this.spawnDelay = 20;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String s) {
/*	23 */		 this.mobName = s;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b() {
/*	27 */		 return this.world.findNearbyPlayer(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D, 16.0D) != null;
/*		 */	 }
/*		 */ 
/*		 */	 public void g() {
/*	31 */		 if (b()) {
/*	32 */			 if (this.world.isStatic) {
/*	33 */				 double d0 = this.x + this.world.random.nextFloat();
/*	34 */				 double d1 = this.y + this.world.random.nextFloat();
/*	35 */				 double d2 = this.z + this.world.random.nextFloat();
/*		 */ 
/*	37 */				 this.world.a("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*	38 */				 this.world.a("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*	39 */				 this.c = (this.b % 360.0D);
/*	40 */				 this.b += 4.545454502105713D;
/*		 */			 } else {
/*	42 */				 if (this.spawnDelay == -1) {
/*	43 */					 f();
/*		 */				 }
/*		 */ 
/*	46 */				 if (this.spawnDelay > 0) {
/*	47 */					 this.spawnDelay -= 1;
/*	48 */					 return;
/*		 */				 }
/*		 */ 
/*	51 */				 for (int i = 0; i < this.spawnCount; i++) {
/*	52 */					 Entity entity = EntityTypes.createEntityByName(this.mobName, this.world);
/*		 */ 
/*	54 */					 if (entity == null) {
/*	55 */						 return;
/*		 */					 }
/*		 */ 
/*	58 */					 int j = this.world.a(entity.getClass(), AxisAlignedBB.a().a(this.x, this.y, this.z, this.x + 1, this.y + 1, this.z + 1).grow(8.0D, 4.0D, 8.0D)).size();
/*		 */ 
/*	60 */					 if (j >= 6) {
/*	61 */						 f();
/*	62 */						 return;
/*		 */					 }
/*		 */ 
/*	65 */					 if (entity != null) {
/*	66 */						 double d3 = this.x + (this.world.random.nextDouble() - this.world.random.nextDouble()) * 4.0D;
/*	67 */						 double d4 = this.y + this.world.random.nextInt(3) - 1;
/*	68 */						 double d5 = this.z + (this.world.random.nextDouble() - this.world.random.nextDouble()) * 4.0D;
/*	69 */						 EntityLiving entityliving = (entity instanceof EntityLiving) ? (EntityLiving)entity : null;
/*		 */ 
/*	71 */						 entity.setPositionRotation(d3, d4, d5, this.world.random.nextFloat() * 360.0F, 0.0F);
/*	72 */						 if ((entityliving == null) || (entityliving.canSpawn())) {
/*	73 */							 a(entity);
/*	74 */							 this.world.addEntity(entity, CreatureSpawnEvent.SpawnReason.SPAWNER);
/*	75 */							 this.world.triggerEffect(2004, this.x, this.y, this.z, 0);
/*	76 */							 if (entityliving != null) {
/*	77 */								 entityliving.aK();
/*		 */							 }
/*		 */ 
/*	80 */							 f();
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	86 */			 super.g();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Entity entity) {
/*	91 */		 if (this.spawnData != null) {
/*	92 */			 NBTTagCompound nbttagcompound = new NBTTagCompound();
/*		 */ 
/*	94 */			 entity.c(nbttagcompound);
/*	95 */			 Iterator iterator = this.spawnData.c().iterator();
/*		 */ 
/*	97 */			 while (iterator.hasNext()) {
/*	98 */				 NBTBase nbtbase = (NBTBase)iterator.next();
/*		 */ 
/* 100 */				 nbttagcompound.set(nbtbase.getName(), nbtbase.clone());
/*		 */			 }
/*		 */ 
/* 103 */			 entity.e(nbttagcompound);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void f() {
/* 108 */		 this.spawnDelay = (this.minSpawnDelay + this.world.random.nextInt(this.maxSpawnDelay - this.minSpawnDelay));
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/* 112 */		 super.a(nbttagcompound);
/* 113 */		 this.mobName = nbttagcompound.getString("EntityId");
/* 114 */		 this.spawnDelay = nbttagcompound.getShort("Delay");
/* 115 */		 if (nbttagcompound.hasKey("SpawnData"))
/* 116 */			 this.spawnData = nbttagcompound.getCompound("SpawnData");
/*		 */		 else {
/* 118 */			 this.spawnData = null;
/*		 */		 }
/*		 */ 
/* 121 */		 if (nbttagcompound.hasKey("MinSpawnDelay")) {
/* 122 */			 this.minSpawnDelay = nbttagcompound.getShort("MinSpawnDelay");
/* 123 */			 this.maxSpawnDelay = nbttagcompound.getShort("MaxSpawnDelay");
/* 124 */			 this.spawnCount = nbttagcompound.getShort("SpawnCount");
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/* 129 */		 super.b(nbttagcompound);
/* 130 */		 nbttagcompound.setString("EntityId", this.mobName);
/* 131 */		 nbttagcompound.setShort("Delay", (short)this.spawnDelay);
/* 132 */		 nbttagcompound.setShort("MinSpawnDelay", (short)this.minSpawnDelay);
/* 133 */		 nbttagcompound.setShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
/* 134 */		 nbttagcompound.setShort("SpawnCount", (short)this.spawnCount);
/* 135 */		 if (this.spawnData != null)
/* 136 */			 nbttagcompound.setCompound("SpawnData", this.spawnData);
/*		 */	 }
/*		 */ 
/*		 */	 public Packet e()
/*		 */	 {
/* 141 */		 NBTTagCompound nbttagcompound = new NBTTagCompound();
/*		 */ 
/* 143 */		 b(nbttagcompound);
/* 144 */		 return new Packet132TileEntityData(this.x, this.y, this.z, 1, nbttagcompound);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.TileEntityMobSpawner
 * JD-Core Version:		0.6.0
 */