package net.minecraft.server;

public class ExceptionUnknownCommand extends CommandException
{
	public ExceptionUnknownCommand()
	{
		this("commands.generic.notFound", new Object[0]);
	}

	public ExceptionUnknownCommand(String paramString, Object[] paramArrayOfObject) {
		super(paramString, paramArrayOfObject);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ExceptionUnknownCommand
 * JD-Core Version:		0.6.0
 */