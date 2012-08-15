package net.minecraft.server;

import java.util.List;

public class CommandSay extends CommandAbstract
{
	public String b()
	{
		return "say";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.say.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if ((paramArrayOfString.length > 0) && (paramArrayOfString[0].length() > 0)) {
			String str = a(paramArrayOfString, 0);

			MinecraftServer.getServer().getServerConfigurationManager().sendAll(new Packet3Chat(String.format("[%s] %s", new Object[] { paramICommandListener.getName(), str })));
			return;
		}

		throw new ExceptionUsage("commands.say.usage", new Object[0]);
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if (paramArrayOfString.length >= 1) {
			return a(paramArrayOfString, MinecraftServer.getServer().getPlayers());
		}

		return null;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandSay
 * JD-Core Version:		0.6.0
 */