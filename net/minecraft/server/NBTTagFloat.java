package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagFloat extends NBTBase
{
	public float data;

	public NBTTagFloat(String paramString)
	{
		super(paramString);
	}

	public NBTTagFloat(String paramString, float paramFloat) {
		super(paramString);
		this.data = paramFloat;
	}

	void write(DataOutput paramDataOutput)
	{
		paramDataOutput.writeFloat(this.data);
	}

	void load(DataInput paramDataInput)
	{
		this.data = paramDataInput.readFloat();
	}

	public byte getTypeId()
	{
		return 5;
	}

	public String toString() {
		return "" + this.data;
	}

	public NBTBase clone()
	{
		return new NBTTagFloat(getName(), this.data);
	}

	public boolean equals(Object paramObject)
	{
		if (super.equals(paramObject)) {
			NBTTagFloat localNBTTagFloat = (NBTTagFloat)paramObject;
			return this.data == localNBTTagFloat.data;
		}
		return false;
	}

	public int hashCode()
	{
		return super.hashCode() ^ Float.floatToIntBits(this.data);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NBTTagFloat
 * JD-Core Version:		0.6.0
 */