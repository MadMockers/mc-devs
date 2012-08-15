package net.minecraft.server;

import java.util.List;

public class CommandMe extends CommandAbstract
{
	public String b()
	{
		return "me";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.me.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if (paramArrayOfString.length > 0) {
			String str = a(paramArrayOfString, 0);

			MinecraftServer.getServer().getServerConfigurationManager().sendAll(new Packet3Chat("* " + paramICommandListener.getName() + " " + str));
			return;
		}

		throw new ExceptionUsage("commands.me.usage", new Object[0]);
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		return a(paramArrayOfString, MinecraftServer.getServer().getPlayers());
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandMe
 * JD-Core Version:		0.6.0
 */