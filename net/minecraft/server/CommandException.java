package net.minecraft.server;

public class CommandException extends RuntimeException
{
	private Object[] a;

	public CommandException(String paramString, Object[] paramArrayOfObject)
	{
		super(paramString);

		this.a = paramArrayOfObject;
	}

	public Object[] a() {
		return this.a;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandException
 * JD-Core Version:		0.6.0
 */