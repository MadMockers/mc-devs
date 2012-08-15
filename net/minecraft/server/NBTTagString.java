package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagString extends NBTBase
{
	public String data;

	public NBTTagString(String paramString)
	{
		super(paramString);
	}

	public NBTTagString(String paramString1, String paramString2) {
		super(paramString1);
		this.data = paramString2;
		if (paramString2 == null) throw new IllegalArgumentException("Empty string not allowed");
	}

	void write(DataOutput paramDataOutput)
	{
		paramDataOutput.writeUTF(this.data);
	}

	void load(DataInput paramDataInput)
	{
		this.data = paramDataInput.readUTF();
	}

	public byte getTypeId()
	{
		return 8;
	}

	public String toString() {
		return "" + this.data;
	}

	public NBTBase clone()
	{
		return new NBTTagString(getName(), this.data);
	}

	public boolean equals(Object paramObject)
	{
		if (super.equals(paramObject)) {
			NBTTagString localNBTTagString = (NBTTagString)paramObject;
			return ((this.data == null) && (localNBTTagString.data == null)) || ((this.data != null) && (this.data.equals(localNBTTagString.data)));
		}
		return false;
	}

	public int hashCode()
	{
		return super.hashCode() ^ this.data.hashCode();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.NBTTagString
 * JD-Core Version:		0.6.0
 */