/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public abstract class NetHandler
/*		 */ {
/*		 */	 public abstract boolean a();
/*		 */ 
/*		 */	 public void a(Packet51MapChunk paramPacket51MapChunk)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void onUnhandledPacket(Packet paramPacket)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String paramString, Object[] paramArrayOfObject)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet255KickDisconnect paramPacket255KickDisconnect)
/*		 */	 {
/*	17 */		 onUnhandledPacket(paramPacket255KickDisconnect);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet1Login paramPacket1Login) {
/*	21 */		 onUnhandledPacket(paramPacket1Login);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet10Flying paramPacket10Flying) {
/*	25 */		 onUnhandledPacket(paramPacket10Flying);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet52MultiBlockChange paramPacket52MultiBlockChange) {
/*	29 */		 onUnhandledPacket(paramPacket52MultiBlockChange);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet14BlockDig paramPacket14BlockDig) {
/*	33 */		 onUnhandledPacket(paramPacket14BlockDig);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet53BlockChange paramPacket53BlockChange) {
/*	37 */		 onUnhandledPacket(paramPacket53BlockChange);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet20NamedEntitySpawn paramPacket20NamedEntitySpawn) {
/*	41 */		 onUnhandledPacket(paramPacket20NamedEntitySpawn);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet30Entity paramPacket30Entity) {
/*	45 */		 onUnhandledPacket(paramPacket30Entity);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet34EntityTeleport paramPacket34EntityTeleport) {
/*	49 */		 onUnhandledPacket(paramPacket34EntityTeleport);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet15Place paramPacket15Place) {
/*	53 */		 onUnhandledPacket(paramPacket15Place);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet16BlockItemSwitch paramPacket16BlockItemSwitch) {
/*	57 */		 onUnhandledPacket(paramPacket16BlockItemSwitch);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet29DestroyEntity paramPacket29DestroyEntity) {
/*	61 */		 onUnhandledPacket(paramPacket29DestroyEntity);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet21PickupSpawn paramPacket21PickupSpawn) {
/*	65 */		 onUnhandledPacket(paramPacket21PickupSpawn);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet22Collect paramPacket22Collect) {
/*	69 */		 onUnhandledPacket(paramPacket22Collect);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet3Chat paramPacket3Chat) {
/*	73 */		 onUnhandledPacket(paramPacket3Chat);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet23VehicleSpawn paramPacket23VehicleSpawn) {
/*	77 */		 onUnhandledPacket(paramPacket23VehicleSpawn);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet18ArmAnimation paramPacket18ArmAnimation) {
/*	81 */		 onUnhandledPacket(paramPacket18ArmAnimation);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet19EntityAction paramPacket19EntityAction) {
/*	85 */		 onUnhandledPacket(paramPacket19EntityAction);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet2Handshake paramPacket2Handshake) {
/*	89 */		 onUnhandledPacket(paramPacket2Handshake);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet253KeyRequest paramPacket253KeyRequest) {
/*	93 */		 onUnhandledPacket(paramPacket253KeyRequest);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet252KeyResponse paramPacket252KeyResponse) {
/*	97 */		 onUnhandledPacket(paramPacket252KeyResponse);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet24MobSpawn paramPacket24MobSpawn) {
/* 101 */		 onUnhandledPacket(paramPacket24MobSpawn);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet4UpdateTime paramPacket4UpdateTime) {
/* 105 */		 onUnhandledPacket(paramPacket4UpdateTime);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet6SpawnPosition paramPacket6SpawnPosition) {
/* 109 */		 onUnhandledPacket(paramPacket6SpawnPosition);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet28EntityVelocity paramPacket28EntityVelocity) {
/* 113 */		 onUnhandledPacket(paramPacket28EntityVelocity);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet40EntityMetadata paramPacket40EntityMetadata) {
/* 117 */		 onUnhandledPacket(paramPacket40EntityMetadata);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet39AttachEntity paramPacket39AttachEntity) {
/* 121 */		 onUnhandledPacket(paramPacket39AttachEntity);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet7UseEntity paramPacket7UseEntity) {
/* 125 */		 onUnhandledPacket(paramPacket7UseEntity);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet38EntityStatus paramPacket38EntityStatus) {
/* 129 */		 onUnhandledPacket(paramPacket38EntityStatus);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet8UpdateHealth paramPacket8UpdateHealth) {
/* 133 */		 onUnhandledPacket(paramPacket8UpdateHealth);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet9Respawn paramPacket9Respawn) {
/* 137 */		 onUnhandledPacket(paramPacket9Respawn);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet60Explosion paramPacket60Explosion) {
/* 141 */		 onUnhandledPacket(paramPacket60Explosion);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet100OpenWindow paramPacket100OpenWindow) {
/* 145 */		 onUnhandledPacket(paramPacket100OpenWindow);
/*		 */	 }
/*		 */ 
/*		 */	 public void handleContainerClose(Packet101CloseWindow paramPacket101CloseWindow) {
/* 149 */		 onUnhandledPacket(paramPacket101CloseWindow);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet102WindowClick paramPacket102WindowClick) {
/* 153 */		 onUnhandledPacket(paramPacket102WindowClick);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet103SetSlot paramPacket103SetSlot) {
/* 157 */		 onUnhandledPacket(paramPacket103SetSlot);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet104WindowItems paramPacket104WindowItems) {
/* 161 */		 onUnhandledPacket(paramPacket104WindowItems);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet130UpdateSign paramPacket130UpdateSign) {
/* 165 */		 onUnhandledPacket(paramPacket130UpdateSign);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet105CraftProgressBar paramPacket105CraftProgressBar) {
/* 169 */		 onUnhandledPacket(paramPacket105CraftProgressBar);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet5EntityEquipment paramPacket5EntityEquipment) {
/* 173 */		 onUnhandledPacket(paramPacket5EntityEquipment);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet106Transaction paramPacket106Transaction) {
/* 177 */		 onUnhandledPacket(paramPacket106Transaction);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet25EntityPainting paramPacket25EntityPainting) {
/* 181 */		 onUnhandledPacket(paramPacket25EntityPainting);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet54PlayNoteBlock paramPacket54PlayNoteBlock) {
/* 185 */		 onUnhandledPacket(paramPacket54PlayNoteBlock);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet200Statistic paramPacket200Statistic) {
/* 189 */		 onUnhandledPacket(paramPacket200Statistic);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet17EntityLocationAction paramPacket17EntityLocationAction) {
/* 193 */		 onUnhandledPacket(paramPacket17EntityLocationAction);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet70Bed paramPacket70Bed)
/*		 */	 {
/* 201 */		 onUnhandledPacket(paramPacket70Bed);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet71Weather paramPacket71Weather) {
/* 205 */		 onUnhandledPacket(paramPacket71Weather);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet131ItemData paramPacket131ItemData) {
/* 209 */		 onUnhandledPacket(paramPacket131ItemData);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet61WorldEvent paramPacket61WorldEvent) {
/* 213 */		 onUnhandledPacket(paramPacket61WorldEvent);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet254GetInfo paramPacket254GetInfo) {
/* 217 */		 onUnhandledPacket(paramPacket254GetInfo);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet41MobEffect paramPacket41MobEffect) {
/* 221 */		 onUnhandledPacket(paramPacket41MobEffect);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet42RemoveMobEffect paramPacket42RemoveMobEffect) {
/* 225 */		 onUnhandledPacket(paramPacket42RemoveMobEffect);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet201PlayerInfo paramPacket201PlayerInfo) {
/* 229 */		 onUnhandledPacket(paramPacket201PlayerInfo);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet0KeepAlive paramPacket0KeepAlive) {
/* 233 */		 onUnhandledPacket(paramPacket0KeepAlive);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet43SetExperience paramPacket43SetExperience) {
/* 237 */		 onUnhandledPacket(paramPacket43SetExperience);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet107SetCreativeSlot paramPacket107SetCreativeSlot) {
/* 241 */		 onUnhandledPacket(paramPacket107SetCreativeSlot);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet26AddExpOrb paramPacket26AddExpOrb) {
/* 245 */		 onUnhandledPacket(paramPacket26AddExpOrb);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet108ButtonClick paramPacket108ButtonClick) {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet250CustomPayload paramPacket250CustomPayload) {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet35EntityHeadRotation paramPacket35EntityHeadRotation) {
/* 255 */		 onUnhandledPacket(paramPacket35EntityHeadRotation);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet132TileEntityData paramPacket132TileEntityData) {
/* 259 */		 onUnhandledPacket(paramPacket132TileEntityData);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet202Abilities paramPacket202Abilities) {
/* 263 */		 onUnhandledPacket(paramPacket202Abilities);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet203TabComplete paramPacket203TabComplete) {
/* 267 */		 onUnhandledPacket(paramPacket203TabComplete);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet204LocaleAndViewDistance paramPacket204LocaleAndViewDistance) {
/* 271 */		 onUnhandledPacket(paramPacket204LocaleAndViewDistance);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet62NamedSoundEffect paramPacket62NamedSoundEffect) {
/* 275 */		 onUnhandledPacket(paramPacket62NamedSoundEffect);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet55BlockBreakAnimation paramPacket55BlockBreakAnimation) {
/* 279 */		 onUnhandledPacket(paramPacket55BlockBreakAnimation);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet205ClientCommand paramPacket205ClientCommand) {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet56MapChunkBulk paramPacket56MapChunkBulk) {
/* 286 */		 onUnhandledPacket(paramPacket56MapChunkBulk);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b() {
/* 290 */		 return false;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NetHandler
 * JD-Core Version:		0.6.0
 */