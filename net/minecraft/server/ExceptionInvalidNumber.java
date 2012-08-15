package net.minecraft.server;

public class ExceptionInvalidNumber extends CommandException
{
	public ExceptionInvalidNumber()
	{
		this("commands.generic.num.invalid", new Object[0]);
	}

	public ExceptionInvalidNumber(String paramString, Object[] paramArrayOfObject) {
		super(paramString, paramArrayOfObject);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ExceptionInvalidNumber
 * JD-Core Version:		0.6.0
 */