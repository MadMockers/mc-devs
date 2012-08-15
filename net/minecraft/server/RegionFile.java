/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.BufferedInputStream;
/*		 */ import java.io.ByteArrayInputStream;
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ import java.io.File;
/*		 */ import java.io.IOException;
/*		 */ import java.io.RandomAccessFile;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.zip.DeflaterOutputStream;
/*		 */ import java.util.zip.GZIPInputStream;
/*		 */ import java.util.zip.InflaterInputStream;
/*		 */ 
/*		 */ public class RegionFile
/*		 */ {
/*	74 */	 private static final byte[] a = new byte[4096];
/*		 */	 private final File b;
/*		 */	 private RandomAccessFile c;
/*		 */	 private final int[] d;
/*		 */	 private final int[] e;
/*		 */	 private ArrayList f;
/*		 */	 private int g;
/*	82 */	 private long h = 0L;
/*		 */ 
/*		 */	 public RegionFile(File paramFile) {
/*	85 */		 this.d = new int[1024];
/*	86 */		 this.e = new int[1024];
/*		 */ 
/*	88 */		 this.b = paramFile;
/*		 */ 
/*	90 */		 this.g = 0;
/*		 */		 try
/*		 */		 {
/*	93 */			 if (paramFile.exists()) {
/*	94 */				 this.h = paramFile.lastModified();
/*		 */			 }
/*		 */ 
/*	97 */			 this.c = new RandomAccessFile(paramFile, "rw");
/*		 */ 
/*	99 */			 if (this.c.length() < 4096L)
/*		 */			 {
/* 101 */				 for (i = 0; i < 1024; i++) {
/* 102 */					 this.c.writeInt(0);
/*		 */				 }
/*		 */ 
/* 105 */				 for (i = 0; i < 1024; i++) {
/* 106 */					 this.c.writeInt(0);
/*		 */				 }
/*		 */ 
/* 109 */				 this.g += 8192;
/*		 */			 }
/*		 */ 
/* 112 */			 if ((this.c.length() & 0xFFF) != 0L)
/*		 */			 {
/* 114 */				 for (i = 0; i < (this.c.length() & 0xFFF); i++) {
/* 115 */					 this.c.write(0);
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 120 */			 int i = (int)this.c.length() / 4096;
/* 121 */			 this.f = new ArrayList(i);
/*		 */ 
/* 123 */			 for (int j = 0; j < i; j++) {
/* 124 */				 this.f.add(Boolean.valueOf(true));
/*		 */			 }
/*		 */ 
/* 127 */			 this.f.set(0, Boolean.valueOf(false));
/* 128 */			 this.f.set(1, Boolean.valueOf(false));
/*		 */ 
/* 130 */			 this.c.seek(0L);
/*		 */			 int k;
/* 131 */			 for (j = 0; j < 1024; j++) {
/* 132 */				 k = this.c.readInt();
/* 133 */				 this.d[j] = k;
/* 134 */				 if ((k != 0) && ((k >> 8) + (k & 0xFF) <= this.f.size())) {
/* 135 */					 for (int m = 0; m < (k & 0xFF); m++) {
/* 136 */						 this.f.set((k >> 8) + m, Boolean.valueOf(false));
/*		 */					 }
/*		 */				 }
/*		 */			 }
/* 140 */			 for (j = 0; j < 1024; j++) {
/* 141 */				 k = this.c.readInt();
/* 142 */				 this.e[j] = k;
/*		 */			 }
/*		 */		 } catch (IOException localIOException) {
/* 145 */			 localIOException.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public synchronized DataInputStream a(int paramInt1, int paramInt2)
/*		 */	 {
/* 166 */		 if (d(paramInt1, paramInt2)) {
/* 167 */			 return null;
/*		 */		 }
/*		 */		 try
/*		 */		 {
/* 171 */			 int i = e(paramInt1, paramInt2);
/* 172 */			 if (i == 0) {
/* 173 */				 return null;
/*		 */			 }
/*		 */ 
/* 176 */			 int j = i >> 8;
/* 177 */			 int k = i & 0xFF;
/*		 */ 
/* 179 */			 if (j + k > this.f.size()) {
/* 180 */				 return null;
/*		 */			 }
/*		 */ 
/* 183 */			 this.c.seek(j * 4096);
/* 184 */			 int m = this.c.readInt();
/*		 */ 
/* 186 */			 if (m > 4096 * k)
/* 187 */				 return null;
/* 188 */			 if (m <= 0) {
/* 189 */				 return null;
/*		 */			 }
/*		 */ 
/* 192 */			 int n = this.c.readByte();
/*		 */			 byte[] arrayOfByte;
/* 193 */			 if (n == 1) {
/* 194 */				 arrayOfByte = new byte[m - 1];
/* 195 */				 this.c.read(arrayOfByte);
/* 196 */				 return new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(arrayOfByte))));
/* 197 */			 }if (n == 2) {
/* 198 */				 arrayOfByte = new byte[m - 1];
/* 199 */				 this.c.read(arrayOfByte);
/* 200 */				 return new DataInputStream(new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(arrayOfByte))));
/*		 */			 }
/*		 */ 
/* 203 */			 return null; } catch (IOException localIOException) {
/*		 */		 }
/* 205 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public DataOutputStream b(int paramInt1, int paramInt2)
/*		 */	 {
/* 210 */		 if (d(paramInt1, paramInt2)) return null;
/*		 */ 
/* 212 */		 return new DataOutputStream(new DeflaterOutputStream(new ChunkBuffer(this, paramInt1, paramInt2)));
/*		 */	 }
/*		 */ 
/*		 */	 protected synchronized void a(int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3)
/*		 */	 {
/*		 */		 try
/*		 */		 {
/* 237 */			 int i = e(paramInt1, paramInt2);
/* 238 */			 int j = i >> 8;
/* 239 */			 int k = i & 0xFF;
/* 240 */			 int m = (paramInt3 + 5) / 4096 + 1;
/*		 */ 
/* 243 */			 if (m >= 256) {
/* 244 */				 return;
/*		 */			 }
/*		 */ 
/* 247 */			 if ((j != 0) && (k == m))
/*		 */			 {
/* 249 */				 a(j, paramArrayOfByte, paramInt3);
/*		 */			 }
/*		 */			 else
/*		 */			 {
/* 254 */				 for (int n = 0; n < k; n++) {
/* 255 */					 this.f.set(j + n, Boolean.valueOf(true));
/*		 */				 }
/*		 */ 
/* 259 */				 n = this.f.indexOf(Boolean.valueOf(true));
/* 260 */				 int i1 = 0;
/*		 */				 int i2;
/* 261 */				 if (n != -1) {
/* 262 */					 for (i2 = n; i2 < this.f.size(); i2++) {
/* 263 */						 if (i1 != 0) {
/* 264 */							 if (((Boolean)this.f.get(i2)).booleanValue()) i1++; else
/* 265 */								 i1 = 0;
/* 266 */						 } else if (((Boolean)this.f.get(i2)).booleanValue()) {
/* 267 */							 n = i2;
/* 268 */							 i1 = 1;
/*		 */						 }
/* 270 */						 if (i1 >= m)
/*		 */						 {
/*		 */							 break;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/* 276 */				 if (i1 >= m)
/*		 */				 {
/* 278 */					 j = n;
/* 279 */					 a(paramInt1, paramInt2, j << 8 | m);
/* 280 */					 for (i2 = 0; i2 < m; i2++) {
/* 281 */						 this.f.set(j + i2, Boolean.valueOf(false));
/*		 */					 }
/* 283 */					 a(j, paramArrayOfByte, paramInt3);
/*		 */				 }
/*		 */				 else {
/* 286 */					 this.c.seek(this.c.length());
/* 287 */					 j = this.f.size();
/* 288 */					 for (i2 = 0; i2 < m; i2++) {
/* 289 */						 this.c.write(a);
/* 290 */						 this.f.add(Boolean.valueOf(false));
/*		 */					 }
/* 292 */					 this.g += 4096 * m;
/*		 */ 
/* 294 */					 a(j, paramArrayOfByte, paramInt3);
/* 295 */					 a(paramInt1, paramInt2, j << 8 | m);
/*		 */				 }
/*		 */			 }
/* 298 */			 b(paramInt1, paramInt2, (int)(System.currentTimeMillis() / 1000L));
/*		 */		 } catch (IOException localIOException) {
/* 300 */			 localIOException.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void a(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
/*		 */	 {
/* 306 */		 this.c.seek(paramInt1 * 4096);
/* 307 */		 this.c.writeInt(paramInt2 + 1);
/* 308 */		 this.c.writeByte(2);
/* 309 */		 this.c.write(paramArrayOfByte, 0, paramInt2);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean d(int paramInt1, int paramInt2)
/*		 */	 {
/* 314 */		 return (paramInt1 < 0) || (paramInt1 >= 32) || (paramInt2 < 0) || (paramInt2 >= 32);
/*		 */	 }
/*		 */ 
/*		 */	 private int e(int paramInt1, int paramInt2) {
/* 318 */		 return this.d[(paramInt1 + paramInt2 * 32)];
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(int paramInt1, int paramInt2) {
/* 322 */		 return e(paramInt1, paramInt2) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 private void a(int paramInt1, int paramInt2, int paramInt3) {
/* 326 */		 this.d[(paramInt1 + paramInt2 * 32)] = paramInt3;
/* 327 */		 this.c.seek((paramInt1 + paramInt2 * 32) * 4);
/* 328 */		 this.c.writeInt(paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 private void b(int paramInt1, int paramInt2, int paramInt3) {
/* 332 */		 this.e[(paramInt1 + paramInt2 * 32)] = paramInt3;
/* 333 */		 this.c.seek(4096 + (paramInt1 + paramInt2 * 32) * 4);
/* 334 */		 this.c.writeInt(paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public void c() {
/* 338 */		 if (this.c != null) this.c.close();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.RegionFile
 * JD-Core Version:		0.6.0
 */