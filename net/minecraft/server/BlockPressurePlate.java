/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.Cancellable;
/*		 */ import org.bukkit.event.block.Action;
/*		 */ import org.bukkit.event.block.BlockRedstoneEvent;
/*		 */ import org.bukkit.event.entity.EntityInteractEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockPressurePlate extends Block
/*		 */ {
/*		 */	 private EnumMobType a;
/*		 */ 
/*		 */	 protected BlockPressurePlate(int i, int j, EnumMobType enummobtype, Material material)
/*		 */	 {
/*	16 */		 super(i, j, material);
/*	17 */		 this.a = enummobtype;
/*	18 */		 a(CreativeModeTab.d);
/*	19 */		 b(true);
/*	20 */		 float f = 0.0625F;
/*		 */ 
/*	22 */		 a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
/*		 */	 }
/*		 */ 
/*		 */	 public int p_() {
/*	26 */		 return 20;
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/*	30 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	34 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	38 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(IBlockAccess iblockaccess, int i, int j, int k) {
/*	42 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k) {
/*	46 */		 return (world.t(i, j - 1, k)) || (BlockFence.c(world.getTypeId(i, j - 1, k)));
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/*	50 */		 boolean flag = false;
/*		 */ 
/*	52 */		 if ((!world.t(i, j - 1, k)) && (!BlockFence.c(world.getTypeId(i, j - 1, k)))) {
/*	53 */			 flag = true;
/*		 */		 }
/*		 */ 
/*	56 */		 if (flag) {
/*	57 */			 c(world, i, j, k, world.getData(i, j, k), 0);
/*	58 */			 world.setTypeId(i, j, k, 0);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/*	63 */		 if ((!world.isStatic) && 
/*	64 */			 (world.getData(i, j, k) != 0))
/*	65 */			 l(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, int i, int j, int k, Entity entity)
/*		 */	 {
/*	71 */		 if ((!world.isStatic) && 
/*	72 */			 (world.getData(i, j, k) != 1))
/*	73 */			 l(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 private void l(World world, int i, int j, int k)
/*		 */	 {
/*	79 */		 boolean flag = world.getData(i, j, k) == 1;
/*	80 */		 boolean flag1 = false;
/*	81 */		 float f = 0.125F;
/*	82 */		 List list = null;
/*		 */ 
/*	84 */		 if (this.a == EnumMobType.EVERYTHING) {
/*	85 */			 list = world.getEntities((Entity)null, AxisAlignedBB.a().a(i + f, j, k + f, i + 1 - f, j + 0.25D, k + 1 - f));
/*		 */		 }
/*		 */ 
/*	88 */		 if (this.a == EnumMobType.MOBS) {
/*	89 */			 list = world.a(EntityLiving.class, AxisAlignedBB.a().a(i + f, j, k + f, i + 1 - f, j + 0.25D, k + 1 - f));
/*		 */		 }
/*		 */ 
/*	92 */		 if (this.a == EnumMobType.PLAYERS) {
/*	93 */			 list = world.a(EntityHuman.class, AxisAlignedBB.a().a(i + f, j, k + f, i + 1 - f, j + 0.25D, k + 1 - f));
/*		 */		 }
/*		 */ 
/*	96 */		 if (!list.isEmpty()) {
/*	97 */			 flag1 = true;
/*		 */		 }
/*		 */ 
/* 101 */		 org.bukkit.World bworld = world.getWorld();
/* 102 */		 PluginManager manager = world.getServer().getPluginManager();
/*		 */ 
/* 104 */		 if (flag != flag1)
/*		 */		 {
/*		 */			 Iterator i$;
/* 105 */			 if (flag1) {
/* 106 */				 for (i$ = list.iterator(); i$.hasNext(); ) { Object object = i$.next();
/* 107 */					 if (object != null)
/*		 */					 {
/*		 */						 Cancellable cancellable;
/*		 */						 Cancellable cancellable;
/* 110 */						 if ((object instanceof EntityHuman)) {
/* 111 */							 cancellable = CraftEventFactory.callPlayerInteractEvent((EntityHuman)object, Action.PHYSICAL, i, j, k, -1, null);
/* 112 */						 } else if ((object instanceof Entity)) {
/* 113 */							 cancellable = new EntityInteractEvent(((Entity)object).getBukkitEntity(), bworld.getBlockAt(i, j, k));
/* 114 */							 manager.callEvent((EntityInteractEvent)cancellable);
/*		 */						 }
/*		 */ 
/* 119 */						 if (cancellable.isCancelled()) {
/* 120 */							 return;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 126 */			 BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(bworld.getBlockAt(i, j, k), flag ? 1 : 0, flag1 ? 1 : 0);
/* 127 */			 manager.callEvent(eventRedstone);
/*		 */ 
/* 129 */			 flag1 = eventRedstone.getNewCurrent() > 0;
/*		 */		 }
/*		 */ 
/* 133 */		 if ((flag1) && (!flag)) {
/* 134 */			 world.setData(i, j, k, 1);
/* 135 */			 world.applyPhysics(i, j, k, this.id);
/* 136 */			 world.applyPhysics(i, j - 1, k, this.id);
/* 137 */			 world.d(i, j, k, i, j, k);
/* 138 */			 world.makeSound(i + 0.5D, j + 0.1D, k + 0.5D, "random.click", 0.3F, 0.6F);
/*		 */		 }
/*		 */ 
/* 141 */		 if ((!flag1) && (flag)) {
/* 142 */			 world.setData(i, j, k, 0);
/* 143 */			 world.applyPhysics(i, j, k, this.id);
/* 144 */			 world.applyPhysics(i, j - 1, k, this.id);
/* 145 */			 world.d(i, j, k, i, j, k);
/* 146 */			 world.makeSound(i + 0.5D, j + 0.1D, k + 0.5D, "random.click", 0.3F, 0.5F);
/*		 */		 }
/*		 */ 
/* 149 */		 if (flag1)
/* 150 */			 world.a(i, j, k, this.id, p_());
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World world, int i, int j, int k, int l, int i1)
/*		 */	 {
/* 155 */		 if (i1 > 0) {
/* 156 */			 world.applyPhysics(i, j, k, this.id);
/* 157 */			 world.applyPhysics(i, j - 1, k, this.id);
/*		 */		 }
/*		 */ 
/* 160 */		 super.remove(world, i, j, k, l, i1);
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
/* 164 */		 boolean flag = iblockaccess.getData(i, j, k) == 1;
/* 165 */		 float f = 0.0625F;
/*		 */ 
/* 167 */		 if (flag)
/* 168 */			 a(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
/*		 */		 else
/* 170 */			 a(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l)
/*		 */	 {
/* 175 */		 return iblockaccess.getData(i, j, k) > 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(World world, int i, int j, int k, int l) {
/* 179 */		 return world.getData(i, j, k) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isPowerSource() {
/* 183 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void f() {
/* 187 */		 float f = 0.5F;
/* 188 */		 float f1 = 0.125F;
/* 189 */		 float f2 = 0.5F;
/*		 */ 
/* 191 */		 a(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
/*		 */	 }
/*		 */ 
/*		 */	 public int e() {
/* 195 */		 return 1;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockPressurePlate
 * JD-Core Version:		0.6.0
 */