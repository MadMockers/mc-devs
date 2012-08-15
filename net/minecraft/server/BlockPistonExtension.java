/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockPistonExtension extends Block
/*		 */ {
/*	 8 */	 private int a = -1;
/*		 */ 
/*		 */	 public BlockPistonExtension(int i, int j) {
/*	11 */		 super(i, j, Material.PISTON);
/*	12 */		 a(h);
/*	13 */		 c(0.5F);
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World world, int i, int j, int k, int l, int i1) {
/*	17 */		 super.remove(world, i, j, k, l, i1);
/*	18 */		 if ((i1 & 0x7) >= Facing.OPPOSITE_FACING.length) return;
/*	19 */		 int j1 = Facing.OPPOSITE_FACING[f(i1)];
/*		 */ 
/*	21 */		 i += Facing.b[j1];
/*	22 */		 j += Facing.c[j1];
/*	23 */		 k += Facing.d[j1];
/*	24 */		 int k1 = world.getTypeId(i, j, k);
/*		 */ 
/*	26 */		 if ((k1 == Block.PISTON.id) || (k1 == Block.PISTON_STICKY.id)) {
/*	27 */			 i1 = world.getData(i, j, k);
/*	28 */			 if (BlockPiston.f(i1)) {
/*	29 */				 Block.byId[k1].c(world, i, j, k, i1, 0);
/*	30 */				 world.setTypeId(i, j, k, 0);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i, int j) {
/*	36 */		 int k = f(j);
/*		 */ 
/*	38 */		 return (k < 6) && (i == Facing.OPPOSITE_FACING[k]) ? 107 : i == k ? this.textureId : (j & 0x8) != 0 ? this.textureId - 1 : this.a >= 0 ? this.a : 108;
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/*	42 */		 return 17;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	46 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	50 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k) {
/*	54 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k, int l) {
/*	58 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random random) {
/*	62 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
/*	66 */		 int l = world.getData(i, j, k);
/*		 */ 
/*	68 */		 switch (f(l)) {
/*		 */		 case 0:
/*	70 */			 a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
/*	71 */			 super.a(world, i, j, k, axisalignedbb, list, entity);
/*	72 */			 a(0.375F, 0.25F, 0.375F, 0.625F, 1.0F, 0.625F);
/*	73 */			 super.a(world, i, j, k, axisalignedbb, list, entity);
/*	74 */			 break;
/*		 */		 case 1:
/*	77 */			 a(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
/*	78 */			 super.a(world, i, j, k, axisalignedbb, list, entity);
/*	79 */			 a(0.375F, 0.0F, 0.375F, 0.625F, 0.75F, 0.625F);
/*	80 */			 super.a(world, i, j, k, axisalignedbb, list, entity);
/*	81 */			 break;
/*		 */		 case 2:
/*	84 */			 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
/*	85 */			 super.a(world, i, j, k, axisalignedbb, list, entity);
/*	86 */			 a(0.25F, 0.375F, 0.25F, 0.75F, 0.625F, 1.0F);
/*	87 */			 super.a(world, i, j, k, axisalignedbb, list, entity);
/*	88 */			 break;
/*		 */		 case 3:
/*	91 */			 a(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
/*	92 */			 super.a(world, i, j, k, axisalignedbb, list, entity);
/*	93 */			 a(0.25F, 0.375F, 0.0F, 0.75F, 0.625F, 0.75F);
/*	94 */			 super.a(world, i, j, k, axisalignedbb, list, entity);
/*	95 */			 break;
/*		 */		 case 4:
/*	98 */			 a(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
/*	99 */			 super.a(world, i, j, k, axisalignedbb, list, entity);
/* 100 */			 a(0.375F, 0.25F, 0.25F, 0.625F, 0.75F, 1.0F);
/* 101 */			 super.a(world, i, j, k, axisalignedbb, list, entity);
/* 102 */			 break;
/*		 */		 case 5:
/* 105 */			 a(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 106 */			 super.a(world, i, j, k, axisalignedbb, list, entity);
/* 107 */			 a(0.0F, 0.375F, 0.25F, 0.75F, 0.625F, 0.75F);
/* 108 */			 super.a(world, i, j, k, axisalignedbb, list, entity);
/*		 */		 }
/*		 */ 
/* 111 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
/* 115 */		 int l = iblockaccess.getData(i, j, k);
/*		 */ 
/* 117 */		 switch (f(l)) {
/*		 */		 case 0:
/* 119 */			 a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
/* 120 */			 break;
/*		 */		 case 1:
/* 123 */			 a(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 124 */			 break;
/*		 */		 case 2:
/* 127 */			 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
/* 128 */			 break;
/*		 */		 case 3:
/* 131 */			 a(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
/* 132 */			 break;
/*		 */		 case 4:
/* 135 */			 a(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
/* 136 */			 break;
/*		 */		 case 5:
/* 139 */			 a(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/* 144 */		 int i1 = f(world.getData(i, j, k));
/* 145 */		 if ((i1 & 0x7) >= Facing.OPPOSITE_FACING.length) return;
/* 146 */		 int j1 = world.getTypeId(i - Facing.b[i1], j - Facing.c[i1], k - Facing.d[i1]);
/*		 */ 
/* 148 */		 if ((j1 != Block.PISTON.id) && (j1 != Block.PISTON_STICKY.id))
/* 149 */			 world.setTypeId(i, j, k, 0);
/*		 */		 else
/* 151 */			 Block.byId[j1].doPhysics(world, i - Facing.b[i1], j - Facing.c[i1], k - Facing.d[i1], l);
/*		 */	 }
/*		 */ 
/*		 */	 public static int f(int i)
/*		 */	 {
/* 156 */		 return i & 0x7;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockPistonExtension
 * JD-Core Version:		0.6.0
 */