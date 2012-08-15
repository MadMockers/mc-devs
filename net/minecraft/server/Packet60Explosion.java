package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Packet60Explosion extends Packet
{
	public double a;
	public double b;
	public double c;
	public float d;
	public List e;
	private float f;
	private float g;
	private float h;

	public Packet60Explosion()
	{
	}

	public Packet60Explosion(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat, List paramList, Vec3D paramVec3D)
	{
		this.a = paramDouble1;
		this.b = paramDouble2;
		this.c = paramDouble3;
		this.d = paramFloat;
		this.e = new ArrayList(paramList);

		if (paramVec3D != null) {
			this.f = (float)paramVec3D.a;
			this.g = (float)paramVec3D.b;
			this.h = (float)paramVec3D.c;
		}
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readDouble();
		this.b = paramDataInputStream.readDouble();
		this.c = paramDataInputStream.readDouble();
		this.d = paramDataInputStream.readFloat();
		int i = paramDataInputStream.readInt();

		this.e = new ArrayList(i);

		int j = (int)this.a;
		int k = (int)this.b;
		int m = (int)this.c;
		for (int n = 0; n < i; n++) {
			int i1 = paramDataInputStream.readByte() + j;
			int i2 = paramDataInputStream.readByte() + k;
			int i3 = paramDataInputStream.readByte() + m;
			this.e.add(new ChunkPosition(i1, i2, i3));
		}

		this.f = paramDataInputStream.readFloat();
		this.g = paramDataInputStream.readFloat();
		this.h = paramDataInputStream.readFloat();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeDouble(this.a);
		paramDataOutputStream.writeDouble(this.b);
		paramDataOutputStream.writeDouble(this.c);
		paramDataOutputStream.writeFloat(this.d);
		paramDataOutputStream.writeInt(this.e.size());

		int i = (int)this.a;
		int j = (int)this.b;
		int k = (int)this.c;
		for (ChunkPosition localChunkPosition : this.e) {
			int m = localChunkPosition.x - i;
			int n = localChunkPosition.y - j;
			int i1 = localChunkPosition.z - k;
			paramDataOutputStream.writeByte(m);
			paramDataOutputStream.writeByte(n);
			paramDataOutputStream.writeByte(i1);
		}

		paramDataOutputStream.writeFloat(this.f);
		paramDataOutputStream.writeFloat(this.g);
		paramDataOutputStream.writeFloat(this.h);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 32 + this.e.size() * 3 + 3;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet60Explosion
 * JD-Core Version:		0.6.0
 */