package net.minecraft.server;

import java.util.List;
import java.util.Map;

public class CommandPardon extends CommandAbstract
{
	public String b()
	{
		return "pardon";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.unban.usage", new Object[0]);
	}

	public boolean b(ICommandListener paramICommandListener)
	{
		return (MinecraftServer.getServer().getServerConfigurationManager().getNameBans().isEnabled()) && (super.b(paramICommandListener));
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if ((paramArrayOfString.length == 1) && (paramArrayOfString[0].length() > 0)) {
			MinecraftServer.getServer().getServerConfigurationManager().getNameBans().remove(paramArrayOfString[0]);

			a(paramICommandListener, "commands.unban.success", new Object[] { paramArrayOfString[0] });
			return;
		}

		throw new ExceptionUsage("commands.unban.usage", new Object[0]);
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if (paramArrayOfString.length == 1) {
			return a(paramArrayOfString, MinecraftServer.getServer().getServerConfigurationManager().getNameBans().getEntries().keySet());
		}

		return null;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandPardon
 * JD-Core Version:		0.6.0
 */