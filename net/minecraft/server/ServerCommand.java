package net.minecraft.server;

public class ServerCommand
{
	public final String command;
	public final ICommandListener source;

	public ServerCommand(String paramString, ICommandListener paramICommandListener)
	{
		this.command = paramString;
		this.source = paramICommandListener;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ServerCommand
 * JD-Core Version:		0.6.0
 */