/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class Navigation
/*		 */ {
/*		 */	 private EntityLiving a;
/*		 */	 private World b;
/*		 */	 private PathEntity c;
/*		 */	 private float d;
/*		 */	 private float e;
/*	18 */	 private boolean f = false;
/*		 */	 private int g;
/*		 */	 private int h;
/*	21 */	 private Vec3D i = Vec3D.a(0.0D, 0.0D, 0.0D);
/*		 */ 
/*	23 */	 private boolean j = true;
/*	24 */	 private boolean k = false;
/*	25 */	 private boolean l = false;
/*	26 */	 private boolean m = false;
/*		 */ 
/*		 */	 public Navigation(EntityLiving paramEntityLiving, World paramWorld, float paramFloat) {
/*	29 */		 this.a = paramEntityLiving;
/*	30 */		 this.b = paramWorld;
/*	31 */		 this.e = paramFloat;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(boolean paramBoolean) {
/*	35 */		 this.l = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a() {
/*	39 */		 return this.l;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(boolean paramBoolean) {
/*	43 */		 this.k = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public void c(boolean paramBoolean)
/*		 */	 {
/*	51 */		 this.j = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	55 */		 return this.k;
/*		 */	 }
/*		 */ 
/*		 */	 public void d(boolean paramBoolean) {
/*	59 */		 this.f = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(float paramFloat) {
/*	63 */		 this.d = paramFloat;
/*		 */	 }
/*		 */ 
/*		 */	 public void e(boolean paramBoolean) {
/*	67 */		 this.m = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public PathEntity a(double paramDouble1, double paramDouble2, double paramDouble3) {
/*	71 */		 if (!k()) return null;
/*	72 */		 return this.b.a(this.a, MathHelper.floor(paramDouble1), (int)paramDouble2, MathHelper.floor(paramDouble3), this.e, this.j, this.k, this.l, this.m);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat) {
/*	76 */		 PathEntity localPathEntity = a(MathHelper.floor(paramDouble1), (int)paramDouble2, MathHelper.floor(paramDouble3));
/*	77 */		 return a(localPathEntity, paramFloat);
/*		 */	 }
/*		 */ 
/*		 */	 public PathEntity a(EntityLiving paramEntityLiving) {
/*	81 */		 if (!k()) return null;
/*	82 */		 return this.b.findPath(this.a, paramEntityLiving, this.e, this.j, this.k, this.l, this.m);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityLiving paramEntityLiving, float paramFloat) {
/*	86 */		 PathEntity localPathEntity = a(paramEntityLiving);
/*	87 */		 if (localPathEntity != null) return a(localPathEntity, paramFloat);
/*	88 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(PathEntity paramPathEntity, float paramFloat)
/*		 */	 {
/*	93 */		 if (paramPathEntity == null) {
/*	94 */			 this.c = null;
/*	95 */			 return false;
/*		 */		 }
/*	97 */		 if (!paramPathEntity.a(this.c)) this.c = paramPathEntity;
/*	98 */		 if (this.f) m();
/*	99 */		 if (this.c.d() == 0) return false;
/*		 */ 
/* 101 */		 this.d = paramFloat;
/* 102 */		 Vec3D localVec3D = i();
/* 103 */		 this.h = this.g;
/* 104 */		 this.i.a = localVec3D.a;
/* 105 */		 this.i.b = localVec3D.b;
/* 106 */		 this.i.c = localVec3D.c;
/* 107 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public PathEntity d() {
/* 111 */		 return this.c;
/*		 */	 }
/*		 */ 
/*		 */	 public void e() {
/* 115 */		 this.g += 1;
/* 116 */		 if (f()) return;
/*		 */ 
/* 118 */		 if (k()) h();
/*		 */ 
/* 120 */		 if (f()) return;
/* 121 */		 Vec3D localVec3D = this.c.a(this.a);
/* 122 */		 if (localVec3D == null) return;
/*		 */ 
/* 124 */		 this.a.getControllerMove().a(localVec3D.a, localVec3D.b, localVec3D.c, this.d);
/*		 */	 }
/*		 */ 
/*		 */	 private void h() {
/* 128 */		 Vec3D localVec3D = i();
/*		 */ 
/* 131 */		 int n = this.c.d();
/* 132 */		 for (int i1 = this.c.e(); i1 < this.c.d(); i1++) {
/* 133 */			 if (this.c.a(i1).b != (int)localVec3D.b) {
/* 134 */				 n = i1;
/* 135 */				 break;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 140 */		 float f1 = this.a.width * this.a.width;
/* 141 */		 for (int i2 = this.c.e(); i2 < n; i2++) {
/* 142 */			 if (localVec3D.distanceSquared(this.c.a(this.a, i2)) < f1) {
/* 143 */				 this.c.c(i2 + 1);
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 148 */		 i2 = MathHelper.f(this.a.width);
/* 149 */		 int i3 = (int)this.a.length + 1;
/* 150 */		 int i4 = i2;
/* 151 */		 for (int i5 = n - 1; i5 >= this.c.e(); i5--) {
/* 152 */			 if (a(localVec3D, this.c.a(this.a, i5), i2, i3, i4)) {
/* 153 */				 this.c.c(i5);
/* 154 */				 break;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 159 */		 if (this.g - this.h > 100) {
/* 160 */			 if (localVec3D.distanceSquared(this.i) < 2.25D) g();
/* 161 */			 this.h = this.g;
/* 162 */			 this.i.a = localVec3D.a;
/* 163 */			 this.i.b = localVec3D.b;
/* 164 */			 this.i.c = localVec3D.c;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean f() {
/* 169 */		 return (this.c == null) || (this.c.b());
/*		 */	 }
/*		 */ 
/*		 */	 public void g() {
/* 173 */		 this.c = null;
/*		 */	 }
/*		 */ 
/*		 */	 private Vec3D i() {
/* 177 */		 return Vec3D.a().create(this.a.locX, j(), this.a.locZ);
/*		 */	 }
/*		 */ 
/*		 */	 private int j() {
/* 181 */		 if ((!this.a.H()) || (!this.m)) return (int)(this.a.boundingBox.b + 0.5D);
/*		 */ 
/* 183 */		 int n = (int)this.a.boundingBox.b;
/* 184 */		 int i1 = this.b.getTypeId(MathHelper.floor(this.a.locX), n, MathHelper.floor(this.a.locZ));
/* 185 */		 int i2 = 0;
/* 186 */		 while ((i1 == Block.WATER.id) || (i1 == Block.STATIONARY_WATER.id)) {
/* 187 */			 n++;
/* 188 */			 i1 = this.b.getTypeId(MathHelper.floor(this.a.locX), n, MathHelper.floor(this.a.locZ));
/* 189 */			 i2++; if (i2 > 16) return (int)this.a.boundingBox.b;
/*		 */		 }
/* 191 */		 return n;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean k() {
/* 195 */		 return (this.a.onGround) || ((this.m) && (l()));
/*		 */	 }
/*		 */ 
/*		 */	 private boolean l() {
/* 199 */		 return (this.a.H()) || (this.a.J());
/*		 */	 }
/*		 */ 
/*		 */	 private void m() {
/* 203 */		 if (this.b.j(MathHelper.floor(this.a.locX), (int)(this.a.boundingBox.b + 0.5D), MathHelper.floor(this.a.locZ))) return;
/*		 */ 
/* 205 */		 for (int n = 0; n < this.c.d(); n++) {
/* 206 */			 PathPoint localPathPoint = this.c.a(n);
/* 207 */			 if (this.b.j(localPathPoint.a, localPathPoint.b, localPathPoint.c)) {
/* 208 */				 this.c.b(n - 1);
/* 209 */				 return;
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(Vec3D paramVec3D1, Vec3D paramVec3D2, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 216 */		 int n = MathHelper.floor(paramVec3D1.a);
/* 217 */		 int i1 = MathHelper.floor(paramVec3D1.c);
/*		 */ 
/* 219 */		 double d1 = paramVec3D2.a - paramVec3D1.a;
/* 220 */		 double d2 = paramVec3D2.c - paramVec3D1.c;
/* 221 */		 double d3 = d1 * d1 + d2 * d2;
/* 222 */		 if (d3 < 1.0E-008D) return false;
/*		 */ 
/* 224 */		 double d4 = 1.0D / Math.sqrt(d3);
/* 225 */		 d1 *= d4;
/* 226 */		 d2 *= d4;
/*		 */ 
/* 228 */		 paramInt1 += 2;
/* 229 */		 paramInt3 += 2;
/* 230 */		 if (!a(n, (int)paramVec3D1.b, i1, paramInt1, paramInt2, paramInt3, paramVec3D1, d1, d2)) return false;
/* 231 */		 paramInt1 -= 2;
/* 232 */		 paramInt3 -= 2;
/*		 */ 
/* 234 */		 double d5 = 1.0D / Math.abs(d1);
/* 235 */		 double d6 = 1.0D / Math.abs(d2);
/*		 */ 
/* 237 */		 double d7 = n * 1 - paramVec3D1.a;
/* 238 */		 double d8 = i1 * 1 - paramVec3D1.c;
/* 239 */		 if (d1 >= 0.0D) d7 += 1.0D;
/* 240 */		 if (d2 >= 0.0D) d8 += 1.0D;
/* 241 */		 d7 /= d1;
/* 242 */		 d8 /= d2;
/*		 */ 
/* 244 */		 int i2 = d1 < 0.0D ? -1 : 1;
/* 245 */		 int i3 = d2 < 0.0D ? -1 : 1;
/* 246 */		 int i4 = MathHelper.floor(paramVec3D2.a);
/* 247 */		 int i5 = MathHelper.floor(paramVec3D2.c);
/* 248 */		 int i6 = i4 - n;
/* 249 */		 int i7 = i5 - i1;
/* 250 */		 while ((i6 * i2 > 0) || (i7 * i3 > 0)) {
/* 251 */			 if (d7 < d8) {
/* 252 */				 d7 += d5;
/* 253 */				 n += i2;
/* 254 */				 i6 = i4 - n;
/*		 */			 } else {
/* 256 */				 d8 += d6;
/* 257 */				 i1 += i3;
/* 258 */				 i7 = i5 - i1;
/*		 */			 }
/*		 */ 
/* 261 */			 if (!a(n, (int)paramVec3D1.b, i1, paramInt1, paramInt2, paramInt3, paramVec3D1, d1, d2)) return false;
/*		 */		 }
/* 263 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Vec3D paramVec3D, double paramDouble1, double paramDouble2)
/*		 */	 {
/* 268 */		 int n = paramInt1 - paramInt4 / 2;
/* 269 */		 int i1 = paramInt3 - paramInt6 / 2;
/*		 */ 
/* 271 */		 if (!b(n, paramInt2, i1, paramInt4, paramInt5, paramInt6, paramVec3D, paramDouble1, paramDouble2)) return false;
/*		 */ 
/* 274 */		 for (int i2 = n; i2 < n + paramInt4; i2++) {
/* 275 */			 for (int i3 = i1; i3 < i1 + paramInt6; i3++) {
/* 276 */				 double d1 = i2 + 0.5D - paramVec3D.a;
/* 277 */				 double d2 = i3 + 0.5D - paramVec3D.c;
/* 278 */				 if (d1 * paramDouble1 + d2 * paramDouble2 >= 0.0D) {
/* 279 */					 int i4 = this.b.getTypeId(i2, paramInt2 - 1, i3);
/* 280 */					 if (i4 <= 0) return false;
/* 281 */					 Material localMaterial = Block.byId[i4].material;
/* 282 */					 if ((localMaterial == Material.WATER) && (!this.a.H())) return false;
/* 283 */					 if (localMaterial == Material.LAVA) return false;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 287 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Vec3D paramVec3D, double paramDouble1, double paramDouble2)
/*		 */	 {
/* 292 */		 for (int n = paramInt1; n < paramInt1 + paramInt4; n++) {
/* 293 */			 for (int i1 = paramInt2; i1 < paramInt2 + paramInt5; i1++)
/* 294 */				 for (int i2 = paramInt3; i2 < paramInt3 + paramInt6; i2++)
/*		 */				 {
/* 296 */					 double d1 = n + 0.5D - paramVec3D.a;
/* 297 */					 double d2 = i2 + 0.5D - paramVec3D.c;
/* 298 */					 if (d1 * paramDouble1 + d2 * paramDouble2 >= 0.0D) {
/* 299 */						 int i3 = this.b.getTypeId(n, i1, i2);
/* 300 */						 if ((i3 > 0) && 
/* 301 */							 (!Block.byId[i3].c(this.b, n, i1, i2))) return false;
/*		 */					 }
/*		 */				 }
/*		 */		 }
/* 305 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Navigation
 * JD-Core Version:		0.6.0
 */