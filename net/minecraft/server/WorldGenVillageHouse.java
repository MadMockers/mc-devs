/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenVillageHouse extends WorldGenVillagePiece
/*		 */ {
/* 610 */	 private int a = -1;
/*		 */	 private final boolean b;
/*		 */ 
/*		 */	 public WorldGenVillageHouse(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 614 */		 super(paramWorldGenVillageStartPiece, paramInt1);
/*		 */ 
/* 616 */		 this.f = paramInt2;
/* 617 */		 this.e = paramStructureBoundingBox;
/* 618 */		 this.b = paramRandom.nextBoolean();
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenVillageHouse a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 623 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 5, 6, 5, paramInt4);
/*		 */ 
/* 625 */		 if (StructurePiece.a(paramList, localStructureBoundingBox) != null) {
/* 626 */			 return null;
/*		 */		 }
/*		 */ 
/* 629 */		 return new WorldGenVillageHouse(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 635 */		 if (this.a < 0) {
/* 636 */			 this.a = b(paramWorld, paramStructureBoundingBox);
/* 637 */			 if (this.a < 0) {
/* 638 */				 return true;
/*		 */			 }
/* 640 */			 this.e.a(0, this.a - this.e.e + 6 - 1, 0);
/*		 */		 }
/*		 */ 
/* 644 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 0, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*		 */ 
/* 646 */		 a(paramWorld, paramStructureBoundingBox, 0, 4, 0, 4, 4, 4, Block.LOG.id, Block.LOG.id, false);
/* 647 */		 a(paramWorld, paramStructureBoundingBox, 1, 4, 1, 3, 4, 3, Block.WOOD.id, Block.WOOD.id, false);
/*		 */ 
/* 650 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 0, 1, 0, paramStructureBoundingBox);
/* 651 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 0, 2, 0, paramStructureBoundingBox);
/* 652 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 0, 3, 0, paramStructureBoundingBox);
/* 653 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 4, 1, 0, paramStructureBoundingBox);
/* 654 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 4, 2, 0, paramStructureBoundingBox);
/* 655 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 4, 3, 0, paramStructureBoundingBox);
/* 656 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 0, 1, 4, paramStructureBoundingBox);
/* 657 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 0, 2, 4, paramStructureBoundingBox);
/* 658 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 0, 3, 4, paramStructureBoundingBox);
/* 659 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 4, 1, 4, paramStructureBoundingBox);
/* 660 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 4, 2, 4, paramStructureBoundingBox);
/* 661 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 4, 3, 4, paramStructureBoundingBox);
/* 662 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 1, 0, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
/* 663 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 1, 4, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
/* 664 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 4, 3, 3, 4, Block.WOOD.id, Block.WOOD.id, false);
/* 665 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 2, 2, paramStructureBoundingBox);
/* 666 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 2, 2, 4, paramStructureBoundingBox);
/* 667 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 4, 2, 2, paramStructureBoundingBox);
/*		 */ 
/* 670 */		 a(paramWorld, Block.WOOD.id, 0, 1, 1, 0, paramStructureBoundingBox);
/* 671 */		 a(paramWorld, Block.WOOD.id, 0, 1, 2, 0, paramStructureBoundingBox);
/* 672 */		 a(paramWorld, Block.WOOD.id, 0, 1, 3, 0, paramStructureBoundingBox);
/* 673 */		 a(paramWorld, Block.WOOD.id, 0, 2, 3, 0, paramStructureBoundingBox);
/* 674 */		 a(paramWorld, Block.WOOD.id, 0, 3, 3, 0, paramStructureBoundingBox);
/* 675 */		 a(paramWorld, Block.WOOD.id, 0, 3, 2, 0, paramStructureBoundingBox);
/* 676 */		 a(paramWorld, Block.WOOD.id, 0, 3, 1, 0, paramStructureBoundingBox);
/* 677 */		 if ((a(paramWorld, 2, 0, -1, paramStructureBoundingBox) == 0) && (a(paramWorld, 2, -1, -1, paramStructureBoundingBox) != 0)) {
/* 678 */			 a(paramWorld, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 2, 0, -1, paramStructureBoundingBox);
/*		 */		 }
/*		 */ 
/* 682 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 1, 3, 3, 3, 0, 0, false);
/*		 */ 
/* 685 */		 if (this.b) {
/* 686 */			 a(paramWorld, Block.FENCE.id, 0, 0, 5, 0, paramStructureBoundingBox);
/* 687 */			 a(paramWorld, Block.FENCE.id, 0, 1, 5, 0, paramStructureBoundingBox);
/* 688 */			 a(paramWorld, Block.FENCE.id, 0, 2, 5, 0, paramStructureBoundingBox);
/* 689 */			 a(paramWorld, Block.FENCE.id, 0, 3, 5, 0, paramStructureBoundingBox);
/* 690 */			 a(paramWorld, Block.FENCE.id, 0, 4, 5, 0, paramStructureBoundingBox);
/* 691 */			 a(paramWorld, Block.FENCE.id, 0, 0, 5, 4, paramStructureBoundingBox);
/* 692 */			 a(paramWorld, Block.FENCE.id, 0, 1, 5, 4, paramStructureBoundingBox);
/* 693 */			 a(paramWorld, Block.FENCE.id, 0, 2, 5, 4, paramStructureBoundingBox);
/* 694 */			 a(paramWorld, Block.FENCE.id, 0, 3, 5, 4, paramStructureBoundingBox);
/* 695 */			 a(paramWorld, Block.FENCE.id, 0, 4, 5, 4, paramStructureBoundingBox);
/* 696 */			 a(paramWorld, Block.FENCE.id, 0, 4, 5, 1, paramStructureBoundingBox);
/* 697 */			 a(paramWorld, Block.FENCE.id, 0, 4, 5, 2, paramStructureBoundingBox);
/* 698 */			 a(paramWorld, Block.FENCE.id, 0, 4, 5, 3, paramStructureBoundingBox);
/* 699 */			 a(paramWorld, Block.FENCE.id, 0, 0, 5, 1, paramStructureBoundingBox);
/* 700 */			 a(paramWorld, Block.FENCE.id, 0, 0, 5, 2, paramStructureBoundingBox);
/* 701 */			 a(paramWorld, Block.FENCE.id, 0, 0, 5, 3, paramStructureBoundingBox);
/*		 */		 }
/*		 */ 
/* 705 */		 if (this.b) {
/* 706 */			 i = c(Block.LADDER.id, 3);
/* 707 */			 a(paramWorld, Block.LADDER.id, i, 3, 1, 3, paramStructureBoundingBox);
/* 708 */			 a(paramWorld, Block.LADDER.id, i, 3, 2, 3, paramStructureBoundingBox);
/* 709 */			 a(paramWorld, Block.LADDER.id, i, 3, 3, 3, paramStructureBoundingBox);
/* 710 */			 a(paramWorld, Block.LADDER.id, i, 3, 4, 3, paramStructureBoundingBox);
/*		 */		 }
/*		 */ 
/* 714 */		 a(paramWorld, Block.TORCH.id, 0, 2, 3, 1, paramStructureBoundingBox);
/*		 */ 
/* 716 */		 for (int i = 0; i < 5; i++) {
/* 717 */			 for (int j = 0; j < 5; j++) {
/* 718 */				 b(paramWorld, j, 6, i, paramStructureBoundingBox);
/* 719 */				 b(paramWorld, Block.COBBLESTONE.id, 0, j, -1, i, paramStructureBoundingBox);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 723 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 2, 1);
/*		 */ 
/* 725 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageHouse
 * JD-Core Version:		0.6.0
 */