package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.security.PublicKey;

public class Packet253KeyRequest extends Packet
{
	private String a;
	private PublicKey b;
	private byte[] c = new byte[0];

	public Packet253KeyRequest()
	{
	}

	public Packet253KeyRequest(String paramString, PublicKey paramPublicKey, byte[] paramArrayOfByte) {
		this.a = paramString;
		this.b = paramPublicKey;
		this.c = paramArrayOfByte;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = a(paramDataInputStream, 20);
		this.b = MinecraftEncryption.a(b(paramDataInputStream));
		this.c = b(paramDataInputStream);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		a(this.a, paramDataOutputStream);
		a(paramDataOutputStream, this.b.getEncoded());
		a(paramDataOutputStream, this.c);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 2 + this.a.length() * 2 + 2 + this.b.getEncoded().length + 2 + this.c.length;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet253KeyRequest
 * JD-Core Version:		0.6.0
 */