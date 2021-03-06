package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Arrays;

public class NBTTagIntArray extends NBTBase
{
	public int[] data;

	public NBTTagIntArray(String paramString)
	{
		super(paramString);
	}

	public NBTTagIntArray(String paramString, int[] paramArrayOfInt) {
		super(paramString);
		this.data = paramArrayOfInt;
	}

	void write(DataOutput paramDataOutput)
	{
		paramDataOutput.writeInt(this.data.length);
		for (int k : this.data)
			paramDataOutput.writeInt(k);
	}

	void load(DataInput paramDataInput)
	{
		int i = paramDataInput.readInt();
		this.data = new int[i];
		for (int j = 0; j < i; j++)
			this.data[j] = paramDataInput.readInt();
	}

	public byte getTypeId()
	{
		return 11;
	}

	public String toString() {
		return "[" + this.data.length + " bytes]";
	}

	public NBTBase clone()
	{
		int[] arrayOfInt = new int[this.data.length];
		System.arraycopy(this.data, 0, arrayOfInt, 0, this.data.length);
		return new NBTTagIntArray(getName(), arrayOfInt);
	}

	public boolean equals(Object paramObject)
	{
		if (super.equals(paramObject)) {
			NBTTagIntArray localNBTTagIntArray = (NBTTagIntArray)paramObject;
			return ((this.data == null) && (localNBTTagIntArray.data == null)) || ((this.data != null) && (Arrays.equals(this.data, localNBTTagIntArray.data)));
		}
		return false;
	}

	public int hashCode()
	{
		return super.hashCode() ^ Arrays.hashCode(this.data);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.NBTTagIntArray
 * JD-Core Version:		0.6.0
 */