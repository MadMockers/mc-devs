package net.minecraft.server;

public class ExceptionInvalidSyntax extends CommandException
{
	public ExceptionInvalidSyntax()
	{
		this("commands.generic.snytax", new Object[0]);
	}

	public ExceptionInvalidSyntax(String paramString, Object[] paramArrayOfObject) {
		super(paramString, paramArrayOfObject);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ExceptionInvalidSyntax
 * JD-Core Version:		0.6.0
 */