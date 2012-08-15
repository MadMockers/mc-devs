/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import org.bukkit.craftbukkit.block.CraftBlockState;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.block.BlockPlaceEvent;
/*		 */ 
/*		 */ public class ItemDoor extends Item
/*		 */ {
/*		 */	 private Material a;
/*		 */ 
/*		 */	 public ItemDoor(int i, Material material)
/*		 */	 {
/*	10 */		 super(i);
/*	11 */		 this.a = material;
/*	12 */		 this.maxStackSize = 1;
/*	13 */		 a(CreativeModeTab.d);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
/*	17 */		 if (l != 1) {
/*	18 */			 return false;
/*		 */		 }
/*	20 */		 j++;
/*		 */		 Block block;
/*		 */		 Block block;
/*	23 */		 if (this.a == Material.WOOD)
/*	24 */			 block = Block.WOODEN_DOOR;
/*		 */		 else {
/*	26 */			 block = Block.IRON_DOOR_BLOCK;
/*		 */		 }
/*		 */ 
/*	29 */		 if ((entityhuman.e(i, j, k)) && (entityhuman.e(i, j + 1, k))) {
/*	30 */			 if (!block.canPlace(world, i, j, k)) {
/*	31 */				 return false;
/*		 */			 }
/*	33 */			 int i1 = MathHelper.floor((entityhuman.yaw + 180.0F) * 4.0F / 360.0F - 0.5D) & 0x3;
/*		 */ 
/*	36 */			 if (!place(world, i, j, k, i1, block, entityhuman)) {
/*	37 */				 return false;
/*		 */			 }
/*		 */ 
/*	41 */			 itemstack.count -= 1;
/*	42 */			 return true;
/*		 */		 }
/*		 */ 
/*	45 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public static void place(World world, int i, int j, int k, int l, Block block)
/*		 */	 {
/*	52 */		 place(world, i, j, k, l, block, null);
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean place(World world, int i, int j, int k, int l, Block block, EntityHuman entityhuman)
/*		 */	 {
/*	57 */		 byte b0 = 0;
/*	58 */		 byte b1 = 0;
/*		 */ 
/*	60 */		 if (l == 0) {
/*	61 */			 b1 = 1;
/*		 */		 }
/*		 */ 
/*	64 */		 if (l == 1) {
/*	65 */			 b0 = -1;
/*		 */		 }
/*		 */ 
/*	68 */		 if (l == 2) {
/*	69 */			 b1 = -1;
/*		 */		 }
/*		 */ 
/*	72 */		 if (l == 3) {
/*	73 */			 b0 = 1;
/*		 */		 }
/*		 */ 
/*	76 */		 int i1 = (world.s(i - b0, j, k - b1) ? 1 : 0) + (world.s(i - b0, j + 1, k - b1) ? 1 : 0);
/*	77 */		 int j1 = (world.s(i + b0, j, k + b1) ? 1 : 0) + (world.s(i + b0, j + 1, k + b1) ? 1 : 0);
/*	78 */		 boolean flag = (world.getTypeId(i - b0, j, k - b1) == block.id) || (world.getTypeId(i - b0, j + 1, k - b1) == block.id);
/*	79 */		 boolean flag1 = (world.getTypeId(i + b0, j, k + b1) == block.id) || (world.getTypeId(i + b0, j + 1, k + b1) == block.id);
/*	80 */		 boolean flag2 = false;
/*		 */ 
/*	82 */		 if ((flag) && (!flag1))
/*	83 */			 flag2 = true;
/*	84 */		 else if (j1 > i1) {
/*	85 */			 flag2 = true;
/*		 */		 }
/*		 */ 
/*	88 */		 CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j, k);
/*	89 */		 world.suppressPhysics = true;
/*	90 */		 world.setTypeIdAndData(i, j, k, block.id, l);
/*		 */ 
/*	92 */		 if (entityhuman != null) {
/*	93 */			 BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, i, j, k);
/*		 */ 
/*	95 */			 if ((event.isCancelled()) || (!event.canBuild())) {
/*	96 */				 event.getBlockPlaced().setTypeIdAndData(blockState.getTypeId(), blockState.getRawData(), false);
/*	97 */				 return false;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 101 */		 world.setTypeIdAndData(i, j + 1, k, block.id, 0x8 | (flag2 ? 1 : 0));
/* 102 */		 world.suppressPhysics = false;
/* 103 */		 world.applyPhysics(i, j, k, block.id);
/* 104 */		 world.applyPhysics(i, j + 1, k, block.id);
/* 105 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemDoor
 * JD-Core Version:		0.6.0
 */