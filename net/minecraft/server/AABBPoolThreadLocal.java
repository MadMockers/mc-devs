package net.minecraft.server;

final class AABBPoolThreadLocal extends ThreadLocal
{
	protected AABBPool a()
	{
		return new AABBPool(300, 2000);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.AABBPoolThreadLocal
 * JD-Core Version:		0.6.0
 */