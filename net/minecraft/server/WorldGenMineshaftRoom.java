/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.LinkedList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenMineshaftRoom extends StructurePiece
/*		 */ {
/*	66 */	 private List a = new LinkedList();
/*		 */ 
/*		 */	 public WorldGenMineshaftRoom(int paramInt1, Random paramRandom, int paramInt2, int paramInt3) {
/*	69 */		 super(paramInt1);
/*		 */ 
/*	71 */		 this.e = new StructureBoundingBox(paramInt2, 50, paramInt3, paramInt2 + 7 + paramRandom.nextInt(6), 54 + paramRandom.nextInt(6), paramInt3 + 7 + paramRandom.nextInt(6));
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/*	77 */		 int i = c();
/*		 */ 
/*	81 */		 int j = this.e.c() - 3 - 1;
/*	82 */		 if (j <= 0) {
/*	83 */			 j = 1;
/*		 */		 }
/*		 */ 
/*	87 */		 int k = 0;
/*		 */		 StructurePiece localStructurePiece;
/*		 */		 StructureBoundingBox localStructureBoundingBox;
/*	88 */		 while (k < this.e.b()) {
/*	89 */			 k += paramRandom.nextInt(this.e.b());
/*	90 */			 if (k + 3 > this.e.b()) {
/*		 */				 break;
/*		 */			 }
/*	93 */			 localStructurePiece = WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a + k, this.e.b + paramRandom.nextInt(j) + 1, this.e.c - 1, 2, i);
/*		 */ 
/*	95 */			 if (localStructurePiece != null) {
/*	96 */				 localStructureBoundingBox = localStructurePiece.b();
/*	97 */				 this.a.add(new StructureBoundingBox(localStructureBoundingBox.a, localStructureBoundingBox.b, this.e.c, localStructureBoundingBox.d, localStructureBoundingBox.e, this.e.c + 1));
/*		 */			 }
/*	99 */			 k += 4;
/*		 */		 }
/*		 */ 
/* 102 */		 k = 0;
/* 103 */		 while (k < this.e.b()) {
/* 104 */			 k += paramRandom.nextInt(this.e.b());
/* 105 */			 if (k + 3 > this.e.b()) {
/*		 */				 break;
/*		 */			 }
/* 108 */			 localStructurePiece = WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a + k, this.e.b + paramRandom.nextInt(j) + 1, this.e.f + 1, 0, i);
/*		 */ 
/* 110 */			 if (localStructurePiece != null) {
/* 111 */				 localStructureBoundingBox = localStructurePiece.b();
/* 112 */				 this.a.add(new StructureBoundingBox(localStructureBoundingBox.a, localStructureBoundingBox.b, this.e.f - 1, localStructureBoundingBox.d, localStructureBoundingBox.e, this.e.f));
/*		 */			 }
/* 114 */			 k += 4;
/*		 */		 }
/*		 */ 
/* 117 */		 k = 0;
/* 118 */		 while (k < this.e.d()) {
/* 119 */			 k += paramRandom.nextInt(this.e.d());
/* 120 */			 if (k + 3 > this.e.d()) {
/*		 */				 break;
/*		 */			 }
/* 123 */			 localStructurePiece = WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b + paramRandom.nextInt(j) + 1, this.e.c + k, 1, i);
/*		 */ 
/* 125 */			 if (localStructurePiece != null) {
/* 126 */				 localStructureBoundingBox = localStructurePiece.b();
/* 127 */				 this.a.add(new StructureBoundingBox(this.e.a, localStructureBoundingBox.b, localStructureBoundingBox.c, this.e.a + 1, localStructureBoundingBox.e, localStructureBoundingBox.f));
/*		 */			 }
/* 129 */			 k += 4;
/*		 */		 }
/*		 */ 
/* 132 */		 k = 0;
/* 133 */		 while (k < this.e.d()) {
/* 134 */			 k += paramRandom.nextInt(this.e.d());
/* 135 */			 if (k + 3 > this.e.d()) {
/*		 */				 break;
/*		 */			 }
/* 138 */			 localStructurePiece = WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b + paramRandom.nextInt(j) + 1, this.e.c + k, 3, i);
/*		 */ 
/* 140 */			 if (localStructurePiece != null) {
/* 141 */				 localStructureBoundingBox = localStructurePiece.b();
/* 142 */				 this.a.add(new StructureBoundingBox(this.e.d - 1, localStructureBoundingBox.b, localStructureBoundingBox.c, this.e.d, localStructureBoundingBox.e, localStructureBoundingBox.f));
/*		 */			 }
/* 144 */			 k += 4;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 151 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 152 */			 return false;
/*		 */		 }
/*		 */ 
/* 156 */		 a(paramWorld, paramStructureBoundingBox, this.e.a, this.e.b, this.e.c, this.e.d, this.e.b, this.e.f, Block.DIRT.id, 0, true);
/*		 */ 
/* 159 */		 a(paramWorld, paramStructureBoundingBox, this.e.a, this.e.b + 1, this.e.c, this.e.d, Math.min(this.e.b + 3, this.e.e), this.e.f, 0, 0, false);
/* 160 */		 for (StructureBoundingBox localStructureBoundingBox : this.a) {
/* 161 */			 a(paramWorld, paramStructureBoundingBox, localStructureBoundingBox.a, localStructureBoundingBox.e - 2, localStructureBoundingBox.c, localStructureBoundingBox.d, localStructureBoundingBox.e, localStructureBoundingBox.f, 0, 0, false);
/*		 */		 }
/* 163 */		 a(paramWorld, paramStructureBoundingBox, this.e.a, this.e.b + 4, this.e.c, this.e.d, this.e.e, this.e.f, 0, false);
/*		 */ 
/* 165 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenMineshaftRoom
 * JD-Core Version:		0.6.0
 */