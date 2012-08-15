package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagLong extends NBTBase
{
	public long data;

	public NBTTagLong(String paramString)
	{
		super(paramString);
	}

	public NBTTagLong(String paramString, long paramLong) {
		super(paramString);
		this.data = paramLong;
	}

	void write(DataOutput paramDataOutput)
	{
		paramDataOutput.writeLong(this.data);
	}

	void load(DataInput paramDataInput)
	{
		this.data = paramDataInput.readLong();
	}

	public byte getTypeId()
	{
		return 4;
	}

	public String toString() {
		return "" + this.data;
	}

	public NBTBase clone()
	{
		return new NBTTagLong(getName(), this.data);
	}

	public boolean equals(Object paramObject)
	{
		if (super.equals(paramObject)) {
			NBTTagLong localNBTTagLong = (NBTTagLong)paramObject;
			return this.data == localNBTTagLong.data;
		}
		return false;
	}

	public int hashCode()
	{
		return super.hashCode() ^ (int)(this.data ^ this.data >>> 32);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NBTTagLong
 * JD-Core Version:		0.6.0
 */