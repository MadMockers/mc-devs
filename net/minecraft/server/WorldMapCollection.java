/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutput;
/*		 */ import java.io.DataOutputStream;
/*		 */ import java.io.File;
/*		 */ import java.io.FileInputStream;
/*		 */ import java.io.FileOutputStream;
/*		 */ import java.lang.reflect.Constructor;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Set;
/*		 */ 
/*		 */ public class WorldMapCollection
/*		 */ {
/*		 */	 private IDataManager a;
/*	22 */	 private Map b = new HashMap();
/*	23 */	 private List c = new ArrayList();
/*	24 */	 private Map d = new HashMap();
/*		 */ 
/*		 */	 public WorldMapCollection(IDataManager paramIDataManager) {
/*	27 */		 this.a = paramIDataManager;
/*	28 */		 b();
/*		 */	 }
/*		 */ 
/*		 */	 public WorldMapBase get(Class paramClass, String paramString) {
/*	32 */		 WorldMapBase localWorldMapBase = (WorldMapBase)this.b.get(paramString);
/*	33 */		 if (localWorldMapBase != null) return localWorldMapBase;
/*		 */ 
/*	35 */		 if (this.a != null) {
/*		 */			 try {
/*	37 */				 File localFile = this.a.getDataFile(paramString);
/*	38 */				 if ((localFile != null) && (localFile.exists())) {
/*		 */					 try {
/*	40 */						 localWorldMapBase = (WorldMapBase)paramClass.getConstructor(new Class[] { String.class }).newInstance(new Object[] { paramString });
/*		 */					 } catch (Exception localException2) {
/*	42 */						 throw new RuntimeException("Failed to instantiate " + paramClass.toString(), localException2);
/*		 */					 }
/*		 */ 
/*	45 */					 FileInputStream localFileInputStream = new FileInputStream(localFile);
/*	46 */					 NBTTagCompound localNBTTagCompound = NBTCompressedStreamTools.a(localFileInputStream);
/*	47 */					 localFileInputStream.close();
/*		 */ 
/*	49 */					 localWorldMapBase.a(localNBTTagCompound.getCompound("data"));
/*		 */				 }
/*		 */			 } catch (Exception localException1) {
/*	52 */				 localException1.printStackTrace();
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	56 */		 if (localWorldMapBase != null) {
/*	57 */			 this.b.put(paramString, localWorldMapBase);
/*	58 */			 this.c.add(localWorldMapBase);
/*		 */		 }
/*	60 */		 return localWorldMapBase;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String paramString, WorldMapBase paramWorldMapBase) {
/*	64 */		 if (paramWorldMapBase == null) throw new RuntimeException("Can't set null data");
/*	65 */		 if (this.b.containsKey(paramString)) {
/*	66 */			 this.c.remove(this.b.remove(paramString));
/*		 */		 }
/*	68 */		 this.b.put(paramString, paramWorldMapBase);
/*	69 */		 this.c.add(paramWorldMapBase);
/*		 */	 }
/*		 */ 
/*		 */	 public void a() {
/*	73 */		 for (WorldMapBase localWorldMapBase : this.c)
/*	74 */			 if (localWorldMapBase.b()) {
/*	75 */				 a(localWorldMapBase);
/*	76 */				 localWorldMapBase.a(false);
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 private void a(WorldMapBase paramWorldMapBase)
/*		 */	 {
/*	82 */		 if (this.a == null) return; try
/*		 */		 {
/*	84 */			 File localFile = this.a.getDataFile(paramWorldMapBase.id);
/*	85 */			 if (localFile != null) {
/*	86 */				 NBTTagCompound localNBTTagCompound1 = new NBTTagCompound();
/*	87 */				 paramWorldMapBase.b(localNBTTagCompound1);
/*		 */ 
/*	89 */				 NBTTagCompound localNBTTagCompound2 = new NBTTagCompound();
/*	90 */				 localNBTTagCompound2.setCompound("data", localNBTTagCompound1);
/*		 */ 
/*	92 */				 FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
/*	93 */				 NBTCompressedStreamTools.a(localNBTTagCompound2, localFileOutputStream);
/*	94 */				 localFileOutputStream.close();
/*		 */			 }
/*		 */		 } catch (Exception localException) {
/*	97 */			 localException.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void b() {
/*		 */		 try {
/* 103 */			 this.d.clear();
/* 104 */			 if (this.a == null) return;
/* 105 */			 File localFile = this.a.getDataFile("idcounts");
/* 106 */			 if ((localFile != null) && (localFile.exists())) {
/* 107 */				 DataInputStream localDataInputStream = new DataInputStream(new FileInputStream(localFile));
/* 108 */				 NBTTagCompound localNBTTagCompound = NBTCompressedStreamTools.a(localDataInputStream);
/* 109 */				 localDataInputStream.close();
/*		 */ 
/* 111 */				 for (NBTBase localNBTBase : localNBTTagCompound.c())
/* 112 */					 if ((localNBTBase instanceof NBTTagShort)) {
/* 113 */						 NBTTagShort localNBTTagShort = (NBTTagShort)localNBTBase;
/* 114 */						 String str = localNBTTagShort.getName();
/* 115 */						 short s = localNBTTagShort.data;
/* 116 */						 this.d.put(str, Short.valueOf(s));
/*		 */					 }
/*		 */			 }
/*		 */		 }
/*		 */		 catch (Exception localException) {
/* 121 */			 localException.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int a(String paramString) {
/* 126 */		 Short localShort = (Short)this.d.get(paramString);
/*		 */		 Object localObject1;
/*		 */		 Object localObject2;
/* 127 */		 if (localShort == null) {
/* 128 */			 localShort = Short.valueOf(0);
/*		 */		 } else {
/* 130 */			 localObject1 = localShort; localObject2 = localShort = Short.valueOf((short)(localShort.shortValue() + 1));
/*		 */		 }
/*		 */ 
/* 133 */		 this.d.put(paramString, localShort);
/* 134 */		 if (this.a == null) return localShort.shortValue(); try
/*		 */		 {
/* 136 */			 localObject1 = this.a.getDataFile("idcounts");
/* 137 */			 if (localObject1 != null) {
/* 138 */				 localObject2 = new NBTTagCompound();
/*		 */ 
/* 140 */				 for (Object localObject3 = this.d.keySet().iterator(); ((Iterator)localObject3).hasNext(); ) { String str = (String)((Iterator)localObject3).next();
/* 141 */					 short s = ((Short)this.d.get(str)).shortValue();
/* 142 */					 ((NBTTagCompound)localObject2).setShort(str, s);
/*		 */				 }
/*		 */ 
/* 145 */				 localObject3 = new DataOutputStream(new FileOutputStream((File)localObject1));
/* 146 */				 NBTCompressedStreamTools.a((NBTTagCompound)localObject2, (DataOutput)localObject3);
/* 147 */				 ((DataOutputStream)localObject3).close();
/*		 */			 }
/*		 */		 } catch (Exception localException) {
/* 150 */			 localException.printStackTrace();
/*		 */		 }
/* 152 */		 return localShort.shortValue();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldMapCollection
 * JD-Core Version:		0.6.0
 */