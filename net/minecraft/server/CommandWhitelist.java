package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommandWhitelist extends CommandAbstract
{
	public String b()
	{
		return "whitelist";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.whitelist.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if (paramArrayOfString.length >= 1) {
			if (paramArrayOfString[0].equals("on")) {
				MinecraftServer.getServer().getServerConfigurationManager().setHasWhitelist(true);
				a(paramICommandListener, "commands.whitelist.enabled", new Object[0]);
				return;
			}if (paramArrayOfString[0].equals("off")) {
				MinecraftServer.getServer().getServerConfigurationManager().setHasWhitelist(false);
				a(paramICommandListener, "commands.whitelist.disabled", new Object[0]);
				return;
			}if (paramArrayOfString[0].equals("list")) {
				paramICommandListener.sendMessage(paramICommandListener.a("commands.whitelist.list", new Object[] { Integer.valueOf(MinecraftServer.getServer().getServerConfigurationManager().getWhitelisted().size()), Integer.valueOf(MinecraftServer.getServer().getServerConfigurationManager().getSeenPlayers().length) }));
				paramICommandListener.sendMessage(a(MinecraftServer.getServer().getServerConfigurationManager().getWhitelisted().toArray(new String[0])));
				return;
			}if (paramArrayOfString[0].equals("add")) {
				if (paramArrayOfString.length < 2) {
					throw new ExceptionUsage("commands.whitelist.add.usage", new Object[0]);
				}

				MinecraftServer.getServer().getServerConfigurationManager().addWhitelist(paramArrayOfString[1]);
				a(paramICommandListener, "commands.whitelist.add.success", new Object[] { paramArrayOfString[1] });
				return;
			}if (paramArrayOfString[0].equals("remove")) {
				if (paramArrayOfString.length < 2) {
					throw new ExceptionUsage("commands.whitelist.remove.usage", new Object[0]);
				}

				MinecraftServer.getServer().getServerConfigurationManager().removeWhitelist(paramArrayOfString[1]);
				a(paramICommandListener, "commands.whitelist.remove.success", new Object[] { paramArrayOfString[1] });
				return;
			}if (paramArrayOfString[0].equals("reload")) {
				MinecraftServer.getServer().getServerConfigurationManager().reloadWhitelist();
				a(paramICommandListener, "commands.whitelist.reloaded", new Object[0]);
				return;
			}
		}

		throw new ExceptionUsage("commands.whitelist.usage", new Object[0]);
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if (paramArrayOfString.length == 1) {
			return a(paramArrayOfString, new String[] { "on", "off", "list", "add", "remove", "reload" });
		}

		if (paramArrayOfString.length == 2) {
			if (paramArrayOfString[0].equals("add")) {
				String[] arrayOfString1 = MinecraftServer.getServer().getServerConfigurationManager().getSeenPlayers();
				ArrayList localArrayList = new ArrayList();
				String str1 = paramArrayOfString[(paramArrayOfString.length - 1)];

				for (String str2 : arrayOfString1) {
					if ((a(str1, str2)) && (!MinecraftServer.getServer().getServerConfigurationManager().getWhitelisted().contains(str2))) {
						localArrayList.add(str2);
					}
				}

				return localArrayList;
			}

			if (paramArrayOfString[0].equals("remove")) {
				return a(paramArrayOfString, MinecraftServer.getServer().getServerConfigurationManager().getWhitelisted());
			}
		}

		return null;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandWhitelist
 * JD-Core Version:		0.6.0
 */