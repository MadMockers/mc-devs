package net.minecraft.server;

import java.util.List;

public abstract interface ICrafting
{
	public abstract void a(Container paramContainer, List paramList);

	public abstract void a(Container paramContainer, int paramInt, ItemStack paramItemStack);

	public abstract void setContainerData(Container paramContainer, int paramInt1, int paramInt2);
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ICrafting
 * JD-Core Version:		0.6.0
 */