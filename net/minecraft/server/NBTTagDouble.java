package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagDouble extends NBTBase
{
	public double data;

	public NBTTagDouble(String paramString)
	{
		super(paramString);
	}

	public NBTTagDouble(String paramString, double paramDouble) {
		super(paramString);
		this.data = paramDouble;
	}

	void write(DataOutput paramDataOutput)
	{
		paramDataOutput.writeDouble(this.data);
	}

	void load(DataInput paramDataInput)
	{
		this.data = paramDataInput.readDouble();
	}

	public byte getTypeId()
	{
		return 6;
	}

	public String toString() {
		return "" + this.data;
	}

	public NBTBase clone()
	{
		return new NBTTagDouble(getName(), this.data);
	}

	public boolean equals(Object paramObject)
	{
		if (super.equals(paramObject)) {
			NBTTagDouble localNBTTagDouble = (NBTTagDouble)paramObject;
			return this.data == localNBTTagDouble.data;
		}
		return false;
	}

	public int hashCode()
	{
		long l = Double.doubleToLongBits(this.data);
		return super.hashCode() ^ (int)(l ^ l >>> 32);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NBTTagDouble
 * JD-Core Version:		0.6.0
 */