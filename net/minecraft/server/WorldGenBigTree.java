/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.BlockChangeDelegate;
/*		 */ 
/*		 */ public class WorldGenBigTree extends WorldGenerator
/*		 */	 implements BlockSapling.TreeGenerator
/*		 */ {
/*	 9 */	 static final byte[] a = { 2, 0, 0, 1, 2, 1 };
/*	10 */	 Random b = new Random();
/*		 */	 BlockChangeDelegate world;
/*	12 */	 int[] d = { 0, 0, 0 };
/*	13 */	 int e = 0;
/*		 */	 int f;
/*	15 */	 double g = 0.618D;
/*	16 */	 double h = 1.0D;
/*	17 */	 double i = 0.381D;
/*	18 */	 double j = 1.0D;
/*	19 */	 double k = 1.0D;
/*	20 */	 int l = 1;
/*	21 */	 int m = 12;
/*	22 */	 int n = 4;
/*		 */	 int[][] o;
/*		 */ 
/*		 */	 public WorldGenBigTree(boolean flag)
/*		 */	 {
/*	26 */		 super(flag);
/*		 */	 }
/*		 */ 
/*		 */	 void a() {
/*	30 */		 this.f = (int)(this.e * this.g);
/*	31 */		 if (this.f >= this.e) {
/*	32 */			 this.f = (this.e - 1);
/*		 */		 }
/*		 */ 
/*	35 */		 int i = (int)(1.382D + Math.pow(this.k * this.e / 13.0D, 2.0D));
/*		 */ 
/*	37 */		 if (i < 1) {
/*	38 */			 i = 1;
/*		 */		 }
/*		 */ 
/*	41 */		 int[][] aint = new int[i * this.e][4];
/*	42 */		 int j = this.d[1] + this.e - this.n;
/*	43 */		 int k = 1;
/*	44 */		 int l = this.d[1] + this.f;
/*	45 */		 int i1 = j - this.d[1];
/*		 */ 
/*	47 */		 aint[0][0] = this.d[0];
/*	48 */		 aint[0][1] = j;
/*	49 */		 aint[0][2] = this.d[2];
/*	50 */		 aint[0][3] = l;
/*	51 */		 j--;
/*		 */ 
/*	53 */		 while (i1 >= 0) {
/*	54 */			 int j1 = 0;
/*	55 */			 float f = a(i1);
/*		 */ 
/*	57 */			 if (f < 0.0F) {
/*	58 */				 j--;
/*	59 */				 i1--;
/*		 */			 } else {
/*	61 */				 for (double d0 = 0.5D; j1 < i; j1++) {
/*	62 */					 double d1 = this.j * f * (this.b.nextFloat() + 0.328D);
/*	63 */					 double d2 = this.b.nextFloat() * 2.0D * 3.14159D;
/*	64 */					 int k1 = MathHelper.floor(d1 * Math.sin(d2) + this.d[0] + d0);
/*	65 */					 int l1 = MathHelper.floor(d1 * Math.cos(d2) + this.d[2] + d0);
/*	66 */					 int[] aint1 = { k1, j, l1 };
/*	67 */					 int[] aint2 = { k1, j + this.n, l1 };
/*		 */ 
/*	69 */					 if (a(aint1, aint2) == -1) {
/*	70 */						 int[] aint3 = { this.d[0], this.d[1], this.d[2] };
/*	71 */						 double d3 = Math.sqrt(Math.pow(Math.abs(this.d[0] - aint1[0]), 2.0D) + Math.pow(Math.abs(this.d[2] - aint1[2]), 2.0D));
/*	72 */						 double d4 = d3 * this.i;
/*		 */ 
/*	74 */						 if (aint1[1] - d4 > l)
/*	75 */							 aint3[1] = l;
/*		 */						 else {
/*	77 */							 aint3[1] = (int)(aint1[1] - d4);
/*		 */						 }
/*		 */ 
/*	80 */						 if (a(aint3, aint1) == -1) {
/*	81 */							 aint[k][0] = k1;
/*	82 */							 aint[k][1] = j;
/*	83 */							 aint[k][2] = l1;
/*	84 */							 aint[k][3] = aint3[1];
/*	85 */							 k++;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/*	90 */				 j--;
/*	91 */				 i1--;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	95 */		 this.o = new int[k][4];
/*	96 */		 System.arraycopy(aint, 0, this.o, 0, k);
/*		 */	 }
/*		 */ 
/*		 */	 void a(int i, int j, int k, float f, byte b0, int l) {
/* 100 */		 int i1 = (int)(f + 0.618D);
/* 101 */		 byte b1 = a[b0];
/* 102 */		 byte b2 = a[(b0 + 3)];
/* 103 */		 int[] aint = { i, j, k };
/* 104 */		 int[] aint1 = { 0, 0, 0 };
/* 105 */		 int j1 = -i1;
/* 106 */		 int k1 = -i1;
/*		 */ 
/* 108 */		 for (aint1[b0] = aint[b0]; j1 <= i1; j1++) {
/* 109 */			 aint[b1] += j1;
/* 110 */			 k1 = -i1;
/*		 */ 
/* 112 */			 while (k1 <= i1) {
/* 113 */				 double d0 = Math.pow(Math.abs(j1) + 0.5D, 2.0D) + Math.pow(Math.abs(k1) + 0.5D, 2.0D);
/*		 */ 
/* 115 */				 if (d0 > f * f) {
/* 116 */					 k1++;
/*		 */				 } else {
/* 118 */					 aint[b2] += k1;
/* 119 */					 int l1 = this.world.getTypeId(aint1[0], aint1[1], aint1[2]);
/*		 */ 
/* 121 */					 if ((l1 != 0) && (l1 != 18)) {
/* 122 */						 k1++;
/*		 */					 } else {
/* 124 */						 setTypeAndData(this.world, aint1[0], aint1[1], aint1[2], l, 0);
/* 125 */						 k1++;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 float a(int i) {
/* 133 */		 if (i < this.e * 0.3D) {
/* 134 */			 return -1.618F;
/*		 */		 }
/* 136 */		 float f = this.e / 2.0F;
/* 137 */		 float f1 = this.e / 2.0F - i;
/*		 */		 float f2;
/*		 */		 float f2;
/* 140 */		 if (f1 == 0.0F) {
/* 141 */			 f2 = f;
/*		 */		 }
/*		 */		 else
/*		 */		 {
/*		 */			 float f2;
/* 142 */			 if (Math.abs(f1) >= f)
/* 143 */				 f2 = 0.0F;
/*		 */			 else {
/* 145 */				 f2 = (float)Math.sqrt(Math.pow(Math.abs(f), 2.0D) - Math.pow(Math.abs(f1), 2.0D));
/*		 */			 }
/*		 */		 }
/* 148 */		 f2 *= 0.5F;
/* 149 */		 return f2;
/*		 */	 }
/*		 */ 
/*		 */	 float b(int i)
/*		 */	 {
/* 154 */		 return (i >= 0) && (i < this.n) ? 2.0F : (i != 0) && (i != this.n - 1) ? 3.0F : -1.0F;
/*		 */	 }
/*		 */ 
/*		 */	 void a(int i, int j, int k) {
/* 158 */		 int l = j;
/*		 */ 
/* 160 */		 for (int i1 = j + this.n; l < i1; l++) {
/* 161 */			 float f = b(l - j);
/*		 */ 
/* 163 */			 a(i, l, k, f, 1, 18);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 void a(int[] aint, int[] aint1, int i) {
/* 168 */		 int[] aint2 = { 0, 0, 0 };
/* 169 */		 byte b0 = 0;
/*		 */ 
/* 173 */		 for (byte b1 = 0; b0 < 3; b0 = (byte)(b0 + 1)) {
/* 174 */			 aint1[b0] -= aint[b0];
/* 175 */			 if (Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
/* 176 */				 b1 = b0;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 180 */		 if (aint2[b1] != 0) {
/* 181 */			 byte b2 = a[b1];
/* 182 */			 byte b3 = a[(b1 + 3)];
/*		 */			 byte b4;
/*		 */			 byte b4;
/* 185 */			 if (aint2[b1] > 0)
/* 186 */				 b4 = 1;
/*		 */			 else {
/* 188 */				 b4 = -1;
/*		 */			 }
/*		 */ 
/* 191 */			 double d0 = aint2[b2] / aint2[b1];
/* 192 */			 double d1 = aint2[b3] / aint2[b1];
/* 193 */			 int[] aint3 = { 0, 0, 0 };
/* 194 */			 int j = 0;
/*		 */ 
/* 196 */			 for (int k = aint2[b1] + b4; j != k; j += b4) {
/* 197 */				 aint3[b1] = MathHelper.floor(aint[b1] + j + 0.5D);
/* 198 */				 aint3[b2] = MathHelper.floor(aint[b2] + j * d0 + 0.5D);
/* 199 */				 aint3[b3] = MathHelper.floor(aint[b3] + j * d1 + 0.5D);
/* 200 */				 setTypeAndData(this.world, aint3[0], aint3[1], aint3[2], i, 0);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 void b() {
/* 206 */		 int i = 0;
/*		 */ 
/* 208 */		 for (int j = this.o.length; i < j; i++) {
/* 209 */			 int k = this.o[i][0];
/* 210 */			 int l = this.o[i][1];
/* 211 */			 int i1 = this.o[i][2];
/*		 */ 
/* 213 */			 a(k, l, i1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 boolean c(int i) {
/* 218 */		 return i >= this.e * 0.2D;
/*		 */	 }
/*		 */ 
/*		 */	 void c() {
/* 222 */		 int i = this.d[0];
/* 223 */		 int j = this.d[1];
/* 224 */		 int k = this.d[1] + this.f;
/* 225 */		 int l = this.d[2];
/* 226 */		 int[] aint = { i, j, l };
/* 227 */		 int[] aint1 = { i, k, l };
/*		 */ 
/* 229 */		 a(aint, aint1, 17);
/* 230 */		 if (this.l == 2) {
/* 231 */			 aint[0] += 1;
/* 232 */			 aint1[0] += 1;
/* 233 */			 a(aint, aint1, 17);
/* 234 */			 aint[2] += 1;
/* 235 */			 aint1[2] += 1;
/* 236 */			 a(aint, aint1, 17);
/* 237 */			 aint[0] += -1;
/* 238 */			 aint1[0] += -1;
/* 239 */			 a(aint, aint1, 17);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 void d() {
/* 244 */		 int i = 0;
/* 245 */		 int j = this.o.length;
/*		 */ 
/* 247 */		 for (int[] aint = { this.d[0], this.d[1], this.d[2] }; i < j; i++) {
/* 248 */			 int[] aint1 = this.o[i];
/* 249 */			 int[] aint2 = { aint1[0], aint1[1], aint1[2] };
/*		 */ 
/* 251 */			 aint[1] = aint1[3];
/* 252 */			 int k = aint[1] - this.d[1];
/*		 */ 
/* 254 */			 if (c(k))
/* 255 */				 a(aint, aint2, 17);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 int a(int[] aint, int[] aint1)
/*		 */	 {
/* 261 */		 int[] aint2 = { 0, 0, 0 };
/* 262 */		 byte b0 = 0;
/*		 */ 
/* 266 */		 for (byte b1 = 0; b0 < 3; b0 = (byte)(b0 + 1)) {
/* 267 */			 aint1[b0] -= aint[b0];
/* 268 */			 if (Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
/* 269 */				 b1 = b0;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 273 */		 if (aint2[b1] == 0) {
/* 274 */			 return -1;
/*		 */		 }
/* 276 */		 byte b2 = a[b1];
/* 277 */		 byte b3 = a[(b1 + 3)];
/*		 */		 byte b4;
/*		 */		 byte b4;
/* 280 */		 if (aint2[b1] > 0)
/* 281 */			 b4 = 1;
/*		 */		 else {
/* 283 */			 b4 = -1;
/*		 */		 }
/*		 */ 
/* 286 */		 double d0 = aint2[b2] / aint2[b1];
/* 287 */		 double d1 = aint2[b3] / aint2[b1];
/* 288 */		 int[] aint3 = { 0, 0, 0 };
/* 289 */		 int i = 0;
/*		 */ 
/* 293 */		 for (int j = aint2[b1] + b4; i != j; i += b4) {
/* 294 */			 aint[b1] += i;
/* 295 */			 aint3[b2] = MathHelper.floor(aint[b2] + i * d0);
/* 296 */			 aint3[b3] = MathHelper.floor(aint[b3] + i * d1);
/* 297 */			 int k = this.world.getTypeId(aint3[0], aint3[1], aint3[2]);
/*		 */ 
/* 299 */			 if (((k != 0) && (k != 18)) || (aint3[1] >= 256))
/*		 */			 {
/*		 */				 break;
/*		 */			 }
/*		 */		 }
/* 304 */		 return i == j ? -1 : Math.abs(i);
/*		 */	 }
/*		 */ 
/*		 */	 boolean e()
/*		 */	 {
/* 309 */		 int[] aint = { this.d[0], this.d[1], this.d[2] };
/* 310 */		 int[] aint1 = { this.d[0], this.d[1] + this.e - 1, this.d[2] };
/* 311 */		 int i = this.world.getTypeId(this.d[0], this.d[1] - 1, this.d[2]);
/*		 */ 
/* 313 */		 if ((i != 2) && (i != 3)) {
/* 314 */			 return false;
/*		 */		 }
/* 316 */		 int j = a(aint, aint1);
/*		 */ 
/* 318 */		 if (j == -1)
/* 319 */			 return true;
/* 320 */		 if (j < 6) {
/* 321 */			 return false;
/*		 */		 }
/* 323 */		 this.e = j;
/* 324 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(double d0, double d1, double d2)
/*		 */	 {
/* 330 */		 this.m = (int)(d0 * 12.0D);
/* 331 */		 if (d0 > 0.5D) {
/* 332 */			 this.n = 5;
/*		 */		 }
/*		 */ 
/* 335 */		 this.j = d1;
/* 336 */		 this.k = d2;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World world, Random random, int i, int j, int k)
/*		 */	 {
/* 345 */		 return generate((BlockChangeDelegate)world, random, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean generate(BlockChangeDelegate world, Random random, int i, int j, int k)
/*		 */	 {
/* 350 */		 this.world = world;
/* 351 */		 long l = random.nextLong();
/*		 */ 
/* 353 */		 this.b.setSeed(l);
/* 354 */		 this.d[0] = i;
/* 355 */		 this.d[1] = j;
/* 356 */		 this.d[2] = k;
/* 357 */		 if (this.e == 0) {
/* 358 */			 this.e = (5 + this.b.nextInt(this.m));
/*		 */		 }
/*		 */ 
/* 361 */		 if (!e()) {
/* 362 */			 return false;
/*		 */		 }
/* 364 */		 a();
/* 365 */		 b();
/* 366 */		 c();
/* 367 */		 d();
/* 368 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenBigTree
 * JD-Core Version:		0.6.0
 */