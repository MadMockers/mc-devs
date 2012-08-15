/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.PrintStream;
/*		 */ import java.lang.reflect.Constructor;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.LinkedHashMap;
/*		 */ import java.util.Map;
/*		 */ 
/*		 */ public class EntityTypes
/*		 */ {
/*	16 */	 private static Map b = new HashMap();
/*	17 */	 private static Map c = new HashMap();
/*	18 */	 private static Map d = new HashMap();
/*	19 */	 private static Map e = new HashMap();
/*	20 */	 private static Map f = new HashMap();
/*		 */ 
/*	22 */	 public static HashMap a = new LinkedHashMap();
/*		 */ 
/*		 */	 private static void a(Class paramClass, String paramString, int paramInt) {
/*	25 */		 b.put(paramString, paramClass);
/*	26 */		 c.put(paramClass, paramString);
/*	27 */		 d.put(Integer.valueOf(paramInt), paramClass);
/*	28 */		 e.put(paramClass, Integer.valueOf(paramInt));
/*	29 */		 f.put(paramString, Integer.valueOf(paramInt));
/*		 */	 }
/*		 */ 
/*		 */	 private static void a(Class paramClass, String paramString, int paramInt1, int paramInt2, int paramInt3) {
/*	33 */		 a(paramClass, paramString, paramInt1);
/*		 */ 
/*	35 */		 a.put(Integer.valueOf(paramInt1), new MonsterEggInfo(paramInt1, paramInt2, paramInt3));
/*		 */	 }
/*		 */ 
/*		 */	 public static Entity createEntityByName(String paramString, World paramWorld)
/*		 */	 {
/*	93 */		 Entity localEntity = null;
/*		 */		 try {
/*	95 */			 Class localClass = (Class)b.get(paramString);
/*	96 */			 if (localClass != null) localEntity = (Entity)localClass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { paramWorld }); 
/*		 */		 }
/*		 */		 catch (Exception localException)
/*		 */		 {
/*	99 */			 localException.printStackTrace();
/*		 */		 }
/* 101 */		 return localEntity;
/*		 */	 }
/*		 */ 
/*		 */	 public static Entity a(NBTTagCompound paramNBTTagCompound, World paramWorld) {
/* 105 */		 Entity localEntity = null;
/*		 */		 try {
/* 107 */			 Class localClass = (Class)b.get(paramNBTTagCompound.getString("id"));
/* 108 */			 if (localClass != null) localEntity = (Entity)localClass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { paramWorld }); 
/*		 */		 }
/*		 */		 catch (Exception localException)
/*		 */		 {
/* 111 */			 localException.printStackTrace();
/*		 */		 }
/* 113 */		 if (localEntity != null)
/* 114 */			 localEntity.e(paramNBTTagCompound);
/*		 */		 else {
/* 116 */			 System.out.println("Skipping Entity with id " + paramNBTTagCompound.getString("id"));
/*		 */		 }
/* 118 */		 return localEntity;
/*		 */	 }
/*		 */ 
/*		 */	 public static Entity a(int paramInt, World paramWorld) {
/* 122 */		 Entity localEntity = null;
/*		 */		 try {
/* 124 */			 Class localClass = (Class)d.get(Integer.valueOf(paramInt));
/* 125 */			 if (localClass != null) localEntity = (Entity)localClass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { paramWorld }); 
/*		 */		 }
/*		 */		 catch (Exception localException)
/*		 */		 {
/* 128 */			 localException.printStackTrace();
/*		 */		 }
/* 130 */		 if (localEntity == null)
/*		 */		 {
/* 132 */			 System.out.println("Skipping Entity with id " + paramInt);
/*		 */		 }
/* 134 */		 return localEntity;
/*		 */	 }
/*		 */ 
/*		 */	 public static int a(Entity paramEntity) {
/* 138 */		 Class localClass = paramEntity.getClass();
/*		 */ 
/* 140 */		 return e.containsKey(localClass) ? ((Integer)e.get(localClass)).intValue() : 0;
/*		 */	 }
/*		 */ 
/*		 */	 public static String b(Entity paramEntity) {
/* 144 */		 return (String)c.get(paramEntity.getClass());
/*		 */	 }
/*		 */ 
/*		 */	 static
/*		 */	 {
/*	39 */		 a(EntityItem.class, "Item", 1);
/*	40 */		 a(EntityExperienceOrb.class, "XPOrb", 2);
/*		 */ 
/*	42 */		 a(EntityPainting.class, "Painting", 9);
/*	43 */		 a(EntityArrow.class, "Arrow", 10);
/*	44 */		 a(EntitySnowball.class, "Snowball", 11);
/*	45 */		 a(EntityFireball.class, "Fireball", 12);
/*	46 */		 a(EntitySmallFireball.class, "SmallFireball", 13);
/*	47 */		 a(EntityEnderPearl.class, "ThrownEnderpearl", 14);
/*	48 */		 a(EntityEnderSignal.class, "EyeOfEnderSignal", 15);
/*	49 */		 a(EntityPotion.class, "ThrownPotion", 16);
/*	50 */		 a(EntityThrownExpBottle.class, "ThrownExpBottle", 17);
/*		 */ 
/*	52 */		 a(EntityTNTPrimed.class, "PrimedTnt", 20);
/*	53 */		 a(EntityFallingBlock.class, "FallingSand", 21);
/*		 */ 
/*	55 */		 a(EntityMinecart.class, "Minecart", 40);
/*	56 */		 a(EntityBoat.class, "Boat", 41);
/*		 */ 
/*	58 */		 a(EntityLiving.class, "Mob", 48);
/*	59 */		 a(EntityMonster.class, "Monster", 49);
/*		 */ 
/*	61 */		 a(EntityCreeper.class, "Creeper", 50, 894731, 0);
/*	62 */		 a(EntitySkeleton.class, "Skeleton", 51, 12698049, 4802889);
/*	63 */		 a(EntitySpider.class, "Spider", 52, 3419431, 11013646);
/*	64 */		 a(EntityGiantZombie.class, "Giant", 53);
/*	65 */		 a(EntityZombie.class, "Zombie", 54, 44975, 7969893);
/*	66 */		 a(EntitySlime.class, "Slime", 55, 5349438, 8306542);
/*	67 */		 a(EntityGhast.class, "Ghast", 56, 16382457, 12369084);
/*	68 */		 a(EntityPigZombie.class, "PigZombie", 57, 15373203, 5009705);
/*	69 */		 a(EntityEnderman.class, "Enderman", 58, 1447446, 0);
/*	70 */		 a(EntityCaveSpider.class, "CaveSpider", 59, 803406, 11013646);
/*	71 */		 a(EntitySilverfish.class, "Silverfish", 60, 7237230, 3158064);
/*	72 */		 a(EntityBlaze.class, "Blaze", 61, 16167425, 16775294);
/*	73 */		 a(EntityMagmaCube.class, "LavaSlime", 62, 3407872, 16579584);
/*	74 */		 a(EntityEnderDragon.class, "EnderDragon", 63);
/*		 */ 
/*	76 */		 a(EntityPig.class, "Pig", 90, 15771042, 14377823);
/*	77 */		 a(EntitySheep.class, "Sheep", 91, 15198183, 16758197);
/*	78 */		 a(EntityCow.class, "Cow", 92, 4470310, 10592673);
/*	79 */		 a(EntityChicken.class, "Chicken", 93, 10592673, 16711680);
/*	80 */		 a(EntitySquid.class, "Squid", 94, 2243405, 7375001);
/*	81 */		 a(EntityWolf.class, "Wolf", 95, 14144467, 13545366);
/*	82 */		 a(EntityMushroomCow.class, "MushroomCow", 96, 10489616, 12040119);
/*	83 */		 a(EntitySnowman.class, "SnowMan", 97);
/*	84 */		 a(EntityOcelot.class, "Ozelot", 98, 15720061, 5653556);
/*	85 */		 a(EntityIronGolem.class, "VillagerGolem", 99);
/*		 */ 
/*	87 */		 a(EntityVillager.class, "Villager", 120, 5651507, 12422002);
/*		 */ 
/*	89 */		 a(EntityEnderCrystal.class, "EnderCrystal", 200);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityTypes
 * JD-Core Version:		0.6.0
 */