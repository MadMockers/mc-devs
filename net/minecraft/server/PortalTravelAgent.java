/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class PortalTravelAgent
/*		 */ {
/*	17 */	 private Random a = new Random();
/*		 */ 
/*		 */	 public void a(World world, Entity entity)
/*		 */	 {
/*	22 */		 if (world.worldProvider.dimension != 1) {
/*	23 */			 if (!b(world, entity)) {
/*	24 */				 c(world, entity);
/*	25 */				 b(world, entity);
/*		 */			 }
/*		 */		 } else {
/*	28 */			 int i = MathHelper.floor(entity.locX);
/*	29 */			 int j = MathHelper.floor(entity.locY) - 1;
/*	30 */			 int k = MathHelper.floor(entity.locZ);
/*	31 */			 byte b0 = 1;
/*	32 */			 byte b1 = 0;
/*		 */ 
/*	34 */			 for (int l = -2; l <= 2; l++) {
/*	35 */				 for (int i1 = -2; i1 <= 2; i1++) {
/*	36 */					 for (int j1 = -1; j1 < 3; j1++) {
/*	37 */						 int k1 = i + i1 * b0 + l * b1;
/*	38 */						 int l1 = j + j1;
/*	39 */						 int i2 = k + i1 * b1 - l * b0;
/*	40 */						 boolean flag = j1 < 0;
/*		 */ 
/*	42 */						 world.setTypeId(k1, l1, i2, flag ? Block.OBSIDIAN.id : 0);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	47 */			 entity.setPositionRotation(i, j, k, entity.yaw, 0.0F);
/*	48 */			 entity.motX = (entity.motY = entity.motZ = 0.0D);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b(World world, Entity entity) {
/*	53 */		 short short1 = 128;
/*	54 */		 double d0 = -1.0D;
/*	55 */		 int i = 0;
/*	56 */		 int j = 0;
/*	57 */		 int k = 0;
/*	58 */		 int l = MathHelper.floor(entity.locX);
/*	59 */		 int i1 = MathHelper.floor(entity.locZ);
/*		 */ 
/*	63 */		 for (int j1 = l - short1; j1 <= l + short1; j1++) {
/*	64 */			 double d2 = j1 + 0.5D - entity.locX;
/*		 */ 
/*	66 */			 for (int k1 = i1 - short1; k1 <= i1 + short1; k1++) {
/*	67 */				 double d3 = k1 + 0.5D - entity.locZ;
/*		 */ 
/*	69 */				 for (int l1 = world.L() - 1; l1 >= 0; l1--) {
/*	70 */					 if (world.getTypeId(j1, l1, k1) == Block.PORTAL.id) {
/*	71 */						 while (world.getTypeId(j1, l1 - 1, k1) == Block.PORTAL.id) {
/*	72 */							 l1--;
/*		 */						 }
/*		 */ 
/*	75 */						 double d1 = l1 + 0.5D - entity.locY;
/*	76 */						 double d4 = d2 * d2 + d1 * d1 + d3 * d3;
/*		 */ 
/*	78 */						 if ((d0 < 0.0D) || (d4 < d0)) {
/*	79 */							 d0 = d4;
/*	80 */							 i = j1;
/*	81 */							 j = l1;
/*	82 */							 k = k1;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	89 */		 if (d0 >= 0.0D) {
/*	90 */			 double d5 = i + 0.5D;
/*	91 */			 double d6 = j + 0.5D;
/*		 */ 
/*	93 */			 double d1 = k + 0.5D;
/*	94 */			 if (world.getTypeId(i - 1, j, k) == Block.PORTAL.id) {
/*	95 */				 d5 -= 0.5D;
/*		 */			 }
/*		 */ 
/*	98 */			 if (world.getTypeId(i + 1, j, k) == Block.PORTAL.id) {
/*	99 */				 d5 += 0.5D;
/*		 */			 }
/*		 */ 
/* 102 */			 if (world.getTypeId(i, j, k - 1) == Block.PORTAL.id) {
/* 103 */				 d1 -= 0.5D;
/*		 */			 }
/*		 */ 
/* 106 */			 if (world.getTypeId(i, j, k + 1) == Block.PORTAL.id) {
/* 107 */				 d1 += 0.5D;
/*		 */			 }
/*		 */ 
/* 110 */			 entity.setPositionRotation(d5, d6, d1, entity.yaw, 0.0F);
/* 111 */			 entity.motX = (entity.motY = entity.motZ = 0.0D);
/* 112 */			 return true;
/*		 */		 }
/* 114 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(World world, Entity entity)
/*		 */	 {
/* 119 */		 byte b0 = 16;
/* 120 */		 double d0 = -1.0D;
/* 121 */		 int i = MathHelper.floor(entity.locX);
/* 122 */		 int j = MathHelper.floor(entity.locY);
/* 123 */		 int k = MathHelper.floor(entity.locZ);
/* 124 */		 int l = i;
/* 125 */		 int i1 = j;
/* 126 */		 int j1 = k;
/* 127 */		 int k1 = 0;
/* 128 */		 int l1 = this.a.nextInt(4);
/*		 */ 
/* 146 */		 for (int i2 = i - b0; i2 <= i + b0; i2++) {
/* 147 */			 double d1 = i2 + 0.5D - entity.locX;
/*		 */ 
/* 149 */			 for (int j2 = k - b0; j2 <= k + b0; j2++) {
/* 150 */				 double d2 = j2 + 0.5D - entity.locZ;
/*		 */ 
/* 153 */				 label419: for (int l2 = world.L() - 1; l2 >= 0; l2--) {
/* 154 */					 if (world.isEmpty(i2, l2, j2)) {
/* 155 */						 while ((l2 > 0) && (world.isEmpty(i2, l2 - 1, j2))) {
/* 156 */							 l2--;
/*		 */						 }
/*		 */ 
/* 159 */						 for (int k2 = l1; k2 < l1 + 4; k2++) {
/* 160 */							 int j3 = k2 % 2;
/* 161 */							 int i3 = 1 - j3;
/* 162 */							 if (k2 % 4 >= 2) {
/* 163 */								 j3 = -j3;
/* 164 */								 i3 = -i3;
/*		 */							 }
/*		 */ 
/* 167 */							 for (int l3 = 0; l3 < 3; l3++) {
/* 168 */								 for (int k3 = 0; k3 < 4; k3++) {
/* 169 */									 for (int j4 = -1; j4 < 4; j4++) {
/* 170 */										 int i4 = i2 + (k3 - 1) * j3 + l3 * i3;
/* 171 */										 int k4 = l2 + j4;
/* 172 */										 int l4 = j2 + (k3 - 1) * i3 - l3 * j3;
/*		 */ 
/* 174 */										 if (((j4 < 0) && (!world.getMaterial(i4, k4, l4).isBuildable())) || ((j4 >= 0) && (!world.isEmpty(i4, k4, l4)))) {
/*		 */											 break label419;
/*		 */										 }
/*		 */									 }
/*		 */								 }
/*		 */							 }
/* 181 */							 double d3 = l2 + 0.5D - entity.locY;
/* 182 */							 double d4 = d1 * d1 + d3 * d3 + d2 * d2;
/* 183 */							 if ((d0 < 0.0D) || (d4 < d0)) {
/* 184 */								 d0 = d4;
/* 185 */								 l = i2;
/* 186 */								 i1 = l2;
/* 187 */								 j1 = j2;
/* 188 */								 k1 = k2 % 4;
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 196 */		 if (d0 < 0.0D) {
/* 197 */			 for (i2 = i - b0; i2 <= i + b0; i2++) {
/* 198 */				 double d1 = i2 + 0.5D - entity.locX;
/*		 */ 
/* 200 */				 for (int j2 = k - b0; j2 <= k + b0; j2++) {
/* 201 */					 double d2 = j2 + 0.5D - entity.locZ;
/*		 */ 
/* 204 */					 label758: for (int l2 = world.L() - 1; l2 >= 0; l2--) {
/* 205 */						 if (world.isEmpty(i2, l2, j2)) {
/* 206 */							 while ((l2 > 0) && (world.isEmpty(i2, l2 - 1, j2))) {
/* 207 */								 l2--;
/*		 */							 }
/*		 */ 
/* 210 */							 for (int k2 = l1; k2 < l1 + 2; k2++) {
/* 211 */								 int j3 = k2 % 2;
/* 212 */								 int i3 = 1 - j3;
/*		 */ 
/* 214 */								 for (int l3 = 0; l3 < 4; l3++) {
/* 215 */									 for (int k3 = -1; k3 < 4; k3++) {
/* 216 */										 int j4 = i2 + (l3 - 1) * j3;
/* 217 */										 int i4 = l2 + k3;
/* 218 */										 int k4 = j2 + (l3 - 1) * i3;
/* 219 */										 if (((k3 < 0) && (!world.getMaterial(j4, i4, k4).isBuildable())) || ((k3 >= 0) && (!world.isEmpty(j4, i4, k4)))) {
/*		 */											 break label758;
/*		 */										 }
/*		 */									 }
/*		 */								 }
/* 225 */								 double d3 = l2 + 0.5D - entity.locY;
/* 226 */								 double d4 = d1 * d1 + d3 * d3 + d2 * d2;
/* 227 */								 if ((d0 < 0.0D) || (d4 < d0)) {
/* 228 */									 d0 = d4;
/* 229 */									 l = i2;
/* 230 */									 i1 = l2;
/* 231 */									 j1 = j2;
/* 232 */									 k1 = k2 % 2;
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 241 */		 int i5 = l;
/* 242 */		 int j5 = i1;
/*		 */ 
/* 244 */		 int j2 = j1;
/* 245 */		 int k5 = k1 % 2;
/* 246 */		 int l5 = 1 - k5;
/*		 */ 
/* 248 */		 if (k1 % 4 >= 2) {
/* 249 */			 k5 = -k5;
/* 250 */			 l5 = -l5;
/*		 */		 }
/*		 */ 
/* 255 */		 if (d0 < 0.0D) {
/* 256 */			 if (i1 < 70) {
/* 257 */				 i1 = 70;
/*		 */			 }
/*		 */ 
/* 260 */			 if (i1 > world.L() - 10) {
/* 261 */				 i1 = world.L() - 10;
/*		 */			 }
/*		 */ 
/* 264 */			 j5 = i1;
/*		 */ 
/* 266 */			 for (int l2 = -1; l2 <= 1; l2++) {
/* 267 */				 for (int k2 = 1; k2 < 3; k2++) {
/* 268 */					 for (int j3 = -1; j3 < 3; j3++) {
/* 269 */						 int i3 = i5 + (k2 - 1) * k5 + l2 * l5;
/* 270 */						 int l3 = j5 + j3;
/* 271 */						 int k3 = j2 + (k2 - 1) * l5 - l2 * k5;
/* 272 */						 boolean flag = j3 < 0;
/* 273 */						 world.setTypeId(i3, l3, k3, flag ? Block.OBSIDIAN.id : 0);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 279 */		 for (int l2 = 0; l2 < 4; l2++) {
/* 280 */			 world.suppressPhysics = true;
/*		 */ 
/* 282 */			 for (int k2 = 0; k2 < 4; k2++) {
/* 283 */				 for (int j3 = -1; j3 < 4; j3++) {
/* 284 */					 int i3 = i5 + (k2 - 1) * k5;
/* 285 */					 int l3 = j5 + j3;
/* 286 */					 int k3 = j2 + (k2 - 1) * l5;
/* 287 */					 boolean flag = (k2 == 0) || (k2 == 3) || (j3 == -1) || (j3 == 3);
/* 288 */					 world.setTypeId(i3, l3, k3, flag ? Block.OBSIDIAN.id : Block.PORTAL.id);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 292 */			 world.suppressPhysics = false;
/*		 */ 
/* 294 */			 for (k2 = 0; k2 < 4; k2++) {
/* 295 */				 for (int j3 = -1; j3 < 4; j3++) {
/* 296 */					 int i3 = i5 + (k2 - 1) * k5;
/* 297 */					 int l3 = j5 + j3;
/* 298 */					 int k3 = j2 + (k2 - 1) * l5;
/* 299 */					 world.applyPhysics(i3, l3, k3, world.getTypeId(i3, l3, k3));
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 304 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PortalTravelAgent
 * JD-Core Version:		0.6.0
 */