/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BiomeDecorator
/*		 */ {
/*		 */	 protected World a;
/*		 */	 protected Random b;
/*		 */	 protected int c;
/*		 */	 protected int d;
/*		 */	 protected BiomeBase e;
/*	33 */	 protected WorldGenerator f = new WorldGenClay(4);
/*	34 */	 protected WorldGenerator g = new WorldGenSand(7, Block.SAND.id);
/*	35 */	 protected WorldGenerator h = new WorldGenSand(6, Block.GRAVEL.id);
/*	36 */	 protected WorldGenerator i = new WorldGenMinable(Block.DIRT.id, 32);
/*	37 */	 protected WorldGenerator j = new WorldGenMinable(Block.GRAVEL.id, 32);
/*	38 */	 protected WorldGenerator k = new WorldGenMinable(Block.COAL_ORE.id, 16);
/*	39 */	 protected WorldGenerator l = new WorldGenMinable(Block.IRON_ORE.id, 8);
/*	40 */	 protected WorldGenerator m = new WorldGenMinable(Block.GOLD_ORE.id, 8);
/*	41 */	 protected WorldGenerator n = new WorldGenMinable(Block.REDSTONE_ORE.id, 7);
/*	42 */	 protected WorldGenerator o = new WorldGenMinable(Block.DIAMOND_ORE.id, 7);
/*	43 */	 protected WorldGenerator p = new WorldGenMinable(Block.LAPIS_ORE.id, 6);
/*	44 */	 protected WorldGenerator q = new WorldGenFlowers(Block.YELLOW_FLOWER.id);
/*	45 */	 protected WorldGenerator r = new WorldGenFlowers(Block.RED_ROSE.id);
/*	46 */	 protected WorldGenerator s = new WorldGenFlowers(Block.BROWN_MUSHROOM.id);
/*	47 */	 protected WorldGenerator t = new WorldGenFlowers(Block.RED_MUSHROOM.id);
/*	48 */	 protected WorldGenerator u = new WorldGenHugeMushroom();
/*	49 */	 protected WorldGenerator v = new WorldGenReed();
/*	50 */	 protected WorldGenerator w = new WorldGenCactus();
/*	51 */	 protected WorldGenerator x = new WorldGenWaterLily();
/*		 */ 
/*	53 */	 protected int y = 0;
/*	54 */	 protected int z = 0;
/*	55 */	 protected int A = 2;
/*	56 */	 protected int B = 1;
/*	57 */	 protected int C = 0;
/*	58 */	 protected int D = 0;
/*	59 */	 protected int E = 0;
/*	60 */	 protected int F = 0;
/*	61 */	 protected int G = 1;
/*	62 */	 protected int H = 3;
/*	63 */	 protected int I = 1;
/*	64 */	 protected int J = 0;
/*	65 */	 public boolean K = true;
/*		 */ 
/*		 */	 public BiomeDecorator(BiomeBase paramBiomeBase)
/*		 */	 {
/*	17 */		 this.e = paramBiomeBase;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2) {
/*	21 */		 if (this.a != null) throw new RuntimeException("Already decorating!!");
/*	22 */		 this.a = paramWorld;
/*	23 */		 this.b = paramRandom;
/*	24 */		 this.c = paramInt1;
/*	25 */		 this.d = paramInt2;
/*		 */ 
/*	27 */		 a();
/*		 */ 
/*	29 */		 this.a = null;
/*	30 */		 this.b = null;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a()
/*		 */	 {
/*	68 */		 b();
/*		 */		 int i3;
/*	70 */		 for (int i1 = 0; i1 < this.H; i1++) {
/*	71 */			 i2 = this.c + this.b.nextInt(16) + 8;
/*	72 */			 i3 = this.d + this.b.nextInt(16) + 8;
/*	73 */			 this.g.a(this.a, this.b, i2, this.a.h(i2, i3), i3);
/*		 */		 }
/*		 */ 
/*	76 */		 for (i1 = 0; i1 < this.I; i1++) {
/*	77 */			 i2 = this.c + this.b.nextInt(16) + 8;
/*	78 */			 i3 = this.d + this.b.nextInt(16) + 8;
/*	79 */			 this.f.a(this.a, this.b, i2, this.a.h(i2, i3), i3);
/*		 */		 }
/*		 */ 
/*	82 */		 for (i1 = 0; i1 < this.G; i1++) {
/*	83 */			 i2 = this.c + this.b.nextInt(16) + 8;
/*	84 */			 i3 = this.d + this.b.nextInt(16) + 8;
/*	85 */			 this.g.a(this.a, this.b, i2, this.a.h(i2, i3), i3);
/*		 */		 }
/*		 */ 
/*	88 */		 i1 = this.z;
/*	89 */		 if (this.b.nextInt(10) == 0) i1++;
/*		 */		 int i4;
/*	91 */		 for (int i2 = 0; i2 < i1; i2++) {
/*	92 */			 i3 = this.c + this.b.nextInt(16) + 8;
/*	93 */			 i4 = this.d + this.b.nextInt(16) + 8;
/*	94 */			 WorldGenerator localWorldGenerator1 = this.e.a(this.b);
/*	95 */			 localWorldGenerator1.a(1.0D, 1.0D, 1.0D);
/*	96 */			 localWorldGenerator1.a(this.a, this.b, i3, this.a.getHighestBlockYAt(i3, i4), i4);
/*		 */		 }
/*		 */ 
/*	99 */		 for (i2 = 0; i2 < this.J; i2++) {
/* 100 */			 i3 = this.c + this.b.nextInt(16) + 8;
/* 101 */			 i4 = this.d + this.b.nextInt(16) + 8;
/* 102 */			 this.u.a(this.a, this.b, i3, this.a.getHighestBlockYAt(i3, i4), i4);
/*		 */		 }
/*		 */		 int i5;
/* 105 */		 for (i2 = 0; i2 < this.A; i2++) {
/* 106 */			 i3 = this.c + this.b.nextInt(16) + 8;
/* 107 */			 i4 = this.b.nextInt(128);
/* 108 */			 i5 = this.d + this.b.nextInt(16) + 8;
/* 109 */			 this.q.a(this.a, this.b, i3, i4, i5);
/*		 */ 
/* 111 */			 if (this.b.nextInt(4) == 0) {
/* 112 */				 i3 = this.c + this.b.nextInt(16) + 8;
/* 113 */				 i4 = this.b.nextInt(128);
/* 114 */				 i5 = this.d + this.b.nextInt(16) + 8;
/* 115 */				 this.r.a(this.a, this.b, i3, i4, i5);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 119 */		 for (i2 = 0; i2 < this.B; i2++) {
/* 120 */			 i3 = this.c + this.b.nextInt(16) + 8;
/* 121 */			 i4 = this.b.nextInt(128);
/* 122 */			 i5 = this.d + this.b.nextInt(16) + 8;
/* 123 */			 WorldGenerator localWorldGenerator2 = this.e.b(this.b);
/* 124 */			 localWorldGenerator2.a(this.a, this.b, i3, i4, i5);
/*		 */		 }
/*		 */ 
/* 127 */		 for (i2 = 0; i2 < this.C; i2++) {
/* 128 */			 i3 = this.c + this.b.nextInt(16) + 8;
/* 129 */			 i4 = this.b.nextInt(128);
/* 130 */			 i5 = this.d + this.b.nextInt(16) + 8;
/* 131 */			 new WorldGenDeadBush(Block.DEAD_BUSH.id).a(this.a, this.b, i3, i4, i5);
/*		 */		 }
/*		 */ 
/* 134 */		 for (i2 = 0; i2 < this.y; i2++) {
/* 135 */			 i3 = this.c + this.b.nextInt(16) + 8;
/* 136 */			 i4 = this.d + this.b.nextInt(16) + 8;
/* 137 */			 i5 = this.b.nextInt(128);
/* 138 */			 while ((i5 > 0) && (this.a.getTypeId(i3, i5 - 1, i4) == 0))
/* 139 */				 i5--;
/* 140 */			 this.x.a(this.a, this.b, i3, i5, i4);
/*		 */		 }
/*		 */ 
/* 143 */		 for (i2 = 0; i2 < this.D; i2++) {
/* 144 */			 if (this.b.nextInt(4) == 0) {
/* 145 */				 i3 = this.c + this.b.nextInt(16) + 8;
/* 146 */				 i4 = this.d + this.b.nextInt(16) + 8;
/* 147 */				 i5 = this.a.getHighestBlockYAt(i3, i4);
/* 148 */				 this.s.a(this.a, this.b, i3, i5, i4);
/*		 */			 }
/*		 */ 
/* 151 */			 if (this.b.nextInt(8) == 0) {
/* 152 */				 i3 = this.c + this.b.nextInt(16) + 8;
/* 153 */				 i4 = this.d + this.b.nextInt(16) + 8;
/* 154 */				 i5 = this.b.nextInt(128);
/* 155 */				 this.t.a(this.a, this.b, i3, i5, i4);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 159 */		 if (this.b.nextInt(4) == 0) {
/* 160 */			 i2 = this.c + this.b.nextInt(16) + 8;
/* 161 */			 i3 = this.b.nextInt(128);
/* 162 */			 i4 = this.d + this.b.nextInt(16) + 8;
/* 163 */			 this.s.a(this.a, this.b, i2, i3, i4);
/*		 */		 }
/*		 */ 
/* 166 */		 if (this.b.nextInt(8) == 0) {
/* 167 */			 i2 = this.c + this.b.nextInt(16) + 8;
/* 168 */			 i3 = this.b.nextInt(128);
/* 169 */			 i4 = this.d + this.b.nextInt(16) + 8;
/* 170 */			 this.t.a(this.a, this.b, i2, i3, i4);
/*		 */		 }
/*		 */ 
/* 173 */		 for (i2 = 0; i2 < this.E; i2++) {
/* 174 */			 i3 = this.c + this.b.nextInt(16) + 8;
/* 175 */			 i4 = this.d + this.b.nextInt(16) + 8;
/* 176 */			 i5 = this.b.nextInt(128);
/* 177 */			 this.v.a(this.a, this.b, i3, i5, i4);
/*		 */		 }
/*		 */ 
/* 180 */		 for (i2 = 0; i2 < 10; i2++) {
/* 181 */			 i3 = this.c + this.b.nextInt(16) + 8;
/* 182 */			 i4 = this.b.nextInt(128);
/* 183 */			 i5 = this.d + this.b.nextInt(16) + 8;
/* 184 */			 this.v.a(this.a, this.b, i3, i4, i5);
/*		 */		 }
/*		 */ 
/* 187 */		 if (this.b.nextInt(32) == 0) {
/* 188 */			 i2 = this.c + this.b.nextInt(16) + 8;
/* 189 */			 i3 = this.b.nextInt(128);
/* 190 */			 i4 = this.d + this.b.nextInt(16) + 8;
/* 191 */			 new WorldGenPumpkin().a(this.a, this.b, i2, i3, i4);
/*		 */		 }
/*		 */ 
/* 194 */		 for (i2 = 0; i2 < this.F; i2++) {
/* 195 */			 i3 = this.c + this.b.nextInt(16) + 8;
/* 196 */			 i4 = this.b.nextInt(128);
/* 197 */			 i5 = this.d + this.b.nextInt(16) + 8;
/* 198 */			 this.w.a(this.a, this.b, i3, i4, i5);
/*		 */		 }
/*		 */ 
/* 201 */		 if (this.K) {
/* 202 */			 for (i2 = 0; i2 < 50; i2++) {
/* 203 */				 i3 = this.c + this.b.nextInt(16) + 8;
/* 204 */				 i4 = this.b.nextInt(this.b.nextInt(120) + 8);
/* 205 */				 i5 = this.d + this.b.nextInt(16) + 8;
/* 206 */				 new WorldGenLiquids(Block.WATER.id).a(this.a, this.b, i3, i4, i5);
/*		 */			 }
/*		 */ 
/* 209 */			 for (i2 = 0; i2 < 20; i2++) {
/* 210 */				 i3 = this.c + this.b.nextInt(16) + 8;
/* 211 */				 i4 = this.b.nextInt(this.b.nextInt(this.b.nextInt(112) + 8) + 8);
/* 212 */				 i5 = this.d + this.b.nextInt(16) + 8;
/* 213 */				 new WorldGenLiquids(Block.LAVA.id).a(this.a, this.b, i3, i4, i5);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(int paramInt1, WorldGenerator paramWorldGenerator, int paramInt2, int paramInt3)
/*		 */	 {
/* 223 */		 for (int i1 = 0; i1 < paramInt1; i1++) {
/* 224 */			 int i2 = this.c + this.b.nextInt(16);
/* 225 */			 int i3 = this.b.nextInt(paramInt3 - paramInt2) + paramInt2;
/* 226 */			 int i4 = this.d + this.b.nextInt(16);
/* 227 */			 paramWorldGenerator.a(this.a, this.b, i2, i3, i4);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void b(int paramInt1, WorldGenerator paramWorldGenerator, int paramInt2, int paramInt3) {
/* 232 */		 for (int i1 = 0; i1 < paramInt1; i1++) {
/* 233 */			 int i2 = this.c + this.b.nextInt(16);
/* 234 */			 int i3 = this.b.nextInt(paramInt3) + this.b.nextInt(paramInt3) + (paramInt2 - paramInt3);
/* 235 */			 int i4 = this.d + this.b.nextInt(16);
/* 236 */			 paramWorldGenerator.a(this.a, this.b, i2, i3, i4);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void b() {
/* 241 */		 a(20, this.i, 0, 128);
/* 242 */		 a(10, this.j, 0, 128);
/* 243 */		 a(20, this.k, 0, 128);
/* 244 */		 a(20, this.l, 0, 64);
/* 245 */		 a(2, this.m, 0, 32);
/* 246 */		 a(8, this.n, 0, 16);
/* 247 */		 a(1, this.o, 0, 16);
/* 248 */		 b(1, this.p, 16, 16);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BiomeDecorator
 * JD-Core Version:		0.6.0
 */