package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Arrays;

public class NBTTagByteArray extends NBTBase
{
	public byte[] data;

	public NBTTagByteArray(String paramString)
	{
		super(paramString);
	}

	public NBTTagByteArray(String paramString, byte[] paramArrayOfByte) {
		super(paramString);
		this.data = paramArrayOfByte;
	}

	void write(DataOutput paramDataOutput)
	{
		paramDataOutput.writeInt(this.data.length);
		paramDataOutput.write(this.data);
	}

	void load(DataInput paramDataInput)
	{
		int i = paramDataInput.readInt();
		this.data = new byte[i];
		paramDataInput.readFully(this.data);
	}

	public byte getTypeId()
	{
		return 7;
	}

	public String toString() {
		return "[" + this.data.length + " bytes]";
	}

	public NBTBase clone()
	{
		byte[] arrayOfByte = new byte[this.data.length];
		System.arraycopy(this.data, 0, arrayOfByte, 0, this.data.length);
		return new NBTTagByteArray(getName(), arrayOfByte);
	}

	public boolean equals(Object paramObject)
	{
		if (super.equals(paramObject)) {
			return Arrays.equals(this.data, ((NBTTagByteArray)paramObject).data);
		}
		return false;
	}

	public int hashCode()
	{
		return super.hashCode() ^ Arrays.hashCode(this.data);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NBTTagByteArray
 * JD-Core Version:		0.6.0
 */