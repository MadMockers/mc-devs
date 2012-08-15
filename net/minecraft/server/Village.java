/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
/*		 */ 
/*		 */ public class Village
/*		 */ {
/*		 */	 private final World world;
/*	10 */	 private final List doors = new ArrayList();
/*	11 */	 private final ChunkCoordinates c = new ChunkCoordinates(0, 0, 0);
/*	12 */	 private final ChunkCoordinates center = new ChunkCoordinates(0, 0, 0);
/*	13 */	 private int size = 0;
/*	14 */	 private int f = 0;
/*	15 */	 private int time = 0;
/*	16 */	 private int population = 0;
/*	17 */	 private List i = new ArrayList();
/*	18 */	 private int j = 0;
/*		 */ 
/*		 */	 public Village(World world) {
/*	21 */		 this.world = world;
/*		 */	 }
/*		 */ 
/*		 */	 public void tick(int i) {
/*	25 */		 this.time = i;
/*	26 */		 k();
/*	27 */		 j();
/*	28 */		 if (i % 20 == 0) {
/*	29 */			 i();
/*		 */		 }
/*		 */ 
/*	32 */		 if (i % 30 == 0) {
/*	33 */			 countPopulation();
/*		 */		 }
/*		 */ 
/*	36 */		 int j = this.population / 16;
/*		 */ 
/*	38 */		 if ((this.j < j) && (this.doors.size() > 20) && (this.world.random.nextInt(7000) == 0)) {
/*	39 */			 Vec3D vec3d = a(MathHelper.d(this.center.x), MathHelper.d(this.center.y), MathHelper.d(this.center.z), 2, 4, 2);
/*		 */ 
/*	41 */			 if (vec3d != null) {
/*	42 */				 EntityIronGolem entityirongolem = new EntityIronGolem(this.world);
/*		 */ 
/*	44 */				 entityirongolem.setPosition(vec3d.a, vec3d.b, vec3d.c);
/*	45 */				 this.world.addEntity(entityirongolem, CreatureSpawnEvent.SpawnReason.VILLAGE_DEFENSE);
/*	46 */				 this.j += 1;
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private Vec3D a(int i, int j, int k, int l, int i1, int j1) {
/*	52 */		 for (int k1 = 0; k1 < 10; k1++) {
/*	53 */			 int l1 = i + this.world.random.nextInt(16) - 8;
/*	54 */			 int i2 = j + this.world.random.nextInt(6) - 3;
/*	55 */			 int j2 = k + this.world.random.nextInt(16) - 8;
/*		 */ 
/*	57 */			 if ((a(l1, i2, j2)) && (b(l1, i2, j2, l, i1, j1))) {
/*	58 */				 return Vec3D.a().create(l1, i2, j2);
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	62 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean b(int i, int j, int k, int l, int i1, int j1) {
/*	66 */		 if (!this.world.t(i, j - 1, k)) {
/*	67 */			 return false;
/*		 */		 }
/*	69 */		 int k1 = i - l / 2;
/*	70 */		 int l1 = k - j1 / 2;
/*		 */ 
/*	72 */		 for (int i2 = k1; i2 < k1 + l; i2++) {
/*	73 */			 for (int j2 = j; j2 < j + i1; j2++) {
/*	74 */				 for (int k2 = l1; k2 < l1 + j1; k2++) {
/*	75 */					 if (this.world.s(i2, j2, k2)) {
/*	76 */						 return false;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	82 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 private void countPopulation()
/*		 */	 {
/*	87 */		 List list = this.world.a(EntityIronGolem.class, AxisAlignedBB.a().a(this.center.x - this.size, this.center.y - 4, this.center.z - this.size, this.center.x + this.size, this.center.y + 4, this.center.z + this.size));
/*		 */ 
/*	89 */		 this.j = list.size();
/*		 */	 }
/*		 */ 
/*		 */	 private void i() {
/*	93 */		 List list = this.world.a(EntityVillager.class, AxisAlignedBB.a().a(this.center.x - this.size, this.center.y - 4, this.center.z - this.size, this.center.x + this.size, this.center.y + 4, this.center.z + this.size));
/*		 */ 
/*	95 */		 this.population = list.size();
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkCoordinates getCenter() {
/*	99 */		 return this.center;
/*		 */	 }
/*		 */ 
/*		 */	 public int getSize() {
/* 103 */		 return this.size;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDoorCount() {
/* 107 */		 return this.doors.size();
/*		 */	 }
/*		 */ 
/*		 */	 public int d() {
/* 111 */		 return this.time - this.f;
/*		 */	 }
/*		 */ 
/*		 */	 public int getPopulationCount() {
/* 115 */		 return this.population;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int i, int j, int k) {
/* 119 */		 return this.center.e(i, j, k) < this.size * this.size;
/*		 */	 }
/*		 */ 
/*		 */	 public List getDoors() {
/* 123 */		 return this.doors;
/*		 */	 }
/*		 */ 
/*		 */	 public VillageDoor b(int i, int j, int k) {
/* 127 */		 VillageDoor villagedoor = null;
/* 128 */		 int l = 2147483647;
/* 129 */		 Iterator iterator = this.doors.iterator();
/*		 */ 
/* 131 */		 while (iterator.hasNext()) {
/* 132 */			 VillageDoor villagedoor1 = (VillageDoor)iterator.next();
/* 133 */			 int i1 = villagedoor1.b(i, j, k);
/*		 */ 
/* 135 */			 if (i1 < l) {
/* 136 */				 villagedoor = villagedoor1;
/* 137 */				 l = i1;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 141 */		 return villagedoor;
/*		 */	 }
/*		 */ 
/*		 */	 public VillageDoor c(int i, int j, int k) {
/* 145 */		 VillageDoor villagedoor = null;
/* 146 */		 int l = 2147483647;
/* 147 */		 Iterator iterator = this.doors.iterator();
/*		 */ 
/* 149 */		 while (iterator.hasNext()) {
/* 150 */			 VillageDoor villagedoor1 = (VillageDoor)iterator.next();
/* 151 */			 int i1 = villagedoor1.b(i, j, k);
/*		 */ 
/* 153 */			 if (i1 > 256)
/* 154 */				 i1 *= 1000;
/*		 */			 else {
/* 156 */				 i1 = villagedoor1.f();
/*		 */			 }
/*		 */ 
/* 159 */			 if (i1 < l) {
/* 160 */				 villagedoor = villagedoor1;
/* 161 */				 l = i1;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 165 */		 return villagedoor;
/*		 */	 }
/*		 */ 
/*		 */	 public VillageDoor e(int i, int j, int k) {
/* 169 */		 if (this.center.e(i, j, k) > this.size * this.size) {
/* 170 */			 return null;
/*		 */		 }
/* 172 */		 Iterator iterator = this.doors.iterator();
/*		 */		 VillageDoor villagedoor;
/*		 */		 do {
/* 177 */			 if (!iterator.hasNext()) {
/* 178 */				 return null;
/*		 */			 }
/*		 */ 
/* 181 */			 villagedoor = (VillageDoor)iterator.next();
/* 182 */		 }while ((villagedoor.locX != i) || (villagedoor.locZ != k) || (Math.abs(villagedoor.locY - j) > 1));
/*		 */ 
/* 184 */		 return villagedoor;
/*		 */	 }
/*		 */ 
/*		 */	 public void addDoor(VillageDoor villagedoor)
/*		 */	 {
/* 189 */		 this.doors.add(villagedoor);
/* 190 */		 this.c.x += villagedoor.locX;
/* 191 */		 this.c.y += villagedoor.locY;
/* 192 */		 this.c.z += villagedoor.locZ;
/* 193 */		 l();
/* 194 */		 this.f = villagedoor.addedTime;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isAbandoned() {
/* 198 */		 return this.doors.isEmpty();
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityLiving entityliving) {
/* 202 */		 Iterator iterator = this.i.iterator();
/*		 */		 VillageAggressor villageaggressor;
/*		 */		 do {
/* 207 */			 if (!iterator.hasNext()) {
/* 208 */				 this.i.add(new VillageAggressor(this, entityliving, this.time));
/* 209 */				 return;
/*		 */			 }
/*		 */ 
/* 212 */			 villageaggressor = (VillageAggressor)iterator.next();
/* 213 */		 }while (villageaggressor.a != entityliving);
/*		 */ 
/* 215 */		 villageaggressor.b = this.time;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityLiving b(EntityLiving entityliving) {
/* 219 */		 double d0 = 1.7976931348623157E+308D;
/* 220 */		 VillageAggressor villageaggressor = null;
/* 221 */		 Iterator iterator = this.i.iterator();
/*		 */ 
/* 223 */		 while (iterator.hasNext()) {
/* 224 */			 VillageAggressor villageaggressor1 = (VillageAggressor)iterator.next();
/* 225 */			 double d1 = villageaggressor1.a.e(entityliving);
/*		 */ 
/* 227 */			 if (d1 <= d0) {
/* 228 */				 villageaggressor = villageaggressor1;
/* 229 */				 d0 = d1;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 233 */		 return villageaggressor != null ? villageaggressor.a : null;
/*		 */	 }
/*		 */ 
/*		 */	 private void j() {
/* 237 */		 Iterator iterator = this.i.iterator();
/*		 */ 
/* 239 */		 while (iterator.hasNext()) {
/* 240 */			 VillageAggressor villageaggressor = (VillageAggressor)iterator.next();
/*		 */ 
/* 242 */			 if ((!villageaggressor.a.isAlive()) || (Math.abs(this.time - villageaggressor.b) > 300))
/* 243 */				 iterator.remove();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void k()
/*		 */	 {
/* 249 */		 boolean flag = false;
/* 250 */		 boolean flag1 = this.world.random.nextInt(50) == 0;
/* 251 */		 Iterator iterator = this.doors.iterator();
/*		 */ 
/* 253 */		 while (iterator.hasNext()) {
/* 254 */			 VillageDoor villagedoor = (VillageDoor)iterator.next();
/*		 */ 
/* 256 */			 if (flag1) {
/* 257 */				 villagedoor.d();
/*		 */			 }
/*		 */ 
/* 260 */			 if ((!isDoor(villagedoor.locX, villagedoor.locY, villagedoor.locZ)) || (Math.abs(this.time - villagedoor.addedTime) > 1200)) {
/* 261 */				 this.c.x -= villagedoor.locX;
/* 262 */				 this.c.y -= villagedoor.locY;
/* 263 */				 this.c.z -= villagedoor.locZ;
/* 264 */				 flag = true;
/* 265 */				 villagedoor.g = true;
/* 266 */				 iterator.remove();
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 270 */		 if (flag)
/* 271 */			 l();
/*		 */	 }
/*		 */ 
/*		 */	 private boolean isDoor(int i, int j, int k)
/*		 */	 {
/* 276 */		 int l = this.world.getTypeId(i, j, k);
/*		 */ 
/* 278 */		 return l > 0;
/*		 */	 }
/*		 */ 
/*		 */	 private void l() {
/* 282 */		 int i = this.doors.size();
/*		 */ 
/* 284 */		 if (i == 0) {
/* 285 */			 this.center.b(0, 0, 0);
/* 286 */			 this.size = 0;
/*		 */		 } else {
/* 288 */			 this.center.b(this.c.x / i, this.c.y / i, this.c.z / i);
/* 289 */			 int j = 0;
/*		 */			 VillageDoor villagedoor;
/* 293 */			 for (Iterator iterator = this.doors.iterator(); iterator.hasNext(); j = Math.max(villagedoor.b(this.center.x, this.center.y, this.center.z), j)) {
/* 294 */				 villagedoor = (VillageDoor)iterator.next();
/*		 */			 }
/*		 */ 
/* 297 */			 this.size = Math.max(32, (int)Math.sqrt(j) + 1);
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Village
 * JD-Core Version:		0.6.0
 */