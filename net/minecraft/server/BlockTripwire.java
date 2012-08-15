/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.Cancellable;
/*		 */ import org.bukkit.event.block.Action;
/*		 */ import org.bukkit.event.entity.EntityInteractEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockTripwire extends Block
/*		 */ {
/*		 */	 public BlockTripwire(int i)
/*		 */	 {
/*	14 */		 super(i, 173, Material.ORIENTABLE);
/*	15 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 0.15625F, 1.0F);
/*	16 */		 b(true);
/*		 */	 }
/*		 */ 
/*		 */	 public int p_() {
/*	20 */		 return 10;
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/*	24 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	28 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	32 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/*	36 */		 return 30;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int i, Random random, int j) {
/*	40 */		 return Item.STRING.id;
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/*	44 */		 int i1 = world.getData(i, j, k);
/*	45 */		 boolean flag = (i1 & 0x2) == 2;
/*	46 */		 boolean flag1 = !world.t(i, j - 1, k);
/*		 */ 
/*	48 */		 if (flag != flag1) {
/*	49 */			 c(world, i, j, k, i1, 0);
/*	50 */			 world.setTypeId(i, j, k, 0);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
/*	55 */		 int l = iblockaccess.getData(i, j, k);
/*	56 */		 boolean flag = (l & 0x4) == 4;
/*	57 */		 boolean flag1 = (l & 0x2) == 2;
/*		 */ 
/*	59 */		 if (!flag1)
/*	60 */			 a(0.0F, 0.0F, 0.0F, 1.0F, 0.09375F, 1.0F);
/*	61 */		 else if (!flag)
/*	62 */			 a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/*		 */		 else
/*	64 */			 a(0.0F, 0.0625F, 0.0F, 1.0F, 0.15625F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World world, int i, int j, int k)
/*		 */	 {
/*	69 */		 int l = world.t(i, j - 1, k) ? 0 : 2;
/*		 */ 
/*	71 */		 world.setData(i, j, k, l);
/*	72 */		 e(world, i, j, k, l);
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World world, int i, int j, int k, int l, int i1) {
/*	76 */		 e(world, i, j, k, i1 | 0x1);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, int i, int j, int k, int l, EntityHuman entityhuman) {
/*	80 */		 if ((!world.isStatic) && 
/*	81 */			 (entityhuman.bC() != null) && (entityhuman.bC().id == Item.SHEARS.id))
/*	82 */			 world.setData(i, j, k, l | 0x8);
/*		 */	 }
/*		 */ 
/*		 */	 private void e(World world, int i, int j, int k, int l)
/*		 */	 {
/*	88 */		 int i1 = 0;
/*		 */ 
/*	90 */		 while (i1 < 2) {
/*	91 */			 int j1 = 1;
/*		 */ 
/*	94 */			 while (j1 < 42) {
/*	95 */				 int k1 = i + Direction.a[i1] * j1;
/*	96 */				 int l1 = k + Direction.b[i1] * j1;
/*	97 */				 int i2 = world.getTypeId(k1, j, l1);
/*		 */ 
/*	99 */				 if (i2 == Block.TRIPWIRE_SOURCE.id) {
/* 100 */					 int j2 = world.getData(k1, j, l1) & 0x3;
/*		 */ 
/* 102 */					 if (j2 == Direction.e[i1]) {
/* 103 */						 Block.TRIPWIRE_SOURCE.a(world, k1, j, l1, i2, world.getData(k1, j, l1), true, j1, l);
/*		 */					 }
/* 105 */					 break; } if (i2 != Block.TRIPWIRE.id) break;
/* 106 */				 j1++;
/*		 */			 }
/*		 */ 
/* 111 */			 i1++;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, int i, int j, int k, Entity entity)
/*		 */	 {
/* 118 */		 if ((!world.isStatic) && 
/* 119 */			 ((world.getData(i, j, k) & 0x1) != 1))
/* 120 */			 l(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random)
/*		 */	 {
/* 126 */		 if ((!world.isStatic) && 
/* 127 */			 ((world.getData(i, j, k) & 0x1) == 1))
/* 128 */			 l(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 private void l(World world, int i, int j, int k)
/*		 */	 {
/* 134 */		 int l = world.getData(i, j, k);
/* 135 */		 boolean flag = (l & 0x1) == 1;
/* 136 */		 boolean flag1 = false;
/* 137 */		 List list = world.getEntities((Entity)null, AxisAlignedBB.a().a(i + this.minX, j + this.minY, k + this.minZ, i + this.maxX, j + this.maxY, k + this.maxZ));
/*		 */ 
/* 139 */		 if (!list.isEmpty()) {
/* 140 */			 flag1 = true;
/*		 */		 }
/*		 */ 
/* 144 */		 org.bukkit.World bworld = world.getWorld();
/* 145 */		 PluginManager manager = world.getServer().getPluginManager();
/*		 */		 Iterator i$;
/* 147 */		 if ((flag != flag1) && 
/* 148 */			 (flag1)) {
/* 149 */			 for (i$ = list.iterator(); i$.hasNext(); ) { Object object = i$.next();
/* 150 */				 if (object != null)
/*		 */				 {
/*		 */					 Cancellable cancellable;
/*		 */					 Cancellable cancellable;
/* 153 */					 if ((object instanceof EntityHuman)) {
/* 154 */						 cancellable = CraftEventFactory.callPlayerInteractEvent((EntityHuman)object, Action.PHYSICAL, i, j, k, -1, null);
/* 155 */					 } else if ((object instanceof Entity)) {
/* 156 */						 cancellable = new EntityInteractEvent(((Entity)object).getBukkitEntity(), bworld.getBlockAt(i, j, k));
/* 157 */						 manager.callEvent((EntityInteractEvent)cancellable);
/*		 */					 }
/*		 */ 
/* 162 */					 if (cancellable.isCancelled()) {
/* 163 */						 return;
/*		 */					 }
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 171 */		 if ((flag1) && (!flag)) {
/* 172 */			 l |= 1;
/*		 */		 }
/*		 */ 
/* 175 */		 if ((!flag1) && (flag)) {
/* 176 */			 l &= -2;
/*		 */		 }
/*		 */ 
/* 179 */		 if (flag1 != flag) {
/* 180 */			 world.setData(i, j, k, l);
/* 181 */			 e(world, i, j, k, l);
/*		 */		 }
/*		 */ 
/* 184 */		 if (flag1)
/* 185 */			 world.a(i, j, k, this.id, p_());
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockTripwire
 * JD-Core Version:		0.6.0
 */