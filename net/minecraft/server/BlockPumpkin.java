/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.util.BlockStateListPopulator;
/*		 */ import org.bukkit.event.block.BlockRedstoneEvent;
/*		 */ import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockPumpkin extends BlockDirectional
/*		 */ {
/*		 */	 private boolean a;
/*		 */ 
/*		 */	 protected BlockPumpkin(int i, int j, boolean flag)
/*		 */	 {
/*	14 */		 super(i, Material.PUMPKIN);
/*	15 */		 this.textureId = j;
/*	16 */		 b(true);
/*	17 */		 this.a = flag;
/*	18 */		 a(CreativeModeTab.b);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i, int j) {
/*	22 */		 if (i == 1)
/*	23 */			 return this.textureId;
/*	24 */		 if (i == 0) {
/*	25 */			 return this.textureId;
/*		 */		 }
/*	27 */		 int k = this.textureId + 1 + 16;
/*		 */ 
/*	29 */		 if (this.a) {
/*	30 */			 k++;
/*		 */		 }
/*		 */ 
/*	33 */		 return (j == 1) && (i == 4) ? k : (j == 0) && (i == 3) ? k : (j == 3) && (i == 5) ? k : (j == 2) && (i == 2) ? k : this.textureId + 16;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i)
/*		 */	 {
/*	38 */		 return i == 3 ? this.textureId + 1 + 16 : i == 0 ? this.textureId : i == 1 ? this.textureId : this.textureId + 16;
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World world, int i, int j, int k) {
/*	42 */		 super.onPlace(world, i, j, k);
/*	43 */		 if (world.suppressPhysics) return;
/*	44 */		 if ((world.getTypeId(i, j - 1, k) == Block.SNOW_BLOCK.id) && (world.getTypeId(i, j - 2, k) == Block.SNOW_BLOCK.id)) {
/*	45 */			 if (!world.isStatic)
/*		 */			 {
/*	47 */				 BlockStateListPopulator blockList = new BlockStateListPopulator(world.getWorld());
/*		 */ 
/*	49 */				 blockList.setTypeId(i, j, k, 0);
/*	50 */				 blockList.setTypeId(i, j - 1, k, 0);
/*	51 */				 blockList.setTypeId(i, j - 2, k, 0);
/*		 */ 
/*	53 */				 EntitySnowman entitysnowman = new EntitySnowman(world);
/*		 */ 
/*	55 */				 entitysnowman.setPositionRotation(i + 0.5D, j - 1.95D, k + 0.5D, 0.0F, 0.0F);
/*	56 */				 if (world.addEntity(entitysnowman, CreatureSpawnEvent.SpawnReason.BUILD_SNOWMAN)) {
/*	57 */					 blockList.updateList();
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*	62 */			 for (int l = 0; l < 120; l++)
/*	63 */				 world.a("snowshovel", i + world.random.nextDouble(), j - 2 + world.random.nextDouble() * 2.5D, k + world.random.nextDouble(), 0.0D, 0.0D, 0.0D);
/*		 */		 }
/*	65 */		 else if ((world.getTypeId(i, j - 1, k) == Block.IRON_BLOCK.id) && (world.getTypeId(i, j - 2, k) == Block.IRON_BLOCK.id)) {
/*	66 */			 boolean flag = (world.getTypeId(i - 1, j - 1, k) == Block.IRON_BLOCK.id) && (world.getTypeId(i + 1, j - 1, k) == Block.IRON_BLOCK.id);
/*	67 */			 boolean flag1 = (world.getTypeId(i, j - 1, k - 1) == Block.IRON_BLOCK.id) && (world.getTypeId(i, j - 1, k + 1) == Block.IRON_BLOCK.id);
/*		 */ 
/*	69 */			 if ((flag) || (flag1))
/*		 */			 {
/*	71 */				 BlockStateListPopulator blockList = new BlockStateListPopulator(world.getWorld());
/*		 */ 
/*	73 */				 blockList.setTypeId(i, j, k, 0);
/*	74 */				 blockList.setTypeId(i, j - 1, k, 0);
/*	75 */				 blockList.setTypeId(i, j - 2, k, 0);
/*		 */ 
/*	77 */				 if (flag) {
/*	78 */					 blockList.setTypeId(i - 1, j - 1, k, 0);
/*	79 */					 blockList.setTypeId(i + 1, j - 1, k, 0);
/*		 */				 } else {
/*	81 */					 blockList.setTypeId(i, j - 1, k - 1, 0);
/*	82 */					 blockList.setTypeId(i, j - 1, k + 1, 0);
/*		 */				 }
/*		 */ 
/*	85 */				 EntityIronGolem entityirongolem = new EntityIronGolem(world);
/*		 */ 
/*	87 */				 entityirongolem.f(true);
/*	88 */				 entityirongolem.setPositionRotation(i + 0.5D, j - 1.95D, k + 0.5D, 0.0F, 0.0F);
/*	89 */				 if (world.addEntity(entityirongolem, CreatureSpawnEvent.SpawnReason.BUILD_IRONGOLEM)) {
/*	90 */					 for (int i1 = 0; i1 < 120; i1++) {
/*	91 */						 world.a("snowballpoof", i + world.random.nextDouble(), j - 2 + world.random.nextDouble() * 3.9D, k + world.random.nextDouble(), 0.0D, 0.0D, 0.0D);
/*		 */					 }
/*		 */ 
/*	94 */					 blockList.updateList();
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k)
/*		 */	 {
/* 102 */		 int l = world.getTypeId(i, j, k);
/*		 */ 
/* 104 */		 return ((l == 0) || (Block.byId[l].material.isReplaceable())) && (world.t(i, j - 1, k));
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
/* 108 */		 int l = MathHelper.floor(entityliving.yaw * 4.0F / 360.0F + 2.5D) & 0x3;
/*		 */ 
/* 110 */		 world.setData(i, j, k, l);
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l)
/*		 */	 {
/* 115 */		 if ((Block.byId[l] != null) && (Block.byId[l].isPowerSource())) {
/* 116 */			 org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
/* 117 */			 int power = block.getBlockPower();
/*		 */ 
/* 119 */			 BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, power, power);
/* 120 */			 world.getServer().getPluginManager().callEvent(eventRedstone);
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockPumpkin
 * JD-Core Version:		0.6.0
 */