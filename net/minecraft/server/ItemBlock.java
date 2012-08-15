/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import org.bukkit.craftbukkit.block.CraftBlockState;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.block.BlockPlaceEvent;
/*		 */ 
/*		 */ public class ItemBlock extends Item
/*		 */ {
/*		 */	 private int id;
/*		 */ 
/*		 */	 public ItemBlock(int i)
/*		 */	 {
/*	10 */		 super(i);
/*	11 */		 this.id = (i + 256);
/*	12 */		 c(Block.byId[(i + 256)].a(2));
/*		 */	 }
/*		 */ 
/*		 */	 public int f() {
/*	16 */		 return this.id;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
/*	20 */		 int clickedX = i; int clickedY = j; int clickedZ = k;
/*	21 */		 int i1 = world.getTypeId(i, j, k);
/*		 */ 
/*	23 */		 if (i1 == Block.SNOW.id) {
/*	24 */			 l = 1;
/*	25 */		 } else if ((i1 != Block.VINE.id) && (i1 != Block.LONG_GRASS.id) && (i1 != Block.DEAD_BUSH.id)) {
/*	26 */			 if (l == 0) {
/*	27 */				 j--;
/*		 */			 }
/*		 */ 
/*	30 */			 if (l == 1) {
/*	31 */				 j++;
/*		 */			 }
/*		 */ 
/*	34 */			 if (l == 2) {
/*	35 */				 k--;
/*		 */			 }
/*		 */ 
/*	38 */			 if (l == 3) {
/*	39 */				 k++;
/*		 */			 }
/*		 */ 
/*	42 */			 if (l == 4) {
/*	43 */				 i--;
/*		 */			 }
/*		 */ 
/*	46 */			 if (l == 5) {
/*	47 */				 i++;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	51 */		 if (itemstack.count == 0)
/*	52 */			 return false;
/*	53 */		 if (!entityhuman.e(i, j, k))
/*	54 */			 return false;
/*	55 */		 if ((j == 255) && (Block.byId[this.id].material.isBuildable())) {
/*	56 */			 return false;
/*		 */		 }
/*		 */ 
/*	60 */		 int id = this.id;
/*	61 */		 if ((l == -1) && ((itemstack.getItem() instanceof ItemStep))) {
/*	62 */			 if (this.id == Block.STEP.id)
/*	63 */				 id = Block.DOUBLE_STEP.id;
/*	64 */			 else if (this.id == Block.WOOD_STEP.id) {
/*	65 */				 id = Block.WOOD_DOUBLE_STEP.id;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	69 */		 if ((id != this.id) || (world.mayPlace(this.id, i, j, k, false, l, entityhuman))) {
/*	70 */			 Block block = Block.byId[id];
/*		 */ 
/*	72 */			 CraftBlockState replacedBlockState = CraftBlockState.getBlockState(world, i, j, k);
/*		 */ 
/*	74 */			 world.suppressPhysics = true;
/*	75 */			 world.setTypeIdAndData(i, j, k, id, filterData(itemstack.getData()));
/*	76 */			 BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, replacedBlockState, clickedX, clickedY, clickedZ);
/*	77 */			 id = world.getTypeId(i, j, k);
/*	78 */			 int data = world.getData(i, j, k);
/*	79 */			 replacedBlockState.update(true);
/*	80 */			 world.suppressPhysics = false;
/*		 */ 
/*	82 */			 if ((event.isCancelled()) || (!event.canBuild())) {
/*	83 */				 return true;
/*		 */			 }
/*	85 */			 if (world.setTypeIdAndData(i, j, k, id, data)) {
/*	86 */				 if ((world.getTypeId(i, j, k) == id) && (Block.byId[id] != null)) {
/*	87 */					 Block.byId[id].postPlace(world, i, j, k, l, f, f1, f2);
/*	88 */					 Block.byId[id].postPlace(world, i, j, k, entityhuman);
/*		 */				 }
/*		 */ 
/*	92 */				 world.makeSound(i + 0.5F, j + 0.5F, k + 0.5F, block.stepSound.getName(), (block.stepSound.getVolume1() + 1.0F) / 2.0F, block.stepSound.getVolume2() * 0.8F);
/*	93 */				 itemstack.count -= 1;
/*		 */			 }
/*		 */ 
/*	96 */			 return true;
/*		 */		 }
/*	98 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public String c(ItemStack itemstack)
/*		 */	 {
/* 103 */		 return Block.byId[this.id].a();
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/* 107 */		 return Block.byId[this.id].a();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemBlock
 * JD-Core Version:		0.6.0
 */