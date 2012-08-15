package net.minecraft.server;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class RemoteStatusReply
{
	private ByteArrayOutputStream buffer;
	private DataOutputStream stream;

	public RemoteStatusReply(int paramInt)
	{
		this.buffer = new ByteArrayOutputStream(paramInt);
		this.stream = new DataOutputStream(this.buffer);
	}

	public void write(byte[] paramArrayOfByte) {
		this.stream.write(paramArrayOfByte, 0, paramArrayOfByte.length);
	}

	public void write(String paramString) {
		this.stream.writeBytes(paramString);
		this.stream.write(0);
	}

	public void write(int paramInt) {
		this.stream.write(paramInt);
	}

	public void write(short paramShort)
	{
		this.stream.writeShort(Short.reverseBytes(paramShort));
	}

	public byte[] getBytes()
	{
		return this.buffer.toByteArray();
	}

	public void reset() {
		this.buffer.reset();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.RemoteStatusReply
 * JD-Core Version:		0.6.0
 */