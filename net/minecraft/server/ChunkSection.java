/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class ChunkSection
/*		 */ {
/*		 */	 private int a;
/*		 */	 private int b;
/*		 */	 private int c;
/*		 */	 private byte[] d;
/*		 */	 private NibbleArray e;
/*		 */	 private NibbleArray f;
/*		 */	 private NibbleArray g;
/*		 */	 private NibbleArray h;
/*		 */ 
/*		 */	 public ChunkSection(int i)
/*		 */	 {
/*	15 */		 this.a = i;
/*	16 */		 this.d = new byte[4096];
/*	17 */		 this.f = new NibbleArray(this.d.length, 4);
/*	18 */		 this.h = new NibbleArray(this.d.length, 4);
/*	19 */		 this.g = new NibbleArray(this.d.length, 4);
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkSection(int y, byte[] blkData, byte[] extBlkData)
/*		 */	 {
/*	24 */		 this.a = y;
/*	25 */		 this.d = blkData;
/*	26 */		 if (extBlkData != null) {
/*	27 */			 this.e = new NibbleArray(extBlkData, 4);
/*		 */		 }
/*	29 */		 this.f = new NibbleArray(this.d.length, 4);
/*	30 */		 this.h = new NibbleArray(this.d.length, 4);
/*	31 */		 this.g = new NibbleArray(this.d.length, 4);
/*	32 */		 e();
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i, int j, int k)
/*		 */	 {
/*	37 */		 int l = this.d[(j << 8 | k << 4 | i)] & 0xFF;
/*		 */ 
/*	39 */		 return this.e != null ? this.e.a(i, j, k) << 8 | l : l;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int i, int j, int k, int l) {
/*	43 */		 int i1 = this.d[(j << 8 | k << 4 | i)] & 0xFF;
/*		 */ 
/*	45 */		 if (this.e != null) {
/*	46 */			 i1 |= this.e.a(i, j, k) << 8;
/*		 */		 }
/*		 */ 
/*	49 */		 if ((i1 == 0) && (l != 0)) {
/*	50 */			 this.b += 1;
/*	51 */			 if ((Block.byId[l] != null) && (Block.byId[l].r()))
/*	52 */				 this.c += 1;
/*		 */		 }
/*	54 */		 else if ((i1 != 0) && (l == 0)) {
/*	55 */			 this.b -= 1;
/*	56 */			 if ((Block.byId[i1] != null) && (Block.byId[i1].r()))
/*	57 */				 this.c -= 1;
/*		 */		 }
/*	59 */		 else if ((Block.byId[i1] != null) && (Block.byId[i1].r()) && ((Block.byId[l] == null) || (!Block.byId[l].r()))) {
/*	60 */			 this.c -= 1;
/*	61 */		 } else if (((Block.byId[i1] == null) || (!Block.byId[i1].r())) && (Block.byId[l] != null) && (Block.byId[l].r())) {
/*	62 */			 this.c += 1;
/*		 */		 }
/*		 */ 
/*	65 */		 this.d[(j << 8 | k << 4 | i)] = (byte)(l & 0xFF);
/*	66 */		 if (l > 255) {
/*	67 */			 if (this.e == null) {
/*	68 */				 this.e = new NibbleArray(this.d.length, 4);
/*		 */			 }
/*		 */ 
/*	71 */			 this.e.a(i, j, k, (l & 0xF00) >> 8);
/*	72 */		 } else if (this.e != null) {
/*	73 */			 this.e.a(i, j, k, 0);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int b(int i, int j, int k) {
/*	78 */		 return this.f.a(i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(int i, int j, int k, int l) {
/*	82 */		 this.f.a(i, j, k, l);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a() {
/*	86 */		 return this.b == 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b() {
/*	90 */		 return this.c > 0;
/*		 */	 }
/*		 */ 
/*		 */	 public int d() {
/*	94 */		 return this.a;
/*		 */	 }
/*		 */ 
/*		 */	 public void c(int i, int j, int k, int l) {
/*	98 */		 this.h.a(i, j, k, l);
/*		 */	 }
/*		 */ 
/*		 */	 public int c(int i, int j, int k) {
/* 102 */		 return this.h.a(i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public void d(int i, int j, int k, int l) {
/* 106 */		 this.g.a(i, j, k, l);
/*		 */	 }
/*		 */ 
/*		 */	 public int d(int i, int j, int k) {
/* 110 */		 return this.g.a(i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public void e() {
/* 114 */		 this.b = 0;
/* 115 */		 this.c = 0;
/*		 */ 
/* 117 */		 for (int i = 0; i < 16; i++)
/* 118 */			 for (int j = 0; j < 16; j++)
/* 119 */				 for (int k = 0; k < 16; k++) {
/* 120 */					 int l = a(i, j, k);
/*		 */ 
/* 122 */					 if (l > 0)
/* 123 */						 if (Block.byId[l] == null) {
/* 124 */							 this.d[(j << 8 | k << 4 | i)] = 0;
/* 125 */							 if (this.e != null)
/* 126 */								 this.e.a(i, j, k, 0);
/*		 */						 }
/*		 */						 else {
/* 129 */							 this.b += 1;
/* 130 */							 if (Block.byId[l].r())
/* 131 */								 this.c += 1;
/*		 */						 }
/*		 */				 }
/*		 */	 }
/*		 */ 
/*		 */	 public byte[] g()
/*		 */	 {
/* 141 */		 return this.d;
/*		 */	 }
/*		 */ 
/*		 */	 public NibbleArray i() {
/* 145 */		 return this.e;
/*		 */	 }
/*		 */ 
/*		 */	 public NibbleArray j() {
/* 149 */		 return this.f;
/*		 */	 }
/*		 */ 
/*		 */	 public NibbleArray k() {
/* 153 */		 return this.g;
/*		 */	 }
/*		 */ 
/*		 */	 public NibbleArray l() {
/* 157 */		 return this.h;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(byte[] abyte) {
/* 161 */		 this.d = abyte;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NibbleArray nibblearray) {
/* 165 */		 this.e = nibblearray;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NibbleArray nibblearray) {
/* 169 */		 this.f = nibblearray;
/*		 */	 }
/*		 */ 
/*		 */	 public void c(NibbleArray nibblearray) {
/* 173 */		 this.g = nibblearray;
/*		 */	 }
/*		 */ 
/*		 */	 public void d(NibbleArray nibblearray) {
/* 177 */		 this.h = nibblearray;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ChunkSection
 * JD-Core Version:		0.6.0
 */