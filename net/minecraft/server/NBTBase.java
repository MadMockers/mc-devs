/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInput;
/*		 */ import java.io.DataOutput;
/*		 */ 
/*		 */ public abstract class NBTBase
/*		 */ {
/*		 */	 private String name;
/*		 */ 
/*		 */	 abstract void write(DataOutput paramDataOutput);
/*		 */ 
/*		 */	 abstract void load(DataInput paramDataInput);
/*		 */ 
/*		 */	 public abstract byte getTypeId();
/*		 */ 
/*		 */	 protected NBTBase(String paramString)
/*		 */	 {
/*	30 */		 if (paramString == null)
/*	31 */			 this.name = "";
/*		 */		 else
/*	33 */			 this.name = paramString;
/*		 */	 }
/*		 */ 
/*		 */	 public NBTBase setName(String paramString)
/*		 */	 {
/*	56 */		 if (paramString == null)
/*	57 */			 this.name = "";
/*		 */		 else {
/*	59 */			 this.name = paramString;
/*		 */		 }
/*	61 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/*	65 */		 if (this.name == null) return "";
/*	66 */		 return this.name;
/*		 */	 }
/*		 */ 
/*		 */	 public static NBTBase b(DataInput paramDataInput) {
/*	70 */		 byte b = paramDataInput.readByte();
/*	71 */		 if (b == 0) return new NBTTagEnd();
/*		 */ 
/*	73 */		 String str = paramDataInput.readUTF();
/*		 */ 
/*	75 */		 NBTBase localNBTBase = createTag(b, str);
/*		 */ 
/*	77 */		 localNBTBase.load(paramDataInput);
/*	78 */		 return localNBTBase;
/*		 */	 }
/*		 */ 
/*		 */	 public static void a(NBTBase paramNBTBase, DataOutput paramDataOutput) {
/*	82 */		 paramDataOutput.writeByte(paramNBTBase.getTypeId());
/*	83 */		 if (paramNBTBase.getTypeId() == 0) return;
/*		 */ 
/*	85 */		 paramDataOutput.writeUTF(paramNBTBase.getName());
/*		 */ 
/*	87 */		 paramNBTBase.write(paramDataOutput);
/*		 */	 }
/*		 */ 
/*		 */	 public static NBTBase createTag(byte paramByte, String paramString) {
/*	91 */		 switch (paramByte) {
/*		 */		 case 0:
/*	93 */			 return new NBTTagEnd();
/*		 */		 case 1:
/*	95 */			 return new NBTTagByte(paramString);
/*		 */		 case 2:
/*	97 */			 return new NBTTagShort(paramString);
/*		 */		 case 3:
/*	99 */			 return new NBTTagInt(paramString);
/*		 */		 case 4:
/* 101 */			 return new NBTTagLong(paramString);
/*		 */		 case 5:
/* 103 */			 return new NBTTagFloat(paramString);
/*		 */		 case 6:
/* 105 */			 return new NBTTagDouble(paramString);
/*		 */		 case 7:
/* 107 */			 return new NBTTagByteArray(paramString);
/*		 */		 case 11:
/* 109 */			 return new NBTTagIntArray(paramString);
/*		 */		 case 8:
/* 111 */			 return new NBTTagString(paramString);
/*		 */		 case 9:
/* 113 */			 return new NBTTagList(paramString);
/*		 */		 case 10:
/* 115 */			 return new NBTTagCompound(paramString);
/*		 */		 }
/* 117 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public static String getTagName(byte paramByte) {
/* 121 */		 switch (paramByte) {
/*		 */		 case 0:
/* 123 */			 return "TAG_End";
/*		 */		 case 1:
/* 125 */			 return "TAG_Byte";
/*		 */		 case 2:
/* 127 */			 return "TAG_Short";
/*		 */		 case 3:
/* 129 */			 return "TAG_Int";
/*		 */		 case 4:
/* 131 */			 return "TAG_Long";
/*		 */		 case 5:
/* 133 */			 return "TAG_Float";
/*		 */		 case 6:
/* 135 */			 return "TAG_Double";
/*		 */		 case 7:
/* 137 */			 return "TAG_Byte_Array";
/*		 */		 case 11:
/* 139 */			 return "TAG_Int_Array";
/*		 */		 case 8:
/* 141 */			 return "TAG_String";
/*		 */		 case 9:
/* 143 */			 return "TAG_List";
/*		 */		 case 10:
/* 145 */			 return "TAG_Compound";
/*		 */		 }
/* 147 */		 return "UNKNOWN";
/*		 */	 }
/*		 */ 
/*		 */	 public abstract NBTBase clone();
/*		 */ 
/*		 */	 public boolean equals(Object paramObject) {
/* 154 */		 if (!(paramObject instanceof NBTBase)) {
/* 155 */			 return false;
/*		 */		 }
/* 157 */		 NBTBase localNBTBase = (NBTBase)paramObject;
/* 158 */		 if (getTypeId() != localNBTBase.getTypeId()) {
/* 159 */			 return false;
/*		 */		 }
/* 161 */		 if (((this.name == null) && (localNBTBase.name != null)) || ((this.name != null) && (localNBTBase.name == null))) {
/* 162 */			 return false;
/*		 */		 }
/*		 */ 
/* 165 */		 return (this.name == null) || (this.name.equals(localNBTBase.name));
/*		 */	 }
/*		 */ 
/*		 */	 public int hashCode()
/*		 */	 {
/* 172 */		 return this.name.hashCode() ^ getTypeId();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NBTBase
 * JD-Core Version:		0.6.0
 */