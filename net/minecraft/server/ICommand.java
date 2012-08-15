package net.minecraft.server;

import java.util.List;

public abstract interface ICommand extends Comparable
{
	public abstract String b();

	public abstract String a(ICommandListener paramICommandListener);

	public abstract List a();

	public abstract void b(ICommandListener paramICommandListener, String[] paramArrayOfString);

	public abstract boolean b(ICommandListener paramICommandListener);

	public abstract List a(ICommandListener paramICommandListener, String[] paramArrayOfString);
}

/* 
 * Qualified Name:		 net.minecraft.server.ICommand
 * JD-Core Version:		0.6.0
 */