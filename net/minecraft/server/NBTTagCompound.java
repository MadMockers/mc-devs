/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInput;
/*		 */ import java.io.DataOutput;
/*		 */ import java.io.IOException;
/*		 */ import java.util.Collection;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.Map;
/*		 */ import java.util.Set;
/*		 */ 
/*		 */ public class NBTTagCompound extends NBTBase
/*		 */ {
/*	12 */	 private Map map = new HashMap();
/*		 */ 
/*		 */	 public NBTTagCompound() {
/*	15 */		 super("");
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagCompound(String s) {
/*	19 */		 super(s);
/*		 */	 }
/*		 */ 
/*		 */	 void write(DataOutput dataoutput) {
/*	23 */		 Iterator iterator = this.map.values().iterator();
/*		 */ 
/*	25 */		 while (iterator.hasNext()) {
/*	26 */			 NBTBase nbtbase = (NBTBase)iterator.next();
/*		 */ 
/*	28 */			 NBTBase.a(nbtbase, dataoutput);
/*		 */		 }
/*		 */ 
/*		 */		 try
/*		 */		 {
/*	33 */			 dataoutput.writeByte(0);
/*		 */		 } catch (IOException ex) {
/*	35 */			 ex.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(String name) {
/*	40 */		 this.map.remove(name);
/*		 */	 }
/*		 */ 
/*		 */	 void load(DataInput datainput)
/*		 */	 {
/*	45 */		 this.map.clear();
/*		 */		 NBTBase nbtbase;
/*	49 */		 while ((nbtbase = NBTBase.b(datainput)).getTypeId() != 0)
/*	50 */			 this.map.put(nbtbase.getName(), nbtbase);
/*		 */	 }
/*		 */ 
/*		 */	 public Collection c()
/*		 */	 {
/*	55 */		 return this.map.values();
/*		 */	 }
/*		 */ 
/*		 */	 public byte getTypeId() {
/*	59 */		 return 10;
/*		 */	 }
/*		 */ 
/*		 */	 public void set(String s, NBTBase nbtbase) {
/*	63 */		 this.map.put(s, nbtbase.setName(s));
/*		 */	 }
/*		 */ 
/*		 */	 public void setByte(String s, byte b0) {
/*	67 */		 this.map.put(s, new NBTTagByte(s, b0));
/*		 */	 }
/*		 */ 
/*		 */	 public void setShort(String s, short short1) {
/*	71 */		 this.map.put(s, new NBTTagShort(s, short1));
/*		 */	 }
/*		 */ 
/*		 */	 public void setInt(String s, int i) {
/*	75 */		 this.map.put(s, new NBTTagInt(s, i));
/*		 */	 }
/*		 */ 
/*		 */	 public void setLong(String s, long i) {
/*	79 */		 this.map.put(s, new NBTTagLong(s, i));
/*		 */	 }
/*		 */ 
/*		 */	 public void setFloat(String s, float f) {
/*	83 */		 this.map.put(s, new NBTTagFloat(s, f));
/*		 */	 }
/*		 */ 
/*		 */	 public void setDouble(String s, double d0) {
/*	87 */		 this.map.put(s, new NBTTagDouble(s, d0));
/*		 */	 }
/*		 */ 
/*		 */	 public void setString(String s, String s1) {
/*	91 */		 this.map.put(s, new NBTTagString(s, s1));
/*		 */	 }
/*		 */ 
/*		 */	 public void setByteArray(String s, byte[] abyte) {
/*	95 */		 this.map.put(s, new NBTTagByteArray(s, abyte));
/*		 */	 }
/*		 */ 
/*		 */	 public void setIntArray(String s, int[] aint) {
/*	99 */		 this.map.put(s, new NBTTagIntArray(s, aint));
/*		 */	 }
/*		 */ 
/*		 */	 public void setCompound(String s, NBTTagCompound nbttagcompound) {
/* 103 */		 this.map.put(s, nbttagcompound.setName(s));
/*		 */	 }
/*		 */ 
/*		 */	 public void setBoolean(String s, boolean flag) {
/* 107 */		 setByte(s, (byte)(flag ? 1 : 0));
/*		 */	 }
/*		 */ 
/*		 */	 public NBTBase get(String s) {
/* 111 */		 return (NBTBase)this.map.get(s);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean hasKey(String s) {
/* 115 */		 return this.map.containsKey(s);
/*		 */	 }
/*		 */ 
/*		 */	 public byte getByte(String s) {
/* 119 */		 return !this.map.containsKey(s) ? 0 : ((NBTTagByte)this.map.get(s)).data;
/*		 */	 }
/*		 */ 
/*		 */	 public short getShort(String s) {
/* 123 */		 return !this.map.containsKey(s) ? 0 : ((NBTTagShort)this.map.get(s)).data;
/*		 */	 }
/*		 */ 
/*		 */	 public int getInt(String s) {
/* 127 */		 return !this.map.containsKey(s) ? 0 : ((NBTTagInt)this.map.get(s)).data;
/*		 */	 }
/*		 */ 
/*		 */	 public long getLong(String s) {
/* 131 */		 return !this.map.containsKey(s) ? 0L : ((NBTTagLong)this.map.get(s)).data;
/*		 */	 }
/*		 */ 
/*		 */	 public float getFloat(String s) {
/* 135 */		 return !this.map.containsKey(s) ? 0.0F : ((NBTTagFloat)this.map.get(s)).data;
/*		 */	 }
/*		 */ 
/*		 */	 public double getDouble(String s) {
/* 139 */		 return !this.map.containsKey(s) ? 0.0D : ((NBTTagDouble)this.map.get(s)).data;
/*		 */	 }
/*		 */ 
/*		 */	 public String getString(String s) {
/* 143 */		 return !this.map.containsKey(s) ? "" : ((NBTTagString)this.map.get(s)).data;
/*		 */	 }
/*		 */ 
/*		 */	 public byte[] getByteArray(String s) {
/* 147 */		 return !this.map.containsKey(s) ? new byte[0] : ((NBTTagByteArray)this.map.get(s)).data;
/*		 */	 }
/*		 */ 
/*		 */	 public int[] getIntArray(String s) {
/* 151 */		 return !this.map.containsKey(s) ? new int[0] : ((NBTTagIntArray)this.map.get(s)).data;
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagCompound getCompound(String s) {
/* 155 */		 return !this.map.containsKey(s) ? new NBTTagCompound(s) : (NBTTagCompound)this.map.get(s);
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagList getList(String s) {
/* 159 */		 return !this.map.containsKey(s) ? new NBTTagList(s) : (NBTTagList)this.map.get(s);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean getBoolean(String s) {
/* 163 */		 return getByte(s) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public String toString() {
/* 167 */		 return "" + this.map.size() + " entries";
/*		 */	 }
/*		 */ 
/*		 */	 public NBTBase clone() {
/* 171 */		 NBTTagCompound nbttagcompound = new NBTTagCompound(getName());
/* 172 */		 Iterator iterator = this.map.keySet().iterator();
/*		 */ 
/* 174 */		 while (iterator.hasNext()) {
/* 175 */			 String s = (String)iterator.next();
/*		 */ 
/* 177 */			 nbttagcompound.set(s, ((NBTBase)this.map.get(s)).clone());
/*		 */		 }
/*		 */ 
/* 180 */		 return nbttagcompound;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean equals(Object object) {
/* 184 */		 if (super.equals(object)) {
/* 185 */			 NBTTagCompound nbttagcompound = (NBTTagCompound)object;
/*		 */ 
/* 187 */			 return this.map.entrySet().equals(nbttagcompound.map.entrySet());
/*		 */		 }
/* 189 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int hashCode()
/*		 */	 {
/* 194 */		 return super.hashCode() ^ this.map.hashCode();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NBTTagCompound
 * JD-Core Version:		0.6.0
 */