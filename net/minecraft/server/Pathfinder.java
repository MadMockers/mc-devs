/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class Pathfinder
/*		 */ {
/*		 */	 private IBlockAccess a;
/*	13 */	 private Path b = new Path();
/*	14 */	 private IntHashMap c = new IntHashMap();
/*		 */ 
/*	16 */	 private PathPoint[] d = new PathPoint[32];
/*		 */	 private boolean e;
/*		 */	 private boolean f;
/*		 */	 private boolean g;
/*		 */	 private boolean h;
/*		 */ 
/*		 */	 public Pathfinder(IBlockAccess paramIBlockAccess, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
/*		 */	 {
/*	24 */		 this.a = paramIBlockAccess;
/*	25 */		 this.e = paramBoolean1;
/*	26 */		 this.f = paramBoolean2;
/*	27 */		 this.g = paramBoolean3;
/*	28 */		 this.h = paramBoolean4;
/*		 */	 }
/*		 */ 
/*		 */	 public PathEntity a(Entity paramEntity1, Entity paramEntity2, float paramFloat) {
/*	32 */		 return a(paramEntity1, paramEntity2.locX, paramEntity2.boundingBox.b, paramEntity2.locZ, paramFloat);
/*		 */	 }
/*		 */ 
/*		 */	 public PathEntity a(Entity paramEntity, int paramInt1, int paramInt2, int paramInt3, float paramFloat) {
/*	36 */		 return a(paramEntity, paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, paramFloat);
/*		 */	 }
/*		 */ 
/*		 */	 private PathEntity a(Entity paramEntity, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat) {
/*	40 */		 this.b.a();
/*	41 */		 this.c.c();
/*		 */ 
/*	43 */		 boolean bool = this.g;
/*	44 */		 int i = MathHelper.floor(paramEntity.boundingBox.b + 0.5D);
/*	45 */		 if ((this.h) && (paramEntity.H())) {
/*	46 */			 i = (int)paramEntity.boundingBox.b;
/*	47 */			 int j = this.a.getTypeId(MathHelper.floor(paramEntity.locX), i, MathHelper.floor(paramEntity.locZ));
/*	48 */			 while ((j == Block.WATER.id) || (j == Block.STATIONARY_WATER.id)) {
/*	49 */				 i++;
/*	50 */				 j = this.a.getTypeId(MathHelper.floor(paramEntity.locX), i, MathHelper.floor(paramEntity.locZ));
/*		 */			 }
/*	52 */			 bool = this.g;
/*	53 */			 this.g = false; } else {
/*	54 */			 i = MathHelper.floor(paramEntity.boundingBox.b + 0.5D);
/*		 */		 }
/*	56 */		 PathPoint localPathPoint1 = a(MathHelper.floor(paramEntity.boundingBox.a), i, MathHelper.floor(paramEntity.boundingBox.c));
/*	57 */		 PathPoint localPathPoint2 = a(MathHelper.floor(paramDouble1 - paramEntity.width / 2.0F), MathHelper.floor(paramDouble2), MathHelper.floor(paramDouble3 - paramEntity.width / 2.0F));
/*		 */ 
/*	59 */		 PathPoint localPathPoint3 = new PathPoint(MathHelper.d(paramEntity.width + 1.0F), MathHelper.d(paramEntity.length + 1.0F), MathHelper.d(paramEntity.width + 1.0F));
/*	60 */		 PathEntity localPathEntity = a(paramEntity, localPathPoint1, localPathPoint2, localPathPoint3, paramFloat);
/*		 */ 
/*	62 */		 this.g = bool;
/*	63 */		 return localPathEntity;
/*		 */	 }
/*		 */ 
/*		 */	 private PathEntity a(Entity paramEntity, PathPoint paramPathPoint1, PathPoint paramPathPoint2, PathPoint paramPathPoint3, float paramFloat)
/*		 */	 {
/*	68 */		 paramPathPoint1.e = 0.0F;
/*	69 */		 paramPathPoint1.f = paramPathPoint1.b(paramPathPoint2);
/*	70 */		 paramPathPoint1.g = paramPathPoint1.f;
/*		 */ 
/*	72 */		 this.b.a();
/*	73 */		 this.b.a(paramPathPoint1);
/*		 */ 
/*	75 */		 Object localObject = paramPathPoint1;
/*		 */ 
/*	77 */		 while (!this.b.e()) {
/*	78 */			 PathPoint localPathPoint1 = this.b.c();
/*		 */ 
/*	80 */			 if (localPathPoint1.equals(paramPathPoint2)) {
/*	81 */				 return a(paramPathPoint1, paramPathPoint2);
/*		 */			 }
/*		 */ 
/*	84 */			 if (localPathPoint1.b(paramPathPoint2) < ((PathPoint)localObject).b(paramPathPoint2)) {
/*	85 */				 localObject = localPathPoint1;
/*		 */			 }
/*	87 */			 localPathPoint1.i = true;
/*		 */ 
/*	89 */			 int i = b(paramEntity, localPathPoint1, paramPathPoint3, paramPathPoint2, paramFloat);
/*	90 */			 for (int j = 0; j < i; j++) {
/*	91 */				 PathPoint localPathPoint2 = this.d[j];
/*		 */ 
/*	93 */				 float f1 = localPathPoint1.e + localPathPoint1.b(localPathPoint2);
/*	94 */				 if ((!localPathPoint2.a()) || (f1 < localPathPoint2.e)) {
/*	95 */					 localPathPoint2.h = localPathPoint1;
/*	96 */					 localPathPoint2.e = f1;
/*	97 */					 localPathPoint2.f = localPathPoint2.b(paramPathPoint2);
/*	98 */					 if (localPathPoint2.a()) {
/*	99 */						 this.b.a(localPathPoint2, localPathPoint2.e + localPathPoint2.f);
/*		 */					 } else {
/* 101 */						 localPathPoint2.g = (localPathPoint2.e + localPathPoint2.f);
/* 102 */						 this.b.a(localPathPoint2);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 108 */		 if (localObject == paramPathPoint1) return null;
/* 109 */		 return (PathEntity)a(paramPathPoint1, (PathPoint)localObject);
/*		 */	 }
/*		 */ 
/*		 */	 private int b(Entity paramEntity, PathPoint paramPathPoint1, PathPoint paramPathPoint2, PathPoint paramPathPoint3, float paramFloat) {
/* 113 */		 int i = 0;
/*		 */ 
/* 115 */		 int j = 0;
/* 116 */		 if (a(paramEntity, paramPathPoint1.a, paramPathPoint1.b + 1, paramPathPoint1.c, paramPathPoint2) == 1) j = 1;
/*		 */ 
/* 118 */		 PathPoint localPathPoint1 = a(paramEntity, paramPathPoint1.a, paramPathPoint1.b, paramPathPoint1.c + 1, paramPathPoint2, j);
/* 119 */		 PathPoint localPathPoint2 = a(paramEntity, paramPathPoint1.a - 1, paramPathPoint1.b, paramPathPoint1.c, paramPathPoint2, j);
/* 120 */		 PathPoint localPathPoint3 = a(paramEntity, paramPathPoint1.a + 1, paramPathPoint1.b, paramPathPoint1.c, paramPathPoint2, j);
/* 121 */		 PathPoint localPathPoint4 = a(paramEntity, paramPathPoint1.a, paramPathPoint1.b, paramPathPoint1.c - 1, paramPathPoint2, j);
/*		 */ 
/* 123 */		 if ((localPathPoint1 != null) && (!localPathPoint1.i) && (localPathPoint1.a(paramPathPoint3) < paramFloat)) this.d[(i++)] = localPathPoint1;
/* 124 */		 if ((localPathPoint2 != null) && (!localPathPoint2.i) && (localPathPoint2.a(paramPathPoint3) < paramFloat)) this.d[(i++)] = localPathPoint2;
/* 125 */		 if ((localPathPoint3 != null) && (!localPathPoint3.i) && (localPathPoint3.a(paramPathPoint3) < paramFloat)) this.d[(i++)] = localPathPoint3;
/* 126 */		 if ((localPathPoint4 != null) && (!localPathPoint4.i) && (localPathPoint4.a(paramPathPoint3) < paramFloat)) this.d[(i++)] = localPathPoint4;
/*		 */ 
/* 128 */		 return i;
/*		 */	 }
/*		 */ 
/*		 */	 private PathPoint a(Entity paramEntity, int paramInt1, int paramInt2, int paramInt3, PathPoint paramPathPoint, int paramInt4) {
/* 132 */		 PathPoint localPathPoint = null;
/* 133 */		 int i = a(paramEntity, paramInt1, paramInt2, paramInt3, paramPathPoint);
/* 134 */		 if (i == 2) return a(paramInt1, paramInt2, paramInt3);
/* 135 */		 if (i == 1) localPathPoint = a(paramInt1, paramInt2, paramInt3);
/* 136 */		 if ((localPathPoint == null) && (paramInt4 > 0) && (i != -3) && (i != -4) && (a(paramEntity, paramInt1, paramInt2 + paramInt4, paramInt3, paramPathPoint) == 1)) {
/* 137 */			 localPathPoint = a(paramInt1, paramInt2 + paramInt4, paramInt3);
/* 138 */			 paramInt2 += paramInt4;
/*		 */		 }
/*		 */ 
/* 141 */		 if (localPathPoint != null) {
/* 142 */			 int j = 0;
/* 143 */			 int k = 0;
/*		 */ 
/* 145 */			 while (paramInt2 > 0) {
/* 146 */				 k = a(paramEntity, paramInt1, paramInt2 - 1, paramInt3, paramPathPoint);
/* 147 */				 if ((this.g) && (k == -1)) return null;
/* 148 */				 if (k != 1) {
/*		 */					 break;
/*		 */				 }
/* 151 */				 j++; if (j >= 4) return null;
/* 152 */				 paramInt2--;
/* 153 */				 if (paramInt2 <= 0) continue; localPathPoint = a(paramInt1, paramInt2, paramInt3);
/*		 */			 }
/*		 */ 
/* 156 */			 if (k == -2) return null;
/*		 */		 }
/*		 */ 
/* 159 */		 return localPathPoint;
/*		 */	 }
/*		 */ 
/*		 */	 private final PathPoint a(int paramInt1, int paramInt2, int paramInt3) {
/* 163 */		 int i = PathPoint.a(paramInt1, paramInt2, paramInt3);
/* 164 */		 PathPoint localPathPoint = (PathPoint)this.c.get(i);
/* 165 */		 if (localPathPoint == null) {
/* 166 */			 localPathPoint = new PathPoint(paramInt1, paramInt2, paramInt3);
/* 167 */			 this.c.a(i, localPathPoint);
/*		 */		 }
/* 169 */		 return localPathPoint;
/*		 */	 }
/*		 */ 
/*		 */	 private int a(Entity paramEntity, int paramInt1, int paramInt2, int paramInt3, PathPoint paramPathPoint)
/*		 */	 {
/* 181 */		 int i = 0;
/* 182 */		 for (int j = paramInt1; j < paramInt1 + paramPathPoint.a; j++) {
/* 183 */			 for (int k = paramInt2; k < paramInt2 + paramPathPoint.b; k++)
/* 184 */				 for (int m = paramInt3; m < paramInt3 + paramPathPoint.c; m++)
/*		 */				 {
/* 186 */					 int n = this.a.getTypeId(j, k, m);
/* 187 */					 if (n > 0) {
/* 188 */						 if (n == Block.TRAP_DOOR.id) { i = 1;
/* 189 */						 } else if ((n == Block.WATER.id) || (n == Block.STATIONARY_WATER.id)) {
/* 190 */							 if (this.g) return -1;
/* 191 */							 i = 1;
/* 192 */						 } else if ((!this.e) && (n == Block.WOODEN_DOOR.id)) {
/* 193 */							 return 0;
/*		 */						 }
/*		 */ 
/* 196 */						 Block localBlock = Block.byId[n];
/* 197 */						 if ((localBlock.c(this.a, j, k, m)) || (
/* 198 */							 (this.f) && (n == Block.WOODEN_DOOR.id))) continue;
/* 199 */						 if ((n == Block.FENCE.id) || (n == Block.FENCE_GATE.id)) return -3;
/* 200 */						 if (n == Block.TRAP_DOOR.id) return -4;
/* 201 */						 Material localMaterial = localBlock.material;
/* 202 */						 if (localMaterial == Material.LAVA) {
/* 203 */							 if (!paramEntity.J())
/* 204 */								 return -2;
/*		 */						 }
/* 206 */						 else return 0;
/*		 */					 }
/*		 */				 }
/*		 */		 }
/* 210 */		 return i != 0 ? 2 : 1;
/*		 */	 }
/*		 */ 
/*		 */	 private PathEntity a(PathPoint paramPathPoint1, PathPoint paramPathPoint2)
/*		 */	 {
/* 215 */		 int i = 1;
/* 216 */		 PathPoint localPathPoint = paramPathPoint2;
/* 217 */		 while (localPathPoint.h != null) {
/* 218 */			 i++;
/* 219 */			 localPathPoint = localPathPoint.h;
/*		 */		 }
/*		 */ 
/* 222 */		 PathPoint[] arrayOfPathPoint = new PathPoint[i];
/* 223 */		 localPathPoint = paramPathPoint2;
/* 224 */		 i--; arrayOfPathPoint[i] = localPathPoint;
/* 225 */		 while (localPathPoint.h != null) {
/* 226 */			 localPathPoint = localPathPoint.h;
/* 227 */			 i--; arrayOfPathPoint[i] = localPathPoint;
/*		 */		 }
/* 229 */		 return new PathEntity(arrayOfPathPoint);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Pathfinder
 * JD-Core Version:		0.6.0
 */