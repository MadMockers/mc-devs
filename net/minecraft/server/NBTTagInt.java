package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagInt extends NBTBase
{
	public int data;

	public NBTTagInt(String paramString)
	{
		super(paramString);
	}

	public NBTTagInt(String paramString, int paramInt) {
		super(paramString);
		this.data = paramInt;
	}

	void write(DataOutput paramDataOutput)
	{
		paramDataOutput.writeInt(this.data);
	}

	void load(DataInput paramDataInput)
	{
		this.data = paramDataInput.readInt();
	}

	public byte getTypeId()
	{
		return 3;
	}

	public String toString() {
		return "" + this.data;
	}

	public NBTBase clone()
	{
		return new NBTTagInt(getName(), this.data);
	}

	public boolean equals(Object paramObject)
	{
		if (super.equals(paramObject)) {
			NBTTagInt localNBTTagInt = (NBTTagInt)paramObject;
			return this.data == localNBTTagInt.data;
		}
		return false;
	}

	public int hashCode()
	{
		return super.hashCode() ^ this.data;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NBTTagInt
 * JD-Core Version:		0.6.0
 */