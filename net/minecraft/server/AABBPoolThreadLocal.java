package net.minecraft.server;

final class AABBPoolThreadLocal extends ThreadLocal
{
	protected AABBPool a()
	{
		return new AABBPool(300, 2000);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.AABBPoolThreadLocal
 * JD-Core Version:		0.6.0
 */