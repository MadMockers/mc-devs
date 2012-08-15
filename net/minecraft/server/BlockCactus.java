/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.entity.EntityDamageByBlockEvent;
/*		 */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockCactus extends Block
/*		 */ {
/*		 */	 protected BlockCactus(int i, int j)
/*		 */	 {
/*	10 */		 super(i, j, Material.CACTUS);
/*	11 */		 b(true);
/*	12 */		 a(CreativeModeTab.c);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/*	16 */		 if (world.isEmpty(i, j + 1, k))
/*		 */		 {
/*	19 */			 for (int l = 1; world.getTypeId(i, j - l, k) == this.id; l++);
/*	23 */			 if (l < 3) {
/*	24 */				 int i1 = world.getData(i, j, k);
/*		 */ 
/*	26 */				 if (i1 == 15) {
/*	27 */					 CraftEventFactory.handleBlockGrowEvent(world, i, j + 1, k, this.id, 0);
/*	28 */					 world.setData(i, j, k, 0);
/*		 */				 } else {
/*	30 */					 world.setData(i, j, k, i1 + 1);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/*	37 */		 float f = 0.0625F;
/*		 */ 
/*	39 */		 return AxisAlignedBB.a().a(i + f, j, k + f, i + 1 - f, j + 1 - f, k + 1 - f);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i) {
/*	43 */		 return i == 0 ? this.textureId + 1 : i == 1 ? this.textureId - 1 : this.textureId;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	47 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	51 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/*	55 */		 return 13;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k) {
/*	59 */		 return !super.canPlace(world, i, j, k) ? false : d(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/*	63 */		 if (!d(world, i, j, k)) {
/*	64 */			 c(world, i, j, k, world.getData(i, j, k), 0);
/*	65 */			 world.setTypeId(i, j, k, 0);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d(World world, int i, int j, int k) {
/*	70 */		 if (world.getMaterial(i - 1, j, k).isBuildable())
/*	71 */			 return false;
/*	72 */		 if (world.getMaterial(i + 1, j, k).isBuildable())
/*	73 */			 return false;
/*	74 */		 if (world.getMaterial(i, j, k - 1).isBuildable())
/*	75 */			 return false;
/*	76 */		 if (world.getMaterial(i, j, k + 1).isBuildable()) {
/*	77 */			 return false;
/*		 */		 }
/*	79 */		 int l = world.getTypeId(i, j - 1, k);
/*		 */ 
/*	81 */		 return (l == Block.CACTUS.id) || (l == Block.SAND.id);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, int i, int j, int k, Entity entity)
/*		 */	 {
/*	87 */		 if ((entity instanceof EntityLiving)) {
/*	88 */			 org.bukkit.block.Block damager = world.getWorld().getBlockAt(i, j, k);
/*	89 */			 org.bukkit.entity.Entity damagee = entity == null ? null : entity.getBukkitEntity();
/*		 */ 
/*	91 */			 EntityDamageByBlockEvent event = new EntityDamageByBlockEvent(damager, damagee, EntityDamageEvent.DamageCause.CONTACT, 1);
/*	92 */			 world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	94 */			 if (!event.isCancelled()) {
/*	95 */				 damagee.setLastDamageCause(event);
/*	96 */				 entity.damageEntity(DamageSource.CACTUS, event.getDamage());
/*		 */			 }
/*	98 */			 return;
/*		 */		 }
/*		 */ 
/* 102 */		 entity.damageEntity(DamageSource.CACTUS, 1);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockCactus
 * JD-Core Version:		0.6.0
 */