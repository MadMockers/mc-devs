package net.minecraft.server;

public abstract interface ICommandListener
{
	public abstract String getName();

	public abstract void sendMessage(String paramString);

	public abstract boolean b(String paramString);

	public abstract String a(String paramString, Object[] paramArrayOfObject);
}

/* 
 * Qualified Name:		 net.minecraft.server.ICommandListener
 * JD-Core Version:		0.6.0
 */