package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet250CustomPayload extends Packet
{
	public String tag;
	public int length;
	public byte[] data;

	public Packet250CustomPayload()
	{
	}

	public Packet250CustomPayload(String paramString, byte[] paramArrayOfByte)
	{
		this.tag = paramString;
		this.data = paramArrayOfByte;

		if (paramArrayOfByte != null) {
			this.length = paramArrayOfByte.length;

			if (this.length > 32767)
				throw new IllegalArgumentException("Payload may not be larger than 32k");
		}
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.tag = a(paramDataInputStream, 20);
		this.length = paramDataInputStream.readShort();

		if ((this.length > 0) && (this.length < 32767)) {
			this.data = new byte[this.length];
			paramDataInputStream.readFully(this.data);
		}
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		a(this.tag, paramDataOutputStream);
		paramDataOutputStream.writeShort((short)this.length);
		if (this.data != null)
			paramDataOutputStream.write(this.data);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 2 + this.tag.length() * 2 + 2 + this.length;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet250CustomPayload
 * JD-Core Version:		0.6.0
 */