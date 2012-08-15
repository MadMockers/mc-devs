/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Material;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.entity.Player;
/*		 */ import org.bukkit.entity.Sheep;
/*		 */ import org.bukkit.event.entity.SheepRegrowWoolEvent;
/*		 */ import org.bukkit.event.player.PlayerShearEntityEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntitySheep extends EntityAnimal
/*		 */ {
/*	12 */	 public static final float[][] d = { { 1.0F, 1.0F, 1.0F }, { 0.95F, 0.7F, 0.2F }, { 0.9F, 0.5F, 0.85F }, { 0.6F, 0.7F, 0.95F }, { 0.9F, 0.9F, 0.2F }, { 0.5F, 0.8F, 0.1F }, { 0.95F, 0.7F, 0.8F }, { 0.3F, 0.3F, 0.3F }, { 0.6F, 0.6F, 0.6F }, { 0.3F, 0.6F, 0.7F }, { 0.7F, 0.4F, 0.9F }, { 0.2F, 0.4F, 0.8F }, { 0.5F, 0.4F, 0.3F }, { 0.4F, 0.5F, 0.2F }, { 0.8F, 0.3F, 0.3F }, { 0.1F, 0.1F, 0.1F } };
/*		 */	 private int e;
/*	14 */	 private PathfinderGoalEatTile f = new PathfinderGoalEatTile(this);
/*		 */ 
/*		 */	 public EntitySheep(World world) {
/*	17 */		 super(world);
/*	18 */		 this.texture = "/mob/sheep.png";
/*	19 */		 a(0.9F, 1.3F);
/*	20 */		 float f = 0.23F;
/*		 */ 
/*	22 */		 getNavigation().a(true);
/*	23 */		 this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*	24 */		 this.goalSelector.a(1, new PathfinderGoalPanic(this, 0.38F));
/*	25 */		 this.goalSelector.a(2, new PathfinderGoalBreed(this, f));
/*	26 */		 this.goalSelector.a(3, new PathfinderGoalTempt(this, 0.25F, Item.WHEAT.id, false));
/*	27 */		 this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 0.25F));
/*	28 */		 this.goalSelector.a(5, this.f);
/*	29 */		 this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, f));
/*	30 */		 this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
/*	31 */		 this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean aV() {
/*	35 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 protected void bc() {
/*	39 */		 this.e = this.f.f();
/*	40 */		 super.bc();
/*		 */	 }
/*		 */ 
/*		 */	 public void d() {
/*	44 */		 if (this.world.isStatic) {
/*	45 */			 this.e = Math.max(0, this.e - 1);
/*		 */		 }
/*		 */ 
/*	48 */		 super.d();
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	52 */		 return 8;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	56 */		 super.a();
/*	57 */		 this.datawatcher.a(16, new Byte(0));
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i)
/*		 */	 {
/*	62 */		 List loot = new ArrayList();
/*		 */ 
/*	64 */		 if (!isSheared()) {
/*	65 */			 loot.add(new org.bukkit.inventory.ItemStack(Material.WOOL, 1, 0, Byte.valueOf((byte)getColor())));
/*		 */		 }
/*		 */ 
/*	68 */		 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId()
/*		 */	 {
/*	73 */		 return Block.WOOL.id;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman entityhuman) {
/*	77 */		 ItemStack itemstack = entityhuman.inventory.getItemInHand();
/*		 */ 
/*	79 */		 if ((itemstack != null) && (itemstack.id == Item.SHEARS.id) && (!isSheared()) && (!isBaby())) {
/*	80 */			 if (!this.world.isStatic)
/*		 */			 {
/*	82 */				 PlayerShearEntityEvent event = new PlayerShearEntityEvent((Player)entityhuman.getBukkitEntity(), getBukkitEntity());
/*	83 */				 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	85 */				 if (event.isCancelled()) {
/*	86 */					 return false;
/*		 */				 }
/*		 */ 
/*	90 */				 setSheared(true);
/*	91 */				 int i = 1 + this.random.nextInt(3);
/*		 */ 
/*	93 */				 for (int j = 0; j < i; j++) {
/*	94 */					 EntityItem entityitem = a(new ItemStack(Block.WOOL.id, 1, getColor()), 1.0F);
/*		 */ 
/*	96 */					 entityitem.motY += this.random.nextFloat() * 0.05F;
/*	97 */					 entityitem.motX += (this.random.nextFloat() - this.random.nextFloat()) * 0.1F;
/*	98 */					 entityitem.motZ += (this.random.nextFloat() - this.random.nextFloat()) * 0.1F;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 102 */			 itemstack.damage(1, entityhuman);
/*		 */		 }
/*		 */ 
/* 105 */		 return super.c(entityhuman);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/* 109 */		 super.b(nbttagcompound);
/* 110 */		 nbttagcompound.setBoolean("Sheared", isSheared());
/* 111 */		 nbttagcompound.setByte("Color", (byte)getColor());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/* 115 */		 super.a(nbttagcompound);
/* 116 */		 setSheared(nbttagcompound.getBoolean("Sheared"));
/* 117 */		 setColor(nbttagcompound.getByte("Color"));
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ() {
/* 121 */		 return "mob.sheep";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/* 125 */		 return "mob.sheep";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/* 129 */		 return "mob.sheep";
/*		 */	 }
/*		 */ 
/*		 */	 public int getColor() {
/* 133 */		 return this.datawatcher.getByte(16) & 0xF;
/*		 */	 }
/*		 */ 
/*		 */	 public void setColor(int i) {
/* 137 */		 byte b0 = this.datawatcher.getByte(16);
/*		 */ 
/* 139 */		 this.datawatcher.watch(16, Byte.valueOf((byte)(b0 & 0xF0 | i & 0xF)));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isSheared() {
/* 143 */		 return (this.datawatcher.getByte(16) & 0x10) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void setSheared(boolean flag) {
/* 147 */		 byte b0 = this.datawatcher.getByte(16);
/*		 */ 
/* 149 */		 if (flag)
/* 150 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(b0 | 0x10)));
/*		 */		 else
/* 152 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(b0 & 0xFFFFFFEF)));
/*		 */	 }
/*		 */ 
/*		 */	 public static int a(Random random)
/*		 */	 {
/* 157 */		 int i = random.nextInt(100);
/*		 */ 
/* 159 */		 return random.nextInt(500) == 0 ? 6 : i < 18 ? 12 : i < 15 ? 8 : i < 10 ? 7 : i < 5 ? 15 : 0;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityAnimal createChild(EntityAnimal entityanimal) {
/* 163 */		 EntitySheep entitysheep = (EntitySheep)entityanimal;
/* 164 */		 EntitySheep entitysheep1 = new EntitySheep(this.world);
/*		 */ 
/* 166 */		 if (this.random.nextBoolean())
/* 167 */			 entitysheep1.setColor(getColor());
/*		 */		 else {
/* 169 */			 entitysheep1.setColor(entitysheep.getColor());
/*		 */		 }
/*		 */ 
/* 172 */		 return entitysheep1;
/*		 */	 }
/*		 */ 
/*		 */	 public void aA()
/*		 */	 {
/* 177 */		 SheepRegrowWoolEvent event = new SheepRegrowWoolEvent((Sheep)getBukkitEntity());
/* 178 */		 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 180 */		 if (!event.isCancelled()) {
/* 181 */			 setSheared(false);
/*		 */		 }
/*		 */ 
/* 185 */		 if (isBaby()) {
/* 186 */			 int i = getAge() + 1200;
/*		 */ 
/* 188 */			 if (i > 0) {
/* 189 */				 i = 0;
/*		 */			 }
/*		 */ 
/* 192 */			 setAge(i);
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntitySheep
 * JD-Core Version:		0.6.0
 */