/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public abstract class EntityAnimal extends EntityAgeable
/*		 */	 implements IAnimal
/*		 */ {
/*		 */	 private int love;
/*	18 */	 private int e = 0;
/*		 */ 
/*		 */	 public EntityAnimal(World paramWorld) {
/*	21 */		 super(paramWorld);
/*		 */	 }
/*		 */ 
/*		 */	 protected void bd()
/*		 */	 {
/*	26 */		 if (getAge() != 0) this.love = 0;
/*	27 */		 super.bd();
/*		 */	 }
/*		 */ 
/*		 */	 public void d()
/*		 */	 {
/*	32 */		 super.d();
/*		 */ 
/*	34 */		 if (getAge() != 0) this.love = 0;
/*		 */ 
/*	36 */		 if (this.love > 0) {
/*	37 */			 this.love -= 1;
/*	38 */			 String str = "heart";
/*	39 */			 if (this.love % 10 == 0) {
/*	40 */				 double d1 = this.random.nextGaussian() * 0.02D;
/*	41 */				 double d2 = this.random.nextGaussian() * 0.02D;
/*	42 */				 double d3 = this.random.nextGaussian() * 0.02D;
/*	43 */				 this.world.a(str, this.locX + this.random.nextFloat() * this.width * 2.0F - this.width, this.locY + 0.5D + this.random.nextFloat() * this.length, this.locZ + this.random.nextFloat() * this.width * 2.0F - this.width, d1, d2, d3);
/*		 */			 }
/*		 */		 } else {
/*	46 */			 this.e = 0;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(Entity paramEntity, float paramFloat)
/*		 */	 {
/*		 */		 Object localObject;
/*	52 */		 if ((paramEntity instanceof EntityHuman)) {
/*	53 */			 if (paramFloat < 3.0F) {
/*	54 */				 double d1 = paramEntity.locX - this.locX;
/*	55 */				 double d2 = paramEntity.locZ - this.locZ;
/*	56 */				 this.yaw = ((float)(Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - 90.0F);
/*		 */ 
/*	58 */				 this.b = true;
/*		 */			 }
/*		 */ 
/*	61 */			 localObject = (EntityHuman)paramEntity;
/*	62 */			 if ((((EntityHuman)localObject).bC() == null) || (!b(((EntityHuman)localObject).bC())))
/*		 */			 {
/*	64 */				 this.target = null;
/*		 */			 }
/*		 */		 }
/*	67 */		 else if ((paramEntity instanceof EntityAnimal)) {
/*	68 */			 localObject = (EntityAnimal)paramEntity;
/*	69 */			 if ((getAge() > 0) && (((EntityAnimal)localObject).getAge() < 0)) {
/*	70 */				 if (paramFloat < 2.5D)
/*	71 */					 this.b = true;
/*		 */			 }
/*	73 */			 else if ((this.love > 0) && (((EntityAnimal)localObject).love > 0)) {
/*	74 */				 if (((EntityAnimal)localObject).target == null) ((EntityAnimal)localObject).target = this;
/*		 */ 
/*	76 */				 if ((((EntityAnimal)localObject).target == this) && (paramFloat < 3.5D)) {
/*	77 */					 localObject.love += 1;
/*	78 */					 this.love += 1;
/*	79 */					 this.e += 1;
/*	80 */					 if (this.e % 4 == 0) {
/*	81 */						 this.world.a("heart", this.locX + this.random.nextFloat() * this.width * 2.0F - this.width, this.locY + 0.5D + this.random.nextFloat() * this.length, this.locZ + this.random.nextFloat() * this.width * 2.0F - this.width, 0.0D, 0.0D, 0.0D);
/*		 */					 }
/*		 */ 
/*	84 */					 if (this.e == 60) c((EntityAnimal)paramEntity); 
/*		 */				 } else {
/*	85 */					 this.e = 0;
/*		 */				 }
/*		 */			 } else {
/*	87 */				 this.e = 0;
/*	88 */				 this.target = null;
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void c(EntityAnimal paramEntityAnimal)
/*		 */	 {
/*	95 */		 EntityAnimal localEntityAnimal = createChild(paramEntityAnimal);
/*	96 */		 if (localEntityAnimal != null) {
/*	97 */			 setAge(6000);
/*	98 */			 paramEntityAnimal.setAge(6000);
/*	99 */			 this.love = 0;
/* 100 */			 this.e = 0;
/* 101 */			 this.target = null;
/* 102 */			 paramEntityAnimal.target = null;
/* 103 */			 paramEntityAnimal.e = 0;
/* 104 */			 paramEntityAnimal.love = 0;
/* 105 */			 localEntityAnimal.setAge(-24000);
/* 106 */			 localEntityAnimal.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
/* 107 */			 for (int i = 0; i < 7; i++) {
/* 108 */				 double d1 = this.random.nextGaussian() * 0.02D;
/* 109 */				 double d2 = this.random.nextGaussian() * 0.02D;
/* 110 */				 double d3 = this.random.nextGaussian() * 0.02D;
/* 111 */				 this.world.a("heart", this.locX + this.random.nextFloat() * this.width * 2.0F - this.width, this.locY + 0.5D + this.random.nextFloat() * this.length, this.locZ + this.random.nextFloat() * this.width * 2.0F - this.width, d1, d2, d3);
/*		 */			 }
/* 113 */			 this.world.addEntity(localEntityAnimal);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public abstract EntityAnimal createChild(EntityAnimal paramEntityAnimal);
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource paramDamageSource, int paramInt) {
/* 121 */		 this.c = 60;
/* 122 */		 this.target = null;
/* 123 */		 this.love = 0;
/* 124 */		 return super.damageEntity(paramDamageSource, paramInt);
/*		 */	 }
/*		 */ 
/*		 */	 public float a(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 129 */		 if (this.world.getTypeId(paramInt1, paramInt2 - 1, paramInt3) == Block.GRASS.id) return 10.0F;
/* 130 */		 return this.world.o(paramInt1, paramInt2, paramInt3) - 0.5F;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/* 135 */		 super.b(paramNBTTagCompound);
/* 136 */		 paramNBTTagCompound.setInt("InLove", this.love);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/* 141 */		 super.a(paramNBTTagCompound);
/* 142 */		 this.love = paramNBTTagCompound.getInt("InLove");
/*		 */	 }
/*		 */ 
/*		 */	 protected Entity findTarget()
/*		 */	 {
/* 147 */		 if (this.c > 0) return null;
/*		 */ 
/* 149 */		 float f = 8.0F;
/*		 */		 List localList;
/*		 */		 Iterator localIterator;
/*		 */		 Object localObject;
/* 150 */		 if (this.love > 0) {
/* 151 */			 localList = this.world.a(getClass(), this.boundingBox.grow(f, f, f));
/* 152 */			 for (localIterator = localList.iterator(); localIterator.hasNext(); ) { localObject = (EntityAnimal)localIterator.next();
/* 153 */				 if ((localObject != this) && (((EntityAnimal)localObject).love > 0)) {
/* 154 */					 return localObject;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 158 */		 else if (getAge() == 0) {
/* 159 */			 localList = this.world.a(EntityHuman.class, this.boundingBox.grow(f, f, f));
/* 160 */			 for (localIterator = localList.iterator(); localIterator.hasNext(); ) { localObject = (EntityHuman)localIterator.next();
/* 161 */				 if ((((EntityHuman)localObject).bC() != null) && (b(((EntityHuman)localObject).bC())))
/* 162 */					 return localObject;
/*		 */			 }
/*		 */		 }
/* 165 */		 else if (getAge() > 0) {
/* 166 */			 localList = this.world.a(getClass(), this.boundingBox.grow(f, f, f));
/* 167 */			 for (localIterator = localList.iterator(); localIterator.hasNext(); ) { localObject = (EntityAnimal)localIterator.next();
/* 168 */				 if ((localObject != this) && (((EntityAnimal)localObject).getAge() < 0)) {
/* 169 */					 return localObject;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 174 */		 return (Entity)null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSpawn()
/*		 */	 {
/* 179 */		 int i = MathHelper.floor(this.locX);
/* 180 */		 int j = MathHelper.floor(this.boundingBox.b);
/* 181 */		 int k = MathHelper.floor(this.locZ);
/* 182 */		 return (this.world.getTypeId(i, j - 1, k) == Block.GRASS.id) && (this.world.k(i, j, k) > 8) && (super.canSpawn());
/*		 */	 }
/*		 */ 
/*		 */	 public int aG()
/*		 */	 {
/* 187 */		 return 120;
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean ba()
/*		 */	 {
/* 192 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected int getExpValue(EntityHuman paramEntityHuman)
/*		 */	 {
/* 197 */		 return 1 + this.world.random.nextInt(3);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b(ItemStack paramItemStack) {
/* 201 */		 return paramItemStack.id == Item.WHEAT.id;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman paramEntityHuman)
/*		 */	 {
/* 206 */		 ItemStack localItemStack = paramEntityHuman.inventory.getItemInHand();
/* 207 */		 if ((localItemStack != null) && (b(localItemStack)) && (getAge() == 0)) {
/* 208 */			 if (!paramEntityHuman.abilities.canInstantlyBuild) {
/* 209 */				 localItemStack.count -= 1;
/* 210 */				 if (localItemStack.count <= 0) {
/* 211 */					 paramEntityHuman.inventory.setItem(paramEntityHuman.inventory.itemInHandIndex, null);
/*		 */				 }
/*		 */			 }
/* 214 */			 this.love = 600;
/*		 */ 
/* 216 */			 this.target = null;
/* 217 */			 for (int i = 0; i < 7; i++) {
/* 218 */				 double d1 = this.random.nextGaussian() * 0.02D;
/* 219 */				 double d2 = this.random.nextGaussian() * 0.02D;
/* 220 */				 double d3 = this.random.nextGaussian() * 0.02D;
/* 221 */				 this.world.a("heart", this.locX + this.random.nextFloat() * this.width * 2.0F - this.width, this.locY + 0.5D + this.random.nextFloat() * this.length, this.locZ + this.random.nextFloat() * this.width * 2.0F - this.width, d1, d2, d3);
/*		 */			 }
/*		 */ 
/* 224 */			 return true;
/*		 */		 }
/* 226 */		 return super.c(paramEntityHuman);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean s() {
/* 230 */		 return this.love > 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void t() {
/* 234 */		 this.love = 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean mate(EntityAnimal paramEntityAnimal) {
/* 238 */		 if (paramEntityAnimal == this) return false;
/* 239 */		 if (paramEntityAnimal.getClass() != getClass()) return false;
/* 240 */		 return (s()) && (paramEntityAnimal.s());
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityAnimal
 * JD-Core Version:		0.6.0
 */