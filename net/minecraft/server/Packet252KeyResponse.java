package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.security.PrivateKey;
import javax.crypto.SecretKey;

public class Packet252KeyResponse extends Packet
{
	private byte[] a = new byte[0];
	private byte[] b = new byte[0];
	private SecretKey c;

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = b(paramDataInputStream);
		this.b = b(paramDataInputStream);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		a(paramDataOutputStream, this.a);
		a(paramDataOutputStream, this.b);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 2 + this.a.length + 2 + this.b.length;
	}

	public SecretKey a(PrivateKey paramPrivateKey) {
		if (paramPrivateKey == null) {
			return this.c;
		}
		return this.c = MinecraftEncryption.a(paramPrivateKey, this.a);
	}

	public SecretKey d() {
		return a(null);
	}

	public byte[] b(PrivateKey paramPrivateKey) {
		if (paramPrivateKey == null) {
			return this.b;
		}
		return MinecraftEncryption.b(paramPrivateKey, this.b);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet252KeyResponse
 * JD-Core Version:		0.6.0
 */