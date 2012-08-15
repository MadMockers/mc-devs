package net.minecraft.server;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandPardonIP extends CommandAbstract
{
	public String b()
	{
		return "pardon-ip";
	}

	public boolean b(ICommandListener paramICommandListener)
	{
		return (MinecraftServer.getServer().getServerConfigurationManager().getIPBans().isEnabled()) && (super.b(paramICommandListener));
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.unbanip.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if ((paramArrayOfString.length == 1) && (paramArrayOfString[0].length() > 1)) {
			Matcher localMatcher = CommandBanIp.a.matcher(paramArrayOfString[0]);

			if (localMatcher.matches()) {
				MinecraftServer.getServer().getServerConfigurationManager().getIPBans().remove(paramArrayOfString[0]);
				a(paramICommandListener, "commands.unbanip.success", new Object[] { paramArrayOfString[0] });
				return;
			}
			throw new ExceptionInvalidSyntax("commands.unbanip.invalid", new Object[0]);
		}

		throw new ExceptionUsage("commands.unbanip.usage", new Object[0]);
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if (paramArrayOfString.length == 1) {
			return a(paramArrayOfString, MinecraftServer.getServer().getServerConfigurationManager().getIPBans().getEntries().keySet());
		}

		return null;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CommandPardonIP
 * JD-Core Version:		0.6.0
 */