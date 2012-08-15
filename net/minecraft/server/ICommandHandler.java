package net.minecraft.server;

import java.util.List;
import java.util.Map;

public abstract interface ICommandHandler
{
	public abstract void a(ICommandListener paramICommandListener, String paramString);

	public abstract List b(ICommandListener paramICommandListener, String paramString);

	public abstract List a(ICommandListener paramICommandListener);

	public abstract Map a();
}

/* 
 * Qualified Name:		 net.minecraft.server.ICommandHandler
 * JD-Core Version:		0.6.0
 */