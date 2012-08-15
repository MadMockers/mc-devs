/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public final class WorldSettings
/*		 */ {
/*		 */	 private final long a;
/*		 */	 private final EnumGamemode b;
/*		 */	 private final boolean c;
/*		 */	 private final boolean d;
/*		 */	 private final WorldType e;
/*		 */	 private boolean f;
/*		 */	 private boolean g;
/*		 */ 
/*		 */	 public WorldSettings(long paramLong, EnumGamemode paramEnumGamemode, boolean paramBoolean1, boolean paramBoolean2, WorldType paramWorldType)
/*		 */	 {
/*	81 */		 this.a = paramLong;
/*	82 */		 this.b = paramEnumGamemode;
/*	83 */		 this.c = paramBoolean1;
/*	84 */		 this.d = paramBoolean2;
/*	85 */		 this.e = paramWorldType;
/*		 */	 }
/*		 */ 
/*		 */	 public WorldSettings(WorldData paramWorldData) {
/*	89 */		 this(paramWorldData.getSeed(), paramWorldData.getGameType(), paramWorldData.shouldGenerateMapFeatures(), paramWorldData.isHardcore(), paramWorldData.getType());
/*		 */	 }
/*		 */ 
/*		 */	 public WorldSettings a() {
/*	93 */		 this.g = true;
/*	94 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/* 103 */		 return this.g;
/*		 */	 }
/*		 */ 
/*		 */	 public long d() {
/* 107 */		 return this.a;
/*		 */	 }
/*		 */ 
/*		 */	 public EnumGamemode e() {
/* 111 */		 return this.b;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean f() {
/* 115 */		 return this.d;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean g() {
/* 119 */		 return this.c;
/*		 */	 }
/*		 */ 
/*		 */	 public WorldType h() {
/* 123 */		 return this.e;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean i() {
/* 127 */		 return this.f;
/*		 */	 }
/*		 */ 
/*		 */	 public static EnumGamemode a(int paramInt) {
/* 131 */		 return EnumGamemode.a(paramInt);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldSettings
 * JD-Core Version:		0.6.0
 */