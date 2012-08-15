/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import com.google.common.collect.Iterators;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.event.block.BlockIgniteEvent;
/*		 */ import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntityLightning extends EntityWeather
/*		 */ {
/*		 */	 private int lifeTicks;
/*	11 */	 public long a = 0L;
/*		 */	 private int c;
/*		 */	 private CraftWorld cworld;
/*	16 */	 public boolean isEffect = false;
/*		 */ 
/*		 */	 public EntityLightning(World world, double d0, double d1, double d2) {
/*	19 */		 this(world, d0, d1, d2, false);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityLightning(World world, double d0, double d1, double d2, boolean isEffect)
/*		 */	 {
/*	25 */		 super(world);
/*		 */ 
/*	28 */		 this.isEffect = isEffect;
/*	29 */		 this.cworld = world.getWorld();
/*		 */ 
/*	32 */		 setPositionRotation(d0, d1, d2, 0.0F, 0.0F);
/*	33 */		 this.lifeTicks = 2;
/*	34 */		 this.a = this.random.nextLong();
/*	35 */		 this.c = (this.random.nextInt(3) + 1);
/*		 */ 
/*	38 */		 if ((!isEffect) && (world.difficulty >= 2) && (world.areChunksLoaded(MathHelper.floor(d0), MathHelper.floor(d1), MathHelper.floor(d2), 10))) {
/*	39 */			 int i = MathHelper.floor(d0);
/*	40 */			 int j = MathHelper.floor(d1);
/*	41 */			 int k = MathHelper.floor(d2);
/*		 */ 
/*	43 */			 if ((world.getTypeId(i, j, k) == 0) && (Block.FIRE.canPlace(world, i, j, k)))
/*		 */			 {
/*	45 */				 BlockIgniteEvent event = new BlockIgniteEvent(this.cworld.getBlockAt(i, j, k), BlockIgniteEvent.IgniteCause.LIGHTNING, null);
/*	46 */				 world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	48 */				 if (!event.isCancelled()) {
/*	49 */					 world.setTypeId(i, j, k, Block.FIRE.id);
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*	54 */			 for (i = 0; i < 4; i++) {
/*	55 */				 j = MathHelper.floor(d0) + this.random.nextInt(3) - 1;
/*	56 */				 k = MathHelper.floor(d1) + this.random.nextInt(3) - 1;
/*	57 */				 int l = MathHelper.floor(d2) + this.random.nextInt(3) - 1;
/*		 */ 
/*	59 */				 if ((world.getTypeId(j, k, l) != 0) || (!Block.FIRE.canPlace(world, j, k, l)))
/*		 */					 continue;
/*	61 */				 BlockIgniteEvent event = new BlockIgniteEvent(this.cworld.getBlockAt(j, k, l), BlockIgniteEvent.IgniteCause.LIGHTNING, null);
/*	62 */				 world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	64 */				 if (!event.isCancelled())
/*	65 */					 world.setTypeId(j, k, l, Block.FIRE.id);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void h_()
/*		 */	 {
/*	74 */		 super.h_();
/*	75 */		 if (this.lifeTicks == 2) {
/*	76 */			 this.world.makeSound(this.locX, this.locY, this.locZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.random.nextFloat() * 0.2F);
/*	77 */			 this.world.makeSound(this.locX, this.locY, this.locZ, "random.explode", 2.0F, 0.5F + this.random.nextFloat() * 0.2F);
/*		 */		 }
/*		 */ 
/*	80 */		 this.lifeTicks -= 1;
/*	81 */		 if (this.lifeTicks < 0) {
/*	82 */			 if (this.c == 0) {
/*	83 */				 die();
/*	84 */			 } else if (this.lifeTicks < -this.random.nextInt(10)) {
/*	85 */				 this.c -= 1;
/*	86 */				 this.lifeTicks = 1;
/*	87 */				 this.a = this.random.nextLong();
/*		 */ 
/*	89 */				 if ((!this.isEffect) && (this.world.areChunksLoaded(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ), 10))) {
/*	90 */					 int i = MathHelper.floor(this.locX);
/*	91 */					 int j = MathHelper.floor(this.locY);
/*	92 */					 int k = MathHelper.floor(this.locZ);
/*		 */ 
/*	94 */					 if ((this.world.getTypeId(i, j, k) == 0) && (Block.FIRE.canPlace(this.world, i, j, k)))
/*		 */					 {
/*	96 */						 BlockIgniteEvent event = new BlockIgniteEvent(this.cworld.getBlockAt(i, j, k), BlockIgniteEvent.IgniteCause.LIGHTNING, null);
/*	97 */						 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	99 */						 if (!event.isCancelled()) {
/* 100 */							 this.world.setTypeId(i, j, k, Block.FIRE.id);
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 108 */		 if ((this.lifeTicks >= 0) && (!this.isEffect)) {
/* 109 */			 double d0 = 3.0D;
/*		 */ 
/* 111 */			 Object[] array = this.world.getEntities(this, AxisAlignedBB.a().a(this.locX - d0, this.locY - d0, this.locZ - d0, this.locX + d0, this.locY + 6.0D + d0, this.locZ + d0)).toArray();
/* 112 */			 Iterator iterator = Iterators.forArray(array);
/*		 */ 
/* 115 */			 while (iterator.hasNext()) {
/* 116 */				 Entity entity = (Entity)iterator.next();
/*		 */ 
/* 118 */				 entity.a(this);
/*		 */			 }
/*		 */ 
/* 121 */			 this.world.s = 2;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(NBTTagCompound nbttagcompound)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 protected void b(NBTTagCompound nbttagcompound)
/*		 */	 {
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityLightning
 * JD-Core Version:		0.6.0
 */