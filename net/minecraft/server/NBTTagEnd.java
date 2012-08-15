package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagEnd extends NBTBase
{
	public NBTTagEnd()
	{
		super(null);
	}

	void load(DataInput paramDataInput)
	{
	}

	void write(DataOutput paramDataOutput)
	{
	}

	public byte getTypeId()
	{
		return 0;
	}

	public String toString() {
		return "END";
	}

	public NBTBase clone()
	{
		return new NBTTagEnd();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.NBTTagEnd
 * JD-Core Version:		0.6.0
 */