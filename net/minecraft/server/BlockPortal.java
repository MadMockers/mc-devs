/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Collection;
/*		 */ import java.util.HashSet;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Location;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.event.entity.EntityPortalEnterEvent;
/*		 */ import org.bukkit.event.world.PortalCreateEvent;
/*		 */ import org.bukkit.event.world.PortalCreateEvent.CreateReason;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockPortal extends BlockHalfTransparant
/*		 */ {
/*		 */	 public BlockPortal(int i, int j)
/*		 */	 {
/*	13 */		 super(i, j, Material.PORTAL, false);
/*	14 */		 b(true);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/*	18 */		 super.b(world, i, j, k, random);
/*	19 */		 if ((world.worldProvider.d()) && (random.nextInt(2000) < world.difficulty))
/*		 */		 {
/*	22 */			 for (int l = j; (!world.t(i, l, k)) && (l > 0); l--);
/*	26 */			 if ((l > 0) && (!world.s(i, l + 1, k)))
/*	27 */				 ItemMonsterEgg.a(world, 57, i + 0.5D, l + 1.1D, k + 0.5D);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k)
/*		 */	 {
/*	33 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k)
/*		 */	 {
/*	40 */		 if ((iblockaccess.getTypeId(i - 1, j, k) != this.id) && (iblockaccess.getTypeId(i + 1, j, k) != this.id)) {
/*	41 */			 float f = 0.125F;
/*	42 */			 float f1 = 0.5F;
/*	43 */			 a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
/*		 */		 } else {
/*	45 */			 float f = 0.5F;
/*	46 */			 float f1 = 0.125F;
/*	47 */			 a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	52 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	56 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean i_(World world, int i, int j, int k) {
/*	60 */		 byte b0 = 0;
/*	61 */		 byte b1 = 0;
/*		 */ 
/*	63 */		 if ((world.getTypeId(i - 1, j, k) == Block.OBSIDIAN.id) || (world.getTypeId(i + 1, j, k) == Block.OBSIDIAN.id)) {
/*	64 */			 b0 = 1;
/*		 */		 }
/*		 */ 
/*	67 */		 if ((world.getTypeId(i, j, k - 1) == Block.OBSIDIAN.id) || (world.getTypeId(i, j, k + 1) == Block.OBSIDIAN.id)) {
/*	68 */			 b1 = 1;
/*		 */		 }
/*		 */ 
/*	71 */		 if (b0 == b1) {
/*	72 */			 return false;
/*		 */		 }
/*		 */ 
/*	75 */		 Collection blocks = new HashSet();
/*	76 */		 org.bukkit.World bworld = world.getWorld();
/*		 */ 
/*	79 */		 if (world.getTypeId(i - b0, j, k - b1) == 0) {
/*	80 */			 i -= b0;
/*	81 */			 k -= b1;
/*		 */		 }
/*		 */ 
/*	87 */		 for (int l = -1; l <= 2; l++) {
/*	88 */			 for (int i1 = -1; i1 <= 3; i1++) {
/*	89 */				 boolean flag = (l == -1) || (l == 2) || (i1 == -1) || (i1 == 3);
/*		 */ 
/*	91 */				 if (((l != -1) && (l != 2)) || ((i1 != -1) && (i1 != 3))) {
/*	92 */					 int j1 = world.getTypeId(i + b0 * l, j + i1, k + b1 * l);
/*		 */ 
/*	94 */					 if (flag) {
/*	95 */						 if (j1 != Block.OBSIDIAN.id) {
/*	96 */							 return false;
/*		 */						 }
/*	98 */						 blocks.add(bworld.getBlockAt(i + b0 * l, j + i1, k + b1 * l));
/*		 */					 }
/* 100 */					 else if ((j1 != 0) && (j1 != Block.FIRE.id)) {
/* 101 */						 return false;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 108 */		 for (l = 0; l < 2; l++) {
/* 109 */			 for (int i1 = 0; i1 < 3; i1++) {
/* 110 */				 blocks.add(bworld.getBlockAt(i + b0 * l, j + i1, k + b1 * l));
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 114 */		 PortalCreateEvent event = new PortalCreateEvent(blocks, bworld, PortalCreateEvent.CreateReason.FIRE);
/* 115 */		 world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 117 */		 if (event.isCancelled()) {
/* 118 */			 return false;
/*		 */		 }
/*		 */ 
/* 122 */		 world.suppressPhysics = true;
/*		 */ 
/* 124 */		 for (l = 0; l < 2; l++) {
/* 125 */			 for (int i1 = 0; i1 < 3; i1++) {
/* 126 */				 world.setTypeId(i + b0 * l, j + i1, k + b1 * l, Block.PORTAL.id);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 130 */		 world.suppressPhysics = false;
/* 131 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l)
/*		 */	 {
/* 136 */		 byte b0 = 0;
/* 137 */		 byte b1 = 1;
/*		 */ 
/* 139 */		 if ((world.getTypeId(i - 1, j, k) == this.id) || (world.getTypeId(i + 1, j, k) == this.id)) {
/* 140 */			 b0 = 1;
/* 141 */			 b1 = 0;
/*		 */		 }
/*		 */ 
/* 146 */		 for (int i1 = j; world.getTypeId(i, i1 - 1, k) == this.id; i1--);
/* 150 */		 if (world.getTypeId(i, i1 - 1, k) != Block.OBSIDIAN.id) {
/* 151 */			 world.setTypeId(i, j, k, 0);
/*		 */		 }
/*		 */		 else
/*		 */		 {
/* 155 */			 for (int j1 = 1; (j1 < 4) && (world.getTypeId(i, i1 + j1, k) == this.id); j1++);
/* 159 */			 if ((j1 == 3) && (world.getTypeId(i, i1 + j1, k) == Block.OBSIDIAN.id)) {
/* 160 */				 boolean flag = (world.getTypeId(i - 1, j, k) == this.id) || (world.getTypeId(i + 1, j, k) == this.id);
/* 161 */				 boolean flag1 = (world.getTypeId(i, j, k - 1) == this.id) || (world.getTypeId(i, j, k + 1) == this.id);
/*		 */ 
/* 163 */				 if ((flag) && (flag1)) {
/* 164 */					 world.setTypeId(i, j, k, 0);
/*		 */				 }
/* 166 */				 else if (((world.getTypeId(i + b0, j, k + b1) != Block.OBSIDIAN.id) || (world.getTypeId(i - b0, j, k - b1) != this.id)) && ((world.getTypeId(i - b0, j, k - b1) != Block.OBSIDIAN.id) || (world.getTypeId(i + b0, j, k + b1) != this.id)))
/* 167 */					 world.setTypeId(i, j, k, 0);
/*		 */			 }
/*		 */			 else
/*		 */			 {
/* 171 */				 world.setTypeId(i, j, k, 0);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random random) {
/* 177 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, int i, int j, int k, Entity entity) {
/* 181 */		 if ((entity.vehicle == null) && (entity.passenger == null))
/*		 */		 {
/* 183 */			 EntityPortalEnterEvent event = new EntityPortalEnterEvent(entity.getBukkitEntity(), new Location(world.getWorld(), i, j, k));
/* 184 */			 world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 187 */			 entity.aa();
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockPortal
 * JD-Core Version:		0.6.0
 */