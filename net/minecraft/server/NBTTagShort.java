package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagShort extends NBTBase
{
	public short data;

	public NBTTagShort(String paramString)
	{
		super(paramString);
	}

	public NBTTagShort(String paramString, short paramShort) {
		super(paramString);
		this.data = paramShort;
	}

	void write(DataOutput paramDataOutput)
	{
		paramDataOutput.writeShort(this.data);
	}

	void load(DataInput paramDataInput)
	{
		this.data = paramDataInput.readShort();
	}

	public byte getTypeId()
	{
		return 2;
	}

	public String toString() {
		return "" + this.data;
	}

	public NBTBase clone()
	{
		return new NBTTagShort(getName(), this.data);
	}

	public boolean equals(Object paramObject)
	{
		if (super.equals(paramObject)) {
			NBTTagShort localNBTTagShort = (NBTTagShort)paramObject;
			return this.data == localNBTTagShort.data;
		}
		return false;
	}

	public int hashCode()
	{
		return super.hashCode() ^ this.data;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.NBTTagShort
 * JD-Core Version:		0.6.0
 */