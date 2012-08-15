package net.minecraft.server;

public class ExceptionPlayerNotFound extends CommandException
{
	public ExceptionPlayerNotFound()
	{
		this("commands.generic.player.notFound", new Object[0]);
	}

	public ExceptionPlayerNotFound(String paramString, Object[] paramArrayOfObject) {
		super(paramString, paramArrayOfObject);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ExceptionPlayerNotFound
 * JD-Core Version:		0.6.0
 */