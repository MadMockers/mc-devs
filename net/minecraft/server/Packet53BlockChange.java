package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet53BlockChange extends Packet
{
	public int a;
	public int b;
	public int c;
	public int material;
	public int data;

	public Packet53BlockChange()
	{
		this.lowPriority = true;
	}

	public Packet53BlockChange(int paramInt1, int paramInt2, int paramInt3, World paramWorld) {
		this.lowPriority = true;
		this.a = paramInt1;
		this.b = paramInt2;
		this.c = paramInt3;
		this.material = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3);
		this.data = paramWorld.getData(paramInt1, paramInt2, paramInt3);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.read();
		this.c = paramDataInputStream.readInt();
		this.material = paramDataInputStream.readShort();
		this.data = paramDataInputStream.read();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.write(this.b);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.writeShort(this.material);
		paramDataOutputStream.write(this.data);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 11;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet53BlockChange
 * JD-Core Version:		0.6.0
 */