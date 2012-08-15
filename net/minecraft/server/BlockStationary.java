/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.block.BlockIgniteEvent;
/*		 */ import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
/*		 */ 
/*		 */ public class BlockStationary extends BlockFluids
/*		 */ {
/*		 */	 protected BlockStationary(int i, Material material)
/*		 */	 {
/*	13 */		 super(i, material);
/*	14 */		 b(false);
/*	15 */		 if (material == Material.LAVA)
/*	16 */			 b(true);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(IBlockAccess iblockaccess, int i, int j, int k)
/*		 */	 {
/*	21 */		 return this.material != Material.LAVA;
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/*	25 */		 super.doPhysics(world, i, j, k, l);
/*	26 */		 if (world.getTypeId(i, j, k) == this.id)
/*	27 */			 l(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 private void l(World world, int i, int j, int k)
/*		 */	 {
/*	32 */		 int l = world.getData(i, j, k);
/*		 */ 
/*	34 */		 world.suppressPhysics = true;
/*	35 */		 world.setRawTypeIdAndData(i, j, k, this.id - 1, l);
/*	36 */		 world.d(i, j, k, i, j, k);
/*	37 */		 world.a(i, j, k, this.id - 1, p_());
/*	38 */		 world.suppressPhysics = false;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/*	42 */		 if (this.material == Material.LAVA) {
/*	43 */			 int l = random.nextInt(3);
/*		 */ 
/*	49 */			 org.bukkit.World bworld = world.getWorld();
/*	50 */			 BlockIgniteEvent.IgniteCause igniteCause = BlockIgniteEvent.IgniteCause.LAVA;
/*		 */ 
/*	53 */			 for (int i1 = 0; i1 < l; i1++) {
/*	54 */				 i += random.nextInt(3) - 1;
/*	55 */				 j++;
/*	56 */				 k += random.nextInt(3) - 1;
/*	57 */				 int j1 = world.getTypeId(i, j, k);
/*	58 */				 if (j1 == 0) {
/*	59 */					 if ((!n(world, i - 1, j, k)) && (!n(world, i + 1, j, k)) && (!n(world, i, j, k - 1)) && (!n(world, i, j, k + 1)) && (!n(world, i, j - 1, k)) && (!n(world, i, j + 1, k)))
/*		 */						 continue;
/*	61 */					 org.bukkit.block.Block block = bworld.getBlockAt(i, j, k);
/*	62 */					 if ((block.getTypeId() != Block.FIRE.id) && 
/*	63 */						 (((BlockIgniteEvent)CraftEventFactory.callEvent(new BlockIgniteEvent(block, igniteCause, null))).isCancelled()))
/*		 */					 {
/*		 */						 continue;
/*		 */					 }
/*		 */ 
/*	69 */					 world.setTypeId(i, j, k, Block.FIRE.id);
/*	70 */					 return;
/*		 */				 }
/*	72 */				 if (Block.byId[j1].material.isSolid()) {
/*	73 */					 return;
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	77 */			 if (l == 0) {
/*	78 */				 i1 = i;
/*	79 */				 int j1 = k;
/*		 */ 
/*	81 */				 for (int k1 = 0; k1 < 3; k1++) {
/*	82 */					 i = i1 + random.nextInt(3) - 1;
/*	83 */					 k = j1 + random.nextInt(3) - 1;
/*	84 */					 if ((!world.isEmpty(i, j + 1, k)) || (!n(world, i, j, k)))
/*		 */						 continue;
/*	86 */					 org.bukkit.block.Block block = bworld.getBlockAt(i, j + 1, k);
/*	87 */					 if ((block.getTypeId() != Block.FIRE.id) && 
/*	88 */						 (((BlockIgniteEvent)CraftEventFactory.callEvent(new BlockIgniteEvent(block, igniteCause, null))).isCancelled()))
/*		 */					 {
/*		 */						 continue;
/*		 */					 }
/*		 */ 
/*	94 */					 world.setTypeId(i, j + 1, k, Block.FIRE.id);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean n(World world, int i, int j, int k)
/*		 */	 {
/* 102 */		 return world.getMaterial(i, j, k).isBurnable();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockStationary
 * JD-Core Version:		0.6.0
 */