package net.minecraft.server;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommandBanList extends CommandAbstract
{
	public String b()
	{
		return "banlist";
	}

	public boolean b(ICommandListener paramICommandListener)
	{
		return ((MinecraftServer.getServer().getServerConfigurationManager().getIPBans().isEnabled()) || (MinecraftServer.getServer().getServerConfigurationManager().getNameBans().isEnabled())) && (super.b(paramICommandListener));
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.banlist.usage", new Object[0]);
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if ((paramArrayOfString.length >= 1) && (paramArrayOfString[0].equalsIgnoreCase("ips"))) {
			paramICommandListener.sendMessage(paramICommandListener.a("commands.banlist.ips", new Object[] { Integer.valueOf(MinecraftServer.getServer().getServerConfigurationManager().getIPBans().getEntries().size()) }));
			paramICommandListener.sendMessage(a(MinecraftServer.getServer().getServerConfigurationManager().getIPBans().getEntries().keySet().toArray()));
		} else {
			paramICommandListener.sendMessage(paramICommandListener.a("commands.banlist.players", new Object[] { Integer.valueOf(MinecraftServer.getServer().getServerConfigurationManager().getNameBans().getEntries().size()) }));
			paramICommandListener.sendMessage(a(MinecraftServer.getServer().getServerConfigurationManager().getNameBans().getEntries().keySet().toArray()));
		}
	}

	public List a(ICommandListener paramICommandListener, String[] paramArrayOfString)
	{
		if (paramArrayOfString.length == 1) {
			return a(paramArrayOfString, new String[] { "players", "ips" });
		}

		return null;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CommandBanList
 * JD-Core Version:		0.6.0
 */