/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.HashSet;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.Set;
/*		 */ 
/*		 */ public class EntityTracker
/*		 */ {
/*		 */	 private final WorldServer world;
/*	11 */	 private Set b = new HashSet();
/*	12 */	 public IntHashMap trackedEntities = new IntHashMap();
/*		 */	 private int d;
/*		 */ 
/*		 */	 public EntityTracker(WorldServer worldserver)
/*		 */	 {
/*	16 */		 this.world = worldserver;
/*	17 */		 this.d = worldserver.getMinecraftServer().getServerConfigurationManager().a();
/*		 */	 }
/*		 */ 
/*		 */	 public synchronized void track(Entity entity)
/*		 */	 {
/*	22 */		 if ((entity instanceof EntityPlayer)) {
/*	23 */			 addEntity(entity, 512, 2);
/*	24 */			 EntityPlayer entityplayer = (EntityPlayer)entity;
/*	25 */			 Iterator iterator = this.b.iterator();
/*		 */ 
/*	27 */			 while (iterator.hasNext()) {
/*	28 */				 EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)iterator.next();
/*		 */ 
/*	30 */				 if (entitytrackerentry.tracker != entityplayer)
/*	31 */					 entitytrackerentry.updatePlayer(entityplayer);
/*		 */			 }
/*		 */		 }
/*	34 */		 else if ((entity instanceof EntityFishingHook)) {
/*	35 */			 addEntity(entity, 64, 5, true);
/*	36 */		 } else if ((entity instanceof EntityArrow)) {
/*	37 */			 addEntity(entity, 64, 20, false);
/*	38 */		 } else if ((entity instanceof EntitySmallFireball)) {
/*	39 */			 addEntity(entity, 64, 10, false);
/*	40 */		 } else if ((entity instanceof EntityFireball)) {
/*	41 */			 addEntity(entity, 64, 10, false);
/*	42 */		 } else if ((entity instanceof EntitySnowball)) {
/*	43 */			 addEntity(entity, 64, 10, true);
/*	44 */		 } else if ((entity instanceof EntityEnderPearl)) {
/*	45 */			 addEntity(entity, 64, 10, true);
/*	46 */		 } else if ((entity instanceof EntityEnderSignal)) {
/*	47 */			 addEntity(entity, 64, 4, true);
/*	48 */		 } else if ((entity instanceof EntityEgg)) {
/*	49 */			 addEntity(entity, 64, 10, true);
/*	50 */		 } else if ((entity instanceof EntityPotion)) {
/*	51 */			 addEntity(entity, 64, 10, true);
/*	52 */		 } else if ((entity instanceof EntityThrownExpBottle)) {
/*	53 */			 addEntity(entity, 64, 10, true);
/*	54 */		 } else if ((entity instanceof EntityItem)) {
/*	55 */			 addEntity(entity, 64, 20, true);
/*	56 */		 } else if ((entity instanceof EntityMinecart)) {
/*	57 */			 addEntity(entity, 80, 3, true);
/*	58 */		 } else if ((entity instanceof EntityBoat)) {
/*	59 */			 addEntity(entity, 80, 3, true);
/*	60 */		 } else if ((entity instanceof EntitySquid)) {
/*	61 */			 addEntity(entity, 64, 3, true);
/*	62 */		 } else if ((entity instanceof IAnimal)) {
/*	63 */			 addEntity(entity, 80, 3, true);
/*	64 */		 } else if ((entity instanceof EntityEnderDragon)) {
/*	65 */			 addEntity(entity, 160, 3, true);
/*	66 */		 } else if ((entity instanceof EntityTNTPrimed)) {
/*	67 */			 addEntity(entity, 160, 10, true);
/*	68 */		 } else if ((entity instanceof EntityFallingBlock)) {
/*	69 */			 addEntity(entity, 160, 20, true);
/*	70 */		 } else if ((entity instanceof EntityPainting)) {
/*	71 */			 addEntity(entity, 160, 2147483647, false);
/*	72 */		 } else if ((entity instanceof EntityExperienceOrb)) {
/*	73 */			 addEntity(entity, 160, 20, true);
/*	74 */		 } else if ((entity instanceof EntityEnderCrystal)) {
/*	75 */			 addEntity(entity, 256, 2147483647, false);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void addEntity(Entity entity, int i, int j) {
/*	80 */		 addEntity(entity, i, j, false);
/*		 */	 }
/*		 */ 
/*		 */	 public synchronized void addEntity(Entity entity, int i, int j, boolean flag)
/*		 */	 {
/*	85 */		 if (i > this.d) {
/*	86 */			 i = this.d;
/*		 */		 }
/*		 */ 
/*	89 */		 if (!this.trackedEntities.b(entity.id))
/*		 */		 {
/*	93 */			 EntityTrackerEntry entitytrackerentry = new EntityTrackerEntry(entity, i, j, flag);
/*		 */ 
/*	95 */			 this.b.add(entitytrackerentry);
/*	96 */			 this.trackedEntities.a(entity.id, entitytrackerentry);
/*	97 */			 entitytrackerentry.scanPlayers(this.world.players);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public synchronized void untrackEntity(Entity entity)
/*		 */	 {
/* 103 */		 if ((entity instanceof EntityPlayer)) {
/* 104 */			 EntityPlayer entityplayer = (EntityPlayer)entity;
/* 105 */			 Iterator iterator = this.b.iterator();
/*		 */ 
/* 107 */			 while (iterator.hasNext()) {
/* 108 */				 EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)iterator.next();
/*		 */ 
/* 110 */				 entitytrackerentry.a(entityplayer);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 114 */		 EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry)this.trackedEntities.d(entity.id);
/*		 */ 
/* 116 */		 if (entitytrackerentry1 != null) {
/* 117 */			 this.b.remove(entitytrackerentry1);
/* 118 */			 entitytrackerentry1.a();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public synchronized void updatePlayers()
/*		 */	 {
/* 124 */		 ArrayList arraylist = new ArrayList();
/* 125 */		 Iterator iterator = this.b.iterator();
/*		 */ 
/* 127 */		 while (iterator.hasNext()) {
/* 128 */			 EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)iterator.next();
/*		 */ 
/* 130 */			 entitytrackerentry.track(this.world.players);
/* 131 */			 if ((entitytrackerentry.n) && ((entitytrackerentry.tracker instanceof EntityPlayer))) {
/* 132 */				 arraylist.add((EntityPlayer)entitytrackerentry.tracker);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 136 */		 iterator = arraylist.iterator();
/*		 */ 
/* 138 */		 while (iterator.hasNext()) {
/* 139 */			 EntityPlayer entityplayer = (EntityPlayer)iterator.next();
/* 140 */			 EntityPlayer entityplayer1 = entityplayer;
/* 141 */			 Iterator iterator1 = this.b.iterator();
/*		 */ 
/* 143 */			 while (iterator1.hasNext()) {
/* 144 */				 EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry)iterator1.next();
/*		 */ 
/* 146 */				 if (entitytrackerentry1.tracker != entityplayer1)
/* 147 */					 entitytrackerentry1.updatePlayer(entityplayer1);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public synchronized void a(Entity entity, Packet packet)
/*		 */	 {
/* 155 */		 EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)this.trackedEntities.get(entity.id);
/*		 */ 
/* 157 */		 if (entitytrackerentry != null)
/* 158 */			 entitytrackerentry.broadcast(packet);
/*		 */	 }
/*		 */ 
/*		 */	 public synchronized void sendPacketToEntity(Entity entity, Packet packet)
/*		 */	 {
/* 164 */		 EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)this.trackedEntities.get(entity.id);
/*		 */ 
/* 166 */		 if (entitytrackerentry != null)
/* 167 */			 entitytrackerentry.broadcastIncludingSelf(packet);
/*		 */	 }
/*		 */ 
/*		 */	 public synchronized void untrackPlayer(EntityPlayer entityplayer)
/*		 */	 {
/* 173 */		 Iterator iterator = this.b.iterator();
/*		 */ 
/* 175 */		 while (iterator.hasNext()) {
/* 176 */			 EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)iterator.next();
/*		 */ 
/* 178 */			 entitytrackerentry.clear(entityplayer);
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityTracker
 * JD-Core Version:		0.6.0
 */