package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagByte extends NBTBase
{
	public byte data;

	public NBTTagByte(String paramString)
	{
		super(paramString);
	}

	public NBTTagByte(String paramString, byte paramByte) {
		super(paramString);
		this.data = paramByte;
	}

	void write(DataOutput paramDataOutput)
	{
		paramDataOutput.writeByte(this.data);
	}

	void load(DataInput paramDataInput)
	{
		this.data = paramDataInput.readByte();
	}

	public byte getTypeId()
	{
		return 1;
	}

	public String toString() {
		return "" + this.data;
	}

	public NBTBase clone()
	{
		return new NBTTagByte(getName(), this.data);
	}

	public boolean equals(Object paramObject)
	{
		if (super.equals(paramObject)) {
			NBTTagByte localNBTTagByte = (NBTTagByte)paramObject;
			return this.data == localNBTTagByte.data;
		}
		return false;
	}

	public int hashCode()
	{
		return super.hashCode() ^ this.data;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NBTTagByte
 * JD-Core Version:		0.6.0
 */