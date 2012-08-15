package net.minecraft.server;

final class Vec3DPoolThreadLocal extends ThreadLocal
{
	protected Vec3DPool a()
	{
		return new Vec3DPool(300, 2000);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Vec3DPoolThreadLocal
 * JD-Core Version:		0.6.0
 */