/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ 
/*		 */ class MinecartTrackLogic
/*		 */ {
/*		 */	 private World b;
/*		 */	 private int c;
/*		 */	 private int d;
/*		 */	 private int e;
/*		 */	 private final boolean f;
/*		 */	 private List g;
/*		 */	 final BlockMinecartTrack a;
/*		 */ 
/*		 */	 public MinecartTrackLogic(BlockMinecartTrack blockminecarttrack, World world, int i, int j, int k)
/*		 */	 {
/*	20 */		 this.a = blockminecarttrack;
/*	21 */		 this.g = new ArrayList();
/*	22 */		 this.b = world;
/*	23 */		 this.c = i;
/*	24 */		 this.d = j;
/*	25 */		 this.e = k;
/*	26 */		 int l = world.getTypeId(i, j, k);
/*	27 */		 int i1 = world.getData(i, j, k);
/*		 */ 
/*	29 */		 if (BlockMinecartTrack.a((BlockMinecartTrack)Block.byId[l])) {
/*	30 */			 this.f = true;
/*	31 */			 i1 &= -9;
/*		 */		 } else {
/*	33 */			 this.f = false;
/*		 */		 }
/*		 */ 
/*	36 */		 a(i1);
/*		 */	 }
/*		 */ 
/*		 */	 private void a(int i) {
/*	40 */		 this.g.clear();
/*	41 */		 if (i == 0) {
/*	42 */			 this.g.add(new ChunkPosition(this.c, this.d, this.e - 1));
/*	43 */			 this.g.add(new ChunkPosition(this.c, this.d, this.e + 1));
/*	44 */		 } else if (i == 1) {
/*	45 */			 this.g.add(new ChunkPosition(this.c - 1, this.d, this.e));
/*	46 */			 this.g.add(new ChunkPosition(this.c + 1, this.d, this.e));
/*	47 */		 } else if (i == 2) {
/*	48 */			 this.g.add(new ChunkPosition(this.c - 1, this.d, this.e));
/*	49 */			 this.g.add(new ChunkPosition(this.c + 1, this.d + 1, this.e));
/*	50 */		 } else if (i == 3) {
/*	51 */			 this.g.add(new ChunkPosition(this.c - 1, this.d + 1, this.e));
/*	52 */			 this.g.add(new ChunkPosition(this.c + 1, this.d, this.e));
/*	53 */		 } else if (i == 4) {
/*	54 */			 this.g.add(new ChunkPosition(this.c, this.d + 1, this.e - 1));
/*	55 */			 this.g.add(new ChunkPosition(this.c, this.d, this.e + 1));
/*	56 */		 } else if (i == 5) {
/*	57 */			 this.g.add(new ChunkPosition(this.c, this.d, this.e - 1));
/*	58 */			 this.g.add(new ChunkPosition(this.c, this.d + 1, this.e + 1));
/*	59 */		 } else if (i == 6) {
/*	60 */			 this.g.add(new ChunkPosition(this.c + 1, this.d, this.e));
/*	61 */			 this.g.add(new ChunkPosition(this.c, this.d, this.e + 1));
/*	62 */		 } else if (i == 7) {
/*	63 */			 this.g.add(new ChunkPosition(this.c - 1, this.d, this.e));
/*	64 */			 this.g.add(new ChunkPosition(this.c, this.d, this.e + 1));
/*	65 */		 } else if (i == 8) {
/*	66 */			 this.g.add(new ChunkPosition(this.c - 1, this.d, this.e));
/*	67 */			 this.g.add(new ChunkPosition(this.c, this.d, this.e - 1));
/*	68 */		 } else if (i == 9) {
/*	69 */			 this.g.add(new ChunkPosition(this.c + 1, this.d, this.e));
/*	70 */			 this.g.add(new ChunkPosition(this.c, this.d, this.e - 1));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void a() {
/*	75 */		 for (int i = 0; i < this.g.size(); i++) {
/*	76 */			 MinecartTrackLogic minecarttracklogic = a((ChunkPosition)this.g.get(i));
/*		 */ 
/*	78 */			 if ((minecarttracklogic != null) && (minecarttracklogic.b(this)))
/*	79 */				 this.g.set(i, new ChunkPosition(minecarttracklogic.c, minecarttracklogic.d, minecarttracklogic.e));
/*		 */			 else
/*	81 */				 this.g.remove(i--);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(int i, int j, int k)
/*		 */	 {
/*	87 */		 return BlockMinecartTrack.d_(this.b, i, j + 1, k) ? true : BlockMinecartTrack.d_(this.b, i, j, k) ? true : BlockMinecartTrack.d_(this.b, i, j - 1, k);
/*		 */	 }
/*		 */ 
/*		 */	 private MinecartTrackLogic a(ChunkPosition chunkposition) {
/*	91 */		 return BlockMinecartTrack.d_(this.b, chunkposition.x, chunkposition.y - 1, chunkposition.z) ? new MinecartTrackLogic(this.a, this.b, chunkposition.x, chunkposition.y - 1, chunkposition.z) : BlockMinecartTrack.d_(this.b, chunkposition.x, chunkposition.y + 1, chunkposition.z) ? new MinecartTrackLogic(this.a, this.b, chunkposition.x, chunkposition.y + 1, chunkposition.z) : BlockMinecartTrack.d_(this.b, chunkposition.x, chunkposition.y, chunkposition.z) ? new MinecartTrackLogic(this.a, this.b, chunkposition.x, chunkposition.y, chunkposition.z) : null;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean b(MinecartTrackLogic minecarttracklogic) {
/*	95 */		 Iterator iterator = this.g.iterator();
/*		 */		 ChunkPosition chunkposition;
/*		 */		 do {
/* 100 */			 if (!iterator.hasNext()) {
/* 101 */				 return false;
/*		 */			 }
/*		 */ 
/* 104 */			 chunkposition = (ChunkPosition)iterator.next();
/* 105 */		 }while ((chunkposition.x != minecarttracklogic.c) || (chunkposition.z != minecarttracklogic.e));
/*		 */ 
/* 107 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean b(int i, int j, int k) {
/* 111 */		 Iterator iterator = this.g.iterator();
/*		 */		 ChunkPosition chunkposition;
/*		 */		 do {
/* 116 */			 if (!iterator.hasNext()) {
/* 117 */				 return false;
/*		 */			 }
/*		 */ 
/* 120 */			 chunkposition = (ChunkPosition)iterator.next();
/* 121 */		 }while ((chunkposition.x != i) || (chunkposition.z != k));
/*		 */ 
/* 123 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 private int b() {
/* 127 */		 int i = 0;
/*		 */ 
/* 129 */		 if (a(this.c, this.d, this.e - 1)) {
/* 130 */			 i++;
/*		 */		 }
/*		 */ 
/* 133 */		 if (a(this.c, this.d, this.e + 1)) {
/* 134 */			 i++;
/*		 */		 }
/*		 */ 
/* 137 */		 if (a(this.c - 1, this.d, this.e)) {
/* 138 */			 i++;
/*		 */		 }
/*		 */ 
/* 141 */		 if (a(this.c + 1, this.d, this.e)) {
/* 142 */			 i++;
/*		 */		 }
/*		 */ 
/* 145 */		 return i;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean c(MinecartTrackLogic minecarttracklogic) {
/* 149 */		 if (b(minecarttracklogic))
/* 150 */			 return true;
/* 151 */		 if (this.g.size() == 2)
/* 152 */			 return false;
/* 153 */		 if (this.g.isEmpty()) {
/* 154 */			 return true;
/*		 */		 }
/* 156 */		 ChunkPosition chunkposition = (ChunkPosition)this.g.get(0);
/*		 */ 
/* 158 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 private void d(MinecartTrackLogic minecarttracklogic)
/*		 */	 {
/* 163 */		 this.g.add(new ChunkPosition(minecarttracklogic.c, minecarttracklogic.d, minecarttracklogic.e));
/* 164 */		 boolean flag = b(this.c, this.d, this.e - 1);
/* 165 */		 boolean flag1 = b(this.c, this.d, this.e + 1);
/* 166 */		 boolean flag2 = b(this.c - 1, this.d, this.e);
/* 167 */		 boolean flag3 = b(this.c + 1, this.d, this.e);
/* 168 */		 byte b0 = -1;
/*		 */ 
/* 170 */		 if ((flag) || (flag1)) {
/* 171 */			 b0 = 0;
/*		 */		 }
/*		 */ 
/* 174 */		 if ((flag2) || (flag3)) {
/* 175 */			 b0 = 1;
/*		 */		 }
/*		 */ 
/* 178 */		 if (!this.f) {
/* 179 */			 if ((flag1) && (flag3) && (!flag) && (!flag2)) {
/* 180 */				 b0 = 6;
/*		 */			 }
/*		 */ 
/* 183 */			 if ((flag1) && (flag2) && (!flag) && (!flag3)) {
/* 184 */				 b0 = 7;
/*		 */			 }
/*		 */ 
/* 187 */			 if ((flag) && (flag2) && (!flag1) && (!flag3)) {
/* 188 */				 b0 = 8;
/*		 */			 }
/*		 */ 
/* 191 */			 if ((flag) && (flag3) && (!flag1) && (!flag2)) {
/* 192 */				 b0 = 9;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 196 */		 if (b0 == 0) {
/* 197 */			 if (BlockMinecartTrack.d_(this.b, this.c, this.d + 1, this.e - 1)) {
/* 198 */				 b0 = 4;
/*		 */			 }
/*		 */ 
/* 201 */			 if (BlockMinecartTrack.d_(this.b, this.c, this.d + 1, this.e + 1)) {
/* 202 */				 b0 = 5;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 206 */		 if (b0 == 1) {
/* 207 */			 if (BlockMinecartTrack.d_(this.b, this.c + 1, this.d + 1, this.e)) {
/* 208 */				 b0 = 2;
/*		 */			 }
/*		 */ 
/* 211 */			 if (BlockMinecartTrack.d_(this.b, this.c - 1, this.d + 1, this.e)) {
/* 212 */				 b0 = 3;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 216 */		 if (b0 < 0) {
/* 217 */			 b0 = 0;
/*		 */		 }
/*		 */ 
/* 220 */		 int i = b0;
/*		 */ 
/* 222 */		 if (this.f) {
/* 223 */			 i = this.b.getData(this.c, this.d, this.e) & 0x8 | b0;
/*		 */		 }
/*		 */ 
/* 226 */		 this.b.setData(this.c, this.d, this.e, i);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean c(int i, int j, int k) {
/* 230 */		 MinecartTrackLogic minecarttracklogic = a(new ChunkPosition(i, j, k));
/*		 */ 
/* 232 */		 if (minecarttracklogic == null) {
/* 233 */			 return false;
/*		 */		 }
/* 235 */		 minecarttracklogic.a();
/* 236 */		 return minecarttracklogic.c(this);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(boolean flag, boolean flag1)
/*		 */	 {
/* 241 */		 boolean flag2 = c(this.c, this.d, this.e - 1);
/* 242 */		 boolean flag3 = c(this.c, this.d, this.e + 1);
/* 243 */		 boolean flag4 = c(this.c - 1, this.d, this.e);
/* 244 */		 boolean flag5 = c(this.c + 1, this.d, this.e);
/* 245 */		 byte b0 = -1;
/*		 */ 
/* 247 */		 if (((flag2) || (flag3)) && (!flag4) && (!flag5)) {
/* 248 */			 b0 = 0;
/*		 */		 }
/*		 */ 
/* 251 */		 if (((flag4) || (flag5)) && (!flag2) && (!flag3)) {
/* 252 */			 b0 = 1;
/*		 */		 }
/*		 */ 
/* 255 */		 if (!this.f) {
/* 256 */			 if ((flag3) && (flag5) && (!flag2) && (!flag4)) {
/* 257 */				 b0 = 6;
/*		 */			 }
/*		 */ 
/* 260 */			 if ((flag3) && (flag4) && (!flag2) && (!flag5)) {
/* 261 */				 b0 = 7;
/*		 */			 }
/*		 */ 
/* 264 */			 if ((flag2) && (flag4) && (!flag3) && (!flag5)) {
/* 265 */				 b0 = 8;
/*		 */			 }
/*		 */ 
/* 268 */			 if ((flag2) && (flag5) && (!flag3) && (!flag4)) {
/* 269 */				 b0 = 9;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 273 */		 if (b0 == -1) {
/* 274 */			 if ((flag2) || (flag3)) {
/* 275 */				 b0 = 0;
/*		 */			 }
/*		 */ 
/* 278 */			 if ((flag4) || (flag5)) {
/* 279 */				 b0 = 1;
/*		 */			 }
/*		 */ 
/* 282 */			 if (!this.f) {
/* 283 */				 if (flag) {
/* 284 */					 if ((flag3) && (flag5)) {
/* 285 */						 b0 = 6;
/*		 */					 }
/*		 */ 
/* 288 */					 if ((flag4) && (flag3)) {
/* 289 */						 b0 = 7;
/*		 */					 }
/*		 */ 
/* 292 */					 if ((flag5) && (flag2)) {
/* 293 */						 b0 = 9;
/*		 */					 }
/*		 */ 
/* 296 */					 if ((flag2) && (flag4))
/* 297 */						 b0 = 8;
/*		 */				 }
/*		 */				 else {
/* 300 */					 if ((flag2) && (flag4)) {
/* 301 */						 b0 = 8;
/*		 */					 }
/*		 */ 
/* 304 */					 if ((flag5) && (flag2)) {
/* 305 */						 b0 = 9;
/*		 */					 }
/*		 */ 
/* 308 */					 if ((flag4) && (flag3)) {
/* 309 */						 b0 = 7;
/*		 */					 }
/*		 */ 
/* 312 */					 if ((flag3) && (flag5)) {
/* 313 */						 b0 = 6;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 319 */		 if (b0 == 0) {
/* 320 */			 if (BlockMinecartTrack.d_(this.b, this.c, this.d + 1, this.e - 1)) {
/* 321 */				 b0 = 4;
/*		 */			 }
/*		 */ 
/* 324 */			 if (BlockMinecartTrack.d_(this.b, this.c, this.d + 1, this.e + 1)) {
/* 325 */				 b0 = 5;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 329 */		 if (b0 == 1) {
/* 330 */			 if (BlockMinecartTrack.d_(this.b, this.c + 1, this.d + 1, this.e)) {
/* 331 */				 b0 = 2;
/*		 */			 }
/*		 */ 
/* 334 */			 if (BlockMinecartTrack.d_(this.b, this.c - 1, this.d + 1, this.e)) {
/* 335 */				 b0 = 3;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 339 */		 if (b0 < 0) {
/* 340 */			 b0 = 0;
/*		 */		 }
/*		 */ 
/* 343 */		 a(b0);
/* 344 */		 int i = b0;
/*		 */ 
/* 346 */		 if (this.f) {
/* 347 */			 i = this.b.getData(this.c, this.d, this.e) & 0x8 | b0;
/*		 */		 }
/*		 */ 
/* 350 */		 if ((flag1) || (this.b.getData(this.c, this.d, this.e) != i)) {
/* 351 */			 this.b.setData(this.c, this.d, this.e, i);
/* 352 */			 Iterator iterator = this.g.iterator();
/*		 */ 
/* 354 */			 while (iterator.hasNext()) {
/* 355 */				 ChunkPosition chunkposition = (ChunkPosition)iterator.next();
/* 356 */				 MinecartTrackLogic minecarttracklogic = a(chunkposition);
/*		 */ 
/* 358 */				 if (minecarttracklogic != null) {
/* 359 */					 minecarttracklogic.a();
/* 360 */					 if (minecarttracklogic.c(this))
/* 361 */						 minecarttracklogic.d(this);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 static int a(MinecartTrackLogic minecarttracklogic)
/*		 */	 {
/* 369 */		 return minecarttracklogic.b();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.MinecartTrackLogic
 * JD-Core Version:		0.6.0
 */