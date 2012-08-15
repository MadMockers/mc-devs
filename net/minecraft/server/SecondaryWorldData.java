/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class SecondaryWorldData extends WorldData
/*		 */ {
/*		 */	 private final WorldData a;
/*		 */ 
/*		 */	 public SecondaryWorldData(WorldData paramWorldData)
/*		 */	 {
/*	22 */		 this.a = paramWorldData;
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagCompound a()
/*		 */	 {
/*	27 */		 return this.a.a();
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagCompound a(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/*	32 */		 return this.a.a(paramNBTTagCompound);
/*		 */	 }
/*		 */ 
/*		 */	 public long getSeed()
/*		 */	 {
/*	37 */		 return this.a.getSeed();
/*		 */	 }
/*		 */ 
/*		 */	 public int c()
/*		 */	 {
/*	42 */		 return this.a.c();
/*		 */	 }
/*		 */ 
/*		 */	 public int d()
/*		 */	 {
/*	47 */		 return this.a.d();
/*		 */	 }
/*		 */ 
/*		 */	 public int e()
/*		 */	 {
/*	52 */		 return this.a.e();
/*		 */	 }
/*		 */ 
/*		 */	 public long getTime()
/*		 */	 {
/*	57 */		 return this.a.getTime();
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagCompound h()
/*		 */	 {
/*	67 */		 return this.a.h();
/*		 */	 }
/*		 */ 
/*		 */	 public int i()
/*		 */	 {
/*	72 */		 return this.a.i();
/*		 */	 }
/*		 */ 
/*		 */	 public String getName()
/*		 */	 {
/*	77 */		 return this.a.getName();
/*		 */	 }
/*		 */ 
/*		 */	 public int k()
/*		 */	 {
/*	82 */		 return this.a.k();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isThundering()
/*		 */	 {
/*	92 */		 return this.a.isThundering();
/*		 */	 }
/*		 */ 
/*		 */	 public int getThunderDuration()
/*		 */	 {
/*	97 */		 return this.a.getThunderDuration();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean hasStorm()
/*		 */	 {
/* 102 */		 return this.a.hasStorm();
/*		 */	 }
/*		 */ 
/*		 */	 public int getWeatherDuration()
/*		 */	 {
/* 107 */		 return this.a.getWeatherDuration();
/*		 */	 }
/*		 */ 
/*		 */	 public EnumGamemode getGameType()
/*		 */	 {
/* 112 */		 return this.a.getGameType();
/*		 */	 }
/*		 */ 
/*		 */	 public void b(long paramLong)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void setSpawn(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void setName(String paramString)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void e(int paramInt)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void setThundering(boolean paramBoolean)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void setThunderDuration(int paramInt)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void setStorm(boolean paramBoolean)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void setWeatherDuration(int paramInt)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean shouldGenerateMapFeatures()
/*		 */	 {
/* 161 */		 return this.a.shouldGenerateMapFeatures();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isHardcore()
/*		 */	 {
/* 168 */		 return this.a.isHardcore();
/*		 */	 }
/*		 */ 
/*		 */	 public WorldType getType() {
/* 172 */		 return this.a.getType();
/*		 */	 }
/*		 */ 
/*		 */	 public void setType(WorldType paramWorldType) {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean allowCommands() {
/* 179 */		 return this.a.allowCommands();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isInitialized()
/*		 */	 {
/* 186 */		 return this.a.isInitialized();
/*		 */	 }
/*		 */ 
/*		 */	 public void d(boolean paramBoolean)
/*		 */	 {
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.SecondaryWorldData
 * JD-Core Version:		0.6.0
 */