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

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandException
 * JD-Core Version:		0.6.0
 */