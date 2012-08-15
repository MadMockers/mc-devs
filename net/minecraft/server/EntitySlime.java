/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.entity.Slime;
/*		 */ import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
/*		 */ import org.bukkit.event.entity.SlimeSplitEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntitySlime extends EntityLiving
/*		 */	 implements IMonster
/*		 */ {
/*		 */	 public float a;
/*		 */	 public float b;
/*		 */	 public float c;
/*	10 */	 private int jumpDelay = 0;
/*		 */ 
/*		 */	 public EntitySlime(World world) {
/*	13 */		 super(world);
/*	14 */		 this.texture = "/mob/slime.png";
/*	15 */		 int i = 1 << this.random.nextInt(3);
/*		 */ 
/*	17 */		 this.height = 0.0F;
/*	18 */		 this.jumpDelay = (this.random.nextInt(20) + 10);
/*	19 */		 setSize(i);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	23 */		 super.a();
/*	24 */		 this.datawatcher.a(16, new Byte(1));
/*		 */	 }
/*		 */ 
/*		 */	 public void setSize(int i) {
/*	28 */		 this.datawatcher.watch(16, new Byte((byte)i));
/*	29 */		 a(0.6F * i, 0.6F * i);
/*	30 */		 setPosition(this.locX, this.locY, this.locZ);
/*	31 */		 setHealth(getMaxHealth());
/*	32 */		 this.aV = i;
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	36 */		 int i = getSize();
/*		 */ 
/*	38 */		 return i * i;
/*		 */	 }
/*		 */ 
/*		 */	 public int getSize() {
/*	42 */		 return this.datawatcher.getByte(16);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/*	46 */		 super.b(nbttagcompound);
/*	47 */		 nbttagcompound.setInt("Size", getSize() - 1);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/*	51 */		 super.a(nbttagcompound);
/*	52 */		 setSize(nbttagcompound.getInt("Size") + 1);
/*		 */	 }
/*		 */ 
/*		 */	 protected String i() {
/*	56 */		 return "slime";
/*		 */	 }
/*		 */ 
/*		 */	 protected String o() {
/*	60 */		 return "mob.slime";
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/*	64 */		 if ((!this.world.isStatic) && (this.world.difficulty == 0) && (getSize() > 0)) {
/*	65 */			 this.dead = true;
/*		 */		 }
/*		 */ 
/*	68 */		 this.b += (this.a - this.b) * 0.5F;
/*	69 */		 this.c = this.b;
/*	70 */		 boolean flag = this.onGround;
/*		 */ 
/*	72 */		 super.h_();
/*	73 */		 if ((this.onGround) && (!flag)) {
/*	74 */			 int i = getSize();
/*		 */ 
/*	76 */			 for (int j = 0; j < i * 8; j++) {
/*	77 */				 float f = this.random.nextFloat() * 3.141593F * 2.0F;
/*	78 */				 float f1 = this.random.nextFloat() * 0.5F + 0.5F;
/*	79 */				 float f2 = MathHelper.sin(f) * i * 0.5F * f1;
/*	80 */				 float f3 = MathHelper.cos(f) * i * 0.5F * f1;
/*		 */ 
/*	82 */				 this.world.a(i(), this.locX + f2, this.boundingBox.b, this.locZ + f3, 0.0D, 0.0D, 0.0D);
/*		 */			 }
/*		 */ 
/*	85 */			 if (p()) {
/*	86 */				 this.world.makeSound(this, o(), aP(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
/*		 */			 }
/*		 */ 
/*	89 */			 this.a = -0.5F;
/*	90 */		 } else if ((!this.onGround) && (flag)) {
/*	91 */			 this.a = 1.0F;
/*		 */		 }
/*		 */ 
/*	94 */		 l();
/*		 */	 }
/*		 */ 
/*		 */	 protected void be() {
/*	98 */		 bb();
/*	99 */		 EntityHuman entityhuman = this.world.findNearbyVulnerablePlayer(this, 16.0D);
/*		 */ 
/* 101 */		 if (entityhuman != null) {
/* 102 */			 a(entityhuman, 10.0F, 20.0F);
/*		 */		 }
/*		 */ 
/* 105 */		 if ((this.onGround) && (this.jumpDelay-- <= 0)) {
/* 106 */			 this.jumpDelay = k();
/* 107 */			 if (entityhuman != null) {
/* 108 */				 this.jumpDelay /= 3;
/*		 */			 }
/*		 */ 
/* 111 */			 this.bu = true;
/* 112 */			 if (r()) {
/* 113 */				 this.world.makeSound(this, o(), aP(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
/*		 */			 }
/*		 */ 
/* 116 */			 this.br = (1.0F - this.random.nextFloat() * 2.0F);
/* 117 */			 this.bs = (1 * getSize());
/*		 */		 } else {
/* 119 */			 this.bu = false;
/* 120 */			 if (this.onGround)
/* 121 */				 this.br = (this.bs = 0.0F);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void l()
/*		 */	 {
/* 127 */		 this.a *= 0.6F;
/*		 */	 }
/*		 */ 
/*		 */	 protected int k() {
/* 131 */		 return this.random.nextInt(20) + 10;
/*		 */	 }
/*		 */ 
/*		 */	 protected EntitySlime j() {
/* 135 */		 return new EntitySlime(this.world);
/*		 */	 }
/*		 */ 
/*		 */	 public void die() {
/* 139 */		 int i = getSize();
/*		 */ 
/* 141 */		 if ((!this.world.isStatic) && (i > 1) && (getHealth() <= 0)) {
/* 142 */			 int j = 2 + this.random.nextInt(3);
/*		 */ 
/* 145 */			 SlimeSplitEvent event = new SlimeSplitEvent((Slime)getBukkitEntity(), j);
/* 146 */			 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 148 */			 if ((!event.isCancelled()) && (event.getCount() > 0)) {
/* 149 */				 j = event.getCount();
/*		 */			 } else {
/* 151 */				 super.die();
/* 152 */				 return;
/*		 */			 }
/*		 */ 
/* 156 */			 for (int k = 0; k < j; k++) {
/* 157 */				 float f = (k % 2 - 0.5F) * i / 4.0F;
/* 158 */				 float f1 = (k / 2 - 0.5F) * i / 4.0F;
/* 159 */				 EntitySlime entityslime = j();
/*		 */ 
/* 161 */				 entityslime.setSize(i / 2);
/* 162 */				 entityslime.setPositionRotation(this.locX + f, this.locY + 0.5D, this.locZ + f1, this.random.nextFloat() * 360.0F, 0.0F);
/* 163 */				 this.world.addEntity(entityslime, CreatureSpawnEvent.SpawnReason.SLIME_SPLIT);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 167 */		 super.die();
/*		 */	 }
/*		 */ 
/*		 */	 public void b_(EntityHuman entityhuman) {
/* 171 */		 if (m()) {
/* 172 */			 int i = getSize();
/*		 */ 
/* 174 */			 if ((l(entityhuman)) && (e(entityhuman) < 0.6D * i * 0.6D * i) && (entityhuman.damageEntity(DamageSource.mobAttack(this), n())))
/* 175 */				 this.world.makeSound(this, "mob.slimeattack", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean m()
/*		 */	 {
/* 181 */		 return getSize() > 1;
/*		 */	 }
/*		 */ 
/*		 */	 protected int n() {
/* 185 */		 return getSize();
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/* 189 */		 return "mob.slime";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/* 193 */		 return "mob.slime";
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId() {
/* 197 */		 return getSize() == 1 ? Item.SLIME_BALL.id : 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSpawn() {
/* 201 */		 Chunk chunk = this.world.getChunkAtWorldCoords(MathHelper.floor(this.locX), MathHelper.floor(this.locZ));
/*		 */ 
/* 203 */		 return (this.world.getWorldData().getType() != WorldType.FLAT) || (this.random.nextInt(4) == 1);
/*		 */	 }
/*		 */ 
/*		 */	 protected float aP() {
/* 207 */		 return 0.4F * getSize();
/*		 */	 }
/*		 */ 
/*		 */	 public int bf() {
/* 211 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean r() {
/* 215 */		 return getSize() > 1;
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean p() {
/* 219 */		 return getSize() > 2;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntitySlime
 * JD-Core Version:		0.6.0
 */