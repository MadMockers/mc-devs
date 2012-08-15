/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInput;
/*		 */ import java.io.DataOutput;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ 
/*		 */ public class NBTTagList extends NBTBase
/*		 */ {
/*	 7 */	 private List list = new ArrayList();
/*		 */	 private byte type;
/*		 */ 
/*		 */	 public NBTTagList()
/*		 */	 {
/*	11 */		 super("");
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagList(String paramString) {
/*	15 */		 super(paramString);
/*		 */	 }
/*		 */ 
/*		 */	 void write(DataOutput paramDataOutput)
/*		 */	 {
/*	20 */		 if (!this.list.isEmpty()) this.type = ((NBTBase)this.list.get(0)).getTypeId(); else {
/*	21 */			 this.type = 1;
/*		 */		 }
/*	23 */		 paramDataOutput.writeByte(this.type);
/*	24 */		 paramDataOutput.writeInt(this.list.size());
/*	25 */		 for (NBTBase localNBTBase : this.list)
/*	26 */			 localNBTBase.write(paramDataOutput);
/*		 */	 }
/*		 */ 
/*		 */	 void load(DataInput paramDataInput)
/*		 */	 {
/*	32 */		 this.type = paramDataInput.readByte();
/*	33 */		 int i = paramDataInput.readInt();
/*		 */ 
/*	35 */		 this.list = new ArrayList();
/*	36 */		 for (int j = 0; j < i; j++) {
/*	37 */			 NBTBase localNBTBase = NBTBase.createTag(this.type, null);
/*	38 */			 localNBTBase.load(paramDataInput);
/*	39 */			 this.list.add(localNBTBase);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public byte getTypeId()
/*		 */	 {
/*	45 */		 return 9;
/*		 */	 }
/*		 */ 
/*		 */	 public String toString() {
/*	49 */		 return "" + this.list.size() + " entries of type " + NBTBase.getTagName(this.type);
/*		 */	 }
/*		 */ 
/*		 */	 public void add(NBTBase paramNBTBase)
/*		 */	 {
/*	65 */		 this.type = paramNBTBase.getTypeId();
/*	66 */		 this.list.add(paramNBTBase);
/*		 */	 }
/*		 */ 
/*		 */	 public NBTBase get(int paramInt)
/*		 */	 {
/*	74 */		 return (NBTBase)this.list.get(paramInt);
/*		 */	 }
/*		 */ 
/*		 */	 public int size() {
/*	78 */		 return this.list.size();
/*		 */	 }
/*		 */ 
/*		 */	 public NBTBase clone()
/*		 */	 {
/*	83 */		 NBTTagList localNBTTagList = new NBTTagList(getName());
/*	84 */		 localNBTTagList.type = this.type;
/*	85 */		 for (NBTBase localNBTBase1 : this.list)
/*		 */		 {
/*	87 */			 NBTBase localNBTBase2 = localNBTBase1.clone();
/*	88 */			 localNBTTagList.list.add(localNBTBase2);
/*		 */		 }
/*	90 */		 return localNBTTagList;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean equals(Object paramObject)
/*		 */	 {
/*	96 */		 if (super.equals(paramObject)) {
/*	97 */			 NBTTagList localNBTTagList = (NBTTagList)paramObject;
/*	98 */			 if (this.type == localNBTTagList.type) {
/*	99 */				 return this.list.equals(localNBTTagList.list);
/*		 */			 }
/*		 */		 }
/* 102 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int hashCode()
/*		 */	 {
/* 107 */		 return super.hashCode() ^ this.list.hashCode();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NBTTagList
 * JD-Core Version:		0.6.0
 */