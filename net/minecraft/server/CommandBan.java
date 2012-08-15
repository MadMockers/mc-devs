package net.minecraft.server;

import java.util.List;

public class CommandBan extends CommandAbstract
{
	public String b()
	{
		return "ban";
	}

	public String a(ICommandListener paramICommandListener)
	{
		return paramICommandListener.a("commands.ban.usage", new Object[0]);
	}

	public boolean b(ICommandListener paramICommandListener)
	{
		return (MinecraftServer.getServer().getServerConfigurationManager().getNameBans().isEnabled()) && (super.b(paramICommandListener));
	}

	public void b(ICommandListener paramICommandListener, String[] paramArrayOfString) {
		if ((paramArrayOfString.length >= 1) && (paramArrayOfString[0].length() > 0)) {
			EntityPlayer localEntityPlayer = MinecraftServer.getServer().getServerConfigurationManager().f(paramArrayOfString[0]);
			BanEntry localBanEntry = new BanEntry(paramArrayOfString[0]);

			localBanEntry.setSource(paramICommandListener.getName());
			if (paramArrayOfString.length >= 2) {
				localBanEntry.setReason(a(paramArrayOfString, 1));
			}

			MinecraftServer.getServer().getServerConfigurationManager().getNameBans().add(localBanEntry);

			if (localEntityPlayer != null) {
				localEntityPlayer.netServerHandler.disconnect("You are banned from this server.");
			}

			a(paramICommandListener, "commands.ban.success", new Object[] { paramArrayOfString[0] });
			return;
		}

		throw new ExceptionUsage("commands.ban.usage", new Object[0]);
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
 * Qualified Name:		 net.minecraft.server.CommandBan
 * JD-Core Version:		0.6.0
 */