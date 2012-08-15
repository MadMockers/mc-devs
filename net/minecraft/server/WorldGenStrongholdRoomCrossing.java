/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenStrongholdRoomCrossing extends WorldGenStrongholdPiece
/*		 */ {
/* 850 */	 private static final StructurePieceTreasure[] c = { new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 1, 3, 5), new StructurePieceTreasure(Item.REDSTONE.id, 0, 4, 9, 5), new StructurePieceTreasure(Item.COAL.id, 0, 3, 8, 10), new StructurePieceTreasure(Item.BREAD.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.APPLE.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.IRON_PICKAXE.id, 0, 1, 1, 1) };
/*		 */	 protected final WorldGenStrongholdDoorType a;
/*		 */	 protected final int b;
/*		 */ 
/*		 */	 public WorldGenStrongholdRoomCrossing(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 869 */		 super(paramInt1);
/*		 */ 
/* 871 */		 this.f = paramInt2;
/* 872 */		 this.a = a(paramRandom);
/* 873 */		 this.e = paramStructureBoundingBox;
/* 874 */		 this.b = paramRandom.nextInt(5);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 880 */		 a((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 4, 1);
/* 881 */		 b((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 4);
/* 882 */		 c((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 4);
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenStrongholdRoomCrossing a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 888 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -4, -1, 0, 11, 7, 11, paramInt4);
/*		 */ 
/* 890 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 891 */			 return null;
/*		 */		 }
/*		 */ 
/* 894 */		 return new WorldGenStrongholdRoomCrossing(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 899 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 900 */			 return false;
/*		 */		 }
/*		 */ 
/* 904 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 10, 6, 10, true, paramRandom, WorldGenStrongholdPieces.b());
/*		 */ 
/* 906 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, this.a, 4, 1, 0);
/*		 */ 
/* 908 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 10, 6, 3, 10, 0, 0, false);
/* 909 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 4, 0, 3, 6, 0, 0, false);
/* 910 */		 a(paramWorld, paramStructureBoundingBox, 10, 1, 4, 10, 3, 6, 0, 0, false);
/*		 */		 int i;
/* 912 */		 switch (this.b) {
/*		 */		 default:
/* 914 */			 break;
/*		 */		 case 0:
/* 917 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 5, 1, 5, paramStructureBoundingBox);
/* 918 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 5, 2, 5, paramStructureBoundingBox);
/* 919 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 5, 3, 5, paramStructureBoundingBox);
/* 920 */			 a(paramWorld, Block.TORCH.id, 0, 4, 3, 5, paramStructureBoundingBox);
/* 921 */			 a(paramWorld, Block.TORCH.id, 0, 6, 3, 5, paramStructureBoundingBox);
/* 922 */			 a(paramWorld, Block.TORCH.id, 0, 5, 3, 4, paramStructureBoundingBox);
/* 923 */			 a(paramWorld, Block.TORCH.id, 0, 5, 3, 6, paramStructureBoundingBox);
/* 924 */			 a(paramWorld, Block.STEP.id, 0, 4, 1, 4, paramStructureBoundingBox);
/* 925 */			 a(paramWorld, Block.STEP.id, 0, 4, 1, 5, paramStructureBoundingBox);
/* 926 */			 a(paramWorld, Block.STEP.id, 0, 4, 1, 6, paramStructureBoundingBox);
/* 927 */			 a(paramWorld, Block.STEP.id, 0, 6, 1, 4, paramStructureBoundingBox);
/* 928 */			 a(paramWorld, Block.STEP.id, 0, 6, 1, 5, paramStructureBoundingBox);
/* 929 */			 a(paramWorld, Block.STEP.id, 0, 6, 1, 6, paramStructureBoundingBox);
/* 930 */			 a(paramWorld, Block.STEP.id, 0, 5, 1, 4, paramStructureBoundingBox);
/* 931 */			 a(paramWorld, Block.STEP.id, 0, 5, 1, 6, paramStructureBoundingBox);
/* 932 */			 break;
/*		 */		 case 1:
/* 934 */			 for (i = 0; i < 5; i++) {
/* 935 */				 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 3, 1, 3 + i, paramStructureBoundingBox);
/* 936 */				 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 7, 1, 3 + i, paramStructureBoundingBox);
/* 937 */				 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 3 + i, 1, 3, paramStructureBoundingBox);
/* 938 */				 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 3 + i, 1, 7, paramStructureBoundingBox);
/*		 */			 }
/* 940 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 5, 1, 5, paramStructureBoundingBox);
/* 941 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 5, 2, 5, paramStructureBoundingBox);
/* 942 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 5, 3, 5, paramStructureBoundingBox);
/* 943 */			 a(paramWorld, Block.WATER.id, 0, 5, 4, 5, paramStructureBoundingBox);
/* 944 */			 break;
/*		 */		 case 2:
/* 946 */			 for (i = 1; i <= 9; i++) {
/* 947 */				 a(paramWorld, Block.COBBLESTONE.id, 0, 1, 3, i, paramStructureBoundingBox);
/* 948 */				 a(paramWorld, Block.COBBLESTONE.id, 0, 9, 3, i, paramStructureBoundingBox);
/*		 */			 }
/* 950 */			 for (i = 1; i <= 9; i++) {
/* 951 */				 a(paramWorld, Block.COBBLESTONE.id, 0, i, 3, 1, paramStructureBoundingBox);
/* 952 */				 a(paramWorld, Block.COBBLESTONE.id, 0, i, 3, 9, paramStructureBoundingBox);
/*		 */			 }
/* 954 */			 a(paramWorld, Block.COBBLESTONE.id, 0, 5, 1, 4, paramStructureBoundingBox);
/* 955 */			 a(paramWorld, Block.COBBLESTONE.id, 0, 5, 1, 6, paramStructureBoundingBox);
/* 956 */			 a(paramWorld, Block.COBBLESTONE.id, 0, 5, 3, 4, paramStructureBoundingBox);
/* 957 */			 a(paramWorld, Block.COBBLESTONE.id, 0, 5, 3, 6, paramStructureBoundingBox);
/* 958 */			 a(paramWorld, Block.COBBLESTONE.id, 0, 4, 1, 5, paramStructureBoundingBox);
/* 959 */			 a(paramWorld, Block.COBBLESTONE.id, 0, 6, 1, 5, paramStructureBoundingBox);
/* 960 */			 a(paramWorld, Block.COBBLESTONE.id, 0, 4, 3, 5, paramStructureBoundingBox);
/* 961 */			 a(paramWorld, Block.COBBLESTONE.id, 0, 6, 3, 5, paramStructureBoundingBox);
/* 962 */			 for (i = 1; i <= 3; i++) {
/* 963 */				 a(paramWorld, Block.COBBLESTONE.id, 0, 4, i, 4, paramStructureBoundingBox);
/* 964 */				 a(paramWorld, Block.COBBLESTONE.id, 0, 6, i, 4, paramStructureBoundingBox);
/* 965 */				 a(paramWorld, Block.COBBLESTONE.id, 0, 4, i, 6, paramStructureBoundingBox);
/* 966 */				 a(paramWorld, Block.COBBLESTONE.id, 0, 6, i, 6, paramStructureBoundingBox);
/*		 */			 }
/* 968 */			 a(paramWorld, Block.TORCH.id, 0, 5, 3, 5, paramStructureBoundingBox);
/* 969 */			 for (i = 2; i <= 8; i++) {
/* 970 */				 a(paramWorld, Block.WOOD.id, 0, 2, 3, i, paramStructureBoundingBox);
/* 971 */				 a(paramWorld, Block.WOOD.id, 0, 3, 3, i, paramStructureBoundingBox);
/* 972 */				 if ((i <= 3) || (i >= 7)) {
/* 973 */					 a(paramWorld, Block.WOOD.id, 0, 4, 3, i, paramStructureBoundingBox);
/* 974 */					 a(paramWorld, Block.WOOD.id, 0, 5, 3, i, paramStructureBoundingBox);
/* 975 */					 a(paramWorld, Block.WOOD.id, 0, 6, 3, i, paramStructureBoundingBox);
/*		 */				 }
/* 977 */				 a(paramWorld, Block.WOOD.id, 0, 7, 3, i, paramStructureBoundingBox);
/* 978 */				 a(paramWorld, Block.WOOD.id, 0, 8, 3, i, paramStructureBoundingBox);
/*		 */			 }
/* 980 */			 a(paramWorld, Block.LADDER.id, c(Block.LADDER.id, 4), 9, 1, 3, paramStructureBoundingBox);
/* 981 */			 a(paramWorld, Block.LADDER.id, c(Block.LADDER.id, 4), 9, 2, 3, paramStructureBoundingBox);
/* 982 */			 a(paramWorld, Block.LADDER.id, c(Block.LADDER.id, 4), 9, 3, 3, paramStructureBoundingBox);
/*		 */ 
/* 984 */			 a(paramWorld, paramStructureBoundingBox, paramRandom, 3, 4, 8, c, 1 + paramRandom.nextInt(4));
/*		 */		 }
/*		 */ 
/* 988 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdRoomCrossing
 * JD-Core Version:		0.6.0
 */